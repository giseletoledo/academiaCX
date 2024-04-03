package com.example.academiacx.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class AddressDto {

    @JsonProperty(value = "logradouro")
    private String street;

    @JsonProperty(value = "numero")
    private String number;

    @JsonProperty(value = "bairro")
    private String district;

    @Column(name = "cep")
    private String cep;

    @JsonProperty(value = "localidade")
    private String city;

    @JsonProperty(value = "uf")
    @Column(name = "uf")
    private String state;

    // Constructors (optional)
    public AddressDto() {
    }

    public AddressDto(String street, String number, String district, String city) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Override toString() (without Lombok)
    @Override
    public String toString() {
        return "AddressDto{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                '}';
    }


}