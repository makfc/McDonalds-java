package com.mcdonalds.app.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class TutorialScreenFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    String mImageBackgroundColor;
    int mImageResourceID;
    String mTextResourceValue;

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

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
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

    public static TutorialScreenFragment newInstance(int num, int imageId, String textValue, String color) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.tutorial.TutorialScreenFragment", "newInstance", new Object[]{new Integer(num), new Integer(imageId), textValue, color});
        TutorialScreenFragment f = new TutorialScreenFragment();
        Bundle args = new Bundle();
        args.putInt("fragment-number", num);
        args.putInt("image_resource_id", imageId);
        args.putString("text_resource_name", textValue);
        args.putString("image_background_color", color);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("TutorialScreenFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TutorialScreenFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TutorialScreenFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mImageResourceID = getArguments().getInt("image_resource_id");
            this.mTextResourceValue = getArguments().getString("text_resource_name");
            this.mImageBackgroundColor = getArguments().getString("image_background_color");
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "TutorialScreenFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TutorialScreenFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View v = layoutInflater.inflate(C2658R.layout.fragment_tutorial_screen, viewGroup, false);
        ImageView iv = (ImageView) v.findViewById(C2358R.C2357id.main_imageview);
        TextView tv = (TextView) v.findViewById(C2358R.C2357id.main_textview);
        iv.setImageResource(this.mImageResourceID);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.tutorial.scaleUpTutorialImage")) {
            iv.setScaleType(ScaleType.CENTER);
        }
        if (this.mImageBackgroundColor != null) {
            iv.setBackgroundColor(Color.parseColor(this.mImageBackgroundColor));
        }
        tv.setText(this.mTextResourceValue);
        TraceMachine.exitMethod();
        return v;
    }
}
