package com.mcdonalds.app.ordering;

import android.os.AsyncTask;
import android.os.Handler;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.OrderView;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.models.PromotionView;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderUtils {

    public static class CustomerOrdersConversionAsyncTask extends AsyncTask<Void, Void, List<Order>> implements TraceFieldInterface {
        public Trace _nr_trace;
        private boolean isFavorite;
        private List<? extends CustomerOrder> mCustomerOrders;
        private AsyncListener<List<Order>> mListener;
        private OrderingModule mOrderingModule;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        public CustomerOrdersConversionAsyncTask(List<? extends CustomerOrder> customerOrders, OrderingModule orderingModule, boolean isFavorite, AsyncListener<List<Order>> listener) {
            this.mCustomerOrders = customerOrders;
            this.mOrderingModule = orderingModule;
            this.isFavorite = isFavorite;
            this.mListener = listener;
        }

        /* Access modifiers changed, original: protected|varargs */
        public List<Order> doInBackground(Void... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            List<Order> result = new ArrayList();
            for (CustomerOrder customerOrder : this.mCustomerOrders) {
                Order order = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "access$000", new Object[]{customerOrder, this.mOrderingModule});
                if (this.isFavorite) {
                    FavoriteItem favoriteItem = (FavoriteItem) customerOrder;
                    order.setFavoriteId(favoriteItem.getFavoriteId());
                    order.setFavoriteName(favoriteItem.getName());
                } else {
                    order.setRecentId(customerOrder.getOrderId());
                    order.setRecentName(customerOrder.getName());
                    order.setOrderNumber(customerOrder.getOrderNumber());
                }
                if (ListUtils.isNotEmpty(order.getProducts())) {
                    for (OrderProduct product : order.getProducts()) {
                        if (product.getProductCode().equals("7030")) {
                            order.removeProduct(product);
                        }
                    }
                }
                result.add(order);
            }
            return result;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(List<Order> orders) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{orders});
            super.onPostExecute(orders);
            this.mListener.onResponse(orders, null, null);
        }
    }

    static class CustomerOrdersConversionThread extends Thread {
        private Handler handler = new Handler();
        private boolean isFavorite;
        private List<? extends CustomerOrder> mCustomerOrders;
        private AsyncListener<List<Order>> mListener;
        private OrderingModule mOrderingModule;

        public CustomerOrdersConversionThread(List<? extends CustomerOrder> customerOrders, OrderingModule orderingModule, boolean isFavorite, AsyncListener<List<Order>> listener) {
            this.mCustomerOrders = customerOrders;
            this.mOrderingModule = orderingModule;
            this.isFavorite = isFavorite;
            this.mListener = listener;
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            final List<Order> result = new ArrayList();
            for (CustomerOrder customerOrder : this.mCustomerOrders) {
                if (customerOrder != null) {
                    Order order = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "access$000", new Object[]{customerOrder, this.mOrderingModule});
                    if (this.isFavorite) {
                        FavoriteItem favoriteItem = (FavoriteItem) customerOrder;
                        order.setFavoriteId(favoriteItem.getFavoriteId());
                        order.setFavoriteName(favoriteItem.getName());
                    } else {
                        order.setRecentId(customerOrder.getOrderId());
                        order.setRecentName(customerOrder.getName());
                        order.setOrderNumber(customerOrder.getOrderNumber());
                    }
                    if (ListUtils.isNotEmpty(order.getProducts())) {
                        for (OrderProduct product : order.getProducts()) {
                            if (product.getProductCode().equals("7030")) {
                                order.removeProduct(product);
                            }
                        }
                    }
                    result.add(order);
                }
            }
            this.handler.post(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils$CustomerOrdersConversionThread", "access$100", new Object[]{CustomerOrdersConversionThread.this}).onResponse(result, null, null);
                }
            });
        }
    }

    public static void ordersFromFavoriteItems(List<FavoriteItem> favoriteItems, OrderingModule orderingModule, AsyncListener<List<Order>> listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "ordersFromFavoriteItems", new Object[]{favoriteItems, orderingModule, listener});
        CustomerOrdersConversionAsyncTask task = new CustomerOrdersConversionAsyncTask(favoriteItems, orderingModule, true, listener);
        Void[] voidArr = new Void[0];
        if (task instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(task, voidArr);
        } else {
            task.execute(voidArr);
        }
    }

    public static int getNumberOfRecentOrder() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getNumberOfRecentOrder", null);
        if (Configuration.getSharedInstance().hasKey("interface.numberOfRecentOrders")) {
            return Configuration.getSharedInstance().getIntForKey("interface.numberOfRecentOrders");
        }
        return 10;
    }

    public static void ordersFromCustomerOrders(List<CustomerOrder> customerOrders, OrderingModule orderingModule, AsyncListener<List<Order>> listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "ordersFromCustomerOrders", new Object[]{customerOrders, orderingModule, listener});
        new CustomerOrdersConversionThread(customerOrders, orderingModule, false, listener).start();
    }

    private static Order fromCustomerOrder(CustomerOrder customerOrder, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "fromCustomerOrder", new Object[]{customerOrder, orderingModule});
        Order order = new Order();
        order.setPriceType(OrderManager.getInstance().getCurrentOrder().getPriceType());
        order.setIsDelivery(customerOrder.isDelivery());
        if (!(customerOrder.getProducts() == null || customerOrder.getProducts().isEmpty())) {
            for (CustomerOrderProduct customerOrderProduct : customerOrder.getProducts()) {
                OrderProduct orderProduct = ProductUtils.createOrderProduct(customerOrderProduct, orderingModule);
                if (orderProduct != null) {
                    boolean z;
                    if (orderProduct.getProduct() == null || orderProduct.getProduct().getProductType() != ProductType.Meal) {
                        z = false;
                    } else {
                        z = true;
                    }
                    orderProduct.setMeal(z);
                    order.addProduct(orderProduct);
                }
            }
        }
        return order;
    }

    public static String getTotalEnergyUnit(Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getTotalEnergyUnit", new Object[]{order});
        if (order.getProducts() != null && !order.getProducts().isEmpty()) {
            return OrderProductUtils.getTotalEnergyUnit((OrderProduct) order.getProducts().iterator().next());
        }
        if (!(order.getOffers() == null || order.getOffers().isEmpty())) {
            for (OrderOffer offer : order.getOffers()) {
                if (offer.getOrderOfferProducts() != null) {
                    for (OrderOfferProduct product : offer.getOrderOfferProducts()) {
                        if (product.getSelectedProductOption() != null) {
                            return OrderProductUtils.getTotalEnergyUnit(product.getSelectedProductOption());
                        }
                    }
                    continue;
                }
            }
        }
        return "";
    }

    public static boolean orderHasUnavailableProductsOrIsEmpty(Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "orderHasUnavailableProductsOrIsEmpty", new Object[]{order});
        boolean hasUnavailableProducts = false;
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        if (com.mcdonalds.sdk.utils.ListUtils.isEmpty(order.getProducts())) {
            hasUnavailableProducts = true;
        }
        for (OrderProduct orderProduct : order.getProducts()) {
            if (!orderingModule.productIsInCurrentStore(orderProduct.getBaseProductCode())) {
                hasUnavailableProducts = true;
            }
            for (Choice choice : orderProduct.getRealChoices()) {
                if (choice.getSelection() != null) {
                    List<String> productCodes = new ArrayList();
                    checkProductInCurrentStore(choice, orderProduct, orderingModule, productCodes);
                    if (productCodes.size() > 0) {
                        hasUnavailableProducts = true;
                    }
                }
            }
        }
        return hasUnavailableProducts;
    }

    public static void checkProductInCurrentStore(OrderProduct subProduct, OrderProduct product, OrderingModule orderingModule, List<String> unavailableProductCodes) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "checkProductInCurrentStore", new Object[]{subProduct, product, orderingModule, unavailableProductCodes});
        OrderManager.checkProductInCurrentStore(subProduct, product, orderingModule, unavailableProductCodes);
    }

    public static List<OrderOffer> getProblematicOffers(List<String> problematicOfferCodes, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getProblematicOffers", new Object[]{problematicOfferCodes, order});
        ArrayList<OrderOffer> orderOffers = new ArrayList();
        boolean alreadyAdded = false;
        if (problematicOfferCodes != null) {
            int size = problematicOfferCodes.size();
            for (int i = 0; i < size; i++) {
                String offerCode = (String) problematicOfferCodes.get(i);
                for (OrderOffer orderOffer : order.getOffers()) {
                    if (orderOffer.getOffer().getOfferId().toString().equals(offerCode)) {
                        orderOffers.add(orderOffer);
                        alreadyAdded = true;
                    }
                    if (!alreadyAdded) {
                        for (int j = 0; j < orderOffer.getOffer().getProductSets().size(); j++) {
                            OfferProduct offerProduct = (OfferProduct) orderOffer.getOffer().getProductSets().get(j);
                            for (int k = 0; k < offerProduct.getProducts().size(); k++) {
                                if (((OfferProductOption) offerProduct.getProducts().get(k)).getProductCode().equals(offerCode) && !alreadyAdded) {
                                    orderOffers.add(orderOffer);
                                    alreadyAdded = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return orderOffers;
    }

    public static List<OrderProduct> getProblematicProducts(List<String> problematicProductsCodes, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getProblematicProducts", new Object[]{problematicProductsCodes, order});
        ArrayList<OrderProduct> products = new ArrayList();
        if (problematicProductsCodes != null) {
            int size = problematicProductsCodes.size();
            for (int i = 0; i < size; i++) {
                String productCode = (String) problematicProductsCodes.get(i);
                for (OrderProduct orderProduct : order.getProducts()) {
                    if (orderProduct.getRealChoices().size() > 0) {
                        for (int j = 0; j < orderProduct.getRealChoices().size(); j++) {
                            OrderProduct subChoiceProduct;
                            Choice choice = (Choice) orderProduct.getRealChoices().get(j);
                            if (choice.getProductCode().equals(productCode)) {
                                products.add(choice);
                            }
                            OrderProduct subProduct = choice.getSelection();
                            if (subProduct.getProductCode().equals(productCode)) {
                                products.add(subProduct);
                            }
                            if (subProduct.getRealChoices().size() > 0) {
                                subChoiceProduct = ((Choice) subProduct.getRealChoices().get(0)).getSelection();
                                if (subChoiceProduct.getProductCode().equals(productCode)) {
                                    products.add(subChoiceProduct);
                                }
                            }
                            if (subProduct instanceof Choice) {
                                subChoiceProduct = ((Choice) subProduct).getSelection();
                                if (subChoiceProduct.getProductCode().equals(productCode)) {
                                    products.add(subChoiceProduct);
                                }
                            }
                        }
                    }
                    if (orderProduct.getProductCode().equals(productCode)) {
                        products.add(orderProduct);
                    }
                }
            }
        }
        return products;
    }

    public static int getErrorCount(List<String> problematicErrorCodes, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getErrorCount", new Object[]{problematicErrorCodes, order});
        List<OrderProduct> products = getProblematicProducts(problematicErrorCodes, order);
        int count = problematicErrorCodes.size();
        int size = products.size();
        for (int i = 0; i < size; i++) {
            if (!order.getProducts().contains((OrderProduct) products.get(i))) {
                count--;
            }
        }
        return count;
    }

    public static int getErrorOfferCount(ArrayList<String> problematicOfferCodes, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getErrorOfferCount", new Object[]{problematicOfferCodes, order});
        return getProblematicOffers(problematicOfferCodes, order).size();
    }

    public static Order getOrderWithOnlyAvailableItems(Order originalOrder) {
        int i;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getOrderWithOnlyAvailableItems", new Object[]{originalOrder});
        Order order = Order.cloneOrderForEditing(originalOrder);
        ArrayList<OrderProduct> removeOrders = new ArrayList();
        Iterator<OrderProduct> originalOrderProductList = originalOrder.getProducts().iterator();
        Iterator<OrderProduct> copyOrderProductList = order.getProducts().iterator();
        while (originalOrderProductList.hasNext() && originalOrderProductList != null) {
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            OrderProduct orderProduct = (OrderProduct) originalOrderProductList.next();
            OrderProduct copyOrderProduct = (OrderProduct) copyOrderProductList.next();
            if (!orderingModule.productIsInCurrentStore(orderProduct.getProduct().getExternalId().intValue())) {
                removeOrders.add(copyOrderProduct);
                orderProduct.setUnavailable(true);
            }
            for (i = 0; i < orderProduct.getRealChoices().size(); i++) {
                OrderProduct choice = ((Choice) orderProduct.getRealChoices().get(i)).getSelection();
                if (!orderingModule.productIsInCurrentStore(choice.getProduct().getExternalId().intValue())) {
                    removeOrders.add(copyOrderProduct);
                    orderProduct.setUnavailable(true);
                    choice.setUnavailable(true);
                }
            }
        }
        for (i = removeOrders.size() - 1; i >= 0; i--) {
            order.removeProduct((OrderProduct) removeOrders.get(i));
        }
        return order;
    }

    public static boolean isProductBagProduct(OrderProduct product) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "isProductBagProduct", new Object[]{product});
        OrderManager orderManager = OrderManager.getInstance();
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (orderManager.isBagChargeAdded()) {
            int productCode = Integer.parseInt(product.getProductCode());
            if (productCode == store.getBagProductCode() || productCode == store.getNoBagProductCode()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> getProblematicOfferCodes(OrderResponse orderResponse, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getProblematicOfferCodes", new Object[]{orderResponse, order});
        ArrayList<Integer> problematicProductCodes = new ArrayList();
        if (!(order.getTotalizeResult() == null || order.getTotalizeResult().getTotalValue() == null)) {
            OrderView responseOrderView = orderResponse.getOrderView();
            OrderView mOrderOrderView = order.getTotalizeResult().getOrderView();
            int size = responseOrderView.getPromotionalItems().size();
            for (int i = 0; i < size; i++) {
                PromotionView productViewAfterCheckin = (PromotionView) responseOrderView.getPromotionalItems().get(i);
                int k = 0;
                while (k < productViewAfterCheckin.getProductSet().size()) {
                    int productCodeAfterCheckIn = ((ProductView) productViewAfterCheckin.getProductSet().get(k)).getProductCode().intValue();
                    ProductView productViewBeforeCheckin = (ProductView) ((PromotionView) mOrderOrderView.getPromotionalItems().get(i)).getProductSet().get(k);
                    int productCodeBeforeCheckIn = productViewBeforeCheckin.getProductCode().intValue();
                    int errProductCodeAfter = productViewBeforeCheckin.getValidationErrorCode().intValue();
                    problematicProductCodes.addAll(getOutOfStockProductFromChoices(productViewBeforeCheckin));
                    if (productCodeBeforeCheckIn == productCodeAfterCheckIn && errProductCodeAfter == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) {
                        productViewBeforeCheckin.setValidationErrorCode(Integer.valueOf(errProductCodeAfter));
                        if (!problematicProductCodes.contains(Integer.valueOf(productCodeAfterCheckIn))) {
                            problematicProductCodes.add(Integer.valueOf(productCodeAfterCheckIn));
                        }
                    } else {
                        k++;
                    }
                }
            }
        }
        return problematicProductCodes;
    }

    private static ArrayList<Integer> getOutOfStockProductFromChoices(ProductView productView) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getOutOfStockProductFromChoices", new Object[]{productView});
        ArrayList<Integer> tempProblematicProductCodes = new ArrayList();
        if (ListUtils.isNotEmpty(productView.getChoices())) {
            Iterator it = productView.getChoices().iterator();
            while (it.hasNext()) {
                ProductView choiceView = (ProductView) it.next();
                if (choiceView.getValidationErrorCode().intValue() == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) {
                    tempProblematicProductCodes.add(choiceView.getProductCode());
                }
                tempProblematicProductCodes.addAll(getOutOfStockProductFromChoices(choiceView));
            }
        }
        return tempProblematicProductCodes;
    }

    public static ArrayList<Integer> getProblematicProductCodes(OrderResponse orderResponse, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "getProblematicProductCodes", new Object[]{orderResponse, order});
        ArrayList<Integer> problematicProductCodes = new ArrayList();
        if (!(order.getTotalizeResult() == null || order.getTotalizeResult().getTotalValue() == null)) {
            OrderView responseOrderView = orderResponse.getOrderView();
            OrderView mOrderOrderView = order.getTotalizeResult().getOrderView();
            int size = responseOrderView.getProducts().size();
            for (int i = 0; i < size; i++) {
                ProductView productViewAfterCheckin = (ProductView) responseOrderView.getProducts().get(i);
                int productCodeAfterCheckIn = productViewAfterCheckin.getProductCode().intValue();
                int prodSize = mOrderOrderView.getProducts().size();
                for (int j = 0; j < prodSize; j++) {
                    ProductView productViewBeforeCheckin = (ProductView) mOrderOrderView.getProducts().get(j);
                    int productCodeBeforeCheckIn = productViewBeforeCheckin.getProductCode().intValue();
                    int errProductCodeAfter = productViewAfterCheckin.getValidationErrorCode().intValue();
                    if (productCodeBeforeCheckIn == productCodeAfterCheckIn && errProductCodeAfter == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) {
                        productViewBeforeCheckin.setValidationErrorCode(Integer.valueOf(errProductCodeAfter));
                        if (!problematicProductCodes.contains(Integer.valueOf(productCodeAfterCheckIn))) {
                            problematicProductCodes.add(Integer.valueOf(productCodeAfterCheckIn));
                        }
                        if (ListUtils.isNotEmpty(productViewAfterCheckin.getChoices())) {
                            Iterator it = productViewAfterCheckin.getChoices().iterator();
                            while (it.hasNext()) {
                                ArrayList<Integer> problematicChoiceList = loopOverChoiceSolutionsForErrorCodes((ProductView) it.next());
                                if (ListUtils.isNotEmpty(problematicChoiceList)) {
                                    problematicProductCodes.addAll(problematicChoiceList);
                                }
                            }
                        }
                    }
                }
            }
        }
        return problematicProductCodes;
    }

    private static ArrayList<Integer> loopOverChoiceSolutionsForErrorCodes(ProductView choice) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "loopOverChoiceSolutionsForErrorCodes", new Object[]{choice});
        ArrayList<Integer> tempProblematicChoiceList = new ArrayList();
        if (choice.getValidationErrorCode().intValue() == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) {
            tempProblematicChoiceList.add(choice.getProductCode());
        }
        if (ListUtils.isNotEmpty(choice.getChoices())) {
            Iterator it = choice.getChoices().iterator();
            while (it.hasNext()) {
                tempProblematicChoiceList.addAll(loopOverChoiceSolutionsForErrorCodes((ProductView) it.next()));
            }
        }
        return tempProblematicChoiceList;
    }

    public static void clearTotalizeResponses(Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.OrderUtils", "clearTotalizeResponses", new Object[]{order});
        order.setPreparePaymentResult(null);
        order.setTotalizeResult(null);
        order.setCheckinResult(null);
        order.setCheckoutResult(null);
    }
}
