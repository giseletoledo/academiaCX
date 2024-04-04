package com.example.academiacx.services.inter;

import com.example.academiacx.models.dto.AddressDto;

public interface CepService {
    /**
     * Encontra um endereço pelo CEP.
     *
     * @param cep O CEP a ser consultado.
     * @return O endereço correspondente ao CEP fornecido.
     */

    AddressDto getAddressWithFeignByCep(String cep);
}
