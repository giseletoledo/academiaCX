package com.example.academiacx.services.inter;

import com.example.academiacx.models.StudioModel;

import java.util.List;

public interface StudioService {
    List<StudioModel> findAll();

    StudioModel findById(Long id);

    StudioModel save(StudioModel studio);

}

