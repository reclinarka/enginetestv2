package de.reclinarka.graphics.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DistortFilter extends Filter {
    public DistortFilter(String ID, double percentage) {
        super(ID);
        this.percentage = percentage;
    }

    private double percentage = 0.3;

    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        //int[][][] pixelBuffer = new int[3][image.getHeight()][image.getWidth()];
        //for(int row = 0; row < image.getHeight(); row++){
        //    for(int col = 0; col < image.getWidth(); col++){
        //        Color c = new Color(image.getRGB(col,row));
        //        pixelBuffer[0][row][col] = c.getRed();
        //        pixelBuffer[1][row][col] = c.getGreen();
        //        pixelBuffer[2][row][col] = c.getBlue();
        //    }
        //}
        //int tempR = 0;
        //int tempG = 0;
        //int tempB = 0;
        //for(int row = 0; row < image.getHeight(); row++){
        //    if(Math.random() < percentage) {
        //        tempR = pixelBuffer[0][row][0];
        //        tempG = pixelBuffer[1][row][0];
        //        tempB = pixelBuffer[2][row][0];
        //        for (int col = 0; col < image.getWidth() - 1; col++) {
        //            pixelBuffer[0][row][col] = pixelBuffer[0][row][col + 1];
        //            pixelBuffer[1][row][col] = pixelBuffer[1][row][col + 1];
        //            pixelBuffer[2][row][col] = pixelBuffer[2][row][col + 1];
        //        }
        //        pixelBuffer[0][row][image.getWidth() - 1] = tempR;
        //        pixelBuffer[1][row][image.getWidth() - 1] = tempG;
        //        pixelBuffer[2][row][image.getWidth() - 1] = tempB;
        //    }
        //}
        //for(int row = 0; row < image.getHeight(); row++){
        //    for(int col = 0; col < image.getWidth(); col++){
        //        image.setRGB(col,row,new Color(pixelBuffer[0][row][col],pixelBuffer[1][row][col],pixelBuffer[2][row][col]).getRGB());
        //    }
        //}
        int tempRGB = 0;
        for(int row = 0; row < image.getHeight(); row++){
            if(Math.random() < percentage) {
                tempRGB = image.getRGB(0,row);
                for (int col = 0; col < image.getWidth() - 1; col++) {
                    image.setRGB(col,row,image.getRGB(col + 1,row));
                }
                image.setRGB(image.getWidth() - 1,row,tempRGB);
            } else if( Math.random() < percentage){
                tempRGB = image.getRGB(image.getWidth()-1,row);
                for (int col = image.getWidth()-1; col > 1; col--) {
                    image.setRGB(col,row,image.getRGB(col - 1 ,row));
                }
                image.setRGB(0,row,tempRGB);
            }
        }

        return image;
    }
}
