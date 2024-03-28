package com.example.academiacx.services.inter;

import com.example.academiacx.models.FranchiseModel;

import java.util.List;
import java.util.Optional;

public interface FranchiseService {
    List<FranchiseModel> findAll();
    Optional<FranchiseModel> findById(Long id);
    FranchiseModel create(FranchiseModel franchise);
    void delete(Long id);
}




