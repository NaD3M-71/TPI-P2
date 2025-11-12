/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Giuliano Scaglioni
 */
public interface GenericDAO<T> {
    void crear(T entidad, Connection conn) throws SQLException;
    T leerPorId(String id, Connection conn) throws SQLException;
    List<T> leerTodos(Connection conn) throws SQLException;
    void actualizar(T entidad, Connection conn) throws SQLException;
    void eliminar(String id, Connection conn) throws SQLException;
}
