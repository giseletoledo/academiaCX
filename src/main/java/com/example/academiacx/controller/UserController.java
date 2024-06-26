package com.example.academiacx.controller;

import com.example.academiacx.dto.ResponseDto;
import com.example.academiacx.facades.inter.BookMarksFacade;
import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.UserBookmarkRequest;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.AddressDto;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.repository.UserRepository;
import com.example.academiacx.services.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private BookMarksFacade bookMarksFacade;

    @Autowired
    private UserRepository userRepository;

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
    @PostMapping("/cep")
    public ResponseEntity<ResponseDto> updateAddress(@RequestBody Map<String, Object> requestBody) {
        Long userId = Long.valueOf(requestBody.get("userId").toString());
        String cep = requestBody.get("cep").toString();

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + userId));

        // Remover qualquer caractere não numérico do CEP
        cep = cep.replaceAll("[^\\d.]", "");

        // Verificar se o CEP tem o tamanho correto (8 caracteres)
        if (cep.length() != 8) {
            throw new InvalidParamException("CEP inválido: " + cep);
        }

        AddressDto addressDto = userService.getAddressWithFeignByCep(cep, userId);

        user.getAddress().setCep(addressDto.getCep());
        user.getAddress().setStreet(addressDto.getStreet());
        user.getAddress().setDistrict(addressDto.getDistrict());
        user.getAddress().setCity(addressDto.getCity());
        user.getAddress().setState(addressDto.getState());

        userRepository.save(user);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Endereço atualizado com sucesso");
        responseDto.setStatus(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


}