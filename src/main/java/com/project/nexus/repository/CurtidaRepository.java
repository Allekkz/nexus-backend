package com.project.nexus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nexus.model.Curtida;
import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    // Verifica se um usuário já curtiu uma postagem:
    Optional<Curtida> findByUsuarioAndPostagem(Usuario usuario, Postagem postagem);
    
    /* Contagem de curtidas de um Post: */
    long countByPostagem(Postagem postagem);

    /* Remove curtida: */
    void deleteByUsuarioAndPostagem(Usuario usuario, Postagem postagem);

    boolean existsByUsuarioAndPostagem(Usuario usuario, Postagem postagem);

    /* Contagem de curtidas dadas: */
    long countByUsuario(Usuario usuario);

    /* Contagem de curtidas recebidas */
    long countByPostagemUsuario(Usuario usuario);
}
