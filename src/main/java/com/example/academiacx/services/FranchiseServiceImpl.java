package com.example.academiacx.services;

import com.example.academiacx.models.FranchiseModel;
import com.example.academiacx.models.GenreModel;
import com.example.academiacx.models.StudioModel;
import com.example.academiacx.repository.FranchiseRepository;
import com.example.academiacx.services.inter.FranchiseService;
import com.example.academiacx.services.inter.GenreService;
import com.example.academiacx.services.inter.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private StudioService studioService;

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
        // Verificar se o gênero da franquia está nulo
        if (franchise.getGenre() == null) {
            throw new IllegalArgumentException("O gênero da franquia não pode ser nulo");
        }

        // Verificar se o estúdio da franquia está nulo
        if (franchise.getStudio() == null) {
            throw new IllegalArgumentException("O estúdio da franquia não pode ser nulo");
        }

        // Buscar o gênero pelo ID e lançar uma exceção se não for encontrado
        GenreModel genre = genreService.findById(franchise.getGenre().getId())
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado"));

        // Buscar o estúdio pelo ID e lançar uma exceção se não for encontrado
        StudioModel studio = studioService.findById(franchise.getStudio().getId())
                .orElseThrow(() -> new IllegalArgumentException("Estúdio não encontrado"));

        // Definir o gênero e o estúdio na franquia
        franchise.setGenre(genre);
        franchise.setStudio(studio);

        // Salvar a franquia no banco de dados
        return franchiseRepository.save(franchise);
    }


    @Override
    public void delete(Long id) {
        franchiseRepository.deleteById(id);
    }
}
