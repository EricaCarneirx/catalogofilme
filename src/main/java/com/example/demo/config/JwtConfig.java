package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {


    public SecretKey secretKey() {
        // definindo a chave secreta como uma string
        String secretKeyString = "subscrybe";

        // codificando a string em bytes
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);

        // Criando uma chave secreta a partir dos bytes
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    @Service
    public class UsuarioService {

        @Autowired
        private SecretKey secretKey;

            }
}
