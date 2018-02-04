package de.reclinarka.instances;

import de.reclinarka.objects.Writeable;
import de.reclinarka.util.InteractableCreator;
import de.reclinarka.util.WriterReader;

import java.io.IOException;
import java.util.ArrayList;

public class InstanceManager {
    public InstanceManager(){

    }
    private String ID;
    private ArrayList<Instance> instances = new ArrayList<>();

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setInstances(ArrayList<Instance> instances) {
        this.instances = instances;
    }

    public void setParents(){
        instances.forEach( f -> f.setParent(this));
    }

    public void load(String path) throws IOException {
        String subPath = path + "\\" + ID;
        setID(ID);
        this.instances = (ArrayList<Instance>) WriterReader.load(new ArrayList<Instance>(),subPath + "\\instances.json");
    }

    public void load(String path, String ID) throws IOException {
        String subPath = path + "\\" + ID;
        setID(ID);
        this.instances = (ArrayList<Instance>) WriterReader.load(new ArrayList<Instance>(),subPath + "\\instances.json");
    }

    public void save(String path) throws IOException {
        WriterReader.save(instances,path + "\\" + ID);
    }
}
