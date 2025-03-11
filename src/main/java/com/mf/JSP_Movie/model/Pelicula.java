package com.mf.JSP_Movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPelicula;
    private String titulo;
    private String genero;
    private int año;
    private String director;
    private int duracion;
    private String urlImagen;
    //private String descripcion;

}
