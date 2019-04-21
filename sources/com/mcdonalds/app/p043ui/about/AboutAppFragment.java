package com.mcdonalds.app.p043ui.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.tutorial.TutorialActivity;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.web.WebHamburgerActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Calendar;

/* renamed from: com.mcdonalds.app.ui.about.AboutAppFragment */
public class AboutAppFragment extends URLNavigationFragment implements OnClickListener {
    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_about_app);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_about_app, container, false);
        ((TextView) rootView.findViewById(C2358R.C2357id.text_version)).setText("4.8.10");
        TextView copyrightText = (TextView) rootView.findViewById(C2358R.C2357id.textview_copyright);
        Calendar calendar = Calendar.getInstance();
        copyrightText.setText(String.format(getResources().getString(C2658R.string.title_copyright), new Object[]{Integer.valueOf(calendar.get(1))}));
        Button eulaButton = (Button) rootView.findViewById(C2358R.C2357id.button_eula);
        if (AppUtils.getLocalisedLegalUrl("eula") == null) {
            eulaButton.setVisibility(4);
            rootView.findViewById(C2358R.C2357id.ruler_eula).setVisibility(4);
        } else {
            eulaButton.setOnClickListener(this);
        }
        Button tutorialButton = (Button) rootView.findViewById(C2358R.C2357id.button_tutorial);
        Button termsButton = (Button) rootView.findViewById(C2358R.C2357id.button_terms);
        Button privacyButton = (Button) rootView.findViewById(C2358R.C2357id.button_privacy);
        Button faqButton = (Button) rootView.findViewById(C2358R.C2357id.button_faq);
        if (getArguments().getBoolean("hideFaqButton", false)) {
            faqButton.setVisibility(8);
        } else {
            faqButton.setOnClickListener(this);
        }
        tutorialButton.setOnClickListener(this);
        termsButton.setOnClickListener(this);
        privacyButton.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        String link = null;
        int title = 0;
        String analyticsTitle = null;
        switch (v.getId()) {
            case C2358R.C2357id.button_eula /*2131821062*/:
                link = AppUtils.getLocalisedLegalUrl("eula");
                title = C2658R.string.end_user_license_agreement;
                analyticsTitle = getString(C2658R.string.analytics_screen_about_app_eula);
                break;
            case C2358R.C2357id.button_tutorial /*2131821064*/:
                startActivity(new Intent(getActivity(), TutorialActivity.class));
                break;
            case C2358R.C2357id.button_terms /*2131821065*/:
                link = AppUtils.getLocalisedLegalUrl("registerTOC");
                title = C2658R.string.terms_and_conditions;
                analyticsTitle = getString(C2658R.string.analytics_screen_about_app_terms);
                break;
            case C2358R.C2357id.button_privacy /*2131821066*/:
                link = AppUtils.getLocalisedLegalUrl("privacy");
                title = C2658R.string.privacy;
                analyticsTitle = getString(C2658R.string.analytics_screen_about_app_privacy);
                break;
            case C2358R.C2357id.button_faq /*2131821067*/:
                link = AppUtils.getLocalisedLegalUrl("FAQ");
                if (link == null) {
                    if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                        link = (String) Configuration.getSharedInstance().getValueForKey("interface.legalCopy.FAQ.zh");
                    }
                    link = (String) Configuration.getSharedInstance().getValueForKey("interface.legalCopy.FAQ.en");
                }
                title = C2658R.string.faq;
                analyticsTitle = getString(C2658R.string.analytics_screen_about_app_faq);
                break;
        }
        if (link != null) {
            Bundle args = new Bundle();
            args.putString("link", link);
            args.putInt("view_title", title);
            args.putString("analytics_title", analyticsTitle);
            startActivity(WebHamburgerActivity.class, "web", args);
        }
    }

    public void onResume() {
        super.onResume();
        getNavigationActivity().setTitle(getString(C2658R.string.appmenu_about_the_app));
    }

    public void onStart() {
        super.onStart();
        getNavigationActivity().showNavigateUp(true);
    }

    public void onStop() {
        super.onStop();
        getNavigationActivity().showNavigateUp(false);
    }
}
