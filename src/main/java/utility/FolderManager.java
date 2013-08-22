package utility;

import java.io.File;


/**
 * Classe usata principalmente per la creazione
 * della cartella in cui verranno salvati i file di esportazione
 * da spedire al lato mobile.
 */
public class FolderManager {

    private FolderManager() {

    }

    public static void createFolderIfNotExists(String folder) {
        File foldFile = new File(folder);

        if (!foldFile.exists()) {

            foldFile.mkdir();

        } else if (foldFile.isFile()) {
            throw new InvalidPathException("It was given a file instead of a folder");
        }

        /*
            Altrimenti lancierebbe sempre eccezione
            Il vero nome era creaUnaCartellaQualoraNonEsistesse ma è difficile tradurlo in inglese
         */
        //else throw new FolderAlreadyExistsException();
    }
}
