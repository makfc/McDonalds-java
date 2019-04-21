package com.mcdonalds.app.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

@SuppressLint({"AppCompatCustomView"})
public class CenteredRadioButton extends RadioButton {
    Drawable buttonDrawable;

    public CenteredRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.buttonDrawable = context.obtainStyledAttributes(attrs, C2358R.styleable.CompoundButton, 0, 0).getDrawable(1);
        setButtonDrawable(17170445);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        super.onDraw(canvas);
        if (this.buttonDrawable != null) {
            this.buttonDrawable.setState(getDrawableState());
            int verticalGravity = getGravity() & 112;
            int height = this.buttonDrawable.getIntrinsicHeight();
            int y = 0;
            switch (verticalGravity) {
                case 16:
                    y = (getHeight() - height) / 2;
                    break;
                case 80:
                    y = getHeight() - height;
                    break;
            }
            int buttonWidth = this.buttonDrawable.getIntrinsicWidth();
            int buttonLeft = (getWidth() - buttonWidth) / 2;
            this.buttonDrawable.setBounds(buttonLeft, y, buttonLeft + buttonWidth, y + height);
            this.buttonDrawable.draw(canvas);
        }
    }
}
