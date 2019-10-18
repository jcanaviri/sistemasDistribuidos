
package sisbanco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICessa extends Remote {
    public Factura[] pedientes(int idcliente) throws RemoteException;

    public String pagar(Factura[] facturas) throws RemoteException;
}
