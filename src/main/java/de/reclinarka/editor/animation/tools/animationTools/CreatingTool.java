package de.reclinarka.editor.animation.tools.animationTools;

import de.reclinarka.editor.animation.tools.Tool;
import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.objects.interaction.EventType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreatingTool extends Tool {
    public CreatingTool(String ID, Toolbar parent){
        super(ID, parent);
        buttons.add(new Button(this,new int[]{10,10,25,25},""));
    }

    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    public void draw(Graphics g, String[] metadata) {
        super.draw(g, metadata);
        buttons.forEach(f -> f.draw(g));
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        super.mouseEvent(e, type, ID);
    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        super.keyEvent(e, type, ID);
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        super.commandThrown(command, ID);
    }
}
class Button {
    public Button(Tool parent, int[] dimensions/*x,y,width,height*/, String ID){
        this.parent = parent;
        this.ID = ID;
        x = dimensions[0];
        y = dimensions[1];
        width = dimensions[2];
        height = dimensions[3];
    }

    private Tool parent;

    private String ID;

    private int x;
    private int y;

    private int width;
    private int height;

    private Color primary = new Color(255,255,255,40);


    public void draw(Graphics g){
        g.setColor(primary);
        g.fillRect(x,y,width,height);
    }

    public String[] withinBounds(int x, int y){
        String[] output = new String[]{"false",ID};
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
            output[0] = "true";
        return output;
    }
    public String getID() {
        return ID;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
