package de.reclinarka.util;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.Test;
import de.reclinarka.objects.interaction.Interactable;

import java.util.ArrayList;

public class InteractableCreator { //builder for loading an ArrayLists for the Interface Interactable
    private ArrayList<Interactable> builder = new ArrayList<>();
    public void add(String path, String classy){
        switch (classy){
            case "class de.reclinarka.objects.Test":
                builder.add((Test) WriterReader.load(new Test(),path));
                break;
        }
    }

    public ArrayList<Interactable> getBuilder() {
        return builder;
    }
}
