import java.rmi.Remote;

public interface ICliente extends Remote {
    public void atualizaAdminPage(String s) throws java.rmi.RemoteException;
}
