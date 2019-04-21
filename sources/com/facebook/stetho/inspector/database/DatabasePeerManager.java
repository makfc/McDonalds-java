package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database.AddDatabaseEvent;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseObject;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class DatabasePeerManager extends ChromePeerManager {
    private static final String[] UNINTERESTING_FILENAME_SUFFIXES = new String[]{"-journal", "-shm", "-uid", "-wal"};
    private final Context mContext;
    private final DatabaseFilesProvider mDatabaseFilesProvider;
    private final PeerRegistrationListener mPeerRegistrationListener;

    /* renamed from: com.facebook.stetho.inspector.database.DatabasePeerManager$1 */
    class C19771 implements PeerRegistrationListener {
        C19771() {
        }

        public void onPeerRegistered(JsonRpcPeer peer) {
            DatabasePeerManager.this.bootstrapNewPeer(peer);
        }

        public void onPeerUnregistered(JsonRpcPeer peer) {
        }
    }

    public interface ExecuteResultHandler<T> {
        T handleInsert(long j) throws SQLiteException;

        T handleRawQuery() throws SQLiteException;

        T handleSelect(Cursor cursor) throws SQLiteException;

        T handleUpdateDelete(int i) throws SQLiteException;
    }

    @Deprecated
    public DatabasePeerManager(Context context) {
        this(context, new DefaultDatabaseFilesProvider(context));
    }

    public DatabasePeerManager(Context context, DatabaseFilesProvider databaseFilesProvider) {
        this.mPeerRegistrationListener = new C19771();
        this.mContext = context;
        this.mDatabaseFilesProvider = databaseFilesProvider;
        setListener(this.mPeerRegistrationListener);
    }

    private void bootstrapNewPeer(JsonRpcPeer peer) {
        List<File> potentialDatabaseFiles = this.mDatabaseFilesProvider.getDatabaseFiles();
        Collections.sort(potentialDatabaseFiles);
        for (File database : tidyDatabaseList(potentialDatabaseFiles)) {
            DatabaseObject databaseParams = new DatabaseObject();
            databaseParams.f6030id = database.getPath();
            databaseParams.name = database.getName();
            databaseParams.domain = this.mContext.getPackageName();
            databaseParams.version = "N/A";
            AddDatabaseEvent eventParams = new AddDatabaseEvent();
            eventParams.database = databaseParams;
            peer.invokeMethod("Database.addDatabase", eventParams, null);
        }
    }

    static List<File> tidyDatabaseList(List<File> databaseFiles) {
        Set<File> originalAsSet = new HashSet(databaseFiles);
        List<File> tidiedList = new ArrayList();
        for (File databaseFile : databaseFiles) {
            String databaseFilename = databaseFile.getPath();
            String sansSuffix = removeSuffix(databaseFilename, UNINTERESTING_FILENAME_SUFFIXES);
            if (sansSuffix.equals(databaseFilename) || !originalAsSet.contains(new File(sansSuffix))) {
                tidiedList.add(databaseFile);
            }
        }
        return tidiedList;
    }

    private static String removeSuffix(String str, String[] suffixesToRemove) {
        for (String suffix : suffixesToRemove) {
            if (str.endsWith(suffix)) {
                return str.substring(0, str.length() - suffix.length());
            }
        }
        return str;
    }

    public List<String> getDatabaseTableNames(String databaseName) throws SQLiteException {
        SQLiteDatabase database = openDatabase(databaseName);
        Cursor cursor;
        try {
            String str = "SELECT name FROM sqlite_master WHERE type=?";
            String[] strArr = new String[]{"table"};
            cursor = !(database instanceof SQLiteDatabase) ? database.rawQuery(str, strArr) : SQLiteInstrumentation.rawQuery(database, str, strArr);
            List<String> tableNames = new ArrayList();
            while (cursor.moveToNext()) {
                tableNames.add(cursor.getString(0));
            }
            cursor.close();
            database.close();
            return tableNames;
        } catch (Throwable th) {
            database.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x001d A:{Catch:{ all -> 0x0079 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A:{Catch:{ all -> 0x0079 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0069 A:{SYNTHETIC, Splitter:B:28:0x0069} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0071 A:{SYNTHETIC, Splitter:B:31:0x0071} */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x001d A:{Catch:{ all -> 0x0079 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A:{Catch:{ all -> 0x0079 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0069 A:{SYNTHETIC, Splitter:B:28:0x0069} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0071 A:{SYNTHETIC, Splitter:B:31:0x0071} */
    public <T> T executeSQL(java.lang.String r5, java.lang.String r6, com.facebook.stetho.inspector.database.DatabasePeerManager.ExecuteResultHandler<T> r7) throws android.database.sqlite.SQLiteException {
        /*
        r4 = this;
        com.facebook.stetho.common.Util.throwIfNull(r6);
        com.facebook.stetho.common.Util.throwIfNull(r7);
        r0 = r4.openDatabase(r5);
        r2 = getFirstWord(r6);	 Catch:{ all -> 0x0079 }
        r1 = r2.toUpperCase();	 Catch:{ all -> 0x0079 }
        r2 = -1;
        r3 = r1.hashCode();	 Catch:{ all -> 0x0079 }
        switch(r3) {
            case -2130463047: goto L_0x0039;
            case -1926899396: goto L_0x004d;
            case -1852692228: goto L_0x0043;
            case -1785516855: goto L_0x0025;
            case -591179561: goto L_0x0057;
            case 2012838315: goto L_0x002f;
            default: goto L_0x001a;
        };	 Catch:{ all -> 0x0079 }
    L_0x001a:
        switch(r2) {
            case 0: goto L_0x0061;
            case 1: goto L_0x0061;
            case 2: goto L_0x0069;
            case 3: goto L_0x0071;
            case 4: goto L_0x0071;
            case 5: goto L_0x0071;
            default: goto L_0x001d;
        };	 Catch:{ all -> 0x0079 }
    L_0x001d:
        r2 = r4.executeRawQuery(r0, r6, r7);	 Catch:{ all -> 0x0079 }
        r0.close();
    L_0x0024:
        return r2;
    L_0x0025:
        r3 = "UPDATE";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x001a;
    L_0x002d:
        r2 = 0;
        goto L_0x001a;
    L_0x002f:
        r3 = "DELETE";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x001a;
    L_0x0037:
        r2 = 1;
        goto L_0x001a;
    L_0x0039:
        r3 = "INSERT";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x001a;
    L_0x0041:
        r2 = 2;
        goto L_0x001a;
    L_0x0043:
        r3 = "SELECT";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x001a;
    L_0x004b:
        r2 = 3;
        goto L_0x001a;
    L_0x004d:
        r3 = "PRAGMA";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x001a;
    L_0x0055:
        r2 = 4;
        goto L_0x001a;
    L_0x0057:
        r3 = "EXPLAIN";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x001a;
    L_0x005f:
        r2 = 5;
        goto L_0x001a;
    L_0x0061:
        r2 = r4.executeUpdateDelete(r0, r6, r7);	 Catch:{ all -> 0x0079 }
        r0.close();
        goto L_0x0024;
    L_0x0069:
        r2 = r4.executeInsert(r0, r6, r7);	 Catch:{ all -> 0x0079 }
        r0.close();
        goto L_0x0024;
    L_0x0071:
        r2 = r4.executeSelect(r0, r6, r7);	 Catch:{ all -> 0x0079 }
        r0.close();
        goto L_0x0024;
    L_0x0079:
        r2 = move-exception;
        r0.close();
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.database.DatabasePeerManager.executeSQL(java.lang.String, java.lang.String, com.facebook.stetho.inspector.database.DatabasePeerManager$ExecuteResultHandler):java.lang.Object");
    }

    private static String getFirstWord(String s) {
        s = s.trim();
        int firstSpace = s.indexOf(32);
        return firstSpace >= 0 ? s.substring(0, firstSpace) : s;
    }

    @TargetApi(11)
    private <T> T executeUpdateDelete(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        return handler.handleUpdateDelete(database.compileStatement(query).executeUpdateDelete());
    }

    private <T> T executeInsert(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        return handler.handleInsert(database.compileStatement(query).executeInsert());
    }

    private <T> T executeSelect(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        Cursor cursor = !(database instanceof SQLiteDatabase) ? database.rawQuery(query, null) : SQLiteInstrumentation.rawQuery(database, query, null);
        try {
            T handleSelect = handler.handleSelect(cursor);
            return handleSelect;
        } finally {
            cursor.close();
        }
    }

    private <T> T executeRawQuery(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        if (database instanceof SQLiteDatabase) {
            SQLiteInstrumentation.execSQL(database, query);
        } else {
            database.execSQL(query);
        }
        return handler.handleRawQuery();
    }

    private SQLiteDatabase openDatabase(String databaseName) throws SQLiteException {
        Util.throwIfNull(databaseName);
        return SQLiteDatabase.openDatabase(this.mContext.getDatabasePath(databaseName).getAbsolutePath(), null, 0);
    }
}
