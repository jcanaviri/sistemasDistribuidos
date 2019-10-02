
package battleship;

import java.rmi.*;

public interface Interfaz extends Remote{
    public void iniciarPartida() throws RemoteException;
    public void ponerFichas(int jugador, int x, int y) throws RemoteException;
    public boolean disparar(int jugador, int x, int y) throws RemoteException;
}
