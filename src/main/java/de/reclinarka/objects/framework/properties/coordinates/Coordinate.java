package de.reclinarka.objects.framework.properties.coordinates;

public class Coordinate {

    public Coordinate(){

    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
