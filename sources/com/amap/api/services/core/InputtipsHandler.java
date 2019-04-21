package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.help.Tip;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONException;

/* renamed from: com.amap.api.services.core.h */
public class InputtipsHandler extends C1076q<InputtipsParam, ArrayList<Tip>> {
    public InputtipsHandler(Context context, InputtipsParam inputtipsParam) {
        super(context, inputtipsParam);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ArrayList<Tip> mo11978b(String str) throws AMapException {
        ArrayList<Tip> arrayList = null;
        try {
            return JSONHelper.m5044o(JSONObjectInstrumentation.init(str));
        } catch (JSONException e) {
            C1128d.m4975a(e, "InputtipsHandler", "paseJSON");
            return arrayList;
        }
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/assistant/inputtips?";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&keywords=").append(mo11979c(((InputtipsParam) this.f3612a).f3788a));
        String str = ((InputtipsParam) this.f3612a).f3789b;
        if (!JSONHelper.m5033h(str)) {
            stringBuffer.append("&city=").append(mo11979c(str));
        }
        stringBuffer.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuffer.toString();
    }
}
