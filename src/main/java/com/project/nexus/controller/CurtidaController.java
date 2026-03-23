package com.project.nexus.controller;

import org.springframework.http.ResponseEntity;
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

    /**
     * ✅ TOGGLE: Curtir ou Descurtir (POST /curtidas)
     * Retorna true = agora está curtido | false = descurtido
     */
    @PostMapping
    public ResponseEntity<Boolean> toggleCurtida(
            @RequestParam Long usuarioId,
            @RequestParam Long postagemId) {

        try {
            Usuario usuario = usuarioService.buscarPorId(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            Postagem postagem = postagemService.buscarEntidadePorId(postagemId);

            boolean agoraCurtiu = curtidaService.curtirOuDescurtir(usuario, postagem);

            return ResponseEntity.ok(agoraCurtiu);

        } catch (Exception e) {
            // Evita 500 no frontend e retorna false (descurtido)
            return ResponseEntity.badRequest().body(false);
        }
    }

    /**
     * Contar curtidas da postagem
     */
    @GetMapping("/postagem/{postagemId}/count")
    public long contarCurtidas(@PathVariable Long postagemId) {
        try {
            Postagem postagem = postagemService.buscarEntidadePorId(postagemId);
            return curtidaService.contarCurtidas(postagem);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Verificar se o usuário já curtiu
     */
    @GetMapping("/postagem/{postagemId}/usuario/{usuarioId}")
    public boolean usuarioCurtiu(
            @PathVariable Long postagemId,
            @PathVariable Long usuarioId) {

        try {
            Usuario usuario = usuarioService.buscarPorId(usuarioId)
                    .orElse(null);

            if (usuario == null) return false;

            Postagem postagem = postagemService.buscarEntidadePorId(postagemId);
            return curtidaService.usuarioCurtiu(usuario, postagem);

        } catch (Exception e) {
            return false;
        }
    }
}