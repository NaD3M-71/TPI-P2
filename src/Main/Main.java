/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Models.Empleado;
import Models.Legajo;
import Service.EmpleadoService;

/**
 *
 * @author giuli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            EmpleadoService empleadoService = new EmpleadoService();

            // -----------------------------
            // CREAR LEGAJO (OBJETO EN MEMORIA)
            // -----------------------------
            Legajo leg = new Legajo();
            leg.setNroLegajo("L-1001");
            leg.setCategoria("Administrativo");
            leg.setEstado(Estado.ACTIVO);
            leg.setFechaAlta(java.time.LocalDate.now());
            leg.setObservaciones("Sin observaciones");
            leg.setEliminado(false);

            // -----------------------------
            // CREAR EMPLEADO (OBJETO)
            // -----------------------------
            Empleado emp = new Empleado();
            emp.setDni("40123456");
            emp.setNombre("Juan");
            emp.setApellido("Pérez");
            emp.setEmail("juan.perez@test.com");
            emp.setFechaIngreso(java.time.LocalDate.of(2024, 1, 1));
            emp.setArea("Recursos Humanos");
            emp.setEliminado(false);

            // ASIGNAR EL LEGAJO
            emp.setLegajo(leg);

            // -----------------------------
            // INSERTAR
            // -----------------------------
            empleadoService.insertar(emp);

            System.out.println("\n✔ Prueba finalizada: empleado insertado con legajo.");

        } catch (Exception e) {
            System.err.println("❌ Error en la prueba: " + e.getMessage());
        }
    }
    
    
    
}
