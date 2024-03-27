package com.example.academiacx.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
@Entity
@Table(name = "tb_streaming")
public class StreamingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome do Stremming é obrigatório")
    private String name;

    @NotBlank(message = "A Url do Stremming é obrigatória")
    private String url;

    @OneToMany(mappedBy = "streaming")
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }
    public StreamingModel() {
    }

    public StreamingModel(Long id, String name, String url, List<MovieModel> movies) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "StreamingModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", movies=" + movies +
                '}';
    }
}