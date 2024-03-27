package com.example.academiacx.services.inter;

import com.example.academiacx.models.DirectorModel;

import java.util.List;

public interface DirectorService {

    List<DirectorModel> findAll();

    DirectorModel findById(Long id);

    DirectorModel save(DirectorModel director);

}

