package com.galaxy.fym.model;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by fengyiming on 2016/8/15.
 */
public class MethodDocument {

    @FieldAnnotation(desc = "顺序")
    private int index;

    @FieldAnnotation(desc = "方法的名称")
    private String name;

    @FieldAnnotation(desc = "方法的介绍")
    private String desc;

    @FieldAnnotation(desc = "方法的访问路径")
    private List<String> path;

    @FieldAnnotation(desc = "方法的修饰符属性")
    private int modifierValue;

    @FieldAnnotation(desc = "方法的返回值数据类型")
    private Class<?> returnClass;

    @FieldAnnotation(desc = "方法的返回视图属性")
    private Boolean isJson = false;

    @FieldAnnotation(desc = "方法的入参")
    private List<ParameterDocument> parameterDocumentList;

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

    public Class<?> getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(Class<?> returnClass) {
        this.returnClass = returnClass;
    }

    public Boolean getIsJson() {
        return isJson;
    }

    public void setIsJson(Boolean isJson) {
        this.isJson = isJson;
    }

    public List<ParameterDocument> getParameterDocumentList() {
        return parameterDocumentList;
    }

    public void setParameterDocumentList(List<ParameterDocument> parameterDocumentList) {
        this.parameterDocumentList = parameterDocumentList;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getModifierValue() {
        return modifierValue;
    }

    public void setModifierValue(int modifierValue) {
        this.modifierValue = modifierValue;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}
