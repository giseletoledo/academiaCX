package com.example.academiacx.controller;

import com.example.academiacx.facades.UserFacadeImpl;
import com.example.academiacx.facades.inter.BookMarksFacade;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.UserBookmarkRequest;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.ObjectMapperUtils;
import com.example.academiacx.models.dto.ResponseDto;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.models.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private final ModelMapper modelMapper;

    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    private UserFacadeImpl userFacade;

    @Autowired
    private BookMarksFacade bookMarksFacade;
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<UserModel> users = userFacade.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto("Não tem usuários cadastrados", 404));
        }

        List<UserDto> userDTOs = new ArrayList<>();
        ObjectMapperUtils.populateUserDtos(users, userDTOs);
        return ResponseEntity.ok(userDTOs);
    }

   /* @GetMapping
    public ResponseEntity<List<UserModel>> findAll()
    {
        List<UserModel> response = userFacade.findAll();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }*/

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<UserDto>> findById(@PathVariable Long id) {
        Optional<UserModel> response = userFacade.findById(id);

        if (response.isPresent()) {
            UserModel user = response.get();
            UserDto userDTO = modelMapper.map(user, UserDto.class);
            return ResponseEntity.ok(Optional.of(userDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/bookmark/{id}")
    public ResponseEntity<?> findFavoritesBookMark(@PathVariable Long id)
    {
        UserBookmarkDto response = bookMarksFacade.getFavorites(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody UserDto userDto) {
        if (userDto == null) {
            return ResponseEntity.badRequest().build();
        }

        UserModel response = userFacade.create(userDto);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (id == null || userDto == null) {
            return ResponseEntity.badRequest().build();
        }

        UserModel updatedUser = userFacade.update(id, userDto);

        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = userFacade.delete(id);

        return Boolean.TRUE.equals(success) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/bookmark")
    public ResponseEntity<String> saveFavoriteMovies(@PathVariable Long id, @RequestBody UserBookmarkRequest requestBody) {
        try {
            List<Long> movieIds = requestBody.getMovieIds();

            bookMarksFacade.saveFavorites(id, movieIds);

            return ResponseEntity.ok("Favorite movies saved successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save favorite movies: " + e.getMessage());
        }
    }

}