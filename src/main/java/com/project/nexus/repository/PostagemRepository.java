package com.project.nexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    List<Postagem> findByUsuarioOrderByDataCriacaoDesc(Usuario usuario);
    List<Postagem> findByUsuarioIdOrderByDataCriacaoDesc(Long usuarioId);

    List<Postagem> findAllByOrderByDataCriacaoDesc();

    /* Contagem de postagens do usuário: */
    long countByUsuario(Usuario usuario); 
}
