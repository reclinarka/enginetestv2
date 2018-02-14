package de.reclinarka.graphics.frame.type;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.util.ColorStorage;

import javax.swing.*;
import java.awt.*;

public class Slate extends JPanel { //content for the Window
    public Slate(){}
    public Slate(DrawableRegister content){
        this.content = content;
    }
    private DrawableRegister content;

    public void setContent(DrawableRegister content) {
        this.content = content;
    }

    protected void paintComponent(Graphics g){
        g.setColor(ColorStorage.defaultBackGround);
        g.fillRect(-3,-3,this.getWidth() + 6, this.getHeight() + 6);
        try {
            content.draw(g);
        } catch ( NullPointerException e){}
    }
}
