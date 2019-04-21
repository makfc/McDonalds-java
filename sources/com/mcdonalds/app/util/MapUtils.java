package com.mcdonalds.app.util;

import android.content.Context;
import android.location.Location;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class MapUtils {

    public static class CameraBuilder {
        private Context context;
        private List<LatLng> locations;
        private McdMap map;
        private int margin = 0;

        public CameraBuilder(Context context) {
            this.context = context;
            this.locations = new ArrayList();
        }

        public CameraBuilder map(McdMap map) {
            Ensighten.evaluateEvent(this, "map", new Object[]{map});
            this.map = map;
            return this;
        }

        public CameraBuilder userLocation(Location location) {
            Ensighten.evaluateEvent(this, "userLocation", new Object[]{location});
            if (location != null) {
                this.locations.add(new LatLng(location.getLatitude(), location.getLongitude()));
            }
            return this;
        }

        public CameraBuilder store(Store store) {
            Ensighten.evaluateEvent(this, "store", new Object[]{store});
            if (store != null) {
                this.locations.add(new LatLng(store.getLatitude(), store.getLongitude()));
            }
            return this;
        }

        public CameraBuilder margin(int marginInDp) {
            Ensighten.evaluateEvent(this, "margin", new Object[]{new Integer(marginInDp)});
            this.margin = marginInDp;
            return this;
        }

        public void move() {
            Ensighten.evaluateEvent(this, "move", null);
            checkPreconditions();
            if (this.margin <= 0) {
                this.margin = 24;
            }
            this.map.animateCamera(this.locations, UIUtils.dpAsPixels(this.context, this.margin));
        }

        public void move(float zoom) {
            Ensighten.evaluateEvent(this, "move", new Object[]{new Float(zoom)});
            checkPreconditions();
            if (this.locations.size() != 1) {
                throw new IllegalStateException("There must only be 1 location for a move with a zoom.");
            }
            this.map.moveCamera((LatLng) this.locations.get(0), zoom);
        }

        private void checkPreconditions() {
            Ensighten.evaluateEvent(this, "checkPreconditions", null);
            if (this.map == null) {
                throw new IllegalStateException("There must be a map.");
            } else if (this.locations.isEmpty()) {
                throw new IllegalStateException("There must be locations.");
            }
        }
    }

    public static void updateMap(Context context, McdMap map, Store store) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.MapUtils", "updateMap", new Object[]{context, map, store});
        Location userLocation = null;
        try {
            userLocation = AppUtils.getUserLocation();
        } catch (IllegalStateException e) {
        }
        updateMap(context, map, store, userLocation);
    }

    public static void updateMap(Context context, McdMap map, Store store, Location location) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.MapUtils", "updateMap", new Object[]{context, map, store, location});
        if (map != null && store != null) {
            map.addMarker(new MarkerOptions().position(new LatLng(store.getLatitude(), store.getLongitude())).icon(C2358R.C2359drawable.map_pin_red));
            with(context).map(map).store(store).userLocation(location).move();
        }
    }

    public static CameraBuilder with(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.MapUtils", "with", new Object[]{context});
        return new CameraBuilder(context);
    }
}
