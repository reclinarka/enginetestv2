package de.reclinarka.graphics.drawing;

import de.reclinarka.graphics.registers.Register;
import de.reclinarka.util.Count;
import de.reclinarka.util.DrawableCreator;
import de.reclinarka.util.WriterReader;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawableRegister extends Register<Drawable> {
    public DrawableRegister(String ID){
        setID(ID);
    }
    public void draw(Graphics g){
        getRegister().forEach(f -> f.exec(g));
    }
    public void delete(Drawable drawable){
        ArrayList<Drawable> content = getRegister();
        for(int i = 0; i < content.size(); i++){
            if(content.get(i).getID().contentEquals(drawable.getID())){
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
        DrawableCreator drawableCreator = new DrawableCreator();
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
