// https://www.alura.com.br/imersao-java/aula01-consumindo-api-com-java

import java.util.List;
import java.io.InputStream;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Connects to the API via http and extracts the content list
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ContentExtractor extractor = new ImdbContentExtractor();
        
        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //ContentExtractor extractor = new NasaContentExtractor();

        String url = "https://picsum.photos/v2/list";
        ContentExtractor extractor = new PicsumContentExtractor();

        var http = new ImportHttpClient();
        String json = http.importContent(url);
        
        List<Content> contentList = extractor.extractsContent(json);

        var builder = new StickerBuilder();

        for (int i = 0; i < 3; i++) {

            Content content = contentList.get(i);

            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            String fileName = content.getTitle() + ".png";

            builder.build(inputStream, fileName);

            System.out.println(content.getTitle());
        }  
    }
}
