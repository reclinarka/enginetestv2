package de.reclinarka.objects;

import java.io.IOException;
//Interface for Saving Object-Instances as File
public interface Writeable {
    void load(String path) throws IOException;
    void save(String path) throws IOException;
}
