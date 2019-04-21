package com.mcdonalds.sdk.connectors.autonavi;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AutoNaviStore {
    private static String s24Hours;
    private static String sDelivery;
    private static String sDriveThru;
    private static List<String> sFilters;
    private static Map<String, String> sFiltersMap;
    private static String sKiosk;
    private static String sMcCafe;
    private static String sMobileOffer;
    private static String sMobileOrdering;
    private static String sOpenAllDay;
    private static String sPlayland;
    private static String sWiFi;
    @SerializedName("_address")
    private String address;
    @SerializedName("StoreAddress_CN")
    private String chineseAddress;
    @SerializedName("CityName_CN")
    private String chineseCityName;
    @SerializedName("StoreName_CN")
    private String chineseName;
    @SerializedName("_createtime")
    private String createtime;
    @SerializedName("_distance")
    private double distance;
    @SerializedName("IsDriveThrough")
    private int driveThrough;
    @SerializedName("StoreAddress_EN")
    private String englishAddress;
    @SerializedName("CityName_EN")
    private String englishCityName;
    @SerializedName("StoreName_EN")
    private String englishName;
    @SerializedName("HasMDS")
    private int hasMDS;
    @SerializedName("HasMobileOffers")
    private int hasMobileOffers;
    @SerializedName("HasMobileOrdering")
    private int hasMobileOrdering;
    @SerializedName("StoreCode")
    /* renamed from: id */
    private int f6061id;
    @SerializedName("HasKIOSK")
    private int kiosk;
    @SerializedName("_location")
    private String location;
    @SerializedName("LocationX")
    private double locationX;
    @SerializedName("LocationY")
    private double locationY;
    @SerializedName("HasMcCafe")
    private int mcCafe;
    @SerializedName("_name")
    private String name;
    @SerializedName("IsOpen")
    private String open;
    @SerializedName("Is24")
    private int open24Hours;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("HasPlayland")
    private int playground;
    @SerializedName("_updatetime")
    private String updatetime;
    @SerializedName("HasWifi")
    private int wifi;
    @SerializedName("ZipCode")
    private String zipCode;

    public static void init(Context context) {
        sWiFi = context.getString(C3883R.string.sl_feature_wifi);
        sPlayland = context.getString(C3883R.string.sl_feature_playland);
        sDriveThru = context.getString(C3883R.string.sl_feature_drivethru);
        sDelivery = context.getString(C3883R.string.facility_delivery);
        sMobileOrdering = context.getString(C3883R.string.facility_mobile_ordering);
        sMobileOffer = context.getString(C3883R.string.facility_mobile_offers);
        sMcCafe = context.getString(C3883R.string.facility_mccafe);
        sKiosk = context.getString(C3883R.string.facility_has_kiosk);
        s24Hours = context.getString(C3883R.string.facility_24_hours);
        sOpenAllDay = context.getString(C3883R.string.label_open_all_day);
        sFilters = new ArrayList(7);
        sFilters.add(sWiFi);
        sFilters.add(sPlayland);
        sFilters.add(sDriveThru);
        sFilters.add(sDelivery);
        sFilters.add(sMobileOrdering);
        sFilters.add(sMobileOffer);
        sFilters.add(sMcCafe);
        sFilters.add(sKiosk);
        sFilters.add(s24Hours);
        sFiltersMap = new HashMap(7);
        sFiltersMap.put(sWiFi, "HasWifi");
        sFiltersMap.put(sPlayland, "HasPlayland");
        sFiltersMap.put(sDriveThru, "IsDriveThrough");
        sFiltersMap.put(sDelivery, "HasMDS");
        sFiltersMap.put(sMobileOrdering, "HasMobileOrdering");
        sFiltersMap.put(sMobileOffer, "HasMobileOffers");
        sFiltersMap.put(sMcCafe, "HasMcCafe");
        sFiltersMap.put(sKiosk, "HasKIOSK");
        sFiltersMap.put(s24Hours, "Is24");
    }

    public Store toStore() {
        boolean inEnglish = Configuration.getSharedInstance().getCurrentLocale().getLanguage().equals(Locale.US.getLanguage());
        Store store = new Store();
        store.setStoreId(this.f6061id);
        if (inEnglish) {
            setEnglishData(store);
        } else {
            setChineseData(store);
        }
        store.setDistance(this.distance);
        store.setPhoneNumber(this.phoneNumber);
        store.setPostalCode(this.zipCode);
        store.setHasMobileOffers(Boolean.valueOf(hasMobileOffers()));
        store.setHasMobileOrdering(Boolean.valueOf(hasMobileOrdering()));
        if (isOpen24Hours()) {
            List<String> listHours = new ArrayList();
            for (int i = 0; i < 7; i++) {
                listHours.add(sOpenAllDay);
            }
            store.setStoreHours(listHours);
        }
        setStoreLocation(store);
        setStoreFacilities(store);
        return store;
    }

    private void setChineseData(Store store) {
        store.setAddress1(this.chineseAddress);
        store.setCity(this.chineseCityName);
    }

    private void setEnglishData(Store store) {
        if (TextUtils.isEmpty(this.englishAddress)) {
            store.setAddress1(this.chineseAddress);
        } else {
            store.setAddress1(this.englishAddress);
        }
        if (TextUtils.isEmpty(this.englishCityName)) {
            store.setCity(this.chineseCityName);
        } else {
            store.setCity(this.englishCityName);
        }
    }

    private void setStoreFacilities(Store store) {
        List<String> facilities = new ArrayList();
        if (hasWifi()) {
            facilities.add(sWiFi);
        }
        if (hasPlayground()) {
            facilities.add(sPlayland);
        }
        if (hasDriveThrough()) {
            facilities.add(sDriveThru);
        }
        if (hasMDS()) {
            facilities.add(sDelivery);
        }
        if (hasMobileOrdering()) {
            facilities.add(sMobileOrdering);
        }
        if (hasMobileOffers()) {
            facilities.add(sMobileOffer);
        }
        if (isMcCafe()) {
            facilities.add(sMcCafe);
        }
        if (hasKiosk()) {
            facilities.add(sKiosk);
        }
        if (isOpen24Hours()) {
            facilities.add(s24Hours);
        }
        store.setFacilityNames(facilities);
    }

    private void setStoreLocation(Store store) {
        if (this.locationY != 0.0d) {
            store.setLongitude(this.locationY);
            store.setLatitude(this.locationX);
            return;
        }
        String[] loc = this.location.split(",");
        if (loc.length == 2) {
            double longitude = Double.valueOf(loc[0]).doubleValue();
            double latitude = Double.valueOf(loc[1]).doubleValue();
            store.setLongitude(longitude);
            store.setLatitude(latitude);
        }
    }

    public static List<String> getFilters() {
        return sFilters;
    }

    public static String getFilterKey(String filter) {
        return (String) sFiltersMap.get(filter);
    }

    public int getId() {
        return this.f6061id;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean hasMDS() {
        return this.hasMDS == 1;
    }

    public void setMDS(int mds) {
        this.hasMDS = mds;
    }

    public boolean hasDelivery() {
        return hasMDS();
    }

    public boolean hasMobileOffers() {
        return this.hasMobileOffers == 1;
    }

    public void setMobileOffers(int mobileOffers) {
        this.hasMobileOffers = mobileOffers;
    }

    public boolean hasMobileOrdering() {
        return this.hasMobileOrdering == 1;
    }

    public void setMobileOrdering(int mobileOredring) {
        this.hasMobileOrdering = mobileOredring;
    }

    public boolean hasDriveThrough() {
        return this.driveThrough == 1;
    }

    public void setDriveThrough(int driveThrough) {
        this.driveThrough = driveThrough;
    }

    public String getChineseAddress() {
        return this.chineseAddress;
    }

    public void setChineseAddress(String chineseAddress) {
        this.chineseAddress = chineseAddress;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean hasWifi() {
        return this.wifi == 1;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public String getEnglishAddress() {
        return this.englishAddress;
    }

    public void setEnglishAddress(String englishAddress) {
        this.englishAddress = englishAddress;
    }

    public String getOpen() {
        return this.open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public boolean hasKiosk() {
        return this.kiosk == 1;
    }

    public void setKiosk(int kiosk) {
        this.kiosk = kiosk;
    }

    public String getEnglishCityName() {
        return this.englishCityName;
    }

    public void setEnglishCityName(String englishCityName) {
        this.englishCityName = englishCityName;
    }

    public boolean isMcCafe() {
        return this.mcCafe == 1;
    }

    public void setMcCafe(int mcCafe) {
        this.mcCafe = mcCafe;
    }

    public boolean hasPlayground() {
        return this.playground == 1;
    }

    public void setPlayground(int playground) {
        this.playground = playground;
    }

    public String getChineseCityName() {
        return this.chineseCityName;
    }

    public void setChineseCityName(String chineseCityName) {
        this.chineseCityName = chineseCityName;
    }

    public double getLocationX() {
        return this.locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return this.locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public boolean isOpen24Hours() {
        return this.open24Hours == 1;
    }

    public void setOpen24Hours(int open24Hours) {
        this.open24Hours = open24Hours;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
