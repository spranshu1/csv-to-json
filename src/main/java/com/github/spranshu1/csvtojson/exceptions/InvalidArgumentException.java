package com.github.spranshu1.csvtojson.exceptions;

public class InvalidArgumentException extends RuntimeException {

    /** Message of exception. */
    private String message;

    /**Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call.
     * @param message The exception message
     */
    public InvalidArgumentException(final String message) {
        super(message);
        this.message = message;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return "InvalidArgumentException [message=" + message + "]";
    }
}
