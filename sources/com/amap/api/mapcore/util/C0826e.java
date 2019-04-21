package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;

/* compiled from: AMapDelegateImp */
/* renamed from: com.amap.api.mapcore.util.e */
class C0826e extends Handler {
    /* renamed from: a */
    final /* synthetic */ AMapDelegateImp f1841a;

    C0826e(AMapDelegateImp aMapDelegateImp) {
        this.f1841a = aMapDelegateImp;
    }

    public void handleMessage(Message message) {
        if (message != null && !this.f1841a.f1575aQ.booleanValue()) {
            this.f1841a.setRunLowFrame(false);
            CameraPosition cameraPosition;
            CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate;
            int currX;
            int currY;
            switch (message.what) {
                case 2:
                    Log.w("amapsdk", "Key验证失败：[" + C0811dm.f1759b + "]");
                    break;
                case 10:
                    cameraPosition = (CameraPosition) message.obj;
                    if (!(cameraPosition == null || this.f1841a.f1585aa == null)) {
                        this.f1841a.f1585aa.onCameraChange(cameraPosition);
                        break;
                    }
                case 11:
                    if (this.f1841a.f1564aE != null) {
                        try {
                            this.f1841a.moveCamera(this.f1841a.f1564aE);
                        } catch (Throwable th) {
                            SDKLogHandler.m2563a(th, "AMapDelegateImp", "onMapLoaded");
                            th.printStackTrace();
                        }
                    }
                    if (this.f1841a.f1558Z != null) {
                        this.f1841a.f1558Z.onMapLoaded();
                        break;
                    }
                    break;
                case 12:
                    cameraUpdateFactoryDelegate = (CameraUpdateFactoryDelegate) message.obj;
                    if (cameraUpdateFactoryDelegate != null) {
                        this.f1841a.f1638e.mo8487a(cameraUpdateFactoryDelegate);
                        break;
                    }
                    break;
                case 13:
                    if (this.f1841a.f1604at != null && this.f1841a.f1604at.computeScrollOffset()) {
                        switch (this.f1841a.f1604at.getMode()) {
                            case 2:
                                cameraUpdateFactoryDelegate = CameraUpdateFactoryDelegate.newCamera(new IPoint(this.f1841a.f1604at.getCurrX(), this.f1841a.f1604at.getCurrY()), this.f1841a.f1604at.getCurrZ(), this.f1841a.f1604at.getCurrBearing(), this.f1841a.f1604at.getCurrTilt());
                                if (this.f1841a.f1604at.isFinished()) {
                                    cameraUpdateFactoryDelegate.isChangeFinished = true;
                                }
                                cameraUpdateFactoryDelegate.isUseAnchor = this.f1841a.f1604at.isUseAnchor();
                                this.f1841a.f1638e.mo8487a(cameraUpdateFactoryDelegate);
                                break;
                            default:
                                currX = this.f1841a.f1604at.getCurrX() - this.f1841a.f1605au;
                                currY = this.f1841a.f1604at.getCurrY() - this.f1841a.f1606av;
                                this.f1841a.f1605au = this.f1841a.f1604at.getCurrX();
                                this.f1841a.f1606av = this.f1841a.f1604at.getCurrY();
                                FPoint fPoint = new FPoint((float) (currX + (this.f1841a.mo9173c() / 2)), (float) (currY + (this.f1841a.mo9174d() / 2)));
                                FPoint fPoint2 = new FPoint();
                                this.f1841a.f1542J.win2Map((int) fPoint.f4560x, (int) fPoint.f4561y, fPoint2);
                                IPoint iPoint = new IPoint();
                                this.f1841a.f1542J.map2Geo(fPoint2.f4560x, fPoint2.f4561y, iPoint);
                                cameraUpdateFactoryDelegate = CameraUpdateFactoryDelegate.changeGeoCenter(iPoint);
                                if (this.f1841a.f1604at.isFinished()) {
                                    cameraUpdateFactoryDelegate.isChangeFinished = true;
                                }
                                this.f1841a.f1638e.mo8487a(cameraUpdateFactoryDelegate);
                                break;
                        }
                    }
                case 14:
                    if (this.f1841a.f1550R != null) {
                        this.f1841a.f1550R.mo9540b();
                        break;
                    }
                    return;
                case 16:
                    Bitmap bitmap = (Bitmap) message.obj;
                    currY = message.arg1;
                    if (bitmap != null) {
                        Canvas canvas = new Canvas(bitmap);
                        if (this.f1841a.f1548P != null) {
                            this.f1841a.f1548P.onDraw(canvas);
                        }
                        if (!(this.f1841a.f1594aj == null || this.f1841a.f1595ak == null)) {
                            Bitmap drawingCache = this.f1841a.f1594aj.getDrawingCache(true);
                            if (drawingCache != null) {
                                canvas.drawBitmap(drawingCache, (float) this.f1841a.f1594aj.getLeft(), (float) this.f1841a.f1594aj.getTop(), new Paint());
                            }
                        }
                        if (this.f1841a.f1560aA != null) {
                            this.f1841a.f1560aA.onMapPrint(new BitmapDrawable(this.f1841a.f1540H.getResources(), bitmap));
                        }
                        if (this.f1841a.f1561aB != null) {
                            this.f1841a.f1561aB.onMapScreenShot(bitmap);
                            this.f1841a.f1561aB.onMapScreenShot(bitmap, currY);
                        }
                    } else {
                        if (this.f1841a.f1560aA != null) {
                            this.f1841a.f1560aA.onMapPrint(null);
                        }
                        if (this.f1841a.f1561aB != null) {
                            this.f1841a.f1561aB.onMapScreenShot(null);
                            this.f1841a.f1561aB.onMapScreenShot(null, currY);
                        }
                    }
                    this.f1841a.f1560aA = null;
                    this.f1841a.f1561aB = null;
                    break;
                case 17:
                    if (!(this.f1841a.f1594aj == null || this.f1841a.f1596al == null)) {
                        this.f1841a.f1594aj.setVisibility(0);
                    }
                    try {
                        cameraPosition = this.f1841a.getCameraPosition();
                        if (cameraPosition != null) {
                            if (cameraPosition.zoom < 10.0f || RegionUtil.m2325a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
                                this.f1841a.f1548P.setVisibility(0);
                            } else {
                                this.f1841a.f1548P.setVisibility(8);
                            }
                        }
                        if (this.f1841a.f1607aw == null || !this.f1841a.f1572aN) {
                            this.f1841a.mo9171a(true, cameraPosition);
                        }
                        if (this.f1841a.f1607aw != null) {
                            this.f1841a.f1573aO = true;
                            this.f1841a.f1607aw.onFinish();
                            this.f1841a.f1573aO = false;
                        }
                        if (!this.f1841a.f1574aP) {
                            this.f1841a.f1607aw = null;
                            break;
                        } else {
                            this.f1841a.f1574aP = false;
                            break;
                        }
                    } catch (Throwable th2) {
                        SDKLogHandler.m2563a(th2, "AMapDelegateImpGLSurfaceView", "CameraUpdateFinish");
                        break;
                    }
                case 18:
                    currX = this.f1841a.mo9173c();
                    int d = this.f1841a.mo9174d();
                    if (currX > 0 && d > 0) {
                        try {
                            CameraPosition cameraPosition2 = this.f1841a.getCameraPosition();
                            MapProjection.lonlat2Geo(cameraPosition2.target.longitude, cameraPosition2.target.latitude, new IPoint());
                            MapProjection mapProjection = new MapProjection(this.f1841a.f1539G);
                            mapProjection.setCameraHeaderAngle(cameraPosition2.tilt);
                            mapProjection.setMapAngle(cameraPosition2.bearing);
                            mapProjection.setMapZoomer(cameraPosition2.zoom);
                            mapProjection.recalculate();
                            DPoint dPoint = new DPoint();
                            this.f1841a.m2048a(mapProjection, 0, 0, dPoint);
                            LatLng latLng = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
                            this.f1841a.m2048a(mapProjection, currX, 0, dPoint);
                            LatLng latLng2 = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
                            this.f1841a.m2048a(mapProjection, 0, d, dPoint);
                            LatLng latLng3 = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
                            this.f1841a.m2048a(mapProjection, currX, d, dPoint);
                            this.f1841a.f1627bp = LatLngBounds.builder().include(latLng3).include(new LatLng(dPoint.f4559y, dPoint.f4558x, false)).include(latLng).include(latLng2).build();
                            mapProjection.recycle();
                            break;
                        } catch (Throwable th3) {
                            break;
                        }
                    }
                    this.f1841a.f1627bp = null;
                    break;
                    break;
                case 20:
                    if (this.f1841a.f1604at.isFinished() || !(this.f1841a.f1604at.getMode() == 1 || this.f1841a.f1640g == null)) {
                        this.f1841a.f1640g.mo8748b(false);
                    }
                    if (this.f1841a.f1640g != null) {
                        this.f1841a.f1640g.mo8746a(message.arg1 != 0);
                        break;
                    }
                    break;
                case 21:
                    if (this.f1841a.f1639f != null) {
                        this.f1841a.f1639f.mo8787a(this.f1841a.getZoomLevel());
                        break;
                    }
                    break;
                case 22:
                    this.f1841a.m2096p();
                    break;
            }
            this.f1841a.setRunLowFrame(false);
        }
    }
}
