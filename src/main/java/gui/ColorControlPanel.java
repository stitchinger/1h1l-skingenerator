package main.java.gui;

import main.java.ApplicationWindow;
import main.java.LifePhase;
import main.java.PreviewPanel;
import main.java.layers.LayerManagement;

import javax.swing.*;
import java.awt.*;

public class ColorControlPanel extends JPanel {

    private final JButton skinColorButton;
    private final JButton eyeWhiteColorButton;
    private final JButton eyeColorButton;
    private final JButton browColorButton;
    private final JButton hairColorButton;
    private final JButton noseColorButton;
    private final JButton skirtColorButton;
    private final ApplicationWindow app;
    private final LayerManagement layerManagement;
    private final PreviewPanel previewPanel;


    public ColorControlPanel(ApplicationWindow app, LayerManagement layerManagement, PreviewPanel previewPanel) {
        this.layerManagement = layerManagement;
        this.previewPanel = previewPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.app = app;

        skinColorButton = createColorButton("Skin", new Color(224, 187, 150));
        eyeColorButton = createColorButton("Eyes", new Color(103, 162, 197));
        eyeWhiteColorButton = createColorButton("Eye White", new Color(255, 255, 255));
        browColorButton = createColorButton("Brows", new Color(50, 72, 100));
        hairColorButton = createColorButton("Hair", new Color(206, 108, 238));
        noseColorButton = createColorButton("Nose", new Color(182, 141, 123));
        skirtColorButton = createColorButton("Skirt", new Color(174, 211, 108));

        this.add(skinColorButton);
        this.add(eyeColorButton);
        this.add(eyeWhiteColorButton);
        this.add(browColorButton);
        this.add(hairColorButton);
        this.add(noseColorButton);
        this.add(skirtColorButton);
    }

    private JButton createColorButton(String label, Color defaultColor) {
        JButton button = new JButton(label);
        button.setBackground(defaultColor);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(ColorControlPanel.this, "Choose " + label, button.getBackground());
            if (newColor != null) {
                button.setBorderPainted(false);
                button.setOpaque(true);
                button.setBackground(newColor);

                layerManagement.updateColorsInLayers(
                        skinColorButton.getBackground(),
                        eyeColorButton.getBackground(),
                        eyeWhiteColorButton.getBackground(),
                        browColorButton.getBackground(),
                        hairColorButton.getBackground(),
                        noseColorButton.getBackground(),
                        skirtColorButton.getBackground()
                );
                previewPanel.setDrawLayers(layerManagement.getLayersForLifePhase(app.getCurrentPhase()));
            }
        });
        return button;
    }
}
