package be.civadis.gpdoc.service.exception;

public class EboxRetryableException extends Exception{

    public EboxRetryableException() {
    }

    public EboxRetryableException(String message) {
        super(message);
    }

    public EboxRetryableException(Throwable cause) {
        super(cause);
    }

    public EboxRetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    public EboxRetryableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
