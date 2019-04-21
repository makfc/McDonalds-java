package com.mcdonalds.app.ordering.preparepayment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.EditCardActivity;
import com.mcdonalds.app.ordering.MPGSPaymentActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.PaymentProviderActivity;
import com.mcdonalds.app.ordering.preparepayment.PaymentSelectionListAdapterHeaders.CardRow;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.connectors.middleware.model.MWLocation;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import p046se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class PaymentSelectionFragment extends URLNavigationFragment implements OnClickListener {
    private CustomerModule mCustomerModule;
    private boolean mFromOrderCheckin;
    private PaymentSelectionListAdapterHeaders mListAdapterHeaders;
    private StickyListHeadersListView mListView;
    private PaymentMode mPaymentTypeSelected;
    private LinkedHashSet<PaymentMethod> mPaymentTypes;
    private Button mSaveButton;
    private PaymentCard mSelectedCard;

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment$1 */
    class C36361 implements OnItemClickListener {
        C36361() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{adapterView, view, new Integer(i), new Long(l)});
            switch (C36383.f6661x99cade59[Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$000", new Object[]{PaymentSelectionFragment.this}).getPaymentType(i).ordinal()]) {
                case 1:
                    PaymentSelectionFragment.access$102(PaymentSelectionFragment.this, PaymentMode.Cash);
                    return;
                case 2:
                    switch (C36383.f6660xd9d5a7f9[Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$000", new Object[]{PaymentSelectionFragment.this}).getCardRowType(i).ordinal()]) {
                        case 1:
                            PaymentSelectionFragment.access$102(PaymentSelectionFragment.this, PaymentMode.Credit);
                            PaymentSelectionFragment.access$202(PaymentSelectionFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$000", new Object[]{PaymentSelectionFragment.this}).getPaymentCard(i));
                            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$200", new Object[]{PaymentSelectionFragment.this}).isNewCardStub()) {
                                PaymentSelectionFragment.access$300(PaymentSelectionFragment.this);
                                return;
                            }
                            return;
                        case 2:
                            PaymentSelectionFragment.this.onAddPayment(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$000", new Object[]{PaymentSelectionFragment.this}).getPaymentMethodID(i), false);
                            return;
                        case 3:
                            PaymentSelectionFragment.this.onAddPayment(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$000", new Object[]{PaymentSelectionFragment.this}).getPaymentMethodID(i), true);
                            return;
                        default:
                            return;
                    }
                case 3:
                    PaymentSelectionFragment.access$102(PaymentSelectionFragment.this, PaymentMode.ThirdPart);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment$2 */
    class C36372 implements DialogInterface.OnClickListener {
        C36372() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment$3 */
    static /* synthetic */ class C36383 {
        /* renamed from: $SwitchMap$com$mcdonalds$app$ordering$preparepayment$PaymentSelectionListAdapterHeaders$CardRow */
        static final /* synthetic */ int[] f6660xd9d5a7f9 = new int[CardRow.values().length];

        static {
            f6661x99cade59 = new int[PaymentMode.values().length];
            try {
                f6661x99cade59[PaymentMode.Cash.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6661x99cade59[PaymentMode.Credit.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6661x99cade59[PaymentMode.ThirdPart.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f6660xd9d5a7f9[CardRow.Card.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f6660xd9d5a7f9[CardRow.Add.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f6660xd9d5a7f9[CardRow.One_Time.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    static /* synthetic */ PaymentMode access$102(PaymentSelectionFragment x0, PaymentMode x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$102", new Object[]{x0, x1});
        x0.mPaymentTypeSelected = x1;
        return x1;
    }

    static /* synthetic */ PaymentCard access$202(PaymentSelectionFragment x0, PaymentCard x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$202", new Object[]{x0, x1});
        x0.mSelectedCard = x1;
        return x1;
    }

    static /* synthetic */ void access$300(PaymentSelectionFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.preparepayment.PaymentSelectionFragment", "access$300", new Object[]{x0});
        x0.onAddOneClickPayment();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout_cards);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPaymentTypes = new LinkedHashSet();
        this.mFromOrderCheckin = getActivity().getIntent().getExtras().getBoolean("FROM_ORDER_CHECKIN", false);
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            this.mPaymentTypes = (LinkedHashSet) getArguments().getSerializable(URLNavigationActivity.DATA_PASSER_KEY);
        } else {
            displayMethods((LinkedHashSet) getArguments().getSerializable(URLNavigationActivity.DATA_PASSER_KEY));
        }
    }

    private void displayMethods(LinkedHashSet<PaymentMethod> paymentMethods) {
        int isDelivery = 1;
        Ensighten.evaluateEvent(this, "displayMethods", new Object[]{paymentMethods});
        ArrayList<Integer> result = new ArrayList();
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            if (!OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                isDelivery = 0;
            }
            List<MWLocation> locations = store.getLocations();
            if (locations != null && locations.size() > isDelivery) {
                result.addAll(((MWLocation) locations.get(isDelivery)).getPaymentMethods());
            }
        }
        Iterator it = paymentMethods.iterator();
        while (it.hasNext()) {
            PaymentMethod method = (PaymentMethod) it.next();
            if (result.contains(method.getID())) {
                this.mPaymentTypes.add(method);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View group = inflater.inflate(C2658R.layout.fragment_payment_selection, container, false);
        this.mListView = (StickyListHeadersListView) group.findViewById(C2358R.C2357id.listView);
        this.mSaveButton = (Button) group.findViewById(C2358R.C2357id.save_button);
        this.mSaveButton.setOnClickListener(this);
        if (this.mFromOrderCheckin) {
            ((TextView) group.findViewById(C2358R.C2357id.curbside_explanation)).setVisibility(0);
            this.mSaveButton.setText(getResources().getString(C2658R.string.next_button));
        }
        if (ConfigurationUtils.isOneClickPaymentFlow()) {
            ((TextView) group.findViewById(C2358R.C2357id.one_click_cc_add_textView)).setVisibility(0);
        }
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        updateWithProfile(this.mCustomerModule.getCurrentProfile());
        return group;
    }

    private void updateWithProfile(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "updateWithProfile", new Object[]{profile});
        if (profile != null) {
            this.mListAdapterHeaders = new PaymentSelectionListAdapterHeaders(getActivity(), profile, this.mPaymentTypes);
            this.mListView.setAdapter(this.mListAdapterHeaders);
            this.mListView.setChoiceMode(1);
            this.mListView.setOnItemClickListener(new C36361());
        }
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (view == this.mSaveButton) {
            if (this.mPaymentTypeSelected != null) {
                Intent intent;
                switch (this.mPaymentTypeSelected) {
                    case Cash:
                        intent = new Intent();
                        intent.putExtra("Cash", true);
                        getActivity().setResult(-1, intent);
                        break;
                    case Credit:
                        if (this.mSelectedCard != null) {
                            intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("PaymentSelectionFragment.DATA_KEY", this.mSelectedCard);
                            intent.putExtras(bundle);
                            getActivity().setResult(-1, intent);
                            break;
                        }
                        getActivity().setResult(0);
                        break;
                    case ThirdPart:
                        intent = new Intent();
                        intent.putExtra("ThirdPart", true);
                        getActivity().setResult(-1, intent);
                        break;
                    default:
                        getActivity().setResult(0);
                        break;
                }
            }
            getActivity().setResult(0);
            getActivity().finish();
        }
    }

    public void paymentsUpdated() {
        Ensighten.evaluateEvent(this, "paymentsUpdated", null);
        if (this.mCustomerModule != null) {
            updateWithProfile(this.mCustomerModule.getCurrentProfile());
        }
    }

    public void proceedWithOneTimePayment(PaymentCard oneTimecard) {
        Ensighten.evaluateEvent(this, "proceedWithOneTimePayment", new Object[]{oneTimecard});
        this.mSelectedCard = oneTimecard;
        this.mPaymentTypeSelected = PaymentMode.Credit;
        onClick(this.mSaveButton);
    }

    private void onAddOneClickPayment() {
        Ensighten.evaluateEvent(this, "onAddOneClickPayment", null);
        if (!this.mCustomerModule.shouldSaveCard()) {
            this.mSelectedCard = null;
            showMaxPaymentsDialog();
        }
    }

    public void onAddPayment(int paymentID, boolean oneTime) {
        Ensighten.evaluateEvent(this, "onAddPayment", new Object[]{new Integer(paymentID), new Boolean(oneTime)});
        this.mSelectedCard = null;
        if (oneTime) {
            launchAddPaymentScreen(paymentID, oneTime);
        } else if (this.mCustomerModule.shouldSaveCard()) {
            launchAddPaymentScreen(paymentID, oneTime);
        } else {
            showMaxPaymentsDialog();
        }
    }

    private void showMaxPaymentsDialog() {
        Ensighten.evaluateEvent(this, "showMaxPaymentsDialog", null);
        MCDAlertDialogBuilder.withContext(getContext()).setMessage((int) C2658R.string.max_payment_card_error_ordering).setPositiveButton((int) C2658R.string.f6083ok, new C36372()).create().show();
    }

    private void launchAddPaymentScreen(int paymentID, boolean oneTime) {
        Ensighten.evaluateEvent(this, "launchAddPaymentScreen", new Object[]{new Integer(paymentID), new Boolean(oneTime)});
        PaymentMethod paymentMethod = ((OrderingModule) ModuleManager.getModule("ordering")).getPaymentMethodForId(paymentID);
        if (paymentMethod != null && paymentMethod.getPaymentMode().equals(PaymentMode.Credit)) {
            Class classToLaunch;
            int requestCode;
            int paymentId = paymentMethod.getID().intValue();
            boolean useNativeInterface = Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.useNativeInterface");
            Bundle args = new Bundle();
            if (Configuration.getSharedInstance().getBooleanForKey("supportedPaymentMethods.creditCard.isMPGS")) {
                args.putInt("EXTRA_PAYMENT_ID", paymentId);
                args.putBoolean("EXTRA_ONE_TIME_PAYMENT", oneTime);
                classToLaunch = MPGSPaymentActivity.class;
                requestCode = 42804;
            } else if (useNativeInterface) {
                args.putParcelable("payment_method", paymentMethod);
                args.putBoolean("one_time_payment", oneTime);
                classToLaunch = EditCardActivity.class;
                requestCode = 42807;
            } else {
                args.putInt("EXTRA_PAYMENT_ID", paymentId);
                args.putBoolean("EXTRA_ONE_TIME_PAYMENT", oneTime);
                classToLaunch = PaymentProviderActivity.class;
                requestCode = 42803;
            }
            startActivityForResult(classToLaunch, args, requestCode);
        }
    }
}
