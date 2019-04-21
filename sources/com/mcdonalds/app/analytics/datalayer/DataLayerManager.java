package com.mcdonalds.app.analytics.datalayer;

import android.app.Application;
import android.location.Location;
import android.util.Log;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcdonalds.app.analytics.datalayer.analytics.DataLayerAnalytics;
import com.mcdonalds.app.analytics.datalayer.analytics.DataLayerAnalyticsKochava;
import com.mcdonalds.app.analytics.datalayer.analytics.DataLayerAnalyticsNoOp;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.datalayer.DataLayer;
import com.mcdonalds.sdk.services.analytics.datalayer.DataLayer.Listener;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataLayerManager {
    private static DataLayerManager instance;
    private DataLayerAnalytics analytics;
    private String currentPageSection;
    private DataLayerWrapper dataLayerWrapper;
    private boolean enabled;

    public static DataLayerManager getInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.DataLayerManager", "getInstance", null);
        if (instance == null) {
            Log.w("DLA", "DataLayerManager is not initialized. Defaulting to off.");
            instance = new DataLayerManager(null, new DataLayerAnalyticsNoOp(), false);
        }
        return instance;
    }

    private DataLayerManager(DataLayerWrapper dataLayerWrapper, DataLayerAnalytics analytics, boolean enabled) {
        this.dataLayerWrapper = dataLayerWrapper;
        this.analytics = analytics;
        this.enabled = enabled;
    }

    public static void init(Application app, Configuration config) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.DataLayerManager", "init", new Object[]{app, config});
        if (isEnabled(config)) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            Map<String, Object> trackedEvents = new LinkedHashMap();
            try {
                Reader inputStreamReader = new InputStreamReader(app.getAssets().open(getJsonFileName(config) + ".json"));
                Class cls = Map.class;
                trackedEvents = (Map) (!(gson instanceof Gson) ? gson.fromJson(inputStreamReader, cls) : GsonInstrumentation.fromJson(gson, inputStreamReader, cls));
            } catch (IOException e) {
                Log.w("DLA", "Unable to read in events file.");
            }
            instance = new DataLayerManager(new DataLayerWrapper(new DataLayer(trackedEvents)), new DataLayerAnalyticsKochava(app, config), isEnabled(config));
            instance.setSite(config);
            app.registerActivityLifecycleCallbacks(new DataLayerLifecycleManager());
            return;
        }
        instance = new DataLayerManager(null, new DataLayerAnalyticsNoOp(), false);
    }

    private static String getJsonFileName(Configuration config) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.DataLayerManager", "getJsonFileName", new Object[]{config});
        if (config.hasKey("analytics.DataLayer.trackedEvents")) {
            return (String) config.getValueForKey("analytics.DataLayer.trackedEvents");
        }
        return "DLAEvents";
    }

    public static boolean isEnabled(Configuration config) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.DataLayerManager", "isEnabled", new Object[]{config});
        return config.getBooleanForKey("analytics.DataLayer.enableDataLayerAnalytics");
    }

    public <T> void setState(String key, T value) {
        Ensighten.evaluateEvent(this, "setState", new Object[]{key, value});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setState(key, value);
        }
    }

    public void setSite(Configuration config) {
        Ensighten.evaluateEvent(this, "setSite", new Object[]{config});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setSite(config);
        }
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setStore(store);
        }
    }

    public void setUser(CustomerProfile customerProfile, String authStatus, MenuType menuType) {
        Ensighten.evaluateEvent(this, "setUser", new Object[]{customerProfile, authStatus, menuType});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setUser(customerProfile, authStatus, menuType);
        }
    }

    public void setRecipe(NutritionRecipe recipe) {
        Ensighten.evaluateEvent(this, "setRecipe", new Object[]{recipe});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setRecipe(recipe);
        }
    }

    public void setProduct(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "setProduct", new Object[]{orderProduct});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setProduct(orderProduct);
        }
    }

    public void setOffer(Offer offer, String qrCode) {
        Ensighten.evaluateEvent(this, "setOffer", new Object[]{offer, qrCode});
        setOffers(Collections.singletonList(offer), Collections.singletonList(qrCode));
    }

    public void setOffers(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "setOffers", new Object[]{offers});
        setOffers(offers, Collections.emptyList());
    }

    public void setSearchTerm(String searchTerm, List<String> filters, String searchType, int numResults) {
        Ensighten.evaluateEvent(this, "setSearchTerm", new Object[]{searchTerm, filters, searchType, new Integer(numResults)});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setSearchTerm(searchTerm, filters, searchType, numResults);
        }
    }

    public void setStoreFilterToggled(boolean storeFilterToggled) {
        Ensighten.evaluateEvent(this, "setStoreFilterToggled", new Object[]{new Boolean(storeFilterToggled)});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setStoreFilterToggled(storeFilterToggled);
        }
    }

    public void setTransaction(Order order, MenuType menuType, Store store) {
        Ensighten.evaluateEvent(this, "setTransaction", new Object[]{order, menuType, store});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setTransaction(order, menuType, store);
        }
    }

    public void setLocation(Location location) {
        Ensighten.evaluateEvent(this, "setLocation", new Object[]{location});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setLocation(location);
        }
    }

    public void setFormName(String formName) {
        Ensighten.evaluateEvent(this, "setFormName", new Object[]{formName});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setFormName(formName);
        }
    }

    public void setPageSection(String section) {
        Ensighten.evaluateEvent(this, "setPageSection", new Object[]{section});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setPageSection(section);
        }
    }

    public void setCurrentPageSection(String currentPageSection) {
        Ensighten.evaluateEvent(this, "setCurrentPageSection", new Object[]{currentPageSection});
        this.currentPageSection = currentPageSection;
    }

    public void reportCurrentPageSection() {
        Ensighten.evaluateEvent(this, "reportCurrentPageSection", null);
        setPageSection(this.currentPageSection);
    }

    public void logLifecycleEvent(String event) {
        Ensighten.evaluateEvent(this, "logLifecycleEvent", new Object[]{event});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.logLifecycleEvent(event);
        }
    }

    public void logPageLoad(String pageName, String eventName) {
        Ensighten.evaluateEvent(this, "logPageLoad", new Object[]{pageName, eventName});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.logPageLoad(pageName, eventName);
        }
    }

    public void logButtonClick(String tag) {
        Ensighten.evaluateEvent(this, "logButtonClick", new Object[]{tag});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.logButtonClick(tag);
        }
    }

    public void logItemClick(String tag, int positionTag) {
        Ensighten.evaluateEvent(this, "logItemClick", new Object[]{tag, new Integer(positionTag)});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.logListItemClick(tag, positionTag);
        }
    }

    public void recordError(AsyncException exception) {
        Ensighten.evaluateEvent(this, "recordError", new Object[]{exception});
        if (exception != null) {
            recordError(exception.getShortDescription());
        }
    }

    public void recordError(String errorMessage) {
        Ensighten.evaluateEvent(this, "recordError", new Object[]{errorMessage});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.recordError(errorMessage);
        }
    }

    public void setListener(Listener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setListener(listener);
        }
    }

    public DataLayerAnalytics getAnalytics() {
        Ensighten.evaluateEvent(this, "getAnalytics", null);
        return this.analytics;
    }

    public static void reportWarning(String message) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.DataLayerManager", "reportWarning", new Object[]{message});
        Log.w("DLA", message);
    }

    private void setOffers(List<Offer> offers, List<String> qrCodes) {
        Ensighten.evaluateEvent(this, "setOffers", new Object[]{offers, qrCodes});
        if (this.enabled && this.dataLayerWrapper != null) {
            this.dataLayerWrapper.setOffers(offers, qrCodes);
        }
    }
}
