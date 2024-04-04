import java.rmi.*;

public interface IGatewayCliente extends Remote{
    StringBuilder sendURLIndex(StringBuilder url) throws RemoteException;
    StringBuilder searchInput(StringBuilder input) throws RemoteException;

}
