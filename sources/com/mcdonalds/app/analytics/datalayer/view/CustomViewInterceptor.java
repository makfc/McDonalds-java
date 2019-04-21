package com.mcdonalds.app.analytics.datalayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.ensighten.Ensighten;
import p041io.github.inflationx.viewpump.InflateRequest;
import p041io.github.inflationx.viewpump.InflateResult;
import p041io.github.inflationx.viewpump.Interceptor;
import p041io.github.inflationx.viewpump.Interceptor.Chain;

public class CustomViewInterceptor implements Interceptor {
    public InflateResult intercept(Chain chain) {
        Ensighten.evaluateEvent(this, "intercept", new Object[]{chain});
        InflateRequest request = chain.request();
        View view = inflateView(request.name(), request.context(), request.attrs());
        if (view != null) {
            return InflateResult.builder().view(view).name(view.getClass().getName()).context(request.context()).attrs(request.attrs()).build();
        }
        return chain.proceed(request);
    }

    @Nullable
    private View inflateView(String name, Context context, AttributeSet attrs) {
        Ensighten.evaluateEvent(this, "inflateView", new Object[]{name, context, attrs});
        if ("Button".equals(name)) {
            return new DataLayerButton(context, attrs);
        }
        if ("TextView".equals(name)) {
            return new DataLayerTextView(context, attrs);
        }
        if ("ListView".equals(name)) {
            return new DataLayerListView(context, attrs);
        }
        if ("FrameLayout".equals(name)) {
            return new DataLayerFrameLayout(context, attrs);
        }
        if ("LinearLayout".equals(name)) {
            return new DataLayerLinearLayout(context, attrs);
        }
        if ("RelativeLayout".equals(name)) {
            return new DataLayerRelativeLayout(context, attrs);
        }
        if ("android.support.design.widget.TabLayout".equals(name)) {
            return new DataLayerTabLayout(context, attrs);
        }
        if ("ImageButton".equals(name)) {
            return new DataLayerImageButton(context, attrs);
        }
        return null;
    }
}
