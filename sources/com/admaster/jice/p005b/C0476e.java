package com.admaster.jice.p005b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.admaster.jice.p007d.FileUtils;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.File;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JicePushConfig */
/* renamed from: com.admaster.jice.b.e */
public class C0476e implements Serializable {
    private static final long serialVersionUID = 1;
    private Bitmap cacheBitmap = null;
    private boolean hasCached = false;
    final /* synthetic */ JicePushConfig this$0;
    private C0477f type;
    private String url;

    public C0476e(JicePushConfig jicePushConfig, JSONObject jSONObject) throws JSONException {
        this.this$0 = jicePushConfig;
        this.url = jSONObject.optString(NativeProtocol.IMAGE_URL_KEY);
        if (jSONObject.optString("type").equals(NativeProtocol.IMAGE_URL_KEY)) {
            this.type = C0477f.WEBVIEW;
            return;
        }
        this.type = C0477f.IMAGE;
        this.hasCached = jicePushConfig.hasCacheImage(this);
    }

    public Bitmap getCacheBitmap() throws Exception {
        if (this.cacheBitmap == null) {
            this.cacheBitmap = getImage(this.this$0.getImageMatericalPath(getUrl()));
        }
        return this.cacheBitmap;
    }

    public void setCacheBitmap(Bitmap bitmap) {
        this.cacheBitmap = bitmap;
    }

    public C0477f getType() {
        return this.type;
    }

    public void setType(C0477f c0477f) {
        this.type = c0477f;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Bitmap getImage(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return BitmapFactoryInstrumentation.decodeFile(new File(str).getAbsolutePath());
    }

    public String toString() {
        return "JCMaterial[" + this.type + "," + this.url + "," + this.hasCached + "]";
    }

    public String getPath() {
        return this.this$0.getImageMatericalPath(this.url);
    }

    public boolean hasCached() {
        if (this.type != C0477f.IMAGE) {
            return true;
        }
        return FileUtils.m215c(getPath());
    }
}
