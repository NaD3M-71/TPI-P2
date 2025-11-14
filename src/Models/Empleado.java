/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

import Main.Estado;

/**
 *
 * @author Giuliano Scaglioni
 */
public class Empleado {
<<<<<<< HEAD
    private int id;
=======
>>>>>>> b8e45f776083cea272096cacc132fe4fca31d999
    private String dni; //PK
    private String nombre;
    private String apellido;
    private String email;
    private Boolean eliminado;
    private java.time.LocalDate fechaIngreso;
    private String area;
    private Legajo legajo;
    
    // constructor
    public Empleado(String dni, String nombre, String apellido, String email,
                boolean eliminado, java.time.LocalDate fechaIngreso, String area, Legajo legajo) {
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.eliminado = eliminado;
    this.fechaIngreso = fechaIngreso;
    this.area = area;
    this.legajo = legajo;
}
    
<<<<<<< HEAD
    
     // Getters
    public String getNombre(){
        return nombre;
    }

    public int getId() {
        return id;
    }
    
    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
=======
    // Getters
    public String getNombreCompleto(){
        return nombre+", "+apellido ;
    }
    public String getEmail(){
>>>>>>> b8e45f776083cea272096cacc132fe4fca31d999
        return email;
    }
    public String getDni() {
        return dni;
    }
    public boolean isEliminado() {
    return eliminado;
    }
    public java.time.LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
    public String getArea() {
        return area;
    }
    public Legajo getLegajo() {
        return legajo;
    }
    
    // setters
    public void setLegajo(Legajo legajo){
        this.legajo = legajo;
    }
    //crearEmpleado
    public static Empleado crearEmpleado(
        String dni,
        String nombre,
        String apellido,
        String email,
        String categoria,
        Estado estado,
        String observaciones,
        String area

    ){
        java.time.LocalDate fechaIngreso = java.time.LocalDate.now();

        Legajo legajo = new Legajo();
        legajo.setNroLegajo(Legajo.generarNroLegajoUnico()); //TODO
        legajo.setEliminado(false);
        legajo.setCategoria(categoria);
        legajo.setEstado(estado);
        legajo.setFechaIngreso(fechaIngreso);
        legajo.setObservaciones(observaciones);

        return new Empleado(dni, nombre, apellido, email, false, fechaIngreso, area, legajo);
    }


    public void mostrarLegajo(Legajo legajo){
        
    }
    
<<<<<<< HEAD
    // setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setFechaIngreso(java.time.LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setArea(String area) {
        this.area = area;
    }


    
=======
>>>>>>> b8e45f776083cea272096cacc132fe4fca31d999
    @Override
    public String toString() {
    return "Empleado{" +
           ", dni='" + dni + '\'' +
           ", nombre='" + nombre + '\'' +
           ", apellido='" + apellido + '\'' +
           ", email='" + email + '\'' +
           ", area='" + area + '\'' +
           ", fechaIngreso=" + fechaIngreso +
           ", legajo=" + (legajo != null ? legajo.getNroLegajo() : "sin asignar") +
           '}';
    }
<<<<<<< HEAD
    
=======
>>>>>>> b8e45f776083cea272096cacc132fe4fca31d999

}
