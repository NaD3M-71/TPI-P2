/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Models.Empleado;
import Models.Legajo;
import Service.EmpleadoService;
import Service.LegajoService;
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
        LegajoService legajoService =new LegajoService();
        try {
            //EmpleadoService empleadoService = new EmpleadoService();

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
    
            String[] opciones={"Ingresar Empleado","Listar empleados","Buscar empleado por ID","Actualizar Empleado","Listar legajos", "Buscar legajos por ID","Actualizar informacion de legajos", "Eliminar",};
            Runnable[] acciones = {
            
                () -> { 
                    try{
                        System.out.println("\n-------INGRESAR EMPLEADO-------\n");
                        
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        
                        // 1. SOLICITAR DATOS DEL EMPLEADO
                        System.out.print("Ingrese DNI: ");
                        String dni = scanner.nextLine();
                    
                        System.out.print("Ingrese Nombre: ");
                        String nombre = scanner.nextLine();
                    
                        System.out.print("Ingrese Apellido: ");
                        String apellido = scanner.nextLine();
                    
                        System.out.print("Ingrese Área: ");
                        String area = scanner.nextLine();
                    
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
                        
                        System.out.println("\n-------LISTA DE EMPLEADOS-------\n");
                        
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        List<Empleado> empleados = empleadoService.getAll();
                        for (Empleado emp: empleados){
                            System.out.println(emp);
                        }
                    } catch (Exception e) {
                        System.err.println("❌ Error al obtener empleados: "+ e.getMessage());
                    }
                },
                () ->{
                    try{
                        System.out.println("\n-------BUSQUEDA DE EMPLEADOS POR ID-------\n");
                        
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        
                        
                        System.out.println("Ingrese ID del empleado que desea buscar: ");
                        long id =scanner.nextLong();
                        
                        Empleado empleado = empleadoService.getById(id);
                        
                        if (empleado != null){
                            System.out.println("Empleado encontrado: " + empleado);
                        } else {
                            System.out.println("No existe empleado con ese ID.");
                        }
                    } catch( Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                      }
                    },
                () ->{
                    try{
                        System.out.println("\n-------ACTUALIZAR DATOS DE EMPLEADOS-------\n");
                        
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        
                                       
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
               ()->{
                   try{
                        
                        System.out.println("\n-------LISTA DE LEGAJOS-------\n");
                        
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        List<Legajo> legajos = legajoService.getAll();
                        for (Legajo legajo: legajos){
                            System.out.println(legajo);
                        }
                    } catch (Exception e) {
                        System.err.println("❌ Error al obtener legajos: "+ e.getMessage());
                    }
                },
               ()->{
                    try{
                        System.out.println("\n-------BUSQUEDA DE LEGAJO POR ID-------\n");
                        
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        
                        System.out.println("Ingrese ID del legajo que desea buscar: ");
                        long id =scanner.nextLong();
                        
                        Legajo legajo = legajoService.getById(id);
                        
                        if (legajo != null){
                            System.out.println("Legajo encontrado: " + legajo);
                        } else {
                            System.out.println("No existe el legajo con ese ID.");
                        }
                    } catch( Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                      }
               },
               () -> {
                     try{  
                        System.out.println("\n-------ACTUALIZAR DATOS DEL LEGAJO-------\n");
                        System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        
                        System.out.println("Ingrese el ID del legajo que desea actualizad: ");
                        long id = scanner.nextLong();
                        scanner.nextLine(); // esto es para limpiar el buffer
                        
                        Legajo legajo = legajoService.getById(id);
                        if (legajo == null){
                            System.out.println("No existe legajo con ese ID.");
                            return;
                        }
                        
                        System.out.println("Datos actuales: " + legajo);
                        
                        System.out.println("Ingrese nuevo Nro de legajo (Legajo actual: "+ legajo.getNroLegajo() +"):");
                        String Nlegajo = scanner.nextLine();
                        
                        System.out.println("Ingrese nueva categoria (categoria actual: " +legajo.getCategoria()+ "): ");
                        String categoria= scanner.nextLine();
                        
                        System.out.println("Ingrese nuevo estado (estado actual: "+legajo.getEstado()+ "): ");
                        Estado estado= Estado.valueOf(scanner.nextLine().toUpperCase());
                        
                        System.out.println("Modificar la fecha de alta? (fecha de alta original: "+legajo.getFechaAlta()+"): ");
                        String fechaStr = scanner.nextLine();
                        LocalDate fecha=LocalDate.parse(fechaStr);
                        
                        System.out.println("Modificar observaciones: (actuales: "+legajo.getObservaciones()+"): ");
                        String observaciones = scanner.nextLine();
                    
                        legajo.setNroLegajo(Nlegajo);
                        legajo.setCategoria(categoria);
                        legajo.setEstado(estado);
                        legajo.setFechaAlta(fecha);
                        legajo.setObservaciones(observaciones);
                                                
                        legajoService.actualizar(legajo);
                        System.out.println("✔ Legajo actualizado");
                    } catch (Exception e){
                        System.out.println("❌ Error al actualizar: "+e.getMessage());
                    }
                   
               },
                     
               () -> {
                   try {
                       System.out.println("\n-------ELIMINAR EMPLEADO Y LEGAJO-------\n");
                       
                       System.out.println("Presione 'S' para continuar o cualquier otra tecla para volver al menú.");
                        
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmacion.equals("S")) {
                        System.out.println("↩️ Operación cancelada. Volviendo al menú.");
                        return; // Sale del método run
                        }
                        
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

