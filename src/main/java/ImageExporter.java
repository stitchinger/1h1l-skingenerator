package main.java;

import main.java.layers.LayerComposer;
import main.java.layers.LayerManagement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageExporter {
    private final LayerManagement layerManagement;

    public ImageExporter(LayerManagement layerManagement) {
        this.layerManagement = layerManagement;
    }

    public void exportAllPhases() {
        String exportDirectory = "export";
        ensureExportDirectoryExists(exportDirectory);

        for (LifePhase phase : LifePhase.values()) {
            BufferedImage image = LayerComposer.combineLayers(layerManagement.getLayersForLifePhase(phase));
            String fileName = phase.name().toLowerCase() + ".png";
            saveSkinImage(image, fileName, exportDirectory);
        }
    }

    private void ensureExportDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void saveSkinImage(BufferedImage image, String fileName, String directoryPath) {
        File outputFile = new File(directoryPath, fileName);
        try {
            ImageIO.write(image, "PNG", outputFile);
            System.out.println("Saved skin: " + outputFile.getPath());
        } catch (IOException e) {
            System.err.println("Error saving skin: " + outputFile.getPath());
            e.printStackTrace();
        }
    }
}
