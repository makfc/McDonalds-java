package com.amap.api.mapcore2d;

/* renamed from: com.amap.api.mapcore2d.u */
public class GeoPoint {
    /* renamed from: a */
    private long f3061a;
    /* renamed from: b */
    private long f3062b;
    /* renamed from: c */
    private double f3063c;
    /* renamed from: d */
    private double f3064d;

    public GeoPoint() {
        this.f3061a = Long.MIN_VALUE;
        this.f3062b = Long.MIN_VALUE;
        this.f3063c = Double.MIN_VALUE;
        this.f3064d = Double.MIN_VALUE;
        this.f3061a = 0;
        this.f3062b = 0;
    }

    public GeoPoint(int i, int i2) {
        this.f3061a = Long.MIN_VALUE;
        this.f3062b = Long.MIN_VALUE;
        this.f3063c = Double.MIN_VALUE;
        this.f3064d = Double.MIN_VALUE;
        this.f3061a = (long) i;
        this.f3062b = (long) i2;
    }

    GeoPoint(double d, double d2, boolean z) {
        this.f3061a = Long.MIN_VALUE;
        this.f3062b = Long.MIN_VALUE;
        this.f3063c = Double.MIN_VALUE;
        this.f3064d = Double.MIN_VALUE;
        if (z) {
            this.f3061a = (long) (d * 1000000.0d);
            this.f3062b = (long) (d2 * 1000000.0d);
            return;
        }
        this.f3063c = d;
        this.f3064d = d2;
    }

    /* renamed from: a */
    public void mo10333a(double d) {
        this.f3064d = d;
    }

    /* renamed from: b */
    public void mo10335b(double d) {
        this.f3063c = d;
    }

    private GeoPoint(double d, double d2, long j, long j2) {
        this.f3061a = Long.MIN_VALUE;
        this.f3062b = Long.MIN_VALUE;
        this.f3063c = Double.MIN_VALUE;
        this.f3064d = Double.MIN_VALUE;
        this.f3063c = d;
        this.f3064d = d2;
        this.f3061a = j;
        this.f3062b = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        if (this.f3061a != geoPoint.f3061a) {
            return false;
        }
        if (this.f3062b != geoPoint.f3062b) {
            return false;
        }
        if (Double.doubleToLongBits(this.f3063c) != Double.doubleToLongBits(geoPoint.f3063c)) {
            return false;
        }
        if (Double.doubleToLongBits(this.f3064d) != Double.doubleToLongBits(geoPoint.f3064d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = ((((int) (this.f3061a ^ (this.f3061a >>> 32))) + 31) * 31) + ((int) (this.f3062b ^ (this.f3062b >>> 32)));
        long doubleToLongBits = Double.doubleToLongBits(this.f3063c);
        i = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.f3064d);
        return (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    /* renamed from: a */
    public int mo10332a() {
        return (int) this.f3062b;
    }

    /* renamed from: b */
    public int mo10334b() {
        return (int) this.f3061a;
    }

    /* renamed from: c */
    public long mo10336c() {
        return this.f3062b;
    }

    /* renamed from: d */
    public long mo10337d() {
        return this.f3061a;
    }

    /* renamed from: e */
    public double mo10338e() {
        if (Double.doubleToLongBits(this.f3064d) == Double.doubleToLongBits(Double.MIN_VALUE)) {
            this.f3064d = (C1043q.m4373a(this.f3062b) * 2.003750834E7d) / 180.0d;
        }
        return this.f3064d;
    }

    /* renamed from: f */
    public double mo10340f() {
        if (Double.doubleToLongBits(this.f3063c) == Double.doubleToLongBits(Double.MIN_VALUE)) {
            this.f3063c = ((Math.log(Math.tan(((C1043q.m4373a(this.f3061a) + 90.0d) * 3.141592653589793d) / 360.0d)) / 0.017453292519943295d) * 2.003750834E7d) / 180.0d;
        }
        return this.f3063c;
    }

    /* renamed from: g */
    public GeoPoint mo10341g() {
        return new GeoPoint(this.f3063c, this.f3064d, this.f3061a, this.f3062b);
    }
}
