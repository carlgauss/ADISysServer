package utility;

public class InvalidPathExeption extends RuntimeException {

    public InvalidPathExeption() {

    }

    public InvalidPathExeption(String message) {
        super(message);

    }

    public InvalidPathExeption(Throwable cause) {
        super(cause);

    }

    public InvalidPathExeption(String message, Throwable cause) {
        super(message, cause);

    }

    public InvalidPathExeption(String message, Throwable cause,
                               boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

}
