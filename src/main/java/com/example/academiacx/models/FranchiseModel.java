package com.example.academiacx.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_franchise")
public class FranchiseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da franquia é obrigatória")
    private String name;

    @NotNull(message = "O Genero é obrigatório")
    @Valid
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    @NotNull(message = "O Studio é obrigatório")
    @Valid
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioModel studio;

    //@NotNull(message = "Os Filmes da franquia é obrigatório")
    @Valid
    @JsonIgnore
    @OneToMany(mappedBy = "franchise", cascade = CascadeType.PERSIST)
    private List<MovieModel> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenreModel getGenre() {
        return genre;
    }

    public void setGenre(GenreModel genre) {
        this.genre = genre;
    }

    public StudioModel getStudio() {
        return studio;
    }

    public void setStudio(StudioModel studio) {
        this.studio = studio;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }
    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }
    public FranchiseModel() {
    }

    public FranchiseModel(String name) {
        this.name = name;
    }

    public FranchiseModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FranchiseModel(String name, GenreModel genre, StudioModel studio, List<MovieModel> movies) {
        this.name = name;
        this.genre = genre;
        this.studio = studio;
        this.movies = movies;
    }

    public FranchiseModel(Long id, String name, GenreModel genre, StudioModel studio, List<MovieModel> movies) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.studio = studio;
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "FranchiseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';  // Exclude genre, studio, and movies
    }
}