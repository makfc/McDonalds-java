package com.mcdonalds.sdk.modules.ordering;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.OrderingConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.KioskCheckinResponse;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.repository.CategoryRepository;
import com.mcdonalds.sdk.services.data.repository.MenuTypeRepository;
import com.mcdonalds.sdk.services.data.repository.PaymentMethodRepository;
import com.mcdonalds.sdk.services.data.repository.ProductRepository;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderingModule extends BaseModule {
    public static final String CONNECTOR_KEY = "connector";
    private static final String KEY_ALLOW_ZERO_PRICED_ORDER_DELIVERY = "modules.delivery.allowZeroPriceOrder";
    private static final String KEY_ALLOW_ZERO_PRICED_ORDER_PICKUP = "modules.ordering.allowZeroPriceOrder";
    public static final String NAME = "ordering";
    private OrderingConnector mConnector = ((OrderingConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey("modules.ordering.connector")));
    private Context mContext;

    public OrderingModule(Context context) {
        this.mContext = context;
    }

    private Order getCurrentOrder() {
        return OrderManager.getInstance().getCurrentOrder();
    }

    public AsyncToken checkStoreListForOrderingSupport(List<Store> stores, Location location, @NonNull final AsyncListener<List<Store>> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("checkStoresForOrderingSupport");
        this.mConnector.checkMobileOrderingSupportForStores(stores, location, new AsyncListener<List<Store>>() {
            public void onResponse(List<Store> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    requestListener.onResponse(response, moduleToken, null);
                } else {
                    requestListener.onResponse(null, moduleToken, exception);
                }
            }
        });
        return moduleToken;
    }

    public AsyncToken getStoreOrderingCapabilties(Store store, @NonNull final AsyncListener<StoreCapabilties> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("getStoreOrderingCapabilties");
        this.mConnector.getStoreOrderingCapabilties(String.valueOf(store.getStoreId()), new AsyncListener<StoreCapabilties>() {
            public void onResponse(StoreCapabilties capabilties, AsyncToken token, AsyncException exception) {
                requestListener.onResponse(capabilties, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public AsyncToken checkStoreForOrderingSupport(Store store, @NonNull final AsyncListener<Boolean> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("checkStoreForOrderingSupport");
        this.mConnector.checkMobileOrderingSupport(Integer.valueOf(store.getStoreId()), new AsyncListener<Store>() {
            public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    requestListener.onResponse(Boolean.valueOf(true), moduleToken, null);
                } else {
                    requestListener.onResponse(Boolean.valueOf(false), moduleToken, exception);
                }
            }
        });
        return moduleToken;
    }

    public AsyncToken getStoreInformation(Store store, @NonNull AsyncListener<Store> requestListener) {
        AsyncToken moduleToken = new AsyncToken("checkStoreForOrderingSupport");
        this.mConnector.checkMobileOrderingSupport(Integer.valueOf(store.getStoreId()), requestListener);
        return moduleToken;
    }

    public AsyncToken getStoreFromList(List<String> storeIds, Date deliveryTime, boolean isNormalOrder, AsyncListener<Store> listener) {
        AsyncToken moduleToken = new AsyncToken("checkStoreForOrderingSupport");
        this.mConnector.getStoreFromList(deliveryTime, isNormalOrder, getCurrentOrder().getTotalValue(), storeIds, listener);
        return moduleToken;
    }

    public boolean allowZeroPricedOrder() {
        if (getCurrentOrder().isDelivery()) {
            return Configuration.getSharedInstance().getBooleanForKey(KEY_ALLOW_ZERO_PRICED_ORDER_DELIVERY);
        }
        return Configuration.getSharedInstance().getBooleanForKey(KEY_ALLOW_ZERO_PRICED_ORDER_PICKUP);
    }

    public List<MenuType> getMenuTypes() {
        return MenuTypeRepository.getValid(this.mContext);
    }

    private MenuType createMenuType(int menuTypeId, String shortName, String displayImage) {
        MenuType menuType = new MenuType();
        menuType.setID(menuTypeId);
        menuType.setShortName(shortName);
        menuType.setDisplayImage(displayImage);
        return menuType;
    }

    public Date getOrderTime() {
        Order order = getCurrentOrder();
        if (order != null && order.isDelivery() && order.getDeliveryDate() != null && !order.isNormalOrder()) {
            return order.getDeliveryDate();
        }
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (store != null) {
            return store.getCurrentDate();
        }
        return new Date();
    }

    public AsyncToken totalize(@NonNull final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("totalize");
        this.mConnector.totalize(getCurrentOrder(), new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                requestListener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public AsyncToken totalize(Order forOrder, @NonNull final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("totalize");
        this.mConnector.totalize(forOrder, new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                requestListener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public AsyncToken checkin(String checkinData, String password, @NonNull final AsyncListener<OrderResponse> requestListener) {
        final AsyncToken moduleToken = new AsyncToken("checkin");
        this.mConnector.checkin(getCurrentOrder(), checkinData, password, new AsyncListener<OrderResponse>() {
            public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    requestListener.onResponse(response, moduleToken, null);
                } else {
                    requestListener.onResponse(null, moduleToken, exception);
                }
            }
        });
        return moduleToken;
    }

    public AsyncToken checkinKiosk(String password, @NonNull final AsyncListener<KioskCheckinResponse> listener) {
        final AsyncToken moduleToken = new AsyncToken("#checkinKiosk");
        this.mConnector.checkinKiosk(getCurrentOrder(), password, new AsyncListener<KioskCheckinResponse>() {
            public void onResponse(KioskCheckinResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, moduleToken, exception);
            }
        });
        return moduleToken;
    }

    public AsyncToken preparePayment(Order order, @NonNull AsyncListener<OrderResponse> requestListener) {
        AsyncToken moduleToken = new AsyncToken("preparePayment");
        this.mConnector.preparePayment(order, requestListener);
        return moduleToken;
    }

    public Boolean supportsDayParts() {
        return Boolean.valueOf(true);
    }

    public int getMaxBasketQuantity() {
        return this.mConnector.getMaxBasketQuantity();
    }

    public int getMinMinutesToAdvOrder() {
        return this.mConnector.getMinMinutesToAdvOrder();
    }

    public int getMaxMinutesToAdvOrder() {
        return this.mConnector.getMaxMinutesToAdvOrder();
    }

    public void getPaymentMethods(@NonNull AsyncListener<List<PaymentMethod>> listener) {
        listener.onResponse(PaymentMethodRepository.getAll(this.mContext), null, null);
    }

    public PaymentMethod getPaymentMethodForId(int paymentId) {
        return PaymentMethodRepository.getById(paymentId, this.mContext);
    }

    public Map<Category, List<Product>> getProductsForOrdering(String pod, int menuTypeId, String searchQuery) {
        Map<Category, List<Product>> productMap = ProductRepository.getProductMap(this.mContext, pod, menuTypeId, searchQuery);
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            int storeId = store.getStoreId();
            if (CatalogManager.hasCatalogDownloaded(this.mContext, storeId) && ((productMap == null || productMap.isEmpty()) && searchQuery.isEmpty() && CatalogManager.getSyncStatus() == 1)) {
                CatalogManager.markMarketCatalogDirty(this.mContext);
                CatalogManager.markStoreCatalogDirty(this.mContext, storeId);
                CatalogManager.requestSync();
            }
        }
        return productMap;
    }

    public void getProductForId(final String itemId, @NonNull final AsyncListener<Product> listener) {
        C26098 c26098 = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            public Product doInBackground(Void... params) {
                return ProductRepository.getByRecipeId(OrderingModule.this.mContext, Integer.parseInt(itemId));
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(Product product) {
                listener.onResponse(product, null, null);
            }
        };
        Void[] voidArr = new Void[0];
        if (c26098 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c26098, voidArr);
        } else {
            c26098.execute(voidArr);
        }
    }

    public void getProductForExternalId(final String externalId, @NonNull final AsyncListener<Product> listener, boolean resolveProductDetailsNoDimen) {
        C26109 c26109 = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            public Product doInBackground(Void... params) {
                return ProductRepository.getByProductCode(OrderingModule.this.mContext, Integer.parseInt(externalId), false);
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(Product product) {
                listener.onResponse(product, null, null);
            }
        };
        Void[] voidArr = new Void[0];
        if (c26109 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c26109, voidArr);
        } else {
            c26109.execute(voidArr);
        }
    }

    public Product getProductForExternalId(String externalId) {
        return ProductRepository.getByProductCode(this.mContext, Integer.parseInt(externalId), false);
    }

    public Product getProductForExternalId(String externalId, Date orderDate) {
        return ProductRepository.getByProductCode(this.mContext, Integer.parseInt(externalId), false, orderDate);
    }

    @Deprecated
    public Product getProductForExternalId(String externalId, boolean resolveProductDetailsNoDimen) {
        return ProductRepository.getByProductCode(this.mContext, Integer.parseInt(externalId), false);
    }

    public void populateProductWithStoreSpecificData(Product product) {
        ProductRepository.populateProductWithStoreSpecificData(this.mContext, product);
    }

    public boolean productIsInCurrentStore(int externalId) {
        return ProductRepository.productIsInCurrentStore(this.mContext, externalId);
    }

    public List<ProductDimension> getProductDimensions(Product product, int storeId) {
        return ProductRepository.getProductDimensions(this.mContext, product, storeId);
    }

    public List<Ingredient> getProductIngredients(Product product) {
        return ProductRepository.getProductIngredients(this.mContext, product);
    }

    public List<Ingredient> getProductChoices(Product product) {
        return ProductRepository.getProductChoices(this.mContext, product);
    }

    public List<Ingredient> getProductChoices(Product product, boolean getChoiceIngredients) {
        return ProductRepository.getProductChoices(this.mContext, product, getChoiceIngredients);
    }

    public List<Ingredient> getProductExtras(Product product) {
        return ProductRepository.getProductExtras(this.mContext, product);
    }

    public double getProductBasePrice(OrderProduct orderProduct) {
        return orderProduct.getBasePrice(getCurrentOrder().getPriceType());
    }

    @Deprecated
    public double getProductBasePrice(Product product) {
        return product.getBasePrice(getCurrentOrder().getPriceType());
    }

    public void getProductForExternalId(String externalId, @NonNull AsyncListener<Product> listener) {
        getProductForExternalId(externalId, (AsyncListener) listener, false);
    }

    @Deprecated
    public void getProductForExternalId(String externalId, String name, @NonNull AsyncListener<Product> listener) {
        getProductForExternalId(externalId, (AsyncListener) listener);
    }

    public void getFullProductForExternalId(String externalId, @NonNull AsyncListener<Product> listener) {
        getProductForExternalId(externalId, (AsyncListener) listener, true);
    }

    public void getProductsWithExternalIds(List<String> externalIds, @NonNull final AsyncListener<List<Product>> listener) {
        final int[] productCodes = new int[externalIds.size()];
        for (int i = 0; i < externalIds.size(); i++) {
            productCodes[i] = Integer.parseInt((String) externalIds.get(i));
        }
        C260010 c260010 = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            public List<Product> doInBackground(Void... params) {
                return ProductRepository.getByProductCodes(OrderingModule.this.mContext, productCodes);
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(List<Product> products) {
                listener.onResponse(products, null, null);
            }
        };
        Void[] voidArr = new Void[0];
        if (c260010 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c260010, voidArr);
        } else {
            c260010.execute(voidArr);
        }
    }

    public void getAllProducts(@NonNull AsyncListener<List<Product>> listener) {
        listener.onResponse(ProductRepository.getAll(this.mContext), null, null);
    }

    public void getAllProductsForCategory(String categoryId, @NonNull AsyncListener<List<Product>> listener) {
        listener.onResponse(ProductRepository.getByCategory(this.mContext, Integer.parseInt(categoryId)), null, null);
    }

    public void getAllCategories(@NonNull AsyncListener<List<Category>> listener) {
        listener.onResponse(CategoryRepository.getAll(this.mContext), null, null);
    }

    @Deprecated
    public void populateFullProductDetails(Product product, @NonNull AsyncListener<Product> listener) {
        listener.onResponse(null, null, null);
    }

    public void getUpsellItems(final AsyncListener<List<Product>> listener) {
        this.mConnector.getUpsellItems(getCurrentOrder(), new AsyncListener<int[]>() {
            public void onResponse(int[] response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    listener.onResponse(OrderingModule.this.sortProductsByProductCodes(response, ProductRepository.getByProductCodes(OrderingModule.this.mContext, response)), token, exception);
                    return;
                }
                listener.onResponse(new ArrayList(), token, exception);
            }
        });
    }

    private List<Product> sortProductsByProductCodes(int[] codes, List<Product> products) {
        List<Product> orderedProducts = new ArrayList();
        for (int code : codes) {
            for (Product product : products) {
                if (product.getExternalId().intValue() == code) {
                    orderedProducts.add(product);
                    break;
                }
            }
        }
        return orderedProducts;
    }
}
