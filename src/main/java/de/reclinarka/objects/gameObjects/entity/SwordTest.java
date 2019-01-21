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

public class SwordTest implements Drawable, Interactable {

    public SwordTest(String ID,Coordinate origin,double diameter,double distanceToOrigin) {
        this.origin = origin;
        this.diameter = diameter;
        this.distanceToOrigin = distanceToOrigin;
        this.ID = ID;
    }

    private String ID;
    private boolean test = true;
    private Coordinate pos = new Coordinate(0,0);
    private Coordinate origin;
    private Coordinate target = new Coordinate(0, 0);
    private Coordinate lastPos = new Coordinate(0,1);
    private double ratio1 = 0.09;
    private double ratio2 = 0.5;
    private int width;
    private double diameter = 200;
    private double distanceToOrigin = 30;
    private double angle = 0;
    private int direction;

    public void setOrigin(Coordinate origin) {
        this.origin = origin;
    }

    private void updateTarget(){
        if(pos.getX() == lastPos.getX() && pos.getY() == lastPos.getY()){
        } else {
            Vector distanceToMouse = Vector.getVector(origin, pos);
            Vector originToBorder;
            lastPos = new Coordinate(pos.getX(), pos.getY());
            angle = Math.toDegrees(Vector.getNormalVector().getAngle(distanceToMouse));
        }
        Vector normal = new Vector(0, -diameter);
        target = normal.rotate(360 - (angle + 180)).getPosRelativeTo(origin);

        //if(angle >= 157.5){
        //    target = normal.getPosRelativeTo(origin);
        //    direction = 0;
        //    return;
        //} else if( angle >= 112.5){
        //    target = normal.rotate(45).getPosRelativeTo(origin);
        //    direction = 45;
        //    return;
        //} else if( angle >= 67.5) {
        //    target = normal.rotate(90).getPosRelativeTo(origin);
        //    direction = 90;
        //    return;
        //} else if( angle >= 22.5) {
        //    target = normal.rotate(135).getPosRelativeTo(origin);
        //    direction = 135;
        //    return;
        //} else if( angle >= -22.5) {
        //    target = normal.rotate(180).getPosRelativeTo(origin);
        //    direction = 180;
        //    return;
        //} else if( angle >= -67.5) {
        //    target = normal.rotate(225).getPosRelativeTo(origin);
        //    direction = 225;
        //    return;
        //} else if( angle >= -112.5) {
        //    target = normal.rotate(270).getPosRelativeTo(origin);
        //    direction = 270;
        //    return;
        //} else if( angle >= -157.5){
        //    target = normal.rotate(315).getPosRelativeTo(origin);
        //    direction = 315;
        //    return;
        //} else {
        //    target = normal.getPosRelativeTo(origin);
        //    direction = 0;
        //    return;
        //}
    }

    @Override
    public void exec(Graphics g) {
        updateTarget();

        double distance = Math.sqrt((Math.pow(target.getX() - origin.getX(), 2) + Math.pow(target.getY() - origin.getY(), 2)));

        Vector v1 = Vector.getVector(origin,target);

        Coordinate origin = v1.devide(v1.getLength()).multiply(distanceToOrigin).getPosRelativeTo(this.origin);

        //v1 = origin -> target
        //v2 = v1 ( length = 100% - ratio1% )
        //v3 = v1 - v2 ( tip / ratio1 )
        Vector vector1 = new Vector(target.getX() - origin.getX(), target.getY() - origin.getY());
        Vector vector2 = new Vector(vector1.getX() * (1 - ratio1), vector1.getY() * (1 - ratio1));
        Vector vector3 = new Vector(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY());

        //v4 = pos where pos + v3 = tip
        //v5 = perpendicular to v3
        //v6 = opposite of v5
        Vector vector4 = new Vector(origin.getX() + vector2.getX(), origin.getY() + vector2.getY());
        Vector vector5 = new Vector(vector3.getY(), vector3.getX() * (-1));
        Vector vector6 = new Vector(vector3.getY() * (-1), vector3.getX());

        //v7 = top end of guard
        //v8 = lower end of quard
        Vector vector7 = new Vector(origin.getX() + (vector3.getX() * 2.5), origin.getY() + (vector3.getY() * 2.5));
        Vector vector8 = new Vector(origin.getX() + (vector3.getX() * 2), origin.getY() + (vector3.getY() * 2));


        Coordinate vec7 = vector7.getCoordinate();
        Coordinate vec8 = vector8.getCoordinate();

        Coordinate pos1 = new Coordinate(Math.round((float) (vector4.getX() + vector5.getX())), Math.round((float) (vector4.getY() + vector5.getY())));
        Coordinate pos2 = new Coordinate(Math.round((float) (vector4.getX() + vector6.getX())), Math.round((float) (vector4.getY() + vector6.getY())));
        Coordinate pos3 = new Coordinate(Math.round((float) (vector7.getX() + (vector6.getX() * ratio2))), Math.round((float) (vector7.getY() + (vector6.getY() * ratio2))));
        Coordinate pos4 = new Coordinate(Math.round((float) (vector7.getX() + (vector5.getX() * ratio2))), Math.round((float) (vector7.getY() + (vector5.getY() * ratio2))));
        Coordinate pos5 = new Coordinate(Math.round((float) (vector7.getX() + (vector6.getX() * ratio2 * 2.5))), Math.round((float) (vector7.getY() + (vector6.getY() * ratio2 * 2.5))));
        Coordinate pos6 = new Coordinate(Math.round((float) (vector7.getX() + (vector5.getX() * ratio2 * 2.5))), Math.round((float) (vector7.getY() + (vector5.getY() * ratio2 * 2.5))));
        //pos 7 / 8 = bottom corners of guard
        Coordinate pos7 = new Coordinate(Math.round((float) (vector8.getX() + (vector6.getX() * ratio2 * 2))), Math.round((float) (vector8.getY() + (vector6.getY() * ratio2 * 2))));
        Coordinate pos8 = new Coordinate(Math.round((float) (vector8.getX() + (vector5.getX() * ratio2 * 2))), Math.round((float) (vector8.getY() + (vector5.getY() * ratio2 * 2))));

        width = Math.round( (float) (Math.sqrt((Math.pow(vector7.getX() - vector8.getX(), 2) + Math.pow(vector7.getY() - vector8.getY(), 2)))) );

        g.setColor(Color.RED);
        g.setFont(new Font("",2,18));


        //edges

        //g.drawLine(pos4.getX(), pos4.getY(), pos1.getX(), pos1.getY());
        //g.drawLine(pos3.getX(), pos3.getY(), pos2.getX(), pos2.getY());


        //guard
        g.drawLine(pos5.getX(), pos5.getY(), pos6.getX(), pos6.getY());
        g.drawLine(pos5.getX(), pos5.getY(), pos7.getX(), pos7.getY());
        g.drawLine(pos8.getX(), pos8.getY(), pos6.getX(), pos6.getY());
        g.drawLine(pos8.getX(), pos8.getY(), pos7.getX(), pos7.getY());

        //tip + middleline

        //g.drawLine(pos1.getX(), pos1.getY(), target.getX(), target.getY());
        //g.drawLine(pos2.getX(), pos2.getY(), target.getX(), target.getY());


        g.drawLine(Math.round((float) vector7.getX()), Math.round((float) vector7.getY()), target.getX(), target.getY());
        g.drawLine( Math.round((float) vector8.getX()), Math.round((float) vector8.getY()),origin.getX(),origin.getY());
        g.fillOval(origin.getX() - (width / 2) ,origin.getY() - (width / 2),width,width);

        //filled Sword

        //blade
        //left
        g.fillPolygon(new int[]{pos4.getX(),pos1.getX(),target.getX()}, new int[]{pos4.getY(),pos1.getY(),target.getY()}, 3);
        g.fillPolygon(new int[]{pos4.getX(),target.getX(),vec7.getX()}, new int[]{pos4.getY(),target.getY(),vec7.getY()}, 3);
        //right
        g.fillPolygon(new int[]{vec7.getX(),target.getX(),pos3.getX()},new int[]{vec7.getY(),target.getY(),pos3.getY()},3);
        g.fillPolygon(new int[]{pos3.getX(),target.getX(),pos2.getX()},new int[]{pos3.getY(),target.getY(),pos2.getY()},3);
        //lazy guard


    }

    public void toggle() {
        if (test) {
            test = false;
        } else {
            test = true;
        }
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        if (test) {
            pos.setX(e.getX());
            pos.setY(e.getY());
        }
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        switch (type) {
            case Key_Pressed:
                switch (e.getKeyChar()) {
                    case 'p':
                        toggle();
                }
                break;
        }

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
