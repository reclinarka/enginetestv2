package de.reclinarka.graphics.frame.type;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.util.ColorStorage;

import javax.swing.*;
import java.awt.*;

public class Slate extends JPanel { //content for the Window
    public Slate(){}
    public Slate(DrawableRegister content){
        this.content = content;
    }

    private boolean gameMode;
    private GameInstance instance;
    private DrawableRegister content;

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

    protected void paintComponent(Graphics g){
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
}
