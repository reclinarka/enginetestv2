package de.reclinarka.graphics.frame;

import de.reclinarka.graphics.frame.type.Slate;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(String windowTitle, Slate content, int width, int height, boolean resizable){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(width,height));
        setResizable(resizable);
        setTitle(windowTitle);
        init(content);
    }
    private void init(JPanel content){
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1,0,0));
        getContentPane().add(content);
        pack();
        setVisible(true);
    }
}
