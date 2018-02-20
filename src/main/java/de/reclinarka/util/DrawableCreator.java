package de.reclinarka.util;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.testing.Test;

import java.util.ArrayList;

public class DrawableCreator { //builder for loading an ArrayLists for the Interface Drawable
    private ArrayList<Drawable> builder = new ArrayList<>();
    public void add(String path, String classy){
        switch (classy){
            case "class de.reclinarka.objects.testing.Test":
                builder.add((Test) WriterReader.load(new Test(),path));
                break;
        }
    }

    public ArrayList<Drawable> getBuilder() {
        return builder;
    }
}
