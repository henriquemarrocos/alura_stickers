// https://www.alura.com.br/imersao-java/aula01-consumindo-api-com-java

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Connecting to the API via http and extracting the movie list
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // Sending the API result to the parser
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);
        
        // Choosing which fields to extract and showing the extracted data
        /*for (Map<String,String> movie : movieList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
        }*/
        
        for (int i = 0; i < 5; i++) {

            Map<String, String> movie;
            movie = movieList.get(i);
            String imageUrl = movie.get("image");
            String title = movie.get("title");
            
            InputStream inputStream = new URL(imageUrl).openStream();
            String fileName = title + ".png";

            var builder = new StickerBuilder();
            builder.build(inputStream, fileName);
            //Old link: "https://bl.cwa.sellercloud.com/images/products/4529884.jpg"
            
            System.out.println(title);
            System.out.println();
        }
    }
}
