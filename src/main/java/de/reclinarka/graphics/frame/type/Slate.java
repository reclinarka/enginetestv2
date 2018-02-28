package de.reclinarka.graphics.frame.type;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.filter.BlurrFilter;
import de.reclinarka.graphics.filter.ColorFilter;
import de.reclinarka.graphics.filter.DistortFilter;
import de.reclinarka.graphics.filter.Filter;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.util.ColorStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Slate extends JPanel { //content for the Window
    public Slate(){
        //filters.add(new ColorFilter("testFilter",new Color(255,191,255,20)));
        filters.add(new DistortFilter("testFilter",0.1));
        //filters.add(new BlurrFilter("testFilter",10));
    }
    public Slate(DrawableRegister content){
        this.content = content;
    }

    private boolean gameMode;
    private GameInstance instance;
    private DrawableRegister content;
    private ArrayList<Filter> filters = new ArrayList<>();
    private BufferedImage lastGamemodeFrame;

    public void toggleGameMode(){
        if(gameMode){
            gameMode = false;
        } else {
            gameMode = true;
        }
    }

    public void setGameMode(boolean gameMode) {
        this.gameMode = gameMode;
    }

    public void setInstance(GameInstance instance) {
        this.instance = instance;
    }

    public void setContent(DrawableRegister content) {
        this.content = content;
    }
    private void draw(Graphics g){
        g.setColor(ColorStorage.defaultBackGround);
        g.fillRect(-3,-3,this.getWidth() + 6, this.getHeight() + 6);
        try {
            if(gameMode){
                instance.draw(g);
            } else {
                content.draw(g);
            }
        } catch ( NullPointerException e){}
    }
    private BufferedImage applyFilters(BufferedImage img){
        for(int i = 0; i < filters.size(); i++){
            img = filters.get(i).applyFilter(img);
        }
        return img;
    }
    public void deleteFilter(String ID){
        for(int i = 0; i < filters.size(); i++){
            if(filters.get(i).getID().contentEquals(ID)){
                filters.remove(i);
            }
        }
    }
    public void addFilter(Filter filter){
        filters.add(filter);
    }

    protected void paintComponent(Graphics g){
        BufferedImage img = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        draw(img.getGraphics());
        if(gameMode){
            lastGamemodeFrame = img;
        }
        g.drawImage(applyFilters(img),0,0,null);
    }
}
