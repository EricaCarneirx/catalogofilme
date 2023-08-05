package com.example.demo.repositories;

import com.example.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    List<Usuario> findByIdade(int idade);
    List<Usuario> findByNome(String nome);
    List<Usuario> findByIdadeAndNome(int idade, String nome);
    Usuario findById(long id);
    void deleteById(long id);


}
