package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.ActorModel;
import com.example.academiacx.repository.ActorRepository;
import com.example.academiacx.services.inter.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<ActorModel> listActors() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<ActorModel> findById(Long id) {
        return Optional.ofNullable(actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id)));
    }
    @Override
    public ActorModel create(ActorModel actor) {
        return actorRepository.save(actor);
    }
    @Override
    public ActorModel update(ActorModel actor) {
        if (actor.getId() == null) {
            throw new InvalidParamException("Actor ID must not be null for update");
        }

        return actorRepository.findById(actor.getId())
                .map(existingActor -> {
                    // Copy relevant fields from actor to existingActor
                    existingActor.setName(actor.getName());
                    // ...copy other fields as needed
                    return actorRepository.save(existingActor);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actor.getId()));
    }
    @Override
    public void delete(ActorModel actor) { actorRepository.delete(actor);}
}
