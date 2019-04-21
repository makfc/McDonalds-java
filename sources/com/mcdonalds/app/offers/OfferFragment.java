package com.mcdonalds.app.offers;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.p000v4.media.TransportMediator;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.home.PunchcardOfferPagerAdapter;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductCustomizationActivity;
import com.mcdonalds.app.ordering.ProductDetailsItem;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorActivity;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.MapManager;
import com.mcdonalds.app.storelocator.MapManager.Callback;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController.Builder;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController.OfferSelection;
import com.mcdonalds.app.storelocator.StoresManager;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMapClickListener;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMarkerClickListener;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.storelocator.maps.model.Marker;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.MapUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OfferBarCodeData;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.modules.models.OffersOperationType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OfferFragment extends URLNavigationFragment implements OffersListener, Callback, OnMapClickListener {
    public static final String LOG_TAG = OfferFragment.class.getSimpleName();
    public static final Integer REQUEST_CODE = Integer.valueOf(56831);
    private Double DEFAULT_RADIUS;
    protected OrderOfferListener listener;
    private Boolean mAllChoicesSolved;
    private Button mApplyToOrderButton;
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private Order mEditOrder;
    private OrderOffer mEditOrderOffer;
    private boolean mFromProduct;
    private boolean mHasProductSelection;
    private boolean mInEditMode = false;
    private Store mInitialTargetStore;
    private SparseArray<OfferItemData> mItemDataList;
    private McdMap mMap;
    private MapManager mMapFragment;
    private boolean mMapLoaded = false;
    private boolean mMapUpdated = false;
    private MapWidgetViewHolder mMapViewHolder;
    private boolean mNeedsRefresh = false;
    private NutritionModule mNutritionModule;
    private Offer mOffer;
    private OfferSelection mOfferSelectionType;
    private OffersModule mOffersModule;
    private OrderOffer mOrderOffer;
    private OrderingModule mOrderingModule;
    private OfferActivity mParent;
    private Product mPreSelectedProduct;
    private DecimalFormat mPriceFormat;
    private SparseArray<View> mProductViews;
    private TextView mPunchcardSelectItemTextView;
    private OrderOfferProduct mSavedPunchCardAddItemProduct = null;
    private SparseArray<OrderOfferProduct> mSelectedOrderOfferProducts;
    private StoreLocatorModule mStoreLocatorModule;
    private Marker mTargetMarker = null;
    private String mTitle;
    private TextView mTotalCaloriesText;
    private Double mTotalPrice;
    private TextView mTotalPriceText;
    private ArrayList<OrderOfferProduct> orderOfferProductsApplied;

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$10 */
    class C332010 implements OnClickListener {
        C332010() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OfferFragment.access$1500(OfferFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$11 */
    class C332111 implements Runnable {
        C332111() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            OfferFragment.this.listener.onResponse(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$900", new Object[]{OfferFragment.this}), null, null);
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$12 */
    class C332212 implements AsyncListener<Object> {
        C332212() {
        }

        public void onResponse(Object response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (OfferFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception == null) {
                OrderOffer.createOrderOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}), true, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1600", new Object[]{OfferFragment.this}), OfferFragment.this.listener);
            } else {
                UIUtils.stopActivityIndicator();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$13 */
    class C332313 implements OnMarkerClickListener {
        C332313() {
        }

        public boolean onMarkerClick(Marker marker) {
            Ensighten.evaluateEvent(this, "onMarkerClick", new Object[]{marker});
            OfferFragment.access$1500(OfferFragment.this);
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$19 */
    class C333119 implements AsyncListener<List<Store>> {
        C333119() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && OfferFragment.this.getNavigationActivity() != null) {
                OfferFragment.access$1900(OfferFragment.this, response, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1800", new Object[]{OfferFragment.this}));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$20 */
    class C333620 implements AsyncListener<List<Store>> {
        C333620() {
        }

        public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && OfferFragment.this.getNavigationActivity() != null) {
                OfferFragment.access$1900(OfferFragment.this, response, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1800", new Object[]{OfferFragment.this}));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$2 */
    class C33462 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.offers.OfferFragment$2$1 */
        class C33331 implements DialogInterface.OnClickListener {
            C33331() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            }
        }

        /* renamed from: com.mcdonalds.app.offers.OfferFragment$2$2 */
        class C33352 implements DialogInterface.OnClickListener {

            /* renamed from: com.mcdonalds.app.offers.OfferFragment$2$2$1 */
            class C33341 implements AsyncListener<Boolean> {
                C33341() {
                }

                public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception != null) {
                        UIUtils.stopActivityIndicator();
                        AsyncException.report(exception);
                    } else if (response.booleanValue()) {
                        ServiceUtils.getSharedInstance().updateArchivedOfferInCache(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getOfferId().intValue());
                        UIUtils.stopActivityIndicator();
                        OfferFragment.this.getNavigationActivity().setResult(-1);
                        OfferFragment.this.getNavigationActivity().finish();
                    }
                }
            }

            C33352() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                UIUtils.startActivityIndicator(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.updating_offer));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$300", new Object[]{OfferFragment.this}).archiveOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$200", new Object[]{OfferFragment.this}).getCurrentProfile(), new C33341());
            }
        }

        C33462() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(OfferFragment.this.getAnalyticsTitle(), "Not Interested");
            if (!AppUtils.isNetworkConnected(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}))) {
                UIUtils.showNoNetworkAlert(OfferFragment.this.getNavigationActivity());
            } else if (OrderingManager.getInstance().getCurrentOrder().hasOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}))) {
                MCDAlertDialogBuilder.withContext(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this})).setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.offer_in_use_warning_title)).setMessage(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.offer_in_use_warning_message)).setPositiveButton(OfferFragment.this.getResources().getString(C2658R.string.f6083ok), new C33331()).create().show();
                DataLayerManager.getInstance().recordError("Offer in use");
            } else {
                MCDAlertDialogBuilder.withContext(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this})).setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.are_you_sure)).setMessage(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.offer_not_interested_confirmation_message)).setPositiveButton(OfferFragment.this.getResources().getString(C2658R.string.f6083ok), new C33352()).setNegativeButton(OfferFragment.this.getResources().getString(C2658R.string.cancel), null).create().show();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$3 */
    class C33473 implements OnClickListener {
        C33473() {
        }

        public void onClick(View v) {
            String string;
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackScreenLoad(OfferFragment.this.getString(C2658R.string.analytics_screen_offer_info));
            AnalyticsUtils.trackOnClickEvent(OfferFragment.this.getAnalyticsTitle(), "Info Icon");
            MCDAlertDialogBuilder withContext = MCDAlertDialogBuilder.withContext(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}));
            if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getLongDescription())) {
                string = Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.offer_terms_unavailable);
            } else {
                string = Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getLongDescription();
            }
            withContext.setMessage(string).setNeutralButton((int) C2658R.string.f6083ok, null).create().show();
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$5 */
    class C33525 implements DialogInterface.OnClickListener {
        C33525() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$6 */
    class C33536 implements DialogInterface.OnClickListener {
        C33536() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$7 */
    class C33547 implements DialogInterface.OnClickListener {
        C33547() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$8 */
    class C33568 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.offers.OfferFragment$8$1 */
        class C33551 implements AsyncListener<OfferBarCodeData> {
            C33551() {
            }

            public void onResponse(OfferBarCodeData response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    Bundle args = new Bundle();
                    args.putString("ARG_OFFER_NAME", Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getName());
                    args.putBoolean("ARG_IS_PUNCHCARD_OFFER", Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1400", new Object[]{OfferFragment.this}));
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setAction("On click").setCategory(OfferFragment.this.getAnalyticsTitle()).setLabel("Use in restaurant").setBusiness(BusinessArgs.getOfferClick(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}))).build());
                    AnalyticsUtils.trackEvent(new ArgBuilder().setLabel(BusinessArgs.EVENT_PRODUCT_OFFER_REDEEM).setMapping(BusinessArgs.KEY_OFFER_ID, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getOfferId()).setMapping(BusinessArgs.KEY_OFFER_NAME, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getName()).build());
                    if (response != null) {
                        DataLayerManager.getInstance().setOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}), response.getQrCode());
                    }
                    args.putParcelable("ARG_OFFER_BARCODE_DATA", response);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).startActivityForResult(OfferRedeemActivity.class, OfferRedeemFragment.NAME, args, 38176);
                    return;
                }
                AsyncException.report(exception);
            }
        }

        C33568() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (OrderManager.getInstance().getCurrentStore() == null) {
                OfferFragment.this.startActivity(OrderMethodSelectionActivity.class);
                return;
            }
            Integer integerStoreId = Integer.valueOf(OrderManager.getInstance().getCurrentStore().getStoreId());
            if (!(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getRestaurants() == null || Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getRestaurants().isEmpty() || Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getRestaurants().contains(integerStoreId))) {
                integerStoreId = (Integer) Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getRestaurants().get(0);
            }
            if (integerStoreId == null) {
                AsyncException.report("Illegal Arguments");
                return;
            }
            AsyncListener<OfferBarCodeData> barCodeListener = new C33551();
            UIUtils.startActivityIndicator(OfferFragment.this.getActivity(), (int) C2658R.string.label_generating_offer_barcode);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1400", new Object[]{OfferFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$300", new Object[]{OfferFragment.this}).getCustomerIdentificationCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$200", new Object[]{OfferFragment.this}).getCurrentProfile(), integerStoreId, barCodeListener);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$300", new Object[]{OfferFragment.this}).selectToRedeem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$200", new Object[]{OfferFragment.this}).getCurrentProfile(), Collections.singletonList(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this})), integerStoreId, barCodeListener);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.offers.OfferFragment$9 */
    class C33579 implements OnClickListener {
        C33579() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OfferFragment.access$1500(OfferFragment.this);
        }
    }

    private class ItemCounter {
        private int mCount = 0;

        ItemCounter() {
        }

        public int value() {
            Ensighten.evaluateEvent(this, "value", null);
            return this.mCount;
        }

        public void increment() {
            Ensighten.evaluateEvent(this, "increment", null);
            this.mCount++;
        }
    }

    private class MapWidgetViewHolder {
        private final TextView mDistance = ((TextView) this.mView.findViewById(C2358R.C2357id.distance));
        private final TextView mHours = ((TextView) this.mView.findViewById(C2358R.C2357id.store_hours));
        private final ImageButton mImageButton = ((ImageButton) this.mView.findViewById(C2358R.C2357id.imageButton));
        private final TextView mIsOpen = ((TextView) this.mView.findViewById(C2358R.C2357id.is_open));
        private final TextView mName = ((TextView) this.mView.findViewById(C2358R.C2357id.name));
        private final View mView;

        public MapWidgetViewHolder(View view) {
            this.mView = view;
        }

        public View getView() {
            return this.mView;
        }

        public ImageButton getmImageButton() {
            Ensighten.evaluateEvent(this, "getmImageButton", null);
            return this.mImageButton;
        }

        public TextView getName() {
            Ensighten.evaluateEvent(this, "getName", null);
            return this.mName;
        }

        public TextView getIsOpen() {
            Ensighten.evaluateEvent(this, "getIsOpen", null);
            return this.mIsOpen;
        }

        public TextView getHours() {
            Ensighten.evaluateEvent(this, "getHours", null);
            return this.mHours;
        }

        public TextView getDistance() {
            Ensighten.evaluateEvent(this, "getDistance", null);
            return this.mDistance;
        }
    }

    public static class OfferItemData {
        public int choiceIndex;
        public OrderProduct choiceProduct;
        public boolean isChoice;
        public OrderOfferProduct orderOfferProduct;
        public OrderProduct product;

        public OfferItemData(OrderOfferProduct orderOfferProduct, OrderProduct product, OrderProduct choiceProduct, int choiceIndex, boolean isChoice) {
            this.orderOfferProduct = orderOfferProduct;
            this.product = product;
            this.choiceProduct = choiceProduct;
            this.choiceIndex = choiceIndex;
            this.isChoice = isChoice;
        }

        public OfferItemData(OrderOfferProduct orderOfferProduct, OrderProduct product) {
            this(orderOfferProduct, product, null, -1, false);
        }
    }

    private class OrderOfferListener implements AsyncListener<OrderOffer> {
        private final View mRootView;

        public OrderOfferListener(View rootView) {
            this.mRootView = rootView;
        }

        public void onResponse(OrderOffer response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null && OfferFragment.this.getNavigationActivity() != null) {
                OfferFragment.access$1002(OfferFragment.this, response);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).setOrderOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1000", new Object[]{OfferFragment.this}));
                OfferFragment.access$1700(OfferFragment.this, this.mRootView);
            }
        }
    }

    static /* synthetic */ OrderOffer access$1002(OfferFragment x0, OrderOffer x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1002", new Object[]{x0, x1});
        x0.mOrderOffer = x1;
        return x1;
    }

    static /* synthetic */ void access$1300(OfferFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1300", new Object[]{x0});
        x0.trackBasketCreated();
    }

    static /* synthetic */ void access$1500(OfferFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1500", new Object[]{x0});
        x0.findInStore();
    }

    static /* synthetic */ void access$1700(OfferFragment x0, View x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1700", new Object[]{x0, x1});
        x0.refreshDataForOffer(x1);
    }

    static /* synthetic */ void access$1900(OfferFragment x0, List x1, McdMap x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1900", new Object[]{x0, x1, x2});
        x0.setMapPins(x1, x2);
    }

    static /* synthetic */ void access$2000(OfferFragment x0, OrderProduct x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$2000", new Object[]{x0, x1, new Integer(x2)});
        x0.onProductCustomizeClicked(x1, x2);
    }

    static /* synthetic */ void access$2100(OfferFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$2100", new Object[]{x0, x1});
        x0.onProductInfoButtonClicked(x1);
    }

    static /* synthetic */ void access$2200(OfferFragment x0, OrderProduct x1, OrderProduct x2, int x3, int x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$2200", new Object[]{x0, x1, x2, new Integer(x3), new Integer(x4)});
        x0.onProductChoiceClicked(x1, x2, x3, x4);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        if (isAdded()) {
            return getString(C2658R.string.analytics_screen_offer);
        }
        return "";
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPageSection() {
        Ensighten.evaluateEvent(this, "getDataLayerPageSection", null);
        return this.mOffer != null ? this.mOffer.getName() : "";
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoresManager.getInstance().refreshFavorites();
        this.DEFAULT_RADIUS = Double.valueOf(Configuration.getSharedInstance().getDoubleForKey("modules.storeLocator.defaultSearchRadius"));
        this.mTotalPrice = Double.valueOf(0.0d);
        this.mPriceFormat = new DecimalFormat("#0.00");
        this.mSelectedOrderOfferProducts = new SparseArray();
        this.mProductViews = new SparseArray();
        this.mItemDataList = new SparseArray();
        Bundle extras = getArguments();
        if (extras != null) {
            this.mInEditMode = extras.getBoolean("IN_EDIT_MODE", false);
            this.mOfferSelectionType = OfferSelection.values()[extras.getInt("offer_selection_type", 0)];
            this.mFromProduct = extras.getBoolean("extra_from_product", false);
            if (extras.containsKey("extra_current_recipe")) {
                this.mPreSelectedProduct = (Product) extras.getParcelable("extra_current_recipe");
            } else {
                this.mPreSelectedProduct = (Product) DataPasser.getInstance().getData("extra_current_recipe");
            }
        }
        if (this.mInEditMode) {
            Parcelable data;
            int key;
            if (extras.containsKey("edit_order_data_passer_id")) {
                data = extras.getParcelable("edit_order_data_passer_id");
            } else {
                data = DataPasser.getInstance().getData("edit_order_data_passer_id");
            }
            if (data == null || !(data instanceof Order)) {
                key = getArguments().getInt("edit_order_data_passer_id", -1);
                if (key != -1) {
                    this.mEditOrder = Order.cloneOrderForEditing((Order) DataPasser.getInstance().getData(key));
                }
            } else {
                this.mEditOrder = Order.cloneOrderForEditing((Order) data);
            }
            Serializable data2 = getArguments().getSerializable("edit_order_offer_data_passer_id");
            if (data2 == null || !(data2 instanceof OrderOffer)) {
                key = getArguments().getInt("edit_order_offer_data_passer_id", -1);
                if (key != -1) {
                    this.mEditOrderOffer = ((OrderOffer) DataPasser.getInstance().getData(key)).clone();
                    return;
                }
                return;
            }
            this.mEditOrderOffer = ((OrderOffer) data2).clone();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        Ensighten.evaluateEvent(this, "onSaveInstanceState", new Object[]{outState});
        super.onSaveInstanceState(outState);
        if (this.mOrderOffer != null) {
            DataPasser.getInstance().putData("order_offer_saved_state_key", this.mOrderOffer);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(this.mFromProduct ? C2658R.layout.fragment_offer_from_product : C2658R.layout.fragment_offer, container, false);
        this.mParent = (OfferActivity) getNavigationActivity();
        this.mParent.setOnOfferSelectedListener(this);
        if (this.mInEditMode) {
            this.mOffer = this.mEditOrderOffer.getOffer();
        } else {
            this.mOffer = this.mParent.getOffer();
            if (savedInstanceState != null) {
                this.mOrderOffer = (OrderOffer) DataPasser.getInstance().getData("order_offer_saved_state_key");
            }
        }
        this.orderOfferProductsApplied = new ArrayList();
        if (this.mOffer != null) {
            int intValue;
            trackOffer();
            DataLayerManager.getInstance().setOffer(this.mOffer, null);
            if (this.mInEditMode) {
                this.mParent.setTitle(getResources().getString(C2658R.string.offer_from_basket_title));
            } else {
                String string;
                if (isPunchCardOffer()) {
                    string = getResources().getString(C2658R.string.punchcard_offer_title);
                } else {
                    string = this.mOffer.getName();
                }
                this.mTitle = string;
                getNavigationActivity().setTitle(this.mTitle);
                this.mParent.getABTitle().setTextColor(getResources().getColor(C2658R.color.mcd_yellow));
            }
            TextView offerSubTitle = (TextView) rootView.findViewById(C2358R.C2357id.offer_subtitle);
            if (this.mOffer.getSubtitle() != null) {
                offerSubTitle.setText(this.mOffer.getSubtitle());
            } else {
                offerSubTitle.setVisibility(8);
            }
            TextView offerDescription = (TextView) rootView.findViewById(C2358R.C2357id.offer_description);
            if (this.mOffer.getShortDescription() != null) {
                offerDescription.setText(this.mOffer.getShortDescription());
                offerDescription.setTextSize(14.0f);
            } else {
                offerDescription.setVisibility(8);
            }
            ((TextView) rootView.findViewById(C2358R.C2357id.offer_expiration)).setText(String.format(getResources().getString(C2658R.string.offer_valid_from_through), new Object[]{UIUtils.formatDateMonthDayYear(this.mOffer.getLocalValidFrom()), UIUtils.formatDateMonthDayYear(this.mOffer.getLocalValidThrough())}));
            if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.hideTotalInOfferDetails")) {
                rootView.findViewById(C2358R.C2357id.total_price_energy).setVisibility(8);
            }
            this.mTotalCaloriesText = (TextView) rootView.findViewById(C2358R.C2357id.cal_total_value);
            this.mTotalCaloriesText.setVisibility(4);
            this.mTotalPriceText = (TextView) rootView.findViewById(C2358R.C2357id.total_price);
            this.mTotalPriceText.setText(getPriceText());
            ViewPager punchPager = (ViewPager) rootView.findViewById(C2358R.C2357id.punch_pager);
            View punchPagerContainer = rootView.findViewById(C2358R.C2357id.punch_pager_container);
            ImageView offerImage = (ImageView) rootView.findViewById(C2358R.C2357id.offer_image);
            final RadioGroup pagerIndicatorGroup = (RadioGroup) rootView.findViewById(C2358R.C2357id.pager_indicator);
            this.mPunchcardSelectItemTextView = (TextView) rootView.findViewById(C2358R.C2357id.offer_punchard_message);
            if (isPunchCardOffer()) {
                int i;
                rootView.findViewById(C2358R.C2357id.text_punchcard_terms).setVisibility(0);
                this.mParent.getABTitle().setText(C2658R.string.punchcard_offer_title);
                punchPagerContainer.setVisibility(0);
                punchPager.setVisibility(0);
                offerImage.setVisibility(8);
                intValue = this.mOffer.getCurrentPunch() == null ? 0 : this.mOffer.getCurrentPunch().intValue();
                if (this.mOffer.getTotalPunch() == null) {
                    i = 0;
                } else {
                    i = this.mOffer.getTotalPunch().intValue();
                }
                PunchcardOfferPagerAdapter adapter = new PunchcardOfferPagerAdapter(intValue, i, getChildFragmentManager(), this.mOffer);
                punchPager.setAdapter(adapter);
                if (adapter.getCount() > 1) {
                    for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                        UIUtils.addPagerIndicatorDot(i2, getNavigationActivity(), pagerIndicatorGroup);
                    }
                    pagerIndicatorGroup.check(0);
                    punchPager.setOnPageChangeListener(new OnPageChangeListener() {
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
                        }

                        public void onPageSelected(int position) {
                            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
                            if (OfferFragment.this.getActivity() != null) {
                                pagerIndicatorGroup.check(OfferFragment.this.getResources().getIdentifier(String.valueOf(position), "id", OfferFragment.this.getActivity().getPackageName()));
                            }
                        }

                        public void onPageScrollStateChanged(int state) {
                            Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
                        }
                    });
                }
            } else {
                punchPagerContainer.setVisibility(8);
                punchPager.setVisibility(8);
                offerImage.setVisibility(0);
                Glide.with(getContext()).load(this.mOffer.getLargeImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_meal_gray).into(offerImage);
            }
            this.mApplyToOrderButton = (Button) rootView.findViewById(C2358R.C2357id.apply_to_order_btn);
            initModules(rootView);
            configureRedeemButton(rootView);
            View notInterestedButton = (Button) rootView.findViewById(C2358R.C2357id.not_interested_btn);
            Boolean showNotInterestedButton = (Boolean) Configuration.getSharedInstance().getValueForKey("interface.offers.showNotInterestedButton");
            intValue = (showNotInterestedButton == null || !showNotInterestedButton.booleanValue()) ? 8 : 0;
            notInterestedButton.setVisibility(intValue);
            notInterestedButton.setOnClickListener(new C33462());
            DataLayerClickListener.setDataLayerTag(notInterestedButton, "NotInterestedButtonPressed");
            View termsInfoButton = (ImageButton) rootView.findViewById(C2358R.C2357id.info_button);
            termsInfoButton.setOnClickListener(new C33473());
            DataLayerClickListener.setDataLayerTag(termsInfoButton, "LegalInfoTapped");
            if (this.mInEditMode) {
                this.mApplyToOrderButton.setText(getActivity().getResources().getString(C2658R.string.update_mobile_order_lbl));
            }
            final View view = rootView;
            this.mApplyToOrderButton.setOnClickListener(new OnClickListener() {

                /* renamed from: com.mcdonalds.app.offers.OfferFragment$4$1 */
                class C33481 implements DialogInterface.OnClickListener {
                    C33481() {
                    }

                    public void onClick(DialogInterface dialog, int which) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                        dialog.dismiss();
                        OfferFragment.this.basketWillBeDisplayed();
                        OfferFragment.this.startActivity(BasketActivity.class, "basket");
                    }
                }

                /* renamed from: com.mcdonalds.app.offers.OfferFragment$4$2 */
                class C33492 implements DialogInterface.OnClickListener {
                    C33492() {
                    }

                    public void onClick(DialogInterface dialog, int which) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                        dialog.dismiss();
                    }
                }

                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    final Order order = Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$400", new Object[]{OfferFragment.this}) ? Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$500", new Object[]{OfferFragment.this}) : OrderingManager.getInstance().getCurrentOrder();
                    if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$600", new Object[]{OfferFragment.this, order}) || !Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$700", new Object[]{OfferFragment.this})) {
                        return;
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$800", new Object[]{OfferFragment.this}) == null || !Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$800", new Object[]{OfferFragment.this}).booleanValue()) {
                        ((ScrollView) view.findViewById(2131820653)).fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
                        return;
                    }
                    Iterator it;
                    if (!(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).isPunchCard() || Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$400", new Object[]{OfferFragment.this}))) {
                        for (OrderOffer orderOffer : order.getOffers()) {
                            if (!orderOffer.getOffer().isPunchCard()) {
                                MCDAlertDialogBuilder.withContext(OfferFragment.this.getNavigationActivity()).setTitle((int) C2658R.string.offer_already_in_basket_title).setMessage((int) C2658R.string.offer_already_in_basket_message).setPositiveButton((int) C2658R.string.f6083ok, new C33492()).setNegativeButton((int) C2658R.string.offer_go_to_basket_button, new C33481()).create().show();
                                return;
                            }
                        }
                    }
                    OfferFragment.this.basketWillBeDisplayed();
                    boolean addedToOrder = true;
                    if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$400", new Object[]{OfferFragment.this})) {
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).isPunchCard()) {
                            it = Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1100", new Object[]{OfferFragment.this}).iterator();
                            while (it.hasNext()) {
                                order.addProduct(((OrderOfferProduct) it.next()).getSelectedProductOption());
                            }
                            OfferFragment.access$1002(OfferFragment.this, null);
                        } else if (!order.addOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1000", new Object[]{OfferFragment.this}))) {
                            addedToOrder = false;
                            UIUtils.showInvalidDayPartsError(OfferFragment.this.getNavigationActivity());
                        }
                        OfferFragment.this.getNavigationActivity().getIntent().putExtra("ADDED_TO_ORDER", true);
                        HashMap<String, Object> jiceMap = new HashMap();
                        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_APPLY_OFFER);
                        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(OfferFragment.this.getAnalyticsTitle()).setAction("On click").setLabel(BusinessArgs.EVENT_APPLY_TO_ORDER).setBusiness(BusinessArgs.getAddToMobileOrder(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}))).setJice(jiceMap).build());
                        AnalyticsUtils.trackEvent(new ArgBuilder().setLabel("apply_offer_to_order").setMapping(BusinessArgs.KEY_OFFER_ID, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getOfferId()).setMapping(BusinessArgs.KEY_OFFER_NAME, Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getName()).build());
                        if (addedToOrder) {
                            UIUtils.startActivityIndicator(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).getString(C2658R.string.updating_offer));
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$300", new Object[]{OfferFragment.this}).selectOffersForPurchase(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1200", new Object[]{OfferFragment.this}).getUserName(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).getOfferId(), new AsyncListener() {
                                public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                    UIUtils.stopActivityIndicator();
                                    OfferFragment.access$1300(OfferFragment.this);
                                    OrderingManager.getInstance().updateCurrentOrder(order);
                                    if (OfferFragment.this.isActivityAlive()) {
                                        if (OfferFragment.this.getNavigationActivity() == null) {
                                            OfferFragment.this.getActivity().setResult(-1, OfferFragment.this.getNavigationActivity().getIntent());
                                        }
                                        OfferFragment.this.getActivity().finish();
                                        if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$400", new Object[]{OfferFragment.this})) {
                                            OfferFragment.this.startActivity(BasketActivity.class, "basket");
                                        }
                                    }
                                }
                            });
                        }
                    } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$100", new Object[]{OfferFragment.this}).isPunchCard()) {
                        throw new RuntimeException("Editing of Punchcard offers not supported");
                    } else {
                        order.removeOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$900", new Object[]{OfferFragment.this}));
                        order.addOffer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1000", new Object[]{OfferFragment.this}));
                        OrderingManager.getInstance().updateCurrentOrder(order);
                        OfferFragment.this.getActivity().setResult(-1);
                        OfferFragment.this.getActivity().finish();
                    }
                }
            });
            DataLayerClickListener.setDataLayerTag(this.mApplyToOrderButton, "ApplyToOrderButtonPressed");
            initMap(rootView);
            HashMap<String, Object> jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_OFFER);
            jiceMap.put(JiceArgs.LABEL_OFFER_ID_KEY, String.valueOf(this.mOffer.getOfferId()));
            jiceMap.put(JiceArgs.LABEL_OFFER_TYPE_KEY, String.valueOf(this.mOffer.getOfferType().ordinal()));
            jiceMap.put(JiceArgs.LABEL_OFFER_TITLE_KEY, this.mOffer.getSubtitle());
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
        }
        return rootView;
    }

    private void trackBasketCreated() {
        Ensighten.evaluateEvent(this, "trackBasketCreated", null);
        Order order = OrderManager.getInstance().getCurrentOrder();
        if (order.getBasketCounter() == 0 && order.hasOffers() && order.getOffers().size() == 1) {
            AnalyticsUtils.trackEvent("/basket", "On click", "Basket Created");
        }
    }

    private boolean checkIfDeliveryOrPickUpMatchesOrder(Order order) {
        Ensighten.evaluateEvent(this, "checkIfDeliveryOrPickUpMatchesOrder", new Object[]{order});
        boolean isDeliveryOnly;
        if (!this.mOffer.isDeliveryOffer() || this.mOffer.isPickupOffer()) {
            isDeliveryOnly = false;
        } else {
            isDeliveryOnly = true;
        }
        boolean isPickUpOnly;
        if (!this.mOffer.isPickupOffer() || this.mOffer.isDeliveryOffer()) {
            isPickUpOnly = false;
        } else {
            isPickUpOnly = true;
        }
        if (order.isDelivery() && isPickUpOnly) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.offer_unavailable).setMessage(getString(C2658R.string.offer_unavailable_for_pod, getString(C2658R.string.pickup), getString(C2658R.string.delivery))).setPositiveButton((int) C2658R.string.f6083ok, new C33525()).create().show();
            DataLayerManager.getInstance().recordError("Offer unavailable");
            return false;
        } else if (order.isDelivery() || !isDeliveryOnly) {
            return true;
        } else {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.offer_unavailable).setMessage(getString(C2658R.string.offer_unavailable_for_pod, getString(C2658R.string.delivery), getString(C2658R.string.pickup))).setPositiveButton((int) C2658R.string.f6083ok, new C33536()).create().show();
            DataLayerManager.getInstance().recordError("Offer unavailable");
            return false;
        }
    }

    private boolean checkIfOfferAvailableInStore() {
        int storeId;
        String storeName;
        Ensighten.evaluateEvent(this, "checkIfOfferAvailableInStore", null);
        List<Integer> resturants = this.mOffer.getRestaurants();
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            storeId = store.getStoreId();
        } else {
            storeId = 0;
        }
        if (store.getStoreFavoriteName() != null) {
            storeName = store.getStoreFavoriteName();
        } else {
            storeName = store.getAddress1();
        }
        if (!ListUtils.isNotEmpty(resturants)) {
            return true;
        }
        if (resturants.contains(Integer.valueOf(storeId))) {
            return true;
        }
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.offer_unavailable).setMessage(getString(C2658R.string.restaurant_cant_honor_this_offer, storeName)).setPositiveButton((int) C2658R.string.f6083ok, new C33547()).create().show();
        return false;
    }

    private void trackOffer() {
        Ensighten.evaluateEvent(this, "trackOffer", null);
        if (this.mFromProduct) {
            SparseArray customs = new SparseArray();
            customs.put(25, this.mOffer.getName());
            customs.put(24, String.valueOf(this.mOffer.getOfferType().ordinal()));
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Related Offer", customs);
        }
    }

    private void configureRedeemButton(View rootView) {
        Ensighten.evaluateEvent(this, "configureRedeemButton", new Object[]{rootView});
        View redeemAtRestaurantButton = (Button) rootView.findViewById(C2358R.C2357id.redeem_at_restaurant_btn);
        if (AppParameters.getOfferOperationMode() == OffersOperationType.OnlyMobile.getValue() || !this.mOffer.isPickupOffer()) {
            redeemAtRestaurantButton.setVisibility(8);
            return;
        }
        redeemAtRestaurantButton.setOnClickListener(new C33568());
        DataLayerClickListener.setDataLayerTag(redeemAtRestaurantButton, "RedeemButtonPressed");
    }

    private boolean isPunchCardOffer() {
        Ensighten.evaluateEvent(this, "isPunchCardOffer", null);
        return this.mOffer != null && this.mOffer.isPunchCard();
    }

    private void initMap(View view) {
        Ensighten.evaluateEvent(this, "initMap", new Object[]{view});
        View mapLayout = (RelativeLayout) view.findViewById(C2358R.C2357id.map_widget_container);
        mapLayout.setOnClickListener(new C33579());
        DataLayerClickListener.setDataLayerTag(mapLayout, "ExpandMapPressed");
        this.mMapViewHolder = new MapWidgetViewHolder(mapLayout);
        this.mMapViewHolder.getName().setText("");
        this.mMapViewHolder.getHours().setText("");
        this.mMapViewHolder.getIsOpen().setText("");
        this.mMapViewHolder.getDistance().setText("");
        this.mMapViewHolder.getmImageButton().setOnClickListener(new C332010());
        DataLayerClickListener.setDataLayerTag(this.mMapViewHolder.getmImageButton(), "ExpandMapPressed");
        this.mMapFragment = new MapManager(getActivity(), this);
        getChildFragmentManager().beginTransaction().add(C2358R.C2357id.map_container, this.mMapFragment.getFragment(), "MAP").commit();
    }

    private void initModules(View view) {
        boolean noPickupDelivery = true;
        Ensighten.evaluateEvent(this, "initModules", new Object[]{view});
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        this.mOffersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        this.mNutritionModule = (NutritionModule) ModuleManager.getModule(NutritionModule.NAME);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mStoreLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
        if (this.mOffer.isPickupOffer() || this.mOffer.isDeliveryOffer()) {
            noPickupDelivery = false;
        }
        if (this.mOrderingModule == null || OrderManager.getInstance().getCurrentStore() == null || !OrderManager.getInstance().getCurrentStore().hasMobileOrdering().booleanValue() || AppParameters.getOfferOperationMode() == OffersOperationType.OnlyInStore.getValue() || noPickupDelivery) {
            this.mApplyToOrderButton.setVisibility(8);
        } else {
            performEditModeCheck();
            this.listener = new OrderOfferListener(view);
            if (this.mInEditMode && this.mEditOrderOffer != null) {
                new Handler(Looper.getMainLooper()).post(new C332111());
            } else if (this.mOrderOffer == null) {
                UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.progress_update_products_msg);
                this.mCustomerModule.getCatalogUpdated(this.mCustomerProfile, new C332212());
            }
            this.mApplyToOrderButton.setVisibility(0);
        }
        if (this.mMapLoaded) {
            refreshMapWidget();
        }
    }

    private void performEditModeCheck() {
        Ensighten.evaluateEvent(this, "performEditModeCheck", null);
        Order order = OrderingManager.getInstance().getCurrentOrder();
        if (order != null && order.getOffers() != null) {
            for (OrderOffer orderOffer : order.getOffers()) {
                if (orderOffer.getOffer().getOfferId().equals(this.mOffer.getOfferId())) {
                    this.mInEditMode = true;
                    if (this.mEditOrder == null) {
                        this.mEditOrder = Order.cloneOrderForEditing(order);
                        this.mEditOrderOffer = orderOffer.clone();
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (!(!isPunchCardOffer() || this.mSavedPunchCardAddItemProduct == null || this.mOrderOffer == null)) {
            this.mOrderOffer.getOrderOfferProducts().add(this.mSavedPunchCardAddItemProduct);
            this.mSavedPunchCardAddItemProduct = null;
        }
        if (this.mNeedsRefresh) {
            initModules(getView());
            this.mNeedsRefresh = false;
        }
    }

    public void onPause() {
        super.onPause();
        UIUtils.stopActivityIndicator();
        this.mMapUpdated = false;
        this.mNeedsRefresh = true;
        if (isPunchCardOffer() && this.mOrderingModule != null && this.mOrderOffer != null) {
            if (this.mOrderOffer.getOrderOfferProducts() == null || this.mOrderOffer.getOrderOfferProducts().size() == 0) {
                this.mNeedsRefresh = false;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void basketWillBeDisplayed() {
        Ensighten.evaluateEvent(this, "basketWillBeDisplayed", null);
        if (isPunchCardOffer() && this.mOrderOffer != null && this.mOrderOffer.getOrderOfferProducts() != null && this.mOrderOffer.getOrderOfferProducts().size() > 0) {
            if (((OrderOfferProduct) this.mOrderOffer.getOrderOfferProducts().get(this.mOrderOffer.getOrderOfferProducts().size() - 1)).getSelectedProductOption() == null || this.mOrderOffer.getOrderOfferProducts().size() == this.mOffer.getTotalPunch().intValue()) {
                this.mSavedPunchCardAddItemProduct = (OrderOfferProduct) this.mOrderOffer.getOrderOfferProducts().get(this.mOrderOffer.getOrderOfferProducts().size() - 1);
                this.orderOfferProductsApplied.clear();
                for (OrderOfferProduct tmpProduct : this.mOrderOffer.getOrderOfferProducts()) {
                    this.orderOfferProductsApplied.add(tmpProduct);
                }
                this.mOrderOffer.getOrderOfferProducts().remove(this.mOrderOffer.getOrderOfferProducts().size() - 1);
            }
        }
    }

    public void onOfferProductSelected(int key, OrderProduct orderProduct) {
        Object[] objArr = new Object[]{new Integer(key), orderProduct};
        Ensighten.evaluateEvent(this, "onOfferProductSelected", objArr);
        if (orderProduct != null) {
            View productView = (View) this.mProductViews.get(key);
            OfferItemData itemData = (OfferItemData) this.mItemDataList.get(key);
            if (productView != null) {
                TextView productName = (TextView) productView.findViewById(C2358R.C2357id.name);
                productName.setText(orderProduct.getProduct().getLongName());
                if (!(isPunchCardOffer() || itemData.orderOfferProduct == null || itemData.orderOfferProduct.getOfferProduct() == null || itemData.orderOfferProduct.getOfferProduct().getAction() == null)) {
                    productName.setTextColor(getResources().getColor(C2658R.color.mcd_red));
                }
                DataLayerManager.getInstance().setOffer(this.mOffer, null);
                ImageView thumbnail = (ImageView) productView.findViewById(C2358R.C2357id.food_image_small);
                thumbnail.setBackgroundResource(0);
                ImageInfo thumbnailImage = orderProduct.getProduct().getThumbnailImage();
                if (thumbnailImage != null) {
                    Glide.with(getContext()).load(thumbnailImage.getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(thumbnail);
                }
                OrderOfferProduct orderOfferProduct = itemData.orderOfferProduct;
                orderOfferProduct.setSelectedProductOption(orderProduct);
                this.mSelectedOrderOfferProducts.put(key, orderOfferProduct);
                refreshDataForOffer(getView());
                PriceType priceType = OrderingManager.getInstance().getCurrentOrderPriceType();
                if (isPunchCardOffer()) {
                    this.mTotalPrice = Double.valueOf(this.mTotalPrice.doubleValue() + orderProduct.getProduct().getPrice(priceType));
                    this.mTotalPriceText.setText(getPriceText());
                    if (!(this.mOrderOffer == null || AppUtils.hideNutritionOnOrderingPages())) {
                        this.mTotalCaloriesText.setText(AppUtils.getEnergyTextForOrderOffer(this.mOrderOffer, OrderProductUtils.getTotalEnergyUnit(orderProduct)));
                        this.mTotalCaloriesText.setVisibility(0);
                    }
                }
                int size = this.mItemDataList.size();
                for (int i = 0; i < size; i++) {
                    if (i != key) {
                        OrderOfferProduct product = ((OfferItemData) this.mItemDataList.get(i)).orderOfferProduct;
                        if (product != null && product.isBuyOneGetSame()) {
                            onOfferProductSelected(i, orderProduct);
                        }
                    }
                }
            }
        }
    }

    public void onMapAvailable() {
        Ensighten.evaluateEvent(this, "onMapAvailable", null);
        if (this.mMapFragment.getMap() != null) {
            this.mMap = this.mMapFragment.getMap();
            this.mMapFragment.setCallback(null);
            this.mMap.setOnMapClickListener(this);
            this.mMap.setOnMarkerClickListener(new C332313());
            this.mMap.configure();
            this.mMap.setMyLocationEnabled(LocationServicesManager.isLocationEnabled(getContext()));
            this.mMapLoaded = true;
            if (this.mStoreLocatorModule != null) {
                refreshMapWidget();
            }
        }
    }

    public void onMapError(Dialog dialog) {
        Ensighten.evaluateEvent(this, "onMapError", new Object[]{dialog});
        if (this.mMapViewHolder != null) {
            this.mMapViewHolder.getView().setVisibility(8);
        }
    }

    public void onMapClick(LatLng latLng) {
        Ensighten.evaluateEvent(this, "onMapClick", new Object[]{latLng});
        findInStore();
    }

    private void findInStore() {
        Ensighten.evaluateEvent(this, "findInStore", null);
        if (OrderManager.getInstance().getCurrentStore() != null) {
            OffersStoreLocatorController offersStoreLocatorController = new Builder().useOffer(this.mOffer).withUrlNavigationFragment(this).isMapOnly(false).hasCurrentStoreSelectionMode(false).shouldAutoSelectClosestStore(false).shouldDismissOnPlaceOrder(false).withStoreIds(this.mOffer.getRestaurants()).withOfferSelectionType(this.mOfferSelectionType).withTargetMarkerStore(this.mInitialTargetStore).create();
            Bundle extras = new Bundle();
            extras.putBoolean("OFFERS_MODE", true);
            extras.putBoolean("EXTRA_ALLOWS_SELECTION", true);
            if (this.mMap != null) {
                extras.putSerializable("EXTRA_INITIAL_CAMERA_POSITION", this.mMap.getCameraPosition());
            }
            extras.putInt("offer_selection_type", this.mOfferSelectionType.ordinal());
            extras.putInt(URLNavigationActivity.DATA_PASSER_KEY, DataPasser.getInstance().putData(offersStoreLocatorController));
            startActivity(OffersInStoreActivity.class, "store_locator", extras);
        }
    }

    private boolean canAddMoreProductsToPunchCard() {
        Ensighten.evaluateEvent(this, "canAddMoreProductsToPunchCard", null);
        return this.mOffer.getCurrentPunch().intValue() + this.mOrderOffer.getOrderOfferProducts().size() < this.mOffer.getTotalPunch().intValue() || !Configuration.getSharedInstance().getBooleanForKey("interface.offers.limitPunchCardToTotalPunch");
    }

    private boolean refreshPunchCardOptionsIfNecessary(final View rootView) {
        Ensighten.evaluateEvent(this, "refreshPunchCardOptionsIfNecessary", new Object[]{rootView});
        boolean ret = false;
        int originalViewVisibility = this.mPunchcardSelectItemTextView.getVisibility();
        int newViewVisibility = 8;
        if (isPunchCardOffer()) {
            if (this.mOrderingModule != null) {
                if (!(this.mOrderOffer.getOrderOfferProducts().isEmpty() || ((OrderOfferProduct) this.mOrderOffer.getOrderOfferProducts().get(this.mOrderOffer.getOrderOfferProducts().size() - 1)).getSelectedProductOption() == null || this.mOffer.getProductSets().isEmpty() || !canAddMoreProductsToPunchCard())) {
                    OrderOfferProduct.createOrderOfferProduct((OfferProduct) this.mOffer.getProductSets().get(0), this.mOrderingModule, false, new AsyncListener<OrderOfferProduct>() {
                        public void onResponse(OrderOfferProduct response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (exception == null) {
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1000", new Object[]{OfferFragment.this}).getOrderOfferProducts().add(response);
                                OfferFragment.access$1700(OfferFragment.this, rootView);
                                return;
                            }
                            AsyncException.report(exception);
                        }
                    });
                    ret = true;
                }
                if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.showPunchcardSelectItemView")) {
                    newViewVisibility = 0;
                } else {
                    newViewVisibility = 8;
                }
            } else {
                newViewVisibility = 8;
                ret = true;
            }
        }
        if (newViewVisibility != originalViewVisibility) {
            this.mPunchcardSelectItemTextView.setVisibility(newViewVisibility);
        }
        return ret;
    }

    private void refreshDataForOffer(View rootView) {
        Ensighten.evaluateEvent(this, "refreshDataForOffer", new Object[]{rootView});
        if (this.mOffer.isBuyNGetMOffer()) {
            this.mOrderOffer.reorderOfferOrderProductsForBuyNGetM(OrderingManager.getInstance().getCurrentOrderPriceType());
        }
        this.mPunchcardSelectItemTextView.setVisibility(isPunchCardOffer() ? 0 : 8);
        if (!refreshPunchCardOptionsIfNecessary(rootView)) {
            LinearLayout itemsLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.item_layout);
            ItemCounter itemCount = new ItemCounter();
            itemsLayout.removeAllViews();
            this.mItemDataList.clear();
            this.mProductViews.clear();
            if (this.mOrderOffer == null) {
                return;
            }
            if (this.mOrderOffer.getOrderOfferProducts() == null) {
                this.mAllChoicesSolved = Boolean.valueOf(true);
                this.mApplyToOrderButton.setBackgroundResource(C2358R.C2359drawable.button_red);
                return;
            }
            LayoutInflater inflater = LayoutInflater.from(this.mParent);
            this.mAllChoicesSolved = null;
            for (int i = 0; i < this.mOrderOffer.getOrderOfferProducts().size(); i++) {
                final OrderOfferProduct orderOfferProduct = (OrderOfferProduct) this.mOrderOffer.getOrderOfferProducts().get(i);
                ProductDetailsItem productDetails = new ProductDetailsItem(inflater.inflate(C2658R.layout.product_details_item, null));
                int margin = UIUtils.dpAsPixels(this.mParent, 4);
                LayoutParams params = new LayoutParams(-1, UIUtils.dpAsPixels(this.mParent, 48), 1.0f);
                params.setMargins(0, margin, 0, 0);
                productDetails.getView().setLayoutParams(params);
                this.mProductViews.put(itemCount.value(), productDetails.getView());
                if (orderOfferProduct.getSelectedProductOption() == null) {
                    this.mItemDataList.put(itemCount.value(), new OfferItemData(orderOfferProduct, orderOfferProduct.getSelectedProductOption()));
                    itemsLayout.addView(productDetails.getView());
                    productDetails.getSelectedButton().setVisibility(8);
                    productDetails.getDisclosureArrow().setVisibility(0);
                    productDetails.getInfoButton().setVisibility(8);
                    productDetails.getHatButton().setVisibility(8);
                    if (this.mParent.hasSelectedOfferProduct(itemCount.value())) {
                        if (this.mParent.getSelectedOfferProduct(itemCount.value()).getThumbnailImage() != null) {
                            productDetails.getName().setText(this.mParent.getSelectedOfferProduct(itemCount.value()).getName());
                            Glide.with(getContext()).load(this.mParent.getSelectedOfferProduct(itemCount.value()).getThumbnailImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(productDetails.getFoodImageIcon());
                        }
                        productDetails.getName().setTextColor(getResources().getColor(C2658R.color.dark_gray_2));
                    } else {
                        productDetails.getFoodImageIcon().setImageResource(C2358R.C2359drawable.icon_meal_gray);
                        productDetails.getName().setText(getLocalizedAlias(orderOfferProduct.getOfferProduct().getAlias()));
                        productDetails.getName().setTextColor(getResources().getColor(C2658R.color.medium_gray_1));
                    }
                    productDetails.getSpecialInstructions().setVisibility(8);
                    final int finalPosition = itemCount.value();
                    if (!isPunchCardOffer()) {
                        if (orderOfferProduct.getOfferProduct() != null) {
                            if (!orderOfferProduct.hasMultipleProducts()) {
                                OrderingModule orderingModuleModule = (OrderingModule) ModuleManager.getModule("ordering");
                                if (orderOfferProduct.getOfferProduct().getProducts().size() == 1) {
                                    final ProductDetailsItem productDetailsItem = productDetails;
                                    orderingModuleModule.getProductForExternalId(((OfferProductOption) orderOfferProduct.getOfferProduct().getProducts().get(0)).getProductCode(), (AsyncListener) new AsyncListener<Product>() {

                                        /* renamed from: com.mcdonalds.app.offers.OfferFragment$18$1 */
                                        class C33291 implements OnClickListener {
                                            C33291() {
                                            }

                                            public void onClick(View v) {
                                                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).showOfferProductSelection(finalPosition, orderOfferProduct.getOfferProduct());
                                            }
                                        }

                                        public void onResponse(Product response, AsyncToken token, AsyncException exception) {
                                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                            if (exception != null || response == null) {
                                                productDetailsItem.getView().setOnClickListener(new C33291());
                                                DataLayerClickListener.setDataLayerTag(productDetailsItem.getView(), "ProductItemPressed");
                                                return;
                                            }
                                            OfferFragment.this.onOfferProductSelected(finalPosition, OrderProduct.createProduct(response, Integer.valueOf(1)));
                                        }
                                    });
                                }
                            } else if (!orderOfferProduct.isBuyOneGetSame()) {
                                productDetails.getView().setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).showOfferProductSelection(finalPosition, orderOfferProduct.getOfferProduct());
                                    }
                                });
                                DataLayerClickListener.setDataLayerTag(productDetails.getView(), "ProductOptionItemPressed");
                            }
                        }
                        this.mAllChoicesSolved = Boolean.valueOf(false);
                    } else if (orderOfferProduct.getOfferProduct().getProducts().size() > 1) {
                        productDetails.getView().setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).showOfferProductSelection(finalPosition, orderOfferProduct.getOfferProduct());
                            }
                        });
                        DataLayerClickListener.setDataLayerTag(productDetails.getView(), "ProductItemPressed");
                        if (this.mAllChoicesSolved == null) {
                            this.mAllChoicesSolved = Boolean.valueOf(false);
                        }
                    } else {
                        productDetails.getView().setOnClickListener(new OnClickListener() {

                            /* renamed from: com.mcdonalds.app.offers.OfferFragment$16$1 */
                            class C33261 implements AsyncListener<Product> {
                                C33261() {
                                }

                                public void onResponse(Product response, AsyncToken token, AsyncException exception) {
                                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                    if (exception == null && response != null) {
                                        OfferFragment.this.onOfferProductSelected(finalPosition, OrderProduct.createProduct(response, Integer.valueOf(1)));
                                    }
                                }
                            }

                            public void onClick(View v) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$1600", new Object[]{OfferFragment.this}).getProductForExternalId(((OfferProductOption) orderOfferProduct.getOfferProduct().getProducts().get(0)).getProductCode(), new C33261());
                            }
                        });
                        DataLayerClickListener.setDataLayerTag(productDetails.getView(), "ProductOptionItemPressed");
                        if (this.mAllChoicesSolved == null) {
                            this.mAllChoicesSolved = Boolean.valueOf(true);
                        }
                    }
                    itemCount.increment();
                } else {
                    OrderProduct product = orderOfferProduct.getSelectedProductOption();
                    ProductUtils.populateProductIngredients(product, this.mOrderingModule);
                    ProductUtils.populateProductChoices(product, this.mOrderingModule);
                    if (this.mAllChoicesSolved == null) {
                        this.mAllChoicesSolved = Boolean.valueOf(addProductItemsToList(orderOfferProduct, product, itemsLayout, itemCount));
                    } else {
                        boolean z = addProductItemsToList(orderOfferProduct, product, itemsLayout, itemCount) && this.mAllChoicesSolved.booleanValue();
                        this.mAllChoicesSolved = Boolean.valueOf(z);
                    }
                    this.mHasProductSelection = true;
                }
                if (this.mAllChoicesSolved.booleanValue()) {
                    this.mApplyToOrderButton.setBackgroundResource(C2358R.C2359drawable.button_red);
                }
            }
            if (!this.mHasProductSelection && this.mPreSelectedProduct != null) {
                onOfferProductSelected(getPreselectedProductPosition(), OrderProduct.createProduct(this.mPreSelectedProduct, Integer.valueOf(1)));
            }
        }
    }

    private int getPreselectedProductPosition() {
        Ensighten.evaluateEvent(this, "getPreselectedProductPosition", null);
        for (int i = 0; i < this.mOffer.getProductSets().size(); i++) {
            for (OfferProductOption option : ((OfferProduct) this.mOffer.getProductSets().get(i)).getProducts()) {
                if (option.getProductCode().equalsIgnoreCase(this.mPreSelectedProduct.getExternalId().toString())) {
                    return i;
                }
            }
        }
        return 0;
    }

    private String getLocalizedAlias(@Nullable String alias) {
        Ensighten.evaluateEvent(this, "getLocalizedAlias", new Object[]{alias});
        if (!TextUtils.isEmpty(alias)) {
            int resId = getResources().getIdentifier("alias_" + alias.toLowerCase().replace(" ", "_"), "string", McDonaldsApplication.class.getPackage().getName());
            if (resId != 0) {
                return getString(resId);
            }
        }
        return getString(C2658R.string.select_choices_title);
    }

    private synchronized void refreshMapWidget() {
        Ensighten.evaluateEvent(this, "refreshMapWidget", null);
        if (this.mMapLoaded && this.mStoreLocatorModule != null) {
            this.mMapUpdated = true;
            if (this.mOfferSelectionType == OfferSelection.Current || this.mOfferSelectionType == OfferSelection.Favorite) {
                Store currStore = OrderManager.getInstance().getCurrentStore();
                if (currStore != null) {
                    this.mStoreLocatorModule.getStoresNearLatLongWithinRadius(Double.valueOf(currStore.getLatitude()), Double.valueOf(currStore.getLongitude()), this.DEFAULT_RADIUS, null, new C333119());
                }
            } else {
                this.mStoreLocatorModule.getStoresNearCurrentLocationWithinRadius(this.DEFAULT_RADIUS, null, new C333620());
            }
        }
    }

    private void setMapPins(List<Store> response, McdMap map) {
        Ensighten.evaluateEvent(this, "setMapPins", new Object[]{response, map});
        if (OrderManager.getInstance().getCurrentStore() != null && map != null) {
            Store store;
            map.clear();
            List<Store> sorted = new ArrayList();
            StoresManager manager = StoresManager.getInstance();
            if (response != null) {
                sorted.addAll(response);
                manager.updateFavoriteInfo(sorted);
                manager.sortByDistance(sorted);
            }
            StoresManager.getInstance().updateFavoriteInfo(sorted);
            List<Store> favorites = new ArrayList();
            List<Store> currentFavorites = this.mCustomerModule.getFavoriteStores();
            if (ListUtils.isNotEmpty(currentFavorites)) {
                favorites.addAll(currentFavorites);
                manager.sortByDistance(favorites);
            }
            boolean checkAvailability = (this.mOffer.getRestaurants() == null || this.mOffer.getRestaurants().isEmpty()) ? false : true;
            boolean storeSupportsOffers;
            switch (this.mOfferSelectionType) {
                case Current:
                    this.mInitialTargetStore = OrderManager.getInstance().getCurrentStore();
                    break;
                case Favorite:
                    if (!checkAvailability) {
                        for (Store store2 : favorites) {
                            if (store2.hasMobileOffers()) {
                                this.mInitialTargetStore = store2;
                                break;
                            }
                        }
                        break;
                    }
                    for (Store store22 : favorites) {
                        storeSupportsOffers = store22.hasMobileOffers();
                        if (this.mOffer.getRestaurants().contains(Integer.valueOf(store22.getStoreId())) && storeSupportsOffers) {
                            this.mInitialTargetStore = store22;
                            break;
                        }
                    }
                    break;
                    break;
                case Nearby:
                case FartherAway:
                    if (!checkAvailability) {
                        for (Store store222 : sorted) {
                            if (store222.hasMobileOffers()) {
                                this.mInitialTargetStore = store222;
                                break;
                            }
                        }
                        break;
                    }
                    for (Store store2222 : sorted) {
                        storeSupportsOffers = store2222.hasMobileOffers();
                        if (this.mOffer.getRestaurants().contains(Integer.valueOf(store2222.getStoreId())) && storeSupportsOffers) {
                            this.mInitialTargetStore = store2222;
                            break;
                        }
                    }
                    break;
                    break;
            }
            if (this.mInitialTargetStore == null) {
                this.mInitialTargetStore = OrderManager.getInstance().getCurrentStore();
                sorted.add(0, this.mInitialTargetStore);
            }
            this.mMapViewHolder.getName().setText(this.mInitialTargetStore.getName());
            String dailyStoreHours = UIUtils.getDailyStoreHoursString(getActivity(), this.mInitialTargetStore);
            if (TextUtils.isEmpty(dailyStoreHours)) {
                this.mMapViewHolder.getHours().setVisibility(8);
            } else {
                this.mMapViewHolder.getHours().setText(dailyStoreHours);
            }
            Location userLocation = null;
            try {
                userLocation = AppUtils.getUserLocation();
            } catch (IllegalStateException e) {
            }
            if (userLocation != null) {
                this.mMapViewHolder.getDistance().setText(UIUtils.distanceFromLocation(getActivity(), userLocation, this.mInitialTargetStore));
            }
            for (int i = 0; i < sorted.size(); i++) {
                store2222 = (Store) sorted.get(i);
                boolean isSelected = store2222.getStoreId() == this.mInitialTargetStore.getStoreId();
                boolean isValidForOffer = checkAvailability ? this.mOffer.getRestaurants().contains(Integer.valueOf(store2222.getStoreId())) && store2222.hasMobileOffers() : store2222.hasMobileOffers();
                int mapPinResId = isValidForOffer ? isSelected ? C2358R.C2359drawable.offer_pin_yellow_outline : C2358R.C2359drawable.offer_pin_yellow : isSelected ? C2358R.C2359drawable.pin_gray_outline : C2358R.C2359drawable.pin_gray;
                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(store2222.getLatitude(), store2222.getLongitude())).icon(mapPinResId).alpha(isSelected ? 1.0f : 0.75f));
                if (isSelected) {
                    this.mTargetMarker = marker;
                }
            }
            if (this.mTargetMarker != null) {
                this.mTargetMarker.showInfoWindow();
            }
            if (userLocation != null) {
                MapUtils.with(getActivity()).map(map).userLocation(userLocation).store(this.mInitialTargetStore).margin(45).move();
            } else {
                MapUtils.with(getActivity()).map(map).store(this.mInitialTargetStore).move(13.0f);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void productCustomizationsUpdated(OrderProduct product, int position) {
        Ensighten.evaluateEvent(this, "productCustomizationsUpdated", new Object[]{product, new Integer(position)});
        OfferItemData itemData = (OfferItemData) this.mItemDataList.get(position);
        if (itemData.isChoice) {
            ((Choice) itemData.product.getRealChoices().get(itemData.choiceIndex)).getSelection().setCustomizations(product.getCustomizations());
        } else {
            itemData.product.setCustomizations(product.getCustomizations());
        }
        refreshDataForOffer(getView());
    }

    /* Access modifiers changed, original: protected */
    public void productChoiceSelected(OrderProduct choice, int choiceIndex, int position) {
        Ensighten.evaluateEvent(this, "productChoiceSelected", new Object[]{choice, new Integer(choiceIndex), new Integer(position)});
        OfferItemData itemData = (OfferItemData) this.mItemDataList.get(position);
        if (!(!itemData.isChoice || itemData.product == null || itemData.product.getRealChoices() == null)) {
            ((Choice) itemData.product.getRealChoices().get(choiceIndex)).setSelection(choice);
        }
        this.mTotalPrice = Double.valueOf(this.mTotalPrice.doubleValue() + choice.getTotalPrice(OrderingManager.getInstance().getCurrentOrderPriceType()));
        this.mTotalPriceText.setText(getPriceText());
        refreshDataForOffer(getView());
    }

    private void onProductInfoButtonClicked(String foodId) {
        Ensighten.evaluateEvent(this, "onProductInfoButtonClicked", new Object[]{foodId});
        NavigationManager.getInstance().showNutrition(getActivity(), foodId, null, null, getNavigationActivity());
    }

    private void onProductCustomizeClicked(OrderProduct product, int position) {
        Ensighten.evaluateEvent(this, "onProductCustomizeClicked", new Object[]{product, new Integer(position)});
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "PDP - Customization");
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("ARG_PRODUCT", product);
        bundle.putInt("ARG_PRODUCT_INDEX", position);
        startActivityForResult(ProductCustomizationActivity.class, "product_customization", bundle, 45352);
    }

    private void onProductChoiceClicked(OrderProduct product, OrderProduct choice, int choiceIdx, int position) {
        Ensighten.evaluateEvent(this, "onProductChoiceClicked", new Object[]{product, choice, new Integer(choiceIdx), new Integer(position)});
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "PDP - Product chooser list");
        Intent intent = new Intent(getContext(), ChoiceSelectorActivity.class);
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("ARG_CHOICE_KEY", choice);
        bundle.putInt("ARG_INDEX", choiceIdx);
        bundle.putInt("ARG_PRODUCT_POSITION", position);
        bundle.putString("ARG_TITLE", getString(C2658R.string.title_activity_product_chooser));
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, 13098);
    }

    private boolean addProductItemsToList(OrderOfferProduct orderOfferProduct, OrderProduct orderProduct, LinearLayout itemsLayout, ItemCounter position) {
        Ensighten.evaluateEvent(this, "addProductItemsToList", new Object[]{orderOfferProduct, orderProduct, itemsLayout, position});
        LayoutInflater inflater = LayoutInflater.from(getNavigationActivity());
        boolean choicesSolved = true;
        if (!orderProduct.isMeal()) {
            return addProductToMeaLList(orderOfferProduct, orderProduct, itemsLayout, inflater, position);
        }
        if (orderProduct.getIngredients() != null) {
            for (OrderProduct ingredient : orderProduct.getIngredients()) {
                choicesSolved = addProductToMeaLList(orderOfferProduct, ingredient, itemsLayout, inflater, position) && choicesSolved;
            }
        }
        if (orderProduct.getRealChoices() == null) {
            return choicesSolved;
        }
        List<Choice> realChoices = orderProduct.getRealChoices();
        for (int choiceIdx = 0; choiceIdx < realChoices.size(); choiceIdx++) {
            Choice choice = (Choice) realChoices.get(choiceIdx);
            boolean hideSingleChoice = ProductUtils.hideSingleChoice();
            if (!choice.isSingleChoice() || !hideSingleChoice) {
                choicesSolved = addChoiceToMealList(orderProduct, choice, choiceIdx, itemsLayout, inflater, position) && choicesSolved;
            } else if (!com.mcdonalds.sdk.utils.ListUtils.isEmpty(choice.getOptions())) {
                OrderProduct choiceSolution = (OrderProduct) choice.getOptions().get(0);
                choiceSolution.setQuantity(1);
                choice.setSelection(choiceSolution);
            }
        }
        return choicesSolved;
    }

    private boolean addProductToMeaLList(OrderOfferProduct orderOfferProduct, OrderProduct product, LinearLayout itemsLayout, LayoutInflater inflater, ItemCounter position) {
        Ensighten.evaluateEvent(this, "addProductToMeaLList", new Object[]{orderOfferProduct, product, itemsLayout, inflater, position});
        if (product == null || product.getProduct() == null || orderOfferProduct == null || orderOfferProduct.getOfferProduct() == null) {
            return false;
        }
        final ProductDetailsItem item = new ProductDetailsItem(inflater.inflate(C2658R.layout.product_details_item, null));
        final int finalPosition = position.value();
        if (item.getName() == null) {
            return false;
        }
        int i;
        item.getName().setText(product.getProduct().getLongName());
        item.getSpecialInstructions().setText(OrderProductUtils.getCustomizationsString(product));
        item.getSelectedButton().setVisibility(8);
        if (orderOfferProduct.getOfferProduct().getProducts().size() > 1) {
            if (!orderOfferProduct.isBuyOneGetSame()) {
                final OrderOfferProduct orderOfferProduct2 = orderOfferProduct;
                item.getView().setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferFragment", "access$000", new Object[]{OfferFragment.this}).showOfferProductSelection(finalPosition, orderOfferProduct2.getOfferProduct());
                    }
                });
                DataLayerClickListener.setDataLayerTag(item.getView(), "ProductItemPressed");
            }
            item.getDisclosureArrow().setVisibility(0);
        } else {
            item.getDisclosureArrow().setVisibility(4);
        }
        int count = product.getProduct().getIngredients() == null ? 0 : product.getProduct().getIngredients().size();
        if (product.getProduct().getExtras() == null) {
            i = 0;
        } else {
            i = product.getProduct().getExtras().size();
        }
        count += i;
        if (Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton") || count <= 0) {
            item.getHatButton().setVisibility(8);
        } else {
            item.getHatButton().setVisibility(0);
            item.setHatButtonHighlighted(OrderProductUtils.getCustomizationsString(product));
            final OrderProduct orderProduct = product;
            item.getHatButton().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                    OfferFragment.access$2000(OfferFragment.this, orderProduct, finalPosition);
                }
            });
            DataLayerClickListener.setDataLayerTag(item.getHatButton(), "CustomizeButtonAction");
        }
        item.getInfoButton().setVisibility(8);
        if (!(AppUtils.hideNutritionIconOnOrderingPages() || this.mNutritionModule == null)) {
            this.mNutritionModule.getRecipeForExternalId(product.getProduct().getExternalId().toString(), new AsyncListener<NutritionRecipe>() {
                public void onResponse(final NutritionRecipe response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception == null) {
                        if (OfferFragment.this.getNavigationActivity() != null && response != null) {
                            item.getInfoButton().setVisibility(0);
                            item.getInfoButton().setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                                    OfferFragment.access$2100(OfferFragment.this, response.getId());
                                }
                            });
                            DataLayerClickListener.setDataLayerTag(item.getInfoButton(), "ProductInfoPressed");
                        }
                    } else if (exception.getErrorCode() != 0) {
                        AsyncException.report(exception);
                    }
                }
            });
        }
        if (!(product.getProduct() == null || product.getProduct().getThumbnailImage() == null || TextUtils.isEmpty(product.getProduct().getThumbnailImage().getUrl()))) {
            Glide.with(getContext()).load(product.getProduct().getThumbnailImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(item.getFoodImageIcon());
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, UIUtils.dpAsPixels(getNavigationActivity(), 4), 0, 0);
        item.getView().setLayoutParams(layoutParams);
        item.getView().setMinimumHeight(UIUtils.dpAsPixels(getNavigationActivity(), 50));
        itemsLayout.addView(item.getView());
        this.mItemDataList.put(position.value(), new OfferItemData(orderOfferProduct, product));
        position.increment();
        boolean choicesSolved = true;
        List<Choice> realChoices = product.getRealChoices();
        if (realChoices == null) {
            return true;
        }
        for (int choiceIdx = 0; choiceIdx < realChoices.size(); choiceIdx++) {
            choicesSolved = addChoiceToMealList(product, (Choice) realChoices.get(choiceIdx), choiceIdx, itemsLayout, inflater, position) && choicesSolved;
        }
        return choicesSolved;
    }

    private boolean addChoiceToMealList(OrderProduct product, OrderProduct choice, int choiceIdx, LinearLayout itemsLayout, LayoutInflater inflater, ItemCounter position) {
        Ensighten.evaluateEvent(this, "addChoiceToMealList", new Object[]{product, choice, new Integer(choiceIdx), itemsLayout, inflater, position});
        ProductDetailsItem productDetailsItem = new ProductDetailsItem(inflater.inflate(C2658R.layout.product_details_item, null));
        final int finalPosition = position.value();
        position.increment();
        final OrderProduct choiceSolution = ProductUtils.getActualChoice(choice);
        Boolean hasSolution = Boolean.valueOf(choiceSolution != null);
        itemsLayout.addView(productDetailsItem.getView());
        this.mItemDataList.put(finalPosition, new OfferItemData(null, product, choice, choiceIdx, true));
        productDetailsItem.getSelectedButton().setVisibility(8);
        productDetailsItem.getDisclosureArrow().setVisibility(0);
        String productName;
        if (hasSolution.booleanValue()) {
            int i;
            productDetailsItem.getName().setTextColor(getResources().getColor(C2658R.color.dark_gray_1));
            if (choiceSolution.getProduct().getId().equals("0")) {
                productName = ((Choice) choiceSolution.getRealChoices().get(0)).getSelection().getProduct().getLongName();
            } else {
                productName = choiceSolution.getProduct().getLongName();
            }
            productDetailsItem.getName().setText(productName);
            productDetailsItem.getSpecialInstructions().setText(OrderProductUtils.getCustomizationsString(choiceSolution));
            String nameDetails = ProductUtils.getNameDetailsString(choiceSolution);
            if (!TextUtils.isEmpty(nameDetails)) {
                productDetailsItem.getNameDetails().setVisibility(0);
                productDetailsItem.getNameDetails().setText(nameDetails);
            }
            boolean showUplift = ConfigurationUtils.shouldShowUpLiftPrice();
            productDetailsItem.setPriceUpliftTextVisible(false);
            if (showUplift) {
                if (ProductUtils.getProductTotalPrice(choiceSolution) - ((OrderingModule) ModuleManager.getModule("ordering")).getProductBasePrice(choice) >= 0.01d) {
                    productDetailsItem.setPriceUpliftTextVisible(true);
                    productDetailsItem.setPriceUpliftText(String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)}));
                }
            }
            int count = choiceSolution.getProduct().getIngredients() == null ? 0 : choiceSolution.getProduct().getIngredients().size();
            if (choiceSolution.getProduct().getExtras() == null) {
                i = 0;
            } else {
                i = choiceSolution.getProduct().getExtras().size();
            }
            count += i;
            if (Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton") || count <= 0) {
                productDetailsItem.getHatButton().setVisibility(8);
            } else {
                productDetailsItem.getHatButton().setVisibility(0);
                productDetailsItem.setHatButtonHighlighted(OrderProductUtils.getCustomizationsString(choiceSolution));
                productDetailsItem.getHatButton().setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                        OfferFragment.access$2000(OfferFragment.this, choiceSolution, finalPosition);
                    }
                });
                DataLayerClickListener.setDataLayerTag(productDetailsItem.getHatButton(), "CustomizeButtonAction");
            }
            productDetailsItem.getInfoButton().setVisibility(8);
            if (!(AppUtils.hideNutritionIconOnOrderingPages() || this.mNutritionModule == null)) {
                final ProductDetailsItem productDetailsItem2 = productDetailsItem;
                this.mNutritionModule.getRecipeForExternalId(choiceSolution.getProductCode(), new AsyncListener<NutritionRecipe>() {
                    public void onResponse(final NutritionRecipe response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (exception == null) {
                            if (OfferFragment.this.getNavigationActivity() != null && response != null) {
                                productDetailsItem2.getInfoButton().setVisibility(0);
                                productDetailsItem2.getInfoButton().setOnClickListener(new OnClickListener() {
                                    public void onClick(View view) {
                                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                                        OfferFragment.access$2100(OfferFragment.this, response.getId());
                                    }
                                });
                                DataLayerClickListener.setDataLayerTag(productDetailsItem2.getInfoButton(), "ProductInfoPressed");
                            }
                        } else if (exception.getErrorCode() != 0) {
                            AsyncException.report(exception);
                        }
                    }
                });
            }
        } else {
            productDetailsItem.getName().setTextColor(getResources().getColor(C2658R.color.medium_gray_1));
            productName = "";
            if (!(choice == null || choice.getProduct() == null)) {
                productName = choice.getProduct().getLongName();
            }
            productDetailsItem.getName().setText(productName);
            productDetailsItem.getSpecialInstructions().setVisibility(8);
            productDetailsItem.getInfoButton().setVisibility(8);
            productDetailsItem.getHatButton().setVisibility(8);
        }
        final OrderProduct orderProduct = product;
        final OrderProduct orderProduct2 = choice;
        final int i2 = choiceIdx;
        productDetailsItem.getView().setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                OfferFragment.access$2200(OfferFragment.this, orderProduct, orderProduct2, i2, finalPosition);
            }
        });
        DataLayerClickListener.setDataLayerTag(productDetailsItem.getView(), "ProductOptionItemPressed");
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, UIUtils.dpAsPixels(getNavigationActivity(), 4), 0, 0);
        productDetailsItem.getView().setLayoutParams(layoutParams);
        productDetailsItem.getView().setMinimumHeight(UIUtils.dpAsPixels(getNavigationActivity(), 50));
        if (choiceSolution != null) {
            if (choiceSolution.getProduct().getThumbnailImage() != null) {
                if (!TextUtils.isEmpty(choiceSolution.getProduct().getThumbnailImage().getUrl())) {
                    Glide.with(getContext()).load(choiceSolution.getProduct().getThumbnailImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(productDetailsItem.getFoodImageIcon());
                }
            } else if (!(((Choice) choiceSolution.getRealChoices().get(0)).getSelection().getProduct() == null || TextUtils.isEmpty(((Choice) choiceSolution.getRealChoices().get(0)).getSelection().getProduct().getThumbnailImage().getUrl()))) {
                Glide.with(getContext()).load(((Choice) choiceSolution.getRealChoices().get(0)).getSelection().getProduct().getThumbnailImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(productDetailsItem.getFoodImageIcon());
            }
        }
        this.mProductViews.put(finalPosition, productDetailsItem.getView());
        return hasSolution.booleanValue();
    }

    private String getPriceText() {
        Ensighten.evaluateEvent(this, "getPriceText", null);
        return String.format("$%s", new Object[]{this.mPriceFormat.format(this.mTotalPrice)});
    }
}
