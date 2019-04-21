package com.mcdonalds.app.storelocator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StoreLocatorFiltersFragment extends URLNavigationFragment implements OnItemClickListener {
    public static final String NAME = StoreLocatorFiltersFragment.class.getCanonicalName();
    private List<String> filters = new ArrayList();
    private StoreLocatorController mController;
    private boolean mFiltersChanged = false;
    private FeatureFilterAdapter mListAdapter;
    private ListView mListView;
    private String mSearchText;

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment$1 */
    class C37451 implements AsyncListener<List<String>> {

        /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment$1$1 */
        class C37441 implements Runnable {
            C37441() {
            }

            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                for (int i = 0; i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$000", new Object[]{StoreLocatorFiltersFragment.this}).getCount(); i++) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$100", new Object[]{StoreLocatorFiltersFragment.this}).setItemChecked(i, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$200", new Object[]{StoreLocatorFiltersFragment.this}).getActiveFilters().contains(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$000", new Object[]{StoreLocatorFiltersFragment.this}).getItem(i)));
                }
            }
        }

        C37451() {
        }

        public void onResponse(List<String> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && StoreLocatorFiltersFragment.this.getNavigationActivity() != null) {
                StoreLocatorFiltersFragment.access$002(StoreLocatorFiltersFragment.this, new FeatureFilterAdapter());
                ArrayList<String> responseArrayList = new ArrayList(response);
                int indexToRemove = -1;
                Iterator it = responseArrayList.iterator();
                while (it.hasNext()) {
                    String s = (String) it.next();
                    if (!ModuleManager.isModuleEnabled("ordering").booleanValue() && s.toLowerCase().contains(StoreLocatorFiltersFragment.this.getString(C2658R.string.ordering))) {
                        indexToRemove = responseArrayList.indexOf(s);
                    }
                }
                if (indexToRemove != -1) {
                    responseArrayList.remove(indexToRemove);
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$000", new Object[]{StoreLocatorFiltersFragment.this}).addAll(responseArrayList);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$100", new Object[]{StoreLocatorFiltersFragment.this}).setAdapter(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$000", new Object[]{StoreLocatorFiltersFragment.this}));
                new Handler(Looper.getMainLooper()).post(new C37441());
            }
        }
    }

    private class FeatureFilterAdapter extends ArrayAdapter<String> {
        public FeatureFilterAdapter() {
            super(StoreLocatorFiltersFragment.this.getActivity(), C2658R.layout.storelocator_filters_list_item);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = ((LayoutInflater) StoreLocatorFiltersFragment.this.getActivity().getSystemService("layout_inflater")).inflate(C2658R.layout.storelocator_filters_list_item, parent, false);
            }
            DataLayerClickListener.setDataLayerTag(convertView, "CheckBoxItemPressed", position);
            ((TextView) convertView.findViewById(C2358R.C2357id.textView)).setText((String) getItem(position));
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    static /* synthetic */ FeatureFilterAdapter access$002(StoreLocatorFiltersFragment x0, FeatureFilterAdapter x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorFiltersFragment", "access$002", new Object[]{x0, x1});
        x0.mListAdapter = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_store_filters);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getActivity().getIntent().getExtras() != null) {
            Bundle extras = getActivity().getIntent().getExtras();
            this.mController = (StoreLocatorController) DataPasser.getInstance().getData(extras.getInt(URLNavigationActivity.CONTROLLER_PASSER_KEY));
            this.mSearchText = extras.getString("search_text");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(C2658R.layout.fragment_storelocator_filters, container, false);
        this.mListView = (ListView) vg.findViewById(16908298);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setChoiceMode(2);
        this.mListView.setItemsCanFocus(false);
        return vg;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.clear, menu);
        menu.findItem(C2358R.C2357id.action_clear).setVisible(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_clear /*2131821898*/:
                this.mController.getActiveFilters().clear();
                this.mListAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        if (this.mController != null) {
            this.mController.getStoreLocatorModule().getAvailableStoreFeatures(new C37451());
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Ensighten.evaluateEvent(this, "onItemClick", new Object[]{adapterView, view, new Integer(i), new Long(l)});
        String filter = (String) this.mListAdapter.getItem(i);
        this.mFiltersChanged = true;
        this.mController.setActiveFilters(new ArrayList(this.mController.getActiveFilters()));
        if (this.mController.getActiveFilters().contains(filter)) {
            this.mController.getActiveFilters().remove(filter);
            return;
        }
        this.mController.getActiveFilters().add(filter);
        Analytics.trackCustom(26, filter);
    }

    public void performNewLocationSearch() {
        Ensighten.evaluateEvent(this, "performNewLocationSearch", null);
        if (!this.mFiltersChanged) {
            return;
        }
        if (TextUtils.isEmpty(this.mSearchText)) {
            this.mController.updateStoresByLocation(true);
        } else {
            this.mController.requestUpdateStoresByAddress(this.mSearchText, true);
        }
    }
}
