package de.reclinarka.graphics.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorFilter extends Filter {
    public ColorFilter(String ID, Color color) {
        super(ID);
        this.color = color;
    }

    private Color color;

    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0,0,image.getWidth(),image.getHeight());
        return image;
    }
}
