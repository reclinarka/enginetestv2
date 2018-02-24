package de.reclinarka.objects.framework.properties.size;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;

public interface Dimension {
    boolean containsPoint(Coordinate pos);
    boolean intersectsDimension(Dimension dimension);
    Coordinate getPos();
    int getWidth();
    int getHeight();
}
