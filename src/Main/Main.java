/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Models.Empleado;
import Models.Legajo;
import Service.EmpleadoService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author giuli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        EmpleadoService empleadoService = new EmpleadoService();
//        try {
//            //EmpleadoService empleadoService = new EmpleadoService();
//
//            // -----------------------------
//            // CREAR LEGAJO (OBJETO EN MEMORIA)
//            // -----------------------------
//            Legajo leg = new Legajo();
//            leg.setNroLegajo("L-1001");
//            leg.setCategoria("Administrativo");
//            leg.setEstado(Estado.ACTIVO);
//            leg.setFechaAlta(java.time.LocalDate.now());
//            leg.setObservaciones("Sin observaciones");
//            leg.setEliminado(false);
//
//            // ------------------------23-----
//            // CREAR EMPLEADO (OBJETO)
//            // -----------------------------
//            Empleado emp = new Empleado();
//            emp.setDni("40123456");
//            emp.setNombre("Juan");
//            emp.setApellido("Pérez");
//            emp.setEmail("juan.perez@test.com");
//            emp.setFechaIngreso(java.time.LocalDate.of(2024, 1, 1));
//            emp.setArea("Recursos Humanos");
//            emp.setEliminado(false);
//
//            // ASIGNAR EL LEGAJO
//            emp.setLegajo(leg);
//
//            // -----------------------------
//            // INSERTAR
//            // -----------------------------
//            empleadoService.insertar(emp);
//
//            System.out.println("\n✔ Prueba finalizada: empleado insertado con legajo.");
//
//        } catch (Exception e) {
//            System.err.println("❌ Error en la prueba: " + e.getMessage());
//        }
    
            String[] opciones={"Ingresar Empleado","Listar empleados","Buscar empleado por ID","Actualizar Empleado", "Eliminar"};
            Runnable[] acciones = {
            
                () -> { 
                    try{
                        System.out.println("-------INGRESAR EMPLEADO-------");
                        // 1. SOLICITAR DATOS DEL EMPLEADO
                        System.out.print("Ingrese DNI: ");
                        String dni = scanner.nextLine();
                    
                        System.out.print("Ingrese Nombre: ");
                        String nombre = scanner.nextLine();
                    
                        System.out.print("Ingrese Apellido: ");
                        String apellido = scanner.nextLine();
                    
                        System.out.print("Ingrese Área: ");
                        String area = scanner.nextLine();
                        
                        System.out.print("Opcional - Ingrese Email: ");
                        String email = scanner.nextLine();
                    
                        // Opcional: Solicitar fecha de ingreso (manejo simple)
                        System.out.print("Ingrese Fecha de Ingreso (YYYY-MM-DD): ");
                        LocalDate fechaIngreso = LocalDate.parse(scanner.nextLine());
                        
                        // Crear Legajo
                        Legajo nuevoLegajo = new Legajo();
                        nuevoLegajo.setNroLegajo("L-" + dni); 
                        nuevoLegajo.setCategoria("A Designar");
                        nuevoLegajo.setEstado(Estado.ACTIVO); // Descomentar al importar/definir Estado
                        nuevoLegajo.setFechaAlta(LocalDate.now());
                        nuevoLegajo.setEliminado(false);
                    
                        // Crear Empleado
                        Empleado nuevoEmpleado = new Empleado();
                        nuevoEmpleado.setDni(dni);
                        nuevoEmpleado.setNombre(nombre);
                        nuevoEmpleado.setApellido(apellido);
                        nuevoEmpleado.setArea(area);
                        nuevoEmpleado.setEmail(email);
                        nuevoEmpleado.setFechaIngreso(fechaIngreso);
                    
                        // Asignar el nuevo Legajo
                        nuevoEmpleado.setLegajo(nuevoLegajo);
                        nuevoEmpleado.setEliminado(false);
                    
                        empleadoService.insertar(nuevoEmpleado);
                        
                        System.out.println("\n✅ Nuevo Empleado (" + nombre + " " + apellido + ") insertado correctamente.");

                        } catch (DateTimeParseException e) {
                        System.err.println("❌ ERROR: Formato de fecha incorrecto. Use YYYY-MM-DD.");
                        } catch (IllegalArgumentException e) {
                        System.err.println("❌ ERROR de Validación: " + e.getMessage());
                        } catch (Exception e) {
                        System.err.println("❌ ERROR de Inserción (BD/Service): " + e.getMessage());
                        }
                },
                () -> {
                    try{
                        List<Empleado> empleados = empleadoService.getAll();
                        System.out.println("-------LISTA DE EMPLEADOS-------");
                        for (Empleado emp : empleados) {
                            System.out.println(
                                "ID: " + emp.getId() + " | " +
                                "Nombre: " + emp.getNombre() + " " + emp.getApellido() + " | " +
                                "Área: " + emp.getArea() + " | " +
                                "Legajo: " + (emp.getLegajo() != null ? emp.getLegajo().getNroLegajo() : "sin asignar")
                            );
                        }
                    } catch (Exception e) {
                        System.err.println("❌ Error al obtener empleados: "+ e.getMessage());
                    }
                },
                () ->{
                    try{
                        System.out.println("Ingrese ID del empleado que desea buscar: ");
                        long id =scanner.nextLong();
                        
                        Empleado empleado = empleadoService.getById(id);
                        
                        if (empleado != null){
                            System.out.println("Empleado encontrado: \n" + empleado.toString());
                        } else {
                            System.out.println("No existe empleado con ese ID.");
                        }
                    } catch( Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                      }
                    },
                () ->{
                    try{
                        System.out.println("Ingrese el ID del empleado que desea actualizad: ");
                        long id = scanner.nextLong();
                        scanner.nextLine(); // esto es para limpiar el buffer
                        
                        Empleado empleado = empleadoService.getById(id);
                        if (empleado == null){
                            System.out.println("No existe empleado con ese ID.");
                            return;
                        }
                        
                        System.out.println("Datos actuales: " + empleado);
                        
                        System.out.println("Ingrese nuevo DNI (DNI actual: "+ empleado.getDni() +"):");
                        String dni = scanner.nextLine();
                        
                        System.out.println("Ingrese nuevo nombre (nombre actual: " +empleado.getNombre()+ "): ");
                        String nombre= scanner.nextLine();
                        
                        System.out.println("Ingrese nuevo apellido (apellido actual: "+empleado.getApellido()+ "): ");
                        String apellido= scanner.nextLine();
                        
                        System.out.println("Nuevo email (actual: "+empleado.getEmail()+"): ");
                        String email = scanner.nextLine();
                        
                        System.out.println("Nueva Area: (actual: "+empleado.getArea()+"): ");
                        String area = scanner.nextLine();
                    
                        empleado.setDni(dni);
                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setEmail(email);
                        empleado.setArea(area);
                        
                        empleadoService.actualizar(empleado);
                        System.out.println("✔ Empleado actualizado");
                    } catch (Exception e){
                        System.out.println("❌ Error al actualizar: "+e.getMessage());
                    }    
                },
               () -> {
                   try {
                       System.out.println("Ingrese el ID del empleado que desea eliminar: ");
                       Long id =scanner.nextLong();
                       
                       empleadoService.eliminar(id);
                       
                   } catch (Exception e){
                       System.err.println("❌ Error al eliminar empleado:");
                   }
               }       
            };
        
            AppMenu.menuDesplegado(opciones, acciones);
    }
    
    
}   

