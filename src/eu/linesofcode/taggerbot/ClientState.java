package eu.linesofcode.taggerbot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    ExecutorService notifier = Executors.newSingleThreadExecutor();
    TaggerClient client = null;
    Location currentLocation = null;
    GpsState gpsState = GpsState.BAD;
    NetworkState networkState = NetworkState.ERROR;
    List<ClientStateListener> listeners = Collections
            .synchronizedList(new ArrayList<ClientStateListener>());

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
        fireGpsStateChanged(gpsState);
    }

    /**
     * Notifies all listeners of the new GPS state.
     * 
     * @param newState
     *            New state to notify.
     */
    private void fireGpsStateChanged(final GpsState newState) {
        final List<ClientStateListener> listenerCopy = new ArrayList<ClientStateListener>(
                listeners);
        notifier.submit(new Runnable() {

            @Override
            public void run() {
                for (ClientStateListener listener : listenerCopy) {
                    listener.gpsStateChanged(newState);
                }
            }
        });
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
        fireNetworkStateChanged(networkState);
    }

    /**
     * Notifies all listeners, that the network state has changed.
     * 
     * @param newState
     *            State to notify.
     */
    private void fireNetworkStateChanged(final NetworkState newState) {
        final List<ClientStateListener> listenerCopy = new ArrayList<ClientStateListener>(
                listeners);
        notifier.submit(new Runnable() {

            @Override
            public void run() {
                for (ClientStateListener listener : listenerCopy) {
                    listener.networkStateChanged(newState);
                }
            }
        });
    }

    public void addClientStateListener(ClientStateListener listener) {
        listeners.add(listener);
    }

    public void removeClientStateListener(ClientStateListener listener) {
        listeners.remove(listener);
    }

}
