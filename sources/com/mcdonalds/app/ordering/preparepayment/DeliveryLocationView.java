package com.mcdonalds.app.ordering.preparepayment;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class DeliveryLocationView extends FrameLayout {
    private TextView mTimeLabel;

    public DeliveryLocationView(Context context) {
        super(context);
        inflate(context);
    }

    public DeliveryLocationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.delivery_location, this);
        if (!isInEditMode()) {
            initWidgets();
        }
    }

    public TextView getTimeLabel() {
        Ensighten.evaluateEvent(this, "getTimeLabel", null);
        return this.mTimeLabel;
    }

    private void initWidgets() {
        Ensighten.evaluateEvent(this, "initWidgets", null);
        this.mTimeLabel = (TextView) findViewById(C2358R.C2357id.delivery_time_label);
        findViewById(C2358R.C2357id.delivery_disclosure_image).setVisibility(8);
    }

    public void update(Order order) {
        Ensighten.evaluateEvent(this, "update", new Object[]{order});
        String address = order.getDeliveryAddress().getFullAddress();
        String headerDeliverToString = "";
        if (order.getDeliveryDate() != null) {
            if (order.isNormalOrder() && Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
                String edt = LocalDataManager.getSharedInstance().getEdtString();
                headerDeliverToString = headerDeliverToString + McDonalds.getContext().getString(C2658R.string.estimated_delivery_range_3, new Object[]{edt, address});
            } else {
                String time = UIUtils.formatScheduledDeliveryTime(order.getDeliveryDate());
                headerDeliverToString = McDonalds.getContext().getString(C2658R.string.estimated_delivery_range_2, new Object[]{"<b>" + time + "</b>", "<b>" + address + "</b>"});
            }
        }
        setDeliveryHeaderText(Html.fromHtml(headerDeliverToString));
    }

    public void setDeliveryHeaderText(Spanned headerText) {
        Ensighten.evaluateEvent(this, "setDeliveryHeaderText", new Object[]{headerText});
        this.mTimeLabel.setText(headerText);
    }
}
