package com.newrelic.agent.android.instrumentation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface Trace {
    public static final String NULL = "";

    MetricCategory category() default MetricCategory.NONE;

    String metricName() default "";

    boolean skipTransactionTrace() default false;
}
