package com.example.academiacx.repository;

import com.example.academiacx.models.security.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
}

