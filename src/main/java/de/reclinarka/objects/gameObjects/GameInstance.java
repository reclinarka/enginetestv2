package de.reclinarka.objects.gameObjects;

import de.reclinarka.graphics.drawing.DrawableRegister;
import de.reclinarka.instances.Instance;
import de.reclinarka.objects.framework.properties.coordinates.Coordinate;
import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.gameObjects.solid.RectSolid;
import de.reclinarka.objects.interaction.InteractionRegistry;

import java.util.ArrayList;

public class GameInstance extends Instance {
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

    private int width;
    private int height;
    private int chunkSize;
    private RectDimension viewWindow = new RectDimension(2500,1000, new Coordinate(0,0));
    private ArrayList<DrawableRegister> layers = new ArrayList<>();
    private ArrayList<Chunk> chunks = new ArrayList<>();

    public RectDimension getViewWindow() {
        return viewWindow;
    }

    public Chunk getChunk(String ID) {
        return chunks.stream().filter(f -> f.getID().contentEquals(ID)).findFirst().map(f -> f).orElse(null);
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

    @Override
    public DrawableRegister getDrawableRegister() {
        return super.getDrawableRegister();
    }
}
