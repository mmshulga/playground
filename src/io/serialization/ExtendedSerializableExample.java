package io.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtendedSerializableExample {

    public static void main(String[] args) {
        MySerializable object = new MySerializable("Mikhail", 22);
        System.out.println("object = " + object);
        Path path = Paths.get("test");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File f = path.toFile();
        try (FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            oos.writeObject(object);
            oos.flush();

            object = (MySerializable) ois.readObject();
            System.out.println("object = " + object);

        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static class MySerializable implements Serializable {

        public String name;
        public int age;

        public MySerializable(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private void readObject(ObjectInputStream ois) {
            try {
                this.age = ois.readInt() * 10;
                this.name = (String)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void writeObject(ObjectOutputStream oos) {
            try {
                oos.writeInt(this.age * 2);
                oos.writeObject(this.name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MySerializable{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
