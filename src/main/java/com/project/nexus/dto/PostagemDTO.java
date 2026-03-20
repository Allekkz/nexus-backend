package com.project.nexus.dto;

import com.project.nexus.model.Postagem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostagemDTO {

    private Long id;
    private String titulo;
    private String conteudo;
    private String nomeUsuario;
    private String fotoUsuario;
    private long totalCurtidas;

    public PostagemDTO(Postagem postagem) {
        this.id = postagem.getId();
        this.titulo = postagem.getTitulo();
        this.conteudo = postagem.getConteudo();
        this.nomeUsuario = postagem.getUsuario().getNome();
        this.fotoUsuario = postagem.getUsuario().getImgUrl();
        
    }
}
