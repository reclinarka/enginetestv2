package de.reclinarka.objects.util;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.instances.InstanceManager;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GlobalCounter implements Interactable, Drawable {

    public GlobalCounter(String ID, double count, InstanceManager manager){
        this.count = count;
        this.ID = ID;
        this.manager = manager;
    }

    private String ID;
    private double count;
    private InstanceManager manager;

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {
        if(command[1].contentEquals(ID)){
            switch (command[2]){
                case "ping":
                    manager.getActiveInstance().getInteractionRegistry().commandThrown(new String[]{this.ID,command[0],
                            "ping.return"},this.ID);
                    break;
                case "add":
                    count = count + Double.parseDouble(command[3]);
                    break;
                case "subtract":
                    count = count - Double.parseDouble(command[3]);
                    break;
                case "multiply":
                    count = count * Double.parseDouble(command[3]);
                    break;
                case "divide":
                    count = count / Double.parseDouble(command[3]);
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

    @Override
    public void exec(Graphics L) {

    }

    @Override
    public String getID() {
        return ID;
    }
}
