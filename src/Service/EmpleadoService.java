/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Service;

import Config.DatabaseConnection;
import DAO.EmpleadoDAO;
import DAO.LegajoDAO;
import Models.Empleado;
import Models.Legajo;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Giuliano Scaglioni
 */
public class EmpleadoService implements GenericService<Empleado>{
    
    //llamamos a los DAO de Empleado Y legajo
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private LegajoDAO legajoDAO = new LegajoDAO();
    
    
    @Override
    public void insertar(Empleado empleado) throws Exception {
        Connection conn = null;
        
        try{
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); //transaccion manual
            
            // validaciones
            if (empleado == null) {
                throw new IllegalArgumentException("El empleado no puede ser nulo");
            }
            if (empleado.getDni() == null || empleado.getDni().trim().isEmpty()) {
                throw new IllegalArgumentException("El DNI es obligatorio");
            }
            if (empleado.getLegajo() == null) {
                throw new IllegalArgumentException("El empleado debe tener un legajo asignado");
            }
            
            // insert legajo
            Legajo legajo = empleado.getLegajo();
            legajoDAO.crear(legajo, conn);
            empleado.setLegajo(legajo);
            
            // crear empleado
            empleadoDAO.crear(empleado, conn);
            
            //commit
            conn.commit();
            System.out.println("Empleado insertado correctamente");
            
        } catch( Exception e){
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transacción revertida por error en la creación del empleado.");
                } catch (Exception rbEx) {
                    System.err.println("Error al hacer rollback: " + rbEx.getMessage());
                }
            }

            throw e; // relanzar
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
    public void actualizar(Empleado empleado) throws Exception {
        Connection conn = null;
        
        try{
            // conexion
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); //transaccion manual
            
            // validaciones
            if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo.");
        }
            if (empleado.getId() == null) {
                throw new IllegalArgumentException("El empleado debe tener un ID para actualizar.");
            }
            if (empleado.getDni() == null || empleado.getDni().trim().isEmpty()) {
                throw new IllegalArgumentException("El DNI es obligatorio.");
            }
            if (empleado.getLegajo() == null) {
                throw new IllegalArgumentException("El empleado debe tener un legajo asignado.");
            }
            if (empleado.getLegajo().getId() == null) { /// TODO arreglar eso
                throw new IllegalArgumentException("El legajo debe tener un ID para actualizar.");
            }
            
            // update legajo
             legajoDAO.actualizar(empleado.getLegajo(), conn);
             // update empleado
            empleadoDAO.actualizar(empleado, conn);
            
            // confirmar transaccion
            conn.commit();
            System.out.println("Empleado actualizado correctamente");
            
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
            throw e;
        }finally {
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
    public void eliminar(Long id) throws Exception {
        Connection conn =  null;
        
        try {
            // conexion
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); //transaccion manual
            
            // validar id
            if (id <= 0) {
                throw new IllegalArgumentException("El ID del empleado no es válido");
            }
            // validacion de empleado existente
            Empleado existente = empleadoDAO.leerPorId(id, conn);
            if( existente == null){
                throw new IllegalStateException("No existe un empleado con el ID especificado");
            }
            // ejecutamos baja logica
            empleadoDAO.eliminar(id, conn);
            
            // confirmamos transaccion
            conn.commit();
            System.out.println("Empleado id n° "+ id + " marcado como eliminado correctamente");
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
    public Empleado getById(Long id) throws Exception {
        //validacion de id
        if(id<=0){
            throw new IllegalArgumentException("El ID del empleado no es válido");
        }
        try (Connection conn = DatabaseConnection.getConnection()){
            Empleado empleado = empleadoDAO.leerPorId(id, conn);
            
            if (empleado == null){
                System.out.println("No se encontró un empleado con el ID especificado");
            } 
            return empleado;
        }catch( Exception e){
            System.out.println("Error obteniendo el legajo por ID: "+ e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Empleado> getAll() throws Exception { 
    try (Connection conn = DatabaseConnection.getConnection()) {

            return empleadoDAO.leerTodos(conn);

        } catch (Exception e) {
            System.err.println("Error al obtener todos los legajos: " + e.getMessage());
            throw e;
        }
    }      
}


