package de.reclinarka.graphics.registers;

import de.reclinarka.objects.Writeable;

public interface RegisterElement<Listener> extends Writeable{
    void exec(Listener L);
    String getID();
}
