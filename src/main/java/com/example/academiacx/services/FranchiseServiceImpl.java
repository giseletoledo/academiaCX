package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.models.FranchiseModel;
import com.example.academiacx.models.GenreModel;
import com.example.academiacx.models.StudioModel;
import com.example.academiacx.repository.FranchiseRepository;
import com.example.academiacx.repository.GenreRepository;
import com.example.academiacx.repository.StudioRepository;
import com.example.academiacx.services.inter.FranchiseService;
import com.example.academiacx.services.inter.GenreService;
import com.example.academiacx.services.inter.StudioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    StudioRepository studioRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private StudioService studioService;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);


    @Override
    public List<FranchiseModel> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Optional<FranchiseModel> findById(Long id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public FranchiseModel create(FranchiseModel franchise) {
        Optional<StudioModel> studioOptional = studioRepository.findById(franchise.getStudio().getId());
        StudioModel studio = studioOptional.orElseThrow(() -> {
            logger.error("Estúdio não encontrado com ID: {}", franchise.getStudio().getId());
            return new InvalidParamException("Estúdio não encontrado com ID: " + franchise.getStudio().getId());
        });

        Optional<GenreModel> genreOptional = genreRepository.findById(franchise.getGenre().getId());
        GenreModel genre = genreOptional.orElseThrow(() -> {
            logger.error("Gênero não encontrado com ID: {}", franchise.getGenre().getId());
            return new InvalidParamException("Gênero não encontrado com ID: " + franchise.getGenre().getId());
        });

        franchise.setGenre(genre);
        franchise.setStudio(studio);

        return franchiseRepository.save(franchise);
    }




    @Override
    public void delete(Long id) {
        franchiseRepository.deleteById(id);
    }
}
