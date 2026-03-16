package com.project.nexus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nexus.model.Postagem;
import com.project.nexus.service.PostagemService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/postagens")
@RequiredArgsConstructor

public class PostagemController {

    private final PostagemService postagemService;

    /* Rota para criar Postagem: */
    @PostMapping
    public Postagem criar(@RequestBody Postagem postagem) {
        return postagemService.criar(postagem);
    }

    /* Rota para listar - FEED: */
    @GetMapping
    public List<Postagem> listar() {
        return postagemService.listar();
    }

    /* Buscar postagem por ID: */
    @GetMapping("/{id}")
    public Optional<Postagem> buscar(@PathVariable Long id) {
        return postagemService.buscarPorId(id);
    }

    /* Listar postagens de um usuário: */
    @GetMapping("/usuario/{usuarioId}")
    public List<Postagem> listarPorUsuario(@PathVariable Long usuarioId) {
        return postagemService.listarPorUsuario(usuarioId);
    }

    /* Deletar postagem: */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        postagemService.deletar(id);
    }

}
