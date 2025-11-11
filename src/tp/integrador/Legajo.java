/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tp.integrador;

/**
 *
 * @author Giuliano Scaglioni
 */
public class Legajo {
    private String nroLegajo;
    private Boolean eliminado;
    private String categoria;
    private Estado estado;
    private java.time.LocalDate fechaAlta;
    private String observaciones;
    
    // constructor
    public Legajo() {}

    public Legajo(Long id, String nroLegajo, Boolean eliminado, String categoria,
                  Estado estado, java.time.LocalDate fechaAlta, String observaciones) {

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

    
    //settersLegajo
    public void setNroLegajo(String nroLegajo) { this.nroLegajo = nroLegajo; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public void setFechaIngreso(java.time.LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public static String generarNroLegajoUnico() {
    return "LEG-" + java.util.UUID.randomUUID().toString();
    }
    
    
    @Override
    public String toString() {
        return "Legajo{" +
               ", nroLegajo='" + nroLegajo + '\'' +
               ", categoria='" + categoria + '\'' +
               ", estado=" + estado +
               ", fechaAlta=" + fechaAlta +
               ", observaciones='" + observaciones + '\'' +
               '}';
    }

}
