package com.example.academiacx.controller;

import com.example.academiacx.facades.inter.BookMarksFacade;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.handlers.exceptions.SaveFavoritesException;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.services.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookMarksFacade bookMarksFacade;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll()
    {
        List<UserModel> response = userService.listUsers();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<UserModel>> findById(@PathVariable Long id)
    {
        Optional<UserModel> response = userService.findById(id);

        return response.isPresent() ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/bookmark/{id}")
    public ResponseEntity<?> findFavoritesBookMark(@PathVariable Long id)
    {
        UserBookmarkDto response = bookMarksFacade.getFavorites(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody UserModel userModel)
    {
        UserModel response = userService.create(userModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<UserModel> update(@RequestBody UserModel userModel)
    {
        UserModel response = userService.update(userModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = userService.delete(id);

        return Boolean.TRUE.equals(success) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/bookmark")
    public ResponseEntity<String> saveFavoriteMovie(@PathVariable Long id, @RequestBody Long movieId) {
        try {
            bookMarksFacade.saveFavorites(id, movieId);
            return ResponseEntity.ok("Favorite movie saved successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save favorite movie: " + e.getMessage());
        }
    }

}