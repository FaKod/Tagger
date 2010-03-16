package eu.linesofcode.taggerbot;

import android.location.Location;

/**
 * Interface used to listen for client state changes.
 * 
 * @author Xperimental
 */
public interface ClientStateListener {

    void gpsStateChanged(GpsState newState);

    void networkStateChanged(NetworkState newState);

    void locationChanged(Location newLocation);

}
