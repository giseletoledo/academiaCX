package com.example.academiacx.services.inter;

import com.example.academiacx.models.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping("{cep}/json")
    AddressDto findAddressByCep(@PathVariable("cep") String cep);
}
