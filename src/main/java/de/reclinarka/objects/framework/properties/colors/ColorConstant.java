package de.reclinarka.objects.framework.properties.colors;

public enum ColorConstant {
    MAIN(0),
    ALT_MAIN(1),
    BORDER(2),
    ALT_BORDER(3);

    ColorConstant(int index){
        this.index = index;
    }

    public final int index;
}
