package com.example.academiacx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_studio")
public class StudioModel {

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

    public void setName(String name) {
        this.name = name;
    }

    public StudioModel() {
    }

    public StudioModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudioModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

