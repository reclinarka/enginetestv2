package de.reclinarka.instances;

import de.reclinarka.graphics.frame.Window;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.objects.Writeable;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionListener;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.objects.interaction.Repeater;
import de.reclinarka.util.Count;
import de.reclinarka.util.InteractableCreator;
import de.reclinarka.util.WriterReader;

import java.io.IOException;
import java.util.ArrayList;

public class InstanceManager { //supposed to manage and select between different GUIs

    public InstanceManager() {
    }
    public InstanceManager(String ID,Window window,Slate slate) {
        this.ID = ID;
        this.window = window;
        this.slate = slate;
    }

    private Instance activeInstance;
    private String ID;
    private ArrayList<Instance> instances = new ArrayList<>();
    private Window window;
    private Slate slate;
    private InteractionListener interactionListener = new InteractionListener(ID + "_listener");
    private InteractionRegistry globalInteractions = new InteractionRegistry( ID + "_globalInteractions");
    private Repeater globalRepeater = new Repeater(globalInteractions, ID + "_repeater");

    public void addGlobalRegistry(Interactable interactable){
        globalInteractions.addRegistry(interactable);
    }

    public void addInstance(Instance instance){
        instance.getInteractionRegistry().addRegistry(globalRepeater);
        instances.add(instance);
        instance.setParent(this);
    }

    public void init(String ID){
        slate.addMouseListener(interactionListener);
        slate.addMouseMotionListener(interactionListener);
        slate.addMouseWheelListener(interactionListener);
        window.addKeyListener(interactionListener);
        Instance instance = getInstance(ID);
        if(instance == null)
            return;
        interactionListener.setRegistry(instance.getInteractionRegistry());
        slate.setContent(instance.getDrawableRegister());
        activeInstance = instance;
    }

    public void call(String ID){
        Instance instance = getInstance(ID);
        if(instance == null)
            return;
        interactionListener.setRegistry(instance.getInteractionRegistry());
        slate.setContent(instance.getDrawableRegister());
        activeInstance = instance;
    }

    public String getID() {
        return ID;
    }

    public Instance getActiveInstance() {
        return activeInstance;
    }

    public Slate getSlate() {
        return slate;
    }

    public Window getWindow() {
        return window;
    }

    private Instance getInstance(String ID){
        Instance instance = null;
        for(int i = 0; i < instances.size();i++){

            if(instances.get(i).getID().contentEquals(ID)) {
                instance = instances.get(i);
            }
        }
        return instance;
    }

    public void setActiveInstance(Instance activeInstance) {
        this.activeInstance = activeInstance;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setSlate(Slate slate) {
        this.slate = slate;
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setInstances(ArrayList<Instance> instances) {
        this.instances = instances;
    }

    public void setParents() {
        instances.forEach(f -> f.setParent(this));
    }

    public void load(String path) throws IOException {
        String subPath = path + "\\" + ID;
        String subsubPath = subPath + "\\content";
        instances.clear();
        ArrayList<String> manifest = (ArrayList<String>) WriterReader.load(new ArrayList<String>(), subPath);
        manifest.forEach(f -> {
            Instance instance = (Instance) WriterReader.load(new Instance(), subsubPath + "\\" + f);
            instance.setID(f);
            instances.add(instance);
        });
        instances.forEach(f -> {
            try {
                f.load(subsubPath + "\\" + f.getID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void load(String path, String ID) throws IOException {
        String subPath = path + "\\" + ID;
        String subsubPath = subPath + "\\content";
        setID(ID);
        instances.clear();
        ArrayList<String> manifest = (ArrayList<String>) WriterReader.load(new ArrayList<String>(), subPath);
        manifest.forEach(f -> {
            Instance instance = (Instance) WriterReader.load(new Instance(), subsubPath + "\\" + f);
            instance.setID(f);
            instances.add(instance);
        });
        instances.forEach(f -> {
            try {
                f.load(subsubPath + "\\" + f.getID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void save(String path) throws IOException {
        String subPath = path + "\\" + ID;
        String subsubPath = subPath + "\\content";

        ArrayList<String> manifestContent = new ArrayList<>();
        instances.forEach(f -> {
            manifestContent.add(f.getID() + "");
            try {
                f.save(subsubPath + "\\" + f.getID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        WriterReader.save(manifestContent, subPath + "\\manifest.json");
    }
}
