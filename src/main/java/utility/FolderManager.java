package utility;

import java.io.File;

public class FolderManager {

    private FolderManager() {

    }

    public static void insertFolderIfNotExists(String folder) {
        File foldFile = new File(folder);

        if (!foldFile.exists()) {

            foldFile.mkdir();

        } else if (foldFile.isFile()) {
            throw new InvalidPathExeption("File instead a folder");
        }
    }
}
