package com.amap.api.mapcore.util;

@EntityClass(a = "update_item")
/* renamed from: com.amap.api.mapcore.util.bv */
public class DTInfo {
    @EntityField(a = "title", b = 6)
    /* renamed from: a */
    protected String f1427a = null;
    @EntityField(a = "url", b = 6)
    /* renamed from: b */
    protected String f1428b = null;
    @EntityField(a = "mAdcode", b = 6)
    /* renamed from: c */
    protected String f1429c = null;
    @EntityField(a = "fileName", b = 6)
    /* renamed from: d */
    protected String f1430d = null;
    @EntityField(a = "version", b = 6)
    /* renamed from: e */
    protected String f1431e = "";
    @EntityField(a = "lLocalLength", b = 5)
    /* renamed from: f */
    protected long f1432f = 0;
    @EntityField(a = "lRemoteLength", b = 5)
    /* renamed from: g */
    protected long f1433g = 0;
    @EntityField(a = "localPath", b = 6)
    /* renamed from: h */
    protected String f1434h;
    @EntityField(a = "isProvince", b = 2)
    /* renamed from: i */
    protected int f1435i = 0;
    @EntityField(a = "mCompleteCode", b = 2)
    /* renamed from: j */
    protected int f1436j;
    @EntityField(a = "mCityCode", b = 6)
    /* renamed from: k */
    protected String f1437k = "";
    @EntityField(a = "mState", b = 2)
    /* renamed from: l */
    public int f1438l;

    /* renamed from: e */
    public String mo8953e() {
        return this.f1427a;
    }

    /* renamed from: f */
    public String mo8954f() {
        return this.f1431e;
    }

    /* renamed from: g */
    public String mo8955g() {
        return this.f1429c;
    }

    /* renamed from: h */
    public String mo8956h() {
        return this.f1428b;
    }

    /* renamed from: i */
    public long mo8957i() {
        return this.f1433g;
    }

    /* renamed from: a */
    public void mo8951a(long j) {
        this.f1432f = j;
    }

    /* renamed from: a */
    public void mo8950a(int i) {
        this.f1436j = i;
    }

    /* renamed from: j */
    public int mo8958j() {
        return this.f1436j;
    }

    /* renamed from: c */
    public void mo8952c(String str) {
        this.f1437k = str;
    }

    /* renamed from: d */
    public static String m1941d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }
}
