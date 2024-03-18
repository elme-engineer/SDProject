import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Downloader {

    public static void downloadPage(String url, String filename) {
        try {
            Document document = Jsoup.connect(url).get();
            String htmlContent = document.outerHtml();

            // Write HTML content to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(htmlContent);
            writer.close();

            System.out.println("Page downloaded successfully to: " + filename);
        } catch (IOException e) {
            System.err.println("Error while downloading page: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String url = "https://uc.pt"; // Specify the URL to download
        String filename = "downloaded_page.html"; // Specify the filename to save the downloaded page
        downloadPage(url, filename);
    }
}
