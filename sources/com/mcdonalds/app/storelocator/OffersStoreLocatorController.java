package com.mcdonalds.app.storelocator;

import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider.OfferState;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.io.Serializable;
import java.util.List;

public class OffersStoreLocatorController extends StoreLocatorController implements Serializable {
    private static int OFFER_UNAVAILABLE_MAP_ICON_RES_ID = 0;
    private static int OFFER_UNAVAILABLE_SELECTED_MAP_ICON_RES_ID = 0;
    private static int OFFER_VALID_MAP_ICON_RES_ID = 0;
    private static int OFFER_VALID_SELECTED_MAP_ICON_RES_ID = 0;
    private Store mInitialMarkerStore;
    private final Offer mOffer;
    private OfferSelection mOfferSelectionType;
    private List<Integer> mOfferStoresIds;

    public static class Builder {
        private boolean mAutoSelectClosestStore;
        private boolean mCurrentStoreSelectionMode;
        private Store mInitialMarkerStore;
        private boolean mIsMapOnly;
        private Offer mOffer;
        private OfferSelection mOfferSelectionType = OfferSelection.Nearby;
        private OffersStoreLocatorController mOffersStoreLocatorController;
        private boolean mShouldDismissOnPlaceOrder;
        private List<Integer> mStoreIdList;
        private URLNavigationFragment mUrlNavigationFragment;

        public Builder withUrlNavigationFragment(URLNavigationFragment urlNavigationFragment) {
            Ensighten.evaluateEvent(this, "withUrlNavigationFragment", new Object[]{urlNavigationFragment});
            this.mUrlNavigationFragment = urlNavigationFragment;
            return this;
        }

        public Builder isMapOnly(boolean isMapOnly) {
            Ensighten.evaluateEvent(this, "isMapOnly", new Object[]{new Boolean(isMapOnly)});
            this.mIsMapOnly = isMapOnly;
            return this;
        }

        public Builder hasCurrentStoreSelectionMode(boolean currentStoreSelectionMode) {
            Ensighten.evaluateEvent(this, "hasCurrentStoreSelectionMode", new Object[]{new Boolean(currentStoreSelectionMode)});
            this.mCurrentStoreSelectionMode = currentStoreSelectionMode;
            return this;
        }

        public Builder shouldAutoSelectClosestStore(boolean autoSelectClosestStore) {
            Ensighten.evaluateEvent(this, "shouldAutoSelectClosestStore", new Object[]{new Boolean(autoSelectClosestStore)});
            this.mAutoSelectClosestStore = autoSelectClosestStore;
            return this;
        }

        public Builder shouldDismissOnPlaceOrder(boolean shouldDismissOnPlaceOrder) {
            Ensighten.evaluateEvent(this, "shouldDismissOnPlaceOrder", new Object[]{new Boolean(shouldDismissOnPlaceOrder)});
            this.mShouldDismissOnPlaceOrder = shouldDismissOnPlaceOrder;
            return this;
        }

        public Builder withStoreIds(List<Integer> storeIdList) {
            Ensighten.evaluateEvent(this, "withStoreIds", new Object[]{storeIdList});
            this.mStoreIdList = storeIdList;
            return this;
        }

        public OffersStoreLocatorController create() {
            Ensighten.evaluateEvent(this, "create", null);
            this.mOffersStoreLocatorController = new OffersStoreLocatorController(this.mUrlNavigationFragment, this.mIsMapOnly, this.mCurrentStoreSelectionMode, this.mAutoSelectClosestStore, null, this.mShouldDismissOnPlaceOrder, this.mStoreIdList, this.mOfferSelectionType, this.mInitialMarkerStore, this.mOffer);
            return this.mOffersStoreLocatorController;
        }

        public Builder withOfferSelectionType(OfferSelection offerSelectionType) {
            Ensighten.evaluateEvent(this, "withOfferSelectionType", new Object[]{offerSelectionType});
            this.mOfferSelectionType = offerSelectionType;
            return this;
        }

        public Builder withTargetMarkerStore(Store initialMarkerStore) {
            Ensighten.evaluateEvent(this, "withTargetMarkerStore", new Object[]{initialMarkerStore});
            this.mInitialMarkerStore = initialMarkerStore;
            return this;
        }

        public Builder useOffer(Offer offer) {
            Ensighten.evaluateEvent(this, "useOffer", new Object[]{offer});
            this.mOffer = offer;
            return this;
        }
    }

    public enum OfferSelection {
        Current,
        Favorite,
        Nearby,
        FartherAway
    }

    public OffersStoreLocatorController(URLNavigationFragment urlNavigationFragment, boolean mapOnly, boolean currentStoreSelectionMode, boolean autoSelectClosestStore, String nearbySearchAddress, boolean dismissOnPlaceOrder, List<Integer> offerStoresId, OfferSelection offerSelectionType, Store initialMarkerStore, Offer offer) {
        super(urlNavigationFragment, mapOnly, currentStoreSelectionMode, autoSelectClosestStore, nearbySearchAddress, dismissOnPlaceOrder);
        this.mOfferStoresIds = offerStoresId;
        this.mOfferSelectionType = offerSelectionType;
        this.mInitialMarkerStore = initialMarkerStore;
        this.mOffer = offer;
        if (initialMarkerStore != null) {
            StoreLocatorSection section = StoreLocatorSection.Nearby;
            switch (offerSelectionType) {
                case Current:
                    section = StoreLocatorSection.Current;
                    break;
                case Favorite:
                    section = StoreLocatorSection.Favorites;
                    break;
            }
            selectStore(Integer.valueOf(this.mInitialMarkerStore.getStoreId()), section);
        }
    }

    public boolean isMapOnly() {
        Ensighten.evaluateEvent(this, "isMapOnly", null);
        return false;
    }

    public int getSelectMapPinResID(Integer storeId) {
        boolean isValid = false;
        Ensighten.evaluateEvent(this, "getSelectMapPinResID", new Object[]{storeId});
        if (getNearByStores() == null || getNearByStores().isEmpty()) {
            isValid = true;
        }
        if (isValid || getOfferState(storeId, StoreLocatorSection.Nearby) == OfferState.VALID_OFFER) {
            return getValidSelectedMapIconResID();
        }
        return getUnavailableSelectedMapIconResID();
    }

    public int getMapPinResID(Integer storeId) {
        boolean isValid = false;
        Ensighten.evaluateEvent(this, "getMapPinResID", new Object[]{storeId});
        if (getNearByStores() == null || getNearByStores().isEmpty()) {
            isValid = true;
        }
        if (isValid || getOfferState(storeId, StoreLocatorSection.Nearby) == OfferState.VALID_OFFER) {
            return getValidMapIconResID();
        }
        return getUnavailableMapIconResID();
    }

    public OfferState getOfferState(Integer storeId, StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "getOfferState", new Object[]{storeId, section});
        boolean isValid = false;
        if (storeId != null) {
            Store store = getStore(storeId, section);
            if (store == null) {
                store = findStoreById(storeId);
            }
            boolean storeSupportsOffers = store.hasMobileOffers();
            if (this.mOfferStoresIds == null || this.mOfferStoresIds.size() == 0) {
                if (storeSupportsOffers) {
                    isValid = true;
                }
            } else if (this.mOfferStoresIds.contains(storeId) && storeSupportsOffers) {
                isValid = true;
            }
        }
        if (isValid) {
            return OfferState.VALID_OFFER;
        }
        if (this.mOffer.isPunchCard()) {
            return OfferState.INVALID_PUNCHCARD_OFFER;
        }
        return OfferState.INVALID_OFFER;
    }

    private Store findStoreById(Integer storeID) {
        Ensighten.evaluateEvent(this, "findStoreById", new Object[]{storeID});
        Store ret = getStore(storeID, StoreLocatorSection.Nearby);
        if (ret == null) {
            ret = getStore(storeID, StoreLocatorSection.Favorites);
        }
        if (ret == null) {
            return getStore(storeID, StoreLocatorSection.Current);
        }
        return ret;
    }

    private int getValidSelectedMapIconResID() {
        Ensighten.evaluateEvent(this, "getValidSelectedMapIconResID", null);
        if (OFFER_VALID_SELECTED_MAP_ICON_RES_ID == 0) {
            OFFER_VALID_SELECTED_MAP_ICON_RES_ID = getDrawableResIDByString("interface.storelocator.offerValidSelectedMapIcon", "offer_pin_yellow_outline");
        }
        return OFFER_VALID_SELECTED_MAP_ICON_RES_ID;
    }

    private int getValidMapIconResID() {
        Ensighten.evaluateEvent(this, "getValidMapIconResID", null);
        if (OFFER_VALID_MAP_ICON_RES_ID == 0) {
            OFFER_VALID_MAP_ICON_RES_ID = getDrawableResIDByString("interface.storelocator.offerValidUnselectedMapIcon", "offer_pin_yellow");
        }
        return OFFER_VALID_MAP_ICON_RES_ID;
    }

    private int getUnavailableSelectedMapIconResID() {
        Ensighten.evaluateEvent(this, "getUnavailableSelectedMapIconResID", null);
        if (OFFER_UNAVAILABLE_SELECTED_MAP_ICON_RES_ID == 0) {
            OFFER_UNAVAILABLE_SELECTED_MAP_ICON_RES_ID = getDrawableResIDByString("interface.storelocator.offerUnavailableSelectedMapIcon", "pin_gray_outline");
        }
        return OFFER_UNAVAILABLE_SELECTED_MAP_ICON_RES_ID;
    }

    private int getUnavailableMapIconResID() {
        Ensighten.evaluateEvent(this, "getUnavailableMapIconResID", null);
        if (OFFER_UNAVAILABLE_MAP_ICON_RES_ID == 0) {
            OFFER_UNAVAILABLE_MAP_ICON_RES_ID = getDrawableResIDByString("interface.storelocator.offerUnavailableUnselectedMapIcon", "pin_gray");
        }
        return OFFER_UNAVAILABLE_MAP_ICON_RES_ID;
    }

    /* Access modifiers changed, original: protected */
    public void updateStoresByLocation(boolean showActivityIndicator) {
        Ensighten.evaluateEvent(this, "updateStoresByLocation", new Object[]{new Boolean(showActivityIndicator)});
        if (this.mOfferSelectionType == OfferSelection.Current || this.mOfferSelectionType == OfferSelection.Favorite) {
            requestUpdateStoresByCurrentStore(false);
        } else {
            super.updateStoresByLocation(showActivityIndicator);
        }
    }

    public Store getSelectedStore() {
        Ensighten.evaluateEvent(this, "getSelectedStore", null);
        if (getSelectedStoreId() == null) {
            return this.mInitialMarkerStore;
        }
        return super.getSelectedStore();
    }
}
