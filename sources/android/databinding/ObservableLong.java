package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableLong extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableLong> CREATOR = new C00091();
    private long mValue;

    /* renamed from: android.databinding.ObservableLong$1 */
    static class C00091 implements Creator<ObservableLong> {
        C00091() {
        }

        public ObservableLong createFromParcel(Parcel source) {
            return new ObservableLong(source.readLong());
        }

        public ObservableLong[] newArray(int size) {
            return new ObservableLong[size];
        }
    }

    public ObservableLong(long value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mValue);
    }
}
