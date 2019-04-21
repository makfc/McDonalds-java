package com.mcdonalds.app.msa;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MsaMainFragment extends URLNavigationFragment {
    private Handler mHandler;

    /* renamed from: com.mcdonalds.app.msa.MsaMainFragment$2 */
    class C32792 implements OnClickListener {
        C32792() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (MsaMainFragment.this.getNavigationActivity() instanceof MsaActivity) {
                ((MsaActivity) MsaMainFragment.this.getNavigationActivity()).changeFragment("MsaEditFragment");
            } else {
                MsaMainFragment.this.startActivity(MsaActivity.class, "MsaEditFragment");
            }
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MsaMainFragment$3 */
    class C32803 implements OnClickListener {
        C32803() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            MsaMainFragment.this.getNavigationActivity().startActivity(MainActivity.class, "dashboard");
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MsaMainFragment$7 */
    class C32857 implements DialogInterface.OnClickListener {

        /* renamed from: com.mcdonalds.app.msa.MsaMainFragment$7$1 */
        class C32841 implements AsyncListener<CustomerProfile> {
            C32841() {
            }

            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            }
        }

        C32857() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            CustomerModule cm = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            cm.getCurrentProfile().setSubscribedToOffers(true);
            cm.getCurrentProfile().setMSAlarmEnabled(true);
            cm.updateCustomerProfile(cm.getCurrentProfile(), new C32841());
        }
    }

    static /* synthetic */ void access$000(MsaMainFragment x0, TextView x1, TextView x2, TextView x3, TextView x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$000", new Object[]{x0, x1, x2, x3, x4});
        x0.showUsageDialog(x1, x2, x3, x4);
    }

    static /* synthetic */ void access$100(MsaMainFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$100", new Object[]{x0, new Boolean(x1)});
        x0.saveMSAStatus(x1);
    }

    static /* synthetic */ void access$200(MsaMainFragment x0, TextView x1, TextView x2, TextView x3, int x4, int x5) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$200", new Object[]{x0, x1, x2, x3, new Integer(x4), new Integer(x5)});
        x0.setTimeUI(x1, x2, x3, x4, x5);
    }

    static /* synthetic */ void access$300(MsaMainFragment x0, TextView x1, TextView x2, TextView x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$300", new Object[]{x0, x1, x2, x3});
        x0.setupRefresh(x1, x2, x3);
    }

    static /* synthetic */ void access$400(MsaMainFragment x0, TextView x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$400", new Object[]{x0, x1});
        x0.showNextAlarmTime(x1);
    }

    static /* synthetic */ void access$600(MsaMainFragment x0, Switch x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$600", new Object[]{x0, x1});
        x0.showOfferSubscriptionDialog(x1);
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.appmenu_msa);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_msa_confirm, container, false);
        final TextView nextAlarm = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_to_next_alarm);
        showAlarmDays(new TextView[]{(TextView) rootView.findViewById(C2358R.C2357id.msa_box_sun), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_mon), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_tue), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_wed), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_thu), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_fri), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_sat)});
        TextView amPmView = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_am_pm);
        TextView hourView = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_hour);
        TextView minuteView = (TextView) rootView.findViewById(C2358R.C2357id.msa_time_minute);
        Calendar now = Calendar.getInstance();
        this.mHandler = new Handler();
        setTimeUI(amPmView, hourView, minuteView, now.get(11), now.get(12));
        setupRefresh(amPmView, hourView, minuteView);
        SharedPreferences pref = getActivity().getSharedPreferences(MSASettings.getPrefName(), 0);
        Switch sw = (Switch) rootView.findViewById(C2358R.C2357id.msa_switch);
        if (!pref.getBoolean("IS_MSA_TURNED_ON", false)) {
            sw.setChecked(false);
            nextAlarm.setText("");
        } else if (shouldShowNextAlarmTime()) {
            sw.setChecked(true);
            showNextAlarmTime(nextAlarm);
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages(null);
            }
            setTimeUI(amPmView, hourView, minuteView, pref.getInt("HOUR", -1), pref.getInt("MINUTE", -1));
        }
        if (pref.getInt("HOUR", -1) < 0) {
            sw.setEnabled(false);
        }
        final TextView textView = amPmView;
        final TextView textView2 = hourView;
        final TextView textView3 = minuteView;
        sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{compoundButton, new Boolean(checked)});
                if (checked) {
                    MsaMainFragment.access$000(MsaMainFragment.this, nextAlarm, textView, textView2, textView3);
                } else {
                    MsaMainFragment.access$100(MsaMainFragment.this, false);
                    nextAlarm.setText("");
                    Calendar now = Calendar.getInstance();
                    MsaMainFragment.access$200(MsaMainFragment.this, textView, textView2, textView3, now.get(11), now.get(12));
                    MsaMainFragment.access$300(MsaMainFragment.this, textView, textView2, textView3);
                }
                HashMap<String, Object> jiceMap = new HashMap();
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_ENABLE_MSA);
                jiceMap.put("status", checked ? "open" : "close");
                Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
            }
        });
        rootView.findViewById(C2358R.C2357id.button_edit).setOnClickListener(new C32792());
        rootView.findViewById(C2358R.C2357id.button_back).setOnClickListener(new C32803());
        CustomerModule cm = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (!(cm.getCurrentProfile() == null || cm.getCurrentProfile().isSubscribedToOffers())) {
            showEnableDialog(sw);
        }
        return rootView;
    }

    public void onResume() {
        super.onResume();
        if (getActivity().getSharedPreferences(MSASettings.getPrefName(), 0).getBoolean("IS_MSA_TURNED_ON", false)) {
            MSASettings.scheduleNextAlarm(getActivity());
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    private void showUsageDialog(TextView nextAlarm, TextView amPmView, TextView hourView, TextView minuteView) {
        Ensighten.evaluateEvent(this, "showUsageDialog", new Object[]{nextAlarm, amPmView, hourView, minuteView});
        final TextView textView = nextAlarm;
        final TextView textView2 = amPmView;
        final TextView textView3 = hourView;
        final TextView textView4 = minuteView;
        DialogInterface.OnClickListener positiveClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                MsaMainFragment.access$100(MsaMainFragment.this, true);
                MSASettings.scheduleNextAlarm(MsaMainFragment.this.getActivity());
                MsaMainFragment.access$400(MsaMainFragment.this, textView);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$500", new Object[]{MsaMainFragment.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaMainFragment", "access$500", new Object[]{MsaMainFragment.this}).removeCallbacksAndMessages(null);
                }
                SharedPreferences pref = MsaMainFragment.this.getActivity().getSharedPreferences(MSASettings.getPrefName(), 0);
                MsaMainFragment.access$200(MsaMainFragment.this, textView2, textView3, textView4, pref.getInt("HOUR", -1), pref.getInt("MINUTE", -1));
            }
        };
        Builder b = new Builder(getActivity());
        b.setTitle(C2658R.string.msa_alert_alarm_instructions_title);
        b.setMessage(C2658R.string.msa_alert_alarm_instructions_msg);
        b.setPositiveButton(C2658R.string.f6083ok, positiveClick);
        b.setCancelable(false);
        b.show();
    }

    private void showEnableDialog(final Switch sw) {
        Ensighten.evaluateEvent(this, "showEnableDialog", new Object[]{sw});
        DialogInterface.OnClickListener positiveClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                MsaMainFragment.access$600(MsaMainFragment.this, sw);
            }
        };
        DialogInterface.OnClickListener negativeClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                MsaMainFragment.access$100(MsaMainFragment.this, false);
                sw.setChecked(false);
                MsaMainFragment.this.startActivity(MainActivity.class, "dashboard");
            }
        };
        Builder b = new Builder(getActivity());
        b.setTitle(C2658R.string.msa_alert_enable_alarm_title);
        b.setMessage(C2658R.string.msa_alert_enable_alarm_msg);
        b.setPositiveButton(C2658R.string.msa_alert_enable_alarm_button_enable, positiveClick);
        b.setNegativeButton(C2658R.string.cancel, negativeClick);
        b.setCancelable(false);
        b.show();
    }

    private void showOfferSubscriptionDialog(final Switch sw) {
        Ensighten.evaluateEvent(this, "showOfferSubscriptionDialog", new Object[]{sw});
        DialogInterface.OnClickListener positiveClick = new C32857();
        DialogInterface.OnClickListener negativeClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                MsaMainFragment.access$100(MsaMainFragment.this, false);
                sw.setChecked(false);
                MsaMainFragment.this.startActivity(MainActivity.class, "dashboard");
            }
        };
        Builder b = new Builder(getActivity());
        b.setTitle(C2658R.string.msa_alert_enable_alarm_title);
        b.setMessage(C2658R.string.msa_alert_enable_alarm_not_opt_in_msg);
        b.setPositiveButton(C2658R.string.f6083ok, positiveClick);
        b.setNegativeButton(C2658R.string.cancel, negativeClick);
        b.setCancelable(false);
        b.show();
    }

    private void setupRefresh(final TextView amPmView, final TextView hourView, final TextView minuteView) {
        Ensighten.evaluateEvent(this, "setupRefresh", new Object[]{amPmView, hourView, minuteView});
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            Calendar c = Calendar.getInstance();
            long timeInMillisNow = c.getTimeInMillis();
            c.add(12, 1);
            c.set(13, 0);
            c.set(14, 100);
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    Calendar now = Calendar.getInstance();
                    MsaMainFragment.access$200(MsaMainFragment.this, amPmView, hourView, minuteView, now.get(11), now.get(12));
                    MsaMainFragment.access$300(MsaMainFragment.this, amPmView, hourView, minuteView);
                }
            }, c.getTimeInMillis() - timeInMillisNow);
        }
    }

    private void saveMSAStatus(boolean enabled) {
        Ensighten.evaluateEvent(this, "saveMSAStatus", new Object[]{new Boolean(enabled)});
        Editor e = getActivity().getSharedPreferences(MSASettings.getPrefName(), 0).edit();
        e.putBoolean("IS_MSA_TURNED_ON", enabled);
        e.commit();
    }

    private void setTimeUI(TextView amPmView, TextView hourView, TextView minuteView, int hour, int minute) {
        Ensighten.evaluateEvent(this, "setTimeUI", new Object[]{amPmView, hourView, minuteView, new Integer(hour), new Integer(minute)});
        if (amPmView != null && hourView != null && minuteView != null && this.mHandler != null) {
            if (hour == 12) {
                amPmView.setText(getString(C2658R.string.msa_time_pm));
            } else if (hour > 12) {
                amPmView.setText(getString(C2658R.string.msa_time_pm));
                hour -= 12;
            } else {
                amPmView.setText(getString(C2658R.string.msa_time_am));
            }
            hourView.setText(Integer.toString(hour));
            minuteView.setText(new DecimalFormat("00").format((long) minute));
        }
    }

    private boolean shouldShowNextAlarmTime() {
        Ensighten.evaluateEvent(this, "shouldShowNextAlarmTime", null);
        SharedPreferences pref = getActivity().getSharedPreferences(MSASettings.getPrefName(), 0);
        int hour = pref.getInt("HOUR", -1);
        int minute = pref.getInt("MINUTE", -1);
        if (hour < 0 || minute < 0) {
            return false;
        }
        return true;
    }

    private void showNextAlarmTime(TextView nextAlarm) {
        Ensighten.evaluateEvent(this, "showNextAlarmTime", new Object[]{nextAlarm});
        Calendar nextAlarmDate = MSASettings.findNextAlarm(getActivity());
        long timeNow = Calendar.getInstance().getTimeInMillis();
        if (nextAlarmDate != null) {
            long diff = nextAlarmDate.getTimeInMillis() - timeNow;
            long diffMinutes = (diff - (3600000 * (diff / 3600000))) / 60000;
            nextAlarm.setText(getString(C2658R.string.msa_next_surprise_countdown, Long.valueOf(diff / 3600000), Long.valueOf(diffMinutes)));
        }
    }

    private void showAlarmDays(TextView[] days) {
        Ensighten.evaluateEvent(this, "showAlarmDays", new Object[]{days});
        SharedPreferences pref = getActivity().getSharedPreferences(MSASettings.getPrefName(), 0);
        boolean[] alarmDays = new boolean[]{pref.getBoolean("IS_REPEAT_ON_SUNDAY", false), pref.getBoolean("IS_REPEAT_ON_MONDAY", false), pref.getBoolean("IS_REPEAT_ON_TUESDAY", false), pref.getBoolean("IS_REPEAT_ON_WEDNESDAY", false), pref.getBoolean("IS_REPEAT_ON_THURSDAY", false), pref.getBoolean("IS_REPEAT_ON_FRIDAY", false), pref.getBoolean("IS_REPEAT_ON_SATURDAY", false)};
        for (int i = 0; i < 7; i++) {
            TextView day = days[i];
            if (alarmDays[i]) {
                day.setBackgroundColor(-1095340);
                day.setTextColor(-1);
            } else {
                day.setBackgroundResource(C2358R.C2359drawable.msa_day_box_bg);
                day.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
    }
}
