package com.example.academiacx.facades.inter;

import com.example.academiacx.models.dto.UserBookmarkDto;

public interface BookMarksFacade {

    UserBookmarkDto getFavorites(Long id);
}
