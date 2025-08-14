package com.desafio.foro.foroHub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHash {
    public static void main(String[] args) {
        String rawPassword = "trapo2020";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode(rawPassword);
        System.out.println(hashed);
    }
}
