package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RMMUtils {
    public ArrayList<File> getFilesByType(String type, String path){
        File dir = new File(path);
        ArrayList<File> files = new ArrayList<>();

        // Searches for files of type (pl, em, etc)
        File[] filesTemp = dir.listFiles();
        for (File file : filesTemp) {
            if (file.isFile() && file.getName().contains(type)) {
                files.add(file);
                System.out.println(file);
            }
        }

        return files;
    }
    public String createProject(String path, String name,ArrayList<String> folders){

        return path+"/"+name;
    }
    public void createModini(String path,String author, String description){

    }

}
