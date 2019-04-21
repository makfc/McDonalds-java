package com.mcdonalds.app.widget.offers;

import com.ensighten.Ensighten;
import com.mcdonalds.app.widget.offers.OfferHomeItemModel.RowType;
import com.mcdonalds.sdk.modules.models.Offer;
import java.util.List;

public class GridRowModel extends OfferHomeItemModel {
    private List<Offer> mOffers;

    public GridRowModel(List<Offer> offers) {
        this.mOffers = offers;
    }

    public List<Offer> getOffers() {
        Ensighten.evaluateEvent(this, "getOffers", null);
        return this.mOffers;
    }

    public RowType getItemType() {
        Ensighten.evaluateEvent(this, "getItemType", null);
        return RowType.GridRow;
    }
}
