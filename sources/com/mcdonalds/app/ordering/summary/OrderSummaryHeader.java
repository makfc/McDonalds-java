package com.mcdonalds.app.ordering.summary;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Date;

public class OrderSummaryHeader {
    private final LinearLayout mDisplayMessageContainer;
    private final LinearLayout mDisplayMessageContainerCash;
    private final LinearLayout mEDTContainer;
    private final TextView mEstimatedDeliveryTime;
    private final TextView mEstimatedDeliveryTimeMessage;
    private final TextView mOrderDisplayMessage;
    private final TextView mOrderNumber;
    private final TextView mOrderPlaced;
    private final TextView mOrderPlacedMessage;
    private final TextView mOrderReceivedTime;
    private final Button mTrackOrderButton;

    public OrderSummaryHeader(View view) {
        this.mOrderPlaced = (TextView) view.findViewById(C2358R.C2357id.order_place_title);
        this.mOrderPlacedMessage = (TextView) view.findViewById(C2358R.C2357id.order_place_message);
        this.mOrderNumber = (TextView) view.findViewById(C2358R.C2357id.order_number_label);
        this.mOrderDisplayMessage = (TextView) view.findViewById(C2358R.C2357id.order_display_msg);
        this.mDisplayMessageContainerCash = (LinearLayout) view.findViewById(C2358R.C2357id.display_msg_container_cash);
        this.mDisplayMessageContainer = (LinearLayout) view.findViewById(C2358R.C2357id.display_msg_container);
        this.mEDTContainer = (LinearLayout) view.findViewById(C2358R.C2357id.edt_container);
        this.mOrderReceivedTime = (TextView) view.findViewById(C2358R.C2357id.order_received_time);
        this.mEstimatedDeliveryTimeMessage = (TextView) view.findViewById(C2358R.C2357id.edt_msg);
        this.mEstimatedDeliveryTime = (TextView) view.findViewById(C2358R.C2357id.edt);
        this.mTrackOrderButton = (Button) view.findViewById(C2358R.C2357id.track_order_button);
    }

    public TextView getOrderNumber() {
        Ensighten.evaluateEvent(this, "getOrderNumber", null);
        return this.mOrderNumber;
    }

    public LinearLayout getDisplayMessageContainer() {
        Ensighten.evaluateEvent(this, "getDisplayMessageContainer", null);
        return this.mDisplayMessageContainer;
    }

    public LinearLayout getDisplayMessageContainerCash() {
        Ensighten.evaluateEvent(this, "getDisplayMessageContainerCash", null);
        return this.mDisplayMessageContainerCash;
    }

    public LinearLayout getEDTContainer() {
        Ensighten.evaluateEvent(this, "getEDTContainer", null);
        return this.mEDTContainer;
    }

    public TextView getEstimatedDeliveryTimeMessage() {
        Ensighten.evaluateEvent(this, "getEstimatedDeliveryTimeMessage", null);
        return this.mEstimatedDeliveryTimeMessage;
    }

    public TextView getOrderReceivedTime() {
        Ensighten.evaluateEvent(this, "getOrderReceivedTime", null);
        return this.mOrderReceivedTime;
    }

    public TextView getEstimatedDeliveryTime() {
        Ensighten.evaluateEvent(this, "getEstimatedDeliveryTime", null);
        return this.mEstimatedDeliveryTime;
    }

    public Button getTrackOrderButton() {
        Ensighten.evaluateEvent(this, "getTrackOrderButton", null);
        return this.mTrackOrderButton;
    }

    public void showLargeOrderAlert(boolean isDelivery, Context context, Date orderDate) {
        Ensighten.evaluateEvent(this, "showLargeOrderAlert", new Object[]{new Boolean(isDelivery), context, orderDate});
        this.mOrderPlaced.setText(C2658R.string.additional_step_needed);
        String orderDisplayMsg = context.getString(C2658R.string.pending_order_status);
        if (!isDelivery) {
            orderDisplayMsg = orderDisplayMsg + "\n" + UIUtils.formatDateMonthDayYear(orderDate);
        }
        this.mOrderDisplayMessage.setText(orderDisplayMsg);
        this.mOrderPlacedMessage.setVisibility(0);
        String phoneNumber;
        if (Configuration.getSharedInstance().getBooleanForKey("interface.confirmationNeededOrders.callCenterCallsUser")) {
            this.mOrderPlacedMessage.setText(C2658R.string.alert_restaurant_calls_text);
        } else if (isDelivery) {
            phoneNumber = Configuration.getSharedInstance().getStringForKey("interface.confirmationNeededOrders.deliveryCallCenter");
            this.mOrderPlacedMessage.setText(context.getString(C2658R.string.alert_user_calls_text_delivery, new Object[]{phoneNumber}));
        } else {
            phoneNumber = Configuration.getSharedInstance().getStringForKey("interface.confirmationNeededOrders.mcdonaldsCallCenter");
            this.mOrderPlacedMessage.setText(context.getString(C2658R.string.alert_user_calls_text, new Object[]{phoneNumber}));
        }
    }
}
