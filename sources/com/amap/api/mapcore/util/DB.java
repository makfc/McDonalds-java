package com.amap.api.mapcore.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.amap.api.mapcore.util.ep */
public class DB extends SQLiteOpenHelper {
    /* renamed from: a */
    private DBCreator f1888a;

    public DB(Context context, String str, CursorFactory cursorFactory, int i, DBCreator dBCreator) {
        super(context, str, cursorFactory, i);
        this.f1888a = dBCreator;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f1888a.mo8971a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f1888a.mo8972a(sQLiteDatabase, i, i2);
    }
}
