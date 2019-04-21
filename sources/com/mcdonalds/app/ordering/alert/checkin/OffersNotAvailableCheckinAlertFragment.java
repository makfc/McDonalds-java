package com.mcdonalds.app.ordering.alert.checkin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.alert.EditBasketAlertFragment;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import java.util.ArrayList;
import java.util.List;

public class OffersNotAvailableCheckinAlertFragment extends EditBasketAlertFragment {
    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_in_offers_not_valid;
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        TextView message = (TextView) view.findViewById(C2358R.C2357id.content_message);
        if (OrderingManager.getInstance().getProductErrorCode() == OrderResponse.OFFERS_ERROR_DELIVERY_ONLY) {
            message.setText(getString(C2658R.string.offer_unavailable_for_pod, getString(C2658R.string.delivery), getString(C2658R.string.pickup)));
        } else if (OrderingManager.getInstance().getProductErrorCode() == OrderResponse.OFFERS_ERROR_PICKUP_ONLY) {
            message.setText(getString(C2658R.string.offer_unavailable_for_pod, getString(C2658R.string.pickup), getString(C2658R.string.delivery)));
        }
        return view;
    }

    /* Access modifiers changed, original: protected */
    public List<OrderOffer> getProblematicOffers(List<String> problematicOffersCodes) {
        Ensighten.evaluateEvent(this, "getProblematicOffers", new Object[]{problematicOffersCodes});
        ArrayList<OrderOffer> offers = new ArrayList();
        if (problematicOffersCodes != null) {
            for (String offerCode : problematicOffersCodes) {
                for (OrderOffer offer : this.mOrder.getOffers()) {
                    if (offerCode.equals(Integer.toString(offer.getOffer().getOfferId().intValue()))) {
                        offers.add(offer);
                    }
                }
            }
        }
        return offers;
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        List<String> problematicOffersCodes = this.mBundle.getStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES);
        if (problematicOffersCodes != null) {
            for (OrderOffer offer : getProblematicOffers(problematicOffersCodes)) {
                if (this.mOrder.getOffers().contains(offer)) {
                    this.mOrder.removeOffer(offer);
                }
            }
        }
        OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        getActivity().setResult(-1);
        getActivity().finish();
    }
}
