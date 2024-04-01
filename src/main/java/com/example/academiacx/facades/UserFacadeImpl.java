package com.example.academiacx.facades;

import com.example.academiacx.facades.inter.UserFacade;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserDto;
import com.example.academiacx.services.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
        userModel.setUsername(userDto.getUsername());
        userModel.setPassword(hashPassword(userDto.getPassword())); // criptografia da senha

        return userService.create(userModel);
    }


    @Override
    public UserModel update(Long id, UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setUsername(userModel.getUsername());
        userModel.setPassword(hashPassword(userModel.getPassword()));

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

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
