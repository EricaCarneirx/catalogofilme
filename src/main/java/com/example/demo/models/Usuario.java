package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private int idade;
    private String email;
    private String senha;;

    public String getSenha() {

        return senha;
    }

        public void setSenha (String senha){
            this.senha = senha;
        }

    public Object getId() {
        return idUsuario;
    }
}

