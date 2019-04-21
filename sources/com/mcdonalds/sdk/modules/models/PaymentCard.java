package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class PaymentCard extends AppModel implements Parcelable {
    public static final Creator<PaymentCard> CREATOR = new C40741();
    private String mAlias;
    private String mExpiration;
    private String mHolderName;
    private Integer mIdentifier = Integer.valueOf(0);
    private Boolean mIsOneTimePayment;
    private Boolean mIsPreferred = Boolean.valueOf(false);
    private Boolean mIsValid = Boolean.valueOf(false);
    private boolean mNewCardStub;
    private String mNickName;
    private Integer mPaymentMethodId = Integer.valueOf(0);

    /* renamed from: com.mcdonalds.sdk.modules.models.PaymentCard$1 */
    static class C40741 implements Creator<PaymentCard> {
        C40741() {
        }

        public PaymentCard createFromParcel(Parcel source) {
            return new PaymentCard(source);
        }

        public PaymentCard[] newArray(int size) {
            return new PaymentCard[size];
        }
    }

    public Integer getIdentifier() {
        return this.mIdentifier;
    }

    public void setIdentifier(Integer identifier) {
        this.mIdentifier = identifier;
    }

    public Integer getPaymentMethodId() {
        return this.mPaymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.mPaymentMethodId = paymentMethodId;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public void setAlias(String alias) {
        this.mAlias = alias;
    }

    public String getExpiration() {
        return this.mExpiration;
    }

    public void setExpiration(String expiration) {
        this.mExpiration = expiration;
    }

    public String getHolderName() {
        return this.mHolderName;
    }

    public void setHolderName(String holderName) {
        this.mHolderName = holderName;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String nickName) {
        this.mNickName = nickName;
    }

    public Boolean isPreferred() {
        return this.mIsPreferred;
    }

    public void setIsPreferred(Boolean isPreferred) {
        this.mIsPreferred = isPreferred;
    }

    public Boolean getIsValid() {
        return this.mIsValid;
    }

    public void setIsValid(Boolean mIsValid) {
        this.mIsValid = mIsValid;
    }

    public boolean isNewCardStub() {
        return this.mNewCardStub;
    }

    public void setNewCardStub(boolean newCardStub) {
        this.mNewCardStub = newCardStub;
    }

    public Boolean getIsOneTimePayment() {
        return this.mIsOneTimePayment;
    }

    public void setIsOneTimePayment(Boolean isOneTimePayment) {
        this.mIsOneTimePayment = isOneTimePayment;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mIdentifier);
        dest.writeValue(this.mPaymentMethodId);
        dest.writeString(this.mAlias);
        dest.writeString(this.mExpiration);
        dest.writeString(this.mHolderName);
        dest.writeString(this.mNickName);
        dest.writeValue(this.mIsPreferred);
        dest.writeValue(this.mIsValid);
        dest.writeInt(this.mNewCardStub ? 1 : 0);
        dest.writeValue(this.mIsOneTimePayment);
    }

    protected PaymentCard(Parcel in) {
        boolean z;
        this.mIdentifier = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPaymentMethodId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mAlias = in.readString();
        this.mExpiration = in.readString();
        this.mHolderName = in.readString();
        this.mNickName = in.readString();
        this.mIsPreferred = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mIsValid = (Boolean) in.readValue(Boolean.class.getClassLoader());
        if (in.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.mNewCardStub = z;
        this.mIsOneTimePayment = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof PaymentCard)) {
            return false;
        }
        PaymentCard other = (PaymentCard) o;
        if (this.mIdentifier == null) {
            if (other.getIdentifier() != null) {
                return false;
            }
        } else if (!this.mIdentifier.equals(other.getIdentifier())) {
            return false;
        }
        if (this.mPaymentMethodId == null) {
            if (other.getPaymentMethodId() != null) {
                return false;
            }
        } else if (!this.mPaymentMethodId.equals(other.getPaymentMethodId())) {
            return false;
        }
        if (this.mAlias == null) {
            if (other.getAlias() != null) {
                return false;
            }
        } else if (!this.mAlias.equals(other.getAlias())) {
            return false;
        }
        if (this.mExpiration == null) {
            if (other.getExpiration() != null) {
                return false;
            }
        } else if (!this.mExpiration.equals(other.getExpiration())) {
            return false;
        }
        if (this.mHolderName == null) {
            if (other.getHolderName() != null) {
                return false;
            }
        } else if (!this.mHolderName.equals(other.getHolderName())) {
            return false;
        }
        if (this.mNickName == null) {
            if (other.getNickName() != null) {
                return false;
            }
        } else if (!this.mNickName.equals(other.getNickName())) {
            return false;
        }
        if (this.mIsPreferred == null) {
            if (other.isPreferred() != null) {
                return false;
            }
        } else if (!this.mIsPreferred.equals(other.isPreferred())) {
            return false;
        }
        if (this.mIsValid == null) {
            if (other.getIsValid() != null) {
                return false;
            }
        } else if (!this.mIsValid.equals(other.getIsValid())) {
            return false;
        }
        if (this.mIsOneTimePayment == null) {
            if (other.getIsOneTimePayment() != null) {
                return false;
            }
        } else if (!this.mIsOneTimePayment.equals(other.getIsOneTimePayment())) {
            return false;
        }
        return true;
    }
}
