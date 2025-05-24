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
            if (file.isFile() && (file.getName().startsWith(type) || file.getName().endsWith(type))) {
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
            if (!new File(toWhere.toPath() + "/" + filesTemp.get(i).getName()).exists()) {
                Files.copy(filesTemp.get(i).toPath(), Paths.get(toWhere.toPath() + "/" + filesTemp.get(i).getName()));
            }
        }
    }
    public static String createProject(String path, String originalFolder,String name,ArrayList<String> foldersData000,ArrayList<String> rootFolder) throws IOException {
        new File(path+"/"+name).mkdirs();
        new File(path+"/"+name+"/data000").mkdirs();
        for (int i = 0; i < foldersData000.size(); i++) {
            new File(path+"/"+name+"/data000/"+foldersData000.get(i)).mkdirs();
            copyFiles(foldersData000.get(i), originalFolder,path+"/"+name+"/data000/"+foldersData000.get(i));
        }
        for (int i = 0; i < rootFolder.size(); i++) {
            copyFiles(rootFolder.get(i), originalFolder,path+"/"+name+"/");
        }
        createModini(path+"/"+name,"lalilulelo","", searchDinput(path, name));
        return path+"/"+name+"/data000";
    }
    private static String searchDinput(String path, String name){
        if (!getFilesByType("dinput", path+"/"+name).isEmpty()){
            return getFilesByType("dinput", path+"/"+name).get(0).getName();
        } else {
            return "";
        }
    }
    public static void createModini(String path,String author, String description, String dinput) throws IOException {
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
        bw.write("DLLFile=\""+dinput+"\"");
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
        bw.write("Title=\""+modini.getParent()+"\"");
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
