import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;

public interface IQueue extends Remote {
    void addUrlToQueue(String url) throws RemoteException;
    String getCurrentUrl() throws RemoteException;
    int subscribeDownloader(IDownloader downloader) throws RemoteException;
    void checkDownloaderHeartbeat(int index) throws RemoteException;
    int getDownloaderListSize() throws RemoteException;
    HashSet<Integer> updateActiveDownloaders() throws RemoteException;
    int getNumberActiveDownloaders() throws RemoteException;
}


