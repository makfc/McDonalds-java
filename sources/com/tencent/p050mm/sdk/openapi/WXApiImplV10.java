package com.tencent.p050mm.sdk.openapi;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.p050mm.sdk.modelbiz.CreateChatroom;
import com.tencent.p050mm.sdk.modelbiz.JoinChatroom;
import com.tencent.p050mm.sdk.modelbiz.OpenWebview;
import com.tencent.p050mm.sdk.modelmsg.GetMessageFromWX.Req;
import com.tencent.p050mm.sdk.modelmsg.LaunchFromWX;
import com.tencent.p050mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.p050mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.p050mm.sdk.modelmsg.ShowMessageFromWX;
import com.tencent.p050mm.sdk.modelpay.PayResp;
import com.tencent.p050mm.sdk.p064a.C4350a;
import com.tencent.p050mm.sdk.p064a.C4350a.C4346a;
import com.tencent.p050mm.sdk.p064a.p065a.C4348a;
import com.tencent.p050mm.sdk.p064a.p065a.C4348a.C4347a;
import com.tencent.p050mm.sdk.p064a.p065a.C4349b;
import com.tencent.p050mm.sdk.p066b.C4353b;
import com.tencent.wxop.stat.MtaSDkException;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatReportStrategy;
import com.tencent.wxop.stat.StatService;

/* renamed from: com.tencent.mm.sdk.openapi.WXApiImplV10 */
final class WXApiImplV10 implements IWXAPI {
    private static ActivityLifecycleCb activityCb = null;
    private static String wxappPayEntryClassname = null;
    private String appId;
    private boolean checkSignature = false;
    private Context context;
    private boolean detached = false;

    /* renamed from: com.tencent.mm.sdk.openapi.WXApiImplV10$ActivityLifecycleCb */
    private static final class ActivityLifecycleCb implements ActivityLifecycleCallbacks {
        private Context context;
        private Handler handler;
        private boolean isForeground;
        private Runnable onPausedRunnable;
        private Runnable onResumedRunnable;

        /* renamed from: com.tencent.mm.sdk.openapi.WXApiImplV10$ActivityLifecycleCb$1 */
        class C43751 implements Runnable {
            C43751() {
            }

            public void run() {
                if (WXApiImplV10.activityCb != null && ActivityLifecycleCb.this.isForeground) {
                    Log.v("MicroMsg.SDK.WXApiImplV10.ActivityLifecycleCb", "WXStat trigger onBackground");
                    StatService.trackCustomKVEvent(ActivityLifecycleCb.this.context, "onBackground_WX", null);
                    ActivityLifecycleCb.this.isForeground = false;
                }
            }
        }

        /* renamed from: com.tencent.mm.sdk.openapi.WXApiImplV10$ActivityLifecycleCb$2 */
        class C43762 implements Runnable {
            C43762() {
            }

            public void run() {
                if (WXApiImplV10.activityCb != null && !ActivityLifecycleCb.this.isForeground) {
                    Log.v("MicroMsg.SDK.WXApiImplV10.ActivityLifecycleCb", "WXStat trigger onForeground");
                    StatService.trackCustomKVEvent(ActivityLifecycleCb.this.context, "onForeground_WX", null);
                    ActivityLifecycleCb.this.isForeground = true;
                }
            }
        }

        private ActivityLifecycleCb(Context context) {
            this.isForeground = false;
            this.handler = new Handler(Looper.getMainLooper());
            this.onPausedRunnable = new C43751();
            this.onResumedRunnable = new C43762();
            this.context = context;
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
            Log.v("MicroMsg.SDK.WXApiImplV10.ActivityLifecycleCb", activity.getComponentName().getClassName() + "  onActivityPaused");
            this.handler.removeCallbacks(this.onResumedRunnable);
            this.handler.postDelayed(this.onPausedRunnable, 800);
        }

        public final void onActivityResumed(Activity activity) {
            Log.v("MicroMsg.SDK.WXApiImplV10.ActivityLifecycleCb", activity.getComponentName().getClassName() + "  onActivityResumed");
            this.handler.removeCallbacks(this.onPausedRunnable);
            this.handler.postDelayed(this.onResumedRunnable, 800);
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityStopped(Activity activity) {
        }
    }

    WXApiImplV10(Context context, String str, boolean z) {
        C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "<init>, appId = " + str + ", checkSignature = " + z);
        this.context = context;
        this.appId = str;
        this.checkSignature = z;
    }

    private boolean checkSumConsistent(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, invalid arguments");
            return false;
        } else if (bArr.length != bArr2.length) {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, length is different");
            return false;
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean createChatroom(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/createChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_basereq_transaction"), bundle.getString("_wxapi_create_chatroom_group_id"), bundle.getString("_wxapi_create_chatroom_chatroom_name"), bundle.getString("_wxapi_create_chatroom_chatroom_nickname"), bundle.getString("_wxapi_create_chatroom_ext_msg")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private void initMta(Context context, String str) {
        String str2 = "AWXOP" + str;
        StatConfig.setAppKey(context, str2);
        StatConfig.setEnableSmartReporting(true);
        StatConfig.setStatSendStrategy(StatReportStrategy.PERIOD);
        StatConfig.setSendPeriodMinutes(60);
        StatConfig.setInstallChannel(context, "Wechat_Sdk");
        try {
            StatService.startStatService(context, str2, "2.0.3");
        } catch (MtaSDkException e) {
            e.printStackTrace();
        }
    }

    private boolean joinChatroom(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/joinChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_basereq_transaction"), bundle.getString("_wxapi_join_chatroom_group_id"), bundle.getString("_wxapi_join_chatroom_chatroom_nickname"), bundle.getString("_wxapi_join_chatroom_ext_msg")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendAddCardToWX(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), null, null, new String[]{this.appId, bundle.getString("_wxapi_add_card_to_wx_card_list"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizProfileReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg"), bundle.getInt("_wxapi_jump_to_biz_profile_req_scene"), bundle.getInt("_wxapi_jump_to_biz_profile_req_profile_type")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizTempSessionReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizTempSession"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_session_from"), bundle.getInt("_wxapi_jump_to_biz_webview_req_show_type")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizWebviewReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_ext_msg"), bundle.getInt("_wxapi_jump_to_biz_webview_req_scene")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusiLuckyMoney(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), null, null, new String[]{this.appId, bundle.getString("_wxapi_open_busi_lucky_money_timeStamp"), bundle.getString("_wxapi_open_busi_lucky_money_nonceStr"), bundle.getString("_wxapi_open_busi_lucky_money_signType"), bundle.getString("_wxapi_open_busi_lucky_money_signature"), bundle.getString("_wxapi_open_busi_lucky_money_package")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenRankListReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openRankList"), null, null, new String[0], null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenWebview(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openWebview"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_webview_url"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayReq(Context context, Bundle bundle) {
        if (wxappPayEntryClassname == null) {
            wxappPayEntryClassname = new MMSharedPreferences(context).getString("_wxapp_pay_entry_classname_", null);
            C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
            if (wxappPayEntryClassname == null) {
                C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        C4346a c4346a = new C4346a();
        c4346a.f6781Z = bundle;
        c4346a.f6778W = "com.tencent.mm";
        c4346a.f6779X = wxappPayEntryClassname;
        return C4350a.m7881a(context, c4346a);
    }

    public final int getWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
        } else if (isWXAppInstalled()) {
            return new MMSharedPreferences(this.context).getInt("_build_info_sdk_int_", 0);
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
            return 0;
        }
    }

    public final boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        try {
            if (!WXApiImplComm.isIntentFromWx(intent, "com.tencent.mm.openapi.token")) {
                C4353b.m7891d("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, intent not from weixin msg");
                return false;
            } else if (this.detached) {
                throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
            } else {
                String stringExtra = intent.getStringExtra("_mmessage_content");
                int intExtra = intent.getIntExtra("_mmessage_sdkVersion", 0);
                String stringExtra2 = intent.getStringExtra("_mmessage_appPackage");
                if (stringExtra2 == null || stringExtra2.length() == 0) {
                    C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "invalid argument");
                    return false;
                } else if (checkSumConsistent(intent.getByteArrayExtra("_mmessage_checksum"), C4349b.m7880a(stringExtra, intExtra, stringExtra2))) {
                    int intExtra2 = intent.getIntExtra("_wxapi_command_type", 0);
                    switch (intExtra2) {
                        case 1:
                            iWXAPIEventHandler.onResp(new Resp(intent.getExtras()));
                            return true;
                        case 2:
                            iWXAPIEventHandler.onResp(new SendMessageToWX.Resp(intent.getExtras()));
                            return true;
                        case 3:
                            iWXAPIEventHandler.onReq(new Req(intent.getExtras()));
                            return true;
                        case 4:
                            iWXAPIEventHandler.onReq(new ShowMessageFromWX.Req(intent.getExtras()));
                            return true;
                        case 5:
                            iWXAPIEventHandler.onResp(new PayResp(intent.getExtras()));
                            return true;
                        case 6:
                            iWXAPIEventHandler.onReq(new LaunchFromWX.Req(intent.getExtras()));
                            return true;
                        case 9:
                            iWXAPIEventHandler.onResp(new AddCardToWXCardPackage.Resp(intent.getExtras()));
                            return true;
                        case 12:
                            iWXAPIEventHandler.onResp(new OpenWebview.Resp(intent.getExtras()));
                            return true;
                        case 14:
                            iWXAPIEventHandler.onResp(new CreateChatroom.Resp(intent.getExtras()));
                            return true;
                        case 15:
                            iWXAPIEventHandler.onResp(new JoinChatroom.Resp(intent.getExtras()));
                            return true;
                        default:
                            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "unknown cmd = " + intExtra2);
                            return false;
                    }
                } else {
                    C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "checksum fail");
                    return false;
                }
            }
        } catch (Exception e) {
            C4353b.m7888a("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, ex = %s", e.getMessage());
            return false;
        }
    }

    public final boolean isWXAppInstalled() {
        if (this.detached) {
            throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
        }
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
            return packageInfo == null ? false : WXApiImplComm.validateAppSignature(this.context, packageInfo.signatures, this.checkSignature);
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public final boolean isWXAppSupportAPI() {
        if (!this.detached) {
            return getWXAppSupportAPI() >= 587268097;
        } else {
            throw new IllegalStateException("isWXAppSupportAPI fail, WXMsgImpl has been detached");
        }
    }

    public final boolean registerApp(String str) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        } else if (WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            if (activityCb == null && VERSION.SDK_INT >= 14) {
                if (this.context instanceof Activity) {
                    initMta(this.context, str);
                    activityCb = new ActivityLifecycleCb(this.context);
                    ((Activity) this.context).getApplication().registerActivityLifecycleCallbacks(activityCb);
                } else if (this.context instanceof Service) {
                    initMta(this.context, str);
                    activityCb = new ActivityLifecycleCb(this.context);
                    ((Service) this.context).getApplication().registerActivityLifecycleCallbacks(activityCb);
                } else {
                    C4353b.m7890c("MicroMsg.SDK.WXApiImplV10", "context is not instanceof Activity or Service, disable WXStat");
                }
            }
            C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + str);
            if (str != null) {
                this.appId = str;
            }
            C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "register app " + this.context.getPackageName());
            C4347a c4347a = new C4347a();
            c4347a.f6784aa = "com.tencent.mm";
            c4347a.f6785ab = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER";
            c4347a.f6782Y = "weixin://registerapp?appid=" + this.appId;
            return C4348a.m7879a(this.context, c4347a);
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        }
    }

    public final boolean sendReq(BaseReq baseReq) {
        if (this.detached) {
            throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "sendReq failed for wechat app signature check failed");
            return false;
        } else if (baseReq.checkArgs()) {
            C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "sendReq, req type = " + baseReq.getType());
            Bundle bundle = new Bundle();
            baseReq.toBundle(bundle);
            if (baseReq.getType() == 5) {
                return sendPayReq(this.context, bundle);
            }
            if (baseReq.getType() == 7) {
                return sendJumpToBizProfileReq(this.context, bundle);
            }
            if (baseReq.getType() == 8) {
                return sendJumpToBizWebviewReq(this.context, bundle);
            }
            if (baseReq.getType() == 10) {
                return sendJumpToBizTempSessionReq(this.context, bundle);
            }
            if (baseReq.getType() == 9) {
                return sendAddCardToWX(this.context, bundle);
            }
            if (baseReq.getType() == 11) {
                return sendOpenRankListReq(this.context, bundle);
            }
            if (baseReq.getType() == 12) {
                return sendOpenWebview(this.context, bundle);
            }
            if (baseReq.getType() == 13) {
                return sendOpenBusiLuckyMoney(this.context, bundle);
            }
            if (baseReq.getType() == 14) {
                return createChatroom(this.context, bundle);
            }
            if (baseReq.getType() == 15) {
                return joinChatroom(this.context, bundle);
            }
            C4346a c4346a = new C4346a();
            c4346a.f6781Z = bundle;
            c4346a.f6780Y = "weixin://sendreq?appid=" + this.appId;
            c4346a.f6778W = "com.tencent.mm";
            c4346a.f6779X = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            return C4350a.m7881a(this.context, c4346a);
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "sendReq checkArgs fail");
            return false;
        }
    }

    public final void unregisterApp() {
        if (this.detached) {
            throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
        } else if (WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "unregisterApp, appId = " + this.appId);
            if (this.appId == null || this.appId.length() == 0) {
                C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
                return;
            }
            C4353b.m7892e("MicroMsg.SDK.WXApiImplV10", "unregister app " + this.context.getPackageName());
            C4347a c4347a = new C4347a();
            c4347a.f6784aa = "com.tencent.mm";
            c4347a.f6785ab = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_UNREGISTER";
            c4347a.f6782Y = "weixin://unregisterapp?appid=" + this.appId;
            C4348a.m7879a(this.context, c4347a);
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
        }
    }
}
