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
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class zzw implements zzc {
    private static final String zzboG = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", Parameters.API_KEY, "value", "expires"});
    private final Context mContext;
    private final Executor zzboH;
    private zza zzboI;
    private int zzboJ;
    private zze zzsd;

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw$zza.zza(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        private void zzc(SQLiteDatabase sQLiteDatabase) {
            String str = "SELECT * FROM datalayer WHERE 0";
            Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                if (!hashSet.remove(Parameters.API_KEY) || !hashSet.remove("value") || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } finally {
                rawQuery.close();
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                zzw.this.mContext.getDatabasePath("google_tagmanager.db").delete();
            }
            return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
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
            if (zza("datalayer", sQLiteDatabase)) {
                zzc(sQLiteDatabase);
                return;
            }
            String zzJE = zzw.zzboG;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, zzJE);
            } else {
                sQLiteDatabase.execSQL(zzJE);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    private static class zzb {
        final byte[] zzboP;
        final String zzwQ;

        zzb(String str, byte[] bArr) {
            this.zzwQ = str;
            this.zzboP = bArr;
        }

        public String toString() {
            String str = this.zzwQ;
            return new StringBuilder(String.valueOf(str).length() + 54).append("KeyAndSerialized: key = ").append(str).append(" serialized hash = ").append(Arrays.hashCode(this.zzboP)).toString();
        }
    }

    public zzw(Context context) {
        this(context, zzh.zzuW(), "google_tagmanager.db", ActivityTrace.MAX_TRACES, Executors.newSingleThreadExecutor());
    }

    zzw(Context context, zze zze, String str, int i, Executor executor) {
        this.mContext = context;
        this.zzsd = zze;
        this.zzboJ = i;
        this.zzboH = executor;
        this.zzboI = new zza(this.mContext, str);
    }

    private List<zza> zzG(List<zzb> list) {
        ArrayList arrayList = new ArrayList();
        for (zzb zzb : list) {
            arrayList.add(new zza(zzb.zzwQ, zzy(zzb.zzboP)));
        }
        return arrayList;
    }

    private List<zzb> zzH(List<zza> list) {
        ArrayList arrayList = new ArrayList();
        for (zza zza : list) {
            arrayList.add(new zzb(zza.zzwQ, zzI(zza.zzRF)));
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A:{SYNTHETIC, Splitter:B:20:0x002e} */
    private byte[] zzI(java.lang.Object r6) {
        /*
        r5 = this;
        r0 = 0;
        r2 = new java.io.ByteArrayOutputStream;
        r2.<init>();
        r1 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x001b, all -> 0x0028 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x001b, all -> 0x0028 }
        r1.writeObject(r6);	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        r0 = r2.toByteArray();	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        if (r1 == 0) goto L_0x0017;
    L_0x0014:
        r1.close();	 Catch:{ IOException -> 0x003b }
    L_0x0017:
        r2.close();	 Catch:{ IOException -> 0x003b }
    L_0x001a:
        return r0;
    L_0x001b:
        r1 = move-exception;
        r1 = r0;
    L_0x001d:
        if (r1 == 0) goto L_0x0022;
    L_0x001f:
        r1.close();	 Catch:{ IOException -> 0x0026 }
    L_0x0022:
        r2.close();	 Catch:{ IOException -> 0x0026 }
        goto L_0x001a;
    L_0x0026:
        r1 = move-exception;
        goto L_0x001a;
    L_0x0028:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x002c:
        if (r1 == 0) goto L_0x0031;
    L_0x002e:
        r1.close();	 Catch:{ IOException -> 0x0035 }
    L_0x0031:
        r2.close();	 Catch:{ IOException -> 0x0035 }
    L_0x0034:
        throw r0;
    L_0x0035:
        r1 = move-exception;
        goto L_0x0034;
    L_0x0037:
        r0 = move-exception;
        goto L_0x002c;
    L_0x0039:
        r3 = move-exception;
        goto L_0x001d;
    L_0x003b:
        r1 = move-exception;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zzI(java.lang.Object):byte[]");
    }

    private List<zza> zzJA() {
        try {
            zzaq(this.zzsd.currentTimeMillis());
            List<zza> zzG = zzG(zzJB());
            return zzG;
        } finally {
            zzJD();
        }
    }

    private List<zzb> zzJB() {
        SQLiteDatabase zzgv = zzgv("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (zzgv == null) {
            return arrayList;
        }
        String[] strArr = new String[]{Parameters.API_KEY, "value"};
        String str = "datalayer";
        String str2 = "ID";
        Cursor query = !(zzgv instanceof SQLiteDatabase) ? zzgv.query(str, strArr, null, null, null, null, str2, null) : SQLiteInstrumentation.query(zzgv, str, strArr, null, null, null, null, str2, null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzb(query.getString(0), query.getBlob(1)));
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

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
    private int zzJC() {
        /*
        r6 = this;
        r2 = 0;
        r1 = 0;
        r0 = "Error opening database for getNumStoredEntries.";
        r0 = r6.zzgv(r0);
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return r1;
    L_0x000b:
        r3 = "SELECT COUNT(*) from datalayer";
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
        r2 = "Error getting numStoredEntries";
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zzJC():int");
    }

    private void zzJD() {
        try {
            this.zzboI.close();
        } catch (SQLiteException e) {
        }
    }

    private void zzaq(long j) {
        SQLiteDatabase zzgv = zzgv("Error opening database for deleteOlderThan.");
        if (zzgv != null) {
            try {
                String str = "datalayer";
                String str2 = "expires <= ?";
                String[] strArr = new String[]{Long.toString(j)};
                zzbn.m7502v("Deleted " + (!(zzgv instanceof SQLiteDatabase) ? zzgv.delete(str, str2, strArr) : SQLiteInstrumentation.delete(zzgv, str, str2, strArr)) + " expired items");
            } catch (SQLiteException e) {
                zzbn.zzaW("Error deleting old entries.");
            }
        }
    }

    private synchronized void zzb(List<zzb> list, long j) {
        try {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            zzaq(currentTimeMillis);
            zzkO(list.size());
            zzc(list, currentTimeMillis + j);
            zzJD();
        } catch (Throwable th) {
            zzJD();
        }
    }

    private void zzc(List<zzb> list, long j) {
        SQLiteDatabase zzgv = zzgv("Error opening database for writeEntryToDatabase.");
        if (zzgv != null) {
            for (zzb zzb : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("expires", Long.valueOf(j));
                contentValues.put(Parameters.API_KEY, zzb.zzwQ);
                contentValues.put("value", zzb.zzboP);
                String str = "datalayer";
                if (zzgv instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.insert(zzgv, str, null, contentValues);
                } else {
                    zzgv.insert(str, null, contentValues);
                }
            }
        }
    }

    private void zzg(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzgv = zzgv("Error opening database for deleteEntries.");
            if (zzgv != null) {
                String format = String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))});
                try {
                    String str = "datalayer";
                    if (zzgv instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(zzgv, str, format, strArr);
                    } else {
                        zzgv.delete(str, format, strArr);
                    }
                } catch (SQLiteException e) {
                    format = "Error deleting entries ";
                    String valueOf = String.valueOf(Arrays.toString(strArr));
                    zzbn.zzaW(valueOf.length() != 0 ? format.concat(valueOf) : new String(format));
                }
            }
        }
    }

    private void zzgu(String str) {
        SQLiteDatabase zzgv = zzgv("Error opening database for clearKeysWithPrefix.");
        if (zzgv != null) {
            try {
                String str2 = "datalayer";
                String str3 = "key = ? OR key LIKE ?";
                String[] strArr = new String[]{str, String.valueOf(str).concat(".%")};
                zzbn.m7502v("Cleared " + (!(zzgv instanceof SQLiteDatabase) ? zzgv.delete(str2, str3, strArr) : SQLiteInstrumentation.delete(zzgv, str2, str3, strArr)) + " items");
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                zzbn.zzaW(new StringBuilder((String.valueOf(str).length() + 44) + String.valueOf(valueOf).length()).append("Error deleting entries with key prefix: ").append(str).append(" (").append(valueOf).append(").").toString());
            } finally {
                zzJD();
            }
        }
    }

    private SQLiteDatabase zzgv(String str) {
        try {
            return this.zzboI.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbn.zzaW(str);
            return null;
        }
    }

    private void zzkO(int i) {
        int zzJC = (zzJC() - this.zzboJ) + i;
        if (zzJC > 0) {
            List zzkP = zzkP(zzJC);
            zzbn.zzaV("DataLayer store full, deleting " + zzkP.size() + " entries to make room.");
            zzg((String[]) zzkP.toArray(new String[0]));
        }
    }

    private List<String> zzkP(int i) {
        SQLiteException e;
        Throwable th;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            zzbn.zzaW("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase zzgv = zzgv("Error opening database for peekEntryIds.");
        if (zzgv == null) {
            return arrayList;
        }
        Cursor query;
        try {
            String str = "datalayer";
            String[] strArr = new String[]{"ID"};
            String format = String.format("%s ASC", new Object[]{"ID"});
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
                    String str2 = "Error in peekEntries fetching entryIds: ";
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

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0029 A:{SYNTHETIC, Splitter:B:20:0x0029} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0038 A:{SYNTHETIC, Splitter:B:27:0x0038} */
    private java.lang.Object zzy(byte[] r6) {
        /*
        r5 = this;
        r0 = 0;
        r2 = new java.io.ByteArrayInputStream;
        r2.<init>(r6);
        r1 = new java.io.ObjectInputStream;	 Catch:{ IOException -> 0x0018, ClassNotFoundException -> 0x0025, all -> 0x0032 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x0018, ClassNotFoundException -> 0x0025, all -> 0x0032 }
        r0 = r1.readObject();	 Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0043, all -> 0x0041 }
        if (r1 == 0) goto L_0x0014;
    L_0x0011:
        r1.close();	 Catch:{ IOException -> 0x0047 }
    L_0x0014:
        r2.close();	 Catch:{ IOException -> 0x0047 }
    L_0x0017:
        return r0;
    L_0x0018:
        r1 = move-exception;
        r1 = r0;
    L_0x001a:
        if (r1 == 0) goto L_0x001f;
    L_0x001c:
        r1.close();	 Catch:{ IOException -> 0x0023 }
    L_0x001f:
        r2.close();	 Catch:{ IOException -> 0x0023 }
        goto L_0x0017;
    L_0x0023:
        r1 = move-exception;
        goto L_0x0017;
    L_0x0025:
        r1 = move-exception;
        r1 = r0;
    L_0x0027:
        if (r1 == 0) goto L_0x002c;
    L_0x0029:
        r1.close();	 Catch:{ IOException -> 0x0030 }
    L_0x002c:
        r2.close();	 Catch:{ IOException -> 0x0030 }
        goto L_0x0017;
    L_0x0030:
        r1 = move-exception;
        goto L_0x0017;
    L_0x0032:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0036:
        if (r1 == 0) goto L_0x003b;
    L_0x0038:
        r1.close();	 Catch:{ IOException -> 0x003f }
    L_0x003b:
        r2.close();	 Catch:{ IOException -> 0x003f }
    L_0x003e:
        throw r0;
    L_0x003f:
        r1 = move-exception;
        goto L_0x003e;
    L_0x0041:
        r0 = move-exception;
        goto L_0x0036;
    L_0x0043:
        r3 = move-exception;
        goto L_0x0027;
    L_0x0045:
        r3 = move-exception;
        goto L_0x001a;
    L_0x0047:
        r1 = move-exception;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zzy(byte[]):java.lang.Object");
    }

    public void zza(final com.google.android.gms.tagmanager.DataLayer.zzc.zza zza) {
        this.zzboH.execute(new Runnable() {
            public void run() {
                zza.zzF(zzw.this.zzJA());
            }
        });
    }

    public void zza(List<zza> list, final long j) {
        final List zzH = zzH(list);
        this.zzboH.execute(new Runnable() {
            public void run() {
                zzw.this.zzb(zzH, j);
            }
        });
    }

    public void zzgt(final String str) {
        this.zzboH.execute(new Runnable() {
            public void run() {
                zzw.this.zzgu(str);
            }
        });
    }
}
