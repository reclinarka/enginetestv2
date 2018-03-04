package de.reclinarka.objects.gameObjects;

import de.reclinarka.graphics.drawing.Drawable;
import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.graphics.frame.type.Slate;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.gameObjects.entity.Player;
import de.reclinarka.objects.gameObjects.solid.RectSolid;
import de.reclinarka.objects.interaction.EventType;
import de.reclinarka.objects.interaction.Interactable;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameInstance extends Instance implements Interactable {
    public GameInstance() {
    }



    public GameInstance(String ID, DrawableRegister register, InteractionRegistry registry,int width,int height,int chunkSize) {
        super(ID, register, registry);
        this.width = width;
        this.height = height;
        this.chunkSize = chunkSize;
        int count = 0;
        for(int i = 0; i <= width; i = i + chunkSize){
            for(int c = 0; c <= height; c = c + chunkSize){
                chunks.add( new Chunk(getID() + "_chunk_" + count , this ,
                        new RectDimension( chunkSize , chunkSize , new Coordinate( i , c ) ) ) );
                count++;
            }
        }
        chunks.forEach(f -> {
            getDrawableRegister().addRegistry(f);
            getInteractionRegistry().addRegistry(f);
        });
    }

    private Player player;
    private int width;
    private int height;
    private int chunkSize;
    private RectDimension viewWindow = new RectDimension(2500,1000, new Coordinate(200,200));
    private ArrayList<DrawableRegister> layers = new ArrayList<>();
    private ArrayList<Chunk> chunks = new ArrayList<>();
    private boolean running = true;

    private Thread updater = new Thread(() -> {
        while (true){
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {}
            if(running)
                update();
        }
    });

    public void start(){
        running = true;
    }

    public void pause(){
        running = false;
    }

    public RectDimension getViewWindow() {
        return viewWindow;
    }

    public void update(){
        commandThrown(new String[]{getID(),"@a","update"},getID());
    }

    public Chunk getChunk(String ID) {
        return chunks.stream().filter(f -> f.getID().contentEquals(ID)).findFirst().map(f -> f).orElse(null);
    }

    public void addPlayer(Player player) {
        this.player = player;
        addInteractable(player);
    }

    public void addSolid(RectSolid solid){
        Chunk temp;
        for(int i = 0; i < chunks.size(); i++){
            temp = chunks.get(i);
            if(temp.getDimension().intersectsDimension(solid.getDimension())){
                temp.addDrawable(solid);
                temp.addInteractable(solid);
                return;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void addInteractable(Interactable interactable){
        getInteractionRegistry().addRegistry(interactable);
    }

    public void addEntity(Interactable interactable, Drawable drawable){
        getInteractionRegistry().addRegistry(interactable);
        getDrawableRegister().addRegistry(drawable);
    }



    public void draw(Graphics g){
        if (player != null) {
            viewWindow.getPos().setX(0-player.getPosition().getX() + (getParent().getSlate().getOriginalWidth() / 2));
            viewWindow.getPos().setY(0-player.getPosition().getY() + (getParent().getSlate().getOriginalHeight() / 2));
            g.translate(viewWindow.getPos().getX(),viewWindow.getPos().getY());
            player.exec(g);
            getDrawableRegister().draw(g);
        } else {
            g.translate(viewWindow.getPos().getX(),viewWindow.getPos().getY());
            getDrawableRegister().draw(g);
        }


    }

    @Override
    public DrawableRegister getDrawableRegister() {
        return super.getDrawableRegister();
    }

    @Override
    public void mouseEvent(MouseEvent e, EventType type, String ID) {
        Slate slate = getParent().getSlate();
        e = new MouseEvent( (Component) e.getSource(),e.getID(),e.getWhen(),e.getModifiers(),
                ((int)(slate.getOriginalWidth() * slate.getXModifier(e.getX()))) - (viewWindow.getPos().getX()),
                ((int)(slate.getOriginalHeight() * slate.getYModifier(e.getY()))) - (viewWindow.getPos().getY()),
                e.getClickCount(),false,e.getButton());
        getInteractionRegistry().mouseEvent(e,type,ID);

    }

    @Override
    public void keyEvent(KeyEvent e, EventType type, String ID) {
        getInteractionRegistry().keyEvent(e,type,ID);
    }

    @Override
    public void commandThrown(String[] command, String ID) {
        if(command[1].contentEquals(getID()))
            if(command[2].contentEquals("start"))
                start();
            else if(command[2].contentEquals("pause"))
                pause();
        getInteractionRegistry().commandThrown(command,ID);
    }

    @Override
    public void setReciever(InteractionRegistry reciever, String ID) {

    }
}
