package io;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("data.txt")) {
            int c;
            while ((c = fileInputStream.read()) != -1) {
                System.out.print((char)c);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
