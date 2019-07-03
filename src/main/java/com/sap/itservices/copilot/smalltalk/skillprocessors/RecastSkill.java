package com.sap.itservices.copilot.smalltalk.skillprocessors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RecastSkill {
    //TODO: The value should be a list of string.
    String[] value() default "";
}
