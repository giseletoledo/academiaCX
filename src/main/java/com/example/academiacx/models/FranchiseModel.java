package com.example.academiacx.models;

import jakarta.persistence.*;
@Entity
@Table(name = "tb_franchise")
public class FranchiseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public FranchiseModel() {
    }

    public FranchiseModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "FranchiseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
