package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.DateUtils;
import com.mcdonalds.sdk.utils.ListUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

public class MiddlewareStoreLocatorStore {
    public static final String DEFAULT_POD = "LOBBY";
    private static final String DELIMITER = ", ";
    private static final String KEY_FILTERS = "connectors.MiddlewareStoreLocator.storeLocator.filters";
    public static final String KEY_POD_OPENING_HOURS = "connectors.MiddlewareStoreLocator.storeLocator.openingHoursPOD";
    public static final String KEY_STORE_ID_TYPE = "connectors.MiddlewareStoreLocator.storeLocator.storeIdType";
    public static final String KEY_TIMEZONE_CONVERSION = "connectors.MiddlewareStoreLocator.storeLocator.serverTimeZoneAbbreviation";
    private static final String NULL = "null";
    private static final String OPEN = "OPEN";
    private static final String START_OR_END_TIME_EXCEEDING_THRESHOLD = "Error: start time %s or end time %s exceeding threshold %d";
    private static final String STORE_DEVICE_TIME_ZONE_START_END_TIME = "Store device time zone start and end time: ";
    private static final String STORE_START_END_TIME = "Store start and end time: ";
    private static final String TAG = MiddlewareStoreLocatorStore.class.getName();
    private static final long THRESHOLD_TIME = 90000000;
    private static List<String> sFilters;
    private static HashMap<String, String> sFiltersMap = new HashMap();
    @SerializedName("address")
    public MiddlewareStoreLocatorAddress address;
    @SerializedName("countryCode")
    public String countryCode;
    @SerializedName("identifiers")
    public MiddlewareStoreLocatorIdentifier identifiers;
    @SerializedName("localization")
    public String locale;
    @SerializedName("marketCode")
    public String marketCode;
    @SerializedName("storeNumbers")
    public MiddlewareStoreLocatorStoreNumbers numbers;
    @SerializedName("publicName")
    public String publicName;
    @SerializedName("storeAttributes")
    public MiddlewareStoreLocatorAttributeList storeAttributes;
    @SerializedName("generalStatus")
    public MiddlewareStoreGeneralStatus storeGeneralStatus;
    @SerializedName("storeServices")
    public MiddlewareStoreServices storeServices;
    @SerializedName("storeType")
    public MiddlewareStoreLocatorStoreType storeType;
    @SerializedName("id")
    public String uniqueID;
    @SerializedName("urls")
    public MiddlewareStoreLocatorURLList urls;

    public static void init(Context context) {
        ArrayList<String> filters = (ArrayList) Configuration.getSharedInstance().getValueForKey(KEY_FILTERS);
        sFilters = new ArrayList();
        sFiltersMap = new HashMap();
        if (context != null && filters != null) {
            TreeMap<String, String> treeMap = new TreeMap();
            int size = filters.size();
            for (int i = 0; i < size; i++) {
                String filter = (String) filters.get(i);
                int resourceId = context.getResources().getIdentifier("store_locator_filter_" + filter.toLowerCase(), "string", context.getPackageName());
                if (resourceId > 0) {
                    String fromFilter = context.getString(resourceId);
                    if (!TextUtils.isEmpty(fromFilter)) {
                        treeMap.put(fromFilter, filter);
                    }
                }
            }
            sFilters.addAll(treeMap.keySet());
            sFiltersMap.putAll(treeMap);
        }
    }

    public static void init(HashMap<String, String> filtersMap) {
        sFiltersMap = filtersMap;
    }

    public Store toStore() {
        Store store = new Store();
        if (!ListUtils.isEmpty(this.identifiers.storeIdentifier)) {
            MiddlewareStoreLocatorStoreIdentifier identifier = (MiddlewareStoreLocatorStoreIdentifier) this.identifiers.storeIdentifier.get(0);
            String identifierType = (String) Configuration.getSharedInstance().getValueForKey(KEY_STORE_ID_TYPE);
            if (identifierType != null) {
                int size = this.identifiers.storeIdentifier.size();
                for (int i = 1; i < size; i++) {
                    if (((MiddlewareStoreLocatorStoreIdentifier) this.identifiers.storeIdentifier.get(i)).identifierType.equals(identifierType)) {
                        identifier = (MiddlewareStoreLocatorStoreIdentifier) this.identifiers.storeIdentifier.get(i);
                        break;
                    }
                }
            }
            if (!(identifier == null || identifier.identifierValue == null || identifier.identifierValue.isEmpty())) {
                store.setStoreId(Integer.parseInt(identifier.identifierValue));
            }
        }
        setData(store);
        return store;
    }

    private void setData(Store store) {
        setAddressData(store);
        setStoreLocation(store);
        setStoreFacilities(store);
        setPrimaryPhoneNumer(store);
        if (!(this.storeGeneralStatus == null || this.storeGeneralStatus.status.equalsIgnoreCase(OPEN))) {
            store.setGeneralStatusIsOpen(false);
        }
        if (!(this.numbers == null || ListUtils.isEmpty(this.numbers.phoneNumbers))) {
            store.setPhoneNumber(((MiddlewareStoreLocatorPhoneNumber) this.numbers.phoneNumbers.get(0)).number);
        }
        if (this.urls != null && this.urls.urlList != null) {
            int size = this.urls.urlList.size();
            for (int i = 0; i < size; i++) {
                MiddlewareStoreLocatorURL url = (MiddlewareStoreLocatorURL) this.urls.urlList.get(i);
                if (url.urlType.equalsIgnoreCase("STORE")) {
                    store.setStoreURL(url.url);
                    break;
                }
            }
        }
        if (!(this.storeType == null || TextUtils.isEmpty(this.storeType.storeTypeCode))) {
            store.setStoreType(this.storeType.storeTypeCode);
        }
        setStoreOperationHours(store);
        if (this.publicName != null) {
            store.setPublicName(this.publicName);
        }
    }

    private void setStoreOperationHours(Store store) {
        String[] listHoursArray = new String[7];
        String[][] operatingHoursArray = (String[][]) Array.newInstance(String.class, new int[]{7, 2});
        List<String> listHours = new ArrayList();
        List<String[]> operatingHoursList = new ArrayList();
        String configServiceValue = Configuration.getSharedInstance().getStringForKey(KEY_POD_OPENING_HOURS);
        if (configServiceValue == null || configServiceValue.isEmpty()) {
            configServiceValue = DEFAULT_POD;
        }
        if (this.storeServices != null) {
            int i;
            int dayOfWeek;
            Map<String, MiddlewareDayOfWeekService> servicesForDay;
            SparseArray<Map<String, MiddlewareDayOfWeekService>> weekDays = new SparseArray();
            for (i = 0; i < this.storeServices.dayOfWeekService.size(); i++) {
                dayOfWeek = getDayOfWeek(((MiddlewareDayOfWeekService) this.storeServices.dayOfWeekService.get(i)).dayOfWeek);
                servicesForDay = (Map) weekDays.get(dayOfWeek);
                if (servicesForDay == null) {
                    servicesForDay = new HashMap();
                    weekDays.put(dayOfWeek, servicesForDay);
                }
                servicesForDay.put(((MiddlewareDayOfWeekService) this.storeServices.dayOfWeekService.get(i)).service, this.storeServices.dayOfWeekService.get(i));
            }
            for (dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                servicesForDay = (Map) weekDays.get(dayOfWeek);
                MiddlewareDayOfWeekService service = null;
                if (servicesForDay != null) {
                    service = (MiddlewareDayOfWeekService) servicesForDay.get(configServiceValue);
                    if (service == null) {
                        service = (MiddlewareDayOfWeekService) servicesForDay.get(DEFAULT_POD);
                    }
                }
                if (service != null) {
                    String startTime = service.startTime;
                    String endTime = service.endTime;
                    String timeZone = Configuration.getSharedInstance().getStringForKey(KEY_TIMEZONE_CONVERSION);
                    if (timeZone != null) {
                        startTime = convertTimeToTimeZone(startTime, timeZone);
                        endTime = convertTimeToTimeZone(endTime, timeZone);
                    }
                    String fromTime = DateUtils.getTimeInHours(startTime);
                    String toTime = DateUtils.getTimeInHours(endTime);
                    if (DateUtils.areTimesEqualOrWithinAMinute(fromTime, toTime)) {
                        listHoursArray[dayOfWeek] = McDonalds.getContext().getString(C3883R.string.label_open_all_day);
                    } else {
                        listHoursArray[dayOfWeek] = String.format(McDonalds.getContext().getString(C3883R.string.store_opening_hours_range), new Object[]{fromTime, toTime});
                    }
                    operatingHoursArray[dayOfWeek][0] = fromTime;
                    operatingHoursArray[dayOfWeek][1] = toTime;
                }
            }
            for (i = 0; i < listHoursArray.length; i++) {
                listHours.add(listHoursArray[i]);
                operatingHoursList.add(operatingHoursArray[i]);
            }
            store.setStoreHours(listHours);
            store.setStoreOperatingHours(operatingHoursList);
        }
    }

    private void setStoreLongestOperationHours(Store store) {
        if (this.storeServices != null) {
            String[][] operatingHoursArray = (String[][]) Array.newInstance(String.class, new int[]{7, 2});
            String[] listHours = new String[7];
            HashMap<String, List<MiddlewareDayOfWeekService>> dayOfWeekServices = getDayOfWeekServices();
            Set<String> daysOfweek = dayOfWeekServices.keySet();
            String timeZone = Configuration.getSharedInstance().getStringForKey(KEY_TIMEZONE_CONVERSION);
            for (String day : daysOfweek) {
                List<MiddlewareDayOfWeekService> services = (List) dayOfWeekServices.get(day);
                String smallestStartTime = "";
                String longestEndTime = "";
                for (int i = 0; i < services.size(); i++) {
                    String startTime = ((MiddlewareDayOfWeekService) services.get(i)).startTime;
                    String endTime = ((MiddlewareDayOfWeekService) services.get(i)).endTime;
                    if (!TextUtils.isEmpty(startTime) && (TextUtils.isEmpty(smallestStartTime) || Long.parseLong(smallestStartTime) > Long.parseLong(startTime))) {
                        smallestStartTime = startTime;
                    }
                    if (!TextUtils.isEmpty(endTime) && (TextUtils.isEmpty(longestEndTime) || Long.parseLong(longestEndTime) < Long.parseLong(endTime))) {
                        longestEndTime = endTime;
                    }
                    if ((!TextUtils.isEmpty(startTime) && Long.parseLong(startTime) > THRESHOLD_TIME) || (!TextUtils.isEmpty(endTime) && Long.parseLong(endTime) > THRESHOLD_TIME)) {
                        Log.e(TAG, String.format(START_OR_END_TIME_EXCEEDING_THRESHOLD, new Object[]{startTime, endTime, Long.valueOf(THRESHOLD_TIME)}));
                    }
                }
                int dayOfWeek = getDayOfWeek(day);
                Log.d(TAG, STORE_START_END_TIME + smallestStartTime + DELIMITER + longestEndTime);
                boolean is24Hour = DateUtils.is24HourOpen(smallestStartTime, longestEndTime);
                String fromTime = DateUtils.getHoursTimeInTimeZone(smallestStartTime, timeZone);
                String toTime = DateUtils.getHoursTimeInTimeZone(longestEndTime, timeZone);
                Log.d(TAG, STORE_DEVICE_TIME_ZONE_START_END_TIME + fromTime + DELIMITER + toTime);
                if (is24Hour) {
                    listHours[dayOfWeek] = McDonalds.getContext().getString(C3883R.string.label_open_all_day);
                } else {
                    listHours[dayOfWeek] = String.format("%1$s %2$s", new Object[]{fromTime, toTime});
                }
                operatingHoursArray[dayOfWeek][0] = fromTime;
                operatingHoursArray[dayOfWeek][1] = toTime;
            }
            store.setmStoreLongestHour(Arrays.asList(listHours));
            store.setmStoreLongestOperatingHours(Arrays.asList(operatingHoursArray));
        }
    }

    private HashMap<String, List<MiddlewareDayOfWeekService>> getDayOfWeekServices() {
        HashMap<String, List<MiddlewareDayOfWeekService>> dayOfWeekServices = new HashMap();
        for (MiddlewareDayOfWeekService dayOfWeekService : this.storeServices.dayOfWeekService) {
            String day = dayOfWeekService.dayOfWeek;
            if (!(TextUtils.isEmpty(day) || day.equals("null"))) {
                List<MiddlewareDayOfWeekService> services;
                if (dayOfWeekServices.containsKey(day)) {
                    services = (List) dayOfWeekServices.get(day);
                } else {
                    services = new ArrayList();
                }
                services.add(dayOfWeekService);
                dayOfWeekServices.put(day, services);
            }
        }
        return dayOfWeekServices;
    }

    private String convertTimeToTimeZone(String origTime, String timezone) {
        return String.valueOf(Long.parseLong(origTime) + ((long) TimeZone.getTimeZone(timezone).getRawOffset()));
    }

    private void setAddressData(Store store) {
        if (this.address != null) {
            if (!TextUtils.isEmpty(this.address.addressLine1)) {
                store.setAddress1(this.address.addressLine1);
            }
            if (!TextUtils.isEmpty(this.address.city)) {
                store.setCity(this.address.city);
            }
            if (!TextUtils.isEmpty(this.address.state)) {
                store.setState(this.address.state);
            }
            if (!TextUtils.isEmpty(this.address.zip)) {
                store.setPostalCode(this.address.zip);
            }
            if (!TextUtils.isEmpty(this.address.country)) {
                store.setCountry(this.address.country);
            }
        }
    }

    private void setStoreFacilities(Store store) {
        List<String> facilities = new ArrayList();
        List<String> storeFacilities = new ArrayList();
        int size = this.storeAttributes.attributeList.size();
        for (int i = 0; i < size; i++) {
            MiddlewareStoreLocatorAttribute attribute = (MiddlewareStoreLocatorAttribute) this.storeAttributes.attributeList.get(i);
            for (String filterKey : sFiltersMap.keySet()) {
                if (((String) sFiltersMap.get(filterKey)).equals(attribute.type)) {
                    facilities.add(filterKey);
                    break;
                }
            }
            storeFacilities.add(attribute.type);
        }
        store.setHasMobileOffers(Boolean.valueOf(storeFacilities.contains("MOBILEOFFERS")));
        store.setHasMobileOrdering(Boolean.valueOf(storeFacilities.contains("MOBILEORDERS")));
        store.setFacilityNames(facilities);
    }

    private void setStoreLocation(Store store) {
        if (this.address != null && this.address.location != null) {
            store.setLongitude(this.address.location.longitude);
            store.setLatitude(this.address.location.latitude);
        }
    }

    private void setPrimaryPhoneNumer(Store store) {
        if (this.numbers != null && this.numbers.phoneNumbers != null && !this.numbers.phoneNumbers.isEmpty()) {
            int i = 0;
            int size = this.numbers.phoneNumbers.size();
            while (i < size) {
                MiddlewareStoreLocatorPhoneNumber storePhoneNumber = (MiddlewareStoreLocatorPhoneNumber) this.numbers.phoneNumbers.get(i);
                if (storePhoneNumber.type == null || !storePhoneNumber.type.equals("PRIMARY")) {
                    i++;
                } else {
                    store.setPhoneNumber(storePhoneNumber.number);
                    return;
                }
            }
        }
    }

    private int getDayOfWeek(String dayOfWeek) {
        if (dayOfWeek == null || dayOfWeek.equalsIgnoreCase("SUNDAY")) {
            return 0;
        }
        if (dayOfWeek.equalsIgnoreCase("MONDAY")) {
            return 1;
        }
        if (dayOfWeek.equalsIgnoreCase("TUESDAY")) {
            return 2;
        }
        if (dayOfWeek.equalsIgnoreCase("WEDNESDAY")) {
            return 3;
        }
        if (dayOfWeek.equalsIgnoreCase("THURSDAY")) {
            return 4;
        }
        if (dayOfWeek.equalsIgnoreCase("FRIDAY")) {
            return 5;
        }
        if (dayOfWeek.equalsIgnoreCase("SATURDAY")) {
            return 6;
        }
        return 0;
    }
}
