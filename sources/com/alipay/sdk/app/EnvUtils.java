package com.alipay.sdk.app;

public class EnvUtils {
    private static EnvEnum mEnv = EnvEnum.ONLINE;

    public enum EnvEnum {
        ONLINE,
        SANDBOX
    }

    public static boolean isSandBox() {
        return mEnv == EnvEnum.SANDBOX;
    }
}
