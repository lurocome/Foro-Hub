package com.desafio.foro.foroHub;


import com.desafio.foro.foroHub.topico.DatosActualizarTopico;
import com.desafio.foro.foroHub.topico.DatosRegistroTopico;
import com.desafio.foro.foroHub.topico.Topico;
import com.desafio.foro.foroHub.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

     @Transactional
     @PostMapping
     public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
         boolean existe = repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent();
         if (existe) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un tópico con el mismo título y mensaje");
         }
             repository.save(new Topico(datos)) ;

       }

    //@GetMapping
    //public Page<DatosListaMedico> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion) {
    //    return repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
    // }

    //Trae todos los registros con status true
    @GetMapping
    public List<Topico> listarOrdenadosPorFecha() {
        return repository.findByStatusTrueOrderByFechaCreacionAsc();
    }

    @GetMapping("/{id}")
    public  Topico obtenerPorId(@PathVariable Long id) {
        return repository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "ID no existe o topico desactivado en la base de datos"
                ));

    }


    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Topico topico = repository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "ID no existe o status = false"
                ));

        topico.eliminar();


    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = repository.findByIdAndStatusTrue(datos.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
         topico.actualizar(datos);

        //repository.save(topico);
    }

}
