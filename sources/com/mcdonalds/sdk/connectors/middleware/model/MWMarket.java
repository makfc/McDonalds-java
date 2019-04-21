package com.mcdonalds.sdk.connectors.middleware.model;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.modules.models.MarketCatalog;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MWMarket implements Serializable {
    @SerializedName("DisplayCategory")
    public List<MWDisplayCategory> displayCategory;
    @SerializedName("DisplayCategoryVersion")
    public String displayCategoryVersion;
    @SerializedName("Facilities")
    public List<MWFacility> facilities;
    @SerializedName("FacilityVersion")
    public String facilityVersion;
    @SerializedName("FeedbackTypeNames")
    public List<MWFeedbackTypeName> feedbackTypeNames;
    @SerializedName("FeedbackTypeNamesVersion")
    public String feedbackTypeNamesVersion;
    @SerializedName("LanguageVersion")
    public String languageVersion;
    @SerializedName("Languages")
    public List<MWLanguage> languages;
    @SerializedName("MenuTypeVersion")
    public String menuTypeVersion;
    @SerializedName("MenuType")
    public List<MWMenuType> menuTypes;
    @SerializedName("Names")
    public List<MWNameInfo> names;
    @SerializedName("NamesVersion")
    public String namesVersion;
    @SerializedName("PaymentMethods")
    public List<MWPaymentMethod> paymentMethods;
    @SerializedName("PaymentMethodsVersion")
    public String paymentMethodsVersion;
    @SerializedName("PromotionVersion")
    public String promotionVersion;
    @SerializedName("Promotions")
    public List<MWPromotion> promotions;
    @SerializedName("RecipeVersion")
    public String recipeVersion;
    @SerializedName("Recipes")
    public List<MWRecipe> recipes;
    @SerializedName("Restaurants")
    public List<MWRestaurant> restaurants;
    @SerializedName("RestaurantsVersion")
    public String restaurantsVersion;
    @SerializedName("SocialNetworkVersion")
    public String socialNetworkVersion;
    @SerializedName("SocialNetwork")
    public List<MWSocialNetwork> socialNetworks;
    @SerializedName("StaticData")
    public List<MWStaticData> staticData;
    @SerializedName("StaticDataVersion")
    public String staticDataVersion;
    @SerializedName("TenderTypeVersion")
    public String tenderTypeVersion;
    @SerializedName("TenderTypes")
    public List<MWTenderType> tenderTypes;

    public MarketCatalog toMarketCatalog(Context context) {
        if (VERSION.SDK_INT >= 23) {
            Configuration configuration = context.getResources().getConfiguration();
            configuration.setLocale(new Locale(LocalDataManager.getSharedInstance().getDeviceLanguage()));
            context = context.createConfigurationContext(configuration);
        }
        MarketCatalog marketCatalog = new MarketCatalog();
        List<Category> categories = new ArrayList();
        if (this.displayCategory != null) {
            for (MWDisplayCategory mwCategory : this.displayCategory) {
                categories.add(mwCategory.toCategory());
            }
        }
        marketCatalog.setCategoriesVersion(this.displayCategoryVersion);
        marketCatalog.setCategories(categories);
        List<Facility> appFacilities = new ArrayList();
        SerializableSparseArray<MWFacility> facilityMap = new SerializableSparseArray();
        if (this.facilities != null) {
            for (MWFacility mwFacility : this.facilities) {
                facilityMap.put(mwFacility.facilityID, mwFacility);
                appFacilities.add(mwFacility.toFacility());
            }
        }
        marketCatalog.setFacilitiesVersion(this.facilityVersion);
        marketCatalog.setFacilities(appFacilities);
        List<Store> stores = new ArrayList();
        if (this.restaurants != null) {
            for (MWRestaurant restaurant : this.restaurants) {
                stores.add(restaurant.toStore(context));
            }
        }
        marketCatalog.setStoresVersion(this.restaurantsVersion);
        marketCatalog.setStores(stores);
        List<PaymentMethod> appPaymentMethods = new ArrayList();
        if (this.paymentMethods != null) {
            appPaymentMethods.addAll(getPaymentMethods(this.paymentMethods));
        }
        marketCatalog.setPaymentMethodsVersion(this.paymentMethodsVersion);
        marketCatalog.setPaymentMethods(appPaymentMethods);
        List<FeedBackType> appFeedBackTypes = new ArrayList();
        if (this.feedbackTypeNames != null) {
            for (MWFeedbackTypeName mwFeedbackTypeName : this.feedbackTypeNames) {
                appFeedBackTypes.add(mwFeedbackTypeName.toFeedBackType());
            }
        }
        marketCatalog.setFeedBackTypesVersion(this.feedbackTypeNamesVersion);
        marketCatalog.setFeedBackTypes(appFeedBackTypes);
        List<TenderType> appTenderTypes = new ArrayList();
        if (this.tenderTypes != null) {
            for (MWTenderType mwTenderType : this.tenderTypes) {
                appTenderTypes.add(mwTenderType.toTenderType());
            }
        }
        marketCatalog.setTenderTypesVersion(this.tenderTypeVersion);
        marketCatalog.setTenderTypes(appTenderTypes);
        List<Promotion> appPromotions = new ArrayList();
        if (this.promotions != null) {
            for (MWPromotion mwPromotion : this.promotions) {
                appPromotions.add(mwPromotion.toPromotion());
            }
        }
        marketCatalog.setPromotionsVersion(this.promotionVersion);
        marketCatalog.setPromotions(appPromotions);
        List<MenuType> appMenuTypes = new ArrayList();
        if (this.menuTypes != null) {
            for (MWMenuType mwMenuType : this.menuTypes) {
                appMenuTypes.add(mwMenuType.toMenuType());
            }
        } else if (!AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES)) {
            this.menuTypes = new ArrayList();
            String languageId = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance().getCurrentLanguageTag();
            MWMenuType breakfastMenuType = new MWMenuType();
            breakfastMenuType.menuTypeID = 0;
            MWName breakFastName = new MWName();
            String breakfastStr = context.getString(C3883R.string.breakfast);
            breakFastName.name = breakfastStr;
            breakFastName.longName = breakfastStr;
            breakFastName.shortName = breakfastStr;
            breakFastName.languageID = languageId;
            breakfastMenuType.names = Collections.singletonList(breakFastName);
            breakfastMenuType.isValid = true;
            this.menuTypes.add(breakfastMenuType);
            appMenuTypes.add(breakfastMenuType.toMenuType());
            MWMenuType lunchMenuType = new MWMenuType();
            lunchMenuType.menuTypeID = 1;
            MWName lunchName = new MWName();
            String regularStr = context.getString(C3883R.string.lunch_and_dinner);
            lunchName.name = regularStr;
            lunchName.longName = regularStr;
            lunchName.shortName = regularStr;
            lunchName.languageID = languageId;
            lunchMenuType.names = Collections.singletonList(lunchName);
            lunchMenuType.isValid = true;
            this.menuTypes.add(lunchMenuType);
            appMenuTypes.add(lunchMenuType.toMenuType());
        }
        marketCatalog.setMenuTypesVersion(this.menuTypeVersion);
        marketCatalog.setMenuTypes(appMenuTypes);
        List<SocialNetwork> appSocialNetworks = new ArrayList();
        if (this.socialNetworks != null) {
            for (MWSocialNetwork mwSocialNetwork : this.socialNetworks) {
                appSocialNetworks.add(mwSocialNetwork.toSocialNetwork());
            }
        }
        marketCatalog.setSocialNetworksVersion(this.socialNetworkVersion);
        marketCatalog.setSocialNetworks(appSocialNetworks);
        marketCatalog.setNamesVersion(this.namesVersion);
        marketCatalog.setRecipesVersion(this.recipeVersion);
        return marketCatalog;
    }

    private static List<PaymentMethod> getPaymentMethods(List<MWPaymentMethod> mwPaymentMethods) {
        if (mwPaymentMethods == null) {
            return null;
        }
        com.mcdonalds.sdk.services.configuration.Configuration config = com.mcdonalds.sdk.services.configuration.Configuration.getSharedInstance();
        ArrayList<Double> paymentMethodsConfig = (ArrayList) config.getValueForKey("supportedPaymentMethods.paymentMethodIDs");
        List<Integer> paymentMethodIDs = null;
        if (paymentMethodsConfig != null) {
            paymentMethodIDs = new ArrayList();
            Iterator it = paymentMethodsConfig.iterator();
            while (it.hasNext()) {
                paymentMethodIDs.add(Integer.valueOf(((Double) it.next()).intValue()));
            }
        }
        SparseArray<String> paymentMethodNames = new SparseArray();
        ArrayList<Integer> expectedPaymentMethodIDs = new ArrayList();
        expectedPaymentMethodIDs.add(Integer.valueOf(config.getIntForKey("supportedPaymentMethods.creditCard.expectedPaymentMethodID")));
        expectedPaymentMethodIDs.add(Integer.valueOf(config.getIntForKey("supportedPaymentMethods.cash.expectedPaymentMethodID")));
        expectedPaymentMethodIDs.add(Integer.valueOf(config.getIntForKey("supportedPaymentMethods.thirdParty.expectedPaymentMethodID")));
        expectedPaymentMethodIDs.add(Integer.valueOf(config.getIntForKey(PaymentMethod.KEY_WECHAT_PAYMENT)));
        expectedPaymentMethodIDs.add(Integer.valueOf(config.getIntForKey("supportedPaymentMethods.other.expectedPaymentMethodID")));
        int size = expectedPaymentMethodIDs.size();
        for (int j = 0; j < size; j++) {
            int expectedPaymentMethodID = ((Integer) expectedPaymentMethodIDs.get(j)).intValue();
            if (expectedPaymentMethodID != 0) {
                switch (j) {
                    case 0:
                        paymentMethodNames.put(expectedPaymentMethodID, "Credit");
                        break;
                    case 1:
                        paymentMethodNames.put(expectedPaymentMethodID, "Cash");
                        break;
                    case 2:
                        paymentMethodNames.put(expectedPaymentMethodID, "ThirdPart");
                        break;
                    case 3:
                        paymentMethodNames.put(expectedPaymentMethodID, SocialNetwork.WECHAT_NAME);
                        break;
                    case 4:
                        paymentMethodNames.put(expectedPaymentMethodID, "Other");
                        break;
                    default:
                        break;
                }
            }
        }
        List<PaymentMethod> paymentMethods = new ArrayList();
        size = mwPaymentMethods.size();
        for (int i = 0; i < size; i++) {
            MWPaymentMethod mwPaymentMethod = (MWPaymentMethod) mwPaymentMethods.get(i);
            if (mwPaymentMethod.isEnabled.booleanValue()) {
                if (paymentMethodIDs != null) {
                    Integer paymentID = mwPaymentMethod.getPaymentMethodID();
                    if (paymentMethodIDs.contains(paymentID)) {
                        String paymentName = (String) paymentMethodNames.get(paymentID.intValue());
                        if (paymentMethodNames.size() <= 0 || paymentName == null) {
                            switch (mwPaymentMethod.paymentMode.intValue()) {
                                case 2:
                                    mwPaymentMethod.paymentMode = Integer.valueOf(PaymentMode.Credit.ordinal());
                                    break;
                                case 3:
                                case 4:
                                    mwPaymentMethod.paymentMode = Integer.valueOf(PaymentMode.Cash.ordinal());
                                    break;
                                default:
                                    mwPaymentMethod.paymentMode = Integer.valueOf(PaymentMode.Other.ordinal());
                                    break;
                            }
                        }
                        mwPaymentMethod.paymentMode = Integer.valueOf(PaymentMode.valueOf((String) paymentMethodNames.get(paymentID.intValue())).ordinal());
                        paymentMethods.add(mwPaymentMethod.toPaymentMethod());
                    }
                } else if (mwPaymentMethod.paymentLabels != null) {
                    paymentMethods.add(mwPaymentMethod.toPaymentMethod());
                }
            }
        }
        return paymentMethods;
    }
}
