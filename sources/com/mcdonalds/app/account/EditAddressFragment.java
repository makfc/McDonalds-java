package com.mcdonalds.app.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.AddressAliasType;
import com.mcdonalds.sdk.modules.models.AddressAliasValue;
import com.mcdonalds.sdk.modules.models.AddressElement;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import com.mcdonalds.sdk.modules.models.AddressElementValidationRule;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EditAddressFragment extends URLNavigationFragment implements OnClickListener {
    public static final String NAME = EditAddressFragment.class.getSimpleName();
    public static final Map<String, String> STATE_MAP = new HashMap();
    private CustomerAddress mAddress;
    private View mAddressTypeContainer;
    private TextView mAddressTypeLabel;
    private List<AddressType> mAvailableAddressTypes;
    private CustomerModule mCustomerModule;
    private LinearLayout mElementsContainer;
    private boolean mIsEdit;
    private String mPreValidatedValue;
    private GetAddressElementsResult mResult;
    private Button mSaveButton;
    private Map<AddressElementType, AddressElementValidationRule> mValidationRules;

    /* renamed from: com.mcdonalds.app.account.EditAddressFragment$1 */
    class C29621 implements AsyncListener<Boolean> {
        C29621() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (EditAddressFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception == null) {
                EditAddressFragment.this.getActivity().setResult(-1);
                EditAddressFragment.this.getActivity().finish();
                return;
            }
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.account.EditAddressFragment$2 */
    class C29652 implements AsyncListener<AddressValidationResult> {

        /* renamed from: com.mcdonalds.app.account.EditAddressFragment$2$1 */
        class C29631 implements AsyncListener<Boolean> {
            C29631() {
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (EditAddressFragment.this.getNavigationActivity() == null) {
                    return;
                }
                if (exception == null) {
                    EditAddressFragment.this.getActivity().setResult(-1);
                    EditAddressFragment.this.getActivity().finish();
                    return;
                }
                AsyncException.report(exception);
            }
        }

        /* renamed from: com.mcdonalds.app.account.EditAddressFragment$2$2 */
        class C29642 implements AsyncListener<Boolean> {
            C29642() {
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (EditAddressFragment.this.getNavigationActivity() == null) {
                    return;
                }
                if (exception == null) {
                    EditAddressFragment.this.getActivity().setResult(-1);
                    EditAddressFragment.this.getActivity().finish();
                    return;
                }
                AsyncException.report(exception);
            }
        }

        C29652() {
        }

        public void onResponse(AddressValidationResult response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (EditAddressFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception != null) {
                AsyncException.report(exception);
            } else if (!response.isAddressValid()) {
                UIUtils.showGlobalAlertDialog(EditAddressFragment.this.getActivity(), null, "Invalid address", null);
                DataLayerManager.getInstance().recordError("Invalid address");
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$000", new Object[]{EditAddressFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$200", new Object[]{EditAddressFragment.this}).updateAddressBook(Arrays.asList(new CustomerAddress[]{Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$100", new Object[]{EditAddressFragment.this})}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$200", new Object[]{EditAddressFragment.this}).getCurrentProfile(), new C29631());
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$200", new Object[]{EditAddressFragment.this}).addAddress(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$100", new Object[]{EditAddressFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$200", new Object[]{EditAddressFragment.this}).getCurrentProfile(), new C29642());
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.EditAddressFragment$3 */
    class C29663 implements AsyncListener<GetAddressElementsResult> {
        C29663() {
        }

        public void onResponse(GetAddressElementsResult response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (EditAddressFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception == null) {
                EditAddressFragment.access$302(EditAddressFragment.this, response);
                EditAddressFragment.access$402(EditAddressFragment.this, new HashMap());
                for (AddressElementValidationRule rule : response.getAddressElementValidationRules()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$400", new Object[]{EditAddressFragment.this}).put(rule.getAddressElementType(), rule);
                }
                EditAddressFragment.access$500(EditAddressFragment.this);
                return;
            }
            AsyncException.report(exception);
        }
    }

    public static class EditAddressDataPasser implements Serializable {
        private List<AddressType> mAvailableTypes;
        private CustomerAddress mCustomerAddress;
        private List<CustomerAddress> mCustomerAddresses;

        public CustomerAddress getCustomerAddress() {
            Ensighten.evaluateEvent(this, "getCustomerAddress", null);
            return this.mCustomerAddress;
        }

        public EditAddressDataPasser setCustomerAddress(CustomerAddress customerAddress) {
            Ensighten.evaluateEvent(this, "setCustomerAddress", new Object[]{customerAddress});
            this.mCustomerAddress = customerAddress;
            return this;
        }

        public List<AddressType> getAvailableTypes() {
            Ensighten.evaluateEvent(this, "getAvailableTypes", null);
            return this.mAvailableTypes;
        }

        public EditAddressDataPasser setAvailableTypes(List<AddressType> availableTypes) {
            Ensighten.evaluateEvent(this, "setAvailableTypes", new Object[]{availableTypes});
            this.mAvailableTypes = availableTypes;
            return this;
        }

        public List<CustomerAddress> getCustomerAddresses() {
            Ensighten.evaluateEvent(this, "getCustomerAddresses", null);
            return this.mCustomerAddresses;
        }

        public EditAddressDataPasser setCustomerAddresses(List<CustomerAddress> customerAddresses) {
            Ensighten.evaluateEvent(this, "setCustomerAddresses", new Object[]{customerAddresses});
            this.mCustomerAddresses = customerAddresses;
            return this;
        }
    }

    static /* synthetic */ GetAddressElementsResult access$302(EditAddressFragment x0, GetAddressElementsResult x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$302", new Object[]{x0, x1});
        x0.mResult = x1;
        return x1;
    }

    static /* synthetic */ Map access$402(EditAddressFragment x0, Map x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$402", new Object[]{x0, x1});
        x0.mValidationRules = x1;
        return x1;
    }

    static /* synthetic */ void access$500(EditAddressFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$500", new Object[]{x0});
        x0.refreshViews();
    }

    static /* synthetic */ String access$602(EditAddressFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$602", new Object[]{x0, x1});
        x0.mPreValidatedValue = x1;
        return x1;
    }

    static /* synthetic */ void access$700(EditAddressFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$700", new Object[]{x0});
        x0.updateSaveButton();
    }

    static {
        STATE_MAP.put("AL", "Alabama");
        STATE_MAP.put("AK", "Alaska");
        STATE_MAP.put("AB", "Alberta");
        STATE_MAP.put("AZ", "Arizona");
        STATE_MAP.put("AR", "Arkansas");
        STATE_MAP.put("BC", "British Columbia");
        STATE_MAP.put("CA", "California");
        STATE_MAP.put("CO", "Colorado");
        STATE_MAP.put("CT", "Connecticut");
        STATE_MAP.put("DE", "Delaware");
        STATE_MAP.put("DC", "District Of Columbia");
        STATE_MAP.put("FL", "Florida");
        STATE_MAP.put("GA", "Georgia");
        STATE_MAP.put("GU", "Guam");
        STATE_MAP.put("HI", "Hawaii");
        STATE_MAP.put("ID", "Idaho");
        STATE_MAP.put("IL", "Illinois");
        STATE_MAP.put(BusinessArgs.VALUE_IN, "Indiana");
        STATE_MAP.put("IA", "Iowa");
        STATE_MAP.put("KS", "Kansas");
        STATE_MAP.put("KY", "Kentucky");
        STATE_MAP.put("LA", "Louisiana");
        STATE_MAP.put("ME", "Maine");
        STATE_MAP.put("MB", "Manitoba");
        STATE_MAP.put("MD", "Maryland");
        STATE_MAP.put("MA", "Massachusetts");
        STATE_MAP.put("MI", "Michigan");
        STATE_MAP.put("MN", "Minnesota");
        STATE_MAP.put("MS", "Mississippi");
        STATE_MAP.put("MO", "Missouri");
        STATE_MAP.put("MT", "Montana");
        STATE_MAP.put("NE", "Nebraska");
        STATE_MAP.put("NV", "Nevada");
        STATE_MAP.put("NB", "New Brunswick");
        STATE_MAP.put("NH", "New Hampshire");
        STATE_MAP.put("NJ", "New Jersey");
        STATE_MAP.put("NM", "New Mexico");
        STATE_MAP.put("NY", "New York");
        STATE_MAP.put("NF", "Newfoundland");
        STATE_MAP.put("NC", "North Carolina");
        STATE_MAP.put("ND", "North Dakota");
        STATE_MAP.put("NT", "Northwest Territories");
        STATE_MAP.put("NS", "Nova Scotia");
        STATE_MAP.put("NU", "Nunavut");
        STATE_MAP.put("OH", "Ohio");
        STATE_MAP.put("OK", "Oklahoma");
        STATE_MAP.put("ON", "Ontario");
        STATE_MAP.put("OR", "Oregon");
        STATE_MAP.put("PA", "Pennsylvania");
        STATE_MAP.put("PE", "Prince Edward Island");
        STATE_MAP.put("PR", "Puerto Rico");
        STATE_MAP.put("QC", "Quebec");
        STATE_MAP.put("RI", "Rhode Island");
        STATE_MAP.put("SK", "Saskatchewan");
        STATE_MAP.put("SC", "South Carolina");
        STATE_MAP.put("SD", "South Dakota");
        STATE_MAP.put("TN", "Tennessee");
        STATE_MAP.put("TX", "Texas");
        STATE_MAP.put("UT", "Utah");
        STATE_MAP.put("VT", "Vermont");
        STATE_MAP.put("VI", "Virgin Islands");
        STATE_MAP.put("VA", "Virginia");
        STATE_MAP.put("WA", "Washington");
        STATE_MAP.put("WV", "West Virginia");
        STATE_MAP.put("WI", "Wisconsin");
        STATE_MAP.put("WY", "Wyoming");
        STATE_MAP.put("YT", "Yukon Territory");
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_edit_address);
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z = true;
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EditAddressDataPasser data = (EditAddressDataPasser) getArguments().getSerializable("addressData");
        this.mAddress = data.getCustomerAddress();
        this.mAvailableAddressTypes = data.getAvailableTypes();
        if (this.mAddress == null) {
            z = false;
        }
        this.mIsEdit = z;
        if (this.mIsEdit) {
            this.mAvailableAddressTypes.add(0, this.mAddress.getAddressType());
        } else {
            this.mAddress = new CustomerAddress();
            this.mAddress.setDefaultAddress(false);
            this.mAddress.setAllowPromotionsToAddress(false);
            this.mAddress.setAddressType((AddressType) this.mAvailableAddressTypes.get(0));
            this.mAddress.setPhone(null);
        }
        refreshAvailableElements();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.address_delete, menu);
        menu.findItem(C2358R.C2357id.action_delete).setVisible(this.mIsEdit);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_delete /*2131821892*/:
                this.mCustomerModule.removeAddress(this.mAddress, this.mCustomerModule.getCurrentProfile(), new C29621());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_edit_address, null);
        this.mElementsContainer = (LinearLayout) view.findViewById(C2358R.C2357id.address_elements_container);
        this.mAddressTypeContainer = view.findViewById(C2358R.C2357id.address_type_container);
        this.mAddressTypeLabel = (TextView) view.findViewById(C2358R.C2357id.address_type_label);
        this.mAddressTypeContainer.setOnClickListener(this);
        this.mSaveButton = (Button) view.findViewById(C2358R.C2357id.save_button);
        this.mSaveButton.setEnabled(false);
        this.mSaveButton.setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (view == this.mAddressTypeContainer) {
            Bundle args = new Bundle();
            args.putInt("CURRENT_ADDRESS_TYPE_KEY", this.mAvailableAddressTypes.indexOf(this.mAddress.getAddressType()));
            startChooseAddressTypeActivity(args);
        } else if (view == this.mSaveButton) {
            if (this.mAddress.getAddressElements().size() == 4) {
                if (((AddressElement) this.mAddress.getAddressElements().get(0)).getAddressElementType().name().equals("State")) {
                    String stateAlias = ((AddressAliasValue) ((AddressElement) this.mAddress.getAddressElements().get(0)).getValue().get(0)).getAlias().trim();
                    if (stateAlias.length() == 2) {
                        ((AddressAliasValue) ((AddressElement) this.mAddress.getAddressElements().get(0)).getValue().get(0)).setAlias((String) STATE_MAP.get(stateAlias.toUpperCase()));
                    }
                }
                if (((AddressElement) this.mAddress.getAddressElements().get(1)).getAddressElementType().name().equals("City")) {
                    String[] cityParts = ((AddressAliasValue) ((AddressElement) this.mAddress.getAddressElements().get(1)).getValue().get(0)).getAlias().trim().split("\\s+");
                    for (int i = 0; i < cityParts.length; i++) {
                        cityParts[i] = cityParts[i].substring(0, 1).toUpperCase() + cityParts[i].substring(1);
                    }
                    String tempCity = "";
                    for (String cityPart : cityParts) {
                        tempCity = tempCity + cityPart + " ";
                    }
                    ((AddressAliasValue) ((AddressElement) this.mAddress.getAddressElements().get(1)).getValue().get(0)).setAlias(tempCity.trim());
                }
            }
            this.mCustomerModule.validateAddress(this.mAddress, this.mCustomerModule.getCurrentProfile(), new C29652());
        }
    }

    private void startChooseAddressTypeActivity(Bundle args) {
        Ensighten.evaluateEvent(this, "startChooseAddressTypeActivity", new Object[]{args});
        args.putSerializable("availableTypes", (ArrayList) this.mAvailableAddressTypes);
        startActivityForResult(ChooseAddressTypeActivity.class, args, 11534);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11534 && resultCode == -1) {
            this.mAddress.setAddressType(AddressType.values()[data.getIntExtra("selected_address_type", 0)]);
            this.mAddressTypeLabel.setText(CustomerAddress.getAddressLabel(this.mAddress.getAddressType(), getActivity()));
            updateSaveButton();
        }
    }

    private void updateSaveButton() {
        Ensighten.evaluateEvent(this, "updateSaveButton", null);
        boolean valid = true;
        for (AddressElement element : this.mAddress.getAddressElements()) {
            if (element.getValue() != null) {
                if (((AddressAliasValue) element.getValue().get(0)).getAlias() == null) {
                }
            }
            valid = false;
        }
        this.mSaveButton.setEnabled(valid);
    }

    private void refreshAvailableElements() {
        Ensighten.evaluateEvent(this, "refreshAvailableElements", null);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerModule.getAddressElements(this.mCustomerModule.getCurrentProfile(), new C29663());
    }

    private void refreshViews() {
        Ensighten.evaluateEvent(this, "refreshViews", null);
        if (this.mElementsContainer != null) {
            AddressElement element;
            List<AddressElement> elements;
            if (this.mIsEdit) {
                elements = new ArrayList();
                for (AddressElement element2 : this.mAddress.getAddressElements()) {
                    elements.add(Math.min(this.mResult.getAddressElementTypes().indexOf(element2.getAddressElementType()), elements.size()), element2);
                }
                this.mAddress.setAddressElements(elements);
            } else {
                elements = new ArrayList();
                for (AddressElementType type : this.mResult.getAddressElementTypes()) {
                    element2 = new AddressElement();
                    element2.setAddressElementType(type);
                    new AddressAliasValue().setAliasType(AddressAliasType.Kanji);
                    element2.setValue(Arrays.asList(new AddressAliasValue[]{value}));
                    elements.add(element2);
                }
                this.mAddress.setAddressElements(elements);
            }
            for (final AddressElement element22 : this.mAddress.getAddressElements()) {
                final EditText editText = new EditText(getActivity());
                String value = ((AddressAliasValue) element22.getValue().get(0)).getAlias();
                if (value == null) {
                    editText.setHint(CustomerAddress.getElementLabel(element22, getActivity()));
                } else {
                    editText.setText(value);
                }
                editText.setTag(element22);
                editText.addTextChangedListener(new TextWatcher() {

                    /* renamed from: com.mcdonalds.app.account.EditAddressFragment$4$1 */
                    class C29671 implements DialogInterface.OnClickListener {
                        C29671() {
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                            editText.setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$600", new Object[]{EditAddressFragment.this}));
                            EditAddressFragment.access$602(EditAddressFragment.this, null);
                        }
                    }

                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{charSequence, new Integer(i), new Integer(i2), new Integer(i3)});
                        EditAddressFragment.access$602(EditAddressFragment.this, editText.getText().toString());
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{charSequence, new Integer(i), new Integer(i2), new Integer(i3)});
                    }

                    public void afterTextChanged(Editable editable) {
                        Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{editable});
                        String value = editable.toString();
                        AddressElementValidationRule rule = (AddressElementValidationRule) Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$400", new Object[]{EditAddressFragment.this}).get(element22.getAddressElementType());
                        Pattern pattern = null;
                        try {
                            pattern = Pattern.compile(rule.getValidationRegex());
                        } catch (PatternSyntaxException e) {
                        }
                        boolean isValid = false;
                        if (pattern != null) {
                            if (!pattern.matcher(value).find() || value.length() >= rule.getValidationLength()) {
                                isValid = false;
                            } else {
                                isValid = true;
                            }
                        }
                        if (isValid) {
                            ((AddressAliasValue) element22.getValue().get(0)).setAlias(value);
                            EditAddressFragment.access$700(EditAddressFragment.this);
                            return;
                        }
                        UIUtils.showGlobalAlertDialog(EditAddressFragment.this.getActivity(), EditAddressFragment.this.getString(C2658R.string.alert_error_title), EditAddressFragment.this.getString(C2658R.string.error_unknown), new C29671());
                        DataLayerManager.getInstance().recordError("Field empty");
                        UIUtils.dismissKeyboard(EditAddressFragment.this.getActivity(), editText);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditAddressFragment", "access$800", new Object[]{EditAddressFragment.this}).setEnabled(false);
                    }
                });
                this.mElementsContainer.addView(editText);
            }
            this.mAddressTypeLabel.setText(CustomerAddress.getAddressLabel(this.mAddress.getAddressType(), getActivity()));
        }
    }
}
