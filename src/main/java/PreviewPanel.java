package main.java;

import main.java.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PreviewPanel extends JPanel {
    private List<Layer> layers;

    public PreviewPanel(List<Layer> layers) {
        this.layers = layers;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        for (Layer layer : layers) {
            BufferedImage layerImage = layer.getImage(); // Ensure this method applies the current opacity
            // Optionally set an AlphaComposite if each layer manages its own opacity
            float layerOpacity = layer.getOpacity(); // Ensure Layer class has this method
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layerOpacity);
            g2d.setComposite(ac);

            // Draw the image, respecting layer order
            g2d.drawImage(layerImage, 0, 0, this);
        }

        g2d.dispose();
    }

}