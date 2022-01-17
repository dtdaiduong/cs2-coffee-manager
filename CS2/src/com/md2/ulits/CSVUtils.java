package com.md2.ulits;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtils {
    public static <T> void write(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            for (Object item : items) {
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(path + "invalid");
        }
    }

    public static List<String> read(String path) throws IllegalArgumentException{
        try{
            return Files.readAllLines(Paths.get(path));
        }catch (IOException e) {
            throw new IllegalArgumentException(path + "invalid");
        }
    }
}
