package com.mcdonalds.app.util;

import android.location.Location;
import com.ensighten.Ensighten;
import com.mcdonalds.app.storelocator.maps.model.LatLng;

public class LocationUtils {
    public static double distanceBetween(LatLng start, LatLng end) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LocationUtils", "distanceBetween", new Object[]{start, end});
        double lat1 = start.latitude;
        double lat2 = end.latitude;
        double lon1 = start.longitude;
        double lon2 = end.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        return 6366000.0d * (2.0d * Math.asin(Math.sqrt((Math.sin(dLat / 2.0d) * Math.sin(dLat / 2.0d)) + (((Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))) * Math.sin(dLon / 2.0d)) * Math.sin(dLon / 2.0d)))));
    }

    public static double getZoomLevelForRadius(double radius, double width, double latitude) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LocationUtils", "getZoomLevelForRadius", new Object[]{new Double(radius), new Double(width), new Double(latitude)});
        return Math.log(((6378140.0d * width) * Math.cos((3.141592653589793d * latitude) / 180.0d)) / (256.0d * radius)) / Math.log(2.0d);
    }

    public static LatLng toLatLng(Location location) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LocationUtils", "toLatLng", new Object[]{location});
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
