package com.baidu.android.pushservice.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1447l;
import com.baidu.android.pushservice.p037i.C1448m;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import org.json.JSONObject;

public class PushADProvider extends ContentProvider {
    /* renamed from: a */
    SQLiteDatabase f5365a;

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        try {
            C1448m c = C1554p.m6976c(getContext());
            StringBuilder append;
            JSONObject a;
            if (c == null || c.mo13911c() != 10 || c.mo13915e() <= 0) {
                C1447l c1447l = new C1447l();
                c1447l.f5106a = 0;
                c1447l.f5107b = 10;
                c1447l.f5108c = 10;
                c1447l.f5109d = 0;
                c1447l.f5110e = C1578v.m7101c();
                C1554p.m6971a(getContext(), c1447l);
                append = new StringBuilder().append("pushadclientinfo  = ");
                a = c1447l.mo13905a();
                C1578v.m7095b(append.append(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)).toString(), getContext());
                return true;
            }
            append = new StringBuilder().append("pushadclientinfo  = ");
            a = c.mo13916f().mo13905a();
            C1578v.m7095b(append.append(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)).toString(), getContext());
            return true;
        } catch (Exception e) {
            C1425a.m6440a("PushADProvider", e);
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            this.f5365a = C1554p.m6972a(getContext());
            if (this.f5365a != null) {
                SQLiteDatabase sQLiteDatabase = this.f5365a;
                String str3 = "PushADInfo";
                Object query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(str3, null, null, null, null, null, null) : SQLiteInstrumentation.query(sQLiteDatabase, str3, null, null, null, null, null, null);
                if (query != null) {
                    C1425a.m6442c("PushADProvider", "return contentprovider Cursor : " + query);
                    return query;
                }
            }
        } catch (Exception e) {
            C1425a.m6442c("PushADProvider", "error " + e.getMessage());
        }
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
