package de.reclinarka.objects;

import java.io.IOException;

public interface Writeable {
    void load(String path) throws IOException;
    void save(String path) throws IOException;
}
