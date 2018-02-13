package de.reclinarka.objects;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionListener;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Creator implements Drawable,Interactable{

    public Creator(InteractionRegistry registry, DrawableRegister register, String ID){
        this.registry = registry;
        this.register = register;
        this.ID = ID;
    }

    private InteractionRegistry registry;
    private DrawableRegister register;
    private boolean activatet = false;
    private String ID;

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void exec(Graphics L) {

    }

    private void toggleActivated(){
        activatet = activatet != true;
    }

    public void addTest(String ID,RectDimension dimension){
        Test test = new Test(ID,dimension);
    }


    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        if (activatet) {
            switch (type) {
                case Mouse_Clicked:
                    Test test = new Test("", new RectDimension(20, 20, new Coordinate(e.getX(), e.getY())));
                    try {
                        register.addRegistry(test);
                    } catch (Exception exe) {}
                    try {
                        registry.addRegistry(test);
                    } catch (Exception ex) {}



                    return;
            }
        }
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        switch (type){
            case Key_Typed:
                switch (e.getKeyChar()){
                    case 'c' :
                    case 'C' :
                        System.out.println("reached");
                        toggleActivated();
                        break;
                }
                break;
        }
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        switch (command[0]){
            case "create" :
                break;
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
}
