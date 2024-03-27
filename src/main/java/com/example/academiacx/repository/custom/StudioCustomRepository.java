package com.example.academiacx.repository.custom;

import com.example.academiacx.models.StudioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioCustomRepository extends JpaRepository<StudioModel, Long> {
    StudioModel findByName(String name);
}
