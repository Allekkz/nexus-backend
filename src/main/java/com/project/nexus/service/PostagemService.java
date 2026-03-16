package com.project.nexus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.PostagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PostagemService {

    private final PostagemRepository postagemRepository;

    /* Criar postagem: */
    public Postagem criar(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    /* Listar Todas Postagens - FEED: */
    public List<Postagem> listar() {
        return postagemRepository.findAllByOrderByDataCriacaoDesc();
    }

    /* Buscar postagem por id: */
    public Optional<Postagem> buscarPorId(Long id) {
        return postagemRepository.findById(id);
    }

    /* Listar postagem de um usuário: */
    public List<Postagem> listarPorUsuario(Long usuarioId) {
        return postagemRepository.findByUsuarioIdOrderByDataCriacaoDesc(usuarioId);
    }

    /* Deletar postagem: */
    public void deletar(Long id) {
        postagemRepository.deleteById(id);
    }

    /* Contar postagens */ 
    public long contarPostagens(Usuario usuario) {
        return postagemRepository.countByUsuario(usuario);
    }

}
