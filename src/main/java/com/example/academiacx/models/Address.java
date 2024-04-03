package com.example.academiacx.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @JsonProperty(value = "logradouro")
    @Column(name = "logradouro")
    private String street;
    @JsonProperty(value = "numero")
    @Column(name = "numero")
    private String number;
    @JsonProperty(value = "bairro")
    @Column(name = "bairro")
    private String district;
    @JsonProperty(value = "localidade")
    @Column(name = "localidade")
    private String city;
    @Column(name = "cep")
    private String cep;
    @JsonProperty(value = "uf")
    @Column(name = "uf")
    private String state;
    public Address() {
    }
    public Address(String street, String number, String district, String city, String cep, String state) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.cep = cep;
        this.state = state;
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

    @Override
    public String toString() {
        return "Address{" +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", cep='" + cep + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}


