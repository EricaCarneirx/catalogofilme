package com.example.demo.services;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

   // private static final long EXPIRATION_TIME
    private final UsuarioRepository usuarioRepository;
    private final SecretKey secretKey;
    private long EXPIRATION_TIME;

    public UsuarioService(UsuarioRepository usuarioRepository, SecretKey secretKey) {
        this.usuarioRepository = usuarioRepository;
        this.secretKey = secretKey;
    }

    public String login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            Claims claims = Jwts.claims().setSubject(email);

            // Defina outros detalhes no token, se necessário
            claims.put("userId", usuario.getId());


            String token = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
            return token;
        }
        return null; // Credenciais inválidas
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario adicionarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setIdade(usuario.getIdade());
            usuarioExistente.setEmail(usuario.getEmail());

            return usuarioRepository.save(usuarioExistente);
        }

        return null;
    }

    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean podeVisualizarFilme(int idadeUsuario, int classificacaoEtariaFilme) {
        return idadeUsuario >= 18 || idadeUsuario >= classificacaoEtariaFilme;
    }

}



