package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

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
            System.out.println(toWhere.toPath() + "/" + filesTemp.get(i).getName());
            Files.copy(filesTemp.get(i).toPath(), Paths.get(toWhere.toPath() + "/" + filesTemp.get(i).getName()));
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
        createModini(path+"/"+name,"lalilulelo","");
        return path+"/"+name+"/data000";
    }
    public static void createModini(String path,String author, String description) throws IOException {
        Random ran = new Random();
        File modini = new File(path+"/mod.ini");
        BufferedWriter bw = new BufferedWriter(new FileWriter(modini.getAbsolutePath()));
        bw.write("[Main]");
        bw.newLine();
        bw.write("UpdateServer=\"\"");
        bw.newLine();
        bw.write("SaveFile=\"\"");
        bw.newLine();
        bw.write("ID=\""+ ran.nextInt(100000) +"\"");
        bw.newLine();
        bw.write("IncludeDir0=\".\"");
        bw.newLine();
        bw.write("IncludeDirCount=\"1\"");
        bw.newLine();
        bw.write("DependsCount=0");
        bw.newLine();
        bw.write("DLLFile=\"\"");
        bw.newLine();
        bw.write("CodeFile=\"\"");
        bw.newLine();
        bw.write("ConfigSchemaFile=\"\"");
        bw.newLine();
        bw.newLine();
        bw.write("[Desc]");
        bw.newLine();
        bw.write("Author=\""+author+"\"");
        bw.newLine();
        bw.write("Title=\"Mod_RMM\"");
        bw.newLine();
        bw.write("Version=\"0.0.0\"");
        bw.newLine();
        bw.write("AuthorURL=\"\"");
        bw.newLine();
        bw.write("Date=\"30/4/2009\"");
        bw.newLine();
        bw.write("Description=\""+description+"\"");
        bw.close();
    }

}
