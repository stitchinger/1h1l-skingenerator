package main.java;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
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

    private  Color skinColor = new Color(238, 219, 194);
    private  Color eyeColor = new Color(18, 156, 182);
    private  Color eyeWhiteColor = new Color(252, 252, 252);
    private  Color browColor = new Color(144, 113, 201);
    private  Color hairColor = new Color(220, 112, 33);
    private  Color noseColor = new Color(182, 141, 123);
    private  Color skirtColor = new Color(137, 222, 55);
    private final PreviewPanel previewPanel;
    private LifePhase currentPhase = LifePhase.CHILD;


    public ImagePreviewApp() {
        initializeWindow();
        loadLayers();

        SidebarPanel sidebarPanel = new SidebarPanel(this);
        sidebarPanel.setPreferredSize(new Dimension(150, getHeight()));
        add(sidebarPanel, BorderLayout.WEST);

        HeaderPanel headerPanel = new HeaderPanel(this);
        add(headerPanel, BorderLayout.NORTH);

        previewPanel = new PreviewPanel(teenLayers);
        onLifePhaseChanged(currentPhase);
        add(previewPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImagePreviewApp::new);
    }

    private void initializeWindow() {
        setTitle("Image Preview");
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void loadLayers() {
        babyLayers.add(new Layer("/skin_color.png").tint(skinColor));
        babyLayers.add(new Layer("/skin_noise.png").opacity(0.1).setBlendMode(BlendMode.MULTIPLY));
        babyLayers.add(new Layer("/skin_shading.png").opacity(0.1).setBlendMode(BlendMode.SOFT_LIGHT));
        babyLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        babyLayers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor,0f)));
        babyLayers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor,0f)));
        babyLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0f)));

        toddlerLayers.add(new Layer("/skin_color.png").tint(skinColor));
        toddlerLayers.add(new Layer("/skin_noise.png").opacity(0.5).setBlendMode(BlendMode.MULTIPLY));
        toddlerLayers.add(new Layer("/skin_shading.png").opacity(0.5).setBlendMode(BlendMode.SOFT_LIGHT));
        toddlerLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        toddlerLayers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor,0.1f)));
        toddlerLayers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor,0.1f)));
        toddlerLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0.1f)));

        childLayers.add(new Layer("/skin_color.png").tint(skinColor));
        childLayers.add(new Layer("/skin_noise.png").opacity(0.2).setBlendMode(BlendMode.MULTIPLY));
        childLayers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        childLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        childLayers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor,0.2f)));
        childLayers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor,0.2f)));
        childLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0.2f)));
        childLayers.add(new Layer("/skirt_child.png").color(ColorUtils.desaturate(skirtColor,0f)));
        childLayers.add(new Layer("/hair_child.png").color(ColorUtils.desaturate(hairColor,0.2f)));

        teenLayers.add(new Layer("/skin_color.png").tint(skinColor));
        teenLayers.add(new Layer("/skin_noise.png").opacity(0.3).setBlendMode(BlendMode.MULTIPLY));
        teenLayers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        teenLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        teenLayers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor,0.3f)));
        teenLayers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor,0.3f)));
        teenLayers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor,0.3f)));
        teenLayers.add(new Layer("/skirt_teen.png").color(ColorUtils.desaturate(skirtColor,0f)));
        teenLayers.add(new Layer("/hair_teen.png").color(ColorUtils.desaturate(hairColor,0.3f)));

        adultLayers.add(new Layer("/skin_color.png").tint(ColorUtils.desaturate(skinColor,0.4f)));
        adultLayers.add(new Layer("/skin_noise.png").opacity(0.4).setBlendMode(BlendMode.MULTIPLY));
        adultLayers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        adultLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        adultLayers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor,0.5f)));
        adultLayers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor,0.5f)));
        adultLayers.add(new Layer("/brow_larger.png").color(ColorUtils.desaturate(browColor,0.5f)));
        adultLayers.add(new Layer("/skirt_adult.png").color(ColorUtils.desaturate(skirtColor,0f)));
        adultLayers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(hairColor,0.5f)));

        elderLayers.add(new Layer("/skin_color.png").tint(ColorUtils.desaturate(skinColor,0.5f)));
        elderLayers.add(new Layer("/skin_noise.png").opacity(0.6).setBlendMode(BlendMode.MULTIPLY));
        elderLayers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        elderLayers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        elderLayers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor,0.7f)));
        elderLayers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor,0.3f)));
        elderLayers.add(new Layer("/brow_larger.png").color(ColorUtils.desaturate(browColor,0.7f)));
        elderLayers.add(new Layer("/skirt_elder.png").color(ColorUtils.desaturate(skirtColor,0f)));
        elderLayers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(hairColor,0.7f)));
    }

    public void onLifePhaseChanged(LifePhase lifePhase) {
        currentPhase = lifePhase;
        switch (lifePhase) {
            case BABY -> previewPanel.setLayers(babyLayers);
            case TODDLER -> previewPanel.setLayers(toddlerLayers);
            case CHILD -> previewPanel.setLayers(childLayers);
            case TEEN -> previewPanel.setLayers(teenLayers);
            case ADULT -> previewPanel.setLayers(adultLayers);
            case ELDER -> previewPanel.setLayers(elderLayers);
        }
    }

    public void refreshColors(Color skin, Color eye, Color brow, Color hair, Color nose, Color skirtColor){
        skinColor = skin;
        eyeColor = eye;
        browColor = brow;
        hairColor = hair;
        noseColor = nose;
        this.skirtColor = skirtColor;
        loadLayers();
        onLifePhaseChanged(currentPhase);
    }
}
