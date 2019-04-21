package com.amap.api.services.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DBOperation */
/* renamed from: com.amap.api.services.core.ah */
public class C1084ah {
    /* renamed from: a */
    private C1083ag f3654a;
    /* renamed from: b */
    private SQLiteDatabase f3655b;

    public C1084ah(Context context) {
        this.f3654a = new C1083ag(context, "logdb.db", null, 1);
    }

    /* renamed from: a */
    private SQLiteDatabase m4731a() {
        this.f3655b = this.f3654a.getReadableDatabase();
        return this.f3655b;
    }

    /* renamed from: b */
    private SQLiteDatabase m4732b() {
        this.f3655b = this.f3654a.getWritableDatabase();
        return this.f3655b;
    }

    /* renamed from: a */
    public <T> void mo12003a(String str, SQlEntity<T> sQlEntity) {
        if (sQlEntity.mo11994a() != null && str != null) {
            if (this.f3655b == null || this.f3655b.isReadOnly()) {
                this.f3655b = m4732b();
            }
            if (this.f3655b != null) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.f3655b;
                    String a = sQlEntity.mo11994a();
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.delete(sQLiteDatabase, a, str, null);
                    } else {
                        sQLiteDatabase.delete(a, str, null);
                    }
                    if (this.f3655b != null) {
                        this.f3655b.close();
                        this.f3655b = null;
                    }
                } catch (Throwable th) {
                    if (this.f3655b != null) {
                        this.f3655b.close();
                        this.f3655b = null;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public <T> void mo12004b(String str, SQlEntity<T> sQlEntity) {
        if (sQlEntity != null && str != null && sQlEntity.mo11994a() != null) {
            ContentValues b = sQlEntity.mo11996b();
            if (b != null) {
                if (this.f3655b == null || this.f3655b.isReadOnly()) {
                    this.f3655b = m4732b();
                }
                if (this.f3655b != null) {
                    try {
                        SQLiteDatabase sQLiteDatabase = this.f3655b;
                        String a = sQlEntity.mo11994a();
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update(sQLiteDatabase, a, b, str, null);
                        } else {
                            sQLiteDatabase.update(a, b, str, null);
                        }
                        if (this.f3655b != null) {
                            this.f3655b.close();
                            this.f3655b = null;
                        }
                    } catch (Throwable th) {
                        if (this.f3655b != null) {
                            this.f3655b.close();
                            this.f3655b = null;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public <T> void mo12002a(SQlEntity<T> sQlEntity) {
        if (sQlEntity != null) {
            ContentValues b = sQlEntity.mo11996b();
            if (b != null && sQlEntity.mo11994a() != null) {
                if (this.f3655b == null || this.f3655b.isReadOnly()) {
                    this.f3655b = m4732b();
                }
                if (this.f3655b != null) {
                    try {
                        SQLiteDatabase sQLiteDatabase = this.f3655b;
                        String a = sQlEntity.mo11994a();
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.insert(sQLiteDatabase, a, null, b);
                        } else {
                            sQLiteDatabase.insert(a, null, b);
                        }
                        if (this.f3655b != null) {
                            this.f3655b.close();
                            this.f3655b = null;
                        }
                    } catch (Throwable th) {
                        if (this.f3655b != null) {
                            this.f3655b.close();
                            this.f3655b = null;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public <T> List<T> mo12005c(String str, SQlEntity<T> sQlEntity) {
        ArrayList arrayList = new ArrayList();
        if (this.f3655b == null) {
            this.f3655b = m4731a();
        }
        if (this.f3655b == null || sQlEntity.mo11994a() == null || str == null) {
            return arrayList;
        }
        try {
            SQLiteDatabase sQLiteDatabase = this.f3655b;
            String a = sQlEntity.mo11994a();
            Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(a, null, str, null, null, null, null) : SQLiteInstrumentation.query(sQLiteDatabase, a, null, str, null, null, null, null);
            if (query == null) {
                this.f3655b.close();
                this.f3655b = null;
                if (this.f3655b != null) {
                    this.f3655b.close();
                    this.f3655b = null;
                }
                return arrayList;
            }
            while (query.moveToNext()) {
                arrayList.add(sQlEntity.mo11997b(query));
            }
            query.close();
            if (this.f3655b != null) {
                this.f3655b.close();
                this.f3655b = null;
            }
            return arrayList;
        } catch (Throwable th) {
            if (this.f3655b != null) {
                this.f3655b.close();
                this.f3655b = null;
            }
        }
    }
}
