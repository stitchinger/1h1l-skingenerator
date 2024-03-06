package main.java.gui;

import main.java.ApplicationWindow;
import main.java.ColorContainer;
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
    private ColorContainer colorContainer;


    public ColorControlPanel(ApplicationWindow app, LayerManagement layerManagement, PreviewPanel previewPanel, ColorContainer colorContainer) {
        this.layerManagement = layerManagement;
        this.previewPanel = previewPanel;
        this.colorContainer = colorContainer;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.app = app;

        skinColorButton = createColorButton("Skin", colorContainer.skinColor);
        eyeColorButton = createColorButton("Eyes", colorContainer.eyeColor);
        eyeWhiteColorButton = createColorButton("Eye White", colorContainer.eyeWhiteColor);
        browColorButton = createColorButton("Brows", colorContainer.browColor);
        hairColorButton = createColorButton("Hair", colorContainer.hairColor);
        noseColorButton = createColorButton("Nose", colorContainer.noseColor);
        skirtColorButton = createColorButton("Skirt", colorContainer.skirtColor);

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

                colorContainer.skinColor = skinColorButton.getBackground();
                colorContainer.eyeColor = eyeColorButton.getBackground();
                colorContainer.eyeWhiteColor = eyeWhiteColorButton.getBackground();
                colorContainer.browColor = browColorButton.getBackground();
                colorContainer.hairColor = hairColorButton.getBackground();
                colorContainer.noseColor = noseColorButton.getBackground();
                colorContainer.skirtColor = skirtColorButton.getBackground();
                layerManagement.loadAllLayers();

                previewPanel.setDrawLayers(layerManagement.getLayersForLifePhase(app.getCurrentPhase()));
            }
        });
        return button;
    }
}
