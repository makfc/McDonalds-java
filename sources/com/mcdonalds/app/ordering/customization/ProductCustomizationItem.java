package com.mcdonalds.app.ordering.customization;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.LocaleList;
import com.ensighten.Ensighten;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.Locale;

public abstract class ProductCustomizationItem {
    private Context context;
    protected boolean header;
    protected Ingredient ingredient;
    protected PortionQuantity portionQuantity;
    protected OrderProduct product;
    protected int quantity;
    protected String title;
    protected int type;

    @interface ProductCustomizationType {
    }

    public abstract void decrementPortion();

    public abstract void incrementPortion();

    public ProductCustomizationItem(Ingredient ingredient, OrderProduct product, int type) {
        if (VERSION.SDK_INT >= 24) {
            Configuration config = McDonaldsApplication.getInstance().getResources().getConfiguration();
            config.setLocale(new Locale(LanguageUtil.getAppLanguage(), "HK"));
            config.setLocales(new LocaleList(new Locale[]{locale}));
            this.context = McDonalds.getContext().createConfigurationContext(config);
        } else {
            this.context = McDonalds.getContext();
        }
        this.ingredient = ingredient;
        this.product = product;
        this.type = type;
        if (product != null) {
            this.title = product.getDisplayName();
            if (product.getQuantity() != ingredient.getDefaultQuantity()) {
                this.quantity = product.getQuantity();
            } else {
                this.quantity = ingredient.getDefaultQuantity();
            }
            if (isPortion()) {
                this.portionQuantity = PortionQuantity.withQuantityAndLight(this.quantity, product.getIsLight());
            }
        }
    }

    private void normalizeQuantity() {
        Ensighten.evaluateEvent(this, "normalizeQuantity", null);
        this.product.setQuantity(getQuantityValue());
        this.product.setIsLight(isLight());
    }

    public void add() {
        Ensighten.evaluateEvent(this, "add", null);
        if (canAdd()) {
            if (isPortion()) {
                incrementPortion();
            } else {
                this.quantity++;
            }
            normalizeQuantity();
        }
    }

    public void remove() {
        Ensighten.evaluateEvent(this, "remove", null);
        if (canRemove()) {
            if (isPortion()) {
                decrementPortion();
            } else {
                this.quantity--;
            }
            normalizeQuantity();
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isPortion() {
        Ensighten.evaluateEvent(this, "isPortion", null);
        if (this.product.getProduct().getAcceptsLight() && this.ingredient.getMinQuantity() == 0 && this.ingredient.getDefaultQuantity() == 1 && this.ingredient.getMaxQuantity() == 2) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldBeTreatedAsRegularNone() {
        Ensighten.evaluateEvent(this, "shouldBeTreatedAsRegularNone", null);
        if (this.ingredient.getMinQuantity() == 0 && this.ingredient.getDefaultQuantity() == 1 && this.ingredient.getMaxQuantity() == 1) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public String getLocalizedProductUnit() {
        Ensighten.evaluateEvent(this, "getLocalizedProductUnit", null);
        String unit = this.product.getProduct().getProductUnit();
        if (unit == null) {
            return null;
        }
        Resources resources = McDonalds.getContext().getResources();
        int resourceId = resources.getIdentifier(unit.toLowerCase() + (this.quantity == 1 ? "_singular" : "_plural"), "string", McDonalds.getContext().getApplicationContext().getPackageName());
        if (resourceId == 0) {
            return null;
        }
        String unitSuffix = resources.getString(resourceId);
        return resources.getString(C2658R.string.customization_label_quantity, new Object[]{Integer.valueOf(this.quantity), unitSuffix});
    }

    public boolean isHeader() {
        Ensighten.evaluateEvent(this, "isHeader", null);
        return this.header;
    }

    public void setHeader(boolean header) {
        Ensighten.evaluateEvent(this, "setHeader", new Object[]{new Boolean(header)});
        this.header = header;
    }

    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return this.title;
    }

    public int getType() {
        Ensighten.evaluateEvent(this, "getType", null);
        return this.type;
    }

    public String getQuantity() {
        Ensighten.evaluateEvent(this, "getQuantity", null);
        if (isPortion()) {
            switch (this.portionQuantity) {
                case NONE:
                    return this.context.getString(C2658R.string.product_unit_none);
                case LIGHT:
                    return this.context.getString(C2658R.string.product_unit_light);
                case REGULAR:
                    return this.context.getString(C2658R.string.product_unit_regular);
                case EXTRA:
                    return this.context.getString(C2658R.string.product_unit_extra);
            }
        } else if (shouldBeTreatedAsRegularNone()) {
            switch (this.quantity) {
                case 0:
                    return this.context.getString(C2658R.string.product_unit_none);
                case 1:
                    return this.context.getString(C2658R.string.product_unit_regular);
                case 2:
                    return this.context.getString(C2658R.string.product_unit_extra);
            }
        } else if (this.quantity == 0) {
            return this.context.getString(C2658R.string.product_unit_none);
        }
        String productUnit = getLocalizedProductUnit();
        if (productUnit != null) {
            return productUnit;
        }
        return String.valueOf(this.quantity);
    }

    public int getQuantityValue() {
        Ensighten.evaluateEvent(this, "getQuantityValue", null);
        return this.portionQuantity != null ? this.portionQuantity.getQuantity() : this.quantity;
    }

    public boolean isLight() {
        Ensighten.evaluateEvent(this, "isLight", null);
        return this.portionQuantity != null && this.portionQuantity.isLight();
    }

    public double getCostValue() {
        Ensighten.evaluateEvent(this, "getCostValue", null);
        PriceType priceType = OrderingManager.getInstance().getCurrentOrder().getPriceType();
        if (this.ingredient.getProduct().getPrice(priceType) <= 0.0d) {
            return 0.0d;
        }
        double difference = 0.0d;
        if (this.product.getQuantity() > this.ingredient.getChargeThreshold()) {
            difference = (double) (this.product.getQuantity() - this.ingredient.getChargeThreshold());
        } else if (this.product.getQuantity() < this.ingredient.getRefundThreshold()) {
            difference = (double) (this.product.getQuantity() - this.ingredient.getRefundThreshold());
        }
        return difference * this.ingredient.getProduct().getPrice(priceType);
    }

    public String getCost() {
        Ensighten.evaluateEvent(this, "getCost", null);
        double cost = getCostValue();
        if (cost > 0.0d) {
            return UIUtils.getLocalizedCurrencyFormatter().format(cost);
        }
        return "";
    }

    public boolean canAdd() {
        Ensighten.evaluateEvent(this, "canAdd", null);
        if (isPortion()) {
            if (this.portionQuantity.ordinal() < PortionQuantity.EXTRA.ordinal()) {
                return true;
            }
            return false;
        } else if (this.quantity >= this.ingredient.getMaxQuantity()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canRemove() {
        Ensighten.evaluateEvent(this, "canRemove", null);
        if (isPortion()) {
            if (this.portionQuantity.ordinal() > PortionQuantity.NONE.ordinal()) {
                return true;
            }
            return false;
        } else if (this.quantity <= this.ingredient.getMinQuantity()) {
            return false;
        } else {
            return true;
        }
    }
}
