package android.support.p000v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.p002os.ParcelableCompat;
import android.support.p000v4.p002os.ParcelableCompatCreatorCallbacks;

/* renamed from: android.support.v4.view.AbsSavedState */
public abstract class AbsSavedState implements Parcelable {
    public static final Creator<AbsSavedState> CREATOR = ParcelableCompat.newCreator(new C00692());
    public static final AbsSavedState EMPTY_STATE = new C00681();
    private final Parcelable mSuperState;

    /* renamed from: android.support.v4.view.AbsSavedState$1 */
    static class C00681 extends AbsSavedState {
        C00681() {
            super();
        }
    }

    /* renamed from: android.support.v4.view.AbsSavedState$2 */
    static class C00692 implements ParcelableCompatCreatorCallbacks<AbsSavedState> {
        C00692() {
        }

        public AbsSavedState createFromParcel(Parcel in, ClassLoader loader) {
            if (in.readParcelable(loader) == null) {
                return AbsSavedState.EMPTY_STATE;
            }
            throw new IllegalStateException("superState must be null");
        }

        public AbsSavedState[] newArray(int size) {
            return new AbsSavedState[size];
        }
    }

    private AbsSavedState() {
        this.mSuperState = null;
    }

    protected AbsSavedState(Parcelable superState) {
        if (superState == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        if (superState == EMPTY_STATE) {
            superState = null;
        }
        this.mSuperState = superState;
    }

    protected AbsSavedState(Parcel source) {
        this(source, null);
    }

    protected AbsSavedState(Parcel source, ClassLoader loader) {
        Parcelable superState = source.readParcelable(loader);
        if (superState == null) {
            superState = EMPTY_STATE;
        }
        this.mSuperState = superState;
    }

    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mSuperState, flags);
    }
}
