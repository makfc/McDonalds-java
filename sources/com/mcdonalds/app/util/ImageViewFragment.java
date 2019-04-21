package com.mcdonalds.app.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.model.Promo;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class ImageViewFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private ImageView mImage;
    private final android.view.View.OnClickListener mOnClickImage = new C38531();
    private OnClickListener mOnClickListener;
    private Promo mPromo;
    boolean mRefreshImage;

    public interface OnClickListener {
        void onImageViewFragmentClick(Promo promo);
    }

    /* renamed from: com.mcdonalds.app.util.ImageViewFragment$1 */
    class C38531 implements android.view.View.OnClickListener {
        C38531() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ImageViewFragment", "access$000", new Object[]{ImageViewFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ImageViewFragment", "access$000", new Object[]{ImageViewFragment.this}).onImageViewFragmentClick(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ImageViewFragment", "access$100", new Object[]{ImageViewFragment.this}));
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ImageViewFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ImageViewFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ImageViewFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
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

    public static ImageViewFragment newInstance(Promo promo) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ImageViewFragment", "newInstance", new Object[]{promo});
        ImageViewFragment fragment = new ImageViewFragment();
        fragment.mPromo = promo;
        return fragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ImageViewFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ImageViewFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View view = layoutInflater.inflate(C2658R.layout.fragment_image_view, viewGroup, false);
        this.mImage = (ImageView) view.findViewById(C2358R.C2357id.header_imageview);
        this.mImage.setOnClickListener(this.mOnClickImage);
        TraceMachine.exitMethod();
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{savedInstanceState});
        super.onActivityCreated(savedInstanceState);
        if (this.mPromo == null) {
            return;
        }
        if (this.mRefreshImage) {
            Glide.with(getContext()).load(this.mPromo.getUrl()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder((int) C2358R.C2359drawable.transparent).into(this.mImage);
        } else {
            Glide.with(getContext()).load(this.mPromo.getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(false).placeholder((int) C2358R.C2359drawable.transparent).into(this.mImage);
        }
    }

    public void refreshImage(boolean refreshImage) {
        Ensighten.evaluateEvent(this, "refreshImage", new Object[]{new Boolean(refreshImage)});
        this.mRefreshImage = refreshImage;
    }

    public void refreshImageView() {
        Ensighten.evaluateEvent(this, "refreshImageView", null);
        if (this.mRefreshImage && getContext() != null && this.mPromo != null && !TextUtils.isEmpty(this.mPromo.getUrl())) {
            Glide.with(getContext()).load(this.mPromo.getUrl()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder((int) C2358R.C2359drawable.transparent).into(this.mImage);
        }
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
    }

    public void setOnClickListener(OnClickListener listener) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{listener});
        this.mOnClickListener = listener;
    }
}
