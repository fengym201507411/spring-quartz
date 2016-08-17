package com.galaxy.fym.model;

/**
 * Created by fengyiming on 2016/7/25.
 * 千万要继承Comparable<Object>,不然实现的时候无法把类当参数，参数类型为默认的Object
 */
public class FymCompareble implements Comparable<FymCompareble>{

    public Integer key;

    public String value;


    public FymCompareble(){

    }

    public FymCompareble(Integer key,String value){
        this.key = key;
        this.value = value;
    }


    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 定义了一个比较的方法
     */
    public int compare(FymCompareble obj2){
        if(this.key == null){
            return -1;
        }else if(obj2.getKey() == null){
            return 1;
        }else{
            int compareKeyValue = this.key.compareTo(obj2.getKey());
            if(compareKeyValue == 0){
                return this.value.compareTo(obj2.getValue());
            }
            return compareKeyValue;
        }
    }

    /**
     *  Collections.sort()会自动调用这个方法排序
     * @param o
     * @return
     */
    public int compareTo(FymCompareble o) {
        return this.compare(o);
    }
}
