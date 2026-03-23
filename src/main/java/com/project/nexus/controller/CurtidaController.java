package com.project.nexus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.nexus.model.Postagem;
import com.project.nexus.model.Usuario;
import com.project.nexus.service.CurtidaService;
import com.project.nexus.service.PostagemService;
import com.project.nexus.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/curtidas")
@RequiredArgsConstructor

public class CurtidaController {

    private final CurtidaService curtidaService;
    private final UsuarioService usuarioService;
    private final PostagemService postagemService;

    /* Curtir ou descurtir postagem: */
    @PostMapping
    public boolean curtirOuDescurtir(@RequestParam Long usuarioId, @RequestParam Long postagemId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Postagem postagem = postagemService.buscarEntidadePorId(postagemId);

        return curtidaService.curtirOuDescurtir(usuario, postagem);
    }

    /* Contar curtidas da postagem: */
    @GetMapping("/postagem/{postagemId}/count")
    public long contarCurtidas(@PathVariable Long postagemId) {
        try {
            Postagem postagem = postagemService.buscarEntidadePorId(postagemId);
            return curtidaService.contarCurtidas(postagem);
        } catch (Exception e) {
            return 0; // evita 500
        }
    }

    /* Verificar se o user já curtiu: */
    @GetMapping("/postagem/{postagemId}/usuario/{usuarioId}")
    public boolean usuarioCurtiu(
            @PathVariable Long postagemId,
            @PathVariable Long usuarioId) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Postagem postagem = postagemService.buscarEntidadePorId(postagemId);

        return curtidaService.usuarioCurtiu(usuario, postagem);
    }

}
