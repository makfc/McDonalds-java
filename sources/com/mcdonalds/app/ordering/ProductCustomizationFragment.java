package com.mcdonalds.app.ordering;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ProductCustomizationFragment extends URLNavigationFragment implements OnClickListener {
    public static String RESULT_PRODUCT = "RESULT_PRODUCT";
    public static String RESULT_PRODUCT_INDEX = "RESULT_PRODUCT_INDEX";
    private String mCategoryName;
    private Map<Integer, OrderProduct> mCustomizationsMap;
    private SparseArray<Quantity> mCustomizedQuantities;
    private int mDayPartIndex = -1;
    private LinearLayout mExtrasContainer;
    private Map<Integer, Ingredient> mIngredientMap = new Hashtable();
    private LinearLayout mIngredientsContainer;
    private OrderProduct mOrderProduct;
    private String mParentName;
    private int mProductIndex;

    private static class Quantity {
        boolean isLight;
        int quantity;

        private Quantity() {
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        if (this.mParentName.isEmpty()) {
            return getString(C2658R.string.analytics_screen_order_item_customize);
        }
        return getString(C2658R.string.analytics_screen_basket_item_customize);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mProductIndex = getArguments().getInt("ARG_PRODUCT_INDEX");
        if (getArguments().containsKey("ARG_PRODUCT")) {
            this.mOrderProduct = (OrderProduct) getArguments().getParcelable("ARG_PRODUCT");
        } else {
            this.mOrderProduct = (OrderProduct) DataPasser.getInstance().getData("ARG_PRODUCT");
        }
        setHasOptionsMenu(true);
        this.mDayPartIndex = getArguments().getInt("ARG_DAY_PART_INDEX");
        this.mCategoryName = getArguments().getString("ARG_CATEGORY_NAME");
        this.mParentName = getArguments().getString("ARG_ANALYTICS_PARENT_NAME", "");
        this.mCustomizedQuantities = new SparseArray();
        this.mCustomizationsMap = new Hashtable(this.mOrderProduct.getCustomizations());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup group = (ViewGroup) inflater.inflate(C2658R.layout.fragment_product_customization, container, false);
        this.mIngredientsContainer = (LinearLayout) group.findViewById(C2358R.C2357id.customizations_ingredients_container);
        this.mExtrasContainer = (LinearLayout) group.findViewById(C2358R.C2357id.customizations_extras_container);
        setupIngredients();
        setupExtras();
        return group;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.product_customization, menu);
        menu.findItem(C2358R.C2357id.action_save).setVisible(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_save /*2131821893*/:
                AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Product Customized");
                finalizeCustomizations();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                DataPasser.getInstance().putData(RESULT_PRODUCT, this.mOrderProduct);
                bundle.putInt(RESULT_PRODUCT_INDEX, this.mProductIndex);
                intent.putExtras(bundle);
                getActivity().setResult(-1, intent);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupIngredients() {
        Ensighten.evaluateEvent(this, "setupIngredients", null);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        if (this.mOrderProduct.getProduct().getIngredients() != null) {
            for (Ingredient ingredient : this.mOrderProduct.getProduct().getIngredients()) {
                boolean z;
                this.mIngredientMap.put(ingredient.getProduct().getExternalId(), ingredient);
                ProductCustomizationItem item = new ProductCustomizationItem(inflater.inflate(C2658R.layout.customization_item, null));
                OrderProduct customization = (OrderProduct) this.mCustomizationsMap.get(ingredient.getProduct().getExternalId());
                if (customization == null) {
                    customization = OrderProduct.createProduct(ingredient.getProduct(), Integer.valueOf(ingredient.getDefaultQuantity()));
                    this.mCustomizationsMap.put(ingredient.getProduct().getExternalId(), customization);
                }
                item.setOrderProduct(customization);
                item.setIngredient(ingredient);
                item.getTypeLabel().setText(customization.getProduct().getName());
                item.getQuantity().setText(quantityLabelText(ingredient, customization.getQuantity(), customization.getIsLight()));
                item.getCost().setText(costLabelText(ingredient, customization.getQuantity(), Double.valueOf(0.0d)));
                item.getDecreaseButton().setTag(item);
                item.getDecreaseButton().setOnClickListener(this);
                ImageView decreaseButton = item.getDecreaseButton();
                if (customization.getQuantity() > 1) {
                    z = true;
                } else {
                    z = false;
                }
                decreaseButton.setEnabled(z);
                item.getIncreaseButton().setTag(item);
                item.getIncreaseButton().setOnClickListener(this);
                DataLayerClickListener.setDataLayerTag(item.getDecreaseButton(), "ChangeQuantityButtonPressed");
                DataLayerClickListener.setDataLayerTag(item.getIncreaseButton(), "ChangeQuantityButtonPressed");
                decreaseButton = item.getIncreaseButton();
                if (customization.getQuantity() < ingredient.getMaxQuantity()) {
                    z = true;
                } else {
                    z = false;
                }
                decreaseButton.setEnabled(z);
                decreaseButton = item.getDecreaseButton();
                if (customization.getQuantity() > ingredient.getMinQuantity()) {
                    z = true;
                } else {
                    z = false;
                }
                decreaseButton.setEnabled(z);
                item.getView().setLayoutParams(new LayoutParams(-1, UIUtils.dpAsPixels(getNavigationActivity(), 50)));
                this.mIngredientsContainer.addView(item.getView());
            }
        }
    }

    private void setupExtras() {
        Ensighten.evaluateEvent(this, "setupExtras", null);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        if (this.mOrderProduct.getProduct().getExtras() != null) {
            for (Ingredient ingredient : this.mOrderProduct.getProduct().getExtras()) {
                boolean z;
                this.mIngredientMap.put(ingredient.getProduct().getExternalId(), ingredient);
                ProductCustomizationItem item = new ProductCustomizationItem(inflater.inflate(C2658R.layout.customization_item, null));
                OrderProduct customization = (OrderProduct) this.mCustomizationsMap.get(ingredient.getProduct().getExternalId());
                if (customization == null) {
                    customization = OrderProduct.createProduct(ingredient.getProduct(), Integer.valueOf(ingredient.getDefaultQuantity()));
                    this.mCustomizationsMap.put(ingredient.getProduct().getExternalId(), customization);
                }
                item.setOrderProduct(customization);
                item.setIngredient(ingredient);
                item.getTypeLabel().setText(customization.getProduct().getName());
                item.getQuantity().setText(quantityLabelText(ingredient, customization.getQuantity(), customization.getIsLight()));
                item.getCost().setText(costLabelText(ingredient, customization.getQuantity(), Double.valueOf(0.0d)));
                item.getDecreaseButton().setTag(item);
                item.getDecreaseButton().setOnClickListener(this);
                ImageView decreaseButton = item.getDecreaseButton();
                if (customization.getQuantity() > 1) {
                    z = true;
                } else {
                    z = false;
                }
                decreaseButton.setEnabled(z);
                item.getIncreaseButton().setTag(item);
                item.getIncreaseButton().setOnClickListener(this);
                decreaseButton = item.getIncreaseButton();
                if (customization.getQuantity() < ingredient.getMaxQuantity()) {
                    z = true;
                } else {
                    z = false;
                }
                decreaseButton.setEnabled(z);
                decreaseButton = item.getDecreaseButton();
                if (customization.getQuantity() > ingredient.getMinQuantity()) {
                    z = true;
                } else {
                    z = false;
                }
                decreaseButton.setEnabled(z);
                item.getView().setLayoutParams(new LayoutParams(-1, UIUtils.dpAsPixels(getNavigationActivity(), 50)));
                this.mExtrasContainer.addView(item.getView());
            }
        }
    }

    private void finalizeCustomizations() {
        Ensighten.evaluateEvent(this, "finalizeCustomizations", null);
        for (int i = 0; i < this.mCustomizedQuantities.size(); i++) {
            Quantity value = (Quantity) this.mCustomizedQuantities.valueAt(i);
            OrderProduct orderProduct = (OrderProduct) this.mCustomizationsMap.get(Integer.valueOf(this.mCustomizedQuantities.keyAt(i)));
            orderProduct.setIsLight(value.isLight);
            orderProduct.setQuantity(value.quantity);
        }
        for (Integer key : new ArrayList(this.mCustomizationsMap.keySet())) {
            OrderProduct customization = (OrderProduct) this.mCustomizationsMap.get(key);
            if (customization.getQuantity() == ((Ingredient) this.mIngredientMap.get(key)).getDefaultQuantity() && !customization.getIsLight()) {
                this.mCustomizationsMap.remove(key);
            }
        }
        this.mOrderProduct.setCustomizations(this.mCustomizationsMap);
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        ProductCustomizationItem item = (ProductCustomizationItem) view.getTag();
        Quantity customizedQuantity = (Quantity) this.mCustomizedQuantities.get(item.getOrderProduct().getProduct().getExternalId().intValue());
        if (customizedQuantity == null) {
            customizedQuantity = new Quantity();
            customizedQuantity.isLight = false;
            customizedQuantity.quantity = item.getOrderProduct().getQuantity();
            this.mCustomizedQuantities.put(item.getOrderProduct().getProduct().getExternalId().intValue(), customizedQuantity);
        }
        boolean hasAcceptsLight = item.getOrderProduct().getProduct().getAcceptsLight();
        boolean currentIsLight = customizedQuantity.isLight;
        int currentQuantity = customizedQuantity.quantity;
        double previousCost = item.getIngredient().getProduct().getPrice(OrderingManager.getInstance().getCurrentOrderPriceType()) * ((double) currentQuantity);
        boolean standardMapping = false;
        if (item.getIngredient().getMinQuantity() == 0 && item.getIngredient().getMaxQuantity() == 2 && (item.getIngredient().getDefaultQuantity() == 0 || item.getIngredient().getDefaultQuantity() == 1)) {
            standardMapping = true;
        }
        int defaultQuantity = item.getIngredient().getDefaultQuantity();
        if (view.getId() == C2358R.C2357id.increase_quantity_img_btn && item.getIngredient().getMaxQuantity() > currentQuantity) {
            trackChanges(item, true);
            if (!standardMapping) {
                currentQuantity++;
            } else if (currentQuantity == 1 && defaultQuantity == 1 && !currentIsLight) {
                currentQuantity = 2;
            } else if (currentQuantity == 1 && defaultQuantity == 1) {
                currentIsLight = false;
            } else if (currentQuantity == 0 && defaultQuantity == 1 && !currentIsLight && hasAcceptsLight) {
                currentQuantity = 1;
                currentIsLight = true;
            } else if (currentQuantity == 0 && defaultQuantity == 0 && !currentIsLight && hasAcceptsLight) {
                currentIsLight = true;
            } else if (currentQuantity == 0 && defaultQuantity == 0) {
                currentIsLight = false;
                currentQuantity = 1;
            } else if (currentQuantity == 1 && !currentIsLight) {
                currentQuantity = 2;
            }
        } else if (view.getId() == C2358R.C2357id.decrease_quantity_img_btn && (item.getIngredient().getMinQuantity() < currentQuantity || currentIsLight)) {
            trackChanges(item, false);
            if (!standardMapping) {
                currentQuantity--;
            } else if (currentQuantity == 1 && defaultQuantity == 1 && !currentIsLight && hasAcceptsLight) {
                currentIsLight = true;
            } else if (currentQuantity == 1 && defaultQuantity == 1) {
                currentQuantity = 0;
                currentIsLight = false;
            } else if (currentQuantity == 2 && defaultQuantity == 1 && !currentIsLight) {
                currentQuantity = 1;
            } else if (currentQuantity == 1 && !currentIsLight && hasAcceptsLight) {
                currentIsLight = true;
            } else if (currentQuantity == 2 && defaultQuantity == 0 && !currentIsLight) {
                currentQuantity = 1;
            } else if (currentQuantity == 1 && defaultQuantity == 0 && currentIsLight && hasAcceptsLight) {
                currentIsLight = false;
                currentQuantity = 0;
            } else if (currentQuantity == 1 && defaultQuantity == 0 && !hasAcceptsLight) {
                currentIsLight = false;
                currentQuantity = 0;
            } else if (currentQuantity == 0 && defaultQuantity == 0 && currentIsLight) {
                currentIsLight = false;
                currentQuantity = 0;
            }
        }
        customizedQuantity.quantity = currentQuantity;
        customizedQuantity.isLight = currentIsLight;
        item.getQuantity().setText(quantityLabelText(item.getIngredient(), currentQuantity, currentIsLight));
        item.getCost().setText(costLabelText(item.getIngredient(), currentQuantity, Double.valueOf(previousCost)));
        ImageView increaseButton = item.getIncreaseButton();
        boolean z = currentQuantity < item.getIngredient().getMaxQuantity() || currentIsLight;
        increaseButton.setEnabled(z);
        increaseButton = item.getDecreaseButton();
        z = currentQuantity > item.getIngredient().getMinQuantity() || currentIsLight;
        increaseButton.setEnabled(z);
    }

    private void trackChanges(ProductCustomizationItem item, boolean increment) {
        Ensighten.evaluateEvent(this, "trackChanges", new Object[]{item, new Boolean(increment)});
        SparseArray custom = new SparseArray();
        custom.put(37, this.mOrderProduct.getProduct().getName());
        custom.put(38, item.getIngredient().getProduct().getName());
        custom.put(39, increment ? "1" : "-1");
        double price = item.getIngredient().getProduct().getPrice(OrderingManager.getInstance().getCurrentOrder().getPriceType());
        if (price > 0.0d) {
            custom.put(54, String.valueOf(price));
        }
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), increment ? "Increment" : "Decrement", custom);
    }

    private String quantityLabelText(Ingredient ingredient, int currentQuantity, boolean isLight) {
        Ensighten.evaluateEvent(this, "quantityLabelText", new Object[]{ingredient, new Integer(currentQuantity), new Boolean(isLight)});
        int defaultQuantity = ingredient.getDefaultQuantity();
        boolean standardMapping = false;
        if (ingredient.getMinQuantity() == 0 && ingredient.getMaxQuantity() == 2 && (defaultQuantity == 0 || defaultQuantity == 1)) {
            standardMapping = true;
        }
        String ret = String.valueOf(currentQuantity);
        if (!standardMapping) {
            return ret;
        }
        if (isLight) {
            return "light";
        }
        if (currentQuantity == 0) {
            return "none";
        }
        if (currentQuantity == 1) {
            return "regular";
        }
        if (currentQuantity == 2) {
            return "extra";
        }
        return ret;
    }

    private String costLabelText(Ingredient ingredient, int currentQuantity, Double previousCost) {
        Ensighten.evaluateEvent(this, "costLabelText", new Object[]{ingredient, new Integer(currentQuantity), previousCost});
        Double cost = Double.valueOf(0.0d);
        if (!ingredient.getCostInclusive()) {
            cost = Double.valueOf(((double) currentQuantity) * ingredient.getProduct().getPrice(OrderingManager.getInstance().getCurrentOrder().getPriceType()));
        }
        if (cost.doubleValue() > 0.0d) {
            return UIUtils.getLocalizedCurrencyFormatter().format(cost);
        }
        return "";
    }
}
