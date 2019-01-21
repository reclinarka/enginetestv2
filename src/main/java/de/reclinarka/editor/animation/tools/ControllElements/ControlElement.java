package de.reclinarka.editor.animation.tools.ControllElements;

import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface ControlElement {

    public void draw(Graphics g);

    public void mouseEvent(MouseEvent e, EventType type, String ID);


    public void keyEvent(KeyEvent e, EventType type, String ID);

    public void commandThrown(String[] command, String ID);

}
