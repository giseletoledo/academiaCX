package com.example.academiacx.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_movie")
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;

    private StudioModel studio;

    private FranchiseModel franchise;

    private List<DirectorModel> directors;

    private List<StreamingModel> streamingServices;

    public MovieModel() {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

    public List<StreamingModel> getStreamingServices() {
        return streamingServices;
    }

    public void setStreamingServices(List<StreamingModel> streamingServices) {
        this.streamingServices = streamingServices;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", studio=" + studio +
                ", franchise=" + franchise +
                ", directors=" + directors +
                ", streamingServices=" + streamingServices +
                '}';
    }
}

