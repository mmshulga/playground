package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.txt"))) {
            bufferedWriter.write("BufferedWriter");
            bufferedWriter.newLine();
            bufferedWriter.write("goes here");
            bufferedWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
