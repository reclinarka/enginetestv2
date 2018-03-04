package de.reclinarka.graphics.frame.type;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.filter.*;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.util.ColorStorage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class Slate extends JPanel { //content for the Window
    public Slate(){
        //filters.add(new ColorFilter("testFilter",new Color(255,165,0,100)));
        //filters.add(new DistortFilter("testFilter",0.3,5,1));
        //filters.add(new BlurrFilter("testFilter",10));
        //filters.add(new GreyscaleFilter("greyScale",16));
        //filters.add(new SepiaFilter("sepia",255,10,30));
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

    private int nativeWidth = 1000;
    private int originalWidth = 1200;
    private int originalHeight = 600;
    private boolean gameMode;
    private GameInstance instance;
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
        g.fillRect(-3,-3,originalWidth + 6, originalHeight + 6);
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
    public static BufferedImage resize(BufferedImage originalImage, int newW, int newH) {
        BufferedImage resizedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newW, newH, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    protected void paintComponent(Graphics g){

        BufferedImage img = new BufferedImage(originalWidth,originalHeight,BufferedImage.TYPE_INT_RGB);
        draw(img.getGraphics());
        if(gameMode){
            lastGamemodeFrame = img;
        }
        g.drawImage( resize(applyFilters(resize(img,nativeWidth,(int)(nativeWidth / getAspectRatio()))),getWidth(),getHeight()),0 ,0 ,getWidth(),getHeight(),null);
        //g.drawImage( resize(applyFilters(img),getWidth(),getHeight()),0 ,0 ,getWidth(),getHeight(),null);
    }
}
