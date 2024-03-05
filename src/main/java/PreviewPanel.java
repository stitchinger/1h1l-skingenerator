package main.java;

import main.java.layers.Layer;
import main.java.layers.LayerComposer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PreviewPanel extends JPanel {
    private List<Layer> layers;

    public PreviewPanel(List<Layer> layers) {
        setLayers(layers);
    }

    public void setLayers(List<Layer> newLayers) {
        this.layers = newLayers;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.fillRect(0,0,640, 640);

        drawLayers(g2d);

        g2d.dispose();
    }

    private void drawLayers(Graphics2D g2d) {
        if (layers.isEmpty())
            return;

        BufferedImage baseImage = layers.get(0).getOutputImage();

        for (int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            baseImage = LayerComposer.combine(new Layer(baseImage), layer).getOutputImage();
        }
        g2d.drawImage(baseImage, 0, 0, this);
    }


}
