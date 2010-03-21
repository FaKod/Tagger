package eu.linesofcode.taggerbot.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.R;
import eu.linesofcode.taggerbot.client.data.Tlocation;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;

/**
 * This overlay class is used to draw the location tags on the map view.
 * 
 * @author Xperimental
 */
public class LocationTagOverlay {

    Context context;
    OverlayList overlays;
    Drawable ownTagImage;
    List<TagOverlayListener> listeners = new ArrayList<TagOverlayListener>();

    public LocationTagOverlay(Context ctx, MapView mapView) {
        context = ctx;
        ownTagImage = context.getResources().getDrawable(R.drawable.tag_own);
        overlays = new OverlayList(ownTagImage);

        mapView.getOverlays().add(overlays);
    }

    public void updateOwnTags() {
        overlays.clear();
        for (Tlocationtag tag : ClientState.getState().getOwnTags()) {
            overlays.addOwnTag(tag);
        }
    }

    /**
     * @param tagListener
     */
    public void addTagOverlayListener(TagOverlayListener listener) {
        listeners.add(listener);
    }

    /**
     * Notify all listeners that a tag has been tapped.
     * 
     * @param tag
     *            LocationTag that has been tapped.
     */
    protected void fireTagTapped(final Tlocationtag tag) {
        final List<TagOverlayListener> copy = new ArrayList<TagOverlayListener>(
                listeners);
        for (TagOverlayListener listener : copy) {
            listener.tagTapped(tag);
        }
    }

    private class OverlayList extends ItemizedOverlay<OverlayItem> {

        private List<Tlocationtag> tags = new ArrayList<Tlocationtag>();
        private List<OverlayItem> overlays = new ArrayList<OverlayItem>();

        public OverlayList(Drawable defaultMarker) {
            super(boundCenterBottom(defaultMarker));

            populate();
        }

        public void clear() {
            overlays.clear();
            tags.clear();
            populate();
        }

        /**
         * @param tag
         */
        public synchronized void addOwnTag(Tlocationtag tag) {
            GeoPoint point = createGeoPoint(tag.getPoint());
            OverlayItem item = new OverlayItem(point, tag.getName(), tag
                    .getInfotext());
            overlays.add(item);
            tags.add(tag);
            populate();
        }

        /**
         * @param point
         * @return
         */
        private GeoPoint createGeoPoint(Tlocation point) {
            int latitude = (int) (point.getLatitude() * 1e6);
            int longitude = (int) (point.getLongitude() * 1e6);
            return new GeoPoint(latitude, longitude);
        }

        /*
         * (non-Javadoc)
         * @see com.google.android.maps.ItemizedOverlay#createItem(int)
         */
        @Override
        protected OverlayItem createItem(int i) {
            return overlays.get(i);
        }

        /*
         * (non-Javadoc)
         * @see com.google.android.maps.ItemizedOverlay#size()
         */
        @Override
        public int size() {
            return overlays.size();
        }

        /*
         * (non-Javadoc)
         * @see com.google.android.maps.ItemizedOverlay#onTap(int)
         */
        @Override
        protected boolean onTap(int index) {
            Tlocationtag tag = tags.get(index);
            fireTagTapped(tag);
            return true;
        }

    }

}
