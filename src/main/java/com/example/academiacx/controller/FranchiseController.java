package com.example.academiacx.controller;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.FranchiseModel;
import com.example.academiacx.services.inter.FranchiseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping
    public List<FranchiseModel> findAllFranchises() {
        return franchiseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseModel> findFranchiseById(@PathVariable Long id) {
        return franchiseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FranchiseModel> createFranchise(@RequestBody FranchiseModel franchise) {
        FranchiseModel newFranchise = franchiseService.create(franchise);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFranchise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable Long id) {
        try {
            franchiseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
