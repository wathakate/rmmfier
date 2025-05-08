package Main;

import java.io.File;
import java.util.ArrayList;

public class RMMUtils {
    public static ArrayList<File> getFilesByType(String type, String path){
         //This function might be unnecessary but it is what it is
        File dir = new File(path);
        ArrayList<File> files = new ArrayList<>();

        files.addAll(search(type,dir,files));

        return files;
    }
    private static ArrayList<File> search(String type, File dir, ArrayList<File> list){
        // Searches for files of type (pl, em, etc.)
        ArrayList<File> files = new ArrayList<>(list);
        File[] filesTemp = dir.listFiles();
        for (File file : filesTemp) {
            if (file.isFile() && file.getName().contains(type)) {
                files.add(file);
                System.out.println(file);
            } else if (file.isDirectory()) {
                files.addAll(search(type,file,files));
            }
        }
        return files;
    }
    public static String createProject(String path, String name,ArrayList<String> folders){
        new File(path+"/"+name).mkdirs();
        for (int i = 0; i < folders.size(); i++) {
            new File(path+"/"+name+"/"+folders.get(i)).mkdirs();
        }
        return path+"/"+name;
    }
    public static void createModini(String path,String author, String description){

    }

}
