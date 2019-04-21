package com.mcdonalds.app.ordering.checkin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.OrderTableService;
import com.mcdonalds.sdk.modules.models.TableService;
import com.mcdonalds.sdk.modules.models.ZoneDefinitions;
import java.util.Iterator;

public class TableServiceFragment extends URLNavigationFragment {
    private CustomerModule mCustomerModule;
    protected Button mFinishAndPayButton;
    private boolean mIsZoneService;
    private OnClickListener mOnClickListener = new C34531();
    private TableService mTableService;
    protected View mTableServiceTouchableLayout;
    protected EditText mUserInputIdEditText;

    /* renamed from: com.mcdonalds.app.ordering.checkin.TableServiceFragment$1 */
    class C34531 implements OnClickListener {
        C34531() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (v == TableServiceFragment.this.mFinishAndPayButton) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.TableServiceFragment", "access$000", new Object[]{TableServiceFragment.this})) {
                    TableServiceFragment.access$100(TableServiceFragment.this, Integer.parseInt(TableServiceFragment.this.mUserInputIdEditText.getText().toString()));
                    return;
                }
                TableServiceFragment.access$200(TableServiceFragment.this);
            } else if (v == TableServiceFragment.this.mTableServiceTouchableLayout) {
                TableServiceFragment.this.mUserInputIdEditText.requestFocus();
                UIUtils.showKeyboard(TableServiceFragment.this.getContext(), TableServiceFragment.this.mUserInputIdEditText, false);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkin.TableServiceFragment$2 */
    class C34542 implements DialogInterface.OnClickListener {
        C34542() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            TableServiceFragment.this.mUserInputIdEditText.getText().clear();
            TableServiceFragment.this.mUserInputIdEditText.requestFocus();
            UIUtils.showKeyboard(TableServiceFragment.this.getContext(), TableServiceFragment.this.mUserInputIdEditText, false);
        }
    }

    static /* synthetic */ void access$100(TableServiceFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.TableServiceFragment", "access$100", new Object[]{x0, new Integer(x1)});
        x0.setupOrderandProceed(x1);
    }

    static /* synthetic */ void access$200(TableServiceFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkin.TableServiceFragment", "access$200", new Object[]{x0});
        x0.showInvalidInputAlert();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mTableService = this.mCustomerModule.getCurrentStore().getTableService();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (this.mTableService.isTableServiceLocatorEnabled()) {
            view = inflater.inflate(C2658R.layout.fragment_table_service_puck, container, false);
        } else {
            view = inflater.inflate(C2658R.layout.fragment_table_service_zone, container, false);
            this.mIsZoneService = true;
        }
        this.mUserInputIdEditText = (EditText) view.findViewById(C2358R.C2357id.table_service_number);
        this.mFinishAndPayButton = (Button) view.findViewById(C2358R.C2357id.table_service_button);
        this.mTableServiceTouchableLayout = view.findViewById(C2358R.C2357id.table_service_touchable_layout);
        if (!this.mIsZoneService) {
            String tableServiceLocatorMaxNumberValue = String.valueOf(this.mTableService.getTableServiceLocatorMaxNumberValue());
            if (!tableServiceLocatorMaxNumberValue.isEmpty()) {
                int maxLength = tableServiceLocatorMaxNumberValue.length();
                this.mUserInputIdEditText.setFilters(new InputFilter[]{new LengthFilter(maxLength)});
            }
        }
        this.mFinishAndPayButton.setOnClickListener(this.mOnClickListener);
        this.mTableServiceTouchableLayout.setOnClickListener(this.mOnClickListener);
        return view;
    }

    private boolean isNumberEnteredValid() {
        Ensighten.evaluateEvent(this, "isNumberEnteredValid", null);
        if (TextUtils.isEmpty(this.mUserInputIdEditText.getText().toString())) {
            return false;
        }
        int userInputId = Integer.parseInt(this.mUserInputIdEditText.getText().toString());
        if (this.mIsZoneService) {
            Iterator it = this.mTableService.getZoneDefinitionsList().iterator();
            while (it.hasNext()) {
                ZoneDefinitions zoneDefinitions = (ZoneDefinitions) it.next();
                if (zoneDefinitions.getZoneId() == userInputId && zoneDefinitions.isEnable()) {
                    return true;
                }
            }
            return false;
        } else if (userInputId <= 0 || userInputId > this.mTableService.getTableServiceLocatorMaxNumberValue()) {
            return false;
        } else {
            return true;
        }
    }

    private void showInvalidInputAlert() {
        Ensighten.evaluateEvent(this, "showInvalidInputAlert", null);
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.table_service_invalid_message)).setPositiveButton((int) C2658R.string.table_service_invalid_ok, new C34542()).create().show();
        DataLayerManager.getInstance().recordError("Invalid input");
    }

    private void setupOrderandProceed(int id) {
        Ensighten.evaluateEvent(this, "setupOrderandProceed", new Object[]{new Integer(id)});
        OrderTableService orderTableService = new OrderTableService();
        if (this.mIsZoneService) {
            orderTableService.setTablseServiceZoneId(id);
        } else {
            orderTableService.setTableTagId(id);
        }
        OrderingManager.getInstance().getCurrentOrder().setOrderTableService(orderTableService);
        getNavigationActivity().setResult(-1);
        getNavigationActivity().finish();
    }
}
