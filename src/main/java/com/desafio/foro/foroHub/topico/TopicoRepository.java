package com.desafio.foro.foroHub.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);

    Optional<Topico> findByIdAndStatusTrue(Long id);

    //List<Topico> findAllByOrderByFechaCreacionAsc();
    List<Topico> findByStatusTrueOrderByFechaCreacionAsc();
}
