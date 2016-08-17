package com.galaxy.fym.model;

/**
 * Created by fengyiming on 2016/8/15.
 */
public @interface ClassRecord {

    String author() default "";

    String[] record() default {};
}
