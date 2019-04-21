package com.mcdonalds.app.account;

import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class CardHintActivity extends URLNavigationActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
    }

    /* Access modifiers changed, original: protected */
    public int getContentViewResource() {
        Ensighten.evaluateEvent(this, "getContentViewResource", null);
        return C2658R.layout.activity_card_hint;
    }

    /* Access modifiers changed, original: protected */
    public int getContainerResource() {
        Ensighten.evaluateEvent(this, "getContainerResource", null);
        return 0;
    }
}
