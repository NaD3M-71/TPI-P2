/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

/**
 *
 * @author Giuliano Scaglioni
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    void crear(T entidad, Connection conn) throws SQLException;
    T leerPorId(Long id, Connection conn) throws SQLException;
    List<T> leerTodos(Connection conn) throws SQLException;
    void actualizar(T entidad, Connection conn) throws SQLException;
    void eliminar(Long id, Connection conn) throws SQLException;
    
//    void crear(T entidad) throws SQLException;
//    T leerPorId(int id) throws SQLException;
//    List<T> leerTodos() throws SQLException;
//    void actualizar(T entidad) throws SQLException;
//    void eliminar(int id) throws SQLException;
}
