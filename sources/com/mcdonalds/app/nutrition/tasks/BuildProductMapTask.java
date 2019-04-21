package com.mcdonalds.app.nutrition.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BuildProductMapTask extends AsyncTask<BuildProductMapParams, Void, Map<Category, List<Product>>> implements TraceFieldInterface {
    public Trace _nr_trace;
    private BuildProductMapListener listener = null;

    /* renamed from: com.mcdonalds.app.nutrition.tasks.BuildProductMapTask$1 */
    class C33171 implements Comparator<Category> {
        C33171() {
        }

        public int compare(Category lhs, Category rhs) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{lhs, rhs});
            return lhs.getDisplayOrder() - rhs.getDisplayOrder();
        }
    }

    public interface BuildProductMapListener {
        void onBuildProductMapComplete(Map<Category, List<Product>> map);
    }

    public static class BuildProductMapParams {
        public Context context;
        public int currentMenuPartId;
        public boolean enableMultipleMenuTypes;
        public String pod;
        public String searchQuery;

        public boolean isValid() {
            Ensighten.evaluateEvent(this, "isValid", null);
            return this.context != null;
        }
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    public void setListener(BuildProductMapListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.listener = listener;
    }

    /* Access modifiers changed, original: protected|varargs */
    public Map<Category, List<Product>> doInBackground(BuildProductMapParams... params) {
        Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
        Map<Category, List<Product>> productMap = new LinkedHashMap();
        if (params.length != 1 || !params[0].isValid()) {
            return productMap;
        }
        BuildProductMapParams mapParams = params[0];
        if (mapParams.searchQuery == null) {
            mapParams.searchQuery = "";
        }
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        LinkedHashMap<Category, List<Product>> orderedProductMap = null;
        if (OrderManager.getInstance().getCurrentStore() != null) {
            Map<Category, List<Product>> unorderedProductMap = orderingModule.getProductsForOrdering(mapParams.pod, mapParams.currentMenuPartId, mapParams.searchQuery);
            if (!Configuration.getSharedInstance().getBooleanForKey("interface.useDisplayOrderExclusivelyForProductCategorySort")) {
                return unorderedProductMap;
            }
            List<Category> categories = new ArrayList(unorderedProductMap.keySet());
            Collections.sort(categories, new C33171());
            orderedProductMap = new LinkedHashMap();
            for (Category category : categories) {
                orderedProductMap.put(category, (List) unorderedProductMap.get(category));
            }
        }
        return orderedProductMap;
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Map<Category, List<Product>> productMap) {
        Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{productMap});
        super.onPostExecute(productMap);
        if (this.listener != null) {
            this.listener.onBuildProductMapComplete(productMap);
            releaseListener();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled() {
        Ensighten.evaluateEvent(this, "onCancelled", null);
        super.onCancelled();
        releaseListener();
    }

    private void releaseListener() {
        Ensighten.evaluateEvent(this, "releaseListener", null);
        this.listener = null;
    }
}
