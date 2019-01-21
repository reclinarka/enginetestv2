package de.reclinarka.objects.util;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;

public class Vector {
    public Vector(double x, double y) {
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

    public static Vector getVector(Coordinate a, Coordinate b) {
        return new Vector(b.getX() - a.getX(), b.getY() - a.getY());
    }

    public double getAngle(Vector vector) {
        if (vector.getX() < 0) {
            return 0-Math.acos(getSkalarProduct(vector) / (getLength() * vector.getLength()));
        } else {
            return Math.acos(getSkalarProduct(vector) / (getLength() * vector.getLength()));
        }
    }

    public Vector rotate(double degree) {
        degree = Math.toRadians(degree);
        return new Vector((getX() * Math.cos(degree)) - (getY() * Math.sin(degree)), (getX() * Math.sin(degree)) + (getY() * Math.cos(degree)));
    }

    public double getSkalarProduct(Vector vector) {
        return (getX() * vector.getX()) + (getY() * vector.getY());
    }

    public Coordinate getPosRelativeTo(Coordinate in) {
        return new Coordinate(Math.round((float) (in.getX() + getX())), Math.round((float) (in.getY() + getY())));
    }

    public double getLength() {
        return Math.sqrt((Math.pow(getX(), 2) + Math.pow(getY(), 2)));
    }

    public Vector devide(double devide) {
        return new Vector(getX() / devide, getY() / devide);
    }

    public Vector multiply(double multiply) {
        return new Vector(getX() * multiply, getY() * multiply);
    }

    public Vector add(Vector add) {
        return new Vector(getX() + add.getX(), getY() + add.getY());
    }

    public static Vector getNormalVector() {
        return new Vector(0, 1);
    }

    public Coordinate getCoordinate(){
        return new Coordinate(Math.round((float) x),Math.round((float) y));
    }

}
