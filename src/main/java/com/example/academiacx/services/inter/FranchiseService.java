package com.example.academiacx.services.inter;

import com.example.academiacx.models.FranchiseModel;

import java.util.List;

public interface FranchiseService {

    List<FranchiseModel> findAll();

    FranchiseModel findById(Long id);

    FranchiseModel save(FranchiseModel franchise);

}

