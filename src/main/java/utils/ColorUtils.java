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
}
