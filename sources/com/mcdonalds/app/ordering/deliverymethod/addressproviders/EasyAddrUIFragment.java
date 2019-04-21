package com.mcdonalds.app.ordering.deliverymethod.addressproviders;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.EditAddressFragment.EditAddressDataPasser;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AddressAliasType;
import com.mcdonalds.sdk.modules.models.AddressAliasValue;
import com.mcdonalds.sdk.modules.models.AddressElement;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p049hk.com.aisoft.easyaddrui.Address;
import p049hk.com.aisoft.easyaddrui.CompleteCallbackInterface;
import p049hk.com.aisoft.easyaddrui.ConfirmCallbackInterface;
import p049hk.com.aisoft.easyaddrui.Customer;
import p049hk.com.aisoft.easyaddrui.EditCallbackInterface;
import p049hk.com.aisoft.easyaddrui.SaveCallbackInterface;
import p049hk.com.aisoft.easyaddrui.eaView;

public class EasyAddrUIFragment extends URLNavigationFragment implements CompleteCallbackInterface, ConfirmCallbackInterface, EditCallbackInterface {
    private CustomerAddress mAddress;
    private AsyncListener<Boolean> mAddressListener = new C35074();
    private List<CustomerAddress> mAddresses;
    private MenuItem mCompleteButton;
    private MenuItem mConfirmButton;
    private CustomerProfile mCurrentProfile;
    private CustomerModule mCustomerModule;
    private eaView mEasyAddrView;
    private boolean mIsEdit;
    private View mProgress;
    private MenuItem mSaveButton;

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment$1 */
    class C35041 implements SaveCallbackInterface {
        C35041() {
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment$2 */
    class C35052 implements AsyncListener<Boolean> {
        C35052() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (EasyAddrUIFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception == null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment", "access$300", new Object[]{EasyAddrUIFragment.this}).removeAddress(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment", "access$000", new Object[]{EasyAddrUIFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment", "access$100", new Object[]{EasyAddrUIFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment", "access$200", new Object[]{EasyAddrUIFragment.this}));
            } else {
                AsyncException.report(exception);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment$3 */
    class C35063 implements OnClickListener {
        C35063() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            EasyAddrUIFragment.this.getActivity().setResult(0);
            EasyAddrUIFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment$4 */
    class C35074 implements AsyncListener<Boolean> {
        C35074() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment", "access$400", new Object[]{EasyAddrUIFragment.this}).setVisibility(8);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment", "access$500", new Object[]{EasyAddrUIFragment.this}).setEnabled(true);
            if (EasyAddrUIFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception == null) {
                EasyAddrUIFragment.this.getActivity().setResult(-1);
                EasyAddrUIFragment.this.getActivity().finish();
                return;
            }
            AsyncException.report(exception);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditAddressDataPasser data = (EditAddressDataPasser) getArguments().getSerializable("addressData");
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCurrentProfile = this.mCustomerModule.getCurrentProfile();
        boolean z = (data == null || data.getCustomerAddress() == null) ? false : true;
        this.mIsEdit = z;
        if (this.mIsEdit) {
            this.mAddress = data.getCustomerAddress();
            this.mAddresses = data.getCustomerAddresses();
            return;
        }
        this.mAddress = new CustomerAddress();
        if (data != null && ListUtils.isNotEmpty(data.getAvailableTypes())) {
            this.mAddress.setAddressType((AddressType) data.getAvailableTypes().get(0));
        }
        this.mAddress.setDefaultAddress(true);
        this.mAddress.setAllowPromotionsToAddress(true);
        this.mAddress.setAddressElements(new ArrayList());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_easyaddrui, container, false);
        setHasOptionsMenu(true);
        this.mEasyAddrView = (eaView) v.findViewById(C2358R.C2357id.easyaddrview);
        this.mEasyAddrView.setMode("GPS");
        Configuration configuration = Configuration.getSharedInstance();
        String lang = configuration.getEasyAddressLanguageTag();
        this.mEasyAddrView.loadEAView(configuration.getStringForKey("connectors.EasyAddress.key"), lang);
        this.mEasyAddrView.setConfirmCallBack(this);
        this.mEasyAddrView.setSaveCallBack(new C35041());
        this.mEasyAddrView.setCompleteCallBack(this);
        this.mEasyAddrView.setEditCallBack(this);
        this.mEasyAddrView.setCurrentAddr(null);
        this.mProgress = v.findViewById(C2358R.C2357id.save_progress);
        if (this.mIsEdit) {
            eaView eaview = this.mEasyAddrView;
            eaView eaview2 = eaview;
            eaview2.setCurrentAddr(new Address(filterNull(this.mAddress.getAddressElementValue(AddressElementType.Area)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.District)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Street)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.StreetLonNumber)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.HouseNumber)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Garden)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Building)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Block)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Level)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Unit)), filterNull(this.mAddress.getAddressElementValue(AddressElementType.Remark))));
        }
        return v;
    }

    private String filterNull(String s) {
        Ensighten.evaluateEvent(this, "filterNull", new Object[]{s});
        if (s == null) {
            return "";
        }
        return s;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.address_eaview, menu);
        menu.findItem(C2358R.C2357id.action_delete).setVisible(this.mIsEdit);
        this.mSaveButton = menu.findItem(C2358R.C2357id.action_save);
        this.mCompleteButton = menu.findItem(C2358R.C2357id.action_complete);
        this.mConfirmButton = menu.findItem(C2358R.C2357id.action_confirm);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        eaView eaview;
        switch (item.getItemId()) {
            case C2358R.C2357id.action_delete /*2131821892*/:
                if (!this.mAddress.isDefaultAddress()) {
                    UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.progress_update_default_address));
                    this.mCustomerModule.removeAddress(this.mAddress, this.mCurrentProfile, this.mAddressListener);
                    return false;
                } else if (this.mAddresses == null) {
                    return false;
                } else {
                    if (this.mAddresses.size() > 1) {
                        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.progress_update_default_address));
                        CustomerAddress newDefault = null;
                        for (CustomerAddress addr : this.mAddresses) {
                            if (!addr.isDefaultAddress()) {
                                addr.setDefaultAddress(true);
                                newDefault = addr;
                                this.mAddress.setDefaultAddress(false);
                                this.mCustomerModule.setDefaultAddress(newDefault, this.mCustomerModule.getCurrentProfile(), new C35052());
                                return false;
                            }
                        }
                        this.mAddress.setDefaultAddress(false);
                        this.mCustomerModule.setDefaultAddress(newDefault, this.mCustomerModule.getCurrentProfile(), new C35052());
                        return false;
                    }
                    MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.address_warning_only_address)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
                    return false;
                }
            case C2358R.C2357id.action_save /*2131821893*/:
                try {
                    onSavePress();
                    return false;
                } catch (Exception e) {
                    showInitAlertBox();
                    return false;
                }
            case C2358R.C2357id.action_confirm /*2131821894*/:
                try {
                    eaview = this.mEasyAddrView;
                    eaView.btnConfirmPressAction();
                    return false;
                } catch (Exception e2) {
                    showInitAlertBox();
                    return false;
                }
            case C2358R.C2357id.action_complete /*2131821895*/:
                try {
                    eaview = this.mEasyAddrView;
                    eaView.btnCompletePressAction();
                    return false;
                } catch (Exception e3) {
                    showInitAlertBox();
                    return false;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showInitAlertBox() {
        Ensighten.evaluateEvent(this, "showInitAlertBox", null);
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.error_title)).setMessage(getString(C2658R.string.error_wait_easyaddr_init)).setPositiveButton((int) C2658R.string.f6083ok, null).setNegativeButton((int) C2658R.string.cancel, new C35063()).setCancelable(false).create().show();
    }

    private void onSavePress() {
        Ensighten.evaluateEvent(this, "onSavePress", null);
        eaView eaview = this.mEasyAddrView;
        if (eaView.btnSavePressAction()) {
        }
    }

    public void onConfirmPress(Address address, Customer customer) {
        Ensighten.evaluateEvent(this, "onConfirmPress", new Object[]{address, customer});
        this.mConfirmButton.setVisible(false);
        this.mSaveButton.setVisible(true);
        this.mSaveButton.setEnabled(false);
        this.mProgress.setVisibility(0);
        if (this.mIsEdit) {
            this.mAddress.getAddressElements().clear();
            addAddressElement(AddressElementType.Building, AddressAliasType.Kanji, address.sBldgC);
            addAddressElement(AddressElementType.Area, AddressAliasType.Kanji, address.sAreaC);
            addAddressElement(AddressElementType.District, AddressAliasType.Kanji, address.sDistrictC);
            addAddressElement(AddressElementType.Street, AddressAliasType.Kanji, address.sStreetC);
            addAddressElement(AddressElementType.Block, AddressAliasType.Kanji, address.sBlockC);
            addAddressElement(AddressElementType.Level, AddressAliasType.Kanji, address.sFloor);
            addAddressElement(AddressElementType.Unit, AddressAliasType.Kanji, address.sFlat);
            addAddressElement(AddressElementType.OneLineAddress, AddressAliasType.Kanji, address.sAddrE);
            addAddressElement(AddressElementType.HouseNumber, AddressAliasType.Kanji, address.sStreetNo);
            addAddressElement(AddressElementType.StreetLonNumber, AddressAliasType.Kanji, address.sStreetLon);
            addAddressElement(AddressElementType.Remark, AddressAliasType.Kanji, address.sRemarks);
            addAddressElement(AddressElementType.Garden, AddressAliasType.Kanji, address.sEstateC);
            this.mCustomerModule.updateAddressBook(Collections.singletonList(this.mAddress), this.mCurrentProfile, this.mAddressListener);
            return;
        }
        addAddressElement(AddressElementType.Building, AddressAliasType.Kanji, address.sBldgC);
        addAddressElement(AddressElementType.Area, AddressAliasType.Kanji, address.sAreaC);
        addAddressElement(AddressElementType.District, AddressAliasType.Kanji, address.sDistrictC);
        addAddressElement(AddressElementType.Street, AddressAliasType.Kanji, address.sStreetC);
        addAddressElement(AddressElementType.Block, AddressAliasType.Kanji, address.sBlockC);
        addAddressElement(AddressElementType.Level, AddressAliasType.Kanji, address.sFloor);
        addAddressElement(AddressElementType.Unit, AddressAliasType.Kanji, address.sFlat);
        addAddressElement(AddressElementType.OneLineAddress, AddressAliasType.Kanji, address.sAddrE);
        addAddressElement(AddressElementType.HouseNumber, AddressAliasType.Kanji, address.sStreetNo);
        addAddressElement(AddressElementType.StreetLonNumber, AddressAliasType.Kanji, address.sStreetLon);
        addAddressElement(AddressElementType.Remark, AddressAliasType.Kanji, address.sRemarks);
        addAddressElement(AddressElementType.Garden, AddressAliasType.Kanji, address.sEstateC);
        this.mCustomerModule.addAddress(this.mAddress, this.mCurrentProfile, this.mAddressListener);
    }

    private void addAddressElement(AddressElementType type, AddressAliasType aliasType, String value) {
        Ensighten.evaluateEvent(this, "addAddressElement", new Object[]{type, aliasType, value});
        AddressElement element = new AddressElement();
        element.setAddressElementType(type);
        List<AddressAliasValue> elementValues = new ArrayList();
        AddressAliasValue elementValue = new AddressAliasValue();
        elementValue.setAliasType(aliasType);
        elementValue.setAlias(value);
        elementValues.add(elementValue);
        element.setValue(elementValues);
        this.mAddress.getAddressElements().add(element);
    }
}
