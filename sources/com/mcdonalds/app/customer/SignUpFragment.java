package com.mcdonalds.app.customer;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.style.ClickableSpan;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.account.OfferData;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.ChooseMethodView.SelectionListener;
import com.mcdonalds.app.customer.push.OffersRequestActivity;
import com.mcdonalds.app.model.RegisterToggle;
import com.mcdonalds.app.social.SocialLoginFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.utils.ListUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class SignUpFragment extends SocialLoginFragment {
    private static Calendar birthdate;
    public static boolean isSocialRegister = false;
    private static Button mBirthdateButton;
    String birthdateFormatted;
    private CheckBox collection_data_acceptance_checkbox;
    final Calendar currentDate = Calendar.getInstance(Locale.ENGLISH);
    private ArrayAdapter<CharSequence> gendersAdapter;
    private View mAlreadyRegistered;
    private boolean mAutoEnrollOffersByDefault;
    private ChooseMethodView mChooseMethod;
    private View mChooseMethodContainer;
    private EditText mEmail;
    private EditText mEmailConfirmation;
    private Button mFinishButton;
    private EditText mFirstName;
    private Spinner mGender;
    private final OnItemSelectedListener mGenderSelected = new C306718();
    private boolean mHideOffersOptInPage = false;
    private EditText mLastName;
    private View mMailAsUser;
    private LoginManager mManager;
    private EditText mMiddleName;
    private List<OfferCategory> mOfferCategories;
    private OffersModule mOffersModule;
    private final OnClickListener mOnCheckboxClicked = new C30767();
    private final OnClickListener mOnClickBirthdate = new C306819();
    private final OnClickListener mOnClickFinish = new C30745();
    private final OnClickListener mOnClickPrivacy = new C30778();
    private final OnClickListener mOnClickResetPassword = new C306617();
    private final OnClickListener mOnClickSignIn = new C30756();
    private final OnClickListener mOnClickTOS = new C305710();
    private final OnClickListener mOnClickTermsAndConditions = new C30789();
    private final OnFocusChangeListener mOnFocusChangeListener = new C306013();
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private TextView mPasswordDescription;
    private final InputFilter mPasswordInputFilter = new C305811();
    private EditText mPhoneNumber;
    private CustomerProfile mProfile;
    private View mSocialContainer;
    private int mSocialLoginId = -1;
    private EditText mUserName;
    private Callback mValidationCallback = new C305912();
    private SparseArray<ValidationListener> mValidations;
    private EditText mZipCode;
    private String selectedGender;
    private boolean useSocialSignUp;
    private boolean useSocialSignUpWithoutEmail;

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$10 */
    class C305710 implements OnClickListener {
        C305710() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String link = AppUtils.getLocalisedLegalUrl("registerTOC");
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", SignUpFragment.this.getString(C2658R.string.analytics_screen_register_terms));
                SignUpFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Privacy Policy URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$11 */
    class C305811 implements InputFilter {
        C305811() {
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

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$12 */
    class C305912 implements Callback {
        C305912() {
        }

        public void onFieldValidationStateChanged(boolean isValidated) {
            Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
            SignUpFragment.access$000(SignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$13 */
    class C306013 implements OnFocusChangeListener {
        C306013() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
            if (!hasFocus) {
                ValidationListener validation = (ValidationListener) Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$500", new Object[]{SignUpFragment.this}).get(v.getId());
                if (validation != null) {
                    if (!validation.isValidated() && validation.getType() != 10) {
                        validation.displayError();
                    }
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2200", new Object[]{SignUpFragment.this}).getSelection() == 0) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2200", new Object[]{SignUpFragment.this}).displayError();
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$15 */
    class C306415 implements AsyncListener<List<OfferCategory>> {
        C306415() {
        }

        public void onResponse(List<OfferCategory> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (SignUpFragment.this.getNavigationActivity() != null) {
                SignUpFragment.access$2502(SignUpFragment.this, response);
                SignUpFragment.access$2600(SignUpFragment.this, true);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$16 */
    class C306516 implements AsyncListener<List<OfferCategory>> {
        C306516() {
        }

        public void onResponse(List<OfferCategory> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            SignUpFragment.access$2502(SignUpFragment.this, response);
            SignUpFragment.access$2700(SignUpFragment.this);
            SignUpFragment.access$1900(SignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$17 */
    class C306617 implements OnClickListener {
        C306617() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            SignUpFragment.this.startActivity(ProfileUpdateActivity.class, "reset_password");
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$18 */
    class C306718 implements OnItemSelectedListener {
        C306718() {
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemSelected", new Object[]{parent, view, new Integer(position), new Long(id)});
            SignUpFragment.access$2802(SignUpFragment.this, (String) Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2900", new Object[]{SignUpFragment.this}).getItem(position));
        }

        public void onNothingSelected(AdapterView<?> parent) {
            Ensighten.evaluateEvent(this, "onNothingSelected", new Object[]{parent});
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$19 */
    class C306819 implements OnClickListener {
        C306819() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            View view = SignUpFragment.this.getActivity().getCurrentFocus();
            if (view != null) {
                ((InputMethodManager) SignUpFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            SignUpFragment.access$3000(SignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$1 */
    class C30691 implements SelectionListener {
        C30691() {
        }

        public void onMethodSelected(int selection) {
            Ensighten.evaluateEvent(this, "onMethodSelected", new Object[]{new Integer(selection)});
            SignUpFragment.access$000(SignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$20 */
    class C307020 implements OnDateSetListener {
        C307020() {
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Ensighten.evaluateEvent(this, "onDateSet", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
            SignUpFragment.access$3100(SignUpFragment.this, year, monthOfYear, dayOfMonth);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$2 */
    class C30712 extends ClickableSpan {
        final /* synthetic */ SignUpFragment this$0;

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            SignUpFragment.access$100(this.this$0);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$3 */
    class C30723 extends ClickableSpan {
        final /* synthetic */ SignUpFragment this$0;

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            SignUpFragment.access$200(this.this$0);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$5 */
    class C30745 implements OnClickListener {
        C30745() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignUpFragment.this.getAnalyticsTitle(), "Continue");
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$600", new Object[]{SignUpFragment.this})) {
                SignUpFragment.access$700(SignUpFragment.this);
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$800", new Object[]{SignUpFragment.this})) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1000", new Object[]{SignUpFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$900", new Object[]{SignUpFragment.this})}).isEmpty()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1100", new Object[]{SignUpFragment.this}).setFirstName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$900", new Object[]{SignUpFragment.this}).getHint().toString());
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1100", new Object[]{SignUpFragment.this}).setFirstName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1000", new Object[]{SignUpFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$900", new Object[]{SignUpFragment.this})}));
                }
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1200", new Object[]{SignUpFragment.this}).showField("birthDate")) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1100", new Object[]{SignUpFragment.this}).setBirthDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1300", null));
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1100", new Object[]{SignUpFragment.this}).setMobileNumber(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1000", new Object[]{SignUpFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1400", new Object[]{SignUpFragment.this})}));
            } else {
                SignUpFragment.access$1500(SignUpFragment.this);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1100", new Object[]{SignUpFragment.this}).setUsingSocialLogin(false);
            }
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1600", new Object[]{SignUpFragment.this})) {
                SignUpFragment.access$2000(SignUpFragment.this);
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1700", new Object[]{SignUpFragment.this})) {
                SignUpFragment.access$1800(SignUpFragment.this);
            } else {
                SignUpFragment.access$1900(SignUpFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$6 */
    class C30756 implements OnClickListener {
        C30756() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignUpFragment.this.getAnalyticsTitle(), "Need an Account");
            SignUpFragment.this.startActivity(SignInActivity.class);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$7 */
    class C30767 implements OnClickListener {
        C30767() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (v.getTag() != null) {
                AnalyticsUtils.trackOnClickEvent(SignUpFragment.this.getAnalyticsTitle(), (String) v.getTag());
            }
            SignUpFragment.access$000(SignUpFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$8 */
    class C30778 implements OnClickListener {
        C30778() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignUpFragment.this.getAnalyticsTitle(), "Privacy Policy");
            String privacyURL = AppUtils.getLocalisedLegalUrl("privacy");
            if (privacyURL != null) {
                SignUpFragment.access$2100(SignUpFragment.this, privacyURL);
            } else {
                AsyncException.report("No Privacy Policy URL Defined");
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$9 */
    class C30789 implements OnClickListener {
        C30789() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String termsURL = AppUtils.getLocalisedLegalUrl("registerTOC");
            if (termsURL != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", termsURL);
                SignUpFragment.this.startActivity(WebActivity.class, attributes);
                return;
            }
            AsyncException.report("No Terms and Condition URL Defined");
        }
    }

    public class MonPickerDialog extends DatePickerDialog {
        @TargetApi(16)
        public MonPickerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
            super(context, theme, listener, year, monthOfYear, dayOfMonth);
            getDatePicker().setMaxDate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$3200", new Object[]{SignUpFragment.this}));
            int daySpinnerId = SignUpFragment.this.getResources().getIdentifier("day", "id", "android");
            if (daySpinnerId != 0) {
                View daySpinner = getDatePicker().findViewById(daySpinnerId);
                if (daySpinner != null) {
                    daySpinner.setVisibility(8);
                }
            }
            setButton(-2, SignUpFragment.this.getString(C2658R.string.cancel), new DialogInterface.OnClickListener(SignUpFragment.this) {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    if (which == -2) {
                        SignUpFragment.access$1302(null);
                        String birthdateLabel = SignUpFragment.this.getString(C2658R.string.signup_birthdate);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$3300", null).setText(null);
                    }
                }
            });
        }

        public void onDateChanged(DatePicker view, int year, int month, int day) {
            Ensighten.evaluateEvent(this, "onDateChanged", new Object[]{view, new Integer(year), new Integer(month), new Integer(day)});
            super.onDateChanged(view, year, month, day);
            setTitle("");
        }
    }

    static /* synthetic */ void access$000(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$000", new Object[]{x0});
        x0.validateData();
    }

    static /* synthetic */ void access$100(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$100", new Object[]{x0});
        x0.onClickTOS();
    }

    static /* synthetic */ Calendar access$1302(Calendar x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1302", new Object[]{x0});
        birthdate = x0;
        return x0;
    }

    static /* synthetic */ void access$1500(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1500", new Object[]{x0});
        x0.updateProfile();
    }

    static /* synthetic */ void access$1800(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1800", new Object[]{x0});
        x0.getOfferCategories();
    }

    static /* synthetic */ void access$1900(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$1900", new Object[]{x0});
        x0.register();
    }

    static /* synthetic */ void access$200(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$200", new Object[]{x0});
        x0.onClickPrivacy();
    }

    static /* synthetic */ void access$2000(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2000", new Object[]{x0});
        x0.chooseOfferPreferences();
    }

    static /* synthetic */ void access$2100(SignUpFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2100", new Object[]{x0, x1});
        x0.openLegalUrl(x1);
    }

    static /* synthetic */ void access$2300(SignUpFragment x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2300", new Object[]{x0, x1});
        x0.hideAllOtherViews(x1);
    }

    static /* synthetic */ List access$2502(SignUpFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2502", new Object[]{x0, x1});
        x0.mOfferCategories = x1;
        return x1;
    }

    static /* synthetic */ void access$2600(SignUpFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2600", new Object[]{x0, new Boolean(x1)});
        x0.validateRegisterNavigateHome(x1);
    }

    static /* synthetic */ void access$2700(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2700", new Object[]{x0});
        x0.enrollOffersByDefault();
    }

    static /* synthetic */ String access$2802(SignUpFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2802", new Object[]{x0, x1});
        x0.selectedGender = x1;
        return x1;
    }

    static /* synthetic */ void access$3000(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$3000", new Object[]{x0});
        x0.selectMonthTime();
    }

    static /* synthetic */ void access$3100(SignUpFragment x0, int x1, int x2, int x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$3100", new Object[]{x0, new Integer(x1), new Integer(x2), new Integer(x3)});
        x0.setBirthDay(x1, x2, x3);
    }

    static /* synthetic */ void access$700(SignUpFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$700", new Object[]{x0});
        x0.updateProfileWithoutEmail();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_register);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mManager = LoginManager.getInstance();
        this.mManager.loadRegisterConfig();
        this.mHideOffersOptInPage = Configuration.getSharedInstance().getBooleanForKey("interface.register.hideOffersOptinPage");
        this.mAutoEnrollOffersByDefault = Configuration.getSharedInstance().getBooleanForKey("interface.signin.autoEnrollOffersByDefault");
        checkForOffersModule();
        DataLayerManager.getInstance().setFormName("Register Form");
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        Ensighten.evaluateEvent(this, "getLayoutResourceId", null);
        return C2658R.layout.fragment_signup;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            throw new RuntimeException("SignUpFragment super.onCreateView is null");
        }
        this.mFirstName = (EditText) view.findViewById(C2358R.C2357id.signup_firstname);
        this.mLastName = (EditText) view.findViewById(C2358R.C2357id.signup_lastname);
        this.mMiddleName = (EditText) view.findViewById(C2358R.C2357id.signup_middlename);
        this.mEmail = (EditText) view.findViewById(C2358R.C2357id.signup_email);
        this.mEmailConfirmation = (EditText) view.findViewById(C2358R.C2357id.signup_email_confirm);
        this.mUserName = (EditText) view.findViewById(C2358R.C2357id.signup_username);
        this.mZipCode = (EditText) view.findViewById(C2358R.C2357id.signup_zipcode);
        this.mPhoneNumber = (EditText) view.findViewById(C2358R.C2357id.signup_phone);
        this.mGender = (Spinner) view.findViewById(C2358R.C2357id.signup_gender);
        this.mPassword = (EditText) view.findViewById(C2358R.C2357id.signup_password);
        this.mPasswordConfirm = (EditText) view.findViewById(C2358R.C2357id.signup_confirm_password);
        this.mPasswordDescription = (TextView) view.findViewById(C2358R.C2357id.password_description);
        mBirthdateButton = (Button) view.findViewById(C2358R.C2357id.signup_birthdate);
        mBirthdateButton.setOnClickListener(this.mOnClickBirthdate);
        this.mSocialContainer = view.findViewById(C2358R.C2357id.social_container);
        this.mChooseMethodContainer = view.findViewById(C2358R.C2357id.container_choose_method);
        this.mMailAsUser = view.findViewById(C2358R.C2357id.signup_mail_as_user);
        this.mAlreadyRegistered = view.findViewById(C2358R.C2357id.already_registered_error);
        this.mChooseMethod = (ChooseMethodView) view.findViewById(C2358R.C2357id.choose_method);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.useMobileNumberRegisterOnly")) {
            this.mChooseMethod.setSelection(2);
            this.mChooseMethod.setEnabled(false);
        }
        this.mChooseMethod.setSelectionListener(new C30691());
        this.mFinishButton = (Button) view.findViewById(C2358R.C2357id.signup_button_finish);
        this.mFinishButton.setEnabled(false);
        this.mFinishButton.setOnClickListener(this.mOnClickFinish);
        this.collection_data_acceptance_checkbox = (CheckBox) view.findViewById(C2358R.C2357id.registration_data_collection_terms_acceptance_checkbox);
        this.collection_data_acceptance_checkbox.setChecked(true);
        ((Button) view.findViewById(C2358R.C2357id.has_account)).setOnClickListener(this.mOnClickSignIn);
        view.findViewById(C2358R.C2357id.btn_reset_password).setOnClickListener(this.mOnClickResetPassword);
        this.gendersAdapter = ArrayAdapter.createFromResource(getActivity(), C2658R.array.signup_genders, 17367049);
        this.gendersAdapter.setDropDownViewResource(17367049);
        this.mGender.setAdapter(this.gendersAdapter);
        this.mGender.setOnItemSelectedListener(this.mGenderSelected);
        this.selectedGender = "";
        birthdate = new GregorianCalendar();
        configure(view);
        configureToggles(view);
        String pwdDescription = Configuration.getSharedInstance().getStringForKey("textValidation.password_description");
        if (!Strings.isNullOrEmpty(pwdDescription)) {
            this.mPasswordDescription.setText(Configuration.getSharedInstance().localizedStringForKey(pwdDescription));
        }
        if (Configuration.getSharedInstance().getMarket().equals("CN")) {
            this.mPasswordDescription.setText(C2658R.string.password_description_china);
        }
        if (Configuration.getSharedInstance().getBooleanForKey("interface.disableInteraction")) {
            UIUtils.disableInteraction(this.mEmail);
            UIUtils.disableInteraction(this.mPhoneNumber);
        }
        return view;
    }

    private void hideAllOtherViews(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "hideAllOtherViews", new Object[]{profile});
        if (profile == null) {
            return;
        }
        if (profile.isUsingSocialLoginWithoutEmail() || profile.isUsingSocialLogin()) {
            this.mProfile = profile;
            this.acces_token = profile.getSocialAuthenticationToken();
            this.openid = profile.getSocialUserID();
            this.nickName = profile.getFirstName();
            this.useSocialSignUpWithoutEmail = profile.isUsingSocialLoginWithoutEmail();
            this.useSocialSignUp = profile.isUsingSocialLogin();
            this.mProfile.setEmailAddress(profile.getUserName());
            this.mProfile.setSocialAccountLoginRegistered(true);
            this.mFirstName.setText(this.nickName);
            this.mFirstName.setEnabled(false);
            this.mLastName.setVisibility(8);
            this.mMiddleName.setVisibility(8);
            this.mEmail.setVisibility(8);
            this.mEmailConfirmation.setVisibility(8);
            this.mZipCode.setVisibility(8);
            this.mGender.setVisibility(8);
            this.mChooseMethodContainer.setVisibility(8);
            this.mPassword.setVisibility(8);
            this.mPasswordConfirm.setVisibility(8);
            this.mPasswordDescription.setVisibility(8);
            this.mValidations = new SparseArray();
            addValidation(this.mPhoneNumber, 5);
        }
    }

    private void configureToggles(View view) {
        Ensighten.evaluateEvent(this, "configureToggles", new Object[]{view});
        if (!ListUtils.isEmpty(this.mManager.getMandatoryToggles())) {
            for (RegisterToggle registerToggle : this.mManager.getRegisterSettings().getToggles()) {
                if (!("terms_and_conditions".equals(registerToggle.getName()) || "three_links_toc".equals(registerToggle.getName()) || "age_verification".equals(registerToggle.getName()) || "email_opt_in".equals(registerToggle.getName()))) {
                    createCheckBox(getContext(), registerToggle, this.mOnCheckboxClicked);
                }
            }
        }
        for (String field : this.mManager.getVisibleFields()) {
            if (!Strings.isNullOrEmpty(UIUtils.getStringByName(getNavigationActivity(), field))) {
                int id = getResources().getIdentifier(field + "_checkbox", "id", getActivity().getPackageName());
                if (id != 0) {
                    CheckBox checkBox = (CheckBox) view.findViewById(id);
                    if (checkBox != null) {
                        checkBox.setVisibility(0);
                        checkBox.setChecked(this.mManager.getDefaultState(field));
                        checkBox.setOnClickListener(this.mOnCheckboxClicked);
                    }
                }
            }
        }
    }

    @NonNull
    private static CheckBox createCheckBox(Context context, RegisterToggle registerToggle, OnClickListener listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "createCheckBox", new Object[]{context, registerToggle, listener});
        CheckBox checkBox = new CheckBox(context);
        checkBox.setTag(registerToggle.getName());
        checkBox.setText(UIUtils.getStringByName(context, registerToggle.getName()));
        checkBox.setChecked(registerToggle.getDefaultState());
        checkBox.setOnClickListener(listener);
        return checkBox;
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
            addValidation(this.mLastName, 11);
            this.mLastName.setVisibility(8);
        } else {
            this.mFirstName.setHint(getString(C2658R.string.text_hint_your_name));
        }
        if (this.mManager.showField("middlename")) {
            addValidation(this.mMiddleName, 11);
            this.mMiddleName.setVisibility(8);
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
        }
        if (this.mManager.showField("selectPhoneOrEmailAsUsername")) {
            this.mChooseMethodContainer.setVisibility(0);
        } else {
            this.mChooseMethodContainer.setVisibility(8);
        }
        if (!this.mManager.showField("username") || isUseEmailAsUserName()) {
            this.mChooseMethodContainer.setVisibility(8);
        } else {
            this.mUserName.setVisibility(0);
            addValidation(this.mUserName, 4);
        }
        setPhoneValidation(view);
        setMailValidation(view);
        if (this.mManager.showField("confirmEmailAddress")) {
            this.mEmailConfirmation.setVisibility(0);
            setConfirmEmailValidation(view);
        }
        addValidation(new ValidationListener(this.mPassword, this.mPasswordConfirm, 1, true, false, true));
        ValidationListener confirmPasswordValidation = new ValidationListener(this.mPasswordConfirm, this.mPassword, 1, true, true, true);
        confirmPasswordValidation.setValidationCallback(this.mValidationCallback);
        addValidation(confirmPasswordValidation);
        InputFilter[] passwordFilters = new InputFilter[]{this.mPasswordInputFilter};
        this.mPassword.setFilters(passwordFilters);
        this.mPasswordConfirm.setFilters(passwordFilters);
    }

    private boolean isUseEmailAsUserName() {
        Ensighten.evaluateEvent(this, "isUseEmailAsUserName", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.useEmailAsUsername");
    }

    private void setConfirmEmailValidation(View view) {
        Ensighten.evaluateEvent(this, "setConfirmEmailValidation", new Object[]{view});
        addValidation(new ValidationListener(this.mEmailConfirmation, this.mEmail, 0, true));
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
                } else if (v.getId() != Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$300", new Object[]{SignUpFragment.this}).getId()) {
                    return true;
                } else {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$400", new Object[]{SignUpFragment.this}).isEnabled()) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$400", new Object[]{SignUpFragment.this}).callOnClick();
                        return true;
                    }
                    int i = 0;
                    while (i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$500", new Object[]{SignUpFragment.this}).size()) {
                        ValidationListener listener = (ValidationListener) Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$500", new Object[]{SignUpFragment.this}).get(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$500", new Object[]{SignUpFragment.this}).keyAt(i));
                        if (listener.isValidated()) {
                            i++;
                        } else {
                            listener.getTextField().requestFocus();
                            listener.displayError();
                            return true;
                        }
                    }
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

    private void onClickTOS() {
        Ensighten.evaluateEvent(this, "onClickTOS", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Terms & Conditions");
        String link = AppUtils.getLocalisedLegalUrl("registerTOC");
        if (link != null) {
            openLegalUrl(link);
        } else {
            AsyncException.report("No Terms and Condition URL Defined");
        }
    }

    private void onClickPrivacy() {
        Ensighten.evaluateEvent(this, "onClickPrivacy", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Privacy Policy");
        String link = AppUtils.getLocalisedLegalUrl("privacy");
        if (link != null) {
            openLegalUrl(link);
        } else {
            AsyncException.report("No Privacy Policy URL Defined");
        }
    }

    private void openLegalUrl(String link) {
        Ensighten.evaluateEvent(this, "openLegalUrl", new Object[]{link});
        Bundle attributes = new Bundle();
        attributes.putString("link", link);
        attributes.putString("analytics_title", getString(C2658R.string.analytics_screen_register_privacy));
        startActivity(WebActivity.class, attributes);
    }

    private void updateProfileWithoutEmail() {
        Ensighten.evaluateEvent(this, "updateProfileWithoutEmail", null);
        initProfile();
        this.mProfile.setEmailAddress("");
        if (getTrimmedText(this.mFirstName).isEmpty()) {
            this.mProfile.setFirstName(this.mFirstName.getHint().toString());
        } else {
            this.mProfile.setFirstName(getTrimmedText(this.mFirstName));
        }
        this.mProfile.setLastName(getTrimmedText(this.mLastName));
        this.mProfile.setMiddleName(getTrimmedText(this.mMiddleName));
        this.mProfile.setZipCode(getTrimmedText(this.mZipCode));
        this.mProfile.setMobileNumber(getTrimmedText(this.mPhoneNumber));
        this.mProfile.setLoginPreference(3);
        this.mProfile.setUserName(this.openid);
        this.mProfile.setUsingSocialLogin(true);
        this.mProfile.setUsingSocialLoginWithoutEmail(true);
        this.mProfile.setSocialUserID(this.openid);
        this.mProfile.setSocialAuthenticationToken(this.acces_token);
        this.mProfile.setSocialServiceAuthenticationID(3);
        this.mProfile.setPreferredNotification(Integer.valueOf(2));
        this.mManager.setProfile(this.mProfile);
    }

    private void updateProfile() {
        boolean z = true;
        Ensighten.evaluateEvent(this, "updateProfile", null);
        initProfile();
        this.mProfile.setEmailAddress(getTrimmedText(this.mEmail));
        this.mProfile.setMobileNumber(getTrimmedText(this.mPhoneNumber));
        this.mProfile.setFirstName(getTrimmedText(this.mFirstName));
        this.mProfile.setLastName(getTrimmedText(this.mLastName));
        this.mProfile.setMiddleName(getTrimmedText(this.mMiddleName));
        this.mProfile.setZipCode(getTrimmedText(this.mZipCode));
        this.mProfile.setPassword(getTrimmedText(this.mPassword));
        this.mProfile.setPreferredNotification(Integer.valueOf(this.mChooseMethod.getSelection()));
        if (this.mManager.showField("phoneNumber") && this.mManager.showField("selectPhoneOrEmailAsUsername")) {
            this.mProfile.setMobileNumber(getTrimmedText(this.mPhoneNumber));
            this.mProfile.setLoginPreference(this.mChooseMethod.getSelection());
            if (this.mChooseMethod.getSelection() == 1) {
                this.mProfile.setUserName(getTrimmedText(this.mEmail));
            } else {
                this.mProfile.setUserName(getTrimmedText(this.mPhoneNumber));
            }
        } else if (isUseEmailAsUserName()) {
            this.mProfile.setUserName(getTrimmedText(this.mEmail));
        } else {
            this.mProfile.setUserName(getTrimmedText(this.mUserName));
        }
        if (this.mManager.showField("gender")) {
            this.mProfile.setGender(this.selectedGender.toLowerCase());
        }
        if (this.mManager.showField("birthDate")) {
            this.mProfile.setBirthDate(birthdate);
        }
        CustomerProfile customerProfile = this.mProfile;
        if (Configuration.getSharedInstance().getBooleanForKey(DCSProfile.KEY_REQUIRES_ACTIVATION)) {
            z = false;
        }
        customerProfile.setEmailActivated(z);
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
        return getResources().getString(C2658R.string.title_activity_signup);
    }

    public void onStart() {
        super.onStart();
        ModuleManager.getModule(CustomerModule.NAME);
    }

    public static void setupPostalCode(EditText postalCode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "setupPostalCode", new Object[]{postalCode});
        String POSTALCODE_ALPHANUMERIC = "interface.register.postalCodeAlphaNumeric";
        String POSTALCODE_MAXLENGTH = "interface.register.postalCodeMaxSize";
        boolean postalCodeAlphaNumeric = Configuration.getSharedInstance().getBooleanForKey("interface.register.postalCodeAlphaNumeric");
        int postalCodeMaxLength = Configuration.getSharedInstance().getIntForKey("interface.register.postalCodeMaxSize");
        if (postalCodeMaxLength == 0) {
            postalCodeMaxLength = 8;
        }
        InputFilter[] filters = new InputFilter[]{new LengthFilter(postalCodeMaxLength)};
        postalCode.setInputType(postalCodeAlphaNumeric ? 112 : 2);
        postalCode.setFilters(filters);
    }

    public void onSocialNetworkAvailable() {
        Ensighten.evaluateEvent(this, "onSocialNetworkAvailable", null);
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.register.hideSocialLogin")) {
            if (isSocialRegister) {
                hideAllOtherViews(this.mManager.getProfile());
                this.mSocialContainer.setVisibility(8);
                isSocialRegister = false;
                return;
            }
            this.mSocialContainer.setAlpha(0.0f);
            this.mSocialContainer.setVisibility(0);
            this.mSocialContainer.animate().alpha(1.0f).setDuration(150).start();
        }
    }

    public void onSocialNetworkSelected(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkSelected", new Object[]{socialNetwork});
        initProfile();
        this.mProfile.setSocialServiceAuthenticationID(socialNetwork.getSocialNetworkID());
        this.mProfile.setUsingSocialLogin(true);
        this.mSocialLoginId = socialNetwork.getType();
        super.onSocialNetworkSelected(socialNetwork);
    }

    public void onSocialNetworkAuthenticationComplete(SocialLoginAuthenticationResults results) {
        Ensighten.evaluateEvent(this, "onSocialNetworkAuthenticationComplete", new Object[]{results});
        final AuthenticationParameters parameters;
        if (!results.isAllowSocialLoginWithoutEmail() && results.getEmailAddress() == null) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.validate_social_network)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
        } else if (results.isAllowSocialLoginWithoutEmail() || results.getEmailAddress() == null) {
            if (results.isAllowSocialLoginWithoutEmail()) {
                this.acces_token = results.getAccessToken();
                this.openid = results.getUserId();
                this.nickName = results.getFirstName();
                this.useSocialSignUpWithoutEmail = true;
                parameters = new AuthenticationParameters();
                parameters.setUserName(results.getUserId());
                parameters.setAllowSocialLoginWithoutEmail(true);
                parameters.setUsingSocialLogin(true);
                parameters.setSocialServiceID(this.mSocialLoginId);
                parameters.setFirstName(results.getFirstName());
                parameters.setSocialAuthenticationToken(results.getAccessToken());
                parameters.setSocialUserID(results.getUserId());
                UIUtils.startActivityIndicator(getActivity(), null);
                final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                customerModule.authenticate(parameters, new AsyncListener<CustomerProfile>() {
                    public void onResponse(final CustomerProfile response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        if (exception == null) {
                            response.setUsingSocialLogin(true);
                            response.setUsingSocialLoginWithoutEmail(true);
                            response.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                            if (response.getSocialAuthenticationToken() == null || response.getSocialAuthenticationToken().isEmpty()) {
                                response.setSocialAuthenticationToken(parameters.getSocialAuthenticationToken());
                            }
                            response.setSocialUserID(parameters.getSocialUserID());
                            if (!response.isSocialAccountLoginRegistered()) {
                                SignUpFragment.access$2300(SignUpFragment.this, response);
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpFragment", "access$2400", new Object[]{SignUpFragment.this}).setVisibility(8);
                                return;
                            } else if (response.isEmailActivated()) {
                                LocalDataManager dataManager = LocalDataManager.getSharedInstance();
                                dataManager.setPrefSavedLogin(response.getUserName());
                                dataManager.setPrefSavedSocialNetworkId(response.getSocialServiceAuthenticationID());
                                if (response.getSocialServiceAuthenticationID() == 3) {
                                    dataManager.setPrefSavedLoginPass(response.getSocialAuthenticationToken());
                                }
                                SignUpFragment.this.startActivity(MainActivity.class, "dashboard");
                                return;
                            } else {
                                C30621 c30621 = new DialogInterface.OnClickListener() {

                                    /* renamed from: com.mcdonalds.app.customer.SignUpFragment$14$1$1 */
                                    class C30611 implements AsyncListener<Void> {
                                        C30611() {
                                        }

                                        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                        }
                                    }

                                    public void onClick(DialogInterface dialog, int which) {
                                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                        AnalyticsUtils.trackOnClickEvent(SignUpFragment.this.getAnalyticsTitle(), "Resend Activation Email");
                                        customerModule.resendActivation(response, new C30611());
                                        MCDAlertDialogBuilder.withContext(SignUpFragment.this.getNavigationActivity()).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, null).setTitle(SignUpFragment.this.getString(C2658R.string.resend_sms_title)).setMessage((int) C2658R.string.activation_sms_sent).create().show();
                                    }
                                };
                                return;
                            }
                        }
                        AsyncException.report(exception);
                    }
                });
            }
        } else if (Configuration.getSharedInstance().getLocalizedStringForKey("modules.customer.connector").equals("MWCustomerSecurity")) {
            parameters = new AuthenticationParameters();
            parameters.setEmailAddress(results.getEmailAddress());
            parameters.setFirstName(results.getFirstName());
            parameters.setLastName(results.getLastName());
            parameters.setSocialUserID(results.getUserId());
            parameters.setSocialAuthenticationToken(results.getAccessToken());
            parameters.setSocialServiceID(this.mSocialLoginId);
            parameters.setUsingSocialLogin(true);
            if (!this.mHideOffersOptInPage) {
                chooseOfferPreferences();
            }
            UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_signing_in));
            LoginManager.getInstance().login(parameters, getNavigationActivity());
        } else {
            this.mProfile.setUsingSocialLogin(true);
            this.mProfile.setFirstName(results.getFirstName());
            this.mProfile.setLastName(results.getLastName());
            this.mProfile.setSocialAuthenticationToken(results.getAccessToken());
            this.mProfile.setSocialUserID(results.getUserId());
            this.mProfile.setEmailAddress(results.getEmailAddress());
            this.mProfile.setUserName(results.getEmailAddress());
            this.mProfile.setSocialProvider(this.provider);
            if (Configuration.getSharedInstance().hasKey("interface.register.defaultPreferredNotification")) {
                this.mProfile.setPreferredNotification(Integer.valueOf(Configuration.getSharedInstance().getIntForKey("interface.register.defaultPreferredNotification")));
            }
            this.nickName = this.mProfile.getFirstName();
            this.useSocialSignUpWithoutEmail = false;
            this.useSocialSignUp = true;
            this.mFirstName.setText(this.nickName);
            this.mFirstName.setEnabled(false);
            this.mLastName.setVisibility(8);
            this.mMiddleName.setVisibility(8);
            this.mEmail.setVisibility(8);
            this.mEmailConfirmation.setVisibility(8);
            this.mZipCode.setVisibility(8);
            this.mGender.setVisibility(8);
            this.mChooseMethodContainer.setVisibility(8);
            this.mPassword.setVisibility(8);
            this.mPasswordConfirm.setVisibility(8);
            this.mPasswordDescription.setVisibility(8);
            this.mValidations = new SparseArray();
            addValidation(this.mPhoneNumber, 5);
            this.mSocialContainer.setVisibility(8);
        }
    }

    private void register() {
        Ensighten.evaluateEvent(this, JiceArgs.EVENT_REGISTER, null);
        this.mManager.setProfile(this.mProfile);
        if (isActivityAlive()) {
            UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_registering));
            LoginManager.getInstance().register(getNavigationActivity());
        }
    }

    private void chooseOfferPreferences() {
        Ensighten.evaluateEvent(this, "chooseOfferPreferences", null);
        this.mManager.setProfile(this.mProfile);
        startActivityForResult(OffersRequestActivity.class, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1) {
            return;
        }
        if (resultCode == 1) {
            populateOfferCategories(true);
        } else {
            populateOfferCategories(false);
        }
    }

    private void populateOfferCategories(boolean acceptOffers) {
        Ensighten.evaluateEvent(this, "populateOfferCategories", new Object[]{new Boolean(acceptOffers)});
        AnalyticsUtils.trackOnClickEvent("/register/offer-opt", acceptOffers ? "Opt-In" : "Opt-Out");
        AnalyticsUtils.trackEvent(BusinessArgs.EVENT_REGISTRATION_OFFERS_OPT, acceptOffers ? BusinessArgs.VALUE_IN : BusinessArgs.VALUE_OUT);
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_registering));
        if (this.mOffersModule != null) {
            boolean hideOfferCategories = Configuration.getSharedInstance().getBooleanForKey("interface.offers.hideOfferCategoryPreferenceSelection", false);
            if (!acceptOffers || hideOfferCategories) {
                validateRegisterNavigateHome(acceptOffers);
                return;
            } else {
                this.mOffersModule.getOfferCategories(new C306415());
                return;
            }
        }
        this.mOffersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        populateOfferCategories(acceptOffers);
    }

    private void validateRegisterNavigateHome(boolean acceptOffers) {
        Ensighten.evaluateEvent(this, "validateRegisterNavigateHome", new Object[]{new Boolean(acceptOffers)});
        CustomerProfile profile = LoginManager.getInstance().getProfile();
        if (profile != null) {
            profile.setSubscribedToOffers(acceptOffers);
            profile.setReceivePromotions(Boolean.valueOf(true));
            if (this.mOfferCategories != null && profile.isSubscribedToOffers()) {
                List<Integer> preferredOfferCategories = new ArrayList();
                for (OfferCategory offerCategory : this.mOfferCategories) {
                    Integer categoryNum = offerCategory.getId();
                    OfferData offerData = new OfferData();
                    offerData.setId(categoryNum);
                    offerData.setDescription(offerCategory.getDescription());
                    offerData.setState(Boolean.valueOf(true));
                    preferredOfferCategories.add(offerData.getId());
                }
                profile.setPreferredOfferCategories(preferredOfferCategories);
            }
        }
        LoginManager.getInstance().register(getNavigationActivity());
    }

    private void getOfferCategories() {
        Ensighten.evaluateEvent(this, "getOfferCategories", null);
        this.mOffersModule.getOfferCategories(new C306516());
    }

    private void enrollOffersByDefault() {
        Ensighten.evaluateEvent(this, "enrollOffersByDefault", null);
        this.mProfile.setSubscribedToOffers(true);
        this.mProfile.setReceivePromotions(Boolean.valueOf(true));
        if (this.mOfferCategories != null) {
            List<Integer> preferredOfferCategories = new ArrayList();
            for (OfferCategory offerCategory : this.mOfferCategories) {
                Integer categoryNum = offerCategory.getId();
                OfferData offerData = new OfferData();
                offerData.setId(categoryNum);
                offerData.setDescription(offerCategory.getDescription());
                offerData.setState(Boolean.valueOf(true));
                preferredOfferCategories.add(offerData.getId());
            }
            this.mProfile.setPreferredOfferCategories(preferredOfferCategories);
        }
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
        for (String toggle : this.mManager.getMandatoryToggles()) {
            CheckBox checkBox = (CheckBox) getView().findViewById(getResources().getIdentifier(toggle + "_checkbox", "id", getActivity().getPackageName()));
            if (checkBox == null) {
                checkBox = (CheckBox) getView().findViewWithTag(toggle);
            }
            if (checkBox != null && !checkBox.isChecked()) {
                return;
            }
        }
        if (!this.mManager.showField("phoneNumber") || !this.mManager.showField("selectPhoneOrEmailAsUsername") || this.mChooseMethod.getSelection() != 0) {
            this.mFinishButton.setEnabled(true);
        } else if (this.useSocialSignUpWithoutEmail) {
            this.mFinishButton.setEnabled(true);
        }
    }

    private void selectMonthTime() {
        Ensighten.evaluateEvent(this, "selectMonthTime", null);
        new MonPickerDialog(getActivity(), 3, new C307020(), this.currentDate.get(1), this.currentDate.get(2), this.currentDate.get(5)).show();
    }

    private long getMaxDate() {
        Ensighten.evaluateEvent(this, "getMaxDate", null);
        Calendar minAgeCalendar = (Calendar) this.currentDate.clone();
        int maxYear = this.currentDate.get(1) - Integer.parseInt((String) Configuration.getSharedInstance().getValueForKey("interface.termsAndConditions.minimumRequiredAge"));
        if (birthdate == null) {
            minAgeCalendar.set(maxYear, this.currentDate.get(2), this.currentDate.get(5));
        } else {
            minAgeCalendar.set(maxYear, birthdate.get(2), birthdate.get(5));
        }
        return minAgeCalendar.getTimeInMillis();
    }

    private void setBirthDay(int year, int monthOfYear, int dayOfMonth) {
        int year1;
        int month;
        Ensighten.evaluateEvent(this, "setBirthDay", new Object[]{new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
        int day;
        if (birthdate == null) {
            birthdate = Calendar.getInstance();
            year1 = this.currentDate.get(1);
            month = this.currentDate.get(2);
            day = this.currentDate.get(5);
        } else {
            year1 = birthdate.get(1);
            month = birthdate.get(2);
            day = birthdate.get(5);
        }
        birthdate.set(year, monthOfYear, dayOfMonth);
        monthOfYear++;
        Calendar.getInstance().set(month, year1);
        mBirthdateButton.setText(clanderTodatetime(Calendar.getInstance(), "MM-yyyy"));
        this.birthdateFormatted = monthOfYear + "-" + year;
        Date date = null;
        DateFormat dateFormat = DateFormat.getDateInstance(1, Locale.getDefault());
        try {
            date = new SimpleDateFormat("yyyy-MM").parse(this.birthdateFormatted);
        } catch (ParseException e) {
        }
        if (date != null) {
            mBirthdateButton.setText(getString(C2658R.string.birthdate_prefix) + this.birthdateFormatted);
        } else {
            mBirthdateButton.setText(getString(C2658R.string.birthdate_prefix) + this.birthdateFormatted);
        }
    }

    public String clanderTodatetime(Calendar calendar, String style) {
        Ensighten.evaluateEvent(this, "clanderTodatetime", new Object[]{calendar, style});
        return new SimpleDateFormat(style).format(calendar.getTime());
    }
}
