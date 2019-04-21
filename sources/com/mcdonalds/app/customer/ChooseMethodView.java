package com.mcdonalds.app.customer;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.animation.AnimatorEndListener;
import com.mcdonalds.gma.hongkong.C2658R;

public class ChooseMethodView extends LinearLayout implements OnClickListener {
    private final AnimatorEndListener mCloseListener = new C30111();
    private boolean mEnabled = true;
    private boolean mIsOpen;
    private View mOption1;
    private View mOption2;
    private View mOptionsContainer;
    private int mSelectedOption;
    private SelectionListener mSelectionListener;
    private TextView mSelectionView;

    public interface SelectionListener {
        void onMethodSelected(int i);
    }

    /* renamed from: com.mcdonalds.app.customer.ChooseMethodView$1 */
    class C30111 extends AnimatorEndListener {
        C30111() {
        }

        public void onAnimationEnd(Animator animation) {
            Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animation});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$000", new Object[]{ChooseMethodView.this}).setVisibility(8);
            switch (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$100", new Object[]{ChooseMethodView.this})) {
                case 1:
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$200", new Object[]{ChooseMethodView.this}).setVisibility(8);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$300", new Object[]{ChooseMethodView.this}).setVisibility(0);
                    return;
                case 2:
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$300", new Object[]{ChooseMethodView.this}).setVisibility(8);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$200", new Object[]{ChooseMethodView.this}).setVisibility(0);
                    return;
                default:
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$200", new Object[]{ChooseMethodView.this}).setVisibility(0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ChooseMethodView", "access$300", new Object[]{ChooseMethodView.this}).setVisibility(0);
                    return;
            }
        }
    }

    public ChooseMethodView(Context context) {
        super(context);
        inflate(context);
    }

    public ChooseMethodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.view_choose_sigin_method, this);
        this.mOptionsContainer = findViewById(C2358R.C2357id.container_options);
        this.mSelectionView = (TextView) findViewById(C2358R.C2357id.selection_view);
        this.mSelectionView.setOnClickListener(this);
        this.mOption1 = findViewById(C2358R.C2357id.option_1);
        this.mOption1.setOnClickListener(this);
        this.mOption2 = findViewById(C2358R.C2357id.option_2);
        this.mOption2.setOnClickListener(this);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (this.mEnabled) {
            switch (v.getId()) {
                case C2358R.C2357id.selection_view /*2131821871*/:
                    toggle();
                    return;
                default:
                    chooseOption(v);
                    return;
            }
        }
    }

    private void toggle() {
        Ensighten.evaluateEvent(this, "toggle", null);
        if (this.mIsOpen) {
            close();
        } else {
            open();
        }
    }

    private void open() {
        Ensighten.evaluateEvent(this, "open", null);
        this.mIsOpen = true;
        this.mOptionsContainer.setAlpha(0.0f);
        this.mOptionsContainer.setVisibility(0);
        this.mOptionsContainer.animate().alpha(1.0f).setListener(null).setDuration(150).start();
    }

    private void close() {
        Ensighten.evaluateEvent(this, "close", null);
        this.mIsOpen = false;
        this.mOptionsContainer.animate().alpha(0.0f).setListener(this.mCloseListener).setDuration(150).start();
    }

    private void chooseOption(View v) {
        Ensighten.evaluateEvent(this, "chooseOption", new Object[]{v});
        TextView view = (TextView) v;
        String tag = (String) view.getTag();
        this.mSelectionView.setText(view.getText());
        this.mSelectedOption = Integer.valueOf(tag).intValue();
        this.mSelectionView.setBackgroundResource(C2358R.C2359drawable.bg_spinner);
        this.mSelectionView.setTextColor(getResources().getColor(C2658R.color.mcd_black));
        close();
        if (this.mSelectionListener != null) {
            this.mSelectionListener.onMethodSelected(this.mSelectedOption);
        }
    }

    public int getSelection() {
        Ensighten.evaluateEvent(this, "getSelection", null);
        return this.mSelectedOption;
    }

    public void displayError() {
        Ensighten.evaluateEvent(this, "displayError", null);
        this.mSelectionView.setBackgroundResource(C2358R.C2359drawable.bg_spinner_error);
    }

    public void setSelection(int selection) {
        Ensighten.evaluateEvent(this, "setSelection", new Object[]{new Integer(selection)});
        if (selection == 1) {
            chooseOption(this.mOption1);
        } else if (selection == 2) {
            chooseOption(this.mOption2);
        }
    }

    public boolean isEnabled() {
        Ensighten.evaluateEvent(this, "isEnabled", null);
        return this.mEnabled;
    }

    public void setEnabled(boolean enabled) {
        Ensighten.evaluateEvent(this, "setEnabled", new Object[]{new Boolean(enabled)});
        this.mEnabled = enabled;
    }

    public void setSelectionListener(SelectionListener listener) {
        Ensighten.evaluateEvent(this, "setSelectionListener", new Object[]{listener});
        this.mSelectionListener = listener;
    }
}
