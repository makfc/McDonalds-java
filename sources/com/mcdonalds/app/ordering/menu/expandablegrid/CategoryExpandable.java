package com.mcdonalds.app.ordering.menu.expandablegrid;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Product;
import java.util.List;

public class CategoryExpandable {
    private Category mCategory;
    private boolean mExpanded = false;
    private List<Product> mProducts;

    public List<Product> getChildItemList() {
        Ensighten.evaluateEvent(this, "getChildItemList", null);
        return this.mProducts;
    }

    public void setChildItemList(List<Product> products) {
        Ensighten.evaluateEvent(this, "setChildItemList", new Object[]{products});
        this.mProducts = products;
    }

    public CategoryExpandable getParentListItem() {
        Ensighten.evaluateEvent(this, "getParentListItem", null);
        return this;
    }

    public boolean isExpanded() {
        Ensighten.evaluateEvent(this, "isExpanded", null);
        return this.mExpanded;
    }

    public void setExpanded(boolean expanded) {
        Ensighten.evaluateEvent(this, "setExpanded", new Object[]{new Boolean(expanded)});
        this.mExpanded = expanded;
    }

    public boolean isInitiallyExpanded() {
        Ensighten.evaluateEvent(this, "isInitiallyExpanded", null);
        return false;
    }

    public Category getCategory() {
        Ensighten.evaluateEvent(this, "getCategory", null);
        return this.mCategory;
    }

    public void setCategory(Category mCategory) {
        Ensighten.evaluateEvent(this, "setCategory", new Object[]{mCategory});
        this.mCategory = mCategory;
    }
}
