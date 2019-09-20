
package reservas;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class BancoCentral
        extends UnicastRemoteObject
        implements Interfaz {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    BancoCentral() throws java.rmi.RemoteException {
        super();
    }

    public String cotizarDolar(String fecha){
        String[] date = fecha.split("-");

        String respuesta;

        switch (date[0]) {
            case "26":
                respuesta = "6.90";
                break;
            case "27":
                respuesta = "6.91";
                break;
            case "28":
                respuesta = "6.93";
                break;
            case "29":
                respuesta = "6.92";
                break;
            case "30":
                respuesta = "6.96";
                break;
            default:
                respuesta = "0";
                break;
            }
        return respuesta;
    } 

    public static void main(String args[]) {
        try {
            BancoCentral cotizacion;
            LocateRegistry.createRegistry(1099);
            cotizacion = new BancoCentral();
            Naming.bind("cotizacion", cotizacion);
            System.out.println("El servidor esta listo\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double Cotizar(String inicio, String fin, String fecha) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Reservar(String inicio, String fin, String id_cliente, String fecha_compra) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
