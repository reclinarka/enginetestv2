package de.reclinarka.objects.gameObjects;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.graphics.drawing.DrawableRegister;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Splitter implements Drawable {
    ArrayList<DrawableRegister> targets = new ArrayList<>();
    @Override
    public void exec(Graphics L) {
        targets.forEach(f -> f.draw(L));
    }

    public void addTarget(DrawableRegister target){
        targets.add(target);
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
}
