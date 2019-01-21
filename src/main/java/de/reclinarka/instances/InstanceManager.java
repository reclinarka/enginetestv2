package de.reclinarka.instances;

import de.reclinarka.editor.animation.AnimatorInstance;
import de.reclinarka.graphics.frame.Window;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionListener;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.objects.interaction.Repeater;
import de.reclinarka.util.WriterReader;

import java.io.IOException;
import java.util.ArrayList;

import static de.reclinarka.instances.InstanceMode.*;

public class InstanceManager { //supposed to manage and select between different GUIs and Scenes

    public InstanceManager() {
    }

    public InstanceManager(String ID, Window window, Slate slate) {
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
    private InteractionRegistry globalInteractions = new InteractionRegistry(ID + "_globalInteractions");
    private Repeater globalRepeater = new Repeater(globalInteractions, ID + "_repeater");

    public void addGlobalRegistry(Interactable interactable) {
        globalInteractions.addRegistry(interactable);
    }

    public void deleteGlobalItem(String ID) {
            globalInteractions.delete(ID);
    }

    public void deleteInstance(String ID) {
        ArrayList<Instance> content = instances;
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getID().contentEquals(ID)) {
                content.remove(i);
            }
        }

    }

    public ArrayList<Instance> getInstances() {
        return instances;
    }

    public InteractionRegistry getGlobalInteractions() {
        return globalInteractions;
    }

    public void addInstance(Instance instance) {
        instance.getInteractionRegistry().addRegistry(globalRepeater);
        instances.add(instance);
        instance.setParent(this);
    }

    public void init(String ID) {
        slate.addMouseListener(interactionListener);
        slate.addMouseMotionListener(interactionListener);
        slate.addMouseWheelListener(interactionListener);
        window.addKeyListener(interactionListener);
        call(ID);
    }

    public void call(String ID) {
        Instance instance = getInstance(ID);
        if (instance == null)
            return;
        activeInstance = instance;

        if (("" + instance.getClass()).contentEquals("class de.reclinarka.objects.gameObjects.GameInstance")) {
            slate.setGameInstance((GameInstance) instance);
            slate.setMode(GAME_MODE);
            interactionListener.setGameInstance((GameInstance) instance);
            interactionListener.setMode(GAME_MODE);
            return;
        } else if (("" + instance.getClass()).contentEquals("class de.reclinarka.editor.animation.AnimatorInstance")) {
            slate.setAnimatorInstance((AnimatorInstance) instance);
            slate.setMode(ANIMATOR_MODE);
            interactionListener.setAnimatorInstance((AnimatorInstance) instance);
            interactionListener.setMode(ANIMATOR_MODE);
            return;
        } else {
            interactionListener.setMode(DEFAULT_MODE);
            slate.setMode(DEFAULT_MODE);
            interactionListener.setRegistry(instance.getInteractionRegistry());
        }
        slate.setContent(instance.getDrawableRegister());


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

    public InteractionListener getInteractionListener() {
        return interactionListener;
    }

    public Repeater getGlobalRepeater() {
        return globalRepeater;
    }

    private Instance getInstance(String ID) {
        Instance instance = null;
        for (int i = 0; i < instances.size(); i++) {

            if (instances.get(i).getID().contentEquals(ID)) {
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
