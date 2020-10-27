package org.example.use;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Key
 *
 * @author: quincy
 * Date: 2020/10/27 上午9:35
 * History:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Component
public @interface Key {
    String method() default "";
}
