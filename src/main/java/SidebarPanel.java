package main.java;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    private final JButton skinColorButton;
    private final JButton eyeColorButton;
    private final JButton browColorButton;
    private final JButton hairColorButton;
    private final JButton noseColorButton;
    private final ImagePreviewApp app;


    public SidebarPanel(ImagePreviewApp app) {
        this.app = app;

        skinColorButton = createColorButton("Skin Color", new Color(224, 187, 150));
        eyeColorButton = createColorButton("Eye Color", new Color(212, 250, 102));
        JButton eyeWhiteColorButton = createColorButton("Eye White Color", new Color(253, 218, 218));
        browColorButton = createColorButton("Brow Color", new Color(144, 113, 201));
        hairColorButton = createColorButton("Hair Color", new Color(253, 214, 120));
        noseColorButton = createColorButton("Nose Color", new Color(182, 141, 123));

        this.add(skinColorButton);
        this.add(eyeColorButton);
        this.add(eyeWhiteColorButton);
        this.add(browColorButton);
        this.add(hairColorButton);
        this.add(noseColorButton);
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
                        noseColorButton.getBackground()
                );
            }
        });
        return button;
    }
}
