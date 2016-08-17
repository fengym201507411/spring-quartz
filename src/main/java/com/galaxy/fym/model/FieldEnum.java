package com.galaxy.fym.model;

/**
 * Created by fengyiming on 2016/7/29.
 */
public enum FieldEnum {
    UNKNOW("没说是入参还是出参"),
    PARAM("入参"),
    RETURN("出参");

    private String name;

    private FieldEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
