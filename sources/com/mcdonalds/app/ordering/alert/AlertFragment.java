package com.mcdonalds.app.ordering.alert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;

public abstract class AlertFragment extends URLNavigationFragment implements OnClickListener {
    public static String PARAMETER_HIDE_POSITIVE = "hide_positive";
    protected Button mNegativeButton;
    protected Button mPositiveButton;

    public abstract int getFragmentResourceId();

    public abstract void onNegativeButtonClicked();

    public abstract void onPositiveButtonClicked();

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentResourceId(), null);
        this.mNegativeButton = (Button) view.findViewById(C2358R.C2357id.button_negative);
        this.mNegativeButton.setOnClickListener(this);
        this.mPositiveButton = (Button) view.findViewById(C2358R.C2357id.button_positive);
        if (shouldHidePositive()) {
            this.mPositiveButton.setVisibility(8);
        } else {
            this.mPositiveButton.setOnClickListener(this);
        }
        return view;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (v.equals(this.mNegativeButton)) {
            onNegativeButtonClicked();
        } else if (v.equals(this.mPositiveButton)) {
            onPositiveButtonClicked();
        }
    }

    private boolean shouldHidePositive() {
        Ensighten.evaluateEvent(this, "shouldHidePositive", null);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null || !extras.getBoolean(PARAMETER_HIDE_POSITIVE, false)) {
            return false;
        }
        return true;
    }
}
