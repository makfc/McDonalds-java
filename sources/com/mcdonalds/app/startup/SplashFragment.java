package com.mcdonalds.app.startup;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.social.SocialLoginFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class SplashFragment extends SocialLoginFragment {
    public static final String NAME = SplashFragment.class.getSimpleName();
    private boolean mSocialLoginInProgress;
    private int mSocialServiceID;

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        int startColor = getResources().getColor(17170443);
        int endColor = getResources().getColor(17170443);
        ObjectAnimator colorFade = ObjectAnimator.ofObject(rootView, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(startColor), Integer.valueOf(endColor)});
        int speed = Configuration.getSharedInstance().getIntForKey("interface.splashscreen.speed");
        if (speed == 0) {
            speed = 1;
        }
        colorFade.setDuration((long) (3000 / speed));
        colorFade.start();
        return rootView;
    }

    public void onResume() {
        super.onResume();
        SocialNetwork socialNetwork = ((SplashActivity) getActivity()).getSocialNetwork();
        if (socialNetwork != null && socialNetwork.getType() != 3) {
            onSocialNetworkSelected(socialNetwork);
        }
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        Ensighten.evaluateEvent(this, "getLayoutResourceId", null);
        return C2658R.layout.fragment_splash;
    }

    public void onSocialNetworkAvailable() {
        Ensighten.evaluateEvent(this, "onSocialNetworkAvailable", null);
    }

    public void onSocialNetworkSelected(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkSelected", new Object[]{socialNetwork});
        if (!this.mSocialLoginInProgress) {
            super.onSocialNetworkSelected(socialNetwork);
            this.mSocialServiceID = socialNetwork.getSocialNetworkID();
            this.mSocialLoginInProgress = true;
        }
    }

    public void onSocialNetworkAuthenticationComplete(SocialLoginAuthenticationResults results) {
        Ensighten.evaluateEvent(this, "onSocialNetworkAuthenticationComplete", new Object[]{results});
        AuthenticationParameters parameters = new AuthenticationParameters();
        parameters.setUserName(results.getEmailAddress());
        parameters.setUsingSocialLogin(true);
        parameters.setSocialServiceID(this.mSocialServiceID);
        parameters.setSocialAuthenticationToken(results.getAccessToken());
        parameters.setSocialUserID(results.getUserId());
        ((SplashActivity) getActivity()).performLogin(parameters);
        this.mSocialLoginInProgress = false;
    }
}
