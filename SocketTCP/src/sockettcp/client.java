package sockettcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args){
        int port = 5001; // puerto de comunicacion
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            //System.out.print("Introduzca una cadena : ");
            //String cadena = br.readLine();
            
            String vector[]  = new String[5];
            Scanner entrada = new Scanner(System.in);
            
            for(int i = 0; i < 5; i++) {
                System.out.print("Introduzca vec["+ i +"] = ");
                vector[i] = entrada.nextLine();
            }
            
            String envio = String.join(",", vector);
            
            Socket client = new Socket("localhost", port); //conectarse al socket
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            
            toServer.println(envio);  //mandar alservidor
            
            String result = fromServer.readLine();  // respuesta del servidor
            String resp[] = result.split(","); 
                      
            System.out.println("El vector devuelto es:");
            for(int i = 0; i < 5; i++) {
                System.out.println("Vector["+i+"] = " + resp[i]);
            }

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
