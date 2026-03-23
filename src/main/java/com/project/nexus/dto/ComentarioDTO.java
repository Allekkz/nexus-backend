package com.project.nexus.dto;

import java.time.LocalDateTime;

import com.project.nexus.model.Comentario;
import com.project.nexus.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    private Long id;
    private String conteudo;
    private LocalDateTime dataCriacao;

    private String nomeUsuario;
    private String fotoUsuario;
    private Long usuarioId;

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.conteudo = comentario.getConteudo();
        this.dataCriacao = comentario.getDataCriacao();

        // Proteção contra null
        Usuario usuario = comentario.getUsuario();
        if (usuario != null) {
            this.nomeUsuario = usuario.getNome();
            this.fotoUsuario = usuario.getImgUrl();
            this.usuarioId = usuario.getId();
        } else {
            this.nomeUsuario = "Usuário removido";
            this.fotoUsuario = null; // ou uma URL default
            this.usuarioId = null;
        }
    }
}