package com.newrelic.agent.android.instrumentation;

public @interface ReplaceCallSite {
    boolean isStatic() default false;

    String scope() default "";
}
