package com.example.academiacx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_streaming")
public class StreamingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public StreamingModel() {
    }

    public StreamingModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StreamingModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

