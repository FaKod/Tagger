package eu.linesofcode.taggerbot;

import android.location.Location;
import eu.linesofcode.taggerbot.client.TaggerClient;
import eu.linesofcode.taggerbot.client.TaggerClientException;
import eu.linesofcode.taggerbot.client.data.Tuser;

/**
 * This class stores and controls the current client state. It is meant to be a
 * singleton in the application.
 * 
 * @author Xperimental
 */
public class ClientState {

    private static ClientState instance = new ClientState();

    public synchronized static ClientState getState() {
        return instance;
    }

    TaggerClient client = null;
    Location currentLocation = null;
    GpsState gpsState = GpsState.BAD;
    NetworkState networkState = NetworkState.ERROR;

    private ClientState() {
    }

    public boolean isConnected() {
        return client != null;
    }

    public boolean login(String username, String password) {
        if (isConnected()) {
            throw new IllegalStateException(
                    "Can't login when already connected!");
        }
        boolean success = false;
        try {
            client = new TaggerClient(username, password);
            Tuser user = client.user().get();
            if (!user.getName().equals(username)) {
                Logger.w("Username we got from server is different: Expected: "
                        + username + " Actual: " + user.getName());
            }
            success = true;
        } catch (TaggerClientException e) {
            Logger.e("Error while login: " + e);
        }
        return success;
    }

    public TaggerClient getClient() {
        return client;
    }

    /**
     * @param location
     */
    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    /**
     * @return the currentLocation
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @return the gpsState
     */
    public GpsState getGpsState() {
        return gpsState;
    }

    /**
     * @param gpsState
     *            the gpsState to set
     */
    public void setGpsState(GpsState gpsState) {
        this.gpsState = gpsState;
    }

    /**
     * @return the networkState
     */
    public NetworkState getNetworkState() {
        return networkState;
    }

    /**
     * @param networkState
     *            the networkState to set
     */
    public void setNetworkState(NetworkState networkState) {
        this.networkState = networkState;
    }

}
