package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.StudioModel;
import com.example.academiacx.repository.StudioRepository;
import com.example.academiacx.services.inter.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {

    @Autowired
    private StudioRepository studioRepository;

    @Override
    public List<StudioModel> findAll() {
        return studioRepository.findAll();
    }

    @Override
    public StudioModel findById(Long id) {
        return studioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Studio not found with id: " + id));
    }

    @Override
    public StudioModel save(StudioModel studio) {
        return studioRepository.save(studio);
    }

 /*   @Override
    public void deleteById(Long id) {
        studioRepository.deleteById(id);
    }*/
}

