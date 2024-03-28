package com.example.academiacx.controller;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.StreamingModel;
import com.example.academiacx.services.inter.StreamingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/streamings")
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @GetMapping
    public List<StreamingModel> findAllStreamings() {
        return streamingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingModel> findStreamingById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<StreamingModel> createStreaming(@RequestBody StreamingModel streaming) {
        StreamingModel newStreaming = streamingService.create(streaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStreaming);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id) {
        try {
            streamingService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
