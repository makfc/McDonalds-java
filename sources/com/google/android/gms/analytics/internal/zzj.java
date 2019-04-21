package com.google.android.gms.analytics.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.stetho.common.Utf8Charset;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzn;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzj extends zzd implements Closeable {
    private static final String zzWN = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzWO = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zza zzWP;
    private final zzal zzWQ = new zzal(zzlQ());
    private final zzal zzWR = new zzal(zzlQ());

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, null, 1);
        }

        private void zza(SQLiteDatabase sQLiteDatabase) {
            String valueOf;
            int i = 1;
            Set zzb = zzb(sQLiteDatabase, "hits2");
            String[] strArr = new String[]{"hit_id", "hit_string", "hit_time", "hit_url"};
            int i2 = 0;
            while (i2 < 4) {
                Object obj = strArr[i2];
                if (zzb.remove(obj)) {
                    i2++;
                } else {
                    String str = "Database hits2 is missing required column: ";
                    valueOf = String.valueOf(obj);
                    throw new SQLiteException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            }
            if (zzb.remove("hit_app_id")) {
                i = 0;
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (i != 0) {
                valueOf = "ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, valueOf);
                } else {
                    sQLiteDatabase.execSQL(valueOf);
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x0046  */
        private boolean zza(android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
            /*
            r11 = this;
            r9 = 0;
            r10 = 0;
            r2 = "SQLITE_MASTER";
            r1 = 1;
            r3 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            r1 = 0;
            r4 = "name";
            r3[r1] = r4;	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            r4 = "name=?";
            r1 = 1;
            r5 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            r1 = 0;
            r5[r1] = r13;	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            r6 = 0;
            r7 = 0;
            r8 = 0;
            r1 = r12 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            if (r1 != 0) goto L_0x002a;
        L_0x001b:
            r1 = r12;
            r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
        L_0x0020:
            r1 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x004d }
            if (r2 == 0) goto L_0x0029;
        L_0x0026:
            r2.close();
        L_0x0029:
            return r1;
        L_0x002a:
            r0 = r12;
            r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            r1 = r0;
            r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0033, all -> 0x0043 }
            goto L_0x0020;
        L_0x0033:
            r1 = move-exception;
            r2 = r10;
        L_0x0035:
            r3 = com.google.android.gms.analytics.internal.zzj.this;	 Catch:{ all -> 0x004a }
            r4 = "Error querying for table";
            r3.zzc(r4, r13, r1);	 Catch:{ all -> 0x004a }
            if (r2 == 0) goto L_0x0041;
        L_0x003e:
            r2.close();
        L_0x0041:
            r1 = r9;
            goto L_0x0029;
        L_0x0043:
            r1 = move-exception;
        L_0x0044:
            if (r10 == 0) goto L_0x0049;
        L_0x0046:
            r10.close();
        L_0x0049:
            throw r1;
        L_0x004a:
            r1 = move-exception;
            r10 = r2;
            goto L_0x0044;
        L_0x004d:
            r1 = move-exception;
            goto L_0x0035;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzj$zza.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
        }

        private Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
            HashSet hashSet = new HashSet();
            String stringBuilder = new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString();
            Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(stringBuilder, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, stringBuilder, null);
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                return hashSet;
            } finally {
                rawQuery.close();
            }
        }

        private void zzb(SQLiteDatabase sQLiteDatabase) {
            int i = 0;
            Set zzb = zzb(sQLiteDatabase, "properties");
            String[] strArr = new String[]{"app_uid", "cid", "tid", "params", "adid", "hits_count"};
            while (i < 6) {
                Object obj = strArr[i];
                if (zzb.remove(obj)) {
                    i++;
                } else {
                    String str = "Database properties is missing required column: ";
                    String valueOf = String.valueOf(obj);
                    throw new SQLiteException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database properties table has extra columns");
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (zzj.this.zzWR.zzx(3600000)) {
                try {
                    return super.getWritableDatabase();
                } catch (SQLiteException e) {
                    zzj.this.zzWR.start();
                    zzj.this.zzbK("Opening the database failed, dropping the table and recreating it");
                    zzj.this.getContext().getDatabasePath(zzj.this.zzmv()).delete();
                    try {
                        SQLiteDatabase writableDatabase = super.getWritableDatabase();
                        zzj.this.zzWR.clear();
                        return writableDatabase;
                    } catch (SQLiteException e2) {
                        zzj.this.zze("Failed to open freshly created database", e2);
                        throw e2;
                    }
                }
            }
            throw new SQLiteException("Database open failed");
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzx.zzbR(sQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            String str;
            if (VERSION.SDK_INT < 15) {
                str = "PRAGMA journal_mode=memory";
                Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (zza(sQLiteDatabase, "hits2")) {
                zza(sQLiteDatabase);
            } else {
                str = zzj.zzWN;
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
            }
            if (zza(sQLiteDatabase, "properties")) {
                zzb(sQLiteDatabase);
                return;
            }
            String str2 = "CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzj(zzf zzf) {
        super(zzf);
        this.zzWP = new zza(zzf.getContext(), zzmv());
    }

    private static String zzP(Map<String, String> map) {
        zzaa.zzz(map);
        Builder builder = new Builder();
        for (Entry entry : map.entrySet()) {
            builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    private long zza(String str, String[] strArr, long j) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery(str, strArr) : SQLiteInstrumentation.rawQuery(writableDatabase, str, strArr);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private long zzb(String str, String[] strArr) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery(str, strArr) : SQLiteInstrumentation.rawQuery(writableDatabase, str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String zzd(zzab zzab) {
        return zzab.zznV() ? zzlS().zznk() : zzlS().zznl();
    }

    private static String zze(zzab zzab) {
        zzaa.zzz(zzab);
        Builder builder = new Builder();
        for (Entry entry : zzab.zzm().entrySet()) {
            String str = (String) entry.getKey();
            if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str))) {
                builder.appendQueryParameter(str, (String) entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    private void zzmu() {
        int zznu = zzlS().zznu();
        long zzml = zzml();
        if (zzml > ((long) (zznu - 1))) {
            List zzq = zzq((zzml - ((long) zznu)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzq.size()));
            zzq(zzq);
        }
    }

    private String zzmv() {
        return !zzlS().zzmW() ? zzlS().zznw() : zzlS().zzmX() ? zzlS().zznw() : zzlS().zznx();
    }

    public void beginTransaction() {
        zzma();
        getWritableDatabase().beginTransaction();
    }

    public void close() {
        try {
            this.zzWP.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    public void endTransaction() {
        zzma();
        getWritableDatabase().endTransaction();
    }

    /* Access modifiers changed, original: 0000 */
    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzWP.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isEmpty() {
        return zzml() == 0;
    }

    public void setTransactionSuccessful() {
        zzma();
        getWritableDatabase().setTransactionSuccessful();
    }

    public long zza(long j, String str, String str2) {
        zzaa.zzdl(str);
        zzaa.zzdl(str2);
        zzma();
        zzkN();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public void zza(long j, String str) {
        zzaa.zzdl(str);
        zzma();
        zzkN();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str2 = "properties";
        String str3 = "app_uid=? AND cid<>?";
        String[] strArr = new String[]{String.valueOf(j), str};
        int delete = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str2, str3, strArr) : SQLiteInstrumentation.delete(writableDatabase, str2, str3, strArr);
        if (delete > 0) {
            zza("Deleted property records", Integer.valueOf(delete));
        }
    }

    public void zzb(zzh zzh) {
        zzaa.zzz(zzh);
        zzma();
        zzkN();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String zzP = zzP(zzh.zzm());
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_uid", Long.valueOf(zzh.zzmi()));
        contentValues.put("cid", zzh.zzku());
        contentValues.put("tid", zzh.zzmj());
        contentValues.put("adid", Integer.valueOf(zzh.zzmk() ? 1 : 0));
        contentValues.put("hits_count", Long.valueOf(zzh.zzml()));
        contentValues.put("params", zzP);
        try {
            String str = "properties";
            if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str, null, contentValues, 5)) == -1) {
                zzbK("Failed to insert/update a property (got -1)");
            }
        } catch (SQLiteException e) {
            zze("Error storing a property", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, String> zzbL(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String str2 = "?";
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            return zzn.zza(new URI(str), Utf8Charset.NAME);
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, String> zzbM(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String str2 = "?";
            String valueOf = String.valueOf(str);
            return zzn.zza(new URI(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)), Utf8Charset.NAME);
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    public void zzc(zzab zzab) {
        zzaa.zzz(zzab);
        zzkN();
        zzma();
        String zze = zze(zzab);
        if (zze.length() > 8192) {
            zzlR().zza(zzab, "Hit length exceeds the maximum allowed size");
            return;
        }
        zzmu();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", zze);
        contentValues.put("hit_time", Long.valueOf(zzab.zznT()));
        contentValues.put("hit_app_id", Integer.valueOf(zzab.zznR()));
        contentValues.put("hit_url", zzd(zzab));
        try {
            zze = "hits2";
            long insert = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insert(zze, null, contentValues) : SQLiteInstrumentation.insert(writableDatabase, zze, null, contentValues);
            if (insert == -1) {
                zzbK("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzab);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public long zzml() {
        zzkN();
        zzma();
        return zzb("SELECT COUNT(*) FROM hits2", null);
    }

    public void zzmq() {
        zzkN();
        zzma();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str = "hits2";
        if (writableDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(writableDatabase, str, null, null);
        } else {
            writableDatabase.delete(str, null, null);
        }
    }

    public void zzmr() {
        zzkN();
        zzma();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str = "properties";
        if (writableDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(writableDatabase, str, null, null);
        } else {
            writableDatabase.delete(str, null, null);
        }
    }

    public int zzms() {
        zzkN();
        zzma();
        if (!this.zzWQ.zzx(86400000)) {
            return 0;
        }
        this.zzWQ.start();
        zzbG("Deleting stale hits (if any)");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str = "hits2";
        String str2 = "hit_time < ?";
        String[] strArr = new String[]{Long.toString(zzlQ().currentTimeMillis() - 2592000000L)};
        int delete = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str, str2, strArr) : SQLiteInstrumentation.delete(writableDatabase, str, str2, strArr);
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public long zzmt() {
        zzkN();
        zzma();
        return zza(zzWO, null, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    public java.util.List<java.lang.Long> zzq(long r14) {
        /*
        r13 = this;
        r10 = 0;
        r13.zzkN();
        r13.zzma();
        r0 = 0;
        r0 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1));
        if (r0 > 0) goto L_0x0012;
    L_0x000d:
        r0 = java.util.Collections.emptyList();
    L_0x0011:
        return r0;
    L_0x0012:
        r0 = r13.getWritableDatabase();
        r9 = new java.util.ArrayList;
        r9.<init>();
        r1 = "hits2";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r3 = 0;
        r4 = "hit_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "%s ASC";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r11 = 0;
        r12 = "hit_id";
        r8[r11] = r12;	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r7 = java.lang.String.format(r7, r8);	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r8 = java.lang.Long.toString(r14);	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        if (r11 != 0) goto L_0x0062;
    L_0x003f:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
    L_0x0043:
        r0 = r1.moveToFirst();	 Catch:{ SQLiteException -> 0x0080 }
        if (r0 == 0) goto L_0x005b;
    L_0x0049:
        r0 = 0;
        r2 = r1.getLong(r0);	 Catch:{ SQLiteException -> 0x0080 }
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ SQLiteException -> 0x0080 }
        r9.add(r0);	 Catch:{ SQLiteException -> 0x0080 }
        r0 = r1.moveToNext();	 Catch:{ SQLiteException -> 0x0080 }
        if (r0 != 0) goto L_0x0049;
    L_0x005b:
        if (r1 == 0) goto L_0x0060;
    L_0x005d:
        r1.close();
    L_0x0060:
        r0 = r9;
        goto L_0x0011;
    L_0x0062:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0069, all -> 0x0076 }
        goto L_0x0043;
    L_0x0069:
        r0 = move-exception;
        r1 = r10;
    L_0x006b:
        r2 = "Error selecting hit ids";
        r13.zzd(r2, r0);	 Catch:{ all -> 0x007d }
        if (r1 == 0) goto L_0x0060;
    L_0x0072:
        r1.close();
        goto L_0x0060;
    L_0x0076:
        r0 = move-exception;
    L_0x0077:
        if (r10 == 0) goto L_0x007c;
    L_0x0079:
        r10.close();
    L_0x007c:
        throw r0;
    L_0x007d:
        r0 = move-exception;
        r10 = r1;
        goto L_0x0077;
    L_0x0080:
        r0 = move-exception;
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzj.zzq(long):java.util.List");
    }

    public void zzq(List<Long> list) {
        zzaa.zzz(list);
        zzkN();
        zzma();
        if (!list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("hit_id");
            stringBuilder.append(" in (");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    Long l = (Long) list.get(i2);
                    if (l != null && l.longValue() != 0) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append(l);
                        i = i2 + 1;
                    }
                } else {
                    stringBuilder.append(")");
                    String stringBuilder2 = stringBuilder.toString();
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                        String str = "hits2";
                        i = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str, stringBuilder2, null) : SQLiteInstrumentation.delete(writableDatabase, str, stringBuilder2, null);
                        if (i != list.size()) {
                            zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(i), stringBuilder2);
                            return;
                        }
                        return;
                    } catch (SQLiteException e) {
                        zze("Error deleting hits", e);
                        throw e;
                    }
                }
            }
            throw new SQLiteException("Invalid hit id");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ad A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0016, PHI: r9 } */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a9  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:0x00a9, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:28:0x00ad, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:29:0x00af, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:30:0x00b0, code skipped:
            r1 = r9;
     */
    public java.util.List<com.google.android.gms.analytics.internal.zzab> zzr(long r14) {
        /*
        r13 = this;
        r0 = 1;
        r1 = 0;
        r9 = 0;
        r2 = 0;
        r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x0093;
    L_0x0009:
        com.google.android.gms.common.internal.zzaa.zzaj(r0);
        r13.zzkN();
        r13.zzma();
        r0 = r13.getWritableDatabase();
        r1 = "hits2";
        r2 = 5;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r3 = 0;
        r4 = "hit_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r3 = 1;
        r4 = "hit_time";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r3 = 2;
        r4 = "hit_string";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r3 = 3;
        r4 = "hit_url";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r3 = 4;
        r4 = "hit_app_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "%s ASC";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r10 = 0;
        r11 = "hit_id";
        r8[r10] = r11;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r7 = java.lang.String.format(r7, r8);	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r8 = java.lang.Long.toString(r14);	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        if (r10 != 0) goto L_0x0096;
    L_0x004e:
        r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
    L_0x0052:
        r10 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r10.<init>();	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r0 = r9.moveToFirst();	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        if (r0 == 0) goto L_0x008d;
    L_0x005d:
        r0 = 0;
        r6 = r9.getLong(r0);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r0 = 1;
        r3 = r9.getLong(r0);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r0 = 2;
        r0 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r1 = 3;
        r1 = r9.getString(r1);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r2 = 4;
        r8 = r9.getInt(r2);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r2 = r13.zzbL(r0);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r5 = com.google.android.gms.analytics.internal.zzao.zzcb(r1);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r0 = new com.google.android.gms.analytics.internal.zzab;	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r1 = r13;
        r0.<init>(r1, r2, r3, r5, r6, r8);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r10.add(r0);	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        r0 = r9.moveToNext();	 Catch:{ SQLiteException -> 0x00af, all -> 0x00ad }
        if (r0 != 0) goto L_0x005d;
    L_0x008d:
        if (r9 == 0) goto L_0x0092;
    L_0x008f:
        r9.close();
    L_0x0092:
        return r10;
    L_0x0093:
        r0 = r1;
        goto L_0x0009;
    L_0x0096:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        r9 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x009d, all -> 0x00ad }
        goto L_0x0052;
    L_0x009d:
        r0 = move-exception;
        r1 = r9;
    L_0x009f:
        r2 = "Error loading hits from the database";
        r13.zze(r2, r0);	 Catch:{ all -> 0x00a5 }
        throw r0;	 Catch:{ all -> 0x00a5 }
    L_0x00a5:
        r0 = move-exception;
        r9 = r1;
    L_0x00a7:
        if (r9 == 0) goto L_0x00ac;
    L_0x00a9:
        r9.close();
    L_0x00ac:
        throw r0;
    L_0x00ad:
        r0 = move-exception;
        goto L_0x00a7;
    L_0x00af:
        r0 = move-exception;
        r1 = r9;
        goto L_0x009f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzj.zzr(long):java.util.List");
    }

    public void zzs(long j) {
        zzkN();
        zzma();
        List arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zzq(arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c3 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000c, PHI: r9 } */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bf  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:37:0x00bf, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:39:0x00c3, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00c5, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:41:0x00c6, code skipped:
            r1 = r9;
     */
    public java.util.List<com.google.android.gms.analytics.internal.zzh> zzt(long r14) {
        /*
        r13 = this;
        r13.zzma();
        r13.zzkN();
        r0 = r13.getWritableDatabase();
        r9 = 0;
        r1 = 5;
        r2 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = 0;
        r3 = "cid";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = 1;
        r3 = "tid";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = 2;
        r3 = "adid";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = 3;
        r3 = "hits_count";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = 4;
        r3 = "params";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = r13.zzlS();	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r10 = r1.zznv();	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r8 = java.lang.String.valueOf(r10);	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r3 = "app_uid=?";
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = 0;
        r5 = java.lang.String.valueOf(r14);	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r4[r1] = r5;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r1 = "properties";
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        if (r11 != 0) goto L_0x00a0;
    L_0x0048:
        r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
    L_0x004c:
        r11 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r11.<init>();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r0 = r9.moveToFirst();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        if (r0 == 0) goto L_0x008f;
    L_0x0057:
        r0 = 0;
        r3 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r0 = 1;
        r4 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r0 = 2;
        r0 = r9.getInt(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        if (r0 == 0) goto L_0x00a7;
    L_0x0068:
        r5 = 1;
    L_0x0069:
        r0 = 3;
        r0 = r9.getInt(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r6 = (long) r0;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r0 = 4;
        r0 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r8 = r13.zzbM(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        if (r0 != 0) goto L_0x0084;
    L_0x007e:
        r0 = android.text.TextUtils.isEmpty(r4);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        if (r0 == 0) goto L_0x00a9;
    L_0x0084:
        r0 = "Read property with empty client id or tracker id";
        r13.zzc(r0, r3, r4);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
    L_0x0089:
        r0 = r9.moveToNext();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        if (r0 != 0) goto L_0x0057;
    L_0x008f:
        r0 = r11.size();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        if (r0 < r10) goto L_0x009a;
    L_0x0095:
        r0 = "Sending hits to too many properties. Campaign report might be incorrect";
        r13.zzbJ(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
    L_0x009a:
        if (r9 == 0) goto L_0x009f;
    L_0x009c:
        r9.close();
    L_0x009f:
        return r11;
    L_0x00a0:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        r9 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00c5, all -> 0x00c3 }
        goto L_0x004c;
    L_0x00a7:
        r5 = 0;
        goto L_0x0069;
    L_0x00a9:
        r0 = new com.google.android.gms.analytics.internal.zzh;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r1 = r14;
        r0.<init>(r1, r3, r4, r5, r6, r8);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        r11.add(r0);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00c3 }
        goto L_0x0089;
    L_0x00b3:
        r0 = move-exception;
        r1 = r9;
    L_0x00b5:
        r2 = "Error loading hits from the database";
        r13.zze(r2, r0);	 Catch:{ all -> 0x00bb }
        throw r0;	 Catch:{ all -> 0x00bb }
    L_0x00bb:
        r0 = move-exception;
        r9 = r1;
    L_0x00bd:
        if (r9 == 0) goto L_0x00c2;
    L_0x00bf:
        r9.close();
    L_0x00c2:
        throw r0;
    L_0x00c3:
        r0 = move-exception;
        goto L_0x00bd;
    L_0x00c5:
        r0 = move-exception;
        r1 = r9;
        goto L_0x00b5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzj.zzt(long):java.util.List");
    }
}
