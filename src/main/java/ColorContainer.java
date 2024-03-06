package main.java;

import java.awt.*;

public class ColorContainer {

    public Color skinColor, eyeColor, browColor, hairColor, noseColor, skirtColor, eyeWhiteColor, underwearColor;

    public ColorContainer(){
        initializeColors();
    }

    private void initializeColors() {
        skinColor = new Color(238, 219, 194);
        eyeColor = new Color(18, 156, 182);
        eyeWhiteColor = new Color(252, 252, 252);
        browColor = new Color(102, 78, 140);
        hairColor = new Color(153, 100, 232);
        noseColor = new Color(182, 141, 123);
        skirtColor = new Color(137, 222, 55);
        underwearColor = new Color(198, 184, 169);
    }
}
