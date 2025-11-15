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

    private Long id;
    private String dni; //PK
    private String nombre;
    private String apellido;
    private String email;
    private Boolean eliminado;
    private java.time.LocalDate fechaIngreso;
    private String area;
    private Legajo legajo;
    
    // constructor
    public Empleado(){};
    
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
    
    public Empleado(long id, String dni, String nombre, String apellido, String email,
                boolean eliminado, java.time.LocalDate fechaIngreso, String area, Legajo legajo) {
    
    this.id = id;
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.eliminado = eliminado;
    this.fechaIngreso = fechaIngreso;
    this.area = area;
    this.legajo = legajo;
}
    

    
     // Getters
    public String getNombre(){
        return nombre;
    }

    public Long getId() {
        return id;
    }
    
    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
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
        long id,    
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
        legajo.setFechaAlta(fechaIngreso);
        legajo.setObservaciones(observaciones);

        return new Empleado(id,dni, nombre, apellido, email, false, fechaIngreso, area, legajo);
    }


    public void mostrarLegajo(Legajo legajo){
        
    }
    

    // setters
    public void setId(Long id) {
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


    

    @Override
    public String toString() {
    return  "Empleado\n" +
            "ID: " + id + "\n" +
            "DNI: " + dni + "\n" +
            "Nombre completo: " + nombre + " " + apellido + "\n" +
            "Email: " + email + "\n" +
            "Área: " + area + "\n" +
            "Fecha de ingreso: " + fechaIngreso + "\n" +
            "Legajo: " + (legajo != null ? legajo.getNroLegajo() : "sin asignar") +
            "\n";
}
}