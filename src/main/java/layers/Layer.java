package main.java.layers;

import main.java.images.ImageLoader;
import main.java.images.ImageManipulator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Layer {
    private final BufferedImage image;
    private float opacity = 1.0f;
    private Color colorFilter;
    private Color tint;
    private BlendMode blendMode = BlendMode.NORMAL;

    public Layer(String imagePath) {
        this.image = ImageLoader.loadImage(imagePath);
    }

    public Layer(BufferedImage image) {
        this.image = image;
    }

    public Layer opacity(double value) {
        this.opacity = (float) value;
        return this;
    }

    public float getOpacity() {
        return opacity;
    }

    public Layer color(Color color) {
        this.colorFilter = color;
        return this;
    }

    public Layer tint(Color color) {
        this.tint = color;
        return this;
    }

    public BlendMode getBlendMode() {
        return blendMode;
    }

    public Layer setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
        return this;
    }

    public BufferedImage getOutputImage() {
        BufferedImage result = copyImage(image);
        if (tint != null) result = ImageManipulator.applyTint(result, this.tint);
        if (colorFilter != null) result = ImageManipulator.applyColorFilter(result, this.colorFilter);
        if (opacity > 0) result = ImageManipulator.applyOpacity(result, this.opacity);
        return result;
    }

    private BufferedImage copyImage(BufferedImage source) {
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return copy;
    }


}
