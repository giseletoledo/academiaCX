package com.example.academiacx.models.dto;

import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.models.MovieModel;

import java.util.List;
public class UserBookmarkDto {

    public UserBookmarkDto() {
    }

    public UserBookmarkDto(UserDto userDto, List<MovieModel> movies, List<DirectorModel> directors) {
        this.userDto = userDto;
        this.movies = movies;
        this.directors = directors;
    }

    private UserDto userDto;
    private List<MovieModel> movies;

    private List<DirectorModel> directors;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    public List<DirectorModel> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorModel> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "UserBookmarkDto{" +
                "userDto=" + userDto +
                ", movies=" + movies +
                ", directors=" + directors +
                '}';
    }
}
