package com.mcdonalds.gma.p051cn.wxapi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.view.MotionEvent;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.ordering.checkin.OrderCheckinFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.openapi.IWXAPI;
import com.tencent.p050mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.p050mm.sdk.openapi.WXAPIFactory;

@Instrumented
/* renamed from: com.mcdonalds.gma.cn.wxapi.WXPayEntryActivity */
public class WXPayEntryActivity extends FragmentActivity implements IWXAPIEventHandler, TraceFieldInterface {
    public Trace _nr_trace;
    private IWXAPI api;

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
        TraceMachine.startTracing("WXPayEntryActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "WXPayEntryActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "WXPayEntryActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView(C2658R.layout.wechat_pay_result);
        this.api = WXAPIFactory.createWXAPI(this, McDonaldsApplication.getInstance().getString(C2658R.string.wechat_api_app_id));
        this.api.handleIntent(getIntent(), this);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    public void onReq(BaseReq req) {
        Ensighten.evaluateEvent(this, "onReq", new Object[]{req});
    }

    public void onResp(BaseResp resp) {
        Ensighten.evaluateEvent(this, "onResp", new Object[]{resp});
        if (resp.getType() == 5) {
            if (resp.errCode == 0) {
                OrderCheckinFragment.wechatPaymentResult = 0;
            } else if (resp.errCode == -2) {
                OrderCheckinFragment.wechatPaymentResult = -2;
                OrderCheckinFragment.sWeChatPaymentRespString = getString(C2658R.string.wechat_payment_cancelled);
            } else {
                OrderCheckinFragment.wechatPaymentResult = -1;
                OrderCheckinFragment.sWeChatPaymentRespString = getString(C2658R.string.wechat_payment_generic_error);
            }
            finish();
        }
    }
}
