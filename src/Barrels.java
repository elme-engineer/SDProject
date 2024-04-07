import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Barrels {
    private HashMap<String, HashSet<String>> wordIndex;
    private HashMap<String, HashSet<String>> linkIndex;
    private HashMap<String, Page> page;

    /**
     * Constructor for a Barrel data structure.
     */
    public Barrels() {
        wordIndex = new HashMap<>();
        linkIndex = new HashMap<>();
        page = new HashMap<>();
    }

    /**
     * Indexes the downloaded contents of a webpage in the Barrel.
     *
     * @param data Contains the contents of a webpage including link, title, quote, words, and links.
     */
    public void write(Page data) {
        for (String word : data.words) {
            if (allowsWord(word)) {
                word = word.toLowerCase();
                HashSet<String> aux = wordIndex.getOrDefault(word, new HashSet<>());
                aux.add(data.link);
                wordIndex.put(word, aux);
            }
        }

        for (String link : data.links) {
            HashSet<String> aux = linkIndex.getOrDefault(link, new HashSet<>());
            aux.add(data.link);
            linkIndex.put(link, aux);
        }

        if (!page.containsKey(data.link)) {
            page.put(data.link, data);
        }
    }

    private boolean allowsWord(String word) {
        // Implement word filtering logic here if needed.
        // For now, assuming all words are allowed.
        return true;
    }

    public HashMap<String, HashSet<String>> readWords(List<String> words) {
        // Implement logic to retrieve links associated with given words.
        // For now, returning an empty map.
        return new HashMap<>();
    }

    public HashMap<String, Integer> linkImportance(List<String> links) {
        // Implement logic to calculate link importance based on criteria.
        // For now, returning an empty map.
        return new HashMap<>();
    }

    public HashSet<String> searchLinks(String url) {
        // Implement logic to search for links associated with given URL.
        // For now, returning an empty set.
        return new HashSet<>();
    }

    public List<Page> readLinks(List<String> links) {
        // Implement logic to read pages associated with given links.
        // For now, returning an empty list.
        return new ArrayList<>();
    }
}
