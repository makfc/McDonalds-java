package com.mcdonalds.app.msa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class MSAGenericFragment extends URLNavigationFragment {

    /* renamed from: com.mcdonalds.app.msa.MSAGenericFragment$1 */
    class C32621 implements OnClickListener {
        C32621() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            MSAGenericFragment.access$000(MSAGenericFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MSAGenericFragment$2 */
    class C32632 implements AsyncListener<CustomerProfile> {
        C32632() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            MSAGenericFragment.access$100(MSAGenericFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.msa.MSAGenericFragment$3 */
    class C32643 implements AsyncListener<CustomerProfile> {
        C32643() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            MSAGenericFragment.access$100(MSAGenericFragment.this);
        }
    }

    static /* synthetic */ void access$000(MSAGenericFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAGenericFragment", "access$000", new Object[]{x0});
        x0.proceedToHomePage();
    }

    static /* synthetic */ void access$100(MSAGenericFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSAGenericFragment", "access$100", new Object[]{x0});
        x0.startHomepage();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.appmenu_msa);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_msa_generic_page, null);
        rootView.findViewById(C2358R.C2357id.msa_generic_continue).setOnClickListener(new C32621());
        return rootView;
    }

    private void proceedToHomePage() {
        boolean useSocial = false;
        Ensighten.evaluateEvent(this, "proceedToHomePage", null);
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        String prefSavedLogin = localDataManager.getPrefSavedLogin();
        String prefSavedLoginPass = localDataManager.getPrefSavedLoginPass();
        int prefSavedSocialID = localDataManager.getPrefSavedSocialNetworkId();
        boolean autoLogin;
        if (TextUtils.isEmpty(prefSavedLogin) || ((TextUtils.isEmpty(prefSavedLoginPass) && prefSavedSocialID == -1) || !McDonaldsApplication.getInstance().isColdStart())) {
            autoLogin = false;
        } else {
            autoLogin = true;
        }
        if (prefSavedSocialID != -1) {
            useSocial = true;
        }
        CustomerModule cm = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        AuthenticationParameters parameters;
        if (cm.isLoggedIn() || !autoLogin) {
            startHomepage();
        } else if (!useSocial) {
            parameters = new AuthenticationParameters();
            parameters.setUserName(prefSavedLogin);
            parameters.setPassword(prefSavedLoginPass);
            UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.dialog_signing_in);
            cm.authenticate(parameters, new C32643());
        } else if (prefSavedSocialID == 3) {
            parameters = new AuthenticationParameters();
            parameters.setUserName(prefSavedLogin);
            parameters.setAllowSocialLoginWithoutEmail(true);
            parameters.setUsingSocialLogin(true);
            parameters.setSocialServiceID(prefSavedSocialID);
            parameters.setSocialAuthenticationToken(prefSavedLoginPass);
            parameters.setSocialUserID(prefSavedLogin);
            UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.dialog_signing_in);
            cm.authenticate(parameters, new C32632());
        } else {
            startHomepage();
        }
    }

    private void startHomepage() {
        Ensighten.evaluateEvent(this, "startHomepage", null);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Bundle b = new Bundle();
        b.putString(URLNavigationActivity.ARG_FRAGMENT, "dashboard");
        intent.putExtras(b);
        startActivity(intent);
        getActivity().finish();
    }
}
