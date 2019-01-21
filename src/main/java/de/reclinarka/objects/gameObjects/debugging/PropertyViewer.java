package de.reclinarka.objects.gameObjects.debugging;

import de.reclinarka.objects.framework.properties.size.RectDimension;
import de.reclinarka.objects.gameObjects.GameInstance;
import de.reclinarka.objects.gameObjects.entity.Entity;
import de.reclinarka.objects.interaction.InteractionListener;

import java.awt.*;

public class PropertyViewer extends Entity {
    public PropertyViewer(String ID, RectDimension hitbox, String texture, GameInstance instance, InteractionListener listener) {
        super(ID, hitbox, texture);
        this.instance = instance;
        this.listener = listener;
    }

    private GameInstance instance;
    private InteractionListener listener;



    @Override
    public void exec(Graphics g) {
        g.translate(0-instance.getViewWindow().getPos().getX(),0-instance.getViewWindow().getPos().getY());
        g.drawString("" + listener.isCooldown(),
                (instance.getParent().getSlate().getOriginalWidth() / 2),
                (instance.getParent().getSlate().getOriginalWidth() / 2) );
    }
}
