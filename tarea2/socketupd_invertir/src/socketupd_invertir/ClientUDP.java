
package socketupd_invertir;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientUDP {

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) throws UnsupportedEncodingException{

    try {
        
        String ip="localhost";
        DatagramSocket socketUDP = new DatagramSocket(); 
        
        InetAddress hostServidor = InetAddress.getByName(ip);
        int puertoServidor = 6789;

        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca una cadena: ");
        String cadena = entrada.nextLine();
        
        byte[] enviar = cadena.getBytes();
        
        // Construimos un datagrama para enviar el mensaje al servidor
        DatagramPacket peticion =
        new DatagramPacket(enviar, cadena.length(), hostServidor,
                           puertoServidor);

        // Enviamos el datagrama
        socketUDP.send(peticion);

        // Construimos el DatagramPacket que contendrá la respuesta
        byte[] bufer = new byte[10000];
        DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
        socketUDP.receive(respuesta);

        byte[] nums = respuesta.getData();
        
        String resp;
        resp = new String(nums, "UTF8");
        
        System.out.println("La cadena recibida es: " + resp);
        
        // Enviamos la respuesta del servidor a la salida estandar
        //System.out.println("Respuesta: " + new String(respuesta.getData()));

        // Cerramos el socket
        socketUDP.close();
        
    } catch (SocketException e) {
        System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}
