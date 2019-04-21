package com.mcdonalds.app.ordering.pickupmethod.kiosk;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.KioskCheckinResponse;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;

public class KioskPickupMethodFragment extends URLNavigationFragment implements OnClickListener {
    public static final String NAME = KioskPickupMethodFragment.class.getSimpleName();
    private Bitmap mBarcodeBitmap;
    private ImageView mBarcodeImage;
    private TextView mBarcodeNumbers;
    private Order mOrder;
    private OrderingModule mOrderingModule;
    private String mPassword;
    private AsyncListener<OrderResponse> mPreparePaymentListener = new C36293();
    private boolean mRequiresCVV = false;
    private boolean mRequiresPassword = false;

    /* renamed from: com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment$1 */
    class C36271 implements DialogInterface.OnClickListener {
        C36271() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
            dialog.dismiss();
            KioskPickupMethodFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment$3 */
    class C36293 implements AsyncListener<OrderResponse> {
        C36293() {
        }

        public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (KioskPickupMethodFragment.this.isActivityAlive()) {
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$200", new Object[]{KioskPickupMethodFragment.this}).setPreparePaymentResult(response);
                    OrderPayment payment = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$200", new Object[]{KioskPickupMethodFragment.this}).getPayment();
                    payment.setOrderPaymentId(response.getOrderPaymentId());
                    payment.setPaymentDataId(response.getPaymentDataId().intValue());
                    KioskPickupMethodFragment.access$302(KioskPickupMethodFragment.this, response.getRequiresCVV().booleanValue());
                    KioskPickupMethodFragment.access$402(KioskPickupMethodFragment.this, response.getRequiresPassword().booleanValue());
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$300", new Object[]{KioskPickupMethodFragment.this}) && !Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$500", new Object[]{KioskPickupMethodFragment.this})) {
                        KioskPickupMethodFragment.access$600(KioskPickupMethodFragment.this);
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$400", new Object[]{KioskPickupMethodFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$000", new Object[]{KioskPickupMethodFragment.this}) == null) {
                        KioskPickupMethodFragment.access$700(KioskPickupMethodFragment.this);
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$400", new Object[]{KioskPickupMethodFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$000", new Object[]{KioskPickupMethodFragment.this}) != null) {
                        KioskPickupMethodFragment.access$800(KioskPickupMethodFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$000", new Object[]{KioskPickupMethodFragment.this}));
                        return;
                    } else if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$400", new Object[]{KioskPickupMethodFragment.this})) {
                        KioskPickupMethodFragment.access$800(KioskPickupMethodFragment.this, null);
                        return;
                    } else {
                        return;
                    }
                }
                AsyncException.report(exception);
                KioskPickupMethodFragment.this.getActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment$4 */
    class C36304 implements DialogInterface.OnClickListener {
        C36304() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
            dialog.dismiss();
            KioskPickupMethodFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment$6 */
    class C36326 implements AsyncListener<KioskCheckinResponse> {
        C36326() {
        }

        public void onResponse(KioskCheckinResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (KioskPickupMethodFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception == null) {
                KioskPickupMethodFragment.access$902(KioskPickupMethodFragment.this, response.getBarcode());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$1000", new Object[]{KioskPickupMethodFragment.this}).setImageBitmap(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$900", new Object[]{KioskPickupMethodFragment.this}));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$1100", new Object[]{KioskPickupMethodFragment.this}).setText(response.getRandomCode());
                return;
            }
            AsyncException.report(exception);
        }
    }

    static /* synthetic */ String access$002(KioskPickupMethodFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$002", new Object[]{x0, x1});
        x0.mPassword = x1;
        return x1;
    }

    static /* synthetic */ void access$100(KioskPickupMethodFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$100", new Object[]{x0, x1});
        x0.preparePaymentAndCheckin(x1);
    }

    static /* synthetic */ boolean access$302(KioskPickupMethodFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.mRequiresCVV = x1;
        return x1;
    }

    static /* synthetic */ boolean access$402(KioskPickupMethodFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$402", new Object[]{x0, new Boolean(x1)});
        x0.mRequiresPassword = x1;
        return x1;
    }

    static /* synthetic */ void access$600(KioskPickupMethodFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$600", new Object[]{x0});
        x0.promptUserForCVV();
    }

    static /* synthetic */ void access$700(KioskPickupMethodFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$700", new Object[]{x0});
        x0.promptUserForPassword();
    }

    static /* synthetic */ void access$800(KioskPickupMethodFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$800", new Object[]{x0, x1});
        x0.checkin(x1);
    }

    static /* synthetic */ Bitmap access$902(KioskPickupMethodFragment x0, Bitmap x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$902", new Object[]{x0, x1});
        x0.mBarcodeBitmap = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout_kiosk_pickup);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_kiosk_pickup_method, container, false);
        this.mBarcodeImage = (ImageView) rootView.findViewById(C2358R.C2357id.barcode_image);
        this.mBarcodeNumbers = (TextView) rootView.findViewById(C2358R.C2357id.barcode_numbers);
        rootView.findViewById(C2358R.C2357id.done_button).setOnClickListener(this);
        CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (module != null) {
            Store current = module.getCurrentStore();
            if (!(current == null || TextUtils.isEmpty(current.getName()))) {
                TextView storeName = (TextView) rootView.findViewById(C2358R.C2357id.kiosk_store_name);
                storeName.setText(current.getName());
                ((ImageView) rootView.findViewById(C2358R.C2357id.kiosk_info_button)).setOnClickListener(this);
                ((View) storeName.getParent()).setVisibility(0);
            }
        }
        preparePaymentAndCheckin(null);
        setHasOptionsMenu(true);
        return rootView;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return "";
    }

    public void onResume() {
        super.onResume();
        if (this.mBarcodeBitmap != null) {
            this.mBarcodeImage.setImageBitmap(this.mBarcodeBitmap);
        }
    }

    private void promptUserForPassword() {
        Ensighten.evaluateEvent(this, "promptUserForPassword", null);
        final EditText input = new EditText(getNavigationActivity());
        input.setInputType(129);
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.dialog_title_password).setMessage((int) C2658R.string.dialog_msg_password).setCancelable(false).setView(input).setPositiveButton((int) C2658R.string.f6083ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
                dialog.dismiss();
                KioskPickupMethodFragment.access$002(KioskPickupMethodFragment.this, input.getText().toString());
                KioskPickupMethodFragment.access$100(KioskPickupMethodFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$000", new Object[]{KioskPickupMethodFragment.this}));
            }
        }).setNegativeButton((int) C2658R.string.cancel, new C36271()).create().show();
    }

    private void preparePaymentAndCheckin(String password) {
        Ensighten.evaluateEvent(this, "preparePaymentAndCheckin", new Object[]{password});
        if (this.mRequiresCVV && !cvvEntered()) {
            return;
        }
        if (!this.mRequiresPassword || password != null) {
            UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.dialog_preparing_payment));
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                checkin(null);
            } else {
                OrderingManager.getInstance().preparePayment(this.mOrder, this.mPreparePaymentListener);
            }
        }
    }

    private void promptUserForCVV() {
        Ensighten.evaluateEvent(this, "promptUserForCVV", null);
        final EditText input = new EditText(getContext());
        input.setInputType(129);
        MCDAlertDialogBuilder.withContext(getContext()).setTitle((int) C2658R.string.dialog_title_cvv).setMessage((int) C2658R.string.dialog_msg_cvv).setCancelable(false).setView(input).setPositiveButton((int) C2658R.string.f6083ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
                dialog.dismiss();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$200", new Object[]{KioskPickupMethodFragment.this}).getPayment().setCVV(input.getText().toString());
                KioskPickupMethodFragment.access$100(KioskPickupMethodFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment", "access$000", new Object[]{KioskPickupMethodFragment.this}));
            }
        }).setNegativeButton((int) C2658R.string.cancel, new C36304()).create().show();
    }

    private boolean cvvEntered() {
        Ensighten.evaluateEvent(this, "cvvEntered", null);
        return this.mOrder.getPayment().getCVV() != null;
    }

    private void checkin(String password) {
        Ensighten.evaluateEvent(this, "checkin", new Object[]{password});
        if (isActivityAlive()) {
            UIUtils.startActivityIndicator(getContext(), getString(C2658R.string.dialog_checking_in));
            this.mOrderingModule.checkinKiosk(password, new C36326());
        }
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case C2358R.C2357id.done_button /*2131820752*/:
                OrderingManager.getInstance().deleteCurrentOrder();
                bundle.putBoolean("REFRESH_LAST_ORDER", true);
                startActivity(MainActivity.class, bundle);
                return;
            case C2358R.C2357id.kiosk_info_button /*2131821238*/:
                Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
                if (store != null) {
                    bundle.putParcelable("extra_store_detail", store);
                    bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
                    startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
                    return;
                }
                AsyncException.report(getString(C2658R.string.dialog_store_details_unavailable));
                return;
            default:
                return;
        }
    }
}
