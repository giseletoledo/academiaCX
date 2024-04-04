package com.example.academiacx.models.dto;

import java.util.List;

public class MovieDto {
    private Long id;
    private String title;
    private String genre;
    private String studio;
    private List<String> directors;
    private String streaming;
    private List<String> actors;

    public MovieDto() {
    }

    public MovieDto(Long id, String title, String genre, String studio, List<String> directors, String streaming, List<String> actors) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.studio = studio;
        this.directors = directors;
        this.streaming = streaming;
        this.actors = actors;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public String getStreaming() {
        return streaming;
    }

    public void setStreaming(String streaming) {
        this.streaming = streaming;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", studio='" + studio + '\'' +
                ", directors=" + directors +
                ", streaming='" + streaming + '\'' +
                ", actors=" + actors +
                '}';
    }
}

