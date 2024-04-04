package com.example.academiacx.services;

import com.example.academiacx.models.dto.AddressDto;
import com.example.academiacx.services.inter.CepService;
import com.example.academiacx.services.inter.ViaCepClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepServiceImpl implements CepService {

    @Autowired
    private ViaCepClient viaCepClient;

    private static final Logger logger = LoggerFactory.getLogger(CepServiceImpl.class);

    @Override
    public AddressDto getAddressWithFeignByCep(String cep) {
        try {
            AddressDto addressDto = viaCepClient.findAddressByCep(cep);
            logger.info("Endereço encontrado para o CEP {}: {}", cep, addressDto);
            return addressDto;
        } catch (Exception e) {
            logger.error("Erro ao buscar endereço para o CEP " + cep, e);
            throw e;
        }
    }
}