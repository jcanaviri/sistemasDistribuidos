package sockettcp_invertir;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args){
        int port = 5001; // puerto de comunicacion
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.print("Introduzca una cadena : ");
            String cadena = br.readLine();
            
            Socket client = new Socket("localhost", port); //conectarse al socket
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            toServer.println(cadena);  //mandar alservidor
            
            String result = fromServer.readLine();  // respuesta del servidor
            System.out.println("Cadena recibida: " + result);

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
