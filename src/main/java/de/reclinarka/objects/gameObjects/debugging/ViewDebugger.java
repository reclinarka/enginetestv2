package de.reclinarka.objects.gameObjects.debugging;

import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ViewDebugger implements Interactable {

    public ViewDebugger(GameInstance instance) {
        this.instance = instance;
        this.slate = instance.getParent().getSlate();
    }

    private GameInstance instance;
    private int incrementSize = 5;
    private Slate slate;
    private String ID;

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        double ratio;
        switch (e.getKeyCode()){
            case 37:
                instance.getViewWindow().getPos().setX(instance.getViewWindow().getPos().getX() + 10);
                break;
            case 38:
                instance.getViewWindow().getPos().setY(instance.getViewWindow().getPos().getY() + 10);
                break;
            case 39:
                instance.getViewWindow().getPos().setX(instance.getViewWindow().getPos().getX() - 10);
                break;
            case 40:
                instance.getViewWindow().getPos().setY(instance.getViewWindow().getPos().getY() - 10);
                break;
            case 33:
                ratio = slate.getAspectRatio();
                slate.setOriginalHeight( slate.getOriginalHeight() + incrementSize );
                slate.setOriginalWidth(  (int) (slate.getOriginalHeight() * ratio)   );
                break;
            case 34:
                ratio = slate.getAspectRatio();
                slate.setOriginalHeight( slate.getOriginalHeight() - incrementSize );
                slate.setOriginalWidth(  (int) (slate.getOriginalHeight() * ratio)   );
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
