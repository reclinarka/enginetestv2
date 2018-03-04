package de.reclinarka.graphics.filter;

import java.awt.image.BufferedImage;

public class SepiaFilter extends GreyscaleFilter {
    public SepiaFilter(String ID, int shadeCount, int sepiaDepth, int sepiaIntensity) {
        super(ID, shadeCount);
        this.sepiaIntensity = sepiaIntensity;
    }

    private int sepiaDepth = 20;
    private int sepiaIntensity;

    @Override
    protected int getGrayRGB(int rgb) {
        int red = (rgb>>16)&0x0ff;
        int green=(rgb>>8) &0x0ff;
        int blue= (rgb)    &0x0ff;

        int ConversionFactor = 255 / (shadeCount - 1);
        int AverageValue = (red + green + blue) / 3;
        int grey =  ( (AverageValue / ConversionFactor)) * ConversionFactor;
        red = green = blue = grey;

        red = red + (sepiaDepth * 2);
        green = green + sepiaDepth;
        if(red > 255) red = 255;
        if(green > 255) green = 255;

        blue -= sepiaIntensity;

        if(blue < 0) blue = 0;

        return rgb = ((red&0x0ff)<<16)|((green&0x0ff)<<8)|(blue&0x0ff);
    }
}
