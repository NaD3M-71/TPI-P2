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
public class Legajo {
    private Long id;
    private String nroLegajo;
    private Boolean eliminado;
    private String categoria;
    private Estado estado;
    private java.time.LocalDate fechaAlta;
    private String observaciones;
    
    // constructor
    public Legajo() {}


    public Legajo(String nroLegajo, Boolean eliminado, String categoria,Estado estado, java.time.LocalDate fechaAlta, String observaciones) {


        this.nroLegajo = nroLegajo;
        this.eliminado = eliminado;
        this.categoria = categoria;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.observaciones = observaciones;
    }
    
    //getters
    public String getNroLegajo() { return nroLegajo; }
    public Boolean getEliminado() { return eliminado; }
    public String getCategoria() { return categoria; }
    public Estado getEstado() { return estado; }
    public java.time.LocalDate getFechaAlta() { return fechaAlta; }
    public String getObservaciones() { return observaciones; }
    public Long getId() { return id; }
    


    
    //settersLegajo
    public void setNroLegajo(String nroLegajo) { this.nroLegajo = nroLegajo; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public void setFechaAlta(java.time.LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public void setId(Long id) { this.id = id;}
    
    
    @Override
    public String toString() {
        return  "Legajo\n" +
            "ID: " + id + "\n" +
            "Número de legajo: " + nroLegajo + "\n" +
            "Categoría: " + categoria + "\n" +
            "Estado: " + estado + "\n" +
            "Fecha de alta: " + fechaAlta + "\n" +
            "Observaciones: " + (observaciones == null ? "Sin Observaciones": observaciones) +"\n"+
            "Eliminado: " + (eliminado == true ? "Si" : "No");

    }
}