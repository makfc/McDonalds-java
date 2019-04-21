package com.amap.api.mapcore.util;

@EntityClass(a = "update_item_download_info")
/* renamed from: com.amap.api.mapcore.util.bt */
class DTDownloadInfo {
    @EntityField(a = "mAdcode", b = 6)
    /* renamed from: a */
    private String f1441a = "";
    @EntityField(a = "fileLength", b = 5)
    /* renamed from: b */
    private long f1442b = 0;
    @EntityField(a = "splitter", b = 2)
    /* renamed from: c */
    private int f1443c = 0;
    @EntityField(a = "startPos", b = 5)
    /* renamed from: d */
    private long f1444d = 0;
    @EntityField(a = "endPos", b = 5)
    /* renamed from: e */
    private long f1445e = 0;

    public DTDownloadInfo(String str, long j, int i, long j2, long j3) {
        this.f1441a = str;
        this.f1442b = j;
        this.f1443c = i;
        this.f1444d = j2;
        this.f1445e = j3;
    }

    /* renamed from: a */
    public long mo8966a(int i) {
        switch (i) {
            case 0:
                return mo8967b();
            default:
                return 0;
        }
    }

    /* renamed from: b */
    public long mo8968b(int i) {
        switch (i) {
            case 0:
                return mo8969c();
            default:
                return 0;
        }
    }

    /* renamed from: a */
    public static String m1957a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public long mo8965a() {
        return this.f1442b;
    }

    /* renamed from: b */
    public long mo8967b() {
        return this.f1444d;
    }

    /* renamed from: c */
    public long mo8969c() {
        return this.f1445e;
    }
}
