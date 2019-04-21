package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class zzcf implements zzav {
    private static final String zzWN = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    private final Context mContext;
    private final zzb zzbpP;
    private volatile zzac zzbpQ;
    private final zzaw zzbpR;
    private final String zzbpS;
    private long zzbpT;
    private final int zzbpU;
    private zze zzsd;

    class zza implements com.google.android.gms.tagmanager.zzde.zza {
        zza() {
        }

        public void zza(zzar zzar) {
            zzcf.this.zzs(zzar.zzJQ());
        }

        public void zzb(zzar zzar) {
            zzcf.this.zzs(zzar.zzJQ());
            zzbn.m7502v("Permanent failure dispatching hitId: " + zzar.zzJQ());
        }

        public void zzc(zzar zzar) {
            long zzJR = zzar.zzJR();
            if (zzJR == 0) {
                zzcf.this.zzd(zzar.zzJQ(), zzcf.this.zzsd.currentTimeMillis());
            } else if (zzJR + 14400000 < zzcf.this.zzsd.currentTimeMillis()) {
                zzcf.this.zzs(zzar.zzJQ());
                zzbn.m7502v("Giving up on failed hitId: " + zzar.zzJQ());
            }
        }
    }

    class zzb extends SQLiteOpenHelper {
        private boolean zzbpW;
        private long zzbpX = 0;

        zzb(Context context, String str) {
            super(context, str, null, 1);
        }

        /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
        private boolean zza(java.lang.String r12, android.database.sqlite.SQLiteDatabase r13) {
            /*
            r11 = this;
            r9 = 0;
            r10 = 0;
            r2 = "SQLITE_MASTER";
            r1 = 1;
            r3 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            r1 = 0;
            r4 = "name";
            r3[r1] = r4;	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            r4 = "name=?";
            r1 = 1;
            r5 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            r1 = 0;
            r5[r1] = r12;	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            r6 = 0;
            r7 = 0;
            r8 = 0;
            r1 = r13 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            if (r1 != 0) goto L_0x002a;
        L_0x001b:
            r1 = r13;
            r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
        L_0x0020:
            r1 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0063, all -> 0x0060 }
            if (r2 == 0) goto L_0x0029;
        L_0x0026:
            r2.close();
        L_0x0029:
            return r1;
        L_0x002a:
            r0 = r13;
            r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            r1 = r0;
            r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0033, all -> 0x005e }
            goto L_0x0020;
        L_0x0033:
            r1 = move-exception;
            r1 = r10;
        L_0x0035:
            r3 = "Error querying for table ";
            r2 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0055 }
            r4 = r2.length();	 Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x004f;
        L_0x0041:
            r2 = r3.concat(r2);	 Catch:{ all -> 0x0055 }
        L_0x0045:
            com.google.android.gms.tagmanager.zzbn.zzaW(r2);	 Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x004d;
        L_0x004a:
            r1.close();
        L_0x004d:
            r1 = r9;
            goto L_0x0029;
        L_0x004f:
            r2 = new java.lang.String;	 Catch:{ all -> 0x0055 }
            r2.<init>(r3);	 Catch:{ all -> 0x0055 }
            goto L_0x0045;
        L_0x0055:
            r2 = move-exception;
            r10 = r1;
            r1 = r2;
        L_0x0058:
            if (r10 == 0) goto L_0x005d;
        L_0x005a:
            r10.close();
        L_0x005d:
            throw r1;
        L_0x005e:
            r1 = move-exception;
            goto L_0x0058;
        L_0x0060:
            r1 = move-exception;
            r10 = r2;
            goto L_0x0058;
        L_0x0063:
            r1 = move-exception;
            r1 = r2;
            goto L_0x0035;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcf$zzb.zza(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        private void zzc(SQLiteDatabase sQLiteDatabase) {
            String str = "SELECT * FROM gtm_hits WHERE 0";
            Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } finally {
                rawQuery.close();
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!this.zzbpW || this.zzbpX + 3600000 <= zzcf.this.zzsd.currentTimeMillis()) {
                SQLiteDatabase sQLiteDatabase = null;
                this.zzbpW = true;
                this.zzbpX = zzcf.this.zzsd.currentTimeMillis();
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    zzcf.this.mContext.getDatabasePath(zzcf.this.zzbpS).delete();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase = super.getWritableDatabase();
                }
                this.zzbpW = false;
                return sQLiteDatabase;
            }
            throw new SQLiteException("Database creation failed");
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzam.zzbR(sQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (VERSION.SDK_INT < 15) {
                String str = "PRAGMA journal_mode=memory";
                Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (zza("gtm_hits", sQLiteDatabase)) {
                zzc(sQLiteDatabase);
                return;
            }
            String zzKg = zzcf.zzWN;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, zzKg);
            } else {
                sQLiteDatabase.execSQL(zzKg);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzcf(zzaw zzaw, Context context) {
        this(zzaw, context, "gtm_urls.db", ActivityTrace.MAX_TRACES);
    }

    zzcf(zzaw zzaw, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.zzbpS = str;
        this.zzbpR = zzaw;
        this.zzsd = zzh.zzuW();
        this.zzbpP = new zzb(this.mContext, this.zzbpS);
        this.zzbpQ = new zzde(this.mContext, new zza());
        this.zzbpT = 0;
        this.zzbpU = i;
    }

    private void zzKd() {
        int zzKe = (zzKe() - this.zzbpU) + 1;
        if (zzKe > 0) {
            List zzkU = zzkU(zzKe);
            zzbn.m7502v("Store full, deleting " + zzkU.size() + " hits to make room.");
            zzh((String[]) zzkU.toArray(new String[0]));
        }
    }

    private void zzd(long j, long j2) {
        SQLiteDatabase zzgv = zzgv("Error opening database for getNumStoredHits.");
        if (zzgv != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                String str = "gtm_hits";
                String str2 = "hit_id=?";
                String[] strArr = new String[]{String.valueOf(j)};
                if (zzgv instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.update(zzgv, str, contentValues, str2, strArr);
                } else {
                    zzgv.update(str, contentValues, str2, strArr);
                }
            } catch (SQLiteException e) {
                zzbn.zzaW("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
                zzs(j);
            }
        }
    }

    private SQLiteDatabase zzgv(String str) {
        try {
            return this.zzbpP.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbn.zzaW(str);
            return null;
        }
    }

    private void zzh(long j, String str) {
        SQLiteDatabase zzgv = zzgv("Error opening database for putHit");
        if (zzgv != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", Integer.valueOf(0));
            try {
                String str2 = "gtm_hits";
                if (zzgv instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.insert(zzgv, str2, null, contentValues);
                } else {
                    zzgv.insert(str2, null, contentValues);
                }
                this.zzbpR.zzaE(false);
            } catch (SQLiteException e) {
                zzbn.zzaW("Error storing hit");
            }
        }
    }

    private void zzs(long j) {
        zzh(new String[]{String.valueOf(j)});
    }

    public void dispatch() {
        zzbn.m7502v("GTM Dispatch running...");
        if (this.zzbpQ.zzJF()) {
            List zzkV = zzkV(40);
            if (zzkV.isEmpty()) {
                zzbn.m7502v("...nothing to dispatch");
                this.zzbpR.zzaE(true);
                return;
            }
            this.zzbpQ.zzI(zzkV);
            if (zzKf() > 0) {
                zzdb.zzKB().dispatch();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003e A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:2:0x000b, PHI: r2 } */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:17:0x0031, code skipped:
            r0 = r2;
     */
    /* JADX WARNING: Missing block: B:22:0x003e, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:24:0x0041, code skipped:
            r2.close();
     */
    public int zzKe() {
        /*
        r6 = this;
        r2 = 0;
        r1 = 0;
        r0 = "Error opening database for getNumStoredHits.";
        r0 = r6.zzgv(r0);
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return r1;
    L_0x000b:
        r3 = "SELECT COUNT(*) from gtm_hits";
        r4 = 0;
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0030, all -> 0x003e }
        if (r5 != 0) goto L_0x0029;
    L_0x0012:
        r2 = r0.rawQuery(r3, r4);	 Catch:{ SQLiteException -> 0x0030, all -> 0x003e }
    L_0x0016:
        r0 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0049, all -> 0x003e }
        if (r0 == 0) goto L_0x004e;
    L_0x001c:
        r0 = 0;
        r0 = r2.getLong(r0);	 Catch:{ SQLiteException -> 0x0049, all -> 0x003e }
        r0 = (int) r0;
    L_0x0022:
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r2.close();
    L_0x0027:
        r1 = r0;
        goto L_0x000a;
    L_0x0029:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0030, all -> 0x003e }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r0, r3, r4);	 Catch:{ SQLiteException -> 0x0030, all -> 0x003e }
        goto L_0x0016;
    L_0x0030:
        r0 = move-exception;
        r0 = r2;
    L_0x0032:
        r2 = "Error getting numStoredHits";
        com.google.android.gms.tagmanager.zzbn.zzaW(r2);	 Catch:{ all -> 0x0045 }
        if (r0 == 0) goto L_0x004c;
    L_0x0039:
        r0.close();
        r0 = r1;
        goto L_0x0027;
    L_0x003e:
        r0 = move-exception;
    L_0x003f:
        if (r2 == 0) goto L_0x0044;
    L_0x0041:
        r2.close();
    L_0x0044:
        throw r0;
    L_0x0045:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x003f;
    L_0x0049:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0032;
    L_0x004c:
        r0 = r1;
        goto L_0x0027;
    L_0x004e:
        r0 = r1;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcf.zzKe():int");
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    public int zzKf() {
        /*
        r11 = this;
        r8 = 0;
        r9 = 0;
        r0 = "Error opening database for getNumStoredHits.";
        r0 = r11.zzgv(r0);
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return r8;
    L_0x000b:
        r1 = "gtm_hits";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
        r3 = 0;
        r4 = "hit_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
        r3 = 1;
        r4 = "hit_first_send_time";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
        r3 = "hit_first_send_time=0";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
        if (r10 != 0) goto L_0x0033;
    L_0x0024:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
    L_0x0028:
        r0 = r1.getCount();	 Catch:{ SQLiteException -> 0x0056, all -> 0x004f }
        if (r1 == 0) goto L_0x0031;
    L_0x002e:
        r1.close();
    L_0x0031:
        r8 = r0;
        goto L_0x000a;
    L_0x0033:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x003a, all -> 0x0048 }
        goto L_0x0028;
    L_0x003a:
        r0 = move-exception;
        r0 = r9;
    L_0x003c:
        r1 = "Error getting num untried hits";
        com.google.android.gms.tagmanager.zzbn.zzaW(r1);	 Catch:{ all -> 0x0052 }
        if (r0 == 0) goto L_0x0059;
    L_0x0043:
        r0.close();
        r0 = r8;
        goto L_0x0031;
    L_0x0048:
        r0 = move-exception;
    L_0x0049:
        if (r9 == 0) goto L_0x004e;
    L_0x004b:
        r9.close();
    L_0x004e:
        throw r0;
    L_0x004f:
        r0 = move-exception;
        r9 = r1;
        goto L_0x0049;
    L_0x0052:
        r1 = move-exception;
        r9 = r0;
        r0 = r1;
        goto L_0x0049;
    L_0x0056:
        r0 = move-exception;
        r0 = r1;
        goto L_0x003c;
    L_0x0059:
        r0 = r8;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcf.zzKf():int");
    }

    public void zzg(long j, String str) {
        zzms();
        zzKd();
        zzh(j, str);
    }

    /* Access modifiers changed, original: 0000 */
    public void zzh(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzgv = zzgv("Error opening database for deleteHits.");
            if (zzgv != null) {
                String format = String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))});
                try {
                    String str = "gtm_hits";
                    if (zzgv instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(zzgv, str, format, strArr);
                    } else {
                        zzgv.delete(str, format, strArr);
                    }
                    this.zzbpR.zzaE(zzKe() == 0);
                } catch (SQLiteException e) {
                    zzbn.zzaW("Error deleting hits");
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> zzkU(int i) {
        SQLiteException e;
        Throwable th;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            zzbn.zzaW("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase zzgv = zzgv("Error opening database for peekHitIds.");
        if (zzgv == null) {
            return arrayList;
        }
        Cursor query;
        try {
            String str = "gtm_hits";
            String[] strArr = new String[]{"hit_id"};
            String format = String.format("%s ASC", new Object[]{"hit_id"});
            String num = Integer.toString(i);
            query = !(zzgv instanceof SQLiteDatabase) ? zzgv.query(str, strArr, null, null, null, null, format, num) : SQLiteInstrumentation.query(zzgv, str, strArr, null, null, null, null, format, num);
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(query.getLong(0)));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    String str2 = "Error in peekHits fetching hitIds: ";
                    String valueOf = String.valueOf(e.getMessage());
                    zzbn.zzaW(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010e A:{SYNTHETIC, Splitter:B:45:0x010e} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0100 A:{Catch:{ all -> 0x0114 }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01a0 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0046} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:41:0x0100, code skipped:
            r3 = r5.concat(r3);
     */
    /* JADX WARNING: Missing block: B:44:0x0109, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:46:?, code skipped:
            r3 = new java.lang.String(r5);
     */
    /* JADX WARNING: Missing block: B:50:0x0119, code skipped:
            r21.close();
     */
    /* JADX WARNING: Missing block: B:85:0x01a0, code skipped:
            r2 = th;
     */
    /* JADX WARNING: Missing block: B:86:0x01a1, code skipped:
            r21 = r12;
     */
    /* JADX WARNING: Missing block: B:87:0x01a5, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:88:0x01a6, code skipped:
            r3 = r2;
            r4 = r12;
            r2 = r20;
     */
    /* JADX WARNING: Missing block: B:98:?, code skipped:
            return r2;
     */
    /* JADX WARNING: Missing block: B:99:?, code skipped:
            return r2;
     */
    public java.util.List<com.google.android.gms.tagmanager.zzar> zzkV(int r23) {
        /*
        r22 = this;
        r20 = new java.util.ArrayList;
        r20.<init>();
        r2 = "Error opening database for peekHits";
        r0 = r22;
        r2 = r0.zzgv(r2);
        if (r2 != 0) goto L_0x0012;
    L_0x000f:
        r2 = r20;
    L_0x0011:
        return r2;
    L_0x0012:
        r21 = 0;
        r3 = "gtm_hits";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r5 = 1;
        r6 = "hit_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r5 = 2;
        r6 = "hit_first_send_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r11 = 0;
        r12 = "hit_id";
        r10[r11] = r12;	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r10 = java.lang.Integer.toString(r23);	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r11 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        if (r11 != 0) goto L_0x00d4;
    L_0x0042:
        r12 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
    L_0x0046:
        r11 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x01a5, all -> 0x01a0 }
        r11.<init>();	 Catch:{ SQLiteException -> 0x01a5, all -> 0x01a0 }
        r3 = r12.moveToFirst();	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        if (r3 == 0) goto L_0x006e;
    L_0x0051:
        r3 = new com.google.android.gms.tagmanager.zzar;	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        r4 = 0;
        r4 = r12.getLong(r4);	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        r6 = 1;
        r6 = r12.getLong(r6);	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        r8 = 2;
        r8 = r12.getLong(r8);	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        r3.<init>(r4, r6, r8);	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        r11.add(r3);	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        r3 = r12.moveToNext();	 Catch:{ SQLiteException -> 0x01ac, all -> 0x01a0 }
        if (r3 != 0) goto L_0x0051;
    L_0x006e:
        if (r12 == 0) goto L_0x0073;
    L_0x0070:
        r12.close();
    L_0x0073:
        r13 = 0;
        r3 = "gtm_hits";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x019b }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x019b }
        r5 = 1;
        r6 = "hit_url";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x019b }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x019b }
        r14 = 0;
        r15 = "hit_id";
        r10[r14] = r15;	 Catch:{ SQLiteException -> 0x019b }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x019b }
        r10 = java.lang.Integer.toString(r23);	 Catch:{ SQLiteException -> 0x019b }
        r14 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x019b }
        if (r14 != 0) goto L_0x011d;
    L_0x009d:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x019b }
    L_0x00a1:
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        if (r2 == 0) goto L_0x00cc;
    L_0x00a7:
        r4 = r13;
    L_0x00a8:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteCursor) r0;	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2 = r0;
        r2 = r2.getWindow();	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2 = r2.getNumRows();	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        if (r2 <= 0) goto L_0x0125;
    L_0x00b6:
        r2 = r11.get(r4);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2 = (com.google.android.gms.tagmanager.zzar) r2;	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2.zzgz(r5);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
    L_0x00c4:
        r2 = r4 + 1;
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        if (r4 != 0) goto L_0x01b2;
    L_0x00cc:
        if (r3 == 0) goto L_0x00d1;
    L_0x00ce:
        r3.close();
    L_0x00d1:
        r2 = r11;
        goto L_0x0011;
    L_0x00d4:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        r11 = r0;
        r12 = r3;
        r13 = r4;
        r14 = r5;
        r15 = r6;
        r16 = r7;
        r17 = r8;
        r18 = r9;
        r19 = r10;
        r12 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r11, r12, r13, r14, r15, r16, r17, r18, r19);	 Catch:{ SQLiteException -> 0x00ea, all -> 0x019d }
        goto L_0x0046;
    L_0x00ea:
        r2 = move-exception;
        r3 = r2;
        r4 = r21;
        r2 = r20;
    L_0x00f0:
        r5 = "Error in peekHits fetching hitIds: ";
        r3 = r3.getMessage();	 Catch:{ all -> 0x0114 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x0114 }
        r6 = r3.length();	 Catch:{ all -> 0x0114 }
        if (r6 == 0) goto L_0x010e;
    L_0x0100:
        r3 = r5.concat(r3);	 Catch:{ all -> 0x0114 }
    L_0x0104:
        com.google.android.gms.tagmanager.zzbn.zzaW(r3);	 Catch:{ all -> 0x0114 }
        if (r4 == 0) goto L_0x0011;
    L_0x0109:
        r4.close();
        goto L_0x0011;
    L_0x010e:
        r3 = new java.lang.String;	 Catch:{ all -> 0x0114 }
        r3.<init>(r5);	 Catch:{ all -> 0x0114 }
        goto L_0x0104;
    L_0x0114:
        r2 = move-exception;
        r21 = r4;
    L_0x0117:
        if (r21 == 0) goto L_0x011c;
    L_0x0119:
        r21.close();
    L_0x011c:
        throw r2;
    L_0x011d:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ SQLiteException -> 0x019b }
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x019b }
        goto L_0x00a1;
    L_0x0125:
        r5 = "HitString for hitId %d too large.  Hit will be deleted.";
        r2 = 1;
        r6 = new java.lang.Object[r2];	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r7 = 0;
        r2 = r11.get(r4);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2 = (com.google.android.gms.tagmanager.zzar) r2;	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r8 = r2.zzJQ();	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r6[r7] = r2;	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        r2 = java.lang.String.format(r5, r6);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        com.google.android.gms.tagmanager.zzbn.zzaW(r2);	 Catch:{ SQLiteException -> 0x0143, all -> 0x0198 }
        goto L_0x00c4;
    L_0x0143:
        r2 = move-exception;
        r12 = r3;
    L_0x0145:
        r3 = "Error in peekHits fetching hit url: ";
        r2 = r2.getMessage();	 Catch:{ all -> 0x018c }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x018c }
        r4 = r2.length();	 Catch:{ all -> 0x018c }
        if (r4 == 0) goto L_0x0186;
    L_0x0155:
        r2 = r3.concat(r2);	 Catch:{ all -> 0x018c }
    L_0x0159:
        com.google.android.gms.tagmanager.zzbn.zzaW(r2);	 Catch:{ all -> 0x018c }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x018c }
        r3.<init>();	 Catch:{ all -> 0x018c }
        r4 = 0;
        r5 = r11.iterator();	 Catch:{ all -> 0x018c }
    L_0x0166:
        r2 = r5.hasNext();	 Catch:{ all -> 0x018c }
        if (r2 == 0) goto L_0x017e;
    L_0x016c:
        r2 = r5.next();	 Catch:{ all -> 0x018c }
        r2 = (com.google.android.gms.tagmanager.zzar) r2;	 Catch:{ all -> 0x018c }
        r6 = r2.zzJS();	 Catch:{ all -> 0x018c }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x018c }
        if (r6 == 0) goto L_0x0194;
    L_0x017c:
        if (r4 == 0) goto L_0x0193;
    L_0x017e:
        if (r12 == 0) goto L_0x0183;
    L_0x0180:
        r12.close();
    L_0x0183:
        r2 = r3;
        goto L_0x0011;
    L_0x0186:
        r2 = new java.lang.String;	 Catch:{ all -> 0x018c }
        r2.<init>(r3);	 Catch:{ all -> 0x018c }
        goto L_0x0159;
    L_0x018c:
        r2 = move-exception;
    L_0x018d:
        if (r12 == 0) goto L_0x0192;
    L_0x018f:
        r12.close();
    L_0x0192:
        throw r2;
    L_0x0193:
        r4 = 1;
    L_0x0194:
        r3.add(r2);	 Catch:{ all -> 0x018c }
        goto L_0x0166;
    L_0x0198:
        r2 = move-exception;
        r12 = r3;
        goto L_0x018d;
    L_0x019b:
        r2 = move-exception;
        goto L_0x0145;
    L_0x019d:
        r2 = move-exception;
        goto L_0x0117;
    L_0x01a0:
        r2 = move-exception;
        r21 = r12;
        goto L_0x0117;
    L_0x01a5:
        r2 = move-exception;
        r3 = r2;
        r4 = r12;
        r2 = r20;
        goto L_0x00f0;
    L_0x01ac:
        r2 = move-exception;
        r3 = r2;
        r4 = r12;
        r2 = r11;
        goto L_0x00f0;
    L_0x01b2:
        r4 = r2;
        goto L_0x00a8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcf.zzkV(int):java.util.List");
    }

    /* Access modifiers changed, original: 0000 */
    public int zzms() {
        boolean z = true;
        long currentTimeMillis = this.zzsd.currentTimeMillis();
        if (currentTimeMillis <= this.zzbpT + 86400000) {
            return 0;
        }
        this.zzbpT = currentTimeMillis;
        SQLiteDatabase zzgv = zzgv("Error opening database for deleteStaleHits.");
        if (zzgv == null) {
            return 0;
        }
        String str = "gtm_hits";
        String str2 = "HIT_TIME < ?";
        String[] strArr = new String[]{Long.toString(this.zzsd.currentTimeMillis() - 2592000000L)};
        int delete = !(zzgv instanceof SQLiteDatabase) ? zzgv.delete(str, str2, strArr) : SQLiteInstrumentation.delete(zzgv, str, str2, strArr);
        zzaw zzaw = this.zzbpR;
        if (zzKe() != 0) {
            z = false;
        }
        zzaw.zzaE(z);
        return delete;
    }
}
