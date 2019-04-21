package p070me.grantland.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.WeakHashMap;

/* renamed from: me.grantland.widget.AutofitLayout */
public class AutofitLayout extends FrameLayout {
    private boolean mEnabled;
    private WeakHashMap<View, AutofitHelper> mHelpers = new WeakHashMap();
    private float mMinTextSize;
    private float mPrecision;

    public AutofitLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutofitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutofitLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        boolean sizeToFit = true;
        int minTextSize = -1;
        float precision = -1.0f;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, C4625R.styleable.AutofitTextView, defStyle, 0);
            sizeToFit = ta.getBoolean(C4625R.styleable.AutofitTextView_sizeToFit, true);
            minTextSize = ta.getDimensionPixelSize(C4625R.styleable.AutofitTextView_minTextSize, -1);
            precision = ta.getFloat(C4625R.styleable.AutofitTextView_precision, -1.0f);
            ta.recycle();
        }
        this.mEnabled = sizeToFit;
        this.mMinTextSize = (float) minTextSize;
        this.mPrecision = precision;
    }

    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        TextView textView = (TextView) child;
        AutofitHelper helper = AutofitHelper.create(textView).setEnabled(this.mEnabled);
        if (this.mPrecision > 0.0f) {
            helper.setPrecision(this.mPrecision);
        }
        if (this.mMinTextSize > 0.0f) {
            helper.setMinTextSize(0, this.mMinTextSize);
        }
        this.mHelpers.put(textView, helper);
    }
}
