package com.example.academiacx.controller;

import com.example.academiacx.models.StudioModel;
import com.example.academiacx.services.inter.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/studio")
public class StudioController {

    @Autowired
    private StudioService studioService;

    //    @PreAuthorize("hasRole('STUDIO_SELECT')")
    @GetMapping
    public ResponseEntity<List<StudioModel>> findAll()
    {
        List<StudioModel> response = studioService.list();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    //    @PreAuthorize("hasRole('STUDIO_SELECT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<StudioModel>> findById(@PathVariable Long id)
    {
        Optional<StudioModel> response = studioService.findById(id);

        return response.isPresent() ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Optional<StudioModel>> findById(@PathVariable String name)
    {
        Optional<StudioModel> response = studioService.findByName(name);

        return response.isPresent() ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    //    @PreAuthorize("hasRole('STUDIO_SAVE')")
    @PostMapping
    public ResponseEntity<StudioModel> save(@RequestBody StudioModel studioModel)
    {
        StudioModel response = studioService.create(studioModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    //    @PreAuthorize("hasRole('STUDIO_UPDATE')")
    @PutMapping
    public ResponseEntity<StudioModel> update(@RequestBody StudioModel studioModel)
    {
        StudioModel response = studioService.update(studioModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    //    @PreAuthorize("hasRole('STUDIO_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = studioService.delete(id);

        return Boolean.TRUE.equals(success) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}

