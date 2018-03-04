package de.reclinarka.objects.gameObjects.solid;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.util.WriterReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RectSolid implements Drawable, Interactable {

    public RectSolid(String ID, RectDimension dimension, String texture){
        this.ID = ID;
        this.dimension = dimension;
        init(texture);
    }

    private boolean test = false;
    private String ID;
    private RectDimension dimension;
    private BufferedImage texture;

    private void init(String texture){ // "\\resources" is added to the texture path, which is formatted: "\\<subdirectories>\\<file>.png"
        try {
            this.texture = resize(ImageIO.read(new File(WriterReader.getDirPath() + "\\resources" + texture)));
        } catch (IOException e) {
            try {
                this.texture = resize(ImageIO.read(new File(WriterReader.getDirPath() + "\\resources\\default.png")));
            } catch (IOException exc) {}
        }
    }

    public BufferedImage resize(BufferedImage img) {
        int newW = dimension.getWidth();
        int newH = dimension.getHeight();
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public RectDimension getDimension() {
        return dimension;
    }

    @Override
    public boolean equals(Object obj) {
        RectSolid in = (RectSolid) obj;
        if(in.getID().contentEquals(getID())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void exec(Graphics L) {
        L.drawImage(texture,dimension.getPos().getX(),dimension.getPos().getY(),null);
        if(test){
            L.setColor(new Color(70,70,70));
            L.drawRect(dimension.getPos().getX(),dimension.getPos().getY(),dimension.getWidth()-1,dimension.getHeight()-1);
        }
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        switch (type){
            case Mouse_Clicked:
                if(dimension.containsPoint(new Coordinate(e.getX(),e.getY())))
                    System.out.println("interacted");
                break;
            case Mouse_Moved:
                if(dimension.containsPoint(new Coordinate(e.getX(),e.getY()))){
                    test = true;
                } else {
                    test = false;
                }
                break;
            case Mouse_Dragged:
                if(dimension.containsPoint(new Coordinate(e.getX(),e.getY()))){
                    test = true;
                } else {
                    test = false;
                }
                break;
        }

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {

    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {

    }

    @Override
    public void load(String path) throws IOException {

    }

    @Override
    public void save(String path) throws IOException {

    }
}
