/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;


import Config.DatabaseConnection;
import Models.Empleado;
import Models.Legajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Giuliano Scaglioni
 */

public class EmpleadoDAO implements GenericDAO<Empleado>{
    @Override
    public void crear (Empleado empleado,Connection conn){
        String sql = "INSERT INTO empleado (dni, nombre, apellido, email, fecha_ingreso, area, legajo_nro_legajo, eliminado) VALUES (?,?,?,?,?,?,?,?)";
       
        try (PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                
                //Asignamos valores a la tabla legajo
                stmt.setString(1, empleado.getDni());
                stmt.setString(2, empleado.getNombre());
                stmt.setString(3, empleado.getApellido());
                stmt.setString(4, empleado.getEmail());
                stmt.setDate(5, java.sql.Date.valueOf(empleado.getFechaIngreso()));
                stmt.setString(6, empleado.getArea());
                //aca si bien getLegajo es clase Legajo como PreparedStat... solo maneja datos String o int lo llamamos como String en relaciona la vinculacion al segundo get que obtenemos de la clase Legajo. para hacer la vinculacion
                stmt.setString(7, empleado.getLegajo().getNroLegajo()); 
                stmt.setBoolean(8, empleado.isEliminado());
                        
                //ejecutamos el sql
                int filasAfectadas =stmt.executeUpdate();
                
                if (filasAfectadas >0){
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                        if(generatedKeys.next()){
                            int nuevoId=generatedKeys.getInt(1);
                            System.out.println("Nuevo Empleado cargado con ID: "+ nuevoId);
                                    
                        }
                    }
                    
                }else {
                    System.out.println("No se puede agregar el Empleado.");
                }
      
                
        }
        catch (SQLException e){
            System.err.println("Error al agregar el empleado: "+ e.getMessage());
                        
        }
        

    }
    
        //Mostrar empleado por ID
    @Override
    public Empleado leerPorId (Long id,Connection conn){
        String sql ="SELECT * FROM empleado WHERE id=?";
        Empleado empleado = null;
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs =stmt.executeQuery();
            
            if(rs.next()){

                empleado = new Empleado(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getBoolean("eliminado"),
                    rs.getDate("fecha_ingreso").toLocalDate(),
                    rs.getString("area"),
                    null // Legajo se asigna después
            );
            empleado.setId(rs.getLong("id")); 
            
            
             // Asignar legajo
            Legajo legajo = new Legajo();
            legajo.setNroLegajo(rs.getString("legajo_nro_legajo"));
            empleado.setLegajo(legajo);

                
            } else{
                System.out.println("Empleado no encontrado");
            }
            
        } catch (SQLException e){
            System.err.println("Error al buscar empleado " + e.getMessage());
          
        } catch (NumberFormatException e){
            System.err.println("Id invalido: "+ e.getMessage());
        }
        
        return empleado;
    }
    //Actualizar empleado
    @Override
    public void actualizar(Empleado empleado,Connection conn){
        String sql= "UPDATE empleado SET dni = ?, nombre = ?, apellido = ?, email = ?, fecha_ingreso= ?, area=?, legajo_nro_legajo = ?, eliminado =? WHERE id=?"; 

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

             stmt.setString(1, empleado.getDni());
             stmt.setString(2, empleado.getNombre());
             stmt.setString(3, empleado.getApellido());
             stmt.setString(4, empleado.getEmail());
             stmt.setDate(5, java.sql.Date.valueOf(empleado.getFechaIngreso()));
             stmt.setString(6, empleado.getArea());
             stmt.setString(7, empleado.getLegajo() != null ? empleado.getLegajo().getNroLegajo() : null);
             stmt.setBoolean(8, empleado.isEliminado());
             stmt.setLong(9, empleado.getId());


             int filasAfectadas =stmt.executeUpdate();
             if(filasAfectadas>0){
                 System.out.println("Empleado actualizado con exito");
             } else {
                 System.out.println("No se encontro el empleado");
             }
        } catch (SQLException e){
            System.err.println("Error al actualizar el empleado " + e.getMessage());

        } catch (NumberFormatException e){
            System.err.println("Id invalido " + e.getMessage());
        }
    }
    @Override
    public List<Empleado> leerTodos(Connection conn) {
    String sql = "SELECT * FROM empleado";
    List<Empleado> empleados = new ArrayList<>();

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Crear el objeto Empleado con el constructor correcto
            
            Empleado empleado = new Empleado(
                    
                rs.getLong("id"),    
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getBoolean("eliminado"),
                rs.getDate("fecha_ingreso").toLocalDate(), // Convertir a LocalDate
                rs.getString("area"),
                null // Legajo se asigna después
            );

            // Asignar el ID
            //empleado.setId(rs.getLong("id"));

            // Crear y asignar el Legajo
            Legajo legajo = new Legajo();
            legajo.setNroLegajo(rs.getString("legajo_nro_legajo"));
            empleado.setLegajo(legajo);

            // Agregar el empleado a la lista
            empleados.add(empleado);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar empleados: " + e.getMessage());
    }
        if (empleados.isEmpty()){
            System.out.println("No hay empleados en la base de datos");
        }
    return empleados;
}
       

    @Override
    public void eliminar (Long id,Connection conn){
    String sql = "UPDATE empleado SET eliminado = TRUE WHERE id = ?";


    // baja logica de empleado
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setLong(1, id);

        int filasAfectadas =stmt.executeUpdate();

        if (filasAfectadas >0){
            System.out.println("Empleado eliminado exitosamente.");

        }else {
            System.out.println("No se encontro el empleado con ID: "+id);
        }

        
    } catch (SQLException e){
        System.err.println("Error al eliminar el empleado: " + e.getMessage());
    } catch (NumberFormatException e){
        System.err.println("Id invalido: "+ e.getMessage());
    }
                
    }
}    