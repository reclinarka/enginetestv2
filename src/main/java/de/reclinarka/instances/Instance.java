package de.reclinarka.instances;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.Window;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Instance implements Interactable{

    private Instance next;

    public void close(){
        DrawableRegister drawableRegister = new DrawableRegister("test123");
        InteractionRegistry interactionRegistry = new InteractionRegistry("test456");
        Slate slate = new Slate(drawableRegister);
        Window window = new Window("instanceTest",slate,1200,400,false);
        window.dispose();
    }


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
