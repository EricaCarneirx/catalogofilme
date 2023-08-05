package com.example.demo.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO  {
    private Long id;
    private String titulo;
    private String genero;
    private int ano;
    private int classificacaoEtaria;
    private String descricao;
    private double mediaAvaliacoes;
    private int totalAvaliacoes;
    private byte[] imagem;

    // Construtores, getters e setters
}

