package com.desafio.foro.foroHub.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
   private UsuarioRepository repository;

    public AutenticacionService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return repository.findByLogin(username);
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
