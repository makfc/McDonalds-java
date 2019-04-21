package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.z */
public class IndoorFloorSwitchView extends ScrollView {
    /* renamed from: a */
    public static final String f2209a = IndoorFloorSwitchView.class.getSimpleName();
    /* renamed from: b */
    int f2210b = 1;
    /* renamed from: c */
    private Context f2211c;
    /* renamed from: d */
    private LinearLayout f2212d;
    /* renamed from: e */
    private int f2213e = 0;
    /* renamed from: f */
    private List<String> f2214f;
    /* renamed from: g */
    private int f2215g = -1;
    /* renamed from: h */
    private int f2216h;
    /* renamed from: i */
    private Bitmap f2217i = null;
    /* renamed from: j */
    private int f2218j = Color.parseColor("#eeffffff");
    /* renamed from: k */
    private int f2219k = Color.parseColor("#44383838");
    /* renamed from: l */
    private int f2220l = 4;
    /* renamed from: m */
    private int f2221m = 1;
    /* renamed from: n */
    private int f2222n;
    /* renamed from: o */
    private int f2223o;
    /* renamed from: p */
    private Runnable f2224p;
    /* renamed from: q */
    private int f2225q = 50;
    /* renamed from: r */
    private C0793a f2226r;

    /* compiled from: IndoorFloorSwitchView */
    /* renamed from: com.amap.api.mapcore.util.z$a */
    public interface C0793a {
        /* renamed from: a */
        void mo9022a(int i);
    }

    /* compiled from: IndoorFloorSwitchView */
    /* renamed from: com.amap.api.mapcore.util.z$1 */
    class C08801 implements Runnable {
        C08801() {
        }

        public void run() {
            if (IndoorFloorSwitchView.this.f2223o - IndoorFloorSwitchView.this.getScrollY() == 0) {
                final int a = IndoorFloorSwitchView.this.f2223o % IndoorFloorSwitchView.this.f2213e;
                final int a2 = IndoorFloorSwitchView.this.f2223o / IndoorFloorSwitchView.this.f2213e;
                if (a == 0) {
                    IndoorFloorSwitchView.this.f2210b = a2 + IndoorFloorSwitchView.this.f2221m;
                    IndoorFloorSwitchView.this.m2958g();
                    return;
                } else if (a > IndoorFloorSwitchView.this.f2213e / 2) {
                    IndoorFloorSwitchView.this.post(new Runnable() {
                        public void run() {
                            IndoorFloorSwitchView.this.smoothScrollTo(0, (IndoorFloorSwitchView.this.f2223o - a) + IndoorFloorSwitchView.this.f2213e);
                            IndoorFloorSwitchView.this.f2210b = (a2 + IndoorFloorSwitchView.this.f2221m) + 1;
                            IndoorFloorSwitchView.this.m2958g();
                        }
                    });
                    return;
                } else {
                    IndoorFloorSwitchView.this.post(new Runnable() {
                        public void run() {
                            IndoorFloorSwitchView.this.smoothScrollTo(0, IndoorFloorSwitchView.this.f2223o - a);
                            IndoorFloorSwitchView.this.f2210b = a2 + IndoorFloorSwitchView.this.f2221m;
                            IndoorFloorSwitchView.this.m2958g();
                        }
                    });
                    return;
                }
            }
            IndoorFloorSwitchView.this.f2223o = IndoorFloorSwitchView.this.getScrollY();
            IndoorFloorSwitchView.this.postDelayed(IndoorFloorSwitchView.this.f2224p, (long) IndoorFloorSwitchView.this.f2225q);
        }
    }

    /* compiled from: IndoorFloorSwitchView */
    /* renamed from: com.amap.api.mapcore.util.z$2 */
    class C08812 extends Drawable {
        C08812() {
        }

        public void draw(Canvas canvas) {
            try {
                m2939a(canvas);
                m2940b(canvas);
                m2941c(canvas);
            } catch (Throwable th) {
            }
        }

        /* renamed from: a */
        private void m2939a(Canvas canvas) {
            canvas.drawColor(IndoorFloorSwitchView.this.f2218j);
        }

        /* renamed from: b */
        private void m2940b(Canvas canvas) {
            Paint paint = new Paint();
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            rect.left = 0;
            rect.top = 0;
            rect.right = IndoorFloorSwitchView.this.f2217i.getWidth() + 0;
            rect.bottom = IndoorFloorSwitchView.this.f2217i.getHeight() + 0;
            rect2.left = 0;
            rect2.top = IndoorFloorSwitchView.this.m2956f()[0];
            rect2.right = IndoorFloorSwitchView.this.f2216h + 0;
            rect2.bottom = IndoorFloorSwitchView.this.m2956f()[1];
            canvas.drawBitmap(IndoorFloorSwitchView.this.f2217i, rect, rect2, paint);
        }

        /* renamed from: c */
        private void m2941c(Canvas canvas) {
            Paint paint = new Paint();
            Rect clipBounds = canvas.getClipBounds();
            paint.setColor(IndoorFloorSwitchView.this.f2219k);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth((float) IndoorFloorSwitchView.this.f2220l);
            canvas.drawRect(clipBounds, paint);
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }

        public int getOpacity() {
            return 0;
        }
    }

    public IndoorFloorSwitchView(Context context) {
        super(context);
        m2947a(context);
    }

    public IndoorFloorSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m2947a(context);
    }

    public IndoorFloorSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m2947a(context);
    }

    /* renamed from: a */
    private void m2947a(Context context) {
        this.f2211c = context;
        setVerticalScrollBarEnabled(false);
        try {
            if (this.f2217i == null) {
                InputStream open = ResourcesUtil.m2327a(context).open("map_indoor_select.png");
                this.f2217i = BitmapFactoryInstrumentation.decodeStream(open);
                open.close();
            }
        } catch (Throwable th) {
        }
        this.f2212d = new LinearLayout(context);
        this.f2212d.setOrientation(1);
        addView(this.f2212d);
        this.f2224p = new C08801();
    }

    /* renamed from: a */
    public void mo9594a() {
        this.f2223o = getScrollY();
        postDelayed(this.f2224p, (long) this.f2225q);
    }

    /* renamed from: e */
    private void m2954e() {
        if (this.f2214f != null && this.f2214f.size() != 0) {
            this.f2212d.removeAllViews();
            this.f2222n = (this.f2221m * 2) + 1;
            for (int size = this.f2214f.size() - 1; size >= 0; size--) {
                this.f2212d.addView(m2949b((String) this.f2214f.get(size)));
            }
            m2946a(0);
        }
    }

    /* renamed from: b */
    private TextView m2949b(String str) {
        View textView = new TextView(this.f2211c);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setTextSize(2, 16.0f);
        textView.setText(str);
        textView.setGravity(17);
        textView.getPaint().setFakeBoldText(true);
        int a = IndoorFloorSwitchView.m2942a(this.f2211c, 8.0f);
        int a2 = IndoorFloorSwitchView.m2942a(this.f2211c, 6.0f);
        textView.setPadding(a, a2, a, a2);
        if (this.f2213e == 0) {
            this.f2213e = IndoorFloorSwitchView.m2943a(textView);
            this.f2212d.setLayoutParams(new LayoutParams(-2, this.f2213e * this.f2222n));
            setLayoutParams(new LinearLayout.LayoutParams(-2, this.f2213e * this.f2222n));
        }
        return textView;
    }

    /* renamed from: a */
    private void m2946a(int i) {
        int i2 = (i / this.f2213e) + this.f2221m;
        int i3 = i % this.f2213e;
        int i4 = i / this.f2213e;
        if (i3 == 0) {
            i3 = this.f2221m + i4;
        } else if (i3 > this.f2213e / 2) {
            i3 = (this.f2221m + i4) + 1;
        } else {
            i3 = i2;
        }
        int childCount = this.f2212d.getChildCount();
        i4 = 0;
        while (i4 < childCount) {
            TextView textView = (TextView) this.f2212d.getChildAt(i4);
            if (textView != null) {
                if (i3 == i4) {
                    textView.setTextColor(Color.parseColor("#0288ce"));
                } else {
                    textView.setTextColor(Color.parseColor("#bbbbbb"));
                }
                i4++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public void mo9598a(String[] strArr) {
        int i;
        if (this.f2214f == null) {
            this.f2214f = new ArrayList();
        }
        this.f2214f.clear();
        for (Object add : strArr) {
            this.f2214f.add(add);
        }
        for (i = 0; i < this.f2221m; i++) {
            this.f2214f.add(0, "");
            this.f2214f.add("");
        }
        m2954e();
    }

    public void setBackgroundColor(int i) {
        this.f2218j = i;
    }

    /* renamed from: b */
    public void mo9599b() {
        if (this.f2217i != null && !this.f2217i.isRecycled()) {
            this.f2217i.recycle();
            this.f2217i = null;
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f2216h == 0) {
            this.f2216h = ((Activity) this.f2211c).getWindowManager().getDefaultDisplay().getWidth();
        }
        super.setBackgroundDrawable(new C08812());
    }

    /* renamed from: f */
    private int[] m2956f() {
        if (null != null) {
            return null;
        }
        return new int[]{this.f2213e * this.f2221m, this.f2213e * (this.f2221m + 1)};
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f2216h = i;
        setBackgroundDrawable(null);
    }

    /* Access modifiers changed, original: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        m2946a(i2);
        if (i2 > i4) {
            this.f2215g = 1;
        } else {
            this.f2215g = 0;
        }
    }

    /* renamed from: g */
    private void m2958g() {
        if (this.f2226r != null) {
            try {
                this.f2226r.mo9022a(mo9600c());
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public void mo9596a(String str) {
        if (this.f2214f != null && this.f2214f.size() != 0) {
            final int size = ((this.f2214f.size() - this.f2221m) - 1) - this.f2214f.indexOf(str);
            this.f2210b = this.f2221m + size;
            post(new Runnable() {
                public void run() {
                    IndoorFloorSwitchView.this.smoothScrollTo(0, size * IndoorFloorSwitchView.this.f2213e);
                }
            });
        }
    }

    /* renamed from: c */
    public int mo9600c() {
        if (this.f2214f == null || this.f2214f.size() == 0) {
            return 0;
        }
        return Math.min(this.f2214f.size() - (this.f2221m * 2), Math.max(0, ((this.f2214f.size() - 1) - this.f2210b) - this.f2221m));
    }

    public void fling(int i) {
        super.fling(i / 3);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            mo9594a();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    public void mo9595a(C0793a c0793a) {
        this.f2226r = c0793a;
    }

    /* renamed from: a */
    public static int m2942a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    /* renamed from: a */
    public static int m2943a(View view) {
        IndoorFloorSwitchView.m2950b(view);
        return view.getMeasuredHeight();
    }

    /* renamed from: b */
    public static void m2950b(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(536870911, ExploreByTouchHelper.INVALID_ID));
    }

    /* renamed from: a */
    public void mo9597a(boolean z) {
        if (z) {
            if (!mo9601d()) {
                setVisibility(0);
            }
        } else if (mo9601d()) {
            setVisibility(8);
        }
    }

    /* renamed from: d */
    public boolean mo9601d() {
        return getVisibility() == 0;
    }
}
