package main.java;

import java.awt.*;

public class ColorContainer {

    public Color skinColor, eyeColor, browColor, hairColor, noseColor, skirtYoungColor, skirtOldColor,  eyeWhiteColor, underwearColor;

    public ColorContainer(){
        initializeColors();
    }

    private void initializeColors() {
        skinColor = new Color(238, 219, 194);
        eyeColor = new Color(98, 174, 190);
        eyeWhiteColor = new Color(252, 252, 252);
        browColor = new Color(102, 78, 140);
        hairColor = new Color(162, 118, 229);
        noseColor = new Color(182, 141, 123);
        skirtYoungColor = new Color(148, 204, 103);
        skirtOldColor = new Color(175, 162, 88);
        underwearColor = new Color(220, 209, 199);
    }
}
