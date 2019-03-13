package io.serialization;

import java.io.Serializable;

public class SerializableExample implements Serializable {
    String name;
    int age;

    public SerializableExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "[name=" + name + ", age=" + age + "]";
    }
}
