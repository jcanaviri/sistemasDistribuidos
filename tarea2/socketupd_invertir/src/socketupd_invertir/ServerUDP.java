
package socketupd_invertir;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {

  public static void main (String args[]) throws UnsupportedEncodingException {

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
        
        // Invertimos la cadena
        byte[] nums = peticion.getData();
        String cadena = new String(nums, "UTF8");
        
        StringBuilder builder= new StringBuilder(cadena);
        String cadena_invertida= builder.reverse().toString();
        
        byte[] inversa = cadena_invertida.getBytes();
        
        // Construimos el DatagramPacket para enviar la respuesta
        DatagramPacket respuesta = new DatagramPacket(inversa, 
                cadena.length(), peticion.getAddress(), peticion.getPort());

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
