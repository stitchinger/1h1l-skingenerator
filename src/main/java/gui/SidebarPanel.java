package main.java.gui;

import main.java.ApplicationWindow;
import main.java.PreviewPanel;
import main.java.layers.LayerManagement;

import javax.swing.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel(ApplicationWindow app, LayerManagement layerManagement, PreviewPanel previewPanel) {
        ColorControlPanel colorControlPanel = new ColorControlPanel(app, layerManagement, previewPanel);
        add(colorControlPanel);

        JButton exportButton = new JButton("Export Skins");
        exportButton.addActionListener(e -> app.exportSkins());
        add(exportButton);
    }
}
