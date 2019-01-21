package de.reclinarka.editor.animation;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AnimatorInstance extends Instance implements Interactable {
    public AnimatorInstance(String ID){
        super(ID,new DrawableRegister(ID + "_drawableRegister"),new InteractionRegistry(ID + "_interactionRegistry"));
        mainToolbar = new Toolbar(ID + "_toolbar");
        getInteractionRegistry().addRegistry(mainToolbar);
    }

    private Toolbar mainToolbar;
    boolean debug = false;

    public Toolbar getMainToolbar() {
        return mainToolbar;
    }

    public void setMainToolbar(Toolbar mainToolbar) {
        this.mainToolbar = mainToolbar;
    }

    public void draw(Graphics g, BufferedImage currentFrame){
        if(!debug) {
            System.out.println("reached");
            debug = !debug;
        }

        getDrawableRegister().draw(g);
        mainToolbar.draw(g,currentFrame);

    }


    @Override
    public DrawableRegister getDrawableRegister() {
        return super.getDrawableRegister();
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        getInteractionRegistry().mouseEvent(e,type,ID);

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        getInteractionRegistry().keyEvent(e,type,ID);
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        if(command[2].contentEquals("delete")){
            getDrawableRegister().delete(command[3]);
            getInteractionRegistry().delete(command[3]);
        }
        getInteractionRegistry().commandThrown(command,ID);
    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {

    }
}
