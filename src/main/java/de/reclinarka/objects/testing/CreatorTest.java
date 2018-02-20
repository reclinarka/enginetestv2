package de.reclinarka.objects.testing;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CreatorTest extends Test {

    public CreatorTest() {
        super();
    }

    public CreatorTest(String ID, RectDimension dimension) {
        super(ID, dimension);
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        super.keyEvent(e, type, ID);
        switch (e.getKeyChar()){
            case 't':
                //new RectDimension(width,height,new Coordinate(x,y));
                getReciever().commandThrown(new String[]{ "sourceID" , "targetID" , "create" , "global" , "Test.java" ,
                        "testID_" + ( (int) (Math.random()*1000000)+1 ) , "" + ( (int) (Math.random()*200)+1 ) ,
                        "" + ( (int) (Math.random()*200)+1 ) , "" + ( (int) (Math.random()*1500)+1 ) , "" + ( (int) (Math.random()*800)+1 )} , getID());
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

    }


}
