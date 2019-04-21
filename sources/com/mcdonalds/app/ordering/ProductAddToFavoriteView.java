package com.mcdonalds.app.ordering;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;

public class ProductAddToFavoriteView extends FrameLayout {
    private Button mCancelButton;
    private EditText mNameInput;
    private final OnClickListener mOnClickCancel = new C33831();
    private final OnClickListener mOnClickSave = new C33842();
    private Button mSaveButton;

    /* renamed from: com.mcdonalds.app.ordering.ProductAddToFavoriteView$1 */
    class C33831 implements OnClickListener {
        C33831() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.ProductAddToFavoriteView$2 */
    class C33842 implements OnClickListener {
        C33842() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        }
    }

    public ProductAddToFavoriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    public ProductAddToFavoriteView(Context context) {
        super(context);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.favorite_name_layout, this);
        this.mNameInput = (EditText) findViewById(C2358R.C2357id.favorite_name_input);
        this.mCancelButton = (Button) findViewById(C2358R.C2357id.favorite_cancel_button);
        this.mCancelButton.setOnClickListener(this.mOnClickCancel);
        this.mSaveButton = (Button) findViewById(C2358R.C2357id.save_to_favorites_button);
        this.mSaveButton.setOnClickListener(this.mOnClickSave);
    }
}
