package be.civadis.gpdoc.service.exception;

public class EboxException extends Exception{

    public EboxException() {
    }

    public EboxException(String message) {
        super(message);
    }

    public EboxException(Throwable cause) {
        super(cause);
    }

    public EboxException(String message, Throwable cause) {
        super(message, cause);
    }

    public EboxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
