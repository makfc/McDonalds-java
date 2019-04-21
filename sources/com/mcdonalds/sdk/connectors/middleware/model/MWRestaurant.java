package com.mcdonalds.sdk.connectors.middleware.model;

import android.content.Context;
import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.middleware.model.MWDigitalServices.TechKey;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.utils.DateUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MWRestaurant implements Serializable {
    @SerializedName("CatalogVersions")
    public List<MWCatalogVersionItem> MWCatalogVersions;
    @SerializedName("NPVersion")
    public String NPVersion;
    @SerializedName("TODCutoffTime")
    public String TODCutoffTime;
    @SerializedName("AdvancedOrderMaximumTimeLimitMinutes")
    public int advancedOrderMaximumTimeLimitMinutes;
    @SerializedName("AdvancedOrderMinimumTimeLimitMinutes")
    public int advancedOrderMinimumTimeLimitMinutes;
    @SerializedName("AutoBagSaleInformation")
    public MWAutoBagSaleInformation autoBagSaleInformation;
    @SerializedName("DayPart")
    public int dayPart;
    @SerializedName("Distance")
    public int distance;
    @SerializedName("EstimatedDeliveryTimeInStoreLocalTime")
    public String estimatedDeliveryTimeInStoreLocalTime;
    @SerializedName("ExpectedDeliveryTime")
    public String expectedDeliveryTime;
    @SerializedName("ExternalStoreNumber")
    public String externalStoreNumber;
    @SerializedName("Facilities")
    public List<Integer> facilities;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("LargeOrderAllowed")
    public boolean largeOrderAllowed;
    @SerializedName("Latitude")
    public double latitude;
    @SerializedName("Locations")
    public List<MWLocation> locations;
    @SerializedName("Longitude")
    public double longitude;
    @SerializedName("MinimumOrderValue")
    public double minimumOrderValue;
    @SerializedName("NowInStoreLocalTime")
    public String nowInStoreLocalTime;
    @SerializedName("OpeningHours")
    public List<MWOpeningHourItem> openingHours;
    @SerializedName("OrderMaxTimeMIN")
    public int orderMaxTimeMin;
    @SerializedName("OrderMinTimeMIN")
    public int orderMinTimeMin;
    @SerializedName("OutageProductCodes")
    public List<String> outageProductCodes;
    @SerializedName("PointsOfDistribution")
    public List<MWPointOfDistributionItem> pointsOfDistribution;
    @SerializedName("RecipeVersion")
    public String recipeVersion;
    @SerializedName("Recipes")
    public List<MWRecipe> recipes;
    @SerializedName("Information")
    public MWRestaurantInformation restaurantInformation;
    @SerializedName("StatusID")
    public int statusID;
    @SerializedName("StoreAddress")
    public String storeAddress;
    @SerializedName("StoreAddressCity")
    public String storeAddressCity;
    @SerializedName("StoreAddressCountry")
    public String storeAddressCountry;
    @SerializedName("StoreAddressState")
    public String storeAddressState;
    @SerializedName("StoreAddressZIP")
    public String storeAddressZIP;
    @SerializedName("StoreCutoffTime")
    public String storeCutoffTime;
    @SerializedName("StoreMenuTypeCalendar")
    public List<MWStoreMenuTypeCalendarItem> storeMenuTypeCalendar;
    @SerializedName("StoreName")
    public String storeName;
    @SerializedName("StoreNumber")
    public int storeNumber;
    @SerializedName("StoreStatus")
    public int storeStatus;
    @SerializedName("SuppressChoiceDiscount")
    public boolean suppressChoiceDiscount;
    @SerializedName("TableService")
    public MWTableService tableService;
    @SerializedName("TimeZone")
    public int timeZone;

    private class MWAutoBagSaleInformation {
        @SerializedName("BagProductCode")
        int bagProductCode;
        @SerializedName("Enabled")
        boolean enabled;
        @SerializedName("NoBagProductCode")
        int noBagProductCode;
    }

    public Store toStore(Context context) {
        return toStore(context, null);
    }

    public Store toStore(Context context, Store store) {
        if (store == null) {
            store = new Store();
        }
        store.setStoreId(this.storeNumber);
        store.setLatitude(this.latitude);
        store.setLongitude(this.longitude);
        store.setDistance((double) this.distance);
        store.setAddress1(this.storeAddress);
        store.setAddress2(null);
        store.setCity(this.storeAddressCity);
        store.setPostalCode(this.storeAddressZIP);
        store.setState(this.storeAddressState);
        store.setCountry(this.storeAddressCountry);
        store.setStoreType(null);
        store.setStoreURL(null);
        store.setPhoneNumber(null);
        store.setMaxAdvanceOrderTime(this.orderMaxTimeMin);
        store.setMinAdvanceOrderTime(this.orderMinTimeMin);
        store.setTimeZone(this.timeZone);
        store.setAdvancedOrderMinimumTimeLimitMinutes(this.advancedOrderMinimumTimeLimitMinutes);
        store.setAdvancedOrderMaximumTimeLimitMinutes(this.advancedOrderMaximumTimeLimitMinutes);
        SparseArray<Facility> facilityMap = ((StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME)).getFacilityMap();
        if (!(this.facilities == null || facilityMap == null)) {
            List<String> facilityNames = new ArrayList();
            for (Integer facilityID : this.facilities) {
                Facility facility = (Facility) facilityMap.get(facilityID.intValue());
                if (facility != null) {
                    facilityNames.add(facility.getName());
                }
            }
            store.setFacilityNames(facilityNames);
        }
        try {
            setStoreHours(store, context);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        if (!(this.pointsOfDistribution == null || this.pointsOfDistribution.isEmpty())) {
            for (MWPointOfDistributionItem item : this.pointsOfDistribution) {
                if (!(item.digitalServices == null || item.digitalServices.isEmpty())) {
                    for (MWDigitalServices ds : item.digitalServices) {
                        if (!(ds.technologies == null || ds.technologies.isEmpty() || ds.technologies.get(0) == null || ((TechKey) ds.technologies.get(0)).key == null || !((TechKey) ds.technologies.get(0)).key.equals(StoreCapabilties.SCANNER_KEY))) {
                            store.setHasMobileOrdering(Boolean.valueOf(true));
                        }
                    }
                }
            }
        }
        store.setPODs(this.pointsOfDistribution);
        store.setExpectedDeliveryTime(this.expectedDeliveryTime);
        store.setNowInStoreLocalTime(this.nowInStoreLocalTime);
        store.setStoreOpen(this.storeStatus == 1);
        store.setDayPart(this.dayPart);
        store.setTODCutoffTime(this.TODCutoffTime);
        store.setStoreCutoffTime(this.storeCutoffTime);
        store.setLargeOrderAllowed(this.largeOrderAllowed);
        store.setNPVersion(this.NPVersion);
        store.setMinimumOrderValue(this.minimumOrderValue);
        store.setExternalStoreNumber(this.externalStoreNumber);
        store.setLocations(this.locations);
        store.setOutageProducts(this.outageProductCodes);
        if (this.autoBagSaleInformation != null) {
            store.setBagChargeEnabled(this.autoBagSaleInformation.enabled);
            store.setBagProductCode(this.autoBagSaleInformation.bagProductCode);
            store.setNoBagProductCode(this.autoBagSaleInformation.noBagProductCode);
        }
        if (this.tableService != null) {
            store.setTableService(this.tableService.toTableService(context));
        }
        return store;
    }

    /* Access modifiers changed, original: protected */
    public void setStoreHours(Store store, Context context) {
        List<String> listHours = new ArrayList();
        List<String[]> listHoursOnly = new ArrayList();
        List<MenuTypeCalendarItem> menuTypeCalendarItems = new ArrayList();
        boolean useEcpOpeningHours = !AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES) || this.storeMenuTypeCalendar == null;
        if (this.openingHours != null) {
            for (MWOpeningHourItem ecpOpeningHourItem : this.openingHours) {
                if (useEcpOpeningHours) {
                    menuTypeCalendarItems.addAll(ecpOpeningHourItem.toStoreMenuTypeCalendarItem());
                }
                listHoursOnly.add(new String[]{DateUtils.formatTo12Hour(ecpOpeningHourItem.fromTime), DateUtils.formatTo12Hour(ecpOpeningHourItem.toTime)});
                if (DateUtils.areTimesEqualOrWithinAMinute(DateUtils.formatTo12Hour(ecpOpeningHourItem.fromTime), DateUtils.formatTo12Hour(toTime))) {
                    listHours.add(context.getString(C3883R.string.label_open_all_day));
                } else {
                    listHours.add(String.format(context.getString(C3883R.string.store_opening_hours_range), new Object[]{DateUtils.formatTo12Hour(ecpOpeningHourItem.fromTime), DateUtils.formatTo12Hour(toTime)}));
                }
            }
        }
        store.setStoreHours(listHours);
        store.setStoreOperatingHours(listHoursOnly);
        if (!useEcpOpeningHours) {
            for (MWStoreMenuTypeCalendarItem storeMenuTypeCalendarItem : this.storeMenuTypeCalendar) {
                menuTypeCalendarItems.add(storeMenuTypeCalendarItem.toStoreMenuTypeCalendarItem());
            }
        }
        store.setMenuTypeCalendar(menuTypeCalendarItems);
        try {
            store.setTimeDifference(DateUtils.parseFromISO8631(this.nowInStoreLocalTime, false).getTime() - new Date().getTime());
        } catch (ParseException e) {
            store.setTimeDifference(0);
        }
    }
}
