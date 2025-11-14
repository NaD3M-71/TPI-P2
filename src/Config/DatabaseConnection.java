
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Giuliano Scaglioni
 */
public class DatabaseConnection {
    // url de conexion local
    private static final String URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/tpi_p2");
    // Usuario de la BD
    private static final String USER = System.getProperty("db.user", "root");
    // Password de la BD
<<<<<<< HEAD
    private static final String PASSWORD = System.getProperty("db.password", "Heberato1990");  
=======
    private static final String PASSWORD = System.getProperty("db.password", "");  
>>>>>>> b8e45f776083cea272096cacc132fe4fca31d999
    
    public static Connection getConnection() throws SQLException {
        validateConfiguration();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    private static void validateConfiguration() {
        if (URL == null || URL.trim().isEmpty()) {
            throw new IllegalStateException("La URL de la base de datos no está configurada");
        }
        if (USER == null || USER.trim().isEmpty()) {
            throw new IllegalStateException("El usuario de la base de datos no está configurado");
        }
        // PASSWORD puede ser vacío (común en MySQL local con usuario root sin contraseña)
        // Solo validamos que no sea null
        if (PASSWORD == null) {
            throw new IllegalStateException("La contraseña de la base de datos no está configurada");
        }
    }
    
}
