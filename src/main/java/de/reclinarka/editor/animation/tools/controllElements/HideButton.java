package de.reclinarka.editor.animation.tools.controllElements;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.util.ColorStorage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class HideButton extends ControlElement {

    public HideButton(Toolbar parent){
        super(parent);
    }

    private int size = 30;


    @Override
    public void draw(Graphics g) {
        if(getParent().isExpanded()){
            g.setColor(ColorStorage.defaultButtonHighlited);
            g.fillRect(10,10,size,size);
        } else {
            g.setColor(ColorStorage.defaultTextfieldBorder);
            g.fillRect(10,10,size,size);
        }

    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {

        if(type == EventType.Mouse_Pressed) {
            int x = e.getX();
            int y = getRelativeMouseY(e);
            System.out.println(x + " + " + y);
            if (x >= 10 && x <= 10 + size && y >= 10 && y <= 10 + size)
                getParent().toggleExpanded();
        }

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {

    }

    @Override
    public void commandThrown(String[] command, String ID) {

    }
}
