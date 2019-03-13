package io.serialization;

import io.serialization.SerializableExample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamExample {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("data2.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            SerializableExample se = new SerializableExample("Bob", 20);
            oos.writeObject(se);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
