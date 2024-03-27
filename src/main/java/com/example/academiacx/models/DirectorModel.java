package com.example.academiacx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_director")
public class DirectorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public DirectorModel() {
    }

    public DirectorModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DirectorModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
