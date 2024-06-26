import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.StringTokenizer;

public class Downloader {

    private int downloaderID;
    private int packetID;

    public static void main(String args[]) {
        String url = "https://www.uc.pt"; //args[0];

        String wordList = "type | word_list; ";
        String linkList = "type | links_list; item_count | ";

        
        try {
            //fazer o pedido ao URL
            Document doc = Jsoup.connect(url).get();

            //Separar palavras
            StringTokenizer tokens = new StringTokenizer(doc.text());

            int countTokens = 0; //counter, para n imprimir mais de 100

            
            while (tokens.hasMoreElements() && countTokens++ < 100){



            }
                System.out.println(tokens.nextToken().toLowerCase());

            //extrair os links para outras páginas
            Elements links = doc.select("a[href]");

            //percorrer e imprimir (texto + link)
            for (Element link : links)
                System.out.println(link.text() + "\n" + link.attr("abs:href") + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
