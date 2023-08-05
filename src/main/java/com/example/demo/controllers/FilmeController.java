package com.example.demo.controllers;

import com.example.demo.models.Filme;
import com.example.demo.models.Usuario;
import com.example.demo.services.FilmeService;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes(@RequestParam(required = false) Long idUsuario) {
        List<Filme> filmes = filmeService.listarTodos();

        if (idUsuario != null) {
            Usuario usuario = usuarioService.obterUsuarioPorId(idUsuario);
            if (usuario != null) {
                // Filtra os filmes de acordo com a idade do usuário
                filmes = filmes.stream()
                        .filter(filme -> usuarioService.podeVisualizarFilme(usuario.getIdade(), filme.getClassificacaoEtaria()))
                        .collect(Collectors.toList());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> obterFilmePorId(@PathVariable Long id) {
        Filme filme = filmeService.obterPorId(id);
        if (filme != null) {
            return new ResponseEntity<>(filme, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Filme> adicionarFilme(@RequestBody Filme request) {
        Filme novoFilme = filmeService.adicionarFilme(
                request.getTitulo(),
                request.getGenero(),
                request.getAno(),
                request.getClassificacaoEtaria(),
                request.getDescricao(),
                request.getImagem(),
                request.getMediaAvaliacoes()
        );
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme request) {
        Filme filmeAtualizado = filmeService.atualizar(
                id,
                request.getTitulo(),
                request.getGenero(),
                request.getAno(),
                request.getClassificacaoEtaria(),
                request.getDescricao(),
                request.getMediaAvaliacoes(),
                request.getImagem()
        );

        if (filmeAtualizado != null) {
            return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFilme(@PathVariable Long id) {
        boolean removido = filmeService.remover(id);

        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/avaliar")
    public ResponseEntity<Filme> avaliarFilme(@PathVariable Long id, @RequestParam double pontuacao) {
        Filme filmeAvaliado = filmeService.avaliarFilme(id, pontuacao);

        if (filmeAvaliado != null) {
            return new ResponseEntity<>(filmeAvaliado, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}






