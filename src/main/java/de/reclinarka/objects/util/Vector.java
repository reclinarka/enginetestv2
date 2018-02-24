package de.reclinarka.objects.util;

public class Vector {
    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }


}
