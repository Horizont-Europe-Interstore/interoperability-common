package si.sunesis.interoperability.common.exceptions;

/**
 * Exception thrown by request handlers during processing.
 *
 * @author David Trafela, Sunesis
 * @since 1.0.0
 */
public class HandlerException extends Exception {
    /**
     * Constructs a new handler exception with the specified detail message.
     *
     * @param message the detail message
     */
    public HandlerException(String message) {
        super(message);
    }

    /**
     * Constructs a new handler exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
