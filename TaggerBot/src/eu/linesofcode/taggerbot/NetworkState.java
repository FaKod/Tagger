package eu.linesofcode.taggerbot;

/**
 * This enumeration contains possible network states.
 * 
 * @author Xperimental
 */
public enum NetworkState {
    /**
     * Server is reachable.
     */
    OK,
    /**
     * Last request to server was interrupted by a network error.
     */
    ERROR,
    /**
     * Network connection is deliberately down.
     */
    OFFLINE,
    /**
     * The network connection is currently used.
     */
    ACTIVE
}
