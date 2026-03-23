package com.project.nexus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nexus.dto.ComentarioDTO;
import com.project.nexus.model.Comentario;
import com.project.nexus.service.ComentarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    /* Criar comentário */
    @PostMapping
    public Comentario criar(@RequestBody Comentario comentario) {
        return comentarioService.criar(comentario);
    }

    /* Listar comentários de uma postagem (agora com DTO) */
    @GetMapping("/postagem/{postagemId}")
    public List<ComentarioDTO> listarPorPostagem(@PathVariable Long postagemId) {
        return comentarioService.listarPorPostagem(postagemId);
    }

    /* Buscar comentário por id */
    @GetMapping("/{id}")
    public Comentario buscar(@PathVariable Long id) {
        return comentarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
    }

    /* Deletar comentário */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        comentarioService.deletar(id);
    }
}