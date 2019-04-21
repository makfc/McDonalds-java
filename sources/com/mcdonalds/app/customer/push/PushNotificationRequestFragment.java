package com.mcdonalds.app.customer.push;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import com.ensighten.Ensighten;
import com.facebook.internal.NativeProtocol;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.home.PromoFragmentStatePagerAdapter;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.app.model.PromoResponse;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.OnPageSelectListener;
import com.mcdonalds.app.util.SimpleJsonRequest;
import com.mcdonalds.app.widget.PagerIndicatorGroup;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushNotificationRequestFragment extends URLNavigationFragment {
    public static final String NAME = PushNotificationRequestFragment.class.getSimpleName();
    private PagerIndicatorGroup indicator;
    private View mContentContainer;
    private int mCurrentPagerIndex;
    private CustomerProfile mCurrentProfile;
    private Handler mHandler;
    private PromoFragmentStatePagerAdapter mPagerAdapter;
    private ProgressBar mProgress;
    private List<Promo> mPromos;
    private final AsyncListener<PromoResponse> mPromosResponseListener = new C31104();
    private ViewPager mSampleOffersPager;
    private RequestManagerServiceConnection mServiceConnection;
    private final Runnable mViewPagerVisibleScroll = new C31115();

    /* renamed from: com.mcdonalds.app.customer.push.PushNotificationRequestFragment$1 */
    class C31051 extends OnPageSelectListener {
        C31051() {
        }

        public void onPageSelected(int position) {
            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
            if (PushNotificationRequestFragment.this.getActivity() != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$000", new Object[]{PushNotificationRequestFragment.this}).select(position);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.PushNotificationRequestFragment$4 */
    class C31104 implements AsyncListener<PromoResponse> {
        C31104() {
        }

        public void onResponse(PromoResponse response, AsyncToken token, AsyncException exception) {
            boolean success = true;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null || PushNotificationRequestFragment.this.getActivity() == null || response.getPromos() == null) {
                success = false;
            }
            if (success) {
                PushNotificationRequestFragment.access$502(PushNotificationRequestFragment.this, response.getPromos());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$500", new Object[]{PushNotificationRequestFragment.this}).removeAll(Collections.singleton(null));
                PushNotificationRequestFragment.access$600(PushNotificationRequestFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.PushNotificationRequestFragment$5 */
    class C31115 implements Runnable {
        C31115() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            PushNotificationRequestFragment.access$708(PushNotificationRequestFragment.this);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$900", new Object[]{PushNotificationRequestFragment.this}).setCurrentItem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$700", new Object[]{PushNotificationRequestFragment.this}) % Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$800", new Object[]{PushNotificationRequestFragment.this}).getCount(), true);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$1100", new Object[]{PushNotificationRequestFragment.this}).postDelayed(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$1000", new Object[]{PushNotificationRequestFragment.this}), 5000);
        }
    }

    static /* synthetic */ void access$100(PushNotificationRequestFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$100", new Object[]{x0, new Boolean(x1)});
        x0.trackOpt(x1);
    }

    static /* synthetic */ void access$200(PushNotificationRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$200", new Object[]{x0});
        x0.showProgress();
    }

    static /* synthetic */ CustomerProfile access$302(PushNotificationRequestFragment x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$302", new Object[]{x0, x1});
        x0.mCurrentProfile = x1;
        return x1;
    }

    static /* synthetic */ void access$400(PushNotificationRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$400", new Object[]{x0});
        x0.proceedToNextScreen();
    }

    static /* synthetic */ List access$502(PushNotificationRequestFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$502", new Object[]{x0, x1});
        x0.mPromos = x1;
        return x1;
    }

    static /* synthetic */ void access$600(PushNotificationRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$600", new Object[]{x0});
        x0.setPagerAdapter();
    }

    static /* synthetic */ int access$708(PushNotificationRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$708", new Object[]{x0});
        int i = x0.mCurrentPagerIndex;
        x0.mCurrentPagerIndex = i + 1;
        return i;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_push_notification);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mViewPagerVisibleScroll);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new Handler();
        if (this.mPromos == null) {
            Object promos = Configuration.getSharedInstance().getValueForKey("interface.signin.defaultOfferPromoPushNotification");
            if (promos instanceof String) {
                loadPromosFromUrl((String) promos);
            } else if (promos instanceof List) {
                loadPromosFromConfig();
            }
        }
        LocalDataManager mgr = LocalDataManager.getSharedInstance();
        int num = mgr.getPushNotificationOptInShownNum();
        mgr.setPushNotificationOptInShownNum(num + 1);
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_NOTIFICATION_OPTION);
        jiceMap.put(JiceArgs.LABEL_PUSH_NOTIFICATION_VIEW, Integer.valueOf(num + 1));
        Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_push_notifications_request, container, false);
        Button confirmButton = (Button) rootView.findViewById(C2358R.C2357id.yes_button);
        Button denyButton = (Button) rootView.findViewById(C2358R.C2357id.no_button);
        this.indicator = (PagerIndicatorGroup) rootView.findViewById(C2358R.C2357id.pager_indicator);
        this.mContentContainer = rootView.findViewById(C2358R.C2357id.container_content);
        this.mProgress = (ProgressBar) rootView.findViewById(C2358R.C2357id.progress);
        this.mSampleOffersPager = (ViewPager) rootView.findViewById(C2358R.C2357id.sample_offer_pager);
        this.mSampleOffersPager.setOnPageChangeListener(new C31051());
        final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        confirmButton.setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.customer.push.PushNotificationRequestFragment$2$1 */
            class C31061 implements AsyncListener<Boolean> {
                C31061() {
                }

                public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    PushNotificationRequestFragment.access$400(PushNotificationRequestFragment.this);
                }
            }

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                PushNotificationRequestFragment.access$100(PushNotificationRequestFragment.this, true);
                PushNotificationRequestFragment.access$200(PushNotificationRequestFragment.this);
                PushNotificationRequestFragment.access$302(PushNotificationRequestFragment.this, customerModule.getCurrentProfile());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$300", new Object[]{PushNotificationRequestFragment.this}).getNotificationPreferences().setAppNotificationPreferencesEnabled(Boolean.valueOf(true));
                NotificationManager.register(new C31061());
            }
        });
        denyButton.setOnClickListener(new OnClickListener() {

            /* renamed from: com.mcdonalds.app.customer.push.PushNotificationRequestFragment$3$1 */
            class C31081 implements AsyncListener<Boolean> {
                C31081() {
                }

                public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    PushNotificationRequestFragment.access$400(PushNotificationRequestFragment.this);
                }
            }

            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                PushNotificationRequestFragment.access$302(PushNotificationRequestFragment.this, customerModule.getCurrentProfile());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.PushNotificationRequestFragment", "access$300", new Object[]{PushNotificationRequestFragment.this}).getNotificationPreferences().setAppNotificationPreferencesEnabled(Boolean.valueOf(false));
                NotificationManager.register(new C31081());
                PushNotificationRequestFragment.access$100(PushNotificationRequestFragment.this, false);
            }
        });
        return rootView;
    }

    private void showProgress() {
        Ensighten.evaluateEvent(this, "showProgress", null);
        this.mContentContainer.setVisibility(4);
        this.mProgress.setVisibility(0);
    }

    private void trackOpt(boolean accepted) {
        Ensighten.evaluateEvent(this, "trackOpt", new Object[]{new Boolean(accepted)});
        Map jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_NOTIFICATION_OPTION);
        jiceMap.put(JiceArgs.LABEL_PUSH_NOTIFICATION_OPT_IN, accepted ? "yes" : "no");
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), accepted ? "Opt-In" : "Opt-Out", jiceMap);
    }

    private void proceedToNextScreen() {
        Ensighten.evaluateEvent(this, "proceedToNextScreen", null);
        this.mProgress.setVisibility(8);
        if (getNavigationActivity() != null) {
            Bundle arguments = getArguments();
            String fragmentName = "dashboard";
            Class<? extends URLNavigationActivity> clazz = MainActivity.class;
            if (this.mCurrentProfile.isMobileTakeOver()) {
                fragmentName = "mobile_take_over";
                clazz = ProfileUpdateActivity.class;
            } else if (this.mCurrentProfile.isEmailTakeOver()) {
                fragmentName = "email_take_over";
                clazz = ProfileUpdateActivity.class;
            } else if (arguments != null) {
                if (arguments.getBoolean("EXTRA_SAVING_FAVORITE", false)) {
                    boolean isDetail = arguments.getBoolean("saving_fav_detail", false);
                    fragmentName = isDetail ? StoreDetailsFragment.NAME : "store_locator";
                    clazz = isDetail ? StoreDetailsActivity.class : MainActivity.class;
                } else if (arguments.getBoolean("GO_TO_MSA", false)) {
                    fragmentName = "msa_logged_in";
                    clazz = MainActivity.class;
                } else if (getArguments().getBoolean("NEED_TO_RETURN_TO_BASKET", false)) {
                    fragmentName = "basket";
                    clazz = BasketActivity.class;
                }
            }
            startActivity(clazz, fragmentName, arguments);
            getNavigationActivity().finish();
        }
    }

    private void loadPromosFromConfig() {
        Ensighten.evaluateEvent(this, "loadPromosFromConfig", null);
        List<LinkedTreeMap<String, String>> promos = (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.promos");
        this.mPromos = new ArrayList();
        for (LinkedTreeMap<String, String> item : promos) {
            this.mPromos.add(new Promo((String) item.get(NativeProtocol.IMAGE_URL_KEY), null, (String) item.get("itemLink")));
        }
        setPagerAdapter();
    }

    private void loadPromosFromUrl(String url) {
        Ensighten.evaluateEvent(this, "loadPromosFromUrl", new Object[]{url});
        this.mServiceConnection.processRequest(new SimpleJsonRequest(url, PromoResponse.class), this.mPromosResponseListener);
    }

    private void setPagerAdapter() {
        Ensighten.evaluateEvent(this, "setPagerAdapter", null);
        this.mPagerAdapter = new PromoFragmentStatePagerAdapter(this.mPromos, getChildFragmentManager(), null);
        this.indicator.setCount(this.mPromos.size());
        this.indicator.select(0);
        if (this.mSampleOffersPager != null) {
            this.mSampleOffersPager.setAdapter(this.mPagerAdapter);
            this.mHandler.post(this.mViewPagerVisibleScroll);
        }
    }
}
