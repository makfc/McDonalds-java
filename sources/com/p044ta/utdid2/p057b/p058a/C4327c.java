package com.p044ta.utdid2.p057b.p058a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.p044ta.utdid2.p055a.p056a.C4321f;
import com.p044ta.utdid2.p057b.p058a.C4326b.C4324a;
import java.io.File;
import java.util.Map.Entry;

/* renamed from: com.ta.utdid2.b.a.c */
public class C4327c {
    /* renamed from: a */
    private Editor f6726a = null;
    /* renamed from: a */
    private SharedPreferences f6727a = null;
    /* renamed from: a */
    private C4324a f6728a = null;
    /* renamed from: a */
    private C4326b f6729a = null;
    /* renamed from: a */
    private C4330d f6730a = null;
    /* renamed from: a */
    private String f6731a = "";
    /* renamed from: b */
    private String f6732b = "";
    /* renamed from: c */
    private boolean f6733c = false;
    /* renamed from: d */
    private boolean f6734d = false;
    /* renamed from: e */
    private boolean f6735e = false;
    /* renamed from: f */
    private boolean f6736f = false;
    private Context mContext = null;

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0 A:{Catch:{ Exception -> 0x01f3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0 A:{Catch:{ Exception -> 0x01f3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0 A:{Catch:{ Exception -> 0x01f3 }} */
    public C4327c(android.content.Context r11, java.lang.String r12, java.lang.String r13, boolean r14, boolean r15) {
        /*
        r10 = this;
        r10.<init>();
        r0 = "";
        r10.f6731a = r0;
        r0 = "";
        r10.f6732b = r0;
        r0 = 0;
        r10.f6733c = r0;
        r0 = 0;
        r10.f6734d = r0;
        r0 = 0;
        r10.f6735e = r0;
        r0 = 0;
        r10.f6727a = r0;
        r0 = 0;
        r10.f6729a = r0;
        r0 = 0;
        r10.f6726a = r0;
        r0 = 0;
        r10.f6728a = r0;
        r0 = 0;
        r10.mContext = r0;
        r0 = 0;
        r10.f6730a = r0;
        r0 = 0;
        r10.f6736f = r0;
        r10.f6733c = r14;
        r10.f6736f = r15;
        r10.f6731a = r13;
        r10.f6732b = r12;
        r10.mContext = r11;
        r0 = 0;
        r4 = 0;
        if (r11 == 0) goto L_0x004a;
    L_0x0039:
        r0 = 0;
        r0 = r11.getSharedPreferences(r13, r0);
        r10.f6727a = r0;
        r0 = r10.f6727a;
        r1 = "t";
        r2 = 0;
        r0 = r0.getLong(r1, r2);
    L_0x004a:
        r2 = 0;
        r2 = android.os.Environment.getExternalStorageState();	 Catch:{ Exception -> 0x00ef }
    L_0x004f:
        r3 = com.p044ta.utdid2.p055a.p056a.C4321f.isEmpty(r2);
        if (r3 == 0) goto L_0x00f5;
    L_0x0055:
        r2 = 0;
        r10.f6735e = r2;
        r10.f6734d = r2;
    L_0x005a:
        r2 = r10.f6734d;
        if (r2 != 0) goto L_0x0062;
    L_0x005e:
        r2 = r10.f6735e;
        if (r2 == 0) goto L_0x00a2;
    L_0x0062:
        if (r11 == 0) goto L_0x00a2;
    L_0x0064:
        r2 = com.p044ta.utdid2.p055a.p056a.C4321f.isEmpty(r12);
        if (r2 != 0) goto L_0x00a2;
    L_0x006a:
        r2 = r10.m7777a(r12);
        r10.f6730a = r2;
        r2 = r10.f6730a;
        if (r2 == 0) goto L_0x00a2;
    L_0x0074:
        r2 = r10.f6730a;	 Catch:{ Exception -> 0x01ea }
        r3 = 0;
        r2 = r2.mo33736a(r13, r3);	 Catch:{ Exception -> 0x01ea }
        r10.f6729a = r2;	 Catch:{ Exception -> 0x01ea }
        r2 = r10.f6729a;	 Catch:{ Exception -> 0x01ea }
        r3 = "t";
        r6 = 0;
        r2 = r2.getLong(r3, r6);	 Catch:{ Exception -> 0x01ea }
        if (r15 != 0) goto L_0x014b;
    L_0x0089:
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x011b;
    L_0x008d:
        r4 = r10.f6727a;	 Catch:{ Exception -> 0x01f6 }
        r5 = r10.f6729a;	 Catch:{ Exception -> 0x01f6 }
        r10.m7779a(r4, r5);	 Catch:{ Exception -> 0x01f6 }
        r4 = r10.f6730a;	 Catch:{ Exception -> 0x01f6 }
        r5 = 0;
        r4 = r4.mo33736a(r13, r5);	 Catch:{ Exception -> 0x01f6 }
        r10.f6729a = r4;	 Catch:{ Exception -> 0x01f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
    L_0x00a0:
        r4 = r0;
        r0 = r2;
    L_0x00a2:
        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x00b2;
    L_0x00a6:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x00ee;
    L_0x00ac:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x00ee;
    L_0x00b2:
        r2 = java.lang.System.currentTimeMillis();
        r6 = r10.f6736f;
        if (r6 == 0) goto L_0x00ca;
    L_0x00ba:
        r6 = r10.f6736f;
        if (r6 == 0) goto L_0x00ee;
    L_0x00be:
        r6 = 0;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 != 0) goto L_0x00ee;
    L_0x00c4:
        r0 = 0;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 != 0) goto L_0x00ee;
    L_0x00ca:
        r0 = r10.f6727a;
        if (r0 == 0) goto L_0x00dc;
    L_0x00ce:
        r0 = r10.f6727a;
        r0 = r0.edit();
        r1 = "t2";
        r0.putLong(r1, r2);
        r0.commit();
    L_0x00dc:
        r0 = r10.f6729a;	 Catch:{ Exception -> 0x01f3 }
        if (r0 == 0) goto L_0x00ee;
    L_0x00e0:
        r0 = r10.f6729a;	 Catch:{ Exception -> 0x01f3 }
        r0 = r0.mo33725a();	 Catch:{ Exception -> 0x01f3 }
        r1 = "t2";
        r0.mo33719a(r1, r2);	 Catch:{ Exception -> 0x01f3 }
        r0.commit();	 Catch:{ Exception -> 0x01f3 }
    L_0x00ee:
        return;
    L_0x00ef:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x004f;
    L_0x00f5:
        r3 = "mounted";
        r3 = r2.equals(r3);
        if (r3 == 0) goto L_0x0104;
    L_0x00fd:
        r2 = 1;
        r10.f6735e = r2;
        r10.f6734d = r2;
        goto L_0x005a;
    L_0x0104:
        r3 = "mounted_ro";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0114;
    L_0x010c:
        r2 = 1;
        r10.f6734d = r2;
        r2 = 0;
        r10.f6735e = r2;
        goto L_0x005a;
    L_0x0114:
        r2 = 0;
        r10.f6735e = r2;
        r10.f6734d = r2;
        goto L_0x005a;
    L_0x011b:
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x0132;
    L_0x011f:
        r4 = r10.f6729a;	 Catch:{ Exception -> 0x01f6 }
        r5 = r10.f6727a;	 Catch:{ Exception -> 0x01f6 }
        r10.m7780a(r4, r5);	 Catch:{ Exception -> 0x01f6 }
        r4 = 0;
        r4 = r11.getSharedPreferences(r13, r4);	 Catch:{ Exception -> 0x01f6 }
        r10.f6727a = r4;	 Catch:{ Exception -> 0x01f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00a0;
    L_0x0132:
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x01ff;
    L_0x0136:
        r4 = r10.f6727a;	 Catch:{ Exception -> 0x01f6 }
        r5 = r10.f6729a;	 Catch:{ Exception -> 0x01f6 }
        r10.m7779a(r4, r5);	 Catch:{ Exception -> 0x01f6 }
        r4 = r10.f6730a;	 Catch:{ Exception -> 0x01f6 }
        r5 = 0;
        r4 = r4.mo33736a(r13, r5);	 Catch:{ Exception -> 0x01f6 }
        r10.f6729a = r4;	 Catch:{ Exception -> 0x01f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00a0;
    L_0x014b:
        r4 = r10.f6727a;	 Catch:{ Exception -> 0x01f6 }
        r5 = "t2";
        r6 = 0;
        r4 = r4.getLong(r5, r6);	 Catch:{ Exception -> 0x01f6 }
        r0 = r10.f6729a;	 Catch:{ Exception -> 0x01fa }
        r1 = "t2";
        r6 = 0;
        r0 = r0.getLong(r1, r6);	 Catch:{ Exception -> 0x01fa }
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 >= 0) goto L_0x017c;
    L_0x0163:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x017c;
    L_0x0169:
        r2 = r10.f6727a;	 Catch:{ Exception -> 0x01fd }
        r3 = r10.f6729a;	 Catch:{ Exception -> 0x01fd }
        r10.m7779a(r2, r3);	 Catch:{ Exception -> 0x01fd }
        r2 = r10.f6730a;	 Catch:{ Exception -> 0x01fd }
        r3 = 0;
        r2 = r2.mo33736a(r13, r3);	 Catch:{ Exception -> 0x01fd }
        r10.f6729a = r2;	 Catch:{ Exception -> 0x01fd }
        r2 = r4;
        goto L_0x00a0;
    L_0x017c:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0197;
    L_0x0180:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0197;
    L_0x0186:
        r2 = r10.f6729a;	 Catch:{ Exception -> 0x01fd }
        r3 = r10.f6727a;	 Catch:{ Exception -> 0x01fd }
        r10.m7780a(r2, r3);	 Catch:{ Exception -> 0x01fd }
        r2 = 0;
        r2 = r11.getSharedPreferences(r13, r2);	 Catch:{ Exception -> 0x01fd }
        r10.f6727a = r2;	 Catch:{ Exception -> 0x01fd }
        r2 = r4;
        goto L_0x00a0;
    L_0x0197:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x01b4;
    L_0x019d:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x01b4;
    L_0x01a3:
        r2 = r10.f6729a;	 Catch:{ Exception -> 0x01fd }
        r3 = r10.f6727a;	 Catch:{ Exception -> 0x01fd }
        r10.m7780a(r2, r3);	 Catch:{ Exception -> 0x01fd }
        r2 = 0;
        r2 = r11.getSharedPreferences(r13, r2);	 Catch:{ Exception -> 0x01fd }
        r10.f6727a = r2;	 Catch:{ Exception -> 0x01fd }
        r2 = r4;
        goto L_0x00a0;
    L_0x01b4:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x01d3;
    L_0x01ba:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x01d3;
    L_0x01c0:
        r2 = r10.f6727a;	 Catch:{ Exception -> 0x01fd }
        r3 = r10.f6729a;	 Catch:{ Exception -> 0x01fd }
        r10.m7779a(r2, r3);	 Catch:{ Exception -> 0x01fd }
        r2 = r10.f6730a;	 Catch:{ Exception -> 0x01fd }
        r3 = 0;
        r2 = r2.mo33736a(r13, r3);	 Catch:{ Exception -> 0x01fd }
        r10.f6729a = r2;	 Catch:{ Exception -> 0x01fd }
        r2 = r4;
        goto L_0x00a0;
    L_0x01d3:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x01e7;
    L_0x01d7:
        r2 = r10.f6727a;	 Catch:{ Exception -> 0x01fd }
        r3 = r10.f6729a;	 Catch:{ Exception -> 0x01fd }
        r10.m7779a(r2, r3);	 Catch:{ Exception -> 0x01fd }
        r2 = r10.f6730a;	 Catch:{ Exception -> 0x01fd }
        r3 = 0;
        r2 = r2.mo33736a(r13, r3);	 Catch:{ Exception -> 0x01fd }
        r10.f6729a = r2;	 Catch:{ Exception -> 0x01fd }
    L_0x01e7:
        r2 = r4;
        goto L_0x00a0;
    L_0x01ea:
        r2 = move-exception;
        r8 = r4;
        r4 = r0;
        r0 = r8;
    L_0x01ee:
        r8 = r0;
        r0 = r4;
        r4 = r8;
        goto L_0x00a2;
    L_0x01f3:
        r0 = move-exception;
        goto L_0x00ee;
    L_0x01f6:
        r4 = move-exception;
        r4 = r0;
        r0 = r2;
        goto L_0x01ee;
    L_0x01fa:
        r0 = move-exception;
        r0 = r2;
        goto L_0x01ee;
    L_0x01fd:
        r2 = move-exception;
        goto L_0x01ee;
    L_0x01ff:
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p044ta.utdid2.p057b.p058a.C4327c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    /* renamed from: a */
    private C4330d m7777a(String str) {
        File a = m7777a(str);
        if (a == null) {
            return null;
        }
        this.f6730a = new C4330d(a.getAbsolutePath());
        return this.f6730a;
    }

    /* renamed from: a */
    private File m8663a(String str) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
        if (file == null || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    /* renamed from: a */
    private void m7779a(SharedPreferences sharedPreferences, C4326b c4326b) {
        if (sharedPreferences != null && c4326b != null) {
            C4324a a = c4326b.mo33725a();
            if (a != null) {
                a.mo33722b();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        a.mo33720a(str, (String) value);
                    } else if (value instanceof Integer) {
                        a.mo33718a(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        a.mo33719a(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        a.mo33717a(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        a.mo33721a(str, ((Boolean) value).booleanValue());
                    }
                }
                a.commit();
            }
        }
    }

    /* renamed from: a */
    private void m7780a(C4326b c4326b, SharedPreferences sharedPreferences) {
        if (c4326b != null && sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : c4326b.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    /* renamed from: b */
    private boolean m8664b() {
        if (this.f6729a == null) {
            return false;
        }
        boolean a = this.f6729a.mo33725a();
        if (a) {
            return a;
        }
        commit();
        return a;
    }

    /* renamed from: b */
    private void m7781b() {
        if (this.f6726a == null && this.f6727a != null) {
            this.f6726a = this.f6727a.edit();
        }
        if (this.f6735e && this.f6728a == null && this.f6729a != null) {
            this.f6728a = this.f6729a.mo33725a();
        }
        m7781b();
    }

    public void putString(String str, String str2) {
        if (!C4321f.isEmpty(str) && !str.equals("t")) {
            m7781b();
            if (this.f6726a != null) {
                this.f6726a.putString(str, str2);
            }
            if (this.f6728a != null) {
                this.f6728a.mo33720a(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!C4321f.isEmpty(str) && !str.equals("t")) {
            m7781b();
            if (this.f6726a != null) {
                this.f6726a.remove(str);
            }
            if (this.f6728a != null) {
                this.f6728a.mo33716a(str);
            }
        }
    }

    public boolean commit() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f6726a != null) {
            if (!(this.f6736f || this.f6727a == null)) {
                this.f6726a.putLong("t", currentTimeMillis);
            }
            if (!this.f6726a.commit()) {
                z = false;
            }
        }
        if (!(this.f6727a == null || this.mContext == null)) {
            this.f6727a = this.mContext.getSharedPreferences(this.f6731a, 0);
        }
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!C4321f.isEmpty(str)) {
            if (str.equals("mounted")) {
                if (this.f6729a == null) {
                    C4330d a = m7777a(this.f6732b);
                    if (a != null) {
                        this.f6729a = a.mo33736a(this.f6731a, 0);
                        if (this.f6736f) {
                            m7780a(this.f6729a, this.f6727a);
                        } else {
                            m7779a(this.f6727a, this.f6729a);
                        }
                        this.f6728a = this.f6729a.mo33725a();
                    }
                } else if (!(this.f6728a == null || this.f6728a.commit())) {
                    z = false;
                }
            }
            if (str.equals("mounted") || (str.equals("mounted_ro") && this.f6729a != null)) {
                try {
                    if (this.f6730a != null) {
                        this.f6729a = this.f6730a.mo33736a(this.f6731a, 0);
                    }
                } catch (Exception e2) {
                }
            }
        }
        return z;
    }

    public String getString(String str) {
        m7781b();
        if (this.f6727a != null) {
            String string = this.f6727a.getString(str, "");
            if (!C4321f.isEmpty(string)) {
                return string;
            }
        }
        if (this.f6729a != null) {
            return this.f6729a.getString(str, "");
        }
        return "";
    }
}
