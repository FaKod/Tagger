package eu.linesofcode.taggerbot.activity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.ClientStateListener;
import eu.linesofcode.taggerbot.GpsState;
import eu.linesofcode.taggerbot.Logger;
import eu.linesofcode.taggerbot.NetworkState;
import eu.linesofcode.taggerbot.R;

public class ShowMap extends MapActivity {

    private static final int REQUIRED_ACCURACY = 10;

    private ImageView gpsIndicator;
    private ImageView networkIndicator;
    private MapView mapView;
    private MyLocationOverlay locationOverlay;
    private TaggerLocationListener locationListener;
    private ClientStateListener stateListener;
    private LocationManager locMan;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        gpsIndicator = (ImageView) findViewById(R.id.gpsIndicator);
        networkIndicator = (ImageView) findViewById(R.id.networkIndicator);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        mapView.setSatellite(true);
        locationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(locationOverlay);

        locationListener = new TaggerLocationListener();
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

        stateListener = new StateListener();
    }

    /*
     * (non-Javadoc)
     * @see com.google.android.maps.MapActivity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();

        ClientState.getState().addClientStateListener(stateListener);

        locationOverlay.enableMyLocation();
        locationOverlay.enableCompass();
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5,
                locationListener);
    }

    /*
     * (non-Javadoc)
     * @see com.google.android.maps.MapActivity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();

        locationOverlay.disableMyLocation();
        locationOverlay.disableCompass();
        locMan.removeUpdates(locationListener);

        ClientState.getState().removeClientStateListener(stateListener);
    }

    /*
     * (non-Javadoc)
     * @see com.google.android.maps.MapActivity#isRouteDisplayed()
     */
    @Override
    protected boolean isRouteDisplayed() {
        return false;
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
            Logger.d("onStatusChanged(" + provider + ", " + status + ")");
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

    private class StateListener implements ClientStateListener {

        /*
         * (non-Javadoc)
         * @seeeu.linesofcode.taggerbot.ClientStateListener#gpsStateChanged(eu.
         * linesofcode.taggerbot.GpsState)
         */
        @Override
        public void gpsStateChanged(final GpsState newState) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Logger.d("New GPS state: " + newState);
                    switch (newState) {
                    case OK:
                        gpsIndicator.setImageResource(R.drawable.gps_ok);
                        break;
                    case MEDIUM:
                        gpsIndicator.setImageResource(R.drawable.gps_medium);
                        break;
                    case BAD:
                        gpsIndicator.setImageResource(R.drawable.gps_bad);
                        break;
                    default:
                        Logger.e("Unknown GPS state: " + newState);
                    }
                }
            });
        }

        /*
         * (non-Javadoc)
         * @see
         * eu.linesofcode.taggerbot.ClientStateListener#networkStateChanged(
         * eu.linesofcode.taggerbot.NetworkState)
         */
        @Override
        public void networkStateChanged(final NetworkState newState) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Logger.d("New network state: " + newState);
                    switch (newState) {
                    case OK:
                        networkIndicator
                                .setImageResource(R.drawable.network_ok);
                        break;
                    case ERROR:
                        networkIndicator
                                .setImageResource(R.drawable.network_error);
                        break;
                    default:
                        Logger.e("Unknown network state: " + newState);
                    }
                }
            });
        }

    }
}
