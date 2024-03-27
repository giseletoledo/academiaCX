package com.example.academiacx.services;

import com.example.academiacx.models.ActorModel;

import java.util.List;
import java.util.Optional;

public interface ActorServiceImpl {

    List<ActorModel> listActors();

    Optional<ActorModel> findById(Long id);

    ActorModel create(ActorModel actor);

    ActorModel update(ActorModel actor);

}