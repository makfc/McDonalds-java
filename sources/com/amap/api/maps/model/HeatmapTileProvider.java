package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.p000v4.util.LongSparseArray;
import android.util.Log;
import com.amap.api.mapcore.util.Bounds;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT = new Gradient(f3172a, f3173b);
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    /* renamed from: a */
    private static final int[] f3172a = new int[]{Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
    /* renamed from: b */
    private static final float[] f3173b = new float[]{0.2f, 1.0f};
    /* renamed from: c */
    private PointQuadTree f3174c;
    /* renamed from: d */
    private Collection<WeightedLatLng> f3175d;
    /* renamed from: e */
    private Bounds f3176e;
    /* renamed from: f */
    private int f3177f;
    /* renamed from: g */
    private Gradient f3178g;
    /* renamed from: h */
    private int[] f3179h;
    /* renamed from: i */
    private double[] f3180i;
    /* renamed from: j */
    private double f3181j;
    /* renamed from: k */
    private double[] f3182k;

    public static class Builder {
        /* renamed from: a */
        private Collection<WeightedLatLng> f3168a;
        /* renamed from: b */
        private int f3169b = 12;
        /* renamed from: c */
        private Gradient f3170c = HeatmapTileProvider.DEFAULT_GRADIENT;
        /* renamed from: d */
        private double f3171d = 0.6d;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.m4473d(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.f3168a = collection;
            return this;
        }

        public Builder radius(int i) {
            this.f3169b = Math.max(10, Math.min(i, 50));
            return this;
        }

        public Builder gradient(Gradient gradient) {
            this.f3170c = gradient;
            return this;
        }

        public Builder transparency(double d) {
            this.f3171d = Math.max(0.0d, Math.min(d, 1.0d));
            return this;
        }

        public HeatmapTileProvider build() {
            if (this.f3168a != null && this.f3168a.size() != 0) {
                return new HeatmapTileProvider(this, null);
            }
            try {
                throw new AMapException("No input points.");
            } catch (AMapException e) {
                Log.e(MapTilsCacheAndResManager.AUTONAVI_PATH, e.getErrorMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    /* synthetic */ HeatmapTileProvider(Builder builder, C1053b c1053b) {
        this(builder);
    }

    private HeatmapTileProvider(Builder builder) {
        this.f3175d = builder.f3168a;
        this.f3177f = builder.f3169b;
        this.f3178g = builder.f3170c;
        if (this.f3178g == null || !this.f3178g.isAvailable()) {
            this.f3178g = DEFAULT_GRADIENT;
        }
        this.f3181j = builder.f3171d;
        this.f3180i = m4469a(this.f3177f, ((double) this.f3177f) / 3.0d);
        m4467a(this.f3178g);
        m4472c(this.f3175d);
    }

    /* renamed from: c */
    private void m4472c(Collection<WeightedLatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (WeightedLatLng weightedLatLng : collection) {
            if (weightedLatLng.latLng.latitude < 85.0d && weightedLatLng.latLng.latitude > -85.0d) {
                arrayList.add(weightedLatLng);
            }
        }
        this.f3175d = arrayList;
        this.f3176e = m4465a(this.f3175d);
        this.f3174c = new PointQuadTree(this.f3176e);
        for (WeightedLatLng weightedLatLng2 : this.f3175d) {
            this.f3174c.mo11075a(weightedLatLng2);
        }
        this.f3182k = m4468a(this.f3177f);
    }

    /* renamed from: d */
    private static Collection<WeightedLatLng> m4473d(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (LatLng weightedLatLng : collection) {
            arrayList.add(new WeightedLatLng(weightedLatLng));
        }
        return arrayList;
    }

    public Tile getTile(int i, int i2, int i3) {
        double d;
        double pow = 1.0d / Math.pow(2.0d, (double) i3);
        double d2 = (((double) this.f3177f) * pow) / 256.0d;
        double d3 = ((2.0d * d2) + pow) / ((double) ((this.f3177f * 2) + 256));
        double d4 = (((double) i) * pow) - d2;
        double d5 = (((double) (i + 1)) * pow) + d2;
        double d6 = (((double) i2) * pow) - d2;
        double d7 = (pow * ((double) (i2 + 1))) + d2;
        ArrayList arrayList = new ArrayList();
        Collection a;
        if (d4 < 0.0d) {
            a = this.f3174c.mo11074a(new Bounds(1.0d + d4, 1.0d, d6, d7));
            d = -1.0d;
        } else if (d5 > 1.0d) {
            a = this.f3174c.mo11074a(new Bounds(0.0d, d5 - 1.0d, d6, d7));
            d = 1.0d;
        } else {
            Object a2 = arrayList;
            d = 0.0d;
        }
        Bounds bounds = new Bounds(d4, d5, d6, d7);
        if (!bounds.mo9222a(new Bounds(this.f3176e.f1692a - d2, this.f3176e.f1694c + d2, this.f3176e.f1693b - d2, d2 + this.f3176e.f1695d))) {
            return TileProvider.NO_TILE;
        }
        Collection<WeightedLatLng> a3 = this.f3174c.mo11074a(bounds);
        if (a3.isEmpty()) {
            return TileProvider.NO_TILE;
        }
        DPoint point;
        int i4;
        int i5;
        double[] dArr;
        double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, new int[]{(this.f3177f * 2) + 256, (this.f3177f * 2) + 256});
        for (WeightedLatLng weightedLatLng : a3) {
            point = weightedLatLng.getPoint();
            i4 = (int) ((point.f4558x - d4) / d3);
            i5 = (int) ((point.f4559y - d6) / d3);
            dArr = dArr2[i4];
            dArr[i5] = dArr[i5] + weightedLatLng.intensity;
        }
        for (WeightedLatLng weightedLatLng2 : a2) {
            point = weightedLatLng2.getPoint();
            i4 = (int) (((point.f4558x + d) - d4) / d3);
            i5 = (int) ((point.f4559y - d6) / d3);
            dArr = dArr2[i4];
            dArr[i5] = dArr[i5] + weightedLatLng2.intensity;
        }
        return m4466a(m4464a(m4470a(dArr2, this.f3180i), this.f3179h, this.f3182k[i3]));
    }

    /* renamed from: a */
    private void m4467a(Gradient gradient) {
        this.f3178g = gradient;
        this.f3179h = gradient.generateColorMap(this.f3181j);
    }

    /* renamed from: a */
    private double[] m4468a(int i) {
        int i2 = 11;
        double[] dArr = new double[21];
        for (int i3 = 5; i3 < 11; i3++) {
            dArr[i3] = m4463a(this.f3175d, this.f3176e, i, (int) (1280.0d * Math.pow(2.0d, (double) i3)));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
        }
        while (i2 < 21) {
            dArr[i2] = dArr[10];
            i2++;
        }
        return dArr;
    }

    /* renamed from: a */
    private static Tile m4466a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(256, 256, byteArrayOutputStream.toByteArray());
    }

    /* renamed from: a */
    static Bounds m4465a(Collection<WeightedLatLng> collection) {
        Iterator it = collection.iterator();
        WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
        double d = weightedLatLng.getPoint().f4558x;
        double d2 = weightedLatLng.getPoint().f4558x;
        double d3 = weightedLatLng.getPoint().f4559y;
        double d4 = weightedLatLng.getPoint().f4559y;
        while (it.hasNext()) {
            weightedLatLng = (WeightedLatLng) it.next();
            double d5 = weightedLatLng.getPoint().f4558x;
            double d6 = weightedLatLng.getPoint().f4559y;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d2) {
                d2 = d5;
            }
            if (d6 < d3) {
                d3 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
        }
        return new Bounds(d, d2, d3, d4);
    }

    /* renamed from: a */
    static double[] m4469a(int i, double d) {
        double[] dArr = new double[((i * 2) + 1)];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d) * d));
        }
        return dArr;
    }

    /* renamed from: a */
    static double[][] m4470a(double[][] dArr, double[] dArr2) {
        int i;
        double d;
        int i2;
        double[] dArr3;
        int floor = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int length = dArr.length;
        int i3 = length - (floor * 2);
        int i4 = (floor + i3) - 1;
        double[][] dArr4 = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, length});
        int i5 = 0;
        while (i5 < length) {
            for (i = 0; i < length; i++) {
                d = dArr[i5][i];
                if (d != 0.0d) {
                    i2 = (i4 < i5 + floor ? i4 : i5 + floor) + 1;
                    int i6 = floor > i5 - floor ? floor : i5 - floor;
                    while (i6 < i2) {
                        dArr3 = dArr4[i6];
                        dArr3[i] = dArr3[i] + (dArr2[i6 - (i5 - floor)] * d);
                        i6++;
                    }
                }
            }
            i5++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, new int[]{i3, i3});
        for (i3 = floor; i3 < i4 + 1; i3++) {
            i5 = 0;
            while (i5 < length) {
                d = dArr4[i3][i5];
                if (d != 0.0d) {
                    i2 = (i4 < i5 + floor ? i4 : i5 + floor) + 1;
                    i = floor > i5 - floor ? floor : i5 - floor;
                    while (i < i2) {
                        dArr3 = dArr5[i3 - floor];
                        int i7 = i - floor;
                        dArr3[i7] = dArr3[i7] + (dArr2[i - (i5 - floor)] * d);
                        i++;
                    }
                }
                i5++;
            }
        }
        return dArr5;
    }

    /* renamed from: a */
    static Bitmap m4464a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[(length2 * length2)];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    /* renamed from: a */
    static double m4463a(Collection<WeightedLatLng> collection, Bounds bounds, int i, int i2) {
        double d = bounds.f1692a;
        double d2 = bounds.f1694c;
        double d3 = bounds.f1693b;
        double d4 = bounds.f1695d;
        double d5 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / (d2 - d > d4 - d3 ? d2 - d : d4 - d3);
        LongSparseArray longSparseArray = new LongSparseArray();
        d2 = 0.0d;
        Iterator it = collection.iterator();
        while (true) {
            d4 = d2;
            if (!it.hasNext()) {
                return d4;
            }
            LongSparseArray longSparseArray2;
            WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
            int i3 = (int) ((weightedLatLng.getPoint().f4558x - d) * d5);
            int i4 = (int) ((weightedLatLng.getPoint().f4559y - d3) * d5);
            LongSparseArray longSparseArray3 = (LongSparseArray) longSparseArray.get((long) i3);
            if (longSparseArray3 == null) {
                longSparseArray3 = new LongSparseArray();
                longSparseArray.put((long) i3, longSparseArray3);
                longSparseArray2 = longSparseArray3;
            } else {
                longSparseArray2 = longSparseArray3;
            }
            Double d6 = (Double) longSparseArray2.get((long) i4);
            if (d6 == null) {
                d6 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(weightedLatLng.intensity + d6.doubleValue());
            longSparseArray2.put((long) i4, valueOf);
            if (valueOf.doubleValue() > d4) {
                d2 = valueOf.doubleValue();
            } else {
                d2 = d4;
            }
        }
    }

    public int getTileHeight() {
        return 256;
    }

    public int getTileWidth() {
        return 256;
    }
}
