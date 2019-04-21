package com.amap.api.maps.model;

import android.graphics.Color;
import android.util.Log;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import java.util.HashMap;

public class Gradient {
    /* renamed from: a */
    private int f3151a;
    /* renamed from: b */
    private int[] f3152b;
    /* renamed from: c */
    private float[] f3153c;
    /* renamed from: d */
    private boolean f3154d;

    /* renamed from: com.amap.api.maps.model.Gradient$a */
    private class C1051a {
        /* renamed from: b */
        private final int f3148b;
        /* renamed from: c */
        private final int f3149c;
        /* renamed from: d */
        private final float f3150d;

        /* synthetic */ C1051a(Gradient gradient, int i, int i2, float f, C1052a c1052a) {
            this(i, i2, f);
        }

        private C1051a(int i, int i2, float f) {
            this.f3148b = i;
            this.f3149c = i2;
            this.f3150d = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        int i2 = 1;
        this.f3154d = true;
        if (iArr == null || fArr == null) {
            try {
                throw new AMapException("colors and startPoints should not be null");
            } catch (AMapException e) {
                this.f3154d = false;
                Log.e(MapTilsCacheAndResManager.AUTONAVI_PATH, e.getErrorMessage());
                e.printStackTrace();
            }
        } else if (iArr.length != fArr.length) {
            throw new AMapException("colors and startPoints should be same length");
        } else if (iArr.length == 0) {
            throw new AMapException("No colors have been defined");
        } else {
            while (i2 < fArr.length) {
                if (fArr[i2] <= fArr[i2 - 1]) {
                    throw new AMapException("startPoints should be in increasing order");
                }
                i2++;
            }
            this.f3151a = i;
            this.f3152b = new int[iArr.length];
            this.f3153c = new float[fArr.length];
            System.arraycopy(iArr, 0, this.f3152b, 0, iArr.length);
            System.arraycopy(fArr, 0, this.f3153c, 0, fArr.length);
            this.f3154d = true;
        }
    }

    /* renamed from: a */
    private HashMap<Integer, C1051a> m4457a() {
        HashMap hashMap = new HashMap();
        if (this.f3153c[0] != 0.0f) {
            int argb = Color.argb(0, Color.red(this.f3152b[0]), Color.green(this.f3152b[0]), Color.blue(this.f3152b[0]));
            hashMap.put(Integer.valueOf(0), new C1051a(this, argb, this.f3152b[0], this.f3153c[0] * ((float) this.f3151a), null));
        }
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3152b.length) {
                break;
            }
            hashMap.put(Integer.valueOf((int) (((float) this.f3151a) * this.f3153c[i2 - 1])), new C1051a(this, this.f3152b[i2 - 1], this.f3152b[i2], (this.f3153c[i2] - this.f3153c[i2 - 1]) * ((float) this.f3151a), null));
            i = i2 + 1;
        }
        if (this.f3153c[this.f3153c.length - 1] != 1.0f) {
            int length = this.f3153c.length - 1;
            hashMap.put(Integer.valueOf((int) (((float) this.f3151a) * this.f3153c[length])), new C1051a(this, this.f3152b[length], this.f3152b[length], ((float) this.f3151a) * (1.0f - this.f3153c[length]), null));
        }
        return hashMap;
    }

    /* Access modifiers changed, original: protected */
    public int[] generateColorMap(double d) {
        int i = 0;
        HashMap a = m4457a();
        int[] iArr = new int[this.f3151a];
        int i2 = 0;
        C1051a c1051a = (C1051a) a.get(Integer.valueOf(0));
        int i3 = 0;
        while (i2 < this.f3151a) {
            int i4;
            C1051a c1051a2;
            if (a.containsKey(Integer.valueOf(i2))) {
                i4 = i2;
                c1051a2 = (C1051a) a.get(Integer.valueOf(i2));
            } else {
                c1051a2 = c1051a;
                i4 = i3;
            }
            iArr[i2] = m4456a(c1051a2.f3148b, c1051a2.f3149c, ((float) (i2 - i4)) / c1051a2.f3150d);
            i2++;
            i3 = i4;
            c1051a = c1051a2;
        }
        if (d != 1.0d) {
            while (i < this.f3151a) {
                i3 = iArr[i];
                iArr[i] = Color.argb((int) (((double) Color.alpha(i3)) * d), Color.red(i3), Color.green(i3), Color.blue(i3));
                i++;
            }
        }
        return iArr;
    }

    /* renamed from: a */
    static int m4456a(int i, int i2, float f) {
        int i3 = 0;
        int alpha = (int) ((((float) (Color.alpha(i2) - Color.alpha(i))) * f) + ((float) Color.alpha(i)));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        while (i3 < 3) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
            i3++;
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    /* Access modifiers changed, original: protected */
    public boolean isAvailable() {
        return this.f3154d;
    }
}
