package com.galaxy.fym.model;

import java.util.Comparator;

/**
 * Created by fengyiming on 2016/7/25.
 */
public class FymComparator{

    public Integer key;

    public String value;

    public FymComparator(){

    }

    public FymComparator(Integer key,String value){
        this.key = key;
        this.value = value;
    }

    /**
     * 定义了一个比较的方法
     */
    public int compare(FymComparator obj2){
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
     * 定义了一个比较器
     */
    public static Comparator<FymComparator> comparable = new Comparator<FymComparator>() {
        public int compare(FymComparator o1,FymComparator o2) {
            return o1.compare(o2);
        }
    };

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
}
