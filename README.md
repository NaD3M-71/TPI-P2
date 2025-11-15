# Sistema de Gestión de Empleados y Legajos

## Descripción del dominio

El proyecto implementa un sistema de gestión de empleados y legajos, orientado a una organización donde cada empleado debe contar con un legajo único asociado.  
La aplicación permite realizar:

- Altas, bajas lógicas y modificaciones de empleados.
- Altas, bajas lógicas y modificaciones de legajos.
- Consultas generales y filtradas.
- Control transaccional para garantizar integridad de datos.

El sistema está desarrollado en Java siguiendo una arquitectura por capas:  
UI de consola, Servicios, DAOs y Base de Datos.

---

## Requisitos y creación de la base de datos

### Requisitos técnicos

- Java 17 o superior  
- MySQL 8 o superior  
- Conector JDBC de MySQL (mysql-connector-j-8.4.0)  
- IDE recomendado: NetBeans / IntelliJ / Eclipse  
- Archivo de creación de base: `DB_Y_Tablas.sql`

### Creación de la base

1. Abrir MySQL Workbench, DBeaver u otro cliente SQL.
2. Ejecutar el archivo `DB_Y_Tablas.sql`.
3. El script crea la base `tpi_p2` y las siguientes tablas:

**legajo**
- id (PK, autoincremental)
- nro_legajo (único)
- categoria
- estado (ACTIVO/INACTIVO)
- fecha_alta
- observaciones
- eliminado (baja lógica)

**empleado**
- id (PK, autoincremental)
- dni (único)
- nombre
- apellido
- email
- fecha_ingreso
- area
- legajo_nro_legajo (único, FK a legajo.nro_legajo)
- eliminado (baja lógica)

---

## Cómo compilar y ejecutar
Ejecutar el archivo `Main.java`

### Configuración de la conexión

Editar `DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/tpi_p2";
private static final String USER = "root"; // modificar según instalación
private static final String PASSWORD = ""; // modificar según instalación
