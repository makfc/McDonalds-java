package android.support.p000v4.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.support.annotation.RequiresApi;

@TargetApi(23)
@RequiresApi
/* renamed from: android.support.v4.print.PrintHelperApi23 */
class PrintHelperApi23 extends PrintHelperApi20 {
    /* Access modifiers changed, original: protected */
    public Builder copyAttributes(PrintAttributes other) {
        Builder b = super.copyAttributes(other);
        if (other.getDuplexMode() != 0) {
            b.setDuplexMode(other.getDuplexMode());
        }
        return b;
    }

    PrintHelperApi23(Context context) {
        super(context);
        this.mIsMinMarginsHandlingCorrect = false;
    }
}
