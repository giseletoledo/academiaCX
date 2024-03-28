package com.example.academiacx.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
@Entity
@Table(name = "tb_studio")
public class StudioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome do Estudio é obrigatório")
    private String name;

    private String country;
    @OneToMany(mappedBy = "studio")
    @JsonIgnore
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    public StudioModel() {
    }

    public StudioModel(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public StudioModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudioModel(Long id, String name, String country, List<MovieModel> movies) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.movies = movies;
    }
    @Override
    public String toString() {
        return "StudioModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", movies=" + movies +
                '}';
    }
}