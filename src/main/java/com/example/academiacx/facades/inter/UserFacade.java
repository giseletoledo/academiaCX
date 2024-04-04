package com.example.academiacx.facades.inter;

import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.AddressDto;
import com.example.academiacx.models.dto.UserDto;


import java.util.List;
import java.util.Optional;

public interface UserFacade {

    List<UserModel> findAll();
    Optional<UserModel> findById(Long id);
    UserModel create(UserDto userDto);
    UserModel update(Long id, UserDto userDto);
    Boolean delete(Long id);
    AddressDto getAddressByCep(String cep);
    UserModel findByUsername(String username);

    Optional<Object> findByEmail(String username);

    void updateUserCep(Long userId, String cep);
}
