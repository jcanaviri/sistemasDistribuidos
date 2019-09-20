
package reservas;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServidorReservas
        extends UnicastRemoteObject
        implements Interfaz {

    ServidorReservas() throws java.rmi.RemoteException {
        super();
    }
    
    public void Reservar(String inicio, String fin,
            String id_cliente, String fecha_compra){
        int port = 5001;
        Scanner in = new Scanner(System.in);
        try{
            Socket client = new Socket("localhost", port); //conectarse al socket
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    
            System.out.print("Introduzca el id_cliente, monto: ");
            String envio = in.nextLine();
                    
            toServer.println(envio);  //Id_cliente, monto
                    
            String result = fromServer.readLine();  // respuesta del servidor
            System.out.println("Respuesta del servidor cliente: " + result);
        
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        
    }

    @Override
    public double Cotizar(String inicio, String fin, String fecha) throws RemoteException {
        String[] inicio_ = inicio.split("-");
        String[] fin_ = fin.split("-");

        int[] valores = {30, 25, 25, 35, 40};

        Integer in = Integer.valueOf(inicio_[0]);
        Integer fi = Integer.valueOf(fin_[0]);

        double acumulador = 0;

        for (int i = in; i <= fi; i++) {
            acumulador += valores[i];
        }
        
        double precio = acumulador * Double.valueOf(cotizarDolar(fecha));
        
        return precio;
    }

    @Override
    public String cotizarDolar(String fecha) {
        String dolar_cotizado = null;
        Interfaz cotizarDolar;
        
        try{
            cotizarDolar = (Interfaz)Naming.lookup("rmi://localhost/cotizar");
            dolar_cotizado = cotizarDolar.cotizarDolar(fecha);
        }catch(Exception e){
            System.out.println(e);
        }
        
        return dolar_cotizado;
    }

}
