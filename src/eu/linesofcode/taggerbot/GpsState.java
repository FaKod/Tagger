package eu.linesofcode.taggerbot;

/**
 * Enumeration for possible GPS states.
 * 
 * @author Xperimental
 */
public enum GpsState {
    /**
     * GPS signal is good and available.
     */
    OK,
    /**
     * GPS signal is available but has a bad quality.
     */
    MEDIUM,
    /**
     * No GPS signal available.
     */
    BAD
}
