package com.project.nexus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.nexus.model.Curtida;
import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;
import com.project.nexus.repository.CurtidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurtidaService {

    private final CurtidaRepository curtidaRepository;

    /* ✅ TOGGLE COM TRANSACTIONAL (obrigatório para delete funcionar) */
    @Transactional
    public boolean curtirOuDescurtir(Usuario usuario, Postagem postagem) {
        boolean jaCurtiu = curtidaRepository
                .existsByUsuarioIdAndPostagemId(usuario.getId(), postagem.getId());

        if (jaCurtiu) {
            curtidaRepository.deleteByUsuarioIdAndPostagemId(usuario.getId(), postagem.getId());
            return false; // descurtiu
        }

        Curtida curtida = new Curtida();
        curtida.setUsuario(usuario);
        curtida.setPostagem(postagem);
        curtidaRepository.save(curtida);
        return true; // curtiu
    }

    /* Contar curtidas de uma postagem */
    public long contarCurtidas(Postagem postagem) {
        return curtidaRepository.countByPostagemId(postagem.getId());
    }

    /* Curtidas que o usuário deu */
    public long contarCurtidasDadas(Usuario usuario) {
        return curtidaRepository.countByUsuario(usuario);
    }

    /* Curtidas que o usuário recebeu */
    public long contarCurtidasRecebidas(Usuario usuario) {
        return curtidaRepository.countByPostagemUsuario(usuario);
    }

    /* Verifica se já curtiu */
    public boolean usuarioCurtiu(Usuario usuario, Postagem postagem) {
        return curtidaRepository
                .existsByUsuarioIdAndPostagemId(usuario.getId(), postagem.getId());
    }
}