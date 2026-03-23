package com.project.nexus.dto;

import java.util.List;

import com.project.nexus.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String imgUrl;
    private String bio;
    private String curso;

    private List<PostagemDTO> postagens;

    public UsuarioDTO(Usuario usuario, List<PostagemDTO> postagens) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.imgUrl = usuario.getImgUrl();
        this.bio = usuario.getBio();
        this.curso = usuario.getCurso();
        this.postagens = postagens;
    }

}
