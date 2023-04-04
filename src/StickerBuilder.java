import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerBuilder {
    
    public void build(InputStream inputStream, String fileName) throws IOException {
                
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Creates a new image based on the height and width of the original image
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copies the original image to a new image adding extra pixels
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // Checks if fileName has an extension and removes it
        var title = "";
        if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
            title = fileName.substring(0, fileName.length() - 4);
        } else {
            title = fileName;
        }

        // Writes text on the image
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 48);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);
        graphics.drawString(title, 50, newHeight - 100);

        // Saves the new image to a file
        ImageIO.write(newImage, "png", new File("output/" + fileName));
    }
}