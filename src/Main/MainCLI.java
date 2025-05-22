package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainCLI {
    public static void main(String[] args) {
        File source = new File(args[0]);

        ArrayList<String> typesData000 = new ArrayList<>();
        ArrayList<String> typesRoot = new ArrayList<>();
        if (!RMMUtils.getFilesByType("pl",source.getAbsolutePath()).isEmpty()){
            typesData000.add("pl");
        }
        if (!RMMUtils.getFilesByType("em",source.getAbsolutePath()).isEmpty()){
            typesData000.add("em");
        }
        if (!RMMUtils.getFilesByType("wp",source.getAbsolutePath()).isEmpty()){
            typesData000.add("wp");
        }
        if (!RMMUtils.getFilesByType("shader",source.getAbsolutePath()).isEmpty()){
            typesRoot.add("shader");
        }

        try {
            RMMUtils.createProject(source.getParentFile().getAbsolutePath(),source.getAbsolutePath(), source.getName()+"_RMM",typesData000, typesRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}