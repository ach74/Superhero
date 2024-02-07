# Proyecto Superhero

¡Bienvenido al proyecto Superhero! Este proyecto es una aplicación de ejemplo construida con Spring Boot que proporciona un servicio para el mantenimiento de superhéroes.

## Funcionalidades Principales
- Consultar todos los superhéroes.
- Consultar un único superhéroe por ID.
- Consultar superhéroes por nombre.
- Crear un superhéroe.
- Modificar un superhéroe.
- Eliminar un superhéroe.

## Tecnologías Utilizadas
- Java 21
- Spring Boot
- Maven
- H2 Database
- Swagger para documentación de la API
- Docker

## Estructura del Proyecto
El proyecto sigue una estructura típica de aplicación Spring Boot:


## Instrucciones de Uso
1. Clona el repositorio.
2. Construye el proyecto con Maven: `mvn clean install`.
3. Ejecuta la aplicación: `java -jar target/Superhero-0.0.1-SNAPSHOT.jar`.
4. Accede a la documentación de la API en Swagger: [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html).

## Instrucciones de Uso con docker
1. Clona el repositorio.
2. Ejecuta en CMD : `docker build -t superhero-app .`
3. Ejecuta por ultimo para levanta la imagen docker : `docker run -p 8090:8090 superhero-app`


