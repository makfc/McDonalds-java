package com.mcdonalds.app.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.gma.hongkong.C2658R;

public class LoginHelpFragment extends URLNavigationFragment {

    /* renamed from: com.mcdonalds.app.customer.LoginHelpFragment$1 */
    class C30121 implements OnClickListener {
        C30121() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (LoginManager.getInstance().getRegisterSettings().chooseSignInMethodEnabled()) {
                LoginHelpFragment.this.startActivity(ProfileUpdateActivity.class, "reset_password");
            } else {
                LoginHelpFragment.this.showFragment(LostPasswordFragment.NAME);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().loadRegisterConfig();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_login_help, container, false);
        ((Button) view.findViewById(C2358R.C2357id.button_lost_password)).setOnClickListener(new C30121());
        return view;
    }
}
