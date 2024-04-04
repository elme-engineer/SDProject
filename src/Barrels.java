import java.util.HashMap;
import java.util.HashSet;

public class Barrels {
    private HashMap<String, HashSet<String>> wordIndex;
    private HashMap<String, HashSet<String>> linkIndex;

    /**
     * Constructor for a Barrel datastructure. 
     */
    public Barrels() {
        wordIndex = new HashMap<>();
        linkIndex = new HashMap<>();
    }

    /**
     * Indexes the downloaded contents of a webpage in the Barrel.
     * 
     * @param data Contains the contents of a webpage including link, title, quote, words, and links.
     */
    public void write(DataPackage data) {
        for (String word : data.getWords()) {

            if (this.allowsWord(word)) {
                HashSet<String> aux = new HashSet<>();

                word = word.toLowerCase();
                // Checks if the word already exists
                if (wordIndex.containsKey(word)) { 
                    aux = wordIndex.get(word);
                }

                aux.add(data.getUrl());
                wordIndex.put(word, aux);
            }

        }

        for (String link : data.getLinks()) {
            HashSet<String> aux = new HashSet<>();
            // Checks if the link already exists
            if (linkIndex.containsKey(link)) { 
                aux = linkIndex.get(link);
            }
            aux.add(data.getUrl());
            linkIndex.put(link, aux);
        }

        Page p = pageIndex.get(data.getUrl());
        if (p == null) {
            p = new Page(data.getQuote(), data.getTitle(), data.getUrl());
            pageIndex.put(data.getUrl(), p);
        }
    };
}
