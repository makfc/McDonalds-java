package com.mcdonalds.app.msa;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.p000v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import java.text.DecimalFormat;

public class MsaEditFragment extends URLNavigationFragment {
    private TextView[] mAlarmDayBoxes;
    private boolean[] mAlarmDays;
    private int mHour;
    private NumberPicker mHrPicker;
    private NumberPicker mMinPicker;
    private int mMinute;
    private Button mSaveBtn;

    /* renamed from: com.mcdonalds.app.msa.MsaEditFragment$2 */
    class C32742 implements OnValueChangeListener {
        C32742() {
        }

        public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
            Ensighten.evaluateEvent(this, "onValueChange", new Object[]{numberPicker, new Integer(oldVal), new Integer(newVal)});
            if (newVal == 10) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$000", new Object[]{MsaEditFragment.this}).setMaxValue(30);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$000", new Object[]{MsaEditFragment.this}).setMaxValue(59);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MsaEditFragment$3 */
    class C32763 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.msa.MsaEditFragment$3$1 */
        class C32751 implements DialogInterface.OnClickListener {
            C32751() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                MsaEditFragment.access$200(MsaEditFragment.this);
            }
        }

        C32763() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            MsaEditFragment.this.saveSettings();
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$100", new Object[]{MsaEditFragment.this})) {
                MCDAlertDialogBuilder.withContext(MsaEditFragment.this.getNavigationActivity()).setMessage(MsaEditFragment.this.getString(C2658R.string.prompt_enable_xiaomi_autostart)).setPositiveButton((int) C2658R.string.f6083ok, new C32751()).create().show();
                return;
            }
            MsaEditFragment.access$200(MsaEditFragment.this);
        }
    }

    static /* synthetic */ void access$200(MsaEditFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$200", new Object[]{x0});
        x0.onSaveClicked();
    }

    static /* synthetic */ void access$400(MsaEditFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$400", new Object[]{x0});
        x0.setupSaveButtonState();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.appmenu_msa);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlarmDays = new boolean[7];
        getMsaSettings();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_msa_setting, container, false);
        this.mAlarmDayBoxes = new TextView[]{(TextView) rootView.findViewById(C2358R.C2357id.msa_box_sun), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_mon), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_tue), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_wed), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_thu), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_fri), (TextView) rootView.findViewById(C2358R.C2357id.msa_box_sat)};
        setupAlarmDays(this.mAlarmDayBoxes);
        this.mMinPicker = (NumberPicker) rootView.findViewById(C2358R.C2357id.msa_alarm_setting_minute);
        this.mMinPicker.setMinValue(0);
        this.mMinPicker.setMaxValue(59);
        final DecimalFormat df = new DecimalFormat("00");
        this.mMinPicker.setFormatter(new Formatter() {
            public String format(int i) {
                Ensighten.evaluateEvent(this, "format", new Object[]{new Integer(i)});
                return df.format((long) i);
            }
        });
        this.mHrPicker = (NumberPicker) rootView.findViewById(C2358R.C2357id.msa_alarm_setting_hour);
        this.mHrPicker.setMinValue(5);
        this.mHrPicker.setMaxValue(10);
        this.mHrPicker.setOnValueChangedListener(new C32742());
        this.mSaveBtn = (Button) rootView.findViewById(C2358R.C2357id.msa_save_alarm);
        this.mSaveBtn.setOnClickListener(new C32763());
        MSATuneItem item = MSASettings.FIXED_TUNE;
        MSASettings.saveAlarmId(getActivity(), item.getType(), item.getTuneId(), item.getChoice());
        ((TextView) rootView.findViewById(C2358R.C2357id.msa_set_ringtone)).setText(MSASettings.getAlarmDesc(getActivity()));
        showAlarmDays(this.mAlarmDayBoxes);
        return rootView;
    }

    public void onResume() {
        super.onResume();
        setupSaveButtonState();
    }

    private boolean isXiaomi() {
        Ensighten.evaluateEvent(this, "isXiaomi", null);
        String mfc = Build.MANUFACTURER;
        return mfc != null && mfc.equalsIgnoreCase("xiaomi");
    }

    private void onSaveClicked() {
        Ensighten.evaluateEvent(this, "onSaveClicked", null);
        if (getNavigationActivity() instanceof MsaActivity) {
            ((MsaActivity) getNavigationActivity()).changeFragment("msa");
        } else {
            startActivity(MsaActivity.class, "msa");
        }
    }

    private void setupAlarmDays(TextView[] days) {
        Ensighten.evaluateEvent(this, "setupAlarmDays", new Object[]{days});
        for (int i = 0; i < 7; i++) {
            final TextView day = days[i];
            final int index = i;
            day.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    boolean z = true;
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$300", new Object[]{MsaEditFragment.this})[index]) {
                        day.setBackgroundResource(C2358R.C2359drawable.msa_day_box_bg);
                        day.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    } else {
                        day.setBackgroundColor(-1095340);
                        day.setTextColor(-1);
                    }
                    boolean[] access$300 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$300", new Object[]{MsaEditFragment.this});
                    int i = index;
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MsaEditFragment", "access$300", new Object[]{MsaEditFragment.this})[index]) {
                        z = false;
                    }
                    access$300[i] = z;
                    MsaEditFragment.access$400(MsaEditFragment.this);
                }
            });
        }
    }

    private void setupSaveButtonState() {
        int i = 0;
        Ensighten.evaluateEvent(this, "setupSaveButtonState", null);
        this.mSaveBtn.setEnabled(false);
        boolean[] zArr = this.mAlarmDays;
        int length = zArr.length;
        while (i < length) {
            if (zArr[i]) {
                this.mSaveBtn.setEnabled(true);
                return;
            }
            i++;
        }
    }

    private void getMsaSettings() {
        Ensighten.evaluateEvent(this, "getMsaSettings", null);
        SharedPreferences mSharedPreferences = getNavigationActivity().getSharedPreferences(MSASettings.getPrefName(), 0);
        this.mHour = mSharedPreferences.getInt("HOUR", -1);
        this.mMinute = mSharedPreferences.getInt("MINUTE", -1);
        this.mAlarmDays[0] = mSharedPreferences.getBoolean("IS_REPEAT_ON_SUNDAY", false);
        this.mAlarmDays[1] = mSharedPreferences.getBoolean("IS_REPEAT_ON_MONDAY", false);
        this.mAlarmDays[2] = mSharedPreferences.getBoolean("IS_REPEAT_ON_TUESDAY", false);
        this.mAlarmDays[3] = mSharedPreferences.getBoolean("IS_REPEAT_ON_WEDNESDAY", false);
        this.mAlarmDays[4] = mSharedPreferences.getBoolean("IS_REPEAT_ON_THURSDAY", false);
        this.mAlarmDays[5] = mSharedPreferences.getBoolean("IS_REPEAT_ON_FRIDAY", false);
        this.mAlarmDays[6] = mSharedPreferences.getBoolean("IS_REPEAT_ON_SATURDAY", false);
    }

    private void showAlarmDays(TextView[] days) {
        Ensighten.evaluateEvent(this, "showAlarmDays", new Object[]{days});
        for (int i = 0; i < 7; i++) {
            TextView day = days[i];
            if (this.mAlarmDays[i]) {
                day.setBackgroundColor(-1095340);
                day.setTextColor(-1);
            } else {
                day.setBackgroundResource(C2358R.C2359drawable.msa_day_box_bg);
                day.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
        if (this.mHour != -1) {
            this.mHrPicker.setValue(this.mHour);
        }
        if (this.mMinute != -1) {
            this.mMinPicker.setValue(this.mMinute);
            if (this.mHour == 10) {
                this.mMinPicker.setMaxValue(30);
            }
        }
    }

    public void saveSettings() {
        Ensighten.evaluateEvent(this, "saveSettings", null);
        MSASettings.saveSettings(getActivity(), this.mHrPicker.getValue(), this.mMinPicker.getValue(), this.mAlarmDays);
    }
}
