package main.java.layers;

import main.java.ColorContainer;
import main.java.LifePhase;
import main.java.utils.ColorUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayerManagement {
    private final Map<LifePhase, List<Layer>> layersByPhase;
    private final ColorContainer colorContainer;


    public LayerManagement(ColorContainer colorContainer) {
        this.colorContainer = colorContainer;
        this.layersByPhase = new HashMap<>();
        loadAllLayers();
    }


    public void loadAllLayers() {
        Color start = colorContainer.wristBandsStart;
        Color end = colorContainer.wristBandsEnd;

        List<Layer> layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(colorContainer.skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.0).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/eyebags.png").opacity(0.1).color(new Color(255,0,0)));
        layers.add(new Layer("/eyewhite_larger.png").color(colorContainer.eyeWhiteColor));
        layers.add(new Layer("/eye_color_alt.png").color(ColorUtils.desaturate(colorContainer.eyeColor, 0f)));
        layers.add(new Layer("/nose_alt.png").color(ColorUtils.desaturate(colorContainer.noseColor, 0f)));
        layers.add(new Layer("/brow_alt.png").color(ColorUtils.desaturate(colorContainer.browColor, 0f)));
        layersByPhase.put(LifePhase.BABY, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(colorContainer.skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.1).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/eyewhite.png").color(colorContainer.eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(colorContainer.eyeColor, 0.1f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(colorContainer.noseColor, 0.1f)));
        layers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(colorContainer.browColor, 0.1f)));
        layersByPhase.put(LifePhase.TODDLER, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(colorContainer.skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.25).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyewhite.png").color(colorContainer.eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(colorContainer.eyeColor, 0.2f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(colorContainer.noseColor, 0.2f)));
        layers.add(new Layer("/brow_default.png").color(ColorUtils.desaturate(colorContainer.browColor, 0.2f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_child_alt.png")
                .color(ColorUtils.interpolateColor(colorContainer.skirtYoungColor, colorContainer.skirtOldColor, 0)));
        layers.add(new Layer("/wristbands.png")
                .color(ColorUtils.interpolateColor(start, end, 0.1f)));
        layers.add(new Layer("/hair_child.png").color(ColorUtils.desaturate(colorContainer.hairColor, 0.2f)));

        layers.add(new Layer("/hairflower_inner.png").color(colorContainer.flowerInnerColor));
        layers.add(new Layer("/hairflower_leaves.png").color(colorContainer.flowerColor));
        layers.add(new Layer("/hairflower_green.png").color(colorContainer.plantGreenColor));

        layers.add(new Layer("/necklace.png"));
        layers.add(new Layer("/necklace_flowers_inner.png").color(colorContainer.flowerInnerColor));
        layers.add(new Layer("/necklace_flowers_leaves.png").color(colorContainer.flowerColor));

        layers.add(new Layer("/hairbands.png").color(colorContainer.plantGreenColor));
        layersByPhase.put(LifePhase.CHILD, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(colorContainer.skinColor));
        layers.add(new Layer("/skin_noise.png").opacity(0.4).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyewhite.png").color(colorContainer.eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(colorContainer.eyeColor, 0.3f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(colorContainer.noseColor, 0.3f)));
        layers.add(new Layer("/brow_default_alt.png").color(ColorUtils.desaturate(colorContainer.browColor, 0.3f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_teen_alt.png")
                .color(ColorUtils.interpolateColor(colorContainer.skirtYoungColor, colorContainer.skirtOldColor, 0.3f)));

        layers.add(new Layer("/wristbands.png")
                .color(ColorUtils.interpolateColor(start, end, 0.3f)));
        layers.add(new Layer("/hair_teen.png").color(ColorUtils.desaturate(colorContainer.hairColor, 0.3f)));
        layersByPhase.put(LifePhase.TEEN, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(ColorUtils.desaturate(colorContainer.skinColor, 0.4f)));
        layers.add(new Layer("/skin_noise.png").opacity(0.65).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyebags.png").opacity(0.15).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/eyewhite.png").color(colorContainer.eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(colorContainer.eyeColor, 0.5f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(colorContainer.noseColor, 0.5f)));
        layers.add(new Layer("/brow_larger_alt.png").color(ColorUtils.desaturate(colorContainer.browColor, 0.5f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/skirt_adult_alt.png")
                .color(ColorUtils.interpolateColor(colorContainer.skirtYoungColor, colorContainer.skirtOldColor, 0.6f)));
        layers.add(new Layer("/wristbands.png")
                .color(ColorUtils.interpolateColor(start, end, 0.5f)));
        layers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(colorContainer.hairColor, 0.5f)));
        layersByPhase.put(LifePhase.ADULT, layers);

        layers = new ArrayList<>();
        layers.add(new Layer("/skin_color.png").tint(ColorUtils.desaturate(colorContainer.skinColor, 0.5f)));
        layers.add(new Layer("/skin_noise.png").opacity(0.8).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/skin_shading.png").opacity(0.9).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/eyebags.png").opacity(0.3).setBlendMode(BlendMode.MULTIPLY));
        layers.add(new Layer("/eyewhite.png").color(colorContainer.eyeWhiteColor));
        layers.add(new Layer("/eye_color.png").tint(ColorUtils.desaturate(colorContainer.eyeColor, 0.7f)));
        layers.add(new Layer("/nose.png").color(ColorUtils.desaturate(colorContainer.noseColor, 0.3f)));
        layers.add(new Layer("/brow_larger.png").color(ColorUtils.desaturate(colorContainer.browColor, 0.7f)));
        layers.add(new Layer("/underwear.png"));
        layers.add(new Layer("/underwear.png").color(Color.YELLOW).opacity(0.1).setBlendMode(BlendMode.SOFT_LIGHT));
        layers.add(new Layer("/wristbands.png")
                .color(ColorUtils.interpolateColor(start, end, 0.9f)));
        layers.add(new Layer("/skirt_elder_alt.png")
                .color(ColorUtils.interpolateColor(colorContainer.skirtYoungColor, colorContainer.skirtOldColor, 1)));
        layers.add(new Layer("/hair_adult.png").color(ColorUtils.desaturate(colorContainer.hairColor, 0.9f)));
        layersByPhase.put(LifePhase.ELDER, layers);
    }


    public List<Layer> getLayersForLifePhase(LifePhase phase) {
        return layersByPhase.get(phase);
    }


}
