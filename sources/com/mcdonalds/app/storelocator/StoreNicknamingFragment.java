package com.mcdonalds.app.storelocator;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class StoreNicknamingFragment extends URLNavigationFragment {
    public static final String NAME = StoreNicknamingFragment.class.getSimpleName();
    ArrayAdapter adapter;
    EditText customText = null;
    private List<String> mCustomNickNamesInUse = new ArrayList();
    private List<String> mNickNames = new ArrayList();
    CheckBox priorButton = null;
    String selectedNickname = null;

    /* renamed from: com.mcdonalds.app.storelocator.StoreNicknamingFragment$1 */
    class C37711 implements OnItemClickListener {
        C37711() {
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
            CheckBox checkBox = (CheckBox) view.findViewById(C2358R.C2357id.rb_name);
            if (checkBox != null) {
                StoreNicknamingFragment.access$000(StoreNicknamingFragment.this, checkBox);
                checkBox.setChecked(true);
            }
            if (StoreNicknamingFragment.this.customText.hasFocus()) {
                view.requestFocus();
                StoreNicknamingFragment.this.customText.clearFocus();
                ((InputMethodManager) StoreNicknamingFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(StoreNicknamingFragment.this.customText.getWindowToken(), 0);
            }
            StoreNicknamingFragment.this.selectedNickname = (String) Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreNicknamingFragment", "access$100", new Object[]{StoreNicknamingFragment.this}).get(position);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreNicknamingFragment$4 */
    class C37744 implements TextWatcher {
        C37744() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreNicknamingFragment", "access$200", new Object[]{StoreNicknamingFragment.this, s})) {
                StoreNicknamingFragment.this.selectedNickname = s.toString();
            }
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreNicknamingFragment$6 */
    class C37766 implements OnFocusChangeListener {
        C37766() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
            if (StoreNicknamingFragment.this.customText.hasFocus()) {
                StoreNicknamingFragment.access$000(StoreNicknamingFragment.this, (CheckBox) v);
                StoreNicknamingFragment.this.customText.requestFocus();
                StoreNicknamingFragment.this.customText.setSelection(StoreNicknamingFragment.this.customText.getText().length());
                ((InputMethodManager) ((URLNavigationActivity) StoreNicknamingFragment.this.getActivity()).getSystemService("input_method")).showSoftInput(StoreNicknamingFragment.this.customText, 1);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreNicknamingFragment$7 */
    class C37777 implements OnClickListener {
        C37777() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    static /* synthetic */ void access$000(StoreNicknamingFragment x0, CheckBox x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreNicknamingFragment", "access$000", new Object[]{x0, x1});
        x0.reversePriorButton(x1);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_nickname_location);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        eliminateDuplicateNames();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_nicknaming, container, false);
        final ListView listView = (ListView) rootView.findViewById(C2358R.C2357id.listview1);
        this.adapter = new ArrayAdapter(getNavigationActivity(), C2658R.layout.nickname_row, 2131821675, this.mNickNames);
        listView.setAdapter(this.adapter);
        listView.setOnItemClickListener(new DataLayerClickListener("CheckBoxItemPressed", new C37711()));
        listView.post(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                listView.performItemClick(listView, 0, listView.getItemIdAtPosition(0));
            }
        });
        View editLayout = getActivity().getLayoutInflater().inflate(C2658R.layout.nickname_row_footer, null);
        this.customText = (EditText) editLayout.findViewById(C2358R.C2357id.text_custom);
        final CheckBox customCheckBox = (CheckBox) editLayout.findViewById(C2358R.C2357id.rb_custom);
        this.customText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
                if (hasFocus) {
                    ((EditText) v).setSelection(((EditText) v).getText().length());
                    StoreNicknamingFragment.this.selectedNickname = StoreNicknamingFragment.this.customText.getText().toString();
                    if (!customCheckBox.isChecked()) {
                        customCheckBox.setChecked(true);
                        StoreNicknamingFragment.access$000(StoreNicknamingFragment.this, customCheckBox);
                        ((InputMethodManager) ((URLNavigationActivity) StoreNicknamingFragment.this.getActivity()).getSystemService("input_method")).showSoftInput(StoreNicknamingFragment.this.customText, 1);
                    }
                }
            }
        });
        this.customText.addTextChangedListener(new C37744());
        customCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                customCheckBox.setChecked(false);
                StoreNicknamingFragment.this.customText.requestFocus();
            }
        });
        customCheckBox.setOnFocusChangeListener(new C37766());
        listView.addFooterView(editLayout);
        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        if (item.getItemId() == C2358R.C2357id.action_save) {
            if (this.selectedNickname.trim().length() == 0) {
                return true;
            }
            if (!textDuplicationWarning(this.customText.getText())) {
                if (this.selectedNickname != null) {
                    this.selectedNickname = this.selectedNickname.trim();
                    AnalyticsUtils.trackOnClickEvent("/restaurant-locator", "Nickname Save");
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", this.selectedNickname);
                    getActivity().setResult(-1, resultIntent);
                    getActivity().finish();
                    return true;
                }
                getActivity().setResult(0);
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getNavigationActivity().getResources().getString(C2658R.string.store_nicknaming_fragment_title);
    }

    private void eliminateDuplicateNames() {
        Ensighten.evaluateEvent(this, "eliminateDuplicateNames", null);
        CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mNickNames = StoresManager.getInstance().getAvailableNickNames();
        this.mCustomNickNamesInUse = new ArrayList();
        if (module.getFavoriteStores() != null) {
            List<String> selectedNames = new ArrayList();
            for (Store favoriteStore : module.getFavoriteStores()) {
                if (favoriteStore.getStoreFavoriteName() != null) {
                    selectedNames.add(favoriteStore.getStoreFavoriteName().trim());
                    this.mCustomNickNamesInUse.add(favoriteStore.getStoreFavoriteName().trim());
                }
            }
            this.mCustomNickNamesInUse.removeAll(this.mNickNames);
            this.mNickNames.removeAll(selectedNames);
        }
    }

    private void reversePriorButton(CheckBox rb) {
        Ensighten.evaluateEvent(this, "reversePriorButton", new Object[]{rb});
        if (this.priorButton != null) {
            this.priorButton.setChecked(false);
        }
        this.priorButton = rb;
    }

    private boolean textDuplicationWarning(CharSequence nickName) {
        Ensighten.evaluateEvent(this, "textDuplicationWarning", new Object[]{nickName});
        boolean match = false;
        for (String aMCustomNickNamesInUse : this.mCustomNickNamesInUse) {
            match = aMCustomNickNamesInUse.toLowerCase().equals(nickName.toString().toLowerCase());
            if (match) {
                break;
            }
        }
        if (match) {
            Builder builder = new Builder(getNavigationActivity());
            builder.setPositiveButton(getResources().getString(C2658R.string.f6083ok), new C37777());
            builder.setTitle(getResources().getString(C2658R.string.warning));
            builder.setMessage(String.format(getResources().getString(C2658R.string.nickname_already_in_use), new Object[]{nickName}));
            builder.create().show();
        }
        return match;
    }
}
