package com.mcdonalds.app.ordering.basket;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.checkout.CheckoutActivity;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.ordering.start.BasketSignInHolder;
import com.mcdonalds.app.ordering.start.DeliveryTimeHolder;
import com.mcdonalds.app.ordering.start.PickupLocationHolder;
import com.mcdonalds.app.ordering.upsell.UpsellActivity;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderOfferUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.provider.Contract.CurrentStore;
import com.mcdonalds.sdk.services.data.repository.StoreProductCategoryRepository;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class BasketFragment extends URLNavigationFragment implements OnClickListener, BasketItemActionListener {
    private static final Uri OBSERVED_URI = CurrentStore.CONTENT_URI;
    public static String PARAMETER_PROBLEMATIC_OFFERS_CODES = "problematic_offers_codes";
    public static String PARAMETER_PROBLEMATIC_PRODUCTS_CODES = "problematic_products_codes";
    public static String PARAMETER_PRODUCT_ERROR_CODE = "product_error_code";
    private double deliveryFee;
    private BasketFooter mBasketFooterHolder;
    private boolean mBasketHasErrors;
    private BasketSignInHolder mBasketSignInHolder;
    ContentObserver mContentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange, Uri uri) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange), uri});
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$000", new Object[]{BasketFragment.this}) && uri != null && uri.equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$100", null))) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).setNeedsUpdatedRecipes(true);
                BasketFragment.access$302(BasketFragment.this, true);
                BasketFragment.this.refreshStoreInfoAndDeliveryFee();
            }
        }
    };
    private CustomerModule mCustomerModule;
    private DeliveryModule mDeliveryModule;
    private DeliveryTimeHolder mDeliveryTimeHolder;
    private boolean mHeaderIsDelivery = false;
    private Boolean mIsEditMode = Boolean.valueOf(false);
    private boolean mIsRefreshing;
    private ListView mListView;
    private BasketListAdapter mListViewAdapter;
    private boolean mLoggedIn;
    private Menu mMenu;
    private TextView mMenuEndingTextView;
    private List<String> mNonProductOfferNames = new ArrayList();
    private Order mOrder;
    private Order mOrderBeforeStoreChange;
    final Comparator<OrderOffer> mOrderOffersComparator = new C34178();
    private OrderingModule mOrderingModule;
    private Order mOriginalOrder;
    private PickupLocationHolder mPickupHeaderHolder;
    private RequestManagerServiceConnection mServiceConnection;
    private boolean mSkipBasketEditSaveFlow = false;
    private AsyncListener<Boolean> mTotalizeListener = new C340617();
    private List<String> mUnavailableProductCodes = new ArrayList();
    private boolean mUpdatingProducts;
    private AsyncListener<List<Product>> mUpsellListener = new C340415();
    private boolean updatingStore;

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$10 */
    class C339910 implements DialogInterface.OnClickListener {
        C339910() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$11 */
    class C340011 implements DialogInterface.OnClickListener {
        C340011() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$12 */
    class C340112 implements DialogInterface.OnClickListener {
        C340112() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$13 */
    class C340213 implements DialogInterface.OnClickListener {
        C340213() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            BasketFragment.access$1500(BasketFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$14 */
    class C340314 implements DialogInterface.OnClickListener {
        C340314() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$15 */
    class C340415 implements AsyncListener<List<Product>> {
        C340415() {
        }

        public void onResponse(List<Product> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Bundle extras = new Bundle();
            DataPasser.getInstance().putData("param_products", new ArrayList(response));
            BasketFragment.this.startActivityForResult(UpsellActivity.class, extras, 154);
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$17 */
    class C340617 implements AsyncListener<Boolean> {
        C340617() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            BasketFragment.this.refresh();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$1 */
    class C34071 extends BroadcastReceiver {
        C34071() {
        }

        public void onReceive(Context context, Intent intent) {
            Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
            if (OrderingManager.getInstance().getCurrentOrder().isEmpty()) {
                BasketFragment.this.resetBasketForEmptyOrder();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$3 */
    class C34093 implements AsyncListener<OrderResponse> {
        C34093() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && response != null) {
                BasketFragment.access$402(BasketFragment.this, response.getDeliveryFee().doubleValue());
            }
            UIUtils.stopActivityIndicator();
            if (BasketFragment.this.isAdded()) {
                BasketFragment.access$500(BasketFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$5 */
    class C34125 implements AsyncListener<Object> {

        /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$5$1 */
        class C34111 implements AsyncListener<Void> {
            C34111() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                BasketFragment.access$002(BasketFragment.this, false);
                if (exception == null) {
                    BasketFragment.access$802(BasketFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getUnavailableProductCodes());
                    if (!ListUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$800", new Object[]{BasketFragment.this}))) {
                        BasketFragment.access$902(BasketFragment.this, true);
                    }
                    if (ListUtils.isNotEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getOffers())) {
                        BasketFragment.access$1000(BasketFragment.this);
                        return;
                    } else {
                        BasketFragment.access$1100(BasketFragment.this);
                        return;
                    }
                }
                BasketFragment.access$902(BasketFragment.this, true);
                UIUtils.stopActivityIndicator();
                AsyncException.report(exception);
            }
        }

        C34125() {
        }

        public void onResponse(Object response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (BasketFragment.this.getNavigationActivity() != null) {
                OrderManager.updateProductsForOrder(new C34111());
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$6 */
    class C34136 implements AsyncListener<List<OrderProduct>> {
        C34136() {
        }

        public void onResponse(List<OrderProduct> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                BasketFragment.access$802(BasketFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getUnavailableProductCodes());
                if (!ListUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$800", new Object[]{BasketFragment.this}))) {
                    BasketFragment.access$902(BasketFragment.this, true);
                }
            } else {
                BasketFragment.access$902(BasketFragment.this, true);
            }
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$900", new Object[]{BasketFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1200", new Object[]{BasketFragment.this}).booleanValue() && !Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).isEmpty()) {
                BasketFragment.this.productEdited();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$7 */
    class C34167 implements AsyncListener<List<Offer>> {

        /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$7$1 */
        class C34141 implements AsyncListener<List<Void>> {
            C34141() {
            }

            public void onResponse(List<Void> response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (BasketFragment.this.getNavigationActivity() != null) {
                    BasketFragment.access$1100(BasketFragment.this);
                }
            }
        }

        C34167() {
        }

        public void onResponse(List<Offer> offerList, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{offerList, token, exception});
            if (exception == null) {
                ArrayList<OrderOffer> list = new ArrayList();
                list.addAll(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getOffers());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).clearOffers();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    OrderOffer offerLocal = (OrderOffer) it.next();
                    for (Offer offer : offerList) {
                        if (offerLocal.getOffer().getOfferId().equals(offer.getOfferId())) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).addOffer(offerLocal);
                            break;
                        }
                    }
                }
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getOffers().size() > 0) {
                    BasketFragment.access$1300(BasketFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}));
                    final AsyncCounter<Void> orderOfferCounter = new AsyncCounter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getOffers().size(), new C34141());
                    for (OrderOffer orderOfferLocal : Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$200", new Object[]{BasketFragment.this}).getOffers()) {
                        orderOfferLocal.revalidate(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1400", new Object[]{BasketFragment.this}), new AsyncListener<Void>() {
                            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                orderOfferCounter.success(null);
                            }
                        });
                    }
                    return;
                }
                UIUtils.stopActivityIndicator();
                return;
            }
            AsyncException.report(exception);
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$8 */
    class C34178 implements Comparator<OrderOffer> {
        C34178() {
        }

        public int compare(OrderOffer offer1, OrderOffer offer2) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{offer1, offer2});
            if (offer1.getOffer().isPunchCard() && !offer2.getOffer().isPunchCard()) {
                return -1;
            }
            if (offer1.getOffer().isPunchCard() || !offer2.getOffer().isPunchCard()) {
                return offer1.getOffer().getLocalValidFrom().compareTo(offer2.getOffer().getLocalValidFrom());
            }
            return 1;
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.basket.BasketFragment$9 */
    class C34189 implements DialogInterface.OnClickListener {
        C34189() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    static /* synthetic */ boolean access$002(BasketFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mUpdatingProducts = x1;
        return x1;
    }

    static /* synthetic */ void access$1000(BasketFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1000", new Object[]{x0});
        x0.updateOffers();
    }

    static /* synthetic */ void access$1100(BasketFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1100", new Object[]{x0});
        x0.hideProgress();
    }

    static /* synthetic */ void access$1300(BasketFragment x0, Order x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1300", new Object[]{x0, x1});
        x0.processOffers(x1);
    }

    static /* synthetic */ void access$1500(BasketFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1500", new Object[]{x0});
        x0.deleteOrder();
    }

    static /* synthetic */ void access$1600(BasketFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$1600", new Object[]{x0});
        x0.updateHeader();
    }

    static /* synthetic */ boolean access$302(BasketFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.updatingStore = x1;
        return x1;
    }

    static /* synthetic */ double access$402(BasketFragment x0, double x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$402", new Object[]{x0, new Double(x1)});
        x0.deliveryFee = x1;
        return x1;
    }

    static /* synthetic */ void access$500(BasketFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$500", new Object[]{x0});
        x0.retrieveStoreInfoAndDeliveryFee();
    }

    static /* synthetic */ void access$600(BasketFragment x0, CustomerAddress x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$600", new Object[]{x0, x1, x2});
        x0.setDeliveryHeader(x1, x2);
    }

    static /* synthetic */ void access$700(BasketFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$700", new Object[]{x0});
        x0.refreshRecipeDataIfNecessary();
    }

    static /* synthetic */ List access$802(BasketFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$802", new Object[]{x0, x1});
        x0.mUnavailableProductCodes = x1;
        return x1;
    }

    static /* synthetic */ boolean access$902(BasketFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketFragment", "access$902", new Object[]{x0, new Boolean(x1)});
        x0.mBasketHasErrors = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        if (this.mIsEditMode.booleanValue()) {
            return getString(C2658R.string.analytics_screen_basket_edit);
        }
        return getString(C2658R.string.analytics_screen_basket);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        OrderManager orderManager = OrderManager.getInstance();
        orderManager.cleanBagsFromOrder();
        this.mOrder = orderManager.getCurrentOrder();
        this.mSkipBasketEditSaveFlow = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.skipBasketEditSaveFlow");
        this.mLoggedIn = true;
        this.updatingStore = false;
        getContext().getContentResolver().registerContentObserver(OBSERVED_URI, false, this.mContentObserver);
        NotificationCenter.getSharedInstance().addObserver(ModuleManager.ORDER_CHANGED_NOTIFICATION, new C34071());
    }

    public void onDestroy() {
        super.onDestroy();
        getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle(), C2658R.string.close);
        View rootView = inflater.inflate(C2658R.layout.fragment_basket, container, false);
        this.mPickupHeaderHolder = new PickupLocationHolder(inflater.inflate(C2658R.layout.pickup_location, null));
        this.mPickupHeaderHolder.getPickupStoreView().setOnClickListener(this);
        this.mPickupHeaderHolder.getContainer().findViewById(C2358R.C2357id.pickup_store_title).setVisibility(8);
        this.mDeliveryTimeHolder = new DeliveryTimeHolder(inflater.inflate(C2658R.layout.delivery_location, null));
        this.mDeliveryTimeHolder.getDeliveryTimeView().setOnClickListener(this);
        DataLayerClickListener.setDataLayerTag(this.mPickupHeaderHolder.getPickupStoreView(), "OrderDeliveryItemPressed");
        DataLayerClickListener.setDataLayerTag(this.mDeliveryTimeHolder.getDeliveryTimeView(), "OrderDeliveryItemPressed");
        this.mListView = (ListView) rootView.findViewById(C2358R.C2357id.basket_list);
        this.mListView.setDivider(null);
        this.mListView.setDividerHeight(0);
        this.mListViewAdapter = new BasketListAdapter(getNavigationActivity(), this, this.mOrder);
        this.mBasketFooterHolder = new BasketFooter(rootView.findViewById(C2358R.C2357id.basket_footer_include));
        this.mBasketFooterHolder.getAddToBasketButton().setOnClickListener(this);
        this.mBasketFooterHolder.getDeleteOrderButton().setOnClickListener(this);
        this.mBasketSignInHolder = new BasketSignInHolder(rootView.findViewById(C2358R.C2357id.basket_sign_in), getNavigationActivity());
        if (ModuleManager.getSharedInstance().isNutritionAvailable()) {
            this.mBasketFooterHolder.getCaloriesWarningView().setVisibility(0);
        }
        this.mMenuEndingTextView = (TextView) rootView.findViewById(C2358R.C2357id.menu_ending_text);
        return rootView;
    }

    public void onResume() {
        super.onResume();
        if (this.mIsEditMode.booleanValue()) {
            getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle());
        } else {
            getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle(), C2658R.string.close);
        }
        updateBasketSignInHolder();
        if (this.mUpdatingProducts) {
            UIUtils.stopActivityIndicator();
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.refresh_product_for_store);
        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateOrder();
        if (this.mOrder.isEmpty()) {
            getActivity().setResult(39);
            getActivity().finish();
        }
        if (this.mHeaderIsDelivery) {
            this.mListView.removeHeaderView(this.mDeliveryTimeHolder.getContainer());
        } else {
            this.mListView.removeHeaderView(this.mPickupHeaderHolder.getContainer());
        }
        this.mHeaderIsDelivery = OrderingManager.getInstance().getCurrentOrder().isDelivery();
        this.mListView.setAdapter(null);
        this.mListView.addHeaderView(this.mHeaderIsDelivery ? this.mDeliveryTimeHolder.getContainer() : this.mPickupHeaderHolder.getContainer());
        this.mListView.setAdapter(this.mListViewAdapter);
        if (!this.mOrder.isEmpty()) {
            this.mUnavailableProductCodes = new ArrayList();
            this.mBasketHasErrors = false;
            refreshStoreInfoAndDeliveryFee();
        }
        totalize();
    }

    private void updateBasketSignInHolder() {
        Ensighten.evaluateEvent(this, "updateBasketSignInHolder", null);
        this.mLoggedIn = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).isLoggedIn();
        BasketSignInHolder basketSignInHolder = this.mBasketSignInHolder;
        boolean z = (this.mLoggedIn || this.mIsEditMode.booleanValue()) ? false : true;
        basketSignInHolder.setVisible(z);
    }

    public void refreshStoreInfoAndDeliveryFee() {
        Ensighten.evaluateEvent(this, "refreshStoreInfoAndDeliveryFee", null);
        if (this.mCustomerModule == null || this.mDeliveryModule == null || this.mOrderingModule == null) {
            this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            this.mDeliveryModule = (DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME);
            this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        }
        if (!this.mHeaderIsDelivery) {
            retrieveStoreInfoAndDeliveryFee();
        } else if (getActivity() != null) {
            UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.sl_retrieve_stores));
            this.mDeliveryModule.lookupDeliveryCharge(this.mOrder, new C34093());
        }
    }

    private void retrieveStoreInfoAndDeliveryFee() {
        Ensighten.evaluateEvent(this, "retrieveStoreInfoAndDeliveryFee", null);
        Store store = OrderManager.getInstance().getCurrentStore();
        this.mMenuEndingTextView.setVisibility(8);
        if (this.mOrderingModule.getMenuTypes() != null) {
            String alertTimeString = (String) Configuration.getSharedInstance().getValueForKey("interface.dayparts.daypartEndingAlertTime");
            if (alertTimeString != null) {
                long alertTime = Long.valueOf(alertTimeString).longValue();
                for (MenuType menuType : this.mOrderingModule.getMenuTypes()) {
                    if (store != null) {
                        long timeLeftInMenu = store.getMenuEndingTime(menuType, isDelivery());
                        if (timeLeftInMenu > 0 && timeLeftInMenu <= alertTime) {
                            this.mMenuEndingTextView.setText(getString(C2658R.string.label_daypart_menu_warning_ios, menuType.getShortName(), Long.valueOf(timeLeftInMenu)));
                            this.mMenuEndingTextView.setVisibility(0);
                        }
                    }
                }
            }
        }
        if (this.mHeaderIsDelivery) {
            final Order deliveryOrder = OrderingManager.getInstance().getCurrentOrder();
            this.mDeliveryTimeHolder.getDeliveryFee().setText(getString(C2658R.string.unknown));
            if (deliveryOrder.getDeliveryDate() != null) {
                if (deliveryOrder.isNormalOrder() && Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
                    setDeliveryHeader(deliveryOrder.getDeliveryAddress(), LocalDataManager.getSharedInstance().getEdtString());
                } else {
                    setDeliveryHeader(deliveryOrder.getDeliveryAddress(), UIUtils.formatScheduledDeliveryTime(deliveryOrder.getDeliveryDate()));
                }
                refreshRecipeDataIfNecessary();
                return;
            } else if (deliveryOrder.getDeliveryAddress() != null) {
                UIUtils.startActivityIndicator(getNavigationActivity(), "Retrieving delivery information");
                this.mDeliveryModule.getDeliveryStore(deliveryOrder.getDeliveryAddress(), deliveryOrder.getDeliveryDate(), this.mCustomerModule.getCurrentProfile(), new AsyncListener<Store>() {
                    public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (!(BasketFragment.this.getNavigationActivity() == null || response == null)) {
                            Date deliveryDateFromStore = UIUtils.getDateFromISO8601(response.getExpectedDeliveryTime());
                            if (deliveryOrder.isNormalOrder()) {
                                BasketFragment.access$600(BasketFragment.this, deliveryOrder.getDeliveryAddress(), UIUtils.formatDeliveryTimeInMinutes(BasketFragment.this.getNavigationActivity(), response.getCurrentDate(), deliveryDateFromStore));
                            } else {
                                BasketFragment.access$600(BasketFragment.this, deliveryOrder.getDeliveryAddress(), UIUtils.formatScheduledDeliveryTime(deliveryDateFromStore));
                            }
                        }
                        UIUtils.stopActivityIndicator();
                        BasketFragment.access$700(BasketFragment.this);
                    }
                });
                return;
            } else {
                return;
            }
        }
        if (store != null) {
            String name;
            if (store.getStoreFavoriteName() != null) {
                name = store.getStoreFavoriteName();
            } else {
                name = store.getAddress1();
            }
            this.mPickupHeaderHolder.getStoreName().setText(Html.fromHtml(getString(C2658R.string.order_location_pickup_title) + "<br/><b>" + name + "</b>"));
        } else {
            this.mPickupHeaderHolder.getStoreName().setText(Html.fromHtml(getString(C2658R.string.order_location_pickup_title) + "<br/><b>" + C2658R.string.tap_to_choose_pickup_location + "</b>"));
        }
        refreshRecipeDataIfNecessary();
    }

    private void setDeliveryHeader(CustomerAddress customerAddress, String deliveryTime) {
        Ensighten.evaluateEvent(this, "setDeliveryHeader", new Object[]{customerAddress, deliveryTime});
        if (customerAddress != null) {
            String headerString = " ";
            if (this.mOrder.isNormalOrder()) {
                Order deliveryOrder = OrderingManager.getInstance().getCurrentOrder();
                if (deliveryOrder != null) {
                    String time = setAsapDeliveryDate(deliveryOrder.getDeliveryStore());
                    headerString = getString(C2658R.string.estimated_delivery_range_5, "<b>" + time + "</b>", "<b>" + customerAddress.getFullAddress() + "</b>");
                } else {
                    headerString = getString(C2658R.string.estimated_delivery_range_4, "<b>" + customerAddress.getFullAddress() + "</b>");
                }
            } else {
                headerString = getString(C2658R.string.estimated_delivery_range_2, "<b>" + deliveryTime + "</b>", "<b>" + customerAddress.getFullAddress() + "</b>");
            }
            this.mDeliveryTimeHolder.setDeliveryHeaderText(Html.fromHtml(headerString));
        }
    }

    private String setAsapDeliveryDate(Store store) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryDate", new Object[]{store});
        String time = getString(C2658R.string.edt_minutes, Integer.valueOf(30));
        if (store == null) {
            return time;
        }
        Date nowInStoreTime = UIUtils.getDateFromISO8601(store.getNowInStoreLocalTime(), TimeZone.getDefault());
        Date edtInStoreTime = UIUtils.getDateFromISO8601(store.getExpectedDeliveryTime());
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
            return UIUtils.formatDeliveryTimeInMinutes(getActivity(), nowInStoreTime, edtInStoreTime);
        }
        return time;
    }

    private void refreshRecipeDataIfNecessary() {
        Ensighten.evaluateEvent(this, "refreshRecipeDataIfNecessary", null);
        if (this.mOrder != null && !this.mIsRefreshing) {
            if (!this.mOrder.needsUpdatedRecipes() || ((this.mOrder.getProducts() == null || this.mOrder.getProducts().isEmpty()) && (this.mOrder.getOffers() == null || this.mOrder.getOffers().isEmpty()))) {
                refresh();
                return;
            }
            this.mIsRefreshing = true;
            if (!(this.mOrder.getStoreId() == null || this.mOrderBeforeStoreChange == null || this.mOrderBeforeStoreChange.getStoreId() == null || this.mOrder.getStoreId().equalsIgnoreCase(this.mOrderBeforeStoreChange.getStoreId()))) {
                OrderUtils.clearTotalizeResponses(this.mOrder);
            }
            if (this.mOrderBeforeStoreChange == null || this.mOrderBeforeStoreChange.getStoreId() == null || !this.mOrderBeforeStoreChange.getStoreId().equals(this.mOrder.getStoreId())) {
                this.mOrderBeforeStoreChange = Order.cloneOrderForEditing(this.mOrder);
            }
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.refresh_product_for_store);
            this.mUpdatingProducts = true;
            this.mCustomerModule.getCatalogUpdated(this.mCustomerModule.getCurrentProfile(), new C34125());
        }
    }

    private void updateProducts() {
        Ensighten.evaluateEvent(this, "updateProducts", null);
        this.mUnavailableProductCodes = new ArrayList();
        this.mBasketHasErrors = false;
        OrderManager.updateProducts(this.mOrder, new AsyncCounter(this.mOrder.getProducts().size(), new C34136()));
    }

    private void updateOffers() {
        Ensighten.evaluateEvent(this, "updateOffers", null);
        CustomerProfile profile = this.mCustomerModule.getCurrentProfile();
        ServiceUtils.getSharedInstance().retrieveOffers(profile == null ? "" : profile.getUserName(), String.valueOf(OrderManager.getInstance().getCurrentStore().getStoreId()), null, null, new C34167());
    }

    private void hideProgress() {
        Ensighten.evaluateEvent(this, "hideProgress", null);
        refresh();
        this.mListViewAdapter.notifyDataSetChanged();
        this.mOrder.setNeedsUpdatedRecipes(false);
        UIUtils.stopActivityIndicator();
        this.mIsRefreshing = false;
    }

    private void processOffers(Order order) {
        Ensighten.evaluateEvent(this, "processOffers", new Object[]{order});
        ArrayList<OrderOffer> list = new ArrayList();
        list.addAll(order.getOffers());
        order.clearOffers();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OrderOffer orderOffer = (OrderOffer) it.next();
            if (!(orderOffer.getOffer().isArchived().booleanValue() || orderOffer.getOffer().isExpired().booleanValue())) {
                order.addOffer(orderOffer);
            }
        }
        List<OrderOffer> filter = new ArrayList();
        filter.addAll(order.getOffers());
        Collections.sort(filter, this.mOrderOffersComparator);
        order.clearOffers();
        for (OrderOffer o : filter) {
            order.addOffer(o);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mServiceConnection = RequestManager.register(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        RequestManager.unregister(getActivity(), this.mServiceConnection);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.basket_menu, menu);
        this.mMenu = menu;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_edit_done /*2131821896*/:
                closeEdit(item);
                saveChangesToBasket();
                return true;
            case C2358R.C2357id.action_edit /*2131821897*/:
                getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle());
                AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Edit Basket");
                this.mOriginalOrder = Order.cloneOrderForEditing(OrderingManager.getInstance().getCurrentOrder());
                prepareEditMode();
                item.setVisible(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void closeEdit(MenuItem item) {
        Ensighten.evaluateEvent(this, "closeEdit", new Object[]{item});
        this.mIsEditMode = Boolean.valueOf(false);
        if (item != null) {
            item.setVisible(false);
        } else {
            MenuItem menuItem = this.mMenu.findItem(C2358R.C2357id.action_edit_done);
            if (menuItem != null) {
                menuItem.setVisible(false);
            }
        }
        this.mMenu.findItem(C2358R.C2357id.action_edit).setVisible(true);
        getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle(), C2658R.string.close);
    }

    public void editDone() {
        Ensighten.evaluateEvent(this, "editDone", null);
        getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle(), C2658R.string.close);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Cancel");
        if (!this.mSkipBasketEditSaveFlow) {
            this.mOrder = this.mOriginalOrder;
            this.mListViewAdapter.clear();
            this.mListViewAdapter.addAll(createBasketItems());
        }
        this.mOriginalOrder = null;
        OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        this.mListViewAdapter.setIsEditMode(Boolean.valueOf(false));
        updateFooter();
        if (!this.mLoggedIn) {
            this.mBasketSignInHolder.setVisible(true);
        }
        if (this.mOrder.isEmpty()) {
            getActivity().setResult(39);
            getActivity().finish();
        }
    }

    public void productEdited() {
        Ensighten.evaluateEvent(this, "productEdited", null);
        this.mListViewAdapter.productEdited();
    }

    public void prepareEditMode() {
        Ensighten.evaluateEvent(this, "prepareEditMode", null);
        this.mListViewAdapter.updateOrder(this.mOrder);
        this.mIsEditMode = Boolean.valueOf(true);
        this.mListViewAdapter.setIsEditMode(Boolean.valueOf(true));
        updateFooter();
        this.mMenu.findItem(C2358R.C2357id.action_edit_done).setVisible(true);
        this.mMenu.findItem(C2358R.C2357id.action_edit).setVisible(false);
        if (!this.mLoggedIn) {
            this.mBasketSignInHolder.setVisible(false);
        }
    }

    public void removeUnavailableItemsComplete() {
        Ensighten.evaluateEvent(this, "removeUnavailableItemsComplete", null);
        this.mUnavailableProductCodes = new ArrayList();
        this.mBasketHasErrors = false;
        refresh();
        preparePayment(true);
    }

    public void resetBasketForEmptyOrder() {
        Ensighten.evaluateEvent(this, "resetBasketForEmptyOrder", null);
        this.mOrderBeforeStoreChange = null;
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (view == this.mBasketFooterHolder.getAddToBasketButton()) {
            if (this.mIsEditMode.booleanValue()) {
                saveChangesToBasket();
            } else if (this.mBasketHasErrors) {
                Bundle bundle = new Bundle();
                bundle.putInt(PARAMETER_PRODUCT_ERROR_CODE, OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE);
                bundle.putStringArrayList(PARAMETER_PROBLEMATIC_PRODUCTS_CODES, (ArrayList) this.mUnavailableProductCodes);
                if (OrderUtils.getErrorCount(this.mUnavailableProductCodes, this.mOrder) >= this.mOrder.getTotalOrderCount()) {
                    startActivityForResult(AlertActivity.class, "check_in_all_items_unavailable", bundle, 12730);
                } else {
                    startActivityForResult(AlertActivity.class, "check_in_items_unavailable", bundle, 12730);
                }
            } else {
                AnalyticsUtils.trackCheckout(getAnalyticsTitle(), this.mOrder, OrderManager.getInstance().getCurrentStore());
                for (OrderProduct product : this.mOrder.getProducts()) {
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setBusiness(BusinessArgs.getProductFromBasket(product)).build());
                }
                if (this.mOrder.getTotalValue() > ((double) Configuration.getSharedInstance().getIntForKey("interface.ordering.orderMaximumAmount")) && this.mOrder.isDelivery()) {
                    MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.large_order_alert).setMessage(getResources().getString(C2658R.string.please_call_24_hours)).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C34189()).create().show();
                    return;
                } else if (this.mOrder.getBasketCounter() >= this.mCustomerModule.getMaxItemQuantity()) {
                    MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C339910()).create().show();
                    return;
                } else {
                    for (OrderOffer orderOffer : this.mOrder.getOffers()) {
                        String message = null;
                        if (!isOfferAvailable(orderOffer)) {
                            message = getString(C2658R.string.ecp_error_8015);
                            continue;
                        } else if (this.mOrder.isDelivery() && !orderOffer.getOffer().isDeliveryOffer()) {
                            message = getString(C2658R.string.offer_unavailable_for_pod_short, getString(C2658R.string.delivery));
                            continue;
                        } else if (!(this.mOrder.isDelivery() || orderOffer.getOffer().isPickupOffer())) {
                            message = getString(C2658R.string.offer_unavailable_for_pod_short, getString(C2658R.string.pickup));
                            continue;
                        }
                        if (message != null) {
                            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(message).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C340011()).create().show();
                            return;
                        }
                    }
                    int numOffersExcludeType10 = 0;
                    for (OrderOffer orderOffer2 : this.mOrder.getOffers()) {
                        if (orderOffer2.getOffer().getOfferType() != OfferType.OFFER_TYPE_DELIVERY_FEE) {
                            numOffersExcludeType10++;
                        }
                    }
                    if (this.mOrder.getProducts().size() + numOffersExcludeType10 <= 0) {
                        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.label_no_items).setMessage((int) C2658R.string.error_ordering_add_items).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C340112()).create().show();
                        return;
                    }
                    checkDayPartAndPreparePayment();
                }
            }
        }
        if (view == this.mBasketFooterHolder.getDeleteOrderButton()) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.delete_basket).setMessage((int) C2658R.string.are_you_sure_delete_basket).setPositiveButton((int) C2658R.string.start_again, new C340213()).setNegativeButton(getResources().getString(C2658R.string.cancel), null).setCancelable(true).create().show();
        }
        if (view == this.mPickupHeaderHolder.getPickupStoreView() || view == this.mDeliveryTimeHolder.getDeliveryTimeView()) {
            this.mOrderBeforeStoreChange = Order.cloneOrderForEditing(OrderingManager.getInstance().getCurrentOrder());
            this.mOrder.setNeedsUpdatedRecipes(true);
            this.updatingStore = true;
            startActivityForResult(OrderMethodSelectionActivity.class, 40);
            HashMap<String, Object> jiceMap = new HashMap();
            if (this.mOrder.isDelivery()) {
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_BASKET_ADDRESS_DELIVERY);
            } else {
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_BASKET_ADDRESS_PICKUP);
            }
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        }
    }

    public void saveChangesToBasket() {
        Ensighten.evaluateEvent(this, "saveChangesToBasket", null);
        MenuItem cancelItem = this.mMenu.findItem(C2358R.C2357id.action_edit_done);
        MenuItem editItem = this.mMenu.findItem(C2358R.C2357id.action_edit);
        this.mIsEditMode = Boolean.valueOf(false);
        this.mOriginalOrder = null;
        this.mOrderBeforeStoreChange = null;
        this.mOrder.setPaymentCard(null);
        this.mOrder.setPayment(null);
        OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        this.mListViewAdapter.setIsEditMode(Boolean.valueOf(false));
        updateFooter();
        cancelItem.setVisible(false);
        editItem.setVisible(true);
        if (!this.mLoggedIn) {
            this.mBasketSignInHolder.setVisible(true);
        }
        if (this.mOrder.isEmpty()) {
            getActivity().setResult(39);
            getActivity().finish();
            return;
        }
        refreshStoreInfoAndDeliveryFee();
    }

    private void checkDayPartAndPreparePayment() {
        Ensighten.evaluateEvent(this, "checkDayPartAndPreparePayment", null);
        Store currentStore = OrderManager.getInstance().getCurrentStore();
        if (this.mOrder.checkDayPart(currentStore.getCurrentMenuTypeID(isDelivery()))) {
            preparePayment(true);
            return;
        }
        StringBuilder builder = new StringBuilder();
        MenuTypeCalendarItem orderCalendarItem;
        String fromTime;
        String toTime;
        if (this.mOrder.getEnabledDayParts() == null || this.mOrder.getEnabledDayParts().size() <= 1) {
            orderCalendarItem = null;
            if (this.mOrder.getEnabledDayParts() != null && this.mOrder.getEnabledDayParts().size() > 0) {
                orderCalendarItem = currentStore.getMenuTypeCalendarItem(((Integer) this.mOrder.getEnabledDayParts().get(0)).intValue(), isDelivery());
            }
            if (orderCalendarItem != null) {
                fromTime = DateUtils.formatTimeToSystemFormat(orderCalendarItem.getFromTime(), getContext());
                toTime = DateUtils.formatTimeToSystemFormat(orderCalendarItem.getToTime(), getContext());
                builder.append(getString(C2658R.string.invalid_daypart_checkout_single, fromTime, toTime));
            } else {
                preparePayment(true);
                return;
            }
        }
        builder.append(getString(C2658R.string.invalid_daypart_checkout));
        builder.append("\n\n");
        for (Integer intValue : this.mOrder.getEnabledDayParts()) {
            orderCalendarItem = currentStore.getMenuTypeCalendarItem(intValue.intValue(), isDelivery());
            if (orderCalendarItem != null) {
                fromTime = DateUtils.formatTimeToSystemFormat(orderCalendarItem.getFromTime(), getContext());
                toTime = DateUtils.formatTimeToSystemFormat(orderCalendarItem.getToTime(), getContext());
                builder.append(getString(C2658R.string.valid_daypart_range, fromTime, toTime));
                builder.append("\n");
            }
        }
        String dayPartWarnMsg = builder.toString();
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showInvalidDayPartWarningWithoutOpeningTimes")) {
            dayPartWarnMsg = getString(C2658R.string.invalid_daypart_checkout_cn);
        }
        UIUtils.showInvalidCurrentDayPart(getNavigationActivity(), dayPartWarnMsg, new C340314());
    }

    private boolean isDelivery() {
        Ensighten.evaluateEvent(this, "isDelivery", null);
        return OrderingManager.getInstance().getCurrentOrder().isDelivery();
    }

    public void preparePayment(boolean checkout) {
        Ensighten.evaluateEvent(this, "preparePayment", new Object[]{new Boolean(checkout)});
        OrderUtils.clearTotalizeResponses(this.mOrder);
        this.mOrderBeforeStoreChange = null;
        if (!((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).isLoggedIn()) {
            this.mBasketSignInHolder.setVisible(true);
        } else if (shouldShowUpsell() && checkout) {
            showUpsell();
        } else {
            startActivityForResult(CheckoutActivity.class, 18);
        }
    }

    private void showUpsell() {
        Ensighten.evaluateEvent(this, "showUpsell", null);
        UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.label_loading_product);
        this.mOrderingModule.getUpsellItems(this.mUpsellListener);
    }

    private boolean shouldShowUpsell() {
        Ensighten.evaluateEvent(this, "shouldShowUpsell", null);
        return isShowUpsell() && this.mOrder.showUpsell() && (this.mOrder.isDelivery() || !Configuration.getSharedInstance().getBooleanForKey("interface.ordering.disablePickupUpsell"));
    }

    private boolean isShowUpsell() {
        Ensighten.evaluateEvent(this, "isShowUpsell", null);
        if (this.mOrder.isDelivery()) {
            return Configuration.getSharedInstance().getBooleanForKey("modules.delivery.showUpsell");
        }
        return Configuration.getSharedInstance().getBooleanForKey("modules.ordering.showUpsell");
    }

    public void onActionRemove(Object item) {
        Ensighten.evaluateEvent(this, "onActionRemove", new Object[]{item});
        if (item instanceof OrderProduct) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Delete");
            this.mOrder.removeProduct((OrderProduct) item);
        } else if (this.mOrder.getOffers().size() > 0) {
            this.mOrder.removeOffer((OrderOffer) this.mOrder.getOffers().toArray()[0]);
        }
        if (this.mSkipBasketEditSaveFlow) {
            OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        }
        refresh();
    }

    public void onActionEdit(Object item) {
        Ensighten.evaluateEvent(this, "onActionEdit", new Object[]{item});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/basket").setAction("On click").setLabel("Edit").build());
        if (item instanceof OrderProduct) {
            SparseArray custom = new SparseArray();
            custom.put(21, ((OrderProduct) item).getProduct().getName());
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Edit", custom);
            Bundle extras = new Bundle();
            DataPasser.getInstance().putData("ARG_PRODUCT", item);
            extras.putBoolean("IN_EDIT_MODE", true);
            extras.putBoolean("arg_editing", true);
            extras.putString("ARG_ANALYTICS_PARENT_NAME", getString(C2658R.string.analytics_screen_basket_item));
            extras.putStringArrayList("ARG_UNAVAILABLE_PRODUCT_CODES", (ArrayList) this.mUnavailableProductCodes);
            Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
            intent.putExtras(extras);
            getActivity().startActivityForResult(intent, ProductDetailsActivity.REQUEST_CODE.intValue());
        } else if (item instanceof Offer) {
            new SparseArray().put(25, ((Offer) item).getName());
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Edit");
            startActivity(OfferActivity.class, "offer_detail");
        }
    }

    public void onActionMakeItAMeal(Object item) {
        Ensighten.evaluateEvent(this, "onActionMakeItAMeal", new Object[]{item});
        if (item instanceof OrderProduct) {
            Map jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_BASKET_MAKE_IT_A_MEAL);
            SparseArray custom = new SparseArray();
            custom.put(21, ((OrderProduct) item).getProduct().getName());
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Make it a meal", custom, jiceMap);
            getNavigationActivity().showLabelInsteadOfNavigateUp(false, getTitle());
            this.mOriginalOrder = Order.cloneOrderForEditing(OrderingManager.getInstance().getCurrentOrder());
            onActionEdit(item);
        }
    }

    private void deleteOrder() {
        Ensighten.evaluateEvent(this, "deleteOrder", null);
        this.mOrderBeforeStoreChange = null;
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Delete");
        OrderingManager.getInstance().deleteCurrentOrder();
        getActivity().setResult(39);
        getActivity().finish();
    }

    /* Access modifiers changed, original: 0000 */
    public void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        this.mOrder = OrderManager.getInstance().getCurrentOrder();
        OrderManager.getInstance().cleanBagsFromOrder();
        this.mListViewAdapter.clear();
        this.mListViewAdapter.addAll(createBasketItems());
        Button addToBasketButton = this.mBasketFooterHolder.getAddToBasketButton();
        boolean z = this.mListViewAdapter.getCount() > 0 || this.mIsEditMode.booleanValue();
        addToBasketButton.setEnabled(z);
        if (this.updatingStore) {
            if (OrderManager.getInstance().getCurrentStore() != null) {
                this.mOrder.setStoreId(OrderManager.getInstance().getCurrentStore().getStoreId());
            }
            this.mListViewAdapter.clear();
            updateProducts();
            this.mListViewAdapter.addAll(createBasketItems());
        } else {
            int currentStoreId = 0;
            if (OrderManager.getInstance().getCurrentStore() != null) {
                currentStoreId = OrderManager.getInstance().getCurrentStore().getStoreId();
            }
            if (this.mOrder == null || this.mOrder.getStoreId() == null || String.valueOf(currentStoreId).equalsIgnoreCase(this.mOrder.getStoreId())) {
                updateProducts();
                updateHeader();
            } else {
                UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.progress_find_store);
                StoreLocatorModule module = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
                final String orderingStore = this.mOrder.getStoreId();
                module.getStoreForId(orderingStore, new AsyncListener<List<Store>>() {
                    public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        if (exception == null && response != null && !response.isEmpty()) {
                            Store newStore = (Store) response.get(0);
                            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                            for (Store s : response) {
                                if (orderingStore.equals(String.valueOf(s.getStoreId()))) {
                                    customerModule.setCurrentStore(s);
                                    DataLayerManager.getInstance().setStore(newStore);
                                    break;
                                }
                            }
                        }
                        BasketFragment.access$1600(BasketFragment.this);
                    }
                });
            }
        }
        this.updatingStore = false;
        updateFooter();
    }

    private void updateHeader() {
        Ensighten.evaluateEvent(this, "updateHeader", null);
        if (getActivity() != null) {
            Store store = OrderManager.getInstance().getCurrentStore();
            if (store != null) {
                String name;
                if (store.getStoreFavoriteName() != null) {
                    name = store.getStoreFavoriteName();
                } else {
                    name = store.getAddress1();
                }
                this.mPickupHeaderHolder.getStoreName().setText(Html.fromHtml(getString(C2658R.string.order_location_pickup_title) + "<br/><b>" + name + "</b>"));
            }
        }
    }

    private void updateFooter() {
        Ensighten.evaluateEvent(this, "updateFooter", null);
        if (this.mIsEditMode.booleanValue()) {
            this.mBasketFooterHolder.getWarningsContainer().setVisibility(0);
            this.mBasketFooterHolder.getDeleteOrderButton().setVisibility(0);
            this.mBasketFooterHolder.getAddToBasketButton().setVisibility(8);
        } else {
            this.mBasketFooterHolder.getWarningsContainer().setVisibility(0);
            this.mBasketFooterHolder.getDeleteOrderButton().setVisibility(8);
            this.mBasketFooterHolder.getAddToBasketButton().setVisibility(0);
        }
        if (Configuration.getSharedInstance().hasKey("interface.orderingDisclaimerInfo")) {
            UIUtils.addDisclaimerTextView((ViewGroup) this.mBasketFooterHolder.getWarningsContainer(), getContext(), "basketView");
        }
    }

    private boolean isOfferAvailable(OrderOffer offerOrder) {
        Ensighten.evaluateEvent(this, "isOfferAvailable", new Object[]{offerOrder});
        Store currentStore = OrderManager.getInstance().getCurrentStore();
        if (!(currentStore == null || offerOrder == null)) {
            Integer storeId = Integer.valueOf(currentStore.getStoreId());
            List<Integer> restaurants = offerOrder.getOffer().getRestaurants();
            if (!(restaurants == null || restaurants.isEmpty() || restaurants.contains(storeId))) {
                return false;
            }
        }
        return true;
    }

    private List<BasketListItem> createBasketItems() {
        Ensighten.evaluateEvent(this, "createBasketItems", null);
        List<BasketListItem> items = new ArrayList();
        if (this.mOrder != null) {
            if (this.mOrder.getProducts() != null) {
                for (OrderProduct orderProduct : this.mOrder.getProducts()) {
                    items.addAll(createProductItems(orderProduct));
                }
            }
            this.mNonProductOfferNames.clear();
            if (this.mOrder.getOffers() != null) {
                ArrayList<OrderOffer> offerListBeforeChange;
                if (this.mOrderBeforeStoreChange != null) {
                    offerListBeforeChange = new ArrayList(this.mOrderBeforeStoreChange.getOffers());
                } else {
                    offerListBeforeChange = null;
                }
                ArrayList<OrderOffer> offerList = new ArrayList(this.mOrder.getOffers());
                for (int a = 0; a < offerList.size(); a++) {
                    OrderOffer orderOffer = (OrderOffer) offerList.get(a);
                    if (offerListBeforeChange != null) {
                        items.addAll(createOfferItems(orderOffer, (OrderOffer) offerListBeforeChange.get(a)));
                    } else {
                        items.addAll(createOfferItems(orderOffer, null));
                    }
                }
            }
            if (!(items.isEmpty() && !this.mOrder.hasDeliveryFeeOffer() && this.mNonProductOfferNames.isEmpty())) {
                items.addAll(createSubtotalItems());
            }
        }
        return items;
    }

    private List<BasketListItem> createProductItems(OrderProduct product) {
        List<OrderProduct> subProducts;
        int i;
        Ensighten.evaluateEvent(this, "createProductItems", new Object[]{product});
        List<BasketListItem> items = new ArrayList();
        if (product.isMeal()) {
            subProducts = subProducts(product, !ProductUtils.hideSingleChoice());
        } else {
            subProducts = Collections.singletonList(product);
        }
        int productErrorCode = 1;
        boolean hasError = false;
        for (i = 0; i < subProducts.size(); i++) {
            OrderUtils.checkProductInCurrentStore(getSubProduct((OrderProduct) subProducts.get(i)), product, this.mOrderingModule, this.mUnavailableProductCodes);
        }
        if (!(this.mOrder.getTotalizeResult() == null || this.mOrder.getTotalizeResult().getOrderView() == null)) {
            int validationErrorCode = this.mOrder.getTotalizeResult().getOrderView().getProductValidationErrorCode(product);
            if (validationErrorCode != 1) {
                hasError = true;
                productErrorCode = validationErrorCode;
            }
        }
        if (ListUtils.isNotEmpty(this.mUnavailableProductCodes) && isUnavailableProductItem(product)) {
            hasError = true;
            productErrorCode = OrderResponse.PRODUCT_UNAVAILABLE_CODE;
        }
        int ingredientsSize = 0;
        if (product.getIngredients() != null) {
            ingredientsSize = product.getIngredients().size();
        }
        i = 0;
        while (i < subProducts.size()) {
            int choiceIndex;
            OrderProduct subProduct = getSubProduct((OrderProduct) subProducts.get(i));
            OrderProduct choiceProduct = OrderProduct.getChoiceWithinChoiceProduct(subProduct);
            if (choiceProduct != null) {
                subProduct = choiceProduct;
            }
            subProduct.setOutOfStock(false);
            if (product.isMeal()) {
                if (ListUtils.isNotEmpty(this.mUnavailableProductCodes) && isUnavailableProductItem(product)) {
                    hasError = true;
                }
                if (this.mOrder.getTotalizeResult() != null && this.mOrder.getTotalizeResult().getOrderView() != null) {
                    if (this.mOrder.getTotalizeResult().getOrderView().getProductValidationErrorCode(subProduct) == -1036) {
                        subProduct.setOutOfStock(true);
                        hasError = true;
                    }
                    for (OrderProduct component : OrderProductUtils.getAllChoices(subProduct)) {
                        if (this.mOrder.getTotalizeResult().getOrderView().getProductValidationErrorCode(component) == -1036) {
                            subProduct.setOutOfStock(true);
                            hasError = true;
                            break;
                        }
                    }
                }
                choiceIndex = i;
                if (!(ListUtils.isEmpty(product.getIngredients()) || product.getIngredients().contains(subProduct))) {
                    choiceIndex -= product.getIngredients().size();
                }
                if (product.getRealChoices().size() > choiceIndex) {
                    Choice choiceSubProduct = (Choice) product.getRealChoices().get(choiceIndex);
                    List<OrderProduct> choiceOptions = choiceSubProduct.getOptions();
                    if (!ListUtils.isEmpty(choiceOptions) && choiceOptions.contains(subProduct) && choiceSubProduct.isSingleChoice() && ProductUtils.hideSingleChoice()) {
                        i++;
                    }
                }
            }
            if (productErrorCode == -6027) {
                hasError = true;
            }
            if (this.mOrderBeforeStoreChange != null && !ListUtils.isEmpty(this.mOrderBeforeStoreChange.getProducts()) && productErrorCode == 1) {
                PriceType oldPriceType = this.mOrderBeforeStoreChange.getPriceType();
                PriceType newPriceType = this.mOrder.getPriceType();
                for (OrderProduct oldProduct : this.mOrderBeforeStoreChange.getProducts()) {
                    if (oldProduct.equals(product) && oldProduct.getTotalPrice(oldPriceType) != product.getTotalPrice(newPriceType)) {
                        hasError = true;
                        productErrorCode = OrderResponse.PRODUCT_PRICE_CHANGED;
                        break;
                    }
                }
            }
            boolean isHeader = i == 0 && product.isMeal();
            boolean isDivider = i == 0 && !product.isMeal();
            boolean isFooter = i == subProducts.size() + -1;
            boolean isUnavailable = false;
            BasketListItem item = new BasketListItem();
            item.setBasketItem(product);
            if ((product.getProduct() == null || product.isUnavailable()) && isHeader) {
                isUnavailable = true;
                setErrorFlag(OrderResponse.PRODUCT_UNAVAILABLE_CODE, item);
            } else if (product.isMeal() && hasError && subProduct.isUnavailable()) {
                setErrorFlag(OrderResponse.PRODUCT_UNAVAILABLE_CODE, item);
                item.setUnavailable(true);
                item.setHasError(true);
                item.setMealErrorItem(true);
            } else if (product.isMeal() && hasError && subProduct.isOutOfStock()) {
                setErrorFlag(OrderResponse.PRODUCT_OUT_OF_STOCK_CODE, item);
                item.setOutOfStock(true);
                item.setHasError(true);
                item.setMealErrorItem(true);
            } else if (!product.isMeal() && hasError) {
                setErrorFlag(productErrorCode, item);
            }
            item.setTimeRestriction(product.getProduct().getTimeRestriction());
            item.setTimeRestrictions(product.getProduct().getTimeRestrictions());
            item.setTopPaddingHidden(Boolean.valueOf(!isHeader));
            if (isHeader) {
                item.setHeaderHidden(Boolean.valueOf(false));
                item.setHeaderIconHidden(Boolean.valueOf(true));
                item.setHeaderText(product.getQuantity() + " " + product.getDisplayName());
                if (hasError) {
                    if (!subProduct.isUnavailable()) {
                        item.setMealHeaderNonErrorWarningItem(true);
                    }
                    setErrorFlag(productErrorCode, item);
                }
            } else {
                item.setHeaderHidden(Boolean.valueOf(true));
            }
            item.setDividerHidden(Boolean.valueOf(!isDivider));
            if (isFooter) {
                item.setFooterHidden(Boolean.valueOf(false));
                if (isUnavailable) {
                    item.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(0.0d));
                } else {
                    item.setEnergyTotal(AppUtils.getEnergyTextForOrderProduct(product, OrderProductUtils.getTotalEnergyUnit(product)));
                    item.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(ProductUtils.getProductTotalPrice(product)));
                }
                item.setPositionInMeal(i);
            } else {
                item.setFooterHidden(Boolean.valueOf(true));
            }
            if (hasNonSingleChoiceItems(subProduct)) {
                item.setHeaderDetailsText(buildCustomizationString(subProduct));
            }
            item.setMakeItAMealHidden(Boolean.valueOf(true));
            Boolean hasMeal = Boolean.valueOf(false);
            String pod;
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                pod = Pod.DELIVERY;
            } else {
                pod = Pod.PICKUP;
            }
            boolean filterDimenPod = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.filterDimenPod");
            if (!(product.isMeal() || isUnavailable || !checkDisplaySizeSelection(product))) {
                List<ProductDimension> dimensions = product.getProduct().getDimensions();
                if (dimensions != null) {
                    for (ProductDimension dimension : dimensions) {
                        if (filterDimenPod) {
                            this.mOrderingModule.populateProductWithStoreSpecificData(dimension.getProduct());
                        }
                        if (dimension.getProduct().getProductType() == ProductType.Meal && (!filterDimenPod || dimension.getProduct().getPODs().contains(pod))) {
                            hasMeal = Boolean.valueOf(true);
                            break;
                        }
                    }
                }
                if (hasMeal.booleanValue() && (isHeader || isDivider)) {
                    item.setMakeItAMealHidden(Boolean.valueOf(false));
                }
            }
            boolean includeQuantity = i == 0 && !product.isMeal();
            updateItemData(item, subProduct, includeQuantity);
            choiceIndex = i - ingredientsSize;
            if (product.isMeal() && !isHeader && ListUtils.isNotEmpty(product.getRealChoices()) && product.getRealChoices().size() > choiceIndex && choiceIndex >= 0) {
                item.setItemUplift(ProductUtils.getProductTotalPrice(subProduct) - this.mOrderingModule.getProductBasePrice((Choice) product.getRealChoices().get(choiceIndex)));
            }
            OrderProduct imageProduct = subProduct;
            if (subProduct instanceof Choice) {
                imageProduct = ((Choice) subProduct).getSelection();
            }
            item.setIconImage(imageProduct.getDisplayThumbnailImage());
            if (!product.isUnavailable()) {
                validatePODs(subProduct, item);
            }
            items.add(item);
            i++;
        }
        return items;
    }

    private boolean checkDisplaySizeSelection(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "checkDisplaySizeSelection", new Object[]{orderProduct});
        Store store = OrderManager.getInstance().getCurrentStore();
        Product product = orderProduct.getProduct();
        StoreProduct storeProduct = new StoreProduct();
        storeProduct.setProductId(product.getExternalId().intValue());
        storeProduct.setStoreId(store.getStoreId());
        if (getActivity() != null) {
            for (StoreProductCategory c : StoreProductCategoryRepository.getCategoryByStoreProduct(getActivity(), storeProduct)) {
                if (c.getDisplaySizeSelection() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validatePODs(OrderProduct product, BasketListItem item) {
        Ensighten.evaluateEvent(this, "validatePODs", new Object[]{product, item});
        List<String> productPODs = product.getProduct().getPODs();
        if (productPODs.size() < PODUtils.getMainPODsLength()) {
            String message;
            if (productPODs.size() == 1) {
                message = getString(C2658R.string.label_available_pod_only, OrderProduct.getPODDisplayName((String) productPODs.get(0), getResources()));
            } else {
                message = createMultiplePODsUnavailableMessage(productPODs);
            }
            item.setUnavailablePODMessage(message);
        }
    }

    private String createMultiplePODsUnavailableMessage(List<String> pods) {
        Ensighten.evaluateEvent(this, "createMultiplePODsUnavailableMessage", new Object[]{pods});
        String name = OrderProduct.getPODDisplayName((String) PODUtils.getRemainingPODs(pods).get(0), getResources());
        return getString(C2658R.string.label_one_or_more_unavailable_pods, name);
    }

    private void setErrorFlag(int productErrorCode, BasketListItem newItem) {
        boolean z;
        Ensighten.evaluateEvent(this, "setErrorFlag", new Object[]{new Integer(productErrorCode), newItem});
        newItem.setHasError(true);
        if (productErrorCode == OrderResponse.PRODUCT_PRICE_CHANGED) {
            z = true;
        } else {
            z = false;
        }
        newItem.setPriceChanged(z);
        if (productErrorCode == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) {
            z = true;
        } else {
            z = false;
        }
        newItem.setOutOfStock(z);
        if (productErrorCode == OrderResponse.OFFERS_ERROR_DELIVERY_ONLY || productErrorCode == OrderResponse.OFFERS_ERROR_PICKUP_ONLY) {
            newItem.setOfferPODErrorCode(productErrorCode);
        }
        newItem.setUnavailable(Arrays.asList(new Integer[]{Integer.valueOf(OrderResponse.PRODUCT_TIME_RESTRICTION_CODE), Integer.valueOf(OrderResponse.PRODUCT_UNAVAILABLE_CODE), Integer.valueOf(OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE)}).contains(Integer.valueOf(productErrorCode)));
    }

    private boolean isUnavailableProductItem(OrderProduct product) {
        Ensighten.evaluateEvent(this, "isUnavailableProductItem", new Object[]{product});
        boolean retValue = false;
        if (!product.isMeal()) {
            return this.mUnavailableProductCodes.contains(product.getProductCode());
        }
        List<OrderProduct> subProducts = subProducts(product);
        if (!ListUtils.isNotEmpty(subProducts)) {
            return false;
        }
        for (OrderProduct subProduct : subProducts) {
            if (this.mUnavailableProductCodes.contains(subProduct.getProductCode())) {
                retValue = true;
            }
        }
        return retValue;
    }

    private List<BasketListItem> createOfferItems(OrderOffer orderOffer, OrderOffer orderOfferBeforeChange) {
        Ensighten.evaluateEvent(this, "createOfferItems", new Object[]{orderOffer, orderOfferBeforeChange});
        List<BasketListItem> items = new ArrayList();
        if (orderOffer.getOffer().getOfferType() != OfferType.OFFER_TYPE_DELIVERY_FEE) {
            BasketListItem newItem;
            if (orderOffer.getOrderOfferProducts() != null) {
                int i = 0;
                while (i < orderOffer.getOrderOfferProducts().size()) {
                    OrderOfferProduct orderOfferProduct = (OrderOfferProduct) orderOffer.getOrderOfferProducts().get(i);
                    boolean isHeader = i == 0;
                    boolean isFooter = i == orderOffer.getOrderOfferProducts().size() + -1;
                    int offerErrorCode = 0;
                    newItem = new BasketListItem();
                    newItem.setBasketItem(orderOffer);
                    Integer productId = Integer.valueOf(orderOfferProduct.getSelectedProductOption().getProductCode());
                    if (!(this.mOrder.getTotalizeResult() == null || orderOfferProduct.getSelectedProductOption() == null || !this.mOrder.getTotalizeResult().getPromotionsOutOfStock().contains(productId))) {
                        offerErrorCode = OrderResponse.PRODUCT_OUT_OF_STOCK_CODE;
                        newItem.setOutOfStock(true);
                        newItem.setHasError(true);
                    }
                    if (!(orderOfferBeforeChange == null || orderOfferBeforeChange.getOrderOfferProducts() == null || orderOfferBeforeChange.getOrderOfferProducts().get(i) == null)) {
                        if (!orderOfferProduct.getTotalValue(this.mOrder.getPriceType()).equals(((OrderOfferProduct) orderOfferBeforeChange.getOrderOfferProducts().get(i)).getTotalValue(this.mOrder.getPriceType()))) {
                            offerErrorCode = OrderResponse.PRODUCT_PRICE_CHANGED;
                            newItem.setOfferPriceChanged(true);
                        }
                    }
                    boolean isDeliveryOnly = orderOffer.getOffer().isDeliveryOffer() && !orderOffer.getOffer().isPickupOffer();
                    newItem.setIsOfferDelivery(orderOffer.getOffer().isDeliveryOffer());
                    if (isDeliveryOnly && !this.mOrder.isDelivery()) {
                        offerErrorCode = OrderResponse.OFFERS_ERROR_DELIVERY_ONLY;
                    }
                    boolean isPickUpOnly = orderOffer.getOffer().isPickupOffer() && !orderOffer.getOffer().isDeliveryOffer();
                    newItem.setOfferIsPickup(orderOffer.getOffer().isPickupOffer());
                    if (isPickUpOnly && this.mOrder.isDelivery()) {
                        offerErrorCode = OrderResponse.OFFERS_ERROR_PICKUP_ONLY;
                    }
                    newItem.setTopPaddingHidden(Boolean.valueOf(!isHeader));
                    if (isHeader) {
                        newItem.setHeaderHidden(Boolean.valueOf(false));
                        newItem.setHeaderIconHidden(Boolean.valueOf(false));
                        newItem.setHeaderText(orderOffer.getOffer().getName());
                    } else {
                        newItem.setHeaderHidden(Boolean.valueOf(true));
                    }
                    newItem.setDividerHidden(Boolean.valueOf(true));
                    if (isFooter) {
                        newItem.setFooterHidden(Boolean.valueOf(false));
                        newItem.setEnergyTotal(AppUtils.getEnergyTextForOrderOffer(orderOffer, OrderOfferUtils.getTotalEnergyUnit(orderOffer)));
                        newItem.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(this.mOrder.getOfferTotalValue()));
                    } else {
                        newItem.setFooterHidden(Boolean.valueOf(true));
                    }
                    OrderProduct subProduct = orderOfferProduct.getSelectedProductOption();
                    if (hasNonSingleChoiceItems(subProduct)) {
                        newItem.setHeaderDetailsText(buildCustomizationString(subProduct));
                    }
                    newItem.setMakeItAMealHidden(Boolean.valueOf(true));
                    updateItemData(newItem, orderOfferProduct.getSelectedProductOption(), false);
                    OrderProduct imageProduct = orderOfferProduct.getSelectedProductOption();
                    if (imageProduct != null) {
                        newItem.setIconImage(imageProduct.getDisplayThumbnailImage());
                    }
                    if ((this.mOrder.getTotalizeResult() != null && this.mOrder.getTotalizeResult().getPromotionsNotAvailable().contains(orderOffer.getOffer().getOfferId())) || !isOfferAvailable(orderOffer) || offerErrorCode != 0) {
                        newItem.setHasError(true);
                        newItem.setOfferUnavailable(true);
                        if (offerErrorCode != 0) {
                            setErrorFlag(offerErrorCode, newItem);
                        }
                    }
                    items.add(newItem);
                    i++;
                }
            } else {
                newItem = new BasketListItem();
                newItem.setBasketItem(orderOffer);
                newItem.setOfferIsNonProduct(true);
                newItem.setTopPaddingHidden(Boolean.valueOf(false));
                newItem.setHeaderHidden(Boolean.valueOf(false));
                newItem.setHeaderIconHidden(Boolean.valueOf(false));
                newItem.setHeaderText(orderOffer.getOffer().getName());
                newItem.setMakeItAMealHidden(Boolean.valueOf(true));
                newItem.setDividerHidden(Boolean.valueOf(true));
                ImageInfo imgInfo = new ImageInfo();
                imgInfo.setImageName(orderOffer.getOffer().getName());
                imgInfo.setUrl(orderOffer.getOffer().getSmallImagePath());
                newItem.setItemName(orderOffer.getOffer().getName());
                newItem.setIconImage(imgInfo);
                newItem.setFooterHidden(Boolean.valueOf(false));
                newItem.setPriceTotal("");
                items.add(newItem);
            }
        }
        return items;
    }

    private List<BasketListItem> createSubtotalItems() {
        Ensighten.evaluateEvent(this, "createSubtotalItems", null);
        List<BasketListItem> ret = new ArrayList();
        SubtotalBasketListItem item = new SubtotalBasketListItem();
        double total = this.mOrder.getTotalValue();
        if (this.mHeaderIsDelivery) {
            item.setDeliveryHidden(false);
            item.setDividerHidden(Boolean.valueOf(false));
            item.setDeliveryFee(this.deliveryFee);
            if (this.mOrder.hasDeliveryFeeOffer()) {
                item.setDeliveryFeeOfferHidden(false);
                item.setOfferName(this.mOrder.getDeliveryChargeOfferName());
                item.setDeliveryFeeDiscount(this.mOrder.getDiscountedDeliveryCharge().doubleValue());
                total += this.mOrder.getDiscountedDeliveryCharge().doubleValue();
            } else {
                item.setDeliveryFeeOfferHidden(true);
                total += this.deliveryFee;
            }
        } else {
            item.setDeliveryFeeOfferHidden(true);
            item.setDeliveryHidden(true);
            item.setDividerHidden(Boolean.valueOf(true));
            item.setShowTotal(true);
        }
        item.setEnergyTotal(AppUtils.getEnergyTextForOrder(this.mOrder, OrderUtils.getTotalEnergyUnit(this.mOrder)));
        item.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(total));
        if (ListUtils.isNotEmpty(this.mOrder.getOffers()) && !TextUtils.isEmpty(this.mOrder.getStoreId())) {
            List<Integer> restaurants = ((OrderOffer) this.mOrder.getOffers().iterator().next()).getOffer().getRestaurants();
            Integer storeId = Integer.valueOf(this.mOrder.getStoreId());
            if (restaurants != null && (restaurants.contains(storeId) || restaurants.isEmpty())) {
                item.setHideOfferUnavailableContainer(true);
            }
        }
        if (this.mNonProductOfferNames.size() > 0) {
            item.setIsNonProductOfferAvailable(true);
            item.setNonProductOfferNames(this.mNonProductOfferNames);
        }
        ret.add(item);
        return ret;
    }

    private List<OrderProduct> subProducts(OrderProduct product) {
        Ensighten.evaluateEvent(this, "subProducts", new Object[]{product});
        return subProducts(product, true);
    }

    private List<OrderProduct> subProducts(OrderProduct product, boolean includeSingleChoices) {
        Ensighten.evaluateEvent(this, "subProducts", new Object[]{product, new Boolean(includeSingleChoices)});
        List<OrderProduct> ret = new ArrayList(product.getIngredients());
        if (!ListUtils.isEmpty(product.getRealChoices())) {
            for (Choice choice : product.getRealChoices()) {
                if ((includeSingleChoices || !choice.isSingleChoice()) && choice.getSelection() != null) {
                    ret.add(choice.getSelection());
                }
            }
        }
        return ret;
    }

    private void updateItemData(BasketListItem item, OrderProduct product, boolean includeQuantity) {
        Ensighten.evaluateEvent(this, "updateItemData", new Object[]{item, product, new Boolean(includeQuantity)});
        if (!(product.getProduct() == null || product.getProduct().getLongName() == null)) {
            if (includeQuantity) {
                item.setItemName(product.getQuantity() + " " + product.getProduct().getLongName());
            } else {
                item.setItemName(product.getProduct().getLongName());
            }
        }
        if (product.getCustomizations() != null) {
            item.setItemInstructions(OrderProductUtils.getCustomizationsString(product));
        }
        if (hasNonSingleChoiceItems(product)) {
            item.setHeaderDetailsText(buildCustomizationString(product));
        }
    }

    public void updateOrder() {
        Ensighten.evaluateEvent(this, "updateOrder", null);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        this.mHeaderIsDelivery = this.mOrder.isDelivery();
    }

    private String buildCustomizationString(OrderProduct product) {
        Ensighten.evaluateEvent(this, "buildCustomizationString", new Object[]{product});
        StringBuilder result = new StringBuilder();
        String separator = ", ";
        boolean hideSingleChoice = ProductUtils.hideSingleChoice();
        for (Choice choice : product.getRealChoices()) {
            if (!hideSingleChoice || !choice.isSingleChoice()) {
                OrderProduct orderProduct = ProductUtils.getActualChoice(choice);
                if (orderProduct != null) {
                    result.append(orderProduct.getDisplayName());
                    result.append(separator);
                    if (ListUtils.isNotEmpty(orderProduct.getRealChoices())) {
                        for (Choice subChoice : orderProduct.getRealChoices()) {
                            OrderProduct choiceSelection = ProductUtils.getActualChoice(subChoice);
                            if (choiceSelection != null) {
                                result.append(choiceSelection.getDisplayName());
                                result.append(separator);
                            }
                        }
                    }
                }
            }
        }
        return result.length() > 0 ? result.substring(0, result.length() - separator.length()) : "";
    }

    private boolean hasNonSingleChoiceItems(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "hasNonSingleChoiceItems", new Object[]{orderProduct});
        if (!ProductUtils.hideSingleChoice()) {
            return true;
        }
        Product product = orderProduct.getProduct();
        if (product != null && ListUtils.isNotEmpty(product.getChoices())) {
            for (Ingredient ingredient : product.getChoices()) {
                if (ingredient.getProduct() != null && !ingredient.getProduct().isSingleChoice().booleanValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private OrderProduct getSubProduct(OrderProduct product) {
        Ensighten.evaluateEvent(this, "getSubProduct", new Object[]{product});
        if (product.getProduct().getProductType() != ProductType.Product) {
            return product;
        }
        return OrderProduct.getChoiceWithinChoiceProduct(product);
    }

    public Boolean isEditMode() {
        Ensighten.evaluateEvent(this, "isEditMode", null);
        return this.mIsEditMode;
    }

    public void updateOrderMethodSelectionHeader() {
        Ensighten.evaluateEvent(this, "updateOrderMethodSelectionHeader", null);
        if (this.mListView != null) {
            if (this.mDeliveryTimeHolder != null) {
                this.mListView.removeHeaderView(this.mDeliveryTimeHolder.getContainer());
            }
            if (this.mPickupHeaderHolder != null) {
                this.mListView.removeHeaderView(this.mPickupHeaderHolder.getContainer());
            }
            if (this.mHeaderIsDelivery) {
                if (this.mDeliveryTimeHolder != null) {
                    this.mListView.addHeaderView(this.mDeliveryTimeHolder.getContainer());
                }
            } else if (this.mPickupHeaderHolder != null) {
                this.mListView.addHeaderView(this.mPickupHeaderHolder.getContainer());
            }
        }
    }

    public void totalize() {
        Ensighten.evaluateEvent(this, "totalize", null);
        OrderingManager.getInstance().totalize(OrderManager.getInstance().getCurrentStore(), this.mTotalizeListener);
    }
}
