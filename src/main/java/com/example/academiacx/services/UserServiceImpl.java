package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.models.AddressModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.AddressDto;
import com.example.academiacx.repository.UserRepository;
import com.example.academiacx.services.inter.UserService;
import com.example.academiacx.services.inter.ViaCepClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ViaCepClient viaCepClient;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

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

//        userModel.setPassword(passwordEncoder().encode(userModel.getPassword()));

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

    @Override
    public AddressDto getAddressWithFeignByCep(String cep, Long userId) {
        try {

            AddressDto addressDto = viaCepClient.findAddressByCep(cep);
            logger.info("Endereço encontrado para o CEP {}: {}", cep, addressDto);

            // Buscar o usuário pelo ID
            UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));

            // Setar o endereço no usuário
            user.setAddress(new AddressModel());
            user.getAddress().setCep(addressDto.getCep());
            user.getAddress().setStreet(addressDto.getStreet());
            user.getAddress().setDistrict(addressDto.getDistrict());
            user.getAddress().setCity(addressDto.getCity());
            user.getAddress().setState(addressDto.getState());

            // Salvar o usuário com o endereço atualizado
            userRepository.save(user);

            return addressDto;
        } catch (Exception e) {
            logger.error("Erro ao buscar endereço para o CEP " + cep, e);
            throw e;
        }
    }
}