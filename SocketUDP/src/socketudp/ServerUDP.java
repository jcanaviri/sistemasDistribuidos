
package socketudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {

  public static void main (String args[]) {

    try {

      DatagramSocket socketUDP = new DatagramSocket(6789);
      byte[] bufer = new byte[1000];

      while (true) {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion =
          new DatagramPacket(bufer, bufer.length);

        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);

        System.out.print("Datagrama recibido del host: " +
                           peticion.getAddress());
        System.out.println(" desde el puerto remoto: " +
                           peticion.getPort());

        byte envio[] = peticion.getData();
        
        for(int i = 0; i < 5; i++) {
            envio[i] += 5;
        }
        
        // Construimos el DatagramPacket para enviar la respuesta
        DatagramPacket respuesta = new DatagramPacket(envio, 
                5, peticion.getAddress(), peticion.getPort());

        // Enviamos la respuesta, que es un eco
        socketUDP.send(respuesta);
      }

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }

}
