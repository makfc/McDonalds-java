package com.mcdonalds.app.ordering.summary;

import android.view.View;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class FavoritesSaveCard {
    private final Button mAddToFavorites;
    private final View mProvideFeedback;
    private final Button mSaveCard;

    public FavoritesSaveCard(View view) {
        this.mAddToFavorites = (Button) view.findViewById(C2358R.C2357id.add_to_favorites);
        this.mSaveCard = (Button) view.findViewById(C2358R.C2357id.save_card);
        this.mProvideFeedback = view.findViewById(C2358R.C2357id.feedback_btn);
    }

    public Button getAddToFavorites() {
        Ensighten.evaluateEvent(this, "getAddToFavorites", null);
        return this.mAddToFavorites;
    }

    public Button getSaveCard() {
        Ensighten.evaluateEvent(this, "getSaveCard", null);
        return this.mSaveCard;
    }

    public View getFeedbackButton() {
        Ensighten.evaluateEvent(this, "getFeedbackButton", null);
        return this.mProvideFeedback;
    }
}
