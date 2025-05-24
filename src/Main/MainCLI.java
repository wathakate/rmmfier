package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainCLI {
    public static void main(String[] args) {
        File source = new File(args[0]);

        // Files in Data000 (or any other data for that matter)
        ArrayList<String> typesData000 = new ArrayList<>();
        String[] typesListData000 = {"pl", "em", "wp", "core", "it", "ba", "bh", "bm"};
        // Files that are outside 000
        ArrayList<String> typesRoot = new ArrayList<>();
        String[] typesListRoot = {"shader", "Shader", ".asi", ".ini", ".dll"};

        for (int i = 0; i <typesListData000.length; i++) {
            if (!RMMUtils.getFilesByType(typesListData000[i],source.getAbsolutePath()).isEmpty()){
                typesData000.add(typesListData000[i]);
            }
        }
        for (int i = 0; i <typesListRoot.length; i++) {
            if (!RMMUtils.getFilesByType(typesListRoot[i],source.getAbsolutePath()).isEmpty()){
                typesRoot.add(typesListRoot[i]);
            }
        }

        try {
            RMMUtils.createProject(source.getParentFile().getAbsolutePath(),source.getAbsolutePath(), source.getName()+"_RMM",typesData000, typesRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}