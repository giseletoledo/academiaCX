package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.repository.MovieRepository;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
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
        return movieRepository.save(movieModel);
    }

    @Override
    public MovieModel update(MovieModel movieModel) {
        return movieRepository.save(movieModel);
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
