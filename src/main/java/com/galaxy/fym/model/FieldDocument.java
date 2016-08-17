package com.galaxy.fym.model;

import java.lang.reflect.Modifier;

/**
 * Created by fengyiming on 2016/8/15.
 */
@ClassRecord(record = {"第一次编写"})
public class FieldDocument {

    @FieldAnnotation(desc = "顺序")
    private int index;

    @FieldAnnotation(desc = "字段的名称")
    private String name;

    @FieldAnnotation(desc = "字段的介绍")
    private String desc;

    @FieldAnnotation(desc = "字段的数据类型")
    private Class<?> baseClass;

    @FieldAnnotation(desc = "字段的修饰符属性")
    private int modifierValue;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class<?> getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(Class<?> baseClass) {
        this.baseClass = baseClass;
    }

    public int getModifierValue() {
        return modifierValue;
    }

    public void setModifierValue(int modifierValue) {
        this.modifierValue = modifierValue;
    }
}
