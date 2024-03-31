package com.example.academiacx.models;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_movie")
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Titulo é obrigatório")
    private String title;

    @NotNull(message = "O Genero é Obrigatório")
    @Valid
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    @NotNull(message = "O Estudio deve ser informado")
    @Valid
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioModel studio;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private FranchiseModel franchise;

    @NotNull
    @Valid
    @ManyToMany(mappedBy = "movies", cascade = CascadeType.MERGE)
    private List<DirectorModel> directors;

    @ManyToOne
    @JoinColumn(name = "streaming_id")
    private StreamingModel streaming;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<ActorModel> actors;

    public MovieModel() {
    }

    public MovieModel(Long id, String title, GenreModel genre, StudioModel studio, List<DirectorModel> directors, StreamingModel streaming, List<ActorModel> actors) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.studio = studio;
        this.directors = directors;
        this.streaming = streaming;
        this.actors = actors;
    }

    public MovieModel(String title, GenreModel genre, StudioModel studio) {
        this.title = title;
        this.genre = genre;
        this.studio = studio;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    public List<DirectorModel> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorModel> directors) {
        this.directors = directors;
    }

    public StreamingModel getStreaming() {
        return streaming;
    }

    public void setStreaming(StreamingModel streaming) {
        this.streaming = streaming;
    }

    public List<ActorModel> getActors() {
        return actors;
    }

    public void setActors(List<ActorModel> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}