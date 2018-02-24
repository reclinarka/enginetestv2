package de.reclinarka.objects.gameObjects.graphics;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
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

    public Chunk(GameInstance instance, RectDimension dimension){
        this.instance = instance;
        this.dimension = dimension;

    }
    public Chunk(GameInstance instance, RectDimension dimension, ArrayList<Drawable> drawables){
        this.instance = instance;
        this.dimension = dimension;
        this.drawables = drawables;
    }

    private GameInstance instance;
    private RectDimension dimension;
    private ArrayList<Drawable> drawables = new ArrayList<>();

    @Override
    public void exec(Graphics L) {
        if(instance.getViewWindow().intersectsDimension(dimension))
            drawables.forEach(f -> f.exec(L));
    }

    public void addDrawable(Drawable target){
        drawables.add(target);
    }

    @Override public String getID() {
        return null;
    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        return;
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        return;
    }

    @Override
    public void commandThrown(String[] command, String ID) {

    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {
        return;
    }
}
