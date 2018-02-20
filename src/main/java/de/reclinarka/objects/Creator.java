package de.reclinarka.objects;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.objects.testing.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Creator implements Drawable,Interactable{

    public Creator(Instance instance, String ID){
        this.instance = instance;
        this.ID = ID;
    }

    private Instance instance;
    private boolean activatet = true;
    private String ID;

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public void setActivatet(boolean activatet) {
        this.activatet = activatet;
    }

    public Instance getInstance() {
        return instance;
    }

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
                        instance.addItem(test,test);
                        System.out.println("created:" + test.getClass() + " and added to Instance:" + instance.getID());
                    } catch (Exception exc) {}



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
                        System.out.println("creator_toggled");
                        toggleActivated();
                        break;
                }
                break;
        }
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        if(command[1].contentEquals(this.ID)) {
            switch (command[2]) {
                case "create":
                    switch (command[3]) {
                        case "global":
                            switch (command[4]) {
                                case "Test.java":
                                    Test add = new Test(command[5], new RectDimension(Integer.parseInt(command[6]),
                                            Integer.parseInt(command[7]), new Coordinate(Integer.parseInt(command[8]),
                                            Integer.parseInt(command[9]))));
                                    instance.addGlobalItem(add, add);
                                    break;
                            }
                            break;
                        case "local":
                            switch (command[4]) {
                                case "Test.java":
                                    Test add = new Test(command[5], new RectDimension(Integer.getInteger(command[6]),
                                            Integer.getInteger(command[7]), new Coordinate(Integer.getInteger(command[8]),
                                            Integer.getInteger(command[9]))));
                                    instance.addItem(add, add);
                                    break;
                            }
                            break;
                    }
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
}
