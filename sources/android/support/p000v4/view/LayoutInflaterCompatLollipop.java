package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.p000v4.view.LayoutInflaterCompatHC.FactoryWrapperHC;
import android.view.LayoutInflater;

@TargetApi(21)
@RequiresApi
/* renamed from: android.support.v4.view.LayoutInflaterCompatLollipop */
class LayoutInflaterCompatLollipop {
    LayoutInflaterCompatLollipop() {
    }

    static void setFactory(LayoutInflater inflater, LayoutInflaterFactory factory) {
        inflater.setFactory2(factory != null ? new FactoryWrapperHC(factory) : null);
    }
}
