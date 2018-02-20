package de.reclinarka.objects.interaction;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Repeater implements Interactable{

    public Repeater(InteractionRegistry interactionRegistry, String ID){
        this.reciever = interactionRegistry;
        this.ID = ID;
    }

    private String ID;
    private InteractionRegistry reciever;

    @Override
    public void mouseEvent(MouseEvent e, EventType type,String ID) {
        reciever.mouseEvent(e,type,ID);
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type,String ID) {
        reciever.keyEvent(e,type,ID);

    }

    @Override
    public void commandThrown(String[] command,String ID) {
        reciever.commandThrown(command,ID);
    }

    @Override
    public void setReciever(InteractionRegistry reciever,String ID) {
        return;
    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }
}
