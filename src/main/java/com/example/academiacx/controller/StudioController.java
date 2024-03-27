package com.example.academiacx.controller;

import com.example.academiacx.models.StudioModel;
import com.example.academiacx.services.inter.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studios")
public class StudioController {

    @Autowired
    private StudioService studioService;

    @GetMapping
    public List<StudioModel> findAll() {
        return studioService.findAll();
    }

    @GetMapping("/{id}")
    public StudioModel findById(@PathVariable Long id) {
        return studioService.findById(id);
    }

    @PostMapping
    public StudioModel save(@RequestBody StudioModel studio) {
        return studioService.save(studio);
    }

   /* @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studioService.deleteById(id);
    }*/
}

