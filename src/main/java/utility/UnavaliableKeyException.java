package utility;

public class UnavaliableKeyException extends RuntimeException {

    public UnavaliableKeyException() {

    }

    public UnavaliableKeyException(String message) {
        super(message);
    }

    public UnavaliableKeyException(Throwable cause) {
        super(cause);
    }

    public UnavaliableKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavaliableKeyException(String message, Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
