package eu.linesofcode.taggerbot;

import java.util.List;

import android.location.Location;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;

/**
 * Interface used to listen for client state changes.
 * 
 * @author Xperimental
 */
public interface ClientStateListener {

    void gpsStateChanged(GpsState newState);

    void networkStateChanged(NetworkState newState);

    void locationChanged(Location newLocation);

    void ownTagsChanged(List<Tlocationtag> added, List<Tlocationtag> removed);

    void alertUser(String message);

}
