
package reservas;

import java.rmi.*;

public interface Interfaz extends Remote {

    double Cotizar(String inicio, String fin, 
            String fecha) throws RemoteException;
    void Reservar(String inicio, String fin, 
            String id_cliente, String fecha_compra) throws RemoteException;
    public String cotizarDolar(String fecha);
}
