<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Películas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Agregar sombras y bordes redondeados a las tarjetas */
        .card {
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            border-radius: 10px;
        }

        .card:hover {
            transform: translateY(-10px); /* Efecto hover: levanta la tarjeta */
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
        }

        /* Estilos para las imágenes de las películas */
        .card-img-top {
            object-fit: cover; /* Asegura que la imagen cubra toda la tarjeta */
            height: 200px;
            border-radius: 10px 10px 0 0; /* Bordes redondeados en la imagen */
        }

        /* Título de la película */
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }

        /* Espaciado y estilo para los botones */
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        /* Estilo para el contenedor de las tarjetas */
        .movie-cards {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); /* Grid responsivo */
            gap: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="my-4 text-center">Listado de Películas</h1>

        <!-- Formulario de búsqueda -->
        <form action="/" method="get">
            <div class="input-group mb-3">
                <input type="text" name="filtro" class="form-control" placeholder="Buscar por nombre" value="${filtro}">
                <button class="btn btn-primary mx-2" type="submit">Buscar</button>
            </div>
        </form>

        <!-- Mostrar mensaje si no hay películas -->
        <c:if test="${not empty mensaje}">
            <div class="alert alert-warning">${mensaje}</div>
        </c:if>

        <!-- Mostrar lista de películas en un grid -->
        <div class="movie-cards">
            <c:forEach var="pelicula" items="${peliculas.content}">
                <div class="card shadow-sm">
                <img src="${pelicula.urlImagen}" class="card-img-top" alt="${pelicula.titulo}">
                    <div class="card-body">
                        <h5 class="card-title">${pelicula.titulo}</h5>
                        <p class="card-text"><strong>Director:</strong> ${pelicula.director}</p>
                        <p class="card-text"><strong>Año:</strong> ${pelicula.año}</p>
                        <p class="card-text"><strong>Género:</strong> ${pelicula.genero}</p>
                    </div>
                    <div class="card-footer text-center">
                        <a href="#" class="btn btn-primary">Ver más</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Paginación -->
        <div class="d-flex justify-content-between my-4">
            <c:if test="${peliculas.hasPrevious()}">
                <a href="?filtro=${filtro}&page=${peliculas.number - 1}&size=${peliculas.size}" class="btn btn-primary">Anterior</a>
            </c:if>

            <c:if test="${peliculas.hasNext()}">
                <a href="?filtro=${filtro}&page=${peliculas.number + 1}&size=${peliculas.size}" class="btn btn-primary">Siguiente</a>
            </c:if>
        </div>

        <!-- Paginación numérica -->
        <c:if test="${!peliculas.isEmpty()}">
            <nav>
                <ul class="pagination justify-content-center">
                    <c:forEach var="pageNum" begin="0" end="${peliculas.totalPages - 1}">
                        <li class="page-item ${peliculas.number == pageNum ? 'active' : ''}">
                            <a class="page-link" href="?filtro=${filtro}&page=${pageNum}&size=${peliculas.size}">${pageNum + 1}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </c:if>

    </div>

    <!-- Bootstrap JS y Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
