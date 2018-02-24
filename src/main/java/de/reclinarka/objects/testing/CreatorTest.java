package de.reclinarka.objects.testing;

import de.reclinarka.instances.InstanceManager;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;

public class CreatorTest extends Test {

    public CreatorTest(String target,InstanceManager manager) {
        super();
        setManager(manager);
        this.target = target;
    }

    public CreatorTest(String target, String ID, RectDimension dimension, InstanceManager manager) {
        super(ID, dimension, manager);
        this.target = target;
    }

    private String target;
    private int count = 0;

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        super.keyEvent(e, type, ID);
        if(type == EventType.Key_Pressed)
        switch (e.getKeyChar()){
            case 't':
                //new RectDimension(width,height,new Coordinate(x,y));
                count++;
                System.out.println(getManager().getWindow().getWidth());

                    getReciever().commandThrown(new String[]{ "sourceID" , target , "create" , "global" , "Test.java" ,
                            "testID_" + ( (int) (Math.random()*1000000)+1 ) , "" + ( (int) (Math.random()*190)+10 ) ,
                            "" + ( (int) (Math.random()*190)+10 ) , "" + ( (int) (Math.random()* (getManager().getWindow().getWidth()-200) )+1 ) ,
                            "" + ( (int) (Math.random()* (getManager().getWindow().getHeight()-200) )+1 )} , getID());


                break;

        }
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {

    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {
        super.setReciever(reciever, ID);
    }

    @Override
    public void exec(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Created::" + count,10,10);
    }


}
