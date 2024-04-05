import java.rmi.*;

public interface IGateway extends Remote {
	public void pesquisa(String s) throws java.rmi.RemoteException;
    public void subscribe(ICliente client) throws RemoteException;
}

