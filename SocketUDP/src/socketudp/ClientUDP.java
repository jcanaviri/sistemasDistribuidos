
package socketudp;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientUDP {

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {

    try {
        
        // Creando un arreglo de tamanio n
        Scanner entrada = new Scanner(System.in);
        
        byte[] arreglo = new byte[5];
        
        for(int i = 0; i < 5; i++){
            System.out.print("arreglo[" + i + "] = ");
            arreglo[i] = entrada.nextByte();
        }
        
        String ip="localhost";
        DatagramSocket socketUDP = new DatagramSocket(); 
        
        InetAddress hostServidor = InetAddress.getByName(ip);
        int puertoServidor = 6789;

        // Construimos un datagrama para enviar el mensaje al servidor
        DatagramPacket peticion =
        new DatagramPacket(arreglo, 5, hostServidor,
                           puertoServidor);

        // Enviamos el datagrama
        socketUDP.send(peticion);

        // Construimos el DatagramPacket que contendrá la respuesta
        byte[] bufer = new byte[1000];
        DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
        socketUDP.receive(respuesta);

        byte[] resp = respuesta.getData();
        
        for(int i = 0; i < 5; i++) {
            System.out.println("arreglo[" + "] = " + resp[i]);
        }
        
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
