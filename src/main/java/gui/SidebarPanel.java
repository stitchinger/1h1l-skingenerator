package main.java.gui;

import main.java.ApplicationWindow;
import main.java.ColorContainer;
import main.java.layers.LayerManagement;

import javax.swing.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel(ApplicationWindow app, LayerManagement layerManagement, PreviewPanel previewPanel, ColorContainer colorContainer) {
        ColorControlPanel colorControlPanel = new ColorControlPanel(app, layerManagement, previewPanel, colorContainer);
        add(colorControlPanel);

        JButton exportButton = new JButton("Export Skins");
        exportButton.addActionListener(e -> app.exportSkins());
        add(exportButton);
    }
}
