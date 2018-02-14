package de.reclinarka.instances.physical;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.util.ArrayList;

public class GameInstance extends Instance {
    public GameInstance() {
    }

    public GameInstance(String ID, DrawableRegister register, InteractionRegistry registry) {
        super(ID, register, registry);
    }

    private Coordinate viewPos = new Coordinate(0,0);
    private ArrayList<DrawableRegister> layers;
}
