package utility;
public class FolderAlreadyExistsException extends RuntimeException {

    public FolderAlreadyExistsException() {
        this("");
    }

    public FolderAlreadyExistsException(String message) {
        super(message);
    }

}
