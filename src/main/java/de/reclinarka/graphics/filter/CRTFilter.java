package de.reclinarka.graphics.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CRTFilter extends Filter {
    public CRTFilter(String ID, double percentage,int stripeHeight) {
        super(ID);
        this.percentage = percentage;
        this.stripeHeigth = stripeHeight;
    }

    private double percentage;
    private int stripeHeigth;

    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(0,0,0,50));
        for(int i = 0; i <= image.getHeight(); i+= stripeHeigth *2){
            g2d.fillRect(0,i,image.getWidth(),stripeHeigth);
        }
        return image;
    }
}
