package de.reclinarka.graphics.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlurrFilter extends Filter {
    public BlurrFilter(String ID) {
        super(ID);
    }

    private int radius = 1;

    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        int[][][] pixelBuffer = new int[3][image.getHeight()][image.getWidth()];
        for(int row = 0; row < image.getHeight(); row++){
            for(int col = 0; col < image.getWidth(); col++){
                Color c = new Color(image.getRGB(col,row));
                pixelBuffer[0][row][col] = c.getRed();
                pixelBuffer[1][row][col] = c.getGreen();
                pixelBuffer[2][row][col] = c.getBlue();
            }
        }
        int r = 0;
        int g = 0;
        int b = 0;
        int count = 0;
        for(int row = radius; row < image.getHeight()-radius; row++){
            for(int col = radius; col < image.getWidth()-radius; col++){
                for(int tempRow = row - radius; tempRow <= row + radius; tempRow++){
                    for(int tempCol = col - radius; tempCol <= col + radius; tempCol++){
                        r = pixelBuffer[0][tempRow][tempCol] + r;
                        g = pixelBuffer[1][tempRow][tempCol] + g;
                        b = pixelBuffer[2][tempRow][tempCol] + b;
                        count++;
                    }
                }
                image.setRGB(col,row,new Color(r/count,g/count,b/count).getRGB());
                r = 0;
                g = 0;
                b = 0;
                count = 0;
            }
        }
        return image;
    }
}
