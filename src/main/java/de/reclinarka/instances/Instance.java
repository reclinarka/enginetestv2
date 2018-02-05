package de.reclinarka.instances;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.Window;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.objects.Writeable;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Instance{

    public Instance(){

    }

    private String ID;
    private DrawableRegister drawableRegister;
    private InteractionRegistry interactionRegistry;
    private InstanceManager parent;

    public DrawableRegister getDrawableRegister() {
        return drawableRegister;
    }

    public InteractionRegistry getInteractionRegistry() {
        return interactionRegistry;
    }

    public void setParent(InstanceManager parent) {
        this.parent = parent;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public InstanceManager getParent() {
        return parent;
    }

    public void load(String path) throws IOException {

    }

    public void save(String path) throws IOException {

    }
}
