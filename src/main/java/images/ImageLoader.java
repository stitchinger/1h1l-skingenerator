package main.java.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static BufferedImage loadImage(String imagePath) {
        try {
            URL imageUrl = ImageLoader.class.getResource(imagePath);
            if (imageUrl != null) {
                return ImageIO.read(imageUrl);
            } else {
                System.out.println("Image URL is null in ImageLoader: loadImage");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
