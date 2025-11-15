CREATE DATABASE IF NOT EXISTS tpi_p2;

CREATE TABLE legajo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nro_legajo VARCHAR(20) UNIQUE,
    categoria VARCHAR(30),
    estado ENUM('ACTIVO','INACTIVO') NOT NULL,
    fecha_alta DATE,
    observaciones VARCHAR(255),
    eliminado BOOLEAN DEFAULT FALSE
);

CREATE TABLE empleado (
	id INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(15) UNIQUE,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    email VARCHAR(120),
    fecha_ingreso DATE,
    area VARCHAR(50),
    legajo_nro_legajo VARCHAR(20) UNIQUE,
    eliminado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (legajo_nro_legajo)
        REFERENCES legajo(nro_legajo)
        ON DELETE CASCADE
);

SELECT * FROM empleado;