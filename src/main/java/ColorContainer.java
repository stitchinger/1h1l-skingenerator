package main.java;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ColorContainer {

    public Color skinColor, eyeColor, browColor, hairColor, noseColor, skirtYoungColor, skirtOldColor,  eyeWhiteColor, underwearColor, plantGreenColor, flowerColor, flowerInnerColor, wristBandsStart, wristBandsEnd;

    public ColorContainer(){
        loadColors();
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
        plantGreenColor = new Color(150, 237, 113);
        flowerColor = new Color(237, 76, 63);
        flowerInnerColor = new Color(250, 227, 110);
        wristBandsStart = new Color(250, 227, 110);
        wristBandsEnd = new Color(250, 227, 110);

    }

    public void saveColors() {
        Properties properties = new Properties();
        properties.setProperty("skinColor", colorToString(skinColor));
        properties.setProperty("eyeColor", colorToString(eyeColor));
        properties.setProperty("eyeWhiteColor", colorToString(eyeWhiteColor));
        properties.setProperty("browColor", colorToString(browColor));
        properties.setProperty("hairColor", colorToString(hairColor));
        properties.setProperty("noseColor", colorToString(noseColor));
        properties.setProperty("skirtYoungColor", colorToString(skirtYoungColor));
        properties.setProperty("skirtOldColor", colorToString(skirtOldColor));
        properties.setProperty("underwearColor", colorToString(underwearColor));
        properties.setProperty("plantGreenColor", colorToString(plantGreenColor));
        properties.setProperty("flowerColor", colorToString(flowerColor));
        properties.setProperty("flowerInnerColor", colorToString(flowerInnerColor));
        properties.setProperty("wristBandsStart", colorToString(wristBandsStart));
        properties.setProperty("wristBandsEnd", colorToString(wristBandsEnd));

        try (FileOutputStream fos = new FileOutputStream("colors.properties")) {
            properties.store(fos, "Colors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadColors() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("colors.properties")) {
            properties.load(fis);
            skinColor = stringToColor(properties.getProperty("skinColor", colorToString(new Color(238, 219, 194))));
            eyeColor = stringToColor(properties.getProperty("eyeColor", colorToString(new Color(98, 174, 190))));
            eyeWhiteColor = stringToColor(properties.getProperty("eyeWhiteColor", colorToString(new Color(98, 174, 190))));
            browColor = stringToColor(properties.getProperty("browColor", colorToString(new Color(98, 174, 190))));
            hairColor = stringToColor(properties.getProperty("hairColor", colorToString(new Color(98, 174, 190))));
            noseColor = stringToColor(properties.getProperty("noseColor", colorToString(new Color(98, 174, 190))));
            skirtYoungColor = stringToColor(properties.getProperty("skirtYoungColor", colorToString(new Color(98, 174, 190))));
            skirtOldColor = stringToColor(properties.getProperty("skirtOldColor", colorToString(new Color(98, 174, 190))));
            underwearColor = stringToColor(properties.getProperty("underwearColor", colorToString(new Color(98, 174, 190))));
            plantGreenColor = stringToColor(properties.getProperty("plantGreenColor", colorToString(new Color(98, 174, 190))));
            flowerColor =stringToColor(properties.getProperty("flowerColor", colorToString(new Color(98, 174, 190))));
            flowerInnerColor = stringToColor(properties.getProperty("flowerInnerColor", colorToString(new Color(98, 174, 190))));
            wristBandsStart = stringToColor(properties.getProperty("wristBandsStart", colorToString(new Color(98, 174, 190))));
            wristBandsEnd = stringToColor(properties.getProperty("wristBandsEnd", colorToString(new Color(98, 174, 190))));
        } catch (IOException e) {
            initializeColors();
        }
    }

    private String colorToString(Color color) {
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

    private Color stringToColor(String s) {
        String[] parts = s.split(",");
        return new Color(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
