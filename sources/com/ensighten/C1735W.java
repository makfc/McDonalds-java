package com.ensighten;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.ensighten.W */
public final class C1735W {
    /* renamed from: A */
    private Map<String, Map<String, Integer>> f5777A = new HashMap();
    /* renamed from: B */
    private Map<String, Integer> f5778B;
    /* renamed from: C */
    private HashMap<String, Integer> f5779C;
    /* renamed from: a */
    public C1733V f5780a;
    /* renamed from: b */
    public String f5781b;
    /* renamed from: c */
    public Adapter f5782c;
    /* renamed from: d */
    public String f5783d;
    /* renamed from: e */
    public View f5784e;
    /* renamed from: f */
    public Fragment f5785f;
    /* renamed from: g */
    public android.support.p000v4.app.Fragment f5786g;
    /* renamed from: h */
    public String f5787h;
    /* renamed from: i */
    public Map<String, Map<Integer, String>> f5788i = new HashMap();
    /* renamed from: j */
    public SparseArray<String> f5789j;
    /* renamed from: k */
    public int f5790k = -1;
    /* renamed from: l */
    private final Context f5791l;
    /* renamed from: m */
    private String f5792m;
    /* renamed from: n */
    private String f5793n;
    /* renamed from: o */
    private WeakReference<Activity> f5794o;
    /* renamed from: p */
    private int f5795p = 0;
    /* renamed from: q */
    private int f5796q = -1;
    /* renamed from: r */
    private SparseArray<String> f5797r;
    /* renamed from: s */
    private SparseArray<Integer> f5798s;
    /* renamed from: t */
    private View f5799t;
    /* renamed from: u */
    private int f5800u;
    /* renamed from: v */
    private boolean f5801v = false;
    /* renamed from: w */
    private Map<Integer, String> f5802w;
    /* renamed from: x */
    private Map<Long, Integer> f5803x;
    /* renamed from: y */
    private Map<String, Integer> f5804y;
    /* renamed from: z */
    private Map<String, Map<Long, Integer>> f5805z = new HashMap();

    public C1735W(Context context) {
        this.f5791l = context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.f5794o = new WeakReference(activity);
            this.f5781b = activity.getClass().getName();
        }
        this.f5797r = new SparseArray();
        this.f5798s = new SparseArray();
    }

    /* JADX WARNING: Missing block: B:112:0x01f8, code skipped:
            r1 = r0;
            r0 = r13.f5781b;
     */
    /* JADX WARNING: Missing block: B:113:0x01fd, code skipped:
            r1.f5792m = r0;
     */
    /* JADX WARNING: Missing block: B:120:0x0256, code skipped:
            r13.f5783d = r14.getClass().getName();
            r0 = java.lang.String.format("%s[%d]:%s", new java.lang.Object[]{r13.f5783d, java.lang.Integer.valueOf(r13.f5796q), r13.f5781b});
            r1 = r13;
     */
    /* renamed from: a */
    public final void mo15086a(java.lang.Object r14, java.lang.String r15) {
        /*
        r13 = this;
        r0 = com.ensighten.C1735W.m7282b();
        if (r0 != 0) goto L_0x0012;
    L_0x0006:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        r0 = "View processing is disabled.";
        com.ensighten.C1845i.m7345a(r0);
    L_0x0011:
        return;
    L_0x0012:
        if (r14 != 0) goto L_0x0020;
    L_0x0014:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x001a:
        r0 = "The class is null during view processing.";
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x0020:
        if (r15 != 0) goto L_0x0045;
    L_0x0022:
        if (r14 == 0) goto L_0x0042;
    L_0x0024:
        r0 = r14.getClass();
        r0 = r0.getSimpleName();
    L_0x002c:
        r1 = com.ensighten.C1845i.m7364k();
        if (r1 == 0) goto L_0x0011;
    L_0x0032:
        r1 = "The method is null for class %s during view processing.";
        r2 = 1;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r2[r3] = r0;
        r0 = java.lang.String.format(r1, r2);
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x0042:
        r0 = "null";
        goto L_0x002c;
    L_0x0045:
        r0 = com.ensighten.Ensighten.isPrivacyMode();
        if (r0 == 0) goto L_0x0057;
    L_0x004b:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x0051:
        r0 = "View not processed due to privacy mode.";
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x0057:
        r0 = com.ensighten.Ensighten.isBatteryKillEnabled();
        if (r0 == 0) goto L_0x0069;
    L_0x005d:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x0063:
        r0 = "View not processed due to battery kill being enabled.";
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x0069:
        r0 = com.ensighten.Ensighten.getOptimizationManager();
        r0 = r0.f5678f;
        if (r0 == 0) goto L_0x0088;
    L_0x0071:
        r0 = 1;
    L_0x0072:
        if (r0 != 0) goto L_0x008a;
    L_0x0074:
        r0 = com.ensighten.Ensighten.getDumpManager();
        r0 = r0.f5930c;
        if (r0 != 0) goto L_0x008a;
    L_0x007c:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x0082:
        r0 = "View not processed due to invalid optimization config and not being in dump mode.";
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x0088:
        r0 = 0;
        goto L_0x0072;
    L_0x008a:
        r0 = com.ensighten.Ensighten.getOptimizationManager();
        r1 = 0;
        r0.f5682j = r1;
        r1 = 0;
        r0.f5683k = r1;
        r0 = "onCreate";
        r0 = r15.equals(r0);
        if (r0 == 0) goto L_0x00bf;
    L_0x009c:
        r0 = 0;
        r13.f5781b = r0;
        r0 = 0;
        r13.f5783d = r0;
        r0 = 0;
        r13.f5787h = r0;
        r0 = 0;
        r13.f5792m = r0;
        r0 = 0;
        r13.f5782c = r0;
        r0 = 0;
        r13.f5795p = r0;
        r0 = -1;
        r13.f5796q = r0;
        r0 = new android.util.SparseArray;
        r0.<init>();
        r13.f5797r = r0;
        r0 = new android.util.SparseArray;
        r0.<init>();
        r13.f5798s = r0;
    L_0x00bf:
        r0 = r14 instanceof android.app.Activity;
        if (r0 == 0) goto L_0x01b0;
    L_0x00c3:
        r0 = com.ensighten.C1733V.ACTIVITY;
        r13.f5780a = r0;
    L_0x00c7:
        r0 = r13.f5780a;
        r1 = com.ensighten.C1733V.OTHER;
        if (r0 == r1) goto L_0x0011;
    L_0x00cd:
        r0 = com.ensighten.C1735W.C17341.f5776a;
        r1 = r13.f5780a;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x01f2;
            case 2: goto L_0x0201;
            case 3: goto L_0x0217;
            case 4: goto L_0x022d;
            case 5: goto L_0x0256;
            case 6: goto L_0x0256;
            case 7: goto L_0x027e;
            default: goto L_0x00da;
        };
    L_0x00da:
        r13.f5793n = r15;
        r0 = r13.f5794o;
        r0 = r0.get();
        r0 = (android.app.Activity) r0;
        if (r0 == 0) goto L_0x0011;
    L_0x00e6:
        r1 = r0.getWindow();
        r1 = r1.getDecorView();
        r5 = r1.getRootView();
        if (r5 == 0) goto L_0x0011;
    L_0x00f4:
        r1 = r13.f5780a;
        r2 = com.ensighten.C1733V.EXPANDABLE_LIST_ADAPTER;
        if (r1 == r2) goto L_0x0100;
    L_0x00fa:
        r1 = r13.f5780a;
        r2 = com.ensighten.C1733V.PAGER_ADAPTER;
        if (r1 != r2) goto L_0x02f3;
    L_0x0100:
        r1 = com.ensighten.C1845i.m7355c();
        if (r1 == 0) goto L_0x0117;
    L_0x0106:
        r1 = "The %s type is not supported when determining paths.";
        r2 = 1;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = r13.f5780a;
        r2[r3] = r4;
        r1 = java.lang.String.format(r1, r2);
        com.ensighten.C1845i.m7345a(r1);
    L_0x0117:
        r1 = 0;
        r2 = r1;
    L_0x0119:
        r3 = com.ensighten.Ensighten.getDumpManager();
        r1 = r3.f5930c;
        if (r1 == 0) goto L_0x01a3;
    L_0x0121:
        r1 = r13.f5780a;
        r1 = r1.f5775i;
        if (r1 == 0) goto L_0x01a3;
    L_0x0127:
        r1 = "onResume";
        r1 = r15.equals(r1);
        if (r1 == 0) goto L_0x01a3;
    L_0x012f:
        r4 = r13.f5781b;
        r6 = r13.f5780a;
        r1 = r13.f5792m;
        r7 = r13.f5793n;
        if (r0 != 0) goto L_0x013d;
    L_0x0139:
        r8 = r3.f5930c;
        if (r8 == 0) goto L_0x01a3;
    L_0x013d:
        r8 = r3.f5934g;
        r8 = r8.containsKey(r1);
        if (r8 == 0) goto L_0x04ff;
    L_0x0145:
        r8 = r3.f5934g;
        r1 = r8.get(r1);
        r1 = (java.lang.String) r1;
        r1 = r1.contentEquals(r7);
        if (r1 == 0) goto L_0x04ff;
    L_0x0153:
        r1 = 1;
    L_0x0154:
        if (r1 != 0) goto L_0x015e;
    L_0x0156:
        r1 = com.ensighten.C1733V.SUPPORT_FRAGMENT;
        if (r6 == r1) goto L_0x015e;
    L_0x015a:
        r1 = com.ensighten.C1733V.FRAGMENT;
        if (r6 != r1) goto L_0x0169;
    L_0x015e:
        r1 = com.ensighten.C1849k.C18482.f5927a;
        r6 = r6.ordinal();
        r1 = r1[r6];
        switch(r1) {
            case 1: goto L_0x0502;
            case 2: goto L_0x0502;
            default: goto L_0x0169;
        };
    L_0x0169:
        r1 = r3.f5942o;
        r1.lock();
        r3.f5937j = r5;
        r3.f5938k = r2;
        r1 = r3.f5939l;
        if (r1 == 0) goto L_0x017a;
    L_0x0176:
        r1 = r3.f5933f;
        if (r1 != 0) goto L_0x019e;
    L_0x017a:
        r1 = 1;
        r3.f5939l = r1;
        r1 = new com.ensighten.k$1;
        r1.<init>(r0, r4, r7);
        r3.f5940m = r1;
        r1 = com.ensighten.C1846j.m7371b();
        if (r1 == 0) goto L_0x0193;
    L_0x018a:
        r1 = "EnsightenInstrumentation:UIThreadScheduling";
        r4 = java.lang.System.currentTimeMillis();
        com.ensighten.C1846j.m7367a(r1, r4);
    L_0x0193:
        r1 = r3.f5933f;
        if (r1 == 0) goto L_0x0510;
    L_0x0197:
        r1 = r3.f5929b;
        r2 = r3.f5940m;
        r1.post(r2);
    L_0x019e:
        r1 = r3.f5942o;
        r1.unlock();
    L_0x01a3:
        r1 = com.ensighten.Ensighten.getOptimizationManager();
        r2 = r13.f5792m;
        r3 = r13.f5793n;
        r1.mo15045a(r0, r2, r3);
        goto L_0x0011;
    L_0x01b0:
        r0 = r14 instanceof android.widget.Adapter;
        if (r0 == 0) goto L_0x01ba;
    L_0x01b4:
        r0 = com.ensighten.C1733V.ADAPTER;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01ba:
        r0 = r14 instanceof android.widget.ExpandableListAdapter;
        if (r0 == 0) goto L_0x01c4;
    L_0x01be:
        r0 = com.ensighten.C1733V.EXPANDABLE_LIST_ADAPTER;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01c4:
        r0 = r14 instanceof android.app.Fragment;
        if (r0 == 0) goto L_0x01ce;
    L_0x01c8:
        r0 = com.ensighten.C1733V.FRAGMENT;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01ce:
        r0 = r14 instanceof android.support.p000v4.app.Fragment;
        if (r0 == 0) goto L_0x01d8;
    L_0x01d2:
        r0 = com.ensighten.C1733V.SUPPORT_FRAGMENT;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01d8:
        r0 = r14 instanceof android.view.LayoutInflater;
        if (r0 == 0) goto L_0x01e2;
    L_0x01dc:
        r0 = com.ensighten.C1733V.LAYOUT_INFLATER;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01e2:
        r0 = r14 instanceof android.support.p000v4.view.PagerAdapter;
        if (r0 == 0) goto L_0x01ec;
    L_0x01e6:
        r0 = com.ensighten.C1733V.PAGER_ADAPTER;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01ec:
        r0 = com.ensighten.C1733V.OTHER;
        r13.f5780a = r0;
        goto L_0x00c7;
    L_0x01f2:
        r14 = (android.app.Activity) r14;
        r13.mo15085a(r14);
        r0 = r13;
    L_0x01f8:
        r1 = r13.f5781b;
        r12 = r1;
        r1 = r0;
        r0 = r12;
    L_0x01fd:
        r1.f5792m = r0;
        goto L_0x00da;
    L_0x0201:
        r0 = r14;
        r0 = (android.app.Fragment) r0;
        r13.f5785f = r0;
        r0 = 0;
        r13.f5786g = r0;
        r0 = r14.getClass();
        r0 = r0.getName();
        r13.f5787h = r0;
        r0 = r13.f5787h;
        r1 = r13;
        goto L_0x01fd;
    L_0x0217:
        r0 = 0;
        r13.f5785f = r0;
        r0 = r14;
        r0 = (android.support.p000v4.app.Fragment) r0;
        r13.f5786g = r0;
        r0 = r14.getClass();
        r0 = r0.getName();
        r13.f5787h = r0;
        r0 = r13.f5787h;
        r1 = r13;
        goto L_0x01fd;
    L_0x022d:
        r0 = r14;
        r0 = (android.widget.Adapter) r0;
        r13.f5782c = r0;
        r0 = r14.hashCode();
        r13.f5795p = r0;
        r0 = r13.f5798s;
        r1 = r13.f5795p;
        r2 = -1;
        r2 = java.lang.Integer.valueOf(r2);
        r0 = r0.get(r1, r2);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = -1;
        if (r0 != r1) goto L_0x0254;
    L_0x024e:
        r0 = r13.f5798s;
        r0 = r0.size();
    L_0x0254:
        r13.f5796q = r0;
    L_0x0256:
        r0 = r14.getClass();
        r0 = r0.getName();
        r13.f5783d = r0;
        r0 = "%s[%d]:%s";
        r1 = 3;
        r1 = new java.lang.Object[r1];
        r2 = 0;
        r3 = r13.f5783d;
        r1[r2] = r3;
        r2 = 1;
        r3 = r13.f5796q;
        r3 = java.lang.Integer.valueOf(r3);
        r1[r2] = r3;
        r2 = 2;
        r3 = r13.f5781b;
        r1[r2] = r3;
        r0 = java.lang.String.format(r0, r1);
        r1 = r13;
        goto L_0x01fd;
    L_0x027e:
        r0 = new java.lang.Throwable;
        r0.<init>();
        r0 = r0.getStackTrace();
        r1 = 3;
        r0 = r0[r1];
        r1 = r0.getClassName();
        r0 = r0.getMethodName();
        r2 = r13.f5787h;
        if (r2 != 0) goto L_0x02ba;
    L_0x0296:
        r2 = r13.f5781b;
        r2 = r1.equals(r2);
        if (r2 != 0) goto L_0x02ba;
    L_0x029e:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x02a4:
        r0 = "Inflater class %s does not match expected class %s.";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r2[r3] = r1;
        r1 = 1;
        r3 = r13.f5781b;
        r2[r1] = r3;
        r0 = java.lang.String.format(r0, r2);
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x02ba:
        r2 = r13.f5787h;
        if (r2 == 0) goto L_0x02ea;
    L_0x02be:
        r2 = r13.f5787h;
        r2 = r1.equals(r2);
        if (r2 == 0) goto L_0x02ce;
    L_0x02c6:
        r2 = "onCreateView";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x02ea;
    L_0x02ce:
        r0 = com.ensighten.C1845i.m7364k();
        if (r0 == 0) goto L_0x0011;
    L_0x02d4:
        r0 = "Inflater class %s does not match expected class %s.";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r2[r3] = r1;
        r1 = 1;
        r3 = r13.f5781b;
        r2[r1] = r3;
        r0 = java.lang.String.format(r0, r2);
        com.ensighten.C1845i.m7345a(r0);
        goto L_0x0011;
    L_0x02ea:
        r0 = r13.f5787h;
        if (r0 != 0) goto L_0x0521;
    L_0x02ee:
        r0 = r13.f5787h;
        r1 = r13;
        goto L_0x01fd;
    L_0x02f3:
        r2 = 0;
        r3 = 0;
        r1 = r13.f5780a;
        r4 = com.ensighten.C1733V.ADAPTER;
        if (r1 != r4) goto L_0x0321;
    L_0x02fb:
        r1 = r13.f5784e;
        r1 = r1 instanceof android.view.ViewGroup;
        if (r1 != 0) goto L_0x031a;
    L_0x0301:
        r2 = 1;
    L_0x0302:
        if (r2 == 0) goto L_0x031c;
    L_0x0304:
        r1 = 0;
    L_0x0305:
        r4 = r2;
        r3 = r1;
    L_0x0307:
        if (r3 != 0) goto L_0x0367;
    L_0x0309:
        if (r4 != 0) goto L_0x0367;
    L_0x030b:
        r1 = com.ensighten.C1845i.m7364k();
        if (r1 == 0) goto L_0x0316;
    L_0x0311:
        r1 = "The current view is an adapter row view, skipping path determination.";
        com.ensighten.C1845i.m7345a(r1);
    L_0x0316:
        r1 = 0;
        r2 = r1;
        goto L_0x0119;
    L_0x031a:
        r2 = 0;
        goto L_0x0302;
    L_0x031c:
        r1 = r13.f5784e;
        r1 = (android.view.ViewGroup) r1;
        goto L_0x0305;
    L_0x0321:
        r1 = r13.f5780a;
        r4 = com.ensighten.C1733V.ACTIVITY;
        if (r1 != r4) goto L_0x033d;
    L_0x0327:
        r1 = r13.f5794o;
        r1 = r1.get();
        r1 = (android.app.Activity) r1;
        if (r1 == 0) goto L_0x051e;
    L_0x0331:
        r2 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        r1 = r1.findViewById(r2);
        r1 = (android.view.ViewGroup) r1;
    L_0x033a:
        r4 = r3;
        r3 = r1;
        goto L_0x0307;
    L_0x033d:
        r1 = r13.f5785f;
        if (r1 == 0) goto L_0x0350;
    L_0x0341:
        r1 = r13.f5785f;
        r1 = r1.getView();
        r13.f5799t = r1;
        r1 = r13.f5799t;
        r1 = (android.view.ViewGroup) r1;
        r4 = r3;
        r3 = r1;
        goto L_0x0307;
    L_0x0350:
        r1 = r13.f5786g;
        if (r1 == 0) goto L_0x0363;
    L_0x0354:
        r1 = r13.f5786g;
        r1 = r1.getView();
        r13.f5799t = r1;
        r1 = r13.f5799t;
        r1 = (android.view.ViewGroup) r1;
        r4 = r3;
        r3 = r1;
        goto L_0x0307;
    L_0x0363:
        r1 = 0;
        r4 = r3;
        r3 = r1;
        goto L_0x0307;
    L_0x0367:
        r1 = r13.f5792m;
        if (r1 != 0) goto L_0x037a;
    L_0x036b:
        r1 = com.ensighten.C1845i.m7364k();
        if (r1 == 0) goto L_0x0376;
    L_0x0371:
        r1 = "The current class is null, skipping path determination.";
        com.ensighten.C1845i.m7345a(r1);
    L_0x0376:
        r1 = 0;
        r2 = r1;
        goto L_0x0119;
    L_0x037a:
        r1 = r13.f5802w;
        if (r1 == 0) goto L_0x04ba;
    L_0x037e:
        r1 = r13.f5788i;
        r2 = r13.f5792m;
        r1 = r1.containsKey(r2);
    L_0x0386:
        r13.f5801v = r1;
        r1 = 0;
        r2 = r13.f5780a;
        r6 = com.ensighten.C1733V.ADAPTER;
        if (r2 != r6) goto L_0x04bd;
    L_0x038f:
        r1 = r13.f5793n;
        r2 = "getView";
        r1 = r1.equals(r2);
        r2 = r1;
    L_0x0398:
        r13.m7284d();
        if (r4 == 0) goto L_0x04da;
    L_0x039d:
        r1 = -1;
    L_0x039e:
        r6 = r13.f5777A;
        r7 = r13.f5792m;
        r6 = r6.containsKey(r7);
        if (r6 != 0) goto L_0x03aa;
    L_0x03a8:
        if (r1 > 0) goto L_0x03b2;
    L_0x03aa:
        r1 = r13.f5801v;
        if (r1 != 0) goto L_0x03b0;
    L_0x03ae:
        if (r4 == 0) goto L_0x04b7;
    L_0x03b0:
        if (r2 == 0) goto L_0x04b7;
    L_0x03b2:
        r1 = r13.f5780a;
        r6 = com.ensighten.C1733V.ADAPTER;
        if (r1 != r6) goto L_0x03c8;
    L_0x03b8:
        r1 = r13.f5780a;
        r6 = com.ensighten.C1733V.ADAPTER;
        if (r1 != r6) goto L_0x0406;
    L_0x03be:
        r1 = r13.f5798s;
        r6 = r13.f5795p;
        r1 = r1.get(r6);
        if (r1 != 0) goto L_0x0406;
    L_0x03c8:
        r1 = new java.util.HashMap;
        r1.<init>();
        r13.f5803x = r1;
        r1 = r13.f5788i;
        r6 = r13.f5792m;
        r1 = r1.get(r6);
        r1 = (java.util.Map) r1;
        r13.f5802w = r1;
        r1 = r13.f5802w;
        if (r1 == 0) goto L_0x03e5;
    L_0x03df:
        r1 = r13.f5801v;
        if (r1 == 0) goto L_0x03ec;
    L_0x03e3:
        if (r2 == 0) goto L_0x03ec;
    L_0x03e5:
        r1 = new java.util.HashMap;
        r1.<init>();
        r13.f5802w = r1;
    L_0x03ec:
        r1 = new java.util.HashMap;
        r1.<init>();
        r13.f5804y = r1;
        r1 = r13.f5780a;
        r2 = com.ensighten.C1733V.ADAPTER;
        if (r1 != r2) goto L_0x0406;
    L_0x03f9:
        r1 = r13.f5798s;
        r2 = r13.f5795p;
        r6 = r13.f5796q;
        r6 = java.lang.Integer.valueOf(r6);
        r1.put(r2, r6);
    L_0x0406:
        if (r4 == 0) goto L_0x04f5;
    L_0x0408:
        r4 = r13.f5792m;
        r6 = r13.f5784e;
        r1 = r6.getClass();
        r7 = r1.getSimpleName();
        r1 = r6.getId();
        r2 = java.lang.Integer.valueOf(r1);
        r1 = r2.intValue();
        r8 = -1;
        if (r1 != r8) goto L_0x04e6;
    L_0x0423:
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 17;
        if (r1 < r2) goto L_0x04e0;
    L_0x0429:
        r1 = android.view.View.generateViewId();
    L_0x042d:
        r2 = java.lang.Integer.valueOf(r1);
        r1 = r2.intValue();
        r6.setId(r1);
        r1 = -559039810; // 0xffffffffdeadbabe float:-6.2592635E18 double:NaN;
        r6.setTag(r1, r4);
        r1 = 1;
    L_0x043f:
        r6 = r13.f5790k;
        r8 = new java.lang.StringBuilder;
        r9 = "/";
        r8.<init>(r9);
        r7 = r8.append(r7);
        r8 = "[";
        r7 = r7.append(r8);
        r6 = r7.append(r6);
        r7 = "]";
        r6 = r6.append(r7);
        r6 = r6.toString();
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r4 = r7.append(r4);
        r7 = ":";
        r4 = r4.append(r7);
        r4 = r4.append(r6);
        r4 = r4.toString();
        r7 = r13.f5803x;
        r4 = r4.hashCode();
        r8 = (long) r4;
        r10 = 4294967295; // 0xffffffff float:NaN double:2.1219957905E-314;
        r8 = r8 & r10;
        r4 = java.lang.Long.valueOf(r8);
        r7.put(r4, r2);
        if (r1 == 0) goto L_0x0492;
    L_0x048d:
        r1 = r13.f5802w;
        r1.put(r2, r6);
    L_0x0492:
        r1 = r13.f5804y;
        r1.put(r6, r2);
    L_0x0497:
        r1 = r13.f5805z;
        r2 = r13.f5792m;
        r4 = r13.f5803x;
        r1.put(r2, r4);
        r1 = r13.f5788i;
        r2 = r13.f5792m;
        r4 = r13.f5802w;
        r1.put(r2, r4);
        r1 = r13.f5777A;
        r2 = r13.f5792m;
        r4 = r13.f5804y;
        r1.put(r2, r4);
        r1 = r13.f5792m;
        r13.m7283c(r1);
    L_0x04b7:
        r2 = r3;
        goto L_0x0119;
    L_0x04ba:
        r1 = 0;
        goto L_0x0386;
    L_0x04bd:
        r2 = r13.f5780a;
        r6 = com.ensighten.C1733V.ACTIVITY;
        if (r2 == r6) goto L_0x04cf;
    L_0x04c3:
        r2 = r13.f5780a;
        r6 = com.ensighten.C1733V.FRAGMENT;
        if (r2 == r6) goto L_0x04cf;
    L_0x04c9:
        r2 = r13.f5780a;
        r6 = com.ensighten.C1733V.SUPPORT_FRAGMENT;
        if (r2 != r6) goto L_0x051b;
    L_0x04cf:
        r1 = r13.f5793n;
        r2 = "onResume";
        r1 = r1.equals(r2);
        r2 = r1;
        goto L_0x0398;
    L_0x04da:
        r1 = r3.getChildCount();
        goto L_0x039e;
    L_0x04e0:
        r1 = com.ensighten.C1720Q.m7254a();
        goto L_0x042d;
    L_0x04e6:
        r1 = -559039810; // 0xffffffffdeadbabe float:-6.2592635E18 double:NaN;
        r1 = r6.getTag(r1);
        if (r1 == 0) goto L_0x04f2;
    L_0x04ef:
        r1 = 1;
        goto L_0x043f;
    L_0x04f2:
        r1 = 0;
        goto L_0x043f;
    L_0x04f5:
        r1 = r13.f5792m;
        r2 = r13.m7280a(r3);
        r13.m7281a(r1, r3, r2);
        goto L_0x0497;
    L_0x04ff:
        r1 = 0;
        goto L_0x0154;
    L_0x0502:
        r1 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        r1 = r0.findViewById(r1);
        r1 = (android.view.ViewGroup) r1;
        if (r1 == 0) goto L_0x0169;
    L_0x050d:
        r2 = r1;
        goto L_0x0169;
    L_0x0510:
        r1 = r3.f5929b;
        r2 = r3.f5940m;
        r4 = 750; // 0x2ee float:1.051E-42 double:3.705E-321;
        r1.postDelayed(r2, r4);
        goto L_0x019e;
    L_0x051b:
        r2 = r1;
        goto L_0x0398;
    L_0x051e:
        r1 = r2;
        goto L_0x033a;
    L_0x0521:
        r0 = r13;
        goto L_0x01f8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1735W.mo15086a(java.lang.Object, java.lang.String):void");
    }

    /* renamed from: a */
    public final void mo15084a() {
        Object currentActivity = Ensighten.getCurrentActivity();
        if (currentActivity != null) {
            mo15086a(currentActivity, "onResume");
        }
        if (Ensighten.getDumpManager().f5932e) {
            Activity activity = (Activity) this.f5794o.get();
            if (activity != null && Ensighten.getDumpManager().f5930c && activity != null) {
                Activity parent;
                do {
                    parent = activity.getParent();
                    if (parent != null) {
                        activity = parent;
                        continue;
                    }
                } while (parent != null);
                if (VERSION.SDK_INT >= 11) {
                    try {
                        activity.recreate();
                        return;
                    } catch (IllegalStateException e) {
                        if (C1845i.m7364k()) {
                            C1845i.m7349b(e);
                            return;
                        }
                        return;
                    }
                }
                Intent intent = activity.getIntent();
                if (intent != null) {
                    activity.finish();
                    activity.startActivity(intent);
                }
            }
        }
    }

    /* renamed from: a */
    private String m7280a(ViewGroup viewGroup) {
        if (this.f5780a != C1733V.ADAPTER || this.f5782c == null) {
            return null;
        }
        String str = (String) this.f5797r.get(this.f5782c.hashCode());
        if (viewGroup != null) {
            return str + viewGroup.getClass().getSimpleName() + "[" + this.f5790k + "]/";
        }
        return str;
    }

    /* renamed from: a */
    public final View mo15083a(int i, View view) {
        this.f5803x = (Map) this.f5805z.get(this.f5792m);
        this.f5802w = (Map) this.f5788i.get(this.f5792m);
        this.f5804y = (Map) this.f5777A.get(this.f5792m);
        m7281a(this.f5792m, (ViewGroup) view, m7280a((ViewGroup) view));
        this.f5805z.put(this.f5792m, this.f5803x);
        this.f5788i.put(this.f5792m, this.f5802w);
        this.f5777A.put(this.f5792m, this.f5804y);
        if (Ensighten.getDumpManager().f5930c) {
            m7283c(this.f5792m);
        }
        String str = this.f5783d + "[" + this.f5796q + "]:" + this.f5781b;
        if (str == null || !this.f5788i.containsKey(str)) {
            str = null;
        } else {
            str = (String) ((Map) this.f5788i.get(str)).get(Integer.valueOf(i));
        }
        return view.findViewById(mo15082a(String.format("xPath:%s", new Object[]{str})));
    }

    /* renamed from: a */
    private void m7281a(String str, ViewGroup viewGroup, String str2) {
        int childCount = viewGroup.getChildCount();
        HashMap hashMap = new HashMap();
        Integer valueOf = Integer.valueOf(0);
        while (true) {
            Integer num = valueOf;
            if (num.intValue() < childCount) {
                Object obj;
                Object obj2;
                View childAt = viewGroup.getChildAt(num.intValue());
                String simpleName = childAt.getClass().getSimpleName();
                Integer valueOf2 = Integer.valueOf(childAt.getId());
                Integer obj3;
                if (valueOf2.intValue() == -1) {
                    valueOf2 = Integer.valueOf(VERSION.SDK_INT >= 17 ? View.generateViewId() : C1720Q.m7254a());
                    childAt.setId(valueOf2.intValue());
                    childAt.setTag(-559039810, str);
                    obj3 = valueOf2;
                    obj2 = 1;
                } else if (childAt.getTag(-559039810) != null) {
                    obj3 = valueOf2;
                    int obj22 = 1;
                } else {
                    obj3 = valueOf2;
                    obj22 = null;
                }
                String str3 = str2 + simpleName + "[" + C1735W.m7279a(str2 + simpleName, hashMap) + "]";
                if (childAt instanceof ViewGroup) {
                    Adapter adapter;
                    if (str2 == null) {
                        this.f5803x.put(Long.valueOf(((long) (str + ":/").hashCode()) & 4294967295L), Integer.valueOf(16908290));
                        if (obj22 != null) {
                            this.f5802w.put(Integer.valueOf(16908290), "/");
                        }
                        this.f5804y.put("/", Integer.valueOf(16908290));
                        str2 = "/" + simpleName + "[0" + "]";
                        if (AdapterView.class.isInstance(childAt)) {
                            adapter = ((AdapterView) childAt).getAdapter();
                            if (adapter != null) {
                                this.f5797r.append(adapter.hashCode(), str2 + "/");
                            }
                        }
                        m7281a(str, (ViewGroup) childAt, str2 + "/");
                        this.f5803x.put(Long.valueOf(((long) (str + ":" + str2).hashCode()) & 4294967295L), obj3);
                        if (obj22 != null) {
                            this.f5802w.put(obj3, str2);
                        }
                        this.f5804y.put(str2, obj3);
                    } else {
                        if (AdapterView.class.isInstance(childAt)) {
                            adapter = ((AdapterView) childAt).getAdapter();
                            if (adapter != null) {
                                this.f5797r.append(adapter.hashCode(), str3 + "/");
                            }
                        }
                        m7281a(str, (ViewGroup) childAt, str3 + "/");
                        this.f5803x.put(Long.valueOf(((long) (str + ":" + str3).hashCode()) & 4294967295L), obj3);
                        if (obj22 != null) {
                            this.f5802w.put(obj3, str3);
                        }
                        this.f5804y.put(str3, obj3);
                    }
                } else if (str2 == null) {
                    int id = this.f5799t != null ? this.f5799t.getId() : 16908290;
                    this.f5803x.put(Long.valueOf(((long) (str + ":/").hashCode()) & 4294967295L), Integer.valueOf(id));
                    if (obj22 != null) {
                        this.f5802w.put(Integer.valueOf(id), "/");
                    }
                    this.f5804y.put("/", Integer.valueOf(id));
                    str2 = "/";
                    this.f5803x.put(Long.valueOf(((long) (str + ":" + str2).hashCode()) & 4294967295L), obj3);
                    if (obj22 != null) {
                        this.f5802w.put(obj3, str2 + simpleName + "[0" + "]");
                    }
                    this.f5804y.put(str2 + simpleName + "[0" + "]", obj3);
                } else {
                    this.f5803x.put(Long.valueOf(((long) (str + ":" + str3).hashCode()) & 4294967295L), obj3);
                    if (obj22 != null) {
                        this.f5802w.put(obj3, str3);
                    }
                    this.f5804y.put(str3, obj3);
                }
                valueOf = Integer.valueOf(num.intValue() + 1);
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private static int m7279a(String str, HashMap<String, Integer> hashMap) {
        int intValue;
        if (hashMap.containsKey(str)) {
            intValue = ((Integer) hashMap.get(str)).intValue() + 1;
        } else {
            intValue = 0;
        }
        hashMap.put(str, Integer.valueOf(intValue));
        return intValue;
    }

    /* renamed from: c */
    private void m7283c(String str) {
        if (C1845i.m7355c()) {
            Iterator it = this.f5805z.keySet().iterator();
            int i = 1;
            while (it.hasNext() && i != 0) {
                Map map = str != null ? (Map) this.f5777A.get(str) : (Map) this.f5777A.get((String) it.next());
                C1845i.m7345a(String.format("mapKey=%s", new Object[]{r0}));
                for (String str2 : map.keySet()) {
                    Integer num = (Integer) map.get(str2);
                    C1845i.m7345a(String.format("pathKey=%s value=0x%s", new Object[]{(String) r6.next(), Integer.toHexString(num.intValue())}));
                }
                i = str == null ? 1 : 0;
            }
        }
    }

    /* renamed from: d */
    private void m7284d() {
        if (this.f5780a == C1733V.ACTIVITY && this.f5793n.equals("onCreate")) {
            this.f5789j = new SparseArray();
            this.f5778B = new HashMap();
            this.f5779C = new HashMap();
        } else if ((this.f5780a == C1733V.FRAGMENT || this.f5780a == C1733V.SUPPORT_FRAGMENT) && this.f5793n.equals("onActivityCreated")) {
            Object obj;
            if (this.f5780a == C1733V.FRAGMENT) {
                obj = this.f5785f;
            } else {
                android.support.p000v4.app.Fragment obj2 = this.f5786g;
            }
            if (obj2 != null && this.f5799t != null) {
                this.f5800u = this.f5780a == C1733V.FRAGMENT ? this.f5785f.getId() : this.f5786g.getId();
                if (this.f5800u == -1) {
                    this.f5800u = VERSION.SDK_INT >= 17 ? View.generateViewId() : C1720Q.m7254a();
                    try {
                        Field declaredField = this.f5780a == C1733V.FRAGMENT ? Fragment.class.getDeclaredField("mFragmentId") : android.support.p000v4.app.Fragment.class.getDeclaredField("mFragmentId");
                        declaredField.setAccessible(true);
                        declaredField.set(obj2, Integer.valueOf(this.f5800u));
                        String str = "/" + obj2.getClass().getSimpleName();
                        str = str + "[" + C1735W.m7279a(str, this.f5779C) + "]";
                        this.f5789j.put(this.f5800u, str);
                        this.f5778B.put(str, Integer.valueOf(this.f5800u));
                    } catch (Exception e) {
                        if (C1845i.m7364k()) {
                            C1845i.m7349b(e);
                        }
                    }
                }
                this.f5799t.setTag(-1414673666, Integer.valueOf(this.f5800u));
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:9:0x0055=Splitter:B:9:0x0055, B:35:0x00e9=Splitter:B:35:0x00e9} */
    /* renamed from: a */
    public final int mo15082a(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        r0 = r6.f5787h;
        r2 = r6.f5780a;
        r3 = com.ensighten.C1733V.ADAPTER;
        if (r2 != r3) goto L_0x005d;
    L_0x0009:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = r6.f5783d;
        r0 = r0.append(r2);
        r2 = "[";
        r0 = r0.append(r2);
        r2 = r6.f5796q;
        r0 = r0.append(r2);
        r2 = "]:";
        r0 = r0.append(r2);
        r2 = r6.f5781b;
        r0 = r0.append(r2);
        r0 = r0.toString();
    L_0x0030:
        r2 = "viewId:android.R.id.";
        r2 = r7.startsWith(r2);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x0066;
    L_0x0039:
        r0 = 20;
        r2 = r7.substring(r0);	 Catch:{ NullPointerException -> 0x011c }
        r0 = r6.f5794o;	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.get();	 Catch:{ NullPointerException -> 0x011c }
        r0 = (android.app.Activity) r0;	 Catch:{ NullPointerException -> 0x011c }
        if (r0 == 0) goto L_0x0126;
    L_0x0049:
        r0 = r0.getResources();	 Catch:{ NullPointerException -> 0x011c }
        r3 = "id";
        r4 = "android";
        r0 = r0.getIdentifier(r2, r3, r4);	 Catch:{ NullPointerException -> 0x011c }
    L_0x0055:
        r1 = com.ensighten.Ensighten.getOptimizationManager();	 Catch:{ NullPointerException -> 0x00b7 }
        r2 = 1;
        r1.f5673a = r2;	 Catch:{ NullPointerException -> 0x00b7 }
    L_0x005c:
        return r0;
    L_0x005d:
        r2 = r6.f5780a;
        r3 = com.ensighten.C1733V.ACTIVITY;
        if (r2 != r3) goto L_0x0030;
    L_0x0063:
        r0 = r6.f5781b;
        goto L_0x0030;
    L_0x0066:
        r2 = "eId:";
        r2 = r7.startsWith(r2);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x009a;
    L_0x006e:
        r2 = r6.f5805z;	 Catch:{ NullPointerException -> 0x011c }
        r2 = r2.containsKey(r0);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x0121;
    L_0x0076:
        r2 = r6.f5805z;	 Catch:{ NullPointerException -> 0x011c }
        r0 = r2.get(r0);	 Catch:{ NullPointerException -> 0x011c }
        r0 = (java.util.Map) r0;	 Catch:{ NullPointerException -> 0x011c }
        r2 = 4;
        r2 = r7.substring(r2);	 Catch:{ NullPointerException -> 0x011c }
        r2 = com.ensighten.Utils.decodeStringToLong(r2);	 Catch:{ NullPointerException -> 0x011c }
        r2 = r2.longValue();	 Catch:{ NullPointerException -> 0x011c }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.get(r2);	 Catch:{ NullPointerException -> 0x011c }
        r0 = (java.lang.Integer) r0;	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.intValue();	 Catch:{ NullPointerException -> 0x011c }
        goto L_0x005c;
    L_0x009a:
        r2 = "resId:";
        r2 = r7.startsWith(r2);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x00c2;
    L_0x00a2:
        r0 = 6;
        r0 = r7.substring(r0);	 Catch:{ NullPointerException -> 0x011c }
        r0 = com.ensighten.Utils.decodeStringToLong(r0);	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.intValue();	 Catch:{ NullPointerException -> 0x011c }
        r1 = com.ensighten.Ensighten.getOptimizationManager();	 Catch:{ NullPointerException -> 0x00b7 }
        r2 = 1;
        r1.f5673a = r2;	 Catch:{ NullPointerException -> 0x00b7 }
        goto L_0x005c;
    L_0x00b7:
        r1 = move-exception;
    L_0x00b8:
        r2 = com.ensighten.C1845i.m7364k();
        if (r2 == 0) goto L_0x005c;
    L_0x00be:
        com.ensighten.C1845i.m7353c(r1);
        goto L_0x005c;
    L_0x00c2:
        r2 = "viewId:R.id.";
        r2 = r7.startsWith(r2);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x00f2;
    L_0x00cb:
        r0 = 12;
        r2 = r7.substring(r0);	 Catch:{ NullPointerException -> 0x011c }
        r0 = r6.f5794o;	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.get();	 Catch:{ NullPointerException -> 0x011c }
        r0 = (android.app.Activity) r0;	 Catch:{ NullPointerException -> 0x011c }
        if (r0 == 0) goto L_0x0124;
    L_0x00db:
        r3 = r0.getResources();	 Catch:{ NullPointerException -> 0x011c }
        r4 = "id";
        r0 = r0.getPackageName();	 Catch:{ NullPointerException -> 0x011c }
        r0 = r3.getIdentifier(r2, r4, r0);	 Catch:{ NullPointerException -> 0x011c }
    L_0x00e9:
        r1 = com.ensighten.Ensighten.getOptimizationManager();	 Catch:{ NullPointerException -> 0x00b7 }
        r2 = 1;
        r1.f5673a = r2;	 Catch:{ NullPointerException -> 0x00b7 }
        goto L_0x005c;
    L_0x00f2:
        r2 = "xPath:";
        r2 = r7.startsWith(r2);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x0121;
    L_0x00fb:
        r2 = r6.f5777A;	 Catch:{ NullPointerException -> 0x011c }
        r2 = r2.containsKey(r0);	 Catch:{ NullPointerException -> 0x011c }
        if (r2 == 0) goto L_0x0121;
    L_0x0103:
        r2 = r6.f5777A;	 Catch:{ NullPointerException -> 0x011c }
        r0 = r2.get(r0);	 Catch:{ NullPointerException -> 0x011c }
        r0 = (java.util.Map) r0;	 Catch:{ NullPointerException -> 0x011c }
        r2 = 6;
        r2 = r7.substring(r2);	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.get(r2);	 Catch:{ NullPointerException -> 0x011c }
        r0 = (java.lang.Integer) r0;	 Catch:{ NullPointerException -> 0x011c }
        r0 = r0.intValue();	 Catch:{ NullPointerException -> 0x011c }
        goto L_0x005c;
    L_0x011c:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x00b8;
    L_0x0121:
        r0 = r1;
        goto L_0x005c;
    L_0x0124:
        r0 = r1;
        goto L_0x00e9;
    L_0x0126:
        r0 = r1;
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1735W.mo15082a(java.lang.String):int");
    }

    /* renamed from: b */
    public final int mo15087b(String str) {
        try {
            if (str.startsWith("resId:")) {
                return Utils.decodeStringToLong(str.substring(6)).intValue();
            }
            int intValue;
            if (str.startsWith("xPath:")) {
                String substring = str.substring(6);
                if (this.f5778B.containsKey(substring)) {
                    intValue = ((Integer) this.f5778B.get(substring)).intValue();
                    return intValue;
                }
            }
            intValue = 0;
            return intValue;
        } catch (NullPointerException e) {
            if (!C1845i.m7364k()) {
                return 0;
            }
            C1845i.m7353c(e);
            return 0;
        }
    }

    /* renamed from: b */
    public static boolean m7282b() {
        return Ensighten.getDumpManager().f5930c || Ensighten.getOptimizationManager().f5681i;
    }

    /* renamed from: a */
    public final void mo15085a(Activity activity) {
        this.f5794o = new WeakReference(activity);
        if (activity != null) {
            this.f5781b = activity.getClass().getName();
        }
    }

    /* renamed from: c */
    public final Activity mo15088c() {
        if (this.f5794o == null) {
            return null;
        }
        return (Activity) this.f5794o.get();
    }
}
