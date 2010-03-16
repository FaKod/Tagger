package eu.linesofcode.taggerbot.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.ClientTask;
import eu.linesofcode.taggerbot.GpsState;
import eu.linesofcode.taggerbot.Logger;
import eu.linesofcode.taggerbot.R;
import eu.linesofcode.taggerbot.activity.ShowMap;
import eu.linesofcode.taggerbot.client.TaggerClient;
import eu.linesofcode.taggerbot.client.data.EStatus;
import eu.linesofcode.taggerbot.client.data.EStatusReturn;
import eu.linesofcode.taggerbot.client.data.LocationType;
import eu.linesofcode.taggerbot.client.data.Tlocation;

/**
 * This service handles the location updates to the Tagger server.
 * 
 * @author Xperimental
 */
public class LocationUpdateService extends Service {

    private static final int NOTIFY_STATUS = 100;

    /**
     * Required accuracy for a "good" GPS signal (meters).
     */
    private static final int REQUIRED_ACCURACY = 10;

    /**
     * GPS update rate (milliseconds). Note that this is maximum update rate and
     * not a fixed rate!
     */
    private static final int GPS_INTERVAL = 5000;

    /**
     * Initial wait time before doing the first location update (seconds).
     */
    private static final int INITIAL_WAIT = 10;

    /**
     * Time between location updated (seconds).
     */
    private static final int UPDATE_INTERVAL = 12;

    private ScheduledExecutorService worker;
    private NotificationManager notifyMan;
    private LocationManager locMan;
    private TaggerLocationListener locationListener;

    /*
     * (non-Javadoc)
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        notifyMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        locationListener = new TaggerLocationListener();
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        locMan.removeUpdates(locationListener);
        notifyMan.cancel(NOTIFY_STATUS);
        if (worker != null) {
            worker.shutdown();
        }
        super.onDestroy();
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onStart(android.content.Intent, int)
     */
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        setupUpdater();
        setupNotification();

        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                GPS_INTERVAL, 5, locationListener);
    }

    /**
     * Starts the worker thread and schedules the update operation.
     */
    private void setupUpdater() {
        worker = Executors.newSingleThreadScheduledExecutor();
        worker.scheduleAtFixedRate(new UpdateWorker(), INITIAL_WAIT,
                UPDATE_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * Displays a notification in the status bar to inform the user of
     * background action.
     */
    private void setupNotification() {
        Notification notification = new Notification(R.drawable.statusicon,
                getString(R.string.statusbar_text), System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, ShowMap.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(this, getString(R.string.app_name),
                getString(R.string.statusbar_text), contentIntent);
        notifyMan.notify(NOTIFY_STATUS, notification);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent arg0) {
        // This service is not bindable.
        return null;
    }

    private class UpdateWorker implements Runnable {

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            if (ClientState.getState().isConnected()) {
                final Location location = ClientState.getState()
                        .getCurrentLocation();
                if (location != null) {
                    ClientTask task = new ClientTask() {

                        @Override
                        public boolean run(TaggerClient client) {
                            Tlocation statusLocation = new Tlocation();
                            statusLocation
                                    .setLocationType(LocationType.MyLocation
                                            .toString());
                            statusLocation.setLatitude(location.getLatitude());
                            statusLocation
                                    .setLongitude(location.getLongitude());
                            EStatus status = new EStatus();
                            status.setLocation(statusLocation);
                            EStatusReturn statusReturn = client.status()
                                    .update(status);
                            Logger.d("Updated location on server: Distance="
                                    + statusReturn.getDistance() + " Azimuth="
                                    + statusReturn.getAzimuth());
                            return true;
                        }
                    };
                    if (!ClientState.getState().doTask(task)) {
                        Toast
                                .makeText(LocationUpdateService.this,
                                        R.string.updateservice_error,
                                        Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }

    private class TaggerLocationListener implements LocationListener {

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onLocationChanged(android.location
         * .Location)
         */
        @Override
        public void onLocationChanged(Location location) {
            boolean accurate = location.hasAccuracy()
                    && (location.getAccuracy() < REQUIRED_ACCURACY);
            if (accurate) {
                ClientState.getState().setGpsState(GpsState.OK);
            } else {
                ClientState.getState().setGpsState(GpsState.MEDIUM);
            }
            ClientState.getState().setCurrentLocation(location);
        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onProviderDisabled(java.lang.String
         * )
         */
        @Override
        public void onProviderDisabled(String provider) {
            ClientState.getState().setGpsState(GpsState.BAD);
        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onProviderEnabled(java.lang.String)
         */
        @Override
        public void onProviderEnabled(String provider) {
            ClientState.getState().setGpsState(GpsState.MEDIUM);
        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onStatusChanged(java.lang.String,
         * int, android.os.Bundle)
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
            case LocationProvider.AVAILABLE:
                ClientState.getState().setGpsState(GpsState.OK);
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
            case LocationProvider.OUT_OF_SERVICE:
                ClientState.getState().setGpsState(GpsState.BAD);
                break;
            }
        }

    }

}
