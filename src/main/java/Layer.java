package main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Layer {
    private BufferedImage image;
    private float opacity = 1.0f;
    private Color colorFilter = null;
    private Color tint = null;
    private BlendMode blendMode = BlendMode.NORMAL;

    public Layer(String imagePath) {
        loadImageAndScale(imagePath, 640, 640); // Load and scale the image
    }

    public Layer(String imagePath, BlendMode blendMode) {
        loadImageAndScale(imagePath, 640, 640); // Load and scale the image
        this.blendMode = blendMode;
    }

    public Layer(BufferedImage originalImage) {
        image = originalImage;
    }

    private void loadImageAndScale(String imagePath, int targetWidth, int targetHeight) {
        try {
            URL imageUrl = getClass().getResource(imagePath);
            BufferedImage originalImage = ImageIO.read(imageUrl);
            image = scaleImage(originalImage, targetWidth, targetHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return scaledImage;
    }

    public Layer opacity(double value) {
        this.opacity = (float) value;
        return this;
    }

    public Layer color(Color newColor) {
        this.colorFilter = newColor;
        applyColorFilter();
        return this;
    }

    public Layer tint(Color newColor) {
        this.tint = newColor;
        applyTint();
        return this;
    }

    private void applyColorFilter() {
        if (colorFilter != null && image != null) {
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = image.getRGB(x, y);
                    Color originalColor = new Color(argb, true);
                    int alpha = originalColor.getAlpha();

                    if (alpha > 0) {
                        int red = (originalColor.getRed() * colorFilter.getRed()) / 255;
                        int green = (originalColor.getGreen() * colorFilter.getGreen()) / 255;
                        int blue = (originalColor.getBlue() * colorFilter.getBlue()) / 255;

                        Color tintedColor = new Color(red, green, blue, alpha);
                        tintedImage.setRGB(x, y, tintedColor.getRGB());
                    } else {
                        tintedImage.setRGB(x, y, argb);
                    }
                }
            }
            this.image = tintedImage;
        }
    }

    private void applyTint() {
        if (tint != null && image != null) {
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = image.getRGB(x, y);
                    Color originalColor = new Color(argb, true);
                    int alpha = originalColor.getAlpha();

                    if (alpha > 0) {
                        tintedImage.setRGB(x, y, tint.getRGB());
                    } else {
                        tintedImage.setRGB(x, y, argb);
                    }
                }
            }
            this.image = tintedImage;
        }
    }



    public BufferedImage getImage() {
        return image;
    }


    public BlendMode getBlendMode() {
        return blendMode;
    }

    public Layer setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
        return this;
    }

    public float getOpacity() {
        return opacity;
    }
}
