package com.example.academiacx.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "tb_actor")
public class ActorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do diretor é obrigatório")
    private String name;

    @ManyToMany(mappedBy = "actors")
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

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    public ActorModel() {
    }

    public ActorModel(Long id, String name, List<MovieModel> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "ActorModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}