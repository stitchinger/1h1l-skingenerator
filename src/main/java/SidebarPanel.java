package main.java;

import main.java.LifePhase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarPanel extends JPanel {
    private final JComboBox<LifePhase> lifePhaseSelect;

    public SidebarPanel(ImagePreviewApp app) {
        lifePhaseSelect = new JComboBox<>(LifePhase.values());
        lifePhaseSelect.setMaximumSize(lifePhaseSelect.getPreferredSize());

        lifePhaseSelect.addActionListener(e -> {
            LifePhase selectedLifePhase = (LifePhase) lifePhaseSelect.getSelectedItem();
            app.onLifePhaseChanged(selectedLifePhase);
        });

        add(lifePhaseSelect);
    }
}
