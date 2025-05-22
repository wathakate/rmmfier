package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainCLI {
    public static void main(String[] args) {
        File source = new File(args[0]);

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
            RMMUtils.createProject(source.getParentFile().getAbsolutePath(),source.getAbsolutePath(), source.getName()+"_RMM",types);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}