package com.example.academiacx.controller;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.services.inter.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieModel> findAllMovies() {
        return movieService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> findMovieById(@PathVariable Long id) {
        return movieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieModel> createMovie(@RequestBody MovieModel movie) {
        MovieModel newMovie = movieService.create(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieModel> updateMovie(@PathVariable Long id, @RequestBody MovieModel movie) {
        movie.setId(id);
        try {
            MovieModel updatedMovie = movieService.update(movie);
            return ResponseEntity.ok(updatedMovie);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public List<MovieModel> getUserMovies(@PathVariable Long id) {
        return movieService.getUser(id);
    }
}
