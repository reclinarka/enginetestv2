package de.reclinarka.graphics.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Filter {

    public Filter(String ID){
        this.ID = ID;
    }


    String ID;
    public String getID(){
        return ID;
    }
    public BufferedImage applyFilter(BufferedImage image){
        return image;
    }
}
