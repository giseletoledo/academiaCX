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

    // Getters and Setters (without Lombok)
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
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

