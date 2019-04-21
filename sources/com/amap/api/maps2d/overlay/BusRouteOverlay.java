package com.amap.api.maps2d.overlay;

import android.content.Context;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.WalkStep;
import java.util.Iterator;
import java.util.List;

public class BusRouteOverlay extends C1067b {
    /* renamed from: a */
    private BusPath f3510a;
    /* renamed from: b */
    private LatLng f3511b;

    public /* bridge */ /* synthetic */ void removeFromMap() {
        super.removeFromMap();
    }

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public BusRouteOverlay(Context context, AMap aMap, BusPath busPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.f3510a = busPath;
        this.startPoint = C1068a.m4636a(latLonPoint);
        this.endPoint = C1068a.m4636a(latLonPoint2);
        this.mAMap = aMap;
    }

    public void addToMap() {
        try {
            List steps = this.f3510a.getSteps();
            for (int i = 0; i < steps.size(); i++) {
                BusStep busStep = (BusStep) steps.get(i);
                if (i < steps.size() - 1) {
                    BusStep busStep2 = (BusStep) steps.get(i + 1);
                    if (!(busStep.getWalk() == null || busStep.getBusLine() == null)) {
                        m4623b(busStep);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() == null)) {
                        m4628c(busStep, busStep2);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() != null || busStep2.getBusLine() == null)) {
                        m4624b(busStep, busStep2);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() != null || busStep2.getBusLine() == null)) {
                        m4620a(busStep, busStep2);
                    }
                }
                if (busStep.getWalk() != null && busStep.getWalk().getSteps().size() > 0) {
                    m4619a(busStep);
                } else if (busStep.getBusLine() == null) {
                    this.allPolyLines.add(m4615a(this.f3511b, this.endPoint));
                }
                if (busStep.getBusLine() != null) {
                    RouteBusLineItem busLine = busStep.getBusLine();
                    m4621a(busLine);
                    m4625b(busLine);
                }
            }
            addStartAndEndMarker();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m4619a(BusStep busStep) {
        List steps = busStep.getWalk().getSteps();
        for (int i = 0; i < steps.size(); i++) {
            WalkStep walkStep = (WalkStep) steps.get(i);
            if (i == 0) {
                m4618a(C1068a.m4636a((LatLonPoint) walkStep.getPolyline().get(0)), walkStep.getRoad(), m4622b(steps));
            }
            List a = C1068a.m4637a(walkStep.getPolyline());
            this.f3511b = (LatLng) a.get(a.size() - 1);
            this.allPolyLines.add(m4617a(a));
            if (i < steps.size() - 1) {
                LatLng latLng = (LatLng) a.get(a.size() - 1);
                LatLng a2 = C1068a.m4636a((LatLonPoint) ((WalkStep) steps.get(i + 1)).getPolyline().get(0));
                if (!latLng.equals(a2)) {
                    this.allPolyLines.add(m4615a(latLng, a2));
                }
            }
        }
    }

    /* renamed from: a */
    private void m4618a(LatLng latLng, String str, String str2) {
        this.stationMarkers.add(this.mAMap.addMarker(new MarkerOptions().position(latLng).title(str).snippet(str2).visible(this.mNodeIconVisible).anchor(0.5f, 0.5f).icon(getWalkBitmapDescriptor())));
    }

    /* renamed from: a */
    private void m4621a(RouteBusLineItem routeBusLineItem) {
        this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().addAll(C1068a.m4637a(routeBusLineItem.getPolyline())).color(getBusColor()).width(getBuslineWidth())));
    }

    /* renamed from: b */
    private void m4625b(RouteBusLineItem routeBusLineItem) {
        this.stationMarkers.add(this.mAMap.addMarker(new MarkerOptions().position(C1068a.m4636a(routeBusLineItem.getDepartureBusStation().getLatLonPoint())).title(routeBusLineItem.getBusLineName()).snippet(m4627c(routeBusLineItem)).anchor(0.5f, 0.5f).visible(this.mNodeIconVisible).icon(getBusBitmapDescriptor())));
    }

    /* renamed from: a */
    private void m4620a(BusStep busStep, BusStep busStep2) {
        LatLng a = C1068a.m4636a(m4630e(busStep));
        LatLng a2 = C1068a.m4636a(m4631f(busStep2));
        if (a2.latitude - a.latitude > 1.0E-4d || a2.longitude - a.longitude > 1.0E-4d) {
            drawLineArrow(a, a2);
        }
    }

    /* renamed from: b */
    private void m4624b(BusStep busStep, BusStep busStep2) {
        LatLng a = C1068a.m4636a(m4630e(busStep));
        LatLng a2 = C1068a.m4636a(m4631f(busStep2));
        if (!a.equals(a2)) {
            drawLineArrow(a, a2);
        }
    }

    /* renamed from: c */
    private void m4628c(BusStep busStep, BusStep busStep2) {
        LatLonPoint e = m4630e(busStep);
        LatLonPoint c = m4626c(busStep2);
        if (!e.equals(c)) {
            this.allPolyLines.add(m4616a(e, c));
        }
    }

    /* renamed from: b */
    private void m4623b(BusStep busStep) {
        LatLonPoint d = m4629d(busStep);
        LatLonPoint f = m4631f(busStep);
        if (!d.equals(f)) {
            this.allPolyLines.add(m4616a(d, f));
        }
    }

    /* renamed from: c */
    private LatLonPoint m4626c(BusStep busStep) {
        return (LatLonPoint) ((WalkStep) busStep.getWalk().getSteps().get(0)).getPolyline().get(0);
    }

    /* renamed from: a */
    private Polyline m4616a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        LatLng a = C1068a.m4636a(latLonPoint);
        LatLng a2 = C1068a.m4636a(latLonPoint2);
        if (this.mAMap != null) {
            return m4615a(a, a2);
        }
        return null;
    }

    /* renamed from: a */
    private Polyline m4615a(LatLng latLng, LatLng latLng2) {
        return this.mAMap.addPolyline(new PolylineOptions().add(latLng, latLng2).width(getBuslineWidth()).color(getWalkColor()));
    }

    /* renamed from: a */
    private Polyline m4617a(List<LatLng> list) {
        return this.mAMap.addPolyline(new PolylineOptions().addAll(list).color(getWalkColor()).width(getBuslineWidth()));
    }

    /* renamed from: b */
    private String m4622b(List<WalkStep> list) {
        float f = 0.0f;
        Iterator it = list.iterator();
        while (true) {
            float f2 = f;
            if (!it.hasNext()) {
                return "步行" + f2 + "米";
            }
            f = ((WalkStep) it.next()).getDistance() + f2;
        }
    }

    public void drawLineArrow(LatLng latLng, LatLng latLng2) {
        this.mAMap.addPolyline(new PolylineOptions().add(latLng, latLng2).width(3.0f).color(getBusColor()).width(getBuslineWidth()));
    }

    /* renamed from: c */
    private String m4627c(RouteBusLineItem routeBusLineItem) {
        return "(" + routeBusLineItem.getDepartureBusStation().getBusStationName() + "-->" + routeBusLineItem.getArrivalBusStation().getBusStationName() + ") 经过" + (routeBusLineItem.getPassStationNum() + 1) + "站";
    }

    /* Access modifiers changed, original: protected */
    public float getBuslineWidth() {
        return 18.0f;
    }

    /* renamed from: d */
    private LatLonPoint m4629d(BusStep busStep) {
        List steps = busStep.getWalk().getSteps();
        steps = ((WalkStep) steps.get(steps.size() - 1)).getPolyline();
        return (LatLonPoint) steps.get(steps.size() - 1);
    }

    /* renamed from: e */
    private LatLonPoint m4630e(BusStep busStep) {
        List polyline = busStep.getBusLine().getPolyline();
        return (LatLonPoint) polyline.get(polyline.size() - 1);
    }

    /* renamed from: f */
    private LatLonPoint m4631f(BusStep busStep) {
        return (LatLonPoint) busStep.getBusLine().getPolyline().get(0);
    }
}
