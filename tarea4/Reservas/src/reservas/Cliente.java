
package reservas;

import java.rmi.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Interfaz reserva;
        Interfaz cotizar;
        Scanner in = new Scanner(System.in);
        
        try{
            reserva = (Interfaz)Naming.lookup("rmi://localhost/reserva");
            cotizar = (Interfaz)Naming.lookup("rmi://localhost/cotizar");
            
            System.out.println("\tMenu");
            System.out.println("1.- Reservar");
            System.out.println("2.- Cotizar");
            int opcion = in.nextInt();
            
            if(opcion == 1){
                System.out.print("Introduzca la fecha de inicio: ");
                String inicio = in.nextLine();
                System.out.print("Introduzca la fecha fin: ");
                String fin = in.nextLine();
                System.out.print("Introduzca el id_cliente: ");
                String id = in.nextLine();
                System.out.print("Introduzca la fecha de compra: ");
                String fecha = in.nextLine();
                
                reserva.Reservar(inicio, fin, id, fecha);
            } else if(opcion == 2){
                System.out.print("Introduzca la fecha de inicio: ");
                String inicio = in.nextLine();
                System.out.print("Introduzca la fecha fin: ");
                String fin = in.nextLine();
                System.out.print("Introduzca la fecha de compra: ");
                String fecha = in.nextLine();
                double monto = cotizar.Cotizar(inicio, fin, fecha);
                
                System.out.println("El monto de cotizacion es: " + monto);
                
            } else {
                System.out.println("Opcion incorrecta!!!");
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
