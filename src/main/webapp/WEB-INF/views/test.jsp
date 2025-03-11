<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Películas</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Estilos personalizados -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            padding-top: 30px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }

        .search-form {
            margin-bottom: 20px;
        }

        .search-input {
            width: 80%;
            padding: 10px;
            margin-right: 10px;
            border-radius: 5px;
        }

        .search-button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .search-button:hover {
            background-color: #0056b3;
        }

        .alert-warning {
            font-weight: bold;
        }

        .movie-card {
            margin-bottom: 20px;
        }
    </style>
</head>

<body>

    <div class="container">
        <h1>Listado de Películas</h1>

        <!-- Formulario de búsqueda -->
        <form action="/" method="get" class="search-form d-flex justify-content-center">
            <input type="text" name="filtro" class="search-input" placeholder="Buscar por título" value="${filtro}">
            <button type="submit" class="search-button">Buscar</button>
        </form>

        <!-- Mostrar mensaje si no se encuentran películas -->
        <c:if test="${not empty mensaje}">
            <div class="alert alert-warning text-center">${mensaje}</div>
        </c:if>

        <!-- Contenedor de las tarjetas de películas -->
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <c:forEach var="pelicula" items="${peliculas}">
                <div class="col">
                    <div class="card movie-card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${pelicula.titulo}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${pelicula.genero}</h6>
                            <p class="card-text">Director: ${pelicula.director}</p>
                            <p class="card-text">Año: ${pelicula.año}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Si no hay películas, mostrar mensaje -->
            <c:if test="${empty peliculas}">
                <div class="col-12 text-center">
                    <div class="alert alert-info">No se encontraron películas.</div>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Bootstrap 5 JS (Opcional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
