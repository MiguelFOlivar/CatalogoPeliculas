package com.mf.JSP_Movie.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.JSP_Movie.model.Pelicula;
import com.mf.JSP_Movie.service.PeliculaService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String API_KEY = "ef8a004b177941a4caaf3e1170bc5ff5";

    private final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZjhhMDA0YjE3Nzk0MWE0Y2FhZjNlMTE3MGJjNWZmNSIsIm5iZiI6MTcwOTI0OTMzMi4xNTI5OTk5LCJzdWIiOiI2NWUxMTMzNDZjNDQ5YzAxODZmOTVlNjMiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.rUurP9dhY70aFvE9jiZiQ2bHwa6Q3NYk_9UFM1oIUt0";


    @GetMapping("/peliculas")
    public String index(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
        List<Pelicula> peliculas;

        // Si hay un filtro
        if (filtro != null && !filtro.isEmpty()) {
            peliculas = peliculaService.buscarPeliculas(filtro); // Buscar las películas por filtro

            // Si no hay coincidencias con el filtro
            if (peliculas.isEmpty()) {
                model.addAttribute("mensaje", "No se encontraron películas con ese título.");
            } else {
                model.addAttribute("mensaje", null); // No mostrar mensaje si hay coincidencias
            }
        } else {
            // Si no hay filtro, obtener todas las películas
            peliculas = peliculaService.obtenerPeliculas();
            model.addAttribute("mensaje", null); // No mostrar mensaje si no hay filtro
        }

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("filtro", filtro);  // Mantener el valor del filtro en el formulario
        return "test";
    }


    @GetMapping("/")
    public String index(@RequestParam(value = "filtro", required = false) String filtro,
                        @RequestParam(value = "page", defaultValue = "0") int pagina,
                        @RequestParam(value = "size", defaultValue = "8") int tamanoPagina,
                        Model model) {

        Page<Pelicula> peliculas;

        // Si hay un filtro
        if (filtro != null && !filtro.isEmpty()) {
            peliculas = peliculaService.buscarPeliculasConPaginacion(filtro, pagina, tamanoPagina); // Buscar las películas por filtro

            // Si no hay coincidencias con el filtro
            if (peliculas.isEmpty()) {
                model.addAttribute("mensaje", "No se encontraron películas con ese título.");
            } else {
                model.addAttribute("mensaje", null); // No mostrar mensaje si hay coincidencias
            }
        } else {
            // Si no hay filtro, obtener todas las películas
            peliculas = peliculaService.obtenerPeliculasConPaginacion(pagina, tamanoPagina);
            model.addAttribute("mensaje", null); // No mostrar mensaje si no hay filtro
        }

        // Obtener las imágenes de TMDB para cada película
        for (Pelicula pelicula : peliculas) {
            String imagenUrl = obtenerImagenDesdeTMDB(pelicula.getTitulo()); // o usa un ID si lo prefieres
            if (imagenUrl != null) {
                pelicula.setUrlImagen(imagenUrl); // Actualizamos el campo de imagen
            }
            // Obtener la descripción completa de TMDB
//            String descripcion = obtenerDescripcionDesdeTMDB(pelicula.getTitulo()); // Obtén la descripción
//            if (descripcion != null) {
//                pelicula.setDescripcion(descripcion); // Actualizamos la descripción
//            }
        }


        model.addAttribute("peliculas", peliculas);
        model.addAttribute("filtro", filtro);
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", peliculas.getTotalPages());
        model.addAttribute("tamanoPagina", tamanoPagina);
        return "index2";
    }


    // Método para obtener la URL de la imagen de TMDB basado en el nombre de la película
    private String obtenerImagenDesdeTMDB(String nombrePelicula) {

        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + nombrePelicula;

        // Realizar la solicitud a la API de TMDB
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        // Parseamos la respuesta JSON
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray results = jsonResponse.getJSONArray("results");

        if (!results.isEmpty()) {
            JSONObject peliculaJson = results.getJSONObject(0); // Tomamos la primera película que coincide
            String posterPath = peliculaJson.getString("poster_path");
            return "https://image.tmdb.org/t/p/w500" + posterPath; // Concatenamos la URL base de la imagen
        }

        return null; // Si no se encuentra una imagen, retornamos null
    }


    // Método para obtener la descripción completa de la película desde TMDB
    private String obtenerDescripcionDesdeTMDB(String nombrePelicula) {
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + nombrePelicula;

        // Realizamos la solicitud a la API de TMDB
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        // Usamos Jackson para parsear el JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode resultsNode = rootNode.path("results");

            if (resultsNode.isArray() && resultsNode.size() > 0) {
                JsonNode movieNode = resultsNode.get(0); // Tomamos el primer resultado
                String descripcion = movieNode.path("overview").asText(); // Obtenemos el `overview`
                return descripcion; // Retornamos la descripción
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return null; // Si no hay descripción, retornamos null
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/forgot")
    public String forgot() {
        return "forgot-password";
    }
}