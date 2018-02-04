package de.reclinarka.graphics.frame;

import de.reclinarka.graphics.frame.type.Slate;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(String windowTitle, Slate content, int width, int height, boolean resizable){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.setLocation(( (gd.getDisplayMode().getWidth() / 2) - (width / 2) ),
                ( (gd.getDisplayMode().getHeight() / 2) - (height / 2) ));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(width,height));
        setResizable(resizable);
        setTitle(windowTitle);
        init(content);
    }
    public Window(String windowTitle, Slate content, int width, int height, int xDisplacement, int yDisplecement, boolean resizable){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.setLocation(( (gd.getDisplayMode().getWidth() / 2) - (width / 2) ) + xDisplacement,
                ( (gd.getDisplayMode().getHeight() / 2) - (height / 2) ) + yDisplecement );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(width,height));
        setResizable(resizable);
        setTitle(windowTitle);
        init(content);
    }

    private void init(JPanel content){
        setLayout(new GridLayout(1,1,0,0));
        getContentPane().add(content);
        pack();
        setVisible(true);
    }
}
