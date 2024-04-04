import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class IGatewayBarrels implements IBarrels{
     private static Barrels barrel = new Barrels();

    /**
     * Default RMI constructor
     * 
     * @throws RemoteException when the RMI connection fails
     */
    public IGatewayBarrels() throws RemoteException {
        super();
        
    }

    /**
     * Provides the RMI instance with access to the Barrel datastructure
     * 
     * @param barrel The data stored in memory
     * @throws RemoteException when the RMI connection fails
     */
    public IGatewayBarrels(Barrels barrel) throws RemoteException{
        this();
        this.barrel = barrel;
    }

    @Override
    public boolean isActive() throws RemoteException {
        return true;
    }

    @Override
    public HashMap<String, HashSet<String>> readBarrel(List<String> words) throws RemoteException{
        return barrel.readWords(words);
    }

    @Override
    public HashMap<String, Integer>  importanceCalculator(List <String> links) throws RemoteException{
        return barrel.linkImportance(links); 
    }

    @Override
    public HashSet<String> searchLinks(String url) throws RemoteException {
        return barrel.searchLinks(url);
    }

    @Override
    public List<Page> readLinks(List<String> links) throws RemoteException {
        return barrel.readLinks(links);
    }
    
}

