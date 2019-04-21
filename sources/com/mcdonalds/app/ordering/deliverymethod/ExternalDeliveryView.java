package com.mcdonalds.app.ordering.deliverymethod;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.ensighten.Ensighten;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.List;
import java.util.Map;

public class ExternalDeliveryView extends FrameLayout {
    private View mBasketWarning;
    private final OnClickListener mOnClickLaunchDelivery = new C34711();

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.ExternalDeliveryView$1 */
    class C34711 implements OnClickListener {
        C34711() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            List<Map<String, Object>> configMap = (List) Configuration.getSharedInstance().getValueForKey("modules.delivery.deliveryUrls");
            String url = "";
            if (configMap != null) {
                String languageId = Configuration.getSharedInstance().getCurrentLanguageTag();
                for (int index = 0; index < configMap.size() && url.isEmpty(); index++) {
                    Map<String, Object> deliveryUrl = (Map) configMap.get(index);
                    if (((String) deliveryUrl.get("language")).equals(languageId)) {
                        url = (String) deliveryUrl.get(NativeProtocol.IMAGE_URL_KEY);
                    }
                }
            }
            if (!url.isEmpty()) {
                ExternalDeliveryView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            }
        }
    }

    public ExternalDeliveryView(Context context) {
        super(context);
        inflate(context);
    }

    public ExternalDeliveryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.view_external_delivery, this);
        if (!isInEditMode()) {
            findViewById(C2358R.C2357id.button_launch_mcdelivery).setOnClickListener(this.mOnClickLaunchDelivery);
            this.mBasketWarning = findViewById(C2358R.C2357id.label_basket_warning);
            refresh();
        }
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        Order order = OrderingManager.getInstance().getCurrentOrder();
        if (order != null) {
            this.mBasketWarning.setVisibility(order.isEmpty() ? 0 : 8);
        }
    }
}
