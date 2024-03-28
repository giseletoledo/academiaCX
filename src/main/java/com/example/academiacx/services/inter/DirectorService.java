package com.example.academiacx.services.inter;

import com.example.academiacx.models.DirectorModel;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    List<DirectorModel> findAll();
    Optional<DirectorModel> findById(Long id);
    DirectorModel create(DirectorModel director);
    void delete(Long id);
}
