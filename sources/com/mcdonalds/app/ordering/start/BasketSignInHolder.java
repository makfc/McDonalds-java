package com.mcdonalds.app.ordering.start;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.TermsOfServiceActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.sdk.services.analytics.JiceArgs;

public class BasketSignInHolder {
    private View mContainer;
    private Context mContext;
    private Button mRegisterButton = ((Button) this.mContainer.findViewById(C2358R.C2357id.register_button));
    private Button mSignInButton = ((Button) this.mContainer.findViewById(C2358R.C2357id.sign_in_button));

    /* renamed from: com.mcdonalds.app.ordering.start.BasketSignInHolder$1 */
    class C36661 implements OnClickListener {
        C36661() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent("/basket", "Start Registration");
            Bundle extras = new Bundle();
            extras.putBoolean("NEED_TO_RETURN_TO_BASKET", true);
            ((URLNavigationActivity) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.BasketSignInHolder", "access$000", new Object[]{BasketSignInHolder.this})).startActivity(TermsOfServiceActivity.class, JiceArgs.EVENT_REGISTER, extras);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.start.BasketSignInHolder$2 */
    class C36672 implements OnClickListener {
        C36672() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent("/basket", "Sign In");
            Bundle extras = new Bundle();
            extras.putBoolean("NEED_TO_RETURN_TO_BASKET", true);
            ((URLNavigationActivity) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.BasketSignInHolder", "access$000", new Object[]{BasketSignInHolder.this})).startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, extras);
        }
    }

    public BasketSignInHolder(View container, Context context) {
        this.mContainer = container;
        this.mContext = context;
        this.mContainer.setVisibility(8);
        this.mRegisterButton.setOnClickListener(new C36661());
        this.mSignInButton.setOnClickListener(new C36672());
    }

    public void setVisible(boolean visible) {
        int i = 0;
        Ensighten.evaluateEvent(this, "setVisible", new Object[]{new Boolean(visible)});
        View view = this.mContainer;
        if (!visible) {
            i = 8;
        }
        view.setVisibility(i);
    }
}
