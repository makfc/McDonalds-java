package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableBoolean extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableBoolean> CREATOR = new C00031();
    private boolean mValue;

    /* renamed from: android.databinding.ObservableBoolean$1 */
    static class C00031 implements Creator<ObservableBoolean> {
        C00031() {
        }

        public ObservableBoolean createFromParcel(Parcel source) {
            boolean z = true;
            if (source.readInt() != 1) {
                z = false;
            }
            return new ObservableBoolean(z);
        }

        public ObservableBoolean[] newArray(int size) {
            return new ObservableBoolean[size];
        }
    }

    public ObservableBoolean(boolean value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue ? 1 : 0);
    }
}
