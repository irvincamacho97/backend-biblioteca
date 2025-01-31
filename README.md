# BackendBiblioteca

Este proyecto fue desarrollado con **Java 17** y utiliza **Spring Boot** en su versión 3.4.2. El backend está configurado para conectarse a una base de datos **MySQL** y tiene rutas protegidas mediante autenticación con un **token**.

## Requisitos

Antes de ejecutar el backend, asegúrate de tener instalados los siguientes programas:

- **Java 17**.
- **Maven**.
- **MySQL**.

## Configuración de la Base de Datos

- **USER** -> root
- **PASSWORD** -> 123
- **PUERTO** -> 3370

## Creación de la bases de Datos

```MySql

-- Crea la BD
CREATE DATABASE DATABASE

-- Usar la base de datos predeterminada
USE db_biblioteca;

-- Crear tabla cat_rol_usuario
CREATE TABLE cat_rol_usuario (
    id_rol_usuario INT AUTO_INCREMENT PRIMARY KEY,
    rol_usuario VARCHAR(50) NOT NULL
);

-- Crear tabla usuario
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    id_rol_usuario INT NOT NULL,
    estatus TINYINT(1) DEFAULT 1,
    FOREIGN KEY (id_rol_usuario) REFERENCES cat_rol_usuario (id_rol_usuario)
);

-- Crear tabla libro
CREATE TABLE libro (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo_libro VARCHAR(200) NOT NULL,
    autor_libro VARCHAR(100) NOT NULL,
    anio_libro YEAR NOT NULL,
    genero_libro VARCHAR(50),
    estatus TINYINT(1) DEFAULT 1
);

-- Crear tabla cat_estatus_consulta
CREATE TABLE cat_estatus_consulta (
    id_estatus_consulta INT AUTO_INCREMENT PRIMARY KEY,
    estatus_consulta VARCHAR(50) NOT NULL
);

-- Crear tabla consulta_libro
CREATE TABLE consulta_libro (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario_consulta INT NOT NULL,
    id_libro INT NOT NULL,
    id_estatus_consulta INT NOT NULL,
    FOREIGN KEY (id_usuario_consulta) REFERENCES usuario (id_usuario),
    FOREIGN KEY (id_libro) REFERENCES libro (id_libro),
    FOREIGN KEY (id_estatus_consulta) REFERENCES cat_estatus_consulta (id_estatus_consulta)
);

-- Insertar roles en la tabla cat_rol_usuario
INSERT INTO cat_rol_usuario (rol_usuario)
VALUES 
    ('Administrador Usuario'),     
    ('Administrador Libros'),      
    ('Bibliotecario'),             
    ('Lector');                    

-- Insertar roles en la tabla cat_estatus_consulta
INSERT INTO cat_estatus_consulta (estatus_consulta) 
VALUES 
	('solicitado'),
 	('aprobado'),
	('devuelto');

-- Insertar Usuario para Pruebas
INSERT INTO usuario (correo, contrasena, nombre, apellido_paterno, apellido_materno, id_rol_usuario, estatus) 
VALUES 
('juan.perez@example.com', '123', 'Juan', 'Pérez', 'Gómez', 1, 1);

-- Insertar Libros para Pruebas
INSERT INTO libro (titulo_libro, autor_libro, anio_libro, genero_libro, estatus) 
VALUES 
('Cien años de soledad', 'Gabriel García Márquez', 1967, 'Realismo mágico', 1);

INSERT INTO libro (titulo_libro, autor_libro, anio_libro, genero_libro, estatus) 
VALUES 
('1984', 'George Orwell', 1949, 'Distopía', 1);
```
