package com.galaxy.fym.model;

import java.lang.annotation.*;

/**
 * Created by fengyiming on 2016/8/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpringDocument {

    /**
     * 文档的序号
     * @return
     */
    int sort() default 1;

    /**
     * 文档的所属模块
     */
    String[] module() default "";

    /**
     * 文档的描述
     * @return
     */
    String desc() default "";

    /**
     * 数据类型
     * @return
     */
    Class<?> baseClass() default Object.class;
}
