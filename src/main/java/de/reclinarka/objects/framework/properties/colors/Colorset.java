package de.reclinarka.objects.framework.properties.colors;

import java.awt.*;

public class Colorset {
    public Colorset(){

    }
    private Color[] colors;

    public Color[] getColors() {
        return colors;
    }

    public Color getColor(ColorConstant color) {
        return colors[color.index];
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }
}
