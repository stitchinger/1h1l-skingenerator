package main.java.layers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Layer {
    private BufferedImage originalImage;
    private BufferedImage outputImage;
    private float opacity = 1.0f;
    private Color colorFilter = null;
    private Color tint = null;
    private BlendMode blendMode = BlendMode.NORMAL;

    public Layer(String imagePath) {
        loadImageAndScale(imagePath, 640, 640); // Load and scale the image
        outputImage = originalImage;
    }

    public Layer(BufferedImage originalImage) {
        this.originalImage = originalImage;
        this.outputImage = originalImage;
    }

    private void loadImageAndScale(String imagePath, int targetWidth, int targetHeight) {
        try {
            URL imageUrl = getClass().getResource(imagePath);
            BufferedImage originalImage = ImageIO.read(imageUrl);
            this.originalImage = scaleImage(originalImage, targetWidth, targetHeight);
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
        applyOpacity();
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

    private void applyOpacity(){
        if (opacity != 0 && outputImage != null) {
            int width = outputImage.getWidth();
            int height = outputImage.getHeight();

            BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = outputImage.getRGB(x, y);
                    Color originalColor = new Color(argb, true);

                    int pixelAlpha = originalColor.getAlpha();

                    if (pixelAlpha > 0) {
                        int red = (originalColor.getRed());
                        int green = (originalColor.getGreen());
                        int blue = (originalColor.getBlue());

                        Color tintedColor = new Color(red, green, blue, (int)(pixelAlpha * opacity));
                        tintedImage.setRGB(x, y, tintedColor.getRGB());
                    } else {
                        tintedImage.setRGB(x, y, argb);
                    }
                }
            }
            this.outputImage = tintedImage;
        }
    }

    private void applyColorFilter() {
        if (colorFilter != null && outputImage != null) {
            int width = outputImage.getWidth();
            int height = outputImage.getHeight();

            BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = outputImage.getRGB(x, y);
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
            this.outputImage = tintedImage;
        }
    }

    private void applyTint() {
        if (tint != null && outputImage != null) {
            int width = outputImage.getWidth();
            int height = outputImage.getHeight();

            BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = outputImage.getRGB(x, y);
                    Color originalColor = new Color(argb, true);
                    int alpha = originalColor.getAlpha();

                    if (alpha > 0) {
                        tintedImage.setRGB(x, y, tint.getRGB());
                    } else {
                        tintedImage.setRGB(x, y, argb);
                    }
                }
            }
            this.outputImage = tintedImage;
        }
    }

    public BufferedImage getOutputImage() {
        return outputImage;
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
