package com.amap.api.mapcore2d;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* renamed from: com.amap.api.mapcore2d.j */
class CameraAnimator {
    /* renamed from: I */
    private static float f2936I = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    /* renamed from: J */
    private static final float[] f2937J = new float[101];
    /* renamed from: K */
    private static final float[] f2938K = new float[101];
    /* renamed from: O */
    private static float f2939O = 8.0f;
    /* renamed from: P */
    private static float f2940P;
    /* renamed from: A */
    private float f2941A;
    /* renamed from: B */
    private float f2942B;
    /* renamed from: C */
    private boolean f2943C;
    /* renamed from: D */
    private Interpolator f2944D;
    /* renamed from: E */
    private boolean f2945E;
    /* renamed from: F */
    private float f2946F;
    /* renamed from: G */
    private int f2947G;
    /* renamed from: H */
    private float f2948H;
    /* renamed from: L */
    private float f2949L;
    /* renamed from: M */
    private final float f2950M;
    /* renamed from: N */
    private float f2951N;
    /* renamed from: a */
    private int f2952a;
    /* renamed from: b */
    private int f2953b;
    /* renamed from: c */
    private int f2954c;
    /* renamed from: d */
    private float f2955d;
    /* renamed from: e */
    private float f2956e;
    /* renamed from: f */
    private float f2957f;
    /* renamed from: g */
    private int f2958g;
    /* renamed from: h */
    private int f2959h;
    /* renamed from: i */
    private float f2960i;
    /* renamed from: j */
    private float f2961j;
    /* renamed from: k */
    private float f2962k;
    /* renamed from: l */
    private int f2963l;
    /* renamed from: m */
    private int f2964m;
    /* renamed from: n */
    private int f2965n;
    /* renamed from: o */
    private int f2966o;
    /* renamed from: p */
    private int f2967p;
    /* renamed from: q */
    private int f2968q;
    /* renamed from: r */
    private float f2969r;
    /* renamed from: s */
    private float f2970s;
    /* renamed from: t */
    private float f2971t;
    /* renamed from: u */
    private long f2972u;
    /* renamed from: v */
    private long f2973v;
    /* renamed from: w */
    private float f2974w;
    /* renamed from: x */
    private float f2975x;
    /* renamed from: y */
    private float f2976y;
    /* renamed from: z */
    private float f2977z;

    static {
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3;
            float f4;
            float f5 = ((float) i) / 100.0f;
            float f6 = 1.0f;
            float f7 = f2;
            while (true) {
                f2 = ((f6 - f7) / 2.0f) + f7;
                f3 = (3.0f * f2) * (1.0f - f2);
                f4 = ((((1.0f - f2) * 0.175f) + (0.35000002f * f2)) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f4 - f5)) < 1.0E-5d) {
                    break;
                } else if (f4 > f5) {
                    f6 = f2;
                } else {
                    f7 = f2;
                }
            }
            f2937J[i] = (f2 * (f2 * f2)) + (f3 * (((1.0f - f2) * 0.5f) + f2));
            f6 = 1.0f;
            while (true) {
                f2 = ((f6 - f) / 2.0f) + f;
                f3 = (3.0f * f2) * (1.0f - f2);
                f4 = ((((1.0f - f2) * 0.5f) + f2) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f4 - f5)) < 1.0E-5d) {
                    break;
                } else if (f4 > f5) {
                    f6 = f2;
                } else {
                    f = f2;
                }
            }
            f2938K[i] = (f2 * (f2 * f2)) + ((((1.0f - f2) * 0.175f) + (0.35000002f * f2)) * f3);
            i++;
            f2 = f7;
        }
        float[] fArr = f2937J;
        f2938K[100] = 1.0f;
        fArr[100] = 1.0f;
        f2940P = 1.0f;
        f2940P = 1.0f / CameraAnimator.m4301a(1.0f);
    }

    public CameraAnimator(Context context) {
        this(context, null);
    }

    private CameraAnimator(Context context, Interpolator interpolator) {
        this(context, interpolator, context.getApplicationInfo().targetSdkVersion >= 11);
    }

    private CameraAnimator(Context context, Interpolator interpolator, boolean z) {
        this.f2948H = ViewConfiguration.getScrollFriction();
        this.f2943C = true;
        this.f2944D = interpolator;
        this.f2950M = context.getResources().getDisplayMetrics().density * 160.0f;
        this.f2949L = m4302b(ViewConfiguration.getScrollFriction());
        this.f2945E = z;
        this.f2951N = m4302b(0.84f);
    }

    /* renamed from: b */
    private float m4302b(float f) {
        return (386.0878f * this.f2950M) * f;
    }

    /* renamed from: a */
    public final boolean mo10294a() {
        return this.f2943C;
    }

    /* renamed from: a */
    public final void mo10293a(boolean z) {
        this.f2943C = z;
    }

    /* renamed from: b */
    public final int mo10295b() {
        return this.f2967p;
    }

    /* renamed from: c */
    public final int mo10296c() {
        return this.f2968q;
    }

    /* renamed from: d */
    public final float mo10297d() {
        return this.f2969r;
    }

    /* renamed from: e */
    public final float mo10298e() {
        return this.f2970s;
    }

    /* renamed from: f */
    public final float mo10299f() {
        return this.f2971t;
    }

    /* renamed from: g */
    public boolean mo10300g() {
        if (this.f2943C) {
            return false;
        }
        int currentAnimationTimeMillis = (int) (AnimationUtils.currentAnimationTimeMillis() - this.f2972u);
        if (((long) currentAnimationTimeMillis) < this.f2973v) {
            float f;
            switch (this.f2952a) {
                case 1:
                    float f2 = ((float) currentAnimationTimeMillis) / ((float) this.f2973v);
                    int i = (int) (100.0f * f2);
                    float f3 = 1.0f;
                    f = 0.0f;
                    if (i < 100) {
                        f3 = ((float) i) / 100.0f;
                        f = ((float) (i + 1)) / 100.0f;
                        float f4 = f2937J[i];
                        f = (f2937J[i + 1] - f4) / (f - f3);
                        f3 = ((f2 - f3) * f) + f4;
                    }
                    this.f2946F = ((f * ((float) this.f2947G)) / ((float) this.f2973v)) * 1000.0f;
                    this.f2967p = this.f2953b + Math.round(((float) (this.f2958g - this.f2953b)) * f3);
                    this.f2967p = Math.min(this.f2967p, this.f2964m);
                    this.f2967p = Math.max(this.f2967p, this.f2963l);
                    this.f2968q = this.f2954c + Math.round(f3 * ((float) (this.f2959h - this.f2954c)));
                    this.f2968q = Math.min(this.f2968q, this.f2966o);
                    this.f2968q = Math.max(this.f2968q, this.f2965n);
                    if (this.f2967p == this.f2958g && this.f2968q == this.f2959h) {
                        this.f2943C = true;
                        break;
                    }
                case 2:
                    f = ((float) currentAnimationTimeMillis) * this.f2974w;
                    if (this.f2944D == null) {
                        f = CameraAnimator.m4301a(f);
                    } else {
                        f = this.f2944D.getInterpolation(f);
                    }
                    this.f2967p = this.f2953b + Math.round(this.f2975x * f);
                    this.f2968q = this.f2954c + Math.round(this.f2976y * f);
                    this.f2969r = this.f2955d + (this.f2977z * f);
                    this.f2970s = this.f2956e + (this.f2941A * f);
                    this.f2971t = (f * this.f2942B) + this.f2957f;
                    break;
            }
        }
        this.f2967p = this.f2958g;
        this.f2968q = this.f2959h;
        this.f2969r = this.f2960i;
        this.f2970s = this.f2961j;
        this.f2971t = this.f2962k;
        this.f2943C = true;
        return true;
    }

    /* renamed from: a */
    static float m4301a(float f) {
        float f2 = f2939O * f;
        if (f2 < 1.0f) {
            f2 -= 1.0f - ((float) Math.exp((double) (-f2)));
        } else {
            f2 = ((1.0f - ((float) Math.exp((double) (1.0f - f2)))) * (1.0f - 0.36787945f)) + 0.36787945f;
        }
        return f2 * f2940P;
    }

    /* renamed from: h */
    public final int mo10301h() {
        return this.f2952a;
    }
}
