import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
    /**
     * The title of the webpage
     */
    public String title;

    /**
     * A quote from the webpage
     */
    public String quote;

    /**
     * The link of the webpage
     */
    public String link;

    /**
     * The list of words from the webpage
     */
    public List<String> words;

    /**
     * The list of links from the webpage
     */
    public List<String> links;

    /**
     * Instantiates the object with the given data
     *
     * @param title The title of the webpage
     * @param quote A quote from the webpage
     * @param link  The link of the webpage
     * @param words The list of words from the webpage
     * @param links The list of links from the webpage
     */
    public Page(String title, String quote, String link, List<String> words, List<String> links) {
        this.title = title;
        this.quote = quote;
        this.link = link;
        this.words = words;
        this.links = links;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", quote='" + quote + '\'' +
                ", link='" + link + '\'' +
                ", words=" + words +
                ", links=" + links +
                '}';
    }

    public static Page deserialize(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }

    // You can add serialization and deserialization methods here if needed
}
