package main.java.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageManipulator {


    public static BufferedImage applyOpacity(BufferedImage source, float opacity) {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = result.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        return result;
    }


    public static BufferedImage applyColorFilter(BufferedImage source, Color colorFilter) {
        return applyFilter(source, (originalColor, x, y) -> {
            if (originalColor.getAlpha() > 0) {
                int red = (originalColor.getRed() * colorFilter.getRed()) / 255;
                int green = (originalColor.getGreen() * colorFilter.getGreen()) / 255;
                int blue = (originalColor.getBlue() * colorFilter.getBlue()) / 255;
                return new Color(red, green, blue, originalColor.getAlpha()).getRGB();
            }
            return originalColor.getRGB();
        });
    }

    private static BufferedImage applyFilter(BufferedImage source, PixelOperator operator) {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                Color originalColor = new Color(source.getRGB(x, y), true);
                result.setRGB(x, y, operator.apply(originalColor, x, y));
            }
        }
        return result;
    }

    public static BufferedImage applyTint(BufferedImage source,  Color tint) {
        return applyFilter(source, (originalColor, x, y) -> originalColor.getAlpha() > 0 ? tint.getRGB() : originalColor.getRGB());
    }

    @FunctionalInterface
    private interface PixelOperator {
        int apply(Color color, int x, int y);
    }
}
