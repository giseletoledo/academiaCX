package com.example.academiacx.models.security;

import jakarta.persistence.*;
@Entity
@Table(name = "tb_role")
public class RoleModel {

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

    public RoleModel() {
    }

    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
