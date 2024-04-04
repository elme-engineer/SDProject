import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface IBarrels {
    /**
     * Searches for the received words within the Barrel and returns a map where each queried word is the key
     * and the set of URLs containing it is the value.
     * 
     * @param words The words to search for in this Barrel.
     * @return A map containing the key-value pairs {Word, Pages that contain the word}.
     * @throws RemoteException If the Barrel isn't active.
     */
    public HashMap<String, HashSet<String>> readBarrel(List<String> words) throws RemoteException;

    /**
     * Calculates the count of pages linking to each URL in the provided list. This count serves as a measure
     * of importance for sorting purposes.
     * 
     * @param links A list of URLs to be ranked by importance.
     * @return An HashSet containing the URL as the key and the count of pages linking to it as the value.
     * @throws RemoteException When the RMI connection fails.
     */
    public HashMap<String, Integer> importanceCalculator(List <String> links) throws RemoteException;

    /**
     * Retrieves the set of pages that have links to the specified URL.
     * 
     * @param link The URL of the page to search for.
     * @return The set of pages that have links to the URL.
     * @throws RemoteException If the Barrel isn't running.
     */
    public HashSet<String> searchLinks(String link) throws RemoteException;

    /**
     * Checks if the Barrel program is still running.
     * 
     * @return True if the Barrel is running; false otherwise.
     * @throws RemoteException If the Barrel isn't running.
     */
    public boolean isActive() throws RemoteException;

    /**
     * Creates a list containing the title, a quote, and the URL of the requested links.
     * 
     * @param links URLs to fetch the data for.
     * @return A list of Page objects that store the title, a quote, and URL of the webpage.
     * @throws RemoteException If the Barrel isn't running.
     */
    public List<Page> readLinks(List<String> links) throws RemoteException;
}

