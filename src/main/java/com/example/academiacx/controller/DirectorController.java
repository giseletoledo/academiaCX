package com.example.academiacx.controller;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.DirectorModel;

import com.example.academiacx.models.dto.DirectorDto;
import com.example.academiacx.services.inter.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<DirectorModel> findAllDirectors() {
        return directorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorModel> findDirectorById(@PathVariable Long id) {
        return directorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DirectorModel> createDirector(@RequestBody DirectorDto directordto) {
        DirectorModel director = new DirectorModel(directordto.getName());
        DirectorModel newDirector = directorService.create(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDirector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        try {
            directorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
