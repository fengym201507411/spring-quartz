package com.galaxy.fym.model;

/**
 * Created by fengyiming on 2016/8/15.
 */
public class ParameterDocument {

    @FieldAnnotation(desc = "顺序")
    private int index;

    @FieldAnnotation(desc = "形参的名称")
    private String value;

    @FieldAnnotation(desc = "形参的默认值")
    private String defaultValue;

    @FieldAnnotation(desc = "形参的介绍")
    private String desc;

    @FieldAnnotation(desc = "形参是否为必传")
    private boolean required = true;

    @FieldAnnotation(desc = "形参的数据类型")
    private Class<?> parameterClass;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Class<?> getParameterClass() {
        return parameterClass;
    }

    public void setParameterClass(Class<?> parameterClass) {
        this.parameterClass = parameterClass;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
