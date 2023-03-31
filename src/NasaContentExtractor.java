import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.net.URL;

public class NasaContentExtractor {
    
    //public List<Content> extractsContent (String json) {
    public void extractsContent (String json) {
        
        // Sending the API result to the parser
        JsonParser parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(json);
        
        for (int i = 0; i < 3; i++) {

            Map<String, String> content;
            content = contentList.get(i);
            String imageUrl = content.get("url");
            String title = content.get("title");
            
            InputStream inputStream = new URL(imageUrl).openStream();
            String fileName = title + ".png";

            var builder = new StickerBuilder();
            builder.build(inputStream, fileName);
            
            System.out.println(title);
            System.out.println();

        }
    }
}
