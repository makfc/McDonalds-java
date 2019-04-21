package com.mcdonalds.app.ordering.productdetail;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.admaster.square.api.CustomEvent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.firstload.SelectStoreActivity;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductCustomizationActivity;
import com.mcdonalds.app.ordering.ProductRelatedOffersView;
import com.mcdonalds.app.ordering.ProductTotalPriceEnergy;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorActivity;
import com.mcdonalds.app.ordering.customization.ProductCustomizationFragment;
import com.mcdonalds.app.ordering.productdetail.MealAdapter.MealItemData;
import com.mcdonalds.app.ordering.productdetail.MealAdapter.OnMealChangedListener;
import com.mcdonalds.app.ordering.productdetail.MealAdapter.OnProductChoiceClickedListener;
import com.mcdonalds.app.ordering.productdetail.MealAdapter.OnProductCustomizeClickedListener;
import com.mcdonalds.app.ordering.productdetail.MealAdapter.OnProductInfoClickedListener;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.FavoriteInputViewHolder;
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
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.CustomerEventAction;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.provider.Contract.Favorites;
import com.mcdonalds.sdk.services.data.repository.StoreProductCategoryRepository;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.utils.ListUtils;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailsActivity extends URLBasketNavigationActivity implements OnMealChangedListener, OnProductChoiceClickedListener, OnProductCustomizeClickedListener, OnProductInfoClickedListener {
    public static final Integer REQUEST_CODE = Integer.valueOf(56731);
    private View mBasketBadgeContainer;
    private Boolean mBasketBadgeContainerIsHidden = Boolean.valueOf(true);
    private TextView mBasketBadgeLabel;
    private String mCategoryName;
    private int mCount;
    private CustomerModule mCustomerModule;
    private int mDayPartIndex;
    private Button mDecreaseQuantityButton;
    private TabLayout mDimensionTabs;
    private OrderProduct mEditOrderProduct;
    private boolean mEditing;
    private FavoriteInputViewHolder mFavoriteInputViewHolder;
    ContentObserver mFavoritesContentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            super.onChange(selfChange);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$400", new Object[]{ProductDetailsActivity.this}) != null) {
                ProductDetailsActivity.access$500(ProductDetailsActivity.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$400", new Object[]{ProductDetailsActivity.this}).getCurrentOrderProduct());
            }
        }
    };
    private ImageView mImageView;
    private Button mIncreaseQuantityButton;
    private int mMaxQuantityAllowed;
    private MealAdapter mMealAdapter;
    private TextView mMenuEndingTextView;
    private List<Offer> mOffers;
    private Order mOriginalOrder;
    private String mParentName;
    private TextView mQuantityText;
    private ProductRelatedOffersView mRelatedOffersView;
    private boolean mShowDimension = true;
    private ProductTotalPriceEnergy mTotalsViewHolder;
    private List<String> mUnavailableProductCodes = new ArrayList();

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$1 */
    class C36571 implements OnClickListener {
        C36571() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ProductDetailsActivity.access$000(ProductDetailsActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$4 */
    class C36604 implements AsyncListener<List<Offer>> {
        C36604() {
        }

        public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            ProductDetailsActivity.access$602(ProductDetailsActivity.this, response);
            if (!ProductDetailsActivity.this.isDestroyed()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$700", new Object[]{ProductDetailsActivity.this}).filter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$600", new Object[]{ProductDetailsActivity.this}), ProductDetailsActivity.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$5 */
    class C36615 implements OnClickListener {
        C36615() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ProductDetailsActivity.access$800(ProductDetailsActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$6 */
    class C36626 implements OnClickListener {
        C36626() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ProductDetailsActivity.access$900(ProductDetailsActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$7 */
    class C36637 implements DialogInterface.OnClickListener {
        C36637() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$8 */
    class C36648 implements DialogInterface.OnClickListener {
        C36648() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity$9 */
    class C36659 implements OnClickListener {
        C36659() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            ProductDetailsActivity.this.navigateToBasket();
        }
    }

    static /* synthetic */ void access$000(ProductDetailsActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$000", new Object[]{x0});
        x0.addToCartButtonClicked();
    }

    static /* synthetic */ void access$200(ProductDetailsActivity x0, OrderProduct x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$200", new Object[]{x0, x1});
        x0.startLoginFlowWithFavorite(x1);
    }

    static /* synthetic */ void access$500(ProductDetailsActivity x0, OrderProduct x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$500", new Object[]{x0, x1});
        x0.setupAddToFavoritesButton(x1);
    }

    static /* synthetic */ List access$602(ProductDetailsActivity x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$602", new Object[]{x0, x1});
        x0.mOffers = x1;
        return x1;
    }

    static /* synthetic */ void access$800(ProductDetailsActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$800", new Object[]{x0});
        x0.increaseQuantity();
    }

    static /* synthetic */ void access$900(ProductDetailsActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$900", new Object[]{x0});
        x0.decreaseQuantity();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") && OrderManager.getInstance().getCurrentStore() == null) {
            startActivity(new Intent(this, SelectStoreActivity.class));
            finish();
            return;
        }
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mImageView = (ImageView) findViewById(2131820643);
        this.mDimensionTabs = (TabLayout) findViewById(C2358R.C2357id.dimension_tabs);
        this.mQuantityText = (TextView) findViewById(C2358R.C2357id.quantity_value);
        this.mMenuEndingTextView = (TextView) findViewById(C2358R.C2357id.menu_ending_text);
        this.mTotalsViewHolder = new ProductTotalPriceEnergy(findViewById(C2358R.C2357id.total_price_energy));
        this.mRelatedOffersView = (ProductRelatedOffersView) findViewById(C2358R.C2357id.related_offers);
        fetchOffers();
        Button addToCartButton = (Button) findViewById(C2358R.C2357id.add_to_basket_button);
        addToCartButton.setOnClickListener(new C36571());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            OrderProduct orderProduct;
            this.mDayPartIndex = extras.getInt("ARG_ANALYTICS_DAYPART_INDEX");
            this.mCategoryName = extras.getString("ARG_ANALYTICS_CATEGORY_NAME");
            this.mParentName = extras.getString("ARG_ANALYTICS_PARENT_NAME");
            this.mUnavailableProductCodes = extras.getStringArrayList("ARG_UNAVAILABLE_PRODUCT_CODES");
            if (extras.containsKey("ARG_PRODUCT")) {
                orderProduct = (OrderProduct) extras.getParcelable("ARG_PRODUCT");
            } else {
                orderProduct = (OrderProduct) DataPasser.getInstance().getData("ARG_PRODUCT");
            }
            if (orderProduct != null) {
                this.mEditing = extras.getBoolean("arg_editing");
                if (this.mEditing) {
                    this.mEditOrderProduct = orderProduct;
                    orderProduct = OrderProduct.cloneProductForEditing(this.mEditOrderProduct);
                    addToCartButton.setText(C2658R.string.button_update_basket);
                    this.mOriginalOrder = Order.cloneOrderForEditing(OrderingManager.getInstance().getCurrentOrder());
                }
                setupProduct(orderProduct, extras.getBoolean("EXTRA_SAVING_FAVORITE_PRODUCT", false));
            } else {
                Product product;
                if (extras.containsKey("ARG_PRODUCT_PASSED")) {
                    product = (Product) extras.getParcelable("ARG_PRODUCT_PASSED");
                } else {
                    product = (Product) DataPasser.getInstance().getData("ARG_PRODUCT_PASSED");
                }
                if (product != null) {
                    orderProduct = OrderProduct.createProduct(product, Integer.valueOf(1));
                    setupProduct(orderProduct, extras.getBoolean("EXTRA_SAVING_FAVORITE_PRODUCT", false));
                }
            }
            if (orderProduct != null) {
                HashMap<String, Object> jiceMap = new HashMap();
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_PRODUCT_DETAIL);
                jiceMap.put(JiceArgs.LABEL_ITEM_IS_DELIVERY, String.valueOf(orderProduct.availableAtPOD(Pod.DELIVERY)));
                jiceMap.put(JiceArgs.LABEL_ITEM_IS_PICKUP, String.valueOf(orderProduct.availableAtPOD(Pod.PICKUP)));
                jiceMap.put(JiceArgs.LABEL_ITEM_NAME, orderProduct.getProduct().getLongName());
                jiceMap.put(JiceArgs.LABEL_ITEM_ID, orderProduct.getProductCode());
                Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).setConversionMaster(new CustomerEventAction(CustomEvent.ADCUSTOM3)).build());
            }
        }
        if (ModuleManager.getSharedInstance().isNutritionAvailable()) {
            View warningsView = findViewById(C2358R.C2357id.energy_warning_view);
            if (warningsView != null) {
                warningsView.setVisibility(0);
            }
        }
        if (Configuration.getSharedInstance().hasKey("interface.orderingDisclaimerInfo")) {
            UIUtils.addDisclaimerTextView((ViewGroup) findViewById(C2358R.C2357id.warnings_container), this, "productView");
        }
        setupBasketBadgeView();
    }

    private void checkDisplaySizeSelection(OrderProduct product, int categoryId) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "checkDisplaySizeSelection", new Object[]{product, new Integer(categoryId)});
        Store store = OrderManager.getInstance().getCurrentStore();
        int storeId = 0;
        if (store != null) {
            storeId = store.getStoreId();
        }
        StoreProduct storeProduct;
        if (categoryId == 0) {
            this.mShowDimension = false;
            storeProduct = new StoreProduct();
            storeProduct.setProductId(product.getProduct().getExternalId().intValue());
            storeProduct.setStoreId(storeId);
            List<StoreProductCategory> categories = StoreProductCategoryRepository.getCategoryByStoreProduct(this, storeProduct);
            if (categories.size() == 0) {
                this.mShowDimension = true;
                return;
            }
            for (StoreProductCategory c : categories) {
                if (c.getDisplaySizeSelection() != 0) {
                    this.mShowDimension = true;
                    return;
                }
            }
            return;
        }
        storeProduct = new StoreProduct();
        storeProduct.setProductId(product.getProduct().getExternalId().intValue());
        storeProduct.setStoreId(storeId);
        for (StoreProductCategory c2 : StoreProductCategoryRepository.getCategoryByStoreProduct(this, storeProduct)) {
            if (c2.getCategoryId() == categoryId) {
                if (c2.getDisplaySizeSelection() == 0) {
                    z = false;
                }
                this.mShowDimension = z;
                return;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        if (intent.getExtras().getBoolean("EXTRA_SAVING_FAVORITE_PRODUCT", false)) {
            this.mFavoriteInputViewHolder.addToFavoritesClicked((OrderProduct) DataPasser.getInstance().getData("ARG_PRODUCT"));
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int getContentViewResource() {
        Ensighten.evaluateEvent(this, "getContentViewResource", null);
        return C2658R.layout.activity_product_details;
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        adjustBasketBadgePosition(true);
        getContentResolver().registerContentObserver(Favorites.CONTENT_URI, true, this.mFavoritesContentObserver);
        AnalyticsUtils.trackScreenLoad(getString(C2658R.string.analytics_screen_order_item));
        setDataLayerTags(this.mDimensionTabs);
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        getContentResolver().unregisterContentObserver(this.mFavoritesContentObserver);
    }

    private void setupProduct(OrderProduct orderProduct, boolean favoriteButtonClicked) {
        Ensighten.evaluateEvent(this, "setupProduct", new Object[]{orderProduct, new Boolean(favoriteButtonClicked)});
        Bundle extras = getIntent().getExtras();
        int categoryId = 0;
        if (extras != null) {
            categoryId = extras.getInt("ARG_CAT_ID", 0);
        }
        checkDisplaySizeSelection(orderProduct, categoryId);
        Product product = orderProduct.getProduct();
        setupPicture(orderProduct);
        setupMaxQuantityAllowed();
        setupChoices(orderProduct);
        setupTabs(orderProduct);
        setupTotals();
        setupTimeRestrictions(orderProduct);
        setupAddToFavoritesButton(orderProduct);
        setupOffers(product, this.mMealAdapter.getProducts());
        if (orderProduct.getProduct() != null) {
            setTitle(orderProduct.getProduct().getName());
        }
        if (favoriteButtonClicked) {
            this.mFavoriteInputViewHolder.addToFavoritesClicked(orderProduct);
        }
        DataLayerManager.getInstance().setPageSection(orderProduct.getDisplayName());
        DataLayerManager.getInstance().setProduct(orderProduct);
    }

    private void setupChoices(OrderProduct product) {
        Ensighten.evaluateEvent(this, "setupChoices", new Object[]{product});
        if (!ListUtils.isEmpty(this.mUnavailableProductCodes)) {
            List<Choice> realChoices = product.getRealChoices();
            if (!ListUtils.isEmpty(realChoices)) {
                for (Choice choice : realChoices) {
                    OrderProduct actualProduct = OrderProduct.getChoiceWithinChoiceProduct(choice);
                    if (actualProduct != null && this.mUnavailableProductCodes.contains(actualProduct.getProductCode())) {
                        choice.setUnavailable(true);
                    }
                }
            }
        }
    }

    private void setupTimeRestrictions(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "setupTimeRestrictions", new Object[]{orderProduct});
        Store mStore = OrderManager.getInstance().getCurrentStore();
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        Boolean isDelivery = Boolean.valueOf(OrderingManager.getInstance().getCurrentOrder().isDelivery());
        this.mMenuEndingTextView.setVisibility(8);
        String alertTimeConfig = (String) Configuration.getSharedInstance().getValueForKey("interface.dayparts.daypartEndingAlertTime");
        if (alertTimeConfig != null) {
            int typeId = mStore.getCurrentMenuTypeID();
            MenuType menuType = null;
            for (MenuType mType : orderingModule.getMenuTypes()) {
                if (mType.getID() == typeId) {
                    menuType = mType;
                }
            }
            if (menuType != null) {
                long timeLeftInMenu = mStore.getMenuEndingTime(menuType, isDelivery.booleanValue());
                long alertTime = Long.valueOf(alertTimeConfig).longValue();
                if (timeLeftInMenu > 0 && timeLeftInMenu <= alertTime) {
                    this.mMenuEndingTextView.setVisibility(0);
                    this.mMenuEndingTextView.setText(getString(C2658R.string.label_daypart_menu_warning_ios, new Object[]{menuType.getShortName(), Long.valueOf(timeLeftInMenu)}));
                }
            }
        }
        UIUtils.showTimeRestrictionAlert((TextView) findViewById(C2358R.C2357id.time_restriction_warning), orderProduct.getProduct().getTimeRestrictions());
    }

    private void startLoginFlowWithFavorite(OrderProduct product) {
        Ensighten.evaluateEvent(this, "startLoginFlowWithFavorite", new Object[]{product});
        Bundle bundle = new Bundle();
        bundle.putInt("ARG_ANALYTICS_DAYPART_INDEX", this.mDayPartIndex);
        bundle.putString("ARG_ANALYTICS_CATEGORY_NAME", this.mCategoryName);
        bundle.putString("ARG_ANALYTICS_PARENT_NAME", this.mParentName);
        bundle.putBoolean("EXTRA_SAVING_FAVORITE_PRODUCT", true);
        DataPasser.getInstance().putData("ARG_PRODUCT", product);
        startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, bundle);
    }

    private void setupAddToFavoritesButton(final OrderProduct orderProduct) {
        boolean isFavorite = true;
        Ensighten.evaluateEvent(this, "setupAddToFavoritesButton", new Object[]{orderProduct});
        this.mFavoriteInputViewHolder = new FavoriteInputViewHolder(this, findViewById(C2358R.C2357id.container));
        getWindow().setSoftInputMode(16);
        this.mFavoriteInputViewHolder.setCategoryName(this.mCategoryName);
        Button addToFavoritesButton = (Button) findViewById(C2358R.C2357id.add_item_to_favorites);
        addToFavoritesButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$100", new Object[]{ProductDetailsActivity.this}).isLoggedIn()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity", "access$300", new Object[]{ProductDetailsActivity.this}).addToFavoritesClicked(orderProduct);
                    HashMap<String, Object> jiceMap = new HashMap();
                    jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_PRODUCT_DETAIL_ADD_FAV);
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
                    return;
                }
                ProductDetailsActivity.access$200(ProductDetailsActivity.this, orderProduct);
            }
        });
        if (this.mCustomerModule.getCurrentProfile() == null || !this.mCustomerModule.getCurrentProfile().isFavoriteOrderProduct(orderProduct, FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM)) {
            isFavorite = false;
        }
        if (isFavorite) {
            addToFavoritesButton.setText(getResources().getString(C2658R.string.favorited_item));
            ((RelativeLayout) addToFavoritesButton.getParent()).setBackgroundResource(C2358R.C2359drawable.button_red);
            return;
        }
        addToFavoritesButton.setText(C2658R.string.add_item_to_favorites);
        addToFavoritesButton.setTag(Boolean.valueOf(false));
        ((RelativeLayout) addToFavoritesButton.getParent()).setBackgroundResource(C2358R.C2359drawable.button_pink);
    }

    private void setupOffers(Product product, List<Product> mProducts) {
        Ensighten.evaluateEvent(this, "setupOffers", new Object[]{product, mProducts});
        this.mRelatedOffersView.setProductId(String.valueOf(product.getExternalId()), mProducts);
        this.mRelatedOffersView.setProduct(product);
        this.mRelatedOffersView.filter(this.mOffers, this);
    }

    private void fetchOffers() {
        Ensighten.evaluateEvent(this, "fetchOffers", null);
        this.mOffers = new ArrayList();
        if (this.mCustomerModule.isLoggedIn()) {
            CustomerProfile profile = this.mCustomerModule.getCurrentProfile();
            Store store = OrderManager.getInstance().getCurrentStore();
            if (!TextUtils.isEmpty(profile.getUserName())) {
                ServiceUtils.getSharedInstance().retrieveOffers(profile.getUserName(), String.valueOf(store.getStoreId()), null, null, new C36604());
            }
        }
    }

    private void setupMaxQuantityAllowed() {
        Ensighten.evaluateEvent(this, "setupMaxQuantityAllowed", null);
        this.mMaxQuantityAllowed = this.mCustomerModule.getMaxItemQuantity() - OrderingManager.getInstance().getCurrentOrder().getItemsCount();
        if (this.mMaxQuantityAllowed <= 1) {
            this.mIncreaseQuantityButton = (Button) findViewById(C2358R.C2357id.increase_quantity);
            this.mIncreaseQuantityButton.setEnabled(false);
        }
    }

    private void setupTotals() {
        Ensighten.evaluateEvent(this, "setupTotals", null);
        OrderProduct orderProduct = this.mMealAdapter.getCurrentOrderProduct();
        this.mIncreaseQuantityButton = (Button) findViewById(C2358R.C2357id.increase_quantity);
        this.mIncreaseQuantityButton.setOnClickListener(new C36615());
        this.mDecreaseQuantityButton = (Button) findViewById(C2358R.C2357id.decrease_quantity);
        this.mDecreaseQuantityButton.setOnClickListener(new C36626());
        setQuantityAndTotals(orderProduct);
    }

    private void decreaseQuantity() {
        Ensighten.evaluateEvent(this, "decreaseQuantity", null);
        OrderProduct orderProduct = this.mMealAdapter.getCurrentOrderProduct();
        orderProduct.setQuantity(orderProduct.getQuantity() - 1);
        setQuantityAndTotals(orderProduct);
    }

    private void increaseQuantity() {
        Ensighten.evaluateEvent(this, "increaseQuantity", null);
        OrderProduct orderProduct = this.mMealAdapter.getCurrentOrderProduct();
        orderProduct.setQuantity(orderProduct.getQuantity() + 1);
        setQuantityAndTotals(orderProduct);
    }

    private void setupTabs(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "setupTabs", new Object[]{orderProduct});
        this.mMealAdapter = new MealAdapter(this, orderProduct, (ViewGroup) findViewById(C2358R.C2357id.dimension_container), this.mShowDimension);
        this.mMealAdapter.setupWithTabLayout(this.mDimensionTabs);
        this.mMealAdapter.setOnMealChangedListener(this);
        this.mMealAdapter.setOnProductChoiceClickedListener(this);
        this.mMealAdapter.setOnProductCustomizeClickedListener(this);
        this.mMealAdapter.setOnProductInfoClickedListener(this);
    }

    public void onChange(OrderProduct product, List<Product> mProducts) {
        Ensighten.evaluateEvent(this, "onChange", new Object[]{product, mProducts});
        setTitle(product.getProduct().getLongName());
        setQuantityAndTotals(product);
        setupAddToFavoritesButton(product);
        setupOffers(product.getProduct(), mProducts);
        setupPicture(product);
    }

    private void setupPicture(OrderProduct product) {
        Ensighten.evaluateEvent(this, "setupPicture", new Object[]{product});
        ImageInfo imageInfo = product.getProduct().getCarouselImage();
        if (imageInfo != null) {
            Glide.with(getApplicationContext()).load(imageInfo.getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_large_meal).into(this.mImageView);
        }
    }

    public void onProductChoiceClicked(OrderProduct choice, int choiceIndex) {
        Ensighten.evaluateEvent(this, "onProductChoiceClicked", new Object[]{choice, new Integer(choiceIndex)});
        Intent intent = new Intent(this, ChoiceSelectorActivity.class);
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("ARG_CHOICE_KEY", choice);
        bundle.putInt("ARG_INDEX", choiceIndex);
        bundle.putString("ARG_TITLE", choice.getDisplayName());
        intent.putExtras(bundle);
        startActivityForResult(intent, 13098);
    }

    public void onProductCustomizeClicked(OrderProduct product, int ingredientIndex) {
        Ensighten.evaluateEvent(this, "onProductCustomizeClicked", new Object[]{product, new Integer(ingredientIndex)});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(product.getProduct().getName()).setAction("On click").setLabel("PDP - Customization").build());
        Bundle extras = new Bundle();
        extras.putString("ARG_ANALYTICS_PARENT_NAME", this.mParentName);
        extras.putInt("ARG_PRODUCT_INDEX", ingredientIndex);
        DataPasser.getInstance().putData("ARG_PRODUCT", product);
        Intent intent = new Intent(this, ProductCustomizationActivity.class);
        intent.putExtras(extras);
        startActivityForResult(intent, 45352);
    }

    public void onProductInfoClicked(String recipeId) {
        Ensighten.evaluateEvent(this, "onProductInfoClicked", new Object[]{recipeId});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/order").setAction("On click").setLabel("PDP - Nutrition info").build());
        NavigationManager.getInstance().showNutrition(this, recipeId, null, this.mParentName, this);
    }

    private void setQuantityAndTotals(OrderProduct product) {
        Ensighten.evaluateEvent(this, "setQuantityAndTotals", new Object[]{product});
        this.mQuantityText.setText(String.valueOf(product.getQuantity()));
        NumberFormat formatter = UIUtils.getLocalizedCurrencyFormatter();
        if (AppUtils.hideNutritionOnOrderingPages()) {
            this.mTotalsViewHolder.getTotalEnergy().setVisibility(4);
        } else {
            this.mTotalsViewHolder.getTotalEnergy().setText(AppUtils.getEnergyTextForOrderProduct(product, OrderProductUtils.getTotalEnergyUnit(product)));
        }
        double price = ProductUtils.getProductTotalPrice(product);
        this.mTotalsViewHolder.getTotalPrice().setText(String.format("%s*", new Object[]{formatter.format(price)}));
        if (product.getQuantity() <= 1) {
            this.mDecreaseQuantityButton.setEnabled(false);
        } else if (product.getQuantity() >= this.mMaxQuantityAllowed) {
            this.mIncreaseQuantityButton.setEnabled(false);
        } else {
            this.mDecreaseQuantityButton.setEnabled(true);
            this.mIncreaseQuantityButton.setEnabled(true);
        }
    }

    private void productChoiceSelected(OrderProduct choice, int index) {
        Ensighten.evaluateEvent(this, "productChoiceSelected", new Object[]{choice, new Integer(index)});
        MealItemData itemData = this.mMealAdapter.getMealItemAt(index);
        if (itemData.isChoice) {
            ((Choice) itemData.product.getRealChoices().get(itemData.choiceIndex)).setUnavailable(choice.isUnavailable());
            ((Choice) itemData.product.getRealChoices().get(itemData.choiceIndex)).setSelection(choice);
            this.mMealAdapter.updateCurrentView();
            return;
        }
        MCDLog.error(getLocalClassName(), "Choice selection error, expected choice at index: " + index);
    }

    private void productCustomizationUpdated(OrderProduct product, int productIndex) {
        Ensighten.evaluateEvent(this, "productCustomizationUpdated", new Object[]{product, new Integer(productIndex)});
        MealItemData itemData = this.mMealAdapter.getMealItemAt(productIndex);
        if (itemData != null) {
            Map<Integer, OrderProduct> customization = product.getCustomizations();
            boolean isChoiceCustomizations = false;
            List<Choice> realChoices = itemData.product.getRealChoices();
            if (!ListUtils.isEmpty(realChoices)) {
                for (Choice choice : realChoices) {
                    OrderProduct actualProduct = OrderProduct.getChoiceWithinChoiceProduct(choice);
                    if (actualProduct != null && actualProduct.equals(product)) {
                        actualProduct.setCustomizations(customization);
                        isChoiceCustomizations = true;
                        break;
                    }
                }
            }
            if (!isChoiceCustomizations) {
                itemData.product.setCustomizations(customization);
            }
            this.mMealAdapter.updateCurrentView();
        }
    }

    private void addToCartButtonClicked() {
        Ensighten.evaluateEvent(this, "addToCartButtonClicked", null);
        int mMaxQuantityAllowed2 = this.mMaxQuantityAllowed;
        if (this.mMealAdapter == null || !this.mMealAdapter.isAllChoiceSelected()) {
            showMealIncompleteAlert();
            return;
        }
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        trackAddToBasket(currentOrder);
        if (this.mEditing) {
            mMaxQuantityAllowed2 = this.mMaxQuantityAllowed + this.mEditOrderProduct.getQuantity();
        }
        if (this.mMealAdapter.getCurrentOrderProduct().getQuantity() > mMaxQuantityAllowed2) {
            showBasketLimitExceededAlert();
            return;
        }
        Store store = OrderManager.getInstance().getCurrentStore();
        OrderProduct orderProduct = this.mMealAdapter.getCurrentOrderProduct();
        boolean isDelivery = OrderingManager.getInstance().getCurrentOrder().isDelivery();
        if (orderProduct.isMeal() && orderProduct.getProduct().getMenuTypes() == null) {
            orderProduct.getProduct().setMenuTypes(this.mMealAdapter.getBaseProductMenuTypes());
        }
        if (this.mEditing || orderProduct.checkDayPart(store.getCurrentMenuTypeID(isDelivery))) {
            boolean showInvalidDayPartsError = true;
            if (this.mEditing) {
                if (currentOrder.addEditedProduct(orderProduct, this.mEditOrderProduct)) {
                    setResult(-1);
                    OrderingManager.getInstance().updateCurrentOrder(currentOrder);
                    showInvalidDayPartsError = false;
                    finish();
                }
            } else if (currentOrder.addProduct(orderProduct)) {
                OrderingManager.getInstance().updateCurrentOrder(currentOrder);
                showInvalidDayPartsError = false;
                finish();
            }
            if (showInvalidDayPartsError) {
                UIUtils.showInvalidDayPartsError(this);
                return;
            }
            return;
        }
        showDayPartAlert(orderProduct);
    }

    private void showMealIncompleteAlert() {
        Ensighten.evaluateEvent(this, "showMealIncompleteAlert", null);
        UIUtils.showGlobalAlertDialog(this, getResources().getString(C2658R.string.select_choices_title), getResources().getString(C2658R.string.select_choices_message), new C36637());
        DataLayerManager.getInstance().recordError("Incomplete meal");
    }

    private void showDayPartAlert(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "showDayPartAlert", new Object[]{orderProduct});
        Bundle bundle = new Bundle();
        bundle.putInt("arg_edit_product", DataPasser.getInstance().putData(orderProduct));
        Intent intent = new Intent(this, AlertActivity.class);
        intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "day_part_alert");
        intent.putExtras(bundle);
        startActivityForResult(intent, 42);
    }

    private void showBasketLimitExceededAlert() {
        Ensighten.evaluateEvent(this, "showBasketLimitExceededAlert", null);
        MCDAlertDialogBuilder.withContext(this).setMessage(getResources().getString(C2658R.string.too_many_products_in_basket)).setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C36648()).create().show();
    }

    private void trackAddToBasket(Order currentOrder) {
        Ensighten.evaluateEvent(this, "trackAddToBasket", new Object[]{currentOrder});
        OrderProduct orderProduct = this.mMealAdapter.getCurrentOrderProduct();
        if (orderProduct.getProduct().getLongName() != null) {
            SparseArray<String> customArgs = new SparseArray();
            if (this.mCategoryName != null) {
                customArgs.put(20, this.mCategoryName);
            }
            customArgs.put(21, orderProduct.getProduct().getLongName());
            if (currentOrder.getBasketCounter() == 1) {
                customArgs.put(45, Analytics.getTimestamp());
            }
            if (currentOrder.getBasketCounter() == 0 && !currentOrder.hasOffers()) {
                trackBasketCreated();
            }
            HashMap<String, Object> jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_ADD_CART);
            jiceMap.put(JiceArgs.LABEL_ITEM_ID, orderProduct.getProductCode());
            jiceMap.put(JiceArgs.LABEL_ITEM_NAME, orderProduct.getProduct().getLongName());
            jiceMap.put(JiceArgs.LABEL_ITEM_IS_DELIVERY, String.valueOf(orderProduct.availableAtPOD(Pod.DELIVERY)));
            jiceMap.put(JiceArgs.LABEL_ITEM_IS_PICKUP, String.valueOf(orderProduct.availableAtPOD(Pod.PICKUP)));
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/order/item").setAction("On click").setLabel("PDP - Add to basket").setCustom(customArgs).setJice(jiceMap).setConversionMaster(new CustomerEventAction(CustomEvent.ADCUSTOM3)).build());
        }
    }

    private void trackBasketCreated() {
        Ensighten.evaluateEvent(this, "trackBasketCreated", null);
        AnalyticsUtils.trackEvent("/basket", "On click", "Basket Created");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        if (this.mEditing) {
            OrderingManager.getInstance().updateCurrentOrder(this.mOriginalOrder);
        }
        if (VERSION.SDK_INT >= 21) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            Bundle extras;
            OrderProduct returnedData;
            if (requestCode == 13098) {
                extras = data.getExtras();
                if (extras.containsKey(ProductCustomizationFragment.RESULT_PRODUCT)) {
                    returnedData = (OrderProduct) extras.getParcelable(ProductCustomizationFragment.RESULT_PRODUCT);
                } else {
                    returnedData = (OrderProduct) DataPasser.getInstance().getData(ProductCustomizationFragment.RESULT_PRODUCT);
                }
                if (ProductUtils.getActualChoice(returnedData) != null) {
                    productChoiceSelected(returnedData, extras.getInt("RESULT_INDEX"));
                } else {
                    return;
                }
            }
            if (requestCode == 45352) {
                extras = data.getExtras();
                if (extras.containsKey(ProductCustomizationFragment.RESULT_PRODUCT)) {
                    returnedData = (OrderProduct) extras.getParcelable(ProductCustomizationFragment.RESULT_PRODUCT);
                } else {
                    returnedData = (OrderProduct) DataPasser.getInstance().getData(ProductCustomizationFragment.RESULT_PRODUCT);
                }
                productCustomizationUpdated(returnedData, extras.getInt(ProductCustomizationFragment.RESULT_PRODUCT_INDEX));
            }
            if (requestCode == 42) {
                finish();
            }
        }
    }

    private void adjustBasketBadgePosition(boolean animated) {
        Ensighten.evaluateEvent(this, "adjustBasketBadgePosition", new Object[]{new Boolean(animated)});
        updateHiddenState();
        this.mBasketBadgeLabel.setText(Integer.toString(this.mCount));
        int translationY = 0;
        if (this.mBasketBadgeContainerIsHidden.booleanValue()) {
            translationY = -this.mBasketBadgeContainer.getHeight();
        }
        if (animated) {
            this.mBasketBadgeContainer.animate().translationY((float) translationY);
        } else {
            this.mBasketBadgeContainer.setTranslationY((float) translationY);
        }
    }

    private void updateHiddenState() {
        boolean z = true;
        Ensighten.evaluateEvent(this, "updateHiddenState", null);
        Order order = OrderingManager.getInstance().getCurrentOrder();
        if (this.mEditing || order == null) {
            this.mBasketBadgeContainerIsHidden = Boolean.valueOf(true);
        } else {
            this.mCount = order.getTotalOrderCount();
            if (this.mCount != 0) {
                z = false;
            }
            this.mBasketBadgeContainerIsHidden = Boolean.valueOf(z);
        }
        if (this.mBasketBadgeContainerIsHidden.booleanValue()) {
            this.mBasketBadgeContainer.setVisibility(8);
        } else {
            this.mBasketBadgeContainer.setVisibility(0);
        }
        LocalDataManager.getSharedInstance().setCurrentOrder(order);
    }

    private void setupBasketBadgeView() {
        Ensighten.evaluateEvent(this, "setupBasketBadgeView", null);
        this.mBasketBadgeContainer = findViewById(C2358R.C2357id.basket_badge);
        this.mBasketBadgeLabel = (TextView) this.mBasketBadgeContainer.findViewById(C2358R.C2357id.basket_badge_label);
        this.mBasketBadgeContainer.setOnClickListener(new C36659());
        adjustBasketBadgePosition(false);
        updateHiddenState();
    }

    /* Access modifiers changed, original: protected */
    public void navigateToBasket() {
        Ensighten.evaluateEvent(this, "navigateToBasket", null);
        if (OrderManager.getInstance().getCurrentOrder() != null) {
            AnalyticsUtils.trackOnClickEvent("/home", "Basket");
            Intent intent = new Intent(this, BasketActivity.class);
            intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "basket");
            startActivity(intent);
            finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPageSection() {
        Ensighten.evaluateEvent(this, "getDataLayerPageSection", null);
        return null;
    }

    public void setDataLayerTags(TabLayout tabs) {
        Ensighten.evaluateEvent(this, "setDataLayerTags", new Object[]{tabs});
        for (int ii = 0; ii < tabs.getTabCount(); ii++) {
            DataLayerClickListener.setDataLayerTag(tabs.getTabAt(ii), "ToggleMealSize");
        }
    }
}
