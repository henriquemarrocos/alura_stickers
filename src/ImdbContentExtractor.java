import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor{
    
    public List<Content> extractsContent(String json) {

        // Sending the API result to the parser
        var parser = new JsonParser();
        List<Map<String, String>> atributesList = parser.parse(json);

        List<Content> contentsList = new ArrayList<>();

        for (Map<String, String> atributes : atributesList) {
            String title = atributes.get("title");
            String imageUrl = atributes.get("image")
                .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            var content = new Content(title, imageUrl);

            contentsList.add(content);
        }

        return contentsList;
    }
}
