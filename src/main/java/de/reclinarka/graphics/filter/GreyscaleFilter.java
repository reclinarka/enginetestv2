package de.reclinarka.graphics.filter;

import java.awt.image.BufferedImage;

public class GreyscaleFilter extends Filter {
    public GreyscaleFilter(String ID, int shadeCount) {
        super(ID);
        this.shadeCount = shadeCount;
    }

    protected int shadeCount;

    protected int getGrayRGB(int rgb){

        int red = (rgb>>16)&0x0ff;
        int green=(rgb>>8) &0x0ff;
        int blue= (rgb)    &0x0ff;

        int ConversionFactor = 255 / (shadeCount - 1);
        int AverageValue = (red + green + blue) / 3;
        int grey =  ( (AverageValue / ConversionFactor)) * ConversionFactor;
        red = green = blue = grey;


        return rgb = ((red&0x0ff)<<16)|((green&0x0ff)<<8)|(blue&0x0ff);
    }

    @Override
    public BufferedImage applyFilter(BufferedImage image) {

        for(int row = 0; row < image.getHeight(); row++){
            for(int col = 0; col < image.getWidth(); col++){
                image.setRGB(col,row,getGrayRGB(image.getRGB(col,row)));
            }
        }

        return image;
    }
}
