package de.reclinarka.objects.framework.buttons;

import com.sun.xml.internal.bind.v2.model.core.ID;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.framework.properties.colors.ColorConstant;
import de.reclinarka.objects.framework.properties.colors.Colorset;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static de.reclinarka.objects.framework.properties.colors.ColorConstant.*;

public class Button implements Interactable {
    public Button(){

    }

    private RectDimension dimensions;
    private Coordinate pos;
    private Colorset colors;
    private ColorConstant currentColor = MAIN;
    private String ID;

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        switch (type){
            case Mouse_Clicked:
                return;
            case Mouse_Moved:
                return;
        }
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
