/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Service;

import DAO.LegajoDAO;
import Config.DatabaseConnection;
import Models.Legajo;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Giuliano Scaglioni
 */
public class LegajoService implements GenericService<Legajo>{
    private LegajoDAO legajoDAO = new LegajoDAO();
    
    @Override
    public void insertar(Legajo legajo) throws Exception {
        Connection conn = null;
        
        try{
            // conexion
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); //transaccion manual
            
            //validaciones null
            if (legajo== null){
                throw new IllegalArgumentException("El legajo no puede ser nulo");
            }
            if (legajo.getNroLegajo() == null || legajo.getNroLegajo().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de legajo es obligatorio");
            }
            if (legajo.getEstado() == null) {
                throw new IllegalArgumentException("El estado del legajo no puede ser nulo");
            }
            
            // validacion de legajo existente
            Legajo existente = legajoDAO.leerPorId(legajo.getId(),conn);
            if( existente == null){
                throw new IllegalStateException("No existe un legajo con ese id");
            }
            
            // insertar legajo nuevo
            legajoDAO.crear(legajo,conn);
            
            // confirmar transaccion
            conn.commit();
            System.out.println("Legajo insertado correctamente");
        } catch (Exception e) {
            // rollback
            if( conn != null) {
                try{
                    conn.rollback();
                    System.out.println("ERROR, transaccion revertida");
                } catch (Exception rbEx){
                    System.err.println("Error al hacer Rollback" + rbEx.getMessage());
                }
            }
            throw e; // lanzamos el error para que lo maneje la capa superior
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (Exception closeEx) {
                    System.err.println("Error al cerrar la conexión: " + closeEx.getMessage());
                }
            }   
        }
    }
    
    @Override
    public void actualizar(Legajo legajo) throws Exception{
        Connection conn = null;
        
        try{
            // conexion
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); //transaccion manual
            
            // validaciones
            if (legajo == null) {
            throw new IllegalArgumentException("El legajo no puede ser nulo");
            }
            if (legajo.getId() <= 0) {
                throw new IllegalArgumentException("El ID del legajo no es válido");
            }
            if (legajo.getNroLegajo() == null || legajo.getNroLegajo().trim().isEmpty()) {
                throw new IllegalArgumentException("El número de legajo es obligatorio");
            }
            if (legajo.getEstado() == null) {
                throw new IllegalArgumentException("El estado del legajo no puede ser nulo");
            }
            
            // validacion de legajo existente
            Legajo existente = legajoDAO.leerPorId(legajo.getId(), conn);
            if( existente != null){
                throw new IllegalStateException("Ya existe un legajo con el ID especificado");
            }
            
            // actualizar legajo
            legajoDAO.actualizar(legajo,conn);
            
            // confirmar transaccion
            conn.commit();
            System.out.println("Legajo insertado correctamente");
        }  catch (Exception e) {
            // rollback
            if( conn != null) {
                try{
                    conn.rollback();
                    System.out.println("ERROR, transaccion revertida");
                } catch (Exception rbEx){
                    System.err.println("Error al hacer Rollback" + rbEx.getMessage());
                }
            }
            throw e; // lanzamos el error para que lo maneje la capa superior
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (Exception closeEx) {
                    System.err.println("Error al cerrar la conexión: " + closeEx.getMessage());
                }
            }   
        }
    }
    
    @Override
    public void eliminar(Long id) throws Exception{
        Connection conn =  null;
        
        try {
            // conexion
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); //transaccion manual
            
            // validar id
            if (id <= 0) {
                throw new IllegalArgumentException("El ID del legajo no es válido");
            }
            
            // validacion de legajo existente
            Legajo existente = legajoDAO.leerPorId(id,conn);
            if( existente == null){
                throw new IllegalStateException("No existe un legajo con el ID especificado");
            }
            
            // ejecutamos baja logica
            legajoDAO.eliminar(id,conn);
            
            // confirmar transaccion
            conn.commit();
            System.out.println("Legajo n° "+ id + " marcado como eliminado correctamente");
        } catch (Exception e) {
            // rollback
            if( conn != null) {
                try{
                    conn.rollback();
                    System.out.println("ERROR, transaccion revertida");
                } catch (Exception rbEx){
                    System.err.println("Error al hacer Rollback" + rbEx.getMessage());
                }
            }
            throw e; // lanzamos el error para que lo maneje la capa superior
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (Exception closeEx) {
                    System.err.println("Error al cerrar la conexión: " + closeEx.getMessage());
                }
            }   
        }
    }
    
    @Override
    public Legajo getById(Long id) throws Exception{
        
        //validacion de id
        if(id<=0){
            throw new IllegalArgumentException("El ID del legajo no es válido");
        }
        
        try (Connection conn = DatabaseConnection.getConnection()){
            Legajo legajo = legajoDAO.leerPorId(id,conn);
            
            if(legajo == null){
                System.out.println("No se encontró un legajo con el ID especificado");
            }
            
            return legajo;
        } catch( Exception e){
            System.out.println("Error obteniendo el legajo por ID: "+ e.getMessage());
            throw e;
        }
    }
            
    @Override
    public List<Legajo> getAll() throws Exception {

        try (Connection conn = DatabaseConnection.getConnection()) {

            return legajoDAO.leerTodos(conn);

        } catch (Exception e) {
            System.err.println("Error al obtener todos los legajos: " + e.getMessage());
            throw e;
        }
    }      
}

