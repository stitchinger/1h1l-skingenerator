package main.java;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    private final JButton skinColorButton;
    private final JButton eyeColorButton;
    private final JButton browColorButton;
    private final JButton hairColorButton;
    private final JButton noseColorButton;
    private final JButton skirtColorButton;
    private final ImagePreviewApp app;


    public SidebarPanel(ImagePreviewApp app) {
        this.app = app;

        skinColorButton = createColorButton("Skin", new Color(224, 187, 150));
        eyeColorButton = createColorButton("Eyes", new Color(212, 250, 102));
        JButton eyeWhiteColorButton = createColorButton("Eye White", new Color(253, 218, 218));
        browColorButton = createColorButton("Brows", new Color(144, 113, 201));
        hairColorButton = createColorButton("Hair", new Color(253, 214, 120));
        noseColorButton = createColorButton("Nose", new Color(182, 141, 123));
        skirtColorButton = createColorButton("Skirt", new Color(182, 141, 123));

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
            Color newColor = JColorChooser.showDialog(SidebarPanel.this, "Choose " + label, button.getBackground());
            if (newColor != null) {
                button.setBorderPainted(false);
                button.setOpaque(true);
                button.setBackground(newColor);


                app.refreshColors(
                        skinColorButton.getBackground(),
                        eyeColorButton.getBackground(),
                        browColorButton.getBackground(),
                        hairColorButton.getBackground(),
                        noseColorButton.getBackground(),
                        skirtColorButton.getBackground()
                );
            }
        });
        return button;
    }
}
