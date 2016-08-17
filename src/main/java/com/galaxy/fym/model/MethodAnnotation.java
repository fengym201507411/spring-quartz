package com.galaxy.fym.model;

import java.lang.annotation.*;

/**
 * Created by fengyiming on 2016/8/16.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodAnnotation {
    /**
     * 方法的序号
     * @return
     */
    int sort() default 1;

    /**
     * 方法的描述
     * @return
     */
    String desc() default "";

    /**
     * 方法返回的数据类型
     * @return
     */
    Class<?> baseClass() default Object.class;
}
