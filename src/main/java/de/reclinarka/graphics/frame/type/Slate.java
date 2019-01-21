package de.reclinarka.graphics.frame.type;

import de.reclinarka.editor.animation.AnimatorInstance;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.filter.*;
import de.reclinarka.instances.Instance;
import de.reclinarka.instances.InstanceMode;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.util.ColorStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

import static de.reclinarka.instances.InstanceMode.*;

/**
 * Basic Canvas for displaying frames which are created through BufferedImages of which the Graphics Object is
 * created and passed to the instances to actually draw stuff. While for Normal instances the DrawableRegister
 * is called directly GameInstances are being drawn through the call of the draw(g) method in the Instance.
 *
 * The InstanceManager has direct Control over the Slate and sets the DrawableRegister as well as the GameInstance
 * in case of running in the gameMode.
 *
 * There is also the possibility for applying filters on the frames before painting them on the canvas.
 *
 * **/
public class Slate extends JPanel { //content for the Window
    public Slate(){ // Debugging for Slate Object
        //filters.add(new ColorFilter("testFilter",new Color(255,165,0,100)));
        //filters.add(new DistortFilter("testFilter",0.3,5,1));
        //filters.add(new BlurrFilter("testFilter",1));
        //filters.add(new GreyscaleFilter("greyScale",16));
        //filters.add(new SepiaFilter("sepia",255,10,30));
        //filters.add(new CRTFilter("crt",0,2));
    }
    public Slate(DrawableRegister content){
        this.content = content;
    }

    public void setOriginalHeight(int originalHeight) {
        this.originalHeight = originalHeight;
    }

    public void setOriginalWidth(int originalWidth) {
        this.originalWidth = originalWidth;
    }

    public double getAspectRatio(){
        return ((double) originalWidth) / ((double) originalHeight);
    }

    private int originalWidth = 1920;
    private int originalHeight = 1080;
    private InstanceMode mode = DEFAULT_MODE;
    private GameInstance gameInstance;
    private AnimatorInstance animatorInstance;
    private DrawableRegister content;
    private ArrayList<Filter> filters = new ArrayList<>();
    private BufferedImage lastGamemodeFrame;


    public int getWidthDifference(){
        return (getWidth() - originalWidth) / 2;
    }

    public int getHeightDifference(){
        return (getWidth() - originalWidth) / 2;
    }

    public double getXModifier(int x) {
        return ((double) x) / ( (double) getWidth());
    }

    public double getYModifier(int y) {
        return ((double) y) / ((double) getHeight());
    }

    public int getOriginalHeight() {
        return originalHeight;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public void setMode(InstanceMode mode) {
        this.mode = mode;
    }

    public void setAnimatorInstance(AnimatorInstance animatorInstance) {
        this.animatorInstance = animatorInstance;
    }

    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void setContent(DrawableRegister content) {
        this.content = content;
    }
    private void draw(Graphics g, BufferedImage currentFrame){
        g.setColor(ColorStorage.defaultBackGround);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRect(-3,-3,originalWidth + 6, originalHeight + 6);
        try {
            switch(mode){
                case GAME_MODE:
                    gameInstance.draw(g);
                    break;
                case ANIMATOR_MODE:
                    animatorInstance.draw(g,currentFrame);
                    break;
                case DEFAULT_MODE:
                    content.draw(g);
                    break;
            }
        } catch ( NullPointerException e){}
        g.dispose();
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

    public static BufferedImage resize(BufferedImage originalImage, int newW, int newH) {
        BufferedImage resizedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newW, newH, null);
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.dispose();
        return resizedImage;
    }

    protected void paintComponent(Graphics g){

        BufferedImage img = new BufferedImage(originalWidth,originalHeight,BufferedImage.TYPE_INT_RGB);
        draw(img.getGraphics(),img);
        lastGamemodeFrame = img;

        //g.drawImage( resize(applyFilters(resize(img,nativeWidth,(int)(nativeWidth / getAspectRatio()))),getWidth(),getHeight()),0 ,0 ,getWidth(),getHeight(),null);
        g.drawImage( resize(applyFilters(img),getWidth(),getHeight()),0 ,0 ,getWidth(),getHeight(),null);
    }
}
