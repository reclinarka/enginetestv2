package de.reclinarka.editor.animation.tools.controllElements;

import de.reclinarka.editor.animation.tools.Toolbar;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.util.ColorStorage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class HideButton extends AnimatorControlElement {

    public HideButton(Toolbar parent, String ID) {
        super(parent, ID);
    }

    private int size = 30;


    @Override
    public void draw(Graphics g) {
        if (isDebugMode())
            g.drawString(getID(), 10, 10);
        if (getParent().isExpanded()) {
            g.setColor(ColorStorage.defaultButtonHighlited);
            g.fillRect(10, 10, size, size);
        } else {
            g.setColor(ColorStorage.defaultTextfieldBorder);
            g.fillRect(10, 10, size, size);
        }

    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        if (e.getButton() == 1)
            if (type == EventType.Mouse_Clicked) {
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
        super.commandThrown(command, ID);
    }
}
