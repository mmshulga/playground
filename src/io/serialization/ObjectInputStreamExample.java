package io.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamExample {
    public static void main(String[] args) {
        ObjectOutputStreamExample.main(args);
        try (FileInputStream fis = new FileInputStream("data2.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            SerializableExample se = (SerializableExample)ois.readObject();
            System.out.println("se = " + se);

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
