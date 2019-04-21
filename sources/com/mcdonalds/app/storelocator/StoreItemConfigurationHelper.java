package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider.OfferState;
import com.mcdonalds.gma.hongkong.C2658R;

class StoreItemConfigurationHelper {
    StoreItemConfigurationHelper() {
    }

    static void configureStoreItem(Context context, StoreItemViewHolder viewHolder, StoreItemViewState state, boolean hideOrderingWarning, boolean allowFavoritingWithoutMobileOrdering, boolean allowSelectingWithoutMobileOrdering, OfferState offerState) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreItemConfigurationHelper", "configureStoreItem", new Object[]{context, viewHolder, state, new Boolean(hideOrderingWarning), new Boolean(allowFavoritingWithoutMobileOrdering), new Boolean(allowSelectingWithoutMobileOrdering), offerState});
        viewHolder.getNotAuthorizedContainer().setVisibility(8);
        if (offerState != OfferState.INVALID_OFFER && offerState != OfferState.INVALID_PUNCHCARD_OFFER) {
            LinearLayout notAuthorizedContainer;
            int i;
            switch (state) {
                case Normal:
                case Favorite:
                    viewHolder.getMyMcDonaldsContainer().setVisibility(8);
                    if (hideOrderingWarning || allowSelectingWithoutMobileOrdering) {
                        viewHolder.getSelectStoreButton().setVisibility(0);
                        if (offerState == OfferState.VALID_OFFER) {
                            viewHolder.getSelectStoreButton().setText(C2658R.string.button_select);
                        }
                    } else {
                        viewHolder.getSelectStoreButton().setVisibility(8);
                    }
                    notAuthorizedContainer = viewHolder.getNotAuthorizedContainer();
                    if (hideOrderingWarning) {
                        i = 8;
                    } else {
                        i = 0;
                    }
                    notAuthorizedContainer.setVisibility(i);
                    viewHolder.getFavoritesContainer().setVisibility(8);
                    viewHolder.getCurrentStoreContainer().setVisibility(8);
                    break;
                case Current:
                case CurrentAndFavorite:
                    viewHolder.getSelectStoreButton().setVisibility(8);
                    viewHolder.getFavoritesContainer().setVisibility(8);
                    viewHolder.getCurrentStoreContainer().setVisibility(8);
                    notAuthorizedContainer = viewHolder.getNotAuthorizedContainer();
                    if (hideOrderingWarning) {
                        i = 8;
                    } else {
                        i = 0;
                    }
                    notAuthorizedContainer.setVisibility(i);
                    if (!hideOrderingWarning && !allowSelectingWithoutMobileOrdering) {
                        viewHolder.getMyMcDonaldsContainer().setVisibility(8);
                        break;
                    } else {
                        viewHolder.getMyMcDonaldsContainer().setVisibility(0);
                        break;
                    }
                case ExpandedPlaceOrder:
                case ExpandedFavoritePlaceOrder:
                    viewHolder.getMyMcDonaldsContainer().setVisibility(0);
                    viewHolder.getSelectStoreButton().setVisibility(8);
                    viewHolder.getNotAuthorizedContainer().setVisibility(8);
                    viewHolder.getFavoritesContainer().setVisibility(8);
                    viewHolder.getCurrentStoreContainer().setVisibility(0);
                    if (state != StoreItemViewState.ExpandedFavoritePlaceOrder) {
                        viewHolder.getSaveToFavoritesButton().setVisibility(0);
                        viewHolder.getRemoveFromFavorites().setVisibility(8);
                        break;
                    }
                    viewHolder.getSaveToFavoritesButton().setVisibility(8);
                    viewHolder.getRemoveFromFavorites().setVisibility(0);
                    break;
                case ExpandedNickname:
                    viewHolder.getMyMcDonaldsContainer().setVisibility(0);
                    viewHolder.getSelectStoreButton().setVisibility(8);
                    viewHolder.getFavoritesContainer().setVisibility(0);
                    viewHolder.getCurrentStoreContainer().setVisibility(8);
                    viewHolder.getSaveToFavoritesButton().setVisibility(8);
                    viewHolder.getRemoveFromFavorites().setVisibility(0);
                    break;
                case ExpandedSelectCurrent:
                    viewHolder.getMyMcDonaldsContainer().setVisibility(0);
                    viewHolder.getSelectStoreButton().setVisibility(8);
                    viewHolder.getNotAuthorizedContainer().setVisibility(8);
                    viewHolder.getFavoritesContainer().setVisibility(8);
                    viewHolder.getCurrentStoreContainer().setVisibility(0);
                    viewHolder.getSaveToFavoritesButton().setVisibility(0);
                    viewHolder.getRemoveFromFavorites().setVisibility(8);
                    break;
            }
        }
        CharSequence string;
        viewHolder.getNotAuthorizedContainer().setVisibility(0);
        TextView notAuthorizedLabel = viewHolder.getNotAuthorizedLabel();
        if (offerState == OfferState.INVALID_OFFER) {
            string = context.getString(C2658R.string.offer_not_available);
        } else {
            string = context.getString(C2658R.string.punchcard_offer_not_available);
        }
        notAuthorizedLabel.setText(string);
        viewHolder.getSelectStoreButton().setVisibility(8);
        viewHolder.getMyMcDonaldsContainer().setVisibility(8);
        viewHolder.getFavoritesContainer().setVisibility(8);
        viewHolder.getCurrentStoreContainer().setVisibility(8);
        if (offerState == OfferState.NO_OFFER) {
            viewHolder.getOffersStatusIcon().setVisibility(8);
        }
        if (allowFavoritingWithoutMobileOrdering) {
            if (state == StoreItemViewState.ExpandedFavoritePlaceOrder) {
                viewHolder.getSaveToFavoritesButton().setVisibility(8);
            } else {
                viewHolder.getSaveToFavoritesButton().setVisibility(0);
            }
        } else if (hideOrderingWarning && viewHolder.getRemoveFromFavorites().getVisibility() == 8) {
            viewHolder.getSaveToFavoritesButton().setVisibility(0);
        } else {
            viewHolder.getSaveToFavoritesButton().setVisibility(8);
        }
        if (!hideOrderingWarning) {
            viewHolder.getOrderHere().setVisibility(8);
        } else if (state != StoreItemViewState.ExpandedSelectCurrent) {
            viewHolder.getOrderHere().setVisibility(0);
        } else {
            viewHolder.getOrderHere().setVisibility(8);
        }
    }
}
