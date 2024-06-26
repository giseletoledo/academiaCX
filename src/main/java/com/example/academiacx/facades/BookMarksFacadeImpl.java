package com.example.academiacx.facades;

import com.example.academiacx.facades.inter.BookMarksFacade;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.MovieDto;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.models.dto.UserDto;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookMarksFacadeImpl implements BookMarksFacade {

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;
    @Override
    public UserBookmarkDto getFavorites(Long id) {
        Logger logger = LoggerFactory.getLogger(getClass());
        UserBookmarkDto userBookmarkDto = new UserBookmarkDto();

        try {
            Optional<UserModel> userModelOptional = userService.findById(id);

            if (userModelOptional.isPresent()) {
                UserModel userModel = userModelOptional.get();
                List<MovieModel> movieFavorites = movieService.getUser(userModel.getId());

                userBookmarkDto.setUserDto(new UserDto(userModel));
                userBookmarkDto.setMovies(movieFavorites);
                userBookmarkDto.setDirectors(new ArrayList<DirectorModel>());
            } else {
                throw new ResourceNotFoundException("User with id " + id + " not found");
            }
        } catch (ResourceNotFoundException e) {
            logger.error("ResourceNotFoundException: {}", e.getMessage());

        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
        }

        return userBookmarkDto;
    }
    public void saveFavorites(Long userId, List<Long> movieIds) {
        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            Optional<UserModel> userModelOptional = userService.findById(userId);
            if (userModelOptional.isPresent()) {
                UserModel user = userModelOptional.get();
                UserBookmarkDto userBookmarkDto = new UserBookmarkDto();
                UserDto userDto = new UserDto(user);

                for (Long movieId : movieIds) {
                    Optional<MovieModel> movieOptional = movieService.findById(movieId);
                    if (movieOptional.isPresent()) {
                        MovieModel movie = movieOptional.get();
                        user.addFavoriteMovie(movie);
                    } else {
                        throw new ResourceNotFoundException("Movie with id " + movieId + " not found");
                    }
                }

                // Atualiza o usuário no banco de dados
                userService.update(user);
                logger.info("Favorite movies saved successfully for user with id {}", userId);

                // Adiciona os dados ao UserBookmarkDto
                userBookmarkDto.setUserDto(userDto);
                userBookmarkDto.setMovies(user.getFavoritesMovies());
                userBookmarkDto.setDirectors(new ArrayList<DirectorModel>());
            } else {
                throw new ResourceNotFoundException("User with id " + userId + " not found");
            }
        } catch (ResourceNotFoundException e) {
            logger.error("ResourceNotFoundException: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
        }
    }


}