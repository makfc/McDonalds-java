package com.mcdonalds.app.analytics.datalayer;

import android.location.Location;
import com.ensighten.Ensighten;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPolicy;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.datalayer.DataLayer;
import com.mcdonalds.sdk.services.analytics.datalayer.DataLayer.Listener;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.MapUtils;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataLayerWrapper {
    private static final List<String> EXCLUDED_PAGES = Arrays.asList(new String[]{"Terms", "FirstLoadTutorial", "Privacy", "FAQ", "Career", "ContactUs", "RMHC", "NutritionInfo", "EULA"});
    private static final List<String> TOP_LEVEL_PAGES = Arrays.asList(new String[]{"Dashboard", "RestaurantLocator", "Nutrition", "ProductMenu", "Registration", "AboutTheApp", "LogIn", "AccountSettings"});
    private DataLayer dataLayer;
    private boolean storeFilterToggled = false;
    private List<String> storeFilters = new ArrayList();

    public DataLayerWrapper(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    public <T> void setState(String key, T value) {
        Ensighten.evaluateEvent(this, "setState", new Object[]{key, value});
        this.dataLayer.setState(key, value);
    }

    public void setSite(Configuration config) {
        Ensighten.evaluateEvent(this, "setSite", new Object[]{config});
        Object countryCode = (String) config.getValueForKey("analytics.DataLayer.country");
        Map siteMap = new LinkedHashMap();
        MapUtils.put(siteMap, "site.platform", (Object) "Android");
        MapUtils.put(siteMap, "site.marketId", countryCode);
        MapUtils.put(siteMap, "site.property", String.format("%s %s", new Object[]{countryCode, DCSPolicy.DEFAULT_SOURCE_ID}));
        MapUtils.put(siteMap, "site.appId", String.format("%s %s", new Object[]{DCSPolicy.DEFAULT_SOURCE_ID, "4.8.10"}));
        this.dataLayer.setState("", siteMap);
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        Map storeMap = new LinkedHashMap();
        if (store != null) {
            MapUtils.put(storeMap, "restaurant.ID", String.valueOf(store.getStoreId()));
            MapUtils.put(storeMap, "restaurant.city", store.getCity());
            MapUtils.put(storeMap, "restaurant.state", store.getState());
            MapUtils.put(storeMap, "restaurant.postalCode", store.getPostalCode());
        }
        this.dataLayer.setState("", storeMap);
    }

    public void setUser(CustomerProfile customerProfile, String authStatus, MenuType menuType) {
        Ensighten.evaluateEvent(this, "setUser", new Object[]{customerProfile, authStatus, menuType});
        Map customerProfileMap = new LinkedHashMap();
        if (customerProfile != null) {
            MapUtils.put(customerProfileMap, "user.emailAddressEncrypted", sha256(customerProfile.getEmailAddress()));
            MapUtils.put(customerProfileMap, "user.ecpId", String.valueOf(customerProfile.getCustomerId()));
            MapUtils.put(customerProfileMap, "user.postalCode", customerProfile.getZipCode());
        } else {
            MapUtils.put(customerProfileMap, "user.emailAddressEncrypted", null);
            MapUtils.put(customerProfileMap, "user.ecpId", null);
            MapUtils.put(customerProfileMap, "user.postalCode", null);
        }
        MapUtils.put(customerProfileMap, "user.authStatus", (Object) authStatus);
        if (menuType != null) {
            MapUtils.put(customerProfileMap, "user.daypart", menuType.getShortName());
        }
        this.dataLayer.setState("", customerProfileMap);
    }

    public void setRecipe(NutritionRecipe recipe) {
        Ensighten.evaluateEvent(this, "setRecipe", new Object[]{recipe});
        Map recipeMap = new LinkedHashMap();
        if (recipe != null) {
            MapUtils.put(recipeMap, "product.category", String.valueOf(recipe.getCategoryId()));
            MapUtils.put(recipeMap, "product.name", recipe.getName());
            MapUtils.put(recipeMap, "product.id", String.valueOf(recipe.getExternalId()));
            MapUtils.put(recipeMap, "product.units", (Object) "1");
        }
        this.dataLayer.setState("", recipeMap);
    }

    public void setProduct(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "setProduct", new Object[]{orderProduct});
        Map productMap = new LinkedHashMap();
        if (!(orderProduct == null || orderProduct.getProduct() == null)) {
            MapUtils.put(productMap, "product.category", String.valueOf(orderProduct.getProduct().getCategoryId()));
            MapUtils.put(productMap, "product.name", orderProduct.getProduct().getName());
            MapUtils.put(productMap, "product.id", String.valueOf(orderProduct.getProduct().getExternalId()));
            MapUtils.put(productMap, "product.availability", orderProduct.isUnavailable() ? "NO" : "YES");
            MapUtils.put(productMap, "product.units", String.valueOf(orderProduct.getQuantity()));
            MapUtils.put(productMap, "product.revenue", Double.valueOf(ProductUtils.getProductTotalPrice(orderProduct)));
        }
        this.dataLayer.setState("", productMap);
    }

    public void setOffers(List<Offer> offers, List<String> qrCodes) {
        Ensighten.evaluateEvent(this, "setOffers", new Object[]{offers, qrCodes});
        this.dataLayer.setState(OffersModule.NAME, createOfferItems(offers, qrCodes));
    }

    public void setSearchTerm(String searchTerm, List<String> filters, String searchType, int numResults) {
        Ensighten.evaluateEvent(this, "setSearchTerm", new Object[]{searchTerm, filters, searchType, new Integer(numResults)});
        this.storeFilters = filters;
        Map searchMap = new LinkedHashMap();
        MapUtils.put(searchMap, "search.term", (Object) searchTerm);
        MapUtils.put(searchMap, "search.filter", getFilters(this.storeFilterToggled, filters));
        MapUtils.put(searchMap, "search.type", (Object) searchType);
        MapUtils.put(searchMap, "search.numResults", Integer.valueOf(numResults));
        this.dataLayer.setState("", searchMap);
    }

    public void setStoreFilterToggled(boolean storeFilterToggled) {
        Ensighten.evaluateEvent(this, "setStoreFilterToggled", new Object[]{new Boolean(storeFilterToggled)});
        this.storeFilterToggled = storeFilterToggled;
        Map filterMap = new LinkedHashMap();
        MapUtils.put(filterMap, "search.filter", getFilters(storeFilterToggled, this.storeFilters));
        this.dataLayer.setState("", filterMap);
    }

    public void setTransaction(Order order, MenuType menuType, Store store) {
        Ensighten.evaluateEvent(this, "setTransaction", new Object[]{order, menuType, store});
        Map orderMap = new LinkedHashMap();
        if (order != null) {
            if (!(order.getPayment() == null || order.getPayment().getPOD() == null)) {
                MapUtils.put(orderMap, "transaction.fulfillment", order.getPayment().getPOD().name());
            }
            MapUtils.put(orderMap, "transaction.calories", AppUtils.getEnergyTextForOrder(order, OrderUtils.getTotalEnergyUnit(order)));
            MapUtils.put(orderMap, "transaction.price.tax", String.valueOf(order.getTotalTax()));
            if (menuType != null) {
                MapUtils.put(orderMap, "transaction.daypart", menuType.getShortName());
            }
            MapUtils.put(orderMap, "transaction.orderId", order.getOrderNumber());
            MapUtils.put(orderMap, "transaction.paymentMethod", order.getPaymentMethodDisplayName());
            if (store != null) {
                MapUtils.put(orderMap, "transaction.state", store.getState());
                MapUtils.put(orderMap, "transaction.zip", store.getPostalCode());
            }
            Object basketItems = new ArrayList();
            if (order.getProducts() != null) {
                basketItems.addAll(createProductBasketItems(order.getProducts()));
            }
            if (order.getOffers() != null) {
                basketItems.addAll(createOfferBasketItems(order.getOffers(), order.getPriceType()));
            }
            MapUtils.put(orderMap, "transaction.basketItems", basketItems);
        }
        this.dataLayer.setState("", orderMap);
    }

    public void setLocation(Location location) {
        Ensighten.evaluateEvent(this, "setLocation", new Object[]{location});
        Map locationMap = new LinkedHashMap();
        if (location != null) {
            MapUtils.put(locationMap, "location.latitude", String.format("%.8f", new Object[]{Double.valueOf(location.getLatitude())}));
            MapUtils.put(locationMap, "location.longitude", String.format("%.8f", new Object[]{Double.valueOf(location.getLongitude())}));
        }
        this.dataLayer.setState("", locationMap);
    }

    public void setFormName(String formName) {
        Ensighten.evaluateEvent(this, "setFormName", new Object[]{formName});
        this.dataLayer.setState("content.formName", formName);
    }

    public void setPageSection(String section) {
        Ensighten.evaluateEvent(this, "setPageSection", new Object[]{section});
        List<String> currentPageSections = (List) this.dataLayer.getState("page.section");
        if (currentPageSections == null) {
            currentPageSections = new ArrayList();
        }
        this.dataLayer.setState("page.section", addPageSection(currentPageSections, section));
    }

    private List<String> addPageSection(List<String> pageSections, String newSection) {
        Ensighten.evaluateEvent(this, "addPageSection", new Object[]{pageSections, newSection});
        if (EXCLUDED_PAGES.contains(newSection)) {
            return null;
        }
        if (TOP_LEVEL_PAGES.contains(newSection)) {
            return Collections.singletonList(formatPageSection(1, newSection));
        }
        List<String> result = new ArrayList();
        if (containsPage(pageSections, newSection)) {
            for (int jj = 0; jj < pageSections.size(); jj++) {
                String value = (String) pageSections.get(jj);
                result.add(value);
                if (value.contains(newSection)) {
                    return result;
                }
            }
            return result;
        }
        String nextLevel = formatPageSection(pageSections.size() + 1, newSection);
        result.addAll(pageSections);
        result.add(nextLevel);
        return result;
    }

    private String formatPageSection(int level, String sectionName) {
        Ensighten.evaluateEvent(this, "formatPageSection", new Object[]{new Integer(level), sectionName});
        return String.format("%s %s: %s", new Object[]{"Level", Integer.valueOf(level), sectionName});
    }

    public void logLifecycleEvent(String event) {
        Ensighten.evaluateEvent(this, "logLifecycleEvent", new Object[]{event});
        this.dataLayer.recordEvent(event, null).log();
    }

    public void logPageLoad(String pageName, String eventName) {
        Ensighten.evaluateEvent(this, "logPageLoad", new Object[]{pageName, eventName});
        if (pageName == null) {
            DataLayerManager.reportWarning("Page name is not set for tracking");
            return;
        }
        Map pageLoadMap = new LinkedHashMap();
        MapUtils.put(pageLoadMap, "page.name", (Object) pageName);
        MapUtils.put(pageLoadMap, "event.name", (Object) eventName);
        this.dataLayer.setState("", pageLoadMap).log();
    }

    public void logButtonClick(String tag) {
        Ensighten.evaluateEvent(this, "logButtonClick", new Object[]{tag});
        this.dataLayer.recordEvent(tag, null).log();
    }

    public void logListItemClick(String tag, int positionTag) {
        Ensighten.evaluateEvent(this, "logListItemClick", new Object[]{tag, new Integer(positionTag)});
        this.dataLayer.recordEvent(tag, null, String.valueOf(positionTag)).log();
    }

    public void recordError(String errorMessage) {
        Ensighten.evaluateEvent(this, "recordError", new Object[]{errorMessage});
        this.dataLayer.setState("error.message", errorMessage).recordEvent("Error", null).log();
        this.dataLayer.setState("error.message", null);
    }

    public void setListener(Listener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.dataLayer.setListener(listener);
    }

    private List<Map<String, Object>> createOfferItems(List<Offer> offers, List<String> qrCodes) {
        Ensighten.evaluateEvent(this, "createOfferItems", new Object[]{offers, qrCodes});
        List<Map<String, Object>> result = new ArrayList();
        if (offers != null && qrCodes != null) {
            int ii = 0;
            while (true) {
                if (ii >= offers.size() && ii >= qrCodes.size()) {
                    break;
                }
                Map offerMap = new LinkedHashMap();
                if (ii < offers.size()) {
                    Offer offer = (Offer) offers.get(ii);
                    MapUtils.put(offerMap, "id", String.valueOf(offer.getOfferId()));
                    MapUtils.put(offerMap, "name", offer.getName());
                    MapUtils.put(offerMap, "expiration", offer.getLocalValidThrough());
                }
                if (ii < qrCodes.size()) {
                    MapUtils.put(offerMap, "qrCode", (String) qrCodes.get(ii));
                }
                result.add(offerMap);
                ii++;
            }
        }
        return result;
    }

    private List<Map<String, Object>> createProductBasketItems(Collection<OrderProduct> orderProducts) {
        Ensighten.evaluateEvent(this, "createProductBasketItems", new Object[]{orderProducts});
        List<Map<String, Object>> result = new ArrayList();
        for (OrderProduct orderProduct : orderProducts) {
            Map<String, Object> productMap = new LinkedHashMap();
            if (orderProduct.getProduct() != null) {
                productMap.put("id", String.valueOf(orderProduct.getProduct().getExternalId()));
            }
            productMap.put("availability", orderProduct.isUnavailable() ? "NO" : "YES");
            productMap.put("units", String.valueOf(orderProduct.getQuantity()));
            productMap.put(AnalyticsArgs.TRANSACTION_REVENUE, Double.valueOf(ProductUtils.getProductTotalPrice(orderProduct)));
            result.add(productMap);
        }
        return result;
    }

    private List<Map<String, Object>> createOfferBasketItems(Collection<OrderOffer> orderOffers, PriceType priceType) {
        Ensighten.evaluateEvent(this, "createOfferBasketItems", new Object[]{orderOffers, priceType});
        List<Map<String, Object>> result = new ArrayList();
        for (OrderOffer orderOffer : orderOffers) {
            Map<String, Object> offerMap = new LinkedHashMap();
            if (orderOffer.getOffer() != null) {
                offerMap.put("id", String.valueOf(orderOffer.getOffer().getOfferId()));
            }
            offerMap.put("availability", "YES");
            offerMap.put("units", "1");
            offerMap.put(AnalyticsArgs.TRANSACTION_REVENUE, String.valueOf(orderOffer.getTotalPrice(priceType)));
            result.add(offerMap);
        }
        return result;
    }

    private List<String> getFilters(boolean storeFilterToggled, List<String> storeFilters) {
        Ensighten.evaluateEvent(this, "getFilters", new Object[]{new Boolean(storeFilterToggled), storeFilters});
        List<String> result = new ArrayList();
        if (storeFilterToggled) {
            result.add("Exclude Non-Mobile Ordering Stores");
        }
        if (storeFilters != null) {
            result.addAll(storeFilters);
        }
        return result;
    }

    private boolean containsPage(List<String> currentBreadcrumbs, String pageName) {
        Ensighten.evaluateEvent(this, "containsPage", new Object[]{currentBreadcrumbs, pageName});
        if (!ListUtils.isEmpty(currentBreadcrumbs)) {
            for (String individualCrumb : currentBreadcrumbs) {
                if (individualCrumb.contains(pageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String sha256(String input) {
        Ensighten.evaluateEvent(this, "sha256", new Object[]{input});
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(input.getBytes(Utf8Charset.NAME));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(b & 255);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            DataLayerManager.reportWarning("Unable to hash text.");
            return "";
        }
    }
}
