package com.mcdonalds.app.msa;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.SignUpActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;

public class MSANotLoggedInLandingFragment extends URLNavigationFragment {

    /* renamed from: com.mcdonalds.app.msa.MSANotLoggedInLandingFragment$1 */
    class C32671 extends ClickableSpan {
        C32671() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            MSANotLoggedInLandingFragment.this.startActivity(SignUpActivity.class);
            MSANotLoggedInLandingFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MSANotLoggedInLandingFragment$2 */
    class C32682 extends ClickableSpan {
        C32682() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            MSANotLoggedInLandingFragment.this.startActivity(SignInActivity.class, MSANotLoggedInLandingFragment.this.getArguments());
            MSANotLoggedInLandingFragment.this.getNavigationActivity().finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.appmenu_msa);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_msa_landing_page_not_logged_in, null);
        TextView instr = (TextView) rootView.findViewById(C2358R.C2357id.msa_landing_instruction);
        String instrText = instr.getText().toString();
        String clickText = getString(C2658R.string.appmenu_register);
        SpannableStringBuilder ssb = new SpannableStringBuilder(instrText);
        setupClickText(instrText, clickText, ssb, new C32671());
        setupClickText(instrText, getString(C2658R.string.appmenu_sign_in), ssb, new C32682());
        instr.setText(ssb);
        instr.setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }

    private void setupClickText(String instrText, String clickText, SpannableStringBuilder ssb, ClickableSpan cs) {
        Ensighten.evaluateEvent(this, "setupClickText", new Object[]{instrText, clickText, ssb, cs});
        int index = instrText.toLowerCase().indexOf(clickText.toLowerCase());
        if (index != -1) {
            ssb.setSpan(cs, index, clickText.length() + index, 0);
            ssb.setSpan(new ForegroundColorSpan(-1), index, clickText.length() + index, 0);
            ssb.setSpan(new StyleSpan(1), index, clickText.length() + index, 33);
        }
    }
}
