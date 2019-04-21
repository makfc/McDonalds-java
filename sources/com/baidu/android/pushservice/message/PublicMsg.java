package com.baidu.android.pushservice.message;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1457i;
import com.baidu.android.pushservice.C1475k;
import com.baidu.android.pushservice.message.p040a.C1498m;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p034f.C1402a;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1436b;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p037i.C1450o;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicMsg implements Parcelable {
    public static final Creator<PublicMsg> CREATOR = new C14772();
    public String mAdvertiseBigPictureClickUrl;
    public String mAdvertiseBigPictureContent;
    public String mAdvertiseBigPictureTitle;
    public String mAdvertiseBigPictureUrl;
    public String mAdvertiseClickUrl;
    public String mAdvertiseDetailClickUrl;
    public String mAdvertiseDownloadClickUrl;
    public String mAdvertiseLargeIconUrl;
    public String mAdvertiseSmallIconUrl;
    public int mAdvertiseStyle;
    public String mAppId;
    public String mCustomContent;
    public String mDescription;
    public boolean mIsSupportApp = true;
    public String mMsgId;
    public int mNetType = 0;
    public int mNotificationBasicStyle = 7;
    public int mNotificationBuilder = 0;
    public int mOpenType = 0;
    public String mPkgContent;
    public String mPkgName;
    public int mPkgVercode = 0;
    public String mSupportAppname;
    public String mTitle;
    public String mUrl;
    public int mUserConfirm = 0;

    /* renamed from: com.baidu.android.pushservice.message.PublicMsg$2 */
    static class C14772 implements Creator<PublicMsg> {
        C14772() {
        }

        /* renamed from: a */
        public PublicMsg createFromParcel(Parcel parcel) {
            return new PublicMsg(parcel);
        }

        /* renamed from: a */
        public PublicMsg[] newArray(int i) {
            return new PublicMsg[i];
        }
    }

    PublicMsg(Parcel parcel) {
        this.mMsgId = parcel.readString();
        this.mAppId = parcel.readString();
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mUrl = parcel.readString();
        this.mPkgName = parcel.readString();
        this.mPkgVercode = parcel.readInt();
        this.mNotificationBuilder = parcel.readInt();
        this.mNotificationBasicStyle = parcel.readInt();
        this.mOpenType = parcel.readInt();
        this.mUserConfirm = parcel.readInt();
        this.mCustomContent = parcel.readString();
        this.mPkgContent = parcel.readString();
        this.mAdvertiseStyle = parcel.readInt();
        this.mAdvertiseSmallIconUrl = parcel.readString();
        this.mAdvertiseLargeIconUrl = parcel.readString();
        this.mAdvertiseClickUrl = parcel.readString();
        this.mAdvertiseBigPictureUrl = parcel.readString();
        this.mAdvertiseBigPictureClickUrl = parcel.readString();
        this.mAdvertiseDownloadClickUrl = parcel.readString();
        this.mAdvertiseDetailClickUrl = parcel.readString();
        this.mAdvertiseBigPictureTitle = parcel.readString();
        this.mAdvertiseBigPictureContent = parcel.readString();
    }

    private void addCustomContentToIntent(Intent intent) {
        if (this.mCustomContent != null) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(this.mCustomContent);
                Iterator keys = init.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    intent.putExtra(str, init.getString(str));
                }
                intent.putExtra("extra_extra_custom_content", this.mCustomContent);
            } catch (JSONException e) {
                C1425a.m6444e("PublicMsg", "Custom content to JSONObject exception::" + e.getMessage());
            }
        }
    }

    private static void insertADBehavior(Context context, C1339h c1339h, C1436b c1436b, C1449n c1449n) {
        if (c1339h != null) {
            c1449n.mo13861d(c1339h.mo13589c());
            c1449n.mo13859c(C1578v.m7069a(c1339h.f4739e));
            c1449n.mo13857b(c1339h.f4739e);
            C1449n a = C1578v.m7066a(c1449n, context, c1339h.mo13589c());
            try {
                C1456u.m6608a(context, c1436b);
                C1456u.m6612a(context, a);
            } catch (Exception e) {
                C1426b.m6448c("PublicMsg", "PM insert db exception", context.getApplicationContext());
            }
        }
    }

    private void insertADNoClientBehavior(Context context, String str, String str2, String str3, String str4, String str5) {
        C1436b c1436b = new C1436b();
        c1436b.f5036f = str5;
        c1436b.f5052a = str;
        c1436b.f5037g = System.currentTimeMillis();
        c1436b.f5038h = C1432b.m6486c(context);
        c1436b.f5054c = C1498m.MSG_TYPE_ADVERTISE.mo13970a();
        c1436b.f5040j = str2;
        c1436b.f5055d = str3;
        c1436b.f5056e = str4;
        C1456u.m6608a(context, c1436b);
    }

    private void insertADNotiBehavior(Context context, String str, String str2, String str3, String str4, String str5) {
        C1436b c1436b = new C1436b();
        c1436b.f5036f = str5;
        c1436b.f5052a = str;
        c1436b.f5037g = System.currentTimeMillis();
        c1436b.f5038h = C1432b.m6486c(context);
        c1436b.f5054c = C1498m.MSG_TYPE_ADVERTISE.mo13970a();
        c1436b.f5040j = str2;
        c1436b.f5055d = str3;
        c1436b.f5056e = str4;
        C1339h d = C1332b.m6020a(context).mo13600d(str2);
        if (d != null) {
            insertADBehavior(context, d, c1436b, new C1449n(str2));
            C1578v.m7095b("pushadvertise:  insert user action", context);
            C1426b.m6445a("PublicMsg", "pushadvertise:  insert user action", context);
        }
    }

    public static void insertADSendACKFailed(Context context, String str, String str2, String str3, String str4, String str5, int i, String str6) {
        C1436b c1436b = new C1436b();
        c1436b.f5036f = str;
        c1436b.f5040j = str2;
        c1436b.f5037g = System.currentTimeMillis();
        c1436b.f5055d = str3;
        c1436b.f5056e = str4;
        c1436b.f5039i = i;
        c1436b.f5038h = str6;
        c1436b.f5052a = str5;
        C1339h d = C1332b.m6020a(context).mo13600d(str2);
        if (d != null) {
            insertADBehavior(context, d, c1436b, new C1449n(str2));
            if (C1328a.m6006b() > 0) {
                C1578v.m7095b("pushadvertise:  insertADSendACKFailed", context);
                C1425a.m6441b("PublicMsg", "pushadvertise: insertADSendACKFailed");
            }
        }
    }

    public static void insertADSetEnableFailed(Context context, String str, int i, String str2, String str3, String str4, short s) {
        C1436b c1436b = new C1436b();
        c1436b.f5036f = str;
        c1436b.f5040j = str2;
        c1436b.f5037g = System.currentTimeMillis();
        c1436b.f5038h = C1432b.m6486c(context);
        c1436b.f5054c = i;
        c1436b.f5055d = str3;
        c1436b.f5056e = str4;
        c1436b.f5053b = s;
        C1339h d = C1332b.m6020a(context).mo13600d(str2);
        if (d != null) {
            insertADBehavior(context, d, c1436b, new C1449n(str2));
            if (C1328a.m6006b() > 0) {
                C1578v.m7095b("pushadvertise: insertADSetEnableFailed", context);
                C1425a.m6441b("PublicMsg", "pushadvertise:  insertADSetEnableFailed");
            }
        }
    }

    private void insertBehavior(Context context, C1339h c1339h, C1450o c1450o, C1449n c1449n) {
        if (c1339h != null) {
            c1449n.mo13861d(c1339h.mo13589c());
            c1449n.mo13859c(C1578v.m7069a(c1339h.f4739e));
            c1449n.mo13857b(c1339h.f4739e);
            C1449n a = C1578v.m7066a(c1449n, context, c1339h.mo13589c());
            try {
                C1456u.m6613a(context, c1450o);
                C1456u.m6612a(context, a);
            } catch (Exception e) {
                C1425a.m6441b("PublicMsg", "PM insert db exception");
            }
        }
    }

    private void insertNotiBehavior(Context context, String str, String str2, String str3) {
        C1450o c1450o = new C1450o();
        c1450o.f5036f = str3;
        c1450o.f5119a = str;
        c1450o.f5037g = System.currentTimeMillis();
        c1450o.f5038h = C1432b.m6486c(context);
        c1450o.f5121c = C1498m.MSG_TYPE_MULTI_PRIVATE_NOTIFICATION.mo13970a();
        c1450o.f5040j = str2;
        C1339h d = C1332b.m6020a(context).mo13600d(str2);
        if (d != null) {
            insertBehavior(context, d, c1450o, new C1449n(str2));
        }
    }

    private void sendResult(Context context, String str, int i) {
        final String a = C1475k.m6721a(context).mo13946a();
        final String b = C1475k.m6721a(context).mo13949b();
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(b)) {
            C1426b.m6447b("PublicMsg", "Fail Send Public msg result. Token invalid!", context.getApplicationContext());
            return;
        }
        C1426b.m6445a("PublicMsg", "Send Linkhit, msgId = " + str + ", resultCode = " + i, context.getApplicationContext());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msgid", str);
            jSONObject.put("result_code", i);
        } catch (JSONException e) {
            C1426b.m6447b("PublicMsg", e.getMessage(), context.getApplicationContext());
        }
        final String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        final Context context2 = context;
        C1462d.m6637a().mo13938a(new C1281c("PushService-linkhit", (short) 90) {
            /* renamed from: a */
            public void mo13487a() {
                try {
                    HashMap hashMap = new HashMap();
                    C1370b.m6206b(hashMap);
                    hashMap.put("method", "linkhit");
                    hashMap.put("channel_token", b);
                    hashMap.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, jSONObject2);
                    C1425a.m6442c("PublicMsg", "linkhit param -- " + C1370b.m6202a(hashMap));
                    C1402a a = C1403b.m6259a(C1457i.m6633f() + a, "POST", hashMap);
                    if (a.mo13745b() == 200) {
                        C1426b.m6448c("PublicMsg", "<<< public msg send result return OK!", context2.getApplicationContext());
                    } else {
                        C1425a.m6444e("PublicMsg", "networkRegister request failed, code=" + a.mo13745b() + ", err=" + C1432b.m6481a(a.mo13742a()));
                    }
                } catch (Exception e) {
                    C1426b.m6447b("PublicMsg", "error : " + e.getMessage(), context2.getApplicationContext());
                }
            }
        });
    }

    private void startApplicationLauncher(Context context, String str, String str2) {
        try {
            Intent parseUri = this.mPkgContent != null ? Intent.parseUri(this.mPkgContent, 0) : new Intent();
            String launcherActivityName = getLauncherActivityName(context, str);
            if (launcherActivityName != null) {
                parseUri.setClassName(str, launcherActivityName);
                parseUri.setFlags(parseUri.getFlags() | 268435456);
                parseUri.putExtra("open_type", 1);
                parseUri.putExtra("msgid", str2);
                context.startActivity(parseUri);
            }
        } catch (URISyntaxException e) {
            C1426b.m6447b("PublicMsg", "error " + e.getMessage(), context.getApplicationContext());
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getLauncherActivityName(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo != null) {
                return resolveInfo.activityInfo.name;
            }
        }
        return null;
    }

    public void handle(Context context, String str, String str2) {
        int i = 1;
        int i2 = 0;
        C1425a.m6442c("PublicMsg", "=== Handle msg: " + toString());
        C1426b.m6445a("PublicMsg", "--handle--", context.getApplicationContext());
        if ("com.baidu.pushservice.action.publicmsg.DELETE_V2".equals(str)) {
            C1425a.m6442c("PublicMsg", "Public msg deleted by user, title = " + this.mTitle);
            C1426b.m6445a("PublicMsg", "Public msg deleted by user", context.getApplicationContext());
            sendResult(context, str2, 2);
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            int i3 = packageManager.getPackageInfo(this.mPkgName, 0).versionCode;
            if (i3 >= this.mPkgVercode) {
                Intent parseUri = Intent.parseUri(this.mPkgContent, 0);
                parseUri.setPackage(this.mPkgName);
                if (packageManager.queryBroadcastReceivers(parseUri, 0).size() > 0) {
                    C1425a.m6442c("PublicMsg", "Intent broadcasted to app! ===> " + parseUri.toUri(0));
                    context.sendBroadcast(parseUri);
                } else if (packageManager.queryIntentActivities(parseUri, 0).size() > 0) {
                    C1425a.m6442c("PublicMsg", "Intent sent to actvity! ===> " + parseUri.toUri(0));
                    parseUri.addFlags(268435456);
                    context.startActivity(parseUri);
                } else {
                    C1425a.m6442c("PublicMsg", "No app component can deal, so start " + this.mPkgName + " launcher activity!");
                    i = 0;
                }
            } else {
                C1425a.m6442c("PublicMsg", "Version code is too low! ===> app ver: " + i3 + ", request ver:" + this.mPkgVercode);
                i = 0;
            }
            i2 = i;
        } catch (NameNotFoundException e) {
            C1426b.m6447b("PublicMsg", "package not exist \r\n" + e.getMessage(), context);
        } catch (URISyntaxException e2) {
            C1426b.m6447b("PublicMsg", "uri to intent fail \r\n" + e2.getMessage(), context);
        } catch (Exception e3) {
            C1426b.m6447b("PublicMsg", "parse customize action error\r\n" + e3.getMessage(), context);
        }
        if (i2 == 0) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.mUrl));
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e4) {
                C1426b.m6447b("PublicMsg", ">>> Url cann't be deal! \r\n" + e4.getMessage(), context);
            }
        }
        sendResult(context, str2, i2);
    }

    public void handleADNotification(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        C1426b.m6445a("PublicMsg", "=== Handle AD notification: " + str, context);
        if ("com.baidu.android.pushservice.action.adnotification.ADDELETE".equals(str)) {
            C1425a.m6442c("PublicMsg", "AD notification deleted by user, title = " + this.mTitle);
            C1578v.m7095b("pushadvertise:  save delete action", context);
            insertADNotiBehavior(context, str2, str3, str6, str4, "010502");
        } else if ("com.baidu.android.pushservice.action.adnotification.ADCLICKFAILED".equals(str)) {
            C1425a.m6442c("PublicMsg", "AD notification open failed by user, title = " + this.mTitle);
            C1578v.m7095b("pushadvertise:  save open failed action", context);
            insertADNotiBehavior(context, str2, str3, str6, str4, "010506");
        } else {
            try {
                int i = context.getPackageManager().getPackageInfo(this.mPkgName, 0).versionCode;
                if (i >= this.mPkgVercode) {
                    insertADNotiBehavior(context, str2, str3, str6, str4, "010501");
                    return;
                }
                C1425a.m6442c("PublicMsg", "Version code is too low! ===> app ver: " + i + ", request ver:" + this.mPkgVercode);
            } catch (NameNotFoundException e) {
                C1426b.m6447b("PublicMsg", "package not exist \r\n" + e.getMessage(), context);
            }
        }
    }

    public void handleADShowNotification(Context context, String str, String str2, String str3, String str4) {
        C1578v.m7095b("pushadvertise: receive show  test action", context);
        C1426b.m6448c("PublicMsg", "--handleADShowNotification--", context);
        if (C1328a.m6006b() > 0) {
            insertADNotiBehavior(context, str, str2, str4, str3, "015503");
            return;
        }
        if ("06".equals(str3)) {
            insertADNoClientBehavior(context, str, str2, str4, str3, "030503");
        } else {
            insertADNotiBehavior(context, str, str2, str4, str3, "010503");
        }
        C1426b.m6448c("PublicMsg", "handleADShowNotification  release", context);
    }

    public void handleAlarmMessage(Context context, String str, String str2, String str3) {
        C1425a.m6442c("PublicMsg", "handle AlarmMessage ");
        insertNotiBehavior(context, str2, str3, str);
    }

    public void handlePrivateNotification(Context context, String str, String str2, String str3) {
        C1426b.m6445a("PublicMsg", "=== Handle private notification: " + str, context);
        if ("com.baidu.android.pushservice.action.privatenotification.DELETE".equals(str)) {
            C1425a.m6442c("PublicMsg", "private notification deleted by user, title = " + this.mTitle);
            insertNotiBehavior(context, str2, str3, "010202");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            int i = packageManager.getPackageInfo(this.mPkgName, 0).versionCode;
            if (i >= this.mPkgVercode) {
                Intent intent = new Intent();
                intent.putExtra("notification_title", this.mTitle);
                intent.putExtra("notification_content", this.mDescription);
                addCustomContentToIntent(intent);
                C1578v.m7094b(context, intent, "com.baidu.android.pushservice.action.notification.CLICK", this.mPkgName);
                insertNotiBehavior(context, str2, str3, "010201");
                if (this.mOpenType == 1 && this.mUrl != null) {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(this.mUrl));
                    intent2.addFlags(268435456);
                    context.startActivity(intent2);
                    return;
                } else if (this.mOpenType != 2) {
                    return;
                } else {
                    if (TextUtils.isEmpty(this.mPkgContent)) {
                        startApplicationLauncher(context, this.mPkgName, str2);
                        return;
                    }
                    intent = Intent.parseUri(this.mPkgContent, 0);
                    intent.setPackage(this.mPkgName);
                    if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
                        C1425a.m6442c("PublicMsg", "Intent broadcasted to app! ===> " + intent.toUri(0));
                        context.sendBroadcast(intent);
                        return;
                    } else if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
                        C1425a.m6442c("PublicMsg", "Intent sent to actvity! ===> " + intent.toUri(0));
                        intent.addFlags(268435456);
                        intent.putExtra("open_type", 1);
                        intent.putExtra("msgid", str2);
                        context.startActivity(intent);
                        return;
                    } else {
                        return;
                    }
                }
            }
            C1425a.m6442c("PublicMsg", "Version code is too low! ===> app ver: " + i + ", request ver:" + this.mPkgVercode);
        } catch (NameNotFoundException e) {
            C1426b.m6447b("PublicMsg", "package not exist \r\n" + e.getMessage(), context);
        } catch (URISyntaxException e2) {
            C1426b.m6447b("PublicMsg", "uri to intent fail \r\n" + e2.getMessage(), context);
        }
    }

    public void handleRichMediaNotification(Context context, String str, String str2) {
        C1426b.m6445a("PublicMsg", "Handle rich media notification", context);
        C1425a.m6442c("PublicMsg", "=== Handle rich media notification: " + str + " title = " + this.mTitle);
        C1450o c1450o = new C1450o();
        if ("com.baidu.android.pushservice.action.media.DELETE".equals(str)) {
            C1425a.m6442c("PublicMsg", "rich media notification deleted by user, title = " + this.mTitle);
            c1450o.f5036f = "010402";
        } else {
            Intent intent = new Intent("com.baidu.android.pushservice.action.media.CLICK");
            intent.setPackage(this.mPkgName);
            intent.putExtra("public_msg", this);
            context.sendBroadcast(intent);
            c1450o.f5036f = "010401";
        }
        c1450o.f5119a = this.mMsgId;
        c1450o.f5121c = C1498m.MSG_TYPE_RICH_MEDIA.mo13970a();
        c1450o.f5037g = System.currentTimeMillis();
        c1450o.f5039i = 0;
        c1450o.f5038h = C1432b.m6486c(context);
        c1450o.f5040j = str2;
        C1339h d = C1332b.m6020a(context).mo13600d(str2);
        if (d != null) {
            insertBehavior(context, d, c1450o, new C1449n(str2));
        }
    }

    public String toString() {
        return "\r\n mMsgId = " + this.mMsgId + "\r\n mAppId = " + this.mAppId + "\r\n mTitle = " + this.mTitle + "\r\n mDescription = " + this.mDescription + "\r\n mUrl = " + this.mUrl + "\r\n mNetType = " + this.mNetType + "\r\n mSupportAppname = " + this.mSupportAppname + "\r\n mIsSupportApp = " + this.mIsSupportApp + "\r\n mPkgName = " + this.mPkgName + "\r\n mPlgVercode = " + this.mPkgVercode + "\r\n mNotificationBuilder = " + this.mNotificationBuilder + "\r\n mNotificationBasicStyle = " + this.mNotificationBasicStyle + "\r\n mOpenType = " + this.mOpenType + "\r\n mCustomContent = " + this.mCustomContent + "\r\n mIntent = " + this.mPkgContent + "AdvertiseStyle " + this.mAdvertiseStyle + "\r\n " + "AdvertiseBigpictureTitle " + this.mAdvertiseBigPictureTitle + "\r\n" + "AdvertiseBigpictureContent " + this.mAdvertiseBigPictureClickUrl + "\r\n" + "AdvertiseBigpictureUrl " + this.mAdvertiseBigPictureUrl + "\r\n" + "AdvertiseClickUrl " + this.mAdvertiseClickUrl + "\r\n" + "AdvertiseSamllIcon " + this.mAdvertiseSmallIconUrl + "\r\n" + "AdvertiseLargeIcon " + this.mAdvertiseLargeIconUrl + "\r\n" + "AdvertiseBigPictureUrl " + this.mAdvertiseBigPictureClickUrl + "\r\n" + "AdvertiseDownloadClickUrl " + this.mAdvertiseDownloadClickUrl + "\r\n" + "AdvertiseDetailClickUrl " + this.mAdvertiseDetailClickUrl + "\r\n";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMsgId);
        parcel.writeString(this.mAppId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mPkgName);
        parcel.writeInt(this.mPkgVercode);
        parcel.writeInt(this.mNotificationBuilder);
        parcel.writeInt(this.mNotificationBasicStyle);
        parcel.writeInt(this.mOpenType);
        parcel.writeInt(this.mUserConfirm);
        parcel.writeString(this.mCustomContent);
        parcel.writeString(this.mPkgContent);
        parcel.writeInt(this.mAdvertiseStyle);
        parcel.writeString(this.mAdvertiseSmallIconUrl);
        parcel.writeString(this.mAdvertiseLargeIconUrl);
        parcel.writeString(this.mAdvertiseClickUrl);
        parcel.writeString(this.mAdvertiseBigPictureUrl);
        parcel.writeString(this.mAdvertiseBigPictureClickUrl);
        parcel.writeString(this.mAdvertiseDownloadClickUrl);
        parcel.writeString(this.mAdvertiseDetailClickUrl);
        parcel.writeString(this.mAdvertiseBigPictureTitle);
        parcel.writeString(this.mAdvertiseBigPictureContent);
    }
}
