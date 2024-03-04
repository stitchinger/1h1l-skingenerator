package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ImagePreviewApp extends JFrame {
    private final List<Layer> babyLayers = new ArrayList<>();
    private final List<Layer> toddlerLayers = new ArrayList<>();
    private final List<Layer> childLayers = new ArrayList<>();
    private final List<Layer> teenLayers = new ArrayList<>();
    private final List<Layer> adultLayers = new ArrayList<>();
    private final List<Layer> elderLayers = new ArrayList<>();

    private final Color skinColor = new Color(224, 187, 150);
    private final Color eyeColor = new Color(212, 250, 102);
    private final Color eyeWhiteColor = new Color(253, 218, 218);
    private final Color browColor = new Color(144, 113, 201);
    private final Color hairColor = new Color(253, 214, 120);
    private final PreviewPanel previewPanel;


    public ImagePreviewApp() {
        initializeWindow();
        loadLayers();

        SidebarPanel sidebarPanel = new SidebarPanel(this);
        sidebarPanel.setPreferredSize(new Dimension(150, getHeight()));
        add(sidebarPanel, BorderLayout.WEST);
        previewPanel = new PreviewPanel(teenLayers);
        add(previewPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImagePreviewApp::new);
    }

    private void initializeWindow() {
        setTitle("Image Preview");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void loadLayers() {
        babyLayers.add(new Layer("/skin_color.png").color(skinColor));
        babyLayers.add(new Layer("/skin_shading.png", BlendMode.SOFT_LIGHT).opacity(0.9));
        babyLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        babyLayers.add(new Layer("/eye_color.png").color(ColorUtils.desaturate(eyeColor,0f)));
        babyLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(hairColor,0f)));

        toddlerLayers.add(new Layer("/skin_color.png").color(skinColor));
        toddlerLayers.add(new Layer("/skin_shading.png", BlendMode.SOFT_LIGHT).opacity(0.9));
        toddlerLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        toddlerLayers.add(new Layer("/eye_color.png").color(ColorUtils.desaturate(eyeColor,0.1f)));
        toddlerLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(hairColor,0.1f)));

        childLayers.add(new Layer("/skin_color.png").color(skinColor));
        childLayers.add(new Layer("/skin_shading.png", BlendMode.SOFT_LIGHT).opacity(0.9));
        childLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        childLayers.add(new Layer("/eye_color.png").color(ColorUtils.desaturate(eyeColor,0.2f)));
        childLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0.2f)));
        childLayers.add(new Layer("/hair_child.png").color(ColorUtils.desaturate(hairColor,0.2f)));

        teenLayers.add(new Layer("/skin_color.png").color(skinColor));
        teenLayers.add(new Layer("/skin_noise.png").opacity(0.1).setBlendMode(BlendMode.SOFT_LIGHT));
        teenLayers.add(new Layer("/skin_shading.png", BlendMode.SOFT_LIGHT).opacity(0.9));
        teenLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        teenLayers.add(new Layer("/eye_color.png").color(ColorUtils.desaturate(eyeColor,0.3f)));
        teenLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0.3f)));
        teenLayers.add(new Layer("/hair_teen.png").color(ColorUtils.desaturate(hairColor,0.3f)));

        adultLayers.add(new Layer("/skin_color.png").color(skinColor));
        adultLayers.add(new Layer("/skin_noise.png").opacity(0.5).setBlendMode(BlendMode.SOFT_LIGHT));
        adultLayers.add(new Layer("/skin_shading.png", BlendMode.SOFT_LIGHT).opacity(0.9));
        adultLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        adultLayers.add(new Layer("/eye_color.png").color(ColorUtils.desaturate(eyeColor,0.5f)));
        adultLayers.add(new Layer("/brow_larger.png").color(ColorUtils.desaturate(browColor,0.5f)));
        adultLayers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(hairColor,0.5f)));

        elderLayers.add(new Layer("/skin_color.png").color(skinColor));
        elderLayers.add(new Layer("/skin_noise.png").opacity(0.99).setBlendMode(BlendMode.SOFT_LIGHT));
        elderLayers.add(new Layer("/skin_shading.png", BlendMode.SOFT_LIGHT).opacity(0.9));
        elderLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        elderLayers.add(new Layer("/eye_color.png").color(ColorUtils.desaturate(eyeColor,0.7f)));
        elderLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0.7f)));
        elderLayers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(hairColor,0.7f)));
    }

    public void onLifePhaseChanged(LifePhase lifePhase) {
        switch (lifePhase) {
            case BABY -> previewPanel.setLayers(babyLayers);
            case TODDLER -> previewPanel.setLayers(toddlerLayers);
            case CHILD -> previewPanel.setLayers(childLayers);
            case TEEN -> previewPanel.setLayers(teenLayers);
            case ADULT -> previewPanel.setLayers(adultLayers);
            case ELDER -> previewPanel.setLayers(elderLayers);
        }
    }


}
