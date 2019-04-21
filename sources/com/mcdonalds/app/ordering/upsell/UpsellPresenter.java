package com.mcdonalds.app.ordering.upsell;

import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import java.util.List;

public class UpsellPresenter {
    private CustomerModule mCustomerModule = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME));
    private List<Product> mProducts;
    private SparseArray<OrderProduct> mSelectedItems = new SparseArray();
    private UpsellView mView;

    public UpsellPresenter(UpsellView view, List<Product> products) {
        this.mView = view;
        this.mProducts = products;
        presentUpsellItems();
    }

    public void toggleSelection(int position) {
        Ensighten.evaluateEvent(this, "toggleSelection", new Object[]{new Integer(position)});
        if (((OrderProduct) this.mSelectedItems.get(position)) != null) {
            setItemUnselected(position);
        } else {
            setItemSelected(position);
        }
    }

    private void setItemSelected(int position) {
        Ensighten.evaluateEvent(this, "setItemSelected", new Object[]{new Integer(position)});
        if (this.mProducts.size() > position) {
            this.mSelectedItems.put(position, OrderProduct.createProduct((Product) this.mProducts.get(position), Integer.valueOf(1)));
            this.mView.markItemSelected(position);
        }
    }

    private void setItemUnselected(int position) {
        Ensighten.evaluateEvent(this, "setItemUnselected", new Object[]{new Integer(position)});
        this.mSelectedItems.delete(position);
        this.mView.markItemUnselected(position);
    }

    public void increaseItemQuantity(int position) {
        Ensighten.evaluateEvent(this, "increaseItemQuantity", new Object[]{new Integer(position)});
        changeItemQuantity(position, 1);
    }

    public void decreaseItemQuantity(int position) {
        Ensighten.evaluateEvent(this, "decreaseItemQuantity", new Object[]{new Integer(position)});
        changeItemQuantity(position, -1);
    }

    public void addItemsToBasket() {
        Ensighten.evaluateEvent(this, "addItemsToBasket", null);
        if (this.mSelectedItems.size() != 0) {
            Order order = OrderingManager.getInstance().getCurrentOrder();
            boolean success = true;
            for (int i = 0; i < this.mSelectedItems.size() && success; i++) {
                OrderProduct selectedItem = (OrderProduct) this.mSelectedItems.get(this.mSelectedItems.keyAt(i));
                if (selectedItem.getQuantity() > 0) {
                    success = order.addProduct(selectedItem);
                }
            }
            this.mView.showCart();
        }
    }

    private void changeItemQuantity(int position, int quantityChange) {
        Ensighten.evaluateEvent(this, "changeItemQuantity", new Object[]{new Integer(position), new Integer(quantityChange)});
        OrderProduct productToIncrease = (OrderProduct) this.mSelectedItems.get(position);
        if (productToIncrease != null) {
            int newQuantity = productToIncrease.getQuantity() + quantityChange;
            if (newQuantity <= getMaxItemQuantity(productToIncrease)) {
                productToIncrease.setQuantity(newQuantity);
                if (newQuantity <= 0) {
                    setItemUnselected(position);
                } else {
                    this.mView.updateItemQuantity(position, newQuantity);
                }
            }
        }
    }

    private int getMaxItemQuantity(OrderProduct product) {
        Ensighten.evaluateEvent(this, "getMaxItemQuantity", new Object[]{product});
        int itemsInBasket = OrderingManager.getInstance().getCurrentOrder().getItemsCount();
        int otherItemsQuantity = 0;
        for (int i = 0; i < this.mSelectedItems.size(); i++) {
            OrderProduct selectedProduct = (OrderProduct) this.mSelectedItems.get(this.mSelectedItems.keyAt(i));
            if (!selectedProduct.equals(product)) {
                otherItemsQuantity += selectedProduct.getQuantity();
            }
        }
        return (this.mCustomerModule.getMaxItemQuantity() - itemsInBasket) - otherItemsQuantity;
    }

    private void presentUpsellItems() {
        Ensighten.evaluateEvent(this, "presentUpsellItems", null);
        this.mView.displayItems(this.mProducts, OrderingManager.getInstance().getCurrentOrderPriceType());
    }
}
