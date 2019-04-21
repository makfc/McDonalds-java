package com.mcdonalds.app.account;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChangeBirthdateFragment extends URLNavigationFragment {
    public static final String NAME = ChangeBirthdateFragment.class.getSimpleName();
    private static Calendar birthdate;
    private static Button mBirthdateButton;
    private static View mSaveButton;
    private static String savedBirthdate;
    final Calendar currentDate = Calendar.getInstance(Locale.ENGLISH);
    private CustomerProfile mCustomerProfile;
    private final OnClickListener mOnClickBirthdate = new C29252();
    private final OnClickListener mOnClickSave = new C29241();
    private boolean pressedCancel = false;

    /* renamed from: com.mcdonalds.app.account.ChangeBirthdateFragment$1 */
    class C29241 implements OnClickListener {
        C29241() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            final CustomerProfile profile = module.getCurrentProfile();
            final Calendar oldBirthdate = profile.getBirthDate();
            profile.setBirthDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$000", null));
            profile.setShouldChangeBirthdate(true);
            AnalyticsUtils.trackOnClickEvent(ChangeBirthdateFragment.this.getAnalyticsTitle(), "Save");
            UIUtils.startActivityIndicator(ChangeBirthdateFragment.this.getActivity(), (int) C2658R.string.lite_dialog_acct_birth);
            module.updateCustomerProfile(profile, new AsyncListener<CustomerProfile>() {
                public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null || response == null) {
                        profile.setBirthDate(oldBirthdate);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$100", null).setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$200", null));
                        AsyncException.report(exception);
                        return;
                    }
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("birthdate", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$100", null).getText().toString());
                    ChangeBirthdateFragment.this.getNavigationActivity().setResult(-1, resultIntent);
                    ChangeBirthdateFragment.this.getNavigationActivity().finish();
                }
            });
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeBirthdateFragment$2 */
    class C29252 implements OnClickListener {
        C29252() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ChangeBirthdateFragment.access$300(ChangeBirthdateFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeBirthdateFragment$3 */
    class C29263 implements OnDateSetListener {
        C29263() {
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Ensighten.evaluateEvent(this, "onDateSet", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
            if (view.isShown()) {
                int year1;
                int month;
                int day;
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$000", null) == null) {
                    ChangeBirthdateFragment.access$002(Calendar.getInstance());
                    year1 = ChangeBirthdateFragment.this.currentDate.get(1);
                    month = ChangeBirthdateFragment.this.currentDate.get(2);
                    day = ChangeBirthdateFragment.this.currentDate.get(5);
                } else {
                    year1 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$000", null).get(1);
                    month = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$000", null).get(2);
                    day = Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$000", null).get(5);
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$000", null).set(year, monthOfYear, dayOfMonth);
                Date date = null;
                String birthdateFormatted = (monthOfYear + 1) + "-" + year;
                try {
                    date = new SimpleDateFormat("yyyy-MM").parse(birthdateFormatted);
                } catch (ParseException e) {
                }
                if (date != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$100", null).setText(birthdateFormatted);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$400", null).setEnabled(true);
                    return;
                }
                ChangeBirthdateFragment.access$002(null);
            }
        }
    }

    public class MonPickerDialog1 extends DatePickerDialog {
        public MonPickerDialog1(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
            super(context, theme, listener, year, monthOfYear, dayOfMonth);
            Calendar minAgeCalendar = (Calendar) ChangeBirthdateFragment.this.currentDate.clone();
            minAgeCalendar.set(ChangeBirthdateFragment.this.currentDate.get(1) - Integer.parseInt((String) Configuration.getSharedInstance().getValueForKey("interface.termsAndConditions.minimumRequiredAge")), ChangeBirthdateFragment.this.currentDate.get(2), ChangeBirthdateFragment.this.currentDate.get(5));
            getDatePicker().setSpinnersShown(true);
            getDatePicker().setMaxDate(minAgeCalendar.getTimeInMillis());
            if (getDatePicker().findViewById(ChangeBirthdateFragment.this.getResources().getIdentifier("day", "id", "android")) != null) {
                getDatePicker().findViewById(ChangeBirthdateFragment.this.getResources().getIdentifier("day", "id", "android")).setVisibility(8);
            }
            setButton(-2, ChangeBirthdateFragment.this.getString(C2658R.string.cancel), new DialogInterface.OnClickListener(ChangeBirthdateFragment.this) {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    if (which == -2) {
                        ChangeBirthdateFragment.access$002(null);
                        String birthdateLabel = ChangeBirthdateFragment.this.getString(C2658R.string.signup_birthdate);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$100", null).setText(null);
                        ChangeBirthdateFragment.access$502(ChangeBirthdateFragment.this, true);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$400", null).setEnabled(true);
                        return;
                    }
                    ChangeBirthdateFragment.access$502(ChangeBirthdateFragment.this, false);
                }
            });
        }

        public void onDateChanged(DatePicker view, int year, int month, int day) {
            Ensighten.evaluateEvent(this, "onDateChanged", new Object[]{view, new Integer(year), new Integer(month), new Integer(day)});
            super.onDateChanged(view, year, month, day);
            setTitle("");
        }
    }

    static /* synthetic */ Calendar access$002(Calendar x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$002", new Object[]{x0});
        birthdate = x0;
        return x0;
    }

    static /* synthetic */ void access$300(ChangeBirthdateFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$300", new Object[]{x0});
        x0.selectMonthTime1();
    }

    static /* synthetic */ boolean access$502(ChangeBirthdateFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeBirthdateFragment", "access$502", new Object[]{x0, new Boolean(x1)});
        x0.pressedCancel = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct_birthdate);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_birthdate);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_change_birthdate, container, false);
        mBirthdateButton = (Button) view.findViewById(C2358R.C2357id.birthdate);
        mBirthdateButton.setOnClickListener(this.mOnClickBirthdate);
        mSaveButton = view.findViewById(C2358R.C2357id.save_btn);
        mSaveButton.setOnClickListener(this.mOnClickSave);
        return view;
    }

    public void onResume() {
        super.onResume();
        Calendar currentBirthdate = LoginManager.getInstance().getProfile().getBirthDate();
        if (currentBirthdate != null) {
            savedBirthdate = new SimpleDateFormat("MM-yyyy").format(currentBirthdate.getTime());
            mBirthdateButton.setText(savedBirthdate);
        }
    }

    private void selectMonthTime1() {
        Ensighten.evaluateEvent(this, "selectMonthTime1", null);
        new MonPickerDialog1(getActivity(), 3, new C29263(), this.currentDate.get(1), this.currentDate.get(2), this.currentDate.get(5)).show();
    }
}
