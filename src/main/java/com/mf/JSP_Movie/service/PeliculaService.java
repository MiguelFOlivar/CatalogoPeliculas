package com.mf.JSP_Movie.service;

import com.mf.JSP_Movie.model.Pelicula;
import com.mf.JSP_Movie.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> obtenerPeliculas() {
        return peliculaRepository.findAll();
    }

    // Buscar películas por filtro de título
    public List<Pelicula> buscarPeliculas(String filtro) {
        return peliculaRepository.buscarPorTitulo(filtro);
    }

    // Método para obtener todas las películas con paginación
    public Page<Pelicula> obtenerPeliculasConPaginacion(int pagina, int tamanoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanoPagina);
        return peliculaRepository.findAll(pageable);
    }

    // Método para obtener películas con paginación
    public Page<Pelicula> buscarPeliculasConPaginacion(String filtro, int pagina, int tamanoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanoPagina);
        return peliculaRepository.findByTituloContainingIgnoreCase(filtro, pageable);
    }


}
