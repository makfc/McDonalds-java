package com.mcdonalds.app.ordering.summary;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class OrderDetailsHolder {
    private final View mCaloriesWarningView;
    private final TextView mEmailMsg;
    private final TextView mOrderNumber;
    private final LinearLayout mOrderReceiptContainer;
    private final TextView mOrderTimestamp;

    public OrderDetailsHolder(View view) {
        this.mEmailMsg = (TextView) view.findViewById(C2358R.C2357id.email_msg_label);
        this.mOrderNumber = (TextView) view.findViewById(C2358R.C2357id.order_details_number_label);
        this.mOrderTimestamp = (TextView) view.findViewById(C2358R.C2357id.order_timestamp_label);
        this.mCaloriesWarningView = view.findViewById(C2358R.C2357id.energy_warning_view);
        this.mOrderReceiptContainer = (LinearLayout) view.findViewById(C2358R.C2357id.order_receipt_container);
    }

    public TextView getEmailMsg() {
        Ensighten.evaluateEvent(this, "getEmailMsg", null);
        return this.mEmailMsg;
    }

    public TextView getOrderNumber() {
        Ensighten.evaluateEvent(this, "getOrderNumber", null);
        return this.mOrderNumber;
    }

    public TextView getOrderTimestamp() {
        Ensighten.evaluateEvent(this, "getOrderTimestamp", null);
        return this.mOrderTimestamp;
    }

    public LinearLayout getOrderReceiptContainer() {
        Ensighten.evaluateEvent(this, "getOrderReceiptContainer", null);
        return this.mOrderReceiptContainer;
    }

    public View getCaloriesWarningView() {
        Ensighten.evaluateEvent(this, "getCaloriesWarningView", null);
        return this.mCaloriesWarningView;
    }
}
