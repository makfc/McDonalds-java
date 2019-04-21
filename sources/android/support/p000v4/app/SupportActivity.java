package android.support.p000v4.app;

import android.app.Activity;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.SimpleArrayMap;

@RestrictTo
/* renamed from: android.support.v4.app.SupportActivity */
public class SupportActivity extends Activity {
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();

    @RestrictTo
    /* renamed from: android.support.v4.app.SupportActivity$ExtraData */
    public static class ExtraData {
    }

    @RestrictTo
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    @RestrictTo
    public <T extends ExtraData> T getExtraData(Class<T> extraDataClass) {
        return (ExtraData) this.mExtraDataMap.get(extraDataClass);
    }
}
