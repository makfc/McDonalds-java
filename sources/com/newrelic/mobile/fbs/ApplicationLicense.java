package com.newrelic.mobile.fbs;

import com.newrelic.com.google.flatbuffers.FlatBufferBuilder;
import com.newrelic.com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class ApplicationLicense extends Table {
    public static ApplicationLicense getRootAsApplicationLicense(ByteBuffer _bb) {
        return getRootAsApplicationLicense(_bb, new ApplicationLicense());
    }

    public static ApplicationLicense getRootAsApplicationLicense(ByteBuffer _bb, ApplicationLicense obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
    }

    public void __init(int _i, ByteBuffer _bb) {
        this.bb_pos = _i;
        this.f6700bb = _bb;
    }

    public ApplicationLicense __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public String licenseKey() {
        int o = __offset(4);
        return o != 0 ? __string(this.bb_pos + o) : null;
    }

    public ByteBuffer licenseKeyAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public long accountId() {
        int o = __offset(6);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateAccountId(long accountId) {
        int o = __offset(6);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, accountId);
        return true;
    }

    public long clusterAgentId() {
        int o = __offset(8);
        return o != 0 ? this.f6700bb.getLong(this.bb_pos + o) : 0;
    }

    public boolean mutateClusterAgentId(long clusterAgentId) {
        int o = __offset(8);
        if (o == 0) {
            return false;
        }
        this.f6700bb.putLong(this.bb_pos + o, clusterAgentId);
        return true;
    }

    public static int createApplicationLicense(FlatBufferBuilder builder, int licenseKeyOffset, long accountId, long clusterAgentId) {
        builder.startObject(3);
        addClusterAgentId(builder, clusterAgentId);
        addAccountId(builder, accountId);
        addLicenseKey(builder, licenseKeyOffset);
        return endApplicationLicense(builder);
    }

    public static void startApplicationLicense(FlatBufferBuilder builder) {
        builder.startObject(3);
    }

    public static void addLicenseKey(FlatBufferBuilder builder, int licenseKeyOffset) {
        builder.addOffset(0, licenseKeyOffset, 0);
    }

    public static void addAccountId(FlatBufferBuilder builder, long accountId) {
        builder.addLong(1, accountId, 0);
    }

    public static void addClusterAgentId(FlatBufferBuilder builder, long clusterAgentId) {
        builder.addLong(2, clusterAgentId, 0);
    }

    public static int endApplicationLicense(FlatBufferBuilder builder) {
        int o = builder.endObject();
        builder.required(o, 4);
        return o;
    }

    /* Access modifiers changed, original: protected */
    public int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
        return Table.compareStrings(Table.__offset(4, o1.intValue(), _bb), Table.__offset(4, o2.intValue(), _bb), _bb);
    }

    public static ApplicationLicense __lookup_by_key(int vectorLocation, String key, ByteBuffer bb) {
        byte[] byteKey = key.getBytes((Charset) Table.UTF8_CHARSET.get());
        int span = bb.getInt(vectorLocation - 4);
        int start = 0;
        while (span != 0) {
            int middle = span / 2;
            int tableOffset = Table.__indirect(((start + middle) * 4) + vectorLocation, bb);
            int comp = Table.compareStrings(Table.__offset(4, bb.array().length - tableOffset, bb), byteKey, bb);
            if (comp > 0) {
                span = middle;
            } else if (comp >= 0) {
                return new ApplicationLicense().__assign(tableOffset, bb);
            } else {
                middle++;
                start += middle;
                span -= middle;
            }
        }
        return null;
    }
}
