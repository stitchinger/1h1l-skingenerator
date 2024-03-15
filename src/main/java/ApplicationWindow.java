package main.java;

import main.java.gui.HeaderPanel;
import main.java.gui.SidebarPanel;
import main.java.layers.LayerManagement;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ApplicationWindow extends JFrame {
    private  PreviewPanel previewPanel;
    private final LayerManagement layerManagement;
    private final ImageExporter imageExporter;
    private LifePhase currentPhase = LifePhase.CHILD;
    private final ColorContainer colorContainer;

    public ApplicationWindow() {
        colorContainer = new ColorContainer();
        layerManagement = new LayerManagement(colorContainer);
        imageExporter = new ImageExporter(layerManagement);

        initializeWindow();
        setupUI();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window is closing, performing clean up tasks...");
                colorContainer.saveColors();
                System.exit(0);
            }
        });
    }

    private void initializeWindow() {
        setTitle("1H1L Skin Generator");
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupUI() {
        // Assume PreviewPanel is adjusted to work with LayerManagement for dynamic updates
        previewPanel = new PreviewPanel(layerManagement.getLayersForLifePhase(LifePhase.CHILD));
        add(previewPanel);

        SidebarPanel sidebarPanel = new SidebarPanel(this, layerManagement, previewPanel, colorContainer); // Adjust as needed to integrate with LayerManagement
        sidebarPanel.setPreferredSize(new Dimension(150, getHeight()));
        add(sidebarPanel, BorderLayout.WEST);

        HeaderPanel headerPanel = new HeaderPanel(this); // Adjust as needed
        add(headerPanel, BorderLayout.NORTH);


        setVisible(true);
    }

    // Method to handle life phase changes, e.g., from UI interactions
    public void onLifePhaseChanged(LifePhase lifePhase) {
        currentPhase = lifePhase;
        previewPanel.setDrawLayers(layerManagement.getLayersForLifePhase(currentPhase));
    }

    public LifePhase getCurrentPhase(){
        return currentPhase;
    }

    // Method to trigger exporting of images
    public void exportSkins() {
        imageExporter.exportAllPhases();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApplicationWindow::new);
    }
}
