package com.ensighten;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ensighten.k */
public final class C1849k {
    /* renamed from: a */
    public final Context f5928a;
    /* renamed from: b */
    public final Handler f5929b;
    /* renamed from: c */
    public boolean f5930c = false;
    /* renamed from: d */
    public boolean f5931d = false;
    /* renamed from: e */
    public boolean f5932e = false;
    /* renamed from: f */
    public boolean f5933f = false;
    /* renamed from: g */
    public Map<String, String> f5934g;
    /* renamed from: h */
    public int f5935h;
    /* renamed from: i */
    public int f5936i;
    /* renamed from: j */
    public View f5937j;
    /* renamed from: k */
    public ViewGroup f5938k;
    /* renamed from: l */
    public boolean f5939l = false;
    /* renamed from: m */
    public Runnable f5940m;
    /* renamed from: n */
    int f5941n = 25;
    /* renamed from: o */
    public Lock f5942o = new ReentrantLock();
    /* renamed from: p */
    private int f5943p;
    /* renamed from: q */
    private String f5944q;
    /* renamed from: r */
    private boolean f5945r;

    /* renamed from: com.ensighten.k$1 */
    public class C18471 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ Activity f5923a;
        /* renamed from: b */
        final /* synthetic */ String f5924b;
        /* renamed from: c */
        final /* synthetic */ String f5925c;

        public C18471(Activity activity, String str, String str2) {
            this.f5923a = activity;
            this.f5924b = str;
            this.f5925c = str2;
        }

        public final void run() {
            long currentTimeMillis;
            C1849k.this.f5942o.lock();
            long j = 0;
            if (C1846j.m7371b()) {
                j = System.currentTimeMillis();
                C1846j.m7366a("Screen Dump Performance:");
                currentTimeMillis = System.currentTimeMillis() - C1846j.m7370b("EnsightenInstrumentation:UIThreadScheduling");
                C1846j.m7366a(String.format("\tThread scheduling took %s.", new Object[]{Utils.durationToString(currentTimeMillis)}));
            }
            long j2 = j;
            try {
                Ensighten.getJavascriptProcessor().mo15487b("socket.sendMessage('screen.dump.start','');");
                try {
                    JSONObject a = C1849k.this.mo15494a(this.f5923a, C1849k.this.f5938k, C1849k.this.f5937j, this.f5924b, this.f5925c, new JSONObject());
                    if (a != null) {
                        Object jSONObjectInstrumentation;
                        currentTimeMillis = 0;
                        if (C1846j.m7371b()) {
                            currentTimeMillis = System.currentTimeMillis();
                        }
                        if (a instanceof JSONObject) {
                            jSONObjectInstrumentation = JSONObjectInstrumentation.toString(a);
                        } else {
                            jSONObjectInstrumentation = a.toString();
                        }
                        if (C1846j.m7371b()) {
                            currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                            C1846j.m7366a(String.format("\tThe conversion from json to string took %s", new Object[]{Utils.durationToString(currentTimeMillis)}));
                        }
                        Ensighten.getJavascriptProcessor();
                        Object[] objArr = new Object[2];
                        objArr[0] = jSONObjectInstrumentation;
                        C1849k c1849k = C1849k.this;
                        int i = c1849k.f5936i;
                        c1849k.f5936i = i + 1;
                        objArr[1] = Integer.valueOf(i);
                        C1843h.m7332a(String.format("socket.ws.emit('message',JSON.stringify({\"command\": 'screen.receipt', \"data\": %s}), function() { prompt(\"opcode\",\"{ opcode : 'callStaticMethod', arg1 : 'com.ensighten.Ensighten', arg2 : 'onFrameReceived', arg3 : 'int', arg4 : '%d' }\"); });", objArr));
                        if (C1846j.m7372c()) {
                            C1846j.m7366a("Screen Dump Size:");
                            C1846j.m7366a(String.format("\tThe size of the screen.receipt message is %s.", new Object[]{Utils.objectSizeToString(jSONObjectInstrumentation)}));
                            j = C1849k.this.mo15492a("", a);
                            C1846j.m7366a(String.format("\tThe total size of all the images is %s.", new Object[]{Utils.bytesToString(j)}));
                        }
                        if (C1846j.m7371b()) {
                            C1846j.m7367a("EnsightenInstrumentation:UIThreadScheduling", System.currentTimeMillis());
                        }
                        C1849k c1849k2 = C1849k.this;
                        currentTimeMillis = (long) (c1849k2.f5936i - c1849k2.f5935h);
                        if (currentTimeMillis < 0) {
                            c1849k2.f5936i = 0;
                            c1849k2.f5935h = 0;
                            c1849k2.f5941n = 25;
                        } else if (currentTimeMillis < 5) {
                            c1849k2.f5941n = 25;
                        } else if (currentTimeMillis < 10) {
                            c1849k2.f5941n = 50;
                        } else if (currentTimeMillis < 15) {
                            c1849k2.f5941n = 100;
                        } else if (currentTimeMillis < 20) {
                            c1849k2.f5941n = VTMCDataCache.MAXSIZE;
                        } else if (currentTimeMillis < 25) {
                            c1849k2.f5941n = 1000;
                        } else {
                            c1849k2.f5941n = 1000;
                            c1849k2.f5933f = false;
                        }
                        if (C1849k.this.f5930c && C1849k.this.f5933f) {
                            C1849k.this.f5929b.postDelayed(C1849k.this.f5940m, (long) C1849k.this.f5941n);
                        }
                        if (C1846j.m7371b()) {
                            C1846j.m7366a(String.format("\tThe screen dump took %s.", new Object[]{Utils.durationToString(System.currentTimeMillis() - j2)}));
                        }
                        C1849k.this.f5942o.unlock();
                    }
                } catch (Exception e) {
                    if (C1845i.m7355c()) {
                        C1845i.m7349b(e);
                    }
                }
            } catch (Exception e2) {
                if (C1845i.m7355c()) {
                    C1845i.m7345a("The screen dump start javascript message encountered an exception.");
                }
            }
        }
    }

    public C1849k(Context context) {
        this.f5928a = context;
        this.f5929b = new Handler(context.getMainLooper());
        this.f5934g = new HashMap();
    }

    /* renamed from: a */
    public final JSONObject mo15494a(Activity activity, ViewGroup viewGroup, View view, String str, String str2, JSONObject jSONObject) {
        if (viewGroup == null && view == null) {
            return null;
        }
        int i;
        Configuration configuration = Ensighten.getStorageManager().f5695c;
        if (VERSION.SDK_INT >= 17) {
            i = configuration.densityDpi;
        } else {
            i = activity.getResources().getDisplayMetrics().densityDpi;
        }
        this.f5943p = i;
        this.f5945r = true;
        this.f5944q = str2;
        long j = 0;
        if (C1846j.m7371b()) {
            j = System.currentTimeMillis();
        }
        if (viewGroup != null) {
            m7384a(viewGroup, jSONObject, false, null);
        }
        if (C1846j.m7371b()) {
            j = System.currentTimeMillis() - j;
            C1846j.m7366a(String.format("\tThe view group dump took %s.", new Object[]{Utils.durationToString(j)}));
        }
        if (view == null) {
            return jSONObject;
        }
        Bitmap bitmap;
        if (view == null) {
            bitmap = null;
        } else {
            j = 0;
            if (C1846j.m7371b()) {
                j = System.currentTimeMillis();
            }
            int i2 = 263;
            int i3 = 468;
            if (view.getWidth() > view.getHeight()) {
                i2 = 468;
                i3 = 263;
            }
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            canvas.scale(((float) i2) / ((float) view.getWidth()), ((float) i3) / ((float) view.getHeight()));
            view.draw(canvas);
            if (C1846j.m7371b()) {
                j = System.currentTimeMillis() - j;
                C1846j.m7366a(String.format("\tThe drawing cache retrieval took %s.", new Object[]{Utils.durationToString(j)}));
            }
            bitmap = createBitmap;
        }
        try {
            long currentTimeMillis;
            JSONObject put = new JSONObject().put("dump", new JSONArray().put(jSONObject));
            put.put("controller", str);
            put.put("event", str2);
            if (C1846j.m7371b()) {
                currentTimeMillis = System.currentTimeMillis();
            } else {
                currentTimeMillis = 0;
            }
            String.format("%s_jpg.json", new Object[]{str});
            C1849k.m7380a(bitmap, CompressFormat.JPEG, 50, "image/jpeg", "screenshot", put);
            if (C1846j.m7371b()) {
                C1846j.m7366a(String.format("\tEncoding took %s.", new Object[]{Utils.durationToString(System.currentTimeMillis() - currentTimeMillis)}));
            }
            return put;
        } catch (Exception e) {
            if (!C1845i.m7355c()) {
                return jSONObject;
            }
            C1845i.m7353c(e);
            return jSONObject;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x008f A:{Catch:{ Exception -> 0x0093 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006f A:{Catch:{ Exception -> 0x0093 }} */
    /* renamed from: a */
    private void m7384a(android.view.ViewGroup r12, org.json.JSONObject r13, boolean r14, android.widget.AdapterView<?> r15) {
        /*
        r11 = this;
        if (r12 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = -1;
        r1 = r11.m7386a(r12, r13, r1);	 Catch:{ Exception -> 0x0093 }
        if (r1 == 0) goto L_0x0002;
    L_0x000a:
        r3 = "";
        r4 = -1;
        r8 = r12.getChildCount();	 Catch:{ Exception -> 0x0093 }
        r1 = 0;
        r7 = r1;
        r5 = r15;
        r6 = r14;
    L_0x0015:
        if (r7 >= r8) goto L_0x0002;
    L_0x0017:
        r1 = r12.getChildAt(r7);	 Catch:{ Exception -> 0x0093 }
        r9 = r1 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x0093 }
        if (r6 != 0) goto L_0x0046;
    L_0x001f:
        r2 = android.widget.AdapterView.class;
        r6 = r2.isInstance(r1);	 Catch:{ Exception -> 0x0093 }
        if (r6 == 0) goto L_0x00a8;
    L_0x0027:
        r0 = r1;
        r0 = (android.widget.AdapterView) r0;	 Catch:{ Exception -> 0x0093 }
        r2 = r0;
        r5 = r6;
        r6 = r4;
        r4 = r2;
    L_0x002e:
        if (r5 == 0) goto L_0x00a1;
    L_0x0030:
        if (r7 != 0) goto L_0x009f;
    L_0x0032:
        r2 = r4.getAdapter();	 Catch:{ Exception -> 0x0093 }
        if (r2 != 0) goto L_0x0052;
    L_0x0038:
        r2 = 0;
        r1 = 0;
        r10 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r10;
    L_0x003e:
        r4 = r7 + 1;
        r7 = r4;
        r5 = r2;
        r4 = r6;
        r6 = r3;
        r3 = r1;
        goto L_0x0015;
    L_0x0046:
        if (r6 == 0) goto L_0x00a3;
    L_0x0048:
        if (r5 == 0) goto L_0x00a3;
    L_0x004a:
        r2 = r5.getPositionForView(r1);	 Catch:{ Exception -> 0x0093 }
        r4 = r5;
        r5 = r6;
        r6 = r2;
        goto L_0x002e;
    L_0x0052:
        r2 = r2.getClass();	 Catch:{ Exception -> 0x0093 }
        r2 = r2.getName();	 Catch:{ Exception -> 0x0093 }
    L_0x005a:
        r3 = "";
        r3 = r2.equals(r3);	 Catch:{ Exception -> 0x0093 }
        if (r3 != 0) goto L_0x0068;
    L_0x0062:
        r3 = "viewController";
        r13.put(r3, r2);	 Catch:{ Exception -> 0x0093 }
    L_0x0068:
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0093 }
        r3.<init>();	 Catch:{ Exception -> 0x0093 }
        if (r9 == 0) goto L_0x008f;
    L_0x006f:
        r1 = (android.view.ViewGroup) r1;	 Catch:{ Exception -> 0x0093 }
        r11.m7384a(r1, r3, r5, r4);	 Catch:{ Exception -> 0x0093 }
        r1 = r11.f5931d;	 Catch:{ Exception -> 0x0093 }
        if (r1 == 0) goto L_0x0080;
    L_0x0078:
        r1 = -1;
        if (r6 == r1) goto L_0x0080;
    L_0x007b:
        r1 = "position";
        r3.put(r1, r6);	 Catch:{ Exception -> 0x0093 }
    L_0x0080:
        r1 = "subviews";
        r1 = r13.get(r1);	 Catch:{ Exception -> 0x0093 }
        r1 = (org.json.JSONArray) r1;	 Catch:{ Exception -> 0x0093 }
        r1.put(r3);	 Catch:{ Exception -> 0x0093 }
        r1 = r2;
        r3 = r5;
        r2 = r4;
        goto L_0x003e;
    L_0x008f:
        r11.m7386a(r1, r3, r6);	 Catch:{ Exception -> 0x0093 }
        goto L_0x0080;
    L_0x0093:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7355c();
        if (r2 == 0) goto L_0x0002;
    L_0x009a:
        com.ensighten.C1845i.m7353c(r1);
        goto L_0x0002;
    L_0x009f:
        r2 = r3;
        goto L_0x005a;
    L_0x00a1:
        r2 = r3;
        goto L_0x0068;
    L_0x00a3:
        r10 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r10;
        goto L_0x002e;
    L_0x00a8:
        r10 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r10;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1849k.m7384a(android.view.ViewGroup, org.json.JSONObject, boolean, android.widget.AdapterView):void");
    }

    /* renamed from: a */
    public final void mo15495a(ViewGroup viewGroup, JSONObject jSONObject) {
        try {
            if (m7385a((View) viewGroup, jSONObject)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    JSONObject jSONObject2 = new JSONObject();
                    if (childAt instanceof ViewGroup) {
                        mo15495a((ViewGroup) childAt, jSONObject2);
                    } else {
                        m7385a(childAt, jSONObject2);
                    }
                    ((JSONArray) jSONObject.get("views")).put(jSONObject2);
                }
            }
        } catch (Exception e) {
            if (C1845i.m7355c()) {
                C1845i.m7353c(e);
            }
        }
    }

    /* renamed from: a */
    private boolean m7386a(View view, JSONObject jSONObject, int i) {
        try {
            if (this.f5931d) {
                JSONObject jSONObject2;
                Drawable divider;
                int dividerHeight;
                int width;
                jSONObject.put("class", view.getClass().getName());
                String[] xPathAndClassNameForGeneratedResId = Ensighten.getXPathAndClassNameForGeneratedResId(view.getId(), view);
                if (xPathAndClassNameForGeneratedResId == null) {
                    jSONObject2 = jSONObject;
                    jSONObject2.put("hashID", String.format("0x%s", new Object[]{Integer.toHexString(r2)}));
                } else {
                    jSONObject.put("viewController", xPathAndClassNameForGeneratedResId[1]);
                    jSONObject.put("hashID", xPathAndClassNameForGeneratedResId[0]);
                }
                Integer num = (Integer) view.getTag(-1414673666);
                if (num != null) {
                    if (Ensighten.getXPathForGeneratedFragmentResId(num.intValue()) == null) {
                        jSONObject2 = jSONObject;
                        jSONObject2.put(URLNavigationActivity.ARG_FRAGMENT, String.format("resId:0x%s", new Object[]{Integer.toHexString(num.intValue())}));
                    } else {
                        jSONObject2 = jSONObject;
                        jSONObject2.put(URLNavigationActivity.ARG_FRAGMENT, String.format("xPath:%s", new Object[]{r3}));
                    }
                }
                if (i != -1) {
                    jSONObject.put("position", i);
                    jSONObject.put("event", "getView");
                } else {
                    jSONObject.put("event", this.f5944q);
                }
                if (C1849k.m7376a(view) != null) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put("onClick");
                    jSONObject.put("listenerClass", C1849k.m7376a(view).getClass().getName());
                    jSONObject.put("listeners", jSONArray);
                }
                int width2 = view.getWidth();
                int height = view.getHeight();
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("x", view.getLeft());
                jSONObject3.put("y", view.getTop());
                jSONObject3.put("width", width2);
                jSONObject3.put("height", height);
                jSONObject.put("rect", jSONObject3);
                if (VERSION.SDK_INT >= 11) {
                    jSONObject.put("alpha", (double) view.getAlpha());
                }
                jSONObject2 = jSONObject;
                jSONObject2.put("visibility", view.getVisibility());
                LayoutParams layoutParams = view.getLayoutParams();
                jSONObject.put("layout_width", layoutParams.width);
                jSONObject.put("layout_height", layoutParams.height);
                if (ListView.class.isInstance(view)) {
                    ListView listView = (ListView) view;
                    divider = listView.getDivider();
                    dividerHeight = listView.getDividerHeight();
                    jSONObject.put("dividerHeight", dividerHeight);
                    width = listView.getWidth();
                    m7383a(divider, jSONObject, "dividerImage", width, dividerHeight);
                    m7388c(divider, jSONObject, "dividerColor");
                }
                Field declaredField;
                if (Switch.class.isInstance(view)) {
                    Drawable trackDrawable;
                    Drawable drawable;
                    Switch switchR = (Switch) view;
                    if (switchR.isChecked()) {
                        jSONObject.put("textOn", switchR.getTextOn());
                    } else {
                        jSONObject.put("textOff", switchR.getTextOff());
                    }
                    if (VERSION.SDK_INT >= 16) {
                        divider = switchR.getThumbDrawable();
                        trackDrawable = switchR.getTrackDrawable();
                        drawable = divider;
                    } else {
                        declaredField = Switch.class.getDeclaredField("mThumbDrawable");
                        declaredField.setAccessible(true);
                        divider = (Drawable) declaredField.get(switchR);
                        Field declaredField2 = Switch.class.getDeclaredField("mTrackDrawable");
                        declaredField2.setAccessible(true);
                        trackDrawable = (Drawable) declaredField2.get(switchR);
                        drawable = divider;
                    }
                    if (drawable != null) {
                        declaredField = Switch.class.getDeclaredField("mSwitchHeight");
                        declaredField.setAccessible(true);
                        dividerHeight = ((Integer) declaredField.get(switchR)).intValue();
                        declaredField = Switch.class.getDeclaredField("mThumbWidth");
                        declaredField.setAccessible(true);
                        width = ((Integer) declaredField.get(switchR)).intValue();
                        m7383a(drawable.getCurrent(), jSONObject, "thumb", width, dividerHeight);
                    } else {
                        width = 0;
                        dividerHeight = 0;
                    }
                    if (trackDrawable != null) {
                        m7383a(trackDrawable.getCurrent(), jSONObject, "track", width * 2, dividerHeight);
                    }
                } else if (CompoundButton.class.isInstance(view)) {
                    int i2;
                    CompoundButton compoundButton = (CompoundButton) view;
                    declaredField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                    declaredField.setAccessible(true);
                    if (compoundButton.isChecked()) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    m7381a((Drawable) declaredField.get(compoundButton), i2, jSONObject, "src", width2, height);
                }
                if (ImageView.class.isInstance(view)) {
                    divider = ((ImageView) view).getDrawable();
                    m7382a(divider, jSONObject, "src");
                    m7381a(divider, -1, jSONObject, "src", width2, height);
                    m7388c(divider, jSONObject, "src");
                }
                if (TextView.class.isInstance(view) && view != null) {
                    TextView textView = (TextView) view;
                    int currentTextColor = textView.getCurrentTextColor();
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("pixelSize", (double) textView.getTextSize());
                    Typeface typeface = textView.getTypeface();
                    if (typeface != null) {
                        jSONObject4.put("typefaceStyle", typeface.getStyle());
                    }
                    jSONObject.put("font", jSONObject4);
                    jSONObject.put("gravity", textView.getGravity());
                    jSONObject.put("text", textView.getText());
                    C1849k.m7379a(currentTextColor, jSONObject, "textColor");
                    Drawable[] compoundDrawables = textView.getCompoundDrawables();
                    m7387b(compoundDrawables[0], jSONObject, "drawableLeft");
                    m7387b(compoundDrawables[1], jSONObject, "drawableTop");
                    m7387b(compoundDrawables[2], jSONObject, "drawableRight");
                    m7387b(compoundDrawables[3], jSONObject, "drawableBottom");
                }
                if (VERSION.SDK_INT >= 17) {
                    jSONObject.put("textAlignment", view.getTextAlignment());
                }
                divider = view.getBackground();
                m7382a(divider, jSONObject, "background");
                m7381a(divider, -1, jSONObject, "background", width2, height);
                m7388c(divider, jSONObject, "background");
                if (this.f5945r) {
                    Object obj = "undefined";
                    Ensighten.getInstance();
                    C1714N storageManager = Ensighten.getStorageManager();
                    if (storageManager != null) {
                        if (storageManager.f5695c.orientation == 2) {
                            obj = "landscape";
                        } else if (storageManager.f5695c.orientation == 1) {
                            obj = "portrait";
                        }
                    }
                    jSONObject.put("viewController", Ensighten.getCurrentActivityName());
                    jSONObject.put("densityDpi", this.f5943p);
                    jSONObject.put("orientation", obj);
                    this.f5945r = false;
                    if (!jSONObject.has("background")) {
                        divider = view.getRootView().getBackground();
                        m7382a(divider, jSONObject, "background");
                        m7381a(divider, -1, jSONObject, "background", width2, height);
                        m7388c(divider, jSONObject, "background");
                    }
                }
                jSONObject.put("subviews", new JSONArray());
            } else {
                String[] xPathAndClassNameForGeneratedResId2 = Ensighten.getXPathAndClassNameForGeneratedResId(view.getId(), view);
                if (xPathAndClassNameForGeneratedResId2 != null) {
                    jSONObject.put("viewController", xPathAndClassNameForGeneratedResId2[1]);
                }
                if (this.f5945r) {
                    jSONObject.put("viewController", Ensighten.getCurrentActivityName());
                    this.f5945r = false;
                }
                jSONObject.put("subviews", new JSONArray());
            }
            return true;
        } catch (Exception e) {
            if (C1845i.m7355c()) {
                C1845i.m7353c(e);
            }
            return false;
        }
    }

    /* renamed from: a */
    private boolean m7385a(View view, JSONObject jSONObject) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        jSONObject.put("class", C1849k.m7377a(view.getClass()));
        jSONObject.put("id", view.getId());
        jSONObject.put("x", iArr[0]);
        jSONObject.put("y", iArr[1]);
        jSONObject.put("width", view.getWidth());
        jSONObject.put("height", view.getHeight());
        jSONObject.put("views", new JSONArray());
        if (view instanceof TextView) {
            jSONObject.put("text", ((TextView) view).getText());
        }
        OnClickListener a = C1849k.m7376a(view);
        if (a != null) {
            JSONObject jSONObject2 = new JSONObject();
            if (a.getClass().getEnclosingClass() == null || !a.getClass().getEnclosingClass().equals(View.class)) {
                try {
                    if (a instanceof OnClickListener) {
                        jSONObject2.put("method", "onClick");
                        jSONObject2.put("class", C1849k.m7377a(a.getClass()));
                    }
                } catch (Exception e) {
                    if (C1845i.m7355c()) {
                        C1845i.m7353c(e);
                    }
                    return false;
                }
            }
            try {
                Field declaredField = a.getClass().getDeclaredField("val$handlerName");
                declaredField.setAccessible(true);
                jSONObject2.put("method", declaredField.get(a));
                if (Ensighten.getCurrentActivity() != null) {
                    jSONObject2.put("class", C1849k.m7377a(Ensighten.getCurrentActivity().getClass()));
                }
            } catch (Exception e2) {
                C1845i.m7353c(e2);
                jSONObject2.put("error", String.format("Unable to find click handler, exception: %s.", new Object[]{e2.getMessage()}));
            }
            jSONObject.put("clickHandler", jSONObject2);
        }
        return true;
    }

    /* renamed from: a */
    private void m7382a(Drawable drawable, JSONObject jSONObject, String str) {
        if (this.f5932e && drawable != null && BitmapDrawable.class.isInstance(drawable)) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap.hasAlpha()) {
                C1849k.m7380a(bitmap, CompressFormat.PNG, 100, "image/png", str, jSONObject);
            } else {
                C1849k.m7380a(bitmap, CompressFormat.JPEG, 50, "image/jpeg", str, jSONObject);
            }
        }
    }

    /* renamed from: b */
    private void m7387b(Drawable drawable, JSONObject jSONObject, String str) throws JSONException {
        if (drawable != null && BitmapDrawable.class.isInstance(drawable)) {
            m7382a(drawable, jSONObject, str);
            Rect bounds = drawable.getBounds();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("x", bounds.left);
            jSONObject2.put("y", bounds.top);
            jSONObject2.put("width", bounds.width());
            jSONObject2.put("height", bounds.height());
            jSONObject.getJSONObject(str).put("rect", jSONObject2);
        }
    }

    /* renamed from: a */
    private void m7383a(Drawable drawable, JSONObject jSONObject, String str, int i, int i2) throws JSONException {
        if (this.f5932e && i != 0 && i2 != 0 && drawable != null && NinePatchDrawable.class.isInstance(drawable)) {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, i, i2);
            drawable.draw(canvas);
            if (createBitmap.hasAlpha()) {
                C1849k.m7380a(createBitmap, CompressFormat.PNG, 100, "image/png", str, jSONObject);
            } else {
                C1849k.m7380a(createBitmap, CompressFormat.JPEG, 50, "image/jpeg", str, jSONObject);
            }
        }
    }

    /* renamed from: a */
    private void m7381a(Drawable drawable, int i, JSONObject jSONObject, String str, int i2, int i3) throws JSONException, SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        if (i2 != 0 && i3 != 0 && drawable != null && StateListDrawable.class.isInstance(drawable)) {
            Drawable drawable2 = null;
            if (i == -1) {
                drawable2 = drawable.getCurrent();
            } else {
                ConstantState constantState = ((StateListDrawable) drawable).getConstantState();
                if (DrawableContainerState.class.isInstance(constantState)) {
                    drawable2 = ((DrawableContainerState) constantState).getChildren()[i];
                }
            }
            m7382a(drawable2, jSONObject, str);
            m7383a(drawable2, jSONObject, str, i2, i3);
            m7388c(drawable2, jSONObject, str);
            if (drawable2 != null && GradientDrawable.class.isInstance(drawable2)) {
                GradientDrawable gradientDrawable = (GradientDrawable) drawable2;
                Field declaredField = GradientDrawable.class.getDeclaredField("mGradientState");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(gradientDrawable);
                try {
                    if (VERSION.SDK_INT == 23) {
                        declaredField = obj.getClass().getDeclaredField("mGradientColors");
                    } else {
                        declaredField = obj.getClass().getDeclaredField("mColors");
                    }
                } catch (NoSuchFieldException e) {
                    if (C1845i.m7355c()) {
                        C1845i.m7353c(e);
                    }
                    declaredField = null;
                }
                if (declaredField != null) {
                    int[] iArr = (int[]) declaredField.get(obj);
                    if (iArr != null) {
                        int i4 = iArr[0];
                        int i5 = iArr[iArr.length - 1];
                        byte[] array = ByteBuffer.allocate(4).putInt(i4).array();
                        byte[] array2 = ByteBuffer.allocate(4).putInt(i5).array();
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("a", (double) (((float) (((array[0] + array2[0]) / 2) & 255)) / 255.0f));
                        jSONObject2.put("r", ((array[1] + array2[1]) / 2) & 255);
                        jSONObject2.put("g", ((array[2] + array2[2]) / 2) & 255);
                        byte b = array[3];
                        jSONObject2.put("b", ((array2[3] + b) / 2) & 255);
                        jSONObject.put(str, jSONObject2);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    private void m7388c(Drawable drawable, JSONObject jSONObject, String str) throws JSONException {
        if (ColorDrawable.class.isInstance(drawable)) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            if (VERSION.SDK_INT >= 11) {
                C1849k.m7379a(colorDrawable.getColor(), jSONObject, str);
            }
        }
    }

    /* renamed from: a */
    private static void m7379a(int i, JSONObject jSONObject, String str) throws JSONException {
        byte[] array = ByteBuffer.allocate(4).putInt(i).array();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("a", (double) (((float) (array[0] & 255)) / 255.0f));
        jSONObject2.put("r", array[1] & 255);
        jSONObject2.put("g", array[2] & 255);
        jSONObject2.put("b", array[3] & 255);
        jSONObject.put(str, jSONObject2);
    }

    /* renamed from: a */
    private static void m7380a(Bitmap bitmap, CompressFormat compressFormat, int i, String str, String str2, JSONObject jSONObject) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, i, byteArrayOutputStream);
            String a = C1719P.m7252a(byteArrayOutputStream.toByteArray(), 2);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("imageData", a);
                jSONObject2.put("imageType", str);
                jSONObject2.put("width", bitmap.getWidth());
                jSONObject2.put("height", bitmap.getHeight());
                jSONObject.put(str2, jSONObject2);
            } catch (JSONException e) {
                if (C1845i.m7355c()) {
                    C1845i.m7349b(e);
                }
            }
        }
    }

    /* renamed from: a */
    private static String m7377a(Class<?> cls) {
        String name = cls.getName();
        Class cls2;
        while (cls2.getSuperclass() != null) {
            name = name + ":" + cls2.getSuperclass().getName().toString();
            cls2 = cls2.getSuperclass();
        }
        return name;
    }

    /* renamed from: a */
    private static OnClickListener m7376a(View view) {
        try {
            Field declaredField;
            if (VERSION.SDK_INT >= 15) {
                declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(view);
                Field declaredField2 = obj.getClass().getDeclaredField("mOnClickListener");
                declaredField2.setAccessible(true);
                return (OnClickListener) declaredField2.get(obj);
            }
            declaredField = Class.forName("android.view.View").getDeclaredField("mOnClickListener");
            declaredField.setAccessible(true);
            return (OnClickListener) declaredField.get(view);
        } catch (Throwable th) {
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public long mo15492a(String str, JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null || jSONObject.names() == null) {
            return 0;
        }
        int i2 = 0;
        while (i < jSONObject.names().length()) {
            try {
                String string = jSONObject.names().getString(i);
                i2 = (int) (m7374a(str, string, jSONObject.get(string)) + ((long) i2));
            } catch (JSONException e) {
            }
            i++;
        }
        return (long) i2;
    }

    /* renamed from: a */
    private long m7375a(String str, JSONArray jSONArray) {
        int i = 0;
        if (jSONArray == null) {
            return 0;
        }
        int i2 = 0;
        while (i < jSONArray.length()) {
            try {
                i2 = (int) (m7374a(str, "", jSONArray.get(i)) + ((long) i2));
            } catch (JSONException e) {
            }
            i++;
        }
        return (long) i2;
    }

    /* renamed from: a */
    public final JSONArray mo15493a(Object obj) {
        Class cls = obj instanceof Class ? (Class) obj : obj.getClass();
        JSONArray jSONArray = new JSONArray();
        while (true) {
            Class cls2 = cls;
            for (Field field : cls2.getDeclaredFields()) {
                JSONObject jSONObject = new JSONObject();
                String name = field.getName();
                int modifiers = field.getModifiers();
                if (!((Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers)) || name.startsWith("shadow$"))) {
                    jSONObject.put("name", name);
                    jSONObject.put("type", m7378a(field.getGenericType()));
                    jSONObject.put("modifier", modifiers);
                    try {
                        field.setAccessible(true);
                        jSONObject.put("value", String.valueOf(field.get(obj)));
                    } catch (Throwable th) {
                    }
                    try {
                        jSONArray.put(jSONObject);
                    } catch (Exception e) {
                        if (C1845i.m7355c()) {
                            C1845i.m7353c(e);
                        }
                    }
                }
            }
            cls = cls2.getSuperclass();
            if (cls == null) {
                break;
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    private String m7378a(Type type) {
        String str = "";
        if (type instanceof Class) {
            return ((Class) type).getName();
        }
        if (!(type instanceof ParameterizedType)) {
            return str;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        str = m7378a(parameterizedType.getRawType());
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            if (i == 0) {
                str = str + "<";
            }
            str = str + m7378a(actualTypeArguments[i]);
            if (i != actualTypeArguments.length - 1) {
                str = str + ",";
            }
            if (i == actualTypeArguments.length - 1) {
                str = str + ">";
            }
        }
        return str;
    }

    /* renamed from: a */
    private long m7374a(String str, String str2, Object obj) {
        if (obj instanceof JSONObject) {
            return mo15492a(str2, (JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return m7375a(str2, (JSONArray) obj);
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        String str3 = (String) obj;
        if (!str2.equals("imageData")) {
            return 0;
        }
        long objectSize = Utils.getObjectSize(str3);
        if (str.equals("screenshot")) {
            C1846j.m7366a(String.format("\tThe size of the screenshot is %s.", new Object[]{Utils.bytesToString(objectSize)}));
            return objectSize;
        }
        C1846j.m7366a(String.format("\tThe size of the image is %s.", new Object[]{Utils.bytesToString(objectSize)}));
        return objectSize;
    }
}
