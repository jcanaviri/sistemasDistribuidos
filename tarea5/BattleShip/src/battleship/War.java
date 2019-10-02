
package battleship;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class War extends UnicastRemoteObject
        implements Interfaz{
    
    War() throws java.rmi.RemoteException {
        super();
    }
    
    private int[][] table1 = new int[10][10];
    private int[][] table2 = new int[10][10];
    
    @Override
    public void iniciarPartida(){
        
        for(int i = 0; i < 10; i++){ // Tablero 1 a 0
            for(int j = 0; j < 10; j++)
                table1[i][j] = 0;
        }
        
        for (int i = 0; i < 10; i++) { // Tablero 2 a 0
            for (int j = 0; j < 10; j++) {
                table2[i][j] = 0;
            }
        }
        
    }
    
    @Override
    public void ponerFichas(int jugador, int x, int y){
        
        if(jugador == 1){
            
            if(table1[x][y] == 0)
                table1[x][y] = 1;
            
        } else if(jugador == 2) {
            
            if (table2[x][y] == 0) 
                table2[x][y] = 1;
        }
    }
    
    @Override
    public boolean disparar(int jugador, int x, int y){
        
        boolean respuesta = false;
        // 0 => vacio  1 => lleno 2 => atacado
        
        if(jugador == 1){
            if(table2[x][y] == 1){
                table2[x][y] = 2;
                respuesta = true;
            }
        } else if(jugador == 2){
            if (table1[x][y] == 1) {
                table1[x][y] = 2;
                respuesta = true;
            }
        }
        
        return respuesta;
    }
 
    public static void main(String args[]) {
        try {
            War iniciar;
            War poner;
            War atacar;
            
            LocateRegistry.createRegistry(1099);
            
            iniciar = new War();
            poner = new War();
            atacar = new War();
            
            Naming.bind("iniciar", iniciar);
            Naming.bind("poner", poner);
            Naming.bind("atacar", atacar);
            
            System.out.println("El servidor esta listo\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
