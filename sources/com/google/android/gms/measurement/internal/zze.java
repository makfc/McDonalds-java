package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzamc;
import com.google.android.gms.internal.zzsp.zzf;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zze extends zzaa {
    private static final Map<String, String> zzbbU = new ArrayMap(16);
    private final zzc zzbbV = new zzc(getContext(), zzmv());
    private final zzah zzbbW = new zzah(zzlQ());

    public static class zza {
        long zzbbX;
        long zzbbY;
        long zzbbZ;
        long zzbca;
    }

    interface zzb {
        boolean zza(long j, com.google.android.gms.internal.zzsp.zzb zzb);

        void zzc(com.google.android.gms.internal.zzsp.zze zze);
    }

    private class zzc extends SQLiteOpenHelper {
        zzc(Context context, String str) {
            super(context, str, null, 1);
        }

        @WorkerThread
        private void zza(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Map<String, String> map) throws SQLiteException {
            if (!zza(sQLiteDatabase, str)) {
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            }
            try {
                zza(sQLiteDatabase, str, str3, map);
            } catch (SQLiteException e) {
                zze.this.zzFm().zzFE().zzj("Failed to verify columns on table that was just created", str);
                throw e;
            }
        }

        @WorkerThread
        private void zza(SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) throws SQLiteException {
            Set zzb = zzb(sQLiteDatabase, str);
            String[] split = str2.split(",");
            int length = split.length;
            int i = 0;
            while (i < length) {
                Object obj = split[i];
                if (zzb.remove(obj)) {
                    i++;
                } else {
                    throw new SQLiteException(new StringBuilder((String.valueOf(str).length() + 35) + String.valueOf(obj).length()).append("Table ").append(str).append(" is missing required column: ").append(obj).toString());
                }
            }
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    if (!zzb.remove(entry.getKey())) {
                        String str3 = (String) entry.getValue();
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.execSQL(sQLiteDatabase, str3);
                        } else {
                            sQLiteDatabase.execSQL(str3);
                        }
                    }
                }
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException(new StringBuilder(String.valueOf(str).length() + 30).append("Table ").append(str).append(" table has extra columns").toString());
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x004e  */
        @android.support.annotation.WorkerThread
        private boolean zza(android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
            /*
            r11 = this;
            r9 = 0;
            r10 = 0;
            r2 = "SQLITE_MASTER";
            r1 = 1;
            r3 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            r1 = 0;
            r4 = "name";
            r3[r1] = r4;	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            r4 = "name=?";
            r1 = 1;
            r5 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            r1 = 0;
            r5[r1] = r13;	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            r6 = 0;
            r7 = 0;
            r8 = 0;
            r1 = r12 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            if (r1 != 0) goto L_0x002a;
        L_0x001b:
            r1 = r12;
            r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
        L_0x0020:
            r1 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0055 }
            if (r2 == 0) goto L_0x0029;
        L_0x0026:
            r2.close();
        L_0x0029:
            return r1;
        L_0x002a:
            r0 = r12;
            r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            r1 = r0;
            r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0033, all -> 0x004b }
            goto L_0x0020;
        L_0x0033:
            r1 = move-exception;
            r2 = r10;
        L_0x0035:
            r3 = com.google.android.gms.measurement.internal.zze.this;	 Catch:{ all -> 0x0052 }
            r3 = r3.zzFm();	 Catch:{ all -> 0x0052 }
            r3 = r3.zzFG();	 Catch:{ all -> 0x0052 }
            r4 = "Error querying for table";
            r3.zze(r4, r13, r1);	 Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x0049;
        L_0x0046:
            r2.close();
        L_0x0049:
            r1 = r9;
            goto L_0x0029;
        L_0x004b:
            r1 = move-exception;
        L_0x004c:
            if (r10 == 0) goto L_0x0051;
        L_0x004e:
            r10.close();
        L_0x0051:
            throw r1;
        L_0x0052:
            r1 = move-exception;
            r10 = r2;
            goto L_0x004c;
        L_0x0055:
            r1 = move-exception;
            goto L_0x0035;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze$zzc.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
        }

        @WorkerThread
        private Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
            Set<String> hashSet = new HashSet();
            String stringBuilder = new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString();
            Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(stringBuilder, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, stringBuilder, null);
            try {
                Collections.addAll(hashSet, rawQuery.getColumnNames());
                return hashSet;
            } finally {
                rawQuery.close();
            }
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            if (zze.this.zzbbW.zzx(zze.this.zzFo().zzEJ())) {
                try {
                    return super.getWritableDatabase();
                } catch (SQLiteException e) {
                    zze.this.zzbbW.start();
                    zze.this.zzFm().zzFE().log("Opening the database failed, dropping and recreating it");
                    zze.this.getContext().getDatabasePath(zze.this.zzmv()).delete();
                    try {
                        SQLiteDatabase writableDatabase = super.getWritableDatabase();
                        zze.this.zzbbW.clear();
                        return writableDatabase;
                    } catch (SQLiteException e2) {
                        zze.this.zzFm().zzFE().zzj("Failed to open freshly created database", e2);
                        throw e2;
                    }
                }
            }
            throw new SQLiteException("Database open failed");
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (VERSION.SDK_INT >= 9) {
                File file = new File(sQLiteDatabase.getPath());
                file.setReadable(false, false);
                file.setWritable(false, false);
                file.setReadable(true, true);
                file.setWritable(true, true);
            }
        }

        @WorkerThread
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
            zza(sQLiteDatabase, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", null);
            zza(sQLiteDatabase, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", null);
            zza(sQLiteDatabase, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zze.zzbbU);
            zza(sQLiteDatabase, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", null);
            zza(sQLiteDatabase, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", null);
            zza(sQLiteDatabase, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", null);
            zza(sQLiteDatabase, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", null);
            zza(sQLiteDatabase, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", null);
            zza(sQLiteDatabase, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", null);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    static {
        zzbbU.put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zzbbU.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zzbbU.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zzbbU.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zzbbU.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zzbbU.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zzbbU.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zzbbU.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zzbbU.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zzbbU.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zzbbU.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zzbbU.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zzbbU.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
        zzbbU.put("app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;");
        zzbbU.put("firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;");
        zzbbU.put("daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;");
    }

    zze(zzx zzx) {
        super(zzx);
    }

    private boolean zzFv() {
        return getContext().getDatabasePath(zzmv()).exists();
    }

    @WorkerThread
    @TargetApi(11)
    static int zza(Cursor cursor, int i) {
        if (VERSION.SDK_INT >= 11) {
            return cursor.getType(i);
        }
        CursorWindow window = ((SQLiteCursor) cursor).getWindow();
        int position = cursor.getPosition();
        return window.isNull(position, i) ? 0 : window.isLong(position, i) ? 1 : window.isFloat(position, i) ? 2 : window.isString(position, i) ? 3 : window.isBlob(position, i) ? 4 : -1;
    }

    @WorkerThread
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
            zzFm().zzFE().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @WorkerThread
    private void zza(String str, com.google.android.gms.internal.zzsn.zza zza) {
        Object obj = null;
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzaa.zzz(zza);
        zzaa.zzz(zza.zzbgk);
        zzaa.zzz(zza.zzbgj);
        if (zza.zzbgi == null) {
            zzFm().zzFG().log("Audience with no ID");
            return;
        }
        int intValue = zza.zzbgi.intValue();
        for (com.google.android.gms.internal.zzsn.zzb zzb : zza.zzbgk) {
            if (zzb.zzbgm == null) {
                zzFm().zzFG().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", str, zza.zzbgi);
                return;
            }
        }
        for (com.google.android.gms.internal.zzsn.zze zze : zza.zzbgj) {
            if (zze.zzbgm == null) {
                zzFm().zzFG().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", str, zza.zzbgi);
                return;
            }
        }
        Object obj2 = 1;
        for (com.google.android.gms.internal.zzsn.zzb zza2 : zza.zzbgk) {
            if (!zza(str, intValue, zza2)) {
                obj2 = null;
                break;
            }
        }
        if (obj2 != null) {
            for (com.google.android.gms.internal.zzsn.zze zza3 : zza.zzbgj) {
                if (!zza(str, intValue, zza3)) {
                    break;
                }
            }
        }
        obj = obj2;
        if (obj == null) {
            zzz(str, intValue);
        }
    }

    @WorkerThread
    private boolean zza(String str, int i, com.google.android.gms.internal.zzsn.zzb zzb) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzaa.zzz(zzb);
        if (TextUtils.isEmpty(zzb.zzbgn)) {
            zzFm().zzFG().zze("Event filter had no event name. Audience definition ignored. audienceId, filterId", Integer.valueOf(i), String.valueOf(zzb.zzbgm));
            return false;
        }
        try {
            byte[] bArr = new byte[zzb.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zzb.writeTo(zzO);
            zzO.zzWU();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzb.zzbgm);
            contentValues.put("event_name", zzb.zzbgn);
            contentValues.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, bArr);
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String str2 = "event_filters";
                if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str2, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str2, null, contentValues, 5)) == -1) {
                    zzFm().zzFE().log("Failed to insert event filter (got -1)");
                }
                return true;
            } catch (SQLiteException e) {
                zzFm().zzFE().zzj("Error storing event filter", e);
                return false;
            }
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Configuration loss. Failed to serialize event filter", e2);
            return false;
        }
    }

    @WorkerThread
    private boolean zza(String str, int i, com.google.android.gms.internal.zzsn.zze zze) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzaa.zzz(zze);
        if (TextUtils.isEmpty(zze.zzbgC)) {
            zzFm().zzFG().zze("Property filter had no property name. Audience definition ignored. audienceId, filterId", Integer.valueOf(i), String.valueOf(zze.zzbgm));
            return false;
        }
        try {
            byte[] bArr = new byte[zze.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zze.writeTo(zzO);
            zzO.zzWU();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zze.zzbgm);
            contentValues.put("property_name", zze.zzbgC);
            contentValues.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, bArr);
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String str2 = "property_filters";
                if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str2, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str2, null, contentValues, 5)) != -1) {
                    return true;
                }
                zzFm().zzFE().log("Failed to insert property filter (got -1)");
                return false;
            } catch (SQLiteException e) {
                zzFm().zzFE().zzj("Error storing property filter", e);
                return false;
            }
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Configuration loss. Failed to serialize property filter", e2);
            return false;
        }
    }

    @WorkerThread
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
            zzFm().zzFE().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @WorkerThread
    public void beginTransaction() {
        zzma();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public void endTransaction() {
        zzma();
        getWritableDatabase().endTransaction();
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        zzkN();
        try {
            return this.zzbbV.getWritableDatabase();
        } catch (SQLiteException e) {
            zzFm().zzFG().zzj("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public void setTransactionSuccessful() {
        zzma();
        getWritableDatabase().setTransactionSuccessful();
    }

    public void zzC(List<Long> list) {
        zzaa.zzz(list);
        zzkN();
        zzma();
        StringBuilder stringBuilder = new StringBuilder("rowid in (");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            if (i2 != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(((Long) list.get(i2)).longValue());
            i = i2 + 1;
        }
        stringBuilder.append(")");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str = "raw_events";
        String stringBuilder2 = stringBuilder.toString();
        i = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str, stringBuilder2, null) : SQLiteInstrumentation.delete(writableDatabase, str, stringBuilder2, null);
        if (i != list.size()) {
            zzFm().zzFE().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(i), Integer.valueOf(list.size()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049  */
    @android.support.annotation.WorkerThread
    public java.lang.String zzFp() {
        /*
        r5 = this;
        r1 = 0;
        r0 = r5.getWritableDatabase();
        r2 = "select app_id from queue where app_id not in (select app_id from apps where measurement_enabled=0) order by rowid limit 1;";
        r3 = 0;
        r4 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x002f, all -> 0x0045 }
        if (r4 != 0) goto L_0x0021;
    L_0x000c:
        r2 = r0.rawQuery(r2, r3);	 Catch:{ SQLiteException -> 0x002f, all -> 0x0045 }
    L_0x0010:
        r0 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x004f }
        if (r0 == 0) goto L_0x0028;
    L_0x0016:
        r0 = 0;
        r0 = r2.getString(r0);	 Catch:{ SQLiteException -> 0x004f }
        if (r2 == 0) goto L_0x0020;
    L_0x001d:
        r2.close();
    L_0x0020:
        return r0;
    L_0x0021:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x002f, all -> 0x0045 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r0, r2, r3);	 Catch:{ SQLiteException -> 0x002f, all -> 0x0045 }
        goto L_0x0010;
    L_0x0028:
        if (r2 == 0) goto L_0x002d;
    L_0x002a:
        r2.close();
    L_0x002d:
        r0 = r1;
        goto L_0x0020;
    L_0x002f:
        r0 = move-exception;
        r2 = r1;
    L_0x0031:
        r3 = r5.zzFm();	 Catch:{ all -> 0x004d }
        r3 = r3.zzFE();	 Catch:{ all -> 0x004d }
        r4 = "Database error getting next bundle app id";
        r3.zzj(r4, r0);	 Catch:{ all -> 0x004d }
        if (r2 == 0) goto L_0x0043;
    L_0x0040:
        r2.close();
    L_0x0043:
        r0 = r1;
        goto L_0x0020;
    L_0x0045:
        r0 = move-exception;
        r2 = r1;
    L_0x0047:
        if (r2 == 0) goto L_0x004c;
    L_0x0049:
        r2.close();
    L_0x004c:
        throw r0;
    L_0x004d:
        r0 = move-exception;
        goto L_0x0047;
    L_0x004f:
        r0 = move-exception;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzFp():java.lang.String");
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzFq() {
        zzkN();
        zzma();
        if (zzFv()) {
            long j = zzFn().zzbdJ.get();
            long elapsedRealtime = zzlQ().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzFo().zzEP()) {
                zzFn().zzbdJ.set(elapsedRealtime);
                zzFr();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzFr() {
        zzkN();
        zzma();
        if (zzFv()) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = new String[]{String.valueOf(zzlQ().currentTimeMillis()), String.valueOf(zzFo().zzEO())};
            String str = "queue";
            String str2 = "abs(bundle_end_timestamp - ?) > cast(? as integer)";
            int delete = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str, str2, strArr) : SQLiteInstrumentation.delete(writableDatabase, str, str2, strArr);
            if (delete > 0) {
                zzFm().zzFL().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
            }
        }
    }

    @WorkerThread
    public long zzFs() {
        return zza("select max(bundle_end_timestamp) from queue", null, 0);
    }

    @WorkerThread
    public long zzFt() {
        return zza("select max(timestamp) from raw_events", null, 0);
    }

    public boolean zzFu() {
        return zzb("select count(1) > 0 from raw_events", null) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097  */
    @android.support.annotation.WorkerThread
    public com.google.android.gms.measurement.internal.zzi zzO(java.lang.String r13, java.lang.String r14) {
        /*
        r12 = this;
        r10 = 0;
        com.google.android.gms.common.internal.zzaa.zzdl(r13);
        com.google.android.gms.common.internal.zzaa.zzdl(r14);
        r12.zzkN();
        r12.zzma();
        r0 = r12.getWritableDatabase();	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r1 = "events";
        r2 = 3;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r3 = 0;
        r4 = "lifetime_count";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r3 = 1;
        r4 = "current_bundle_count";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r3 = 2;
        r4 = "last_fire_timestamp";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r3 = "app_id=? and name=?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r5 = 1;
        r4[r5] = r14;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        if (r8 != 0) goto L_0x0048;
    L_0x0037:
        r11 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
    L_0x003b:
        r0 = r11.moveToFirst();	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        if (r0 != 0) goto L_0x004f;
    L_0x0041:
        if (r11 == 0) goto L_0x0046;
    L_0x0043:
        r11.close();
    L_0x0046:
        r1 = r10;
    L_0x0047:
        return r1;
    L_0x0048:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        r11 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x007e, all -> 0x0094 }
        goto L_0x003b;
    L_0x004f:
        r0 = 0;
        r4 = r11.getLong(r0);	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r0 = 1;
        r6 = r11.getLong(r0);	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r0 = 2;
        r8 = r11.getLong(r0);	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r1 = new com.google.android.gms.measurement.internal.zzi;	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r2 = r13;
        r3 = r14;
        r1.<init>(r2, r3, r4, r6, r8);	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r0 = r11.moveToNext();	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        if (r0 == 0) goto L_0x0078;
    L_0x006b:
        r0 = r12.zzFm();	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r0 = r0.zzFE();	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
        r2 = "Got multiple records for event aggregates, expected one";
        r0.log(r2);	 Catch:{ SQLiteException -> 0x00a1, all -> 0x009b }
    L_0x0078:
        if (r11 == 0) goto L_0x0047;
    L_0x007a:
        r11.close();
        goto L_0x0047;
    L_0x007e:
        r0 = move-exception;
        r1 = r10;
    L_0x0080:
        r2 = r12.zzFm();	 Catch:{ all -> 0x009e }
        r2 = r2.zzFE();	 Catch:{ all -> 0x009e }
        r3 = "Error querying events";
        r2.zzd(r3, r13, r14, r0);	 Catch:{ all -> 0x009e }
        if (r1 == 0) goto L_0x0092;
    L_0x008f:
        r1.close();
    L_0x0092:
        r1 = r10;
        goto L_0x0047;
    L_0x0094:
        r0 = move-exception;
    L_0x0095:
        if (r10 == 0) goto L_0x009a;
    L_0x0097:
        r10.close();
    L_0x009a:
        throw r0;
    L_0x009b:
        r0 = move-exception;
        r10 = r11;
        goto L_0x0095;
    L_0x009e:
        r0 = move-exception;
        r10 = r1;
        goto L_0x0095;
    L_0x00a1:
        r0 = move-exception;
        r1 = r11;
        goto L_0x0080;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzO(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzi");
    }

    @WorkerThread
    public void zzP(String str, String str2) {
        zzaa.zzdl(str);
        zzaa.zzdl(str2);
        zzkN();
        zzma();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str3 = "user_attributes";
            String str4 = "app_id=? and name=?";
            String[] strArr = new String[]{str, str2};
            zzFm().zzFL().zzj("Deleted user attribute rows:", Integer.valueOf(!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr)));
        } catch (SQLiteException e) {
            zzFm().zzFE().zzd("Error deleting user attribute", str, str2, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    @android.support.annotation.WorkerThread
    public com.google.android.gms.measurement.internal.zzak zzQ(java.lang.String r11, java.lang.String r12) {
        /*
        r10 = this;
        r8 = 0;
        com.google.android.gms.common.internal.zzaa.zzdl(r11);
        com.google.android.gms.common.internal.zzaa.zzdl(r12);
        r10.zzkN();
        r10.zzma();
        r0 = r10.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r1 = "user_attributes";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r3 = 0;
        r4 = "set_timestamp";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r3 = 1;
        r4 = "value";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r3 = "app_id=? and name=?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r5 = 0;
        r4[r5] = r11;	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r5 = 1;
        r4[r5] = r12;	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        if (r9 != 0) goto L_0x0043;
    L_0x0032:
        r7 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
    L_0x0036:
        r0 = r7.moveToFirst();	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        if (r0 != 0) goto L_0x004a;
    L_0x003c:
        if (r7 == 0) goto L_0x0041;
    L_0x003e:
        r7.close();
    L_0x0041:
        r1 = r8;
    L_0x0042:
        return r1;
    L_0x0043:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        r7 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x0074, all -> 0x008a }
        goto L_0x0036;
    L_0x004a:
        r0 = 0;
        r4 = r7.getLong(r0);	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        r0 = 1;
        r6 = r10.zzb(r7, r0);	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        r1 = new com.google.android.gms.measurement.internal.zzak;	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        r2 = r11;
        r3 = r12;
        r1.<init>(r2, r3, r4, r6);	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        r0 = r7.moveToNext();	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        if (r0 == 0) goto L_0x006e;
    L_0x0061:
        r0 = r10.zzFm();	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        r0 = r0.zzFE();	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
        r2 = "Got multiple records for user property, expected one";
        r0.log(r2);	 Catch:{ SQLiteException -> 0x0097, all -> 0x0091 }
    L_0x006e:
        if (r7 == 0) goto L_0x0042;
    L_0x0070:
        r7.close();
        goto L_0x0042;
    L_0x0074:
        r0 = move-exception;
        r1 = r8;
    L_0x0076:
        r2 = r10.zzFm();	 Catch:{ all -> 0x0094 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x0094 }
        r3 = "Error querying user property";
        r2.zzd(r3, r11, r12, r0);	 Catch:{ all -> 0x0094 }
        if (r1 == 0) goto L_0x0088;
    L_0x0085:
        r1.close();
    L_0x0088:
        r1 = r8;
        goto L_0x0042;
    L_0x008a:
        r0 = move-exception;
    L_0x008b:
        if (r8 == 0) goto L_0x0090;
    L_0x008d:
        r8.close();
    L_0x0090:
        throw r0;
    L_0x0091:
        r0 = move-exception;
        r8 = r7;
        goto L_0x008b;
    L_0x0094:
        r0 = move-exception;
        r8 = r1;
        goto L_0x008b;
    L_0x0097:
        r0 = move-exception;
        r1 = r7;
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzQ(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzak");
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bb  */
    public java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzsn.zzb>> zzR(java.lang.String r12, java.lang.String r13) {
        /*
        r11 = this;
        r9 = 0;
        r11.zzma();
        r11.zzkN();
        com.google.android.gms.common.internal.zzaa.zzdl(r12);
        com.google.android.gms.common.internal.zzaa.zzdl(r13);
        r8 = new android.support.v4.util.ArrayMap;
        r8.<init>();
        r0 = r11.getWritableDatabase();
        r1 = "event_filters";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r3 = 0;
        r4 = "audience_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r3 = 1;
        r4 = "data";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r3 = "app_id=? AND event_name=?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r5 = 1;
        r4[r5] = r13;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        if (r10 != 0) goto L_0x004b;
    L_0x0037:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
    L_0x003b:
        r0 = r1.moveToFirst();	 Catch:{ SQLiteException -> 0x00a2 }
        if (r0 != 0) goto L_0x0052;
    L_0x0041:
        r0 = java.util.Collections.emptyMap();	 Catch:{ SQLiteException -> 0x00a2 }
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();
    L_0x004a:
        return r0;
    L_0x004b:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        goto L_0x003b;
    L_0x0052:
        r0 = 1;
        r0 = r1.getBlob(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = com.google.android.gms.internal.zzamb.zzN(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r2 = new com.google.android.gms.internal.zzsn$zzb;	 Catch:{ SQLiteException -> 0x00a2 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = r2.mergeFrom(r0);	 Catch:{ IOException -> 0x0093 }
        r0 = (com.google.android.gms.internal.zzsn.zzb) r0;	 Catch:{ IOException -> 0x0093 }
        r0 = 0;
        r3 = r1.getInt(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = r8.get(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = (java.util.List) r0;	 Catch:{ SQLiteException -> 0x00a2 }
        if (r0 != 0) goto L_0x0083;
    L_0x0077:
        r0 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00a2 }
        r0.<init>();	 Catch:{ SQLiteException -> 0x00a2 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x00a2 }
        r8.put(r3, r0);	 Catch:{ SQLiteException -> 0x00a2 }
    L_0x0083:
        r0.add(r2);	 Catch:{ SQLiteException -> 0x00a2 }
    L_0x0086:
        r0 = r1.moveToNext();	 Catch:{ SQLiteException -> 0x00a2 }
        if (r0 != 0) goto L_0x0052;
    L_0x008c:
        if (r1 == 0) goto L_0x0091;
    L_0x008e:
        r1.close();
    L_0x0091:
        r0 = r8;
        goto L_0x004a;
    L_0x0093:
        r0 = move-exception;
        r2 = r11.zzFm();	 Catch:{ SQLiteException -> 0x00a2 }
        r2 = r2.zzFE();	 Catch:{ SQLiteException -> 0x00a2 }
        r3 = "Failed to merge filter";
        r2.zze(r3, r12, r0);	 Catch:{ SQLiteException -> 0x00a2 }
        goto L_0x0086;
    L_0x00a2:
        r0 = move-exception;
    L_0x00a3:
        r2 = r11.zzFm();	 Catch:{ all -> 0x00bf }
        r2 = r2.zzFE();	 Catch:{ all -> 0x00bf }
        r3 = "Database error querying filters";
        r2.zzj(r3, r0);	 Catch:{ all -> 0x00bf }
        if (r1 == 0) goto L_0x00b5;
    L_0x00b2:
        r1.close();
    L_0x00b5:
        r0 = r9;
        goto L_0x004a;
    L_0x00b7:
        r0 = move-exception;
        r1 = r9;
    L_0x00b9:
        if (r1 == 0) goto L_0x00be;
    L_0x00bb:
        r1.close();
    L_0x00be:
        throw r0;
    L_0x00bf:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c1:
        r0 = move-exception;
        r1 = r9;
        goto L_0x00a3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzR(java.lang.String, java.lang.String):java.util.Map");
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bb  */
    public java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzsn.zze>> zzS(java.lang.String r12, java.lang.String r13) {
        /*
        r11 = this;
        r9 = 0;
        r11.zzma();
        r11.zzkN();
        com.google.android.gms.common.internal.zzaa.zzdl(r12);
        com.google.android.gms.common.internal.zzaa.zzdl(r13);
        r8 = new android.support.v4.util.ArrayMap;
        r8.<init>();
        r0 = r11.getWritableDatabase();
        r1 = "property_filters";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r3 = 0;
        r4 = "audience_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r3 = 1;
        r4 = "data";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r3 = "app_id=? AND property_name=?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r5 = 0;
        r4[r5] = r12;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r5 = 1;
        r4[r5] = r13;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r10 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        if (r10 != 0) goto L_0x004b;
    L_0x0037:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
    L_0x003b:
        r0 = r1.moveToFirst();	 Catch:{ SQLiteException -> 0x00a2 }
        if (r0 != 0) goto L_0x0052;
    L_0x0041:
        r0 = java.util.Collections.emptyMap();	 Catch:{ SQLiteException -> 0x00a2 }
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();
    L_0x004a:
        return r0;
    L_0x004b:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x00c1, all -> 0x00b7 }
        goto L_0x003b;
    L_0x0052:
        r0 = 1;
        r0 = r1.getBlob(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = com.google.android.gms.internal.zzamb.zzN(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r2 = new com.google.android.gms.internal.zzsn$zze;	 Catch:{ SQLiteException -> 0x00a2 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = r2.mergeFrom(r0);	 Catch:{ IOException -> 0x0093 }
        r0 = (com.google.android.gms.internal.zzsn.zze) r0;	 Catch:{ IOException -> 0x0093 }
        r0 = 0;
        r3 = r1.getInt(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = r8.get(r0);	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = (java.util.List) r0;	 Catch:{ SQLiteException -> 0x00a2 }
        if (r0 != 0) goto L_0x0083;
    L_0x0077:
        r0 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00a2 }
        r0.<init>();	 Catch:{ SQLiteException -> 0x00a2 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x00a2 }
        r8.put(r3, r0);	 Catch:{ SQLiteException -> 0x00a2 }
    L_0x0083:
        r0.add(r2);	 Catch:{ SQLiteException -> 0x00a2 }
    L_0x0086:
        r0 = r1.moveToNext();	 Catch:{ SQLiteException -> 0x00a2 }
        if (r0 != 0) goto L_0x0052;
    L_0x008c:
        if (r1 == 0) goto L_0x0091;
    L_0x008e:
        r1.close();
    L_0x0091:
        r0 = r8;
        goto L_0x004a;
    L_0x0093:
        r0 = move-exception;
        r2 = r11.zzFm();	 Catch:{ SQLiteException -> 0x00a2 }
        r2 = r2.zzFE();	 Catch:{ SQLiteException -> 0x00a2 }
        r3 = "Failed to merge filter";
        r2.zze(r3, r12, r0);	 Catch:{ SQLiteException -> 0x00a2 }
        goto L_0x0086;
    L_0x00a2:
        r0 = move-exception;
    L_0x00a3:
        r2 = r11.zzFm();	 Catch:{ all -> 0x00bf }
        r2 = r2.zzFE();	 Catch:{ all -> 0x00bf }
        r3 = "Database error querying filters";
        r2.zzj(r3, r0);	 Catch:{ all -> 0x00bf }
        if (r1 == 0) goto L_0x00b5;
    L_0x00b2:
        r1.close();
    L_0x00b5:
        r0 = r9;
        goto L_0x004a;
    L_0x00b7:
        r0 = move-exception;
        r1 = r9;
    L_0x00b9:
        if (r1 == 0) goto L_0x00be;
    L_0x00bb:
        r1.close();
    L_0x00be:
        throw r0;
    L_0x00bf:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c1:
        r0 = move-exception;
        r1 = r9;
        goto L_0x00a3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzS(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0157  */
    @android.support.annotation.WorkerThread
    public com.google.android.gms.measurement.internal.zze.zza zza(long r22, java.lang.String r24, boolean r25, boolean r26, boolean r27) {
        /*
        r21 = this;
        com.google.android.gms.common.internal.zzaa.zzdl(r24);
        r21.zzkN();
        r21.zzma();
        r2 = 1;
        r0 = new java.lang.String[r2];
        r20 = r0;
        r2 = 0;
        r20[r2] = r24;
        r18 = new com.google.android.gms.measurement.internal.zze$zza;
        r18.<init>();
        r19 = 0;
        r2 = r21.getWritableDatabase();	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r3 = "apps";
        r4 = 5;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r5 = 0;
        r6 = "day";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r5 = 1;
        r6 = "daily_events_count";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r5 = 2;
        r6 = "daily_public_events_count";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r5 = 3;
        r6 = "daily_conversions_count";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r5 = 4;
        r6 = "daily_error_events_count";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r5 = "app_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r7 = 0;
        r6[r7] = r24;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        if (r10 != 0) goto L_0x006a;
    L_0x0049:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
    L_0x004d:
        r4 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x013b }
        if (r4 != 0) goto L_0x007c;
    L_0x0053:
        r2 = r21.zzFm();	 Catch:{ SQLiteException -> 0x013b }
        r2 = r2.zzFG();	 Catch:{ SQLiteException -> 0x013b }
        r4 = "Not updating daily counts, app is not known";
        r0 = r24;
        r2.zzj(r4, r0);	 Catch:{ SQLiteException -> 0x013b }
        if (r3 == 0) goto L_0x0067;
    L_0x0064:
        r3.close();
    L_0x0067:
        r2 = r18;
    L_0x0069:
        return r2;
    L_0x006a:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        r10 = r0;
        r11 = r3;
        r12 = r4;
        r13 = r5;
        r14 = r6;
        r15 = r7;
        r16 = r8;
        r17 = r9;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ SQLiteException -> 0x015d, all -> 0x0152 }
        goto L_0x004d;
    L_0x007c:
        r4 = 0;
        r4 = r3.getLong(r4);	 Catch:{ SQLiteException -> 0x013b }
        r4 = (r4 > r22 ? 1 : (r4 == r22 ? 0 : -1));
        if (r4 != 0) goto L_0x00a9;
    L_0x0085:
        r4 = 1;
        r4 = r3.getLong(r4);	 Catch:{ SQLiteException -> 0x013b }
        r0 = r18;
        r0.zzbbY = r4;	 Catch:{ SQLiteException -> 0x013b }
        r4 = 2;
        r4 = r3.getLong(r4);	 Catch:{ SQLiteException -> 0x013b }
        r0 = r18;
        r0.zzbbX = r4;	 Catch:{ SQLiteException -> 0x013b }
        r4 = 3;
        r4 = r3.getLong(r4);	 Catch:{ SQLiteException -> 0x013b }
        r0 = r18;
        r0.zzbbZ = r4;	 Catch:{ SQLiteException -> 0x013b }
        r4 = 4;
        r4 = r3.getLong(r4);	 Catch:{ SQLiteException -> 0x013b }
        r0 = r18;
        r0.zzbca = r4;	 Catch:{ SQLiteException -> 0x013b }
    L_0x00a9:
        r0 = r18;
        r4 = r0.zzbbY;	 Catch:{ SQLiteException -> 0x013b }
        r6 = 1;
        r4 = r4 + r6;
        r0 = r18;
        r0.zzbbY = r4;	 Catch:{ SQLiteException -> 0x013b }
        if (r25 == 0) goto L_0x00c1;
    L_0x00b6:
        r0 = r18;
        r4 = r0.zzbbX;	 Catch:{ SQLiteException -> 0x013b }
        r6 = 1;
        r4 = r4 + r6;
        r0 = r18;
        r0.zzbbX = r4;	 Catch:{ SQLiteException -> 0x013b }
    L_0x00c1:
        if (r26 == 0) goto L_0x00ce;
    L_0x00c3:
        r0 = r18;
        r4 = r0.zzbbZ;	 Catch:{ SQLiteException -> 0x013b }
        r6 = 1;
        r4 = r4 + r6;
        r0 = r18;
        r0.zzbbZ = r4;	 Catch:{ SQLiteException -> 0x013b }
    L_0x00ce:
        if (r27 == 0) goto L_0x00db;
    L_0x00d0:
        r0 = r18;
        r4 = r0.zzbca;	 Catch:{ SQLiteException -> 0x013b }
        r6 = 1;
        r4 = r4 + r6;
        r0 = r18;
        r0.zzbca = r4;	 Catch:{ SQLiteException -> 0x013b }
    L_0x00db:
        r4 = new android.content.ContentValues;	 Catch:{ SQLiteException -> 0x013b }
        r4.<init>();	 Catch:{ SQLiteException -> 0x013b }
        r5 = "day";
        r6 = java.lang.Long.valueOf(r22);	 Catch:{ SQLiteException -> 0x013b }
        r4.put(r5, r6);	 Catch:{ SQLiteException -> 0x013b }
        r5 = "daily_public_events_count";
        r0 = r18;
        r6 = r0.zzbbX;	 Catch:{ SQLiteException -> 0x013b }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteException -> 0x013b }
        r4.put(r5, r6);	 Catch:{ SQLiteException -> 0x013b }
        r5 = "daily_events_count";
        r0 = r18;
        r6 = r0.zzbbY;	 Catch:{ SQLiteException -> 0x013b }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteException -> 0x013b }
        r4.put(r5, r6);	 Catch:{ SQLiteException -> 0x013b }
        r5 = "daily_conversions_count";
        r0 = r18;
        r6 = r0.zzbbZ;	 Catch:{ SQLiteException -> 0x013b }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteException -> 0x013b }
        r4.put(r5, r6);	 Catch:{ SQLiteException -> 0x013b }
        r5 = "daily_error_events_count";
        r0 = r18;
        r6 = r0.zzbca;	 Catch:{ SQLiteException -> 0x013b }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteException -> 0x013b }
        r4.put(r5, r6);	 Catch:{ SQLiteException -> 0x013b }
        r5 = "apps";
        r6 = "app_id=?";
        r7 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x013b }
        if (r7 != 0) goto L_0x0133;
    L_0x0125:
        r0 = r20;
        r2.update(r5, r4, r6, r0);	 Catch:{ SQLiteException -> 0x013b }
    L_0x012a:
        if (r3 == 0) goto L_0x012f;
    L_0x012c:
        r3.close();
    L_0x012f:
        r2 = r18;
        goto L_0x0069;
    L_0x0133:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ SQLiteException -> 0x013b }
        r0 = r20;
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.update(r2, r5, r4, r6, r0);	 Catch:{ SQLiteException -> 0x013b }
        goto L_0x012a;
    L_0x013b:
        r2 = move-exception;
    L_0x013c:
        r4 = r21.zzFm();	 Catch:{ all -> 0x015b }
        r4 = r4.zzFE();	 Catch:{ all -> 0x015b }
        r5 = "Error updating daily counts";
        r4.zzj(r5, r2);	 Catch:{ all -> 0x015b }
        if (r3 == 0) goto L_0x014e;
    L_0x014b:
        r3.close();
    L_0x014e:
        r2 = r18;
        goto L_0x0069;
    L_0x0152:
        r2 = move-exception;
        r3 = r19;
    L_0x0155:
        if (r3 == 0) goto L_0x015a;
    L_0x0157:
        r3.close();
    L_0x015a:
        throw r2;
    L_0x015b:
        r2 = move-exception;
        goto L_0x0155;
    L_0x015d:
        r2 = move-exception;
        r3 = r19;
        goto L_0x013c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zza(long, java.lang.String, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zze$zza");
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zza(ContentValues contentValues, String str, Object obj) {
        zzaa.zzdl(str);
        zzaa.zzz(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    public void zza(com.google.android.gms.internal.zzsp.zze zze) {
        zzkN();
        zzma();
        zzaa.zzz(zze);
        zzaa.zzdl(zze.appId);
        zzaa.zzz(zze.zzbhi);
        zzFq();
        long currentTimeMillis = zzlQ().currentTimeMillis();
        if (zze.zzbhi.longValue() < currentTimeMillis - zzFo().zzEO() || zze.zzbhi.longValue() > zzFo().zzEO() + currentTimeMillis) {
            zzFm().zzFG().zze("Storing bundle outside of the max uploading time span. now, timestamp", Long.valueOf(currentTimeMillis), zze.zzbhi);
        }
        try {
            byte[] bArr = new byte[zze.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zze.writeTo(zzO);
            zzO.zzWU();
            bArr = zzFi().zzh(bArr);
            zzFm().zzFL().zzj("Saving bundle, size", Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.appId);
            contentValues.put("bundle_end_timestamp", zze.zzbhi);
            contentValues.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, bArr);
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String str = "queue";
                if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insert(str, null, contentValues) : SQLiteInstrumentation.insert(writableDatabase, str, null, contentValues)) == -1) {
                    zzFm().zzFE().log("Failed to insert bundle (got -1)");
                }
            } catch (SQLiteException e) {
                zzFm().zzFE().zzj("Error storing bundle", e);
            }
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Data loss. Failed to serialize bundle", e2);
        }
    }

    @WorkerThread
    public void zza(zza zza) {
        zzaa.zzz(zza);
        zzkN();
        zzma();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zza.zziC());
        contentValues.put("app_instance_id", zza.zzvx());
        contentValues.put("gmp_app_id", zza.zzEb());
        contentValues.put("resettable_device_id_hash", zza.zzEc());
        contentValues.put("last_bundle_index", Long.valueOf(zza.zzEl()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zza.zzEe()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zza.zzEf()));
        contentValues.put("app_version", zza.zzkV());
        contentValues.put("app_store", zza.zzEh());
        contentValues.put("gmp_version", Long.valueOf(zza.zzEi()));
        contentValues.put("dev_cert_hash", Long.valueOf(zza.zzEj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zza.zzEk()));
        contentValues.put("day", Long.valueOf(zza.zzEp()));
        contentValues.put("daily_public_events_count", Long.valueOf(zza.zzEq()));
        contentValues.put("daily_events_count", Long.valueOf(zza.zzEr()));
        contentValues.put("daily_conversions_count", Long.valueOf(zza.zzEs()));
        contentValues.put("config_fetched_time", Long.valueOf(zza.zzEm()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zza.zzEn()));
        contentValues.put("app_version_int", Long.valueOf(zza.zzEg()));
        contentValues.put("firebase_instance_id", zza.zzEd());
        contentValues.put("daily_error_events_count", Long.valueOf(zza.zzEt()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str = "apps";
            if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str, null, contentValues, 5)) == -1) {
                zzFm().zzFE().log("Failed to insert/update app (got -1)");
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zzj("Error storing app", e);
        }
    }

    public void zza(zzh zzh, long j) {
        zzkN();
        zzma();
        zzaa.zzz(zzh);
        zzaa.zzdl(zzh.zzPx);
        com.google.android.gms.internal.zzsp.zzb zzb = new com.google.android.gms.internal.zzsp.zzb();
        zzb.zzbgY = Long.valueOf(zzh.zzbci);
        zzb.zzbgW = new com.google.android.gms.internal.zzsp.zzc[zzh.zzbcj.size()];
        Iterator it = zzh.zzbcj.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            com.google.android.gms.internal.zzsp.zzc zzc = new com.google.android.gms.internal.zzsp.zzc();
            int i2 = i + 1;
            zzb.zzbgW[i] = zzc;
            zzc.name = str;
            zzFi().zza(zzc, zzh.zzbcj.get(str));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzb.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zzb.writeTo(zzO);
            zzO.zzWU();
            zzFm().zzFL().zze("Saving event, name, data size", zzh.mName, Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzh.zzPx);
            contentValues.put("name", zzh.mName);
            contentValues.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, Long.valueOf(zzh.zzajg));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, bArr);
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String str2 = "raw_events";
                if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insert(str2, null, contentValues) : SQLiteInstrumentation.insert(writableDatabase, str2, null, contentValues)) == -1) {
                    zzFm().zzFE().log("Failed to insert raw event (got -1)");
                }
            } catch (SQLiteException e) {
                zzFm().zzFE().zzj("Error storing raw event", e);
            }
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Data loss. Failed to serialize event params/data", e2);
        }
    }

    @WorkerThread
    public void zza(zzi zzi) {
        zzaa.zzz(zzi);
        zzkN();
        zzma();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzi.zzPx);
        contentValues.put("name", zzi.mName);
        contentValues.put("lifetime_count", Long.valueOf(zzi.zzbck));
        contentValues.put("current_bundle_count", Long.valueOf(zzi.zzbcl));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzi.zzbcm));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str = "events";
            if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str, null, contentValues, 5)) == -1) {
                zzFm().zzFE().log("Failed to insert/update event aggregates (got -1)");
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zzj("Error storing event aggregates", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(String str, int i, zzf zzf) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzaa.zzz(zzf);
        try {
            byte[] bArr = new byte[zzf.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zzf.writeTo(zzO);
            zzO.zzWU();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("current_results", bArr);
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String str2 = "audience_filter_values";
                if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str2, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str2, null, contentValues, 5)) == -1) {
                    zzFm().zzFE().log("Failed to insert filter results (got -1)");
                }
            } catch (SQLiteException e) {
                zzFm().zzFE().zzj("Error storing filter results", e);
            }
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Configuration loss. Failed to serialize filter results", e2);
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:57:0x0131=Splitter:B:57:0x0131, B:7:0x0028=Splitter:B:7:0x0028, B:32:0x009a=Splitter:B:32:0x009a} */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01da  */
    public void zza(java.lang.String r23, long r24, com.google.android.gms.measurement.internal.zze.zzb r26) {
        /*
        r22 = this;
        com.google.android.gms.common.internal.zzaa.zzz(r26);
        r22.zzkN();
        r22.zzma();
        r4 = 0;
        r2 = r22.getWritableDatabase();	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = android.text.TextUtils.isEmpty(r23);	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        if (r3 == 0) goto L_0x008a;
    L_0x0014:
        r5 = "select app_id, metadata_fingerprint from raw_events where app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;";
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = 0;
        r7 = java.lang.String.valueOf(r24);	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r6[r3] = r7;	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        if (r3 != 0) goto L_0x0034;
    L_0x0024:
        r3 = r2.rawQuery(r5, r6);	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
    L_0x0028:
        r4 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x01e7 }
        if (r4 != 0) goto L_0x003d;
    L_0x002e:
        if (r3 == 0) goto L_0x0033;
    L_0x0030:
        r3.close();
    L_0x0033:
        return;
    L_0x0034:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = r0;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r3, r5, r6);	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        goto L_0x0028;
    L_0x003d:
        r4 = 0;
        r23 = r3.getString(r4);	 Catch:{ SQLiteException -> 0x01e7 }
        r4 = 1;
        r4 = r3.getString(r4);	 Catch:{ SQLiteException -> 0x01e7 }
        r3.close();	 Catch:{ SQLiteException -> 0x01e7 }
        r21 = r4;
        r20 = r3;
    L_0x004e:
        r3 = "raw_events_metadata";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        r5 = 0;
        r6 = "metadata";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        r5 = "app_id=? and metadata_fingerprint=?";
        r6 = 2;
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        r7 = 0;
        r6[r7] = r23;	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        r7 = 1;
        r6[r7] = r21;	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        r7 = 0;
        r8 = 0;
        r9 = "rowid";
        r10 = "2";
        r11 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        if (r11 != 0) goto L_0x00bc;
    L_0x006d:
        r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
    L_0x0071:
        r3 = r11.moveToFirst();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        if (r3 != 0) goto L_0x00d1;
    L_0x0077:
        r2 = r22.zzFm();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r2 = r2.zzFE();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = "Raw event metadata record is missing";
        r2.log(r3);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        if (r11 == 0) goto L_0x0033;
    L_0x0086:
        r11.close();
        goto L_0x0033;
    L_0x008a:
        r5 = "select metadata_fingerprint from raw_events where app_id = ? order by rowid limit 1;";
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = 0;
        r6[r3] = r23;	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        if (r3 != 0) goto L_0x00a6;
    L_0x0096:
        r3 = r2.rawQuery(r5, r6);	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
    L_0x009a:
        r4 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x01e7 }
        if (r4 != 0) goto L_0x00af;
    L_0x00a0:
        if (r3 == 0) goto L_0x0033;
    L_0x00a2:
        r3.close();
        goto L_0x0033;
    L_0x00a6:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        r3 = r0;
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r3, r5, r6);	 Catch:{ SQLiteException -> 0x01c0, all -> 0x01d6 }
        goto L_0x009a;
    L_0x00af:
        r4 = 0;
        r4 = r3.getString(r4);	 Catch:{ SQLiteException -> 0x01e7 }
        r3.close();	 Catch:{ SQLiteException -> 0x01e7 }
        r21 = r4;
        r20 = r3;
        goto L_0x004e;
    L_0x00bc:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        r11 = r0;
        r12 = r3;
        r13 = r4;
        r14 = r5;
        r15 = r6;
        r16 = r7;
        r17 = r8;
        r18 = r9;
        r19 = r10;
        r11 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r11, r12, r13, r14, r15, r16, r17, r18, r19);	 Catch:{ SQLiteException -> 0x01e9, all -> 0x01e1 }
        goto L_0x0071;
    L_0x00d1:
        r3 = 0;
        r3 = r11.getBlob(r3);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = com.google.android.gms.internal.zzamb.zzN(r3);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r4 = new com.google.android.gms.internal.zzsp$zze;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r4.<init>();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = r4.mergeFrom(r3);	 Catch:{ IOException -> 0x014b }
        r3 = (com.google.android.gms.internal.zzsp.zze) r3;	 Catch:{ IOException -> 0x014b }
        r3 = r11.moveToNext();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        if (r3 == 0) goto L_0x00f8;
    L_0x00eb:
        r3 = r22.zzFm();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = r3.zzFG();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r5 = "Get multiple raw event metadata records, expected one";
        r3.log(r5);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
    L_0x00f8:
        r11.close();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r0 = r26;
        r0.zzc(r4);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = "raw_events";
        r4 = 4;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r5 = 0;
        r6 = "rowid";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r5 = 1;
        r6 = "name";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r5 = 2;
        r6 = "timestamp";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r5 = 3;
        r6 = "data";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r5 = "app_id=? and metadata_fingerprint=?";
        r6 = 2;
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r7 = 0;
        r6[r7] = r23;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r7 = 1;
        r6[r7] = r21;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r7 = 0;
        r8 = 0;
        r9 = "rowid";
        r10 = 0;
        r12 = r2 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        if (r12 != 0) goto L_0x0162;
    L_0x012d:
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
    L_0x0131:
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x01e7 }
        if (r2 != 0) goto L_0x0169;
    L_0x0137:
        r2 = r22.zzFm();	 Catch:{ SQLiteException -> 0x01e7 }
        r2 = r2.zzFG();	 Catch:{ SQLiteException -> 0x01e7 }
        r4 = "Raw event data disappeared while in transaction";
        r2.log(r4);	 Catch:{ SQLiteException -> 0x01e7 }
        if (r3 == 0) goto L_0x0033;
    L_0x0146:
        r3.close();
        goto L_0x0033;
    L_0x014b:
        r2 = move-exception;
        r3 = r22.zzFm();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = r3.zzFE();	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r4 = "Data loss. Failed to merge raw event metadata";
        r0 = r23;
        r3.zze(r4, r0, r2);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        if (r11 == 0) goto L_0x0033;
    L_0x015d:
        r11.close();
        goto L_0x0033;
    L_0x0162:
        r2 = (android.database.sqlite.SQLiteDatabase) r2;	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        r3 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x01ed, all -> 0x01e5 }
        goto L_0x0131;
    L_0x0169:
        r2 = 0;
        r4 = r3.getLong(r2);	 Catch:{ SQLiteException -> 0x01e7 }
        r2 = 3;
        r2 = r3.getBlob(r2);	 Catch:{ SQLiteException -> 0x01e7 }
        r2 = com.google.android.gms.internal.zzamb.zzN(r2);	 Catch:{ SQLiteException -> 0x01e7 }
        r6 = new com.google.android.gms.internal.zzsp$zzb;	 Catch:{ SQLiteException -> 0x01e7 }
        r6.<init>();	 Catch:{ SQLiteException -> 0x01e7 }
        r2 = r6.mergeFrom(r2);	 Catch:{ IOException -> 0x01a3 }
        r2 = (com.google.android.gms.internal.zzsp.zzb) r2;	 Catch:{ IOException -> 0x01a3 }
        r2 = 1;
        r2 = r3.getString(r2);	 Catch:{ SQLiteException -> 0x01e7 }
        r6.name = r2;	 Catch:{ SQLiteException -> 0x01e7 }
        r2 = 2;
        r8 = r3.getLong(r2);	 Catch:{ SQLiteException -> 0x01e7 }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x01e7 }
        r6.zzbgX = r2;	 Catch:{ SQLiteException -> 0x01e7 }
        r0 = r26;
        r2 = r0.zza(r4, r6);	 Catch:{ SQLiteException -> 0x01e7 }
        if (r2 != 0) goto L_0x01b3;
    L_0x019c:
        if (r3 == 0) goto L_0x0033;
    L_0x019e:
        r3.close();
        goto L_0x0033;
    L_0x01a3:
        r2 = move-exception;
        r4 = r22.zzFm();	 Catch:{ SQLiteException -> 0x01e7 }
        r4 = r4.zzFE();	 Catch:{ SQLiteException -> 0x01e7 }
        r5 = "Data loss. Failed to merge raw event";
        r0 = r23;
        r4.zze(r5, r0, r2);	 Catch:{ SQLiteException -> 0x01e7 }
    L_0x01b3:
        r2 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x01e7 }
        if (r2 != 0) goto L_0x0169;
    L_0x01b9:
        if (r3 == 0) goto L_0x0033;
    L_0x01bb:
        r3.close();
        goto L_0x0033;
    L_0x01c0:
        r2 = move-exception;
        r3 = r4;
    L_0x01c2:
        r4 = r22.zzFm();	 Catch:{ all -> 0x01de }
        r4 = r4.zzFE();	 Catch:{ all -> 0x01de }
        r5 = "Data loss. Error selecting raw event";
        r4.zzj(r5, r2);	 Catch:{ all -> 0x01de }
        if (r3 == 0) goto L_0x0033;
    L_0x01d1:
        r3.close();
        goto L_0x0033;
    L_0x01d6:
        r2 = move-exception;
        r11 = r4;
    L_0x01d8:
        if (r11 == 0) goto L_0x01dd;
    L_0x01da:
        r11.close();
    L_0x01dd:
        throw r2;
    L_0x01de:
        r2 = move-exception;
        r11 = r3;
        goto L_0x01d8;
    L_0x01e1:
        r2 = move-exception;
        r11 = r20;
        goto L_0x01d8;
    L_0x01e5:
        r2 = move-exception;
        goto L_0x01d8;
    L_0x01e7:
        r2 = move-exception;
        goto L_0x01c2;
    L_0x01e9:
        r2 = move-exception;
        r3 = r20;
        goto L_0x01c2;
    L_0x01ed:
        r2 = move-exception;
        r3 = r11;
        goto L_0x01c2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zza(java.lang.String, long, com.google.android.gms.measurement.internal.zze$zzb):void");
    }

    @WorkerThread
    public boolean zza(zzak zzak) {
        zzaa.zzz(zzak);
        zzkN();
        zzma();
        if (zzQ(zzak.zzPx, zzak.mName) == null) {
            if (zzal.zzfG(zzak.mName)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzak.zzPx}) >= ((long) zzFo().zzEH())) {
                    return false;
                }
            }
            if (zzb("select count(1) from user_attributes where app_id=?", new String[]{zzak.zzPx}) >= ((long) zzFo().zzEI())) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzak.zzPx);
        contentValues.put("name", zzak.mName);
        contentValues.put("set_timestamp", Long.valueOf(zzak.zzbgg));
        zza(contentValues, "value", zzak.zzRF);
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str = "user_attributes";
            if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insertWithOnConflict(str, null, contentValues, 5) : SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str, null, contentValues, 5)) == -1) {
                zzFm().zzFE().log("Failed to insert/update user property (got -1)");
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zzj("Error storing user property", e);
        }
        return true;
    }

    @WorkerThread
    public void zzae(long j) {
        zzkN();
        zzma();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = new String[]{String.valueOf(j)};
        String str = "queue";
        String str2 = "rowid=?";
        if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str, str2, strArr) : SQLiteInstrumentation.delete(writableDatabase, str, str2, strArr)) != 1) {
            zzFm().zzFE().log("Deleted fewer rows from queue than expected");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0065  */
    public java.lang.String zzaf(long r8) {
        /*
        r7 = this;
        r1 = 0;
        r7.zzkN();
        r7.zzma();
        r0 = r7.getWritableDatabase();	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        r4 = 0;
        r5 = java.lang.String.valueOf(r8);	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        r3[r4] = r5;	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        r4 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        if (r4 != 0) goto L_0x0039;
    L_0x001b:
        r2 = r0.rawQuery(r2, r3);	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
    L_0x001f:
        r0 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x006b }
        if (r0 != 0) goto L_0x0040;
    L_0x0025:
        r0 = r7.zzFm();	 Catch:{ SQLiteException -> 0x006b }
        r0 = r0.zzFL();	 Catch:{ SQLiteException -> 0x006b }
        r3 = "No expired configs for apps with pending events";
        r0.log(r3);	 Catch:{ SQLiteException -> 0x006b }
        if (r2 == 0) goto L_0x0037;
    L_0x0034:
        r2.close();
    L_0x0037:
        r0 = r1;
    L_0x0038:
        return r0;
    L_0x0039:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r0, r2, r3);	 Catch:{ SQLiteException -> 0x004b, all -> 0x0061 }
        goto L_0x001f;
    L_0x0040:
        r0 = 0;
        r0 = r2.getString(r0);	 Catch:{ SQLiteException -> 0x006b }
        if (r2 == 0) goto L_0x0038;
    L_0x0047:
        r2.close();
        goto L_0x0038;
    L_0x004b:
        r0 = move-exception;
        r2 = r1;
    L_0x004d:
        r3 = r7.zzFm();	 Catch:{ all -> 0x0069 }
        r3 = r3.zzFE();	 Catch:{ all -> 0x0069 }
        r4 = "Error selecting expired configs";
        r3.zzj(r4, r0);	 Catch:{ all -> 0x0069 }
        if (r2 == 0) goto L_0x005f;
    L_0x005c:
        r2.close();
    L_0x005f:
        r0 = r1;
        goto L_0x0038;
    L_0x0061:
        r0 = move-exception;
        r2 = r1;
    L_0x0063:
        if (r2 == 0) goto L_0x0068;
    L_0x0065:
        r2.close();
    L_0x0068:
        throw r0;
    L_0x0069:
        r0 = move-exception;
        goto L_0x0063;
    L_0x006b:
        r0 = move-exception;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzaf(long):java.lang.String");
    }

    public long zzb(com.google.android.gms.internal.zzsp.zze zze) throws IOException {
        zzkN();
        zzma();
        zzaa.zzz(zze);
        zzaa.zzdl(zze.appId);
        try {
            byte[] bArr = new byte[zze.getSerializedSize()];
            zzamc zzO = zzamc.zzO(bArr);
            zze.writeTo(zzO);
            zzO.zzWU();
            long zzt = zzFi().zzt(bArr);
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.appId);
            contentValues.put("metadata_fingerprint", Long.valueOf(zzt));
            contentValues.put("metadata", bArr);
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String str = "raw_events_metadata";
                if (writableDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.insertWithOnConflict(writableDatabase, str, null, contentValues, 4);
                } else {
                    writableDatabase.insertWithOnConflict(str, null, contentValues, 4);
                }
                return zzt;
            } catch (SQLiteException e) {
                zzFm().zzFE().zzj("Error storing raw event metadata", e);
                throw e;
            }
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Data loss. Failed to serialize event metadata", e2);
            throw e2;
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public Object zzb(Cursor cursor, int i) {
        int zza = zza(cursor, i);
        switch (zza) {
            case 0:
                zzFm().zzFE().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzFm().zzFE().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzFm().zzFE().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(zza));
                return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzb(String str, com.google.android.gms.internal.zzsn.zza[] zzaArr) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        zzaa.zzz(zzaArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzfn(str);
            for (com.google.android.gms.internal.zzsn.zza zza : zzaArr) {
                zza(str, zza);
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public void zzd(String str, byte[] bArr) {
        zzaa.zzdl(str);
        zzkN();
        zzma();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str2 = "apps";
            String str3 = "app_id = ?";
            String[] strArr = new String[]{str};
            if (((long) (!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.update(str2, contentValues, str3, strArr) : SQLiteInstrumentation.update(writableDatabase, str2, contentValues, str3, strArr))) == 0) {
                zzFm().zzFE().log("Failed to update remote config (got 0)");
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zzj("Error storing remote config", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a9  */
    @android.support.annotation.WorkerThread
    public java.util.List<com.google.android.gms.measurement.internal.zzak> zzfj(java.lang.String r13) {
        /*
        r12 = this;
        r10 = 0;
        com.google.android.gms.common.internal.zzaa.zzdl(r13);
        r12.zzkN();
        r12.zzma();
        r9 = new java.util.ArrayList;
        r9.<init>();
        r0 = r12.getWritableDatabase();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r1 = "user_attributes";
        r2 = 3;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r3 = 0;
        r4 = "name";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r3 = 1;
        r4 = "set_timestamp";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r3 = 2;
        r4 = "value";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r3 = "app_id=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r5 = 0;
        r6 = 0;
        r7 = "rowid";
        r8 = r12.zzFo();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r8 = r8.zzEI();	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r8 = java.lang.String.valueOf(r8);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        if (r11 != 0) goto L_0x0054;
    L_0x0043:
        r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
    L_0x0047:
        r0 = r7.moveToFirst();	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        if (r0 != 0) goto L_0x005b;
    L_0x004d:
        if (r7 == 0) goto L_0x0052;
    L_0x004f:
        r7.close();
    L_0x0052:
        r0 = r9;
    L_0x0053:
        return r0;
    L_0x0054:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        r7 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00b3, all -> 0x00a6 }
        goto L_0x0047;
    L_0x005b:
        r0 = 0;
        r3 = r7.getString(r0);	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        r0 = 1;
        r4 = r7.getLong(r0);	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        r0 = 2;
        r6 = r12.zzb(r7, r0);	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        if (r6 != 0) goto L_0x0086;
    L_0x006c:
        r0 = r12.zzFm();	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        r0 = r0.zzFE();	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        r1 = "Read invalid user property value, ignoring it";
        r0.log(r1);	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
    L_0x0079:
        r0 = r7.moveToNext();	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        if (r0 != 0) goto L_0x005b;
    L_0x007f:
        if (r7 == 0) goto L_0x0084;
    L_0x0081:
        r7.close();
    L_0x0084:
        r0 = r9;
        goto L_0x0053;
    L_0x0086:
        r1 = new com.google.android.gms.measurement.internal.zzak;	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        r2 = r13;
        r1.<init>(r2, r3, r4, r6);	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        r9.add(r1);	 Catch:{ SQLiteException -> 0x0090, all -> 0x00ad }
        goto L_0x0079;
    L_0x0090:
        r0 = move-exception;
        r1 = r7;
    L_0x0092:
        r2 = r12.zzFm();	 Catch:{ all -> 0x00b0 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x00b0 }
        r3 = "Error querying user properties";
        r2.zze(r3, r13, r0);	 Catch:{ all -> 0x00b0 }
        if (r1 == 0) goto L_0x00a4;
    L_0x00a1:
        r1.close();
    L_0x00a4:
        r0 = r10;
        goto L_0x0053;
    L_0x00a6:
        r0 = move-exception;
    L_0x00a7:
        if (r10 == 0) goto L_0x00ac;
    L_0x00a9:
        r10.close();
    L_0x00ac:
        throw r0;
    L_0x00ad:
        r0 = move-exception;
        r10 = r7;
        goto L_0x00a7;
    L_0x00b0:
        r0 = move-exception;
        r10 = r1;
        goto L_0x00a7;
    L_0x00b3:
        r0 = move-exception;
        r1 = r10;
        goto L_0x0092;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzfj(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x01b4  */
    @android.support.annotation.WorkerThread
    public com.google.android.gms.measurement.internal.zza zzfk(java.lang.String r13) {
        /*
        r12 = this;
        r10 = 0;
        r9 = 1;
        r8 = 0;
        com.google.android.gms.common.internal.zzaa.zzdl(r13);
        r12.zzkN();
        r12.zzma();
        r0 = r12.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r1 = "apps";
        r2 = 20;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 0;
        r4 = "app_instance_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 1;
        r4 = "gmp_app_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 2;
        r4 = "resettable_device_id_hash";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 3;
        r4 = "last_bundle_index";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 4;
        r4 = "last_bundle_start_timestamp";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 5;
        r4 = "last_bundle_end_timestamp";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 6;
        r4 = "app_version";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 7;
        r4 = "app_store";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 8;
        r4 = "gmp_version";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 9;
        r4 = "dev_cert_hash";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 10;
        r4 = "measurement_enabled";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 11;
        r4 = "day";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 12;
        r4 = "daily_public_events_count";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 13;
        r4 = "daily_events_count";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 14;
        r4 = "daily_conversions_count";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 15;
        r4 = "config_fetched_time";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 16;
        r4 = "failed_config_fetch_time";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 17;
        r4 = "app_version_int";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 18;
        r4 = "firebase_instance_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = 19;
        r4 = "daily_error_events_count";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r3 = "app_id=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        if (r11 != 0) goto L_0x00a6;
    L_0x0095:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
    L_0x0099:
        r0 = r1.moveToFirst();	 Catch:{ SQLiteException -> 0x01ba }
        if (r0 != 0) goto L_0x00ad;
    L_0x009f:
        if (r1 == 0) goto L_0x00a4;
    L_0x00a1:
        r1.close();
    L_0x00a4:
        r0 = r8;
    L_0x00a5:
        return r0;
    L_0x00a6:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x0199, all -> 0x01b0 }
        goto L_0x0099;
    L_0x00ad:
        r0 = new com.google.android.gms.measurement.internal.zza;	 Catch:{ SQLiteException -> 0x01ba }
        r2 = r12.zzbbl;	 Catch:{ SQLiteException -> 0x01ba }
        r0.<init>(r2, r13);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 0;
        r2 = r1.getString(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzeV(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 1;
        r2 = r1.getString(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzeW(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 2;
        r2 = r1.getString(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzeX(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 3;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzW(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 4;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzR(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 5;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzS(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 6;
        r2 = r1.getString(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.setAppVersion(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 7;
        r2 = r1.getString(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzeZ(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 8;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzU(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 9;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzV(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 10;
        r2 = r1.isNull(r2);	 Catch:{ SQLiteException -> 0x01ba }
        if (r2 == 0) goto L_0x0188;
    L_0x010e:
        r2 = r9;
    L_0x010f:
        if (r2 == 0) goto L_0x018f;
    L_0x0111:
        r2 = r9;
    L_0x0112:
        r0.setMeasurementEnabled(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 11;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzZ(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 12;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzaa(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 13;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzab(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 14;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzac(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 15;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzX(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 16;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzY(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 17;
        r2 = r1.isNull(r2);	 Catch:{ SQLiteException -> 0x01ba }
        if (r2 == 0) goto L_0x0191;
    L_0x0153:
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x0156:
        r0.zzT(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 18;
        r2 = r1.getString(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzeY(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = 19;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzad(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r0.zzEa();	 Catch:{ SQLiteException -> 0x01ba }
        r2 = r1.moveToNext();	 Catch:{ SQLiteException -> 0x01ba }
        if (r2 == 0) goto L_0x0181;
    L_0x0174:
        r2 = r12.zzFm();	 Catch:{ SQLiteException -> 0x01ba }
        r2 = r2.zzFE();	 Catch:{ SQLiteException -> 0x01ba }
        r3 = "Got multiple records for app, expected one";
        r2.log(r3);	 Catch:{ SQLiteException -> 0x01ba }
    L_0x0181:
        if (r1 == 0) goto L_0x00a5;
    L_0x0183:
        r1.close();
        goto L_0x00a5;
    L_0x0188:
        r2 = 10;
        r2 = r1.getInt(r2);	 Catch:{ SQLiteException -> 0x01ba }
        goto L_0x010f;
    L_0x018f:
        r2 = r10;
        goto L_0x0112;
    L_0x0191:
        r2 = 17;
        r2 = r1.getInt(r2);	 Catch:{ SQLiteException -> 0x01ba }
        r2 = (long) r2;
        goto L_0x0156;
    L_0x0199:
        r0 = move-exception;
        r1 = r8;
    L_0x019b:
        r2 = r12.zzFm();	 Catch:{ all -> 0x01b8 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x01b8 }
        r3 = "Error querying app";
        r2.zze(r3, r13, r0);	 Catch:{ all -> 0x01b8 }
        if (r1 == 0) goto L_0x01ad;
    L_0x01aa:
        r1.close();
    L_0x01ad:
        r0 = r8;
        goto L_0x00a5;
    L_0x01b0:
        r0 = move-exception;
        r1 = r8;
    L_0x01b2:
        if (r1 == 0) goto L_0x01b7;
    L_0x01b4:
        r1.close();
    L_0x01b7:
        throw r0;
    L_0x01b8:
        r0 = move-exception;
        goto L_0x01b2;
    L_0x01ba:
        r0 = move-exception;
        goto L_0x019b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzfk(java.lang.String):com.google.android.gms.measurement.internal.zza");
    }

    public long zzfl(String str) {
        zzaa.zzdl(str);
        zzkN();
        zzma();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String valueOf = String.valueOf(zzFo().zzfi(str));
            String str2 = "raw_events";
            String str3 = "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)";
            String[] strArr = new String[]{str, valueOf};
            return (long) (!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str2, str3, strArr) : SQLiteInstrumentation.delete(writableDatabase, str2, str3, strArr));
        } catch (SQLiteException e) {
            zzFm().zzFE().zzj("Error deleting over the limit events", e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0076  */
    @android.support.annotation.WorkerThread
    public byte[] zzfm(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 0;
        com.google.android.gms.common.internal.zzaa.zzdl(r11);
        r10.zzkN();
        r10.zzma();
        r0 = r10.getWritableDatabase();	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        r1 = "apps";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        r3 = 0;
        r4 = "remote_config";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        r3 = "app_id=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        r5 = 0;
        r4[r5] = r11;	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        if (r9 != 0) goto L_0x0038;
    L_0x0027:
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
    L_0x002b:
        r0 = r1.moveToFirst();	 Catch:{ SQLiteException -> 0x007d }
        if (r0 != 0) goto L_0x003f;
    L_0x0031:
        if (r1 == 0) goto L_0x0036;
    L_0x0033:
        r1.close();
    L_0x0036:
        r0 = r8;
    L_0x0037:
        return r0;
    L_0x0038:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        r1 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x005d, all -> 0x0073 }
        goto L_0x002b;
    L_0x003f:
        r0 = 0;
        r0 = r1.getBlob(r0);	 Catch:{ SQLiteException -> 0x007d }
        r2 = r1.moveToNext();	 Catch:{ SQLiteException -> 0x007d }
        if (r2 == 0) goto L_0x0057;
    L_0x004a:
        r2 = r10.zzFm();	 Catch:{ SQLiteException -> 0x007d }
        r2 = r2.zzFE();	 Catch:{ SQLiteException -> 0x007d }
        r3 = "Got multiple records for app config, expected one";
        r2.log(r3);	 Catch:{ SQLiteException -> 0x007d }
    L_0x0057:
        if (r1 == 0) goto L_0x0037;
    L_0x0059:
        r1.close();
        goto L_0x0037;
    L_0x005d:
        r0 = move-exception;
        r1 = r8;
    L_0x005f:
        r2 = r10.zzFm();	 Catch:{ all -> 0x007a }
        r2 = r2.zzFE();	 Catch:{ all -> 0x007a }
        r3 = "Error querying remote config";
        r2.zze(r3, r11, r0);	 Catch:{ all -> 0x007a }
        if (r1 == 0) goto L_0x0071;
    L_0x006e:
        r1.close();
    L_0x0071:
        r0 = r8;
        goto L_0x0037;
    L_0x0073:
        r0 = move-exception;
    L_0x0074:
        if (r8 == 0) goto L_0x0079;
    L_0x0076:
        r8.close();
    L_0x0079:
        throw r0;
    L_0x007a:
        r0 = move-exception;
        r8 = r1;
        goto L_0x0074;
    L_0x007d:
        r0 = move-exception;
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzfm(java.lang.String):byte[]");
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzfn(String str) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str2 = "property_filters";
        String str3 = "app_id=?";
        String[] strArr = new String[]{str};
        if (writableDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(writableDatabase, str2, str3, strArr);
        } else {
            writableDatabase.delete(str2, str3, strArr);
        }
        String str4 = "event_filters";
        str2 = "app_id=?";
        String[] strArr2 = new String[]{str};
        if (writableDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(writableDatabase, str4, str2, strArr2);
        } else {
            writableDatabase.delete(str4, str2, strArr2);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a3  */
    public java.util.Map<java.lang.Integer, com.google.android.gms.internal.zzsp.zzf> zzfo(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 0;
        r10.zzma();
        r10.zzkN();
        com.google.android.gms.common.internal.zzaa.zzdl(r11);
        r0 = r10.getWritableDatabase();
        r1 = "audience_filter_values";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        r3 = 0;
        r4 = "audience_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        r3 = 1;
        r4 = "current_results";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        r3 = "app_id=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        r5 = 0;
        r4[r5] = r11;	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        if (r9 != 0) goto L_0x003d;
    L_0x002c:
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
    L_0x0030:
        r0 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        if (r0 != 0) goto L_0x0044;
    L_0x0036:
        if (r2 == 0) goto L_0x003b;
    L_0x0038:
        r2.close();
    L_0x003b:
        r0 = r8;
    L_0x003c:
        return r0;
    L_0x003d:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x00ac, all -> 0x009f }
        goto L_0x0030;
    L_0x0044:
        r1 = new android.support.v4.util.ArrayMap;	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r1.<init>();	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
    L_0x0049:
        r0 = 0;
        r3 = r2.getInt(r0);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r0 = 1;
        r0 = r2.getBlob(r0);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r0 = com.google.android.gms.internal.zzamb.zzN(r0);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r4 = new com.google.android.gms.internal.zzsp$zzf;	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r4.<init>();	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r0 = r4.mergeFrom(r0);	 Catch:{ IOException -> 0x0076 }
        r0 = (com.google.android.gms.internal.zzsp.zzf) r0;	 Catch:{ IOException -> 0x0076 }
        r0 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r1.put(r0, r4);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
    L_0x0069:
        r0 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        if (r0 != 0) goto L_0x0049;
    L_0x006f:
        if (r2 == 0) goto L_0x0074;
    L_0x0071:
        r2.close();
    L_0x0074:
        r0 = r1;
        goto L_0x003c;
    L_0x0076:
        r0 = move-exception;
        r4 = r10.zzFm();	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r4 = r4.zzFE();	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r5 = "Failed to merge filter results. appId, audienceId, error";
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        r4.zzd(r5, r11, r3, r0);	 Catch:{ SQLiteException -> 0x0089, all -> 0x00a7 }
        goto L_0x0069;
    L_0x0089:
        r0 = move-exception;
        r1 = r2;
    L_0x008b:
        r2 = r10.zzFm();	 Catch:{ all -> 0x00a9 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x00a9 }
        r3 = "Database error querying filter results";
        r2.zzj(r3, r0);	 Catch:{ all -> 0x00a9 }
        if (r1 == 0) goto L_0x009d;
    L_0x009a:
        r1.close();
    L_0x009d:
        r0 = r8;
        goto L_0x003c;
    L_0x009f:
        r0 = move-exception;
        r2 = r8;
    L_0x00a1:
        if (r2 == 0) goto L_0x00a6;
    L_0x00a3:
        r2.close();
    L_0x00a6:
        throw r0;
    L_0x00a7:
        r0 = move-exception;
        goto L_0x00a1;
    L_0x00a9:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a1;
    L_0x00ac:
        r0 = move-exception;
        r1 = r8;
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzfo(java.lang.String):java.util.Map");
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzfp(String str) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = new String[]{str};
            String str2 = "events";
            String str3 = "app_id=?";
            int delete = (!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str2, str3, strArr) : SQLiteInstrumentation.delete(writableDatabase, str2, str3, strArr)) + 0;
            str3 = "user_attributes";
            String str4 = "app_id=?";
            delete += !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr);
            str3 = "apps";
            str4 = "app_id=?";
            delete += !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr);
            str3 = "raw_events";
            str4 = "app_id=?";
            delete += !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr);
            str3 = "raw_events_metadata";
            str4 = "app_id=?";
            delete += !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr);
            str3 = "event_filters";
            str4 = "app_id=?";
            delete += !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr);
            str3 = "property_filters";
            str4 = "app_id=?";
            delete += !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str3, str4, strArr) : SQLiteInstrumentation.delete(writableDatabase, str3, str4, strArr);
            String str5 = "audience_filter_values";
            str3 = "app_id=?";
            int delete2 = (!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.delete(str5, str3, strArr) : SQLiteInstrumentation.delete(writableDatabase, str5, str3, strArr)) + delete;
            if (delete2 > 0) {
                zzFm().zzFL().zze("Deleted application data. app, records", str, Integer.valueOf(delete2));
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zze("Error deleting application data. appId, error", str, e);
        }
    }

    public void zzfq(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            String str2 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)";
            String[] strArr = new String[]{str, str};
            if (writableDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(writableDatabase, str2, strArr);
            } else {
                writableDatabase.execSQL(str2, strArr);
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zzj("Failed to remove unused event metadata", e);
        }
    }

    public long zzfr(String str) {
        zzaa.zzdl(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    /* Access modifiers changed, original: 0000 */
    public String zzmv() {
        if (!zzFo().zzmW()) {
            return zzFo().zznw();
        }
        if (zzFo().zzmX()) {
            return zzFo().zznw();
        }
        zzFm().zzFH().log("Using secondary database");
        return zzFo().zznx();
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ea  */
    @android.support.annotation.WorkerThread
    public java.util.List<android.util.Pair<com.google.android.gms.internal.zzsp.zze, java.lang.Long>> zzn(java.lang.String r13, int r14, int r15) {
        /*
        r12 = this;
        r10 = 0;
        r1 = 1;
        r9 = 0;
        r12.zzkN();
        r12.zzma();
        if (r14 <= 0) goto L_0x0052;
    L_0x000b:
        r0 = r1;
    L_0x000c:
        com.google.android.gms.common.internal.zzaa.zzaj(r0);
        if (r15 <= 0) goto L_0x0054;
    L_0x0011:
        com.google.android.gms.common.internal.zzaa.zzaj(r1);
        com.google.android.gms.common.internal.zzaa.zzdl(r13);
        r0 = r12.getWritableDatabase();	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r1 = "queue";
        r2 = 2;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r3 = 0;
        r4 = "rowid";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r3 = 1;
        r4 = "data";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r3 = "app_id=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r5 = 0;
        r6 = 0;
        r7 = "rowid";
        r8 = java.lang.String.valueOf(r14);	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r11 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        if (r11 != 0) goto L_0x0056;
    L_0x003e:
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
    L_0x0042:
        r0 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        if (r0 != 0) goto L_0x005d;
    L_0x0048:
        r0 = java.util.Collections.emptyList();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        if (r2 == 0) goto L_0x0051;
    L_0x004e:
        r2.close();
    L_0x0051:
        return r0;
    L_0x0052:
        r0 = r9;
        goto L_0x000c;
    L_0x0054:
        r1 = r9;
        goto L_0x0011;
    L_0x0056:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        r2 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.query(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00f3, all -> 0x00e6 }
        goto L_0x0042;
    L_0x005d:
        r1 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r1.<init>();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r3 = r9;
    L_0x0063:
        r0 = 0;
        r4 = r2.getLong(r0);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r0 = 1;
        r0 = r2.getBlob(r0);	 Catch:{ IOException -> 0x0086 }
        r6 = r12.zzFi();	 Catch:{ IOException -> 0x0086 }
        r6 = r6.zzr(r0);	 Catch:{ IOException -> 0x0086 }
        r0 = r1.isEmpty();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        if (r0 != 0) goto L_0x009f;
    L_0x007b:
        r0 = r6.length;	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r0 = r0 + r3;
        if (r0 <= r15) goto L_0x009f;
    L_0x007f:
        if (r2 == 0) goto L_0x0084;
    L_0x0081:
        r2.close();
    L_0x0084:
        r0 = r1;
        goto L_0x0051;
    L_0x0086:
        r0 = move-exception;
        r4 = r12.zzFm();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r4 = r4.zzFE();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r5 = "Failed to unzip queued bundle";
        r4.zze(r5, r13, r0);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r0 = r3;
    L_0x0095:
        r3 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        if (r3 == 0) goto L_0x007f;
    L_0x009b:
        if (r0 > r15) goto L_0x007f;
    L_0x009d:
        r3 = r0;
        goto L_0x0063;
    L_0x009f:
        r0 = com.google.android.gms.internal.zzamb.zzN(r6);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r7 = new com.google.android.gms.internal.zzsp$zze;	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r7.<init>();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r0 = r7.mergeFrom(r0);	 Catch:{ IOException -> 0x00d6 }
        r0 = (com.google.android.gms.internal.zzsp.zze) r0;	 Catch:{ IOException -> 0x00d6 }
        r0 = r6.length;	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r0 = r0 + r3;
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r3 = android.util.Pair.create(r7, r3);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r1.add(r3);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        goto L_0x0095;
    L_0x00bc:
        r0 = move-exception;
        r1 = r2;
    L_0x00be:
        r2 = r12.zzFm();	 Catch:{ all -> 0x00f0 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x00f0 }
        r3 = "Error querying bundles";
        r2.zze(r3, r13, r0);	 Catch:{ all -> 0x00f0 }
        r0 = java.util.Collections.emptyList();	 Catch:{ all -> 0x00f0 }
        if (r1 == 0) goto L_0x0051;
    L_0x00d1:
        r1.close();
        goto L_0x0051;
    L_0x00d6:
        r0 = move-exception;
        r4 = r12.zzFm();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r4 = r4.zzFE();	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r5 = "Failed to merge queued bundle";
        r4.zze(r5, r13, r0);	 Catch:{ SQLiteException -> 0x00bc, all -> 0x00ee }
        r0 = r3;
        goto L_0x0095;
    L_0x00e6:
        r0 = move-exception;
        r2 = r10;
    L_0x00e8:
        if (r2 == 0) goto L_0x00ed;
    L_0x00ea:
        r2.close();
    L_0x00ed:
        throw r0;
    L_0x00ee:
        r0 = move-exception;
        goto L_0x00e8;
    L_0x00f0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00e8;
    L_0x00f3:
        r0 = move-exception;
        r1 = r10;
        goto L_0x00be;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zze.zzn(java.lang.String, int, int):java.util.List");
    }

    @WorkerThread
    public void zzy(String str, int i) {
        zzaa.zzdl(str);
        zzkN();
        zzma();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str2 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);";
            String[] strArr = new String[]{str, str, String.valueOf(i)};
            if (writableDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(writableDatabase, str2, strArr);
            } else {
                writableDatabase.execSQL(str2, strArr);
            }
        } catch (SQLiteException e) {
            zzFm().zzFE().zze("Error pruning currencies", str, e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzz(String str, int i) {
        zzma();
        zzkN();
        zzaa.zzdl(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str2 = "property_filters";
        String str3 = "app_id=? and audience_id=?";
        String[] strArr = new String[]{str, String.valueOf(i)};
        if (writableDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(writableDatabase, str2, str3, strArr);
        } else {
            writableDatabase.delete(str2, str3, strArr);
        }
        String str4 = "event_filters";
        str2 = "app_id=? and audience_id=?";
        String[] strArr2 = new String[]{str, String.valueOf(i)};
        if (writableDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.delete(writableDatabase, str4, str2, strArr2);
        } else {
            writableDatabase.delete(str4, str2, strArr2);
        }
    }
}
