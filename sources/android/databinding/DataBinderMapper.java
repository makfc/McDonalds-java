package android.databinding;

import android.view.View;
import com.mcdonalds.app.databinding.ActivityBagChargeBinding;
import com.mcdonalds.app.databinding.ActivityCheckoutBinding;
import com.mcdonalds.app.databinding.ActivityChoiceSelectorBinding;
import com.mcdonalds.app.databinding.ActivityPaymentBinding;
import com.mcdonalds.app.databinding.BoundProductDetailsItemBinding;
import com.mcdonalds.app.databinding.DeliveryLocationBinding;
import com.mcdonalds.app.databinding.InvoiceBinding;
import com.mcdonalds.app.databinding.OrderRemarkBinding;
import com.mcdonalds.app.databinding.PickupLocationBinding;
import com.mcdonalds.app.databinding.ProductQuantityControlsBinding;
import com.mcdonalds.app.databinding.TotalCaloriePriceBinding;
import com.mcdonalds.app.databinding.ViewCheckinTutorialBinding;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;

class DataBinderMapper {
    static final int TARGET_MIN_SDK = 19;

    private static class InnerBrLookup {
        static String[] sKeys = new String[]{"_all", "bagCharge", "checked", "continueButtonText", "deliveryFee", "deliveryHeaderText", "discount", "enableContinueButton", "enableMinusButton", "enablePaymentContainer", "enablePlusButton", "hatButtonResourceId", "invoiceEnabled", "isLoading", "nameDetails", "payer", "paymentMethodName", "presenter", BusinessArgs.KEY_PRODUCT_NAME, "productUplift", AnalyticsArgs.TRANSACTION_ITEM_QUANTITY, "remark", "remarkEnabled", "showBagCharge", "showCheckBox", "showDeliveryFee", "showDeliveryLocation", "showDisclosureArrow", "showDiscount", "showHatButton", "showInfoButton", "showInvoice", "showLocationSelection", "showNameDetails", "showNoPaymentRequired", "showOrderRemark", "showPaymentContainer", "showPickupLocation", "showQRScanFirstTime", "showSubtotal", "showTableService", "showTax", "showTimeRestrictionWarning", "showTotalCalories", "showUnavailablePODs", "showUplift", "specialInstructions", "storeName", "subtotal", "tax", "thumbnailImageUrl", "timeRestrictionWarning", "total", "totalCalories", "totalPrice", "unavailablePODsMessage"};

        private InnerBrLookup() {
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View view, int layoutId) {
        switch (layoutId) {
            case C2658R.layout.activity_bag_charge /*2130968608*/:
                return ActivityBagChargeBinding.bind(view, bindingComponent);
            case C2658R.layout.activity_checkout /*2130968610*/:
                return ActivityCheckoutBinding.bind(view, bindingComponent);
            case C2658R.layout.activity_choice_selector /*2130968611*/:
                return ActivityChoiceSelectorBinding.bind(view, bindingComponent);
            case C2658R.layout.activity_payment /*2130968618*/:
                return ActivityPaymentBinding.bind(view, bindingComponent);
            case C2658R.layout.bound_product_details_item /*2130968642*/:
                return BoundProductDetailsItemBinding.bind(view, bindingComponent);
            case C2658R.layout.delivery_location /*2130968666*/:
                return DeliveryLocationBinding.bind(view, bindingComponent);
            case C2658R.layout.invoice /*2130968884*/:
                return InvoiceBinding.bind(view, bindingComponent);
            case C2658R.layout.order_remark /*2130968944*/:
                return OrderRemarkBinding.bind(view, bindingComponent);
            case C2658R.layout.pickup_location /*2130968953*/:
                return PickupLocationBinding.bind(view, bindingComponent);
            case C2658R.layout.product_quantity_controls /*2130968960*/:
                return ProductQuantityControlsBinding.bind(view, bindingComponent);
            case C2658R.layout.total_calorie_price /*2130968986*/:
                return TotalCaloriePriceBinding.bind(view, bindingComponent);
            case C2658R.layout.view_checkin_tutorial /*2130968989*/:
                return ViewCheckinTutorialBinding.bind(view, bindingComponent);
            default:
                return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View[] views, int layoutId) {
        return null;
    }
}
