package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File source = new File("C:/Users/Lab5/Downloads/test");

        ArrayList<String> types = new ArrayList<>();
        if (!RMMUtils.getFilesByType("pl",source.getAbsolutePath()).isEmpty()){
            types.add("pl");
        }
        if (!RMMUtils.getFilesByType("em",source.getAbsolutePath()).isEmpty()){
            types.add("em");
        }
        if (!RMMUtils.getFilesByType("wp",source.getAbsolutePath()).isEmpty()){
            types.add("wp");
        }

        try {
            RMMUtils.createProject("C:/Users/Lab5/Downloads/",source.getAbsolutePath(), "test2",types);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}