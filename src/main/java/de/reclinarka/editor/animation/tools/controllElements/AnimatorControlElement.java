package de.reclinarka.editor.animation.tools.controllElements;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AnimatorControlElement {

    public AnimatorControlElement(Toolbar parent, String ID){
        this.parent = parent;
        this.ID = ID;
    }

    private boolean debugMode = false;
    private Toolbar parent;
    private String ID;

    public void draw(Graphics g) {

    }

    public void mouseEvent(MouseEvent e, EventType type, String ID) {

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

    protected int getRelativeMouseY(MouseEvent e){

        if(parent.isExpanded()) {
            return e.getY() - (parent.getHeight() * 4);
        } else {
            return e.getY() - (parent.getHeight() * 19);
        }

    }


    public Toolbar getParent() {
        return parent;
    }

    public String getID() {
        return ID;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
