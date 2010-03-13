package eu.linesofcode.taggerbot;

/**
 * Listener interface to listen for client state changes.
 * 
 * @author Xperimental
 */
public interface ClientStateListener {

    void gpsStateChanged(GpsState newState);

    void networkStateChanged(NetworkState newState);

}
