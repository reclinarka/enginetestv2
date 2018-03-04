package de.reclinarka.objects.gameObjects;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.coordinates.Position;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Chunk implements Drawable, Interactable {

    public Chunk(String ID,GameInstance instance, RectDimension dimension){
        this.instance = instance;
        this.dimension = dimension;
        this.ID = "_chunk_" + ID;
    }
    public Chunk(String ID,GameInstance instance, RectDimension dimension, ArrayList<Drawable> drawables){
        this.instance = instance;
        this.dimension = dimension;
        this.drawables = drawables;
        this.ID = ID;
    }

    private boolean loaded;
    private String ID;
    private GameInstance instance;
    private RectDimension dimension;
    private ArrayList<Drawable> drawables = new ArrayList<>();
    private ArrayList<Interactable> interactables = new ArrayList<>();

    public void deleteItem(String ID){
        Drawable drawable = getDrawable(ID);
        Interactable interactable = getInteractable(ID);
        if(drawable != null)
            drawables.remove(drawables.indexOf(drawable));
        if (interactable != null)
        interactables.remove(interactables.indexOf(interactable));
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public Drawable getDrawable(String ID){
        Drawable out = null;
        for(int i = 0; i < drawables.size()-1;i++){
            if(drawables.get(i).getID().contentEquals(ID)){
                out = drawables.get(i);
            }
        }
        return out;
    }

    public ArrayList<Interactable> getInteractables() {
        return interactables;
    }

    public Interactable getInteractable(String ID){
        Interactable out = null;
        for(int i = 0; i < interactables.size();i++) {
            if (interactables.get(i).getID().contentEquals(ID)) {
                out = interactables.get(i);
            }
        }

            return null;
    }

    public RectDimension getDimension() {
        return dimension;
    }

    @Override
    public void exec(Graphics L) {

        //Area arround Player will be loaded
        RectDimension window = instance.getViewWindow();
        Position pos = instance.getPlayer().getPosition();
        if(new RectDimension(window.getWidth(),window.getHeight(),new Coordinate(
                pos.getX() - (window.getWidth()/2),pos.getY()-(window.getHeight()/2))).intersectsDimension(dimension)) {
            drawables.forEach(f -> f.exec(L));
            loaded = true;
        } else {
            loaded = false;
        }
    }

    public void addDrawable(Drawable target){
        drawables.add(target);
    }

    public void addInteractable(Interactable target){
        interactables.add(target);
    }

    @Override public String getID() {
        return ID;
    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        if(loaded){
            interactables.forEach(f -> {
                try {
                    f.mouseEvent(e, type, ID);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
        }
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        if(loaded){
            interactables.forEach(f -> {
                try {
                    f.keyEvent(e, type, ID);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
        }
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        switch (command[2]){
            case "delete":
                deleteItem(command[3]);
                break;
        }
        if(loaded){
            interactables.forEach(f -> {
                try {
                    f.commandThrown(command,ID);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
        }
    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {
        interactables.forEach(f -> {
            try {
                f.setReciever(reciever,ID);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
    }
}
