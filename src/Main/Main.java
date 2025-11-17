/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;



import Main.AppMenu;

/** 
 *
 * @author giuli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppMenu app = new AppMenu();
        
            String[] opciones={"Ingresar Empleado","Listar empleados","Buscar empleado por ID","Actualizar Empleado","Listar legajos", "Buscar legajos por ID","Actualizar informacion de legajos", "Eliminar",};
            Runnable[] acciones = {
            
                () -> { app.IngresarEmpleado();
                },
                () -> {app.listarEmpleado();
                },    
                () ->{app.buscarEmpleadoId();
                },
                () ->{app.actualizaEmpleado();
                },
               ()->{app.listarLegajos();
                },
               ()->{app.buscarLejagoId();
               },
               () -> {app.actualizarLegajos();
               },
               () -> {app.eliminarDatos();
            },
        };       
        
            AppMenu.menuDesplegado(opciones, acciones);
    }
}   