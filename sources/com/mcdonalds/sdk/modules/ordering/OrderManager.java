package com.mcdonalds.sdk.modules.ordering;

import android.support.annotation.Nullable;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final String CONFIG_ALLOW_DOWN_CHARGE_DELIVERY = "modules.delivery.allowDownCharge";
    private static final String CONFIG_ALLOW_DOWN_CHARGE_ORDERING = "modules.ordering.allowDownCharge";
    private static OrderManager sOrderManager;
    private Order mCurrentOrder = null;

    public static OrderManager getInstance() {
        if (sOrderManager == null) {
            sOrderManager = new OrderManager();
        }
        return sOrderManager;
    }

    private OrderManager() {
    }

    public Order getCurrentOrder() {
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (ModuleManager.getSharedInstance().isOrderingAvailable()) {
            if (customerModule == null) {
                throw new AsyncException("Customer Module unavailable");
            }
            if (this.mCurrentOrder == null) {
                Order savedOrder = LocalDataManager.getSharedInstance().getCurrentOrder();
                if (savedOrder != null) {
                    this.mCurrentOrder = savedOrder;
                } else {
                    this.mCurrentOrder = newOrder(customerModule.getCurrentProfile());
                }
                cleanBagsFromOrder();
            }
            this.mCurrentOrder.setProfile(customerModule.getCurrentProfile());
        }
        if (this.mCurrentOrder == null) {
            this.mCurrentOrder = newOrder(customerModule.getCurrentProfile());
        }
        return this.mCurrentOrder;
    }

    public void deleteCurrentOrder() {
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (isDeliveryOrder()) {
            Order order = newOrder(customerModule.getCurrentProfile());
            order.setDeliveryStore(this.mCurrentOrder.getDeliveryStore());
            order.setDeliveryAddress(this.mCurrentOrder.getDeliveryAddress());
            order.setDeliveryDate(this.mCurrentOrder.getDeliveryDate());
            order.setIsDelivery(true);
            order.setPriceType(PriceType.Delivery);
            this.mCurrentOrder = order;
        } else {
            this.mCurrentOrder = newOrder(customerModule.getCurrentProfile());
        }
        updateCurrentOrder(this.mCurrentOrder);
        NotificationCenter.getSharedInstance().postNotification(ModuleManager.ORDER_CHANGED_NOTIFICATION);
    }

    public void deleteCurrentOrderAndAddress() {
        this.mCurrentOrder = newOrder(((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile());
        updateCurrentOrder(this.mCurrentOrder);
        NotificationCenter.getSharedInstance().postNotification(ModuleManager.ORDER_CHANGED_NOTIFICATION);
    }

    public void updateCurrentOrder(Order order) {
        this.mCurrentOrder = order;
        LocalDataManager.getSharedInstance().setCurrentOrder(this.mCurrentOrder);
    }

    public Store getCurrentStore() {
        if (!isDeliveryOrder() || getCurrentOrder().getDeliveryStore() == null) {
            return ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        }
        return getCurrentOrder().getDeliveryStore();
    }

    public int getMaxBasketQuantity() {
        if (isDeliveryOrder()) {
            return ((DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME)).getMaxBasketQuantity();
        }
        return ((OrderingModule) ModuleManager.getModule("ordering")).getMaxBasketQuantity();
    }

    public boolean allowBagCharges() {
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (store == null) {
            return false;
        }
        boolean bagChargeEnabled = store.isBagChargeEnabled();
        boolean storeHasProduct = ((OrderingModule) ModuleManager.getModule("ordering")).productIsInCurrentStore(store.getBagProductCode());
        if (bagChargeEnabled && storeHasProduct) {
            return true;
        }
        return false;
    }

    public OrderProduct getBagProductInOrder() {
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (store != null) {
            return getOrderProductInOrder(store.getBagProductCode());
        }
        return null;
    }

    private OrderProduct getNoBagProductInOrder() {
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (store != null) {
            return getOrderProductInOrder(store.getNoBagProductCode());
        }
        return null;
    }

    @Nullable
    private OrderProduct getOrderProductInOrder(int productCode) {
        for (OrderProduct orderProduct : getCurrentOrder().getProducts()) {
            if (orderProduct.getProduct().getExternalId().intValue() == productCode) {
                return orderProduct;
            }
        }
        return null;
    }

    @Nullable
    private void removeOrderProductFromOrder(int productCode) {
        Order order = getCurrentOrder();
        for (OrderProduct orderProduct : order.getProducts()) {
            if (orderProduct.getProduct().getExternalId().intValue() == productCode) {
                order.removeProduct(orderProduct);
                updateCurrentOrder(order);
            }
        }
    }

    public boolean isBagChargeAdded() {
        return (getBagProductInOrder() == null && getNoBagProductInOrder() == null) ? false : true;
    }

    public void cleanBagsFromOrder() {
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (store != null) {
            int bagProductCode = store.getBagProductCode();
            int noBagProductCode = store.getNoBagProductCode();
            removeOrderProductFromOrder(bagProductCode);
            removeOrderProductFromOrder(noBagProductCode);
        }
    }

    public boolean allowDownCharge() {
        return allowDownCharge(getCurrentOrder());
    }

    public boolean allowDownCharge(Order order) {
        String key;
        if (order == null || order.isDelivery()) {
            key = CONFIG_ALLOW_DOWN_CHARGE_DELIVERY;
        } else {
            key = CONFIG_ALLOW_DOWN_CHARGE_ORDERING;
        }
        return Configuration.getSharedInstance().getBooleanForKey(key);
    }

    private Order newOrder(CustomerProfile profile) {
        if (!ModuleManager.getSharedInstance().isOrderingAvailable()) {
            return null;
        }
        Order order = new Order();
        order.setProfile(profile);
        order.setPriceType(PriceType.EatIn);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule == null || customerModule.getCurrentStore() == null) {
            return order;
        }
        order.setStoreId(Integer.toString(customerModule.getCurrentStore().getStoreId()));
        return order;
    }

    private boolean isDeliveryOrder() {
        return this.mCurrentOrder != null && this.mCurrentOrder.isDelivery();
    }

    public static void updateProductsForOrder(final AsyncListener<Void> listener) {
        Order currentOrder = getInstance().getCurrentOrder();
        if (ListUtils.isEmpty(currentOrder.getProducts())) {
            listener.onResponse(null, null, null);
        } else {
            updateProducts(currentOrder, new AsyncCounter(currentOrder.getProducts().size(), new AsyncListener<List<OrderProduct>>() {
                public void onResponse(List<OrderProduct> list, AsyncToken token, AsyncException exception) {
                    if (listener != null) {
                        listener.onResponse(null, token, exception);
                    }
                }
            }));
        }
    }

    public static void updateProducts(Order order, final AsyncCounter<OrderProduct> counter) {
        final List<String> unavailableProductCodes = new ArrayList();
        order.setUnavailableProductCodes(unavailableProductCodes);
        final OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        for (final OrderProduct orderProduct : order.getProducts()) {
            orderingModule.getProductForExternalId(orderProduct.getProductCode(), new AsyncListener<Product>() {
                public void onResponse(Product response, AsyncToken token, AsyncException exception) {
                    if (exception == null || exception.getMessage().equalsIgnoreCase("no product found")) {
                        if (response != null) {
                            OrderManager.checkProductAvailability(orderProduct, orderingModule, unavailableProductCodes);
                            if (!orderProduct.isUnavailable()) {
                                orderProduct.setProduct(response);
                            }
                        } else {
                            orderProduct.setUnavailable(true);
                            if (!unavailableProductCodes.contains(orderProduct.getProductCode())) {
                                unavailableProductCodes.add(orderProduct.getProductCode());
                            }
                        }
                        if (counter != null) {
                            counter.success(orderProduct);
                        }
                    } else if (counter != null) {
                        counter.error(exception);
                    }
                }
            });
        }
    }

    private static void checkProductAvailability(OrderProduct orderProduct, OrderingModule orderingModule, List<String> unavailableProductCodes) {
        if (orderProduct != null) {
            if (orderingModule.productIsInCurrentStore(Integer.parseInt(orderProduct.getProductCode()))) {
                Choice choice;
                orderProduct.setUnavailable(false);
                if (orderProduct.getRealChoices().size() > 0) {
                    for (int i = 0; i < orderProduct.getRealChoices().size(); i++) {
                        choice = (Choice) orderProduct.getRealChoices().get(i);
                        checkProductAvailability(choice, orderingModule, unavailableProductCodes);
                        if (choice.isUnavailable()) {
                            orderProduct.setUnavailable(true);
                        }
                    }
                }
                if (orderProduct instanceof Choice) {
                    choice = (Choice) orderProduct;
                    OrderProduct selection = choice.getSelection();
                    if (selection != null) {
                        checkProductAvailability(selection, orderingModule, unavailableProductCodes);
                        if (selection.isUnavailable()) {
                            choice.setUnavailable(true);
                        }
                    }
                }
            } else {
                orderProduct.setUnavailable(true);
            }
            if (orderProduct.isUnavailable() && !unavailableProductCodes.contains(orderProduct.getProductCode())) {
                unavailableProductCodes.add(orderProduct.getProductCode());
            }
        }
    }

    public static void checkProductInCurrentStore(OrderProduct subProduct, OrderProduct product, OrderingModule orderingModule, List<String> unavailableProductCodes) {
        if (product != null && subProduct != null) {
            if (orderingModule.productIsInCurrentStore(Integer.parseInt(subProduct.getProductCode()))) {
                subProduct.setUnavailable(false);
                return;
            }
            subProduct.setUnavailable(true);
            if (!unavailableProductCodes.contains(product.getProductCode())) {
                unavailableProductCodes.add(product.getProductCode());
            }
            if (!unavailableProductCodes.contains(subProduct.getProductCode())) {
                unavailableProductCodes.add(unavailableProductCodes.size(), subProduct.getProductCode());
            }
        }
    }

    private static int getChoicesCount(List<Choice> choices) {
        int result = 0;
        for (Choice choice : choices) {
            result++;
            if (choice.getActualChoice() != null) {
                result++;
            }
        }
        return result;
    }

    private static List<Choice> getChoiceList(List<OrderProduct> orderProducts) {
        List<Choice> result = new ArrayList();
        if (orderProducts != null) {
            for (OrderProduct orderProduct : orderProducts) {
                if (orderProduct instanceof Choice) {
                    result.add((Choice) orderProduct);
                }
            }
        }
        return result;
    }
}
