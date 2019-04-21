package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import java.util.List;

public class ChooseAddressTypeFragment extends URLNavigationFragment implements OnItemClickListener {
    public static final String NAME = ChooseAddressTypeFragment.class.getName();
    private AddressTypeAdapter mAddressAdapter;
    private List<AddressType> mAvailableTypes;
    private int mCurrentTypeIndex = 0;
    private ListView mListView;

    private class AddressTypeAdapter extends ArrayAdapter<AddressType> {
        AddressTypeAdapter() {
            super(ChooseAddressTypeFragment.this.getActivity(), 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(C2658R.layout.address_type_item, parent, false);
            }
            DataLayerClickListener.setDataLayerTag(convertView, AddressTypeAdapter.class, position);
            ((TextView) convertView.findViewById(C2358R.C2357id.address_type)).setText(CustomerAddress.getAddressLabel((AddressType) getItem(position), getContext()));
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_choose_address_type);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mCurrentTypeIndex = getArguments().getInt("CURRENT_ADDRESS_TYPE_KEY");
            this.mAvailableTypes = (List) getArguments().getSerializable("availableTypes");
            return;
        }
        this.mCurrentTypeIndex = 0;
        this.mAvailableTypes = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_choose_address_type, container, false);
        this.mListView = (ListView) view.findViewById(C2358R.C2357id.listView);
        this.mListView.setChoiceMode(1);
        this.mListView.setItemsCanFocus(false);
        this.mListView.setOnItemClickListener(this);
        if (this.mAvailableTypes != null) {
            this.mAddressAdapter = new AddressTypeAdapter();
            this.mAddressAdapter.addAll(this.mAvailableTypes);
            this.mListView.setAdapter(this.mAddressAdapter);
            this.mListView.setItemChecked(this.mCurrentTypeIndex, true);
        }
        return view;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
        AddressType type = (AddressType) this.mAddressAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra("selected_address_type", type.ordinal());
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }
}
