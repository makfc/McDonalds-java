package com.mcdonalds.app.ordering.payment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.databinding.ActivityPaymentBinding;
import com.mcdonalds.app.ordering.PaymentProviderActivity;
import com.mcdonalds.app.ordering.ThirdPartActivity;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.bagcharge.BagChargeActivity;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.ordering.checkin.ChoosePaymentActivity;
import com.mcdonalds.app.ordering.checkin.QRScanActivity;
import com.mcdonalds.app.ordering.checkin.TableServiceActivity;
import com.mcdonalds.app.ordering.checkout.CheckoutActivity;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.ordering.preparepayment.PaymentSelectionActivity;
import com.mcdonalds.app.ordering.summary.OrderSummaryActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.util.UIUtils.MCDFullScreenAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class PaymentActivity extends URLActionBarActivity implements PaymentView {
    public ActivityPaymentBinding binding;
    private boolean initialized;
    private DataBindingComponent mDataBindingComponent = new C361717();
    private PaymentPresenter mPresenter;

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$11 */
    class C361111 implements OnClickListener {
        C361111() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            dialogInterface.dismiss();
            PaymentActivity.this.finish();
            PaymentActivity.this.startActivity(CheckoutActivity.class);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$12 */
    class C361212 implements View.OnClickListener {
        C361212() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            PaymentActivity.this.backToBasket();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$13 */
    class C361313 implements View.OnClickListener {
        C361313() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ((Activity) v.getContext()).onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$14 */
    class C361414 implements OnClickListener {
        C361414() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            PaymentActivity.this.onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$15 */
    class C361515 implements OnClickListener {
        C361515() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentActivity", "access$000", new Object[]{PaymentActivity.this}).cashNotAcceptedAtCurbsideErrorResolved();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$16 */
    class C361616 implements OnClickListener {
        C361616() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            PaymentActivity.this.finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$17 */
    class C361717 implements DataBindingComponent {
        C361717() {
        }

        public TakeOutButtonStyleAdapter getTakeOutButtonStyleAdapter() {
            Ensighten.evaluateEvent(this, "getTakeOutButtonStyleAdapter", null);
            return new TakeOutButtonStyleAdapter();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$1 */
    class C36181 implements View.OnClickListener {
        C36181() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            PaymentActivity.this.binding.eatinButton.setEnabled(false);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentActivity", "access$000", new Object[]{PaymentActivity.this}).eatIn();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$2 */
    class C36192 implements View.OnClickListener {
        C36192() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            PaymentActivity.this.binding.takeoutButton.setEnabled(false);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentActivity", "access$000", new Object[]{PaymentActivity.this}).takeOut();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$3 */
    class C36203 implements View.OnClickListener {
        C36203() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentActivity", "access$000", new Object[]{PaymentActivity.this}).tableServices();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$4 */
    class C36214 implements PermissionListener {
        C36214() {
        }

        public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
            Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
            if (grantResult == -1) {
                PaymentActivity.this.finish();
            } else {
                PaymentActivity.this.scanQRCode();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$5 */
    class C36225 implements OnClickListener {
        C36225() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            PaymentActivity.this.onBackPressed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$6 */
    class C36236 implements OnClickListener {
        C36236() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            PaymentActivity.this.backToBasket();
            PaymentActivity.this.finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$7 */
    class C36247 implements OnClickListener {
        C36247() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
            dialog.dismiss();
            PaymentActivity.this.finish();
            PaymentActivity.this.startActivity(CheckoutActivity.class);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.payment.PaymentActivity$9 */
    class C36269 implements OnClickListener {
        C36269() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
            dialog.dismiss();
            PaymentActivity.this.finish();
        }
    }

    public class TakeOutButtonStyleAdapter {
        @BindingAdapter
        public void isRed(Button button, boolean isRed) {
            Ensighten.evaluateEvent(this, "isRed", new Object[]{button, new Boolean(isRed)});
            if (isRed) {
                button.setBackgroundResource(C2358R.C2359drawable.button_red);
            } else {
                button.setBackgroundResource(C2358R.C2359drawable.button_light_gray);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityPaymentBinding) DataBindingUtil.setContentView(this, C2658R.layout.activity_payment, this.mDataBindingComponent);
        setTitle(C2658R.string.title_activity_order_checkin);
        this.mPresenter = new PaymentPresenter(this, this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mPresenter.setComingFromBagCharges(extras.getBoolean("FROM_BAG_CHARGE"));
        }
        this.binding.setPresenter(this.mPresenter);
        if (savedInstanceState != null) {
            this.initialized = savedInstanceState.getBoolean("initialized");
        }
        if (!this.initialized) {
            this.mPresenter.initialize();
        }
        this.binding.eatinButton.setEnabled(true);
        this.binding.eatinButton.setOnClickListener(new C36181());
        this.binding.takeoutButton.setEnabled(true);
        this.binding.takeoutButton.setOnClickListener(new C36192());
        this.binding.tableServiceButton.setOnClickListener(new C36203());
        this.mPresenter.onStart();
        this.initialized = true;
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle outState) {
        Ensighten.evaluateEvent(this, "onSaveInstanceState", new Object[]{outState});
        outState.putBoolean("initialized", this.initialized);
        super.onSaveInstanceState(outState);
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.mPresenter.setComingFromBagCharges(extras.getBoolean("FROM_BAG_CHARGE"));
        }
        this.mPresenter.initialize();
    }

    public void scanQRCode() {
        Ensighten.evaluateEvent(this, "scanQRCode", null);
        if (isPermissionGranted("android.permission.CAMERA")) {
            startActivityForResult(QRScanActivity.class, "qrscan", 51);
            return;
        }
        requestPermission("android.permission.CAMERA", 2, C2658R.string.permission_explanation_camera, new C36214());
    }

    public void showInvalidQRCodeError() {
        Ensighten.evaluateEvent(this, "showInvalidQRCodeError", null);
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.alert_error_title).setMessage((int) C2658R.string.ecp_error_1303).setPositiveButton((int) C2658R.string.f6083ok, new C36225()).create().show();
        DataLayerManager.getInstance().recordError("Invalid QR code");
    }

    public void showInvalidRestaurantError() {
        Ensighten.evaluateEvent(this, "showInvalidRestaurantError", null);
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.alert_error_title).setMessage((int) C2658R.string.error_finding_store).setPositiveButton((int) C2658R.string.f6083ok, new C36236()).create().show();
    }

    public void showActivityIndicator(int messageResourceId) {
        Ensighten.evaluateEvent(this, "showActivityIndicator", new Object[]{new Integer(messageResourceId)});
        UIUtils.startActivityIndicator(this, null, getString(messageResourceId), false);
    }

    public void stopActivityIndicator() {
        Ensighten.evaluateEvent(this, "stopActivityIndicator", null);
        UIUtils.stopActivityIndicator();
    }

    public void showOrderUnavailableAtPODError() {
        Ensighten.evaluateEvent(this, "showOrderUnavailableAtPODError", null);
        startActivity(AlertActivity.class, "check_out_pod_unavailable");
    }

    public void showRestaurantMismatchError() {
        Ensighten.evaluateEvent(this, "showRestaurantMismatchError", null);
        startActivityForResult(AlertActivity.class, "RestaurantMismatchFragment", 4087);
    }

    public void continueToTableServices() {
        Ensighten.evaluateEvent(this, "continueToTableServices", null);
        startActivityForResult(TableServiceActivity.class, 12303);
    }

    public void continueToBagCharges() {
        Ensighten.evaluateEvent(this, "continueToBagCharges", null);
        startActivityForResult(BagChargeActivity.class, 10892);
    }

    public void requestCVV(int maxLength) {
        Ensighten.evaluateEvent(this, "requestCVV", new Object[]{new Integer(maxLength)});
        final EditText input = new EditText(this);
        input.setInputType(18);
        input.setFilters(new InputFilter[]{new LengthFilter(maxLength)});
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.dialog_title_cvv).setMessage((int) C2658R.string.dialog_msg_cvv).setCancelable(false).setView(input).setPositiveButton((int) C2658R.string.f6083ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
                dialog.dismiss();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentActivity", "access$000", new Object[]{PaymentActivity.this}).setCVVEntered(input.getText().toString());
            }
        }).setNegativeButton((int) C2658R.string.cancel, new C36247()).create().show();
    }

    public void requestPassword() {
        Ensighten.evaluateEvent(this, "requestPassword", null);
        final EditText input = new EditText(this);
        input.setInputType(129);
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.dialog_title_password).setMessage((int) C2658R.string.dialog_msg_password).setCancelable(false).setView(input).setPositiveButton((int) C2658R.string.f6083ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(whichButton)});
                dialog.dismiss();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.payment.PaymentActivity", "access$000", new Object[]{PaymentActivity.this}).setPasswordEntered(input.getText().toString());
            }
        }).setNegativeButton((int) C2658R.string.cancel, new C36269()).create().show();
    }

    public void showOrderErrors(int errorType, int productErrorCode, List<String> problematicProductCodes, List<String> problematicOfferCodes, boolean hidePositive) {
        Ensighten.evaluateEvent(this, "showOrderErrors", new Object[]{new Integer(errorType), new Integer(productErrorCode), problematicProductCodes, problematicOfferCodes, new Boolean(hidePositive)});
        String fragmentName = "";
        int requestCode = 21;
        switch (errorType) {
            case 1:
                fragmentName = "check_in_items_out_of_stock";
                break;
            case 2:
                fragmentName = "check_in_items_unavailable";
                break;
            case 3:
                fragmentName = "check_in_all_items_unavailable";
                requestCode = 22;
                break;
            case 4:
                fragmentName = "check_in_price_different";
                break;
            case 5:
                fragmentName = "check_in_items_time_restriction";
                break;
            case 6:
                fragmentName = "check_in_offers_not_valid";
                break;
        }
        if (!fragmentName.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, productErrorCode);
            bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES, (ArrayList) problematicProductCodes);
            bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_OFFERS_CODES, (ArrayList) problematicOfferCodes);
            startActivityForResult(AlertActivity.class, fragmentName, bundle, requestCode);
        }
    }

    public void showFatalError(String title, String errorMessage) {
        Ensighten.evaluateEvent(this, "showFatalError", new Object[]{title, errorMessage});
        if (title == null) {
            title = getString(C2658R.string.error_checkin);
        }
        MCDAlertDialogBuilder.withContext(this).setTitle(title).setMessage(errorMessage).setCancelable(false).setPositiveButton(getString(C2658R.string.button_dismiss), new C361111()).create().show();
    }

    public void showFatalError(String errorMessage) {
        Ensighten.evaluateEvent(this, "showFatalError", new Object[]{errorMessage});
        showFatalError(null, errorMessage);
    }

    public void showOrderNotReadyError() {
        Ensighten.evaluateEvent(this, "showOrderNotReadyError", null);
        MCDFullScreenAlertDialogBuilder.withContext(this).setMessage(C2658R.string.error_communication_indicating_to_the_customer).setPositiveButtonText(C2658R.string.back_to_my_order, new C361212()).create().show();
    }

    public void showOrderNotReadyToAcceptError() {
        Ensighten.evaluateEvent(this, "showOrderNotReadyToAcceptError", null);
        MCDFullScreenAlertDialogBuilder.withContext(this).setMessage(C2658R.string.error_communication_indicating_to_the_customer).setPositiveButtonText(C2658R.string.back_to_check_in, new C361313()).create().show();
    }

    public void showCashNotAcceptedAtCurbsideError() {
        Ensighten.evaluateEvent(this, "showCashNotAcceptedAtCurbsideError", null);
        MCDAlertDialogBuilder.withContext(this).setTitle(getString(C2658R.string.curbside_header)).setMessage(getString(C2658R.string.curbside_message)).setPositiveButton((int) C2658R.string.f6083ok, new C361515()).setNegativeButton((int) C2658R.string.cancel, new C361414()).create().show();
    }

    public void showPaymentChooser(String qrCode) {
        Ensighten.evaluateEvent(this, "showPaymentChooser", new Object[]{qrCode});
        Bundle extras = new Bundle();
        extras.putString("qr_code", qrCode);
        startActivityForResult(ChoosePaymentActivity.class, "choose_payment", extras, 4082);
    }

    public void showPaymentSelection(LinkedHashSet<PaymentMode> paymentModes) {
        Ensighten.evaluateEvent(this, "showPaymentSelection", new Object[]{paymentModes});
        Bundle extras = new Bundle();
        extras.putBoolean("FROM_ORDER_CHECKIN", true);
        extras.putSerializable("payment_types", paymentModes);
        startActivityForResult(PaymentSelectionActivity.class, "select_payment", extras, 4081);
    }

    public Activity getActivityForAlipay() {
        Ensighten.evaluateEvent(this, "getActivityForAlipay", null);
        return this;
    }

    public void backToBasket() {
        Ensighten.evaluateEvent(this, "backToBasket", null);
        startActivity(BasketActivity.class, "basket");
    }

    public void finish() {
        Ensighten.evaluateEvent(this, "finish", null);
        super.finish();
    }

    public void openThirdPartyPaymentUrl(String url) {
        Ensighten.evaluateEvent(this, "openThirdPartyPaymentUrl", new Object[]{url});
        Bundle extras = new Bundle();
        extras.putString("payment_url", url);
        startActivityForResult(ThirdPartActivity.class, extras, 4084);
    }

    public void showLargeOrderAlert() {
        Ensighten.evaluateEvent(this, "showLargeOrderAlert", null);
        AppUtils.showLargeOrderAlert(this);
    }

    public void continueToOrderSummary() {
        Ensighten.evaluateEvent(this, "continueToOrderSummary", null);
        startActivity(OrderSummaryActivity.class, "order_summary");
    }

    public void showPaymentError(String title, String msg) {
        Ensighten.evaluateEvent(this, "showPaymentError", new Object[]{title, msg});
        AlertDialog dialog = MCDAlertDialogBuilder.withContext(this).setMessage(msg).setPositiveButton((int) C2658R.string.f6083ok, new C361616()).create();
        if (!title.isEmpty()) {
            dialog.setTitle(title);
        }
        dialog.show();
    }

    public void requestPaymentInfo(String url, String registerReturnURL) {
        Ensighten.evaluateEvent(this, "requestPaymentInfo", new Object[]{url, registerReturnURL});
        Bundle args = new Bundle();
        args.putString("EXTRA_URL", url);
        args.putBoolean("EXTRA_ONE_TIME_PAYMENT", true);
        args.putString("EXTRA_REGISTER_RETURN_URL", registerReturnURL);
        startActivityForResult(PaymentProviderActivity.class, args, 42803);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 21:
                    int productErrorCode;
                    if (data != null) {
                        productErrorCode = data.getIntExtra(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, -1);
                    } else {
                        productErrorCode = -1;
                    }
                    this.mPresenter.outOfStockErrorResolved(productErrorCode);
                    return;
                case 22:
                    backToBasket();
                    return;
                case 51:
                    this.mPresenter.setQRCodeScanned(data.getExtras().getString("result_code"));
                    return;
                case 4081:
                    this.mPresenter.paymentSelected((PaymentCard) data.getExtras().getParcelable("PaymentSelectionFragment.DATA_KEY"));
                    return;
                case 4082:
                    this.mPresenter.paymentSelected();
                    return;
                case 4084:
                    this.mPresenter.thirdPartyFinished();
                    return;
                case 4087:
                    this.mPresenter.restaurantMismatchResolved();
                    return;
                case 12303:
                    this.mPresenter.tableServicesFinished(true);
                    return;
                case 42803:
                    this.mPresenter.paymentInfoEntered(data.getStringExtra("EXTRA_ONE_TIME_PAYMENT"));
                    return;
                default:
                    return;
            }
        } else if (resultCode == 39) {
            startActivity(MenuActivity.class);
        } else if (requestCode == 51) {
            finish();
        } else if (requestCode == 12303) {
            this.mPresenter.tableServicesFinished(false);
        } else if (requestCode == 4084) {
            finish();
        } else if (requestCode == 4087) {
            backToBasket();
            finish();
        } else if (requestCode == 22) {
            backToBasket();
            finish();
        } else if (resultCode == 15521) {
            this.mPresenter.handlePaymentRegistrationError();
        } else if (requestCode == 42803) {
            finish();
        }
    }

    public void onResume() {
        super.onResume();
        this.binding.eatinButton.setEnabled(true);
        this.binding.takeoutButton.setEnabled(true);
    }
}
