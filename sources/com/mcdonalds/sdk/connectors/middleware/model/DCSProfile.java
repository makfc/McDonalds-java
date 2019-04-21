package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.DCSAddress.DCSAddressDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSAddress.DCSAddressDetails.DCSAddressLineDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSFavorite.DCSFavoriteDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.EcpLegacyDCSPreferenceDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.PreferredOfferCategoryDCSPreferenceDetails;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DCSProfile {
    private static final String EMAIL_STATUS_ACTIVE = "Y";
    public static final String INDICATOR_FALSE = "N";
    public static final String INDICATOR_TRUE = "Y";
    public static final String KEY_NOTIFICATION_PREFERENCES = "modules.customer.register.notificationPreferences";
    public static final String KEY_REQUIRES_ACTIVATION = "requireActivation";
    private static final String STATUS_ACTIVE = "A";
    @SerializedName("audit")
    public DCSProfileAudit audit;
    @SerializedName("base")
    public DCSProfileBase base;
    @SerializedName("extended")
    public DCSProfileExtended extended;

    public class DCSProfileAudit {
        @SerializedName("createdBy")
        public String createdBy;
        @SerializedName("lastLoggedInAt")
        public String lastLoggedInAt;
        @SerializedName("registrationChannel")
        public String registrationChannel;
    }

    public static class DCSProfileBase {
        @SerializedName("activeInd")
        public String activeInd;
        @SerializedName("address")
        public List<DCSAddress> addresses;
        @SerializedName("displayName")
        public String displayName;
        @SerializedName("email")
        public List<DCSEmail> emails;
        @SerializedName("firstName")
        public String firstName;
        @SerializedName("lastName")
        public String lastName;
        @SerializedName("middleName")
        public String middleName;
        @SerializedName("phone")
        public List<DCSPhone> phones;
        @SerializedName("prefix")
        public String prefix;
        @SerializedName("shortName")
        public String shortName;
        @SerializedName("suffix")
        public String suffix;
        @SerializedName("username")
        public String username;
    }

    public static class DCSProfileExtended {
        @SerializedName("demographics")
        public DCSDemographics demographics;
        @SerializedName("devices")
        public List<DCSDevice> devices;
        @SerializedName("externalId")
        public List<DCSExternalId> externalId;
        @SerializedName("favorites")
        public List<DCSFavorite> favorites;
        @SerializedName("policies")
        public DCSPolicies policies;
        @SerializedName("preferences")
        public List<DCSPreference> preferences;
        @SerializedName("subscriptions")
        public List<DCSSubscription> subscriptions;

        public static class DCSDemographics {
            @SerializedName("ageRange")
            public String ageRange;
            @SerializedName("dob")
            public DCSDateOfBirth dateOfBirth;
            @SerializedName("ethnicity")
            public String ethnicity;
            @SerializedName("gender")
            public String gender;

            public static class DCSDateOfBirth {
                @SerializedName("birthDay")
                public String birthDay;
                @SerializedName("birthMonth")
                public String birthMonth;
                @SerializedName("birthYear")
                public String birthYear;
            }
        }

        public class DCSExternalId {
            @SerializedName("activeInd")
            public String activeInd;
            @SerializedName("appId")
            public String appId;
            @SerializedName("appName")
            public String appName;
        }

        public static class DCSPolicies {
            @SerializedName("acceptancePolicies")
            public List<DCSPolicy> acceptancePolicies;
            @SerializedName("accessPolicy")
            public List<DCSPolicy> accessPolicy;
        }
    }

    public static DCSProfile fromCustomerProfile(CustomerProfile customerProfile) {
        DCSProfile dcsProfile = new DCSProfile();
        DCSProfileBase base = new DCSProfileBase();
        base.username = customerProfile.getUserName();
        base.firstName = customerProfile.getFirstName();
        base.middleName = customerProfile.getMiddleName();
        base.lastName = customerProfile.getLastName();
        base.shortName = customerProfile.getNickName();
        base.activeInd = indicatorForBoolean(Boolean.valueOf(customerProfile.isActive()));
        base.addresses = new ArrayList();
        if (customerProfile.getZipCode() != null) {
            base.addresses.add(DCSAddress.fromZipCode(customerProfile.getZipCode()));
        }
        List<CustomerAddress> customerAddresses = customerProfile.getCustomerAddresses();
        if (!ListUtils.isEmpty(customerAddresses)) {
            for (CustomerAddress customerAddress : customerAddresses) {
                base.addresses.add(DCSAddress.fromCustomerAddress(customerAddress));
            }
        }
        DCSEmail email = new DCSEmail();
        email.emailAddress = customerProfile.getEmailAddress();
        boolean z = !Configuration.getSharedInstance().getBooleanForKey(KEY_REQUIRES_ACTIVATION) || customerProfile.isEmailActivated();
        email.activeInd = indicatorForBoolean(Boolean.valueOf(z));
        email.primaryInd = "Y";
        email.type = DCSEmail.TYPE_PERSONAL;
        base.emails = Collections.singletonList(email);
        if (customerProfile.getMobileNumber() != null) {
            DCSPhone phone = new DCSPhone();
            phone.type = DCSPhone.TYPE_CELL;
            phone.number = customerProfile.getMobileNumber();
            base.phones = Collections.singletonList(phone);
        }
        dcsProfile.base = base;
        DCSProfileExtended extended = new DCSProfileExtended();
        extended.devices = Collections.singletonList(DCSDevice.fromDeviceInfo());
        DCSPolicies policies = new DCSPolicies();
        policies.acceptancePolicies = new ArrayList();
        boolean hasPolicies = false;
        if (customerProfile.hasAcceptedTermsAndCondition()) {
            DCSPolicy termsAndConditions = new DCSPolicy();
            termsAndConditions.sourceId = DCSPolicy.DEFAULT_SOURCE_ID;
            termsAndConditions.name = DCSPolicy.TERMS_AND_CONDITIONS_NAME;
            termsAndConditions.type = "1";
            termsAndConditions.acceptanceInd = "Y";
            termsAndConditions.version = customerProfile.getmTermsAndConditionVersion();
            policies.acceptancePolicies.add(termsAndConditions);
            hasPolicies = true;
        }
        if (customerProfile.hasAcceptedPrivacyPolicy()) {
            DCSPolicy privacyPolicy = new DCSPolicy();
            privacyPolicy.sourceId = DCSPolicy.DEFAULT_SOURCE_ID;
            privacyPolicy.name = DCSPolicy.PRIVACY_POLICY_NAME;
            privacyPolicy.type = "2";
            privacyPolicy.acceptanceInd = "Y";
            privacyPolicy.version = customerProfile.getmPrivacyPolicyVersion();
            policies.acceptancePolicies.add(privacyPolicy);
            hasPolicies = true;
        }
        if (hasPolicies) {
            extended.policies = policies;
        }
        List<FavoriteItem> favoriteItems = customerProfile.getFavoriteItems();
        if (!ListUtils.isEmpty(customerProfile.getFavoriteItems())) {
            extended.favorites = new ArrayList();
            extended.favorites.addAll(DCSFavorite.fromFavoriteItems(favoriteItems));
        }
        if (!(customerProfile.getGender() == null || customerProfile.getEthnicity() == null || customerProfile.getAgeRange() == null || customerProfile.getBirthDate() == null)) {
            DCSDemographics demographics = new DCSDemographics();
            demographics.gender = customerProfile.getGender();
            demographics.ethnicity = customerProfile.getEthnicity();
            demographics.ageRange = customerProfile.getAgeRange();
            DCSDateOfBirth dcsDateOfBirth = new DCSDateOfBirth();
            dcsDateOfBirth.birthDay = String.valueOf(customerProfile.getBirthDate().get(5));
            dcsDateOfBirth.birthMonth = String.valueOf(customerProfile.getBirthDate().get(2));
            dcsDateOfBirth.birthYear = String.valueOf(customerProfile.getBirthDate().get(1));
            demographics.dateOfBirth = dcsDateOfBirth;
            extended.demographics = demographics;
        }
        List<DCSPreference> dcsPreferences = DCSPreference.createPreferenceList(customerProfile);
        if (!ListUtils.isEmpty(dcsPreferences)) {
            extended.preferences = dcsPreferences;
        }
        List<DCSSubscription> dcsSubscriptions = DCSSubscription.createSubscriptionList(customerProfile);
        if (!ListUtils.isEmpty(dcsSubscriptions)) {
            extended.subscriptions = dcsSubscriptions;
        }
        dcsProfile.extended = extended;
        return dcsProfile;
    }

    public CustomerProfile toCustomerProfile() {
        if (this.base == null) {
            return null;
        }
        Iterator it;
        Calendar calendar;
        String emailNotificationKey;
        String appNotificationKey;
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setUserName(this.base.username);
        customerProfile.setFirstName(this.base.firstName);
        customerProfile.setMiddleName(this.base.middleName);
        customerProfile.setLastName(this.base.lastName);
        customerProfile.setNickName(this.base.shortName);
        customerProfile.setIsActive(this.base.activeInd.equals(STATUS_ACTIVE));
        if (!ListUtils.isEmpty(this.base.addresses)) {
            DCSAddress zipCodeAddress = (DCSAddress) this.base.addresses.get(0);
            if (!ListUtils.isEmpty(zipCodeAddress.details)) {
                DCSAddressLineDetails addressLineDetails = ((DCSAddressDetails) zipCodeAddress.details.get(0)).addressLineDetails;
                if (!(addressLineDetails == null || addressLineDetails.zipCode == null)) {
                    customerProfile.setZipCode(addressLineDetails.zipCode);
                    this.base.addresses.remove(0);
                }
            }
            List<CustomerAddress> customerAddresses = new ArrayList();
            for (DCSAddress address : this.base.addresses) {
                customerAddresses.add(address.toCustomerAddress());
            }
            customerProfile.setCustomerAddresses(customerAddresses);
        }
        if (!ListUtils.isEmpty(this.base.emails)) {
            customerProfile.setEmailAddress(((DCSEmail) this.base.emails.get(0)).emailAddress);
            customerProfile.setEmailActivated(true);
        }
        if (!ListUtils.isEmpty(this.base.phones)) {
            for (DCSPhone phone : this.base.phones) {
                if (phone.type.equals(DCSPhone.TYPE_CELL)) {
                    customerProfile.setMobileNumber(phone.number);
                    break;
                }
            }
        }
        customerProfile.setCardItems(new ArrayList());
        DCSProfileExtended extendedCopy = this.extended;
        if (extendedCopy == null) {
            extendedCopy = new DCSProfileExtended();
        }
        List<DCSFavorite> favoritesCopy = extendedCopy.favorites;
        if (favoritesCopy == null) {
            favoritesCopy = new ArrayList();
        }
        List<FavoriteItem> favoriteItems = new ArrayList();
        List<String> favoriteStoreIds = new ArrayList();
        List<Store> favoriteStores = new ArrayList();
        for (DCSFavorite favorite : favoritesCopy) {
            if (favorite.type.equals(DCSFavorite.TYPE_ITEM) || favorite.type.equals(DCSFavorite.TYPE_ORDER)) {
                favoriteItems.addAll(favorite.toFavoriteItems());
            } else if (favorite.type.equals(DCSFavorite.TYPE_LOCATION)) {
                it = favorite.details.iterator();
                while (it.hasNext()) {
                    favoriteStoreIds.add(String.valueOf(((DCSFavoriteDetails) it.next()).storeNumber));
                    favoriteStores.addAll(favorite.toStores());
                }
            }
        }
        customerProfile.setFavoriteItems(favoriteItems);
        customerProfile.setFavoriteStoreIdList(favoriteStoreIds);
        customerProfile.setFavoriteStores(favoriteStores);
        if (this.extended.policies != null) {
            toTermsAndCondition(customerProfile);
            toPrivatePolicy(customerProfile);
        }
        if (this.extended.demographics != null) {
            customerProfile.setGender(this.extended.demographics.gender);
            customerProfile.setEthnicity(this.extended.demographics.ethnicity);
            customerProfile.setAgeRange(this.extended.demographics.ageRange);
            try {
                calendar = Calendar.getInstance();
                calendar.set(5, Integer.parseInt(this.extended.demographics.dateOfBirth.birthDay));
                calendar.set(2, Integer.parseInt(this.extended.demographics.dateOfBirth.birthMonth));
                calendar.set(1, Integer.parseInt(this.extended.demographics.dateOfBirth.birthYear));
                customerProfile.setBirthDate(calendar);
                customerProfile.setDayOfBirth(Integer.valueOf(Integer.parseInt(this.extended.demographics.dateOfBirth.birthDay)));
            } catch (NumberFormatException e) {
            }
        }
        DCSDemographics demographicsCopy = extendedCopy.demographics;
        if (demographicsCopy == null) {
            demographicsCopy = new DCSDemographics();
        }
        customerProfile.setGender(demographicsCopy.gender);
        customerProfile.setEthnicity(demographicsCopy.ethnicity);
        customerProfile.setAgeRange(demographicsCopy.ageRange);
        try {
            calendar = Calendar.getInstance();
            calendar.set(5, Integer.parseInt(demographicsCopy.dateOfBirth.birthDay));
            calendar.set(2, Integer.parseInt(demographicsCopy.dateOfBirth.birthMonth));
            calendar.set(1, Integer.parseInt(demographicsCopy.dateOfBirth.birthYear));
            customerProfile.setBirthDate(calendar);
            customerProfile.setDayOfBirth(Integer.valueOf(Integer.parseInt(demographicsCopy.dateOfBirth.birthDay)));
        } catch (NullPointerException | NumberFormatException e2) {
        }
        List<DCSPreference> preferencesCopy = extendedCopy.preferences;
        if (preferencesCopy == null) {
            preferencesCopy = new ArrayList();
        }
        NotificationPreferences notificationPreferences = new NotificationPreferences();
        for (DCSPreference dcsPreference : preferencesCopy) {
            String dcsPreferenceDesc = dcsPreference.preferenceDesc;
            emailNotificationKey = NotificationPreferences.KEY_EMAIL_PREFIX + dcsPreferenceDesc;
            appNotificationKey = NotificationPreferences.KEY_APP_PREFIX + dcsPreferenceDesc;
            if (dcsPreference.details instanceof EcpLegacyDCSPreferenceDetails) {
                EcpLegacyDCSPreferenceDetails details = dcsPreference.details;
                notificationPreferences.set(emailNotificationKey, DCSPreference.booleanFromLegacyStatus(details.email));
                notificationPreferences.set(appNotificationKey, DCSPreference.booleanFromLegacyStatus(details.mobileApp));
            } else if (dcsPreference.details instanceof PreferredOfferCategoryDCSPreferenceDetails) {
                PreferredOfferCategoryDCSPreferenceDetails details2 = dcsPreference.details;
                List<Integer> preferredOfferCategories = new ArrayList();
                for (String dcsPreferredOfferCategory : details2.mobileApp) {
                    preferredOfferCategories.add(Integer.valueOf(Integer.parseInt(dcsPreferredOfferCategory)));
                }
                customerProfile.setPreferredOfferCategories(preferredOfferCategories);
            } else {
                notificationPreferences.set(dcsPreferenceDesc, Boolean.valueOf(booleanForIndicator(dcsPreference.details.enabled)));
            }
        }
        List<DCSSubscription> subscriptionsCopy = extendedCopy.subscriptions;
        if (subscriptionsCopy == null) {
            subscriptionsCopy = new ArrayList();
        }
        emailNotificationKey = NotificationPreferences.KEY_EMAIL_ENABLED;
        appNotificationKey = NotificationPreferences.KEY_APP_ENABLED;
        for (DCSSubscription dcsSubscription : subscriptionsCopy) {
            String str = dcsSubscription.subscriptionId;
            Object obj = -1;
            switch (str.hashCode()) {
                case 49:
                    if (str.equals("1")) {
                        obj = null;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals("3")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 52:
                    if (str.equals("4")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 53:
                    if (str.equals("5")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 55:
                    if (str.equals("7")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 1567:
                    if (str.equals("10")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1568:
                    if (str.equals(DCSSubscription.ID_MOBILE_NOTIFICATION)) {
                        obj = 7;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    customerProfile.setOptInForCommunicationChannel(Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                case 1:
                    customerProfile.setOptInForSurveys(Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                case 2:
                    customerProfile.setOptInForProgramChanges(Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                case 3:
                    customerProfile.setOptInForContests(Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                case 4:
                    customerProfile.setOptInForOtherMarketingMessages(Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                case 5:
                    customerProfile.setSubscribedToOffers(booleanForIndicator(dcsSubscription.optInStatus));
                    break;
                case 6:
                    notificationPreferences.set(emailNotificationKey, Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                case 7:
                    notificationPreferences.set(appNotificationKey, Boolean.valueOf(booleanForIndicator(dcsSubscription.optInStatus)));
                    break;
                default:
                    break;
            }
        }
        customerProfile.setNotificationPreferences(notificationPreferences);
        return customerProfile;
    }

    private void toPrivatePolicy(CustomerProfile customerProfile) {
        if (!ListUtils.isEmpty(this.extended.policies.accessPolicy)) {
            for (DCSPolicy policy : this.extended.policies.accessPolicy) {
                if (policy.type.equals("2")) {
                    customerProfile.setHasAcceptedPrivacyPolicy(policy.acceptanceInd.equals("Y"));
                    customerProfile.setmPrivacyPolicyVersion(policy.version);
                    customerProfile.setPrivacyPolicyAcceptanceDate(policy.acceptanceDate);
                    customerProfile.setShouldUpdatePrivacyPolicy(policy.expired);
                    return;
                }
            }
        }
    }

    private void toTermsAndCondition(CustomerProfile customerProfile) {
        if (!ListUtils.isEmpty(this.extended.policies.acceptancePolicies)) {
            for (DCSPolicy policy : this.extended.policies.acceptancePolicies) {
                if (policy.type.equals("1")) {
                    customerProfile.setHasAcceptedTermsAndCondition(policy.acceptanceInd.equals("Y"));
                    customerProfile.setmTermsAndConditionVersion(policy.version);
                    customerProfile.setTermsAndConditionAcceptanceDate(policy.acceptanceDate);
                    customerProfile.setShouldUpdateTermsAndCondition(policy.expired);
                    return;
                }
            }
        }
    }

    public static DCSProfile difference(DCSProfile original, CustomerProfile customerProfile) {
        DCSProfile dcsProfile = new DCSProfile();
        DCSProfileBase base = new DCSProfileBase();
        if (original == null) {
            original = new DCSProfile();
        }
        if (original.base == null) {
            original.base = new DCSProfileBase();
        }
        base.username = stringOrNullIfEqual(original.base.username, customerProfile.getUserName());
        base.firstName = stringOrNullIfEqual(original.base.firstName, customerProfile.getFirstName());
        base.middleName = stringOrNullIfEqual(original.base.middleName, customerProfile.getMiddleName());
        base.lastName = stringOrNullIfEqual(original.base.lastName, customerProfile.getLastName());
        base.shortName = stringOrNullIfEqual(original.base.shortName, customerProfile.getNickName());
        boolean hasBase = (base.username == null && base.firstName == null && base.middleName == null && base.lastName == null && base.shortName == null) ? false : true;
        List<CustomerAddress> customerAddresses = customerProfile.getCustomerAddresses();
        List<DCSAddress> newDCSAddresses = new ArrayList();
        if (customerProfile.getZipCode() != null) {
            newDCSAddresses.add(DCSAddress.fromZipCode(customerProfile.getZipCode()));
        }
        if (!ListUtils.isEmpty(customerAddresses)) {
            for (CustomerAddress customerAddress : customerAddresses) {
                newDCSAddresses.add(DCSAddress.fromCustomerAddress(customerAddress));
            }
        }
        if (!ListUtils.isEmpty(newDCSAddresses)) {
            base.addresses = newDCSAddresses;
            hasBase = true;
        }
        if (customerProfile.getEmailAddress() != null) {
            String originalEmail = null;
            if (!ListUtils.isEmpty(original.base.emails)) {
                originalEmail = ((DCSEmail) original.base.emails.get(0)).emailAddress;
            }
            DCSEmail newEmail = new DCSEmail();
            newEmail.emailAddress = customerProfile.getEmailAddress();
            if (!newEmail.emailAddress.equals(originalEmail)) {
                boolean z = !Configuration.getSharedInstance().getBooleanForKey(KEY_REQUIRES_ACTIVATION) || customerProfile.isEmailActivated();
                newEmail.activeInd = indicatorForBoolean(Boolean.valueOf(z));
                newEmail.primaryInd = "Y";
                newEmail.type = DCSEmail.TYPE_PERSONAL;
                base.emails = Collections.singletonList(newEmail);
                hasBase = true;
            }
        }
        if (customerProfile.getMobileNumber() != null) {
            String originalPhone = null;
            if (!ListUtils.isEmpty(original.base.phones)) {
                originalPhone = ((DCSPhone) original.base.phones.get(0)).number;
            }
            DCSPhone phone = new DCSPhone();
            phone.number = customerProfile.getMobileNumber();
            if (!phone.number.equals(originalPhone)) {
                phone.type = DCSPhone.TYPE_CELL;
                base.phones = Collections.singletonList(phone);
                hasBase = true;
            }
        }
        if (hasBase) {
            dcsProfile.base = base;
        }
        DCSProfileExtended originalExtended = original.extended;
        DCSProfileExtended newExtended = new DCSProfileExtended();
        if (originalExtended == null) {
            originalExtended = new DCSProfileExtended();
        }
        DCSDevice device = DCSDevice.fromDeviceInfo();
        if (ListUtils.isEmpty(originalExtended.devices)) {
            newExtended.devices = Collections.singletonList(DCSDevice.fromDeviceInfo());
        } else {
            newExtended.devices = originalExtended.devices;
            newExtended.devices.remove(device);
            newExtended.devices.add(device);
        }
        List<FavoriteItem> favoriteItems = customerProfile.getFavoriteItems();
        if (!ListUtils.isEmpty(favoriteItems)) {
            List<DCSFavorite> dcsFavorites = originalExtended.favorites;
            List<DCSFavorite> newDCSFavorites = new ArrayList();
            newDCSFavorites.addAll(DCSFavorite.fromFavoriteItems(favoriteItems));
            if (!ListUtils.isEmpty(dcsFavorites)) {
                newDCSFavorites.removeAll(dcsFavorites);
            }
            if (!ListUtils.isEmpty(newDCSFavorites)) {
                newExtended.favorites = newDCSFavorites;
            }
        }
        if (!(customerProfile.getGender() == null && customerProfile.getEthnicity() == null && customerProfile.getAgeRange() == null && customerProfile.getBirthDate() == null)) {
            DCSDemographics newDCSDemographics = new DCSDemographics();
            DCSDemographics originalDemographics = originalExtended.demographics;
            if (originalDemographics == null) {
                originalDemographics = new DCSDemographics();
            }
            newDCSDemographics.gender = stringOrNullIfEqual(customerProfile.getGender(), originalDemographics.gender);
            newDCSDemographics.ethnicity = stringOrNullIfEqual(customerProfile.getEthnicity(), originalDemographics.ethnicity);
            newDCSDemographics.ageRange = stringOrNullIfEqual(customerProfile.getAgeRange(), originalDemographics.ageRange);
            if (customerProfile.getBirthDate() != null) {
                DCSDateOfBirth dcsDateOfBirth = new DCSDateOfBirth();
                DCSDateOfBirth originalDateOfBirth = originalDemographics.dateOfBirth;
                if (originalDateOfBirth == null) {
                    originalDateOfBirth = new DCSDateOfBirth();
                }
                dcsDateOfBirth.birthDay = stringOrNullIfEqual(String.valueOf(customerProfile.getBirthDate().get(5)), originalDateOfBirth.birthDay);
                dcsDateOfBirth.birthMonth = stringOrNullIfEqual(String.valueOf(customerProfile.getBirthDate().get(2)), originalDateOfBirth.birthMonth);
                dcsDateOfBirth.birthYear = stringOrNullIfEqual(String.valueOf(customerProfile.getBirthDate().get(1)), originalDateOfBirth.birthYear);
                newDCSDemographics.dateOfBirth = dcsDateOfBirth;
            }
            newExtended.demographics = newDCSDemographics;
        }
        List<DCSPreference> newPreferences = DCSPreference.createPreferenceList(customerProfile);
        List<DCSPreference> differencePreferences = new ArrayList();
        List<DCSPreference> originalPreferences = new ArrayList();
        if (!ListUtils.isEmpty(originalExtended.preferences)) {
            originalPreferences.addAll(originalExtended.preferences);
        }
        for (DCSPreference newPreference : newPreferences) {
            for (DCSPreference originalPreference : originalPreferences) {
                if (newPreference.preferenceId != null && newPreference.preferenceId.equals(originalPreference.preferenceId)) {
                    DCSPreference differencePreference = dcsPreferenceOrNullIfEquals(originalPreference, newPreference);
                    if (differencePreference != null) {
                        differencePreferences.add(differencePreference);
                    }
                }
            }
        }
        if (!ListUtils.isEmpty(differencePreferences)) {
            newExtended.preferences = differencePreferences;
        }
        List<DCSSubscription> newSubscriptions = DCSSubscription.createSubscriptionList(customerProfile);
        List<DCSSubscription> differenceSubscriptions = new ArrayList();
        List<DCSSubscription> originalSubscriptions = new ArrayList();
        if (!ListUtils.isEmpty(originalExtended.subscriptions)) {
            originalSubscriptions.addAll(originalExtended.subscriptions);
        }
        for (DCSSubscription newSubscription : newSubscriptions) {
            for (DCSSubscription originalSubscription : originalSubscriptions) {
                if (newSubscription.subscriptionId.equals(originalSubscription.subscriptionId)) {
                    DCSSubscription differenceSubscription = dcsSubscriptionOrNullIfEquals(originalSubscription, newSubscription);
                    if (differenceSubscription != null) {
                        differenceSubscriptions.add(differenceSubscription);
                    }
                }
            }
        }
        if (!ListUtils.isEmpty(differenceSubscriptions)) {
            newExtended.subscriptions = differenceSubscriptions;
        }
        dcsProfile.extended = newExtended;
        return dcsProfile;
    }

    private static String stringOrNullIfEqual(String oldString, String newString) {
        return (oldString == null || !oldString.equals(newString)) ? newString : null;
    }

    private static DCSPreference dcsPreferenceOrNullIfEquals(DCSPreference oldPreference, DCSPreference newPreference) {
        return (oldPreference == null || !oldPreference.equals(newPreference)) ? newPreference : null;
    }

    private static DCSSubscription dcsSubscriptionOrNullIfEquals(DCSSubscription oldSubscription, DCSSubscription newSubscription) {
        return (oldSubscription == null || !oldSubscription.equals(newSubscription)) ? newSubscription : null;
    }

    static String indicatorForBoolean(Boolean value) {
        if (value == null) {
            return null;
        }
        return value.booleanValue() ? "Y" : INDICATOR_FALSE;
    }

    static boolean booleanForIndicator(String value) {
        return value != null && value.equals("Y");
    }
}
