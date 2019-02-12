package de.reclinarka.editor.animation.tools.controllElements;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ToolSlider extends ControlElement {
    public ToolSlider(Toolbar parent) {
        super(parent);
    }

    private int highlightTolerance = 20;
    private int startX = 70;
    private int startY = 10;
    private int length = 1820;
    private int barWidth = length/5;
    private int size = 30;
    private float value = 0;
    private int x = 0;
    private int y = 0;
    private boolean highlighted = false;

    @Override
    public void draw(Graphics g) {

        if(getParent().isExpanded()){
            int toolbarWidth = getParent().getWidth();
            int toolWidth = getParent().getDefaultToolWidth();
            int toolDistance = getParent().getDefaultToolDistance();
            int toolAmmount = getParent().getContent().size();
            if(((toolWidth * toolAmmount) + (toolDistance * toolAmmount)) > toolbarWidth) {
                if(highlighted)
                    g.setColor(new Color(255, 255, 255, 60));
                else
                    g.setColor(new Color(255, 255, 255, 30));

                g.fillRect(startX, startY, length, size);
                g.translate( startX + (int) (value * (length - barWidth)) , startY);
                g.fillRect(0, 0, barWidth, size);
                g.drawString("value: " + value,0,0);
            }



        }


    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        x = e.getX();
        y = getRelativeMouseY(e);

        if(getParent().isExpanded()) {
            if (x > startX && x < startX + length && y > startY && y < startY + size )
                highlighted = true;
            if(highlighted){
                if (!(x > startX - highlightTolerance && x < startX + length + highlightTolerance && y > startY - highlightTolerance && y < startY + size + highlightTolerance ))
                    highlighted = false;
            }
            if(type == EventType.Mouse_Dragged){
                if (highlighted){
                    int x = this.x - startX;
                    x = x - barWidth / 2;
                    value = (float)x / ((float)length - (float)barWidth);
                    if(value < 0)
                        value = 0;
                    else if(value > 1)
                        value = 1;
                }
            }
        }

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {

    }

    public float getValue() {
        return value;
    }
}
