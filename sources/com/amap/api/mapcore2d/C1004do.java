package com.amap.api.mapcore2d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DB */
/* renamed from: com.amap.api.mapcore2d.do */
public class C1004do extends SQLiteOpenHelper {
    /* renamed from: a */
    private C0997di f2843a;

    public C1004do(Context context, String str, CursorFactory cursorFactory, int i, C0997di c0997di) {
        super(context, str, cursorFactory, i);
        this.f2843a = c0997di;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f2843a.mo10204a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f2843a.mo10205a(sQLiteDatabase, i, i2);
    }
}
