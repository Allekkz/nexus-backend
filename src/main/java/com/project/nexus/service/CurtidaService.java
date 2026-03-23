package com.project.nexus.service;

import org.springframework.stereotype.Service;

import com.project.nexus.model.Curtida;
import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.CurtidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CurtidaService {

    private final CurtidaRepository curtidaRepository;

    /* Curtir ou Descurtir postagem: */
    public boolean curtirOuDescurtir(Usuario usuario, Postagem postagem) {
        boolean jaCurtiu = curtidaRepository.existsByUsuarioAndPostagem(usuario, postagem);

        if (jaCurtiu) {
            curtidaRepository.deleteByUsuarioAndPostagem(usuario, postagem);
            return false; // descurtiu;
        }

        Curtida curtida = new Curtida();
        curtida.setUsuario(usuario);
        curtida.setPostagem(postagem);

        curtidaRepository.save(curtida);
        return true; // curtiu;
    }

    /* Contar curtidas de uma postagem: */
    public long contarCurtidas(Postagem postagem) {
        return curtidaRepository.countByPostagem(postagem);
    }

    /* Curtidas que o usuário deu: */
    public long contarCurtidasDadas(Usuario usuario) {
        return curtidaRepository.countByUsuario(usuario);
    }

    /* Curtidas que o usuário recebeu */
    public long contarCurtidasRecebidas(Usuario usuario) {
        return curtidaRepository.countByPostagemUsuario(usuario);
    }

    /* Verifica se já curtiu: */
    public boolean usuarioCurtiu(Usuario usuario, Postagem postagem) {
        return curtidaRepository.existsByUsuarioAndPostagem(usuario, postagem);
    }
}
