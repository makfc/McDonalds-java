package com.mcdonalds.sdk;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.RonaldService;
import com.mcdonalds.sdk.services.RonaldServiceConnection;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.database.DatabaseHelper;
import com.mcdonalds.sdk.services.data.provider.Contract;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.SessionManager;

public class McDonalds {
    private static RonaldServiceConnection mServiceConnection;
    static Context sContext;

    private McDonalds() {
    }

    public static void initialize(@NonNull Context context) {
        initialize(context, null, null);
    }

    public static void initialize(@NonNull Context context, @Nullable Intent reloadIntent, String configJson) {
        sContext = context.getApplicationContext();
        if (configJson != null) {
            Configuration.getSharedInstance().loadConfigurationWithJsonString(configJson);
        }
        if (reloadIntent != null) {
            initializeService(reloadIntent);
        }
    }

    public static boolean isInitialized() {
        return sContext != null;
    }

    public static RonaldServiceConnection getService() {
        return mServiceConnection;
    }

    public static void initializeService(Intent reloadIntent) {
        if (mServiceConnection == null) {
            Intent serviceIntent = new Intent(getContext(), RonaldService.class);
            mServiceConnection = RonaldService.getServiceConnection(getContext(), reloadIntent);
            getContext().bindService(serviceIntent, mServiceConnection, 1);
        }
    }

    public static Context getContext() {
        if (isInitialized()) {
            return sContext;
        }
        throw new IllegalStateException("McDonalds SDK is not initialized. Please call McDonalds.initialize(Context context) to initialize this SDK.");
    }

    public static void clean(Context context, Intent reloadIntent, ConnectorManager connectorManager, ModuleManager moduleManager) {
        context.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        context.getContentResolver().update(Contract.CONTENT_URI, null, null, null);
        context.getContentResolver().notifyChange(Contract.CONTENT_URI, null, true);
        RequestManager.getInstance(context).clearRequestQueue();
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        String prefSavedLogin = LocalDataManager.getSharedInstance().getPrefSavedLogin();
        String prefSavedLoginPass = LocalDataManager.getSharedInstance().getPrefSavedLoginPass();
        int prefSavedSocialID = LocalDataManager.getSharedInstance().getPrefSavedSocialNetworkId();
        String tutorialLastShownVersion = LocalDataManager.getSharedInstance().getTutorialLastShownVersionName();
        Store store = customerModule.getCurrentStore();
        customerModule.setCurrentProfile(null);
        customerModule.clearCurrentStore();
        customerModule.removeSyncAccount();
        SessionManager.getInstance().clearToken();
        connectorManager.clearConnectors();
        moduleManager.clearModules();
        LocalDataManager.getSharedInstance().clearCache();
        LocalDataManager.getSharedInstance().clear();
        CatalogManager.clearMarketCache(context);
        CatalogManager.clearStoreCache(context);
        customerModule.setCurrentStore(store);
        LocalDataManager.getSharedInstance().setPrefSavedLogin(prefSavedLogin);
        LocalDataManager.getSharedInstance().setPrefSavedLoginPass(prefSavedLoginPass);
        LocalDataManager.getSharedInstance().setPrefSavedSocialNetworkId(prefSavedSocialID);
        LocalDataManager.getSharedInstance().setTutorialLastShownVersionName(tutorialLastShownVersion);
        CatalogManager.setFirstCatalogLoad(context, true);
        if (reloadIntent != null) {
            reloadIntent.setFlags(268468224);
            context.startActivity(reloadIntent);
        }
    }
}
