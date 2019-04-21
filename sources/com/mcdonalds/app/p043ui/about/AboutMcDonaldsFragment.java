package com.mcdonalds.app.p043ui.about;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.web.WebHamburgerActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Calendar;
import java.util.List;

/* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment */
public class AboutMcDonaldsFragment extends URLNavigationFragment implements OnClickListener {
    private final String CAREERS_KEY = "interface.aboutMcDonald.careers";
    private final String CHARITY_KEY = "interface.aboutMcDonald.charity";
    private final String CONTACT_US_KEY = "interface.aboutMcDonald.contactUs";
    private final String ORDER_HOTLINE = "+852 2338 2338";
    private final String QUESTION_KEY = "interface.aboutMcDonald.ourfoodyourquestions";
    private final String SINA_WEIBO_KEY = "interface.aboutMcDonald.sinaWeibo";
    private final String TECHNICAL_HOTLINE = "+852 3762 1620";
    private final String WE_CHAT_KEY = "interface.aboutMcDonald.weChat";
    private String mAnalyticsTitle;
    private Bundle mArgs;
    private String mLink;

    /* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment$1 */
    class C38131 implements DialogInterface.OnClickListener {
        C38131() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
        }
    }

    /* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment$2 */
    class C38142 implements DialogInterface.OnClickListener {
        C38142() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            AboutMcDonaldsFragment.access$002(AboutMcDonaldsFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.about.AboutMcDonaldsFragment", "access$100", new Object[]{AboutMcDonaldsFragment.this, "interface.aboutMcDonald.charity"}));
            AboutMcDonaldsFragment.access$200(AboutMcDonaldsFragment.this, false, C2658R.string.rmhc_button);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment$3 */
    class C38153 implements DialogInterface.OnClickListener {
        C38153() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment$4 */
    class C38164 implements DialogInterface.OnClickListener {
        C38164() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Intent dialIntent = new Intent("android.intent.action.DIAL");
            dialIntent.setData(Uri.parse("tel:+852 3762 1620"));
            AboutMcDonaldsFragment.this.startActivity(dialIntent);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment$5 */
    class C38175 implements DialogInterface.OnClickListener {
        C38175() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.about.AboutMcDonaldsFragment$6 */
    class C38186 implements DialogInterface.OnClickListener {
        C38186() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Intent dialIntent = new Intent("android.intent.action.DIAL");
            dialIntent.setData(Uri.parse("tel:+852 2338 2338"));
            AboutMcDonaldsFragment.this.startActivity(dialIntent);
        }
    }

    static /* synthetic */ String access$002(AboutMcDonaldsFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.about.AboutMcDonaldsFragment", "access$002", new Object[]{x0, x1});
        x0.mLink = x1;
        return x1;
    }

    static /* synthetic */ void access$200(AboutMcDonaldsFragment x0, boolean x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.about.AboutMcDonaldsFragment", "access$200", new Object[]{x0, new Boolean(x1), new Integer(x2)});
        x0.openLink(x1, x2);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_about_mcd, container, false);
        ((TextView) rootView.findViewById(C2358R.C2357id.text_version)).setText("4.8.10");
        TextView copyrightText = (TextView) rootView.findViewById(C2358R.C2357id.textview_copyright);
        Calendar calendar = Calendar.getInstance();
        copyrightText.setText(String.format(getResources().getString(C2658R.string.title_copyright), new Object[]{Integer.valueOf(calendar.get(1))}));
        Button termsButton = (Button) rootView.findViewById(C2358R.C2357id.button_terms);
        Button privacyButton = (Button) rootView.findViewById(C2358R.C2357id.button_privacy);
        Button faqButton = (Button) rootView.findViewById(C2358R.C2357id.button_faq);
        if (getArguments().getBoolean("hideFaqButton", false)) {
            faqButton.setVisibility(8);
        } else {
            faqButton.setOnClickListener(this);
        }
        faqButton.setVisibility(8);
        termsButton.setOnClickListener(this);
        privacyButton.setOnClickListener(this);
        TextView orderHotline = (TextView) rootView.findViewById(C2358R.C2357id.txt_order_holine);
        ((TextView) rootView.findViewById(C2358R.C2357id.txt_technical_holine)).setText(getString(C2658R.string.technical_hotline_service_hours, "+852 3762 1620"));
        orderHotline.setText(getString(C2658R.string.hotline, "+852 2338 2338"));
        Button technicalSupportutton = (Button) rootView.findViewById(C2358R.C2357id.button_technical_support);
        Button mcdeliveryOrderButton = (Button) rootView.findViewById(C2358R.C2357id.button_track_my_mcdelivery_order);
        Button generalEnquiresButton = (Button) rootView.findViewById(C2358R.C2357id.button_general_enquires);
        Button specificRestaurantButton = (Button) rootView.findViewById(C2358R.C2357id.button_comment_to_specific_restaurant);
        Button careersButton = (Button) rootView.findViewById(C2358R.C2357id.button_careers);
        Button rmhcButton = (Button) rootView.findViewById(C2358R.C2357id.button_rmhc);
        Button ofyqButton = (Button) rootView.findViewById(C2358R.C2357id.button_ofyq);
        Button contactUsButton = (Button) rootView.findViewById(C2358R.C2357id.button_contactus);
        Button feedbackButton = (Button) rootView.findViewById(C2358R.C2357id.button_feedback);
        Button sinaWeiboButton = (Button) rootView.findViewById(C2358R.C2357id.button_sina_weibo);
        Button weChatButton = (Button) rootView.findViewById(C2358R.C2357id.button_we_chat);
        Button instagramButton = (Button) rootView.findViewById(C2358R.C2357id.button_instagram);
        ((Button) rootView.findViewById(C2358R.C2357id.button_official_website)).setVisibility(8);
        instagramButton.setVisibility(8);
        contactUsButton.setVisibility(8);
        feedbackButton.setVisibility(8);
        technicalSupportutton.setOnClickListener(this);
        mcdeliveryOrderButton.setOnClickListener(this);
        generalEnquiresButton.setOnClickListener(this);
        specificRestaurantButton.setOnClickListener(this);
        if (Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.careers") != null) {
            careersButton.setVisibility(0);
            careersButton.setOnClickListener(this);
        } else {
            careersButton.setVisibility(8);
        }
        if (Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.charity") != null) {
            rmhcButton.setVisibility(0);
            rmhcButton.setOnClickListener(this);
        } else {
            rmhcButton.setVisibility(8);
        }
        if (Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.ourfoodyourquestions") != null) {
            ofyqButton.setVisibility(0);
            ofyqButton.setOnClickListener(this);
        } else {
            ofyqButton.setVisibility(8);
        }
        if (Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.sinaWeibo") != null) {
            sinaWeiboButton.setVisibility(0);
            sinaWeiboButton.setOnClickListener(this);
        } else {
            sinaWeiboButton.setVisibility(8);
        }
        if (Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.weChat") != null) {
            weChatButton.setVisibility(0);
            weChatButton.setOnClickListener(this);
        } else {
            weChatButton.setVisibility(8);
        }
        return rootView;
    }

    public void onResume() {
        super.onResume();
        getNavigationActivity().setTitle(getString(C2658R.string.appmenu_enquiries));
        openDeepLink();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_about_mcd);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        this.mLink = null;
        boolean requiresUser = false;
        this.mAnalyticsTitle = null;
        this.mArgs = new Bundle();
        int titleResource = 0;
        String link = null;
        int title = 0;
        String analyticsTitle = null;
        switch (v.getId()) {
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
                    } else {
                        link = (String) Configuration.getSharedInstance().getValueForKey("interface.legalCopy.FAQ.en");
                    }
                }
                title = C2658R.string.faq;
                analyticsTitle = getString(C2658R.string.analytics_screen_about_app_faq);
                break;
            case C2358R.C2357id.button_technical_support /*2131821069*/:
                MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getString(C2658R.string.call) + "+852 3762 1620").setPositiveButton((int) C2658R.string.f6083ok, new C38164()).setNegativeButton((int) C2658R.string.cancel, new C38153()).create().show();
                break;
            case C2358R.C2357id.button_track_my_mcdelivery_order /*2131821071*/:
                MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getString(C2658R.string.call) + "+852 2338 2338").setPositiveButton((int) C2658R.string.f6083ok, new C38186()).setNegativeButton((int) C2658R.string.cancel, new C38175()).create().show();
                break;
            case C2358R.C2357id.button_general_enquires /*2131821072*/:
                if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                    link = "http://www.mcdonalds.com.hk/ch/about-us/contact-us.html";
                } else {
                    link = "http://www.mcdonalds.com.hk/en/about-us/contact-us.html";
                }
                title = C2658R.string.appmenu_general_enquires;
                break;
            case C2358R.C2357id.button_comment_to_specific_restaurant /*2131821073*/:
                Intent intentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage("kodo.app.mcdhk");
                if (intentForPackage == null) {
                    Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=kodo.app.mcdhk"));
                    if (viewIntent == null) {
                        Toast.makeText(getActivity(), C2658R.string.no_application, 0).show();
                        break;
                    } else {
                        startActivity(viewIntent);
                        break;
                    }
                }
                startActivity(intentForPackage);
                break;
            case C2358R.C2357id.button_careers /*2131821074*/:
                this.mLink = getLocalizedPropertyFromConfiguration("interface.aboutMcDonald.careers");
                this.mAnalyticsTitle = getString(C2658R.string.analytics_screen_about_mcd_careers);
                titleResource = C2658R.string.careers_btn;
                break;
            case C2358R.C2357id.button_official_website /*2131821075*/:
                link = "http://www.mcdonalds.com.hk";
                title = C2658R.string.official_website_btn;
                break;
            case C2358R.C2357id.button_feedback /*2131821076*/:
                link = "https://www.facebook.com/mcdonaldshongkong";
                title = C2658R.string.feedback_btn;
                break;
            case C2358R.C2357id.button_instagram /*2131821077*/:
                link = "https://www.instagram.com/mccafe.hk";
                title = C2658R.string.mccafe_instagram_btn;
                break;
            case C2358R.C2357id.button_rmhc /*2131821078*/:
                requiresUser = true;
                TextView messageView = new TextView(getActivity());
                int padding = UIUtils.dpAsPixels(getActivity(), 12);
                messageView.setPadding(padding, padding, padding, padding);
                messageView.setTextColor(ContextCompat.getColor(getContext(), C2658R.color.dark_gray_1));
                messageView.setText(getResources().getString(C2658R.string.rmhc_disclaimer));
                new Builder(getActivity()).setView(messageView).setPositiveButton(C2658R.string.f6083ok, new C38142()).setNegativeButton(C2658R.string.decline, new C38131()).create().show();
                break;
            case C2358R.C2357id.button_ofyq /*2131821079*/:
                this.mLink = getLocalizedPropertyFromConfiguration("interface.aboutMcDonald.ourfoodyourquestions");
                this.mAnalyticsTitle = getString(C2658R.string.analytics_screen_about_mcd_we_chat);
                titleResource = C2658R.string.ofyq_button;
                break;
            case C2358R.C2357id.button_sina_weibo /*2131821080*/:
                Configuration.getSharedInstance().getCurrentLanguage();
                this.mLink = getLocalizedPropertyFromConfiguration("interface.aboutMcDonald.sinaWeibo");
                this.mAnalyticsTitle = getString(C2658R.string.analytics_screen_about_mcd_sina_weibo);
                titleResource = C2658R.string.sina_weibo_btn;
                break;
            case C2358R.C2357id.button_we_chat /*2131821081*/:
                this.mLink = getLocalizedPropertyFromConfiguration("interface.aboutMcDonald.weChat");
                this.mAnalyticsTitle = getString(C2658R.string.analytics_screen_about_mcd_we_chat);
                titleResource = C2658R.string.we_chat_btn;
                break;
            case C2358R.C2357id.button_contactus /*2131821082*/:
                if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                    this.mLink = (String) Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.contactUs.zh");
                } else {
                    this.mLink = (String) Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.contactUs.en");
                }
                this.mAnalyticsTitle = getString(C2658R.string.analytics_screen_about_mcd_contact_us);
                titleResource = C2658R.string.contact_us_btn;
                break;
        }
        if (!(requiresUser || this.mLink == null)) {
            openLink(true, titleResource);
        }
        if (link != null) {
            Bundle args = new Bundle();
            args.putString("link", link);
            args.putInt("view_title", title);
            args.putString("analytics_title", analyticsTitle);
            startActivity(WebHamburgerActivity.class, "web", args);
        }
    }

    private String getLocalizedPropertyFromConfiguration(String key) {
        Ensighten.evaluateEvent(this, "getLocalizedPropertyFromConfiguration", new Object[]{key});
        String currentLanguage = Configuration.getSharedInstance().getCurrentLanguage();
        return (String) Configuration.getSharedInstance().getValueForKey(String.format("%s.%s", new Object[]{key, currentLanguage}));
    }

    private void openLink(boolean useTagForAnalytics, int viewTitle) {
        Ensighten.evaluateEvent(this, "openLink", new Object[]{new Boolean(useTagForAnalytics), new Integer(viewTitle)});
        if (useTagForAnalytics) {
            this.mArgs.putString("analytics_title", this.mAnalyticsTitle);
        }
        if (viewTitle != 0) {
            this.mArgs.putInt("view_title", viewTitle);
        }
        this.mArgs.putString("link", this.mLink);
        startActivity(WebHamburgerActivity.class, "web", this.mArgs);
    }

    private void openDeepLink() {
        Ensighten.evaluateEvent(this, "openDeepLink", null);
        Uri uri = (Uri) getArguments().getParcelable("Uri");
        if (uri != null) {
            List<String> pathSegments = uri.getPathSegments();
            if (!pathSegments.isEmpty() && ((String) pathSegments.get(0)).equals("ofyq") && Configuration.getSharedInstance().getValueForKey("interface.aboutMcDonald.ourfoodyourquestions") != null) {
                this.mLink = getLocalizedPropertyFromConfiguration("interface.aboutMcDonald.ourfoodyourquestions");
                openLink(true, C2658R.string.ofyq_button);
            }
        }
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
