package business.applicationservice.exception;

public class UnselectedServiceNameException extends RuntimeException {

    public UnselectedServiceNameException() {
         
    }

    public UnselectedServiceNameException(String message) {
        super(message);
    }

    public UnselectedServiceNameException(Throwable cause) {
        super(cause);
    }

    public UnselectedServiceNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnselectedServiceNameException(String message, Throwable cause,
                                          boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
