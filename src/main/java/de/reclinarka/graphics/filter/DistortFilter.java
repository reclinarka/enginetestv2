package de.reclinarka.graphics.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DistortFilter extends Filter {
    public DistortFilter(String ID, double percentage,int maxAmmount,int minAmmount) {
        super(ID);
        this.percentage = percentage;
        this.maxAmmount = maxAmmount;
        this.minAmmount = minAmmount;
    }

    private double percentage = 0.3;
    private int maxAmmount;
    private int minAmmount;

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
        int[] pixelBuffer;
        int tempRGB = 0;
        for(int row = 0; row < image.getHeight(); row++){
            if(Math.random() < percentage) {
                pixelBuffer = new int[ (int) (  ( Math.random()* (maxAmmount-minAmmount) ) + minAmmount ) ];
                //tempRGB = image.getRGB(0,row);
                for(int i = 0; i < pixelBuffer.length; i++){
                    pixelBuffer[i] = image.getRGB(i,row);
                }
                for (int col = 0; col < image.getWidth() - pixelBuffer.length; col++) {
                    image.setRGB(col,row,image.getRGB(col + pixelBuffer.length,row));
                }
                //image.setRGB(image.getWidth() - 1,row,tempRGB);
                int count = 0;
                for(int i = image.getWidth() - pixelBuffer.length;i < image.getWidth(); i++){
                    image.setRGB(i,row,pixelBuffer[count]);
                    count++;
                }
            } else if( Math.random() < percentage){
                pixelBuffer = new int[ (int) (  ( Math.random()* (maxAmmount-minAmmount) ) + minAmmount ) ];
                //tempRGB = image.getRGB(image.getWidth()-1,row);
                for(int i = 0; i < pixelBuffer.length; i++){
                    pixelBuffer[i] = image.getRGB(image.getWidth()-(i+1),row);
                }
                for (int col = image.getWidth()-1; col > pixelBuffer.length ; col--) {
                    image.setRGB(col,row,image.getRGB(col - (pixelBuffer.length-1),row));
                }
                for(int i = 0; i < pixelBuffer.length - 1; i++){
                    image.setRGB(i,row, pixelBuffer[(pixelBuffer.length - (i+1))]);
               }
            }
        }

        return image;
    }
}
