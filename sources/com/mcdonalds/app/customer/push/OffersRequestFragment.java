package com.mcdonalds.app.customer.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.facebook.internal.NativeProtocol;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.PromoFragmentStatePagerAdapter;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.app.model.PromoResponse;
import com.mcdonalds.app.net.JsonGetRequest;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.OnPageSelectListener;
import com.mcdonalds.app.widget.PagerIndicatorGroup;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.util.ArrayList;
import java.util.List;

public class OffersRequestFragment extends URLNavigationFragment {
    public static final String NAME = OffersRequestFragment.class.getSimpleName();
    private PagerIndicatorGroup indicator;
    private int mCurrentPagerIndex;
    private Handler mHandler;
    private OffersModule mOffersModule;
    private PromoFragmentStatePagerAdapter mPagerAdapter;
    private List<Promo> mPromos;
    private final AsyncListener<PromoResponse> mPromosResponseListener = new C31035();
    private ViewPager mSampleOffersPager;
    private RequestManagerServiceConnection mServiceConnection;
    private final Runnable mViewPagerVisibleScroll = new C31046();

    /* renamed from: com.mcdonalds.app.customer.push.OffersRequestFragment$1 */
    class C30991 extends OnPageSelectListener {
        C30991() {
        }

        public void onPageSelected(int position) {
            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
            if (OffersRequestFragment.this.getActivity() != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$000", new Object[]{OffersRequestFragment.this}).select(position);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.OffersRequestFragment$2 */
    class C31002 implements OnClickListener {
        C31002() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OffersRequestFragment.access$100(OffersRequestFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.OffersRequestFragment$3 */
    class C31013 implements OnClickListener {
        C31013() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OffersRequestFragment.this.getActivity().setResult(2);
            OffersRequestFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.OffersRequestFragment$4 */
    class C31024 implements AsyncListener<Boolean> {
        C31024() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OffersRequestFragment.this.getActivity().setResult(1);
            OffersRequestFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.OffersRequestFragment$5 */
    class C31035 implements AsyncListener<PromoResponse> {
        C31035() {
        }

        public void onResponse(PromoResponse response, AsyncToken token, AsyncException exception) {
            boolean success = true;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null || OffersRequestFragment.this.getActivity() == null || response.getPromos() == null) {
                success = false;
            }
            if (success) {
                OffersRequestFragment.access$202(OffersRequestFragment.this, response.getPromos());
                OffersRequestFragment.access$300(OffersRequestFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.push.OffersRequestFragment$6 */
    class C31046 implements Runnable {
        C31046() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$400", new Object[]{OffersRequestFragment.this}).getCount() > 0) {
                OffersRequestFragment.access$508(OffersRequestFragment.this);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$600", new Object[]{OffersRequestFragment.this}).setCurrentItem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$500", new Object[]{OffersRequestFragment.this}) % Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$400", new Object[]{OffersRequestFragment.this}).getCount(), true);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$800", new Object[]{OffersRequestFragment.this}).postDelayed(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$700", new Object[]{OffersRequestFragment.this}), 5000);
            }
        }
    }

    static /* synthetic */ void access$100(OffersRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$100", new Object[]{x0});
        x0.registerForNotificationsAndFinish();
    }

    static /* synthetic */ List access$202(OffersRequestFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$202", new Object[]{x0, x1});
        x0.mPromos = x1;
        return x1;
    }

    static /* synthetic */ void access$300(OffersRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$300", new Object[]{x0});
        x0.setPagerAdapter();
    }

    static /* synthetic */ int access$508(OffersRequestFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.push.OffersRequestFragment", "access$508", new Object[]{x0});
        int i = x0.mCurrentPagerIndex;
        x0.mCurrentPagerIndex = i + 1;
        return i;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_offer_opt);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getNavigationActivity(), this.mServiceConnection);
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mViewPagerVisibleScroll);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOffersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        this.mPromos = new ArrayList();
        this.mHandler = new Handler();
        Object promos = Configuration.getSharedInstance().getValueForKey("interface.signin.defaultOfferPromoRegistration");
        if (promos instanceof String) {
            loadPromosFromUrl((String) promos);
        } else if (promos instanceof List) {
            loadPromosFromConfig();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_offers_request, container, false);
        this.indicator = (PagerIndicatorGroup) view.findViewById(C2358R.C2357id.pager_indicator);
        this.mSampleOffersPager = (ViewPager) view.findViewById(C2358R.C2357id.sample_offer_pager);
        this.mSampleOffersPager.setOnPageChangeListener(new C30991());
        if (this.mPromos != null) {
            setPagerAdapter();
        }
        ((Button) view.findViewById(C2358R.C2357id.yes_button)).setOnClickListener(new C31002());
        ((Button) view.findViewById(C2358R.C2357id.no_button)).setOnClickListener(new C31013());
        return view;
    }

    private void registerForNotificationsAndFinish() {
        Ensighten.evaluateEvent(this, "registerForNotificationsAndFinish", null);
        NotificationManager.register(new C31024());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mPagerAdapter != null) {
            this.mSampleOffersPager.setAdapter(this.mPagerAdapter);
        }
    }

    private void loadPromosFromConfig() {
        Ensighten.evaluateEvent(this, "loadPromosFromConfig", null);
        for (LinkedTreeMap<String, String> item : (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.promos")) {
            this.mPromos.add(new Promo((String) item.get(NativeProtocol.IMAGE_URL_KEY), null, (String) item.get("itemLink")));
        }
        setPagerAdapter();
    }

    private void loadPromosFromUrl(String url) {
        Ensighten.evaluateEvent(this, "loadPromosFromUrl", new Object[]{url});
        this.mServiceConnection.processRequest(new JsonGetRequest(url, PromoResponse.class), this.mPromosResponseListener);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7319 && resultCode == -1) {
            LoginManager.getInstance().register(getNavigationActivity());
        }
    }
}
