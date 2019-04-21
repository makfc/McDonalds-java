package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableShort extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableShort> CREATOR = new C00111();
    private short mValue;

    /* renamed from: android.databinding.ObservableShort$1 */
    static class C00111 implements Creator<ObservableShort> {
        C00111() {
        }

        public ObservableShort createFromParcel(Parcel source) {
            return new ObservableShort((short) source.readInt());
        }

        public ObservableShort[] newArray(int size) {
            return new ObservableShort[size];
        }
    }

    public ObservableShort(short value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue);
    }
}
