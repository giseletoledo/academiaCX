package com.example.academiacx.services.inter;

import com.example.academiacx.models.StreamingModel;

import java.util.List;
import java.util.Optional;

public interface StreamingService {
    List<StreamingModel> findAll();
    Optional<StreamingModel> findById(Long id);
    StreamingModel create(StreamingModel streaming);
    void delete(Long id);
}
