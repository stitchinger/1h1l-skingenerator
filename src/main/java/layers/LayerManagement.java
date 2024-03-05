package main.java.layers;

import main.java.LifePhase;
import main.java.utils.ColorUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayerManagement {
    private final Map<LifePhase, List<Layer>> layersByPhase;
    private Color skinColor, eyeColor, browColor, hairColor, noseColor, skirtColor, eyeWhiteColor;

    public LayerManagement() {
        this.layersByPhase = new HashMap<>();
        initializeColors();
        loadAllLayers();
    }

    private void initializeColors() {
        skinColor = new Color(238, 219, 194);
        eyeColor = new Color(18, 156, 182);
        eyeWhiteColor = new Color(252, 252, 252);
        browColor = new Color(102, 78, 140);
        hairColor = new Color(153, 100, 232);
        noseColor = new Color(182, 141, 123);
        skirtColor = new Color(137, 222, 55);
    }

    private void loadAllLayers() {
        List<Layer> layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.1).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor, 0f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor, 0f)));
        layers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor, 0f)));
        layersByPhase.put(LifePhase.BABY, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.5).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.5).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor, 0.1f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor, 0.1f)));
        layers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor, 0.1f)));
        layersByPhase.put(LifePhase.TODDLER, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.2).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor, 0.2f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor, 0.2f)));
        layers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor, 0.2f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_child.png").color(ColorUtils.desaturate(skirtColor, 0f)));
        layers.add(new Layer("/hair_child.png").color(ColorUtils.desaturate(hairColor, 0.2f)));
        layersByPhase.put(LifePhase.CHILD, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.3).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor, 0.3f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor, 0.3f)));
        layers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(browColor, 0.3f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_teen.png").color(ColorUtils.desaturate(skirtColor, 0f)));
        layers.add(new Layer("/hair_teen.png").color(ColorUtils.desaturate(hairColor, 0.3f)));
        layersByPhase.put(LifePhase.TEEN, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(ColorUtils.desaturate(skinColor, 0.4f)));
        layers.add(new Layer("/skin_noise.png").opacity(0.4).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor, 0.5f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor, 0.5f)));
        layers.add(new Layer("/brow_larger.png").color(ColorUtils.desaturate(browColor, 0.5f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_adult.png").color(ColorUtils.desaturate(skirtColor, 0f)));
        layers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(hairColor, 0.5f)));
        layersByPhase.put(LifePhase.ADULT, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(ColorUtils.desaturate(skinColor, 0.5f)));
        layers.add(new Layer("/skin_noise.png").opacity(0.6).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyewhite.png").color(eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(eyeColor, 0.7f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(noseColor, 0.3f)));
        layers.add(new Layer("/brow_larger.png").color(ColorUtils.desaturate(browColor, 0.7f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_elder.png").color(ColorUtils.desaturate(skirtColor, 0f)));
        layers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(hairColor, 0.7f)));
        layersByPhase.put(LifePhase.ELDER, layers);
    }

    public void updateColorsInLayers(Color skin, Color eye,Color eyeWhite, Color brow, Color hair, Color nose, Color skirt) {
        this.skinColor = skin;
        this.eyeColor = eye;
        this.eyeWhiteColor = eyeWhite;
        this.browColor = brow;
        this.hairColor = hair;
        this.noseColor = nose;
        this.skirtColor = skirt;
        loadAllLayers();
    }

    public List<Layer> getLayersForLifePhase(LifePhase phase) {
        return layersByPhase.get(phase);
    }
}
