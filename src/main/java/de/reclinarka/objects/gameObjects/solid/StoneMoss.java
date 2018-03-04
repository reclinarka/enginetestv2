package de.reclinarka.objects.gameObjects.solid;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;

public class StoneMoss extends RectSolid {
    public StoneMoss(String ID, Coordinate pos) {
        super(ID,new RectDimension(100,100,pos), "\\testSolid2.png");
    }



}
