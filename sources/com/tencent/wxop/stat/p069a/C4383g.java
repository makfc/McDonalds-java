package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatGameUser;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.g */
public class C4383g extends C4377e {
    /* renamed from: a */
    private StatGameUser f6976a = null;

    public C4383g(Context context, int i, StatGameUser statGameUser, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6976a = statGameUser.clone();
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.MTA_GAME_USER;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        if (this.f6976a == null) {
            return false;
        }
        C4439q.m8159a(jSONObject, "wod", this.f6976a.getWorldName());
        C4439q.m8159a(jSONObject, "gid", this.f6976a.getAccount());
        C4439q.m8159a(jSONObject, "lev", this.f6976a.getLevel());
        return true;
    }
}
