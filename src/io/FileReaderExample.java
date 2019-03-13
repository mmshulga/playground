package io;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderExample {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("data.txt")) {

            int c;
            while (fileReader.ready()) {
                if ((c = fileReader.read()) != -1) {
                    System.out.print(((char)c));
                }
            }
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
