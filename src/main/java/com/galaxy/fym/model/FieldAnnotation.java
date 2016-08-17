package com.galaxy.fym.model;

import java.lang.annotation.*;

/**
 * Created by fengyiming on 2016/7/29.
 * Inherited：指示注释类型被自动继承
 * Retention：注释类型的注释要保留多久
 * Target：指示注释类型所适用的程序元素的种类
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldAnnotation {

    /**
     * 字段的序号
     * @return
     */
    int sort() default 1;

    /**
     * 字段的描述
     * @return
     */
    String desc() default "";

    /**
     * 字段的数据类型
     * @return
     */
    Class<?> baseClass() default Object.class;
}
