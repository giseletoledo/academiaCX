package com.example.academiacx.models;

import java.util.List;

public class UserBookmarkRequest {
    private List<Long> movieIds;

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }
}
