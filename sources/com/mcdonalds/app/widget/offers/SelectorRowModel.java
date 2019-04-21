package com.mcdonalds.app.widget.offers;

import com.ensighten.Ensighten;
import com.mcdonalds.app.widget.offers.OfferHomeItemModel.RowType;

public class SelectorRowModel extends OfferHomeItemModel {
    public RowType getItemType() {
        Ensighten.evaluateEvent(this, "getItemType", null);
        return RowType.Selector;
    }
}
