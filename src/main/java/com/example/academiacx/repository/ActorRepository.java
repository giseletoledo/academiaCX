package com.example.academiacx.repository;

import com.example.academiacx.models.ActorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorModel, Long> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}

