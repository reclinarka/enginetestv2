package de.reclinarka.objects.util;

import de.reclinarka.instances.InstanceManager;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GlobalString implements Interactable {
    public GlobalString(String ID, String content, InstanceManager manager){
        this.content = content;
        this.ID = ID;
        this.manager = manager;
    }

    private String ID;
    private String content;
    private InstanceManager manager;

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        return;
    }


    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        return;
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        if(command[1].contentEquals(ID)){
            switch (command[2]){
                case "getValue":
                    manager.getActiveInstance().getInteractionRegistry().commandThrown(new String[]{this.ID,command[0],
                            "returnValue", content},this.ID);
                    break;
                case "add":
                    content = content + command[3];
                    break;
            }
        }
    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {

    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }

    public String getID() {
        return ID;
    }
}
