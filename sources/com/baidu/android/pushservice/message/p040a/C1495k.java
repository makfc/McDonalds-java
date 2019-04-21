package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.message.C1502b;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p028a.p029a.C1312e;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.a.k */
public final class C1495k {
    /* renamed from: a */
    public static PublicMsg m6776a(Context context, String str, String str2, byte[] bArr) {
        boolean z = true;
        PublicMsg publicMsg = new PublicMsg();
        publicMsg.mMsgId = str;
        publicMsg.mAppId = str2;
        try {
            JSONObject init = JSONObjectInstrumentation.init(new String(bArr));
            if (!init.isNull(PushConstants.TITLE_KEY)) {
                publicMsg.mTitle = init.getString(PushConstants.TITLE_KEY);
            }
            if (!init.isNull("description")) {
                publicMsg.mDescription = init.getString("description");
            }
            if (!init.isNull(NativeProtocol.IMAGE_URL_KEY)) {
                publicMsg.mUrl = init.getString(NativeProtocol.IMAGE_URL_KEY);
            }
            if (!init.isNull("notification_builder_id")) {
                publicMsg.mNotificationBuilder = init.getInt("notification_builder_id");
            }
            if (!init.isNull("open_type")) {
                publicMsg.mOpenType = init.getInt("open_type");
            }
            if (!init.isNull("notification_basic_style")) {
                publicMsg.mNotificationBasicStyle = init.getInt("notification_basic_style");
            }
            if (!init.isNull("custom_content")) {
                publicMsg.mCustomContent = init.getString("custom_content");
            }
            if (!init.isNull("net_support")) {
                publicMsg.mNetType = init.getInt("net_support");
            }
            if (!init.isNull("app_situation")) {
                JSONObject jSONObject = init.getJSONObject("app_situation");
                if (jSONObject.getInt("as_is_support") != 1) {
                    z = false;
                }
                publicMsg.mIsSupportApp = z;
                publicMsg.mSupportAppname = jSONObject.getString("as_pkg_name");
            }
            if (!init.isNull("pkg_name")) {
                publicMsg.mPkgName = init.getString("pkg_name");
            }
            if (!init.isNull("pkg_vercode")) {
                publicMsg.mPkgVercode = init.getInt("pkg_vercode");
            }
            if (!init.isNull("pkg_content")) {
                publicMsg.mPkgContent = init.getString("pkg_content");
            }
            String optString = init.optString("redirect_url");
            publicMsg.mAdvertiseStyle = init.optInt("advertise_style");
            publicMsg.mAdvertiseSmallIconUrl = init.optString("smallicon_url");
            publicMsg.mAdvertiseLargeIconUrl = init.optString("largeicon_url");
            publicMsg.mAdvertiseClickUrl = C1495k.m6778a(context, init.optString("click_url"), optString);
            publicMsg.mAdvertiseBigPictureClickUrl = C1495k.m6778a(context, init.optString("pictureclick_url"), optString);
            publicMsg.mAdvertiseBigPictureUrl = init.optString("bigpicture_url");
            publicMsg.mAdvertiseDownloadClickUrl = C1495k.m6778a(context, init.optString("download_click_url"), optString);
            publicMsg.mAdvertiseDetailClickUrl = C1495k.m6778a(context, init.optString("detail_click_url"), optString);
            publicMsg.mAdvertiseBigPictureTitle = init.optString("bigstyle_title");
            publicMsg.mAdvertiseBigPictureContent = init.optString("bigstyle_content");
            return publicMsg;
        } catch (JSONException e) {
            C1426b.m6447b("PublicMsgParser", "Public Message Parsing Fail:\r\n" + e.getMessage(), context.getApplicationContext());
            C1425a.m6440a("PublicMsgParser", e);
            return null;
        }
    }

    /* renamed from: a */
    public static C1502b m6777a(Context context, String str) {
        C1502b c1502b = new C1502b();
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (!init.isNull("msgContent")) {
                JSONObject jSONObject;
                init = init.getJSONObject("msgContent");
                if (!init.isNull("msgId")) {
                    c1502b.f5239a = init.getString("msgId");
                }
                if (!init.isNull("adContent")) {
                    jSONObject = init.getJSONObject("adContent");
                    c1502b.f5244f = jSONObject.getString("notifyTitle");
                    c1502b.f5245g = jSONObject.getString("content");
                    if (!jSONObject.isNull("param")) {
                        jSONObject = jSONObject.getJSONObject("param");
                        if (!jSONObject.isNull(NativeProtocol.IMAGE_URL_KEY)) {
                            c1502b.f5240b = jSONObject.getString(NativeProtocol.IMAGE_URL_KEY);
                        }
                        if (!jSONObject.isNull("acn")) {
                            c1502b.f5242d = jSONObject.getString("acn");
                        }
                    }
                }
                if (!init.isNull("psContent")) {
                    jSONObject = init.getJSONObject("psContent");
                    c1502b.f5246h = jSONObject.getString("notifyTitle");
                    c1502b.f5247i = jSONObject.getString("content");
                    if (!jSONObject.isNull("param")) {
                        jSONObject = jSONObject.getJSONObject("param");
                        if (!jSONObject.isNull(NativeProtocol.IMAGE_URL_KEY)) {
                            c1502b.f5241c = jSONObject.getString(NativeProtocol.IMAGE_URL_KEY);
                        }
                        if (!jSONObject.isNull("acn")) {
                            c1502b.f5243e = jSONObject.getString("acn");
                        }
                    }
                }
                if (!init.isNull("extras")) {
                    c1502b.mo13974a(init.getJSONArray("extras"));
                }
            }
        } catch (Exception e) {
            C1425a.m6440a("PublicMsgParser", e);
        }
        return c1502b;
    }

    /* renamed from: a */
    private static String m6778a(Context context, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim()) || !C1312e.m5924a(Uri.parse(str.trim()))) {
                return null;
            }
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str2.trim()) || !C1312e.m5924a(Uri.parse(str2.trim()))) {
                return str;
            }
            String a = PushSettings.m5874a(context);
            if (a == null || a == "") {
                return str;
            }
            String str3 = str2 + "/channelid=" + a + "/t=" + System.currentTimeMillis() + "/src=" + str;
            C1425a.m6442c("PublicMsgParser", "channel_id  = " + a + "  newurl is : " + str3);
            return str3;
        } catch (Exception e) {
            C1425a.m6444e("PublicMsgParser", "error = " + e.getMessage());
            return str;
        }
    }
}
