package de.reclinarka.graphics.registers;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.util.WriterReader;

import java.util.ArrayList;

public class Register<RegisterElement> {


    private String ID;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    private ArrayList<RegisterElement> register = new ArrayList<RegisterElement>();

    public ArrayList<RegisterElement> getRegister() {
        return register;
    }

    public void setRegister(ArrayList<RegisterElement> register) {
        this.register = register;
    }

    public void addRegistry(RegisterElement e){
        register.add(e);
    }





}
