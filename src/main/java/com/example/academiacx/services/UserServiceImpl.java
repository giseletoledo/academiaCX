package com.example.academiacx.services;


import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.repository.UserRepository;
import com.example.academiacx.services.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public PasswordEncoder passwordEncoder()
   {
       return new BCryptPasswordEncoder();
   }

    @Override
    public List<UserModel> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel create(UserModel userModel) {

        userModel.setId(null);

        UserModel existUser = userRepository.findByUsername(userModel.getUsername());

        if (existUser != null)
        {
            throw new InvalidParamException("Usuario ja existe");
        }

        userModel.setPassword(passwordEncoder().encode(userModel.getPassword()));

        return userRepository.save(userModel);
    }

    @Override
    public UserModel update(UserModel userModel) {

        if(userModel.getId() == null || findById(userModel.getId()).isEmpty()) {
            throw new InvalidParamException("Id não encontrado");
        }

        return userRepository.save(userModel);
    }

    @Override
    public boolean delete(Long id) {

        findById(id);

        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Id informado não encontrado!");
        }

    }
}