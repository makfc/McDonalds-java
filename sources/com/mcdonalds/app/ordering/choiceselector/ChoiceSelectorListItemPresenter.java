package com.mcdonalds.app.ordering.choiceselector;

import android.databinding.BaseObservable;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.ProductDetailsItemPresenter;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Map;

public class ChoiceSelectorListItemPresenter extends BaseObservable implements ProductDetailsItemPresenter {
    private double mBasePrice;
    private boolean mChecked;
    private boolean mIsOutage;
    private OrderProduct mOrderProduct;
    private String mRecipeId;
    private boolean mShowInfoButton;

    /* renamed from: com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorListItemPresenter$1 */
    class C34661 implements AsyncListener<NutritionRecipe> {
        C34661() {
        }

        public void onResponse(NutritionRecipe response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && response != null) {
                ChoiceSelectorListItemPresenter.access$002(ChoiceSelectorListItemPresenter.this, response.getId());
                ChoiceSelectorListItemPresenter.this.setShowInfoButton(true);
            }
        }
    }

    static /* synthetic */ String access$002(ChoiceSelectorListItemPresenter x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorListItemPresenter", "access$002", new Object[]{x0, x1});
        x0.mRecipeId = x1;
        return x1;
    }

    public ChoiceSelectorListItemPresenter(OrderProduct orderProduct, boolean isOutage) {
        this.mOrderProduct = orderProduct;
        this.mIsOutage = isOutage;
        checkForNutritionInfo();
    }

    public void setBasePrice(double mBasePrice) {
        Ensighten.evaluateEvent(this, "setBasePrice", new Object[]{new Double(mBasePrice)});
        this.mBasePrice = mBasePrice;
    }

    public String getRecipeId() {
        Ensighten.evaluateEvent(this, "getRecipeId", null);
        return this.mRecipeId;
    }

    public boolean getShowCheckBox() {
        Ensighten.evaluateEvent(this, "getShowCheckBox", null);
        return true;
    }

    public void setChecked(boolean checked) {
        Ensighten.evaluateEvent(this, "setChecked", new Object[]{new Boolean(checked)});
        this.mChecked = checked;
        notifyPropertyChanged(2);
    }

    public boolean getChecked() {
        Ensighten.evaluateEvent(this, "getChecked", null);
        return this.mChecked;
    }

    public String getThumbnailImageUrl() {
        Ensighten.evaluateEvent(this, "getThumbnailImageUrl", null);
        return this.mOrderProduct.getProduct().getThumbnailImage().getUrl();
    }

    public String getProductName() {
        Ensighten.evaluateEvent(this, "getProductName", null);
        if (!this.mIsOutage) {
            return this.mOrderProduct.getProduct().getLongName();
        }
        return String.format("%s %s", new Object[]{McDonalds.getContext().getString(C2658R.string.outage_unavailable), this.mOrderProduct.getProduct().getLongName()});
    }

    public boolean getShowUplift() {
        Ensighten.evaluateEvent(this, "getShowUplift", null);
        return ConfigurationUtils.shouldShowUpLiftPrice() && getUplift() >= 0.01d;
    }

    public String getProductUplift() {
        Ensighten.evaluateEvent(this, "getProductUplift", null);
        double uplift = getUplift();
        return String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)});
    }

    public boolean getShowNameDetails() {
        Ensighten.evaluateEvent(this, "getShowNameDetails", null);
        return false;
    }

    public String getNameDetails() {
        Ensighten.evaluateEvent(this, "getNameDetails", null);
        return null;
    }

    public String getSpecialInstructions() {
        Ensighten.evaluateEvent(this, "getSpecialInstructions", null);
        return OrderProductUtils.getCustomizationsString(this.mOrderProduct);
    }

    public void setShowInfoButton(boolean showInfoButton) {
        Ensighten.evaluateEvent(this, "setShowInfoButton", new Object[]{new Boolean(showInfoButton)});
        if (!AppUtils.hideNutritionIconOnOrderingPages()) {
            this.mShowInfoButton = showInfoButton;
            notifyPropertyChanged(30);
        }
    }

    public boolean getShowInfoButton() {
        Ensighten.evaluateEvent(this, "getShowInfoButton", null);
        return this.mShowInfoButton;
    }

    public boolean getShowHatButton() {
        Ensighten.evaluateEvent(this, "getShowHatButton", null);
        return (Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton") || !ProductUtils.productHasIngredientsOrExtras(this.mOrderProduct) || this.mOrderProduct.isChoice()) ? false : true;
    }

    public int getHatButtonResourceId() {
        Ensighten.evaluateEvent(this, "getHatButtonResourceId", null);
        Map<Integer, OrderProduct> customizations = this.mOrderProduct.getCustomizations();
        if (customizations == null || customizations.isEmpty()) {
            return C2358R.C2359drawable.icon_chef_hat;
        }
        return C2358R.C2359drawable.icon_customize_gray_selected;
    }

    public boolean getShowDisclosureArrow() {
        Ensighten.evaluateEvent(this, "getShowDisclosureArrow", null);
        return ProductUtils.hasSubChoice(this.mOrderProduct);
    }

    private double getUplift() {
        Ensighten.evaluateEvent(this, "getUplift", null);
        return ProductUtils.getProductTotalPrice(this.mOrderProduct) - this.mBasePrice;
    }

    private void checkForNutritionInfo() {
        Ensighten.evaluateEvent(this, "checkForNutritionInfo", null);
        if (ModuleManager.isModuleEnabled(NutritionModule.NAME).booleanValue()) {
            ((NutritionModule) ModuleManager.getModule(NutritionModule.NAME)).getRecipeForExternalId(this.mOrderProduct.getProduct().getExternalId().toString(), new C34661());
        }
    }
}
