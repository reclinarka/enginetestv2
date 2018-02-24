package de.reclinarka.objects.gameObjects.solid;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;

public class StonePlain extends RectSolid {
    public StonePlain(String ID, Coordinate pos) {
        super(ID,new RectDimension(50,50,pos), "\\StonePlain.png");
    }
}
