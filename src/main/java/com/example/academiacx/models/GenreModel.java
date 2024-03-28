package com.example.academiacx.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_genre")
public class GenreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Genero é obrigatório")
    private String genreName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public GenreModel() {
    }

    public GenreModel(String genreName) {
        this.genreName = genreName;
    }

    public GenreModel(Long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "GenreModel{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}