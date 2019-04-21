package com.amap.api.mapcore.util;

@EntityClass(a = "update_item_file")
/* renamed from: com.amap.api.mapcore.util.bu */
class DTFileInfo {
    @EntityField(a = "mAdcode", b = 6)
    /* renamed from: a */
    private String f1446a = "";
    @EntityField(a = "file", b = 6)
    /* renamed from: b */
    private String f1447b = "";

    public DTFileInfo(String str, String str2) {
        this.f1446a = str;
        this.f1447b = str2;
    }

    /* renamed from: a */
    public String mo8970a() {
        return this.f1447b;
    }

    /* renamed from: a */
    public static String m1963a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static String m1964b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }
}
