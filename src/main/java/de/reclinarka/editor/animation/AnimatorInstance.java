package de.reclinarka.editor.animation;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.controlElements.Textfield;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;



/**
 * TODO Splines
 * TODO Timeline
 * TODO Basic Tools
 *
 * **/
public class AnimatorInstance extends Instance implements Interactable {
    public AnimatorInstance(String ID, int height){
        super(ID,new DrawableRegister(ID + "_drawableRegister"),new InteractionRegistry(ID + "_interactionRegistry"));
        mainToolbar = new Toolbar(ID + "_toolbar", height);
        Textfield testTextfield = new Textfield(50,50,400,50, "textfield_test");
        getInteractionRegistry().addRegistry(mainToolbar);

        getInteractionRegistry().addRegistry(testTextfield);
        getDrawableRegister().addRegistry(testTextfield);
        mainToolbar.setReciever(getInteractionRegistry(),getID());
    }

    private Toolbar mainToolbar;
    private boolean debug = false;
    private boolean cineMode = false;

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
        if(!cineMode)
            mainToolbar.draw(g,currentFrame);

    }


    @Override
    public DrawableRegister getDrawableRegister() {
        return super.getDrawableRegister();
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

        if(!cineMode)
            getInteractionRegistry().mouseEvent(e,type,ID);

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        //toggle cineMode
        if(type == EventType.Key_Pressed && e.getKeyCode() == 145)
            cineMode = !cineMode;

        //when cineMode off passthrough to Toolbar
        if(!cineMode)
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
