# Proyecto BackEnd con Java y Spring Boot

# 📚 API Artículos

API REST desarrollada en Java y con Spring Boot para la gestión de artículos. Proyecto educativo de la carrera TalentoTech.

-> Se modifico para usar DTO en Artículos.
-> Se modificó para usar DTO en categorías.
-> Se agregó paquete Usuario.
-> Se agregó DTO para usuario, login y registro de usuarios.
-> Se agregó pack de Spring Security.
-> Se agregó token de autenticación JWT.
 


## 🎯 Descripción del Proyecto

**API Artículos** es una aplicación backend que proporciona una API REST completa para gestionar artículos. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una base de datos de artículos, con validaciones y gestión de errores.

## 🛠️ Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 4.1.0** - Framework web
- **Spring Data JPA** - Acceso a datos
- **MySQL** - Base de datos relacional
- **Maven** - Gestor de dependencias y construcción
- **REST API** - Arquitectura de servicios web

## 📁 Estructura del Proyecto

```
ApiArticulos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/proyectofinal/ApiArticulos/
│   │   │       ├── ApiArticulosApplication.java       # Clase principal
│   │   │       ├── controller/
│   │   │       │   └── ArticuloController.java        # Controlador REST
│   │   │       ├── model/
│   │   │       │   └── Articulo.java                   # Entidad de datos
│   │   │       ├── repository/
│   │   │       │   └── ArticuloRepository.java        # Acceso a datos
│   │   │       └── service/
│   │   │           ├── ArticuloService.java           # Interfaz de servicio
│   │   │           └── ArticuloServiceImplemets.java  # Implementación
│   │   └── resources/
│   │       └── application.properties                  # Configuración
│   └── test/
│       └── java/
│           └── ApiArticulosApplicationTests.java       # Pruebas
├── pom.xml                                              # Configuración Maven
└── README.md                                            # Este archivo
```

## 📋 Requisitos Previos

Asegúrate de tener instalados los siguientes componentes:

- **Java Development Kit (JDK) 21+** - [Descargar](https://www.oracle.com/java/technologies/downloads/#java21)
- **Apache Maven 3.8.1+** - [Descargar](https://maven.apache.org/download.cgi)
- **MySQL 8.0+** - [Descargar](https://www.mysql.com/downloads/)
- **Git** - [Descargar](https://git-scm.com/)

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/ApiArticulos.git
cd ApiArticulos
```

### 2. Configurar la Base de Datos

Crea una base de datos MySQL llamada `articulos_db`:

```sql
CREATE DATABASE articulos_db;
USE articulos_db;
```

### 3. Configurar las Credenciales de la Base de Datos

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/articulos_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

**Reemplaza `tu_contraseña` con la contraseña de tu usuario MySQL.**

### 4. Compilar el Proyecto

```bash
mvn clean install
```

## ▶️ Ejecución

### Opción 1: Desde la línea de comandos

```bash
mvn spring-boot:run
```

### Opción 2: Ejecutar el JAR

```bash
mvn package
java -jar target/ApiArticulos-0.0.1-SNAPSHOT.jar
```

### Opción 3: Desde IDE (IntelliJ, Eclipse, VS Code)

- Click derecho en `ApiArticulosApplication.java`
- Seleccionar "Run" o "Run As Application"

La aplicación estará disponible en: **http://localhost:8080**

## 📡 Endpoints de la API

### Base URL
```
http://localhost:8080/api/articulos
```

### 1. Listar todos los artículos

```http
GET /api/articulos
```

**Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Laptop Dell",
    "descripcion": "Laptop de alta performance",
    "precio": 1200.50,
    "cantidad": 5
  },
  {
    "id": 2,
    "nombre": "Mouse Logitech",
    "descripcion": "Mouse inalámbrico",
    "precio": 25.99,
    "cantidad": 50
  }
]
```

### 2. Obtener artículo por ID

```http
GET /api/articulos/{id}
```

**Parámetros:**
- `id` (Long) - ID del artículo

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "nombre": "Laptop Dell",
  "descripcion": "Laptop de alta performance",
  "precio": 1200.50,
  "cantidad": 5
}
```

**Respuesta (404 Not Found):** Si el artículo no existe

### 3. Crear nuevo artículo

```http
POST /api/articulos
Content-Type: application/json
```

**Body:**
```json
{
  "nombre": "Teclado Mecánico",
  "descripcion": "Teclado gaming RGB",
  "precio": 89.99,
  "cantidad": 15
}
```

**Respuesta (201 Created):**
```json
{
  "id": 3,
  "nombre": "Teclado Mecánico",
  "descripcion": "Teclado gaming RGB",
  "precio": 89.99,
  "cantidad": 15
}
```

### 4. Actualizar artículo

```http
PUT /api/articulos/{id}
Content-Type: application/json
```

**Parámetros:**
- `id` (Long) - ID del artículo

**Body:**
```json
{
  "nombre": "Teclado Mecánico RGB",
  "descripcion": "Teclado gaming RGB actualizado",
  "precio": 99.99,
  "cantidad": 20
}
```

**Respuesta (200 OK):** Artículo actualizado

**Respuesta (404 Not Found):** Si el artículo no existe

### 5. Eliminar artículo

```http
DELETE /api/articulos/{id}
```

**Parámetros:**
- `id` (Long) - ID del artículo

**Respuesta (204 No Content):** Artículo eliminado exitosamente

**Respuesta (404 Not Found):** Si el artículo no existe

## 🗄️ Modelo de Datos

### Entidad: Articulo

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | Identificador único (PK, Auto-increment) |
| nombre | String | Nombre del artículo |
| descripcion | String | Descripción detallada |
| precio | Double | Precio del artículo |
| cantidad | Integer | Stock disponible |

### Tabla MySQL

```sql
CREATE TABLE articulo (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT,
  precio DOUBLE NOT NULL,
  cantidad INT NOT NULL
);
```

## 🌐 CORS (Cross-Origin Resource Sharing)

La API tiene CORS habilitado para aceptar solicitudes desde cualquier origen:

```java
@CrossOrigin(origins = "*")
```

Esto permite que aplicaciones frontend desde diferentes dominios accedan a la API.

## 📚 Arquitectura

El proyecto sigue el patrón **MVC (Model-View-Controller)** con capas:

1. **Controller** - Maneja las solicitudes HTTP
2. **Service** - Contiene la lógica de negocio
3. **Repository** - Acceso a la base de datos (JPA)
4. **Model** - Entidades de datos

## 🔧 Configuración Adicional

### Cambiar Puerto

Edita `application.properties`:
```properties
server.port=8081
```

### Desabilitar SQL en Logs

```properties
spring.jpa.show-sql=false
```

### Cambiar Dialecto de Base de Datos

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 📦 Dependencias Principales

```xml
<!-- Spring Boot Web Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

## 🐛 Solución de Problemas

### "Connection refused" en MySQL

- Verifica que el servicio MySQL está ejecutándose
- Confirma que la base de datos `articulos_db` existe
- Valida las credenciales en `application.properties`

### "Port 8080 is already in use"

Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

### "No hibernate dialect set"

Asegúrate de que `application.properties` tiene:
```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
