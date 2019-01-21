package de.reclinarka.objects.framework.properties.size;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
//Dimension for Rectangles
public class RectDimension implements Dimension{
    public RectDimension(int width, int height,Coordinate pos){
        this.width = width;
        this.height = height;
        this.pos = pos;
    }
    private Coordinate pos;
    private int width;
    private int height;

    //See Interface Documentation for Override details

    @Override
    public Coordinate getPos() {
        return pos;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean containsPoint(Coordinate obj){
        if(obj.getX() >= pos.getX() && obj.getX() <= pos.getX() + width){
            if(obj.getY() >= pos.getY() && obj.getY() <= pos.getY() + height){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersectsDimension(Dimension dimension) {
        switch (dimension.getClass() + ""){
            case "class de.reclinarka.objects.framework.properties.size.RectDimension":
                RectDimension newDimension = (RectDimension) dimension;
                if   (  (  pos.getX()  <  (newDimension.getPos().getX() + width)  )  &&
                        (   (pos.getX()+width)   >  newDimension.getPos().getX()  )  &&
                        (  pos.getY()  <  (newDimension.getPos().getY() + height)  )  &&
                        (  (pos.getY() + height)  >  newDimension.getPos().getY()  )  ) {
                    return true;
                }
                break;
        }
        return false;
    }
}
