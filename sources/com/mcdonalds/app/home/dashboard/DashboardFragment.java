package com.mcdonalds.app.home.dashboard;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.p001v7.app.ActionBar;
import android.support.p001v7.widget.DefaultItemAnimator;
import android.support.p001v7.widget.GridLayoutManager;
import android.support.p001v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.admaster.square.api.CustomEvent;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ChangeEmailAddressFragment;
import com.mcdonalds.app.account.ChangeMobileFragment;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.TermsOfServiceActivity;
import com.mcdonalds.app.firstload.SelectStoreActivity;
import com.mcdonalds.app.gmalite.customer.LiteSignInActivity;
import com.mcdonalds.app.gmalite.customer.LiteSignUpActivity;
import com.mcdonalds.app.home.HomeListItem;
import com.mcdonalds.app.home.PromoFragmentStatePagerAdapter;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.offers.push.PushNotificationOfferAlertActivity;
import com.mcdonalds.app.offers.push.PushNotificationOfferAlertFragment;
import com.mcdonalds.app.ordering.LatestOrderActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.menu.LastOrderActivity;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.ordering.summary.TrackOrderActivity;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController.OfferSelection;
import com.mcdonalds.app.storelocator.StoresManager;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ImageViewFragment;
import com.mcdonalds.app.util.ImageViewFragment.OnClickListener;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.HeaderViewPager;
import com.mcdonalds.app.widget.PagerIndicatorGroup;
import com.mcdonalds.app.widget.offers.MCDListSectionHeaderModel;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.CustomerEventAction;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.provider.Contract;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DashboardFragment extends URLNavigationFragment implements DashboardListener {
    public static final String LOG_TAG = DashboardFragment.class.getSimpleName();
    public final int REFRESH_ANIMATION_DURATION = 150;
    private Bundle extras;
    private boolean isRefreshing;
    private DashboardAdapter mAdapter;
    private AsyncCounter<Object> mAsyncCounter;
    ContentObserver mCatalogObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            UIUtils.stopActivityIndicator();
            boolean goToProduct = DashboardFragment.this.getArguments().getBoolean("GO_TO_PRODUCT");
            String productId = DashboardFragment.this.getArguments().getString("PRODUCT_RECIPE_ID");
            if (goToProduct && productId != null) {
                DashboardFragment.access$000(DashboardFragment.this, productId);
            } else if (DashboardFragment.this.getArguments().getString("GO_TO_MENU_LINK") != null) {
                DashboardFragment.this.startActivity(MenuActivity.class, DashboardFragment.this.getArguments().getString("GO_TO_MENU_LINK"));
            }
        }
    };
    private boolean mCurrentLoginState;
    private boolean mDeliveryOffers;
    private String mFirstNameGreeting;
    private View mHeaderView;
    private boolean mIsMySurprises;
    private boolean mNearbyOffers;
    private boolean mNeedsRefresh = false;
    private int mOfferCount;
    private MCDListSectionHeaderModel mOfferSectionHeaderModel;
    private MCDListSectionHeaderModel mOffersSectionFavoriteHeaderModel;
    private MCDListSectionHeaderModel mOffersSectionFurtherAwayHeaderModel;
    private final OnClickListener mOnClickPromo = new C322511();
    private OrderingModule mOrderingModule;
    private boolean mPickUpOffers;
    private PromoFragmentStatePagerAdapter mPromoFragmentStatePagerAdapter;
    private HeaderViewPager mPromoPager;
    private final AsyncListener<List<Promo>> mPromosListener = new C322110();
    private Offer mPushOffer;
    private String mQueuedRecipeId;
    private AsyncListener<Product> mRecipeLoadedListener = new C322713();
    private Resources mResources;
    private RequestManagerServiceConnection mServiceConnection;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DashboardViewModel mViewModel;

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$10 */
    class C322110 implements AsyncListener<List<Promo>> {
        C322110() {
        }

        public void onResponse(List<Promo> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$400", new Object[]{DashboardFragment.this}).success(null);
            if (exception != null) {
                AsyncException.report(exception);
            } else if (DashboardFragment.this.getActivity() != null) {
                DashboardFragment.access$502(DashboardFragment.this, new PromoFragmentStatePagerAdapter(response, DashboardFragment.this.getActivity().getSupportFragmentManager(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$600", new Object[]{DashboardFragment.this})));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).setAdapter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$500", new Object[]{DashboardFragment.this}));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).setAutoScroll(true, true);
                DashboardFragment.access$700(DashboardFragment.this);
            }
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$800", new Object[]{DashboardFragment.this}).isLoggedIn() || !Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$800", new Object[]{DashboardFragment.this}).isSubscribedToOffers().booleanValue()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$900", new Object[]{DashboardFragment.this}).setRefreshing(false);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$11 */
    class C322511 implements OnClickListener {
        C322511() {
        }

        public void onImageViewFragmentClick(Promo promo) {
            Ensighten.evaluateEvent(this, "onImageViewFragmentClick", new Object[]{promo});
            String url = promo.getUrl();
            if (!TextUtils.isEmpty(url)) {
                String imageName = url.substring(url.lastIndexOf(47) + 1);
                SparseArray custom = new SparseArray();
                custom.put(50, imageName);
                AnalyticsUtils.trackOnClickEvent(DashboardFragment.this.getAnalyticsTitle(), "Promotional Carousel", custom);
            }
            final String link = promo.getItemLink();
            if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.newPromoWorkflow")) {
                Bundle b;
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$800", new Object[]{DashboardFragment.this}).isLoggedIn()) {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1000", new Object[]{DashboardFragment.this, link})) {
                        if (!Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
                            DashboardFragment.this.showFragment("msa_logged_in");
                            return;
                        } else if (OrderManager.getInstance().getCurrentStore() == null) {
                            DashboardFragment.this.startActivityForResult(OrderMethodSelectionActivity.class, 1691);
                            return;
                        } else {
                            DashboardFragment.this.showFragment("msa_logged_in");
                            return;
                        }
                    } else if (promo.getOfferId() != null) {
                        DashboardFragment.access$1100(DashboardFragment.this, promo.getOfferId());
                        return;
                    }
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1000", new Object[]{DashboardFragment.this, link})) {
                    b = new Bundle();
                    b.putBoolean("GO_TO_MSA", true);
                    DashboardFragment.this.showFragment("msa_not_logged_in", b);
                    return;
                } else if (promo.getOfferId() != null) {
                    b = new Bundle();
                    if (promo.getOfferId() != null) {
                        b.putBoolean("GO_TO_OFFER", true);
                        b.putString("extra_offer", promo.getOfferId());
                    }
                    DashboardFragment.this.startActivity(SignInActivity.class, b);
                    return;
                }
            }
            if (TextUtils.isEmpty(link)) {
                if (ListUtils.isNotEmpty(promo.getAssociatedRecipes())) {
                    final String recipeId = (String) promo.getAssociatedRecipes().get(0);
                    if (!TextUtils.isEmpty(recipeId)) {
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$900", new Object[]{DashboardFragment.this}).isRefreshing()) {
                            DashboardFragment.this.stopRefresh();
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    Ensighten.evaluateEvent(this, "run", null);
                                    DashboardFragment.this.onClickPromoProduct(recipeId);
                                }
                            }, 150);
                        } else {
                            DashboardFragment.this.onClickPromoProduct(recipeId);
                        }
                    }
                } else if (promo.getCategoryId() != null) {
                    final String categoryId = String.valueOf(promo.getCategoryId());
                    if (!TextUtils.isEmpty(categoryId)) {
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$900", new Object[]{DashboardFragment.this}).isRefreshing()) {
                            DashboardFragment.this.stopRefresh();
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    Ensighten.evaluateEvent(this, "run", null);
                                    DashboardFragment.access$1200(DashboardFragment.this, categoryId);
                                }
                            }, 150);
                        } else {
                            DashboardFragment.access$1200(DashboardFragment.this, categoryId);
                        }
                    }
                }
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$900", new Object[]{DashboardFragment.this}).isRefreshing()) {
                DashboardFragment.this.stopRefresh();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Ensighten.evaluateEvent(this, "run", null);
                        DashboardFragment.this.onClickPromoLink(link);
                    }
                }, 150);
            } else {
                DashboardFragment.this.onClickPromoLink(link);
            }
            HashMap<String, Object> jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_PROMO);
            jiceMap.put(JiceArgs.LABEL_PROMO_NUM, String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).getCurrentItem()));
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$13 */
    class C322713 implements AsyncListener<Product> {
        C322713() {
        }

        public void onResponse(Product product, AsyncToken token, AsyncException exception) {
            boolean z = false;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{product, token, exception});
            UIUtils.stopActivityIndicator();
            String str = DashboardFragment.LOG_TAG;
            String str2 = "recipe: %s exception: %s";
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(product != null);
            if (exception != null) {
                z = true;
            }
            objArr[1] = Boolean.valueOf(z);
            Log.d(str, String.format(str2, objArr));
            if (exception != null) {
                Log.e(DashboardFragment.LOG_TAG, exception.getMessage(), exception);
            }
            if (exception == null && product != null) {
                Bundle extras = new Bundle();
                extras.putInt("ARG_RECIPE_ID", product.getExternalId().intValue());
                DashboardFragment.this.startActivity(ProductDetailsActivity.class, extras);
            } else if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.promotionCarrouselAlert")) {
                UIUtils.showGlobalAlertDialog(DashboardFragment.this.getActivity(), DashboardFragment.this.getString(C2658R.string.label_not_available), DashboardFragment.this.getString(C2658R.string.text_not_available_at_your_restaurant), UIUtils.DEFAULT_DIALOG_CLICK_LISTENER);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$14 */
    class C322814 implements AsyncListener<List<Object>> {
        C322814() {
        }

        public void onResponse(List<Object> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            DashboardFragment.access$1302(DashboardFragment.this, false);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$900", new Object[]{DashboardFragment.this}).isRefreshing()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$900", new Object[]{DashboardFragment.this}).setRefreshing(false);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$15 */
    class C322915 implements AsyncListener<CustomerOrder> {
        C322915() {
        }

        public void onResponse(CustomerOrder response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$400", new Object[]{DashboardFragment.this}).success(null);
            if (exception == null && response != null) {
                HashMap<String, Serializable> attributes = new HashMap();
                attributes.put(JiceArgs.EVENT_SUBMIT_ORDER, response);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$300", new Object[]{DashboardFragment.this}).addHomeListItem(new HomeListItem(C2358R.C2359drawable.ic_mcd_order_icon_red, Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1400", new Object[]{DashboardFragment.this}).getString(C2658R.string.latest_order), response.getName(), "order/last/", attributes));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$16 */
    class C323016 implements AsyncListener<List<HomeListItem>> {
        C323016() {
        }

        public void onResponse(List<HomeListItem> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$400", new Object[]{DashboardFragment.this}).success(null);
            for (HomeListItem item : response) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$300", new Object[]{DashboardFragment.this}).addHomeListItem(item);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$17 */
    class C323117 implements AsyncListener<List<Store>> {
        C323117() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$400", new Object[]{DashboardFragment.this}).success(null);
            DashboardFragment.access$1500(DashboardFragment.this);
            if (exception != null) {
                AsyncException.report(exception);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$18 */
    class C323218 implements AsyncListener<List<Offer>> {
        C323218() {
        }

        public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$400", new Object[]{DashboardFragment.this}).success(null);
            if (exception != null) {
                AsyncException.report(exception);
            }
            DashboardFragment.access$1600(DashboardFragment.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$21 */
    class C323621 implements DialogInterface.OnClickListener {
        C323621() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            DashboardFragment.access$2000(DashboardFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1900", new Object[]{DashboardFragment.this}));
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$23 */
    class C323923 implements DialogInterface.OnClickListener {
        C323923() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            DashboardFragment.this.startActivity(MenuActivity.class, "start_order_delivery");
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$25 */
    class C324225 implements DialogInterface.OnClickListener {
        C324225() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            DashboardFragment.this.startActivity(MenuActivity.class, "start_order");
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$28 */
    class C324528 implements Runnable {
        C324528() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (ConfigurationUtils.isGmaLiteFlow()) {
                DashboardFragment.this.startActivity(LiteSignUpActivity.class);
            } else {
                DashboardFragment.this.startActivity(TermsOfServiceActivity.class);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$29 */
    class C324629 implements Runnable {
        C324629() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (ConfigurationUtils.isGmaLiteFlow()) {
                DashboardFragment.this.startActivity(LiteSignInActivity.class);
            } else {
                DashboardFragment.this.startActivity(SignInActivity.class);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$31 */
    class C324931 implements Runnable {
        C324931() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (DashboardFragment.this.getNavigationActivity() != null) {
                CustomerProfile mProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
                if (mProfile == null) {
                    return;
                }
                if (mProfile.isUsingSocialLoginWithoutEmail() || mProfile.getLoginPreference() == 2) {
                    DashboardFragment.this.startActivity(ProfileUpdateActivity.class, ChangeMobileFragment.NAME);
                } else {
                    DashboardFragment.this.startActivity(ProfileUpdateActivity.class, ChangeEmailAddressFragment.NAME);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$3 */
    class C32503 implements DialogInterface.OnClickListener {
        C32503() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            dialogInterface.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$4 */
    class C32514 implements OnRefreshListener {
        C32514() {
        }

        public void onRefresh() {
            Ensighten.evaluateEvent(this, "onRefresh", null);
            ServiceUtils.getSharedInstance().removeOffersCache();
            ServiceUtils.getSharedInstance().removeAdvertisableCache();
            DashboardFragment.access$100(DashboardFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$5 */
    class C32525 extends OnScrollListener {
        C32525() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Ensighten.evaluateEvent(this, "onScrollStateChanged", new Object[]{recyclerView, new Integer(newState)});
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).onResume();
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).onPause();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$6 */
    class C32536 implements DialogInterface.OnClickListener {
        C32536() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            DashboardFragment.this.startActivity(SelectStoreActivity.class);
            DashboardFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$8 */
    class C32558 extends SpanSizeLookup {
        C32558() {
        }

        public int getSpanSize(int position) {
            boolean isGrid = false;
            Ensighten.evaluateEvent(this, "getSpanSize", new Object[]{new Integer(position)});
            int type = Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$300", new Object[]{DashboardFragment.this}).getItemViewType(position);
            if (type == 13 || type == 4) {
                isGrid = true;
            }
            if (isGrid) {
                return 1;
            }
            return 2;
        }
    }

    static /* synthetic */ void access$000(DashboardFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$000", new Object[]{x0, x1});
        x0.newPromoWorkflowForProduct(x1);
    }

    static /* synthetic */ void access$100(DashboardFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$100", new Object[]{x0});
        x0.refresh();
    }

    static /* synthetic */ void access$1100(DashboardFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1100", new Object[]{x0, x1});
        x0.newPromoWorkflowForOffer(x1);
    }

    static /* synthetic */ void access$1200(DashboardFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1200", new Object[]{x0, x1});
        x0.onClickPromoCategory(x1);
    }

    static /* synthetic */ boolean access$1302(DashboardFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1302", new Object[]{x0, new Boolean(x1)});
        x0.isRefreshing = x1;
        return x1;
    }

    static /* synthetic */ void access$1500(DashboardFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1500", new Object[]{x0});
        x0.populateCurrentLocationSubtitle();
    }

    static /* synthetic */ void access$1600(DashboardFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1600", new Object[]{x0, new Boolean(x1)});
        x0.checkAndDisplayOffers(x1);
    }

    static /* synthetic */ void access$1700(DashboardFragment x0, HomeListItem x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1700", new Object[]{x0, x1});
        x0.checkIfDuplicateOrderAlertEnabled(x1);
    }

    static /* synthetic */ void access$1800(DashboardFragment x0, HomeListItem x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$1800", new Object[]{x0, x1});
        x0.homeListItemClicked(x1);
    }

    static /* synthetic */ void access$2000(DashboardFragment x0, Bundle x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$2000", new Object[]{x0, x1});
        x0.StartOrder(x1);
    }

    static /* synthetic */ void access$2200(DashboardFragment x0, Offer x1, OfferSelection x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$2200", new Object[]{x0, x1, x2});
        x0.commonOnOfferClick(x1, x2);
    }

    static /* synthetic */ PromoFragmentStatePagerAdapter access$502(DashboardFragment x0, PromoFragmentStatePagerAdapter x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$502", new Object[]{x0, x1});
        x0.mPromoFragmentStatePagerAdapter = x1;
        return x1;
    }

    static /* synthetic */ void access$700(DashboardFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$700", new Object[]{x0});
        x0.populatePromos();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 1389 || requestCode == 326) {
            ServiceUtils.getSharedInstance().removeOffersCache();
            this.mNeedsRefresh = true;
        } else if (requestCode == 1223) {
            if (CatalogManager.hasCatalogDownloaded(getActivity())) {
                boolean goToProduct = getArguments().getBoolean("GO_TO_PRODUCT");
                String productId = getArguments().getString("PRODUCT_RECIPE_ID");
                if (goToProduct && productId != null) {
                    newPromoWorkflowForProduct(productId);
                    return;
                } else if (getArguments().getString("GO_TO_MENU_LINK") != null) {
                    startActivity(MenuActivity.class, getArguments().getString("GO_TO_MENU_LINK"));
                    return;
                } else {
                    return;
                }
            }
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.progress_update_products_msg);
            getActivity().getContentResolver().registerContentObserver(Contract.CONTENT_URI, false, this.mCatalogObserver);
        } else if (requestCode == 1690) {
            Bundle b = data.getExtras().getBundle("PASS_THROUGH_ARGS");
            startActivity(LastOrderActivity.class, data.getExtras().getString("LAST_ORDER_FRAGMENT_NAME"), b);
        } else if (requestCode == 1691) {
            showFragment("msa_logged_in");
        }
    }

    private void loadNutritionModule() {
        Ensighten.evaluateEvent(this, "loadNutritionModule", null);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        checkForQueuedRecipeLoading();
    }

    public void checkForQueuedRecipeLoading() {
        Ensighten.evaluateEvent(this, "checkForQueuedRecipeLoading", null);
        if (this.mQueuedRecipeId != null) {
            this.mOrderingModule.getProductForId(this.mQueuedRecipeId, this.mRecipeLoadedListener);
            this.mQueuedRecipeId = null;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onStart() {
        super.onStart();
        this.isRefreshing = false;
        if (this.mViewModel.isLoggedIn()) {
            this.mFirstNameGreeting = this.mViewModel.getCustomerFirstName();
        } else {
            this.mFirstNameGreeting = null;
        }
        populateCurrentLocationSubtitle();
        if (ConfigurationUtils.isGmaLiteFlow() || this.mViewModel.getCurrentStoreId() != null || Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
            if (getActivity().getIntent().getBooleanExtra("REFRESH_LAST_ORDER", false)) {
                this.mNeedsRefresh = true;
            } else {
                Order order = OrderManager.getInstance().getCurrentOrder();
                if (order == null || ListUtils.isEmpty(order.getOffers())) {
                    this.mNeedsRefresh = true;
                }
            }
            updateOffersTabSelectionState();
            if (!this.mNeedsRefresh && !this.mNearbyOffers) {
                this.mAdapter.setOfferTabSelection(this.mDeliveryOffers);
                return;
            }
            return;
        }
        startActivity(SelectStoreActivity.class);
        getNavigationActivity().finish();
    }

    private void checkAutoLoginFailed() {
        boolean autoLogin;
        Ensighten.evaluateEvent(this, "checkAutoLoginFailed", null);
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        final String prefSavedLogin = localDataManager.getPrefSavedLogin();
        final String prefSavedLoginPass = localDataManager.getPrefSavedLoginPass();
        final int prefSavedSocialID = localDataManager.getPrefSavedSocialNetworkId();
        if (TextUtils.isEmpty(prefSavedLogin) || (TextUtils.isEmpty(prefSavedLoginPass) && prefSavedSocialID == -1)) {
            autoLogin = false;
        } else {
            autoLogin = true;
        }
        boolean rememberLogin = LanguageUtil.getPrefRememberLogin();
        if (autoLogin && !rememberLogin) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.auto_login_fail_title)).setMessage(getString(C2658R.string.auto_login_fail_message)).setNeutralButton(getString(C2658R.string.f6083ok), new C32503()).setPositiveButton(getString(C2658R.string.button_title_sign_in), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                    if (ConfigurationUtils.isGmaLiteFlow()) {
                        DashboardFragment.this.startActivity(LiteSignInActivity.class);
                        return;
                    }
                    Bundle extras = new Bundle();
                    if (prefSavedSocialID != 3) {
                        extras.putString("EXTRA_AUTO_LOGIN_USERNAME", prefSavedLogin);
                        extras.putString("EXTRA_AUTO_LOGIN_PASSWORD", prefSavedLoginPass);
                    } else {
                        extras.putString("EXTRA_AUTO_LOGIN_USERNAME", "");
                        extras.putString("EXTRA_AUTO_LOGIN_PASSWORD", "");
                    }
                    DashboardFragment.this.startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, extras);
                }
            }).setCancelable(false).create().show();
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoresManager.getInstance().refreshFavorites();
        this.mViewModel = DashboardViewModel.getInstance(getActivity());
        this.mIsMySurprises = Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.useMySurprises");
        loadNutritionModule();
        if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.newPromoWorkflow") && getArguments() != null) {
            boolean goToOffer = getArguments().getBoolean("GO_TO_OFFER");
            String offerId = getArguments().getString("extra_offer");
            boolean goToProduct = getArguments().getBoolean("GO_TO_PRODUCT");
            String productId = getArguments().getString("PRODUCT_RECIPE_ID");
            if (goToOffer && offerId != null) {
                newPromoWorkflowForOffer(offerId);
            } else if (goToProduct && productId != null) {
                newPromoWorkflowForProduct(productId);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String string;
        View rootView = inflater.inflate(C2658R.layout.fragment_dashboard, container, false);
        this.mNearbyOffers = Configuration.getSharedInstance().getBooleanForKey("interface.offers.showCurrentRestaurantOffersByDefault");
        updateOffersTabSelectionState();
        this.mOfferCount = this.mViewModel.getOfferCount();
        this.mResources = getResources();
        this.mOffersSectionFavoriteHeaderModel = new MCDListSectionHeaderModel(this.mResources.getString(C2658R.string.offers_subsection_at_favorite), 0, false);
        this.mOffersSectionFurtherAwayHeaderModel = new MCDListSectionHeaderModel(this.mResources.getString(C2658R.string.offers_subsection_further_away), 0, false);
        if (this.mIsMySurprises) {
            string = this.mResources.getString(C2658R.string.title_your_surprises);
        } else {
            string = this.mResources.getString(C2658R.string.title_your_offers);
        }
        this.mOfferSectionHeaderModel = new MCDListSectionHeaderModel(string, C2358R.C2359drawable.ic_offer, true, 17170443, C2658R.color.mcd_yellow);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(C2358R.C2357id.refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(new C32514());
        UIUtils.setDefaultRefreshColors(this.mSwipeRefreshLayout);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(C2358R.C2357id.list_view);
        initializeRecyclerView(recyclerView);
        recyclerView.setOnScrollListener(new C32525());
        this.mHeaderView = inflater.inflate(C2658R.layout.view_home_list_header, null, false);
        this.mPromoPager = (HeaderViewPager) this.mHeaderView.findViewById(C2358R.C2357id.header_view_pager);
        this.mAdapter.setDashboardListener(this);
        return rootView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NotificationCenter.getSharedInstance().postNotification("NOTIFICATION_FINISH_TUTORIAL");
        this.mNeedsRefresh = true;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        if (this.mFirstNameGreeting != null) {
            return String.format(this.mResources.getString(C2658R.string.text_initial_greeting), new Object[]{this.mFirstNameGreeting});
        }
        int hour = Calendar.getInstance().get(11);
        if (hour >= 3 && hour < 12) {
            return getString(C2658R.string.title_morning);
        }
        if (hour < 12 || hour >= 18) {
            return getString(C2658R.string.title_evening);
        }
        return getString(C2658R.string.title_afternoon);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_home);
    }

    public void onResume() {
        boolean verified = true;
        super.onResume();
        this.mPromoPager.onResume();
        ActionBar actionBar = getNavigationActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }
        OrderingManager manager = OrderingManager.getInstance();
        if (this.mViewModel.isLoggedIn() != this.mCurrentLoginState || (manager.isOrderingAvailable() && manager.getCurrentOrder().getDeliveryDate() != null)) {
            this.mNeedsRefresh = true;
        } else {
            Bundle arguments = getArguments();
            if (arguments != null && arguments.getBoolean("EXTRA_REFRESH_DASHBOARD")) {
                if (!(this.mViewModel.isEmailVerified().booleanValue() || this.mViewModel.isMobileVerified().booleanValue())) {
                    verified = false;
                }
                this.mAdapter.addAlertItem(verified);
                arguments.remove("EXTRA_REFRESH_DASHBOARD");
            }
        }
        if (getActivity().getIntent().getBooleanExtra("show_email_changed_message", false)) {
            getActivity().getIntent().removeExtra("show_email_changed_message");
            UIUtils.showGlobalAlertDialog(getActivity(), getString(C2658R.string.dialog_title_success), getString(C2658R.string.dialog_body_email_changed), null);
        }
        if (this.mPushOffer != null) {
            if (this.mPushOffer.isExpired().booleanValue()) {
                startActivity(PushNotificationOfferAlertActivity.class, PushNotificationOfferAlertFragment.NAME);
            } else {
                Bundle extras = new Bundle();
                extras.putInt("offer_selection_type", OfferSelection.Nearby.ordinal());
                extras.putSerializable(URLNavigationActivity.DATA_PASSER_KEY, this.mPushOffer);
                startActivityForResult(OfferActivity.class, "offer_detail", extras, 1389);
            }
            this.mPushOffer = null;
        }
        if (ConfigurationUtils.shouldFilterStoreResultsUsingGeneralStatus()) {
            checkIfCurrentStoreIsOpen();
        }
        getNavigationActivity().setTitle(getTitle());
        if (this.mNeedsRefresh) {
            refresh();
        }
    }

    public void onPause() {
        super.onPause();
        this.mPromoPager.onPause();
        getActivity().getContentResolver().unregisterContentObserver(this.mCatalogObserver);
    }

    private void checkIfCurrentStoreIsOpen() {
        Ensighten.evaluateEvent(this, "checkIfCurrentStoreIsOpen", null);
        StoreLocatorModule storeLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
        final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        String currentStoreId = null;
        if (customerModule.getCurrentStore() != null) {
            currentStoreId = String.valueOf(customerModule.getCurrentStore().getStoreId());
        }
        final DialogInterface.OnClickListener onPositiveClick = new C32536();
        if (customerModule != null && storeLocatorModule != null && currentStoreId != null) {
            storeLocatorModule.getStoreForId(currentStoreId, new AsyncListener<List<Store>>() {
                public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null || !DashboardFragment.this.isActivityAlive()) {
                        return;
                    }
                    if (response.isEmpty() || response.get(0) == null || !((Store) response.get(0)).isGeneralStatusIsOpen()) {
                        MCDAlertDialogBuilder.withContext(DashboardFragment.this.getContext()).setMessage(DashboardFragment.this.getString(C2658R.string.location_out_of_service)).setPositiveButton((int) C2658R.string.f6083ok, onPositiveClick).create().show();
                        DataLayerManager.getInstance().recordError("Must select a store before checking out");
                        return;
                    }
                    Store store = (Store) response.get(0);
                    customerModule.setCurrentStore(store);
                    DataLayerManager.getInstance().setStore(store);
                }
            });
        }
    }

    public void setPushOffer(Offer pushOffer) {
        Ensighten.evaluateEvent(this, "setPushOffer", new Object[]{pushOffer});
        this.mPushOffer = pushOffer;
    }

    private void populateHomeListItems() {
        Ensighten.evaluateEvent(this, "populateHomeListItems", null);
        for (HomeListItem homeListItem : this.mViewModel.getHomeListItems()) {
            this.mAdapter.addHomeListItem(homeListItem);
        }
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        Ensighten.evaluateEvent(this, "initializeRecyclerView", new Object[]{recyclerView});
        this.mAdapter = new DashboardAdapter(getNavigationActivity(), this.mServiceConnection, this.mNearbyOffers);
        this.mAdapter.setOfferTabSelection(this.mDeliveryOffers);
        GridLayoutManager layoutManager = new GridLayoutManager(getNavigationActivity(), 2);
        layoutManager.setSpanSizeLookup(new C32558());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.mAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateCurrentLocationSubtitle() {
        Ensighten.evaluateEvent(this, "populateCurrentLocationSubtitle", null);
        String storeName = this.mViewModel.getCurrentStoreName();
        if (storeName != null) {
            this.mAdapter.setCurrentStoreSubtitle(storeName);
        } else if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
            this.mAdapter.setCurrentStoreSubtitle("");
        }
    }

    private void populatePromos() {
        Ensighten.evaluateEvent(this, "populatePromos", null);
        if (this.mPromoPager.getAdapter() == null) {
            this.mAsyncCounter.incrementMax();
            this.mViewModel.getPromoList(this.mPromosListener, this.mServiceConnection);
            return;
        }
        if (this.mPromoFragmentStatePagerAdapter != null) {
            for (int i = 0; i < this.mPromoFragmentStatePagerAdapter.getCount(); i++) {
                ((ImageViewFragment) this.mPromoFragmentStatePagerAdapter.getItem(i)).refreshImageView();
            }
        }
        final PagerIndicatorGroup indicator = (PagerIndicatorGroup) this.mHeaderView.findViewById(C2358R.C2357id.pager_indicator);
        indicator.setCount(this.mPromoFragmentStatePagerAdapter.getCount());
        indicator.select(this.mPromoPager.getCurrentItem());
        this.mPromoPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
            }

            public void onPageSelected(int position) {
                Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
                if (DashboardFragment.this.getActivity() != null) {
                    indicator.select(position);
                }
            }

            public void onPageScrollStateChanged(int state) {
                Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
                switch (state) {
                    case 0:
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).onResume();
                        return;
                    case 1:
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$200", new Object[]{DashboardFragment.this}).onPause();
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void newPromoWorkflowForOffer(String offerId) {
        Ensighten.evaluateEvent(this, "newPromoWorkflowForOffer", new Object[]{offerId});
        if (OrderManager.getInstance().getCurrentStore() != null) {
            NavigationManager.getInstance().showOffer(getActivity(), offerId);
        } else {
            startActivity(SelectStoreActivity.class);
        }
    }

    private void newPromoWorkflowForProduct(String productId) {
        Ensighten.evaluateEvent(this, "newPromoWorkflowForProduct", new Object[]{productId});
        if (OrderingManager.getInstance().getCurrentOrder().getIsPODSet()) {
            onClickPromoProduct(productId);
        } else {
            startActivityForResult(OrderMethodSelectionActivity.class, 1223);
        }
    }

    private void onClickPromoCategory(String categoryId) {
        Ensighten.evaluateEvent(this, "onClickPromoCategory", new Object[]{categoryId});
        Bundle extras = new Bundle();
        extras.putString("arg_category_id", categoryId);
        extras.putString("module", "ordering");
        extras.putBoolean(URLNavigationActivity.MODAL, true);
        startActivity(MenuActivity.class, "menu_grid", extras);
    }

    public void onClickPromoProduct(String productId) {
        Ensighten.evaluateEvent(this, "onClickPromoProduct", new Object[]{productId});
        UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_loading_product);
        if (this.mOrderingModule != null) {
            this.mOrderingModule.getProductForId(productId, this.mRecipeLoadedListener);
        } else {
            this.mQueuedRecipeId = productId;
        }
    }

    public void onClickPromoLink(final String link) {
        Ensighten.evaluateEvent(this, "onClickPromoLink", new Object[]{link});
        if (link.contains(URLNavigationActivity.URI_SCHEME)) {
            HomeListItem homeItem = getHomeItemByLink(link);
            if (homeItem != null) {
                onHomeListItemClicked(homeItem);
                return;
            } else if (this.mSwipeRefreshLayout.isRefreshing()) {
                stopRefresh();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Ensighten.evaluateEvent(this, "run", null);
                        DashboardFragment.this.openSelfUrl(link);
                    }
                }, 150);
                return;
            } else {
                openSelfUrl(link);
                return;
            }
        }
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(link)));
    }

    public HomeListItem getHomeItemByLink(String link) {
        Ensighten.evaluateEvent(this, "getHomeItemByLink", new Object[]{link});
        for (HomeListItem item : this.mViewModel.getHomeListItems()) {
            if (item.getLink().equals(link)) {
                return item;
            }
        }
        return null;
    }

    private boolean isMSALink(String link) {
        Ensighten.evaluateEvent(this, "isMSALink", new Object[]{link});
        return link != null && link.contains("morning_surprise_alarm");
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        if (!this.isRefreshing) {
            this.isRefreshing = true;
            this.mAdapter.clear();
            this.mAdapter.addHeaderView(this.mHeaderView);
            this.mAsyncCounter = new AsyncCounter(1, new C322814());
            populateLatestOrderNumber();
            populateHomeListItems();
            populateAlerts();
            populateCurrentLocationSubtitle();
            populateCustomerLastOrder();
            populateScheduledOrders();
            populateOffers();
            populateDailyAdvertisablePromotions();
            populatePromos();
            this.mAdapter.setLoginState(this.mViewModel.isLoggedIn());
            this.mOfferCount = this.mViewModel.getOfferCount();
            this.mCurrentLoginState = this.mViewModel.isLoggedIn();
            updateDrawerLoginState();
            this.mAsyncCounter.success(null);
            this.mNeedsRefresh = false;
            if (!this.mViewModel.isLoggedIn()) {
                checkAutoLoginFailed();
            }
            AnalyticsUtils.trackEvent("offer_view_all");
        }
    }

    private void populateAlerts() {
        Ensighten.evaluateEvent(this, "populateAlerts", null);
        boolean verified = this.mViewModel.isEmailVerified().booleanValue() || this.mViewModel.isMobileVerified().booleanValue();
        this.mAdapter.addAlertItem(verified);
    }

    private void updateDrawerLoginState() {
        Ensighten.evaluateEvent(this, "updateDrawerLoginState", null);
        if (getNavigationActivity() != null) {
            if (!(this.mCurrentLoginState || this.mFirstNameGreeting == null)) {
                this.mFirstNameGreeting = null;
            }
            NavigationDrawerFragment navFragment = (NavigationDrawerFragment) getNavigationActivity().getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer);
            if (navFragment != null) {
                navFragment.setLoggedInDrawerState(this.mCurrentLoginState);
            }
        }
    }

    private void populateLatestOrderNumber() {
        Ensighten.evaluateEvent(this, "populateLatestOrderNumber", null);
        if (Configuration.getSharedInstance().getValueForKey("modules.ordering.cacheLatestOrderMinutes") != null) {
            String latestOrderNumber = LocalDataManager.getSharedInstance().getLatestOrderNumber();
            HashMap<String, Serializable> attributes = new HashMap();
            if (latestOrderNumber != null) {
                attributes.put("EXTRA_ORDER_NUMBER", latestOrderNumber);
                attributes.put("EXTRA_IS_DRIVE_THRU", Boolean.valueOf(LocalDataManager.getSharedInstance().isLatestOrderDriveThru()));
                this.mAdapter.addHomeListItem(new HomeListItem(C2358R.C2359drawable.icon_order_number, this.mResources.getString(C2658R.string.dashboard_latest_order_number, new Object[]{latestOrderNumber}), this.mResources.getString(C2658R.string.dashboard_latest_order_number_subtitle), "mcdmobileapp://latest_order", attributes));
            }
        }
    }

    private void populateCustomerLastOrder() {
        boolean showLastOrder = false;
        Ensighten.evaluateEvent(this, "populateCustomerLastOrder", null);
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.hideLastOrder", false)) {
            showLastOrder = true;
        }
        if (showLastOrder && !ConfigurationUtils.isInterimCheckinFlow() && this.mViewModel.isLoggedIn()) {
            this.mViewModel.getCustomerLastOrder(new C322915());
        }
    }

    public void populateScheduledOrders() {
        Ensighten.evaluateEvent(this, "populateScheduledOrders", null);
        if (this.mViewModel.isLoggedIn()) {
            this.mViewModel.loadScheduledOrders(new C323016());
        }
    }

    private void populateOffers() {
        Ensighten.evaluateEvent(this, "populateOffers", null);
        if (this.mViewModel.isLoggedIn()) {
            AsyncListener<List<Store>> favStoreAsyncListener = new C323117();
            AsyncListener<List<Offer>> offerAsyncListener = new C323218();
            if (this.mViewModel.isSubscribedToOffers().booleanValue()) {
                this.mAdapter.removeAllOffers(true);
                this.mAdapter.showProgressSpinner(true);
                this.mAsyncCounter.incrementMax();
                this.mViewModel.getOffers(offerAsyncListener, favStoreAsyncListener);
                return;
            }
            this.mAdapter.addNotSubscribedToOffersView();
        }
    }

    private void populateDailyAdvertisablePromotions() {
        Ensighten.evaluateEvent(this, "populateDailyAdvertisablePromotions", null);
        if (OrderManager.getInstance().getCurrentStore() != null) {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            String userName = "";
            if (customerModule != null && customerModule.getCurrentProfile() != null && customerModule.isLoggedIn()) {
                customerModule.getCurrentProfile().getUserName();
            }
        }
    }

    public void stopRefresh() {
        Ensighten.evaluateEvent(this, "stopRefresh", null);
        if (this.mAsyncCounter != null && this.mAsyncCounter.hasActiveProcesses()) {
            this.mAsyncCounter.error(null);
        } else if (this.mSwipeRefreshLayout.isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void onHomeListItemClicked(final HomeListItem item) {
        Ensighten.evaluateEvent(this, "onHomeListItemClicked", new Object[]{item});
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            stopRefresh();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    DashboardFragment.access$1700(DashboardFragment.this, item);
                }
            }, 150);
            return;
        }
        checkIfDuplicateOrderAlertEnabled(item);
    }

    private void checkIfDuplicateOrderAlertEnabled(final HomeListItem item) {
        Ensighten.evaluateEvent(this, "checkIfDuplicateOrderAlertEnabled", new Object[]{item});
        if ((!item.getLink().contains("menu_grid") && !item.getLink().contains("start_order_delivery") && !item.getLink().contains("start_order")) || !ConfigurationUtils.isDuplicateOrderCheckinFlow() || !LocalDataManager.getSharedInstance().hasObjectInCache(LocalDataManager.KEY_CHECKIN_TIMER)) {
            homeListItemClicked(item);
        } else if (ConfigurationUtils.isDuplicateOrderCheckinAllowOrdering()) {
            UIUtils.showCheckinFlowAlert(getContext(), true, null, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    DashboardFragment.access$1800(DashboardFragment.this, item);
                }
            });
        } else {
            UIUtils.showCheckinFlowAlert(getContext(), false, null, null);
        }
    }

    private void homeListItemClicked(HomeListItem item) {
        Ensighten.evaluateEvent(this, "homeListItemClicked", new Object[]{item});
        this.extras = null;
        if (item.getAttributes() != null) {
            this.extras = new Bundle();
            for (String key : item.getAttributes().keySet()) {
                this.extras.putSerializable(key, (Serializable) item.getAttributes().get(key));
            }
        }
        HashMap<String, Object> jiceMap;
        if (item.getLink().contains("order/last/") && this.mViewModel != null) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") && OrderManager.getInstance().getCurrentStore() == null) {
                Bundle b = new Bundle();
                b.putString("LAST_ORDER_FRAGMENT_NAME", item.getLink());
                b.putBundle("PASS_THROUGH_ARGS", this.extras);
                startActivityForResult(OrderMethodSelectionActivity.class, b, 1690);
            } else {
                startActivity(LastOrderActivity.class, item.getLink(), this.extras);
            }
            jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_DASHBOARD_LAST_ORDER);
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        } else if (item.getLink().contains("order/track/")) {
            String orderNumber = item.getLink().replace("order/track/", "");
            Bundle arguments = new Bundle();
            arguments.putString("arg_order_number", orderNumber);
            arguments.putString("arg_edt", item.getSubTitle());
            startActivity(TrackOrderActivity.class, arguments);
            jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_DASHBOARD_TRACK);
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        } else if (item.getLink().contains("latest_order")) {
            startActivity(LatestOrderActivity.class, this.extras);
        } else if (item.getLink().contains("menu_grid")) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Food");
            openSelfUrl(item.getLink(), this.extras);
        } else if (item.getLink().contains("start_order_delivery")) {
            Map jiceMap2 = new HashMap();
            jiceMap2.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_START_DELIVERY_ORDER);
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Food", jiceMap2, new CustomerEventAction(CustomEvent.ADCUSTOM1));
            StartDeliveryOrder();
        } else if (item.getLink().contains("start_order")) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.warnNonMobileOrdering")) {
                Store currStore = OrderManager.getInstance().getCurrentStore();
                if (!(currStore == null || currStore.hasMobileOrdering().booleanValue())) {
                    MCDAlertDialogBuilder.withContext(getActivity()).setMessage((int) C2658R.string.mobile_ordering_not_supported).setPositiveButton((int) C2658R.string.f6083ok, new C323621()).create().show();
                    DataLayerManager.getInstance().recordError("Mobile ordering is not supported");
                    return;
                }
            }
            StartOrder(this.extras);
        } else if (item.getLink().contains("store_locator")) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Restaurant Locator");
            openSelfUrl(item.getLink(), this.extras);
        } else if (item.getLink().contains("nutrition_list")) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Restaurant Locator");
            openSelfUrl(item.getLink(), this.extras);
        } else if (item.getLink().contains("favorites_grid")) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Restaurant Locator");
            openSelfUrl(item.getLink(), this.extras);
        }
    }

    private boolean CheckInBreakfastTime(DialogInterface.OnClickListener listener) {
        Ensighten.evaluateEvent(this, "CheckInBreakfastTime", new Object[]{listener});
        Store currStore = OrderManager.getInstance().getCurrentStore();
        String alertTimeConfig = (String) Configuration.getSharedInstance().getValueForKey("interface.dayparts.daypartEndingAlertTime");
        if (!(alertTimeConfig == null || currStore == null)) {
            Boolean isDelivery = Boolean.valueOf(OrderingManager.getInstance().getCurrentOrder().isDelivery());
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            int typeId = currStore.getCurrentMenuTypeID();
            MenuType menuType = null;
            for (MenuType mType : orderingModule.getMenuTypes()) {
                if (mType.getID() == typeId) {
                    menuType = mType;
                }
            }
            if (!(menuType == null || menuType.getShortName() == null)) {
                long timeLeftInMenu = currStore.getMenuEndingTime(menuType, isDelivery.booleanValue());
                long alertTime = Long.valueOf(alertTimeConfig).longValue();
                if (timeLeftInMenu > 0 && timeLeftInMenu <= alertTime) {
                    MCDAlertDialogBuilder.withContext(getActivity()).setMessage(getString(C2658R.string.label_daypart_menu_warning_ios, menuType.getShortName(), Long.valueOf(timeLeftInMenu))).setPositiveButton((int) C2658R.string.f6083ok, listener).create().show();
                    return true;
                }
            }
        }
        return false;
    }

    private void StartDeliveryOrder() {
        Ensighten.evaluateEvent(this, "StartDeliveryOrder", null);
        final Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        if (currentOrder.isEmpty() || currentOrder.isDelivery()) {
            if (!currentOrder.isDelivery()) {
                currentOrder.setNormalOrder(true);
            }
            currentOrder.setIsDelivery(true);
            currentOrder.setPriceType(PriceType.Delivery);
            LocalDataManager.getSharedInstance().setModuleName("start_order_delivery");
            if (!CheckInBreakfastTime(new C323923())) {
                startActivity(MenuActivity.class, "start_order_delivery");
                return;
            }
            return;
        }
        Bundle finalExtras = this.extras;
        MCDAlertDialogBuilder.withContext(getActivity()).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {

            /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$22$1 */
            class C32371 implements DialogInterface.OnClickListener {
                C32371() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    DashboardFragment.this.startActivity(MenuActivity.class, "start_order_delivery");
                }
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                currentOrder.clearOffers();
                currentOrder.clearProducts();
                currentOrder.setIsDelivery(true);
                currentOrder.setNormalOrder(true);
                currentOrder.setPriceType(PriceType.Delivery);
                LocalDataManager.getSharedInstance().setModuleName("start_order_delivery");
                if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$2100", new Object[]{DashboardFragment.this, new C32371()})) {
                    DashboardFragment.this.startActivity(MenuActivity.class, "start_order_delivery");
                }
            }
        }).setNegativeButton(getString(C2658R.string.button_cancel), null).setCancelable(false).create().show();
    }

    private void StartOrder(Bundle extras) {
        Ensighten.evaluateEvent(this, "StartOrder", new Object[]{extras});
        Map jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_START_PICKUP_ORDER);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Food", jiceMap, new CustomerEventAction(CustomEvent.ADCUSTOM1));
        final Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        if (currentOrder.isEmpty() || !currentOrder.isDelivery()) {
            if (currentOrder.isDelivery()) {
                currentOrder.setNormalOrder(false);
            }
            currentOrder.setIsDelivery(false);
            currentOrder.setPriceType(PriceType.EatIn);
            LocalDataManager.getSharedInstance().setModuleName("start_order");
            if (!CheckInBreakfastTime(new C324225())) {
                startActivity(MenuActivity.class, "start_order");
                return;
            }
            return;
        }
        Bundle finalExtras = extras;
        MCDAlertDialogBuilder.withContext(getActivity()).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {

            /* renamed from: com.mcdonalds.app.home.dashboard.DashboardFragment$24$1 */
            class C32401 implements DialogInterface.OnClickListener {
                C32401() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    DashboardFragment.this.startActivity(MenuActivity.class, "start_order");
                }
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                currentOrder.clearOffers();
                currentOrder.clearProducts();
                currentOrder.setIsDelivery(false);
                currentOrder.setNormalOrder(false);
                currentOrder.setPriceType(PriceType.EatIn);
                LocalDataManager.getSharedInstance().setModuleName("start_order");
                if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$2100", new Object[]{DashboardFragment.this, new C32401()})) {
                    DashboardFragment.this.startActivity(MenuActivity.class, "start_order");
                }
            }
        }).setNegativeButton(getString(C2658R.string.button_cancel), null).setCancelable(false).create().show();
    }

    public void onListItemClick(Offer offer) {
        Ensighten.evaluateEvent(this, "onListItemClick", new Object[]{offer});
        if (OrderManager.getInstance().getCurrentStore() == null && Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
            startActivity(SelectStoreActivity.class);
        } else {
            onOfferItemClicked(offer);
        }
    }

    public void onGridItemClick(Offer offer) {
        Ensighten.evaluateEvent(this, "onGridItemClick", new Object[]{offer});
        if (OrderManager.getInstance().getCurrentStore() == null && Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
            startActivity(SelectStoreActivity.class);
        } else {
            onOfferItemClicked(offer);
        }
    }

    private void onOfferItemClicked(final Offer offer) {
        Ensighten.evaluateEvent(this, "onOfferItemClicked", new Object[]{offer});
        final OfferSelection selection = !this.mNearbyOffers ? OfferSelection.Current : OfferSelection.Nearby;
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            stopRefresh();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    DashboardFragment.access$2200(DashboardFragment.this, offer, selection);
                }
            }, 150);
            return;
        }
        commonOnOfferClick(offer, selection);
    }

    private void commonOnOfferClick(Offer offer, OfferSelection selection) {
        Ensighten.evaluateEvent(this, "commonOnOfferClick", new Object[]{offer, selection});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory(getAnalyticsTitle()).setLabel(offer.getName()).setBusiness(BusinessArgs.getOfferClick(offer)).setECommercePromotion(offer).build());
        AnalyticsUtils.trackEvent(new ArgBuilder().setLabel(BusinessArgs.EVENT_PRODUCT_OFFER_CLICK).setMapping(BusinessArgs.KEY_OFFER_ID, offer.getOfferId()).setMapping(BusinessArgs.KEY_OFFER_NAME, offer.getName()).build());
        final Bundle extras = new Bundle();
        extras.putInt("offer_selection_type", selection.ordinal());
        DataPasser.getInstance().putData("extra_offer", offer);
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            stopRefresh();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    DashboardFragment.this.startActivityForResult(OfferActivity.class, "offer_detail", extras, 1389);
                }
            }, 150);
            return;
        }
        startActivityForResult(OfferActivity.class, "offer_detail", extras, 1389);
    }

    public void onRegisterClick() {
        Ensighten.evaluateEvent(this, "onRegisterClick", null);
        Map jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_REGISTER);
        jiceMap.put(JiceArgs.LABEL_DASHBOARD_REGISTER_KEY, JiceArgs.LABEL_DASHBOARD_REGISTER_VAL);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Start Registration", jiceMap);
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            stopRefresh();
            new Handler().postDelayed(new C324528(), 150);
        } else if (ConfigurationUtils.isGmaLiteFlow()) {
            startActivity(LiteSignUpActivity.class);
        } else {
            startActivity(TermsOfServiceActivity.class);
        }
    }

    public void onSignInClick() {
        Ensighten.evaluateEvent(this, "onSignInClick", null);
        Map jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_DASHBOARD_LOGIN);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Sign In", jiceMap);
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            stopRefresh();
            new Handler().postDelayed(new C324629(), 150);
        } else if (ConfigurationUtils.isGmaLiteFlow()) {
            startActivity(LiteSignInActivity.class);
        } else {
            skipSignIn();
        }
    }

    private void skipSignIn() {
        Ensighten.evaluateEvent(this, "skipSignIn", null);
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        String prefSavedLogin = localDataManager.getPrefSavedLogin();
        String prefSavedLoginPass = localDataManager.getPrefSavedLoginPass();
        int prefSavedSocialID = localDataManager.getPrefSavedSocialNetworkId();
        boolean autoLogin = (TextUtils.isEmpty(prefSavedLogin) || (TextUtils.isEmpty(prefSavedLoginPass) && prefSavedSocialID == -1)) ? false : true;
        if (autoLogin) {
            Bundle extras = new Bundle();
            if (prefSavedSocialID != 3) {
                extras.putString("EXTRA_AUTO_LOGIN_USERNAME", prefSavedLogin);
                extras.putString("EXTRA_AUTO_LOGIN_PASSWORD", prefSavedLoginPass);
            } else {
                extras.putString("EXTRA_AUTO_LOGIN_USERNAME", "");
                extras.putString("EXTRA_AUTO_LOGIN_PASSWORD", "");
            }
            startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, extras);
            return;
        }
        startActivity(SignInActivity.class);
    }

    public void onRemoveFromBasketClicked(final OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "onRemoveFromBasketClicked", new Object[]{orderOffer});
        this.mViewModel.removeOfferFromOrder(orderOffer, new AsyncListener<Boolean>() {
            public void onResponse(Boolean removed, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{removed, token, exception});
                if (removed.booleanValue()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$300", new Object[]{DashboardFragment.this}).removeRemoveFromBasketView();
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.DashboardFragment", "access$300", new Object[]{DashboardFragment.this}).removeAppliedOfferBadge(orderOffer);
                    URLNavigationActivity a = DashboardFragment.this.getNavigationActivity();
                    if (a instanceof URLBasketNavigationActivity) {
                        ((URLBasketNavigationActivity) a).updateBasketBadge();
                    }
                }
            }
        });
    }

    public void onOffersTypeSelectorChanged(RadioGroup offersSelector) {
        Ensighten.evaluateEvent(this, "onOffersTypeSelectorChanged", new Object[]{offersSelector});
        offersSelector.getCheckedRadioButtonId();
        switch (offersSelector.getCheckedRadioButtonId()) {
            case C2358R.C2357id.offers_type_near_you /*2131821738*/:
                this.mNearbyOffers = true;
                this.mDeliveryOffers = false;
                this.mPickUpOffers = false;
                DataLayerManager.getInstance().logButtonClick("NearYouTabPressed");
                break;
            case C2358R.C2357id.offers_type_current /*2131821740*/:
                this.mNearbyOffers = false;
                this.mDeliveryOffers = false;
                this.mPickUpOffers = false;
                DataLayerManager.getInstance().logButtonClick("SelectedRestaurantTabPressed");
                break;
            case C2358R.C2357id.offers_type_pickup /*2131821742*/:
                this.mNearbyOffers = false;
                this.mDeliveryOffers = false;
                this.mPickUpOffers = true;
                break;
            case C2358R.C2357id.offers_type_delivery /*2131821744*/:
                this.mNearbyOffers = false;
                this.mDeliveryOffers = true;
                this.mPickUpOffers = false;
                break;
        }
        checkAndDisplayOffers(true);
        AnalyticsUtils.trackEvent("offer_view_all");
    }

    public void onDismissAlertClicked() {
        Ensighten.evaluateEvent(this, "onDismissAlertClicked", null);
        getNavigationActivity().getSharedPreferences("config", 0).edit().putBoolean("email_verification_alert", true).apply();
        this.mAdapter.removeAlertView();
    }

    public void onAlertClicked() {
        Ensighten.evaluateEvent(this, "onAlertClicked", null);
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            stopRefresh();
            new Handler().postDelayed(new C324931(), 150);
        } else if (getNavigationActivity() != null) {
            CustomerProfile mProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
            if (mProfile == null) {
                return;
            }
            if (mProfile.isUsingSocialLoginWithoutEmail() || mProfile.getLoginPreference() == 2) {
                startActivity(ProfileUpdateActivity.class, ChangeMobileFragment.NAME);
            } else {
                startActivity(ProfileUpdateActivity.class, ChangeEmailAddressFragment.NAME);
            }
        }
    }

    public void onSelectStoreClicked() {
        Ensighten.evaluateEvent(this, "onSelectStoreClicked", null);
        Bundle b = new Bundle();
        b.putBoolean("INIT_AS_DELIVERY", this.mDeliveryOffers);
        startActivityForResult(OrderMethodSelectionActivity.class, b, 1223);
    }

    private void checkAndDisplayOffers(boolean isSwitchingTabs) {
        Ensighten.evaluateEvent(this, "checkAndDisplayOffers", new Object[]{new Boolean(isSwitchingTabs)});
        displayOffers(isSwitchingTabs, true);
    }

    private void displayOffers(boolean isSwitchingTabs, boolean hasAddress) {
        Ensighten.evaluateEvent(this, "displayOffers", new Object[]{new Boolean(isSwitchingTabs), new Boolean(hasAddress)});
        if (this.mIsMySurprises) {
            displaySurprises();
            return;
        }
        this.mAdapter.removeNoOffersView();
        this.mAdapter.showProgressSpinner(isSwitchingTabs);
        this.mAdapter.removeNoSelectedStoreView();
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") || this.mNearbyOffers || ((!this.mDeliveryOffers || hasAddress) && OrderManager.getInstance().getCurrentStore() != null)) {
            List<Offer> offersToDisplayInGrid;
            List<Offer> offersToDisplayInList;
            if (isSwitchingTabs) {
                this.mAdapter.removeAllOffers(false);
                this.mOfferCount = this.mViewModel.getOfferCount();
            } else {
                this.mAdapter.setOfferTabSelection(this.mDeliveryOffers);
                this.mAdapter.addSectionHeader(this.mOfferSectionHeaderModel, true);
                this.mAdapter.removeAllOffers(false);
                this.mOfferCount = this.mViewModel.getOfferCount();
            }
            if (this.mPickUpOffers) {
                offersToDisplayInGrid = this.mViewModel.getCurrentStorePickupOffers();
                offersToDisplayInList = new ArrayList();
            } else if (this.mDeliveryOffers) {
                offersToDisplayInGrid = this.mViewModel.getCurrentStoreDeliveryOffers();
                offersToDisplayInList = this.mViewModel.getFavoriteStoreDeliveryOffers();
            } else {
                offersToDisplayInGrid = this.mViewModel.getCurrentStoreDeliveryOffers();
                if (this.mNearbyOffers) {
                    offersToDisplayInList = this.mViewModel.getALittleFurtherOffers();
                } else {
                    offersToDisplayInList = this.mViewModel.getFavoriteStorePickupOffers();
                }
            }
            boolean hasOffersToDisplayInGrid = ListUtils.isNotEmpty(offersToDisplayInGrid);
            boolean hasOffersToDisplayInList = ListUtils.isNotEmpty(offersToDisplayInList);
            if (this.mNearbyOffers && !LocationServicesManager.isLocationEnabled(getContext())) {
                this.mAdapter.addNoGPSView();
            } else if (hasOffersToDisplayInGrid) {
                for (Offer offer : offersToDisplayInGrid) {
                    if (this.mOfferCount <= 0) {
                        break;
                    }
                    this.mAdapter.addOffer(offer, true);
                    this.mOfferCount--;
                }
                if (hasOffersToDisplayInList) {
                    removeDuplicates(offersToDisplayInGrid, offersToDisplayInList);
                }
            } else if (!hasOffersToDisplayInGrid && this.mNearbyOffers) {
                this.mAdapter.addNoRestaurantsNearbyView();
            } else if (!hasOffersToDisplayInList) {
                this.mAdapter.addNoOffersView();
            }
            if (this.mOfferCount > 0 && hasOffersToDisplayInList && !offersToDisplayInList.isEmpty()) {
                this.mAdapter.addOffersSubsectionHeader(this.mNearbyOffers ? this.mOffersSectionFurtherAwayHeaderModel : this.mOffersSectionFavoriteHeaderModel);
                for (Offer offer2 : offersToDisplayInList) {
                    if (this.mOfferCount <= 0) {
                        break;
                    }
                    this.mAdapter.addOffer(offer2, false);
                    this.mOfferCount--;
                }
            }
            if (!isSwitchingTabs) {
                addRemoveFromBasketView();
                this.mSwipeRefreshLayout.setRefreshing(false);
            }
            this.mAdapter.showProgressSpinner(false);
            return;
        }
        if (isSwitchingTabs) {
            this.mAdapter.removeAllOffers(false);
        } else {
            this.mAdapter.setOfferTabSelection(this.mDeliveryOffers);
            this.mAdapter.addSectionHeader(this.mOfferSectionHeaderModel, true);
            this.mAdapter.removeAllOffers(false);
        }
        this.mAdapter.addNoSelectedStoreView(this.mDeliveryOffers);
        this.mAdapter.showProgressSpinner(false);
    }

    private void displaySurprises() {
        Ensighten.evaluateEvent(this, "displaySurprises", null);
        this.mAdapter.removeNoOffersView();
        this.mAdapter.removeAllOffers(false);
        this.mAdapter.addSectionHeader(this.mOfferSectionHeaderModel, false);
        List<Offer> offers = this.mViewModel.getActiveOffers();
        if (ListUtils.isNotEmpty(offers)) {
            for (Offer offer : offers) {
                this.mAdapter.addOffer(offer, true);
            }
        } else {
            this.mAdapter.addNoOffersView();
        }
        addRemoveFromBasketView();
        this.mSwipeRefreshLayout.setRefreshing(false);
        this.mAdapter.showProgressSpinner(false);
    }

    private void addRemoveFromBasketView() {
        Ensighten.evaluateEvent(this, "addRemoveFromBasketView", null);
        List<OrderOffer> currentOrderOffers = this.mViewModel.getCurrentOrderOffers();
        if (this.mViewModel.verifyIsAddedToOrder()) {
            for (OrderOffer currentOrderOffer : currentOrderOffers) {
                if (!currentOrderOffer.getOffer().isPunchCard()) {
                    this.mAdapter.addRemoveFromBasketView(currentOrderOffer);
                }
            }
        } else {
            this.mAdapter.removeRemoveFromBasketView();
        }
        this.mAdapter.setCurrentOrderOffers(currentOrderOffers);
    }

    private void removeDuplicates(List<Offer> primary, List<Offer> secondary) {
        Ensighten.evaluateEvent(this, "removeDuplicates", new Object[]{primary, secondary});
        Iterator<Offer> iterator = secondary.iterator();
        while (iterator.hasNext()) {
            Offer offer = (Offer) iterator.next();
            for (Offer gridOffer : primary) {
                if (offer.getOfferId().equals(gridOffer.getOfferId())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    private void updateOffersTabSelectionState() {
        Ensighten.evaluateEvent(this, "updateOffersTabSelectionState", null);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
            Order order = OrderingManager.getInstance().getCurrentOrder();
            if (OrderManager.getInstance().getCurrentStore() == null || !order.getIsPODSet()) {
                this.mPickUpOffers = true;
                this.mDeliveryOffers = false;
                return;
            }
            this.mPickUpOffers = true;
            this.mNearbyOffers = false;
            this.mDeliveryOffers = false;
        }
    }
}
