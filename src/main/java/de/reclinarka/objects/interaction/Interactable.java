package de.reclinarka.objects.interaction;

import de.reclinarka.graphics.registers.RegisterElement;
import de.reclinarka.objects.Writeable;

import java.awt.event.*;

public interface Interactable extends Writeable {
    void mouseEvent(MouseEvent e, EventType type,String ID);
    void keyEvent(KeyEvent e, EventType type,String ID);
    void commandThrown(String[] command,String ID);
    void setReciever(InteractionRegistry reciever,String ID);
    String getID();
}