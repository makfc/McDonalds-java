package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.amap.api.maps2d.AMapException;
import com.amap.api.maps2d.model.TileProvider;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.amap.api.mapcore2d.bt */
class TileServerHandler extends C0932bh<ArrayList<TileCoordinate>, ArrayList<TileCoordinate>> {
    /* renamed from: b */
    private Context f2625b;
    /* renamed from: f */
    private C0886am f2626f = null;
    /* renamed from: g */
    private TileProvider f2627g;

    public TileServerHandler(Context context, ArrayList<TileCoordinate> arrayList, TileProvider tileProvider) {
        super(arrayList);
        this.f2625b = context;
        this.f2627g = tileProvider;
        mo10068a(C0971ct.m4002a(this.f2625b));
        mo10067a(5000);
        mo10070b(AutoNaviConnector.DEFAULT_SEARCH_RADIUS);
    }

    /* renamed from: a */
    public void mo10121a(C0886am c0886am) {
        this.f2626f = c0886am;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public byte[] mo10078b() throws AMapException {
        if (this.f2627g != null) {
            return this.f2627g.getTile(((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2595b, ((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2596c, ((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2597d).data;
        }
        return super.mo10078b();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public ArrayList<TileCoordinate> mo10079c() {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) this.f2564a).iterator();
        while (it.hasNext()) {
            arrayList.add(new TileCoordinate((TileCoordinate) it.next()));
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public ArrayList<TileCoordinate> mo10077a(byte[] bArr) throws AMapException {
        ArrayList<TileCoordinate> arrayList = null;
        if (!(this.f2564a == null || bArr == null)) {
            int size = ((ArrayList) this.f2564a).size();
            int i = 0;
            while (i < size) {
                TileCoordinate tileCoordinate = (TileCoordinate) ((ArrayList) this.f2564a).get(i);
                if (mo10120a(bArr, tileCoordinate) < 0) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    TileCoordinate tileCoordinate2 = new TileCoordinate(tileCoordinate);
                    if (this.f2626f.f2244h && tileCoordinate2.f2597d > 9 && !C0954cj.m3877a(tileCoordinate2.f2595b, tileCoordinate2.f2596c, tileCoordinate2.f2597d)) {
                        tileCoordinate2.f2601h = true;
                    }
                    arrayList.add(tileCoordinate2);
                }
                i++;
                arrayList = arrayList;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public int mo10120a(byte[] bArr, TileCoordinate tileCoordinate) {
        if (tileCoordinate == null || bArr == null) {
            return -1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tileCoordinate.f2595b);
        stringBuilder.append("-");
        stringBuilder.append(tileCoordinate.f2596c);
        stringBuilder.append("-");
        stringBuilder.append(tileCoordinate.f2597d);
        if (this.f2626f == null || this.f2626f.f2250n == null) {
            return -1;
        }
        int a = this.f2626f.f2250n.mo9919a(null, bArr, false, null, stringBuilder.toString());
        if (a < 0) {
            return -1;
        }
        m3775a(tileCoordinate, a);
        if (this.f2626f == null || !this.f2626f.f2242f) {
            return a;
        }
        byte[] a2 = m3776a(this.f2626f.f2250n.mo9920a(a));
        if (this.f2626f == null || this.f2626f.f2251o == null) {
            return a;
        }
        this.f2626f.f2251o.mo10324a(a2, tileCoordinate.f2595b, tileCoordinate.f2596c, tileCoordinate.f2597d);
        return a;
    }

    /* renamed from: a */
    private byte[] m3776a(Bitmap bitmap) {
        String str = "Bitmap2Bytes";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            C0955ck.m3888a(th, "TileServerHandler", str);
            return null;
        }
    }

    /* renamed from: a */
    private void m3775a(TileCoordinate tileCoordinate, int i) {
        if (tileCoordinate != null && i >= 0 && this.f2626f != null && this.f2626f.f2252p != null) {
            SyncList syncList = this.f2626f.f2252p;
            synchronized (this.f2626f) {
                int size = syncList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    TileCoordinate tileCoordinate2 = (TileCoordinate) syncList.get(i2);
                    if (tileCoordinate2 != null && tileCoordinate2.equals(tileCoordinate)) {
                        tileCoordinate2.f2600g = i;
                        break;
                    }
                }
            }
        }
    }

    /* renamed from: e */
    public Map<String, String> mo10071e() {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", "AMAP_SDK_Android_2DMap_2.9.0");
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{"2.9.0", "2dmap"}));
        hashMap.put("X-INFO", C0966cp.m3936a(this.f2625b));
        hashMap.put(Parameters.API_KEY, C0957cm.m3906f(this.f2625b));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    /* renamed from: f */
    public Map<String, String> mo10072f() {
        return null;
    }

    /* renamed from: g */
    public String mo10073g() {
        if (((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2601h) {
            return String.format(MapServerUrl.m3230a().mo9862d(), new Object[]{Integer.valueOf(((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2595b), Integer.valueOf(((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2596c), Integer.valueOf(((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2597d)});
        }
        int pow = (int) Math.pow(2.0d, (double) ((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2597d);
        int i = ((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2595b;
        pow = i >= pow ? i - pow : i < 0 ? i + pow : i;
        return this.f2626f.f2246j.mo9873a(pow, ((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2596c, ((TileCoordinate) ((ArrayList) this.f2564a).get(0)).f2597d);
    }
}
