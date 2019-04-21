package com.mcdonalds.gma.p051cn.wxapi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.facebook.widget.ProfilePictureView;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.social.SocialLoginFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.p050mm.sdk.openapi.IWXAPI;
import com.tencent.p050mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.p050mm.sdk.openapi.WXAPIFactory;

@Instrumented
/* renamed from: com.mcdonalds.gma.cn.wxapi.WXEntryActivity */
public class WXEntryActivity extends FragmentActivity implements IWXAPIEventHandler, TraceFieldInterface {
    public static String AUTHEN_RESP = "Authen.Resp";
    private static final String TAG = WXEntryActivity.class.getSimpleName();
    public Trace _nr_trace;
    private IWXAPI mWeChatApi;
    private Resp resp;

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        EnsightenGestureRecognizerFactory.getFourFingerGestureRecognizer().process(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateDialog", new Object[]{new Integer(i), bundle});
        return super.onCreateDialog(i);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    /* Access modifiers changed, original: protected */
    public void onRestart() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onRestart", null);
        super.onRestart();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("WXEntryActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "WXEntryActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "WXEntryActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView(C2658R.layout.activity_wechat_signin);
        this.mWeChatApi = WXAPIFactory.createWXAPI(this, McDonaldsApplication.getInstance().getString(C2658R.string.wechat_api_app_id), false);
        this.mWeChatApi.handleIntent(getIntent(), this);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        setIntent(intent);
        this.mWeChatApi.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
        Ensighten.evaluateEvent(this, "onReq", new Object[]{baseReq});
    }

    public void onResp(BaseResp baseResp) {
        Ensighten.evaluateEvent(this, "onResp", new Object[]{baseResp});
        if (baseResp instanceof Resp) {
            this.resp = (Resp) baseResp;
            switch (this.resp.errCode) {
                case ProfilePictureView.LARGE /*-4*/:
                    finish();
                    break;
                case -2:
                    finish();
                    break;
                case 0:
                    if (SocialLoginFragment.mStateToken != null && SocialLoginFragment.mStateToken.toString().equals(this.resp.state)) {
                        Log.d(TAG, "Code: " + this.resp.code);
                        SocialLoginFragment.code = this.resp.code;
                    }
                    finish();
                    break;
                default:
                    finish();
                    break;
            }
        }
        finish();
    }
}
