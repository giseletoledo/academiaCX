package com.example.academiacx.facades;

import com.example.academiacx.facades.inter.UserFacade;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserDto;
import com.example.academiacx.services.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserServiceImpl userService;

    public UserFacadeImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public List<UserModel> findAll() {
        return userService.listUsers();
    }

    @Override
    public UserModel create(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setName(userDto.getName());
        userModel.setEmail(userDto.getEmail());
        userModel.setUsername(userDto.getUsername());
        userModel.setPassword(userDto.getPassword()); // tem criptografia na senha

        return userService.create(userModel);
    }

    @Override
    public UserModel update(Long id, UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(userDto.getName());
        userModel.setEmail(userDto.getEmail());
        userModel.setUsername(userModel.getUsername());
        userModel.setPassword(userModel.getPassword());

        return userService.update(userModel);
    }
    @Override
    public Boolean delete(Long id) {
        userService.delete(id);
        return true;
    }
    @Override
    public UserModel findByUsername(String username) {
        return userService.findByUsername(username);
    }
}