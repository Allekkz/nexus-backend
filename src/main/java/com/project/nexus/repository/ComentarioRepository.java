package com.project.nexus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nexus.model.Comentario;
import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPostagemOrderByDataCriacaoAsc(Postagem postagem);
    List<Comentario> findByPostagemIdOrderByDataCriacaoAsc(Long postagemId);

    /* Contagem de comentarios de um usuário: */
    long countByUsuario(Usuario usuario);
}
