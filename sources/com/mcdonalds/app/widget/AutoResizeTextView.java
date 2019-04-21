package com.mcdonalds.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import com.ensighten.Ensighten;

public class AutoResizeTextView extends TextView {
    private final RectF _availableSpaceRect;
    private boolean _initialized;
    private int _maxLines;
    private float _maxTextSize;
    private float _minTextSize;
    private TextPaint _paint;
    private final SizeTester _sizeTester;
    private float _spacingAdd;
    private float _spacingMult;
    private int _widthLimit;

    private interface SizeTester {
        int onTestSize(int i, RectF rectF);
    }

    /* renamed from: com.mcdonalds.app.widget.AutoResizeTextView$1 */
    class C38691 implements SizeTester {
        final RectF textRect = new RectF();

        C38691() {
        }

        @TargetApi(16)
        public int onTestSize(int suggestedSize, RectF availableSPace) {
            String text;
            Ensighten.evaluateEvent(this, "onTestSize", new Object[]{new Integer(suggestedSize), availableSPace});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$000", new Object[]{AutoResizeTextView.this}).setTextSize((float) suggestedSize);
            TransformationMethod transformationMethod = AutoResizeTextView.this.getTransformationMethod();
            if (transformationMethod != null) {
                text = transformationMethod.getTransformation(AutoResizeTextView.this.getText(), AutoResizeTextView.this).toString();
            } else {
                text = AutoResizeTextView.this.getText().toString();
            }
            if (AutoResizeTextView.this.getMaxLines() == 1) {
                this.textRect.bottom = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$000", new Object[]{AutoResizeTextView.this}).getFontSpacing();
                this.textRect.right = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$000", new Object[]{AutoResizeTextView.this}).measureText(text);
            } else {
                StaticLayout layout = new StaticLayout(text, Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$000", new Object[]{AutoResizeTextView.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$100", new Object[]{AutoResizeTextView.this}), Alignment.ALIGN_NORMAL, Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$200", new Object[]{AutoResizeTextView.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.AutoResizeTextView", "access$300", new Object[]{AutoResizeTextView.this}), true);
                if (AutoResizeTextView.this.getMaxLines() != -1 && layout.getLineCount() > AutoResizeTextView.this.getMaxLines()) {
                    return 1;
                }
                this.textRect.bottom = (float) layout.getHeight();
                int maxWidth = -1;
                for (int i = 0; i < layout.getLineCount(); i++) {
                    if (((float) maxWidth) < layout.getLineRight(i) - layout.getLineLeft(i)) {
                        maxWidth = ((int) layout.getLineRight(i)) - ((int) layout.getLineLeft(i));
                    }
                }
                this.textRect.right = (float) maxWidth;
            }
            this.textRect.offsetTo(0.0f, 0.0f);
            if (availableSPace.contains(this.textRect)) {
                return -1;
            }
            return 1;
        }
    }

    public AutoResizeTextView(Context context) {
        this(context, null, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this._availableSpaceRect = new RectF();
        this._spacingMult = 1.0f;
        this._spacingAdd = 0.0f;
        this._initialized = false;
        this._minTextSize = TypedValue.applyDimension(2, 12.0f, getResources().getDisplayMetrics());
        this._maxTextSize = getTextSize();
        this._paint = new TextPaint(getPaint());
        if (this._maxLines == 0) {
            this._maxLines = -1;
        }
        this._sizeTester = new C38691();
        this._initialized = true;
    }

    public void setAllCaps(boolean allCaps) {
        Ensighten.evaluateEvent(this, "setAllCaps", new Object[]{new Boolean(allCaps)});
        super.setAllCaps(allCaps);
        adjustTextSize();
    }

    public void setTypeface(Typeface tf) {
        Ensighten.evaluateEvent(this, "setTypeface", new Object[]{tf});
        super.setTypeface(tf);
        adjustTextSize();
    }

    public void setTextSize(float size) {
        Ensighten.evaluateEvent(this, "setTextSize", new Object[]{new Float(size)});
        this._maxTextSize = size;
        adjustTextSize();
    }

    public void setMaxLines(int maxlines) {
        Ensighten.evaluateEvent(this, "setMaxLines", new Object[]{new Integer(maxlines)});
        super.setMaxLines(maxlines);
        this._maxLines = maxlines;
        adjustTextSize();
    }

    public int getMaxLines() {
        Ensighten.evaluateEvent(this, "getMaxLines", null);
        return this._maxLines;
    }

    public void setSingleLine() {
        Ensighten.evaluateEvent(this, "setSingleLine", null);
        super.setSingleLine();
        this._maxLines = 1;
        adjustTextSize();
    }

    public void setSingleLine(boolean singleLine) {
        Ensighten.evaluateEvent(this, "setSingleLine", new Object[]{new Boolean(singleLine)});
        super.setSingleLine(singleLine);
        if (singleLine) {
            this._maxLines = 1;
        } else {
            this._maxLines = -1;
        }
        adjustTextSize();
    }

    public void setLines(int lines) {
        Ensighten.evaluateEvent(this, "setLines", new Object[]{new Integer(lines)});
        super.setLines(lines);
        this._maxLines = lines;
        adjustTextSize();
    }

    public void setTextSize(int unit, float size) {
        Resources r;
        Ensighten.evaluateEvent(this, "setTextSize", new Object[]{new Integer(unit), new Float(size)});
        Context c = getContext();
        if (c == null) {
            r = Resources.getSystem();
        } else {
            r = c.getResources();
        }
        this._maxTextSize = TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
        adjustTextSize();
    }

    public void setLineSpacing(float add, float mult) {
        Ensighten.evaluateEvent(this, "setLineSpacing", new Object[]{new Float(add), new Float(mult)});
        super.setLineSpacing(add, mult);
        this._spacingMult = mult;
        this._spacingAdd = add;
    }

    public void setMinTextSize(float minTextSize) {
        Ensighten.evaluateEvent(this, "setMinTextSize", new Object[]{new Float(minTextSize)});
        this._minTextSize = minTextSize;
        adjustTextSize();
    }

    private void adjustTextSize() {
        Ensighten.evaluateEvent(this, "adjustTextSize", null);
        if (this._initialized) {
            int startSize = (int) this._minTextSize;
            int heightLimit = (getMeasuredHeight() - getCompoundPaddingBottom()) - getCompoundPaddingTop();
            this._widthLimit = (getMeasuredWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
            if (this._widthLimit > 0) {
                this._paint = new TextPaint(getPaint());
                this._availableSpaceRect.right = (float) this._widthLimit;
                this._availableSpaceRect.bottom = (float) heightLimit;
                superSetTextSize(startSize);
            }
        }
    }

    private void superSetTextSize(int startSize) {
        Ensighten.evaluateEvent(this, "superSetTextSize", new Object[]{new Integer(startSize)});
        super.setTextSize(0, (float) binarySearch(startSize, (int) this._maxTextSize, this._sizeTester, this._availableSpaceRect));
    }

    private int binarySearch(int start, int end, SizeTester sizeTester, RectF availableSpace) {
        Ensighten.evaluateEvent(this, "binarySearch", new Object[]{new Integer(start), new Integer(end), sizeTester, availableSpace});
        int lastBest = start;
        int lo = start;
        int hi = end - 1;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int midValCmp = sizeTester.onTestSize(i, availableSpace);
            if (midValCmp < 0) {
                lastBest = lo;
                lo = i + 1;
            } else if (midValCmp <= 0) {
                return i;
            } else {
                hi = i - 1;
                lastBest = hi;
            }
        }
        return lastBest;
    }

    /* Access modifiers changed, original: protected */
    public void onTextChanged(CharSequence text, int start, int before, int after) {
        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{text, new Integer(start), new Integer(before), new Integer(after)});
        super.onTextChanged(text, start, before, after);
        adjustTextSize();
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int width, int height, int oldwidth, int oldheight) {
        Ensighten.evaluateEvent(this, "onSizeChanged", new Object[]{new Integer(width), new Integer(height), new Integer(oldwidth), new Integer(oldheight)});
        super.onSizeChanged(width, height, oldwidth, oldheight);
        if (width != oldwidth || height != oldheight) {
            adjustTextSize();
        }
    }
}
