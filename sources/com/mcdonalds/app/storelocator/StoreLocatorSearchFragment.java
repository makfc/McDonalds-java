package com.mcdonalds.app.storelocator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StoreLocatorSearchFragment extends URLNavigationFragment {
    ArrayAdapter<String> mAdapter;
    private ImageView mCheckedImageView;
    private StoreLocatorController mController;
    private ListView mListView;
    private StoreLocatorDataProvider mProvider;
    private List<String> mSearchList = new ArrayList();

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorSearchFragment$1 */
    class C37691 implements OnClickListener {
        C37691() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorSearchFragment", "access$000", new Object[]{StoreLocatorSearchFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorSearchFragment", "access$000", new Object[]{StoreLocatorSearchFragment.this}).displayFilters(((StoreLocatorContainerFragment) StoreLocatorSearchFragment.this.getParentFragment()).getSearchView().getText());
            }
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorSearchFragment$2 */
    class C37702 implements OnItemClickListener {
        C37702() {
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
            ((StoreLocatorContainerFragment) StoreLocatorSearchFragment.this.getParentFragment()).getSearchView().setText((String) StoreLocatorSearchFragment.this.mAdapter.getItem(position));
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateList();
        if (this.mController == null) {
            setController(((StoreLocatorContainerFragment) getParentFragment()).getController());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(C2658R.layout.fragment_storelocator_search, container, false);
        vg.findViewById(C2358R.C2357id.filters_view).setOnClickListener(new C37691());
        this.mListView = (ListView) vg.findViewById(C2358R.C2357id.list);
        this.mAdapter = new ArrayAdapter(getActivity(), 17367043, this.mSearchList);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new C37702());
        this.mCheckedImageView = (ImageView) vg.findViewById(C2358R.C2357id.red_filters_check);
        return vg;
    }

    public void onResume() {
        boolean checked;
        int i = 0;
        super.onResume();
        if (this.mController.getActiveFilters().isEmpty()) {
            checked = false;
        } else {
            checked = true;
        }
        ImageView imageView = this.mCheckedImageView;
        if (!checked) {
            i = 4;
        }
        imageView.setVisibility(i);
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPage() {
        Ensighten.evaluateEvent(this, "getDataLayerPage", null);
        return null;
    }

    public void setController(StoreLocatorController controller) {
        Ensighten.evaluateEvent(this, "setController", new Object[]{controller});
        this.mController = controller;
        this.mProvider = controller;
    }

    public void updateViewItems() {
        boolean checked;
        int i = 0;
        Ensighten.evaluateEvent(this, "updateViewItems", null);
        if (this.mController.getActiveFilters().isEmpty()) {
            checked = false;
        } else {
            checked = true;
        }
        ImageView imageView = this.mCheckedImageView;
        if (!checked) {
            i = 4;
        }
        imageView.setVisibility(i);
        updateList();
        this.mAdapter.notifyDataSetChanged();
    }

    private void updateList() {
        Ensighten.evaluateEvent(this, "updateList", null);
        List<String> tempTitles = LocalDataManager.getSharedInstance().getStringList(LocalDataManager.PREF_LOCATION_SEARCH_TITLE, null);
        if (tempTitles != null) {
            Set<String> set = new LinkedHashSet(tempTitles);
            this.mSearchList.clear();
            this.mSearchList.addAll(set);
        }
    }
}
