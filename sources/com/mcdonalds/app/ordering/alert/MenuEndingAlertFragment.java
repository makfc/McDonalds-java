package com.mcdonalds.app.ordering.alert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;

public class MenuEndingAlertFragment extends URLNavigationFragment implements OnClickListener {
    private Bundle mBundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBundle = getArguments();
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_menu_ending_warning, container, false);
        String menuName = this.mBundle.getString("menuName", "");
        long minutesRemaining = this.mBundle.getLong("minutesRemaining", 0);
        ((TextView) view.findViewById(C2358R.C2357id.instructions)).setText(getString(C2658R.string.label_daypart_menu_warning_ios, menuName, Long.valueOf(minutesRemaining)));
        ((Button) view.findViewById(C2358R.C2357id.button_positive)).setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        getActivity().finish();
    }
}
