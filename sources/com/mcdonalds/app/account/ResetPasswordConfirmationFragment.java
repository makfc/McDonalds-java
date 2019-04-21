package com.mcdonalds.app.account;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;

public class ResetPasswordConfirmationFragment extends URLNavigationFragment {
    private final OnClickListener mOnClickDone = new C30092();
    private int mValidationMethod;
    private String mValidationMethodValue;

    /* renamed from: com.mcdonalds.app.account.ResetPasswordConfirmationFragment$1 */
    class C30081 extends ClickableSpan {
        C30081() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            if (ResetPasswordConfirmationFragment.this.getNavigationActivity() != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("validation_method", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ResetPasswordConfirmationFragment", "access$000", new Object[]{ResetPasswordConfirmationFragment.this}));
                ResetPasswordConfirmationFragment.this.startActivity(ProfileUpdateActivity.class, "reset_password", bundle);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.ResetPasswordConfirmationFragment$2 */
    class C30092 implements OnClickListener {
        C30092() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ResetPasswordConfirmationFragment.this.getActivity().finish();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mValidationMethod = arguments.getInt("validation_method");
            this.mValidationMethodValue = arguments.getString("validation_method_value");
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_reset_confirmation, container, false);
        view.findViewById(C2358R.C2357id.button_done).setOnClickListener(this.mOnClickDone);
        SpannableString resendText = new SpannableString(getString(C2658R.string.text_reset_resend_link_click));
        resendText.setSpan(new C30081(), 0, resendText.length(), 256);
        TextView instructions = (TextView) view.findViewById(C2358R.C2357id.resend_link);
        instructions.setText(TextUtils.concat(new CharSequence[]{getText(C2658R.string.text_reset_resend_link), " ", resendText}));
        instructions.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }
}
