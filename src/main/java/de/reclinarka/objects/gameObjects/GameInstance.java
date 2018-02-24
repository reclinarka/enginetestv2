package de.reclinarka.objects.gameObjects;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.util.ArrayList;

public class GameInstance extends Instance {
    public GameInstance() {
    }

    public GameInstance(String ID, DrawableRegister register, InteractionRegistry registry) {
        super(ID, register, registry);
    }

    private RectDimension loadedArea;
    private RectDimension viewWindow;
    private ArrayList<DrawableRegister> layers;

    public RectDimension getViewWindow() {
        return viewWindow;
    }



    @Override
    public DrawableRegister getDrawableRegister() {
        return super.getDrawableRegister();
    }
}
