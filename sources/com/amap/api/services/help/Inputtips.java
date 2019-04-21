package com.amap.api.services.help;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.InputtipsHandler;
import com.amap.api.services.core.InputtipsParam;
import com.amap.api.services.core.ManifestConfig;
import java.util.List;

public final class Inputtips {
    /* renamed from: a */
    Handler f3903a = new C1154a(this);
    /* renamed from: b */
    private Context f3904b;
    /* renamed from: c */
    private InputtipsListener f3905c;

    public interface InputtipsListener {
        void onGetInputtips(List<Tip> list, int i);
    }

    public Inputtips(Context context, InputtipsListener inputtipsListener) {
        this.f3904b = context.getApplicationContext();
        this.f3905c = inputtipsListener;
    }

    public void requestInputtips(final String str, final String str2) throws AMapException {
        if (str == null || str.equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        ManifestConfig.m5058a(this.f3904b);
        new Thread() {
            public void run() {
                InputtipsHandler inputtipsHandler = new InputtipsHandler(Inputtips.this.f3904b, new InputtipsParam(str, str2));
                Message message = new Message();
                try {
                    message.obj = (List) inputtipsHandler.mo11981g();
                    message.what = 0;
                } catch (AMapException e) {
                    C1128d.m4975a(e, "Inputtips", "requestInputtips");
                    message.what = e.getErrorCode();
                } finally {
                    Inputtips.this.f3903a.sendMessage(message);
                }
            }
        }.start();
    }
}
