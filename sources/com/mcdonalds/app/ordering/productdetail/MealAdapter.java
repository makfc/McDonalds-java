package com.mcdonalds.app.ordering.productdetail;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductDetailsItem;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealAdapter implements OnTabSelectedListener, OnClickListener {
    private final List<MenuType> baseProductMenuTypes;
    private boolean mAllChoiceSelected;
    private ViewGroup mContainer;
    private Context mContext;
    private int mCurrentPosition;
    private OrderProduct mCurrentProduct;
    boolean mHideCustomizationButton;
    private List<MealItemData> mMealItems;
    private SparseArray<NutritionRecipe> mNutritionRecipes;
    private OnMealChangedListener mOnMealChangedListener;
    private OnProductChoiceClickedListener mOnProductChoiceClickedListener;
    private OnProductCustomizeClickedListener mOnProductCustomizeClickedListener;
    private OnProductInfoClickedListener mOnProductInfoClickedListener;
    private SparseArray<OrderProduct> mOrderProducts;
    private OrderingModule mOrderingModule = ((OrderingModule) ModuleManager.getModule("ordering"));
    private List<Product> mProducts;

    public static class MealItemData {
        public int choiceIndex;
        public OrderProduct choiceProduct;
        public boolean isChoice;
        public OrderProduct product;

        public MealItemData(OrderProduct product, OrderProduct choiceProduct, int choiceIndex, boolean isChoice) {
            this.product = product;
            this.choiceProduct = choiceProduct;
            this.choiceIndex = choiceIndex;
            this.isChoice = isChoice;
        }

        public MealItemData(OrderProduct product) {
            this(product, null, -1, false);
        }
    }

    public interface OnMealChangedListener {
        void onChange(OrderProduct orderProduct, List<Product> list);
    }

    public interface OnProductChoiceClickedListener {
        void onProductChoiceClicked(OrderProduct orderProduct, int i);
    }

    public interface OnProductCustomizeClickedListener {
        void onProductCustomizeClicked(OrderProduct orderProduct, int i);
    }

    public interface OnProductInfoClickedListener {
        void onProductInfoClicked(String str);
    }

    static /* synthetic */ void access$000(MealAdapter x0, OrderProduct x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.MealAdapter", "access$000", new Object[]{x0, x1, new Integer(x2)});
        x0.productChoiceClicked(x1, x2);
    }

    static /* synthetic */ void access$100(MealAdapter x0, OrderProduct x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.MealAdapter", "access$100", new Object[]{x0, x1, new Integer(x2)});
        x0.productCustomizeClicked(x1, x2);
    }

    static /* synthetic */ void access$300(MealAdapter x0, NutritionRecipe x1, ProductDetailsItem x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.MealAdapter", "access$300", new Object[]{x0, x1, x2});
        x0.setupInfoButton(x1, x2);
    }

    static /* synthetic */ void access$400(MealAdapter x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.MealAdapter", "access$400", new Object[]{x0, x1});
        x0.productInfoClicked(x1);
    }

    public MealAdapter(Context context, OrderProduct orderProduct, ViewGroup container, boolean showDimension) {
        this.mContext = context;
        Product product = orderProduct.getProduct();
        this.baseProductMenuTypes = product.getMenuTypes();
        this.mHideCustomizationButton = Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton");
        List<ProductDimension> dimensions = product.getDimensions();
        if (!ListUtils.isEmpty(dimensions)) {
            sortDimensionBySizeCode(dimensions);
            this.mProducts = new ArrayList();
            String pod;
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                pod = Pod.DELIVERY;
            } else {
                pod = Pod.PICKUP;
            }
            boolean filterDimenPod = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.filterDimenPod");
            for (int i = 0; i < dimensions.size(); i++) {
                ProductDimension dimension = (ProductDimension) dimensions.get(i);
                if (filterDimenPod) {
                    this.mOrderingModule.populateProductWithStoreSpecificData(dimension.getProduct());
                }
                if (dimension.getShowSizeToCustomer() && (!filterDimenPod || dimension.getProduct().getPODs().contains(pod))) {
                    this.mProducts.add(dimension.getProduct());
                }
            }
        }
        if (Configuration.getSharedInstance().getBooleanForKey("interface.hideOutagedItemsInMenu")) {
            filterOutageProducts(this.mProducts);
        }
        if (ListUtils.isEmpty(this.mProducts) || !showDimension) {
            this.mProducts = Collections.singletonList(product);
        }
        this.mOrderProducts = new SparseArray();
        this.mNutritionRecipes = new SparseArray();
        this.mContainer = container;
        this.mMealItems = new ArrayList();
        this.mCurrentPosition = getProductPosition(orderProduct);
        this.mOrderProducts.put(this.mCurrentPosition, orderProduct);
        showView(this.mCurrentPosition);
    }

    private void sortDimensionBySizeCode(List<ProductDimension> dimensions) {
        Ensighten.evaluateEvent(this, "sortDimensionBySizeCode", new Object[]{dimensions});
        if (dimensions.size() >= 2) {
            for (int i = 0; i < dimensions.size(); i++) {
                for (int j = 1; j < dimensions.size() - i; j++) {
                    if (((ProductDimension) dimensions.get(j)).getSizeCode().intValue() < ((ProductDimension) dimensions.get(j - 1)).getSizeCode().intValue()) {
                        ProductDimension temp = (ProductDimension) dimensions.get(j);
                        dimensions.set(j, dimensions.get(j - 1));
                        dimensions.set(j - 1, temp);
                    }
                }
            }
        }
    }

    public void setupWithTabLayout(TabLayout tabLayout) {
        int i;
        Tab tab;
        Ensighten.evaluateEvent(this, "setupWithTabLayout", new Object[]{tabLayout});
        List<String> mOutageProductCodes = OrderManager.getInstance().getCurrentStore().getOutageProducts();
        List<Integer> mOutageCode = new ArrayList();
        if (!ListUtils.isEmpty(mOutageProductCodes)) {
            for (String productCode : mOutageProductCodes) {
                mOutageCode.add(Integer.valueOf(Integer.parseInt(productCode)));
            }
        }
        tabLayout.removeAllTabs();
        if (this.mProducts.size() > 1) {
            for (i = 0; i < this.mProducts.size(); i++) {
                Product product = (Product) this.mProducts.get(i);
                if (mOutageCode.contains(product.getExternalId())) {
                    tab = tabLayout.newTab().setText(String.format("%s %s", new Object[]{this.mContext.getString(C2658R.string.outage_unavailable), product.getShortName()}));
                    tabLayout.addTab(tab);
                    View v = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
                    v.setEnabled(false);
                    v.setClickable(false);
                    v.setAlpha(0.5f);
                } else {
                    tab = tabLayout.newTab().setText(product.getShortName());
                    tabLayout.addTab(tab);
                }
                if (i == this.mCurrentPosition) {
                    tab.select();
                }
            }
        }
        for (i = 0; i < tabLayout.getTabCount(); i++) {
            tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView((int) C2658R.layout.tab_header);
                ((TextView) tab.getCustomView().findViewById(16908308)).setTextSize(this.mContext.getResources().getDimension(C2658R.dimen.text_size_productdetail_tab));
            }
        }
        tabLayout.setOnTabSelectedListener(this);
    }

    private int getProductPosition(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "getProductPosition", new Object[]{orderProduct});
        Product product = orderProduct.getProduct();
        for (int i = 0; i < this.mProducts.size(); i++) {
            if (((Product) this.mProducts.get(i)).equals(product)) {
                return i;
            }
        }
        return -1;
    }

    public OrderProduct getCurrentOrderProduct() {
        Ensighten.evaluateEvent(this, "getCurrentOrderProduct", null);
        return this.mCurrentProduct;
    }

    public MealItemData getMealItemAt(int index) {
        Ensighten.evaluateEvent(this, "getMealItemAt", new Object[]{new Integer(index)});
        return (MealItemData) this.mMealItems.get(index);
    }

    public boolean isAllChoiceSelected() {
        Ensighten.evaluateEvent(this, "isAllChoiceSelected", null);
        return this.mAllChoiceSelected;
    }

    public void setOnMealChangedListener(OnMealChangedListener listener) {
        Ensighten.evaluateEvent(this, "setOnMealChangedListener", new Object[]{listener});
        this.mOnMealChangedListener = listener;
    }

    public void setOnProductChoiceClickedListener(OnProductChoiceClickedListener onProductChoiceClickedListener) {
        Ensighten.evaluateEvent(this, "setOnProductChoiceClickedListener", new Object[]{onProductChoiceClickedListener});
        this.mOnProductChoiceClickedListener = onProductChoiceClickedListener;
    }

    public void setOnProductInfoClickedListener(OnProductInfoClickedListener onProductInfoClickedListener) {
        Ensighten.evaluateEvent(this, "setOnProductInfoClickedListener", new Object[]{onProductInfoClickedListener});
        this.mOnProductInfoClickedListener = onProductInfoClickedListener;
    }

    public void setOnProductCustomizeClickedListener(OnProductCustomizeClickedListener onProductCustomizeClickedListener) {
        Ensighten.evaluateEvent(this, "setOnProductCustomizeClickedListener", new Object[]{onProductCustomizeClickedListener});
        this.mOnProductCustomizeClickedListener = onProductCustomizeClickedListener;
    }

    public void updateCurrentView() {
        Ensighten.evaluateEvent(this, "updateCurrentView", null);
        clearAll();
        showView(this.mCurrentPosition);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
    }

    public void onTabSelected(Tab tab) {
        Ensighten.evaluateEvent(this, "onTabSelected", new Object[]{tab});
        int position = tab.getPosition();
        if (this.mOrderProducts.get(position) != null && ((OrderProduct) this.mOrderProducts.get(position)).isMeal()) {
            initProductInstance(position);
        }
        showView(position);
    }

    public void onTabUnselected(Tab tab) {
        Ensighten.evaluateEvent(this, "onTabUnselected", new Object[]{tab});
    }

    public void onTabReselected(Tab tab) {
        Ensighten.evaluateEvent(this, "onTabReselected", new Object[]{tab});
    }

    private void showView(int position) {
        Ensighten.evaluateEvent(this, "showView", new Object[]{new Integer(position)});
        this.mCurrentPosition = position;
        int quantity = -1;
        if (this.mCurrentProduct != null) {
            quantity = this.mCurrentProduct.getQuantity();
            this.mCurrentProduct.setQuantity(1);
        }
        OrderProduct orderProduct = (OrderProduct) this.mOrderProducts.get(position);
        if (orderProduct == null) {
            orderProduct = initProductInstance(position);
        }
        populateMealList(orderProduct);
        if (quantity != -1) {
            orderProduct.setQuantity(quantity);
        }
        this.mCurrentProduct = orderProduct;
        mealChanged();
    }

    private OrderProduct initProductInstance(int position) {
        Ensighten.evaluateEvent(this, "initProductInstance", new Object[]{new Integer(position)});
        OrderProduct orderProduct = OrderProduct.createProduct((Product) this.mProducts.get(position), Integer.valueOf(1));
        if (orderProduct != null) {
            this.mOrderProducts.put(position, orderProduct);
        }
        return orderProduct;
    }

    private void populateMealList(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "populateMealList", new Object[]{orderProduct});
        this.mMealItems.clear();
        boolean allChoicesSelected = true;
        ArrayList<View> viewsToKeep = new ArrayList();
        int index = 0;
        List<OrderProduct> productIngredients = new ArrayList();
        if (orderProduct.isMeal()) {
            int ingredientSize = orderProduct.getIngredients().size();
            for (int i = 0; i < ingredientSize; i++) {
                OrderProduct ingredient = getActualProduct((OrderProduct) orderProduct.getIngredients().get(i));
                productIngredients.add(ingredient);
                View ingredientView = getProductView(ingredient);
                if (ingredientView == null) {
                    ingredientView = createProductView(ingredient, this.mContainer, index);
                    this.mContainer.addView(ingredientView, index);
                }
                this.mMealItems.add(new MealItemData(ingredient));
                viewsToKeep.add(ingredientView);
                index++;
                index += populateChoicesForProduct(ingredient, viewsToKeep, index);
                if (!this.mAllChoiceSelected) {
                    allChoicesSelected = false;
                }
            }
            orderProduct.setIngredients(productIngredients);
        } else {
            this.mContainer.removeAllViews();
            OrderProduct product = getActualProduct(orderProduct);
            if (orderProduct.getCustomizations() == null || orderProduct.getCustomizations().size() == 0) {
                orderProduct.setCustomizations(product.getCustomizations());
                orderProduct.setProduct(product.getProduct());
            }
            orderProduct.setRealChoices(product.getRealChoices());
            View productView = getProductView(product);
            if (productView == null) {
                productView = createProductView(product, this.mContainer, 0);
                this.mContainer.addView(productView);
            }
            this.mMealItems.add(new MealItemData(product));
            viewsToKeep.add(productView);
            index = 0 + 1;
        }
        populateChoicesForProduct(orderProduct, viewsToKeep, index);
        this.mAllChoiceSelected &= allChoicesSelected;
        removeAllViews(viewsToKeep);
    }

    private int populateChoicesForProduct(OrderProduct orderProduct, ArrayList<View> viewsToKeep, int startIndex) {
        Ensighten.evaluateEvent(this, "populateChoicesForProduct", new Object[]{orderProduct, viewsToKeep, new Integer(startIndex)});
        int choicesSize = 0;
        this.mAllChoiceSelected = true;
        for (int choiceIndex = 0; choiceIndex < orderProduct.getRealChoices().size(); choiceIndex++) {
            OrderProduct choice = (Choice) orderProduct.getRealChoices().get(choiceIndex);
            boolean hideSingleChoice = ProductUtils.hideSingleChoice();
            if (choice != null && choice.isSingleChoice() && hideSingleChoice) {
                if (!ListUtils.isEmpty(choice.getOptions())) {
                    OrderProduct choiceSolution = (OrderProduct) choice.getOptions().get(0);
                    choiceSolution.setQuantity(1);
                    if (!choice.isUnavailable()) {
                        choice.setSelection(choiceSolution);
                    }
                }
                startIndex--;
            } else {
                OrderProduct choiceProduct = ProductUtils.getActualChoice(choice.getSelection());
                if (choiceProduct == null || choice.isUnavailable()) {
                    this.mAllChoiceSelected = false;
                    choiceProduct = choice;
                }
                View productView = getProductView(orderProduct, choiceProduct);
                if (productView == null) {
                    int index = startIndex + choiceIndex;
                    productView = createProductView(choiceProduct, this.mContainer, index, true, choice);
                    this.mContainer.addView(productView, index);
                }
                this.mMealItems.add(new MealItemData(orderProduct, choice, choiceIndex, true));
                viewsToKeep.add(productView);
                choicesSize++;
            }
        }
        return choicesSize;
    }

    private void removeAllViews(@Nullable List<View> viewsToKeep) {
        Ensighten.evaluateEvent(this, "removeAllViews", new Object[]{viewsToKeep});
        int i = 0;
        while (i < this.mContainer.getChildCount()) {
            View v = this.mContainer.getChildAt(i);
            if (viewsToKeep == null || !viewsToKeep.contains(v)) {
                this.mContainer.removeView(v);
            } else {
                i++;
            }
        }
    }

    @Nullable
    private View getProductView(OrderProduct product) {
        Ensighten.evaluateEvent(this, "getProductView", new Object[]{product});
        return getProductView(product, null);
    }

    @Nullable
    private View getProductView(OrderProduct product, OrderProduct choice) {
        Ensighten.evaluateEvent(this, "getProductView", new Object[]{product, choice});
        View productView = null;
        Object tag = getTagForProduct(product, choice);
        for (int i = 0; i < this.mContainer.getChildCount(); i++) {
            View v = this.mContainer.getChildAt(i);
            if (v.getTag().equals(tag)) {
                productView = v;
            }
        }
        return productView;
    }

    @Nullable
    private Object getTagForProduct(OrderProduct product, OrderProduct choice) {
        Ensighten.evaluateEvent(this, "getTagForProduct", new Object[]{product, choice});
        if (product == null || choice == null) {
            return product;
        }
        return product.getProduct().getExternalId().toString() + choice.getProduct().getExternalId().toString() + product.getRealChoices().indexOf(choice);
    }

    private OrderProduct getActualProduct(OrderProduct baseProduct) {
        Ensighten.evaluateEvent(this, "getActualProduct", new Object[]{baseProduct});
        OrderProduct product = baseProduct;
        if (this.mCurrentProduct == null) {
            return product;
        }
        if (this.mCurrentProduct.equals(product)) {
            return this.mCurrentProduct;
        }
        if (this.mCurrentProduct.getIngredients() == null || !this.mCurrentProduct.getIngredients().contains(product)) {
            return product;
        }
        return (OrderProduct) this.mCurrentProduct.getIngredients().get(this.mCurrentProduct.getIngredients().indexOf(product));
    }

    private void clearAll() {
        Ensighten.evaluateEvent(this, "clearAll", null);
        this.mCurrentProduct = null;
        this.mContainer.removeAllViews();
    }

    private View createProductView(OrderProduct product, ViewGroup container, int index) {
        Ensighten.evaluateEvent(this, "createProductView", new Object[]{product, container, new Integer(index)});
        return createProductView(product, container, index, false, null);
    }

    private View createProductView(OrderProduct product, ViewGroup container, int index, boolean isChoice, OrderProduct choice) {
        Ensighten.evaluateEvent(this, "createProductView", new Object[]{product, container, new Integer(index), new Boolean(isChoice), choice});
        View detailsView = LayoutInflater.from(this.mContext).inflate(C2658R.layout.product_details_item, container, false);
        detailsView.setTag(getTagForProduct(product, choice));
        ProductDetailsItem item = new ProductDetailsItem(detailsView);
        item.getName().setText(product.getProduct().getLongName());
        item.getSpecialInstructions().setText("");
        item.getInfoButton().setVisibility(8);
        item.getHatButton().setVisibility(8);
        item.getSelectedButton().setVisibility(4);
        if (isChoice) {
            if (!product.isChoice()) {
                if (ConfigurationUtils.shouldShowUpLiftPrice()) {
                    if (ProductUtils.getProductTotalPrice(product) - this.mOrderingModule.getProductBasePrice(choice) >= 0.01d) {
                        item.getPriceUplift().setVisibility(0);
                        item.getPriceUplift().setText(String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)}));
                    }
                } else {
                    item.getPriceUplift().setVisibility(8);
                }
            }
            item.getDisclosureArrow().setVisibility(0);
            if (product.isChoice()) {
                item.getName().setTextColor(ContextCompat.getColor(this.mContext, C2658R.color.medium_gray_1));
                item.getFoodImageIcon().setVisibility(4);
            } else {
                setCustomizationButton(product, index, item);
            }
            final OrderProduct orderProduct = choice;
            final int i = index;
            item.getView().setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    MealAdapter.access$000(MealAdapter.this, orderProduct, i);
                }
            });
        } else {
            setCustomizationButton(product, index, item);
            checkNutritionInformation(product, item);
        }
        if (!product.isChoice()) {
            String nameDetails = ProductUtils.getNameDetailsString(product);
            if (!TextUtils.isEmpty(nameDetails)) {
                item.getNameDetails().setVisibility(0);
                item.getNameDetails().setText(nameDetails);
            }
            String specialInstructions = OrderProductUtils.getCustomizationsString(product);
            if (TextUtils.isEmpty(specialInstructions)) {
                item.getSpecialInstructions().setVisibility(4);
            } else {
                item.getSpecialInstructions().setVisibility(0);
                item.getSpecialInstructions().setText(specialInstructions);
            }
        }
        ImageInfo imageInfo = product.getDisplayThumbnailImage();
        Glide.with(this.mContext).load(imageInfo.getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_meal_gray).into(item.getFoodImageIcon());
        DataLayerClickListener.setDataLayerTag(item.getView(), "ProductMealChoiceItemPressed");
        DataLayerClickListener.setDataLayerTag(item.getHatButton(), "CustomizeButtonAction");
        return detailsView;
    }

    private void setCustomizationButton(final OrderProduct product, final int index, ProductDetailsItem item) {
        Ensighten.evaluateEvent(this, "setCustomizationButton", new Object[]{product, new Integer(index), item});
        if (this.mHideCustomizationButton || !isCustomizable(product)) {
            item.getHatButton().setVisibility(8);
            return;
        }
        item.getHatButton().setVisibility(0);
        if (hasCustomization(product)) {
            item.setHatButtonHighlighted(true);
        }
        item.getHatButton().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                MealAdapter.access$100(MealAdapter.this, product, index);
            }
        });
    }

    private void checkNutritionInformation(final OrderProduct orderProduct, final ProductDetailsItem item) {
        Ensighten.evaluateEvent(this, "checkNutritionInformation", new Object[]{orderProduct, item});
        NutritionRecipe recipe = (NutritionRecipe) this.mNutritionRecipes.get(Integer.parseInt(orderProduct.getProductCode()));
        if (recipe != null) {
            setupInfoButton(recipe, item);
            return;
        }
        NutritionModule nutritionModule = (NutritionModule) ModuleManager.getModule(NutritionModule.NAME);
        if (nutritionModule != null) {
            nutritionModule.getRecipeForExternalId(orderProduct.getProductCode(), new AsyncListener<NutritionRecipe>() {
                public void onResponse(NutritionRecipe response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (response != null) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.productdetail.MealAdapter", "access$200", new Object[]{MealAdapter.this}).put(orderProduct.getProduct().getExternalId().intValue(), response);
                        MealAdapter.access$300(MealAdapter.this, response, item);
                    }
                }
            });
        }
    }

    private void setupInfoButton(final NutritionRecipe recipe, ProductDetailsItem item) {
        Ensighten.evaluateEvent(this, "setupInfoButton", new Object[]{recipe, item});
        if (AppUtils.hideNutritionIconOnOrderingPages()) {
            item.getInfoButton().setVisibility(8);
            return;
        }
        item.getInfoButton().setVisibility(0);
        item.getInfoButton().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                MealAdapter.access$400(MealAdapter.this, String.valueOf(recipe.getId()));
            }
        });
    }

    private boolean hasCustomization(OrderProduct product) {
        Ensighten.evaluateEvent(this, "hasCustomization", new Object[]{product});
        return product.getCustomizations() != null && product.getCustomizations().size() > 0;
    }

    private boolean isCustomizable(OrderProduct product) {
        Ensighten.evaluateEvent(this, "isCustomizable", new Object[]{product});
        if (ListUtils.isEmpty(product.getProduct().getIngredients()) && ListUtils.isEmpty(product.getProduct().getExtras())) {
            return false;
        }
        return true;
    }

    private void mealChanged() {
        Ensighten.evaluateEvent(this, "mealChanged", null);
        OrderProduct product = getCurrentOrderProduct();
        if (this.mOnMealChangedListener != null) {
            this.mOnMealChangedListener.onChange(product, this.mProducts);
        }
    }

    public List<Product> getProducts() {
        Ensighten.evaluateEvent(this, "getProducts", null);
        return this.mProducts;
    }

    private void productCustomizeClicked(OrderProduct product, int index) {
        Ensighten.evaluateEvent(this, "productCustomizeClicked", new Object[]{product, new Integer(index)});
        if (this.mOnProductCustomizeClickedListener != null) {
            this.mOnProductCustomizeClickedListener.onProductCustomizeClicked(product, index);
        }
    }

    private void productInfoClicked(String recipeId) {
        Ensighten.evaluateEvent(this, "productInfoClicked", new Object[]{recipeId});
        if (this.mOnProductInfoClickedListener != null) {
            this.mOnProductInfoClickedListener.onProductInfoClicked(recipeId);
        }
    }

    private void productChoiceClicked(OrderProduct choice, int choiceIndex) {
        Ensighten.evaluateEvent(this, "productChoiceClicked", new Object[]{choice, new Integer(choiceIndex)});
        if (this.mOnProductChoiceClickedListener != null) {
            this.mOnProductChoiceClickedListener.onProductChoiceClicked(choice, choiceIndex);
        }
    }

    public List<MenuType> getBaseProductMenuTypes() {
        Ensighten.evaluateEvent(this, "getBaseProductMenuTypes", null);
        return this.baseProductMenuTypes;
    }

    private void filterOutageProducts(List<Product> products) {
        Ensighten.evaluateEvent(this, "filterOutageProducts", new Object[]{products});
        if (OrderManager.getInstance().getCurrentStore() != null) {
            List<String> outageProductCodes = OrderManager.getInstance().getCurrentStore().getOutageProducts();
            List<Integer> outageCodes = new ArrayList();
            if (!ListUtils.isEmpty(outageProductCodes)) {
                for (String productCode : outageProductCodes) {
                    outageCodes.add(Integer.valueOf(Integer.parseInt(productCode)));
                }
            }
            ArrayList<Product> outageProducts = new ArrayList();
            if (!ListUtils.isEmpty(products)) {
                for (Product p : products) {
                    if (outageCodes.contains(p.getExternalId())) {
                        outageProducts.add(p);
                    }
                }
                products.removeAll(outageProducts);
            }
        }
    }
}
