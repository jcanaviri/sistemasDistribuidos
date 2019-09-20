
package reservas;

import java.io.*;
import java.net.*;

public class BancoCliente {
    public static void main(String[] args){
        int puerto = 5001;
        String[] id_cliente = {"1", "2", "3"};
        String[] saldo = {"300", "400", "1000"};
        
        try {
            ServerSocket server = new ServerSocket(puerto);
            Socket client;
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura

            while (true) {   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion 
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector

                String cadena = fromClient.readLine();//cadena obtenida desde el lector  

                String[] arreglo = cadena.split(", "); // Arreglo[id_cliente, monto]
                
                String respuesta = null;
                
                for(int i = 0; i < id_cliente.length; i++) {
                    if(arreglo[0] == id_cliente[i] && arreglo[1] == saldo[i])
                        respuesta = "1";
                    else
                        respuesta = "0";
                } 

                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                toClient.flush(); // 
                toClient.println(respuesta);
                server.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
