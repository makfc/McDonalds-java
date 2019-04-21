package com.mcdonalds.app.widget.offers;

import com.ensighten.Ensighten;
import com.mcdonalds.app.widget.offers.OfferHomeItemModel.RowType;
import com.mcdonalds.sdk.modules.models.Offer;

public class ListRowModel extends OfferHomeItemModel {
    private Offer mOffer;

    public ListRowModel(Offer offer) {
        this.mOffer = offer;
    }

    public Offer getOffer() {
        Ensighten.evaluateEvent(this, "getOffer", null);
        return this.mOffer;
    }

    public RowType getItemType() {
        Ensighten.evaluateEvent(this, "getItemType", null);
        return RowType.ListRow;
    }
}
