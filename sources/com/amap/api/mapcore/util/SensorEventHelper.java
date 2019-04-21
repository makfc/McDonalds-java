package com.amap.api.mapcore.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.RemoteException;
import android.view.WindowManager;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

/* renamed from: com.amap.api.mapcore.util.as */
public class SensorEventHelper implements SensorEventListener {
    /* renamed from: a */
    private SensorManager f1167a;
    /* renamed from: b */
    private Sensor f1168b;
    /* renamed from: c */
    private long f1169c = 0;
    /* renamed from: d */
    private final int f1170d = 100;
    /* renamed from: e */
    private float f1171e;
    /* renamed from: f */
    private Context f1172f;
    /* renamed from: g */
    private IAMapDelegate f1173g;
    /* renamed from: h */
    private Marker f1174h;

    public SensorEventHelper(Context context, IAMapDelegate iAMapDelegate) {
        this.f1172f = context;
        this.f1173g = iAMapDelegate;
        this.f1167a = (SensorManager) context.getSystemService("sensor");
        this.f1168b = this.f1167a.getDefaultSensor(3);
    }

    /* renamed from: a */
    public void mo8678a() {
        this.f1167a.registerListener(this, this.f1168b, 3);
    }

    /* renamed from: b */
    public void mo8680b() {
        this.f1167a.unregisterListener(this, this.f1168b);
    }

    /* renamed from: a */
    public void mo8679a(Marker marker) {
        this.f1174h = marker;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (System.currentTimeMillis() - this.f1169c >= 100 && this.f1173g.getCameraAnimator().isFinished()) {
            switch (sensorEvent.sensor.getType()) {
                case 3:
                    float a = (sensorEvent.values[0] + ((float) SensorEventHelper.m1635a(this.f1172f))) % 360.0f;
                    if (a > 180.0f) {
                        a -= 360.0f;
                    } else if (a < -180.0f) {
                        a += 360.0f;
                    }
                    if (Math.abs(this.f1171e - a) >= 3.0f) {
                        if (Float.isNaN(a)) {
                            a = 0.0f;
                        }
                        this.f1171e = a;
                        if (this.f1174h != null) {
                            try {
                                this.f1173g.moveCamera(CameraUpdateFactoryDelegate.changeBearing(this.f1171e));
                                this.f1174h.setRotateAngle(-this.f1171e);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        this.f1169c = System.currentTimeMillis();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public static int m1635a(Context context) {
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return -90;
            default:
                return 0;
        }
    }
}
