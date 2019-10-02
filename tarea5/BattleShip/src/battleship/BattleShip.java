
package battleship;

import java.util.Scanner;
import java.rmi.*;

public class BattleShip {

   public static void main(String[] args) {
       
       Interfaz iniciar;
       Interfaz poner;
       Interfaz atacar;
    
       Scanner in = new Scanner(System.in);
       
       int posx = 0, posy = 0; 
       
       try{
                iniciar = (Interfaz)Naming.lookup("rmi://localhost/iniciar");
                poner = (Interfaz)Naming.lookup("rmi://localhost/poner");
                atacar = (Interfaz) Naming.lookup("rmi://localhost/atacar");
       
                System.out.println("\n\tIniciando la partida");
                iniciar.iniciarPartida();

                System.out.println("\n\tPoniendo las fichas para jugador1");
                for(int i = 0; i < 3; i++){
                     System.out.print("\nPosicion x " + i + ": ");
                     posx = in.nextInt();
                     System.out.print("Posicion y " + i + ": ");
                     posy = in.nextInt();

                     poner.ponerFichas(1, posx, posy);
                }

                System.out.println("\n\tPoniendo las fichas del jugador2");
                 for (int i = 0; i < 3; i++) {
                     System.out.print("\nPosicion x " + i + ": ");
                     posx = in.nextInt();
                     System.out.print("Posicion y " + i + ": ");
                     posy = in.nextInt();

                     poner.ponerFichas(2, posx, posy);
                 }

                 int x = 0, y = 0, contador1 = 0, contador2 = 0, ganador = 0;
                 int turno = 1; // True para jugador1 y false para jugador2

                 System.out.println("\n\tIniciando la Batalla");
                 while(true){
                     if(turno == 1){
                         System.out.println("\nJUGADOR1:");
                         System.out.print("Atacando posicion x: ");
                         x = in.nextInt();
                         System.out.print("Atacando posicion y: ");
                         y = in.nextInt();

                         if(atacar.disparar(1, x, y)){
                             System.out.println("Acertaste");
                             turno = 1;
                             contador1++;
                         } else {
                             System.out.println("Fallaste");
                             turno = 2;
                         }

                         if(contador1 == 3){
                             ganador = 1;
                             break;
                         }

                     } else if(turno == 2){
                         System.out.println("\nJUGADOR2:");
                         System.out.print("Atacando posicion x: ");
                         x = in.nextInt();
                         System.out.print("Atacando posicion y: ");
                         y = in.nextInt();

                         if (atacar.disparar(2, x, y)) {
                             System.out.println("Acertaste");
                             turno = 2;
                             contador2++;
                         } else {
                             System.out.println("Fallaste");
                             turno = 1;
                         }

                         if (contador2 == 3) {
                             ganador = 2;
                             break;
                         }
                     }
                 }

                 if(ganador == 1)
                     System.out.println("\tFelicidades!!!\n GANA jugador1");

                 if(ganador == 2)
                     System.out.println("\tFelicidades!!!\n GANA jugador2");
                 
       }catch(Exception e){
           System.out.println(e);
       }
        
   }
    
}
