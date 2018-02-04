package de.reclinarka.instances;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.Window;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Instance{

    public Instance(){

    }

    private InstanceManager parent;

    public void setParent(InstanceManager parent) {
        this.parent = parent;
    }

    public InstanceManager getParent() {
        return parent;
    }
}
