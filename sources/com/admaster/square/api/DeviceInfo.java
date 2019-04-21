package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import com.admaster.square.p008a.AdMasterCollect;
import com.admaster.square.p008a.AdMasterDetect;
import com.admaster.square.utils.C0493n;
import com.admaster.square.utils.Constant;
import com.admaster.square.utils.GSMInfoUtil;
import com.admaster.square.utils.NetWorkInfoUtil;
import com.admaster.square.utils.OSInfoUtil;
import com.admaster.square.utils.Reflection;
import com.admaster.square.utils.SelfInfoUtil;
import com.admaster.square.utils.UdidInfoUtil;

/* renamed from: com.admaster.square.api.k */
public class DeviceInfo {
    /* renamed from: A */
    public String f201A;
    /* renamed from: B */
    public String f202B;
    /* renamed from: C */
    public String f203C;
    /* renamed from: D */
    public String f204D;
    /* renamed from: E */
    private AdMasterCollect f205E;
    /* renamed from: F */
    private AdMasterDetect f206F;
    /* renamed from: a */
    public String f207a;
    /* renamed from: b */
    public String f208b;
    /* renamed from: c */
    public String f209c;
    /* renamed from: d */
    public String f210d;
    /* renamed from: e */
    public String f211e;
    /* renamed from: f */
    public String f212f;
    /* renamed from: g */
    public String f213g;
    /* renamed from: h */
    public String f214h;
    /* renamed from: i */
    public String f215i;
    /* renamed from: j */
    public String f216j;
    /* renamed from: k */
    public String f217k;
    /* renamed from: l */
    public String f218l;
    /* renamed from: m */
    public String f219m;
    /* renamed from: n */
    public String f220n;
    /* renamed from: o */
    public String f221o = "0";
    /* renamed from: p */
    public String f222p;
    /* renamed from: q */
    public String f223q;
    /* renamed from: r */
    public String f224r;
    /* renamed from: s */
    public String f225s;
    /* renamed from: t */
    public String f226t;
    /* renamed from: u */
    public String f227u;
    /* renamed from: v */
    public String f228v;
    /* renamed from: w */
    public String f229w;
    /* renamed from: x */
    public String f230x;
    /* renamed from: y */
    public long f231y;
    /* renamed from: z */
    public long f232z;

    public DeviceInfo(Context context) {
        this.f205E = AdMasterCollect.m247a(context);
        this.f206F = AdMasterDetect.m261a(context);
        this.f207a = m343e(context);
        this.f208b = m345f(context);
        this.f209c = m347g(context);
        this.f210d = m349h(context);
        this.f211e = m350i(context);
        this.f212f = m352k(context);
        this.f213g = m338c();
        this.f214h = m340d();
        this.f215i = m353l(context);
        this.f216j = m354m(context);
        this.f217k = m355n(context);
        this.f218l = m356o(context);
        this.f219m = m357p(context);
        this.f220n = m342e();
        this.f222p = m336b();
        this.f223q = m359r(context);
        this.f224r = m358q(context);
        this.f226t = m334a();
        this.f225s = m339c(context);
        this.f231y = m344f();
        this.f232z = m346g();
        this.f229w = m341d(context);
        m351j(context);
        this.f228v = this.f205E.mo7719b();
        this.f230x = this.f205E.mo7718a();
        this.f201A = m348h();
        this.f227u = this.f205E.mo7725h();
        this.f202B = C0493n.m447a(this.f205E.mo7724g());
        this.f203C = C0493n.m447a(this.f205E.mo7723f());
        this.f204D = C0493n.m447a(this.f205E.mo7720c());
        m337b(context);
    }

    /* renamed from: b */
    private void m337b(Context context) {
        if (TextUtils.isEmpty(this.f207a)) {
            this.f207a = UdidInfoUtil.m482a(NetWorkInfoUtil.m454c(context));
        } else if (TextUtils.isEmpty(this.f208b)) {
            this.f208b = UdidInfoUtil.m482a(GSMInfoUtil.m444a(context));
        } else if (TextUtils.isEmpty(this.f209c)) {
            this.f209c = UdidInfoUtil.m482a(GSMInfoUtil.m446c(context));
        } else if (TextUtils.isEmpty(this.f210d)) {
            this.f210d = UdidInfoUtil.m482a(OSInfoUtil.m463c(context));
        } else if (TextUtils.isEmpty(this.f211e)) {
            this.f211e = UdidInfoUtil.m482a(Constant.f267i);
        }
    }

    /* renamed from: c */
    private String m339c(Context context) {
        return SelfInfoUtil.m469a(context);
    }

    /* renamed from: a */
    private String m334a() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SelfInfoUtil.m471c()).append('|').append(SelfInfoUtil.m475g()).append('|').append(SelfInfoUtil.m472d()).append('|').append(SelfInfoUtil.m468a()).append('|').append(SelfInfoUtil.m470b());
            return C0493n.m447a(stringBuilder.toString());
        } catch (Exception e) {
            Logger.m364b(e.getMessage());
            return "";
        }
    }

    /* renamed from: b */
    private String m336b() {
        return "1.3.3";
    }

    /* renamed from: d */
    private String m341d(Context context) {
        return UdidInfoUtil.m480a(context, "MAC1", new String[0]);
    }

    /* renamed from: e */
    private String m343e(Context context) {
        return UdidInfoUtil.m480a(context, "mac", new String[0]);
    }

    /* renamed from: f */
    private String m345f(Context context) {
        return UdidInfoUtil.m480a(context, "imei", new String[0]);
    }

    /* renamed from: g */
    private String m347g(Context context) {
        return UdidInfoUtil.m480a(context, "imsi", new String[0]);
    }

    /* renamed from: h */
    private String m349h(Context context) {
        return UdidInfoUtil.m480a(context, "androidID", new String[0]);
    }

    /* renamed from: i */
    private String m350i(Context context) {
        return UdidInfoUtil.m480a(context, "idfa", new String[0]);
    }

    /* renamed from: j */
    private void m351j(Context context) {
        UdidInfoUtil.m480a(context, "device_md5", new String[0]);
    }

    /* renamed from: a */
    public static String m335a(Context context) {
        try {
            if (Reflection.isGooglePlayServicesAvailable(context)) {
                String playAdId = Reflection.getPlayAdId(context);
                if (playAdId != null) {
                    return playAdId;
                }
                playAdId = "";
                Logger.m364b("Unable to get Google Play Services Advertising ID at start time");
                return playAdId;
            }
            Logger.m364b("google play service is missing!!!");
            return "";
        } catch (Exception e) {
            Logger.m364b("google play service is missing!!!");
            return "";
        }
    }

    /* renamed from: k */
    private String m352k(Context context) {
        return OSInfoUtil.m458a();
    }

    /* renamed from: c */
    private String m338c() {
        return OSInfoUtil.m462c();
    }

    /* renamed from: d */
    private String m340d() {
        return OSInfoUtil.m460b();
    }

    /* renamed from: l */
    private String m353l(Context context) {
        return NetWorkInfoUtil.m453b(context);
    }

    /* renamed from: m */
    private String m354m(Context context) {
        try {
            String b = GSMInfoUtil.m445b(context);
            if (TextUtils.isEmpty(b)) {
                return b;
            }
            return b.substring(0, 3);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: n */
    private String m355n(Context context) {
        try {
            String b = GSMInfoUtil.m445b(context);
            if (TextUtils.isEmpty(b)) {
                return b;
            }
            return b.substring(3);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: o */
    private String m356o(Context context) {
        return NetWorkInfoUtil.m455d(context);
    }

    /* renamed from: p */
    private String m357p(Context context) {
        return NetWorkInfoUtil.m456e(context);
    }

    /* renamed from: q */
    private String m358q(Context context) {
        return OSInfoUtil.m461b(context);
    }

    /* renamed from: e */
    private String m342e() {
        return new StringBuilder(String.valueOf(OSInfoUtil.m465e())).toString();
    }

    /* renamed from: r */
    private String m359r(Context context) {
        return OSInfoUtil.m459a(context);
    }

    /* renamed from: f */
    private long m344f() {
        return SelfInfoUtil.m474f();
    }

    /* renamed from: g */
    private long m346g() {
        return SelfInfoUtil.m473e();
    }

    /* renamed from: h */
    private String m348h() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f206F.mo7726a());
        stringBuffer.append(this.f206F.mo7727b());
        stringBuffer.append(this.f206F.mo7728c());
        stringBuffer.append(this.f206F.mo7729d());
        stringBuffer.append(this.f206F.mo7730e());
        stringBuffer.append(this.f206F.mo7731f());
        stringBuffer.append(this.f206F.mo7732g());
        stringBuffer.append(this.f206F.mo7733h());
        stringBuffer.append(this.f206F.mo7734i());
        stringBuffer.append(this.f206F.mo7735j());
        stringBuffer.append(this.f206F.mo7736k());
        return stringBuffer.toString();
    }
}
