package main.java.utils;

import java.awt.Color;

public class ColorUtils {


    public static Color desaturate(Color color, float desaturationFactor) {
        // Clamp the desaturation factor to the range [0, 1]
        float factor = Math.max(0, Math.min(desaturationFactor, 1));

        // Convert the color to HSB values
        float[] hsbVals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        // Reduce the saturation by the desaturation factor
        // Note that HSB's saturation is also in the range [0, 1]
        hsbVals[1] *= (1 - factor);

        // Convert back to RGB color model
        return Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]);
    }

    public static Color interpolateColor(Color color1, Color color2, float fraction) {
        if (fraction < 0 || fraction > 1) {
            throw new IllegalArgumentException("The fraction must be in the range [0, 1]");
        }

        // Calculate the difference in each color component
        int deltaRed = color2.getRed() - color1.getRed();
        int deltaGreen = color2.getGreen() - color1.getGreen();
        int deltaBlue = color2.getBlue() - color1.getBlue();

        // Calculate the interpolated components
        int red = color1.getRed() + Math.round(deltaRed * fraction);
        int green = color1.getGreen() + Math.round(deltaGreen * fraction);
        int blue = color1.getBlue() + Math.round(deltaBlue * fraction);

        // Return the new color
        return new Color(red, green, blue);
    }
}
