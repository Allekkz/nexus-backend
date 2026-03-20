package com.project.nexus.dto;

import java.time.LocalDateTime;

import com.project.nexus.model.Postagem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostagemDTO {

    private Long id;
    private String titulo;
    private String conteudo;
    private String imgUrl;
    private LocalDateTime dataCriacao;

    private String nomeUsuario;
    private String fotoUsuario;
    private long idDoUsuario;

    private long totalCurtidas;

    public PostagemDTO(Postagem postagem) {
        this.id = postagem.getId();
        this.titulo = postagem.getTitulo();
        this.conteudo = postagem.getConteudo();
        this.imgUrl = postagem.getImgUrl();
        this.dataCriacao = postagem.getDataCriacao();

        this.nomeUsuario = postagem.getUsuario().getNome();
        this.fotoUsuario = postagem.getUsuario().getImgUrl();
        this.idDoUsuario = postagem.getUsuario().getId();
    }
}
