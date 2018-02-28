package de.reclinarka.instances;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.io.IOException;

public class Instance{

    public Instance(){}

    public Instance(String ID, DrawableRegister register, InteractionRegistry registry){
        this.ID = ID;
        this.drawableRegister = register;
        this.interactionRegistry = registry;
    }

    private String ID;
    private DrawableRegister drawableRegister;
    private InteractionRegistry interactionRegistry;
    private InstanceManager parent;

    public void setDrawableRegister(DrawableRegister drawableRegister) {
        this.drawableRegister = drawableRegister;
    }

    public void setInteractionRegistry(InteractionRegistry interactionRegistry) {
        this.interactionRegistry = interactionRegistry;
    }

    public void addItem(Drawable drawable, Interactable interactable){
        if(drawable != null){
            drawableRegister.addRegistry(drawable);
        }
        if(interactable != null){
            interactionRegistry.addRegistry(interactable);
        }
    }

    public void addGlobalItem(Drawable drawable, Interactable interactable){
        addItem(drawable,null);
        parent.addGlobalRegistry(interactable);

    }

    public void deleteItem(Drawable drawable,Interactable interactable){
        if(interactable != null)
        getInteractionRegistry().delete(interactable);
        if(drawable != null)
        getDrawableRegister().delete(drawable);
    }

    public void deleteGlobalItem(Drawable drawable,Interactable interactable){
        deleteItem(drawable,null);
        parent.deleteGlobalItem(interactable);
    }

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
