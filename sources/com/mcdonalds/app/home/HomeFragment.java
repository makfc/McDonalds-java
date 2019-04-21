package com.mcdonalds.app.home;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import com.ensighten.Ensighten;
import com.facebook.internal.NativeProtocol;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.TermsOfServiceActivity;
import com.mcdonalds.app.firstload.ChooseSearchMethodFragment;
import com.mcdonalds.app.firstload.SelectStoreActivity;
import com.mcdonalds.app.home.HomeListAdapter.HomeListAdapterListener;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.app.model.PromoResponse;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.offers.OfferSection;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.menu.LastOrderActivity;
import com.mcdonalds.app.ordering.menu.OrderDetailsFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController.OfferSelection;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OfferUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.offers.MCDListSectionHeaderModel;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import com.newrelic.agent.android.payload.PayloadController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Deprecated
public class HomeFragment extends URLNavigationFragment implements HomeListAdapterListener {
    private Boolean mAddedToOrder = Boolean.valueOf(false);
    private CustomerModule mCustomerModule;
    private List<Store> mFavoriteStores = null;
    private List<Offer> mFilteredOffers = null;
    private String mFirstNameGreeting = null;
    private HomeListAdapter mHomeListAdapter;
    private int mHomeListItemCount;
    private ListView mHomeListView;
    private CustomerOrder mLastOrder;
    private Boolean mLocationServiceOn;
    private List<Store> mNearbyStores = null;
    private List<OfferSection> mOfferSections;
    private int mOfferSelectorID = C2358R.C2357id.offers_type_near_you;
    private int mOffersCount;
    private Timer mPromoCarouselTimer;
    private PromoFragmentStatePagerAdapter mPromoFragmentStatePagerAdapter;
    private List<Promo> mPromoList;
    private ViewPager mPromoPager;
    private boolean mRecipeFound = false;
    private SwipeRefreshLayout mRefreshLayout;
    private Resources mResources;
    private RequestManagerServiceConnection mServiceConnection;

    /* renamed from: com.mcdonalds.app.home.HomeFragment$1 */
    class C23611 implements AsyncListener<List<CustomerOrder>> {
        C23611() {
        }

        public void onResponse(List<CustomerOrder> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (HomeFragment.this.getNavigationActivity() != null) {
                HomeFragment.access$000(HomeFragment.this);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).removeHomeListItem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$100", new Object[]{HomeFragment.this}).getString(C2658R.string.last_order_title));
                if (response != null && response.size() > 0) {
                    List<CustomerOrder> filteredOrders = OfferUtils.filterIfFinalized(response);
                    if (!ListUtils.isEmpty(filteredOrders)) {
                        HomeFragment.access$302(HomeFragment.this, (CustomerOrder) filteredOrders.get(0));
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$300", new Object[]{HomeFragment.this}) != null) {
                        HomeFragment.access$408(HomeFragment.this);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).addHomeListItem(new HomeListItem(C2358R.C2359drawable.icon_meal_yellow, Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$100", new Object[]{HomeFragment.this}).getString(C2658R.string.last_order_title), Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$300", new Object[]{HomeFragment.this}).getName(), URLNavigationActivity.URI_SCHEME + OrderDetailsFragment.NAME, null));
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).notifyDataSetChanged();
                    }
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeFragment$2 */
    class C23622 implements AsyncListener<List<StoreFavoriteInfo>> {
        C23622() {
        }

        public void onResponse(List<StoreFavoriteInfo> customerFavoriteStoresInfo, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{customerFavoriteStoresInfo, token, exception});
            if (HomeFragment.this.getNavigationActivity() != null && exception == null && customerFavoriteStoresInfo != null) {
                List<String> favoriteStoreIds = new ArrayList();
                final SparseArray<StoreFavoriteInfo> favoriteInfoArray = new SparseArray();
                for (StoreFavoriteInfo info : customerFavoriteStoresInfo) {
                    Integer storeId = new Integer(info.getStoreId());
                    favoriteStoreIds.add(new Integer(info.getStoreId()).toString());
                    favoriteInfoArray.put(storeId.intValue(), info);
                }
                ((StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME)).getStoresForIds(favoriteStoreIds, new AsyncListener<List<Store>>() {
                    public void onResponse(List<Store> customerFavoriteStores, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{customerFavoriteStores, token, exception});
                        if (HomeFragment.this.getNavigationActivity() == null || exception != null) {
                            HomeFragment.access$800(HomeFragment.this, Boolean.valueOf(false), true);
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).setSubscribedToOffers(false);
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).setHasOffers(false);
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).notifyDataSetChanged();
                            return;
                        }
                        for (Store store : customerFavoriteStores) {
                            StoreFavoriteInfo info = (StoreFavoriteInfo) favoriteInfoArray.get(store.getStoreId());
                            store.setStoreFavoriteId(Integer.valueOf(info.getFavoriteId()));
                            store.setStoreFavoriteName(info.getFavoriteNickName());
                        }
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).setFavoriteStores(customerFavoriteStores);
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$600", new Object[]{HomeFragment.this}).booleanValue()) {
                            Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                            if (currentOrder.getOffers() != null) {
                                for (OrderOffer orderOffer : currentOrder.getOffers()) {
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).clearOffers();
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).addAppliedOffer(orderOffer);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).setSubscribedToOffers(true);
                                    HomeFragment.access$800(HomeFragment.this, Boolean.valueOf(false), true);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).setHasOffers(true);
                                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).notifyDataSetChanged();
                                }
                            }
                        } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).getCurrentProfile() == null) {
                            HomeFragment.access$800(HomeFragment.this, Boolean.valueOf(false), true);
                        } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).getCurrentProfile().isSubscribedToOffers()) {
                            List<String> storeIds;
                            StoreLocatorModule storeLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
                            CustomerProfile profile = Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).getCurrentProfile();
                            OffersModule offersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
                            if (TextUtils.isEmpty(String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).getCurrentStore().getStoreId()))) {
                                storeIds = null;
                            } else {
                                storeIds = Arrays.asList(new String[]{String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).getCurrentStore().getStoreId())});
                            }
                            offersModule.getCustomerOffers(profile.getUserName(), null, null, storeIds, new CustomerOffersListener(storeLocatorModule, Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$700", new Object[]{HomeFragment.this}), customerFavoriteStores));
                        } else {
                            HomeFragment.access$800(HomeFragment.this, Boolean.valueOf(false), true);
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).setSubscribedToOffers(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$500", new Object[]{HomeFragment.this}).getCurrentProfile().isSubscribedToOffers());
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).setHasOffers(false);
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$700", new Object[]{HomeFragment.this}).setAdapter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}));
                        }
                    }
                });
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeFragment$3 */
    class C23643 implements AsyncListener<Object> {
        C23643() {
        }

        public void onResponse(Object response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeFragment$4 */
    class C23654 implements AsyncListener<List<Product>> {
        C23654() {
        }

        public void onResponse(List<Product> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeFragment$5 */
    class C23665 implements OnRefreshListener {
        C23665() {
        }

        public void onRefresh() {
            Ensighten.evaluateEvent(this, "onRefresh", null);
            HomeFragment.access$800(HomeFragment.this, Boolean.valueOf(true), true);
            HomeFragment.access$900(HomeFragment.this, true);
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeFragment$7 */
    class C23687 implements AsyncListener<PromoResponse> {
        C23687() {
        }

        public void onResponse(PromoResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                MCDLog.debug(response.toString());
                if (HomeFragment.this.getNavigationActivity() != null && response.getPromos() != null) {
                    HomeFragment.access$1002(HomeFragment.this, response.getPromos());
                    HomeFragment.access$1102(HomeFragment.this, new PromoFragmentStatePagerAdapter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1000", new Object[]{HomeFragment.this}), HomeFragment.this.getActivity().getSupportFragmentManager(), null));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1200", new Object[]{HomeFragment.this}).setAdapter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1100", new Object[]{HomeFragment.this}));
                    HomeFragment.access$1300(HomeFragment.this);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeFragment$9 */
    class C23709 implements OnItemClickListener {
        C23709() {
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
            int index = position - 1;
            if (index < Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$400", new Object[]{HomeFragment.this})) {
                HomeListItem item = (HomeListItem) Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new Object[]{HomeFragment.this}).getItem(index);
                Bundle extras = null;
                if (item.getAttributes() != null) {
                    extras = new Bundle();
                    for (String key : item.getAttributes().keySet()) {
                        extras.putSerializable(key, (Serializable) item.getAttributes().get(key));
                    }
                }
                if (item.getLink().equals(URLNavigationActivity.URI_SCHEME + OrderDetailsFragment.NAME)) {
                    if (extras == null) {
                        extras = new Bundle();
                    }
                    extras.putParcelable(JiceArgs.EVENT_SUBMIT_ORDER, Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$300", new Object[]{HomeFragment.this}));
                    HomeFragment.this.startActivity(LastOrderActivity.class, extras);
                    return;
                }
                HomeFragment.this.openSelfUrl(item.getLink(), extras);
                if (item.getLink().contains("menu_grid")) {
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory("/home").setLabel("Food").build());
                } else if (item.getLink().contains("start_order")) {
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory("/home").setLabel("Food").build());
                } else if (item.getLink().contains("store_locator")) {
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory("/home").setLabel("Restaurant Locator").build());
                }
            }
        }
    }

    private class CustomerOffersListener implements AsyncListener<List<Offer>> {
        private List<Store> mCustomerFavoriteStores;
        private final CustomerModule mCustomerModule;
        Handler mHandler = new Handler(Looper.getMainLooper());
        private final ListView mHomeListView;
        private final StoreLocatorModule mStoreLocatorModule;

        public CustomerOffersListener(StoreLocatorModule storeLocatorModule, CustomerModule customerModule, ListView homeListView, List<Store> customerFavoriteStores) {
            this.mStoreLocatorModule = storeLocatorModule;
            this.mCustomerModule = customerModule;
            this.mHomeListView = homeListView;
            this.mCustomerFavoriteStores = customerFavoriteStores;
        }

        /* JADX WARNING: Removed duplicated region for block: B:31:0x00ad  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0097  */
        public void onResponse(final java.util.List<com.mcdonalds.sdk.modules.models.Offer> r12, com.mcdonalds.sdk.AsyncToken r13, com.mcdonalds.sdk.AsyncException r14) {
            /*
            r11 = this;
            r10 = 0;
            r5 = 1;
            r6 = 0;
            r7 = "onResponse";
            r8 = 3;
            r8 = new java.lang.Object[r8];
            r8[r6] = r12;
            r8[r5] = r13;
            r9 = 2;
            r8[r9] = r14;
            com.ensighten.Ensighten.evaluateEvent(r11, r7, r8);
            if (r12 == 0) goto L_0x0128;
        L_0x0014:
            if (r14 != 0) goto L_0x00f7;
        L_0x0016:
            r7 = com.mcdonalds.app.home.HomeFragment.this;
            r7 = r7.getNavigationActivity();
            if (r7 == 0) goto L_0x00f7;
        L_0x001e:
            r7 = r12.size();
            if (r7 == 0) goto L_0x00db;
        L_0x0024:
            r0 = new java.util.ArrayList;
            r0.<init>();
            r1 = new java.util.ArrayList;
            r1.<init>();
            r7 = r12.iterator();
        L_0x0032:
            r8 = r7.hasNext();
            if (r8 == 0) goto L_0x0059;
        L_0x0038:
            r3 = r7.next();
            r3 = (com.mcdonalds.sdk.modules.models.Offer) r3;
            r8 = r3.isArchived();
            r8 = r8.booleanValue();
            if (r8 == 0) goto L_0x004b;
        L_0x0048:
            r0.add(r3);
        L_0x004b:
            r8 = r3.isExpired();
            r8 = r8.booleanValue();
            if (r8 == 0) goto L_0x0032;
        L_0x0055:
            r1.add(r3);
            goto L_0x0032;
        L_0x0059:
            r12.removeAll(r0);
            r12.removeAll(r1);
            r7 = r12.size();
            if (r7 == 0) goto L_0x00b5;
        L_0x0065:
            r2 = 0;
            r7 = com.mcdonalds.app.home.HomeFragment.this;
            r7 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1700", new java.lang.Object[]{r7});
            r7 = r7.iterator();
        L_0x0070:
            r8 = r7.hasNext();
            if (r8 == 0) goto L_0x0095;
        L_0x0076:
            r4 = r7.next();
            r4 = (com.mcdonalds.app.offers.OfferSection) r4;
            if (r2 != 0) goto L_0x0070;
        L_0x007e:
            r7 = com.mcdonalds.app.offers.OfferSection.OfferSections.NEAR;
            r7 = r7.getName();
            r8 = r4.getSectionType();
            r7 = r7.equals(r8);
            if (r7 == 0) goto L_0x00ab;
        L_0x008e:
            r7 = r4.isEnabled();
            if (r7 == 0) goto L_0x00ab;
        L_0x0094:
            r2 = r5;
        L_0x0095:
            if (r2 == 0) goto L_0x00ad;
        L_0x0097:
            r5 = r11.mStoreLocatorModule;
            r6 = 4670076682932060160; // 0x40cf720000000000 float:0.0 double:16100.0;
            r6 = java.lang.Double.valueOf(r6);
            r7 = new com.mcdonalds.app.home.HomeFragment$CustomerOffersListener$1;
            r7.<init>(r12);
            r5.getStoresNearCurrentLocationWithinRadius(r6, r10, r7);
        L_0x00aa:
            return;
        L_0x00ab:
            r2 = r6;
            goto L_0x0095;
        L_0x00ad:
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r6 = r11.mCustomerFavoriteStores;
            com.mcdonalds.app.home.HomeFragment.access$1900(r5, r12, r10, r6);
            goto L_0x00aa;
        L_0x00b5:
            r7 = com.mcdonalds.app.home.HomeFragment.this;
            r8 = java.lang.Boolean.valueOf(r6);
            com.mcdonalds.app.home.HomeFragment.access$800(r7, r8, r5);
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r7 = r11.mCustomerModule;
            r7 = r7.getCurrentProfile();
            r7 = r7.isSubscribedToOffers();
            r5.setSubscribedToOffers(r7);
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r5.setHasOffers(r6);
            goto L_0x00aa;
        L_0x00db:
            r7 = com.mcdonalds.app.home.HomeFragment.this;
            r8 = java.lang.Boolean.valueOf(r6);
            com.mcdonalds.app.home.HomeFragment.access$800(r7, r8, r5);
            r7 = com.mcdonalds.app.home.HomeFragment.this;
            r7 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r7});
            r7.setSubscribedToOffers(r5);
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r5.setHasOffers(r6);
            goto L_0x00aa;
        L_0x00f7:
            r7 = com.mcdonalds.app.home.HomeFragment.this;
            r8 = java.lang.Boolean.valueOf(r6);
            com.mcdonalds.app.home.HomeFragment.access$800(r7, r8, r5);
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r7 = r11.mCustomerModule;
            r7 = r7.getCurrentProfile();
            r7 = r7.isSubscribedToOffers();
            r5.setSubscribedToOffers(r7);
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r5.setHasOffers(r6);
            r5 = r11.mHomeListView;
            r6 = com.mcdonalds.app.home.HomeFragment.this;
            r6 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r6});
            r5.setAdapter(r6);
            goto L_0x00aa;
        L_0x0128:
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r7 = r11.mCustomerModule;
            r7 = r7.getCurrentProfile();
            r7 = r7.isSubscribedToOffers();
            r5.setSubscribedToOffers(r7);
            r5 = com.mcdonalds.app.home.HomeFragment.this;
            r5 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r5});
            r5.setHasOffers(r6);
            r5 = r11.mHomeListView;
            r6 = com.mcdonalds.app.home.HomeFragment.this;
            r6 = com.ensighten.Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$200", new java.lang.Object[]{r6});
            r5.setAdapter(r6);
            goto L_0x00aa;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.home.HomeFragment$CustomerOffersListener.onResponse(java.util.List, com.mcdonalds.sdk.AsyncToken, com.mcdonalds.sdk.AsyncException):void");
        }
    }

    private class PromoSlideRunnable extends TimerTask {
        private int mIndex;
        private final ViewPager mViewPager;

        /* renamed from: com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable$1 */
        class C23721 implements Runnable {
            C23721() {
            }

            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable", "access$1500", new Object[]{PromoSlideRunnable.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable", "access$1500", new Object[]{PromoSlideRunnable.this}).setCurrentItem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable", "access$1600", new Object[]{PromoSlideRunnable.this}), true);
                    PromoSlideRunnable.access$1602(PromoSlideRunnable.this, PromoSlideRunnable.access$1604(PromoSlideRunnable.this) % Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable", "access$1500", new Object[]{PromoSlideRunnable.this}).getAdapter().getCount());
                }
            }
        }

        static /* synthetic */ int access$1602(PromoSlideRunnable x0, int x1) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable", "access$1602", new Object[]{x0, new Integer(x1)});
            x0.mIndex = x1;
            return x1;
        }

        static /* synthetic */ int access$1604(PromoSlideRunnable x0) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment$PromoSlideRunnable", "access$1604", new Object[]{x0});
            int i = x0.mIndex + 1;
            x0.mIndex = i;
            return i;
        }

        public PromoSlideRunnable(ViewPager pager) {
            this.mViewPager = pager;
            if (pager != null) {
                this.mIndex = pager.getCurrentItem();
            }
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            new Handler(Looper.getMainLooper()).post(new C23721());
        }
    }

    static /* synthetic */ void access$000(HomeFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$000", new Object[]{x0});
        x0.populateCurrentLocationSubtitle();
    }

    static /* synthetic */ List access$1002(HomeFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1002", new Object[]{x0, x1});
        x0.mPromoList = x1;
        return x1;
    }

    static /* synthetic */ PromoFragmentStatePagerAdapter access$1102(HomeFragment x0, PromoFragmentStatePagerAdapter x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1102", new Object[]{x0, x1});
        x0.mPromoFragmentStatePagerAdapter = x1;
        return x1;
    }

    static /* synthetic */ void access$1300(HomeFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1300", new Object[]{x0});
        x0.startPromoCarouselTimer();
    }

    static /* synthetic */ void access$1900(HomeFragment x0, List x1, List x2, List x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1900", new Object[]{x0, x1, x2, x3});
        x0.updateFilteredOffersAndStores(x1, x2, x3);
    }

    static /* synthetic */ CustomerOrder access$302(HomeFragment x0, CustomerOrder x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$302", new Object[]{x0, x1});
        x0.mLastOrder = x1;
        return x1;
    }

    static /* synthetic */ int access$408(HomeFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$408", new Object[]{x0});
        int i = x0.mHomeListItemCount;
        x0.mHomeListItemCount = i + 1;
        return i;
    }

    static /* synthetic */ void access$800(HomeFragment x0, Boolean x1, boolean x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$800", new Object[]{x0, x1, new Boolean(x2)});
        x0.setRefreshing(x1, x2);
    }

    static /* synthetic */ void access$900(HomeFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$900", new Object[]{x0, new Boolean(x1)});
        x0.refreshHomeAdapter(x1);
    }

    private void refreshHomeAdapter(boolean clearCache) {
        Ensighten.evaluateEvent(this, "refreshHomeAdapter", new Object[]{new Boolean(clearCache)});
        if (this.mHomeListAdapter != null) {
            this.mOffersCount = (int) Configuration.getSharedInstance().getDoubleForKey("interface.dashboard.maxVisibleOffers");
            if (this.mOffersCount == 0) {
                this.mOffersCount = 6;
            }
            this.mHomeListAdapter.removeHomeListItem(this.mResources.getString(C2658R.string.last_order_title));
            if (clearCache) {
                this.mHomeListAdapter.setHasOffers(false);
                this.mHomeListAdapter.clearAppliedOffers();
                this.mHomeListAdapter.clearOffers();
                this.mHomeListView.setAdapter(this.mHomeListAdapter);
            }
            if (this.mCustomerModule != null) {
                if (ModuleManager.isModuleEnabled("ordering").booleanValue()) {
                    this.mCustomerModule.getRecentOrders(this.mCustomerModule.getCurrentProfile(), Integer.valueOf(OrderUtils.getNumberOfRecentOrder()), new C23611());
                } else {
                    populateCurrentLocationSubtitle();
                }
            }
            boolean loggedIn = false;
            if (this.mCustomerModule == null || !this.mCustomerModule.isLoggedIn()) {
                setRefreshing(Boolean.valueOf(false), true);
            } else {
                loggedIn = this.mCustomerModule.isLoggedIn();
                Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
                if (!(currentOrder == null || currentOrder.getOffers() == null || currentOrder.getOffers().size() == 0)) {
                    this.mAddedToOrder = Boolean.valueOf(true);
                }
                refreshOffers();
            }
            this.mHomeListView.setAdapter(this.mHomeListAdapter);
            this.mHomeListAdapter.setLoggedIn(loggedIn);
            this.mHomeListAdapter.notifyDataSetChanged();
        }
    }

    private void setRefreshing(Boolean refreshing, boolean refreshAdapter) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "setRefreshing", new Object[]{refreshing, new Boolean(refreshAdapter)});
        SwipeRefreshLayout swipeRefreshLayout = this.mRefreshLayout;
        if (refreshing.booleanValue()) {
            z = false;
        }
        swipeRefreshLayout.setEnabled(z);
        this.mRefreshLayout.setRefreshing(refreshing.booleanValue());
        if (refreshAdapter) {
            this.mHomeListAdapter.setRefreshing(refreshing);
        }
    }

    private void refreshOffers() {
        Ensighten.evaluateEvent(this, "refreshOffers", null);
        if (this.mCustomerModule.getCurrentProfile() != null) {
            ServiceUtils.getSharedInstance().retrieveFavoriteStores(this.mCustomerModule.getCurrentProfile(), new C23622());
        }
    }

    private void populateCurrentLocationSubtitle() {
        Ensighten.evaluateEvent(this, "populateCurrentLocationSubtitle", null);
        for (HomeListItem item : this.mHomeListAdapter.getHomeListItems()) {
            String currentLocationTitle = getString(C2658R.string.home_link_current_location);
            if (!(item.getTitle() == null || !item.getTitle().equals(currentLocationTitle) || this.mCustomerModule.getCurrentStore() == null)) {
                item.setSubTitle(this.mCustomerModule.getCurrentStore().getStoreFavoriteName() != null ? this.mCustomerModule.getCurrentStore().getStoreFavoriteName() : this.mCustomerModule.getCurrentStore().getAddress1());
            }
        }
    }

    private void loadOfferSections() {
        Ensighten.evaluateEvent(this, "loadOfferSections", null);
        this.mOfferSections = new ArrayList();
        for (LinkedTreeMap map : (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.offerSections")) {
            this.mOfferSections.add(new OfferSection(UIUtils.getStringByName(getActivity(), (String) map.get("sectionTitle")), (String) map.get("sectionType"), ((Boolean) map.get("enabled")).booleanValue()));
        }
    }

    private void preCacheCurrentStoreCatalogIfNeeded() {
        Ensighten.evaluateEvent(this, "preCacheCurrentStoreCatalogIfNeeded", null);
        String homeStoreIdString = null;
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (!(this.mCustomerModule == null || this.mCustomerModule.getCurrentStore() == null)) {
            homeStoreIdString = String.valueOf(this.mCustomerModule.getCurrentStore().getStoreId());
        }
        if (homeStoreIdString != null && ModuleManager.isModuleEnabled("ordering").booleanValue() && ModuleManager.isModuleEnabled(CustomerModule.NAME).booleanValue()) {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            customerModule.getCatalogUpdated(customerModule.getCurrentProfile(), new C23643());
        }
    }

    private void preCacheNutritionInfo() {
        Ensighten.evaluateEvent(this, "preCacheNutritionInfo", null);
        if (ModuleManager.isModuleEnabled("ordering").booleanValue()) {
            ((OrderingModule) ModuleManager.getModule("ordering")).getAllProducts(new C23654());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_home, container, false);
        this.mResources = getResources();
        loadOfferSections();
        preCacheCurrentStoreCatalogIfNeeded();
        preCacheNutritionInfo();
        this.mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(C2358R.C2357id.refresh_layout);
        UIUtils.setDefaultRefreshColors(this.mRefreshLayout);
        this.mOffersCount = (int) Configuration.getSharedInstance().getDoubleForKey("interface.dashboard.maxVisibleOffers");
        if (this.mOffersCount == 0) {
            this.mOffersCount = 6;
        }
        this.mRefreshLayout.setOnRefreshListener(new C23665());
        this.mHomeListView = (ListView) rootView.findViewById(C2358R.C2357id.list_view);
        View headerView = inflater.inflate(C2658R.layout.view_home_list_header, null, false);
        this.mPromoPager = (ViewPager) headerView.findViewById(C2358R.C2357id.header_view_pager);
        this.mPromoList = new ArrayList();
        Object promos = Configuration.getSharedInstance().getValueForKey("interface.dashboard.promos");
        if (promos instanceof String) {
            final Object obj = promos;
            this.mServiceConnection.processRequest(new RequestProvider() {
                public MethodType getMethodType() {
                    Ensighten.evaluateEvent(this, "getMethodType", null);
                    return MethodType.GET;
                }

                public RequestType getRequestType() {
                    Ensighten.evaluateEvent(this, "getRequestType", null);
                    return RequestType.JSON;
                }

                public String getURLString() {
                    Ensighten.evaluateEvent(this, "getURLString", null);
                    return (String) obj;
                }

                public Map<String, String> getHeaders() {
                    Ensighten.evaluateEvent(this, "getHeaders", null);
                    return null;
                }

                public String getBody() {
                    Ensighten.evaluateEvent(this, "getBody", null);
                    return null;
                }

                public void setBody(Object body) {
                    Ensighten.evaluateEvent(this, "setBody", new Object[]{body});
                }

                public Class getResponseClass() {
                    Ensighten.evaluateEvent(this, "getResponseClass", null);
                    return PromoResponse.class;
                }

                public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
                    Ensighten.evaluateEvent(this, "getCustomTypeAdapters", null);
                    return null;
                }

                public String toString() {
                    Ensighten.evaluateEvent(this, "toString", null);
                    return null;
                }
            }, new C23687());
        } else if (promos instanceof List) {
            for (LinkedTreeMap<String, String> stringStringLinkedTreeMap : (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.promos")) {
                this.mPromoList.add(new Promo((String) stringStringLinkedTreeMap.get(NativeProtocol.IMAGE_URL_KEY), null, (String) stringStringLinkedTreeMap.get("itemLink")));
            }
            this.mPromoPager.setAdapter(new PromoFragmentStatePagerAdapter(this.mPromoList, getActivity().getSupportFragmentManager(), null));
            startPromoCarouselTimer();
        }
        final RadioGroup pagerIndicatorGroup = (RadioGroup) headerView.findViewById(C2358R.C2357id.pager_indicator);
        pagerIndicatorGroup.check(C2358R.C2357id.page_indicator_0);
        this.mPromoPager.setOnPageChangeListener(new OnPageChangeListener() {
            public boolean mRestartTimer = false;

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
            }

            public void onPageSelected(int position) {
                Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
                if (HomeFragment.this.getActivity() != null) {
                    pagerIndicatorGroup.check(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$100", new Object[]{HomeFragment.this}).getIdentifier("page_indicator_" + position, "id", HomeFragment.this.getActivity().getPackageName()));
                }
            }

            public void onPageScrollStateChanged(int state) {
                Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
                switch (state) {
                    case 0:
                        if (this.mRestartTimer) {
                            this.mRestartTimer = false;
                            HomeFragment.access$1300(HomeFragment.this);
                            return;
                        }
                        return;
                    case 1:
                        this.mRestartTimer = true;
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeFragment", "access$1400", new Object[]{HomeFragment.this}).cancel();
                        return;
                    default:
                        return;
                }
            }
        });
        this.mHomeListView.addHeaderView(headerView);
        List<Map<String, Object>> items = (List) Configuration.getSharedInstance().getValueForKey("interface.dashboard.items");
        this.mHomeListItemCount = items.size();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mHomeListAdapter = new HomeListAdapter(getActivity(), this);
        if (items != null) {
            for (Map<String, Object> item : items) {
                int iconResourceId = UIUtils.getDrawableIdByName(getActivity(), (String) item.get("itemImage"));
                this.mHomeListAdapter.addHomeListItem(new HomeListItem(iconResourceId, UIUtils.getStringByName(getActivity(), (String) item.get("itemName")), UIUtils.getStringByName(getActivity(), (String) item.get("itemDescription")), (String) item.get("itemLink"), (Map) item.get("itemAttr")));
                this.mHomeListAdapter.notifyDataSetChanged();
            }
        }
        refreshHomeAdapter(false);
        this.mHomeListView.setOnItemClickListener(new C23709());
        return rootView;
    }

    private void startPromoCarouselTimer() {
        Ensighten.evaluateEvent(this, "startPromoCarouselTimer", null);
        this.mPromoCarouselTimer = new Timer();
        this.mPromoCarouselTimer.scheduleAtFixedRate(new PromoSlideRunnable(this.mPromoPager), PayloadController.PAYLOAD_COLLECTOR_TIMEOUT, PayloadController.PAYLOAD_COLLECTOR_TIMEOUT);
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
        super.onResume();
        this.mRecipeFound = false;
        NotificationCenter.getSharedInstance().postNotification("NOTIFICATION_FINISH_TUTORIAL");
        if (this.mAddedToOrder.booleanValue()) {
            if (OrderingManager.getInstance().getCurrentOrder() == null || (OrderingManager.getInstance().getCurrentOrder() != null && OrderingManager.getInstance().getCurrentOrder().getOffers().size() == 0)) {
                this.mAddedToOrder = Boolean.valueOf(false);
            }
            refreshHomeAdapter(true);
        } else if (this.mLocationServiceOn != null && this.mLocationServiceOn.booleanValue() != LocationServicesManager.isLocationEnabled(getContext())) {
            this.mLocationServiceOn = Boolean.valueOf(LocationServicesManager.isLocationEnabled(getContext()));
            refreshHomeAdapter(true);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 || requestCode != 1389) {
            return;
        }
        if (data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                this.mAddedToOrder = Boolean.valueOf(extras.getBoolean("ADDED_TO_ORDER"));
                return;
            }
            return;
        }
        refreshHomeAdapter(true);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onStart() {
        super.onStart();
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule != null) {
            if (customerModule.isLoggedIn()) {
                this.mFirstNameGreeting = customerModule.getCurrentProfile().getFirstName();
                if (this.mFirstNameGreeting == null || this.mFirstNameGreeting.length() == 0) {
                    this.mFirstNameGreeting = customerModule.getCurrentProfile().getUserName();
                }
            } else {
                this.mFirstNameGreeting = null;
            }
        }
        String homeStoreIdString = null;
        if (!(customerModule == null || customerModule.getCurrentStore() == null)) {
            homeStoreIdString = String.valueOf(customerModule.getCurrentStore().getStoreId());
        }
        if (homeStoreIdString == null) {
            startActivity(SelectStoreActivity.class, ChooseSearchMethodFragment.NAME);
            getNavigationActivity().finish();
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mPromoCarouselTimer != null) {
            this.mPromoCarouselTimer.cancel();
        }
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    public void onGridItemClick(Offer offer) {
        Ensighten.evaluateEvent(this, "onGridItemClick", new Object[]{offer});
        commonOnOfferClick(offer, this.mOfferSelectorID == C2358R.C2357id.offers_type_current ? OfferSelection.Current : OfferSelection.Nearby);
    }

    private void commonOnOfferClick(Offer offer, OfferSelection selection) {
        Ensighten.evaluateEvent(this, "commonOnOfferClick", new Object[]{offer, selection});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory(getAnalyticsTitle()).setLabel(offer.getName()).setBusiness(BusinessArgs.getOfferClick(offer)).build());
        Bundle extras = new Bundle();
        extras.putInt("offer_selection_type", selection.ordinal());
        extras.putSerializable(URLNavigationActivity.DATA_PASSER_KEY, offer);
        startActivityForResult(OfferActivity.class, "offer_detail", extras, 1389);
    }

    public void onRegisterClick() {
        Ensighten.evaluateEvent(this, "onRegisterClick", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory("/home").setLabel("Start Registration").build());
        startActivity(TermsOfServiceActivity.class, JiceArgs.EVENT_REGISTER);
    }

    public void onSignInClick() {
        Ensighten.evaluateEvent(this, "onSignInClick", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory("/home").setLabel("Sign In").build());
        startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN);
    }

    public void onOffersTypeSelectorChanged(RadioGroup offersSelector) {
        Ensighten.evaluateEvent(this, "onOffersTypeSelectorChanged", new Object[]{offersSelector});
        this.mOfferSelectorID = offersSelector.getCheckedRadioButtonId();
        this.mHomeListAdapter.clearOffers();
        updateOffersDisplay();
    }

    public void onRemoveFromBasketClicked(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "onRemoveFromBasketClicked", new Object[]{orderOffer});
        OrderingManager.getInstance().getCurrentOrder().removeOffer(orderOffer);
        this.mAddedToOrder = Boolean.valueOf(false);
        refreshHomeAdapter(true);
    }

    private void updateFilteredOffersAndStores(List<Offer> offerList, List<Store> nearbyStores, List<Store> favoriteStores) {
        Ensighten.evaluateEvent(this, "updateFilteredOffersAndStores", new Object[]{offerList, nearbyStores, favoriteStores});
        this.mFilteredOffers = offerList;
        this.mNearbyStores = nearbyStores;
        this.mFavoriteStores = favoriteStores;
        updateOffersDisplay();
    }

    private void updateOffersDisplay() {
        Ensighten.evaluateEvent(this, "updateOffersDisplay", null);
        boolean nearbyEnabled = this.mNearbyStores != null;
        if (!nearbyEnabled && this.mOfferSelectorID == C2358R.C2357id.offers_type_near_you) {
            this.mOfferSelectorID = C2358R.C2357id.offers_type_current;
            this.mHomeListAdapter.setOffersTypeSelectorSelected(this.mOfferSelectorID);
        }
        this.mLocationServiceOn = Boolean.valueOf(LocationServicesManager.isLocationEnabled(getContext()));
        this.mHomeListAdapter.setLocationDisabled(!this.mLocationServiceOn.booleanValue());
        this.mOffersCount = (int) Configuration.getSharedInstance().getDoubleForKey("interface.dashboard.maxVisibleOffers");
        if (this.mOffersCount == 0) {
            this.mOffersCount = 6;
        }
        this.mHomeListAdapter.clearOffers();
        this.mHomeListAdapter.addSectionHeader(new MCDListSectionHeaderModel(getString(C2658R.string.title_your_offers), C2358R.C2359drawable.ic_offer, true));
        this.mHomeListAdapter.addOffersTypeSelector();
        List<Offer> offerList = new ArrayList();
        offerList.addAll(this.mFilteredOffers);
        Iterator it;
        if (this.mOfferSelectorID != C2358R.C2357id.offers_type_near_you) {
            List<Offer> currentOffers = new ArrayList();
            List<Offer> favoriteOffers = new ArrayList();
            currentOffers.addAll(offersInStore(Arrays.asList(new Store[]{this.mCustomerModule.getCurrentStore()}), offerList));
            offerList.removeAll(currentOffers);
            int count = 0 + currentOffers.size();
            if (count < 20 && this.mFavoriteStores != null) {
                it = this.mFavoriteStores.iterator();
                while (it.hasNext()) {
                    List<Offer> offersForStore = offersInStore(Arrays.asList(new Store[]{(Store) it.next()}), offerList);
                    offerList.removeAll(offersForStore);
                    favoriteOffers.addAll(offersForStore);
                    count += offersForStore.size();
                    if (count >= 20) {
                        break;
                    }
                }
            }
            this.mHomeListAdapter.addOffersGridSection(currentOffers);
            if (favoriteOffers.size() > 0) {
                this.mHomeListAdapter.addOffersListSection(new MCDListSectionHeaderModel(getString(C2658R.string.offers_subsection_at_favorite), 0, false), favoriteOffers);
            }
        } else if (nearbyEnabled) {
            List<Offer> nearbyOffers = offersInStore(this.mNearbyStores, offerList);
            offerList.removeAll(nearbyOffers);
            List<Offer> otherOffers = new ArrayList();
            if (this.mOffersCount > 0) {
                for (Offer offer : offerList) {
                    otherOffers.add(offer);
                    this.mOffersCount--;
                    if (this.mOffersCount == 0) {
                        break;
                    }
                }
            }
            this.mHomeListAdapter.addOffersGridSection(nearbyOffers);
            if (otherOffers.size() > 0) {
                this.mHomeListAdapter.addOffersListSection(new MCDListSectionHeaderModel(getString(C2658R.string.offers_subsection_further_away), 0, false), otherOffers);
            }
        }
        setRefreshing(Boolean.valueOf(false), true);
        this.mHomeListAdapter.setSubscribedToOffers(true);
        this.mHomeListAdapter.setLoggedIn(this.mCustomerModule.isLoggedIn());
        this.mHomeListAdapter.setHasOffers(true);
        this.mHomeListView.setAdapter(this.mHomeListAdapter);
        this.mHomeListAdapter.notifyDataSetChanged();
    }

    private List<Offer> offersInStore(List<? extends Store> stores, List<Offer> offers) {
        Ensighten.evaluateEvent(this, "offersInStore", new Object[]{stores, offers});
        List<Offer> offersInStores = new ArrayList();
        if (!(stores == null || offers == null)) {
            for (Offer offer : offers) {
                for (Store store : stores) {
                    List<Store> storesWithOffer = new ArrayList();
                    if (offer.getRestaurants() != null) {
                        for (Integer restaurantId : offer.getRestaurants()) {
                            if (this.mOffersCount > 0 && restaurantId.equals(Integer.valueOf(store.getStoreId()))) {
                                storesWithOffer.add(store);
                                break;
                            }
                        }
                    } else {
                        storesWithOffer.add(store);
                    }
                    if (storesWithOffer.size() > 0) {
                        offersInStores.add(offer);
                        this.mOffersCount--;
                        break;
                    }
                }
            }
        }
        return offersInStores;
    }
}
