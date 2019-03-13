package io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("data.txt")) {
            fileWriter.write("this is a test");
            fileWriter.write("это тест");
            fileWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
