package com.amap.api.mapcore.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.amap.api.mapcore.util.el */
public @interface EntityClass {
    /* renamed from: a */
    String mo9333a();

    /* renamed from: b */
    boolean mo9334b() default false;
}
