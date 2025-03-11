# Proyecto de Catalogo de peliculas - API

![Static Badge](https://img.shields.io/badge/Java-%2017%2B-green?style=flat&logo=CoffeeScript&label=Java)
![Static Badge](https://img.shields.io/badge/Spring-%203.0%2B-brightgreen?style=flat&logo=Spring%20Boot)
![Oracle](https://img.shields.io/badge/Oracle-%2012c%2B-red?style=flat)
![Maven](https://img.shields.io/badge/Maven-%203.8%2B-blue?style=flat&logo=Apache%20Maven)

Este es un proyecto de **Catalogo de peliculas** que expone una API para realizar la consulta a un catalogo de peliculas, usando un ai key de TMDB.
El API está construida utilizando **Spring Boot**, **JSP** y **Maven**
## Descripción
El proyecto proporciona un conjunto de funcionalidades para consultar los datos de determinadas peliculas almacenadas en BD Oracle.
Las principales características incluyen:

- **Operaciones CRUD**: Crear, leer, actualizar y eliminar peliculas.(por el momento solo operaciones de lectura)

- **Ingreso por login**: Se accede al catalogo de peliculas mediante un login de usuario y contraseña, ademas de proporcionar la opcion de registro y recuperacion(En desarrollo)

La API está diseñada para ser utilizada en sistemas de gestión de peliculas donde se necesita almacenar y consultar información como nombre, director, imagenes, y realizar actualizaciones en los registros.(En proceso)

## Tecnologías Utilizadas

| Tecnología        | Descripción                                    |
|-------------------|------------------------------------------------|
| **Spring Boot**    | Framework para crear aplicaciones Java basadas en microservicios. |
| **Java 17+**       | Lenguaje de programación para desarrollo backend. |
| **Oracle Database**| Base de datos relacional para almacenamiento de clientes. |
| **JUnit**          | Framework de pruebas unitarias para Java. |
| **Mockito**        | Biblioteca para pruebas unitarias (mocking). |(En proceso)

## Base de Datos Oracle

Este proyecto utiliza una base de datos de Oracle para almacenar los datos de los clientes y los productos.
CREATE TABLE peliculas (
    id_pelicula NUMBER PRIMARY KEY,
    titulo VARCHAR2(255) NOT NULL,
    genero VARCHAR2(100) NOT NULL,
    año NUMBER(4),
    director VARCHAR2(255),
    duracion NUMBER
);

## Configuración de Oracle

1. **Dependencias**: En el archivo `pom.xml`, incluye las dependencias necesarias para Oracle. Asegúrate de tener el driver adecuado en el tu proyecto

```xml
<dependency>
  <groupId>com.oracle.database.jdbc</groupId>
  <artifactId>ojdbc11</artifactId>
  <scope>runtime</scope>
</dependency>
```
2. **Configuración de la conexión**: configura el archivo `application.prperties` o `application.yml` para conectar la aplicación a la base de datos Oracle.
Asegúrate de tener la URL, usuario y contraseña correctos.

```properties
# URL de conexión a la base de datos Oracle
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/orcl
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Configuración del driver Oracle
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.jpa.properties.hibernate.jdbc.time_zone=UTC

# create and drop tables
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.format_sql=true

#Swagger configuration
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html

server.port=8080 (puedes cambiar el puerto si lo requieres)

```


## Instalación y Configuración
**Requisitos:**

- Java 17+
- Maven 3.8+
- IDE (Ejemplo: IntelliJ IDEA, Eclipse)
- Oracle Database (Instalado y configurado para usar en desarrollo)

## Pasos para ejecutar el proyecto

1. **Clona el repositorio**
   ```
   git clone https://github.com/tu-usuario/tu-repositorio.git
   
   ```
2. **Navega al directorio del proyecto**
   ```
   cd tu-repositorio

   ```
3. **Construye el proyecto usando Maven**
   ```
   mvn clean install
   
   ```
4. **Configura tu base de datos de Oracle:** Asegurate de que la base de datos esté configurada correctamente.
5. **Ejecuta el proyecto**
   ```
   mvn spring-boot:run

   ```
6. **La aplicación estará disponible en**
   ```
   http://localhost:8080
   
   ```


