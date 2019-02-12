package de.reclinarka.objects.gameObjects.entity;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.gameObjects.solid.RectSolid;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.util.WriterReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entity implements Drawable,Interactable{ // has to update (physics-wise) on the command "game_update"


    public Entity(String ID,RectDimension hitbox,String texture){
        this.ID = ID;
        this.hitbox = hitbox;
        setTexture(texture);
    }

    private String ID;
    private RectDimension hitbox;
    private BufferedImage texture;

    public BufferedImage getTexture() {
        return texture;
    }

    public RectDimension getHitbox() {
        return hitbox;
    }

    private void setTexture(String texture){ // "\\resources" is added to the texture path, which is formatted: "\\<subdirectories>\\<file>.png"
        if(texture.contentEquals(""))
            return;
        try {
            this.texture = resize(ImageIO.read(new File(WriterReader.getDirPath() + "\\resources" + texture)));
        } catch (IOException e) {
            try {
                this.texture = resize(ImageIO.read(new File(WriterReader.getDirPath() + "\\resources\\default.png")));
            } catch (IOException exc) {}
        }
    }

    public BufferedImage resize(BufferedImage img) {
        int newW = hitbox.getWidth();
        int newH = hitbox.getHeight();
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    @Override
    public void exec(Graphics g) {


    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

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
