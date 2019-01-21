package de.reclinarka.objects.interaction;

import de.reclinarka.graphics.registers.RegisterElement;
import de.reclinarka.objects.Writeable;

import java.awt.event.*;
//Interface for Objects that need to have basic and advanced Interaction
public interface Interactable extends Writeable {
    //method for Swing Mouse Events
    void mouseEvent(MouseEvent e, EventType type,String ID);
    //method for Swing KeyBoard Events
    void keyEvent(KeyEvent e, EventType type,String ID);
    //method for Engine Communication
    void commandThrown(String[] command,String ID);
    //method for setting Reciever to throw commands at
    void setReciever(InteractionRegistry reciever,String ID);
    //pretty obvious
    String getID();
}