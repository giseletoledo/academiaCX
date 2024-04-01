package com.example.academiacx.facades.inter;

import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

    Optional<UserModel> findById(Long id);

    List<UserModel> findAll();

    UserModel create(UserDto userDto);

    UserModel update(Long id, UserDto userDto);

    Boolean delete(Long id);

    UserModel findByUsername(String username);
}

