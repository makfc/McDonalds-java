package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableInt extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableInt> CREATOR = new C00081();
    private int mValue;

    /* renamed from: android.databinding.ObservableInt$1 */
    static class C00081 implements Creator<ObservableInt> {
        C00081() {
        }

        public ObservableInt createFromParcel(Parcel source) {
            return new ObservableInt(source.readInt());
        }

        public ObservableInt[] newArray(int size) {
            return new ObservableInt[size];
        }
    }

    public ObservableInt(int value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue);
    }
}
