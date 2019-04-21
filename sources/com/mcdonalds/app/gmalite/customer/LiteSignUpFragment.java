package com.mcdonalds.app.gmalite.customer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.DialogFragment;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.customer.ChooseMethodView;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.social.SocialLoginFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.StringUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.app.widget.EditTextPhone;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class LiteSignUpFragment extends SocialLoginFragment {
    private static Calendar birthdate;
    private static Button mBirthdateButton;
    private ArrayAdapter<String> gendersAdapter;
    private View mAlreadyRegistered;
    private ChooseMethodView mChooseMethod;
    private View mChooseMethodContainer;
    private EditText mEmail;
    private EditText mEmailConfirmation;
    private Button mFinishButton;
    private EditText mFirstName;
    private Spinner mGender;
    private final OnItemSelectedListener mGenderSelected = new C319715();
    private EditText mLastName;
    private View mMailAsUser;
    private LoginManager mManager;
    private OffersModule mOffersModule;
    private final OnClickListener mOnCheckboxClicked = new C32108();
    private final OnClickListener mOnClickBirthdate = new C319816();
    private OnClickListener mOnClickCustomerSupport = new C32042();
    private final OnClickListener mOnClickFinish = new C32075();
    private final OnClickListener mOnClickPrivacy = new C32119();
    private final OnClickListener mOnClickResetPassword = new C319614();
    private final OnClickListener mOnClickSignIn = new C32086();
    private final OnClickListener mOnClickTOS = new C32097();
    private final OnClickListener mOnClickTermsAndConditions = new C319210();
    private final OnFocusChangeListener mOnFocusChangeListener = new C319513();
    ClickableSpan mPPClicked = new C320320();
    protected URLNavigationActivity mParent;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private final InputFilter mPasswordInputFilter = new C319311();
    private EditText mPhoneNumber;
    private CustomerProfile mProfile;
    private HashMap<Integer, Boolean> mRequiredToggles;
    private int mSocialLoginId = -1;
    ClickableSpan mTOCClicked = new C320119();
    private OnCheckedChangeListener mToggleChangedListener = new C32053();
    private Callback mValidationCallback = new C319412();
    private SparseArray<ValidationListener> mValidations;
    private EditText mZipCode;
    private String selectedGender;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$10 */
    class C319210 implements OnClickListener {
        C319210() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String termsURL = AppUtils.getLocalisedLegalUrl("registerTOC");
            if (termsURL != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", termsURL);
                LiteSignUpFragment.this.startActivity(WebActivity.class, attributes);
                return;
            }
            AsyncException.report("No Terms and Condition URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$11 */
    class C319311 implements InputFilter {
        C319311() {
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Ensighten.evaluateEvent(this, Parameters.FILTER, new Object[]{source, new Integer(start), new Integer(end), dest, new Integer(dstart), new Integer(dend)});
            for (int i = start; i < end; i++) {
                if (Character.isWhitespace(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$12 */
    class C319412 implements Callback {
        C319412() {
        }

        public void onFieldValidationStateChanged(boolean isValidated) {
            Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$500", new Object[]{LiteSignUpFragment.this}).setEnabled(false);
            LiteSignUpFragment.access$000(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$13 */
    class C319513 implements OnFocusChangeListener {
        C319513() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
            if (!hasFocus) {
                ValidationListener validation = (ValidationListener) Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$600", new Object[]{LiteSignUpFragment.this}).get(v.getId());
                if (validation != null) {
                    if (!validation.isValidated()) {
                        validation.displayError();
                    }
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$700", new Object[]{LiteSignUpFragment.this}).getSelection() == 0) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$700", new Object[]{LiteSignUpFragment.this}).displayError();
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$14 */
    class C319614 implements OnClickListener {
        C319614() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteSignUpFragment.this.startActivity(ProfileUpdateActivity.class, "reset_password");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$15 */
    class C319715 implements OnItemSelectedListener {
        C319715() {
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemSelected", new Object[]{parent, view, new Integer(position), new Long(id)});
            LiteSignUpFragment.access$802(LiteSignUpFragment.this, (String) Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$900", new Object[]{LiteSignUpFragment.this}).getItem(position));
        }

        public void onNothingSelected(AdapterView<?> parent) {
            Ensighten.evaluateEvent(this, "onNothingSelected", new Object[]{parent});
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$16 */
    class C319816 implements OnClickListener {
        C319816() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            new BirthdatePicker().show(LiteSignUpFragment.this.getFragmentManager(), "birthdatePicker");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$17 */
    class C319917 extends ClickableSpan {
        C319917() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            LiteSignUpFragment.access$1300(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$18 */
    class C320018 extends ClickableSpan {
        C320018() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            LiteSignUpFragment.access$1400(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$19 */
    class C320119 extends ClickableSpan {
        C320119() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            LiteSignUpFragment.access$1300(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$1 */
    class C32021 implements OnClickListener {
        C32021() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteSignUpFragment.access$000(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$20 */
    class C320320 extends ClickableSpan {
        C320320() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            LiteSignUpFragment.access$1400(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$2 */
    class C32042 implements OnClickListener {
        C32042() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignUpFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteSignUpFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteSignUpFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$3 */
    class C32053 implements OnCheckedChangeListener {
        C32053() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
            LiteSignUpFragment.access$000(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$5 */
    class C32075 implements OnClickListener {
        C32075() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignUpFragment.this.getAnalyticsTitle(), "Continue");
            LiteSignUpFragment.access$200(LiteSignUpFragment.this);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$300", new Object[]{LiteSignUpFragment.this}).setUsingSocialLogin(false);
            LiteSignUpFragment.access$400(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$6 */
    class C32086 implements OnClickListener {
        C32086() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteSignUpFragment.this.startActivity(LiteSignInActivity.class);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$7 */
    class C32097 implements OnClickListener {
        C32097() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignUpFragment.this.getAnalyticsTitle(), "Terms & Conditions");
            String link = AppUtils.getLocalisedLegalUrl("registerTOC");
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteSignUpFragment.this.getString(C2658R.string.analytics_screen_register_terms));
                LiteSignUpFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Privacy Policy URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$8 */
    class C32108 implements OnClickListener {
        C32108() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteSignUpFragment.access$000(LiteSignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$9 */
    class C32119 implements OnClickListener {
        C32119() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignUpFragment.this.getAnalyticsTitle(), "Privacy Policy");
            String privacyURL = AppUtils.getLocalisedLegalUrl("privacy");
            if (privacyURL != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", privacyURL);
                attributes.putString("analytics_title", LiteSignUpFragment.this.getString(C2658R.string.analytics_screen_register_privacy));
                LiteSignUpFragment.this.startActivity(WebActivity.class, attributes);
                return;
            }
            AsyncException.report("No Privacy Policy URL Defined");
        }
    }

    @Instrumented
    public static class BirthdatePicker extends DialogFragment implements OnDateSetListener, TraceFieldInterface {
        public Trace _nr_trace;
        private boolean pressedCancel = false;

        /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$BirthdatePicker$1 */
        class C32121 implements DialogInterface.OnClickListener {
            C32121() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                if (which == -2) {
                    LiteSignUpFragment.access$1002(null);
                    String birthdateLabel = BirthdatePicker.this.getString(C2658R.string.lite_hint_birthdate);
                    if (!LoginManager.getInstance().fieldIsMandatory("birthDate")) {
                        birthdateLabel = BirthdatePicker.this.getString(C2658R.string.lite_hint_optional_android, BirthdatePicker.this.getString(C2658R.string.lite_hint_birthdate));
                    }
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1100", null).setText(birthdateLabel);
                    BirthdatePicker.access$1202(BirthdatePicker.this, true);
                }
            }
        }

        public void onActivityCreated(Bundle bundle) {
            EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
            super.onActivityCreated(bundle);
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
            super.onActivityResult(i, i2, intent);
        }

        public void onAttach(Activity activity) {
            EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
            super.onAttach(activity);
        }

        public void onCreate(Bundle bundle) {
            TraceMachine.startTracing("LiteSignUpFragment$BirthdatePicker");
            try {
                TraceMachine.enterMethod(this._nr_trace, "LiteSignUpFragment$BirthdatePicker#onCreate", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "LiteSignUpFragment$BirthdatePicker#onCreate", null);
                }
            }
            EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
            super.onCreate(bundle);
            Ensighten.processView((Object) this, "onCreate");
            TraceMachine.exitMethod();
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "LiteSignUpFragment$BirthdatePicker#onCreateView", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "LiteSignUpFragment$BirthdatePicker#onCreateView", null);
                }
            }
            EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
            View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            TraceMachine.exitMethod();
            return onCreateView;
        }

        public void onDestroy() {
            EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
            super.onDestroy();
        }

        public void onDestroyView() {
            EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
            super.onDestroyView();
        }

        public void onDetach() {
            EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
            super.onDetach();
        }

        public void onPause() {
            EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
            super.onPause();
            Ensighten.processView((Object) this, "onPause");
        }

        public void onResume() {
            EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
            super.onResume();
            Ensighten.processView((Object) this, "onResume");
        }

        public void onStart() {
            ApplicationStateMonitor.getInstance().activityStarted();
            EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
            super.onStart();
            Ensighten.processView((Object) this, "onStart");
        }

        public void onStop() {
            ApplicationStateMonitor.getInstance().activityStopped();
            EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
            super.onStop();
        }

        public void onViewStateRestored(Bundle bundle) {
            EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
            super.onViewStateRestored(bundle);
            Ensighten.processView((Object) this, "onViewStateRestored");
        }

        static /* synthetic */ boolean access$1202(BirthdatePicker x0, boolean x1) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment$BirthdatePicker", "access$1202", new Object[]{x0, new Boolean(x1)});
            x0.pressedCancel = x1;
            return x1;
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int year;
            int month;
            int day;
            Ensighten.evaluateEvent(this, "onCreateDialog", new Object[]{savedInstanceState});
            Calendar currentDate = Calendar.getInstance();
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1000", null) == null) {
                year = currentDate.get(1);
                month = currentDate.get(2);
                day = currentDate.get(5);
            } else {
                year = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1000", null).get(1);
                month = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1000", null).get(2);
                day = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1000", null).get(5);
            }
            Calendar minAgeCalendar = (Calendar) currentDate.clone();
            minAgeCalendar.set(currentDate.get(1) - Integer.parseInt((String) Configuration.getSharedInstance().getValueForKey("interface.termsAndConditions.minimumRequiredAge")), month, day);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), C2658R.style.BirthdatePicker, this, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(minAgeCalendar.getTimeInMillis());
            datePickerDialog.getDatePicker().setSpinnersShown(true);
            datePickerDialog.setButton(-2, getString(C2658R.string.cancel), new C32121());
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Ensighten.evaluateEvent(this, "onDateSet", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
            if (view.isShown()) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1000", null) == null) {
                    LiteSignUpFragment.access$1002(Calendar.getInstance());
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1000", null).set(year, monthOfYear, dayOfMonth);
                monthOfYear++;
                DateFormat dateFormat = DateFormat.getDateInstance(1, Locale.getDefault());
                Date date = null;
                try {
                    date = new SimpleDateFormat("dd-MM-yyyy").parse(dayOfMonth + "-" + monthOfYear + "-" + year);
                } catch (ParseException e) {
                }
                if (date == null || this.pressedCancel) {
                    LiteSignUpFragment.access$1002(null);
                    return;
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1100", null).setText(dateFormat.format(date));
            }
        }
    }

    static /* synthetic */ void access$000(LiteSignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$000", new Object[]{x0});
        x0.validateData();
    }

    static /* synthetic */ Calendar access$1002(Calendar x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1002", new Object[]{x0});
        birthdate = x0;
        return x0;
    }

    static /* synthetic */ void access$1300(LiteSignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1300", new Object[]{x0});
        x0.clickedTermsAndConditions();
    }

    static /* synthetic */ void access$1400(LiteSignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$1400", new Object[]{x0});
        x0.clickedPrivacyPolicy();
    }

    static /* synthetic */ void access$200(LiteSignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$200", new Object[]{x0});
        x0.updateProfile();
    }

    static /* synthetic */ void access$400(LiteSignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$400", new Object[]{x0});
        x0.register();
    }

    static /* synthetic */ String access$802(LiteSignUpFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$802", new Object[]{x0, x1});
        x0.selectedGender = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_register);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParent = getNavigationActivity();
        this.mManager = LoginManager.getInstance();
        this.mManager.loadRegisterConfig();
        checkForOffersModule();
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        Ensighten.evaluateEvent(this, "getLayoutResourceId", null);
        return C2658R.layout.fragment_lite_signup;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Log.d("NAV_TEST", "Launched Lite Sign Up Fragment!");
        if (view == null) {
            throw new RuntimeException("SignUpFragment super.onCreateView is null");
        }
        this.mFirstName = (EditText) view.findViewById(C2358R.C2357id.signup_firstname);
        this.mLastName = (EditText) view.findViewById(C2358R.C2357id.signup_lastname);
        this.mEmail = (EditText) view.findViewById(C2358R.C2357id.signup_email);
        this.mEmailConfirmation = (EditText) view.findViewById(C2358R.C2357id.signup_email_confirm);
        this.mZipCode = (EditText) view.findViewById(C2358R.C2357id.signup_zipcode);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.phoneNumberShowCountryCode")) {
            String countryCode = Configuration.getSharedInstance().getStringForKey("interface.register.phoneNumberCountryCode");
            if (TextUtils.isEmpty(countryCode)) {
                countryCode = "";
            }
            this.mPhoneNumber = (EditTextPhone) view.findViewById(C2358R.C2357id.signup_phone_with_country_code);
            ((EditTextPhone) this.mPhoneNumber).setCountryCode(countryCode);
        } else {
            this.mPhoneNumber = (EditText) view.findViewById(C2358R.C2357id.signup_phone);
        }
        this.mGender = (Spinner) view.findViewById(C2358R.C2357id.signup_gender);
        this.mPassword = (EditText) view.findViewById(C2358R.C2357id.signup_password);
        this.mPasswordConfirm = (EditText) view.findViewById(C2358R.C2357id.signup_confirm_password);
        mBirthdateButton = (Button) view.findViewById(C2358R.C2357id.signup_birthdate);
        mBirthdateButton.setOnClickListener(this.mOnClickBirthdate);
        this.mChooseMethodContainer = view.findViewById(C2358R.C2357id.container_choose_method);
        this.mMailAsUser = view.findViewById(C2358R.C2357id.signup_mail_as_user);
        this.mAlreadyRegistered = view.findViewById(C2358R.C2357id.already_registered_error);
        this.mChooseMethod = (ChooseMethodView) view.findViewById(C2358R.C2357id.choose_method);
        this.mChooseMethod.setOnClickListener(new C32021());
        this.mFinishButton = (Button) view.findViewById(C2358R.C2357id.signup_button_finish);
        this.mFinishButton.setEnabled(false);
        this.mFinishButton.setOnClickListener(this.mOnClickFinish);
        ((TextView) view.findViewById(C2358R.C2357id.has_account)).setOnClickListener(this.mOnClickSignIn);
        view.findViewById(C2358R.C2357id.btn_reset_password).setOnClickListener(this.mOnClickResetPassword);
        String[] genderList = (String[]) this.mManager.getOptionFields().get("gender");
        if (!this.mManager.fieldIsMandatory("gender")) {
            genderList[0] = getString(C2658R.string.lite_hint_optional_android, getString(C2658R.string.lite_hint_gender));
        }
        this.gendersAdapter = new ArrayAdapter(getActivity(), C2658R.layout.item_spinner, (Object[]) this.mManager.getOptionFields().get("gender"));
        this.gendersAdapter.setDropDownViewResource(17367049);
        this.mGender.setAdapter(this.gendersAdapter);
        this.mGender.setOnItemSelectedListener(this.mGenderSelected);
        this.selectedGender = "";
        this.mRequiredToggles = new HashMap();
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        configure(view);
        return view;
    }

    private void checkForOffersModule() {
        Ensighten.evaluateEvent(this, "checkForOffersModule", null);
        if (this.mOffersModule == null) {
            this.mOffersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        }
    }

    private void configure(View view) {
        Ensighten.evaluateEvent(this, "configure", new Object[]{view});
        this.mValidations = new SparseArray();
        addValidation(this.mFirstName, 4);
        if (this.mManager.showField("lastname")) {
            addValidation(this.mLastName, 4);
            this.mLastName.setVisibility(0);
        } else {
            this.mFirstName.setHint(getString(C2658R.string.text_hint_your_name));
        }
        if (this.mManager.showField("zipcode")) {
            if (this.mManager.fieldIsMandatory("zipcode")) {
                addValidation(this.mZipCode, 3);
            }
            this.mZipCode.setVisibility(0);
            setupPostalCode(this.mZipCode);
        }
        if (this.mManager.showField("gender")) {
            this.mGender.setVisibility(0);
        }
        if (this.mManager.showField("birthDate")) {
            mBirthdateButton.setVisibility(0);
            if (!LoginManager.getInstance().fieldIsMandatory("birthDate")) {
                mBirthdateButton.setText(getString(C2658R.string.lite_hint_optional_android, getString(C2658R.string.lite_hint_birthdate)));
            }
        }
        configureToggles(view);
        setPhoneValidation(view);
        if (this.mManager.showField("confirmEmailAddress")) {
            this.mEmailConfirmation.setVisibility(0);
            setMailConfirmValidation(view);
        } else {
            setMailValidation(view);
        }
        ValidationListener passwordValidation = new ValidationListener(this.mPassword, this.mPasswordConfirm, 1, true, false, true);
        passwordValidation.setValidationCallback(this.mValidationCallback);
        addValidation(passwordValidation);
        addValidation(new ValidationListener(this.mPasswordConfirm, this.mPassword, 1, true, true, true));
        InputFilter[] passwordFilters = new InputFilter[]{this.mPasswordInputFilter};
        this.mPassword.setFilters(passwordFilters);
        this.mPasswordConfirm.setFilters(passwordFilters);
    }

    private void configureToggles(View view) {
        CheckBox checkBox;
        Ensighten.evaluateEvent(this, "configureToggles", new Object[]{view});
        if (this.mManager.showField("terms_and_conditions")) {
            view.findViewById(C2358R.C2357id.terms_and_conditions_toggle).setVisibility(0);
            checkBox = (CheckBox) view.findViewById(C2358R.C2357id.terms_and_conditions_checkbox);
            checkBox.setChecked(this.mManager.getDefaultState("terms_and_conditions"));
            checkBox.setOnClickListener(this.mOnCheckboxClicked);
            configurePolicyUpdatesString((TextView) view.findViewById(C2358R.C2357id.policy_updates_agree));
        }
        for (String field : this.mManager.getVisibleFields()) {
            if (!Strings.isNullOrEmpty(UIUtils.getStringByName(getNavigationActivity(), field))) {
                int id = getResources().getIdentifier(field + "_checkbox", "id", getActivity().getPackageName());
                if (id != 0) {
                    checkBox = (CheckBox) view.findViewById(id);
                    checkBox.setVisibility(0);
                    checkBox.setChecked(this.mManager.getDefaultState(field));
                    checkBox.setOnCheckedChangeListener(this.mToggleChangedListener);
                }
            }
        }
    }

    private void setPhoneValidation(View view) {
        Ensighten.evaluateEvent(this, "setPhoneValidation", new Object[]{view});
        if (this.mManager.showField("phoneNumber")) {
            TextView phoneErrorDisplay = (TextView) view.findViewById(C2358R.C2357id.mobile_error);
            String phoneErrorMessage = getString(C2658R.string.error_check_mobile_format);
            String phoneEmptyMessage = getString(C2658R.string.error_provide_mobile_number);
            ValidationListener phoneValidation = addValidation(this.mPhoneNumber, 5);
            phoneValidation.setErrorDisplay(phoneErrorDisplay);
            phoneValidation.setErrorMessage(phoneErrorMessage);
            phoneValidation.setEmptyMessage(phoneEmptyMessage);
            this.mPhoneNumber.setVisibility(0);
            if (Configuration.getSharedInstance().getBooleanForKey("register.chooseEmailOrPhoneAsUsername")) {
                this.mChooseMethodContainer.setVisibility(0);
                return;
            }
            return;
        }
        this.mMailAsUser.setVisibility(0);
    }

    private void setMailValidation(View view) {
        Ensighten.evaluateEvent(this, "setMailValidation", new Object[]{view});
        TextView mailErrorDisplay = (TextView) view.findViewById(C2358R.C2357id.email_error);
        String mailErrorMessage = getString(C2658R.string.error_check_email_format);
        String mailEmptyMessage = getString(C2658R.string.error_empty_mail);
        ValidationListener mailValidation = addValidation(this.mEmail, 0);
        mailValidation.setErrorDisplay(mailErrorDisplay);
        mailValidation.setErrorMessage(mailErrorMessage);
        mailValidation.setEmptyMessage(mailEmptyMessage);
    }

    private void setMailConfirmValidation(View view) {
        Ensighten.evaluateEvent(this, "setMailConfirmValidation", new Object[]{view});
        TextView mailErrorDisplay = (TextView) view.findViewById(C2358R.C2357id.email_error);
        String mailErrorMessage = getString(C2658R.string.error_check_email_format);
        String mailEmptyMessage = getString(C2658R.string.error_empty_mail);
        ValidationListener emailValidation = new ValidationListener(this.mEmail, this.mEmailConfirmation, 0, true, false, true);
        ValidationListener confirmEmailValidation = new ValidationListener(this.mEmailConfirmation, this.mEmail, 0, true, true, true);
        addValidation(emailValidation);
        addValidation(confirmEmailValidation);
        emailValidation.setErrorDisplay(mailErrorDisplay);
        emailValidation.setErrorMessage(mailErrorMessage);
        emailValidation.setEmptyMessage(mailEmptyMessage);
    }

    private ValidationListener addValidation(EditText field, int type) {
        boolean z = false;
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{field, new Integer(type)});
        if (type != 4) {
            z = true;
        }
        return addValidation(new ValidationListener(field, type, z, true));
    }

    private ValidationListener addValidation(ValidationListener validation) {
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{validation});
        EditText validationEditText = validation.getTextField();
        validationEditText.addTextChangedListener(validation);
        validationEditText.setOnFocusChangeListener(this.mOnFocusChangeListener);
        validationEditText.setOnEditorActionListener(onDoneKeyPressed(validation));
        validation.setValidationCallback(this.mValidationCallback);
        this.mValidations.put(validationEditText.getId(), validation);
        return validation;
    }

    @NonNull
    private OnEditorActionListener onDoneKeyPressed(final ValidationListener validation) {
        Ensighten.evaluateEvent(this, "onDoneKeyPressed", new Object[]{validation});
        return new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
                if (actionId != 6) {
                    return false;
                }
                if (!validation.isValidated()) {
                    validation.displayError();
                    return true;
                } else if (v.getId() != Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "access$100", new Object[]{LiteSignUpFragment.this}).getId()) {
                    return true;
                } else {
                    ((InputMethodManager) v.getContext().getSystemService("input_method")).hideSoftInputFromWindow(v.getWindowToken(), 0);
                    ((LinearLayout) ((View) v.getParent()).findViewById(C2358R.C2357id.terms_and_condition)).requestFocus();
                    return true;
                }
            }
        };
    }

    private void initProfile() {
        Ensighten.evaluateEvent(this, "initProfile", null);
        if (this.mProfile == null) {
            this.mProfile = new CustomerProfile();
        }
    }

    private void updateProfile() {
        Ensighten.evaluateEvent(this, "updateProfile", null);
        initProfile();
        this.mProfile.setEmailAddress(getTrimmedText(this.mEmail));
        String firstName = getTrimmedText(this.mFirstName);
        this.mProfile.setFirstName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase());
        String lastName = getTrimmedText(this.mLastName);
        this.mProfile.setLastName(lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase());
        this.mProfile.setZipCode(getTrimmedText(this.mZipCode));
        this.mProfile.setPassword(getTrimmedText(this.mPassword));
        if (this.mManager.showField("phoneNumber")) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.register.phoneNumberShowCountryCode") && (this.mPhoneNumber instanceof EditTextPhone)) {
                this.mProfile.setMobileNumber(this.mPhoneNumber.getText().toString());
            } else {
                this.mProfile.setMobileNumber(((String) Configuration.getSharedInstance().getValueForKey("interface.register.phoneNumberCountryCode")) + getTrimmedText(this.mPhoneNumber));
            }
            this.mProfile.setLoginPreference(this.mChooseMethod.getSelection());
            this.mProfile.setUserName(getTrimmedText(this.mEmail));
        } else {
            this.mProfile.setUserName(getTrimmedText(this.mEmail));
        }
        boolean genderOptional = this.selectedGender.contains(getString(C2658R.string.lite_hint_optional_android, getString(C2658R.string.lite_hint_gender)));
        if (this.mManager.showField("gender") && !genderOptional) {
            this.mProfile.setGender(this.selectedGender.toLowerCase());
        }
        if (this.mManager.showField("birthDate")) {
            this.mProfile.setBirthDate(birthdate);
        }
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timeZone);
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault()).setTimeZone(timeZone);
        String currentTimestamp = UIUtils.getCurrentTimestampUsingFormat("yyyy-MM-dd HH:mm:ss Z", "UTC");
        this.mProfile.setmTermsAndConditionVersion(currentTimestamp);
        this.mProfile.setmPrivacyPolicyVersion(currentTimestamp);
        this.mManager.setProfile(this.mProfile);
    }

    private String getTrimmedText(EditText field) {
        Ensighten.evaluateEvent(this, "getTrimmedText", new Object[]{field});
        if (field.getText() != null) {
            return field.getText().toString().trim();
        }
        return "";
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.lite_title_reg);
    }

    public void onStart() {
        super.onStart();
        ModuleManager.getModule(CustomerModule.NAME);
    }

    public static void setupPostalCode(EditText postalCode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignUpFragment", "setupPostalCode", new Object[]{postalCode});
        String POSTALCODE_ALPHANUMERIC = "interface.register.postalCodeAlphaNumeric";
        String POSTALCODE_MAXLENGTH = "interface.register.postalCodeMaxLength";
        boolean postalCodeAlphaNumeric = Configuration.getSharedInstance().getBooleanForKey("interface.register.postalCodeAlphaNumeric");
        int postalCodeMaxLength = Configuration.getSharedInstance().getIntForKey("interface.register.postalCodeMaxLength");
        if (postalCodeMaxLength == 0) {
            postalCodeMaxLength = 8;
        }
        InputFilter[] filters = new InputFilter[]{new LengthFilter(postalCodeMaxLength)};
        postalCode.setInputType(postalCodeAlphaNumeric ? 112 : 2);
        postalCode.setFilters(filters);
    }

    public void onSocialNetworkAvailable() {
        Ensighten.evaluateEvent(this, "onSocialNetworkAvailable", null);
    }

    public void onSocialNetworkSelected(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkSelected", new Object[]{socialNetwork});
        initProfile();
        this.mProfile.setSocialServiceAuthenticationID(socialNetwork.getSocialNetworkID());
        this.mProfile.setUsingSocialLogin(true);
        if (socialNetwork.getType() == 2) {
            this.mSocialLoginId = 2;
        } else if (socialNetwork.getType() == 1) {
            this.mSocialLoginId = 1;
        }
        super.onSocialNetworkSelected(socialNetwork);
    }

    public void onSocialNetworkAuthenticationComplete(SocialLoginAuthenticationResults results) {
        Ensighten.evaluateEvent(this, "onSocialNetworkAuthenticationComplete", new Object[]{results});
        if (results.getEmailAddress() == null) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.validate_social_network)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
        } else if (Configuration.getSharedInstance().getLocalizedStringForKey("modules.customer.connector").equals("MWCustomerSecurity")) {
            AuthenticationParameters parameters = new AuthenticationParameters();
            parameters.setEmailAddress(results.getEmailAddress());
            parameters.setFirstName(results.getFirstName());
            parameters.setLastName(results.getLastName());
            parameters.setSocialUserID(results.getUserId());
            parameters.setSocialAuthenticationToken(results.getAccessToken());
            parameters.setSocialServiceID(this.mSocialLoginId);
            parameters.setUsingSocialLogin(true);
            UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_signing_in));
            LoginManager.getInstance().login(parameters, getNavigationActivity());
        } else {
            this.mProfile.setFirstName(results.getFirstName());
            this.mProfile.setLastName(results.getLastName());
            this.mProfile.setSocialAuthenticationToken(results.getAccessToken());
            this.mProfile.setSocialUserID(results.getUserId());
            this.mProfile.setEmailAddress(results.getEmailAddress());
            this.mProfile.setUserName(results.getEmailAddress());
            register();
        }
    }

    private void register() {
        Ensighten.evaluateEvent(this, JiceArgs.EVENT_REGISTER, null);
        this.mManager.setProfile(this.mProfile);
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.lite_dialog_reg));
        LoginManager.getInstance().register(getNavigationActivity());
    }

    private void validateData() {
        Ensighten.evaluateEvent(this, "validateData", null);
        int i = 0;
        int size = this.mValidations.size();
        while (i < size) {
            if (((ValidationListener) this.mValidations.get(this.mValidations.keyAt(i))).isValidated()) {
                i++;
            } else {
                return;
            }
        }
        if (!Configuration.getSharedInstance().getBooleanForKey("register.chooseEmailOrPhoneAsUsername") || !this.mManager.showField("phoneNumber") || this.mChooseMethod.getSelection() != 0) {
            for (String toggle : this.mManager.getMandatoryToggles()) {
                CheckBox checkBox = (CheckBox) getView().findViewById(getResources().getIdentifier(toggle + "_checkbox", "id", getActivity().getPackageName()));
                if (checkBox != null && !checkBox.isChecked()) {
                    this.mFinishButton.setEnabled(false);
                    return;
                }
            }
            this.mFinishButton.setEnabled(true);
        }
    }

    private void clickedTermsAndConditions() {
        Ensighten.evaluateEvent(this, "clickedTermsAndConditions", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Terms & Conditions");
        String link = AppUtils.getLocalisedLegalUrl("registerTOC");
        if (link != null) {
            Bundle attributes = new Bundle();
            attributes.putString("link", link);
            attributes.putString("analytics_title", getString(C2658R.string.analytics_screen_register_terms));
            getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
            return;
        }
        AsyncException.report("No Privacy Policy URL Defined");
    }

    private void configurePolicyUpdatesString(TextView textView) {
        String minDrivingAge;
        Ensighten.evaluateEvent(this, "configurePolicyUpdatesString", new Object[]{textView});
        String minAge = (String) Configuration.getSharedInstance().getValueForKey("interface.termsAndConditions.minimumRequiredAge");
        if (TextUtils.isEmpty(minAge)) {
            minDrivingAge = "";
        } else {
            minDrivingAge = minAge;
        }
        String template = getString(C2658R.string.lite_label_policy_agree);
        SpannableString terms = new SpannableString(getString(C2658R.string.lite_btn_tnc));
        terms.setSpan(new C319917(), 0, terms.length(), 33);
        SpannableString privacyPolicy = new SpannableString(getString(C2658R.string.lite_btn_privacy_policy));
        privacyPolicy.setSpan(new C320018(), 0, privacyPolicy.length(), 33);
        textView.setText(StringUtils.formatWithSpans(template, terms, privacyPolicy, minDrivingAge));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void clickedPrivacyPolicy() {
        Ensighten.evaluateEvent(this, "clickedPrivacyPolicy", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Privacy Policy");
        String privacyURL = AppUtils.getLocalisedLegalUrl("privacy");
        if (privacyURL != null) {
            Bundle attributes = new Bundle();
            attributes.putString("link", privacyURL);
            attributes.putString("analytics_title", getString(C2658R.string.analytics_screen_register_privacy));
            startActivity(WebActivity.class, attributes);
            return;
        }
        AsyncException.report("No Privacy Policy URL Defined");
    }
}
