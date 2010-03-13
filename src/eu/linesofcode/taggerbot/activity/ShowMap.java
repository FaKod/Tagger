package eu.linesofcode.taggerbot.activity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import eu.linesofcode.taggerbot.Logger;
import eu.linesofcode.taggerbot.R;
import eu.linesofcode.taggerbot.client.TaggerClient;
import eu.linesofcode.taggerbot.client.TaggerClientException;
import eu.linesofcode.taggerbot.client.data.EStatus;
import eu.linesofcode.taggerbot.client.data.EStatusReturn;
import eu.linesofcode.taggerbot.client.data.LocationType;
import eu.linesofcode.taggerbot.client.data.Tlocation;
import eu.linesofcode.taggerbot.client.data.Tuser;

public class ShowMap extends MapActivity {

    private MapView mapView;
    private MyLocationOverlay locationOverlay;
    private TaggerClient client = null;
    private TaggerLocationListener locationListener;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        mapView.setSatellite(true);
        locationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(locationOverlay);

        locationListener = new TaggerLocationListener();
    }

    /*
     * (non-Javadoc)
     * @see com.google.android.maps.MapActivity#isRouteDisplayed()
     */
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.showmap, menu);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.showGps:
            toggleCurrentLocation();
            return true;
        case R.id.login:
            if (client == null) {
                client = new TaggerClient("FaKod", "password");
                Tuser user = client.user().get();
                Logger.d("Logged in as: " + user.getName());
            }
            return true;
        }
        return false;
    }

    /**
     * Toggles the location display on the map.
     */
    private void toggleCurrentLocation() {
        if (locationOverlay.isMyLocationEnabled()) {
            locationOverlay.disableMyLocation();
            locationOverlay.disableCompass();
            locationListener.stopListen();
        } else {
            locationOverlay.enableMyLocation();
            locationOverlay.enableCompass();
            locationListener.startListen();
        }
    }

    private class TaggerLocationListener implements LocationListener {

        LocationManager locMan;

        public TaggerLocationListener() {
            locMan = (LocationManager) ShowMap.this
                    .getSystemService(LOCATION_SERVICE);
        }

        /**
         * 
         */
        public void stopListen() {
            locMan.removeUpdates(this);
        }

        /**
         * 
         */
        public void startListen() {
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
                    5, this);
        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onLocationChanged(android.location
         * .Location)
         */
        @Override
        public void onLocationChanged(Location location) {
            if (client != null) {
                try {
                    Tlocation loc = new Tlocation();
                    loc.setLatitude(location.getLatitude());
                    loc.setLongitude(location.getLongitude());
                    loc.setLocationType(LocationType.MyLocation.toString());
                    EStatus status = new EStatus();
                    status.setLocation(loc);
                    EStatusReturn result = client.status().update(status);
                    Logger.d("Updated status: Dist: " + result.getDistance()
                            + " Azi: " + result.getAzimuth());
                } catch (TaggerClientException e) {
                    Logger.d("Client error: " + e);
                }
            }
        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onProviderDisabled(java.lang.String
         * )
         */
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onProviderEnabled(java.lang.String)
         */
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see
         * android.location.LocationListener#onStatusChanged(java.lang.String,
         * int, android.os.Bundle)
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    }
}
