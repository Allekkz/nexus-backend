package com.project.nexus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.nexus.model.Usuario;
import com.project.nexus.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

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
    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email ou senha incorretos"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Email ou senha incorretos!");
        }

        return usuario;
    }

}
