package com.example.academiacx.services;

import com.example.academiacx.models.GenreModel;
import com.example.academiacx.repository.GenreRepository;
import com.example.academiacx.services.inter.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreModel> list() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<GenreModel> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public GenreModel create(GenreModel genreModel) {
        if (genreModel == null) {
            throw new IllegalArgumentException("O gênero a ser criado não pode ser nulo");
        }

        return genreRepository.save(genreModel);
    }


    @Override
    public GenreModel update(GenreModel genreModel) {
        return genreRepository.save(genreModel);
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
