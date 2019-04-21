package p041io.github.inflationx.viewpump;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: io.github.inflationx.viewpump.ViewPumpActivityFactory */
interface ViewPumpActivityFactory {
    @Nullable
    View onActivityCreateView(View view, View view2, String str, Context context, AttributeSet attributeSet);
}
