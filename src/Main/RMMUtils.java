package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            } else if (file.isDirectory()) {
                files.addAll(search(type,file,files));
            }
        }
        return files;
    }
    private static void copyFiles(String type,String fromFolder,String toFolder) throws IOException {
        File toWhere = new File(toFolder);
        ArrayList<File> filesTemp = getFilesByType(type,fromFolder);
        for (int i = 0; i < filesTemp.size();i++){
            Files.copy(filesTemp.get(i).toPath(), toWhere.toPath());
            System.out.println("copied "+filesTemp.get(i).getName());
        }
    }
    public static String createProject(String path, String originalFolder,String name,ArrayList<String> folders) throws IOException {
        new File(path+"/"+name).mkdirs();
        new File(path+"/"+name+"/data000").mkdirs();
        for (int i = 0; i < folders.size(); i++) {
            new File(path+"/"+name+"/data000/"+folders.get(i)).mkdirs();
            System.out.println("made "+folders.get(i));
            copyFiles(folders.get(i), originalFolder,path+"/"+name+"/data000/"+folders.get(i));
            System.out.println("copied");
        }

        return path+"/"+name+"/data000";
    }
    public static void createModini(String path,String author, String description){

    }

}
