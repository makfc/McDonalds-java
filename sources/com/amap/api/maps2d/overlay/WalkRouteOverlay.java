package com.amap.api.maps2d.overlay;

import android.content.Context;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkStep;
import java.util.List;

public class WalkRouteOverlay extends C1067b {
    /* renamed from: a */
    private WalkPath f3519a;

    public /* bridge */ /* synthetic */ void removeFromMap() {
        super.removeFromMap();
    }

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public WalkRouteOverlay(Context context, AMap aMap, WalkPath walkPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.mAMap = aMap;
        this.f3519a = walkPath;
        this.startPoint = C1068a.m4636a(latLonPoint);
        this.endPoint = C1068a.m4636a(latLonPoint2);
    }

    public void addToMap() {
        List steps = this.f3519a.getSteps();
        for (int i = 0; i < steps.size(); i++) {
            WalkStep walkStep = (WalkStep) steps.get(i);
            LatLng a = C1068a.m4636a((LatLonPoint) walkStep.getPolyline().get(0));
            LatLng a2;
            if (i < steps.size() - 1) {
                if (i == 0) {
                    this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().add(this.startPoint, a).color(getWalkColor()).width(getBuslineWidth())));
                }
                if (!C1068a.m4636a((LatLonPoint) walkStep.getPolyline().get(walkStep.getPolyline().size() - 1)).equals(C1068a.m4636a((LatLonPoint) ((WalkStep) steps.get(i + 1)).getPolyline().get(0)))) {
                    this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().add(r6, a2).color(getWalkColor()).width(getBuslineWidth())));
                }
            } else {
                a2 = C1068a.m4636a((LatLonPoint) walkStep.getPolyline().get(walkStep.getPolyline().size() - 1));
                this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().add(a2, this.endPoint).color(getWalkColor()).width(getBuslineWidth())));
            }
            this.stationMarkers.add(this.mAMap.addMarker(new MarkerOptions().position(a).title("方向:" + walkStep.getAction() + "\n道路:" + walkStep.getRoad()).snippet(walkStep.getInstruction()).anchor(0.5f, 0.5f).visible(this.mNodeIconVisible).icon(getWalkBitmapDescriptor())));
            this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().addAll(C1068a.m4637a(walkStep.getPolyline())).color(getWalkColor()).width(getBuslineWidth())));
        }
        addStartAndEndMarker();
    }

    /* Access modifiers changed, original: protected */
    public float getBuslineWidth() {
        return 18.0f;
    }
}
