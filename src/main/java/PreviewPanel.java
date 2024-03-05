package main.java;

import main.java.layers.Layer;
import main.java.layers.LayerComposer;
import main.java.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PreviewPanel extends JPanel {
    private List<Layer> drawLayers;

    public PreviewPanel(List<Layer> layers) {
        setDrawLayers(layers);
    }

    public void setDrawLayers(List<Layer> newLayers) {
        this.drawLayers = newLayers;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.fillRect(0, 0, 640, 640);

        drawLayers(g2d);

        g2d.dispose();
    }

    private void drawLayers(Graphics2D g2d) {
        if (drawLayers.isEmpty())
            return;
        BufferedImage scaledOutput = ImageUtils.scaleImage(LayerComposer.combineLayers(drawLayers), 640, 640);
        g2d.drawImage(scaledOutput, 0, 0, this);
    }



}
