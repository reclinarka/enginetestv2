package de.reclinarka.editor.animation.tools;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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

    public int getRelativeMouseY(MouseEvent e){
        return e.getY() - ((parent.getHeight() * 4) + parent.getToolYOffset());
    }

    public int getRelativeMouseX(MouseEvent e){
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

    public InteractionRegistry getParentInteractionRegistry(){
        return parent.getParent().getInteractionRegistry();
    }

    public DrawableRegister getParentDrawableRegister(){
        return parent.getParent().getDrawableRegister();
    }


}

class Button {
    public Button(Tool parent, int[] dimensions/*x,y,width,height*/, String ID){
        this.parent = parent;
        this.ID = ID;
        x = dimensions[0];
        y = dimensions[1];
        width = dimensions[2];
        height = dimensions[3];
    }

    private Tool parent;

    private String ID;

    private int x;
    private int y;

    private int width;
    private int height;

    private Color primary = new Color(255,255,255,40);


    public void draw(Graphics g){
        g.setColor(primary);
        g.fillRect(x,y,width,height);
    }

    public String[] withinBounds(int x, int y){
        String[] output = new String[]{"false",ID};
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
            output[0] = "true";
        return output;
    }
    public String getID() {
        return ID;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
