package com.mcdonalds.app.offers;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.ensighten.Ensighten;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.DownloadBitmap;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Offer;

public class OfferItemView extends FrameLayout {
    private ImageView mImage;
    private TextView mNumberOfPunchesTextView;
    private Offer mOffer;
    private TextView mPunchNumberSubtitleTextView;
    private TextView mSubtitle;
    private TextView mTitle;

    public OfferItemView(Context context) {
        super(context);
    }

    public OfferItemView(Context context, Offer offer) {
        super(context);
        this.mOffer = offer;
        inflate(context);
    }

    public OfferItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        if (this.mOffer == null || !this.mOffer.isPunchCard()) {
            inflate(context, C2658R.layout.offer_list_item_base, this);
            this.mImage = (ImageView) findViewById(C2358R.C2357id.icon_imageview);
            this.mTitle = (TextView) findViewById(C2358R.C2357id.title_textview);
            this.mSubtitle = (TextView) findViewById(C2358R.C2357id.subtitle_textview);
            return;
        }
        inflate(context, C2658R.layout.punchcard_offer_list_item, this);
        this.mImage = (ImageView) findViewById(2131820643);
        this.mTitle = (TextView) findViewById(C2358R.C2357id.name);
        this.mSubtitle = (TextView) findViewById(C2358R.C2357id.expiration);
        this.mNumberOfPunchesTextView = (TextView) findViewById(C2358R.C2357id.number_of_punches);
        this.mPunchNumberSubtitleTextView = (TextView) findViewById(C2358R.C2357id.punch_number_subtitle);
        findViewById(C2358R.C2357id.added_to_order_icon).setVisibility(4);
    }

    public void display(Activity activity) {
        Ensighten.evaluateEvent(this, ServerProtocol.DIALOG_PARAM_DISPLAY, new Object[]{activity});
        this.mTitle.setText(this.mOffer.getName());
        this.mSubtitle.setText(getContext().getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(this.mOffer.getLocalValidThrough()));
        if (getContext() != null) {
            Glide.with(getContext()).load(this.mOffer.getSmallImagePath()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into((Target) new DownloadBitmap(activity, this.mImage));
        }
        if (this.mOffer.isPunchCard()) {
            int totalPunch = 0;
            int currentPunch = 0;
            if (this.mOffer.getTotalPunch() != null) {
                totalPunch = this.mOffer.getTotalPunch().intValue();
            }
            if (this.mOffer.getCurrentPunch() != null) {
                currentPunch = this.mOffer.getCurrentPunch().intValue();
            }
            int punchesLeft = totalPunch - currentPunch;
            if (currentPunch == 0) {
                this.mPunchNumberSubtitleTextView.setText(getContext().getResources().getString(C2658R.string.start_your_punchcard));
                this.mNumberOfPunchesTextView.setVisibility(8);
                return;
            }
            CharSequence string;
            this.mNumberOfPunchesTextView.setText(String.valueOf(punchesLeft));
            TextView textView = this.mPunchNumberSubtitleTextView;
            if (punchesLeft == 1) {
                string = getContext().getString(C2658R.string.punch_left);
            } else {
                string = getContext().getString(C2658R.string.punches_left);
            }
            textView.setText(string);
        }
    }

    public Offer getOffer() {
        Ensighten.evaluateEvent(this, "getOffer", null);
        return this.mOffer;
    }
}
