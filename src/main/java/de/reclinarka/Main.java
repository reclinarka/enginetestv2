package de.reclinarka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.graphics.registers.Register;
import de.reclinarka.objects.Test;
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

        System.out.println(new Test("test").getClass());
        test();
    }



    public static void test(){
        DrawableRegister editorDrawings = new DrawableRegister("editorWindow");
        InteractionRegistry editorInteractables = new InteractionRegistry("editorInteractables");
        InteractionListener interactionListener = new InteractionListener(editorInteractables, "editorWindow");
        Slate editorPanel = new Slate(editorDrawings);
        Window editorWindow = new de.reclinarka.graphics.frame.Window("test",editorPanel,1200,600,false);
        editorWindow.addMouseListener(interactionListener);
        editorWindow.addKeyListener(interactionListener);
        editorWindow.addMouseMotionListener(interactionListener);


        InteractionListener buttonListener = new InteractionListener(editorInteractables,"buttonPanel");
        DrawableRegister buttonDrawings = new DrawableRegister("buttonPanel");
        Slate buttonPanel = new Slate(buttonDrawings);
        Window buttonWindow = new de.reclinarka.graphics.frame.Window("Tools",buttonPanel,100,400,false);
        buttonWindow.addMouseMotionListener(buttonListener);
        buttonWindow.addKeyListener(buttonListener);
        buttonWindow.addMouseListener(buttonListener);



        //editorDrawings.save(WriterReader.getDirPath()+"\\editor\\objects");
    }

}