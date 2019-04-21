package com.mcdonalds.app.widget.offers;

public abstract class OfferHomeItemModel {

    public enum RowType {
        Header,
        Selector,
        GridRow,
        ListRow
    }

    public abstract RowType getItemType();
}
