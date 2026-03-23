package com.project.nexus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nexus.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
