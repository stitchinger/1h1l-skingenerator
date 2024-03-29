package main.java.layers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public class LayerComposer {

    public static BufferedImage combineLayers(List<Layer> layers){
        BufferedImage baseImage = layers.get(0).getOutputImage();
        for (int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            baseImage = LayerComposer.combine(new Layer(baseImage), layer).getOutputImage();
        }
        return baseImage;
    }

    public static Layer combine(Layer layer1, Layer layer2) {
        BufferedImage image1 = layer1.getOutputImage();
        BufferedImage image2 = layer2.getOutputImage();
        int width = image1.getWidth();
        int height = image1.getHeight();

        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color1 = new Color(image1.getRGB(x, y), true);
                Color color2 = new Color(image2.getRGB(x, y), true);
                Color blendedColor;

                switch (layer2.getBlendMode()) {
                    case SOFT_LIGHT:
                        blendedColor = blendSoftLight(color1, color2);
                        break;
                    case MULTIPLY:
                        blendedColor = blendMultiply(color1, color2, layer2.getOpacity());
                        break;
                    default:
                        blendedColor = blendNormal(color1, color2);
                        break;
                }
                combinedImage.setRGB(x, y, blendedColor.getRGB());
            }
        }

        return new Layer(combinedImage); // Set blend mode of combined layer to NORMAL or as needed
    }

    private static Color blendNormal(Color baseColor, Color blendColor) {
        float alpha1 = baseColor.getAlpha() / 255.0f;
        float alpha2 = blendColor.getAlpha() / 255.0f;
        float combinedAlpha = alpha1 + alpha2 * (1 - alpha1);

        if (combinedAlpha == 0) {
            // Avoid division by zero; if alpha is zero, color is irrelevant but should be fully transparent
            return new Color(0, 0, 0, 0);
        }

        int red = (int) (((blendColor.getRed() * alpha2) + (baseColor.getRed() * alpha1 * (1 - alpha2))) / combinedAlpha);
        int green = (int) (((blendColor.getGreen() * alpha2) + (baseColor.getGreen() * alpha1 * (1 - alpha2))) / combinedAlpha);
        int blue = (int) (((blendColor.getBlue() * alpha2) + (baseColor.getBlue() * alpha1 * (1 - alpha2))) / combinedAlpha);

        return new Color(clamp(red), clamp(green), clamp(blue), (int) (combinedAlpha * 255));
    }

    public static Color blendMultiply(Color baseColor, Color blendColor, float opacity) {
        if (opacity < 0.0f || opacity > 1.0f) {
            throw new IllegalArgumentException("Opacity must be between 0.0 and 1.0");
        }

        // Extract RGBA components from both colors
        int baseAlpha = baseColor.getAlpha();
        int baseRed = baseColor.getRed();
        int baseGreen = baseColor.getGreen();
        int baseBlue = baseColor.getBlue();

        int blendRed = blendColor.getRed();
        int blendGreen = blendColor.getGreen();
        int blendBlue = blendColor.getBlue();
        int blendAlpha = blendColor.getAlpha();

        if (blendAlpha == 0) {
            return baseColor;
        }

        // Perform the Multiply blend mode operation
        int redResult = (baseRed * blendRed) / 255;
        int greenResult = (baseGreen * blendGreen) / 255;
        int blueResult = (baseBlue * blendBlue) / 255;

        // Apply opacity to the blended result
        redResult = (int)((redResult - baseRed) * opacity + baseRed);
        greenResult = (int)((greenResult - baseGreen) * opacity + baseGreen);
        blueResult = (int)((blueResult - baseBlue) * opacity + baseBlue);

        // Return the new color with original alpha
        return new Color(redResult, greenResult, blueResult, baseAlpha);
    }

    private static Color blendSoftLight(Color baseColor, Color blendColor) {
        float alpha1 = baseColor.getAlpha() / 255.0f;
        float alpha2 = blendColor.getAlpha() / 255.0f;
        float combinedAlpha = alpha1 + alpha2 * (1 - alpha1); // This calculates the combined alpha

        // If blendColor is fully transparent, return the baseColor
        if (alpha2 == 0) {
            return baseColor;
        }

        double baseRed = baseColor.getRed() / 255.0;
        double baseGreen = baseColor.getGreen() / 255.0;
        double baseBlue = baseColor.getBlue() / 255.0;

        double blendRed = blendColor.getRed() / 255.0;
        double blendGreen = blendColor.getGreen() / 255.0;
        double blendBlue = blendColor.getBlue() / 255.0;

        // Apply soft light blend component-wise, modulated by the blend color's alpha
        double red = softLightComponent(baseRed, blendRed) * alpha2 + baseRed * (1 - alpha2);
        double green = softLightComponent(baseGreen, blendGreen) * alpha2 + baseGreen * (1 - alpha2);
        double blue = softLightComponent(baseBlue, blendBlue) * alpha2 + baseBlue * (1 - alpha2);

        // The resulting color should combine the alphas and clamp the values to ensure they're within the valid range
        return new Color(
                (int) (clamp(red) * 255),
                (int) (clamp(green) * 255),
                (int) (clamp(blue) * 255),
                (int) (combinedAlpha * 255)
        );
    }

    private static double softLightComponent(double base, double blend) {
        if (blend < 0.5) {
            return 2 * base * blend + base * base * (1 - 2 * blend);
        } else {
            return 2 * base * (1 - blend) + Math.sqrt(base) * (2 * blend - 1);
        }
    }

    private static double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

}
