package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableChar extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableChar> CREATOR = new C00051();
    private char mValue;

    /* renamed from: android.databinding.ObservableChar$1 */
    static class C00051 implements Creator<ObservableChar> {
        C00051() {
        }

        public ObservableChar createFromParcel(Parcel source) {
            return new ObservableChar((char) source.readInt());
        }

        public ObservableChar[] newArray(int size) {
            return new ObservableChar[size];
        }
    }

    public ObservableChar(char value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue);
    }
}
