package com.mcdonalds.app.ordering;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductChooserListAdapter extends ArrayAdapter<OrderProduct> {
    private double mBasePrice;
    private final Context mContext;
    private boolean mHideCustomizationButton;
    private OnProductChooserListener mListener;
    private OrderingModule mOrderingModule = ((OrderingModule) ModuleManager.getModule("ordering"));
    private List<Integer> mOutageCode;
    private int mSelectedPosition = -1;

    public interface OnProductChooserListener {
        void onProductCustomizeClicked(OrderProduct orderProduct, int i);

        void onProductInfoButtonClicked(String str);
    }

    static /* synthetic */ void access$000(ProductChooserListAdapter x0, OrderProduct x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductChooserListAdapter", "access$000", new Object[]{x0, x1, new Integer(x2)});
        x0.onProductCustomizeClicked(x1, x2);
    }

    static /* synthetic */ void access$100(ProductChooserListAdapter x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductChooserListAdapter", "access$100", new Object[]{x0, x1});
        x0.onProductInfoButtonClicked(x1);
    }

    ProductChooserListAdapter(Context context, OrderProduct orderProduct, double basePrice) {
        super(context, C2658R.layout.product_details_item);
        this.mContext = context;
        this.mHideCustomizationButton = Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton");
        this.mBasePrice = basePrice;
        reset(orderProduct);
    }

    public void setListener(OnProductChooserListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
    }

    public void reset(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "reset", new Object[]{orderProduct});
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        ProductUtils.populateProductChoices(orderProduct, this.mOrderingModule);
        List<OrderProduct> choices = orderProduct.getChoices();
        List<OrderProduct> ingredients = new ArrayList();
        if (ProductType.Choice.equals(orderProduct.getProduct().getProductType())) {
            ProductUtils.populateProductIngredients(orderProduct, this.mOrderingModule);
            ingredients.addAll(orderProduct.getIngredients());
        }
        boolean isDelivery = currentOrder.isDelivery();
        Store store = OrderManager.getInstance().getCurrentStore();
        int currentDayPart = store.getCurrentMenuTypeID(isDelivery);
        List<OrderProduct> choicesFiltered = new ArrayList();
        List<OrderProduct> ingredientsFiltered = new ArrayList();
        if (currentOrder.isDelivery()) {
            for (OrderProduct p : choices) {
                if (p.getProduct().getPODs().contains(Pod.DELIVERY) && p.checkDayPart(currentDayPart)) {
                    choicesFiltered.add(p);
                }
            }
            for (OrderProduct p2 : ingredients) {
                if (p2.getProduct().getPODs().contains(Pod.DELIVERY) && p2.checkDayPart(currentDayPart)) {
                    ingredientsFiltered.add(p2);
                }
            }
        } else {
            for (OrderProduct p22 : choices) {
                if (p22.checkDayPart(currentDayPart)) {
                    choicesFiltered.add(p22);
                }
            }
            for (OrderProduct p222 : ingredients) {
                if (p222.checkDayPart(currentDayPart)) {
                    ingredientsFiltered.add(p222);
                }
            }
        }
        orderProduct.setChoices(choicesFiltered);
        orderProduct.setIngredients(ingredientsFiltered);
        clearAndAddAll(choicesFiltered, ingredientsFiltered);
        List<String> mOutageProductCodes = new ArrayList();
        if (store != null) {
            mOutageProductCodes = store.getOutageProducts();
        }
        this.mOutageCode = new ArrayList();
        if (!ListUtils.isEmpty(mOutageProductCodes)) {
            for (String productCode : mOutageProductCodes) {
                this.mOutageCode.add(Integer.valueOf(Integer.parseInt(productCode)));
            }
        }
    }

    public void clearAndAddAll(List<OrderProduct> choiceList, List<OrderProduct> productList) {
        Ensighten.evaluateEvent(this, "clearAndAddAll", new Object[]{choiceList, productList});
        clear();
        addAll(choiceList);
        addAll(productList);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<OrderProduct> orderProductList) {
        Ensighten.evaluateEvent(this, "clearAndAddAll", new Object[]{orderProductList});
        clear();
        addAll(orderProductList);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int selectedPosition) {
        Ensighten.evaluateEvent(this, "setSelectedPosition", new Object[]{new Integer(selectedPosition)});
        this.mSelectedPosition = selectedPosition;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ProductDetailsItem holder;
        final OrderProduct ingredient = (OrderProduct) getItem(position);
        if (convertView == null) {
            convertView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C2658R.layout.product_details_item, parent, false);
            holder = new ProductDetailsItem(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ProductDetailsItem) convertView.getTag();
        }
        DataLayerClickListener.setDataLayerTag(convertView, ProductDetailsItem.class, position);
        if (this.mOutageCode.contains(ingredient.getProduct().getExternalId())) {
            holder.setNameText(String.format("%s %s", new Object[]{this.mContext.getString(C2658R.string.outage_unavailable), ingredient.getProduct().getLongName()}));
            holder.setSpecialInstructionsText(getSpecialInstructionsNames(ingredient));
        } else {
            boolean hasSubSelection = false;
            if (ingredient.isChoice()) {
                hasSubSelection = (ingredient.isSingleChoice() && ProductUtils.hideSingleChoice()) ? false : true;
            } else if (ingredient.getProduct().hasChoice().booleanValue()) {
                hasSubSelection = checkForSingleChoiceItems(ingredient);
            }
            holder.setViewChecked(parent, position);
            holder.setSubSelection(hasSubSelection);
            holder.setNameText(ingredient.getProduct().getLongName());
            holder.setSpecialInstructionsText(getSpecialInstructionsNames(ingredient));
            if (this.mSelectedPosition == position) {
                holder.getSelectedButton().setChecked(true);
            } else {
                holder.getSelectedButton().setChecked(false);
            }
            boolean showUplift = ConfigurationUtils.shouldShowUpLiftPrice();
            holder.setPriceUpliftTextVisible(false);
            if (showUplift) {
                if (ProductUtils.getProductTotalPrice(ingredient) - this.mBasePrice >= 0.01d) {
                    holder.setPriceUpliftTextVisible(true);
                    holder.setPriceUpliftText(String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)}));
                }
            }
        }
        boolean showHatButton = (this.mHideCustomizationButton || !productHasIngredientsOrExtras(ingredient) || ingredient.isChoice()) ? false : true;
        holder.setHatButtonVisibility(showHatButton ? 0 : 4);
        holder.setHatButtonHighlighted(showHatButton ? OrderProductUtils.getCustomizationsString(ingredient) : null);
        if (showHatButton) {
            final int i = position;
            holder.setHatButtonOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                    ProductChooserListAdapter.access$000(ProductChooserListAdapter.this, ingredient, i);
                }
            });
        } else {
            holder.setHatButtonOnClickListener(null);
        }
        final ImageButton infoButton = holder.getInfoButton();
        infoButton.setVisibility(4);
        if (!AppUtils.hideNutritionIconOnOrderingPages()) {
            ((NutritionModule) ModuleManager.getModule(NutritionModule.NAME)).getRecipeForExternalId(ingredient.getProduct().getExternalId().toString(), new AsyncListener<NutritionRecipe>() {
                public void onResponse(final NutritionRecipe response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception == null && response != null) {
                        infoButton.setVisibility(0);
                        infoButton.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                                ProductChooserListAdapter.access$100(ProductChooserListAdapter.this, response.getId());
                            }
                        });
                    } else if (exception != null && exception.getErrorCode() != 0) {
                        AsyncException.report(exception);
                    }
                }
            });
        }
        if (!(ingredient.getProduct() == null || ingredient.getProduct().getThumbnailImage() == null)) {
            Glide.with(this.mContext).load(ingredient.getProduct().getThumbnailImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.getFoodImageIcon());
        }
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }

    private boolean checkForSingleChoiceItems(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "checkForSingleChoiceItems", new Object[]{orderProduct});
        ProductUtils.populateProductChoices(orderProduct, this.mOrderingModule);
        if (!ListUtils.isEmpty(orderProduct.getChoices())) {
            for (int choiceIndex = 0; choiceIndex < orderProduct.getChoices().size(); choiceIndex++) {
                OrderProduct choice = (OrderProduct) orderProduct.getChoices().get(choiceIndex);
                boolean hideSingleChoice = ProductUtils.hideSingleChoice();
                if (!choice.isSingleChoice() || !hideSingleChoice) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getSpecialInstructionsNames(OrderProduct ingredient) {
        Ensighten.evaluateEvent(this, "getSpecialInstructionsNames", new Object[]{ingredient});
        ArrayList<String> productNames = new ArrayList();
        Map<Integer, OrderProduct> customization = ingredient.getCustomizations();
        for (Integer key : customization.keySet()) {
            productNames.add(((OrderProduct) customization.get(key)).getProduct().getName());
        }
        if (productNames.size() == 0) {
            return "";
        }
        return TextUtils.join(", ", productNames);
    }

    private boolean productHasIngredientsOrExtras(OrderProduct ingredient) {
        Ensighten.evaluateEvent(this, "productHasIngredientsOrExtras", new Object[]{ingredient});
        Product product = ingredient.getProduct();
        if (ListUtils.isEmpty(product.getIngredients())) {
            product.setIngredients(this.mOrderingModule.getProductIngredients(product));
        }
        if (ListUtils.isEmpty(product.getExtras())) {
            product.setExtras(this.mOrderingModule.getProductExtras(product));
        }
        if (ListUtils.isEmpty(product.getIngredients()) && ListUtils.isEmpty(product.getExtras())) {
            return false;
        }
        return true;
    }

    private void onProductInfoButtonClicked(String foodId) {
        Ensighten.evaluateEvent(this, "onProductInfoButtonClicked", new Object[]{foodId});
        if (this.mListener != null) {
            this.mListener.onProductInfoButtonClicked(foodId);
        }
    }

    private void onProductCustomizeClicked(OrderProduct product, int position) {
        Ensighten.evaluateEvent(this, "onProductCustomizeClicked", new Object[]{product, new Integer(position)});
        if (this.mListener != null) {
            this.mListener.onProductCustomizeClicked(product, position);
        }
    }
}
