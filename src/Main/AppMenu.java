/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Main;

import java.util.Scanner;

/**
 *
 * @author Gustavo Silva
 */
public class AppMenu {
    
        public static void menuDesplegado(String[] indicacion, Runnable[]acciones){
        Scanner scan= new Scanner (System.in);
        int opcion;
        int salir= indicacion.length +1;
        
            do {
            
            System.out.println("\n===== MENÚ PRINCIPAL =====\n");
            for (int i=0; i < indicacion.length; i++){
                System.out.println((i+1) +". " + indicacion[i] + "\n");
               
            }
            System.out.println(salir+". Salir");                      
            System.out.println("\nSeleccione una opción");
            opcion = scan.nextInt();
            
            if (opcion>=1 && opcion<= indicacion.length){
                acciones [opcion - 1].run();
            }    
            else if (opcion == salir){
                        System.out.println("\nGracias por su visita");
                }
            else {
                        System.out.println("\nOpción invalida intente nuevamente");
                }
                
        }while (opcion !=salir); 
    
        }
}


