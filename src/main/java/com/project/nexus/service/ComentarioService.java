package com.project.nexus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.nexus.dto.ComentarioDTO;
import com.project.nexus.model.Comentario;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.ComentarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    /* Criar comentário (mantemos retornando entity por enquanto) */
    public Comentario criar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    /* Listar comentários com DTO (SOLUÇÃO DO ERRO) */
    public List<ComentarioDTO> listarPorPostagem(Long postagemId) {
        return comentarioRepository.findByPostagemIdOrderByDataCriacaoAsc(postagemId)
                .stream()
                .map(ComentarioDTO::new)
                .toList();
    }

    /* Buscar por ID (opcional, pode manter ou mudar depois) */
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