package de.reclinarka.objects.testing;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;

public class CreatorTest extends Test {

    public CreatorTest(String target) {
        super();
        this.target = target;
    }

    public CreatorTest(String target,String ID, RectDimension dimension) {
        super(ID, dimension);
        this.target = target;
    }

    private String target;
    private int count = 0;

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        super.keyEvent(e, type, ID);
        switch (e.getKeyChar()){
            case 't':
                //new RectDimension(width,height,new Coordinate(x,y));
                count++;

                    getReciever().commandThrown(new String[]{ "sourceID" , target , "create" , "global" , "Test.java" ,
                            "testID_" + ( (int) (Math.random()*1000000)+1 ) , "" + ( (int) (Math.random()*190)+10 ) ,
                            "" + ( (int) (Math.random()*190)+10 ) , "" + ( (int) (Math.random()*1500)+1 ) ,
                            "" + ( (int) (Math.random()*800)+1 )} , getID());


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
