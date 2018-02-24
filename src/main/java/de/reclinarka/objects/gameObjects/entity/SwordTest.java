package de.reclinarka.objects.gameObjects.entity;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.objects.util.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SwordTest implements Drawable,Interactable {

    public SwordTest(Coordinate origin){
        this.origin = origin;
    }

    private Coordinate origin;
    private Coordinate target = new Coordinate(0,0);
    private double ratio1 = 0.12;
    private double ratio2 = 0.5;





    @Override
    public void exec(Graphics g) {
        double distance = Math.sqrt( (Math.pow(target.getX() - origin.getX(),2)+Math.pow(target.getY() - origin.getY(),2)) );
        Vector vector1 = new Vector(target.getX() - origin.getX(),target.getY() - origin.getY());
        Vector vector2 = new Vector(vector1.getX() * (1-ratio1),vector1.getY() * (1-ratio1));
        Vector vector3 = new Vector(vector1.getX()-vector2.getX(),vector1.getY()-vector2.getY());

        Vector vector4 = new Vector(origin.getX()+vector2.getX(),origin.getY()+vector2.getY());
        Vector vector5 = new Vector(vector3.getY(),vector3.getX() *(-1));
        Vector vector6 = new Vector(vector3.getY()*(-1),vector3.getX() );

        Vector vector7 = new Vector(origin.getX() + vector3.getX(),origin.getY() + vector3.getY());
        Vector vector8 = new Vector(origin.getX() + (vector3.getX() * 0.7),origin.getY() + (vector3.getY() * 0.7));


        Coordinate pos1 = new Coordinate( Math.round( (float) ( vector4.getX()+vector5.getX() ) ) ,  Math.round( (float)  (vector4.getY() + vector5.getY()) ) );
        Coordinate pos2 = new Coordinate( Math.round( (float) ( vector4.getX()+vector6.getX() ) ) ,  Math.round( (float)  (vector4.getY() + vector6.getY()) ) );
        Coordinate pos3 = new Coordinate( Math.round( (float) ( vector7.getX()+ (vector6.getX() * ratio2) ) ) ,  Math.round( (float)  (vector7.getY() + (vector6.getY() * ratio2)) ) );
        Coordinate pos4 = new Coordinate( Math.round( (float) ( vector7.getX()+ (vector5.getX() * ratio2) ) ) ,  Math.round( (float)  (vector7.getY() + (vector5.getY() * ratio2)) ) );
        Coordinate pos5 = new Coordinate( Math.round( (float) ( vector7.getX()+ (vector6.getX() * ratio2 * 2.5) ) ) ,  Math.round( (float)  (vector7.getY() + (vector6.getY() * ratio2 * 2.5)) ) );
        Coordinate pos6 = new Coordinate( Math.round( (float) ( vector7.getX()+ (vector5.getX() * ratio2 * 2.5) ) ) ,  Math.round( (float)  (vector7.getY() + (vector5.getY() * ratio2 * 2.5)) ) );
        Coordinate pos7 = new Coordinate( Math.round( (float) ( vector8.getX()+ (vector6.getX() * ratio2 * 2) ) ) ,  Math.round( (float)  (vector8.getY() + (vector6.getY() * ratio2 * 2)) ) );
        Coordinate pos8 = new Coordinate( Math.round( (float) ( vector8.getX()+ (vector5.getX() * ratio2 * 2) ) ) ,  Math.round( (float)  (vector8.getY() + (vector5.getY() * ratio2 * 2)) ) );
        g.setColor(Color.RED);

        g.drawLine(pos4.getX(),pos4.getY(),pos1.getX(),pos1.getY());
        g.drawLine(pos3.getX(),pos3.getY(),pos2.getX(),pos2.getY());
        g.drawLine(pos5.getX(),pos5.getY(),pos6.getX(),pos6.getY());

        g.drawLine(pos5.getX(),pos5.getY(),pos7.getX(),pos7.getY());
        g.drawLine(pos8.getX(),pos8.getY(),pos6.getX(),pos6.getY());
        g.drawLine(pos8.getX(),pos8.getY(),pos7.getX(),pos7.getY());

        g.drawLine(pos1.getX(),pos1.getY(),target.getX(),target.getY());
        g.drawLine(pos2.getX(),pos2.getY(),target.getX(),target.getY());
        g.drawLine(Math.round((float)vector7.getX()),Math.round((float)vector7.getY()),target.getX(),target.getY());
        g.drawString(""+distance,Math.round((float)vector7.getX()),Math.round((float)vector7.getY()));
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
                target.setX(e.getX());
                target.setY(e.getY());
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
