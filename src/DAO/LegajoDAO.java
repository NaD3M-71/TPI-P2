/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

<<<<<<< HEAD
import Config.DatabaseConnection;
import Models.Legajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Main.Estado;

/**
 *
 * @author Gustavo Silva
 */
public class LegajoDAO implements GenericDAO<Legajo> {
    public void crear (Legajo legajo){
        String sql = "INSERT INTO legajo(nro_legajo, categoria, estado, fecha_alta, observaciones, eliminado) VALUES (?,?,?,?,?,?)";
       
        try (Connection conn =DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                
                //Asignamos valores a la tabla legajo
                stmt.setString(1, legajo.getNroLegajo());
                stmt.setString(2, legajo.getCategoria());
                stmt.setString(3, legajo.getEstado().name());
                stmt.setDate(4, java.sql.Date.valueOf(legajo.getFechaAlta()));
                stmt.setString(5, legajo.getObservaciones());
                stmt.setBoolean(6, legajo.getEliminado());
                //ejecutamos el sql
                int filasAfectadas =stmt.executeUpdate();
                
                if (filasAfectadas >0){
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                        if(generatedKeys.next()){
                            int nuevoId=generatedKeys.getInt(1);
                            System.out.println("Nuevo Legajo cargado con ID: "+ nuevoId);
                                    
                        }
                    }
                    
                }else {
                    System.out.println("No se puede agregar el legajo.");
                }
      
                
        }
        catch (SQLException e){
            System.err.println("Error al agregar el legajo: " +e.getMessage());
                        
        }
    };
    
    //Mostrar legajo por ID
    public Legajo leerPorId(int id) {
    String sql = "SELECT legajo_nro_legajo FROM empleado WHERE id = ?";
    Legajo legajo = null;

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            legajo = new Legajo();
            legajo.setNroLegajo(rs.getString("legajo_nro_legajo"));
        } else {
            System.out.println("Empleado no encontrado");
        }

    } catch (SQLException e) {
        System.err.println("Error al buscar legajo: " + e.getMessage());
    }

    return legajo;
}

//Actualizar legajo
public void actualizar(Legajo legajo){
    String sql= "UPDATE legajo SET nro_legajo = ?, categoria = ?, estado = ?, fecha_alta = ?, observaciones = ?, eliminado= ? WHERE id=?"; 
    
    try (Connection conn =DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)){
         
         stmt.setString(1, legajo.getNroLegajo());
         stmt.setString(2, legajo.getCategoria());
         stmt.setString(3, legajo.getEstado().name());
         stmt.setDate(4,java.sql.Date.valueOf(legajo.getFechaAlta()));
         stmt.setString(5, legajo.getObservaciones());
         stmt.setBoolean(6, legajo.getEliminado());
         stmt.setInt(7, legajo.getId());
         
         int filasAfectadas =stmt.executeUpdate();
         if(filasAfectadas>0){
             System.out.println("Legajo actualizado con exito");
         } else {
             System.out.println("No se encontro el legajo");
         }
    } catch (SQLException e){
        System.err.println("Error al actualizar el legajo " + e.getMessage());
        
    } catch (NumberFormatException e){
        System.err.println("Id invalido " + e.getMessage());
    }
} 
    // Mostrar todos los legajos.
public List<Legajo> leerTodos() {
    String sql = "SELECT * FROM legajo";
    List<Legajo> legajos = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Crear el objeto Legajo usando el constructor

            Legajo legajo = new Legajo(
                rs.getString("nro_legajo"),
                rs.getBoolean("eliminado"),
                rs.getString("categoria"),
                Estado.valueOf(rs.getString("estado")),
                rs.getDate("fecha_alta").toLocalDate(),
                rs.getString("observaciones")
            );


            // Si necesitas asignar el ID (porque no está en el constructor):
            legajo.setId(rs.getInt("id"));

            // Agregar el legajo a la lista
            legajos.add(legajo);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar legajos: " + e.getMessage());
    }

    if (legajos.isEmpty()) {
        System.out.println("No hay legajos en la base de datos");
    } else {
        System.out.println("\n LISTA DE LEGAJOS");
        legajos.forEach(System.out::println);
    }

    return legajos;
}

    public void eliminar (int id){
        String sql = "DELETE FROM legajo WHERE id=?";
               
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);

            int filasAfectadas =stmt.executeUpdate();
            
            if (filasAfectadas >0){
                System.out.println("Legajo eliminado exitosamente.");
                
            }else {
                System.out.println("No se encontro el Legajon con ID: "+id);
            }
        } catch (SQLException e){
            System.err.println("Error al eliminar el legajo: " + e.getMessage());
        } catch (NumberFormatException e){
            System.err.println("Id invalido: "+ e.getMessage());
        }
                
    }

 
}

=======
/**
 *
 * @author Giuliano Scaglioni
 */
public class LegajoDAO {

}
>>>>>>> b8e45f776083cea272096cacc132fe4fca31d999
