package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class ApplicationInfo extends Table {
    public static ApplicationInfo getRootAsApplicationInfo(ByteBuffer _bb) {
        return getRootAsApplicationInfo(_bb, new ApplicationInfo());
    }

    public static ApplicationInfo getRootAsApplicationInfo(ByteBuffer _bb, ApplicationInfo obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public ApplicationInfo __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public ApplicationLicense applicationLicense() {
        return applicationLicense(new ApplicationLicense());
    }

    public ApplicationLicense applicationLicense(ApplicationLicense obj) {
        int o = __offset(4);
        return o != 0 ? obj.__assign(__indirect(this.bb_pos + o), this.f6700bb) : null;
    }

    public int platform() {
        int o = __offset(6);
        return o != 0 ? this.f6700bb.get(this.bb_pos + o) & 255 : 0;
    }

    public boolean mutatePlatform(int platform) {
        int o = __offset(6);
        if (o == 0) {
            return false;
        }
        this.f6700bb.put(this.bb_pos + o, (byte) platform);
        return true;
    }

    public String appVersion() {
        int o = __offset(8);
        return o != 0 ? __string(this.bb_pos + o) : null;
    }

    public ByteBuffer appVersionAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public long appVersionId() {
        int o = __offset(10);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateAppVersionId(long appVersionId) {
        int o = __offset(10);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, appVersionId);
        return true;
    }

    public static int createApplicationInfo(FlatBufferBuilder builder, int applicationLicenseOffset, int platform, int appVersionOffset, long appVersionId) {
        builder.startObject(4);
        addAppVersionId(builder, appVersionId);
        addAppVersion(builder, appVersionOffset);
        addApplicationLicense(builder, applicationLicenseOffset);
        addPlatform(builder, platform);
        return endApplicationInfo(builder);
    }

    public static void startApplicationInfo(FlatBufferBuilder builder) {
        builder.startObject(4);
    }

    public static void addApplicationLicense(FlatBufferBuilder builder, int applicationLicenseOffset) {
        builder.addOffset(0, applicationLicenseOffset, 0);
    }

    public static void addPlatform(FlatBufferBuilder builder, int platform) {
        builder.addByte(1, (byte) platform, 0);
    }

    public static void addAppVersion(FlatBufferBuilder builder, int appVersionOffset) {
        builder.addOffset(2, appVersionOffset, 0);
    }

    public static void addAppVersionId(FlatBufferBuilder builder, long appVersionId) {
        builder.addLong(3, appVersionId, 0);
    }

    public static int endApplicationInfo(FlatBufferBuilder builder) {
        return builder.endObject();
    }
}
