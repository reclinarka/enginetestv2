package de.reclinarka.objects.framework.buttons;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Button implements Interactable {
    public Button(){

    }

    private Coordinate pos;




    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {

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
}
