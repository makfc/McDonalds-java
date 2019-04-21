package com.mcdonalds.app.home;

import com.ensighten.Ensighten;
import java.util.Observable;

@Deprecated
public class HomeListObservable extends Observable {
    /* Access modifiers changed, original: protected */
    public void setChanged() {
        Ensighten.evaluateEvent(this, "setChanged", null);
        super.setChanged();
    }
}
