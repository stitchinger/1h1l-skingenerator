package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ImagePreviewApp extends JFrame {
    private List<Layer> layers = new ArrayList<>();
    private Color skinColor = new Color(224, 187, 150);
    private Color eyeColor = new Color(212, 250, 102);
    private Color eyeWhiteColor = new Color(253, 218, 218);
    private Color browColor = new Color(144, 113, 201);
    private Color hairColor = new Color(174, 112, 196);


    public ImagePreviewApp() {
        initializeWindow();
        loadLayers();
        addPreviewPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImagePreviewApp::new);
    }

    private void initializeWindow() {
        setTitle("Image Preview");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void loadLayers() {
        layers.add(new Layer("/skin_color.png")
                .color(skinColor));
        layers.add(new Layer("/skin_noise.png")
                .opacity(0.2));
        layers.add(new Layer("/skin_shading.png")
                .opacity(0.3));
        layers.add(new Layer("/eyewhite.png")
                .color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png")
                .color(eyeColor));
        layers.add(new Layer("/brow_larger.png")
                .color(browColor));
        layers.add(new Layer("/hair_teen.png")
                .color(hairColor)
        );

    }

    private void addPreviewPanel() {
        PreviewPanel previewPanel = new PreviewPanel(layers);
        add(previewPanel);
        setVisible(true);
    }


}
