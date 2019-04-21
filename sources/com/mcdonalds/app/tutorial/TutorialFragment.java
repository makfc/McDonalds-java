package com.mcdonalds.app.tutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.firstload.SelectStoreActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.widget.PagerIndicator;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import java.util.ArrayList;

public class TutorialFragment extends URLNavigationFragment {
    private boolean fromSideMenu;
    private String mAnalyticsTitle = null;
    OnPageChangeListener mPageChangeListener = new C38005();
    URLNavigationActivity mParent;
    private Button nextButton;
    private ViewPager viewPager;

    /* renamed from: com.mcdonalds.app.tutorial.TutorialFragment$1 */
    class C37961 extends BroadcastReceiver {
        C37961() {
        }

        public void onReceive(Context context, Intent intent) {
            Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
            if (TutorialFragment.this.getActivity() != null) {
                NotificationCenter.getSharedInstance().removeObserver(this);
                TutorialFragment.this.getActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.tutorial.TutorialFragment$2 */
    class C37972 implements OnClickListener {
        C37972() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            TutorialFragment.access$000(TutorialFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.tutorial.TutorialFragment$3 */
    class C37983 implements OnClickListener {
        C37983() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (TutorialFragment.this.mParent != null) {
                TutorialFragment.access$100(TutorialFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.tutorial.TutorialFragment$4 */
    class C37994 implements OnClickListener {
        C37994() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (TutorialFragment.this.mParent != null) {
                TutorialFragment.access$200(TutorialFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.tutorial.TutorialFragment$5 */
    class C38005 implements OnPageChangeListener {
        C38005() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
        }

        public void onPageSelected(int position) {
            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
            AnalyticsUtils.trackOnSlideEvent(TutorialFragment.this.getAnalyticsTitle(), "Slide");
            if (position == Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$300", new Object[]{TutorialFragment.this}).getAdapter().getCount() - 1 && Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$400", new Object[]{TutorialFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$500", new Object[]{TutorialFragment.this}).setText(C2658R.string.tutorial_done_button);
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$400", new Object[]{TutorialFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$500", new Object[]{TutorialFragment.this}).setText(C2658R.string.tutorial_next_button);
            }
        }

        public void onPageScrollStateChanged(int state) {
            Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
        }
    }

    static /* synthetic */ void access$000(TutorialFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$000", new Object[]{x0});
        x0.nextClicked();
    }

    static /* synthetic */ void access$100(TutorialFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$100", new Object[]{x0});
        x0.getStartedClicked();
    }

    static /* synthetic */ void access$200(TutorialFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "access$200", new Object[]{x0});
        x0.signInClicked();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mAnalyticsTitle;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null || args.getString("viewMode") == null || !args.getString("viewMode").equals("sideMenu")) {
            NotificationCenter.getSharedInstance().addObserver("NOTIFICATION_FINISH_TUTORIAL", new C37961());
        } else {
            this.fromSideMenu = true;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<LinkedTreeMap> screens = null;
        Bundle args = getArguments();
        if (!(args == null || args.get("tutorial") == null)) {
            String tutorial_attr = args.getString("tutorial");
            if (Configuration.getSharedInstance().getValueForKey(tutorial_attr) != null) {
                screens = (ArrayList) Configuration.getSharedInstance().getValueForKey(tutorial_attr);
            }
        }
        if (screens == null) {
            screens = (ArrayList) Configuration.getSharedInstance().getValueForKey("interface.tutorial.screens");
        }
        if (screens == null) {
            startActivity(MainActivity.class);
            return null;
        }
        this.mAnalyticsTitle = getString(C2658R.string.analytics_screen_tutorial);
        View rootView = inflater.inflate(C2658R.layout.fragment_tutorial, container, false);
        this.viewPager = (ViewPager) rootView.findViewById(C2358R.C2357id.main_view_pager);
        this.viewPager.setAdapter(new TutorialPagerAdapter(getFragmentManager(), getContext(), screens));
        ((PagerIndicator) rootView.findViewById(C2358R.C2357id.pager_indicator)).setupWithViewPager(this.viewPager);
        this.viewPager.addOnPageChangeListener(this.mPageChangeListener);
        this.mParent = getNavigationActivity();
        if (this.fromSideMenu) {
            this.nextButton = (Button) rootView.findViewById(C2358R.C2357id.button_next);
            this.nextButton.setVisibility(0);
            rootView.findViewById(C2358R.C2357id.button_container).setVisibility(8);
            this.nextButton.setOnClickListener(new C37972());
        } else {
            setupStartupButtons(rootView);
        }
        try {
            LocalDataManager.getSharedInstance().setTutorialLastShownVersionName("4.8.10");
            return rootView;
        } catch (Exception e) {
            return rootView;
        }
    }

    private void setupStartupButtons(View rootView) {
        Ensighten.evaluateEvent(this, "setupStartupButtons", new Object[]{rootView});
        Button signInButton = (Button) rootView.findViewById(C2358R.C2357id.button_sign_in);
        ((Button) rootView.findViewById(C2358R.C2357id.button_get_started)).setOnClickListener(new C37983());
        signInButton.setOnClickListener(new C37994());
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule != null && customerModule.isLoggedIn()) {
            signInButton.setVisibility(8);
        }
    }

    private void nextClicked() {
        Ensighten.evaluateEvent(this, "nextClicked", null);
        int currentPosition = this.viewPager.getCurrentItem();
        if (currentPosition < this.viewPager.getAdapter().getCount() - 1) {
            this.viewPager.setCurrentItem(currentPosition + 1, true);
        } else {
            startMainActivity();
        }
    }

    private void getStartedClicked() {
        Ensighten.evaluateEvent(this, "getStartedClicked", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Get Started");
        startMainActivity();
    }

    private void signInClicked() {
        Ensighten.evaluateEvent(this, "signInClicked", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Sign In");
        startActivity(new Intent(getActivity(), SignInActivity.class));
    }

    private void startMainActivity() {
        Ensighten.evaluateEvent(this, "startMainActivity", null);
        if (OrderManager.getInstance().getCurrentStore() == null) {
            startActivity(new Intent(getActivity(), SelectStoreActivity.class));
        } else {
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }

    public static boolean shouldShowTutorial(Configuration config, LocalDataManager localDataManager) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialFragment", "shouldShowTutorial", new Object[]{config, localDataManager});
        if (!config.getBooleanForKey("interface.tutorial.show")) {
            return false;
        }
        boolean showFirstLaunchOnly = config.getBooleanForKey("interface.tutorial.firstLaunchOnly");
        String tutorialLastViewedVersion = localDataManager.getTutorialLastShownVersionName();
        if (showFirstLaunchOnly && tutorialLastViewedVersion == null) {
            return true;
        }
        return !"4.8.10".equals(tutorialLastViewedVersion);
    }
}
