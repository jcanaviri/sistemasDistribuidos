
package sockettcp;
import java.io.*;
import java.net.*;

public class server {
    
    public static void main(String[] args) throws InterruptedException{
        int port =5001; // puerto en el que escuchara el socket
        
        try {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;      
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            
            while(true){   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion 
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                
                // Devolver al cliente el vector multiplicaco por 5
                //String vector[] = new String[100];
                
                String vector = fromClient.readLine();//cadena obtenida desde el lector  
                String resultado[] = vector.split(",");
                int resp[] = new int[5];
                
                for(int i = 0; i < 5; i++) {
                    resp[i] = Integer.parseInt(resultado[i].trim()) + 5; //trim le quita espacios de adelante y detras
                }
                
                String[] cadena = new String[5];
                for(int i = 0; i < 5; i++){
                    cadena[i] = String.valueOf(resp[i]);
                }
                
                String respuesta;
                respuesta = String.join(",", cadena);
                
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                //System.out.println(cadena+client.getInetAddress());
                //imprime cadena recibida desde el cliente
                //Thread.sleep(3000);
                 //for (int i=0;i<=10000000;i++);
                toClient.flush(); // 
                toClient.println(respuesta);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String toString(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
