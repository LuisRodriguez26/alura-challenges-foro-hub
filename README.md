# Foro Hub - API Rest

Este proyecto es una API Rest para un sistema de foro, desarrollada como parte del desafío de **Alura Latam** y **Oracle Next Education**. La aplicación permite gestionar tópicos (mensajes), usuarios y autenticación segura.

## Tecnologías Utilizadas
* **Java 17**
* **Spring Boot 3**
* **Spring Security** (Autenticación JWT)
* **MySQL/PostgreSQL** (Persistencia de datos)
* **Flyway** (Migraciones de base de datos)
* **Hibernate/JPA**
* **Insomnia** (Pruebas de endpoints)

## Seguridad e Implementación
La API cuenta con un robusto sistema de seguridad:
* **BCrypt**: Las contraseñas de los usuarios se almacenan de forma encriptada en la base de datos.
* **JWT (JSON Web Token)**: Se requiere un token válido para acceder a los métodos de creación, listado y eliminación de tópicos.

## Cómo probar la API
1. **Login**: Realizar una petición `POST` a `/login` con las credenciales de usuario.
   - Recibirás un token JWT en la respuesta.
2. **Autorización**: Copia el token y agrégalo en la pestaña **Auth > Bearer Token** de tus peticiones en Insomnia.
3. **Crear Tópico**: Realiza un `POST` a `/topicos` con el cuerpo en formato JSON.

## Base de Datos
El proyecto utiliza una base de datos relacional para gestionar la persistencia. Se ha verificado la correcta vinculación entre autores y sus respectivos tópicos.

---
Desarrollado por: **Luis**
