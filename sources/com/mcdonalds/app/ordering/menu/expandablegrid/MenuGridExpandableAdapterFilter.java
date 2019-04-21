package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.text.TextUtils;
import android.widget.Filter.FilterResults;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask.BuildProductMapParams;
import com.mcdonalds.app.ordering.menu.MenuGridAdapterFilter;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import java.util.List;
import java.util.Map;

public class MenuGridExpandableAdapterFilter extends MenuGridAdapterFilter {
    private MenuGridExpandableAdapter mAdapter;
    private TextView mNoItemFound;
    private BuildProductMapParams mParams;

    public static class ProductsFilterResults {
        protected Map<Category, List<Product>> productMap;
    }

    public MenuGridExpandableAdapterFilter(BuildProductMapParams params, MenuGridExpandableAdapter adapter, TextView noItemFound) {
        super(params, null, noItemFound);
        this.mParams = params;
        this.mAdapter = adapter;
        this.mNoItemFound = noItemFound;
    }

    /* Access modifiers changed, original: protected */
    public FilterResults performFiltering(CharSequence charSequence) {
        Ensighten.evaluateEvent(this, "performFiltering", new Object[]{charSequence});
        this.mParams.searchQuery = charSequence.toString();
        ProductsFilterResults holder = new ProductsFilterResults();
        FilterResults results = new FilterResults();
        holder.productMap = ((OrderingModule) ModuleManager.getModule("ordering")).getProductsForOrdering(this.mParams.pod, this.mParams.currentMenuPartId, charSequence.toString());
        results.values = holder;
        results.count = getCount(holder.productMap);
        return results;
    }

    /* Access modifiers changed, original: protected */
    public void publishResults(CharSequence charSequence, FilterResults filterResults) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "publishResults", new Object[]{charSequence, filterResults});
        ProductsFilterResults results = filterResults.values;
        MenuGridExpandableAdapter menuGridExpandableAdapter = this.mAdapter;
        if (TextUtils.isEmpty(charSequence)) {
            z = false;
        }
        menuGridExpandableAdapter.setFilterFlag(z);
        this.mAdapter.setProductMap(results.productMap);
        if (results.productMap.size() == 0) {
            this.mNoItemFound.setVisibility(0);
        } else {
            this.mNoItemFound.setVisibility(8);
        }
    }
}
