package com.example.demo.services;

import com.example.demo.models.Filme;
import com.example.demo.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public Filme adicionarFilme(String titulo, String genero, int ano, int classificacaoEtaria, String descricao, byte imagem, double mediaAvaliacoes) {
        Filme novoFilme = new Filme();
        novoFilme.setTitulo(titulo);
        novoFilme.setGenero(genero);
        novoFilme.setAno(ano);
        novoFilme.setClassificacaoEtaria(classificacaoEtaria);
        novoFilme.setDescricao(descricao);
        novoFilme.setMediaAvaliacoes(mediaAvaliacoes);

        return filmeRepository.save(novoFilme);
    }

    public Filme atualizar(Long id, String titulo, String genero, int ano, int classificacaoEtaria, String descricao, Double mediaAvaliacoes, byte imagem) {
        Filme filmeExistente = filmeRepository.findById(id).orElse(null);

        if (filmeExistente != null) {
            filmeExistente.setTitulo(titulo);
            filmeExistente.setGenero(genero);
            filmeExistente.setAno(ano);
            filmeExistente.setClassificacaoEtaria(classificacaoEtaria);
            filmeExistente.setDescricao(descricao);
            filmeExistente.setMediaAvaliacoes(mediaAvaliacoes);

            return filmeRepository.save(filmeExistente);
        }

        return null; // ou lançar exceção, caso o filme não seja encontrado
    }

    public boolean remover(Long id) {
        Filme filmeExistente = filmeRepository.findById(id).orElse(null);

        if (filmeExistente != null) {
            filmeRepository.delete(filmeExistente);
            return true;
        }

        return false;
    }

    public Filme obterPorId(Long id) {
        return filmeRepository.findById(id).orElse(null);
    }

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme avaliarFilme(Long id, double pontuacao) {
        Filme filmeExistente = filmeRepository.findById(id).orElse(null);

        if (filmeExistente != null) {
            double mediaAtual = filmeExistente.getMediaAvaliacoes();
            int totalAvaliacoes = filmeExistente.getTotalAvaliacoes();

            // Realiza a média da nova pontuação com as pontuações já existentes
            double novaMedia = (mediaAtual * totalAvaliacoes + pontuacao) / (totalAvaliacoes + 1);
            filmeExistente.setMediaAvaliacoes(novaMedia);
            filmeExistente.setTotalAvaliacoes(totalAvaliacoes + 1);

            return filmeRepository.save(filmeExistente);
        }

        return null; // Ou uma exceção, caso o filme não seja encontrado
    }
}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
