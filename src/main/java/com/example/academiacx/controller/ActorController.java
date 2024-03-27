package com.example.academiacx.controller;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.ActorModel;
import com.example.academiacx.services.ActorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class ActorController {


    @Autowired
    private ActorServiceImpl actorService;

    @GetMapping
    public List<ActorModel> listarAtores() {
        return actorService.listActors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorModel> findById(@PathVariable Long id) {
        return actorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ActorModel> create(@RequestBody ActorModel ator) {
        ActorModel novoAtor = actorService.create(ator);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorModel> update(@PathVariable Long id, @RequestBody ActorModel ator) {
        ator.setId(id);
        try {
            ActorModel atorAtualizado = actorService.update(ator);
            return ResponseEntity.ok(atorAtualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            actorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }*/
}

