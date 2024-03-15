package main.java.utils;

import java.awt.Color;

public class ColorUtils {
    public static Color desaturate(Color color, float desaturationFactor) {
        float factor = Math.max(0, Math.min(desaturationFactor, 1));
        float[] hsbVals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        hsbVals[1] *= (1 - factor);

        return Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]);
    }

    public static Color interpolateColor(Color color1, Color color2, float fraction) {
        if (fraction < 0 || fraction > 1) {
            throw new IllegalArgumentException("The fraction must be in the range [0, 1]");
        }

        int deltaRed = color2.getRed() - color1.getRed();
        int deltaGreen = color2.getGreen() - color1.getGreen();
        int deltaBlue = color2.getBlue() - color1.getBlue();

        int red = color1.getRed() + Math.round(deltaRed * fraction);
        int green = color1.getGreen() + Math.round(deltaGreen * fraction);
        int blue = color1.getBlue() + Math.round(deltaBlue * fraction);

        return new Color(red, green, blue);
    }
}
