package eu.linesofcode.taggerbot.client;

import java.io.IOException;

/**
 * Generic exception class for Tagger client errors.
 * 
 * @author Xperimental
 */
public class TaggerClientException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -314308404038169228L;

    public TaggerClientException(String message) {
        super(message);
    }

    public TaggerClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaggerClientException(Throwable cause) {
        super(cause);
    }

    /**
     * Returns true, if the exception was caused by a network error.
     * 
     * @return True, if network caused error.
     */
    public boolean isNetworkError() {
        Throwable cause = getCause();
        return (cause != null) && (cause instanceof IOException);
    }

}
