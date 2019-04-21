package com.mcdonalds.app.storelocator.maps.model;

import com.ensighten.Ensighten;
import java.io.Serializable;

public class CameraPosition implements Serializable {
    public LatLng target;
    public float zoom;

    public static CameraPosition fromGMap(com.google.android.gms.maps.model.CameraPosition cameraPosition) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.CameraPosition", "fromGMap", new Object[]{cameraPosition});
        CameraPosition position = new CameraPosition();
        position.target = LatLng.fromGMap(cameraPosition.target);
        position.zoom = cameraPosition.zoom;
        return position;
    }

    public static CameraPosition fromAmap(com.amap.api.maps.model.CameraPosition cameraPosition) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.CameraPosition", "fromAmap", new Object[]{cameraPosition});
        if (cameraPosition == null) {
            return null;
        }
        CameraPosition position = new CameraPosition();
        position.target = LatLng.fromAMaps(cameraPosition.target);
        position.zoom = cameraPosition.zoom;
        return position;
    }

    public static CameraPosition fromAmap2D(com.amap.api.maps2d.model.CameraPosition cameraPosition) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.CameraPosition", "fromAmap2D", new Object[]{cameraPosition});
        CameraPosition position = new CameraPosition();
        position.target = LatLng.fromAMaps2D(cameraPosition.target);
        position.zoom = cameraPosition.zoom;
        return position;
    }
}
