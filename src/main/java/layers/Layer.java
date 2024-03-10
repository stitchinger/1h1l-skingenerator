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

    private BufferedImage applyFancyColor(BufferedImage image, Color color){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage coloredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Paint the new image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int grayColor = image.getRGB(x, y);
                int alpha = (grayColor >> 24) & 0xff;
                int grayLuminance = grayColor & 0xff; // Assuming the image is grayscale, R=G=B=grayLuminance

                // Adjust the color's brightness based on the grayscale luminance
                int newColor = (alpha << 24) | (adjustColorComponent(color.getRed(), grayLuminance) << 16) |
                        (adjustColorComponent(color.getGreen(), grayLuminance) << 8) |
                        adjustColorComponent(color.getBlue(), grayLuminance);

                coloredImage.setRGB(x, y, newColor);
            }
        }
        return  coloredImage;
    }

    private static int adjustColorComponent(int colorComponent, int luminance) {
        float ratio;
        if (luminance <= 128) {
            // Scale luminance to the range [0, 1] as it goes from 0 to 128
            ratio = luminance / 128f;
        } else {
            // Invert the scale for luminance going from 128 to 255
            ratio = (256f - luminance) / 128f;
        }
        return (int)(colorComponent * ratio);
    }

    public static BufferedImage applyColorBasedOnLuminance(BufferedImage originalImage, Color color) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int originalPixel = originalImage.getRGB(x, y);
                Color originalColor = new Color(originalPixel, true);
                if(originalColor.getAlpha() <= 0){
                    newImage.setRGB(x, y, originalPixel);
                }else{
                    double luminance = calculateLuminance(originalColor);
                    Color mixedColor = mixColorBasedOnLuminance(originalColor, color, luminance);

                    newImage.setRGB(x, y, mixedColor.getRGB());
                }
            }
        }

        return newImage;
    }

    private static double calculateLuminance(Color color) {
        return 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
    }

    private static Color mixColorBasedOnLuminance(Color originalColor, Color mixWith, double luminance) {
        double factor;
        if (luminance < 128) {
            factor = luminance / 128.0; // Closer to 0%, mix more with black
        } else {
            factor = (luminance - 128) / 128.0; // Closer to 100%, mix more with white
        }

        int r = mixWithChannel(mixWith.getRed(), originalColor.getRed(), factor);
        int g = mixWithChannel(mixWith.getGreen(), originalColor.getGreen(), factor);
        int b = mixWithChannel(mixWith.getBlue(), originalColor.getBlue(), factor);

        return new Color(r, g, b);
    }

    private static int mixWithChannel(int mixWith, int original, double factor) {
        int difference = mixWith - original;
        return (int)(original + difference * factor);
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
