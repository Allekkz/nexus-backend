package com.project.nexus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.nexus.model.Comentario;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.ComentarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    /* Criar comentário: */
    public Comentario criar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    /* Listar comentários de uma postagem: */
    public List<Comentario> listarPorPostagem(Long postagemId) {
        return comentarioRepository.findByPostagemIdOrderByDataCriacaoAsc(postagemId);
    }

    /* Buscar comentário por ID: */ 
    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    /* Deletar comentário */
    public void deletar(Long id) {
        comentarioRepository.deleteById(id);
    }

    /* Contar comentários */
    public long contarComentarios(Usuario usuario) {
        return comentarioRepository.countByUsuario(usuario);
    }

}
