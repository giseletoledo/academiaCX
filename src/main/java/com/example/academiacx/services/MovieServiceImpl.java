package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.*;
import com.example.academiacx.repository.*;
import com.example.academiacx.services.inter.FranchiseService;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    StudioRepository studioRepository;

    @Autowired
    FranchiseRepository franchiseRepository;

    @Autowired
    UserService userService;

    @Autowired
    FranchiseService franchiseService;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    StreamingRepository streamingRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public List<MovieModel> list() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieModel> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public MovieModel create(MovieModel movieModel) {
        // Verificar se o estúdio está cadastrado
        Optional<StudioModel> studio = studioRepository.findById(movieModel.getStudio().getId());
        if (studio.isEmpty()) {
            logger.error("Estúdio não encontrado com ID S: {}", movieModel.getStudio().getId());
            // Tratar a situação em que o estúdio não está cadastrado
            throw new InvalidParamException("Estúdio não encontrado com ID: " + movieModel.getStudio().getId());
        }

        // Verificar se os atores estão cadastrados
        for (ActorModel actor : movieModel.getActors()) {
            Optional<ActorModel> actorOptional = actorRepository.findById(actor.getId());
            if (actorOptional.isEmpty()) {
                logger.error("Ator não encontrado com ID A: {}", actor.getId());
                // Tratar a situação em que um ator não está cadastrado
                throw new InvalidParamException("Ator não encontrado com ID: " + actor.getId());
            }
        }

        // Verificar se os diretores estão cadastrados
        for (DirectorModel director : movieModel.getDirectors()) {
            Optional<DirectorModel> directorOptional = directorRepository.findById(director.getId());
            if (directorOptional.isEmpty()) {
                logger.error("Diretor não encontrado com ID: {}", director.getId());
                // Tratar a situação em que um diretor não está cadastrado
                throw new InvalidParamException("Diretor não encontrado com ID DR: " + director.getId());
            }
        }

        // Verificar se os franquias estão cadastradas
        Optional<FranchiseModel> franchiseOptinal = franchiseRepository.findById(movieModel.getFranchise().getId());
        if (franchiseOptinal.isEmpty()) {
            logger.error("Franquia não encontrado com ID: {}", movieModel.getFranchise().getId());
            throw new InvalidParamException("Franquia não encontrado com ID F: " + movieModel.getFranchise().getId());
        }

        Optional<StreamingModel> streamingOptinal = streamingRepository.findById(movieModel.getStreaming().getId());
        if (streamingOptinal.isEmpty()) {
            logger.error("Streaming não encontrado com ID: {}", movieModel.getStreaming().getId());
            throw new InvalidParamException("Streaming não encontrado com ID ST: " + movieModel.getStreaming().getId());
        }

        // Verificar se o gênero está cadastrado
        Optional<GenreModel> genre = genreRepository.findById(movieModel.getGenre().getId());
        if (genre.isEmpty()) {
            logger.error("Gênero não encontrado com ID: {}", movieModel.getGenre().getId());
            // Tratar a situação em que o gênero não está cadastrado
            throw new InvalidParamException("Gênero não encontrado com ID GR: " + movieModel.getGenre().getId());
        }

        // Se todas as validações passaram, salvar o filme
        MovieModel movieSaved = movieRepository.save(movieModel);
        logger.info("Filme salvo com sucesso: {}", movieSaved.getTitle());
        return movieSaved;
    }

    @Override
    public MovieModel update(MovieModel movie) {

        if(movie.getId() == null || findById(movie.getId()).isEmpty()) {
            throw new InvalidParamException("Id não encontrado");
        }
            logger.info("Preparando para atualizar o filme: {}", movie.getTitle());
            // Outras operações antes de salvar, se necessário
           MovieModel updateMovie = movieRepository.save(movie);
            logger.info("Filme atualizado com sucesso: {}", movie.getTitle());
        return updateMovie;
    }

    @Override
    public List<MovieModel> getUser(Long userId) {
        Optional<UserModel> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            return user.getFavoritesMovies();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void delete(Long id) {
        Optional<MovieModel> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Movie not found with id: " + id);
        }
    }
}
