package com.mcdonalds.app.gmalite.customer;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;

public class LiteSmsVerificationEnterCodeFragment extends URLNavigationFragment {
    private LoginManager mManager;
    private final OnFocusChangeListener mOnFocusChangeListener = new C32153();
    protected URLNavigationActivity mParent;
    private Callback mValidationCallback = new C32142();
    private SparseArray<ValidationListener> mValidations;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationEnterCodeFragment$1 */
    class C32131 implements OnEditorActionListener {
        final /* synthetic */ ValidationListener val$validation;

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
            if (actionId != 6) {
                return false;
            }
            if (this.val$validation.isValidated()) {
                return true;
            }
            this.val$validation.displayError();
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationEnterCodeFragment$2 */
    class C32142 implements Callback {
        C32142() {
        }

        public void onFieldValidationStateChanged(boolean isValidated) {
            Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
            LiteSmsVerificationEnterCodeFragment.access$000(LiteSmsVerificationEnterCodeFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationEnterCodeFragment$3 */
    class C32153 implements OnFocusChangeListener {
        C32153() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
            if (!hasFocus) {
                ValidationListener validation = (ValidationListener) Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSmsVerificationEnterCodeFragment", "access$100", new Object[]{LiteSmsVerificationEnterCodeFragment.this}).get(v.getId());
                if (validation != null && !validation.isValidated()) {
                    validation.displayError();
                }
            }
        }
    }

    static /* synthetic */ void access$000(LiteSmsVerificationEnterCodeFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSmsVerificationEnterCodeFragment", "access$000", new Object[]{x0});
        x0.validateData();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return "";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParent = getNavigationActivity();
        this.mManager = LoginManager.getInstance();
        this.mManager.loadRegisterConfig();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_sms_verification_enter_code, container, false);
        if (view != null) {
            return view;
        }
        throw new RuntimeException("LiteSmsVerificationFragment super.onCreateView is null");
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.title_activity_sms_verification);
    }

    public void onStart() {
        super.onStart();
        ModuleManager.getModule(CustomerModule.NAME);
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
    }
}
