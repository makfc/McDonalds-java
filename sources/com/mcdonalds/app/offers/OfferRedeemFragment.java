package com.mcdonalds.app.offers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.Barcode;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OfferBarCodeData;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.HashMap;

public class OfferRedeemFragment extends URLNavigationFragment {
    public static final String NAME = OfferRedeemFragment.class.getSimpleName();
    private OfferBarCodeData mBarCodeData;
    private ImageView mBarCodeImage;
    private TextView mBarCodeTextView;
    private boolean mIsPunchcardOffer;
    private String mOfferName;
    private final OnClickListener mOnClickDone = new C33641();
    private TextView mPunchcardTextView;

    /* renamed from: com.mcdonalds.app.offers.OfferRedeemFragment$1 */
    class C33641 implements OnClickListener {
        C33641() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            URLNavigationActivity activity = OfferRedeemFragment.this.getNavigationActivity();
            if (activity != null) {
                AnalyticsUtils.trackOnClickEvent(OfferRedeemFragment.this.getAnalyticsTitle(), "Done");
                activity.setResult(-1);
                OfferRedeemFragment.this.startActivity(MainActivity.class, "dashboard");
            }
        }
    }

    private final class GenerateCodeTask extends AsyncTask<String, Integer, Bitmap> implements TraceFieldInterface {
        public Trace _nr_trace;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        private GenerateCodeTask() {
        }

        /* synthetic */ GenerateCodeTask(OfferRedeemFragment x0, C33641 x1) {
            this();
        }

        /* Access modifiers changed, original: protected|varargs */
        public Bitmap doInBackground(String... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            return Barcode.generateAztec(Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferRedeemFragment", "access$100", new Object[]{OfferRedeemFragment.this}).getBarCodeContent(), UIUtils.dpAsPixels(OfferRedeemFragment.this.getActivity(), 200));
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Bitmap bitmap) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{bitmap});
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferRedeemFragment", "access$200", new Object[]{OfferRedeemFragment.this}).setImageBitmap(bitmap);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_offers_use_at_location);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parcelable data = getArguments().getParcelable("ARG_OFFER_BARCODE_DATA");
        if (data instanceof OfferBarCodeData) {
            this.mBarCodeData = (OfferBarCodeData) data;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mOfferName = arguments.getString("ARG_OFFER_NAME");
            this.mIsPunchcardOffer = arguments.getBoolean("ARG_IS_PUNCHCARD_OFFER");
        }
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_REDEEM_OFFER);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_offer_redeem, container, false);
        this.mBarCodeTextView = (TextView) view.findViewById(C2358R.C2357id.barcode_text);
        this.mBarCodeImage = (ImageView) view.findViewById(C2358R.C2357id.barcode_image);
        this.mPunchcardTextView = (TextView) view.findViewById(C2358R.C2357id.punchcard_textView);
        ((Button) view.findViewById(C2358R.C2357id.button_done)).setOnClickListener(this.mOnClickDone);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        int i = 0;
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(this.mOfferName);
        int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        if (titleId > 0) {
            TextView title = (TextView) getActivity().findViewById(titleId);
            if (title != null) {
                title.setSingleLine(false);
            }
        }
        TextView textView = this.mPunchcardTextView;
        if (!this.mIsPunchcardOffer) {
            i = 8;
        }
        textView.setVisibility(i);
        generateBarCodeImage();
    }

    public void onResume() {
        super.onResume();
        screenBrightness();
    }

    private void screenBrightness() {
        Ensighten.evaluateEvent(this, "screenBrightness", null);
        LayoutParams l = getActivity().getWindow().getAttributes();
        l.screenBrightness = 1.0f;
        getActivity().getWindow().setAttributes(l);
    }

    private void generateBarCodeImage() {
        Ensighten.evaluateEvent(this, "generateBarCodeImage", null);
        this.mBarCodeTextView.setText(this.mBarCodeData.getRandomCode());
        GenerateCodeTask generateCodeTask = new GenerateCodeTask(this, null);
        String[] strArr = new String[0];
        if (generateCodeTask instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(generateCodeTask, strArr);
        } else {
            generateCodeTask.execute(strArr);
        }
    }
}
