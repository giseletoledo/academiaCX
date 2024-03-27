package com.example.academiacx.services;

import com.example.academiacx.models.MovieModel;
import com.example.academiacx.repository.MovieRepository;
import com.example.academiacx.services.inter.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<MovieModel> list() {
        return null;
    }

    @Override
    public Optional<MovieModel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public MovieModel create(MovieModel movieModel) {
        return null;
    }

    @Override
    public MovieModel update(MovieModel movieModel) {
        return null;
    }

    @Override
    public List<MovieModel> getUser(Long id) {

        //Montar Custom Query
        return (List<MovieModel>) movieRepository.getReferenceById(id);
    }
}
