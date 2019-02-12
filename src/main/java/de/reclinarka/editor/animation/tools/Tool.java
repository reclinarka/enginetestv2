package de.reclinarka.editor.animation.tools;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Tool {
    public Tool(String ID, Toolbar parent){
        this.ID = ID;
        this.parent = parent;
    }

    private String ID;
    private Toolbar parent;

    public String getID() {
        return ID;
    }

    public void draw(Graphics g, String[] metadata){
        g.setColor(new Color(255,255,255,80));
        g.fillRoundRect(0,0,parent.getDefaultToolWidth(),parent.getToolHeight(),20,20);
        g.drawString(ID,0,0);
    }

    protected int getRelativeMouseY(MouseEvent e){
        return e.getY() - ((parent.getHeight() * 4) + parent.getToolYOffset());
    }

    protected int getRelativeMouseX(MouseEvent e){
        float value = parent.getSliderValue();
        int size = getParent().getContent().size();
        int toolWidth = parent.getDefaultToolWidth();
        int distance = parent.getDefaultToolDistance();
        int index = getParent().getToolIndex(getID());
        int width = parent.getWidth();

        int length = (distance + (distance * size) + (toolWidth * size));


        return 0;
    }


    public Toolbar getParent() {
        return parent;
    }
}
