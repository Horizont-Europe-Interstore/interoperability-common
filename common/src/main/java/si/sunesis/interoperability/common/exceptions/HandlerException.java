package si.sunesis.interoperability.common.exceptions;

public class HandlerException extends Exception {
    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
