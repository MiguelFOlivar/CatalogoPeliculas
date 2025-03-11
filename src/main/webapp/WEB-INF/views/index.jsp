<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Películas</title>

    <!-- Agregar el enlace a Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJGQhkl5u5Z9q8wECrU9vOOkV0RsbzImy6z3zV5I5R47nFdl4ug9dqY1wqVX" crossorigin="anonymous">

    <!-- Agregar el enlace a jQuery (opcional si quieres usar JavaScript con Bootstrap) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gyb+YbQ8k0zGZpx5F6h4OnWgf96pFj6d3j2e3uFdnzv6d8/6Hfu" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0xk/1ttFoPz3gH96v3p7wA+2gRpvgt9kKj0bBoWu0J69YyYX" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container my-5">
        <h1 class="text-center">Lista de Películas</h1>

        <!-- Formulario de búsqueda -->
        <form action="/peliculas" method="get" class="d-flex justify-content-center my-3">
            <div class="input-group w-50">
                <input type="text" class="form-control" id="filtro" name="filtro" placeholder="Buscar película" value="${filtro}">
                <button class="btn btn-primary" type="submit">Buscar</button>
            </div>
        </form>

        <!-- Tabla de películas -->
        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Género</th>
                    <th>Año</th>
                    <th>Director</th>
                    <th>Duración (min)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pelicula" items="${peliculas}">
                    <tr>
                        <td>${pelicula.idPelicula}</td>
                        <td>${pelicula.titulo}</td>
                        <td>${pelicula.genero}</td>
                        <td>${pelicula.año}</td>
                        <td>${pelicula.director}</td>
                        <td>${pelicula.duracion}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
