package de.reclinarka.objects.interaction;

import java.awt.event.*;
import java.util.ConcurrentModificationException;

public class InteractionListener implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener { //Listener for mouse and keyboard

    public InteractionListener(InteractionRegistry interactionRegistry,String ID){
        registry = interactionRegistry;
        this.ID = ID;
    }
    public InteractionListener(String ID){
        this.ID = ID;
    }

    private InteractionRegistry registry;
    private String ID;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setRegistry(InteractionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        registry.keyEvent(e,EventType.Key_Typed,ID);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        registry.keyEvent(e,EventType.Key_Pressed,ID);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        registry.keyEvent(e,EventType.Key_Released,ID);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            registry.mouseEvent(e,EventType.Mouse_Clicked,ID);
        } catch (ConcurrentModificationException ex) {}
        System.out.println("x_"+ e.getX() + "; y_" + e.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        registry.mouseEvent(e,EventType.Mouse_Pressed,ID);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        registry.mouseEvent(e,EventType.Mouse_Released,ID);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        registry.mouseEvent(e,EventType.Mouse_Dragged,ID);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        registry.mouseEvent(e,EventType.Mouse_Moved,ID);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getPreciseWheelRotation() > 0){
            registry.mouseEvent(e,EventType.Wheel_Down,ID);
        } else {
            registry.mouseEvent(e,EventType.Wheel_Up,ID);
        }
        System.out.println(e.getPreciseWheelRotation());
    }
}