package main.java.gui;

import main.java.Main;

import javax.swing.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel(Main app) {
        ColorControlPanel colorControlPanel = new ColorControlPanel(app);
        add(colorControlPanel);

        JButton exportButton = new JButton("Export Skins");
        exportButton.addActionListener(e -> app.export());
        add(exportButton);
    }
}
