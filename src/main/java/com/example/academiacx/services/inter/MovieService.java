package com.example.academiacx.services.inter;

import com.example.academiacx.models.MovieModel;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieModel> list();

    Optional<MovieModel> findById(Long id);

    MovieModel create(MovieModel movieModel);

    MovieModel update(MovieModel movieModel);

    List<MovieModel> getUser(Long id);

    void delete(Long id);
}
