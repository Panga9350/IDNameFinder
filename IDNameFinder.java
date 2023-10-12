import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IDNameFinder {
    public static void main(String[] args) {
        BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter an email ID");
        String emailID;
        try {
            emailID = inpReader.readLine();
            String webAddress = "https://www.southampton.ac.uk/people/" + emailID;
            URL url = new URL(webAddress);
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean found = false;
            while (!found) {
                String currentLine;
                try {
                    currentLine = urlReader.readLine();
                    if (currentLine.contains("og:title")) {
                        found = true;
                        int startIndex = currentLine.lastIndexOf("=") + 2;
                        int endIndex = currentLine.indexOf("/>", startIndex) - 2;
                        String name = currentLine.substring(startIndex, endIndex);
                        System.out.println(name);
                    }
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

