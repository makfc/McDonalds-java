package p070me.grantland.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import p070me.grantland.widget.AutofitHelper.OnTextSizeChangeListener;

/* renamed from: me.grantland.widget.AutofitTextView */
public class AutofitTextView extends TextView implements OnTextSizeChangeListener {
    private AutofitHelper mHelper;

    public AutofitTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutofitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutofitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.mHelper = AutofitHelper.create(this, attrs, defStyle).addOnTextSizeChangeListener(this);
    }

    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        if (this.mHelper != null) {
            this.mHelper.setTextSize(unit, size);
        }
    }

    public void setLines(int lines) {
        super.setLines(lines);
        if (this.mHelper != null) {
            this.mHelper.setMaxLines(lines);
        }
    }

    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        if (this.mHelper != null) {
            this.mHelper.setMaxLines(maxLines);
        }
    }

    public AutofitHelper getAutofitHelper() {
        return this.mHelper;
    }

    public void setSizeToFit() {
        setSizeToFit(true);
    }

    public void setSizeToFit(boolean sizeToFit) {
        this.mHelper.setEnabled(sizeToFit);
    }

    public float getMaxTextSize() {
        return this.mHelper.getMaxTextSize();
    }

    public void setMaxTextSize(float size) {
        this.mHelper.setMaxTextSize(size);
    }

    public void setMaxTextSize(int unit, float size) {
        this.mHelper.setMaxTextSize(unit, size);
    }

    public float getMinTextSize() {
        return this.mHelper.getMinTextSize();
    }

    public void setMinTextSize(int minSize) {
        this.mHelper.setMinTextSize(2, (float) minSize);
    }

    public void setMinTextSize(int unit, float minSize) {
        this.mHelper.setMinTextSize(unit, minSize);
    }

    public float getPrecision() {
        return this.mHelper.getPrecision();
    }

    public void setPrecision(float precision) {
        this.mHelper.setPrecision(precision);
    }

    public void onTextSizeChange(float textSize, float oldTextSize) {
    }
}
