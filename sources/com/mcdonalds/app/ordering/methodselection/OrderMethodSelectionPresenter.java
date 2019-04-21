package com.mcdonalds.app.ordering.methodselection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class OrderMethodSelectionPresenter {
    private boolean checkfirstimeload;
    private AsyncListener<CustomerAddress> mAddressAsyncListener = new C36042();
    public boolean mAsapDelivery = true;
    private Date mAsapDeliveryDate;
    private String mAsapDeliveryDateString;
    private Context mContext;
    private CustomerModule mCustomerModule;
    private CustomerAddress mDeliveryAddress;
    private Store mDeliveryStore;
    private String mEdtInMin;
    private boolean mIsDelivery;
    private Order mOrder;
    private Store mPickupStore;
    private Date mScheduledDeliveryDate;
    private String mScheduledDeliveryDateString;
    private OrderMethodSelectionView mView;

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter$1 */
    class C36031 implements AsyncListener<Void> {
        C36031() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).setAsapDelivery(OrderMethodSelectionPresenter.this.mAsapDelivery);
            OrderMethodSelectionPresenter.access$100(OrderMethodSelectionPresenter.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter$2 */
    class C36042 implements AsyncListener<CustomerAddress> {
        C36042() {
        }

        public void onResponse(CustomerAddress address, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{address, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).hideActivityIndicator();
            } else if (address != null) {
                OrderMethodSelectionPresenter.this.setAddress(address);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).hideActivityIndicator();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter$3 */
    class C36053 implements AsyncListener<Store> {
        C36053() {
        }

        public void onResponse(Store response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).hideActivityIndicator();
            if (exception == null) {
                OrderMethodSelectionPresenter.access$200(OrderMethodSelectionPresenter.this, response);
            } else {
                AsyncException.report(exception);
            }
        }
    }

    static /* synthetic */ void access$100(OrderMethodSelectionPresenter x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$100", new Object[]{x0});
        x0.checkSaveState();
    }

    static /* synthetic */ void access$200(OrderMethodSelectionPresenter x0, Store x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$200", new Object[]{x0, x1});
        x0.setupDeliveryStore(x1);
    }

    static /* synthetic */ Store access$302(OrderMethodSelectionPresenter x0, Store x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$302", new Object[]{x0, x1});
        x0.mDeliveryStore = x1;
        return x1;
    }

    static /* synthetic */ boolean access$402(OrderMethodSelectionPresenter x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$402", new Object[]{x0, new Boolean(x1)});
        x0.checkfirstimeload = x1;
        return x1;
    }

    public OrderMethodSelectionPresenter(Context context, OrderMethodSelectionView view) {
        this.mContext = context;
        this.mView = view;
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public void init() {
        Ensighten.evaluateEvent(this, "init", null);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        this.mIsDelivery = this.mOrder.isDelivery();
        if (hasDelivery() && this.mIsDelivery) {
            this.mAsapDelivery = this.mOrder.isNormalOrder();
            if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
                this.mEdtInMin = LocalDataManager.getSharedInstance().getEdtString();
            }
            setupDelivery();
            return;
        }
        setupPickup();
    }

    public void initFavorite() {
        Ensighten.evaluateEvent(this, "initFavorite", null);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        this.mIsDelivery = false;
        setupPickup();
    }

    public void setupPickup() {
        Ensighten.evaluateEvent(this, "setupPickup", null);
        this.mPickupStore = this.mCustomerModule.getCurrentStore();
        updateStores();
        this.mView.showPickup();
    }

    public void setupDelivery() {
        Ensighten.evaluateEvent(this, "setupDelivery", null);
        this.mView.showActivityIndicator(C2658R.string.sl_retrieve_stores);
        this.mIsDelivery = true;
        setAsapDeliveryFirst(false);
        if (!(this.mAsapDelivery || this.mOrder.getDeliveryDate() == null)) {
            setScheduledDeliveryDate(this.mOrder.getDeliveryDate());
            this.checkfirstimeload = false;
        }
        if (this.mDeliveryAddress == null) {
            this.mDeliveryAddress = this.mOrder.getDeliveryAddress();
            this.mDeliveryStore = this.mOrder.getDeliveryStore();
        }
        if (this.mDeliveryAddress != null) {
            this.mView.updateSelectedAddress(this.mDeliveryAddress.getFullAddress());
            if (this.mDeliveryStore == null || this.mDeliveryStore.getExpectedDeliveryTimeReference() == null) {
                getStoreForDelivery();
            } else {
                setupStoreForDelivery();
            }
        } else if (isLoggedIn()) {
            updateSelectedAddress();
        }
        checkSaveState();
        this.mView.hideActivityIndicator();
        this.mView.showDelivery();
    }

    public void setAddress(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "setAddress", new Object[]{address});
        setAddress(address, null);
    }

    public void setAddress(CustomerAddress address, AsyncListener<Void> listener) {
        Ensighten.evaluateEvent(this, "setAddress", new Object[]{address, listener});
        if (address != this.mDeliveryAddress) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
                this.mEdtInMin = null;
            }
            this.mDeliveryAddress = address;
            this.mView.updateSelectedAddress(this.mDeliveryAddress.getFullAddress());
        }
        getStoreForDelivery(listener);
    }

    public void setScheduledDeliveryDate(Date date) {
        Ensighten.evaluateEvent(this, "setScheduledDeliveryDate", new Object[]{date});
        this.mScheduledDeliveryDate = date;
        this.mScheduledDeliveryDateString = UIUtils.formatDeliveryTime(this.mContext, new Date(), this.mScheduledDeliveryDate, true);
        this.mView.updateScheduledDeliveryDate(this.mScheduledDeliveryDateString);
        checkSaveState();
    }

    private void setAsapDeliveryDate(Store store) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryDate", new Object[]{store});
        Date nowInStoreTime = UIUtils.getDateFromISO8601(store.getNowInStoreLocalTime(), TimeZone.getDefault());
        this.mAsapDeliveryDate = UIUtils.getDateFromISO8601(store.getExpectedDeliveryTime());
        this.mAsapDeliveryDateString = UIUtils.formatDeliveryTime(this.mContext, nowInStoreTime, this.mAsapDeliveryDate);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
            if (this.mEdtInMin == null) {
                this.mEdtInMin = UIUtils.formatTimeInMinutes(this.mContext, nowInStoreTime, this.mAsapDeliveryDate, true);
            }
            this.mView.updateAsapDeliveryDate(this.mEdtInMin);
        } else {
            this.mView.updateAsapDeliveryDate(this.mAsapDeliveryDateString);
        }
        checkSaveState();
    }

    public void setAsapDelivery(boolean asapDelivery) {
        Ensighten.evaluateEvent(this, "setAsapDelivery", new Object[]{new Boolean(asapDelivery)});
        this.mAsapDelivery = asapDelivery;
        this.mView.setAsapDelivery(this.mAsapDelivery);
        checkSaveState();
    }

    public void setAsapDeliveryFirst(boolean asapDelivery) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryFirst", new Object[]{new Boolean(asapDelivery)});
        if (this.mScheduledDeliveryDateString == null || this.mScheduledDeliveryDateString.equals(Integer.valueOf(C2658R.string.schedule_delivery))) {
            this.checkfirstimeload = true;
        }
        this.mAsapDelivery = asapDelivery;
        this.mView.setAsapDeliveryFirst(false);
        checkSaveState();
    }

    public void setAsapDeliveryAndUpdateStore(boolean asapDelivery) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryAndUpdateStore", new Object[]{new Boolean(asapDelivery)});
        this.mAsapDelivery = asapDelivery;
        if (true) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
                this.mEdtInMin = null;
            }
            getStoreForDelivery(new C36031());
        }
    }

    private void updateStores() {
        Ensighten.evaluateEvent(this, "updateStores", null);
        this.mIsDelivery = false;
        List<Store> stores = new ArrayList();
        for (Store store : this.mCustomerModule.getFavoriteStores()) {
            if (!(this.mPickupStore == null || this.mPickupStore.getStoreId() == store.getStoreId() || !store.hasMobileOrdering().booleanValue())) {
                stores.add(store);
            }
        }
        if (this.mPickupStore != null && this.mPickupStore.hasMobileOrdering().booleanValue()) {
            stores.add(0, this.mPickupStore);
        }
        this.mView.updateStores(stores);
    }

    public void setPickupStore(Store pickupStore) {
        Ensighten.evaluateEvent(this, "setPickupStore", new Object[]{pickupStore});
        this.mPickupStore = pickupStore;
        this.mView.updateSelectedStore(this.mPickupStore);
    }

    public boolean isDelivery() {
        Ensighten.evaluateEvent(this, "isDelivery", null);
        return this.mIsDelivery;
    }

    private void updateSelectedAddress() {
        Ensighten.evaluateEvent(this, "updateSelectedAddress", null);
        this.mView.showActivityIndicator(C2658R.string.sl_retrieve_stores);
        this.mCustomerModule.getDefaultAddress(this.mAddressAsyncListener);
    }

    private void setupStoreForDelivery() {
        Date date;
        Ensighten.evaluateEvent(this, "setupStoreForDelivery", null);
        List<String> storeIds = new ArrayList();
        storeIds.add(Integer.toString(this.mDeliveryStore.getStoreId()));
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mView.showActivityIndicator(C2658R.string.sl_retrieve_stores);
        if (this.mAsapDelivery) {
            date = this.mAsapDeliveryDate;
        } else {
            date = this.mScheduledDeliveryDate;
        }
        orderingModule.getStoreFromList(storeIds, date, this.mAsapDelivery, new C36053());
    }

    private void getStoreForDelivery() {
        Ensighten.evaluateEvent(this, "getStoreForDelivery", null);
        getStoreForDelivery(null);
    }

    public void getStoreForDelivery(final AsyncListener<Void> listener) {
        Ensighten.evaluateEvent(this, "getStoreForDelivery", new Object[]{listener});
        this.mView.hideActivityIndicator();
        if (this.mDeliveryAddress != null) {
            this.mView.showActivityIndicator(C2658R.string.sl_retrieve_stores);
            ((DeliveryModule) ModuleManager.getModule(DeliveryModule.NAME)).getDeliveryStore(this.mDeliveryAddress, this.mAsapDelivery ? this.mAsapDeliveryDate : this.mScheduledDeliveryDate, this.mCustomerModule.getCurrentProfile(), this.mAsapDelivery, new AsyncListener<Store>() {

                /* renamed from: com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter$4$1 */
                class C36061 implements OnClickListener {
                    C36061() {
                    }

                    public void onClick(DialogInterface dialog, int which) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                        dialog.dismiss();
                    }
                }

                public void onResponse(Store response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (exception == null) {
                        OrderMethodSelectionPresenter.access$302(OrderMethodSelectionPresenter.this, response);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$300", new Object[]{OrderMethodSelectionPresenter.this}).setExpectedDeliveryTimeReference(new Date());
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$300", new Object[]{OrderMethodSelectionPresenter.this}).isStoreOpen()) {
                            OrderMethodSelectionPresenter.access$200(OrderMethodSelectionPresenter.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$300", new Object[]{OrderMethodSelectionPresenter.this}));
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).hideActivityIndicator();
                            if (listener != null) {
                                listener.onResponse(null, null, null);
                                return;
                            }
                            return;
                        }
                        if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$400", new Object[]{OrderMethodSelectionPresenter.this})) {
                            MCDAlertDialogBuilder.withContext(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$500", new Object[]{OrderMethodSelectionPresenter.this})).setTitle((int) C2658R.string.error_title).setMessage((int) C2658R.string.delivery_is_currently_not_available_to_this_address).setPositiveButton((int) C2658R.string.f6083ok, new C36061()).setCancelable(false).create().show();
                        }
                        OrderMethodSelectionPresenter.access$402(OrderMethodSelectionPresenter.this, false);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).hideActivityIndicator();
                        return;
                    }
                    AsyncException.report(exception);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionPresenter", "access$000", new Object[]{OrderMethodSelectionPresenter.this}).hideActivityIndicator();
                    if (listener != null) {
                        listener.onResponse(null, null, exception);
                    }
                }
            });
        } else if (listener != null) {
            listener.onResponse(null, null, null);
        }
    }

    private void setupDeliveryStore(Store store) {
        Ensighten.evaluateEvent(this, "setupDeliveryStore", new Object[]{store});
        setAsapDeliveryDate(store);
        if (this.mAsapDeliveryDate != null) {
            this.mView.showDeliveryTimeSelector();
        }
        checkSaveState();
    }

    public long getMinimumDeliveryDateInMillis() {
        Ensighten.evaluateEvent(this, "getMinimumDeliveryDateInMillis", null);
        if (this.mDeliveryStore == null) {
            return 0;
        }
        return getDifferenceInMillis(this.mDeliveryStore.getMinAdvanceOrderTime());
    }

    public long getMaximumDeliveryDateInMillis() {
        Ensighten.evaluateEvent(this, "getMaximumDeliveryDateInMillis", null);
        if (this.mDeliveryStore == null) {
            return 0;
        }
        return getDifferenceInMillis(this.mDeliveryStore.getMaxAdvanceOrderTime());
    }

    private long getDifferenceInMillis(int minutes) {
        Ensighten.evaluateEvent(this, "getDifferenceInMillis", new Object[]{new Integer(minutes)});
        return TimeUnit.MINUTES.toMillis((long) minutes) + System.currentTimeMillis();
    }

    private void checkSaveState() {
        boolean canSave = true;
        Ensighten.evaluateEvent(this, "checkSaveState", null);
        if (this.mIsDelivery) {
            if (this.mDeliveryAddress == null || this.mDeliveryStore == null || (((!this.mAsapDelivery || this.mAsapDeliveryDate == null) && (this.mAsapDelivery || this.mScheduledDeliveryDate == null)) || this.mAsapDeliveryDateString == null)) {
                canSave = false;
            }
        } else if (this.mPickupStore == null) {
            canSave = false;
        }
        this.mView.setSaveButtonState(canSave);
    }

    public void save() {
        Ensighten.evaluateEvent(this, "save", null);
        if (this.mIsDelivery) {
            LocalDataManager.getSharedInstance().setFirstTimeDelivery(false);
            this.mOrder.setIsDelivery(true);
            this.mOrder.setDeliveryAddress(this.mDeliveryAddress);
            this.mOrder.setDeliveryStore(this.mDeliveryStore);
            this.mOrder.setStoreId(this.mDeliveryStore.getStoreId());
            if (this.mAsapDelivery) {
                this.mOrder.setDeliveryDate(this.mAsapDeliveryDate);
                this.mOrder.setDeliveryDateString(this.mAsapDeliveryDateString);
            } else {
                this.mOrder.setDeliveryDate(this.mScheduledDeliveryDate);
                this.mOrder.setDeliveryDateString(this.mScheduledDeliveryDateString);
            }
            this.mOrder.setNormalOrder(this.mAsapDelivery);
            this.mOrder.setPriceType(PriceType.Delivery);
            this.mOrder.setStoreId(this.mDeliveryStore.getStoreId());
            if (!(Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes") && this.mEdtInMin == null)) {
            }
        } else {
            this.mOrder.setNormalOrder(false);
            this.mOrder.setIsDelivery(false);
            this.mOrder.setPriceType(PriceType.EatIn);
            this.mOrder.setStoreId(this.mPickupStore.getStoreId());
            this.mCustomerModule.setCurrentStore(this.mPickupStore);
            DataLayerManager.getInstance().setStore(this.mPickupStore);
        }
        if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.newPromoWorkflow")) {
            this.mOrder.setIsPODSet(true);
        }
        LocalDataManager.getSharedInstance().setFirstTimeOrdering(false);
        OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        this.mView.save();
    }

    public Store selectedStore() {
        Ensighten.evaluateEvent(this, "selectedStore", null);
        if (this.mIsDelivery) {
            return this.mDeliveryStore;
        }
        return this.mPickupStore;
    }

    public boolean hasDelivery() {
        Ensighten.evaluateEvent(this, "hasDelivery", null);
        return ModuleManager.isModuleEnabled(DeliveryModule.NAME).booleanValue();
    }

    public boolean isLoggedIn() {
        Ensighten.evaluateEvent(this, "isLoggedIn", null);
        return this.mCustomerModule.isLoggedIn();
    }

    public boolean isExternalDelivery() {
        Ensighten.evaluateEvent(this, "isExternalDelivery", null);
        return Configuration.getSharedInstance().getBooleanForKey("modules.delivery.sendToDeliveryWebsite");
    }

    public boolean isCurrentStore(Store store) {
        Ensighten.evaluateEvent(this, "isCurrentStore", new Object[]{store});
        return (this.mPickupStore == null || store == null || this.mPickupStore.getStoreId() != store.getStoreId()) ? false : true;
    }

    public boolean hasCurrentStoreMobileOrdering() {
        Ensighten.evaluateEvent(this, "hasCurrentStoreMobileOrdering", null);
        return this.mPickupStore != null && this.mPickupStore.hasMobileOrdering().booleanValue();
    }
}
