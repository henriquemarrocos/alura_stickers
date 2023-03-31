// https://www.alura.com.br/imersao-java/aula01-consumindo-api-com-java

public class App {
    public static void main(String[] args) throws Exception {
        
        // Connects to the API via http and extracts the content list
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        var http = new ImportHttpClient();
        String json = http.importContent(url);
        
        var nasa = new NasaContentExtractor();
        nasa.extractsContent(json);
    }
}
