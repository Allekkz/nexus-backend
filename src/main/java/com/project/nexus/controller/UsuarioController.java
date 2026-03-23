package com.project.nexus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nexus.dto.UsuarioDTO;
import com.project.nexus.model.Usuario;
import com.project.nexus.service.ComentarioService;
import com.project.nexus.service.CurtidaService;
import com.project.nexus.service.PostagemService;
import com.project.nexus.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;

    /* para contagem: */
    private final PostagemService postagemService;
    private final ComentarioService comentarioService;
    private final CurtidaService curtidaService;

    /* Rota para criar usuário: */
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    /* Rota para listar usuários: */
    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarComPostagens();
    }

    /* Rota para buscar por ID: */
/*     @GetMapping("/{id}")
    public Optional<Usuario> buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    } */
    @GetMapping("/{id}")
    public UsuarioDTO buscar(@PathVariable Long id) {
        return usuarioService.buscarComPostagens(id);
    }

    /* Rota para atualizar Usuário || Nome && Foto && Bio */
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizar(id, usuario);
    }

    /* Rota para deletar Usuário: */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }

    /* Rota para logar: */
    @PostMapping("/login")
    public UsuarioDTO login(@RequestBody Usuario usuario) {
        return usuarioService.login(usuario.getEmail(), usuario.getSenha());
    }

    /* Rota para contar postagens: */
    @GetMapping("/{id}/postagens/count")
    public long contarPostagens(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        return postagemService.contarPostagens(usuario);
    }

    /* Rota para contar comentários: */
    @GetMapping("/{id}/comentarios/count")
    public long contarComentarios(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return comentarioService.contarComentarios(usuario);
    }

    /* Rota para contar curtidas recebidas */
    @GetMapping("/{id}/curtidas/count")
    public long contarCurtidasRecebidas(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return curtidaService.contarCurtidasRecebidas(usuario);
    }

    /* Rota para contar curtidas dadas */
    @GetMapping("/{id}/curtidas-dadas/count")
    public long contarCurtidasDadas(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return curtidaService.contarCurtidasDadas(usuario);
    }

}
