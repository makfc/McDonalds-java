package com.mcdonalds.app.ordering.menu;

import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask.BuildProductMapParams;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import java.util.List;
import java.util.Map;

public class MenuGridAdapterFilter extends Filter {
    private MenuGridAdapter mAdapter;
    private TextView mNoItemFound;
    private BuildProductMapParams mParams;

    public static class ProductsFilterResults {
        protected Map<Category, List<Product>> productMap;
    }

    public MenuGridAdapterFilter(BuildProductMapParams params, MenuGridAdapter adapter, TextView noItemFound) {
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
        Ensighten.evaluateEvent(this, "publishResults", new Object[]{charSequence, filterResults});
        ProductsFilterResults results = filterResults.values;
        if (this.mAdapter != null && results != null) {
            this.mAdapter.setProductMap(results.productMap);
            if (results.productMap.size() == 0) {
                this.mNoItemFound.setVisibility(0);
            } else {
                this.mNoItemFound.setVisibility(8);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int getCount(Map<Category, List<Product>> productMap) {
        Ensighten.evaluateEvent(this, "getCount", new Object[]{productMap});
        int result = 0;
        if (productMap != null) {
            for (List<Product> productList : productMap.values()) {
                if (productList != null) {
                    result += productList.size();
                }
            }
        }
        return result;
    }
}
