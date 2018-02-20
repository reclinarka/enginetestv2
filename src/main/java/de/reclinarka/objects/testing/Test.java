package de.reclinarka.objects.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.Writeable;
import de.reclinarka.objects.framework.properties.colors.ColorConstant;
import de.reclinarka.objects.framework.properties.colors.Colorset;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static de.reclinarka.objects.framework.properties.colors.ColorConstant.*;

public class Test implements Drawable,Interactable, Writeable {
    public Test(){

    }
    public Test(String ID,RectDimension dimension){
        this.ID = ID;
        this.dimension = dimension;
    }

    private String ID;
    private RectDimension dimension;
    private Colorset colors = Colorset.defaultSet;
    private ColorConstant currentColor = MAIN;
    private ColorConstant currentBorder = BORDER;

    public RectDimension getDimension() {
        return dimension;
    }

    public Colorset getColors() {
        return colors;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type,String ID) {
        switch (type){
            case Mouse_Clicked:
                if(dimension.containsPoint(new Coordinate(e.getX(),e.getY()))){
                    System.out.println("clicked");
                    reciever.commandThrown(new String[]{ID,"","test_command"},ID);

                }
                return;
            case Mouse_Moved:
                if( dimension.containsPoint( new Coordinate( e.getX(),e.getY() ) ) ){
                    currentBorder = ALT_BORDER;
                } else {
                    currentBorder = BORDER;
                }
                return;
        }
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type,String ID) {
        System.out.println(e.getKeyCode());
    }

    private InteractionRegistry reciever;

    public InteractionRegistry getReciever() {
        return reciever;
    }

    @Override
    public void commandThrown(String[] command,String ID) {
        System.out.println(command[2]);
    }

    @Override
    public void setReciever(InteractionRegistry reciever,String ID) {
        this.reciever = reciever;
    }

    @Override
    public void exec(Graphics g) {
        g.setColor(colors.getColor(currentColor));
        g.fillRect(dimension.getPos().getX(), dimension.getPos().getY(),dimension.getWidth(),dimension.getHeight());
        g.setColor(colors.getColor(currentBorder));
        g.drawRect(dimension.getPos().getX(), dimension.getPos().getY(),dimension.getWidth(),dimension.getHeight());
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