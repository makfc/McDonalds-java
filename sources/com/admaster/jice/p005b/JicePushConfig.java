package com.admaster.jice.p005b;

import android.os.Environment;
import com.admaster.jice.p007d.FileUtils;
import com.admaster.jice.p007d.Md5Util;
import com.facebook.internal.NativeProtocol;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.admaster.jice.b.d */
public class JicePushConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private String appKey = "jicesdk";
    private long endDate = 0;
    private C0476e horizontal = null;
    private int pushId = -1;
    private int showTimes = 0;
    private long startDate = 0;
    private String targetUrl = null;
    private C0476e vertical = null;

    public JicePushConfig(String str, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        this.appKey = str;
        this.pushId = jSONObject.getInt("id");
        this.startDate = jSONObject.optLong("start");
        this.endDate = jSONObject.optLong("end");
        this.showTimes = jSONObject.optInt("times");
        this.targetUrl = jSONObject.optString("target_url");
        if (jSONObject.has("vertical")) {
            jSONObject2 = jSONObject.getJSONObject("vertical").getJSONObject("material");
            if (!jSONObject2.isNull(NativeProtocol.IMAGE_URL_KEY)) {
                this.vertical = new C0476e(this, jSONObject2);
            }
        }
        if (jSONObject.has("horizontal")) {
            jSONObject2 = jSONObject.getJSONObject("horizontal").getJSONObject("material");
            if (!jSONObject2.isNull(NativeProtocol.IMAGE_URL_KEY)) {
                this.horizontal = new C0476e(this, jSONObject2);
            }
        }
    }

    public boolean hasCacheImage(C0476e c0476e) {
        return FileUtils.m215c(getImageMatericalPath(c0476e.getUrl()));
    }

    public String getImageMatericalPath(String str) {
        return new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())).append("/").append("Material").append("/").append(this.appKey).append("/").append(this.pushId).append("/").append(Md5Util.m241a(str)).toString();
    }

    public boolean hasInSchedule() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis <= this.startDate || currentTimeMillis >= this.endDate) {
            return false;
        }
        return true;
    }

    public int getPushId() {
        return this.pushId;
    }

    public void setPushId(int i) {
        this.pushId = i;
    }

    public long getStartDate() {
        return this.startDate;
    }

    public void setStartDate(long j) {
        this.startDate = j;
    }

    public long getEndDate() {
        return this.endDate;
    }

    public void setEndDate(long j) {
        this.endDate = j;
    }

    public int getShowTimes() {
        return this.showTimes;
    }

    public void setShowTimes(int i) {
        this.showTimes = i;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setTargetUrl(String str) {
        this.targetUrl = str;
    }

    public C0476e getVertical() {
        return this.vertical;
    }

    public void setVertical(C0476e c0476e) {
        this.vertical = c0476e;
    }

    public C0476e getHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(C0476e c0476e) {
        this.horizontal = c0476e;
    }

    public String toString() {
        return "JCPushConfig[" + this.pushId + "," + hasInSchedule() + "," + this.showTimes + "]";
    }
}
