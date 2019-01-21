package de.reclinarka.objects.framework.properties.coordinates;

import de.reclinarka.objects.framework.properties.size.RectDimension;

public class Position extends Coordinate{
    public Position(RectDimension dimension) {
        this.dimension = dimension;
    }

    private RectDimension dimension;

    @Override
    public int getX(){
        return (dimension.getPos().getX()+(dimension.getWidth() / 2));
    }

    @Override
    public int getY(){
        return (dimension.getPos().getY() + (dimension.getHeight() / 2));
    }

    @Override
    public void setX(int x) {
        dimension.getPos().setX(x - (dimension.getWidth() / 2));
    }

    @Override
    public void setY(int y) {
        dimension.getPos().setY(y - (dimension.getHeight() / 2));
    }

    public RectDimension getDimension() {
        return dimension;
    }

    public int getWidth(){
        return dimension.getWidth();
    }
    public int getHeight(){
        return dimension.getHeight();
    }

}
