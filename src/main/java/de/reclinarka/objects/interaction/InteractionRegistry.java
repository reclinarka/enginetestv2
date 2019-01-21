package de.reclinarka.objects.interaction;

import com.sun.istack.internal.Nullable;
import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.graphics.registers.Register;
import de.reclinarka.util.Count;
import de.reclinarka.util.DrawableCreator;
import de.reclinarka.util.InteractableCreator;
import de.reclinarka.util.WriterReader;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class InteractionRegistry extends Register<Interactable> implements Interactable{ //interface for communication

    public InteractionRegistry(String ID){
        setID(ID);
    }



    @Override
    public void mouseEvent(MouseEvent e, EventType type,String ID) {
        getRegister().forEach(f -> {
            try {
                f.mouseEvent(e, type, ID);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type,String ID) {
        getRegister().forEach(f -> {
            try {
                f.keyEvent(e, type, ID);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
    }

    @Override
    public void setReciever( @Nullable InteractionRegistry interactionRegistry,@Nullable String ID) {
        if(interactionRegistry == null)
            getRegister().forEach(f -> f.setReciever(this,null));
        else
            getRegister().forEach( f -> f.setReciever(interactionRegistry,ID));
    }

    @Override
    public void commandThrown(String[] command,String ID) {
        switch (command[2]){
            case "delete":
                delete(command[3]);
                break;
        }
        new Thread(() -> getRegister().forEach(f -> {
            try {
                f.commandThrown(command,ID);
            } catch (ConcurrentModificationException e ){}
        })).start();

    }


    @Override
    public void addRegistry(Interactable e) {
        super.addRegistry(e);
        e.setReciever(this,null);
    }

    public void delete(String ID){
        ArrayList<Interactable> content = getRegister();
        for(int i = 0; i < content.size(); i++){
            if(content.get(i).getID().contentEquals(ID)){
                content.remove(i);
            }
        }
    }
    public void save(String path){
        String subPath = path + "\\" + getID();
        String subsubPath = subPath + "\\content";
        new File(subsubPath).mkdirs();
        ArrayList<String> manifestContent = new ArrayList<>();
        Count count = new Count();
        getRegister().forEach( f -> {
            manifestContent.add(count.i + "::" + f.getClass());
            try {
                f.save(subsubPath + "\\" + count.i +".json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            count.add();
        });
        WriterReader.save(manifestContent,subPath + "\\manifest.json");

    }
    public void load(String path, String ID) {
        String subPath = path + "\\" + ID;
        String subsubPath = subPath + "\\content";
        setID(ID);
        InteractableCreator drawableCreator = new InteractableCreator();
        ArrayList<String> manifest = (ArrayList<String>) WriterReader.load(new ArrayList<String>(),subPath + "\\manifest.json");
        String selector;
        while (manifest.size() > 0){
            selector = manifest.get(manifest.size() - 1);
            drawableCreator.add(subsubPath + "\\" + grabCount(selector) + ".json", grabClass(selector));
            manifest.remove(manifest.size()-1);
        }
        setRegister(drawableCreator.getBuilder());
    }
    public void load(String path) {
        String subPath = path + "\\" + getID();
        String subsubPath = subPath + "\\content";
        InteractableCreator drawableCreator = new InteractableCreator();
        ArrayList<String> manifest = (ArrayList<String>) WriterReader.load(new ArrayList<String>(),subPath + "\\manifest.json");
        String selector;
        while (manifest.size() > 0){
            selector = manifest.get(manifest.size() - 1);
            drawableCreator.add(subsubPath + "\\" + grabCount(selector) + ".json", grabClass(selector));
            manifest.remove(manifest.size()-1);
        }
        setRegister(drawableCreator.getBuilder());
    }
    private static String grabCount(String string){
        for (int i = 0;i < string.length();i++){
            if(string.charAt(i) == ':'){
                return string.substring(0,i);
            }
        }
        return null;
    }
    private static String grabClass(String string){
        for (int i = 0;i < string.length();i++){
            if(string.charAt(i) == ':'){
                return string.substring(i+2);
            }
        }
        return null;
    }

}
