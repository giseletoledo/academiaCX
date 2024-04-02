package com.example.academiacx.models.dto;

import com.example.academiacx.models.UserModel;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ObjectMapperUtils {
    public static void populateUserDtos(final List<UserModel> users, final List<UserDto> userDTOs) {
        ModelMapper modelMapper = new ModelMapper();

        for (UserModel user : users) {
            UserDto userDTO = new UserDto();
            modelMapper.map(user, userDTO);
            userDTOs.add(userDTO);
        }
    }
}