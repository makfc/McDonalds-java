package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.ev */
public class UpdateLogDBOperation {
    /* renamed from: a */
    private DBOperation f1892a = m2659a(this.f1893b);
    /* renamed from: b */
    private Context f1893b;

    public UpdateLogDBOperation(Context context) {
        this.f1893b = context;
    }

    /* renamed from: a */
    private DBOperation m2659a(Context context) {
        try {
            return new DBOperation(context, DBOperation.m2614a(LogDBCreator.class));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    /* renamed from: a */
    public UpdateLogInfo mo9353a() {
        try {
            if (this.f1892a == null) {
                this.f1892a = m2659a(this.f1893b);
            }
            List b = this.f1892a.mo9332b("1=1", UpdateLogInfo.class);
            if (b.size() > 0) {
                return (UpdateLogInfo) b.get(0);
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    /* renamed from: a */
    public void mo9354a(UpdateLogInfo updateLogInfo) {
        if (updateLogInfo != null) {
            try {
                if (this.f1892a == null) {
                    this.f1892a = m2659a(this.f1893b);
                }
                String str = "1=1";
                List b = this.f1892a.mo9332b(str, UpdateLogInfo.class);
                if (b == null || b.size() == 0) {
                    this.f1892a.mo9325a((Object) updateLogInfo);
                } else {
                    this.f1892a.mo9329a(str, (Object) updateLogInfo);
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}
