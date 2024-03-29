package com.example.academiacx.facades.inter;

import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.dto.UserBookmarkDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookMarksFacade {

    UserBookmarkDto getFavorites(Long id);

    void saveFavorites(Long id, List<Long> movieIds);
}
