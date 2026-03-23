package com.project.nexus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.nexus.dto.PostagemDTO;
import com.project.nexus.dto.UsuarioDTO;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.CurtidaRepository;
import com.project.nexus.repository.PostagemRepository;
import com.project.nexus.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PostagemRepository postagemRepository;
    private final CurtidaRepository curtidaRepository;

    /* Criar Usuario: */
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /* Listar todos usuários: */
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    /* Buscar usuário por ID */
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /* Update // Campos permitidos: Nome && Foto && Bio */
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuario.setNome(dadosAtualizados.getNome());
        usuario.setImgUrl(dadosAtualizados.getImgUrl());
        usuario.setBio(dadosAtualizados.getBio());

        return usuarioRepository.save(usuario);
    }

    /* Deletar usuário */
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    /* Logar */
    public UsuarioDTO login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email ou senha incorretos"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Email ou senha incorretos!");
        }

        return buscarComPostagens(usuario.getId());
    }

    /* novo metodo com dto: apenas infomações necessárias serão passadas: */
    public UsuarioDTO buscarComPostagens(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        List<PostagemDTO> postagens = postagemRepository
                .findByUsuarioIdOrderByDataCriacaoDesc(id)
                .stream()
                .map(postagem -> {
                    PostagemDTO dto = new PostagemDTO(postagem);
                    dto.setTotalCurtidas(
                            curtidaRepository.countByPostagemId(postagem.getId()));
                    return dto;
                })
                .toList();

        return new UsuarioDTO(usuario, postagens);
    }

    public List<UsuarioDTO> listarComPostagens() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> buscarComPostagens(usuario.getId()))
                .toList();
    }

}
