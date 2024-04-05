import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class URLQueue extends UnicastRemoteObject implements IQueue{

    private Queue<String> urlQueue;
    private HashSet<String> uniqueUrls;
    private ArrayList<DownloaderWrapper> downloaderList;
    private int downloaderCount;

    /**
     * Constructs a URLQueue object.
     * @throws RemoteException if RMI remote exception occurs
     */
    public URLQueue() throws RemoteException {
        super();
        this.urlQueue = new LinkedList<>();
        this.uniqueUrls = new HashSet<>();
        this.downloaderList = new ArrayList<>();
        this.downloaderCount = 0;
    }

    /**
     * Adds a URL to the end of the queue.
     * Randomly triggers a downloader to download the information of the URL at the head of the queue.
     * @param url the URL to add to the queue
     * @throws RemoteException if RMI remote exception occurs
     */
    public void addUrlToQueue(String url) throws RemoteException {
        if (!uniqueUrls.contains(url)) {
            this.uniqueUrls.add(url);
            this.urlQueue.add(url);
            if (!downloaderList.isEmpty()) {
                Random random = new Random();
                int index = random.nextInt(downloaderCount);
                downloaderList.get(index).getDownloader().downloadWebPage(this.getCurrentUrl());
            }
        }
    }

    /**
     * Retrieves and removes the URL at the head of the queue.
     * @return the URL at the head of the queue
     * @throws RemoteException if RMI remote exception occurs
     */
    public String getCurrentUrl() throws RemoteException {
        return this.urlQueue.remove();
    }

    /**
     * Subscribes a downloader and adds it to the list of downloaders.
     * @param downloader the downloader to subscribe
     * @return the index of the subscribed downloader
     * @throws RemoteException if RMI remote exception occurs
     */
    public int subscribeDownloader(IDownloader downloader) throws RemoteException {
        this.downloaderList.add(new DownloaderWrapper(downloader));
        System.out.println("[URLQueue] Downloader " + downloaderCount + " subscribed !");
        this.downloaderCount++;
        return this.downloaderCount - 1;
    }

    /**
     * Checks the heartbeat of the downloader at the specified index.
     * @param index the index of the downloader to check
     * @throws RemoteException if RMI remote exception occurs
     */
    public void checkDownloaderHeartbeat(int index) throws RemoteException {
        try {
            this.downloaderList.get(index).setState(this.downloaderList.get(index).getDownloader().Heartbeat());
            //System.out.println("Downloader " + i + " is ok");
        } catch (RemoteException e) {
            this.downloaderList.get(index).setState(false);
        }
    }

    /**
     * Retrieves the number of downloaders in the list.
     * @return the number of downloaders
     * @throws RemoteException if RMI remote exception occurs
     */
    public int getDownloaderListSize() throws RemoteException {
        return this.downloaderList.size();
    }

    /**
     * Retrieves the number of active downloaders in the list.
     * @return the number of active downloaders
     * @throws RemoteException if RMI remote exception occurs
     */
    public int getNumberActiveDownloaders() throws RemoteException {
        int activeCount = 0;
        for (DownloaderWrapper downloader : this.downloaderList) {
            if (downloader.getState()) {
                activeCount++;
            }
        }
        return activeCount;
    }

    /**
     * Updates and retrieves the set of active downloader indices.
     * @return the set of indices of active downloaders
     * @throws RemoteException if RMI remote exception occurs
     */
    public HashSet<Integer> updateActiveDownloaders() throws RemoteException {
        HashSet<Integer> activeIndices = new HashSet<>();
        for (int i = 0; i < this.downloaderList.size(); i++) {
            if (downloaderList.get(i).getState()) {
                activeIndices.add(i);
            }
        }
        return activeIndices;
    }

    /**
     * Main method to start the URLQueue RMI server.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("[URLQueue] Turning on... ");
            IQueue urlQueue = new URLQueue();
            Registry registry = LocateRegistry.createRegistry(9871);
            registry.rebind("URLQueue", (Remote) urlQueue);
            System.out.println("[URLQueue] RMI Server ready !");

            while (true) {
                for (int i = 0; i < urlQueue.getDownloaderListSize(); i++) {
                    urlQueue.checkDownloaderHeartbeat(i);
                }

                try {
                    Thread.sleep((long) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (RemoteException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Downloader secondary class to track if the Downloader is active.
 */
class DownloaderWrapper {

    private IDownloader downloader;
    private boolean isActive;

    public DownloaderWrapper(IDownloader downloader) {
        this.downloader = downloader;
        this.isActive = true;
    }

    public IDownloader getDownloader() {
        return this.downloader;
    }

    public boolean getState() {
        return this.isActive;
    }

    public void setState(boolean isActive) {
        this.isActive = isActive;
    }
}
