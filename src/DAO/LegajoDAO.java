/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

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
 * @author Giuliano Scaglioni
 */
public class LegajoDAO implements GenericDAO<Legajo> {
    @Override

    public void crear(Legajo legajo, Connection conn) {
        String sql = "INSERT INTO legajo(nro_legajo, categoria, estado, fecha_alta, observaciones, eliminado) "
                   + "VALUES (?,?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, legajo.getNroLegajo());
            stmt.setString(2, legajo.getCategoria());
            stmt.setString(3, legajo.getEstado().name());
            stmt.setDate(4, java.sql.Date.valueOf(legajo.getFechaAlta()));
            stmt.setString(5, legajo.getObservaciones());
            stmt.setBoolean(6, legajo.getEliminado());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long nuevoId = generatedKeys.getLong(1);
                        legajo.setId(nuevoId); // asignamos ID generado
                        System.out.println("Nuevo Legajo cargado con ID: " + nuevoId);
                    }
                }

            } else {
                System.out.println("No se puede agregar el legajo.");
            }

        } catch (SQLException e) {
            System.err.println("Error al agregar el legajo: " + e.getMessage());
        }
    }
    
    //Mostrar legajo por ID
    @Override
    public Legajo leerPorId(Long id, Connection conn) {
    String sql = "SELECT legajo_nro_legajo FROM empleado WHERE id = ?";
    Legajo legajo = null;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setLong(1, id);
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
    @Override
    public void actualizar(Legajo legajo, Connection conn){
        String sql= "UPDATE legajo SET nro_legajo = ?, categoria = ?, estado = ?, fecha_alta = ?, observaciones = ?, eliminado= ? WHERE id=?"; 

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

             stmt.setString(1, legajo.getNroLegajo());
             stmt.setString(2, legajo.getCategoria());
             stmt.setString(3, legajo.getEstado().name());
             stmt.setDate(4,java.sql.Date.valueOf(legajo.getFechaAlta()));
             stmt.setString(5, legajo.getObservaciones());
             stmt.setBoolean(6, legajo.getEliminado());
             stmt.setLong(7, legajo.getId());

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
    @Override
    public List<Legajo> leerTodos(Connection conn) {
        String sql = "SELECT * FROM legajo";
        List<Legajo> legajos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
                legajo.setId(rs.getLong("id"));

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
    
    @Override
    public void eliminar (Long id, Connection conn){
        String sql = "DELETE FROM legajo WHERE id=?";
               
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

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