package de.reclinarka.objects.interaction;

import de.reclinarka.editor.animation.AnimatorInstance;
import de.reclinarka.instances.InstanceMode;
import de.reclinarka.objects.gameObjects.GameInstance;

import java.awt.event.*;

import static de.reclinarka.instances.InstanceMode.*;

public class InteractionListener implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener { //Listener for mouse and keyboard

    public InteractionListener(InteractionRegistry interactionRegistry, String ID) {
        registry = interactionRegistry;
        this.ID = ID;
    }

    public InteractionListener(String ID) {
        this.ID = ID;
    }

    private InstanceMode mode = DEFAULT_MODE;
    private AnimatorInstance animatorInstance;
    private GameInstance gameInstance;
    private InteractionRegistry registry;
    private String ID;

    public void setMode(InstanceMode mode) {
        this.mode = mode;
    }

    public void setAnimatorInstance(AnimatorInstance animatorInstance) {
        this.animatorInstance = animatorInstance;
    }

    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setRegistry(InteractionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (mode == GAME_MODE) {
            gameInstance.keyEvent(e,EventType.Key_Typed,ID);
        } else if (mode == ANIMATOR_MODE) {
            animatorInstance.keyEvent(e,EventType.Key_Typed,ID);
        } else {
            registry.keyEvent(e, EventType.Key_Typed, ID);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (mode == GAME_MODE) {
            gameInstance.keyEvent(e,EventType.Key_Pressed,ID);
        } else if (mode == ANIMATOR_MODE) {
            animatorInstance.keyEvent(e,EventType.Key_Pressed,ID);
        } else {
            registry.keyEvent(e, EventType.Key_Pressed, ID);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (mode == GAME_MODE) {
            gameInstance.keyEvent(e,EventType.Key_Released,ID);
        } else if (mode == ANIMATOR_MODE) {
            animatorInstance.keyEvent(e,EventType.Key_Released,ID);
        } else {
            registry.keyEvent(e, EventType.Key_Released, ID);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }


    public boolean isCooldown() {
        return cooldown;
    }

    private boolean cooldown = false;

    private MouseEvent old;

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("x_" + e.getX() + "; y_" + e.getY());
        cooldown = true;
        if (mode == GAME_MODE) {
            gameInstance.mouseEvent(e, EventType.Mouse_Pressed,ID);
            old = e;
        } else if (mode == ANIMATOR_MODE) {
            animatorInstance.mouseEvent(e, EventType.Mouse_Pressed,ID);
            old = e;
        } else {
            registry.mouseEvent(e, EventType.Mouse_Pressed, ID);
            old = e;
        }
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            cooldown = false;
        }).start();


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(cooldown);
        if(cooldown) {
            cooldown = false;
            if (mode == GAME_MODE) {
                gameInstance.mouseEvent(old, EventType.Mouse_Clicked, ID);
            } else {
                registry.mouseEvent(old, EventType.Mouse_Clicked, ID);
            }
        }
        if (mode == GAME_MODE) {
            gameInstance.mouseEvent(e,EventType.Mouse_Released,ID);
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
        if (mode == GAME_MODE) {
            gameInstance.mouseEvent(e,EventType.Mouse_Dragged,ID);
        } else if (mode == ANIMATOR_MODE) {
            animatorInstance.mouseEvent(e,EventType.Mouse_Dragged,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Dragged, ID);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mode == GAME_MODE) {
            gameInstance.mouseEvent(e,EventType.Mouse_Moved,ID);
        } else if (mode == ANIMATOR_MODE) {
            animatorInstance.mouseEvent(e,EventType.Mouse_Moved,ID);
        } else {
            registry.mouseEvent(e, EventType.Mouse_Moved, ID);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (mode == GAME_MODE) {
            if (e.getPreciseWheelRotation() > 0) {
                gameInstance.mouseEvent(e, EventType.Wheel_Down, ID);
            } else  {
                gameInstance.mouseEvent(e, EventType.Wheel_Up, ID);
            }
        } else if (mode == ANIMATOR_MODE) {
            if (e.getPreciseWheelRotation() > 0) {
                animatorInstance.mouseEvent(e, EventType.Wheel_Down, ID);
            } else  {
                animatorInstance.mouseEvent(e, EventType.Wheel_Up, ID);
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