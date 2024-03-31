package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.repository.MovieRepository;
import com.example.academiacx.services.inter.FranchiseService;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserService userService;

    @Autowired
    FranchiseService franchiseService;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);


    @Override
    public List<MovieModel> list() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieModel> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public MovieModel create(MovieModel movieModel) {

        MovieModel movieSaved = movieRepository.save(movieModel);
        logger.info("Filme salvo com sucesso: {}", movieSaved.getTitle());
        return movieSaved;
    }

    @Override
    public MovieModel update(MovieModel movie) {

        if(movie.getId() == null || findById(movie.getId()).isEmpty()) {
            throw new InvalidParamException("Id não encontrado");
        }
            logger.info("Preparando para atualizar o filme: {}", movie.getTitle());
            // Outras operações antes de salvar, se necessário
           MovieModel updateMovie = movieRepository.save(movie);
            logger.info("Filme atualizado com sucesso: {}", movie.getTitle());
        return updateMovie;
    }

    @Override
    public List<MovieModel> getUser(Long userId) {
        Optional<UserModel> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            return user.getFavoritesMovies();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void delete(Long id) {
        Optional<MovieModel> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Movie not found with id: " + id);
        }
    }
}
