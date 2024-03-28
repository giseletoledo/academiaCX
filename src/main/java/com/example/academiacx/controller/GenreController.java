package com.example.academiacx.controller;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.GenreModel;
import com.example.academiacx.services.GenreServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreServiceImpl genreService;

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @GetMapping
    public List<GenreModel> listGenres() {
        logger.info("Entrando no método listGenres()");
        List<GenreModel> genres = genreService.list();
        logger.info("Número de gêneros encontrados: {}", genres.size());
        return genres;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreModel> findById(@PathVariable Long id) {
        return genreService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GenreModel> create(@RequestBody GenreModel genre) {
        GenreModel newGenre = genreService.create(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreModel> update(@PathVariable Long id, @RequestBody GenreModel genre) {
        genre.setId(id);
        try {
            GenreModel updatedGenre = genreService.update(genre);
            return ResponseEntity.ok(updatedGenre);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            genreService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

