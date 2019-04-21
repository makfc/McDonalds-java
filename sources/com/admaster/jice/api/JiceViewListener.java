package com.admaster.jice.api;

public interface JiceViewListener {
    void onJiceViewClicked(String str);

    void onJiceViewDismissed();

    void onJiceViewError(String str);

    void onJiceViewShowed();

    void onJiceViewWillShow();
}
