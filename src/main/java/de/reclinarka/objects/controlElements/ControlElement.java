package de.reclinarka.objects.controlElements;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ControlElement implements Interactable, Drawable {

    public ControlElement(String ID){
        this.ID = ID;
    }
    private boolean debugMode = false;
    private InteractionRegistry reciever;
    private String ID;



    //Utils

    protected boolean isInbound(int[] xy, int[] width_height, int[] e_xy){
        if(e_xy[0] >= xy[0] && e_xy[1] >= xy[1] && e_xy[0] <= xy[0] + width_height[0] && e_xy[1] <= xy[1] + width_height[1])
            return true;
        return false;
    }



    //Overrides

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {
        if(command[0].contentEquals("debug"))
            if(command[1].contentEquals("all") || command[1].contentEquals(ID))
                if(command[2].contentEquals("true"))
                    debugMode = true;
                else if(command[2].contentEquals("false"))
                    debugMode = false;
    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {
        this.reciever = reciever;
    }

    @Override
    public void exec(Graphics g) {
        g.setColor(new Color(255,255,255));
        g.drawString(ID,0,0);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void load(String path) {

    }

    @Override
    public void save(String path) {

    }

    //Getter

    public boolean isDebugMode() {
        return debugMode;
    }

    public InteractionRegistry getReciever() {
        return reciever;
    }

    //Setter


    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
