package de.reclinarka.editor.animation.tools;

import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Tool {
    public Tool(String ID, Toolbar parent){
        this.ID = ID;
        this.parent = parent;
    }

    private boolean debugMode = false;
    private String ID;
    private Toolbar parent;
    private int x;
    private int y;

    public String getID() {
        return ID;
    }

    public void draw(Graphics g, String[] metadata){
        g.setColor(new Color(255,255,255,80));
        g.fillRoundRect(0,0,parent.getDefaultToolWidth(),parent.getToolHeight(),20,20);
        if(debugMode) {
            g.drawString(ID, 0, 0);
            g.fillOval(x - 5, y - 5, 10, 10);
            g.drawString(ID, x, y);
        }
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
        int totalOffset = (int) ((length - width) * value);
        int offset = distance + (index * toolWidth) + (index * distance);

        return (e.getX() - offset) + totalOffset;
    }

    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        x = getRelativeMouseX(e);
        y = getRelativeMouseY(e);
    }


    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    public void commandThrown(String[] command, String ID) {
        if(command[0].contentEquals("debug"))
            if(command[1].contentEquals("all") || command[1].contentEquals(ID))
                if(command[2].contentEquals("true"))
                    debugMode = true;
                else if(command[2].contentEquals("false"))
                    debugMode = false;
    }


    public Toolbar getParent() {
        return parent;
    }
}
