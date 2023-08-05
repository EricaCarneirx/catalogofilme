package com.example.demo.repositories;

import com.example.demo.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {


    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    // Consulta personalizada para pesquisar filmes por gênero
    List<Filme> findByGenero(String genero);

    // Consulta personalizada para pesquisar filmes por ano
    List<Filme> findByAno(int ano);





}
