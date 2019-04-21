package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoresManager {
    private static StoresManager sInstance;
    private Context mContext;
    private SparseArray<StoreFavoriteInfo> mFavoritesData = new SparseArray();
    private final AsyncListener<List<StoreFavoriteInfo>> mFavoritesDataListener = new C24561();
    private final Comparator<Store> mSortByDistance = new C24572();
    private StoreLocatorController mStoreLocatorController;

    /* renamed from: com.mcdonalds.app.storelocator.StoresManager$1 */
    class C24561 implements AsyncListener<List<StoreFavoriteInfo>> {
        C24561() {
        }

        public void onResponse(List<StoreFavoriteInfo> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null && response != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoresManager", "access$000", new Object[]{StoresManager.this}).clear();
                for (StoreFavoriteInfo info : response) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoresManager", "access$000", new Object[]{StoresManager.this}).put(info.getStoreId(), info);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoresManager$2 */
    class C24572 implements Comparator<Store> {
        C24572() {
        }

        public int compare(Store store, Store store2) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{store, store2});
            Double d1 = Double.valueOf(store.getDistance());
            Double d2 = Double.valueOf(store2.getDistance());
            if (d1.equals(d2)) {
                return 0;
            }
            if (d1.doubleValue() < d2.doubleValue()) {
                return -1;
            }
            return 1;
        }
    }

    private StoresManager() {
    }

    public void refreshFavorites() {
        Ensighten.evaluateEvent(this, "refreshFavorites", null);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule != null && customerModule.getCurrentProfile() != null && customerModule.isLoggedIn()) {
            ServiceUtils.getSharedInstance().retrieveFavoriteStores(customerModule.getCurrentProfile(), this.mFavoritesDataListener);
        }
    }

    public void setController(StoreLocatorController storeLocatorController) {
        Ensighten.evaluateEvent(this, "setController", new Object[]{storeLocatorController});
        this.mStoreLocatorController = storeLocatorController;
    }

    public StoreLocatorController getController() {
        Ensighten.evaluateEvent(this, "getController", null);
        return this.mStoreLocatorController;
    }

    public void updateFavoriteInfo(List<Store> stores) {
        Ensighten.evaluateEvent(this, "updateFavoriteInfo", new Object[]{stores});
        for (Store store : stores) {
            StoreFavoriteInfo info = (StoreFavoriteInfo) this.mFavoritesData.get(store.getStoreId());
            if (info != null) {
                store.setStoreFavoriteId(Integer.valueOf(info.getFavoriteId()));
                store.setStoreFavoriteName(info.getFavoriteNickName());
            }
        }
    }

    public void sortByDistance(List<Store> stores) {
        Ensighten.evaluateEvent(this, "sortByDistance", new Object[]{stores});
        Collections.sort(stores, this.mSortByDistance);
    }

    public List<String> getAvailableNickNames() {
        Ensighten.evaluateEvent(this, "getAvailableNickNames", null);
        CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        List<String> presets = (List) Configuration.getSharedInstance().getValueForKey("interface.customer.presetStoreNames");
        List<String> nicknames = new ArrayList();
        if (presets != null) {
            for (String preset : presets) {
                nicknames.add(UIUtils.getStringByName(this.mContext, preset));
            }
        }
        List<String> availableNames = new ArrayList(nicknames);
        if (module.getFavoriteStores() != null) {
            for (Store store : module.getFavoriteStores()) {
                availableNames.remove(store.getStoreFavoriteName());
            }
        }
        return availableNames;
    }

    public static StoresManager getInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoresManager", "getInstance", null);
        if (sInstance == null) {
            sInstance = new StoresManager();
        }
        return sInstance;
    }

    public void setContext(Context context) {
        Ensighten.evaluateEvent(this, "setContext", new Object[]{context});
        this.mContext = context;
    }
}
