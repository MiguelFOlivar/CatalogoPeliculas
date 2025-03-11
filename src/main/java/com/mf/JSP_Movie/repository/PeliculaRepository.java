package com.mf.JSP_Movie.repository;

import com.mf.JSP_Movie.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

    // Consulta personalizada usando JPQL para buscar películas cuyo título contenga el filtro
    @Query("SELECT p FROM Pelicula p WHERE LOWER(p.titulo) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Pelicula> buscarPorTitulo(@Param("filtro") String filtro);

    Page<Pelicula> findByTituloContainingIgnoreCase(String filtro, Pageable pageable);
}
