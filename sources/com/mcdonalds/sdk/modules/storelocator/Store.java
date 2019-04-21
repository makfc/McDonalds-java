package com.mcdonalds.sdk.modules.storelocator;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.model.MWLocation;
import com.mcdonalds.sdk.connectors.middleware.model.MWOpeningHourItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWPointOfDistributionItem;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.TableService;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.utils.ListUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Store extends AppModel implements Parcelable, Cloneable {
    public static final Creator<Store> CREATOR = new C40951();
    private List<MWPointOfDistributionItem> PODs;
    private String mAddress1;
    private String mAddress2;
    private int mAdvancedOrderMaximumTimeLimitMinutes;
    private int mAdvancedOrderMinimumTimeLimitMinutes;
    private boolean mBagChargeEnabled;
    private int mBagProductCode;
    private String mCity;
    private String mCountry;
    private String mCurrentStoreStatus;
    private int mDayPart;
    private double mDistance;
    private String mExpectedDeliveryTime;
    private String mExpectedDeliveryTimeReference;
    private String mExternalStoreNumber;
    private List<String> mFacilityNames;
    private String mGBLNumber;
    private String mGeneralStatus;
    private boolean mGeneralStatusIsOpen = true;
    private boolean mHasMobileOffers;
    private boolean mHasMobileOrdering;
    private boolean mLargeOrderAllowed;
    private double mLatitude;
    private List<MWLocation> mLocations;
    private double mLongitude;
    private int mMaxAdvanceOrderTime;
    private List<MenuTypeCalendarItem> mMenuTypeCalendar;
    private int mMinAdvanceOrderTime;
    private double mMinimumOrderValue;
    private String mNPVersion;
    private int mNoBagProductCode;
    private String mNowInStoreLocalTime;
    private List<String> mOutageProductCodes;
    private String mPhoneNumber;
    private String mPostalCode;
    private String mPublicName;
    private String mState;
    private String mStoreCurrentTiming;
    private String mStoreCutoffTime;
    private Integer mStoreFavoriteId;
    private String mStoreFavoriteName;
    private List<String> mStoreHours;
    private int mStoreId;
    private List<String> mStoreLongestHour;
    private List<String[]> mStoreLongestOperatingHours;
    private boolean mStoreOpen;
    private List<String[]> mStoreOperatingHours;
    private String mStoreType;
    private String mStoreURL;
    private String mTODCutoffTime;
    private TableService mTableService;
    private long mTimeDifference;
    private int mTimeZone;
    private List<PODLocation> podLocation;

    /* renamed from: com.mcdonalds.sdk.modules.storelocator.Store$1 */
    static class C40951 implements Creator<Store> {
        C40951() {
        }

        public Store createFromParcel(Parcel source) {
            return new Store(source);
        }

        public Store[] newArray(int size) {
            return new Store[size];
        }
    }

    public Store(Store s) {
        this.mStoreId = s.getStoreId();
        this.mLatitude = s.getLatitude();
        this.mLongitude = s.getLongitude();
        this.mDistance = s.getDistance();
        this.mAddress1 = s.getAddress1();
        this.mAddress2 = s.getAddress2();
        this.mCity = s.getCity();
        this.mState = s.getState();
        this.mPostalCode = s.getPostalCode();
        this.mCountry = s.getCountry();
        this.mStoreType = s.getStoreType();
        this.mStoreURL = s.getStoreURL();
        this.mPhoneNumber = s.getPhoneNumber();
        this.mStoreHours = s.getStoreHours();
        this.mStoreLongestHour = s.getStoreLongestHours();
        this.mStoreOperatingHours = s.getStoreOperatingHours();
        this.mStoreLongestOperatingHours = s.getStoreLongestOperatingHours();
        this.mMenuTypeCalendar = s.getMenuTypeCalendar();
        this.mTimeDifference = s.getTimeDifference();
        this.mGBLNumber = s.getGBLNumber();
        this.mMaxAdvanceOrderTime = s.getMaxAdvanceOrderTime();
        this.mMinAdvanceOrderTime = s.getMinAdvanceOrderTime();
        List<String> facilityNames = new ArrayList();
        if (s.getFacilityNames() != null) {
            List<String> names = s.getFacilityNames();
            int size = names.size();
            for (int i = 0; i < size; i++) {
                facilityNames.add(names.get(i));
            }
            setFacilityNames(facilityNames);
        }
        this.mLocations = s.getLocations();
        this.mOutageProductCodes = s.getOutageProducts();
        this.mBagChargeEnabled = s.isBagChargeEnabled();
        this.mBagProductCode = s.getBagProductCode();
        this.mNoBagProductCode = s.getNoBagProductCode();
        this.mTableService = s.getTableService();
        this.mGeneralStatusIsOpen = s.isGeneralStatusIsOpen();
        this.mStoreCurrentTiming = s.getStoreCurrentTiming();
        this.PODs = s.getPODs();
        this.mPublicName = s.mPublicName;
        this.mHasMobileOrdering = s.hasMobileOrdering().booleanValue();
    }

    public Location toLocation() {
        Location location = new Location("mcdonalds");
        location.setLatitude(getLatitude());
        location.setLongitude(getLongitude());
        return location;
    }

    public Store clone() {
        try {
            return (Store) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public int getStoreId() {
        return this.mStoreId;
    }

    public void setStoreId(int storeId) {
        this.mStoreId = storeId;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public String getStoreCurrentTiming() {
        return this.mStoreCurrentTiming;
    }

    public void setStoreCurrentTiming(String mStoreCurrentTiming) {
        this.mStoreCurrentTiming = mStoreCurrentTiming;
    }

    public double getDistance() {
        return this.mDistance;
    }

    public void setDistance(double distance) {
        this.mDistance = distance;
    }

    public String getAddress1() {
        return this.mAddress1;
    }

    public void setAddress1(String address1) {
        this.mAddress1 = address1;
    }

    public String getAddress2() {
        return this.mAddress2;
    }

    public void setAddress2(String address2) {
        this.mAddress2 = address2;
    }

    public String getCurrentStoreStatus() {
        return this.mCurrentStoreStatus;
    }

    public void setCurrentStoreStatus(String mStoreStatus) {
        this.mCurrentStoreStatus = mStoreStatus;
    }

    public String getGeneralStatus() {
        return this.mGeneralStatus;
    }

    public void setGeneralStatus(String mGeneralStatus) {
        this.mGeneralStatus = mGeneralStatus;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getState() {
        return this.mState;
    }

    public void setState(String state) {
        this.mState = state;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public void setPostalCode(String postalCode) {
        this.mPostalCode = postalCode;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }

    public String getStoreType() {
        return this.mStoreType;
    }

    public void setStoreType(String storeType) {
        this.mStoreType = storeType;
    }

    public String getStoreURL() {
        return this.mStoreURL;
    }

    public void setStoreURL(String storeURL) {
        this.mStoreURL = storeURL;
    }

    public List<MenuTypeCalendarItem> getMenuTypeCalendar() {
        return this.mMenuTypeCalendar;
    }

    public void setMenuTypeCalendar(List<MenuTypeCalendarItem> menuTypeCalendarItems) {
        this.mMenuTypeCalendar = menuTypeCalendarItems;
    }

    public long getTimeDifference() {
        return this.mTimeDifference;
    }

    public void setTimeDifference(long mTimeDifference) {
        this.mTimeDifference = mTimeDifference;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public boolean canBeFavorited() {
        return hasMobileOffers() || hasMobileOrdering().booleanValue();
    }

    public boolean hasMobileOffers() {
        return this.mHasMobileOffers;
    }

    public void setHasMobileOffers(Boolean hasMobileOffers) {
        this.mHasMobileOffers = hasMobileOffers.booleanValue();
    }

    public Boolean hasMobileOrdering() {
        return Boolean.valueOf(this.mHasMobileOrdering);
    }

    public void setHasMobileOrdering(Boolean hasMobileOrdering) {
        this.mHasMobileOrdering = hasMobileOrdering.booleanValue();
    }

    public String getStoreFavoriteName() {
        return this.mStoreFavoriteName;
    }

    public void setStoreFavoriteName(String storeFavoriteName) {
        this.mStoreFavoriteName = storeFavoriteName;
    }

    public String getStoreName() {
        return getStoreFavoriteName() != null ? getStoreFavoriteName() : getAddress1();
    }

    public String getExpectedDeliveryTime() {
        return this.mExpectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.mExpectedDeliveryTime = expectedDeliveryTime;
    }

    public void setNowInStoreLocalTime(String nowInStoreLocalTime) {
        this.mNowInStoreLocalTime = nowInStoreLocalTime;
    }

    public String getNowInStoreLocalTime() {
        return this.mNowInStoreLocalTime;
    }

    public String getExpectedDeliveryTimeReference() {
        return this.mExpectedDeliveryTimeReference;
    }

    public void setExpectedDeliveryTimeReference(String expectedDeliveryTimeReference) {
        this.mExpectedDeliveryTimeReference = expectedDeliveryTimeReference;
    }

    public void setExpectedDeliveryTimeReference(Date expectedDeliveryTimeReference) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        setExpectedDeliveryTimeReference(df.format(expectedDeliveryTimeReference));
    }

    public boolean isStoreOpen() {
        return this.mStoreOpen;
    }

    public void setStoreOpen(boolean storeOpen) {
        this.mStoreOpen = storeOpen;
    }

    public boolean isGeneralStatusIsOpen() {
        return this.mGeneralStatusIsOpen;
    }

    public void setGeneralStatusIsOpen(boolean generalStatusIsOpen) {
        this.mGeneralStatusIsOpen = generalStatusIsOpen;
    }

    private StoreDayPartRange getBusinessDay(Calendar date) {
        boolean isSingleDay = isSingleWeekDay();
        int weekDay = getDayOfWeek(date);
        StoreDayPartRange result = new StoreDayPartRange(date);
        if (this.mMenuTypeCalendar != null) {
            int size = this.mMenuTypeCalendar.size();
            for (int i = 0; i < size; i++) {
                MenuTypeCalendarItem item = (MenuTypeCalendarItem) this.mMenuTypeCalendar.get(i);
                if (isSingleDay || item.getWeekDay() == weekDay) {
                    result.expandRange(item.getFromTime(), item.getToTime());
                }
            }
        }
        return result;
    }

    /* Access modifiers changed, original: protected */
    public void setStoreHours() {
        boolean useEcpOpeningHours;
        this.mStoreOperatingHours = getStoreOperatingHours();
        List<MenuTypeCalendarItem> menuTypeCalendarItems = new ArrayList();
        if (!AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES) || this.mMenuTypeCalendar == null) {
            useEcpOpeningHours = true;
        } else {
            useEcpOpeningHours = false;
        }
        if (this.mStoreOperatingHours != null) {
            for (String[] todaysOperatingHours : this.mStoreOperatingHours) {
                String fromTime = todaysOperatingHours[0];
                String toTime = todaysOperatingHours[1];
                if (useEcpOpeningHours) {
                    MenuTypeCalendarItem item = new MenuTypeCalendarItem();
                    item.setToTime(toTime);
                    item.setFromTime(fromTime);
                    menuTypeCalendarItems.add(item);
                }
            }
        }
        if (!(useEcpOpeningHours || this.mMenuTypeCalendar == null)) {
            menuTypeCalendarItems.addAll(this.mMenuTypeCalendar);
        }
        setMenuTypeCalendar(menuTypeCalendarItems);
    }

    public boolean isStoreClosed() {
        if (this.mMenuTypeCalendar == null) {
            setStoreHours();
        }
        Calendar date = Calendar.getInstance();
        StoreDayPartRange bizDay = getBusinessDay(date);
        if (date.before(bizDay.from)) {
            Calendar prevDay = Calendar.getInstance();
            prevDay.setTime(date.getTime());
            prevDay.add(5, -1);
            if (date.after(getBusinessDay(prevDay).f6684to)) {
                return true;
            }
            return false;
        } else if (date.before(bizDay.f6684to)) {
            return false;
        } else {
            return true;
        }
    }

    public int getDayPart() {
        return this.mDayPart;
    }

    public void setDayPart(int dayPart) {
        this.mDayPart = dayPart;
    }

    public String getTODCutoffTime() {
        return this.mTODCutoffTime;
    }

    public void setTODCutoffTime(String TODCutoffTime) {
        this.mTODCutoffTime = TODCutoffTime;
    }

    public String getStoreCutoffTime() {
        return this.mStoreCutoffTime;
    }

    public void setStoreCutoffTime(String storeCutoffTime) {
        this.mStoreCutoffTime = storeCutoffTime;
    }

    public boolean isLargeOrderAllowed() {
        return this.mLargeOrderAllowed;
    }

    public void setLargeOrderAllowed(boolean largeOrderAllowed) {
        this.mLargeOrderAllowed = largeOrderAllowed;
    }

    public String getNPVersion() {
        return this.mNPVersion;
    }

    public void setNPVersion(String NPVersion) {
        this.mNPVersion = NPVersion;
    }

    public double getMinimumOrderValue() {
        return this.mMinimumOrderValue;
    }

    public void setMinimumOrderValue(double minimumOrderValue) {
        this.mMinimumOrderValue = minimumOrderValue;
    }

    public String getExternalStoreNumber() {
        return this.mExternalStoreNumber;
    }

    public void setExternalStoreNumber(String externalStoreNumber) {
        this.mExternalStoreNumber = externalStoreNumber;
    }

    public List<String> getStoreHours() {
        if (this.mStoreHours == null) {
            this.mStoreHours = new ArrayList();
        }
        return this.mStoreHours;
    }

    public List<String> getStoreLongestHours() {
        if (this.mStoreLongestHour == null) {
            this.mStoreLongestHour = new ArrayList();
        }
        return this.mStoreLongestHour;
    }

    public List<String[]> getStoreOperatingHours() {
        if (this.mStoreOperatingHours == null) {
            this.mStoreOperatingHours = new ArrayList();
        }
        return this.mStoreOperatingHours;
    }

    public List<String[]> getStoreLongestOperatingHours() {
        if (this.mStoreLongestOperatingHours == null) {
            this.mStoreLongestOperatingHours = new ArrayList();
        }
        return this.mStoreLongestOperatingHours;
    }

    public long getMenuEndingTime(MenuType menuType) {
        return getMenuEndingTime(menuType, false);
    }

    public long getMenuEndingTime(MenuType menuType, boolean isDelivery) {
        MenuTypeCalendarItem menuTypeCalendarItem = getMenuTypeCalendarItem(menuType.getID(), isDelivery);
        boolean isSupportedMenuName = menuType.getShortName().toLowerCase().contains("breakfast") || menuType.getShortName().toLowerCase().contains("lunch") || menuType.getShortName().toLowerCase().contains("regular") || menuType.getShortName().toLowerCase().contains("midnight") || menuType.getShortName().contains("早餐") || menuType.getShortName().contains("正餐") || menuType.getShortName().contains("晚餐");
        if (!isSupportedMenuName || menuTypeCalendarItem == null || menuType.getID() != getCurrentMenuTypeID(isDelivery)) {
            return 0;
        }
        try {
            Date currentStoreTime = StoreDayPartRange.getCurrentTime(Calendar.getInstance());
            Date fromTime = StoreDayPartRange.parseFromTimeString(menuTypeCalendarItem.getFromTime());
            Date toTime = StoreDayPartRange.parseToTimeString(menuTypeCalendarItem.getToTime());
            if (toTime.before(fromTime)) {
                if (currentStoreTime.before(toTime)) {
                    currentStoreTime = addOneDay(currentStoreTime);
                }
                toTime = addOneDay(toTime);
            }
            if (currentStoreTime.after(fromTime) && currentStoreTime.before(toTime)) {
                return (toTime.getTime() - currentStoreTime.getTime()) / 60000;
            }
            return 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setStoreHours(List<String> mStoreHours) {
        this.mStoreHours = mStoreHours;
    }

    public void setmStoreLongestHour(List<String> mStoreHours) {
        this.mStoreLongestHour = mStoreHours;
    }

    public void setStoreOperatingHours(List<String[]> storeOperatingHours) {
        this.mStoreOperatingHours = storeOperatingHours;
    }

    public void setmStoreLongestOperatingHours(List<String[]> storeOperatingHours) {
        this.mStoreLongestOperatingHours = storeOperatingHours;
    }

    public Integer getStoreFavoriteId() {
        return this.mStoreFavoriteId;
    }

    public void setStoreFavoriteId(Integer storeFavoriteId) {
        this.mStoreFavoriteId = storeFavoriteId;
    }

    public void setFacilityNames(List<String> facilityNames) {
        this.mFacilityNames = facilityNames;
    }

    public List<String> getFacilityNames() {
        return this.mFacilityNames;
    }

    public int getMaxAdvanceOrderTime() {
        return this.mMaxAdvanceOrderTime;
    }

    public void setMaxAdvanceOrderTime(int maxAdvanceOrderTime) {
        this.mMaxAdvanceOrderTime = maxAdvanceOrderTime;
    }

    public int getMinAdvanceOrderTime() {
        return this.mMinAdvanceOrderTime;
    }

    public void setMinAdvanceOrderTime(int minAdvanceOrderTime) {
        this.mMinAdvanceOrderTime = minAdvanceOrderTime;
    }

    public String toString() {
        return "Store{mStoreId='" + this.mStoreId + '\'' + ", mLatitude=" + this.mLatitude + ", mLongitude=" + this.mLongitude + ", mDistance=" + this.mDistance + ", mAddress1='" + this.mAddress1 + '\'' + ", mAddress2='" + this.mAddress2 + '\'' + ", mCity='" + this.mCity + '\'' + ", mState='" + this.mState + '\'' + ", mPostalCode='" + this.mPostalCode + '\'' + ", mCountry='" + this.mCountry + '\'' + ", mStoreType='" + this.mStoreType + '\'' + ", mStoreURL='" + this.mStoreURL + '\'' + ", mPhoneNumber='" + this.mPhoneNumber + '\'' + ", mHasMobileOffers=" + this.mHasMobileOffers + ", mHasMobileOrdering=" + this.mHasMobileOrdering + ", mFacilityNames=" + this.mFacilityNames + ", mStoreHours=" + this.mStoreHours + ", mMenuTypeCalendar=" + this.mMenuTypeCalendar + ", mStoreOperatingHours=" + this.mStoreOperatingHours + ", mTimeDifference=" + this.mTimeDifference + ", mExpectedDeliveryTime='" + this.mExpectedDeliveryTime + '\'' + ", mStoreOpen=" + this.mStoreOpen + ", mDayPart=" + this.mDayPart + ", mTODCutoffTime='" + this.mTODCutoffTime + '\'' + ", mStoreCutoffTime='" + this.mStoreCutoffTime + '\'' + ", mLargeOrderAllowed=" + this.mLargeOrderAllowed + ", mNPVersion='" + this.mNPVersion + '\'' + ", mMinimumOrderValue=" + this.mMinimumOrderValue + ", mExternalStoreNumber='" + this.mExternalStoreNumber + '\'' + ", mStoreFavoriteName='" + this.mStoreFavoriteName + '\'' + ", mStoreFavoriteId=" + this.mStoreFavoriteId + ", PODs=" + this.PODs + ", mMaxAdvanceOrderTime=" + this.mMaxAdvanceOrderTime + ", mMinAdvanceOrderTime=" + this.mMinAdvanceOrderTime + ", mTimeZone=" + this.mTimeZone + ", mAdvancedOrderMinimumTimeLimitMinutes=" + this.mAdvancedOrderMinimumTimeLimitMinutes + ", mAdvancedOrderMaximumTimeLimitMinutes=" + this.mAdvancedOrderMaximumTimeLimitMinutes + ", mGBLNumber='" + this.mGBLNumber + '\'' + ", mBagChargeEnabled=" + this.mBagChargeEnabled + ", mBagProductCode=" + this.mBagProductCode + ", mNoBagProductCode=" + this.mNoBagProductCode + ", mLocations=" + this.mLocations + ", mTableService" + this.mTableService + ", mGeneralStatusIsOpen" + this.mGeneralStatusIsOpen + '}';
    }

    public String getName() {
        if (this.mStoreFavoriteId != null) {
            return this.mStoreFavoriteName;
        }
        return this.mAddress1;
    }

    public MenuTypeCalendarItem getMenuTypeCalendarItem(int menuTypeId) {
        return getMenuTypeCalendarItem(menuTypeId, false);
    }

    public MenuTypeCalendarItem getMenuTypeCalendarItem(int menuTypeId, boolean isDelivery) {
        Calendar date = Calendar.getInstance();
        boolean isSingleDay = isSingleWeekDay();
        int weekDay = getDayOfWeek(date);
        int prevWeekDay = getPreviousDayOfWeek(weekDay);
        try {
            Date currentTime = StoreDayPartRange.getCurrentTime(date);
            if (this.mMenuTypeCalendar != null) {
                int size = this.mMenuTypeCalendar.size();
                for (int i = 0; i < size; i++) {
                    MenuTypeCalendarItem item = (MenuTypeCalendarItem) this.mMenuTypeCalendar.get(i);
                    if (item.getMenuTypeId() == menuTypeId) {
                        try {
                            Date from = StoreDayPartRange.parseFromTimeString(item.getFromTime());
                            Date to = StoreDayPartRange.parseToTimeString(item.getToTime());
                            if (isSingleDay || ((prevWeekDay == item.getWeekDay() && !to.before(currentTime)) || weekDay == item.getWeekDay())) {
                                return adjustMenuTypeBasedOnLocations(item, isDelivery);
                            }
                        } catch (ParseException e) {
                            MCDLog.error("DAYPART", "Error while trying to parse the from and/or to hours of the store day part (getMenuTypeCalendarItem)");
                            return null;
                        }
                    }
                }
            }
            return null;
        } catch (ParseException e2) {
            return null;
        }
    }

    private MenuTypeCalendarItem adjustMenuTypeBasedOnLocations(MenuTypeCalendarItem item, boolean isDelivery) {
        try {
            Date from = StoreDayPartRange.parseFromTimeString(item.getFromTime());
            Date to = StoreDayPartRange.parseToTimeString(item.getToTime());
            if (to.before(from)) {
                to = addOneDay(to);
            }
            List<MWOpeningHourItem> openingHours = null;
            List<MWLocation> locations = getLocations();
            MWLocation location;
            int j;
            if (locations != null && !isDelivery) {
                location = null;
                for (j = 0; j < locations.size(); j++) {
                    if (((MWLocation) locations.get(j)).getLocationID() == 2) {
                        location = (MWLocation) locations.get(j);
                        break;
                    }
                }
                if (location != null) {
                    openingHours = location.getStoreAreaOpeningHours();
                }
            } else if (locations != null) {
                location = null;
                for (j = 0; j < locations.size(); j++) {
                    if (((MWLocation) locations.get(j)).getLocationID() == 4) {
                        location = (MWLocation) locations.get(j);
                        break;
                    }
                }
                if (location != null) {
                    openingHours = location.getStoreAreaOpeningHours();
                }
            }
            if (!ListUtils.isEmpty(openingHours)) {
                MWOpeningHourItem openingHour = (MWOpeningHourItem) openingHours.get(item.getWeekDay());
                try {
                    Date fromTime = StoreDayPartRange.parseToTimeString(openingHour.fromTime);
                    Date toTime = StoreDayPartRange.parseToTimeString(openingHour.toTime);
                    if (toTime.before(from)) {
                        toTime = addOneDay(toTime);
                    }
                    if (!isWholeDay(fromTime, toTime)) {
                        int breakfastMenuTypeId = !AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES) ? 0 : 1;
                        int regularMenuTypeId = !AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES) ? 1 : 2;
                        if (item.getMenuTypeId() == breakfastMenuTypeId) {
                            if (fromTime.after(from)) {
                                from = fromTime;
                            }
                        } else if (item.getMenuTypeId() == regularMenuTypeId && toTime.before(to)) {
                            to = toTime;
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (isDelivery) {
                from = getDateWithOffset(from);
                to = getDateWithOffset(to);
            }
            MenuTypeCalendarItem resultMenuTypeCalendarItem = new MenuTypeCalendarItem();
            resultMenuTypeCalendarItem.setFromTime(StoreDayPartRange.formatTimeString(from));
            resultMenuTypeCalendarItem.setToTime(StoreDayPartRange.formatTimeString(to));
            resultMenuTypeCalendarItem.setWeekDay(item.getWeekDay());
            resultMenuTypeCalendarItem.setMenuTypeId(item.getMenuTypeId());
            return resultMenuTypeCalendarItem;
        } catch (ParseException e2) {
            MCDLog.error("DAYPART", "Error while trying to parse the from and/or to hours of the store day part (getMenuTypeCalendarItem)");
            return null;
        }
    }

    public int getCurrentMenuTypeID() {
        return getCurrentMenuTypeID(false);
    }

    public int getCurrentMenuTypeID(boolean isDelivery) {
        MenuTypeCalendarItem currentMenuTypeCalendarItem = getCurrentMenuTypeCalendarItem(isDelivery);
        if (currentMenuTypeCalendarItem != null) {
            return currentMenuTypeCalendarItem.getMenuTypeId();
        }
        return -1;
    }

    public MenuTypeCalendarItem getCurrentMenuTypeCalendarItem(boolean isDelivery) {
        Calendar current = Calendar.getInstance();
        current.setTime(((OrderingModule) ModuleManager.getModule("ordering")).getOrderTime());
        return getCurrentMenuTypeCalendarItem(isDelivery, current);
    }

    public String getGBLNumber() {
        return this.mGBLNumber;
    }

    public void setGBLNumber(String mGBLNumber) {
        this.mGBLNumber = mGBLNumber;
    }

    /* JADX WARNING: Missing block: B:15:0x004b, code skipped:
            if (r9.before(r0) == false) goto L_0x0059;
     */
    private com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem getCurrentMenuTypeCalendarItem(boolean r14, java.util.Calendar r15) {
        /*
        r13 = this;
        r11 = r13.mMenuTypeCalendar;
        if (r11 == 0) goto L_0x00c3;
    L_0x0004:
        r10 = r13.getDayOfWeek(r15);	 Catch:{ ParseException -> 0x00c0 }
        r7 = r13.getPreviousDayOfWeek(r10);	 Catch:{ ParseException -> 0x00c0 }
        r4 = r13.isSingleWeekDay();	 Catch:{ ParseException -> 0x00c0 }
        r0 = com.mcdonalds.sdk.modules.storelocator.StoreDayPartRange.getCurrentTime(r15);	 Catch:{ ParseException -> 0x00c0 }
        r3 = 0;
        r11 = r13.mMenuTypeCalendar;	 Catch:{ ParseException -> 0x00c0 }
        r8 = r11.size();	 Catch:{ ParseException -> 0x00c0 }
    L_0x001b:
        if (r3 >= r8) goto L_0x00c3;
    L_0x001d:
        r11 = r13.mMenuTypeCalendar;	 Catch:{ ParseException -> 0x00c0 }
        r6 = r11.get(r3);	 Catch:{ ParseException -> 0x00c0 }
        r6 = (com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem) r6;	 Catch:{ ParseException -> 0x00c0 }
        r11 = r6.getFromTime();	 Catch:{ ParseException -> 0x0090 }
        r2 = com.mcdonalds.sdk.modules.storelocator.StoreDayPartRange.parseFromTimeString(r11);	 Catch:{ ParseException -> 0x0090 }
        r11 = r6.getToTime();	 Catch:{ ParseException -> 0x0090 }
        r9 = com.mcdonalds.sdk.modules.storelocator.StoreDayPartRange.parseToTimeString(r11);	 Catch:{ ParseException -> 0x0090 }
        if (r14 == 0) goto L_0x003f;
    L_0x0037:
        r2 = r13.getDateWithOffset(r2);	 Catch:{ ParseException -> 0x0090 }
        r9 = r13.getDateWithOffset(r9);	 Catch:{ ParseException -> 0x0090 }
    L_0x003f:
        if (r4 != 0) goto L_0x0059;
    L_0x0041:
        r11 = r6.getWeekDay();	 Catch:{ ParseException -> 0x00c0 }
        if (r7 != r11) goto L_0x004d;
    L_0x0047:
        r11 = r9.before(r0);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 == 0) goto L_0x0059;
    L_0x004d:
        r11 = r6.getWeekDay();	 Catch:{ ParseException -> 0x00c0 }
        if (r10 != r11) goto L_0x00bc;
    L_0x0053:
        r11 = r2.after(r0);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 != 0) goto L_0x00bc;
    L_0x0059:
        r5 = r13.adjustMenuTypeBasedOnLocations(r6, r14);	 Catch:{ ParseException -> 0x00c0 }
        if (r5 == 0) goto L_0x009a;
    L_0x005f:
        r11 = r5.getFromTime();	 Catch:{ ParseException -> 0x00c0 }
        r2 = com.mcdonalds.sdk.modules.storelocator.StoreDayPartRange.parseFromTimeString(r11);	 Catch:{ ParseException -> 0x00c0 }
        r11 = r5.getToTime();	 Catch:{ ParseException -> 0x00c0 }
        r9 = com.mcdonalds.sdk.modules.storelocator.StoreDayPartRange.parseToTimeString(r11);	 Catch:{ ParseException -> 0x00c0 }
        r11 = r9.before(r2);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 == 0) goto L_0x0083;
    L_0x0075:
        r11 = r0.before(r9);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 == 0) goto L_0x007f;
    L_0x007b:
        r0 = r13.addOneDay(r0);	 Catch:{ ParseException -> 0x00c0 }
    L_0x007f:
        r9 = r13.addOneDay(r9);	 Catch:{ ParseException -> 0x00c0 }
    L_0x0083:
        r11 = r2.after(r0);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 != 0) goto L_0x00bc;
    L_0x0089:
        r11 = r9.before(r0);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 != 0) goto L_0x00bc;
    L_0x008f:
        return r5;
    L_0x0090:
        r1 = move-exception;
        r11 = "DAYPART";
        r12 = "Error while trying to parse the from and/or to hours of the store day part (getCurrentMenuTypeCalendarItem)";
        com.mcdonalds.sdk.services.log.MCDLog.error(r11, r12);	 Catch:{ ParseException -> 0x00c0 }
        r5 = 0;
        goto L_0x008f;
    L_0x009a:
        r11 = r9.before(r2);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 == 0) goto L_0x00ae;
    L_0x00a0:
        r11 = r0.before(r9);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 == 0) goto L_0x00aa;
    L_0x00a6:
        r0 = r13.addOneDay(r0);	 Catch:{ ParseException -> 0x00c0 }
    L_0x00aa:
        r9 = r13.addOneDay(r9);	 Catch:{ ParseException -> 0x00c0 }
    L_0x00ae:
        r11 = r2.after(r0);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 != 0) goto L_0x00bc;
    L_0x00b4:
        r11 = r9.before(r0);	 Catch:{ ParseException -> 0x00c0 }
        if (r11 != 0) goto L_0x00bc;
    L_0x00ba:
        r5 = r6;
        goto L_0x008f;
    L_0x00bc:
        r3 = r3 + 1;
        goto L_0x001b;
    L_0x00c0:
        r1 = move-exception;
        r5 = 0;
        goto L_0x008f;
    L_0x00c3:
        r5 = 0;
        goto L_0x008f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.sdk.modules.storelocator.Store.getCurrentMenuTypeCalendarItem(boolean, java.util.Calendar):com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem");
    }

    private Date getDateWithOffset(Date originalDate) {
        String offsetString = AppParameters.getAppParameter(AppParameters.DELIVERY_DAY_PART_OFFSET_IN_MINUTES);
        if (TextUtils.isEmpty(offsetString)) {
            return originalDate;
        }
        return new Date(originalDate.getTime() - ((long) ((Integer.parseInt(offsetString) * 60) * 1000)));
    }

    private Date addOneDay(Date original) {
        return new Date(original.getTime() + 86400000);
    }

    private boolean isWholeDay(Date from, Date to) {
        if (to.before(from)) {
            to = addOneDay(to);
        }
        if (to.getTime() - from.getTime() > 86339999) {
            return true;
        }
        return false;
    }

    public int getDayOfWeek(Calendar date) {
        switch (date.get(7)) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            default:
                return 0;
        }
    }

    public int getPreviousDayOfWeek(int dayOfWeek) {
        dayOfWeek--;
        if (dayOfWeek < 0) {
            return 6;
        }
        return dayOfWeek;
    }

    private boolean isSingleWeekDay() {
        if (this.mMenuTypeCalendar != null) {
            int size = this.mMenuTypeCalendar.size();
            for (int i = 0; i < size; i++) {
                if (((MenuTypeCalendarItem) this.mMenuTypeCalendar.get(i)).getWeekDay() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Date getCurrentDate() {
        return new Date(Calendar.getInstance().getTimeInMillis());
    }

    public void setPODs(List<MWPointOfDistributionItem> PODs) {
        this.PODs = PODs;
    }

    public List<MWPointOfDistributionItem> getPODs() {
        return this.PODs;
    }

    public void mergeWithEcpInfo(Store ecpStore) {
        if (ecpStore != null) {
            setPODs(ecpStore.getPODs());
            setMenuTypeCalendar(ecpStore.getMenuTypeCalendar());
            setStoreHours(ecpStore.getStoreHours());
            setStoreOperatingHours(ecpStore.getStoreOperatingHours());
            setTimeDifference(ecpStore.getTimeDifference());
            setLocations(ecpStore.getLocations());
            setOutageProducts(ecpStore.getOutageProducts());
            setBagChargeEnabled(ecpStore.isBagChargeEnabled());
            setBagProductCode(ecpStore.getBagProductCode());
            setNoBagProductCode(ecpStore.getNoBagProductCode());
            setTableService(ecpStore.getTableService());
            if (TextUtils.isEmpty(getAddress1())) {
                setAddress1(ecpStore.getAddress1());
            }
            if (TextUtils.isEmpty(getAddress2())) {
                setAddress2(ecpStore.getAddress2());
            }
            if (TextUtils.isEmpty(getCity())) {
                setCity(ecpStore.getCity());
            }
            if (TextUtils.isEmpty(getState())) {
                setState(ecpStore.getState());
            }
            if (TextUtils.isEmpty(getPostalCode())) {
                setPostalCode(ecpStore.getPostalCode());
            }
            if (TextUtils.isEmpty(getCountry())) {
                setCountry(ecpStore.getCountry());
            }
        }
    }

    public int getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(int mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public int getAdvancedOrderMaximumTimeLimitMinutes() {
        return this.mAdvancedOrderMaximumTimeLimitMinutes;
    }

    public void setAdvancedOrderMaximumTimeLimitMinutes(int mAdvancedOrderMaximumTimeLimitMinutes) {
        this.mAdvancedOrderMaximumTimeLimitMinutes = mAdvancedOrderMaximumTimeLimitMinutes;
    }

    public int getAdvancedOrderMinimumTimeLimitMinutes() {
        return this.mAdvancedOrderMinimumTimeLimitMinutes;
    }

    public void setAdvancedOrderMinimumTimeLimitMinutes(int mAdvancedOrderMinimumTimeLimitMinutes) {
        this.mAdvancedOrderMinimumTimeLimitMinutes = mAdvancedOrderMinimumTimeLimitMinutes;
    }

    public boolean isBagChargeEnabled() {
        return this.mBagChargeEnabled;
    }

    public void setBagChargeEnabled(boolean mBagChargeEnabled) {
        this.mBagChargeEnabled = mBagChargeEnabled;
    }

    public int getBagProductCode() {
        return this.mBagProductCode;
    }

    public void setBagProductCode(int mBagProductCode) {
        this.mBagProductCode = mBagProductCode;
    }

    public int getNoBagProductCode() {
        return this.mNoBagProductCode;
    }

    public void setNoBagProductCode(int mNoBagProductCode) {
        this.mNoBagProductCode = mNoBagProductCode;
    }

    public TableService getTableService() {
        return this.mTableService;
    }

    public void setTableService(TableService tableService) {
        this.mTableService = tableService;
    }

    public int getCurrentMenuTypeIDAtStore() {
        MenuTypeCalendarItem currentMenuTypeCalendarItem = getCurrentMenuTypeCalItemWithTimeZone();
        if (currentMenuTypeCalendarItem != null) {
            return currentMenuTypeCalendarItem.getMenuTypeId();
        }
        return -1;
    }

    public Date getCurrentDateAtStoreTimeZone() {
        return new Date((Calendar.getInstance().getTimeInMillis() + this.mTimeDifference) + ((long) ((this.mTimeZone * 60) * 1000)));
    }

    public MenuTypeCalendarItem getCurrentMenuTypeCalItemWithTimeZone() {
        Date currentDate = getCurrentDate();
        Calendar current = Calendar.getInstance();
        current.setTime(currentDate);
        return getCurrentMenuTypeCalendarItem(false, current);
    }

    public void setLocations(List<MWLocation> locations) {
        this.mLocations = locations;
    }

    public List<MWLocation> getLocations() {
        return this.mLocations;
    }

    public void setOutageProducts(List<String> outageProducts) {
        this.mOutageProductCodes = outageProducts;
    }

    public List<String> getOutageProducts() {
        return this.mOutageProductCodes;
    }

    public void setPublicName(String publicName) {
        this.mPublicName = publicName;
    }

    public String getPublicName() {
        return this.mPublicName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Store store = (Store) o;
        if (this.mStoreId == store.mStoreId && this.mLatitude == store.mLatitude && this.mLongitude == store.mLongitude && this.mHasMobileOffers == store.mHasMobileOffers && this.mHasMobileOrdering == store.mHasMobileOrdering) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mStoreId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeInt(this.mStoreId);
        dest.writeDouble(this.mLatitude);
        dest.writeDouble(this.mLongitude);
        dest.writeDouble(this.mDistance);
        dest.writeString(this.mAddress1);
        dest.writeString(this.mAddress2);
        dest.writeString(this.mCity);
        dest.writeString(this.mState);
        dest.writeString(this.mPostalCode);
        dest.writeString(this.mCountry);
        dest.writeString(this.mStoreType);
        dest.writeString(this.mStoreURL);
        dest.writeString(this.mPhoneNumber);
        dest.writeByte(this.mHasMobileOffers ? (byte) 1 : (byte) 0);
        if (this.mHasMobileOrdering) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeStringList(this.mFacilityNames);
        dest.writeStringList(this.mStoreHours);
        dest.writeStringList(this.mStoreLongestHour);
        dest.writeList(this.mMenuTypeCalendar);
        dest.writeLong(this.mTimeDifference);
        dest.writeString(this.mExpectedDeliveryTime);
        dest.writeString(this.mExpectedDeliveryTimeReference);
        if (this.mStoreOpen) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeInt(this.mDayPart);
        dest.writeString(this.mTODCutoffTime);
        dest.writeString(this.mStoreCutoffTime);
        if (this.mLargeOrderAllowed) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeString(this.mNPVersion);
        dest.writeDouble(this.mMinimumOrderValue);
        dest.writeString(this.mExternalStoreNumber);
        dest.writeString(this.mStoreFavoriteName);
        dest.writeValue(this.mStoreFavoriteId);
        dest.writeList(this.PODs);
        dest.writeInt(this.mMaxAdvanceOrderTime);
        dest.writeInt(this.mMinAdvanceOrderTime);
        if (this.mHasMobileOffers) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeInt(this.mTimeZone);
        dest.writeList(this.mLocations);
        dest.writeStringList(this.mOutageProductCodes);
        if (this.mBagChargeEnabled) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeInt(this.mBagProductCode);
        dest.writeInt(this.mNoBagProductCode);
        dest.writeParcelable(this.mTableService, 0);
        if (!this.mGeneralStatusIsOpen) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
        dest.writeString(this.mCurrentStoreStatus);
        dest.writeString(this.mGeneralStatus);
        dest.writeList(this.mStoreOperatingHours);
        dest.writeList(this.mStoreLongestOperatingHours);
        dest.writeString(this.mStoreCurrentTiming);
        dest.writeString(this.mPublicName);
    }

    protected Store(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mStoreId = in.readInt();
        this.mLatitude = in.readDouble();
        this.mLongitude = in.readDouble();
        this.mDistance = in.readDouble();
        this.mAddress1 = in.readString();
        this.mAddress2 = in.readString();
        this.mCity = in.readString();
        this.mState = in.readString();
        this.mPostalCode = in.readString();
        this.mCountry = in.readString();
        this.mStoreType = in.readString();
        this.mStoreURL = in.readString();
        this.mPhoneNumber = in.readString();
        this.mHasMobileOffers = in.readByte() != (byte) 0;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mHasMobileOrdering = z;
        this.mFacilityNames = in.createStringArrayList();
        this.mStoreHours = in.createStringArrayList();
        this.mStoreLongestHour = in.createStringArrayList();
        this.mMenuTypeCalendar = new ArrayList();
        in.readList(this.mMenuTypeCalendar, MenuTypeCalendarItem.class.getClassLoader());
        this.mTimeDifference = in.readLong();
        this.mExpectedDeliveryTime = in.readString();
        this.mExpectedDeliveryTimeReference = in.readString();
        this.mStoreOpen = in.readByte() != (byte) 0;
        this.mDayPart = in.readInt();
        this.mTODCutoffTime = in.readString();
        this.mStoreCutoffTime = in.readString();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mLargeOrderAllowed = z;
        this.mNPVersion = in.readString();
        this.mMinimumOrderValue = in.readDouble();
        this.mExternalStoreNumber = in.readString();
        this.mStoreFavoriteName = in.readString();
        this.mStoreFavoriteId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.PODs = new ArrayList();
        in.readList(this.PODs, MWPointOfDistributionItem.class.getClassLoader());
        this.mMaxAdvanceOrderTime = in.readInt();
        this.mMinAdvanceOrderTime = in.readInt();
        this.mHasMobileOffers = in.readByte() != (byte) 0;
        this.mTimeZone = in.readInt();
        this.mLocations = new ArrayList();
        in.readList(this.mLocations, MWLocation.class.getClassLoader());
        this.mOutageProductCodes = in.createStringArrayList();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mBagChargeEnabled = z;
        this.mBagProductCode = in.readInt();
        this.mNoBagProductCode = in.readInt();
        this.mTableService = (TableService) in.readParcelable(TableService.class.getClassLoader());
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mGeneralStatusIsOpen = z2;
        this.mCurrentStoreStatus = in.readString();
        this.mGeneralStatus = in.readString();
        this.mStoreOperatingHours = new ArrayList();
        in.readList(this.mStoreOperatingHours, String[].class.getClassLoader());
        this.mStoreLongestOperatingHours = new ArrayList();
        in.readList(this.mStoreLongestOperatingHours, String[].class.getClassLoader());
        this.mStoreCurrentTiming = in.readString();
        this.mPublicName = in.readString();
    }

    public void setPodLocation(List<PODLocation> podLocation) {
        this.podLocation = podLocation;
    }

    public List<PODLocation> getPodLocations() {
        return this.podLocation;
    }
}
