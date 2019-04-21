package android.support.p000v4.p002os;

import android.annotation.TargetApi;
import android.os.Parcelable.Creator;
import android.support.annotation.RequiresApi;

@TargetApi(13)
@RequiresApi
/* compiled from: ParcelableCompatHoneycombMR2 */
/* renamed from: android.support.v4.os.ParcelableCompatCreatorHoneycombMR2Stub */
class ParcelableCompatCreatorHoneycombMR2Stub {
    ParcelableCompatCreatorHoneycombMR2Stub() {
    }

    static <T> Creator<T> instantiate(ParcelableCompatCreatorCallbacks<T> callbacks) {
        return new ParcelableCompatCreatorHoneycombMR2(callbacks);
    }
}
