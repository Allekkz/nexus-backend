package com.project.nexus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.nexus.dto.PostagemDTO;
import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.CurtidaRepository;
import com.project.nexus.repository.PostagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PostagemService {

    private final PostagemRepository postagemRepository;
    private final CurtidaRepository curtidaRepository;

    /* Criar postagem: */
    public Postagem criar(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    /* Listar Todas Postagens - FEED: */
    /*
     * public List<Postagem> listar() {
     * return postagemRepository.findAllByOrderByDataCriacaoDesc();
     * }
     */
    /* usando postagem DTO, para mandar apenas o necessário no json: */
    public List<PostagemDTO> listar() {
        return postagemRepository.findAllByOrderByDataCriacaoDesc()
                .stream()
                .map(postagem -> {
                    PostagemDTO dto = new PostagemDTO(postagem);

                    dto.setTotalCurtidas(
                            curtidaRepository.countByPostagemId(postagem.getId()));

                    return dto;
                })
                .toList();
    }

    /* Buscar postagem por id: */
    /*
     * public Optional<Postagem> buscarPorId(Long id) {
     * return postagemRepository.findById(id);
     * }
     */
    /* usando postagemDTO: */
    public Optional<PostagemDTO> buscarPorId(Long id) {
        return postagemRepository.findById(id)
                .map(postagem -> {
                    PostagemDTO dto = new PostagemDTO(postagem);

                    dto.setTotalCurtidas(
                            curtidaRepository.countByPostagemId(postagem.getId()));

                    return dto;
                });
    }

    /* Listar postagem de um usuário: */
    /*
     * public List<Postagem> listarPorUsuario(Long usuarioId) {
     * return postagemRepository.findByUsuarioIdOrderByDataCriacaoDesc(usuarioId);
     * }
     */
    /* usando PostagemDTO: */
    public List<PostagemDTO> listarPorUsuario(Long usuarioId) {
        return postagemRepository.findByUsuarioIdOrderByDataCriacaoDesc(usuarioId)
                .stream()
                .map(postagem -> {
                    PostagemDTO dto = new PostagemDTO(postagem);

                    dto.setTotalCurtidas(
                            curtidaRepository.countByPostagemId(postagem.getId()));

                    return dto;
                })
                .toList();
    }

    /* metodo novo: */
    public Postagem buscarEntidadePorId(Long id) {
        return postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));
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
