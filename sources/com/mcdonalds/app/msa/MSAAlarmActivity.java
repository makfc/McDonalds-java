package com.mcdonalds.app.msa;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.System;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController.OfferSelection;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Offer.AndCondition;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

@Instrumented
public class MSAAlarmActivity extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;
    private int mHourTo;
    private MediaPlayer mMediaPlayer;
    private Offer mOffer;

    /* renamed from: com.mcdonalds.app.msa.MSAAlarmActivity$1 */
    class C32571 implements OnClickListener {
        C32571() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$000", new Object[]{MSAAlarmActivity.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$000", new Object[]{MSAAlarmActivity.this}).stop();
            }
            MSAAlarmActivity.this.finish();
            MSASettings.scheduleNotification(MSAAlarmActivity.this, Calendar.getInstance().getTimeInMillis() + 540000);
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MSAAlarmActivity$2 */
    class C32592 implements OnCheckedChangeListener {

        /* renamed from: com.mcdonalds.app.msa.MSAAlarmActivity$2$1 */
        class C32581 implements AsyncListener {
            C32581() {
            }

            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            }
        }

        C32592() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Intent intent;
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{compoundButton, new Boolean(b)});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$000", new Object[]{MSAAlarmActivity.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$000", new Object[]{MSAAlarmActivity.this}).stop();
            }
            MSAAlarmActivity.this.finish();
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$100", new Object[]{MSAAlarmActivity.this}) != null) {
                ServiceUtils.getSharedInstance().removeOffersCache();
                intent = new Intent(MSAAlarmActivity.this, OfferActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("offer_selection_type", OfferSelection.Nearby.ordinal());
                extras.putParcelable("extra_offer", Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$100", new Object[]{MSAAlarmActivity.this}));
                intent.putExtras(extras);
                OffersModule m = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                c.set(14, 0);
                c.set(13, 0);
                c.set(12, 0);
                c.set(11, Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$200", new Object[]{MSAAlarmActivity.this}));
                m.setOfferExpiration(Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$100", new Object[]{MSAAlarmActivity.this}).getOfferId(), Calendar.getInstance().getTime(), c.getTime(), new C32581());
            } else {
                intent = new Intent(MSAAlarmActivity.this, MSAGenericActivity.class);
            }
            MSAAlarmActivity.this.startActivity(intent);
            if (b) {
                HashMap<String, Object> jiceMap = new HashMap();
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_MSA_ALARM_STOP);
                Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
                MSAAlarmActivity.access$400(MSAAlarmActivity.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$300", new Object[]{MSAAlarmActivity.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$100", new Object[]{MSAAlarmActivity.this}), new Boolean(true)}), false);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MSAAlarmActivity$3 */
    class C32603 implements AsyncListener<List<Offer>> {
        C32603() {
        }

        public void onResponse(List<Offer> offerList, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{offerList, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null && offerList != null) {
                MSAAlarmActivity.access$102(MSAAlarmActivity.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$500", new Object[]{MSAAlarmActivity.this, offerList}));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MSAAlarmActivity$4 */
    class C32614 implements AsyncListener<CustomerProfile> {
        C32614() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            MSAAlarmActivity.access$600(MSAAlarmActivity.this);
        }
    }

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

    static /* synthetic */ Offer access$102(MSAAlarmActivity x0, Offer x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$102", new Object[]{x0, x1});
        x0.mOffer = x1;
        return x1;
    }

    static /* synthetic */ void access$400(MSAAlarmActivity x0, Intent x1, boolean x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$400", new Object[]{x0, x1, new Boolean(x2)});
        x0.showNotification(x1, x2);
    }

    static /* synthetic */ void access$600(MSAAlarmActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAAlarmActivity", "access$600", new Object[]{x0});
        x0.getUserOffer();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MSAAlarmActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MSAAlarmActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MSAAlarmActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView(C2658R.layout.activity_msa_alarm);
        Calendar c = Calendar.getInstance();
        TextView hour = (TextView) findViewById(C2358R.C2357id.msa_time_hour);
        int hourOfDay = c.get(11);
        if (hourOfDay > 12) {
            hourOfDay -= 12;
        }
        hour.setText("" + hourOfDay);
        ((TextView) findViewById(C2358R.C2357id.msa_time_minute)).setText("" + new DecimalFormat("00").format((long) c.get(12)));
        ((TextView) findViewById(C2358R.C2357id.msa_time_am_pm)).setText(c.get(9) == 0 ? C2658R.string.msa_time_am : C2658R.string.msa_time_pm);
        ((TextView) findViewById(C2358R.C2357id.msa_alarm_greeting)).setText(c.get(9) == 0 ? C2658R.string.title_morning : C2658R.string.title_afternoon);
        findViewById(C2358R.C2357id.msa_alarm_snooze).setOnClickListener(new C32571());
        ((Switch) findViewById(C2358R.C2357id.msa_alarm_switch)).setOnCheckedChangeListener(new C32592());
        playAudio();
        setupOffer();
        showNotification(createNotificationIntent(null, false), true);
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_MSA_ALARM);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    private void playAudio() {
        Ensighten.evaluateEvent(this, "playAudio", null);
        int alarmType = MSASettings.getAlarmType(this);
        if (alarmType == MSATuneItem.MSA_TUNE_FROM_PHONE) {
            this.mMediaPlayer = MediaPlayer.create(this, Uri.parse(MSASettings.getAlarmId(this)));
        } else if (alarmType == MSATuneItem.MSA_TUNE_FROM_APP || alarmType == MSATuneItem.MSA_TUNE_RANDOM) {
            try {
                this.mMediaPlayer = MediaPlayer.create(this, Integer.parseInt(MSASettings.getAlarmId(this)));
            } catch (NumberFormatException e) {
            }
        } else {
            this.mMediaPlayer = MediaPlayer.create(this, System.DEFAULT_RINGTONE_URI);
        }
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setLooping(true);
            this.mMediaPlayer.start();
        }
    }

    private void setupOffer() {
        Ensighten.evaluateEvent(this, "setupOffer", null);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule == null) {
            return;
        }
        if (customerModule.isLoggedIn()) {
            getUserOffer();
        } else {
            loginUser();
        }
    }

    private void getUserOffer() {
        Ensighten.evaluateEvent(this, "getUserOffer", null);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        OffersModule offersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        String username = LocalDataManager.getSharedInstance().getPrefSavedLogin();
        if (offersModule == null || customerModule == null || username == null) {
            UIUtils.stopActivityIndicator();
            return;
        }
        String store = "";
        if (OrderManager.getInstance().getCurrentStore() != null) {
            store = String.valueOf(OrderManager.getInstance().getCurrentStore().getStoreId());
        }
        UIUtils.startActivityIndicator((Context) this, (int) C2658R.string.label_loading_offers);
        Double d = null;
        offersModule.getCustomerOffers(username, null, d, Arrays.asList(new String[]{store}), new C32603());
    }

    private void loginUser() {
        boolean autoLogin;
        boolean useSocial = false;
        Ensighten.evaluateEvent(this, "loginUser", null);
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        String prefSavedLogin = localDataManager.getPrefSavedLogin();
        String prefSavedLoginPass = localDataManager.getPrefSavedLoginPass();
        int prefSavedSocialID = localDataManager.getPrefSavedSocialNetworkId();
        if (TextUtils.isEmpty(prefSavedLogin) || (TextUtils.isEmpty(prefSavedLoginPass) && prefSavedSocialID == -1)) {
            autoLogin = false;
        } else {
            autoLogin = true;
        }
        if (autoLogin) {
            if (prefSavedSocialID != -1) {
                useSocial = true;
            }
            AuthenticationParameters parameters = new AuthenticationParameters();
            if (!useSocial) {
                parameters.setUserName(prefSavedLogin);
                parameters.setPassword(prefSavedLoginPass);
                authenticateUser(parameters);
            } else if (prefSavedSocialID == 3) {
                parameters.setUserName(prefSavedLogin);
                parameters.setAllowSocialLoginWithoutEmail(true);
                parameters.setUsingSocialLogin(true);
                parameters.setSocialServiceID(prefSavedSocialID);
                parameters.setSocialAuthenticationToken(prefSavedLoginPass);
                parameters.setSocialUserID(prefSavedLogin);
                authenticateUser(parameters);
            }
        }
    }

    private void authenticateUser(AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "authenticateUser", new Object[]{parameters});
        CustomerModule cm = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (cm != null) {
            UIUtils.startActivityIndicator((Context) this, (int) C2658R.string.label_loading_offers);
            cm.authenticate(parameters, new C32614());
        }
    }

    private Offer getMsaOffer(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "getMsaOffer", new Object[]{offers});
        if (!offers.isEmpty()) {
            Calendar current = Calendar.getInstance();
            int dayOfWeekNow = current.get(7);
            int hourOfDayNow = current.get(11);
            int minuteNow = current.get(12);
            long timeNow = current.getTimeInMillis();
            for (Offer offer : offers) {
                if (!(offer.getOfferType() != OfferType.OFFER_TYPE_MSA || offer.isExpired().booleanValue() || offer.isExpirationChanged().booleanValue())) {
                    Date validFrom = offer.getLocalValidFrom();
                    Date validThrough = offer.getLocalValidThrough();
                    long timeValidFrom = validFrom.getTime();
                    long timeValidThrough = validThrough.getTime();
                    if (timeNow >= timeValidFrom && timeNow <= timeValidThrough) {
                        for (AndCondition c : offer.getAndConditions()) {
                            if (dayOfWeekNow == c.getDayOfWeek()) {
                                if (timeCheck(hourOfDayNow, minuteNow, c.getHourOfDayFrom(), c.getHourOfDayTo(), c.getMinuteFrom(), c.getMinuteTo())) {
                                    this.mHourTo = c.getHourOfDayTo();
                                    return offer;
                                }
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private boolean timeCheck(int hourOfDayNow, int minuteNow, int hourOfDayFrom, int hourOfDayTo, int minuteFrom, int minuteTo) {
        Ensighten.evaluateEvent(this, "timeCheck", new Object[]{new Integer(hourOfDayNow), new Integer(minuteNow), new Integer(hourOfDayFrom), new Integer(hourOfDayTo), new Integer(minuteFrom), new Integer(minuteTo)});
        return (hourOfDayNow * 60) + minuteNow >= (hourOfDayFrom * 60) + minuteFrom && (hourOfDayNow * 60) + minuteNow <= (hourOfDayTo * 60) + minuteTo;
    }

    private Intent createNotificationIntent(Offer offer, boolean redirect) {
        Ensighten.evaluateEvent(this, "createNotificationIntent", new Object[]{offer, new Boolean(redirect)});
        Intent intent;
        if (!redirect) {
            intent = new Intent(this, MSAAlarmActivity.class);
            intent.setFlags(536870912);
            return intent;
        } else if (offer == null) {
            return new Intent(this, MSAGenericActivity.class);
        } else {
            intent = new Intent(this, OfferActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("offer_selection_type", OfferSelection.Nearby.ordinal());
            extras.putParcelable("extra_offer", offer);
            intent.putExtras(extras);
            return intent;
        }
    }

    private void showNotification(Intent intent, boolean sound) {
        Ensighten.evaluateEvent(this, "showNotification", new Object[]{intent, new Boolean(sound)});
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Builder builder = new Builder(this);
        builder.setContentTitle(getString(C2658R.string.msa_alert_enable_alarm_title));
        builder.setContentText(getString(C2658R.string.msa_alarm_local_notification_body));
        if (sound) {
            builder.setSound(RingtoneManager.getDefaultUri(2));
        }
        builder.setContentIntent(pIntent);
        builder.setSmallIcon(C2358R.C2359drawable.msa_alarm_icon);
        ((NotificationManager) getSystemService("notification")).notify(1, builder.build());
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        super.onBackPressed();
        showNotification(createNotificationIntent(this.mOffer, true), false);
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
        }
    }
}
