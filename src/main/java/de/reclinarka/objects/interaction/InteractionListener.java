package de.reclinarka.objects.interaction;

import de.reclinarka.Main;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.gameObjects.GameInstance;

import java.awt.event.*;
import java.util.ConcurrentModificationException;

public class InteractionListener implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener { //Listener for mouse and keyboard

    public InteractionListener(InteractionRegistry interactionRegistry, String ID) {
        registry = interactionRegistry;
        this.ID = ID;
    }

    public InteractionListener(String ID) {
        this.ID = ID;
    }

    private boolean gameMode;
    private GameInstance instance;
    private InteractionRegistry registry;
    private String ID;

    public void setGameMode(boolean gameMode) {
        this.gameMode = gameMode;
    }

    public void setInstance(GameInstance instance) {
        this.instance = instance;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setRegistry(InteractionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (gameMode) {
            instance.keyEvent(e,EventType.Key_Typed,ID);
        } else {
            registry.keyEvent(e, EventType.Key_Typed, ID);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (gameMode) {
            instance.keyEvent(e,EventType.Key_Pressed,ID);
        } else {
            registry.keyEvent(e, EventType.Key_Pressed, ID);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameMode) {
            instance.keyEvent(e,EventType.Key_Released,ID);
        } else {
            registry.keyEvent(e, EventType.Key_Released, ID);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameMode) {
            instance.mouseEvent(e,EventType.Mouse_Clicked,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Clicked, ID);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("x_" + e.getX() + "; y_" + e.getY());
        if (gameMode) {
            instance.mouseEvent(e,EventType.Mouse_Pressed,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Pressed, ID);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (gameMode) {
            instance.mouseEvent(e,EventType.Mouse_Released,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Released, ID);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (gameMode) {
            instance.mouseEvent(e,EventType.Mouse_Dragged,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Dragged, ID);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameMode) {
            instance.mouseEvent(e,EventType.Mouse_Moved,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Moved, ID);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (gameMode) {
            if (e.getPreciseWheelRotation() > 0) {
                instance.mouseEvent(e, EventType.Wheel_Down, ID);
            } else {
                instance.mouseEvent(e, EventType.Wheel_Up, ID);
            }
        } else {
            if (e.getPreciseWheelRotation() > 0) {
                registry.mouseEvent(e, EventType.Wheel_Down, ID);
            } else {
                registry.mouseEvent(e, EventType.Wheel_Up, ID);
            }
        }

        System.out.println(e.getPreciseWheelRotation());
    }
}