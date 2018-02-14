package de.reclinarka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;
import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.graphics.registers.Register;
import de.reclinarka.instances.Instance;
import de.reclinarka.instances.InstanceManager;
import de.reclinarka.objects.Creator;
import de.reclinarka.objects.Test;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.InteractionListener;
import de.reclinarka.objects.interaction.InteractionRegistry;
import de.reclinarka.util.WriterReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println(Test.class);
        test2();
    }

    public static void test2(){
        Slate editorPanel = new Slate();
        de.reclinarka.graphics.frame.Window window1 = new de.reclinarka.graphics.frame.Window("Editor Window",editorPanel,1200,
                600,100,0,false);
        InstanceManager window1_manager = new InstanceManager("window1_manager",window1,editorPanel);
        window1_manager.addInstance(testInstance1());
        window1_manager.init("editor_main");
        while (true){
            window1.repaint();
        }
    }

    public static Instance testInstance1(){
        DrawableRegister register = new DrawableRegister("editorWindow");
        InteractionRegistry registry = new InteractionRegistry("editorInteractables");

        Test test = new Test("testobj",new RectDimension(20,20,new Coordinate(20,20)));

        register.addRegistry(test);

        registry.addRegistry(test);

        Instance instance = new Instance("editor_main",register,registry);
        return instance;
    }

    public static void test(){
        Test test = new Test("testobj",new RectDimension(20,20,new Coordinate(20,20)));

        DrawableRegister editorDrawings = new DrawableRegister("editorWindow");
        InteractionRegistry editorInteractables = new InteractionRegistry("editorInteractables");
        InteractionListener interactionListener = new InteractionListener(editorInteractables, "editorWindow");
        Slate editorPanel = new Slate(editorDrawings);
        Window editorWindow = new de.reclinarka.graphics.frame.Window("test",editorPanel,1200,600,
                100,0,false);
        editorPanel.addMouseListener(interactionListener);
        editorWindow.addKeyListener(interactionListener);
        editorPanel.addMouseMotionListener(interactionListener);

        editorInteractables.addRegistry(test);
        editorDrawings.addRegistry(test);

        Creator creator = new Creator(editorInteractables,editorDrawings,"creator");
        editorInteractables.addRegistry(creator);
        editorDrawings.addRegistry(creator);

        InteractionRegistry buttonInteractables = new InteractionRegistry("buttonInteractables");
        InteractionListener buttonListener = new InteractionListener(buttonInteractables,"buttonPanel");
        DrawableRegister buttonDrawings = new DrawableRegister("buttonPanel");
        Slate buttonPanel = new Slate(buttonDrawings);
        Window buttonWindow = new de.reclinarka.graphics.frame.Window("Tools",buttonPanel,200,600,
                -650, 0,false);
        buttonPanel.addMouseMotionListener(buttonListener);
        buttonPanel.addKeyListener(buttonListener);
        buttonPanel.addMouseListener(buttonListener);

        while(true){
            editorWindow.repaint();
            buttonWindow.repaint();
        }

        //editorDrawings.save(WriterReader.getDirPath()+"\\editor\\objects");
    }

}