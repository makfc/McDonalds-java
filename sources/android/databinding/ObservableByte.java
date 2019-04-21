package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableByte extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableByte> CREATOR = new C00041();
    private byte mValue;

    /* renamed from: android.databinding.ObservableByte$1 */
    static class C00041 implements Creator<ObservableByte> {
        C00041() {
        }

        public ObservableByte createFromParcel(Parcel source) {
            return new ObservableByte(source.readByte());
        }

        public ObservableByte[] newArray(int size) {
            return new ObservableByte[size];
        }
    }

    public ObservableByte(byte value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.mValue);
    }
}
