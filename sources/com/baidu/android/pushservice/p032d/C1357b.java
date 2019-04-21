package com.baidu.android.pushservice.p032d;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;

/* renamed from: com.baidu.android.pushservice.d.b */
public class C1357b {
    /* renamed from: a */
    private static C1356a f4785a = null;

    /* renamed from: com.baidu.android.pushservice.d.b$a */
    private static class C1356a extends SQLiteOpenHelper {
        /* renamed from: a */
        private Context f4784a;

        public C1356a(Context context, String str, int i) {
            super(context, str, null, i);
            this.f4784a = context;
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            String str = "DROP TABLE IF EXISTS subscribe";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "DROP TABLE IF EXISTS message";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "DROP TABLE IF EXISTS register";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "DROP TABLE IF EXISTS blacklist";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "DROP TABLE IF EXISTS notification";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "DROP TABLE IF EXISTS weak_subscribe";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "DROP TABLE IF EXISTS app_info";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE subscribe(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT, sub_time LONG, is_bind INTEGER, host_channel TEXT, shortcut_by TEXT);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE message(msgid PRIMARY KEY,appid TEXT, title TEXT, content TEXT, link TEXT, status INTEGER, type INTEGER, time LONG);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE register(pkg_name PRIMARY KEY,channel TEXT, msg_count INTEGER, reg_time LONG, msg_switch INTEGER, host_name TEXT, host_version TEXT);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE blacklist(app_id TEXT, pkg_name TEXT, type INTEGER);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE notification(noti_id PRIMARY KEY,app_id TEXT, msg_id TEXT,time_stamp LONG);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = "CREATE TABLE weak_subscribe(apikey PRIMARY KEY,appid TEXT, sub_tags TEXT, app_name TEXT, app_logo TEXT, sub_time LONG, is_blacked INTEGER, host_channel TEXT, push_token TEXT, shortcut_by TEXT);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            String str2 = "CREATE TABLE app_info(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT);";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            C1425a.m6442c("LightAppDatabase", "downgrade from: " + i + " to: " + i2);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            C1425a.m6442c("LightAppDatabase", "upgrade from: " + i + " to: " + i2);
            String str;
            String str2;
            if (i < 1) {
                str = "DROP TABLE IF EXISTS subscribe";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS message";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS register";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS blacklist";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS notification";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS weak_subscribe";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS app_info";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE subscribe(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT, sub_time LONG, is_bind INTEGER, host_channel TEXT, shortcut_by TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE message(msgid PRIMARY KEY,appid TEXT, title TEXT, content TEXT, link TEXT, status INTEGER, type INTEGER, time LONG);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE register(pkg_name PRIMARY KEY,channel TEXT, msg_count INTEGER, reg_time LONG, msg_switch INTEGER, host_name TEXT, host_version TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE blacklist(app_id TEXT, pkg_name TEXT, type INTEGER);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE notification(noti_id PRIMARY KEY,app_id TEXT, msg_id TEXT,time_stamp LONG);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str2 = "CREATE TABLE app_info(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            } else if (i == 1) {
                str = "DROP TABLE IF EXISTS notification";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS weak_subscribe";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS app_info";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE notification(noti_id PRIMARY KEY,app_id TEXT, msg_id TEXT,time_stamp LONG);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE weak_subscribe(apikey PRIMARY KEY,appid TEXT, sub_tags TEXT, app_name TEXT, app_logo TEXT, sub_time LONG, is_blacked INTEGER, host_channel TEXT, push_token TEXT, shortcut_by TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str2 = "CREATE TABLE app_info(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            } else if (i == 2) {
                str = "DROP TABLE IF EXISTS weak_subscribe";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "DROP TABLE IF EXISTS app_info";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str = "CREATE TABLE weak_subscribe(apikey PRIMARY KEY,appid TEXT, sub_tags TEXT, app_name TEXT, app_logo TEXT, sub_time LONG, is_blacked INTEGER, host_channel TEXT, push_token TEXT, shortcut_by TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str2 = "CREATE TABLE app_info(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            } else if (i == 3) {
                str = "DROP TABLE IF EXISTS app_info";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
                str2 = "CREATE TABLE app_info(appid PRIMARY KEY,apikey TEXT, app_name TEXT, app_logo TEXT);";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
                } else {
                    sQLiteDatabase.execSQL(str2);
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized SQLiteDatabase m6160a(Context context) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (C1357b.class) {
            if (C1578v.m7079a()) {
                C1356a b = C1357b.m6161b(context);
                if (b != null) {
                    try {
                        sQLiteDatabase = b.getWritableDatabase();
                    } catch (SQLException e) {
                        f4785a = null;
                        C1425a.m6442c("LightAppDatabase", "getWritableDb exception: " + e);
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    /* renamed from: b */
    private static synchronized C1356a m6161b(Context context) {
        C1356a c1356a;
        synchronized (C1357b.class) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "baidu/pushservice/database");
            if (!file.exists()) {
                f4785a = null;
                file.mkdirs();
            }
            if (f4785a == null) {
                f4785a = new C1356a(context, file.getAbsolutePath() + File.separator + "pushlappv2.db", 4);
            }
            c1356a = f4785a;
        }
        return c1356a;
    }
}
