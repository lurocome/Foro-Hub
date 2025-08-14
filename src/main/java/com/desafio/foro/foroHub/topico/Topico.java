package com.desafio.foro.foroHub.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private Boolean status;
    private String autor;
    private String curso;



    public  Topico(DatosRegistroTopico datos){
        this.Id = null;
        this.titulo=datos.titulo();
        this.mensaje= datos.mensaje();
        this.fechaCreacion=LocalDate.now();
        this.status=true;
        this.autor= datos.autor();
        this.curso= datos.curso();
    }

    public  void eliminar() {
        this.status = false;
    }

    public void actualizar(@Valid DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            //topico.setTitulo(datos.titulo());
            this.titulo= datos.titulo();
        }
        if (datos.mensaje() != null) {
           // topico.setMensaje(datos.mensaje());
            this.mensaje= datos.mensaje();
        }
        if (datos.autor()!=null){
            this.autor= datos.autor();
          //  topico.setAutor(datos.autor());
        }
        if (datos.curso() != null) {

            this.curso= datos.curso();
        }
        this.fechaCreacion=LocalDate.now();

    }
}

