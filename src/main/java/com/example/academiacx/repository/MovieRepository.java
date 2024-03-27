package com.example.academiacx.repository;

import com.example.academiacx.models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieModel, Long> {
}
