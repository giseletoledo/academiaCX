package com.example.academiacx.services.inter;

import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> listUsers();

    Optional<UserModel> findById(Long id);

    UserModel create(UserModel userModel);

    UserModel update(UserModel userModel);

    boolean delete(Long id);

    UserModel findByUsername(String username);

    AddressDto getAddressWithFeignByCep(String cep, Long userId);
}