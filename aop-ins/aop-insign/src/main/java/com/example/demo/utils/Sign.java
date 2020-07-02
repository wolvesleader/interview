package com.example.demo.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author quincy
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
public @interface Sign {
}
