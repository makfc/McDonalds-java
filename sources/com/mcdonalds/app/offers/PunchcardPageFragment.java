package com.mcdonalds.app.offers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;

@Instrumented
public class PunchcardPageFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private int mCurrentPunch;
    private int mPosition;
    private String mTitle;
    private int mTotalPunch;

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    public void onViewStateRestored(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
        super.onViewStateRestored(bundle);
        Ensighten.processView((Object) this, "onViewStateRestored");
    }

    public static PunchcardPageFragment newInstance(int currentPunchCount, int totalPunchCount, int position, String offerTitle) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.PunchcardPageFragment", "newInstance", new Object[]{new Integer(currentPunchCount), new Integer(totalPunchCount), new Integer(position), offerTitle});
        Bundle extras = new Bundle();
        extras.putInt("CURRENT_PUNCH", currentPunchCount);
        extras.putInt("TOTAL_PUNCH", totalPunchCount);
        extras.putInt("POSITION", position);
        extras.putString(NativeProtocol.METHOD_ARGS_TITLE, offerTitle);
        PunchcardPageFragment punchcardPageFragment = new PunchcardPageFragment();
        punchcardPageFragment.setArguments(extras);
        return punchcardPageFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("PunchcardPageFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "PunchcardPageFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "PunchcardPageFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mCurrentPunch = getArguments().getInt("CURRENT_PUNCH");
            this.mTotalPunch = getArguments().getInt("TOTAL_PUNCH");
            this.mPosition = getArguments().getInt("POSITION");
            this.mTitle = getArguments().getString(NativeProtocol.METHOD_ARGS_TITLE);
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "PunchcardPageFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "PunchcardPageFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View rootView = layoutInflater.inflate(C2658R.layout.fragment_punchcard_page, viewGroup, false);
        LinearLayout tableLayout = (LinearLayout) rootView.findViewById(C2358R.C2357id.table);
        LinearLayout topRow = (LinearLayout) rootView.findViewById(C2358R.C2357id.top_row);
        LinearLayout bottomRow = (LinearLayout) rootView.findViewById(C2358R.C2357id.bottom_row);
        ((TextView) rootView.findViewById(C2358R.C2357id.punchcard_name)).setText(this.mTitle);
        List<View> punchcardPunchViewsTop = new ArrayList();
        List<View> punchcardPunchViewsBottom = new ArrayList();
        LayoutInflater layoutInflater2 = LayoutInflater.from(getActivity());
        int currentPunchCounter = this.mCurrentPunch;
        for (int i = 0; i < this.mTotalPunch; i++) {
            int color;
            Drawable drawable;
            TextView punchcardPunch = (TextView) layoutInflater2.inflate(C2658R.layout.punchcard_punch_circle, null);
            LayoutParams layoutParams = new LayoutParams(UIUtils.dpAsPixels(getActivity(), 42), UIUtils.dpAsPixels(getActivity(), 42));
            int margins = UIUtils.dpAsPixels(getActivity(), 4);
            layoutParams.setMargins(margins, margins, margins, margins);
            punchcardPunch.setLayoutParams(layoutParams);
            punchcardPunch.setText(String.valueOf((i + 1) + (this.mPosition * 10)));
            if (currentPunchCounter > 0) {
                color = getResources().getColor(17170443);
            } else {
                color = getResources().getColor(C2658R.color.dark_gray_2);
            }
            punchcardPunch.setTextColor(color);
            if (currentPunchCounter > 0) {
                drawable = getResources().getDrawable(C2358R.C2359drawable.punchcard_yellow);
            } else {
                drawable = getResources().getDrawable(C2358R.C2359drawable.punchcard_dashed);
            }
            punchcardPunch.setBackgroundDrawable(drawable);
            currentPunchCounter--;
            if (punchcardPunchViewsTop.size() < 5) {
                punchcardPunchViewsTop.add(punchcardPunch);
            } else {
                punchcardPunchViewsBottom.add(punchcardPunch);
            }
        }
        for (View view : punchcardPunchViewsTop) {
            topRow.addView(view);
        }
        for (View view2 : punchcardPunchViewsBottom) {
            bottomRow.addView(view2);
        }
        TraceMachine.exitMethod();
        return rootView;
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }
}
