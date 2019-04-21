package com.baidu.android.pushservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.message.C1502b;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.message.p040a.C1493i;
import com.baidu.android.pushservice.message.p040a.C1495k;
import com.baidu.android.pushservice.message.p040a.C1498m;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p037i.C1451p;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.util.C1543g;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1578v;
import com.baidu.android.pushservice.util.C1579w;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.mipush.sdk.PushMessageHelper;
import com.xiaomi.mipush.sdk.f;
import com.xiaomi.mipush.sdk.g;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.c;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.h;
import com.xiaomi.xmpush.thrift.n;
import com.xiaomi.xmpush.thrift.u;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PushMessageReceiver extends BroadcastReceiver {
    private static String ACTION_LAPP_RECEIVE = "com.baidu.android.pushservice.action.lapp.RECEIVE";
    public static final String TAG = PushMessageReceiver.class.getSimpleName();

    /* renamed from: com.baidu.android.pushservice.PushMessageReceiver$a */
    private static class C1290a extends Handler {
        /* renamed from: d */
        protected final WeakReference<Context> f4598d;

        public C1290a(Context context) {
            this.f4598d = new WeakReference(context);
        }
    }

    /* renamed from: com.baidu.android.pushservice.PushMessageReceiver$b */
    private enum C1293b {
        MSG_PASS(1),
        MSG_ARRIVED(2),
        MSG_CLICKED(3);
        
        /* renamed from: d */
        private int f4615d;

        private C1293b(int i) {
            this.f4615d = i;
        }

        /* renamed from: a */
        private int m5778a() {
            return this.f4615d;
        }
    }

    private Object getActionMsg(h hVar, boolean z, Context context) {
        try {
            b bVar = (b) decryptXmOrigMsg(context, hVar);
            if (bVar == null) {
                C1425a.m6443d(TAG, "receiving an un-recognized message. " + hVar.a);
                return null;
            }
            C1425a.m6441b(TAG, "processing a message, action=" + hVar.a());
            if (((n) bVar).l() != null) {
                return PushMessageHelper.generateMessage((n) bVar, hVar.m(), z);
            }
            C1425a.m6443d(TAG, "receive an empty message without push content!");
            return null;
        } catch (Throwable th) {
            C1425a.m6440a(TAG, th);
            return null;
        }
    }

    private String getLauncherActivityName(Context context, String str) {
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

    private void handleIntentUri(Context context, MiPushMessage miPushMessage, C1502b c1502b) {
        try {
            Map extra = miPushMessage.getExtra();
            if (extra != null && extra.containsKey("notify_effect")) {
                String str = (String) extra.get("notify_effect");
                C1425a.m6441b(TAG, "XM> onNotificationClicked, notify_effect=" + str);
                if (!TextUtils.isEmpty(str) && str.equals("2")) {
                    str = (String) extra.get("intent_uri");
                    if (!TextUtils.isEmpty(str)) {
                        PackageManager packageManager = context.getPackageManager();
                        Intent parseUri = Intent.parseUri(str, 0);
                        parseUri.setPackage(context.getPackageName());
                        if (packageManager.queryBroadcastReceivers(parseUri, 0).size() > 0) {
                            C1425a.m6442c(TAG, "XM> Intent broadcasted to app! ===> " + parseUri.toUri(0));
                            context.sendBroadcast(parseUri);
                        } else if (packageManager.queryIntentActivities(parseUri, 0).size() > 0) {
                            C1425a.m6442c(TAG, "XM> Intent sent to actvity! ===> " + parseUri.toUri(0));
                            parseUri.addFlags(268435456);
                            parseUri.putExtra("open_type", 1);
                            parseUri.putExtra("msgid", c1502b.f5251m);
                            context.startActivity(parseUri);
                        }
                    }
                }
            }
        } catch (Exception e) {
            C1425a.m6440a(TAG, e);
        }
    }

    private void handlePushMetaInfo(h hVar, Context context) {
        try {
            c m = hVar.m();
            e eVar = new e();
            eVar.b(hVar.h());
            eVar.a(m.b());
            eVar.a(m.d());
            if (!TextUtils.isEmpty(m.f())) {
                eVar.c(m.f());
            }
            g.a(context).a(eVar, a.f, false, hVar.m());
        } catch (Throwable th) {
            C1425a.m6440a(TAG, th);
        }
    }

    private void handleXiaoMiMessage(Intent intent, Context context) {
        try {
            int intExtra = intent.getIntExtra("message_type", 1);
            C1425a.m6441b(TAG, "XM> message_type= " + intExtra);
            List commandArguments;
            String str;
            switch (intExtra) {
                case 1:
                    PushMessageHandler.a a = f.a(context).a(intent);
                    if (a == null) {
                        C1425a.m6443d(TAG, "XM> realMessage is null, go on handle");
                        byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                        boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
                        String stringExtra = intent.getStringExtra("mrt");
                        if (byteArrayExtra == null) {
                            C1425a.m6443d(TAG, "XM> receiving an empty message!");
                            return;
                        }
                        h hVar = new h();
                        u.a(hVar, byteArrayExtra);
                        com.xiaomi.mipush.sdk.a a2 = com.xiaomi.mipush.sdk.a.a(context);
                        c m = hVar.m();
                        if (!(hVar.a() != a.e || m == null || a2.l() || booleanExtra)) {
                            if (m != null) {
                                hVar.m().a("mrt", stringExtra);
                                hVar.m().a("mat", Long.toString(System.currentTimeMillis()));
                            }
                            handlePushMetaInfo(hVar, context);
                        }
                        Object actionMsg = getActionMsg(hVar, booleanExtra, context);
                        if (actionMsg != null) {
                            C1425a.m6441b(TAG, "XM> msg clicked-----------------");
                            handleXiaomiMessageCallBack(context, (MiPushMessage) actionMsg, C1293b.MSG_CLICKED.m5778a());
                            return;
                        }
                        return;
                    } else if (a instanceof MiPushMessage) {
                        C1425a.m6441b(TAG, "XM> is a MiPushMessage");
                        if (((MiPushMessage) a).getPassThrough() == 1) {
                            handleXiaomiMessageCallBack(context, (MiPushMessage) a, C1293b.MSG_PASS.m5778a());
                            C1425a.m6441b(TAG, "XM> passthrough msg, content = " + ((MiPushMessage) a).getContent());
                            return;
                        } else if (((MiPushMessage) a).isNotified()) {
                            C1425a.m6441b(TAG, "XM> isNotified=true");
                            handleXiaomiMessageCallBack(context, (MiPushMessage) a, C1293b.MSG_CLICKED.m5778a());
                            return;
                        } else {
                            C1425a.m6441b(TAG, "XM> MessageArrived");
                            return;
                        }
                    } else if ((a instanceof MiPushCommandMessage) && TextUtils.equals(((MiPushCommandMessage) a).getCommand(), JiceArgs.EVENT_REGISTER)) {
                        MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) a;
                        commandArguments = miPushCommandMessage.getCommandArguments();
                        if (commandArguments == null || commandArguments.isEmpty()) {
                            C1420g.m6429d(context);
                            C1425a.m6444e(TAG, miPushCommandMessage.getReason());
                            return;
                        }
                        str = (String) commandArguments.get(0);
                        C1425a.m6441b(TAG, "XM> regId= " + str);
                        if (!TextUtils.isEmpty(str)) {
                            C1420g.m6421a(context, str);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                case 3:
                case 4:
                    Serializable serializableExtra = intent.getSerializableExtra("key_command");
                    if (serializableExtra != null && TextUtils.equals(((MiPushCommandMessage) serializableExtra).getCommand(), JiceArgs.EVENT_REGISTER)) {
                        commandArguments = ((MiPushCommandMessage) serializableExtra).getCommandArguments();
                        if (commandArguments == null || commandArguments.isEmpty()) {
                            C1420g.m6429d(context);
                            C1426b.m6447b(TAG, "the xiaomi appid or appkey is error, please check!", context);
                            return;
                        }
                        str = (String) commandArguments.get(0);
                        C1425a.m6441b(TAG, "XM> regId= " + str);
                        if (!TextUtils.isEmpty(str)) {
                            C1420g.m6421a(context, str);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    C1425a.m6444e(TAG, "XM> err for msg type!!!");
                    return;
            }
        } catch (Throwable th) {
            C1425a.m6440a(TAG, th);
        }
        C1425a.m6440a(TAG, th);
    }

    private void handleXiaomiMessageCallBack(Context context, MiPushMessage miPushMessage, int i) {
        int i2 = 0;
        try {
            String content = miPushMessage.getContent();
            C1425a.m6441b(TAG, "XM> " + content);
            C1502b c1502b = new C1502b();
            String str = "";
            boolean msgFromXMConsole = msgFromXMConsole(content);
            if (msgFromXMConsole) {
                C1425a.m6441b(TAG, "XM> msgFromXmConsole=true-----------------");
                c1502b.f5252n = C1498m.MSG_TYPE_SINGLE_PRIVATE.mo13970a();
            } else {
                content = c1502b.mo13973a(content);
            }
            if (C1578v.m7155y(context)) {
                if (i != C1293b.MSG_CLICKED.m5778a() && C1578v.m7152w(context, c1502b.f5251m)) {
                    i2 = 4;
                    C1425a.m6443d(TAG, "XM> msgid is duplicated!!! msgid=" + c1502b.f5251m);
                } else if (c1502b.f5252n == C1498m.MSG_TYPE_APPSTAT_COMMAND.mo13970a()) {
                    C1578v.m7052A(context);
                } else if (c1502b.f5252n == C1498m.MSG_TYPE_LBS_APPLIST_COMMAND.mo13970a()) {
                    C1578v.m7053B(context);
                } else if (c1502b.f5252n == C1498m.MSG_TYPE_PRIVATE_MESSAGE.mo13970a() || c1502b.f5252n == C1498m.MSG_TYPE_MULTI_PRIVATE.mo13970a() || c1502b.f5252n == C1498m.MSG_TYPE_SINGLE_PRIVATE.mo13970a() || c1502b.f5252n == C1498m.MSG_TYPE_MULTI_PRIVATE_NOTIFICATION.mo13970a() || c1502b.f5252n == C1498m.MSG_TYPE_SINGLE_PUBLIC.mo13970a() || c1502b.f5252n == C1498m.MSG_TYPE_MULTI_PUBLIC.mo13970a()) {
                    if (i == C1293b.MSG_PASS.m5778a()) {
                        onMessage(context, content, null);
                        C1425a.m6441b(TAG, "XM> onMessage call back-----------------");
                    } else if (i == C1293b.MSG_ARRIVED.m5778a()) {
                        onNotificationArrived(context, miPushMessage.getTitle(), miPushMessage.getDescription(), content);
                        C1425a.m6441b(TAG, "XM> onNotificationArrived call back-----------------");
                    } else if (i == C1293b.MSG_CLICKED.m5778a()) {
                        onNotificationClicked(context, miPushMessage.getTitle(), miPushMessage.getDescription(), content);
                        handleIntentUri(context, miPushMessage, c1502b);
                        C1425a.m6441b(TAG, "XM> onNotificationClicked call back-----------------");
                    }
                    if (i != C1293b.MSG_PASS.m5778a()) {
                        i2 = 1;
                    }
                } else {
                    C1425a.m6441b(TAG, "XM> pXmMsg.exType is unknow-----------------");
                    i2 = 6;
                }
            }
            if (!msgFromXMConsole) {
                if (i == C1293b.MSG_PASS.m5778a()) {
                    C1543g.m6921a(context, c1502b.f5251m, i2, c1502b.f5248j, c1502b.f5249k + "", 2);
                } else if (i == C1293b.MSG_ARRIVED.m5778a()) {
                    C1543g.m6921a(context, c1502b.f5251m, i2, c1502b.f5248j, c1502b.f5249k + "", 2);
                } else if (i == C1293b.MSG_CLICKED.m5778a()) {
                    C1543g.m6923a(context, c1502b.f5251m, c1502b.f5248j, c1502b.f5249k + "", 2);
                }
            }
        } catch (Throwable th) {
            C1425a.m6440a(TAG, th);
        }
    }

    private static boolean msgFromXMConsole(String str) {
        try {
            if (JSONObjectInstrumentation.init(str) != null) {
                return false;
            }
            C1425a.m6443d(TAG, "msg from xiaomi platform!!!!");
            return true;
        } catch (Exception e) {
            C1425a.m6443d(TAG, "msg from xiaomi platform!!!!");
            return true;
        }
    }

    private void sendCallback(Context context, Intent intent, int i) {
        intent.putExtra("bd.cross.request.RESULT_CODE", i);
        intent.setClass(context, CommandService.class);
        intent.putExtra("bd.cross.request.COMMAND_TYPE", "bd.cross.command.MESSAGE_ACK");
        context.startService(intent);
    }

    private void startApplicationLauncher(Context context, String str, String str2, String str3) {
        Intent parseUri;
        if (str3 != null) {
            try {
                parseUri = Intent.parseUri(str3, 0);
            } catch (Exception e) {
                C1425a.m6440a(TAG, e);
                return;
            }
        }
        parseUri = new Intent();
        String launcherActivityName = getLauncherActivityName(context, str);
        if (launcherActivityName != null) {
            parseUri.setClassName(str, launcherActivityName);
            parseUri.setFlags(parseUri.getFlags() | 268435456);
            parseUri.putExtra("open_type", 1);
            parseUri.putExtra("msgid", str2);
            context.startActivity(parseUri);
        }
    }

    public Object decryptXmOrigMsg(Context context, h hVar) {
        try {
            byte[] doFinal;
            if (hVar.c()) {
                byte[] bArr = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};
                SecretKeySpec secretKeySpec = new SecretKeySpec(com.xiaomi.channel.commonutils.string.a.a(com.xiaomi.mipush.sdk.a.a(context).f()), "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, ivParameterSpec);
                doFinal = instance.doFinal(hVar.f());
            } else {
                doFinal = hVar.f();
            }
            n nVar = new n();
            if (nVar == null) {
                return nVar;
            }
            u.a((b) nVar, doFinal);
            return nVar;
        } catch (Throwable th) {
            C1425a.m6440a(TAG, th);
            return null;
        }
    }

    public abstract void onBind(Context context, int i, String str, String str2, String str3, String str4);

    public void onCheckBindState(Context context, int i, String str, boolean z) {
    }

    public abstract void onDelTags(Context context, int i, List<String> list, List<String> list2, String str);

    public abstract void onListTags(Context context, int i, List<String> list, String str);

    public abstract void onMessage(Context context, String str, String str2);

    public abstract void onNotificationArrived(Context context, String str, String str2, String str3);

    public abstract void onNotificationClicked(Context context, String str, String str2, String str3);

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            final String stringExtra;
            final String stringExtra2;
            String string;
            String string2;
            C1502b a;
            int i;
            if (action.equals("com.baidu.android.pushservice.action.MESSAGE") || action.equals("com.baidu.android.pushservice.action.LAPP_MESSAGE")) {
                if (!ModeConfig.isProxyMode(context) && intent.getExtras() != null) {
                    final byte[] byteArrayExtra = intent.getByteArrayExtra("baidu_message_secur_info");
                    final byte[] byteArrayExtra2 = intent.getByteArrayExtra("baidu_message_body");
                    stringExtra = intent.getStringExtra("message_id");
                    final int intExtra = intent.getIntExtra("baidu_message_type", -1);
                    stringExtra2 = intent.getStringExtra("app_id");
                    if (byteArrayExtra == null || byteArrayExtra2 == null || TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2) || intExtra == -1) {
                        C1425a.m6444e(TAG, " receive message error !");
                        sendCallback(context, intent, 2);
                    } else if (C1578v.m7140q(context, stringExtra)) {
                        C1425a.m6444e(TAG, " receive message duplicated !");
                        sendCallback(context, intent, 4);
                    } else {
                        final Context context2 = context;
                        final Intent intent2 = intent;
                        final C12911 c12911 = new C1290a(context) {
                            public void handleMessage(Message message) {
                                if (this.f4598d.get() != null) {
                                    PushMessageReceiver.this.onMessage((Context) this.f4598d.get(), message.getData().getString(HexAttributes.HEX_ATTR_MESSAGE), message.getData().getString("custom_content"));
                                    PushMessageReceiver.this.sendCallback(context2, intent2, 10);
                                }
                            }
                        };
                        final Context context3 = context;
                        final Intent intent3 = intent;
                        new Thread() {
                            public void run() {
                                String[] a = C1493i.m6771a(context3, intExtra, stringExtra2, stringExtra, byteArrayExtra, byteArrayExtra2);
                                if (a == null || a.length != 2) {
                                    C1425a.m6444e(PushMessageReceiver.TAG, " check message error !");
                                    PushMessageReceiver.this.sendCallback(context3, intent3, 9);
                                    return;
                                }
                                Message message = new Message();
                                Bundle bundle = new Bundle();
                                bundle.putString(HexAttributes.HEX_ATTR_MESSAGE, a[0]);
                                bundle.putString("custom_content", a[1]);
                                message.setData(bundle);
                                c12911.sendMessage(message);
                                C1578v.m7095b("message " + a[0] + " at time of " + System.currentTimeMillis(), context3);
                                if (C1328a.m6006b() > 0) {
                                    C1451p.m6592b(context3, stringExtra2, stringExtra, intExtra, a[0].getBytes(), 0, C1449n.f5116a);
                                }
                            }
                        }.start();
                    }
                }
            } else if (action.equals("com.baidu.android.pushservice.action.RECEIVE") || action.equals(ACTION_LAPP_RECEIVE)) {
                String stringExtra3 = intent.getStringExtra("method");
                if (!TextUtils.isEmpty(stringExtra3)) {
                    int intExtra2 = intent.getIntExtra("error_msg", 0);
                    action = "";
                    if (intent.getByteArrayExtra("content") != null) {
                        action = new String(intent.getByteArrayExtra("content"));
                    }
                    JSONObject init;
                    JSONArray jSONArray;
                    ArrayList arrayList;
                    ArrayList arrayList2;
                    int i2;
                    JSONObject jSONObject;
                    if (stringExtra3.equals("com.baidu.android.pushservice.action.notification.ARRIVED")) {
                        action = intent.getStringExtra("notification_title");
                        stringExtra3 = intent.getStringExtra("notification_content");
                        onNotificationArrived(context, action, intent.getStringExtra("extra_extra_custom_content"), stringExtra3);
                    } else if (stringExtra3.equals("method_bind") || stringExtra3.equals("method_deal_lapp_bind_intent")) {
                        if (C1328a.m6006b() > 0) {
                            C1456u.m6614a(context, "039905", intExtra2, action);
                        }
                        if (intExtra2 != 0 || TextUtils.isEmpty(action)) {
                            onBind(context, intExtra2, null, null, null, null);
                            if (C1328a.m6006b() > 0) {
                                C1456u.m6614a(context, "039905", -1, String.valueOf(intExtra2));
                            }
                            C1578v.m7095b("onBind from" + context.getPackageName() + " errorCode " + intExtra2 + " at time of " + System.currentTimeMillis(), context);
                            return;
                        }
                        try {
                            init = JSONObjectInstrumentation.init(action);
                            stringExtra2 = init.getString("request_id");
                            init = init.getJSONObject("response_params");
                            string = init.getString("appid");
                            PushSettings.m5881b(context, string);
                            String string3 = init.getString("channel_id");
                            stringExtra = init.getString("user_id");
                            long j = 0;
                            String str = null;
                            String str2 = null;
                            if (intent.hasExtra("real_bind")) {
                                j = System.currentTimeMillis();
                                str = intent.getStringExtra("access_token");
                                str2 = intent.getStringExtra("secret_key");
                            }
                            Context context4 = context;
                            C1550n.m6959a(context4, string, string3, stringExtra2, stringExtra, true, C1578v.m7108d(context, context.getPackageName()), j, str, str2);
                            onBind(context, intExtra2, string, stringExtra, string3, stringExtra2);
                            C1578v.m7095b("PushMessageReceiver#onBind from" + context.getPackageName() + ", errorCode= " + intExtra2 + ", appid=  " + string + ", userId=" + stringExtra + ", channelId=" + string3 + ", requestId=" + stringExtra2 + ", at time of " + System.currentTimeMillis(), context);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(context.getPackageName());
                            stringBuilder.append(",");
                            stringBuilder.append(string);
                            stringBuilder.append(",");
                            stringBuilder.append(stringExtra);
                            action = C1332b.m6020a(context).mo13596b(stringBuilder.toString());
                            C1574s.m7032g(context, action);
                            if (C1578v.m7056E(context)) {
                                C1579w.m7158a(context, context.getPackageName() + ".self_push_sync", "bindinfo", action);
                            }
                        } catch (Exception e) {
                            Throwable th = e;
                            onBind(context, intExtra2, null, null, null, null);
                            if (C1328a.m6006b() > 0) {
                                C1456u.m6614a(context, "039905", -1, C1578v.m7070a(th));
                            }
                            C1578v.m7095b("onBind from" + context.getPackageName() + " errorCode " + intExtra2 + " at time of " + System.currentTimeMillis() + " exception " + C1578v.m7070a(th), context);
                        }
                    } else if (stringExtra3.equals("method_unbind") || stringExtra3.equals("method_lapp_unbind")) {
                        Editor edit = context.getSharedPreferences("bindcache", 0).edit();
                        try {
                            onUnbind(context, intExtra2, JSONObjectInstrumentation.init(action).getString("request_id"));
                            edit.putBoolean("bind_status", false);
                            edit.commit();
                        } catch (JSONException e2) {
                            onUnbind(context, intExtra2, null);
                            edit.putBoolean("bind_status", false);
                            edit.commit();
                        }
                        if (ModeConfig.isXiaomiProxyMode(context)) {
                            MiPushClient.unregisterPush(context);
                            C1425a.m6441b(TAG, "XM> MiPushClient.unregisterPush is call!!!");
                        }
                        C1578v.m7095b("unbind from" + context.getPackageName() + " errorCode " + intExtra2 + " at time of " + System.currentTimeMillis(), context);
                    } else if (stringExtra3.equals("method_get_lapp_bind_state")) {
                        onCheckBindState(context, intExtra2, intent.getStringExtra("secret_key"), intent.getBooleanExtra("lapp_bind_state", false));
                    } else if (stringExtra3.equals("method_set_tags") || stringExtra3.equals("method_set_lapp_tags")) {
                        try {
                            init = JSONObjectInstrumentation.init(action);
                            stringExtra = init.getString("request_id");
                            if (TextUtils.isEmpty(init.optString("error_msg"))) {
                                init = init.optJSONObject("response_params");
                                if (init != null) {
                                    jSONArray = init.getJSONArray("details");
                                    if (jSONArray != null) {
                                        arrayList = new ArrayList();
                                        arrayList2 = new ArrayList();
                                        for (i2 = 0; i2 < jSONArray.length(); i2++) {
                                            jSONObject = jSONArray.getJSONObject(i2);
                                            string2 = jSONObject.getString("tag");
                                            if (jSONObject.getInt("result") == 0) {
                                                arrayList.add(string2);
                                            } else {
                                                arrayList2.add(string2);
                                            }
                                        }
                                        onSetTags(context, intExtra2, arrayList, arrayList2, stringExtra);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            onSetTags(context, intExtra2, new ArrayList(), new ArrayList(), stringExtra);
                        } catch (JSONException e3) {
                            onSetTags(context, intExtra2, null, null, null);
                        }
                    } else if (stringExtra3.equals("method_del_tags") || stringExtra3.equals("method_del_lapp_tags")) {
                        try {
                            init = JSONObjectInstrumentation.init(action);
                            stringExtra = init.getString("request_id");
                            init = init.getJSONObject("response_params");
                            if (init != null) {
                                jSONArray = init.getJSONArray("details");
                                if (jSONArray != null) {
                                    arrayList = new ArrayList();
                                    arrayList2 = new ArrayList();
                                    for (i2 = 0; i2 < jSONArray.length(); i2++) {
                                        jSONObject = jSONArray.getJSONObject(i2);
                                        string2 = jSONObject.getString("tag");
                                        if (jSONObject.getInt("result") == 0) {
                                            arrayList.add(string2);
                                        } else {
                                            arrayList2.add(string2);
                                        }
                                    }
                                    onDelTags(context, intExtra2, arrayList, arrayList2, stringExtra);
                                }
                            }
                        } catch (JSONException e4) {
                            onDelTags(context, intExtra2, null, null, null);
                        }
                    } else if (stringExtra3.equals("method_listtags") || stringExtra3.equals("method_list_lapp_tags")) {
                        try {
                            action = JSONObjectInstrumentation.init(action).getString("request_id");
                            onListTags(context, intExtra2, intent.getStringArrayListExtra("tags_list"), action);
                        } catch (JSONException e5) {
                            onListTags(context, intExtra2, null, null);
                        }
                    }
                }
            } else if (action.equals("com.baidu.android.pushservice.action.notification.CLICK")) {
                onNotificationClicked(context, intent.getStringExtra("notification_title"), intent.getStringExtra("notification_content"), intent.getStringExtra("extra_extra_custom_content"));
            } else if (action.equals("com.huawei.android.push.intent.REGISTRATION")) {
                if (ModeConfig.isHuaweiProxyMode(context)) {
                    try {
                        action = new String(intent.getByteArrayExtra("device_token"), Utf8Charset.NAME);
                        C1425a.m6442c(TAG, "huawei token arrive, value=" + action);
                        if (!TextUtils.isEmpty(action)) {
                            C1420g.m6421a(context, action);
                        }
                    } catch (Exception e6) {
                        C1425a.m6440a(TAG, e6);
                    }
                }
            } else if (action.equals("com.huawei.intent.action.PUSH")) {
                if (ModeConfig.isHuaweiProxyMode(context)) {
                    try {
                        action = new String(intent.getByteArrayExtra("selfshow_info"), Utf8Charset.NAME);
                        C1425a.m6442c(TAG, "Receive Notify Message   = " + action);
                        if (!TextUtils.isEmpty(action) && context != null) {
                            a = C1495k.m6777a(context, action);
                            PublicMsg a2 = a.mo13972a(context);
                            if (a2 != null) {
                                if (C1578v.m7155y(context)) {
                                    if (C1578v.m7152w(context, a.f5239a)) {
                                        i = 4;
                                    } else {
                                        PushServiceReceiver.m5866a(context, context.getPackageName(), "com.baidu.android.pushservice.CommandService", a2);
                                        i = 1;
                                        C1425a.m6442c(TAG, "show HWNotify Message");
                                    }
                                } else {
                                    i = 0;
                                }
                                C1543g.m6921a(context, a.f5239a, i, a.f5248j, a.f5249k + "", 1);
                            }
                        }
                    } catch (Exception e62) {
                        C1425a.m6440a(TAG, e62);
                    }
                }
            } else if (action.equals("com.huawei.android.push.intent.RECEIVE")) {
                if (ModeConfig.isHuaweiProxyMode(context)) {
                    byte[] byteArrayExtra3 = intent.getByteArrayExtra("msg_data");
                    byte[] byteArrayExtra4 = intent.getByteArrayExtra("device_token");
                    try {
                        string = new String(byteArrayExtra3, "utf-8");
                        string2 = new String(byteArrayExtra4, "utf-8");
                        a = new C1502b();
                        String a3 = a.mo13973a(string);
                        if (C1578v.m7155y(context)) {
                            if (C1578v.m7152w(context, a.f5251m)) {
                                i = 4;
                            } else if (a.f5252n == C1498m.MSG_TYPE_APPSTAT_COMMAND.mo13970a()) {
                                C1578v.m7052A(context);
                                i = 0;
                            } else if (a.f5252n == C1498m.MSG_TYPE_LBS_APPLIST_COMMAND.mo13970a()) {
                                C1578v.m7053B(context);
                                i = 0;
                            } else if (a.f5252n == C1498m.MSG_TYPE_PRIVATE_MESSAGE.mo13970a() || a.f5252n == C1498m.MSG_TYPE_SINGLE_PRIVATE.mo13970a()) {
                                onMessage(context, a3, null);
                                i = 0;
                                C1425a.m6442c(TAG, "receive HWPassthrough Message");
                            } else {
                                i = 6;
                            }
                        } else {
                            i = 0;
                        }
                        C1543g.m6921a(context, a.f5251m, i, a.f5248j, a.f5249k + "", 1);
                        C1425a.m6442c(TAG, "receive huawei passthrough message  =  " + a3 + "   DeviceToken = " + string2);
                    } catch (Exception e622) {
                        C1425a.m6440a(TAG, e622);
                    }
                }
            } else if (action.equals("com.xiaomi.mipush.RECEIVE_MESSAGE")) {
                C1425a.m6441b(TAG, "XM> action = " + action);
                if (ModeConfig.isXiaomiProxyMode(context)) {
                    handleXiaoMiMessage(intent, context);
                } else {
                    C1425a.m6443d(TAG, "XM> not xiaomi proxy mode! ");
                }
            } else if (action.equals("com.xiaomi.mipush.MESSAGE_ARRIVED")) {
                C1425a.m6441b(TAG, "XM> action = " + action);
                if (ModeConfig.isXiaomiProxyMode(context)) {
                    handleXiaomiMessageCallBack(context, (MiPushMessage) f.a(context).a(intent), C1293b.MSG_ARRIVED.m5778a());
                    return;
                }
                C1425a.m6443d(TAG, "XM> not xiaomi proxy mode! ");
            }
        }
    }

    public abstract void onSetTags(Context context, int i, List<String> list, List<String> list2, String str);

    public abstract void onUnbind(Context context, int i, String str);
}
