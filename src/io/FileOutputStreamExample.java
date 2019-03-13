package io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("data.txt")) {
            String text = "FileOutputStream goes \nhere";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
