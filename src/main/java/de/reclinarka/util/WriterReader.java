package de.reclinarka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.reclinarka.Main;
import de.reclinarka.objects.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

public class WriterReader {
    public static void deleteFileOrFolder(final Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return CONTINUE;
            }

            @Override public FileVisitResult visitFileFailed(final Path file, final IOException e) {
                return handleException(e);
            }

            private FileVisitResult handleException(final IOException e) {
                e.printStackTrace(); // replace with more robust error handling
                return TERMINATE;
            }

            @Override public FileVisitResult postVisitDirectory(final Path dir, final IOException e)
                    throws IOException {
                if(e!=null)return handleException(e);
                Files.delete(dir);
                return CONTINUE;
            }
        });
    };
    private static ObjectMapper mapper = new ObjectMapper();
    private static void readWriteExample(){
        ObjectMapper mapper = new ObjectMapper();
        Test test = new Test();
        File file = new File("P:\\Coding\\Java\\test\\resources\\test.json");
        try {
            mapper.writeValue(file,test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Test readTest = mapper.readValue(file,Test.class);
            System.out.println(readTest.getID());

         } catch (IOException b){
            b.printStackTrace();
        }
    }

    public static void save(Object o, String path){
        File file = new File(path);
        try {
            mapper.writeValue(file, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object load(Object o, String path) {
        File file = new File(path);
        try {
            return mapper.readValue(file,o.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDirPath(){
        return System.getProperty("user.dir");
    }
}
