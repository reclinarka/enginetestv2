package de.reclinarka.objects.framework.properties.size;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
//Interface for Entity Dimensions
public interface Dimension {
    //method for checking intersections between input pos and this Dimension
    boolean containsPoint(Coordinate pos);
    //method for checking intersections between input Dimension and this Dimension
    boolean intersectsDimension(Dimension dimension);
    //Position
    Coordinate getPos();
    //self-explanatory
    int getWidth();
    int getHeight();
}
