# ForoHub API

## 📖 Descripción del proyecto
ForoHub API es una aplicación backend desarrollada en **Java Spring Boot**, que permite la gestión de un foro en línea.  
Incluye autenticación mediante **JWT** y almacenamiento seguro de contraseñas con **BCrypt**.  
La base de datos utilizada es **MySQL**.

---

## ⚡ Funcionalidades
- Registro de usuarios (opcional, si implementas registro).
- Inicio de sesión con JWT.
- Creación, actualización, consulta y eliminación de tópicos.
- Listado de tópicos ordenados por fecha de creación.
- Validación de duplicados: no se permite crear un tópico con el mismo título y mensaje.
- Seguridad: solo los usuarios autenticados pueden acceder a ciertos endpoints.

---

## 🚀 Cómo usarlo
mvn spring-boot:run
http://localhost:8080
POST /login desde Postman o Insomnia
Content-Type: application/json

{
"login": "usuario",
"contrasena": "tuPasword"
}
Authorization: Bearer <token>


## 📝 Tecnologías utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Token)
- MySQL 9.x
- Maven
- Lombok

### 1. Configuración
1. Crear la base de datos MySQL:
```sql
CREATE DATABASE forohub;





Endpoints principales:
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| POST | /login | Inicia sesión y obtiene token JWT |
| GET | /topicos | Lista todos los tópicos |
| GET | /topicos/{id} | Obtiene un tópico por ID |
| POST | /topicos | Crea un nuevo tópico |
| DELETE | /topicos/{id} | Elimina un tópico |
| PUT | /topicos/{id} | Actualiza un tópico |


❓ Ayuda
Para problemas con la base de datos: revisa que la variable de entorno DB_PASSWORD esté configurada correctamente.

Para problemas de autenticación: verifica que el usuario exista y la contraseña sea correcta.

Si encuentras errores de Spring Boot o JWT, revisa los logs en la consola para más detalles.


⚡ Notas
Las contraseñas de los usuarios se guardan en hash BCrypt, nunca en texto plano.

El token JWT expira en 2 horas.

La aplicación está lista para correr en cualquier entorno que tenga Java y MySQL.


Autores
Luis Cortez – Desarrollo backend, Spring Boot, seguridad y JWT.

 
 
 
