package de.reclinarka.objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Test implements Drawable,Interactable, Writeable{
    public Test(){

    }
    public Test(String ID){
        this.ID = ID;
    }
    private String ID;

    @Override
    public void mouseEvent(MouseEvent e, EventType type,String ID) {

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type,String ID) {

    }

    private InteractionRegistry reciever;

    @Override
    public void commandThrown(String[] command,String ID) {
        System.out.println(command);
    }

    @Override
    public void setReciever(InteractionRegistry reciever,String ID) {
        this.reciever = reciever;
    }

    @Override
    public void exec(Graphics g) {
        System.out.println("reached");
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    @Override
    public void save(String path) throws IOException {
        ObjectMapper  mapper = new ObjectMapper();
        File file = new File(path);
        mapper.writeValue(file,this);
    }

    @Override
    public void load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        Test test = mapper.readValue(file,Test.class);
        this.reciever = test.reciever;
        this.ID = test.ID;
    }
}