package business.applicationservice.exception;

public class UnavaliableApplicationServiceException extends RuntimeException {

    public UnavaliableApplicationServiceException() {

    }

    public UnavaliableApplicationServiceException(String message) {
        super(message);
    }

    public UnavaliableApplicationServiceException(Throwable cause) {
        super(cause);
    }

    public UnavaliableApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavaliableApplicationServiceException(String message,
                                                  Throwable cause,
                                                  boolean enableSuppression,
                                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
