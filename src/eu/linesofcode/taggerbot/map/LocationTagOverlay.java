package eu.linesofcode.taggerbot.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import eu.linesofcode.taggerbot.Logger;
import eu.linesofcode.taggerbot.R;
import eu.linesofcode.taggerbot.client.data.Tlocation;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;

/**
 * This overlay class is used to draw the location tags on the map view.
 * 
 * @author Xperimental
 */
public class LocationTagOverlay {

    OverlayList overlays;
    Drawable ownTagImage;

    public LocationTagOverlay(Context ctx, MapView mapView) {
        ownTagImage = ctx.getResources().getDrawable(R.drawable.tag_own);
        overlays = new OverlayList(ownTagImage);

        mapView.getOverlays().add(overlays);
    }

    /**
     * @param added
     * @param removed
     */
    public void updateOwnTags(List<Tlocationtag> added,
            List<Tlocationtag> removed) {
        overlays.clear();
        for (Tlocationtag tag : added) {
            overlays.addOwnTag(tag);
        }
    }

    private class OverlayList extends ItemizedOverlay<OverlayItem> {

        private List<OverlayItem> overlays = new ArrayList<OverlayItem>();

        public OverlayList(Drawable defaultMarker) {
            super(boundCenterBottom(defaultMarker));

            populate();
        }

        public void clear() {
            overlays.clear();
            populate();
        }

        /**
         * @param tag
         */
        public void addOwnTag(Tlocationtag tag) {
            GeoPoint point = createGeoPoint(tag.getPoint());
            OverlayItem item = new OverlayItem(point, tag.getName(), tag
                    .getInfotext());
            overlays.add(item);
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
            Logger.d("createItem(" + i + ")");
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
            OverlayItem item = overlays.get(index);
            Logger.d("Tapped on:" + item.getTitle());
            return true;
        }

    }

}
