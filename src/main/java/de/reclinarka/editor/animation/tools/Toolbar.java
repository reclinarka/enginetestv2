package de.reclinarka.editor.animation.tools;

import de.reclinarka.editor.animation.tools.ControllElements.ControlElement;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.util.ColorStorage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Toolbar implements Interactable{

    public Toolbar(String ID){
        this.ID = ID;


    }


    private boolean expanded = false;
    private boolean hoverMenu = false;
    private Coordinate lastMousePos = new Coordinate(0,0);
    private String ID;
    private ArrayList<Tool> content;
    private ArrayList<ControlElement> elements;
    private BufferedImage lastFrame;

    public void removeTool(String ID){
        for (Tool f:content) {
            if(f.getID().contentEquals(ID))
                content.remove(f);
        }
    }

    public void addTool(Tool tool){
        content.add(tool);
    }

    public void draw(Graphics g, BufferedImage currentFrame) {

        if(expanded) {
            int height = currentFrame.getHeight() / 5;
            g.setColor(ColorStorage.defaultShade);
            g.fillRect(0, currentFrame.getHeight() - height, currentFrame.getWidth(), height);
            g.setColor(ColorStorage.defaultTextfieldBorder);
            g.drawRect(0, currentFrame.getHeight() - height, currentFrame.getWidth() - 1, height - 1);

            content.forEach(f -> f.draw( g, new String[]{}));
        } else {
            int height = currentFrame.getHeight() / 20;
            g.setColor(ColorStorage.defaultShade);
            g.fillRect(0, currentFrame.getHeight() - height, currentFrame.getWidth(), height);
            g.setColor(ColorStorage.defaultTextfieldBorder);
            g.drawRect(0, currentFrame.getHeight() - height, currentFrame.getWidth() - 1, height - 1);

            if(lastMousePos.getX() >=9 && lastMousePos.getX() <= 9+34 &&
                    lastMousePos.getY() >= currentFrame.getHeight()-height+10 &&
                    lastMousePos.getY() <= currentFrame.getHeight() - height + 10 + 34) {
                g.setColor(ColorStorage.defaultTextfieldBorder);
                g.fillRect(9, currentFrame.getHeight() - height + 10, 34, 34);
            } else {
                g.setColor(ColorStorage.defaultButtonHighlited);
                g.fillRect(9, currentFrame.getHeight() - height + 10, 34, 34);
            }
        }

        lastFrame = currentFrame;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        switch(type){
            case Mouse_Moved:
                break;
            case Mouse_Pressed:
                break;
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
    public String getID() {
        return ID;
    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }

    //Setter
    public void setContent(ArrayList<Tool> content) {
        this.content = content;
    }

    public void toggleExpanded(){
        expanded = !expanded;
    }

    //Getter
    public boolean isExpanded() {
        return expanded;
    }
}
