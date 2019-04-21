package com.mcdonalds.app.ordering.bagcharge;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.util.ProductDetailsItemPresenter;
import com.mcdonalds.app.util.ProductQuantityControlsPresenter;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;

public class BagChargePresenter extends BaseObservable implements ProductDetailsItemPresenter, ProductQuantityControlsPresenter {
    private boolean enableContinueButton = true;
    private boolean enableMinusButton = true;
    private boolean enablePlusButton = true;
    private OrderManager mOrderManager = OrderManager.getInstance();
    private OrderProduct mOrderProduct;
    private OrderingModule mOrderingModule;
    private Store mStore = this.mOrderManager.getCurrentStore();
    private BagChargeView mView;
    private double price;
    private String productName;
    private int quantity;
    private String thumbnailImageUrl;

    public BagChargePresenter(BagChargeView view) {
        this.mView = view;
        int bagProductId = this.mStore.getBagProductCode();
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mOrderProduct = OrderProduct.createProduct(this.mOrderingModule.getProductForExternalId(String.valueOf(bagProductId), true), Integer.valueOf(0));
        if (this.mOrderProduct == null) {
            this.mView.proceedToCheckout();
            return;
        }
        this.productName = this.mOrderProduct.getDisplayName();
        this.thumbnailImageUrl = this.mOrderProduct.getDisplayThumbnailImage().getUrl();
        updateQuantityAndTotal(0);
        checkQuantity();
    }

    public void increaseQuantity() {
        Ensighten.evaluateEvent(this, "increaseQuantity", null);
        int quantity = this.mOrderProduct.getQuantity() + 1;
        if (quantity <= 2) {
            updateQuantityAndTotal(quantity);
            checkQuantity();
        }
    }

    public void decreaseQuantity() {
        Ensighten.evaluateEvent(this, "decreaseQuantity", null);
        int quantity = this.mOrderProduct.getQuantity() - 1;
        if (quantity >= 0) {
            updateQuantityAndTotal(quantity);
            checkQuantity();
        }
    }

    private void updateQuantityAndTotal(int quantity) {
        Ensighten.evaluateEvent(this, "updateQuantityAndTotal", new Object[]{new Integer(quantity)});
        this.mOrderProduct.setQuantity(quantity);
        setPrice(ProductUtils.getProductTotalPrice(this.mOrderProduct));
        setQuantity(quantity);
    }

    public void dismiss() {
        Ensighten.evaluateEvent(this, "dismiss", null);
        OrderProduct noBagOrderProduct = OrderProduct.createProduct(this.mOrderingModule.getProductForExternalId(String.valueOf(this.mStore.getNoBagProductCode()), false), Integer.valueOf(1));
        if (noBagOrderProduct != null) {
            Order order = this.mOrderManager.getCurrentOrder();
            order.addProduct(noBagOrderProduct);
            this.mOrderManager.updateCurrentOrder(order);
        }
        this.mView.proceedToCheckout();
    }

    public void confirm() {
        Ensighten.evaluateEvent(this, "confirm", null);
        Order order = this.mOrderManager.getCurrentOrder();
        order.addProduct(this.mOrderProduct);
        this.mOrderManager.updateCurrentOrder(order);
        this.mView.proceedToCheckout();
    }

    private void checkQuantity() {
        Ensighten.evaluateEvent(this, "checkQuantity", null);
        int quantity = this.mOrderProduct.getQuantity();
        if (quantity <= 0) {
            setEnableMinusButton(false);
            setEnableContinueButton(false);
        } else if (quantity >= 2) {
            setEnablePlusButton(false);
            setEnableContinueButton(true);
        } else {
            setEnableMinusButton(true);
            setEnablePlusButton(true);
            setEnableContinueButton(true);
        }
    }

    public void setQuantity(int quantity) {
        Ensighten.evaluateEvent(this, "setQuantity", new Object[]{new Integer(quantity)});
        this.quantity = quantity;
        notifyPropertyChanged(20);
    }

    public void setPrice(double price) {
        Ensighten.evaluateEvent(this, "setPrice", new Object[]{new Double(price)});
        this.price = price;
        notifyPropertyChanged(54);
    }

    public void setEnableMinusButton(boolean enableMinusButton) {
        Ensighten.evaluateEvent(this, "setEnableMinusButton", new Object[]{new Boolean(enableMinusButton)});
        this.enableMinusButton = enableMinusButton;
        notifyPropertyChanged(8);
    }

    public void setEnablePlusButton(boolean enablePlusButton) {
        Ensighten.evaluateEvent(this, "setEnablePlusButton", new Object[]{new Boolean(enablePlusButton)});
        this.enablePlusButton = enablePlusButton;
        notifyPropertyChanged(10);
    }

    public void setEnableContinueButton(boolean enableContinueButton) {
        Ensighten.evaluateEvent(this, "setEnableContinueButton", new Object[]{new Boolean(enableContinueButton)});
        this.enableContinueButton = enableContinueButton;
        notifyPropertyChanged(7);
    }

    public boolean getShowCheckBox() {
        Ensighten.evaluateEvent(this, "getShowCheckBox", null);
        return false;
    }

    public boolean getChecked() {
        Ensighten.evaluateEvent(this, "getChecked", null);
        return false;
    }

    @Bindable
    public String getThumbnailImageUrl() {
        Ensighten.evaluateEvent(this, "getThumbnailImageUrl", null);
        return this.thumbnailImageUrl;
    }

    @Bindable
    public String getProductName() {
        Ensighten.evaluateEvent(this, "getProductName", null);
        return this.productName;
    }

    public boolean getShowUplift() {
        Ensighten.evaluateEvent(this, "getShowUplift", null);
        return false;
    }

    public String getProductUplift() {
        Ensighten.evaluateEvent(this, "getProductUplift", null);
        return null;
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
        return null;
    }

    public boolean getShowInfoButton() {
        Ensighten.evaluateEvent(this, "getShowInfoButton", null);
        return false;
    }

    public boolean getShowHatButton() {
        Ensighten.evaluateEvent(this, "getShowHatButton", null);
        return false;
    }

    public int getHatButtonResourceId() {
        Ensighten.evaluateEvent(this, "getHatButtonResourceId", null);
        return 0;
    }

    public boolean getShowDisclosureArrow() {
        Ensighten.evaluateEvent(this, "getShowDisclosureArrow", null);
        return false;
    }

    @Bindable
    public String getQuantity() {
        Ensighten.evaluateEvent(this, "getQuantity", null);
        return String.valueOf(this.quantity);
    }

    @Bindable
    public boolean getEnablePlusButton() {
        Ensighten.evaluateEvent(this, "getEnablePlusButton", null);
        return this.enablePlusButton;
    }

    @Bindable
    public boolean getEnableMinusButton() {
        Ensighten.evaluateEvent(this, "getEnableMinusButton", null);
        return this.enableMinusButton;
    }

    @Bindable
    public String getTotalPrice() {
        Ensighten.evaluateEvent(this, "getTotalPrice", null);
        return UIUtils.getLocalizedCurrencyFormatter().format(this.price);
    }
}
