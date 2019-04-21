package p041io.github.inflationx.calligraphy3;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* renamed from: io.github.inflationx.calligraphy3.CalligraphyTypefaceSpan */
public class CalligraphyTypefaceSpan extends MetricAffectingSpan {
    private final Typeface typeface;

    public CalligraphyTypefaceSpan(Typeface typeface) {
        if (typeface == null) {
            throw new IllegalArgumentException("typeface is null");
        }
        this.typeface = typeface;
    }

    public void updateDrawState(TextPaint drawState) {
        apply(drawState);
    }

    public void updateMeasureState(TextPaint paint) {
        apply(paint);
    }

    private void apply(Paint paint) {
        Typeface oldTypeface = paint.getTypeface();
        int fakeStyle = (oldTypeface != null ? oldTypeface.getStyle() : 0) & (this.typeface.getStyle() ^ -1);
        if ((fakeStyle & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((fakeStyle & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(this.typeface);
    }
}
