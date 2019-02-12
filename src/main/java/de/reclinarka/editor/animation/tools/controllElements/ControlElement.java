package de.reclinarka.editor.animation.tools.controllElements;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ControlElement {

    public ControlElement(Toolbar parent){
        this.parent = parent;
    }

    private Toolbar parent;

    public void draw(Graphics g) {

    }

    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }


    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    public void commandThrown(String[] command, String ID) {

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
}
