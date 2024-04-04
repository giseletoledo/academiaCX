package com.example.academiacx.facades;

import com.example.academiacx.facades.inter.UserFacade;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.AddressDto;
import com.example.academiacx.models.dto.UserDto;
import com.example.academiacx.services.inter.CepService;
import com.example.academiacx.services.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    UserService userService;

    @Autowired
    CepService cepService;
    @Override
    public List<UserModel> findAll() {
        return null;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserModel create(UserDto userDto) {
        return null;
    }

    @Override
    public UserModel update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
    @Override
    public UserModel findByUsername(String username) {
        return null;
    }

    @Override
    public Optional<Object> findByEmail(String username) {
        return Optional.empty();
    }

    @Override
    public AddressDto getAddressByCep(String cep) {
        // Chama o serviço de CEP para obter o endereço
        AddressDto addressDto = cepService.getAddressWithFeignByCep(cep);

        // Retorna o endereço
        return addressDto;
    }
    @Override
    public void updateUserCep(Long userId, String cep) {
        // Verifica se o usuário com o ID fornecido existe
        UserModel userModel = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // Atualiza o CEP do usuário
        userModel.getAddress().setCep(cep);

        // Salva o usuário atualizado
        userService.update(userModel);
    }
}
