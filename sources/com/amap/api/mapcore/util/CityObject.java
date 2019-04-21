package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.mapcore.util.FileCopy.C0756a;
import com.amap.api.mapcore.util.IDownloadListener.C0758a;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.newrelic.agent.android.agentdata.HexAttributes;
import java.io.File;

/* renamed from: com.amap.api.mapcore.util.bg */
public class CityObject extends OfflineMapCity implements TaskItem, IDownloadItem {
    /* renamed from: l */
    public static final Creator<CityObject> f1360l = new C0759bh();
    /* renamed from: a */
    public CityStateImp f1361a;
    /* renamed from: b */
    public CityStateImp f1362b;
    /* renamed from: c */
    public CityStateImp f1363c;
    /* renamed from: d */
    public CityStateImp f1364d;
    /* renamed from: e */
    public CityStateImp f1365e;
    /* renamed from: f */
    public CityStateImp f1366f;
    /* renamed from: g */
    public CityStateImp f1367g;
    /* renamed from: h */
    public CityStateImp f1368h;
    /* renamed from: i */
    CityStateImp f1369i;
    /* renamed from: j */
    Context f1370j;
    /* renamed from: k */
    boolean f1371k;
    /* renamed from: m */
    private String f1372m;
    /* renamed from: n */
    private String f1373n;
    /* renamed from: o */
    private long f1374o;

    /* renamed from: a */
    public void mo8855a(String str) {
        this.f1373n = str;
    }

    /* renamed from: a */
    public String mo8851a() {
        return this.f1373n;
    }

    /* renamed from: b */
    public String mo8834b() {
        return getUrl();
    }

    public CityObject(Context context, OfflineMapCity offlineMapCity) {
        this(context, offlineMapCity.getState());
        setCity(offlineMapCity.getCity());
        setUrl(offlineMapCity.getUrl());
        setState(offlineMapCity.getState());
        setCompleteCode(offlineMapCity.getcompleteCode());
        setAdcode(offlineMapCity.getAdcode());
        setVersion(offlineMapCity.getVersion());
        setSize(offlineMapCity.getSize());
        setCode(offlineMapCity.getCode());
        setJianpin(offlineMapCity.getJianpin());
        setPinyin(offlineMapCity.getPinyin());
        mo8866s();
    }

    public CityObject(Context context, int i) {
        this.f1361a = new DefaultState(6, this);
        this.f1362b = new WaitingState(2, this);
        this.f1363c = new LoadingState(0, this);
        this.f1364d = new PauseState(3, this);
        this.f1365e = new UnzipState(1, this);
        this.f1366f = new CompleteState(4, this);
        this.f1367g = new NewVersionState(7, this);
        this.f1368h = new ErrorState(-1, this);
        this.f1372m = null;
        this.f1373n = "";
        this.f1371k = false;
        this.f1374o = 0;
        this.f1370j = context;
        mo8852a(i);
    }

    /* renamed from: a */
    public void mo8852a(int i) {
        switch (i) {
            case -1:
                this.f1369i = this.f1368h;
                break;
            case 0:
                this.f1369i = this.f1363c;
                break;
            case 1:
                this.f1369i = this.f1365e;
                break;
            case 2:
                this.f1369i = this.f1362b;
                break;
            case 3:
                this.f1369i = this.f1364d;
                break;
            case 4:
                this.f1369i = this.f1366f;
                break;
            case 6:
                this.f1369i = this.f1361a;
                break;
            case 7:
                this.f1369i = this.f1367g;
                break;
            default:
                if (i < 0) {
                    this.f1369i = this.f1368h;
                    break;
                }
                break;
        }
        setState(i);
    }

    /* renamed from: a */
    public void mo8854a(CityStateImp cityStateImp) {
        this.f1369i = cityStateImp;
        setState(cityStateImp.mo9210b());
    }

    /* renamed from: c */
    public CityStateImp mo8856c() {
        return this.f1369i;
    }

    /* renamed from: d */
    public void mo8857d() {
        OfflineDownloadManager a = OfflineDownloadManager.m1830a(this.f1370j);
        if (a != null) {
            a.mo8890c(this);
        }
    }

    /* renamed from: e */
    public void mo8858e() {
        OfflineDownloadManager a = OfflineDownloadManager.m1830a(this.f1370j);
        if (a != null) {
            a.mo8896e(this);
            mo8857d();
        }
    }

    /* renamed from: f */
    public void mo8859f() {
        Utility.m2180a("CityOperation current State==>" + mo8856c().mo9210b());
        if (this.f1369i.equals(this.f1364d)) {
            this.f1369i.mo9214e();
        } else if (this.f1369i.equals(this.f1363c)) {
            this.f1369i.mo9215f();
        } else if (this.f1369i.equals(this.f1367g) || this.f1369i.equals(this.f1368h)) {
            mo8863j();
            this.f1371k = true;
        } else {
            mo8856c().mo9212c();
        }
    }

    /* renamed from: g */
    public void mo8860g() {
        this.f1369i.mo9216g();
    }

    /* renamed from: h */
    public void mo8861h() {
        this.f1369i.mo9208a();
        if (this.f1371k) {
            this.f1369i.mo9212c();
        }
        this.f1371k = false;
    }

    /* renamed from: i */
    public void mo8862i() {
        if (!this.f1369i.equals(this.f1366f)) {
        }
        this.f1369i.mo9217h();
    }

    /* renamed from: j */
    public void mo8863j() {
        OfflineDownloadManager a = OfflineDownloadManager.m1830a(this.f1370j);
        if (a != null) {
            a.mo8882a(this);
        }
    }

    /* renamed from: k */
    public void mo8864k() {
        OfflineDownloadManager a = OfflineDownloadManager.m1830a(this.f1370j);
        if (a != null) {
            a.mo8887b(this);
        }
    }

    /* renamed from: l */
    public void mo8865l() {
        OfflineDownloadManager a = OfflineDownloadManager.m1830a(this.f1370j);
        if (a != null) {
            a.mo8893d(this);
        }
    }

    /* renamed from: m */
    public void mo8844m() {
        this.f1374o = 0;
        if (!this.f1369i.equals(this.f1362b)) {
            Log.e(HexAttributes.HEX_ATTR_THREAD_STATE, "state must be waiting when download onStart");
        }
        this.f1369i.mo9213d();
    }

    /* renamed from: a */
    public void mo8842a(long j, long j2) {
        long j3 = (100 * j2) / j;
        if (((int) j3) != getcompleteCode()) {
            setCompleteCode((int) j3);
            mo8857d();
        }
    }

    /* renamed from: n */
    public void mo8845n() {
        if (!this.f1369i.equals(this.f1363c)) {
            Log.e(HexAttributes.HEX_ATTR_THREAD_STATE, "state must be Loading when download onFinish");
        }
        this.f1369i.mo9218i();
    }

    /* renamed from: a */
    public void mo8843a(C0758a c0758a) {
        if (this.f1369i.equals(this.f1363c) || this.f1369i.equals(this.f1362b)) {
            this.f1369i.mo9216g();
        }
    }

    /* renamed from: o */
    public void mo8846o() {
        mo8858e();
    }

    /* renamed from: p */
    public void mo8837p() {
        this.f1374o = 0;
        setCompleteCode(0);
        if (!this.f1369i.equals(this.f1365e)) {
        }
        this.f1369i.mo9213d();
    }

    /* renamed from: q */
    public void mo8838q() {
        if (!this.f1369i.equals(this.f1365e)) {
        }
        this.f1369i.mo9216g();
    }

    /* renamed from: a */
    public void mo8835a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f1374o > 500) {
            if (((int) j) > getcompleteCode()) {
                setCompleteCode((int) j);
                mo8857d();
            }
            this.f1374o = currentTimeMillis;
        }
    }

    /* renamed from: b */
    public void mo8836b(String str) {
        if (!this.f1369i.equals(this.f1365e)) {
        }
        this.f1373n = str;
        String t = mo8867t();
        String u = mo8868u();
        if (TextUtils.isEmpty(t) || TextUtils.isEmpty(u)) {
            mo8838q();
            return;
        }
        File file = new File(u + "/");
        File file2 = new File(Util.m2350a(this.f1370j) + "vmap/");
        File file3 = new File(Util.m2350a(this.f1370j));
        if (!file3.exists()) {
            file3.mkdir();
        }
        if (!file2.exists()) {
            file2.mkdir();
        }
        m1787a(file, file2, t);
    }

    /* renamed from: r */
    public void mo8839r() {
        mo8858e();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: s */
    public void mo8866s() {
        this.f1372m = OfflineDownloadManager.f1380a + getAdcode() + ".zip" + ".tmp";
    }

    /* renamed from: t */
    public String mo8867t() {
        if (TextUtils.isEmpty(this.f1372m)) {
            return null;
        }
        return this.f1372m.substring(0, this.f1372m.lastIndexOf("."));
    }

    /* renamed from: u */
    public String mo8868u() {
        if (TextUtils.isEmpty(this.f1372m)) {
            return null;
        }
        String t = mo8867t();
        return t.substring(0, t.lastIndexOf(46));
    }

    /* renamed from: a */
    private void m1787a(final File file, File file2, final String str) {
        new FileCopy().mo8988a(file, file2, -1, Utility.m2177a(file), new C0756a() {
            /* renamed from: a */
            public void mo8809a(String str, String str2, float f) {
                int i = (int) (60.0d + (((double) f) * 0.39d));
                if (i - CityObject.this.getcompleteCode() > 0 && System.currentTimeMillis() - CityObject.this.f1374o > 1000) {
                    CityObject.this.setCompleteCode(i);
                    CityObject.this.f1374o = System.currentTimeMillis();
                }
            }

            /* renamed from: a */
            public void mo8808a(String str, String str2) {
            }

            /* renamed from: b */
            public void mo8811b(String str, String str2) {
                try {
                    new File(str).delete();
                    Utility.m2184b(file);
                    CityObject.this.setCompleteCode(100);
                    CityObject.this.f1369i.mo9218i();
                } catch (Exception e) {
                    CityObject.this.f1369i.mo9216g();
                }
            }

            /* renamed from: a */
            public void mo8810a(String str, String str2, int i) {
                CityObject.this.f1369i.mo9216g();
            }
        });
    }

    /* renamed from: v */
    public boolean mo8869v() {
        if (((double) Utility.m2176a()) < (((double) getSize()) * 2.5d) - ((double) (((long) getcompleteCode()) * getSize()))) {
        }
        return false;
    }

    /* renamed from: w */
    public UpdateItem mo8870w() {
        setState(this.f1369i.mo9210b());
        UpdateItem updateItem = new UpdateItem((OfflineMapCity) this, this.f1370j);
        updateItem.mo8960a(mo8851a());
        Utility.m2180a("vMapFileNames: " + mo8851a());
        return updateItem;
    }

    /* renamed from: a */
    public void mo8853a(UpdateItem updateItem) {
        mo8852a(updateItem.f1438l);
        setCity(updateItem.mo8953e());
        setSize(updateItem.mo8957i());
        setVersion(updateItem.mo8954f());
        setCompleteCode(updateItem.mo8958j());
        setAdcode(updateItem.mo8955g());
        setUrl(updateItem.mo8956h());
        String c = updateItem.mo8963c();
        if (c != null && c.length() > 0) {
            mo8855a(c);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1373n);
    }

    public CityObject(Parcel parcel) {
        super(parcel);
        this.f1361a = new DefaultState(6, this);
        this.f1362b = new WaitingState(2, this);
        this.f1363c = new LoadingState(0, this);
        this.f1364d = new PauseState(3, this);
        this.f1365e = new UnzipState(1, this);
        this.f1366f = new CompleteState(4, this);
        this.f1367g = new NewVersionState(7, this);
        this.f1368h = new ErrorState(-1, this);
        this.f1372m = null;
        this.f1373n = "";
        this.f1371k = false;
        this.f1374o = 0;
        this.f1373n = parcel.readString();
    }

    /* renamed from: x */
    public boolean mo8848x() {
        return mo8869v();
    }

    /* renamed from: y */
    public String mo8849y() {
        StringBuffer stringBuffer = new StringBuffer(getAdcode());
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    /* renamed from: z */
    public String mo8850z() {
        return getAdcode();
    }

    /* renamed from: A */
    public String mo8840A() {
        return mo8867t();
    }

    /* renamed from: B */
    public String mo8841B() {
        return mo8868u();
    }
}
