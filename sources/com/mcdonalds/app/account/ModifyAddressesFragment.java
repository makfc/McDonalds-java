package com.mcdonalds.app.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.p001v7.widget.LinearLayoutManager;
import android.support.p001v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.AddressesAdapter.ItemClickListener;
import com.mcdonalds.app.account.EditAddressFragment.EditAddressDataPasser;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModifyAddressesFragment extends URLNavigationFragment implements ItemClickListener {
    public static final String NAME = ModifyAddressesFragment.class.getSimpleName();
    private boolean emptyPrompt = true;
    private AddressesAdapter mAdapter;
    RecyclerView mAddressList;
    private ArrayList<Double> mConfigAddressTypes;
    private List<CustomerAddress> mCustomerAddresses;
    private CustomerModule mCustomerModule;
    private OnClickListener mFabClickListener = new C29821();
    private String mFullAddress;
    private Menu mMenu;
    private final DialogInterface.OnClickListener mOnNoAddressDialog = new C29909();
    private AddressType mSelectedAddressType = null;
    private boolean mUpdateDefault;

    /* renamed from: com.mcdonalds.app.account.ModifyAddressesFragment$10 */
    class C298110 implements DialogInterface.OnClickListener {
        C298110() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyAddressesFragment$1 */
    class C29821 implements OnClickListener {
        C29821() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ModifyAddressesFragment.access$000(ModifyAddressesFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyAddressesFragment$7 */
    class C29887 implements AsyncListener<Boolean> {
        C29887() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (ModifyAddressesFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception != null) {
                AsyncException.report(exception);
            } else {
                ModifyAddressesFragment.access$400(ModifyAddressesFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyAddressesFragment$8 */
    class C29898 implements AsyncListener<List<CustomerAddress>> {
        C29898() {
        }

        public void onResponse(List<CustomerAddress> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                CustomerAddress selectedAddress = null;
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$500", new Object[]{ModifyAddressesFragment.this}) == null || Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$500", new Object[]{ModifyAddressesFragment.this}).isEmpty()) {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$600", new Object[]{ModifyAddressesFragment.this}) != null) {
                        for (CustomerAddress address : response) {
                            if (address.getAddressType() == Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$600", new Object[]{ModifyAddressesFragment.this})) {
                                selectedAddress = address;
                                break;
                            }
                        }
                    }
                    for (CustomerAddress address2 : response) {
                        if (address2.isDefaultAddress()) {
                            selectedAddress = address2;
                            break;
                        }
                    }
                }
                for (CustomerAddress address22 : response) {
                    if (address22.getFullAddress().equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$500", new Object[]{ModifyAddressesFragment.this}))) {
                        selectedAddress = address22;
                        break;
                    }
                }
                if (response.isEmpty() && Configuration.getSharedInstance().getBooleanForKey("interface.address.showPromptWhenNoAddress") && Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$700", new Object[]{ModifyAddressesFragment.this})) {
                    if (ModifyAddressesFragment.this.isActivityAlive()) {
                        UIUtils.showGlobalAlertDialog(ModifyAddressesFragment.this.getActivity(), ModifyAddressesFragment.this.getString(C2658R.string.no_address_title), ModifyAddressesFragment.this.getString(C2658R.string.no_address), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$800", new Object[]{ModifyAddressesFragment.this}));
                    }
                    ModifyAddressesFragment.access$702(ModifyAddressesFragment.this, false);
                    DataLayerManager.getInstance().recordError("No address");
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$100", new Object[]{ModifyAddressesFragment.this}).setAddresses(response);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$100", new Object[]{ModifyAddressesFragment.this}).setSelectedAddress(selectedAddress);
                if (selectedAddress != null) {
                    ModifyAddressesFragment.access$602(ModifyAddressesFragment.this, selectedAddress.getAddressType());
                }
                ModifyAddressesFragment.access$900(ModifyAddressesFragment.this);
                ModifyAddressesFragment.access$1002(ModifyAddressesFragment.this, response);
                return;
            }
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyAddressesFragment$9 */
    class C29909 implements DialogInterface.OnClickListener {
        C29909() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            ModifyAddressesFragment.access$000(ModifyAddressesFragment.this);
        }
    }

    static /* synthetic */ void access$000(ModifyAddressesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$000", new Object[]{x0});
        x0.addNewAddress();
    }

    static /* synthetic */ List access$1002(ModifyAddressesFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$1002", new Object[]{x0, x1});
        x0.mCustomerAddresses = x1;
        return x1;
    }

    static /* synthetic */ void access$200(ModifyAddressesFragment x0, CustomerAddress x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$200", new Object[]{x0, x1, new Integer(x2)});
        x0.removeDefaultAddress(x1, x2);
    }

    static /* synthetic */ void access$300(ModifyAddressesFragment x0, CustomerAddress x1, int x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$300", new Object[]{x0, x1, new Integer(x2)});
        x0.removeAddress(x1, x2);
    }

    static /* synthetic */ void access$400(ModifyAddressesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$400", new Object[]{x0});
        x0.refreshAddresses();
    }

    static /* synthetic */ AddressType access$602(ModifyAddressesFragment x0, AddressType x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$602", new Object[]{x0, x1});
        x0.mSelectedAddressType = x1;
        return x1;
    }

    static /* synthetic */ boolean access$702(ModifyAddressesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$702", new Object[]{x0, new Boolean(x1)});
        x0.emptyPrompt = x1;
        return x1;
    }

    static /* synthetic */ void access$900(ModifyAddressesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$900", new Object[]{x0});
        x0.manageEditOption();
    }

    public void onCreate(Bundle savedInstanceState) {
        Order order;
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mUpdateDefault = true;
        Bundle args = getArguments();
        if (args != null) {
            Bundle b = args.getBundle("ADDRESS_BUNDLE");
            if (b != null) {
                this.mUpdateDefault = b.getBoolean("update_default", true);
                this.mFullAddress = b.getString("full_address", "");
            }
        }
        if (!this.mUpdateDefault) {
            order = OrderingManager.getInstance().getCurrentOrder();
            if (order.getDeliveryAddress() != null) {
                this.mSelectedAddressType = order.getDeliveryAddress().getAddressType();
            }
        }
        if (!this.mUpdateDefault) {
            order = OrderingManager.getInstance().getCurrentOrder();
            if (order.getDeliveryAddress() != null) {
                this.mSelectedAddressType = order.getDeliveryAddress().getAddressType();
            }
        }
        this.mConfigAddressTypes = (ArrayList) Configuration.getSharedInstance().getValueForKey("delivery.addressTypes");
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.edit_save, menu);
        this.mMenu = menu;
        MenuItem item = this.mMenu.findItem(C2358R.C2357id.action_edit);
        if (this.mSelectedAddressType != null) {
            z = false;
        }
        item.setVisible(z);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                selectSingleAddress();
                return true;
            case C2358R.C2357id.action_edit /*2131821897*/:
                this.mAdapter.setEditing(true);
                this.mMenu.findItem(C2358R.C2357id.action_done).setVisible(true);
                item.setVisible(false);
                return true;
            case C2358R.C2357id.action_done /*2131821900*/:
                this.mAdapter.setEditing(false);
                this.mMenu.findItem(C2358R.C2357id.action_edit).setVisible(true);
                item.setVisible(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void manageEditOption() {
        boolean z = true;
        Ensighten.evaluateEvent(this, "manageEditOption", null);
        if (this.mMenu != null) {
            if (this.mAdapter.isEditing()) {
                this.mMenu.findItem(C2358R.C2357id.action_done).setVisible(true);
                return;
            }
            MenuItem findItem = this.mMenu.findItem(C2358R.C2357id.action_edit);
            if (this.mSelectedAddressType == null) {
                z = false;
            }
            findItem.setVisible(z);
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_addresses);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_modify_addresses, container, false);
        this.mAddressList = (RecyclerView) rootView.findViewById(C2358R.C2357id.address_list);
        this.mAdapter = new AddressesAdapter();
        this.mAdapter.setClickListener(this);
        this.mAddressList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAddressList.setAdapter(this.mAdapter);
        rootView.findViewById(C2358R.C2357id.add_address_button).setOnClickListener(this.mFabClickListener);
        return rootView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshAddresses();
    }

    public void onItemDismissed(int position) {
        Ensighten.evaluateEvent(this, "onItemDismissed", new Object[]{new Integer(position)});
        if (this.mAdapter.getItemCount() > position) {
            CustomerAddress address = this.mAdapter.getAddressForPosition(position);
            this.mAdapter.removeAddress(address);
            showUndoMessage(position, address);
        }
    }

    private void showUndoMessage(final int position, final CustomerAddress address) {
        Ensighten.evaluateEvent(this, "showUndoMessage", new Object[]{new Integer(position), address});
        Snackbar.make(this.mAddressList, getContext().getString(C2658R.string.address_deletion_confirmation), -1).setAction((int) C2658R.string.undo, new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$100", new Object[]{ModifyAddressesFragment.this}).restoreAddress(address, position);
            }
        }).setCallback(new Callback() {
            public void onDismissed(Snackbar snackbar, int event) {
                Ensighten.evaluateEvent(this, "onDismissed", new Object[]{snackbar, new Integer(event)});
                if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$100", new Object[]{ModifyAddressesFragment.this}).hasAddress(address)) {
                    if (address.isDefaultAddress()) {
                        ModifyAddressesFragment.access$200(ModifyAddressesFragment.this, address, position);
                    } else {
                        ModifyAddressesFragment.access$300(ModifyAddressesFragment.this, address, position);
                    }
                }
            }
        }).show();
    }

    private void removeDefaultAddress(final CustomerAddress address, final int position) {
        Ensighten.evaluateEvent(this, "removeDefaultAddress", new Object[]{address, new Integer(position)});
        int size = this.mCustomerAddresses.size();
        if (size > 0) {
            CustomerAddress tempAddr = null;
            for (int i = 0; i < size; i++) {
                tempAddr = (CustomerAddress) this.mCustomerAddresses.get(i);
                if (!tempAddr.isDefaultAddress()) {
                    address.setDefaultAddress(false);
                    tempAddr.setDefaultAddress(true);
                    this.mSelectedAddressType = tempAddr.getAddressType();
                    this.mAdapter.setSelected(tempAddr);
                    break;
                }
            }
            if (tempAddr != null) {
                UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.progress_update_default_address));
                this.mCustomerModule.updateAddressBook(this.mCustomerAddresses, this.mCustomerModule.getCurrentProfile(), new AsyncListener<Boolean>() {
                    public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        if (ModifyAddressesFragment.this.getNavigationActivity() == null) {
                            return;
                        }
                        if (exception != null) {
                            AsyncException.report(exception);
                        } else {
                            ModifyAddressesFragment.access$300(ModifyAddressesFragment.this, address, position);
                        }
                    }
                });
            }
        } else if (getNavigationActivity() != null) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.address_warning_only_address)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
            this.mAdapter.restoreAddress(address, position);
        }
    }

    private void removeAddress(final CustomerAddress address, final int position) {
        Ensighten.evaluateEvent(this, "removeAddress", new Object[]{address, new Integer(position)});
        this.mCustomerModule.removeAddress(address, this.mCustomerModule.getCurrentProfile(), new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (ModifyAddressesFragment.this.getNavigationActivity() != null && exception != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyAddressesFragment", "access$100", new Object[]{ModifyAddressesFragment.this}).restoreAddress(address, position);
                    AsyncException.report(exception);
                }
            }
        });
    }

    public void selectSingleAddress() {
        Ensighten.evaluateEvent(this, "selectSingleAddress", null);
        if (this.mCustomerAddresses == null || this.mCustomerAddresses.size() <= 0) {
            getActivity().setResult(0);
            getActivity().finish();
            return;
        }
        CustomerAddress selectedAddress = null;
        for (CustomerAddress c : this.mCustomerAddresses) {
            if (c.getAddressType() == this.mSelectedAddressType) {
                selectedAddress = c;
                break;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("ADDRESS_RETURN_KEY", selectedAddress);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    private void save() {
        Ensighten.evaluateEvent(this, "save", null);
        CustomerAddress selectedAddress = null;
        if (this.mSelectedAddressType != null) {
            int size = this.mCustomerAddresses.size();
            for (int i = 0; i < size; i++) {
                CustomerAddress address = (CustomerAddress) this.mCustomerAddresses.get(i);
                if (this.mSelectedAddressType == address.getAddressType()) {
                    selectedAddress = address;
                    address.setDefaultAddress(true);
                } else {
                    address.setDefaultAddress(false);
                }
            }
        }
        this.mAdapter.notifyDataSetChanged();
        if (selectedAddress != null) {
            boolean updateDefaultFromMenu = false;
            Bundle args = getArguments();
            if (args != null) {
                Bundle b = args.getBundle("ADDRESS_BUNDLE");
                if (b != null) {
                    updateDefaultFromMenu = b.getBoolean("update_default_from_menu", false);
                }
            }
            if (updateDefaultFromMenu) {
                final CustomerAddress retSelectedAddress = selectedAddress;
                UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.progress_update_default_address));
                this.mCustomerModule.updateAddressBook(this.mCustomerAddresses, this.mCustomerModule.getCurrentProfile(), new AsyncListener<Boolean>() {
                    public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        if (ModifyAddressesFragment.this.getNavigationActivity() == null) {
                            return;
                        }
                        if (exception != null) {
                            AsyncException.report(exception);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ADDRESS_RETURN_KEY", retSelectedAddress);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        ModifyAddressesFragment.this.getActivity().setResult(-1, intent);
                        ModifyAddressesFragment.this.getActivity().finish();
                    }
                });
                return;
            } else if (this.mUpdateDefault) {
                UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.progress_update_default_address));
                this.mCustomerModule.updateAddressBook(this.mCustomerAddresses, this.mCustomerModule.getCurrentProfile(), new C29887());
                return;
            } else {
                Bundle bundle = new Bundle();
                bundle.putParcelable("ADDRESS_RETURN_KEY", selectedAddress);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                getActivity().setResult(-1, intent);
                getActivity().finish();
                return;
            }
        }
        getActivity().setResult(0);
        getActivity().finish();
    }

    private void refreshAddresses() {
        Ensighten.evaluateEvent(this, "refreshAddresses", null);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerModule.getAddressBook(this.mCustomerModule.getCurrentProfile(), new C29898());
    }

    private void addNewAddress() {
        Ensighten.evaluateEvent(this, "addNewAddress", null);
        String fragmentName = getEditFragmentName();
        List<AddressType> availableTypes = getAvailableAddressTypes();
        EditAddressDataPasser data = new EditAddressDataPasser();
        data.setAvailableTypes(availableTypes);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Add new address");
        startEditAddressActivity(fragmentName, data);
    }

    public void onItemClicked(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "onItemClicked", new Object[]{address});
        List<AddressType> availableTypes = getAvailableAddressTypes();
        EditAddressDataPasser data = new EditAddressDataPasser();
        data.setAvailableTypes(availableTypes);
        data.setCustomerAddresses(this.mCustomerAddresses);
        String fragmentName = getEditFragmentName();
        if (this.mAdapter.isEditing()) {
            startEditAddressActivity(fragmentName, data.setCustomerAddress(address));
            return;
        }
        this.mSelectedAddressType = address.getAddressType();
        save();
    }

    private List<AddressType> getAvailableAddressTypes() {
        Ensighten.evaluateEvent(this, "getAvailableAddressTypes", null);
        List<AddressType> availableTypes = new ArrayList(Arrays.asList(AddressType.values()));
        if (this.mConfigAddressTypes != null) {
            for (int a = 0; a < availableTypes.size(); a++) {
                if (!this.mConfigAddressTypes.contains(Double.valueOf((double) ((AddressType) availableTypes.get(a)).ordinal()))) {
                    availableTypes.remove(availableTypes.get(a));
                }
            }
        } else {
            availableTypes.remove(AddressType.UNUSED);
        }
        if (this.mCustomerAddresses != null) {
            for (CustomerAddress customerAddress : this.mCustomerAddresses) {
                availableTypes.remove(customerAddress.getAddressType());
            }
        }
        return availableTypes;
    }

    private String getEditFragmentName() {
        Ensighten.evaluateEvent(this, "getEditFragmentName", null);
        String fragmentName = EditAddressFragment.NAME;
        String externalAddressProvider = (String) Configuration.getSharedInstance().getValueForKey("modules.delivery.externalAddressProvider");
        if (externalAddressProvider != null) {
            return externalAddressProvider;
        }
        return fragmentName;
    }

    private void startEditAddressActivity(String fragmentName, EditAddressDataPasser data) {
        boolean hasAvailableAddressTypes = true;
        Ensighten.evaluateEvent(this, "startEditAddressActivity", new Object[]{fragmentName, data});
        if (this.mCustomerAddresses != null) {
            if (this.mCustomerAddresses.size() >= this.mConfigAddressTypes.size()) {
                hasAvailableAddressTypes = false;
            }
            if (data.getCustomerAddress() != null || hasAvailableAddressTypes) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("addressData", data);
                startActivityForResult(EditAddressActivity.class, fragmentName, bundle, 18325);
                return;
            }
            UIUtils.showGlobalAlertDialog(getContext(), getContext().getResources().getString(C2658R.string.address_limit_reached), getContext().getResources().getString(C2658R.string.address_limit_reached_message), new C298110());
            DataLayerManager.getInstance().recordError("Maximum number of addresses");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 18325 && resultCode == -1) {
            refreshAddresses();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
