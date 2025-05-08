package Main;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File source = new File("C:/Users/Lab5/Downloads/test");
        File[] filesTemp = source.listFiles();
        ArrayList<String> types = new ArrayList<>();
        for (File file : filesTemp) {
            switch (file.getName()){
                case "pl":
                    types.add("pl");
                    break;
                case "em":
                    types.add("em");
                    break;
                case "wp":
                    types.add("wp");
                    break;
            }
        }
        ArrayList<File> test = RMMUtils.getFilesByType("pl","C:/Users/Lab5/Downloads/test/");
        RMMUtils.createProject("C:/Users/Lab5/Downloads/","test2", types);

    }
}