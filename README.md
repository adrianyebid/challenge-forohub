# ForoHub

ForoHub es una API REST diseñada para gestionar foros donde los participantes pueden crear, consultar, actualizar y eliminar tópicos relacionados con temas de interés. Inspirado en plataformas de aprendizaje como Alura, ForoHub facilita el aprendizaje colaborativo al replicar la estructura backend que sustenta estos sistemas.

## Características principales

- **CRUD de Tópicos**:  
  Permite a los usuarios realizar las siguientes operaciones:
  - Crear un nuevo tópico.
  - Consultar todos los tópicos.
  - Consultar un tópico específico.
  - Actualizar un tópico.
  - Eliminar un tópico.

- **Autenticación y Autorización**:  
  Implementa seguridad mediante un servicio de autenticación basado en JSON Web Tokens (JWT) para proteger el acceso a los recursos.

- **Persistencia de datos**:  
  Utiliza una base de datos relacional para almacenar información de forma persistente.

- **Validaciones robustas**:  
  Garantiza que los datos cumplan con las reglas de negocio mediante validaciones en el modelo.

- **API RESTful**:  
  Sigue las mejores prácticas para el diseño de APIs REST, asegurando escalabilidad y mantenibilidad.

## Tecnologías utilizadas

ForoHub está construido con las siguientes tecnologías y herramientas:

- **Java**  
- **Spring Boot**: Framework principal para la creación de la API.
  - **Spring Data JPA**: Para la interacción con la base de datos.
  - **Spring Security**: Para autenticación y autorización.
  - **Spring Boot Validation**: Para validación de datos.
  - **Spring Boot Web**: Para la implementación de controladores y rutas REST.
- **Flyway**: Migración y gestión de esquemas de base de datos.
- **MySQL**: Base de datos utilizada para la persistencia de información.
- **Lombok**: Para reducir el código boilerplate.
- **JSON Web Tokens (JWT)**: Para la autenticación segura.

## Requisitos previos

Para ejecutar este proyecto en tu entorno local, asegúrate de tener instalados los siguientes componentes:

1. **Java 17 o superior**.
2. **Maven**: Para gestionar dependencias y construir el proyecto.
3. **MySQL**: Configura una base de datos y actualiza las credenciales en el archivo `application.properties` del proyecto.

## Instalación y ejecución

Sigue estos pasos para ejecutar ForoHub en tu máquina local:

1. **Clona este repositorio**:
   ```bash
   git clone https://github.com/adrianyebid/challenge-forohub.git
   cd challenge-forohub
   
## Configura la base de datos

Crea una base de datos MySQL y asegúrate de configurar las credenciales en el archivo `src/main/resources/application.properties`.

## Ejecuta las migraciones

Flyway gestionará las migraciones de esquema automáticamente al ejecutar la aplicación.

## Compila y ejecuta el proyecto

Utiliza Maven para compilar y ejecutar la aplicación:

```bash
mvn spring-boot:run


