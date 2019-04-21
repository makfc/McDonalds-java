package com.mcdonalds.app.ordering.summary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.util.HashMap;
import java.util.Map;

public class RatingBox extends FrameLayout implements OnClickListener, AsyncListener<Boolean> {
    private EditText mComment;
    private LinearLayout mCommentHolder;
    private boolean mIsRatingSubmitted;
    private int mRating = -1;
    private Button mSendRatingButton;
    private Button mSkipButton;
    private ImageView[] mStars;

    /* renamed from: com.mcdonalds.app.ordering.summary.RatingBox$1 */
    class C36901 implements OnClickListener {
        C36901() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            RatingBox.access$000(RatingBox.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.summary.RatingBox$2 */
    class C36912 implements OnClickListener {
        C36912() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            RatingBox.this.sendRating();
        }
    }

    static /* synthetic */ void access$000(RatingBox x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.RatingBox", "access$000", new Object[]{x0});
        x0.skip();
    }

    public RatingBox(Context context) {
        super(context);
        inflate(context);
    }

    public RatingBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.order_rate_experience, this);
        this.mCommentHolder = (LinearLayout) findViewById(C2358R.C2357id.comment_holder);
        this.mComment = (EditText) findViewById(C2358R.C2357id.comment_text);
        this.mStars = new ImageView[5];
        this.mStars[0] = (ImageView) findViewById(C2358R.C2357id.rating_star_1);
        this.mStars[1] = (ImageView) findViewById(C2358R.C2357id.rating_star_2);
        this.mStars[2] = (ImageView) findViewById(C2358R.C2357id.rating_star_3);
        this.mStars[3] = (ImageView) findViewById(C2358R.C2357id.rating_star_4);
        this.mStars[4] = (ImageView) findViewById(C2358R.C2357id.rating_star_5);
        for (ImageView img : this.mStars) {
            img.setOnClickListener(this);
        }
        findViewById(C2358R.C2357id.skip_button).setOnClickListener(new C36901());
        this.mSkipButton = (Button) findViewById(C2358R.C2357id.skip_button);
        this.mSendRatingButton = (Button) findViewById(C2358R.C2357id.send_rating_button);
        this.mSendRatingButton.setEnabled(false);
        this.mSendRatingButton.setOnClickListener(new C36912());
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (!this.mIsRatingSubmitted) {
            this.mRating = Integer.valueOf((String) v.getTag()).intValue();
            refresh();
        }
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        int i = 0;
        while (i < this.mStars.length) {
            this.mStars[i].setImageResource(i < this.mRating ? C2358R.C2359drawable.icon_star_yellow : C2358R.C2359drawable.icon_star_outline);
            i++;
        }
        if (this.mStars.length >= 1) {
            this.mSendRatingButton.setEnabled(true);
        }
    }

    private void skip() {
        Ensighten.evaluateEvent(this, "skip", null);
        if (getContext() instanceof URLNavigationActivity) {
            ((URLNavigationActivity) getContext()).startActivity(MainActivity.class, "dashboard");
        }
    }

    /* Access modifiers changed, original: protected */
    public int getRating() {
        Ensighten.evaluateEvent(this, "getRating", null);
        return this.mRating;
    }

    /* Access modifiers changed, original: protected */
    public String getComment() {
        Ensighten.evaluateEvent(this, "getComment", null);
        return UIUtils.getText(this.mComment, true);
    }

    /* Access modifiers changed, original: protected */
    public void sendRating() {
        Ensighten.evaluateEvent(this, "sendRating", null);
        if (!this.mIsRatingSubmitted) {
            UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.label_sending_rating);
            CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            Map jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_SEND_RATING);
            SparseArray customs = new SparseArray();
            customs.put(10, String.valueOf(this.mRating));
            AnalyticsUtils.trackOnClickEvent("/order-summary", "Rating", customs, jiceMap);
            module.sendRating(this.mRating, getComment(), this);
            this.mSendRatingButton.setEnabled(false);
            this.mComment.setEnabled(false);
            this.mIsRatingSubmitted = true;
        }
    }

    /* Access modifiers changed, original: protected */
    public Button getSendRatingButton() {
        Ensighten.evaluateEvent(this, "getSendRatingButton", null);
        return this.mSendRatingButton;
    }

    /* Access modifiers changed, original: protected */
    public Button getSkipButton() {
        Ensighten.evaluateEvent(this, "getSkipButton", null);
        return this.mSkipButton;
    }

    /* Access modifiers changed, original: protected */
    public void hideCommentFields(Boolean hideFields) {
        Ensighten.evaluateEvent(this, "hideCommentFields", new Object[]{hideFields});
        if (hideFields == null || !hideFields.booleanValue()) {
            this.mCommentHolder.setVisibility(0);
        } else {
            this.mCommentHolder.setVisibility(8);
        }
    }

    public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
        UIUtils.stopActivityIndicator();
    }
}
