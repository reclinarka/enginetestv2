package de.reclinarka.graphics.filter;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlurrFilter extends Filter {
    public BlurrFilter(String ID, int radius) {
        super(ID);
        this.radius = radius;
    }

    private int radius;



    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        ExecutorService exec = Executors.newFixedThreadPool(1000);
        BufferedImage returnImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int index;



            int count = ((radius * 2) + 1) * ((radius * 2) + 1);
            for (int col = radius; col < image.getWidth() - radius; col++) {
                int finalCol = col;
                exec.submit(() -> {
                    for (int row = radius; row < image.getHeight() - radius; row++) {


                        int finalRow = row;
                        int r = 0;
                        int g = 0;
                        int b = 0;
                        for (int tempRow = finalRow - radius; tempRow <= finalRow + radius; tempRow++) {
                            for (int tempCol = finalCol - radius; tempCol <= finalCol + radius; tempCol++) {
                            int rgb = image.getRGB(tempCol, tempRow);
                            int red = (rgb >> 16) & 0x0ff;
                            int green = (rgb >> 8) & 0x0ff;
                            int blue = (rgb) & 0x0ff;
                            r = red + r;
                            g = green + g;
                            b = blue + b;
                            }
                            }
                            r = r / count;
                            g = g / count;
                            b = b / count;
                            returnImage.setRGB(finalCol, finalRow, ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff));
                            r = g = b = 0;
                            }
                }
                );
                        }
            //int r = 0;
            //int g = 0;
            //int b = 0;
            //int count = ((radius * 2) + 1) * ((radius * 2) + 1);
            //for (int col = radius; col < image.getWidth() - radius; col++) {
            //    for (int tempRow = row - radius; tempRow <= row + radius; tempRow++) {
            //        for (int tempCol = col - radius; tempCol <= col + radius; tempCol++) {
            //            int rgb = image.getRGB(tempCol, tempRow);
            //            int red = (rgb >> 16) & 0x0ff;
            //            int green = (rgb >> 8) & 0x0ff;
            //            int blue = (rgb) & 0x0ff;
            //            r = red + r;
            //            g = green + g;
            //            b = blue + b;
            //        }
            //    }
            //    r = r / count;
            //    g = g / count;
            //    b = b / count;
            //    returnImage.setRGB(col, row, ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff));
            //    r = g = b = 0;
            //}
        try {
            exec.shutdown();
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {

        }
        return returnImage;
    }
}
