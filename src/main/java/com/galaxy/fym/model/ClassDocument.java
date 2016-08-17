package com.galaxy.fym.model;

import java.util.List;

/**
 * Created by fengyiming on 2016/8/15.
 */
public class ClassDocument{

    @FieldAnnotation(desc = "顺序")
    private int index;

    @FieldAnnotation(desc = "所属模块")
    private List<String> module;

    @FieldAnnotation(desc = "类的访问路径")
    private List<String> path;

    @FieldAnnotation(desc = "类所在包的路径")
    private String packagePath;

    @FieldAnnotation(desc = "类的介绍")
    private String desc;

    @FieldAnnotation(desc = "类")
    private Class<?> baseClass;

    @FieldAnnotation(desc = "类的所有字段")
    private List<FieldDocument> fieldDocumentList;

    @FieldAnnotation(desc = "类的所有方法")
    private List<MethodDocument> methodDocumentList;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getModule() {
        return module;
    }

    public void setModule(List<String> module) {
        this.module = module;
    }

    public Class<?> getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(Class<?> baseClass) {
        this.baseClass = baseClass;
    }

    public List<FieldDocument> getFieldDocumentList() {
        return fieldDocumentList;
    }

    public void setFieldDocumentList(List<FieldDocument> fieldDocumentList) {
        this.fieldDocumentList = fieldDocumentList;
    }

    public List<MethodDocument> getMethodDocumentList() {
        return methodDocumentList;
    }

    public void setMethodDocumentList(List<MethodDocument> methodDocumentList) {
        this.methodDocumentList = methodDocumentList;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}
