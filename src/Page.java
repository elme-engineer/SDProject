import java.io.Serializable;

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
     * Instantiates the object withe the given data
     * 
     * @param title The title of the webpage
     * @param quote A quote from the webpage
     * @param link  The link of the webpage
     */
    public Page(String quote, String title, String link) {
        this.title = title;
        this.quote = quote;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Page " + title + "\nquote=" + quote + "\n" + link;
    }
}
