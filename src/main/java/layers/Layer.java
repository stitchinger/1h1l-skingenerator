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
        loadImageAndScale(imagePath, 64, 64); // Load and scale the image
        outputImage = originalImage;
    }

    public Layer(BufferedImage originalImage) {
        this.originalImage = originalImage;
        this.outputImage = originalImage;
    }

    private void loadImageAndScale(String imagePath, int targetWidth, int targetHeight) {
        try {
            URL imageUrl = getClass().getResource(imagePath);
            this.originalImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Layer opacity(double value) {
        this.opacity = (float) value;
        return this;
    }

    public Layer color(Color newColor) {
        this.colorFilter = newColor;
        return this;
    }

    public Layer tint(Color newColor) {
        this.tint = newColor;
        return this;
    }

    public BufferedImage applyOpacity(BufferedImage originalImage, float opacity) {
        BufferedImage imageWithOpacity = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imageWithOpacity.createGraphics();

        java.awt.AlphaComposite alpha = java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(alpha);
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        return imageWithOpacity;
    }

    private BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    private BufferedImage applyColorFilter(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int argb = image.getRGB(x, y);
                Color originalColor = new Color(argb, true);
                int alpha = originalColor.getAlpha();

                if (alpha > 0) {
                    int red = (originalColor.getRed() * color.getRed()) / 255;
                    int green = (originalColor.getGreen() * color.getGreen()) / 255;
                    int blue = (originalColor.getBlue() * color.getBlue()) / 255;

                    Color tintedColor = new Color(red, green, blue, alpha);
                    output.setRGB(x, y, tintedColor.getRGB());
                } else {
                    output.setRGB(x, y, argb);
                }
            }
        }
        return output;
    }

    private BufferedImage applyTint(BufferedImage image, Color tint) {
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
        return tintedImage;
    }

    public BufferedImage getOutputImage() {
        BufferedImage output = copyImage(originalImage);

        if(tint != null){
            output = applyTint(output, tint);
        }
        if(colorFilter != null){
            output = applyColorFilter(output, colorFilter);
        }
        if(opacity > 0){
            output = applyOpacity(output,opacity);
        }
        return output;
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
