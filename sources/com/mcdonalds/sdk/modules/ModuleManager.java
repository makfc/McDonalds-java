package com.mcdonalds.sdk.modules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.paymentRegistration.PaymentRegistrationModule;
import com.mcdonalds.sdk.modules.security.SecurityModule;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.listeners.OnSaveCompletedListener;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ModuleManager {
    public static final String MCD_MODULES_RELOADED_NOTIFICATION = "com.mcdonalds.notification.MCD_MODULES_RELOADED_NOTIFICATION";
    private static final String MODULE_CONFIGS_PATH = "modules";
    public static final String ORDER_CHANGED_NOTIFICATION = "com.mcdonalds.notification.ORDER_CHANGED_NOTIFICATION";
    private static ModuleManager mSharedInstance;
    private Context mContext;
    private HashMap<String, BaseModule> mModuleMap;

    /* renamed from: com.mcdonalds.sdk.modules.ModuleManager$1 */
    class C25061 extends BroadcastReceiver {
        C25061() {
        }

        public void onReceive(Context context, Intent intent) {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            if (customerModule != null && !customerModule.isLoggedIn() && ModuleManager.this.getCurrentOrder() != null) {
                ModuleManager.this.getCurrentOrder().clearOffers();
            }
        }
    }

    /* renamed from: com.mcdonalds.sdk.modules.ModuleManager$2 */
    static class C25072 extends TypeToken<MockLocation> {
        C25072() {
        }
    }

    private static class MockLocation implements Serializable {
        public static final String CACHE_KEY = MockLocation.class.getSimpleName().toLowerCase();
        private Double mLatitude;
        private Double mLongitude;

        private MockLocation() {
        }

        /* synthetic */ MockLocation(C25061 x0) {
            this();
        }

        public Double getLatitude() {
            return this.mLatitude;
        }

        public void setLatitude(Double latitude) {
            this.mLatitude = latitude;
        }

        public Double getLongitude() {
            return this.mLongitude;
        }

        public void setLongitude(Double longitude) {
            this.mLongitude = longitude;
        }
    }

    private ModuleManager() {
    }

    public ModuleManager initialize(Context context) {
        this.mContext = context;
        this.mModuleMap = new HashMap();
        NotificationCenter.getSharedInstance().addObserver(CustomerModule.MCD_LOGIN_STATE_CHANGED, new C25061());
        return this;
    }

    public static ModuleManager getSharedInstance() {
        if (mSharedInstance == null) {
            mSharedInstance = new ModuleManager().initialize(McDonalds.getContext());
        }
        return mSharedInstance;
    }

    public static void setSharedInstance(ModuleManager moduleManager) {
        mSharedInstance = moduleManager;
    }

    public static Boolean isModuleEnabled(String implementation) {
        return Boolean.valueOf(Configuration.getSharedInstance().hasKey("modules." + implementation));
    }

    public static <T> T getModule(String implementation) {
        return getSharedInstance().getModuleImpl(implementation);
    }

    public <T> T getModuleImpl(String implementation) {
        BaseModule module = (BaseModule) this.mModuleMap.get(implementation);
        if (module != null) {
            return module;
        }
        module = getModuleInstanceForName(implementation);
        this.mModuleMap.put(implementation, module);
        return module;
    }

    private BaseModule getModuleInstanceForName(String moduleName) {
        if (!isModuleEnabled(moduleName).booleanValue()) {
            return null;
        }
        Object obj = -1;
        switch (moduleName.hashCode()) {
            case -1728722842:
                if (moduleName.equals(NutritionModule.NAME)) {
                    obj = 2;
                    break;
                }
                break;
            case -1563253546:
                if (moduleName.equals(ConfigurationModule.NAME)) {
                    obj = 8;
                    break;
                }
                break;
            case -1019793001:
                if (moduleName.equals(OffersModule.NAME)) {
                    obj = 3;
                    break;
                }
                break;
            case -289237537:
                if (moduleName.equals(PaymentRegistrationModule.NAME)) {
                    obj = 7;
                    break;
                }
                break;
            case 595233003:
                if (moduleName.equals("notification")) {
                    obj = 6;
                    break;
                }
                break;
            case 606175198:
                if (moduleName.equals(CustomerModule.NAME)) {
                    obj = null;
                    break;
                }
                break;
            case 823466996:
                if (moduleName.equals(DeliveryModule.NAME)) {
                    obj = 1;
                    break;
                }
                break;
            case 949122880:
                if (moduleName.equals(SecurityModule.NAME)) {
                    obj = 9;
                    break;
                }
                break;
            case 1234314708:
                if (moduleName.equals("ordering")) {
                    obj = 4;
                    break;
                }
                break;
            case 1375399029:
                if (moduleName.equals(StoreLocatorModule.NAME)) {
                    obj = 5;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new CustomerModule(this.mContext);
            case 1:
                return new DeliveryModule();
            case 2:
                return new NutritionModule();
            case 3:
                return new OffersModule();
            case 4:
                return new OrderingModule(this.mContext);
            case 5:
                return new StoreLocatorModule(this.mContext);
            case 6:
                return new NotificationModule(this.mContext);
            case 7:
                return new PaymentRegistrationModule();
            case 8:
                return new ConfigurationModule();
            case 9:
                return new SecurityModule(this.mContext);
            default:
                Log.e("ModuleManager", "No Valid Module found for type: " + moduleName);
                return null;
        }
    }

    public void clearModules() {
        this.mModuleMap = new HashMap();
    }

    public static void setMockLocation(Double latitude, Double longitude) {
        setMockLocation(latitude, longitude, null);
    }

    public static void setMockLocation(Double latitude, Double longitude, OnSaveCompletedListener listener) {
        LocalDataManager manager = LocalDataManager.getSharedInstance();
        if (latitude == null || longitude == null) {
            manager.deleteObjectFromCache(MockLocation.CACHE_KEY);
            return;
        }
        MockLocation mockLocation = new MockLocation();
        mockLocation.setLatitude(latitude);
        mockLocation.setLongitude(longitude);
        manager.addObjectToCache(MockLocation.CACHE_KEY, mockLocation, TimeUnit.DAYS.toSeconds(18250), false, listener);
    }

    public static Location getMockLocation() {
        MockLocation mockLocation = (MockLocation) LocalDataManager.getSharedInstance().getObjectFromCache(MockLocation.CACHE_KEY, new C25072().getType());
        if (mockLocation == null) {
            return null;
        }
        Location ret = new Location("Mock Location Selection Fragment");
        ret.setLatitude(mockLocation.getLatitude().doubleValue());
        ret.setLongitude(mockLocation.getLongitude().doubleValue());
        return ret;
    }

    public boolean isOrderingAvailable() {
        return (getModule("ordering") == null && getModule(DeliveryModule.NAME) == null) ? false : true;
    }

    public boolean isNutritionAvailable() {
        return getModule(NutritionModule.NAME) != null;
    }

    @Deprecated
    public Order getCurrentOrder() {
        return OrderManager.getInstance().getCurrentOrder();
    }

    @Deprecated
    public void deleteCurrentOrder() {
        OrderManager.getInstance().deleteCurrentOrder();
    }

    @Deprecated
    public int getMaxBasketQuantity() {
        return OrderManager.getInstance().getMaxBasketQuantity();
    }

    @Deprecated
    public void updateCurrentOrder(Order newOrder) {
        OrderManager.getInstance().updateCurrentOrder(newOrder);
    }
}
