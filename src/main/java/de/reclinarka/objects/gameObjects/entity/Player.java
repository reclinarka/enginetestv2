package de.reclinarka.objects.gameObjects.entity;

import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.coordinates.Position;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends Entity {
    public Player(String ID, RectDimension hitbox, String texture,SwordTest test) {
        super(ID, hitbox, texture);
        position = new Position(hitbox);
        test.setOrigin(position);
    }
    private Position position;

    public Position getPosition() {
        return position;
    }

    @Override
    public void exec(Graphics g) {
        g.drawImage(getTexture(),getHitbox().getPos().getX(),getHitbox().getPos().getY(),null);
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        switch (type){
            case Key_Pressed:
                switch (e.getKeyChar()){
                    case 'w':
                        getHitbox().getPos().setY(getHitbox().getPos().getY()-5);
                        break;
                    case 'a':
                        getHitbox().getPos().setX(getHitbox().getPos().getX()-5);
                        break;
                    case 's':
                        getHitbox().getPos().setY(getHitbox().getPos().getY()+5);
                        break;
                    case 'd':
                        getHitbox().getPos().setX(getHitbox().getPos().getX()+5);
                        break;
                }
                break;
        }
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        super.commandThrown(command, ID);
    }
}
