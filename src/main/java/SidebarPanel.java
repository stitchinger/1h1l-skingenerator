package main.java;

import main.java.LifePhase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarPanel extends JPanel {
    private final JComboBox<LifePhase> lifePhaseSelect;

    private JButton skinColorButton;
    private JButton eyeColorButton;
    private JButton eyeWhiteColorButton;
    private JButton browColorButton;
    private JButton hairColorButton;
    private JButton noseColorButton;
    private ImagePreviewApp app;


    public SidebarPanel(ImagePreviewApp app) {
        this.app = app;
        lifePhaseSelect = new JComboBox<>(LifePhase.values());
        lifePhaseSelect.setMaximumSize(lifePhaseSelect.getPreferredSize());

        lifePhaseSelect.addActionListener(e -> {
            LifePhase selectedLifePhase = (LifePhase) lifePhaseSelect.getSelectedItem();
            app.onLifePhaseChanged(selectedLifePhase);
        });

        add(lifePhaseSelect);

        skinColorButton = createColorButton("Skin Color", new Color(224, 187, 150));
        eyeColorButton = createColorButton("Eye Color", new Color(212, 250, 102));
        eyeWhiteColorButton = createColorButton("Eye White Color", new Color(253, 218, 218));
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    // Update the specific color variable and refresh the preview panel
                    // For example: skinColor = newColor;
                    // You need to implement this part based on how your application state is managed.
                }
            }
        });
        return button;
    }
}
