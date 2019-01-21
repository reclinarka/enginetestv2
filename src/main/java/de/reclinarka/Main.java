package de.reclinarka;

import de.reclinarka.editor.animation.AnimatorInstance;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.instances.Instance;
import de.reclinarka.instances.InstanceManager;
import de.reclinarka.objects.gameObjects.debugging.PropertyViewer;
import de.reclinarka.objects.gameObjects.solid.StonePlain;
import de.reclinarka.objects.util.Creator;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.objects.gameObjects.debugging.ViewDebugger;
import de.reclinarka.objects.gameObjects.entity.Player;
import de.reclinarka.objects.gameObjects.entity.SwordTest;
import de.reclinarka.objects.gameObjects.solid.RectSolid;
import de.reclinarka.objects.gameObjects.solid.StoneMoss;
import de.reclinarka.objects.testing.CreatorTest;
import de.reclinarka.objects.testing.Test;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.interaction.InteractionListener;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.util.ArrayList;

public class Main {


    //Main Method. Starts the program
    public static void main(String[] args) {
        //System.out.println(new GameInstance().getClass() + "");  //getting class reference
        //gameTest();
        animationTest();
    }



    public static void animationTest(){
        Slate animatorSlate = new Slate();
        de.reclinarka.graphics.frame.Window window1 = new de.reclinarka.graphics.frame.Window("Animator",
                animatorSlate,1920,1080,0,0,true);
        InstanceManager window1_manager = new InstanceManager("window1_manager",window1,animatorSlate);
        window1_manager.addInstance(testAnimatorInstance());
        window1_manager.init("animatorTest");
        while (true){
            window1.repaint();
        }
    }

    public static AnimatorInstance testAnimatorInstance(){
        AnimatorInstance animatorInstance = new AnimatorInstance("animatorTest");
        return animatorInstance;
    }


    //most current engine test
    public static void gameTest(){ //engine test

        Slate editorPanel = new Slate();
        de.reclinarka.graphics.frame.Window window1 = new de.reclinarka.graphics.frame.Window("Editor Window",editorPanel,1200,
                600,100,0,true);
        InstanceManager window1_manager = new InstanceManager("window1_manager",window1,editorPanel);
        window1_manager.addInstance(gameInstance1(window1_manager));



        window1_manager.init("game_main");
        Toolkit tk = window1.getToolkit();
        window1.setCursor(tk.createCustomCursor(tk.getImage("resources\\Cursor.png"),new Point(16,16),"test"));

        //GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].setFullScreenWindow(window1);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].setFullScreenWindow(window1);
        while (true){
            window1.repaint();
        }
    }


    public static Instance gameInstance1(InstanceManager manager){
        DrawableRegister register = new DrawableRegister("editorWindow");
        InteractionRegistry registry = new InteractionRegistry("editorInteractables");

        ArrayList<RectSolid> creator = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            creator.add(new StoneMoss("testStone_" + i,new Coordinate(-1000 + (100*i),200)));
        }
        for(int i = 0; i < 10; i++){
            for(int c = 0; c < 100; c++) {
                creator.add(new StonePlain("testStone__" + i + c, new Coordinate(-1000 + (100 * c), 300 + (100 * i))));
            }
        }

        SwordTest swordTest = new SwordTest("player_sword", new Coordinate(10,10),170,25);
        Player playerTest = new Player("player_test",new RectDimension(100,200,new Coordinate(0,-200)),"\\playerTest.png",swordTest);
        GameInstance instance = new GameInstance("game_main",register,registry,10000,5000,500);
        instance.setParent(manager);

        PropertyViewer propertyViewer = new PropertyViewer("pViewer",null,"",instance,manager.getInteractionListener());

        ViewDebugger viewDebugger = new ViewDebugger("view_debugger",instance);
        instance.addInteractable(viewDebugger);
        instance.addEntity(swordTest,swordTest);
        instance.addEntity(propertyViewer,propertyViewer);
        instance.addPlayer(playerTest);

        creator.forEach(f -> {
            instance.addSolid(f);
        });

        return instance;
    }

    public static Instance testInstance1(InstanceManager manager){
        DrawableRegister register = new DrawableRegister("editorWindow");
        InteractionRegistry registry = new InteractionRegistry("editorInteractables");

        Test test = new Test("testobj",new RectDimension(20,20,new Coordinate(20,20)),manager);
        CreatorTest creatorTest = new CreatorTest("creator",manager);
        Creator creator = new Creator(manager,"creator");

        Instance instance = new Instance("editor_main",register,registry);
        instance.setParent(manager);



        instance.addItem(creatorTest,creatorTest);
        instance.addItem(test,test);
        instance.addGlobalItem(null,creator);
        return instance;
    }

    public static void test(){
        Test test = new Test("testobj",new RectDimension(20,20,new Coordinate(20,20)), null);

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

        //Creator creator = new Creator(editorInteractables,editorDrawings,"creator");
        //editorInteractables.addRegistry(creator);
        //editorDrawings.addRegistry(creator);

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