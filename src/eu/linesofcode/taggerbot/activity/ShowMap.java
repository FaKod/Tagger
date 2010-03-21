package eu.linesofcode.taggerbot.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.ClientStateListener;
import eu.linesofcode.taggerbot.ClientTask;
import eu.linesofcode.taggerbot.GpsState;
import eu.linesofcode.taggerbot.Logger;
import eu.linesofcode.taggerbot.NetworkState;
import eu.linesofcode.taggerbot.Prefs;
import eu.linesofcode.taggerbot.R;
import eu.linesofcode.taggerbot.client.TaggerClient;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;
import eu.linesofcode.taggerbot.map.LocationTagOverlay;
import eu.linesofcode.taggerbot.map.TagOverlayListener;
import eu.linesofcode.taggerbot.service.LocationUpdateService;
import eu.linesofcode.taggerbot.service.TagUpdateService;

public class ShowMap extends MapActivity {

    private static final int DIALOG_LOGINWARNING = 100;
    private static final int DIALOG_TAGINFO = 101;

    private ImageView gpsIndicator;
    private ImageView networkIndicator;
    private MapView mapView;
    private MyLocationOverlay locationOverlay;
    private ClientStateListener stateListener;
    private ExecutorService worker = Executors.newSingleThreadExecutor();
    private boolean showSatImages = true;
    private LocationTagOverlay tagOverlay;
    private Tlocationtag currentTag;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        if (Prefs.get() == null) {
            Prefs.init(this);
        }

        gpsIndicator = (ImageView) findViewById(R.id.gpsIndicator);
        networkIndicator = (ImageView) findViewById(R.id.networkIndicator);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        mapView.setSatellite(showSatImages);
        locationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(locationOverlay);

        tagOverlay = new LocationTagOverlay(this, mapView);
        tagOverlay.addTagOverlayListener(new TagListener());

        stateListener = new StateListener();

        Intent serviceIntent = new Intent(this, LocationUpdateService.class);
        startService(serviceIntent);

        serviceIntent = new Intent(this, TagUpdateService.class);
        startService(serviceIntent);
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

        if (!ClientState.getState().isConnected()) {
            worker.submit(new Runnable() {

                @Override
                public void run() {
                    if (Prefs.get().getUser() == null) {
                        showDialog(DIALOG_LOGINWARNING);
                        ClientState.getState().setNetworkState(
                                NetworkState.OFFLINE);
                    } else {
                        String user = Prefs.get().getUser();
                        String password = Prefs.get().getPassword();
                        if (!ClientState.getState().login(user, password)) {
                            ClientState
                                    .getState()
                                    .alertUser(
                                            getString(R.string.showmap_toast_loginfail));
                        }
                    }
                }
            });
        }
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

        ClientState.getState().removeClientStateListener(stateListener);
    }

    /*
     * (non-Javadoc)
     * @see com.google.android.maps.MapActivity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        worker.shutdown();
        if (!Prefs.get().isKeepAlive()) {
            // Kill background updater when keep-alive is not active.
            stopLocationUpdater();
        }
        stopTagUpdater();
        super.onDestroy();
    }

    /**
     * Stops the tag updater service.
     */
    private void stopTagUpdater() {
        Intent tagIntent = new Intent(this, TagUpdateService.class);
        stopService(tagIntent);
    }

    /**
     * Stops the background update service.
     */
    private void stopLocationUpdater() {
        Intent locationIntent = new Intent(this, LocationUpdateService.class);
        stopService(locationIntent);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.showmenu, menu);
        return true;
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.showprefs:
            startActivity(new Intent(this, Settings.class));
            return true;
        case R.id.togglesatellite:
            showSatImages = !showSatImages;
            mapView.setSatellite(showSatImages);
            return true;
        case R.id.exit:
            stopLocationUpdater();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateDialog(int)
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener closeListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        switch (id) {
        case DIALOG_LOGINWARNING:
            dialog = builder.setTitle(R.string.dialog_login_title).setMessage(
                    R.string.dialog_login_text).setPositiveButton(
                    R.string.dialog_button_close, closeListener).setCancelable(
                    false).create();
            break;
        case DIALOG_TAGINFO:
            dialog = createTaginfoDialog();
            break;
        }
        return dialog;
    }

    /**
     * Creates the tag info dialog.
     * 
     * @return Created dialog.
     */
    private Dialog createTaginfoDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(R.string.dialog_taginfo_title);
        dialog.setContentView(R.layout.dialog_taginfo);
        Button closeButton = (Button) dialog.findViewById(R.id.taginfo_close);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button deleteButton = (Button) dialog.findViewById(R.id.taginfo_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                deleteCurrentTag();
            }
        });
        return dialog;
    }

    /**
     * Deletes the currently selected location tag.
     */
    protected void deleteCurrentTag() {
        if (currentTag != null) {
            final Tlocationtag deleteTag = currentTag;
            worker.submit(new Runnable() {

                @Override
                public void run() {
                    ClientTask task = new ClientTask() {

                        @Override
                        public boolean run(TaggerClient client) {
                            client.tags().delete(
                                    Integer.parseInt(deleteTag.getId()));
                            List<Tlocationtag> removed = new ArrayList<Tlocationtag>();
                            removed.add(deleteTag);
                            ClientState.getState().modifyOwnTags(
                                    new ArrayList<Tlocationtag>(), removed);
                            return true;
                        }
                    };
                    ClientState.getState().doTask(task);
                }
            });
        }
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog)
     */
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
        case DIALOG_TAGINFO:
            EditText name = (EditText) dialog.findViewById(R.id.taginfo_name);
            EditText text = (EditText) dialog.findViewById(R.id.taginfo_text);
            name.setText(currentTag.getName());
            text.setText(currentTag.getInfotext());
            break;
        case DIALOG_LOGINWARNING:
            break;
        }
    }

    /**
     * Creates a {@link GeoPoint} containing the provided {@link Location}.
     * 
     * @param location
     *            Location to convert.
     * @return GeoPoint that points to the same location.
     */
    private GeoPoint getGeoPoint(Location location) {
        int latitude = (int) (location.getLatitude() * 1E6);
        int longitude = (int) (location.getLongitude() * 1E6);
        return new GeoPoint(latitude, longitude);
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
                    switch (newState) {
                    case OK:
                        networkIndicator
                                .setImageResource(R.drawable.network_ok);
                        break;
                    case ERROR:
                        networkIndicator
                                .setImageResource(R.drawable.network_error);
                        break;
                    case OFFLINE:
                        networkIndicator
                                .setImageResource(R.drawable.network_offline);
                        break;
                    default:
                        Logger.e("Unknown network state: " + newState);
                    }
                }
            });
        }

        /*
         * (non-Javadoc)
         * @see
         * eu.linesofcode.taggerbot.ClientStateListener#locationChanged(android
         * .location.Location)
         */
        @Override
        public void locationChanged(Location newLocation) {
            mapView.getController().animateTo(getGeoPoint(newLocation));
        }

        /*
         * (non-Javadoc)
         * @see
         * eu.linesofcode.taggerbot.ClientStateListener#ownTagsChanged(java.
         * util.List, java.util.List)
         */
        @Override
        public void ownTagsChanged(List<Tlocationtag> added,
                List<Tlocationtag> removed) {
            tagOverlay.updateOwnTags();
        }

        /*
         * (non-Javadoc)
         * @see
         * eu.linesofcode.taggerbot.ClientStateListener#alertUser(java.lang.
         * String)
         */
        @Override
        public void alertUser(final String message) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(ShowMap.this, message, Toast.LENGTH_LONG)
                            .show();
                }
            });
        }

    }

    private class TagListener implements TagOverlayListener {

        /*
         * (non-Javadoc)
         * @see
         * eu.linesofcode.taggerbot.map.TagOverlayListener#tagTapped(eu.linesofcode
         * .taggerbot.client.data.Tlocationtag)
         */
        @Override
        public void tagTapped(Tlocationtag tag) {
            currentTag = tag;
            showDialog(DIALOG_TAGINFO);
        }

    }
}
