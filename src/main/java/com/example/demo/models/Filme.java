package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String genero;
    private int ano;
    private int classificacaoEtaria;
    private String descricao;
    private byte imagem;

    @Min(0)
    @Max(5)
    private double mediaAvaliacoes;
    private int totalAvaliacoes;


    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public void setMediaAvaliacoes(Double totalAvaliacoes) {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }

    public int getTotalAvaliacoes(int totalAvaliacoes) {

        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;

    }


    // getters e setters


}
