package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Objects;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Beta
public final class StoredCredential implements Serializable {
    public static final String DEFAULT_DATA_STORE_ID = StoredCredential.class.getSimpleName();
    private String accessToken;
    private Long expirationTimeMilliseconds;
    private final Lock lock = new ReentrantLock();
    private String refreshToken;

    public StoredCredential(Credential credential) {
        setAccessToken(credential.getAccessToken());
        setRefreshToken(credential.getRefreshToken());
        setExpirationTimeMilliseconds(credential.getExpirationTimeMilliseconds());
    }

    public String getAccessToken() {
        this.lock.lock();
        try {
            String str = this.accessToken;
            return str;
        } finally {
            this.lock.unlock();
        }
    }

    public StoredCredential setAccessToken(String accessToken) {
        this.lock.lock();
        try {
            this.accessToken = accessToken;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public Long getExpirationTimeMilliseconds() {
        this.lock.lock();
        try {
            Long l = this.expirationTimeMilliseconds;
            return l;
        } finally {
            this.lock.unlock();
        }
    }

    public StoredCredential setExpirationTimeMilliseconds(Long expirationTimeMilliseconds) {
        this.lock.lock();
        try {
            this.expirationTimeMilliseconds = expirationTimeMilliseconds;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public String getRefreshToken() {
        this.lock.lock();
        try {
            String str = this.refreshToken;
            return str;
        } finally {
            this.lock.unlock();
        }
    }

    public StoredCredential setRefreshToken(String refreshToken) {
        this.lock.lock();
        try {
            this.refreshToken = refreshToken;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        return Objects.toStringHelper(StoredCredential.class).add("accessToken", getAccessToken()).add("refreshToken", getRefreshToken()).add("expirationTimeMilliseconds", getExpirationTimeMilliseconds()).toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StoredCredential)) {
            return false;
        }
        StoredCredential o = (StoredCredential) other;
        if (Objects.equal(getAccessToken(), o.getAccessToken()) && Objects.equal(getRefreshToken(), o.getRefreshToken()) && Objects.equal(getExpirationTimeMilliseconds(), o.getExpirationTimeMilliseconds())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{getAccessToken(), getRefreshToken(), getExpirationTimeMilliseconds()});
    }
}
