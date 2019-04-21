package com.mcdonalds.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.checkout.CheckoutPresenter;
import com.mcdonalds.app.util.BindingAdapters;
import com.mcdonalds.gma.hongkong.C2658R;

public class ActivityCheckoutBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(42);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout activityCheckout;
    public final TextView bagCharge;
    public final LinearLayout bagChargeContainer;
    public final TextView bagChargeValue;
    public final ListView basketList;
    public final TextView choosePaymentLabel;
    public final LinearLayout containerTotals;
    public final Button continueButton;
    public final TextView deliveryFee;
    public final TextView deliveryFeeValue;
    public final LinearLayout deliveryRow;
    public final DeliveryLocationBinding deliveryView;
    public final TextView discount;
    public final LinearLayout discountContainer;
    public final TextView discountValue;
    public final ImageView imageView;
    public final InvoiceBinding invoiceContainer;
    public final ImageView ivAliImg;
    private long mDirtyFlags = -1;
    private CheckoutPresenter mPresenter;
    private final FrameLayout mboundView19;
    private final FrameLayout mboundView2;
    private final FrameLayout mboundView20;
    private final FrameLayout mboundView24;
    private final FrameLayout mboundView3;
    public final TextView noPaymentRequiredLabel;
    public final TextView orderTotal;
    public final TextView orderTotalValue;
    public final TextView payWithLabel;
    public final LinearLayout paymentContainer;
    public final PickupLocationBinding pickupView;
    public final ProgressBar progress;
    public final ViewCheckinTutorialBinding qrScanFtuView;
    public final OrderRemarkBinding remarkContainer;
    public final LinearLayout subtotalContainer;
    public final TextView tax;
    public final LinearLayout taxContainer;
    public final TextView taxValue;
    public final TextView timeRestrictionWarning;
    public final TextView total;
    public final LinearLayout totalContainer;
    public final TextView totalEnergy;
    public final TextView totalValue;
    public final TextView unavailablePods;

    static {
        sIncludes.setIncludes(2, new String[]{"pickup_location"}, new int[]{26}, new int[]{C2658R.layout.pickup_location});
        sIncludes.setIncludes(20, new String[]{"invoice"}, new int[]{29}, new int[]{C2658R.layout.invoice});
        sIncludes.setIncludes(24, new String[]{"view_checkin_tutorial"}, new int[]{30}, new int[]{C2658R.layout.view_checkin_tutorial});
        sIncludes.setIncludes(3, new String[]{"delivery_location"}, new int[]{27}, new int[]{C2658R.layout.delivery_location});
        sIncludes.setIncludes(19, new String[]{"order_remark"}, new int[]{28}, new int[]{C2658R.layout.order_remark});
        sViewsWithIds.put(C2358R.C2357id.basket_list, 31);
        sViewsWithIds.put(C2358R.C2357id.delivery_fee, 32);
        sViewsWithIds.put(C2358R.C2357id.order_total, 33);
        sViewsWithIds.put(C2358R.C2357id.bag_charge, 34);
        sViewsWithIds.put(C2358R.C2357id.discount, 35);
        sViewsWithIds.put(C2358R.C2357id.tax, 36);
        sViewsWithIds.put(C2358R.C2357id.total_container, 37);
        sViewsWithIds.put(C2358R.C2357id.total, 38);
        sViewsWithIds.put(C2358R.C2357id.pay_with_label, 39);
        sViewsWithIds.put(C2358R.C2357id.iv_ali_img, 40);
        sViewsWithIds.put(C2358R.C2357id.imageView, 41);
    }

    public ActivityCheckoutBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 6);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 42, sIncludes, sViewsWithIds);
        this.activityCheckout = (FrameLayout) bindings[0];
        this.activityCheckout.setTag(null);
        this.bagCharge = (TextView) bindings[34];
        this.bagChargeContainer = (LinearLayout) bindings[8];
        this.bagChargeContainer.setTag(null);
        this.bagChargeValue = (TextView) bindings[9];
        this.bagChargeValue.setTag(null);
        this.basketList = (ListView) bindings[31];
        this.choosePaymentLabel = (TextView) bindings[17];
        this.choosePaymentLabel.setTag(null);
        this.containerTotals = (LinearLayout) bindings[1];
        this.containerTotals.setTag(null);
        this.continueButton = (Button) bindings[23];
        this.continueButton.setTag(null);
        this.deliveryFee = (TextView) bindings[32];
        this.deliveryFeeValue = (TextView) bindings[5];
        this.deliveryFeeValue.setTag(null);
        this.deliveryRow = (LinearLayout) bindings[4];
        this.deliveryRow.setTag(null);
        this.deliveryView = (DeliveryLocationBinding) bindings[27];
        setContainedBinding(this.deliveryView);
        this.discount = (TextView) bindings[35];
        this.discountContainer = (LinearLayout) bindings[10];
        this.discountContainer.setTag(null);
        this.discountValue = (TextView) bindings[11];
        this.discountValue.setTag(null);
        this.imageView = (ImageView) bindings[41];
        this.invoiceContainer = (InvoiceBinding) bindings[29];
        setContainedBinding(this.invoiceContainer);
        this.ivAliImg = (ImageView) bindings[40];
        this.mboundView19 = (FrameLayout) bindings[19];
        this.mboundView19.setTag(null);
        this.mboundView2 = (FrameLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView20 = (FrameLayout) bindings[20];
        this.mboundView20.setTag(null);
        this.mboundView24 = (FrameLayout) bindings[24];
        this.mboundView24.setTag(null);
        this.mboundView3 = (FrameLayout) bindings[3];
        this.mboundView3.setTag(null);
        this.noPaymentRequiredLabel = (TextView) bindings[18];
        this.noPaymentRequiredLabel.setTag(null);
        this.orderTotal = (TextView) bindings[33];
        this.orderTotalValue = (TextView) bindings[7];
        this.orderTotalValue.setTag(null);
        this.payWithLabel = (TextView) bindings[39];
        this.paymentContainer = (LinearLayout) bindings[16];
        this.paymentContainer.setTag(null);
        this.pickupView = (PickupLocationBinding) bindings[26];
        setContainedBinding(this.pickupView);
        this.progress = (ProgressBar) bindings[25];
        this.progress.setTag(null);
        this.qrScanFtuView = (ViewCheckinTutorialBinding) bindings[30];
        setContainedBinding(this.qrScanFtuView);
        this.remarkContainer = (OrderRemarkBinding) bindings[28];
        setContainedBinding(this.remarkContainer);
        this.subtotalContainer = (LinearLayout) bindings[6];
        this.subtotalContainer.setTag(null);
        this.tax = (TextView) bindings[36];
        this.taxContainer = (LinearLayout) bindings[12];
        this.taxContainer.setTag(null);
        this.taxValue = (TextView) bindings[13];
        this.taxValue.setTag(null);
        this.timeRestrictionWarning = (TextView) bindings[22];
        this.timeRestrictionWarning.setTag(null);
        this.total = (TextView) bindings[38];
        this.totalContainer = (LinearLayout) bindings[37];
        this.totalEnergy = (TextView) bindings[14];
        this.totalEnergy.setTag(null);
        this.totalValue = (TextView) bindings[15];
        this.totalValue.setTag(null);
        this.unavailablePods = (TextView) bindings[21];
        this.unavailablePods.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        Ensighten.evaluateEvent(this, "invalidateAll", null);
        synchronized (this) {
            this.mDirtyFlags = 17179869184L;
        }
        this.pickupView.invalidateAll();
        this.deliveryView.invalidateAll();
        this.remarkContainer.invalidateAll();
        this.invoiceContainer.invalidateAll();
        this.qrScanFtuView.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Missing block: B:8:0x0019, code skipped:
            if (r6.pickupView.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:10:0x0021, code skipped:
            if (r6.deliveryView.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:12:0x0029, code skipped:
            if (r6.remarkContainer.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:14:0x0031, code skipped:
            if (r6.invoiceContainer.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:16:0x0039, code skipped:
            if (r6.qrScanFtuView.hasPendingBindings() != false) goto L_?;
     */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:27:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:29:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:30:?, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:31:?, code skipped:
            return false;
     */
    public boolean hasPendingBindings() {
        /*
        r6 = this;
        r0 = 1;
        r1 = "hasPendingBindings";
        r2 = 0;
        com.ensighten.Ensighten.evaluateEvent(r6, r1, r2);
        monitor-enter(r6);
        r2 = r6.mDirtyFlags;	 Catch:{ all -> 0x003d }
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r6);	 Catch:{ all -> 0x003d }
    L_0x0011:
        return r0;
    L_0x0012:
        monitor-exit(r6);	 Catch:{ all -> 0x003d }
        r1 = r6.pickupView;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x001b:
        r1 = r6.deliveryView;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x0023:
        r1 = r6.remarkContainer;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x002b:
        r1 = r6.invoiceContainer;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x0033:
        r1 = r6.qrScanFtuView;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x0011;
    L_0x003b:
        r0 = 0;
        goto L_0x0011;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x003d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.databinding.ActivityCheckoutBinding.hasPendingBindings():boolean");
    }

    public void setPresenter(CheckoutPresenter Presenter) {
        Ensighten.evaluateEvent(this, "setPresenter", new Object[]{Presenter});
        updateRegistration(0, Presenter);
        this.mPresenter = Presenter;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        Ensighten.evaluateEvent(this, "onFieldChange", new Object[]{new Integer(localFieldId), object, new Integer(fieldId)});
        switch (localFieldId) {
            case 0:
                return onChangePresenter((CheckoutPresenter) object, fieldId);
            case 1:
                return onChangeQrScanFtuView((ViewCheckinTutorialBinding) object, fieldId);
            case 2:
                return onChangeRemarkContainer((OrderRemarkBinding) object, fieldId);
            case 3:
                return onChangePickupView((PickupLocationBinding) object, fieldId);
            case 4:
                return onChangeInvoiceContainer((InvoiceBinding) object, fieldId);
            case 5:
                return onChangeDeliveryView((DeliveryLocationBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePresenter(CheckoutPresenter Presenter, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePresenter", new Object[]{Presenter, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            case 1:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                }
                return true;
            case 3:
                synchronized (this) {
                    this.mDirtyFlags |= 4294967296L;
                }
                return true;
            case 4:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
                return true;
            case 6:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                }
                return true;
            case 7:
                synchronized (this) {
                    this.mDirtyFlags |= 2147483648L;
                }
                return true;
            case 9:
                synchronized (this) {
                    this.mDirtyFlags |= 2097152;
                }
                return true;
            case 13:
                synchronized (this) {
                    this.mDirtyFlags |= 64;
                }
                return true;
            case 16:
                synchronized (this) {
                    this.mDirtyFlags |= 8388608;
                }
                return true;
            case 23:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                }
                return true;
            case 25:
                synchronized (this) {
                    this.mDirtyFlags |= 512;
                }
                return true;
            case 26:
                synchronized (this) {
                    this.mDirtyFlags |= 256;
                }
                return true;
            case 28:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
                return true;
            case 31:
                synchronized (this) {
                    this.mDirtyFlags |= 67108864;
                }
                return true;
            case 34:
                synchronized (this) {
                    this.mDirtyFlags |= 16777216;
                }
                return true;
            case 35:
                synchronized (this) {
                    this.mDirtyFlags |= 33554432;
                }
                return true;
            case 36:
                synchronized (this) {
                    this.mDirtyFlags |= 4194304;
                }
                return true;
            case 37:
                synchronized (this) {
                    this.mDirtyFlags |= 128;
                }
                return true;
            case 38:
                synchronized (this) {
                    this.mDirtyFlags |= 8589934592L;
                }
                return true;
            case 39:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
                return true;
            case 41:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                }
                return true;
            case 42:
                synchronized (this) {
                    this.mDirtyFlags |= 1073741824;
                }
                return true;
            case 44:
                synchronized (this) {
                    this.mDirtyFlags |= 268435456;
                }
                return true;
            case 48:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
                return true;
            case 49:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
                return true;
            case 51:
                synchronized (this) {
                    this.mDirtyFlags |= 536870912;
                }
                return true;
            case 52:
                synchronized (this) {
                    this.mDirtyFlags |= 1048576;
                }
                return true;
            case 53:
                synchronized (this) {
                    this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                }
                return true;
            case 55:
                synchronized (this) {
                    this.mDirtyFlags |= 134217728;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeQrScanFtuView(ViewCheckinTutorialBinding QrScanFtuView, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangeQrScanFtuView", new Object[]{QrScanFtuView, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 2;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeRemarkContainer(OrderRemarkBinding RemarkContainer, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangeRemarkContainer", new Object[]{RemarkContainer, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 4;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangePickupView(PickupLocationBinding PickupView, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangePickupView", new Object[]{PickupView, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 8;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeInvoiceContainer(InvoiceBinding InvoiceContainer, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangeInvoiceContainer", new Object[]{InvoiceContainer, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 16;
                }
                return true;
            default:
                return false;
        }
    }

    private boolean onChangeDeliveryView(DeliveryLocationBinding DeliveryView, int fieldId) {
        Ensighten.evaluateEvent(this, "onChangeDeliveryView", new Object[]{DeliveryView, new Integer(fieldId)});
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 32;
                }
                return true;
            default:
                return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        long dirtyFlags;
        Ensighten.evaluateEvent(this, "executeBindings", null);
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean presenterShowPaymentContainer = false;
        CheckoutPresenter presenter = this.mPresenter;
        boolean presenterShowInvoice = false;
        boolean presenterIsLoading = false;
        boolean presenterShowDeliveryFee = false;
        boolean presenterShowTax = false;
        String presenterContinueButtonText = null;
        boolean presenterShowTimeRestrictionWarning = false;
        String presenterTimeRestrictionWarning = null;
        String presenterDiscount = null;
        String presenterUnavailablePODsMessage = null;
        String presenterDeliveryFee = null;
        boolean PresenterIsLoading1 = false;
        String presenterSubtotal = null;
        boolean presenterShowPickupLocation = false;
        boolean presenterShowNoPaymentRequired = false;
        boolean presenterShowOrderRemark = false;
        boolean presenterShowQRScanFirstTime = false;
        boolean presenterShowSubtotal = false;
        String presenterBagCharge = null;
        String presenterPaymentMethodName = null;
        String presenterTax = null;
        String presenterTotal = null;
        boolean presenterShowDiscount = false;
        boolean presenterShowBagCharge = false;
        boolean presenterShowDeliveryLocation = false;
        boolean presenterEnablePaymentContainer = false;
        boolean presenterEnableContinueButton = false;
        boolean presenterShowUnavailablePODs = false;
        String presenterTotalCalories = null;
        if ((34359738305L & dirtyFlags) != 0) {
            if (!((17184063489L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowPaymentContainer = presenter.getShowPaymentContainer();
            }
            if (!((17246978049L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowInvoice = presenter.getShowInvoice();
            }
            if (!((17179869697L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowDeliveryFee = presenter.getShowDeliveryFee();
            }
            if (!((17180000257L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowTax = presenter.getShowTax();
            }
            if (!((21474836481L & dirtyFlags) == 0 || presenter == null)) {
                presenterContinueButtonText = presenter.getContinueButtonText();
            }
            if (!((18253611009L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowTimeRestrictionWarning = presenter.getShowTimeRestrictionWarning();
            }
            if (!((17716740097L & dirtyFlags) == 0 || presenter == null)) {
                presenterTimeRestrictionWarning = presenter.getTimeRestrictionWarning();
            }
            if (!((17179934721L & dirtyFlags) == 0 || presenter == null)) {
                presenterDiscount = presenter.getDiscount();
            }
            if (!((17314086913L & dirtyFlags) == 0 || presenter == null)) {
                presenterUnavailablePODsMessage = presenter.getUnavailablePODsMessage();
            }
            if (!((17179870209L & dirtyFlags) == 0 || presenter == null)) {
                presenterDeliveryFee = presenter.getDeliveryFee();
            }
            if ((17179869249L & dirtyFlags) != 0) {
                if (presenter != null) {
                    PresenterIsLoading1 = presenter.getIsLoading();
                }
                presenterIsLoading = !PresenterIsLoading1;
            }
            if (!((17179873281L & dirtyFlags) == 0 || presenter == null)) {
                presenterSubtotal = presenter.getSubtotal();
            }
            if (!((17179869313L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowPickupLocation = presenter.getShowPickupLocation();
            }
            if (!((17196646401L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowNoPaymentRequired = presenter.getShowNoPaymentRequired();
            }
            if (!((17213423617L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowOrderRemark = presenter.getShowOrderRemark();
            }
            if (!((25769803777L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowQRScanFirstTime = presenter.getShowQRScanFirstTime();
            }
            if (!((17179871233L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowSubtotal = presenter.getShowSubtotal();
            }
            if (!((17179885569L & dirtyFlags) == 0 || presenter == null)) {
                presenterBagCharge = presenter.getBagCharge();
            }
            if (!((17188257793L & dirtyFlags) == 0 || presenter == null)) {
                presenterPaymentMethodName = presenter.getPaymentMethodName();
            }
            if (!((17180131329L & dirtyFlags) == 0 || presenter == null)) {
                presenterTax = presenter.getTax();
            }
            if (!((17180917761L & dirtyFlags) == 0 || presenter == null)) {
                presenterTotal = presenter.getTotal();
            }
            if (!((17179901953L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowDiscount = presenter.getShowDiscount();
            }
            if (!((17179877377L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowBagCharge = presenter.getShowBagCharge();
            }
            if (!((17179869441L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowDeliveryLocation = presenter.getShowDeliveryLocation();
            }
            if (!((17181966337L & dirtyFlags) == 0 || presenter == null)) {
                presenterEnablePaymentContainer = presenter.getEnablePaymentContainer();
            }
            if (!((19327352833L & dirtyFlags) == 0 || presenter == null)) {
                presenterEnableContinueButton = presenter.getEnableContinueButton();
            }
            if (!((17448304641L & dirtyFlags) == 0 || presenter == null)) {
                presenterShowUnavailablePODs = presenter.getShowUnavailablePODs();
            }
            if (!((17180393473L & dirtyFlags) == 0 || presenter == null)) {
                presenterTotalCalories = presenter.getTotalCalories();
            }
        }
        if ((17179877377L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.bagChargeContainer, presenterShowBagCharge);
        }
        if ((17179885569L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.bagChargeValue, presenterBagCharge);
        }
        if ((17188257793L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.choosePaymentLabel, presenterPaymentMethodName);
        }
        if ((17179869249L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.containerTotals, presenterIsLoading);
            BindingAdapters.booleanVisibility(this.progress, PresenterIsLoading1);
        }
        if ((19327352833L & dirtyFlags) != 0) {
            this.continueButton.setEnabled(presenterEnableContinueButton);
        }
        if ((21474836481L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.continueButton, presenterContinueButtonText);
        }
        if ((17179870209L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.deliveryFeeValue, presenterDeliveryFee);
        }
        if ((17179869697L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.deliveryRow, presenterShowDeliveryFee);
        }
        if ((17179869185L & dirtyFlags) != 0) {
            this.deliveryView.setPresenter(presenter);
            this.invoiceContainer.setPresenter(presenter);
            this.pickupView.setPresenter(presenter);
            this.remarkContainer.setPresenter(presenter);
        }
        if ((17179901953L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.discountContainer, presenterShowDiscount);
        }
        if ((17179934721L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.discountValue, presenterDiscount);
        }
        if ((17213423617L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.mboundView19, presenterShowOrderRemark);
        }
        if ((17179869313L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.mboundView2, presenterShowPickupLocation);
        }
        if ((17246978049L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.mboundView20, presenterShowInvoice);
        }
        if ((25769803777L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.mboundView24, presenterShowQRScanFirstTime);
        }
        if ((17179869441L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.mboundView3, presenterShowDeliveryLocation);
        }
        if ((17196646401L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.noPaymentRequiredLabel, presenterShowNoPaymentRequired);
        }
        if ((17179873281L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.orderTotalValue, presenterSubtotal);
        }
        if ((17181966337L & dirtyFlags) != 0) {
            this.paymentContainer.setEnabled(presenterEnablePaymentContainer);
        }
        if ((17184063489L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.paymentContainer, presenterShowPaymentContainer);
        }
        if ((17179871233L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.subtotalContainer, presenterShowSubtotal);
        }
        if ((17180000257L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.taxContainer, presenterShowTax);
        }
        if ((17180131329L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.taxValue, presenterTax);
        }
        if ((17716740097L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.timeRestrictionWarning, presenterTimeRestrictionWarning);
        }
        if ((18253611009L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.timeRestrictionWarning, presenterShowTimeRestrictionWarning);
        }
        if ((17180393473L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.totalEnergy, presenterTotalCalories);
        }
        if ((17180917761L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.totalValue, presenterTotal);
        }
        if ((17314086913L & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.unavailablePods, presenterUnavailablePODsMessage);
        }
        if ((17448304641L & dirtyFlags) != 0) {
            BindingAdapters.booleanVisibility(this.unavailablePods, presenterShowUnavailablePODs);
        }
        ViewDataBinding.executeBindingsOn(this.pickupView);
        ViewDataBinding.executeBindingsOn(this.deliveryView);
        ViewDataBinding.executeBindingsOn(this.remarkContainer);
        ViewDataBinding.executeBindingsOn(this.invoiceContainer);
        ViewDataBinding.executeBindingsOn(this.qrScanFtuView);
    }

    public static ActivityCheckoutBinding bind(View view, DataBindingComponent bindingComponent) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.databinding.ActivityCheckoutBinding", "bind", new Object[]{view, bindingComponent});
        if ("layout/activity_checkout_0".equals(view.getTag())) {
            return new ActivityCheckoutBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
