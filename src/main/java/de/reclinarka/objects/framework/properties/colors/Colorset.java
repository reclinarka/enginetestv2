package de.reclinarka.objects.framework.properties.colors;

import de.reclinarka.util.ColorStorage;

import java.awt.*;

import static de.reclinarka.util.ColorStorage.*;

public class Colorset {
    public Colorset(){

    }

    public Colorset(Color[] colors){
        this.colors = colors;
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

    public static Colorset defaultSet = new Colorset(new Color[]{defaultButton ,defaultButtonAlt ,defaultBorder ,defaultTextfieldBorder});
}
