package com.example.academiacx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_ator")
public class ActorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    @Override
    public String toString() {
        return "AtorModel{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }
}

