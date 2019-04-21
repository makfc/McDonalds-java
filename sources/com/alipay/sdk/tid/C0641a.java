package com.alipay.sdk.tid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.lang.ref.WeakReference;

/* renamed from: com.alipay.sdk.tid.a */
final class C0641a extends SQLiteOpenHelper {
    /* renamed from: c */
    private WeakReference<Context> f621c;

    C0641a(Context context) {
        super(context, "msp.db", null, 1);
        this.f621c = new WeakReference(context);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        String str = "create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
        } else {
            sQLiteDatabase.execSQL(str);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = "drop table if exists tb_tid";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
        } else {
            sQLiteDatabase.execSQL(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* renamed from: a */
    public void mo8092a() {
        /*
        r4 = this;
        r2 = 0;
        r2 = r4.getWritableDatabase();	 Catch:{ Exception -> 0x0022 }
        r3 = "drop table if exists tb_tid";
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0022 }
        if (r1 != 0) goto L_0x001a;
    L_0x000b:
        r2.execSQL(r3);	 Catch:{ Exception -> 0x0022 }
    L_0x000e:
        if (r2 == 0) goto L_0x0019;
    L_0x0010:
        r1 = r2.isOpen();
        if (r1 == 0) goto L_0x0019;
    L_0x0016:
        r2.close();
    L_0x0019:
        return;
    L_0x001a:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0022 }
        r1 = r0;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r1, r3);	 Catch:{ Exception -> 0x0022 }
        goto L_0x000e;
    L_0x0022:
        r1 = move-exception;
        com.alipay.sdk.util.C0646c.m1016a(r1);	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x0019;
    L_0x0028:
        r1 = r2.isOpen();
        if (r1 == 0) goto L_0x0019;
    L_0x002e:
        r2.close();
        goto L_0x0019;
    L_0x0032:
        r1 = move-exception;
        if (r2 == 0) goto L_0x003e;
    L_0x0035:
        r3 = r2.isOpen();
        if (r3 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();
    L_0x003e:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C0641a.mo8092a():void");
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006e  */
    /* renamed from: a */
    public java.lang.String mo8091a(java.lang.String r8, java.lang.String r9) {
        /*
        r7 = this;
        r3 = 0;
        r4 = "select tid from tb_tid where name=?";
        r2 = r7.getReadableDatabase();	 Catch:{ Exception -> 0x0055, all -> 0x006a }
        r1 = 1;
        r5 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0082, all -> 0x007d }
        r1 = 0;
        r6 = r7.m980c(r8, r9);	 Catch:{ Exception -> 0x0082, all -> 0x007d }
        r5[r1] = r6;	 Catch:{ Exception -> 0x0082, all -> 0x007d }
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x0082, all -> 0x007d }
        if (r1 != 0) goto L_0x004c;
    L_0x0015:
        r4 = r2.rawQuery(r4, r5);	 Catch:{ Exception -> 0x0082, all -> 0x007d }
    L_0x0019:
        r1 = r4.moveToFirst();	 Catch:{ Exception -> 0x0085, all -> 0x007f }
        if (r1 == 0) goto L_0x008c;
    L_0x001f:
        r1 = 0;
        r1 = r4.getString(r1);	 Catch:{ Exception -> 0x0085, all -> 0x007f }
    L_0x0024:
        if (r4 == 0) goto L_0x0029;
    L_0x0026:
        r4.close();
    L_0x0029:
        if (r2 == 0) goto L_0x008a;
    L_0x002b:
        r3 = r2.isOpen();
        if (r3 == 0) goto L_0x008a;
    L_0x0031:
        r2.close();
        r2 = r1;
    L_0x0035:
        r1 = android.text.TextUtils.isEmpty(r2);
        if (r1 != 0) goto L_0x004b;
    L_0x003b:
        r1 = r7.f621c;
        r1 = r1.get();
        r1 = (android.content.Context) r1;
        r1 = com.alipay.sdk.util.C0644a.m1006c(r1);
        r2 = com.alipay.sdk.encrypt.C0620b.m901b(r2, r1);
    L_0x004b:
        return r2;
    L_0x004c:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x0082, all -> 0x007d }
        r1 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r4, r5);	 Catch:{ Exception -> 0x0082, all -> 0x007d }
        goto L_0x0019;
    L_0x0055:
        r1 = move-exception;
        r1 = r3;
        r2 = r3;
    L_0x0058:
        if (r1 == 0) goto L_0x005d;
    L_0x005a:
        r1.close();
    L_0x005d:
        if (r2 == 0) goto L_0x0088;
    L_0x005f:
        r1 = r2.isOpen();
        if (r1 == 0) goto L_0x0088;
    L_0x0065:
        r2.close();
        r2 = r3;
        goto L_0x0035;
    L_0x006a:
        r1 = move-exception;
        r2 = r3;
    L_0x006c:
        if (r3 == 0) goto L_0x0071;
    L_0x006e:
        r3.close();
    L_0x0071:
        if (r2 == 0) goto L_0x007c;
    L_0x0073:
        r3 = r2.isOpen();
        if (r3 == 0) goto L_0x007c;
    L_0x0079:
        r2.close();
    L_0x007c:
        throw r1;
    L_0x007d:
        r1 = move-exception;
        goto L_0x006c;
    L_0x007f:
        r1 = move-exception;
        r3 = r4;
        goto L_0x006c;
    L_0x0082:
        r1 = move-exception;
        r1 = r3;
        goto L_0x0058;
    L_0x0085:
        r1 = move-exception;
        r1 = r4;
        goto L_0x0058;
    L_0x0088:
        r2 = r3;
        goto L_0x0035;
    L_0x008a:
        r2 = r1;
        goto L_0x0035;
    L_0x008c:
        r1 = r3;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C0641a.mo8091a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0057  */
    /* renamed from: b */
    public java.lang.String mo8093b(java.lang.String r8, java.lang.String r9) {
        /*
        r7 = this;
        r3 = 0;
        r4 = "select key_tid from tb_tid where name=?";
        r2 = r7.getReadableDatabase();	 Catch:{ Exception -> 0x003e, all -> 0x0053 }
        r1 = 1;
        r5 = new java.lang.String[r1];	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r1 = 0;
        r6 = r7.m980c(r8, r9);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r5[r1] = r6;	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r1 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        if (r1 != 0) goto L_0x0035;
    L_0x0015:
        r4 = r2.rawQuery(r4, r5);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
    L_0x0019:
        r1 = r4.moveToFirst();	 Catch:{ Exception -> 0x006e, all -> 0x0068 }
        if (r1 == 0) goto L_0x0073;
    L_0x001f:
        r1 = 0;
        r1 = r4.getString(r1);	 Catch:{ Exception -> 0x006e, all -> 0x0068 }
    L_0x0024:
        if (r4 == 0) goto L_0x0029;
    L_0x0026:
        r4.close();
    L_0x0029:
        if (r2 == 0) goto L_0x0034;
    L_0x002b:
        r3 = r2.isOpen();
        if (r3 == 0) goto L_0x0034;
    L_0x0031:
        r2.close();
    L_0x0034:
        return r1;
    L_0x0035:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r1 = r0;
        r4 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r1, r4, r5);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        goto L_0x0019;
    L_0x003e:
        r1 = move-exception;
        r1 = r3;
        r2 = r3;
    L_0x0041:
        if (r1 == 0) goto L_0x0046;
    L_0x0043:
        r1.close();
    L_0x0046:
        if (r2 == 0) goto L_0x0071;
    L_0x0048:
        r1 = r2.isOpen();
        if (r1 == 0) goto L_0x0071;
    L_0x004e:
        r2.close();
        r1 = r3;
        goto L_0x0034;
    L_0x0053:
        r1 = move-exception;
        r2 = r3;
    L_0x0055:
        if (r3 == 0) goto L_0x005a;
    L_0x0057:
        r3.close();
    L_0x005a:
        if (r2 == 0) goto L_0x0065;
    L_0x005c:
        r3 = r2.isOpen();
        if (r3 == 0) goto L_0x0065;
    L_0x0062:
        r2.close();
    L_0x0065:
        throw r1;
    L_0x0066:
        r1 = move-exception;
        goto L_0x0055;
    L_0x0068:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0055;
    L_0x006b:
        r1 = move-exception;
        r1 = r3;
        goto L_0x0041;
    L_0x006e:
        r1 = move-exception;
        r1 = r4;
        goto L_0x0041;
    L_0x0071:
        r1 = r3;
        goto L_0x0034;
    L_0x0073:
        r1 = r3;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.C0641a.mo8093b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    private String m980c(String str, String str2) {
        return str + str2;
    }
}
